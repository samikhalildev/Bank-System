package Model;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Random;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Account {
	
	private DoubleProperty balance = new SimpleDoubleProperty();
	private StringProperty accountType = new SimpleStringProperty();

	private int accountNumber;
	private int BSB;
	
	private DoubleProperty totalDeposits = new SimpleDoubleProperty();
	private DoubleProperty totalWithdrawn = new SimpleDoubleProperty();
	private DoubleProperty totalTransfers = new SimpleDoubleProperty();
	
	private Random random = new Random();
		
	private ObservableList <Transaction> transactionsList = FXCollections.observableArrayList();

	public Account(String accountType){
		this.accountType.set(accountType);
		this.balance.set(0.0);
		
		this.accountNumber = numberGenerator();
		this.BSB = numberGenerator();
	}
	
	private int numberGenerator(){
		int num = 0;
	    for (int i = 0; i < 10; i++)    
	       num = random.nextInt(999999) + 100000;
	    return num;   
	} 
	
	public int getAccountNumber(){
		return accountNumber;
	}
	
	public int getBSB(){
		return BSB;
	}
	
	public String getBalance(){
		return formatted(balance.get());
	}
	
	public String getAccountType(){
		return accountType.get();
	}
	
	public void setAccountType(String newAccountType){
		accountType.set(newAccountType);
	}
	
	
	public boolean hasEnoughFunds(double amount){	
		return balance.get() >= amount;
	}
	
	public void deposit(double amount){
		balance.set(balance.get() + amount);
		totalDepWit(totalDeposits, amount);
		transactionsList.add(new Transaction("", "", "+ $" + amount, balance.get()));
	}
	
	public void withdraw(double amount){
		balance.set(balance.get() - amount);
		totalDepWit(totalWithdrawn, amount);
		transactionsList.add(new Transaction("", "- $" + amount, "", balance.get()));
	}
	
	private void totalDepWit(DoubleProperty depWitTra, double amount){
		depWitTra.set(depWitTra.get() + amount);
	}
	
	
	public void transfer(String fromCustomer, String toCustomer, Account otherAccount, double amount){
		balance.set(balance.get() - amount);
		otherAccount.transferTo(fromCustomer, amount);
		totalDepWit(totalTransfers, amount);
		transactionsList.add(new Transaction("- $" + amount + " to " + toCustomer, "", "", balance.get()));
	}
	
	public void transferTo(String fromCustomer, double amount){
		balance.set(balance.get() + amount);
		totalDepWit(totalTransfers, amount);
		transactionsList.add(new Transaction("+ $" + amount + " from " + fromCustomer, "", "", balance.get()));
	}

	public ReadOnlyDoubleProperty balanceProperty(){
		return balance;
	}

	public ReadOnlyDoubleProperty totalDepositsProperty(){
		return totalDeposits;
	}
	
	public ReadOnlyDoubleProperty totalWithdrawnProperty(){
		return totalWithdrawn;
	}
	
	public ReadOnlyDoubleProperty totalTransferProperty(){
		return totalTransfers;
	}
	
	public StringProperty accountTypeProperty(){
		return accountType;
	}

	public boolean hasAccountDetails(int accountNumber, int BSB) {
		return (this.accountNumber == accountNumber) && (this.BSB == BSB);
	}
	
	
	public String formatted(double d){
		DecimalFormat f = new DecimalFormat("###,##0.00");
		return f.format(d);
	}
	
	public void reverseTransactionsList(){
		Collections.reverse(transactionsList);
	}
	
	
	public final ObservableList<Transaction> getTransactions(){
		return transactionsList;
	}
	
	@Override
	public String toString(){
		return accountType.get();
	}

}
