package ch.supsi.dti.miniproject.financialproduct;

import ch.supsi.dti.miniproject.exception.FinancialProductException;
import ch.supsi.dti.miniproject.exception.NumberOfCardsLimitReachedException;
import ch.supsi.dti.miniproject.exception.NumberOfCreditCardsLimitReachedException;
import ch.supsi.dti.miniproject.financialproduct.account.CurrentAccount;
import ch.supsi.dti.miniproject.financialproduct.account.SavingsAccount;
import ch.supsi.dti.miniproject.financialproduct.actions.Balanceable;
import ch.supsi.dti.miniproject.financialproduct.actions.WithdrawableWithCard;
import ch.supsi.dti.miniproject.financialproduct.card.ATMCard;
import ch.supsi.dti.miniproject.financialproduct.card.Card;
import ch.supsi.dti.miniproject.financialproduct.card.CreditCard;
import ch.supsi.dti.miniproject.financialproduct.enums.Circuit;
import ch.supsi.dti.miniproject.financialproduct.insurance.LifeInsurance;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.time.LocalDate;
import java.util.*;

public class Bundle extends FinancialProduct {

    private static final int CARDS_LIMIT = 20;

    final private Set<FinancialProduct> products = new HashSet<>();
    public Bundle(LocalDate openingDate, Banker banker, Person customer, boolean withLifeInsurance) {
        super(openingDate, banker, customer);
        try {
            this.addFinancialProduct(new CreditCard(openingDate, banker, customer, 5000.0, 0.09, true, Circuit.MASTERCARD, "0000000000000000", 12, 24, 456));
            FinancialProduct currentAccount = new CurrentAccount(openingDate, banker, customer, 2000.0, 0.0);
            this.addFinancialProduct(currentAccount);
            FinancialProduct savingsAccount = new SavingsAccount(openingDate, banker, customer, 0.001, 0.0);
            this.addFinancialProduct(savingsAccount);
            this.addFinancialProduct((new ATMCard(openingDate, banker, customer, (WithdrawableWithCard) currentAccount, Circuit.MASTERCARD, "0000000000000000", 12, 14, 231)));
            this.addFinancialProduct((new ATMCard(openingDate, banker, customer, (WithdrawableWithCard) savingsAccount, Circuit.MASTERCARD, "0000000000000000", 12, 14, 231)));
            if (withLifeInsurance) {
                this.addFinancialProduct(new LifeInsurance(openingDate, banker, customer, 300.0));
            }
        }catch(NumberOfCardsLimitReachedException e) {
            e.printStackTrace();
        }
    }


    private boolean addFinancialProduct(FinancialProduct financialProduct) throws NumberOfCardsLimitReachedException {
        int cards = 0;
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();){
            if(i.next() instanceof Card)
                cards++;
        }
        if(cards >= CARDS_LIMIT)
            throw new NumberOfCardsLimitReachedException(this,CARDS_LIMIT);

        this.products.add(financialProduct);
        return true;
    }

    public double getFullAssetBalance() {
        double totalBalance = 0.0;
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();){
            FinancialProduct product = i.next();
            if(product instanceof Balanceable) {
                totalBalance += ((Balanceable)product).getBalance();
            }
        }
        return totalBalance;
    }

    public boolean canPayCreditCardBillInFull() {
        double totalCreditCardsBill = 0.0;
        double totalCurrentAccountBalances = 0.0;
        for(Iterator<FinancialProduct> i = this.products.iterator(); i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof CurrentAccount){
                totalCurrentAccountBalances += ((CurrentAccount)product).getBalance();
            }
            else if(product instanceof CreditCard){
                totalCreditCardsBill += ((CreditCard)product).getCurrentSpending();
            }
        }
        return totalCurrentAccountBalances > totalCreditCardsBill;
    }

    public Set<CreditCard> getCreditCard() {
        Set<CreditCard> creditCards = new HashSet<>();
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof CreditCard) {
                creditCards.add((CreditCard)product);
            }
        }
        return creditCards;
    }

    public Set<CurrentAccount> getCurrentAccount() {
        Set<CurrentAccount> currentAccounts = new HashSet<>();
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof CurrentAccount) {
                currentAccounts.add((CurrentAccount) product);
            }
        }
        return currentAccounts;
    }

    public Set<SavingsAccount> getSavingsAccount() {
        Set<SavingsAccount> savingsAccounts = new HashSet<>();
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof SavingsAccount) {
                savingsAccounts.add((SavingsAccount) product);
            }
        }
        return savingsAccounts;
    }

    public Set<ATMCard> getATMCard() {
        Set<ATMCard> atmCards = new HashSet<>();
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof ATMCard) {
                atmCards.add((ATMCard) product);
            }
        }
        return atmCards;
    }

    public Set<LifeInsurance> getLifeInsurance() {
        Set<LifeInsurance> lifeInsurances = new HashSet<>();
        for(Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            FinancialProduct product = i.next();
            if(product instanceof LifeInsurance) {
                lifeInsurances.add((LifeInsurance) product);
            }
        }
        return lifeInsurances;
    }

    public boolean addCreditCard() throws NumberOfCardsLimitReachedException, NumberOfCreditCardsLimitReachedException {
        if (!(getCustomer() instanceof Banker))
            return false;
        int count = 0;
        for (Iterator<FinancialProduct> i = this.products.iterator();i.hasNext();) {
            if (i.next() instanceof CreditCard)
                count++;
        }

        if (count >= 6)
            throw new NumberOfCreditCardsLimitReachedException(this);

        this.addFinancialProduct(new CreditCard(LocalDate.now(), getBanker(), getCustomer(), 5000.0, 0.09, true, Circuit.MASTERCARD, "0000000000000000", 12, 24, 456));
        return true;
    }

}
