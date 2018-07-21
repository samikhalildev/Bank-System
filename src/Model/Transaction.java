package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	private String date;
	private String transfer;
	private String withdraw;
	private String deposit;
	private double balance;
	
	public Transaction(String transfer, String withdraw, String deposit, double balance){
		this.date = addDate();
		this.transfer = transfer;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.balance = balance;
	}
	
	private String addDate(){
		Date d = new Date();
		DateFormat dateFormatter = new SimpleDateFormat("h:m a EEEE MMMM d yyyy");
		String dateNow = dateFormatter.format(d); 
		
		return dateNow;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTransfer(){
		return transfer;
	}
	
	public String getWithdraw(){
		return withdraw;
	}
	
	public String getDeposit(){
		return deposit;
	}
	
	public double getBalance(){
		return balance;
	}
	
	@Override
	public String toString(){
		return date + "\t" + transfer + "\t" + withdraw + "\t" + deposit + "\t" + balance;
	}
}
