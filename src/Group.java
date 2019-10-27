import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Group {

	protected String groupName;
	protected int groupIndex;
	protected double pricePerHour;
	protected double discount;
	protected int numOfLots;
	protected ParkingLot []lots;
	protected String policy;
	{
		groupIndex=0;
		groupIndex=groupIndex+1;
	}
	public Group(String id,double p, double d, String pol) {
		groupName=id;
		pricePerHour=p;
		discount=d;
		policy=pol;
		numOfLots=1;
		lots[0]=new ParkingLot();
	}
	public Group(String id, String policy) {
		this.pricePerHour=5;
		this.discount=0;
		this.groupName=id;
		this.policy=policy;
		numOfLots=1;
		lots[0]=new ParkingLot();
	}
	public Group(String id) {
		this.groupName=id;
		pricePerHour=5;
		discount=0;
		policy="";
		numOfLots=1;
		lots[0]=new ParkingLot();
	}
	public Group(String id, double p, String pol) {
		groupName=id;
		pricePerHour=p;
		policy=pol;
		discount=0;
		numOfLots=1;
	}
	public Group(String gID, int p) {
		groupName=gID;
		pricePerHour=p;
		discount=0;
		policy="";
		numOfLots=1;
	}
	public Group(String gID, int p,int n) {
		groupName=gID;
		pricePerHour=p;
		discount=0;
		policy="";
		numOfLots=n;
		lots=new ParkingLot[numOfLots];
		for (int i=0;i<n;i++) {
			lots[i]=new ParkingLot();
			//System.out.println("creat parking lot");
		}
	}
	public void setPrice(double price) {
		pricePerHour=price;
		
	}
	public void setDiscount(double dc) {
		discount=dc;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public void setPolicy(File policyFile) {
		 if (policyFile.exists()) {
			 policy="";
			 Scanner sc = null;
			try {
				sc = new Scanner(policyFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 while(sc.hasNext()) {
				 policy=policy+sc.nextLine()+"\n";
			 }
		}	
		 else {
			 policy="Policy is not found";
		 }
	
	}//end set policy
	public double getPricePerHour() {
		return this.pricePerHour;
	}
	public double getDiscount() {
		return this.discount;
	}
	public String getPolicy() {
		return policy;
	}
	public String getGroupName() {
		return groupName;
	}
	public ParkingLot buildParkingLot(String lotID,int numOfSpot) {
		return (new ParkingLot(lotID,numOfSpot));
	}
	public ParkingLot getLot(int lIndex) {
		if(lIndex>=0&&lIndex<numOfLots)
			return lots[lIndex];
		else
			return lots[0];
	}
	public int hasAvailableLot() {
		int lotIndex;
		for (lotIndex=0;lotIndex<numOfLots;lotIndex++) {
			if(lots[lotIndex].isThereAnEmptySpot()>0) {
				return lotIndex;
			}
		}
		return -1;
	}
	
}
	
