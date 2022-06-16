package ch.supsi.dti.miniproject;

import ch.supsi.dti.miniproject.exception.*;
import ch.supsi.dti.miniproject.financialproduct.Bundle;
import ch.supsi.dti.miniproject.financialproduct.account.CurrentAccount;
import ch.supsi.dti.miniproject.financialproduct.account.SavingsAccount;
import ch.supsi.dti.miniproject.financialproduct.card.ATMCard;
import ch.supsi.dti.miniproject.financialproduct.card.CreditCard;
import ch.supsi.dti.miniproject.financialproduct.insurance.LifeInsurance;
import ch.supsi.dti.miniproject.financialproduct.tellermachine.TellerMachine;
import ch.supsi.dti.miniproject.person.Banker;
import ch.supsi.dti.miniproject.person.Person;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {

        Person andrea = new Person("Andrea", "Bernasconi");
        Banker giorgio = new Banker("Giorgio", "Rezzonico", "Lugano");

        Bundle bundle = new Bundle(LocalDate.now(), giorgio, andrea, true);

        // Testing Savings account
        System.out.println("*** Testing savings account ***");
        SavingsAccount sa = new ArrayList<>(bundle.getSavingsAccount()).get(0);
        try {
            sa.deposit(1000.0);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) {
            try {
                sa.withdraw(100.0);
            } catch (FundsNotSufficientException e) {
                e.printStackTrace();
            } catch (InvalidAmountException e) {
                e.printStackTrace();
            }
        }

        System.out.println("After 8 100 CHF withdrawals, do I have 200 CHF left? " + (sa.getBalance() == 200.0));
        try {
            sa.withdraw(100.0);
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
        System.out.println("The 9th withdrawal was with fee? " + (sa.getBalance() == 90.0));
        try {
            System.out.print("Balance can go negative? ");
            sa.withdraw(1000.0);
        } catch (FundsNotSufficientException e) {
            System.out.println("Funds cannot go negative :-)");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
        System.out.println("The savings account is under surveillance? " + sa.isUnderSurveillance());
        // Testing Current account
        System.out.println("*** Testing current account ***");
        CurrentAccount ca = new ArrayList<>(bundle.getCurrentAccount()).get(0);
        try {
            ca.deposit(1000.0);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
        try {
            ca.withdraw(100.0);
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
        System.out.println("Is the withdrawal with fee? " + (ca.getBalance() == 898.0));
        try {
            System.out.print("Is it possible to go negative with withdrawals? ");
            ca.withdraw(2000.0);
            System.out.println("YES!");
        } catch (FundsNotSufficientException e) {
            System.out.println("NO!");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }

        try {
            System.out.print("Is it possible to go negative with direct debit? ");
            ca.directDebit(2000.0);
            System.out.println("YES!");
        } catch (FundsNotSufficientException e) {
            System.out.println("NO!");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }

        try {
            System.out.print("Can you go negative more than allowed? ");
            ca.directDebit(10000.0);
            System.out.println("YES!");
        } catch (FundsNotSufficientException e) {
            System.out.println("NO!");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }

        // Testing Credit Card
        System.out.println("*** Testing credit card ***");
        CreditCard cc = new ArrayList<>(bundle.getCreditCard()).get(0);
        try {
            System.out.println("Can spend more than the max limit? ");
            cc.registerTransaction(cc.getCardLimit() + 100.0);
            System.out.println("YES!");
        } catch (FundsNotSufficientException e) {
            System.out.println("NO!");
        }

        try {
            cc.registerTransaction(1000.0);
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        }
        System.out.println("Card is managing transaction the right way? " + (cc.getCurrentSpending() == 1000.0));
        System.out.print("Bill payment is refused? ");
        try {
            cc.payBill(1000.0);
            System.out.println("NO!");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        } catch (PaymentRefusedException e) {
            System.out.println("YES!");
        }
        System.out.println("Can correctly pay in full? " + (cc.getCurrentSpending() == 0.0));
        System.out.print("Payment is refused? ");
        try {
            cc.registerTransaction(1000.0);
            cc.payBill(900.0);
            System.out.println("NO!");
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (PaymentRefusedException e) {
            System.out.println("YES!");
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }

        System.out.println("Are interests correctly managed? " + (cc.getCurrentSpending() == 109.0));

        // Testing ATM Card
        System.out.println("*** Testing ATM Current account card ***");
        ATMCard cac = new ArrayList<>(bundle.getATMCard()).get(0);
        try {
            cac.withdrawWitPin(100, 0000);
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        } catch (PinNotValidException e) {
            e.printStackTrace();
        } catch (CardBlockedException e) {
            e.printStackTrace();
        }
        System.out.println("After 100 CHF withdrawal, current account is -1202 CHF? " + (ca.getBalance() == -1202));

        System.out.println("*** Testing ATM Savings account card ***");
        ATMCard csc = new ArrayList<>(bundle.getATMCard()).get(1);
        try {
            csc.withdrawWitPin(50, 0000);
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        } catch (PinNotValidException e) {
            e.printStackTrace();
        } catch (CardBlockedException e) {
            e.printStackTrace();
        }
        System.out.println("After 50 CHF withdrawal, current account is 30 CHF? " + (sa.getBalance() == 30));

        // Testing life insurance
        LifeInsurance li = new ArrayList<>(bundle.getLifeInsurance()).get(0);
        try {
            li.deposit(10000);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
        System.out.println("*** Testing Life Insurance ***");
        System.out.println("Do I have 10000 CHF on life insurance? " + (li.getBalance() == 10000.0));
        System.out.println("The life insurance is under surveillance? " + li.isUnderSurveillance());
        // Testing bundle
        System.out.println("*** Testing Bundle ***");
        System.out.println("The total assets is 8828.0 CHF? " + (bundle.getFullAssetBalance() == 8828.0));
        System.out.println("Is it true that customer doesn't have enough money to pay CC in full? " + (!bundle.canPayCreditCardBillInFull()));

        // Testing the teller machine
        System.out.println("*** Testing teller machine");
        try {
            System.out.println("Got the withdrawal done: " + TellerMachine.withdraw(cac, 100.0, 0000));
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        } catch (PinNotValidException e) {
            e.printStackTrace();
        } catch (FundsNotSufficientException e) {
            e.printStackTrace();
        } catch (CardBlockedException e) {
            e.printStackTrace();
        }

        try {
            ca.generateStatement();
        } catch (AccountStatementException e) {
            e.printStackTrace();
        }

        try {
            sa.generateStatement();
        } catch (AccountStatementException e) {
            throw new RuntimeException(e);
        }


        // Trying to serialize everything in a file
        try {
            File file = new File("Bernasconi_bundle.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(bundle);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Trying to deserialize everything
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Bernasconi_bundle.dat")))){
            Bundle loadedBundle = (Bundle) ois.readObject();
        }catch(IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
