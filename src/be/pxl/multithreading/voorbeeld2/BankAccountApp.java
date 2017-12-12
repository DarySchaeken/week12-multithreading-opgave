package be.pxl.multithreading.voorbeeld2;

public class BankAccountApp {
	public static void main(String[] args) {
		BankAccount account = new BankAccount("12345-678", 10000);
		Thread depositer = new Thread(() -> account.deposit(1000));
		Thread withdrawer = new Thread(() -> account.withdraw(500));
		depositer.start();
		withdrawer.start();
		try {
			depositer.join();
			withdrawer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(account.getBalance());
		
	}
}
