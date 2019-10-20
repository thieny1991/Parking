import java.text.DecimalFormat;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class EntranceManager{
	static FinanceManager fManager;
	static int MAX;
	private static int enter=0;
	private static int exit=0;
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
		System.out.println("----------------------Check In--------------------");
		if((enter-exit)<this.MAX) {
			issueTicket();
			//openGate();
			//closeGate();
			
		}
		else {
			System.out.println("I AM SORRY! OUR PARKING LOTS ARE FULL");
			System.out.println("--------------------------------------------------");
		}
	}
	public boolean checkIn(JTextArea t) {
		t.setText("");
		String s=("   Check In:");
		if((enter-exit)<this.MAX) {
			//issueTicket(t);
			//openGate();
			//closeGate();
			enter++;
			s=s+ "Approved! Take your ticket and keep it safe";
			t.setText(s);
			return true;
		}
		else {
			s=s+" SORRY! OUR PARKING LOTS ARE FULL";
			t.setText(s);
			return false;
		}
	}
	public void checkOut(double amount,double hour) {
		DecimalFormat f=new DecimalFormat("##.00");
		System.out.println("----------------------Check Out-------------------");
		double change=fManager.processPayment(amount,hour);
		if((enter-exit)>0 &&change>=0 ) {
			
			openGate();
			closeGate();
			//exit++;
			if((change)>0)
				System.out.println("\tApproved!\n Your change is "+f.format(change));
			else 
				System.out.println("\tApproved!\n");
		}
		else {
			System.out.println("\nYour remaining balance is"+ f.format(change));
			System.out.println("\nGate remains blocked");
			System.out.println("--------------------------------------------------\n");
		}
	}
	
	
	public boolean checkOut(double amount,double hour,JTextArea t) {
	
		t.setText("");
		DecimalFormat f=new DecimalFormat("##.00");
		String s="  Check Out: Duration "+hour+"  Balance "+fManager.getBalance(hour);
		s=s+"\n  Paid Amount "+amount;
		double change=amount-fManager.getBalance(hour);
		
		if((enter-exit)>0 && change>=0) {
			
			openGate();
			closeGate();
			exit++;
			if(change>0) {
			s=s+" Approved! Your change is "+ f.format(change);
			t.setText(s);}
			else if(change==0.0) {
				s=s+"\t Approved! Drive Safe!";
				t.setText(s);
			}
			return true;
		}
		else {
			s=s+ "\n  Denied. Your remaining balance is "+ f.format(change);
			t.setText(s);
			return false;
		}
	}
	public void issueTicket() {
		System.out.println("Please take the ticket and keep it safe");
	}
	public void issueTicket(JTextArea t) {
		t.setText("Please take the ticket and keep it safe.Gate is unlocked");
	}
	public int getTotalEnter() {
		return enter;
	}
	public int getTotalExit() {
		return exit;
	}
	public int getEmptyLots() {
		return (MAX-enter+exit);
	}
	public int getOccupied() {
		return (enter-exit);
	}

}
