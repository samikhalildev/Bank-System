package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Bank {
	
	private ObservableList <Customer> customersList = FXCollections.observableArrayList();
	private HashMap <Customer, Account> customersAccountsTable = new HashMap<>();
	
	public Bank(){

	}
	
	/*
	 * Checking Username, password and type
	 */
	
	public boolean isUsernameTaken(String username){
		for(Customer customer: customersList)
			if(customer.hasUserName(username))
				return true;
		
		return false;
	}
	
	
	public String nameCheck(String name){
		if(!name.isEmpty())
			name = name.toLowerCase();
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		return name;
	}
	
	
	/*
	 * Adding customer and account
	 */
	
	public boolean isNotACustomer (String name, int age, String gender, String location){
		
		for(Customer customer: customersList)
			if(customer.isACustomer(name, age, gender, location))
				return false;
		
		return true;
	}
	
	public void addCustomer(Customer customer){		
		customersList.add(customer);
		saveCustomerToFile(customer);
	}
	
	public void removeCustomer(Customer customer){
		customersList.remove(customer);
	}
	
	private void saveCustomerToFile(Customer customer){	
		
		try {
			//create a file based on username
			String fileName = customer.getUsername() + ".txt";
			File file = new File(fileName);
			
			if(!file.exists())
				file.createNewFile();

			PrintWriter writer = new PrintWriter(file);
			writer.println(customer);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Adding customers and accounts from files into a list
	 */
	
	public void addingCustomerToList() throws IOException{
		
		//gets file location/directory
		String directory = System.getProperty("user.dir");
		File dir = new File(directory);
		
		String fileName = "";
		
		//list of files on this directory
		for(File fileFound: dir.listFiles())
			
			//if file has these names then store it in a variable
			if(fileFound.getName().equals("Sami96.txt") || fileFound.getName().equals("DanielMazani.txt") || fileFound.getName().equals("JackMarios.txt") || fileFound.getName().equals("JohnToz.txt") || fileFound.getName().equals("KrisTom.txt") || fileFound.getName().equals("OwenOzi.txt") || fileFound.getName().equals("ToniYako.txt")){
				fileName = fileFound.getName();
				BufferedReader scan = null;
				
				try {
					FileReader newFile = new FileReader(fileName);
					scan = new BufferedReader(newFile);
					
					//Add customer
					String customerObject;
					
					if ((customerObject = scan.readLine()) != null){
						Customer newCustomer = findCustomer(customerObject);
						customersList.add(newCustomer);
						
						//Add account
						String accountObject;
						
						while ((accountObject = scan.readLine()) != null){
							Account newAccount = new Account(accountObject);
							newCustomer.addAccountFromFile(newAccount);
						}
						
					} 
						
				} catch (Exception e) {
					e.printStackTrace();
				
				} finally {
					
					if(scan != null)
						scan.close();
			}
		}
	}
	
	private Customer findCustomer(String customerObject) {
		String name = "";
		int age = -1;
		String gender = "";
		String location = "";
		String username = "";
		String password = "";
		
		for(String field: customerObject.split(" +")){
			if(name.isEmpty())
				name = field;
			
			else if(age == -1)
				age = Integer.parseInt(field);
			
			else if(gender.isEmpty())
				gender = field;
			
			else if(location.isEmpty())
				location = field;
			
			else if(username.isEmpty())
				username = field;
			
			else if(password.isEmpty())
				password = field;
		}
		
		return new Customer(name, age, gender, location, username, password);		
	}
	
	public void AddTable(Customer customer, Account account){
		customersAccountsTable.put(customer, account);
	}
	
	public Account lookUpAccount(Customer customer){
		Account account = customersAccountsTable.get(customer);
		return account;
	}

	//print out all the keys(customers) and values(accounts)
	public HashMap <Customer, Account> getAllKeysValues(){
		
		for(Customer key: customersAccountsTable.keySet()){
			Account value = customersAccountsTable.get(key);
			System.out.println(key + " " + value);
		}
		
		return customersAccountsTable;
	}
	
	
	/*
	 * Login
	 */
	
	public Customer lookUpCustomer(String username, String password){
		for(Customer customer: customersList)
			if(customer.hasUsernameAndPassword(username, password))
				return customer;
		
		return null;
	}
	
	
	/*
	 * Transfer - lookup
	 */
	
	public Customer customerAvailable(String name){
		for(Customer customer: customersList)
			if(customer.hasName(name))
				return customer;
		
		return null;
	}

		
	public final ObservableList<Customer> getCustomers(){
		return customersList;
	}
	
}
