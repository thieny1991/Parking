import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JTextField;

public class FinanceManager {

	private double pricePerHour;
	private double revenue;
	private String report;
	
	
	public FinanceManager(){
		pricePerHour=5.0;
		revenue=0;
		report="";
	}
	public FinanceManager(double price,double revenue) {
		this.pricePerHour=price;
		this.revenue=revenue;
	}
	
	public FinanceManager(double price) {
		this.pricePerHour=price;
		this.revenue=0;
	}
	
	public void setPrice(double price) {
		this.pricePerHour = price;
	}
	public double getPrice() {
		return this.pricePerHour;
	}
	public void rAdd(double r) {
		this.revenue=this.revenue +r;
	}
	public void rSub(double r) {
		this.revenue=this.revenue-r;
	}
	
	/*public boolean processPayment(double amount,double hours) {
		DecimalFormat f = new DecimalFormat("##.00");
		double price = this.pricePerHour*hours;
		System.out.println("Balance = "+price);
		System.out.println("Paid Amount = "+amount);
		if(amount==price)
			return true;
		else if(amount>price) {
			System.out.println("Please take the change"+f.format(amount-price));
			return true;
		}
		else {
			System.out.println("Please pay the remaining balance is"+f.format(amount-price));
			return false;
		}
	}*/
	
	public double processPayment(double amount,double hours) {
		DecimalFormat f = new DecimalFormat("##.00");
		double price = this.pricePerHour*hours;
		System.out.println("Duration = "+hours);
		System.out.println("Balance = "+f.format(price));
		System.out.println("Paid Amount = "+amount);
		if((amount-price)>=0)
			rAdd(price);
		return (amount-price);
	}
	public double getBalance(double duration) {
		DecimalFormat f = new DecimalFormat("##.00");
		return Double.valueOf(f.format(duration*pricePerHour));
	}
	public void getReport(JTextField t) {
		DecimalFormat f=new DecimalFormat("##.00");
		
		t.setText("\t"+f.format(revenue));
		t.setForeground(Color.RED);
	}
}
