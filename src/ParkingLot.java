import java.awt.Color;
import java.awt.Font;

/**
 * 
 */

/**
 * @author nguyen
 *
 */
public class ParkingLot {

		private int numOfSpots=25;
		private int totalLots=0;
		private int emptyparkingSpots;
		private int occupiedparkingSpots;
		protected EntranceManager checkInMachine;
		protected EntranceManager checkOutMachine;
		private FinanceManager accounter;
		
		//String address;
		protected String parkingLotID;
		private int index;
		private Lot[] lots;
		
		{
			totalLots=totalLots+1;
		}

	public ParkingLot(String id, double p, double d, String pol,int max) {
		numOfSpots=max;
		index=totalLots-1;
		lots= new Lot[numOfSpots];
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
	}
	public ParkingLot(int max) {
		emptyparkingSpots=numOfSpots;
		occupiedparkingSpots=0;
		numOfSpots=max;
		index=totalLots-1;
		lots= new Lot[numOfSpots];
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,numOfSpots);
		checkOutMachine= checkInMachine;

	}
	public ParkingLot() {
		lots= new Lot[numOfSpots];
		index=totalLots-1;
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
		parkingLotID="";
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,numOfSpots);
		checkOutMachine= checkInMachine;
	}

	public ParkingLot(String gID) {
		//super(gID);
		// TODO Auto-generated constructor stub
		index=totalLots-1;
		lots= new Lot[numOfSpots];
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,numOfSpots);
		checkOutMachine= checkInMachine;
	}
	public ParkingLot(String gID,int max) {
		//super(gID);
		index=totalLots-1;
		numOfSpots=max;
		lots= new Lot[numOfSpots];
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,numOfSpots);
		checkOutMachine= checkInMachine;
	}
	public ParkingLot(String gID,int p, int max) {
		//super(gID,p);
		numOfSpots=max;
		//pricePerHour=p;
		lots= new Lot[numOfSpots];
		for (int i=0;i<numOfSpots;i++) {
			lots[i]=new Lot();
		}
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,numOfSpots);
		checkOutMachine= checkInMachine;
	}
	
	public  void carCheckIn() {
		terminal();
		checkInMachine.checkIn();
		terminal();
		System.out.println("\n\n");
	}
	public  void carCheckOut(double amount, double duration) {
		terminal();
		checkOutMachine.checkOut(amount, duration);
		terminal();
		System.out.println("\n\n");
	}
	public  void carCheckOut(double amount, double duration,double discount) {
		terminal();
		checkOutMachine.checkOut(amount, duration,discount);
		terminal();
		System.out.println("\n\n");
	}
	
	public int isThereAnEmptySpot() {
		return (checkInMachine.getEmptySpots());
	}
	
	
	/**
	 * This function shows the current status of parking lot
	 * it will show the number of empty spots,occupied spot;
	 * it also will count how many cars have checked in,
	 * and how many car have checked out during the day.
	 */
	public void terminal() {
		String s=" ***************************************************************\n";
		s=s+"*   Empty Spots \t Occupied Spots\t\tIN\tOUT\t*\n";
		
		s=s+"*\t"+checkInMachine.getEmptySpots()+"\t\t\t"+checkInMachine.getOccupied()+"\t\t";
		s=s+checkInMachine.getTotalEnter()+"\t"+checkOutMachine.getTotalExit()+"\t*";
	    s=s+"\n ***************************************************************";
		System.out.println(s);
	}
	
}
