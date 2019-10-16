
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
	
	public boolean processPayment(double amount,double hours) {
		double price = this.pricePerHour*hours;
		if(amount==price)
			return true;
		else if(amount>price) {
			System.out.println("Please take the change"+(amount-price));
			return true;
		}
		else {
			System.out.println("Waiting for your payment");
			return false;
		}
	}
}
