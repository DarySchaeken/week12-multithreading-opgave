package be.pxl.multithreading.voorbeeld2;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
	private AtomicInteger balance;
	private String accountNumber;
	
	public BankAccount(String accountNumber, int initialBalance) {
		this.accountNumber = accountNumber;
		this.balance = new AtomicInteger(initialBalance);
	}
	
	public void deposit(int amount) {
		balance.addAndGet(amount);
	}
	
	public void withdraw(int amount) {
		balance.addAndGet(-amount);
	}
	
	public double getBalance() {
		return balance.doubleValue();
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
}
