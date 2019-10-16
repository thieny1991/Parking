
public class EntranceManager {
	static FinanceManager fManager;
	static int MAX;
	static int enter=0;
	static int exit=0;
	private boolean blockGate;
	public EntranceManager(FinanceManager fManager,int num) {
		blockGate=true;
		this.fManager=fManager;
		this.MAX=num;
	}
	void openGate() {
		System.out.println("Gate is unblocked");
	}
	void closeGate() {
		System.out.println("Gate is now blocked");
	}
	void checkIn() {
		if((enter-exit)<this.MAX) {
			issueTicket();
			openGate();
			closeGate();
			enter++;
		}
		else {
			System.out.println("I AM SORRY! OUR PARKING LOTS ARE FULL");
		}
	}
	void checkOut(double amount,double hour) {
		if((enter-exit)>0 && fManager.processPayment(amount,hour)) {
			
			openGate();
			closeGate();
			exit--;
		}
		else {
			System.out.println("Gate remains blocked");
		}
	}
	
	void issueTicket() {
		System.out.println("Please take the ticket and keep it safe");
	}

}
