import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class EntranceManager{
	static FinanceManager fManager;
	static int MAX;
	private int enter=0;
	private int exit=0;
	private boolean blockGate;
	public EntranceManager(FinanceManager fManager,int num) {
		blockGate=true;
		this.fManager=fManager;
		this.MAX=num;
	}
	
	
	void openGate() {
		System.out.println("\tGate is unblocked");
	}
	
	void closeGate() {
		System.out.println("\tGate is now blocked");
	}
	
	void checkIn() {
		System.out.println("\t----------------------Check In--------------------");
		if((enter-exit)<this.MAX) {
			issueTicket();
			openGate();
			enter++;
			closeGate();
			System.out.println("\t--------------------------------------------------");
			
		}
		else {
			System.out.println("\tI AM SORRY! OUR PARKING LOTS ARE FULL");
			System.out.println("\t--------------------------------------------------");
		}
	}
	
	
	public boolean checkIn(JTextArea t) {
		t.setText("");
		String s=("  Check In:");
		if((enter-exit)<this.MAX) {
			//issueTicket(t);
			//openGate();
			//closeGate();
			//enter++;
			s=s+ "\tApproved!\n\tTake your ticket and keep it safe";
			t.setText(s);
			return true;
		}
		else {
			s=s+" SORRY! OUR PARKING LOTS ARE FULL";
			t.setText(s);
			return false;
		}
	}

	
	public void checkOut(double amount,double hour,double discount) {
		System.out.println("\t----------------------Check Out-------------------");
		if(enter<=exit) {
			System.out.println("\tHmm! There is no car in this parking lot.");
			System.out.println("\t--------------------------------------------------");
			return;
		}
		DecimalFormat f=new DecimalFormat("##.00");
		
		double change=fManager.processPayment(amount,hour,discount);
		String s="\t";
		if((enter-exit)>0 && change>=0) {
				exit++;
				if(change>0) {
				s=s+"\n\t\tApproved! Your change is "+ f.format(change);
				System.out.println(s);
				}
				else if(change==0.0) {
					s=s+"\n\t\tApproved! Drive Safe!";
					System.out.println(s);
				}
				openGate();
				closeGate();
				System.out.println("\t--------------------------------------------------");
			
			}
		
		else {
			s=s+ "\n\tDenied. Your remaining balance is "+ f.format(change);
			System.out.println(s);
			System.out.println("\tGate remains blocked");
			System.out.println("\t--------------------------------------------------");
		}
	}
	
	public void checkOut(double amount,double hour) {
		System.out.println("\t----------------------Check Out-------------------");
		if(enter<=exit) {
			System.out.println("\tHmm! There is no car in this parking lot.");
			System.out.println("\t--------------------------------------------------");
			return;
		}
		DecimalFormat f=new DecimalFormat("##.00");
		
		double change=fManager.processPayment(amount,hour);
		String s="\t";
		if((enter-exit)>0 && change>=0) {
				exit++;
				if(change>0) {
				s=s+"\n\t\tApproved! Your change is "+ f.format(change);
				System.out.println(s);
				}
				else if(change==0.0) {
					s=s+"\n\t\tApproved! Drive Safe!";
					System.out.println(s);
				}
				openGate();
				closeGate();
				System.out.println("\t--------------------------------------------------");
			
			}
		
		else {
			s=s+ "\n\tDenied. Your remaining balance is "+ f.format(change);
			System.out.println(s);
			System.out.println("\tGate remains blocked");
			System.out.println("\t--------------------------------------------------");
		}
	}
	
	
	public boolean checkOut(double amount,double hour,JTextArea t) {
		t.setText("");
		DecimalFormat f=new DecimalFormat("##.00");
		String s="  Check Out: Duration "+hour+"  Balance "+fManager.getBalance(hour);
		s=s+"\n\tPaid Amount "+amount;
		double change=amount-fManager.getBalance(hour);
		
		if((enter-exit)>0 && change>=0) {
			
			openGate();
			closeGate();
			exit++;
			if(change>0) {
			s=s+"\n\tApproved! Your change is "+ f.format(change);
			t.setText(s);}
			else if(change==0.0) {
				s=s+"\n\tApproved! Drive Safe!";
				t.setText(s);
			}
			return true;
		}
		
		else {
			s=s+ "\n\tDenied. Your remaining balance is "+ f.format(change);
			t.setText(s);
			return false;
		}
	}
	
	
	public void issueTicket() {
		System.out.println("\tPlease take the ticket and keep it safe");
	}
	
	
	public void issueTicket(JTextArea t) {
		t.setText("\n  Please take the ticket and keep it safe.Gate is unlocked");
	}
	
	
	public int getTotalEnter() {
		return enter;
	}
	
	
	public int getTotalExit() {
		return exit;
	}
	
	
	public int getEmptySpots() {
		return (MAX-enter+exit);
	}
	
	
	public int getOccupied() {
		return (enter-exit);
	}
}
