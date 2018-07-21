package Model;

import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
	
	private String name;
	private int age;
	private String gender;
	private String location;	
	
	private String username;
	private String password;
	
	private ObservableList <Account> accountsList = FXCollections.observableArrayList();
	
	public Customer(String name, int age, String gender, String location, String username, String password){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.location = location;
		this.username = username;
		this.password = password;		
	}

	public final String getName(){
		return name;
	}
	
	public final int getAge(){
		return age;
	}
	
	public final String getGender(){
		return gender;
	}
	
	public final String getLocation(){
		return location;
	}
	
	public final String getUsername(){
		return username;
	}
	
	public final String getPassword(){
		return password;
	}
	
	/*
	 * Checking
	 */
	
	public boolean hasName(String name){
		return this.name.equals(name);
	}
	
	public boolean hasUserName(String username) {
		return this.username.equals(username);
	}
	
	public boolean hasUsernameAndPassword(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
	public boolean isACustomer(String name, int age, String gender, String location) {
		return (this.name.equals(name)) && (this.age == age) && (this.gender.equals(gender)) && (this.location.equals(location));
	}
	
	public void addAccount(Account account){
		accountsList.add(account);
		saveAccountToFile(account);
	}
	
	public void addAccountFromFile(Account newAccount) {
		accountsList.add(newAccount);
	}
	
	private void saveAccountToFile(Account account){
		
		try{
			String fileName = getUsername() + ".txt";
			
			FileWriter file = new FileWriter(fileName, true);
			PrintWriter writer = new PrintWriter(file);
			writer.append(account.toString() + "\r\n");
			writer.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void removeAccount(Account account){
		accountsList.remove(account);
	}
	
	public final ObservableList<Account> getAccounts(){
		return accountsList;
	}

	/*
	 * Transfer - lookup
	 */
	
	public Account accountAvailable(int accountNumber, int BSB){
		for(Account account: accountsList)
			if(account.hasAccountDetails(accountNumber, BSB))
				return account;
		
		return null;
	}
	
	@Override
	public String toString(){
		return name + " " + age + " " + gender + " " + location + " " + username + " " + password;
	}

}
