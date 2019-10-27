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
	
	
	public double processPayment(double amount,double hours) {
		DecimalFormat f = new DecimalFormat("##.00");
		double price = this.pricePerHour*hours;
		System.out.println("\tDuration = "+hours);
		System.out.println("\tBalance = "+f.format(price));
		System.out.println("\tPaid Amount = "+amount);
		if((amount-price)>=0)
			rAdd(price);
		return (amount-price);
	}
	public double processPayment(double amount,double hours,double discount) {
		DecimalFormat f = new DecimalFormat("##.00");
		double price = this.pricePerHour*hours;
		System.out.println("Dis ="+discount);
		double disAmount=0;
		if(hours>=12) {
			disAmount=price*discount;
		}
		System.out.println("\tDuration = "+hours);
		System.out.println("\tBalance = "+f.format(price));
		System.out.println("\tDiscount= - "+disAmount);
		System.out.println("\tPaid Amount = "+amount);
		if((amount-price*(1-discount))>=0)
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
