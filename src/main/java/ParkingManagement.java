import java.util.Random;

public class ParkingManagement {

	private Group []groups;
	int numOfGroups;
	
	public ParkingManagement(Group[] gs, int n) {
		groups=gs;
		numOfGroups=n;
	}
	public ParkingManagement() {
		//creating groups of parking lots
		numOfGroups=3;
		Group []gs=new Group[3];
		//System.out.println("break");
		Group groupA= new Group("Ungated",3,2);
		//System.out.println("break");
		Group groupB = new Group("Gated",5,2);
		//System.out.println("break");
		Group groupC= new Group("Garage",10,2);
		//System.out.println("break");
		gs[0]=groupA;
		gs[1]=groupB;
		gs[2]=groupC;
	}
	public void carCheckOut(String groupName,int lotNumber,double paidAmount,double hours) {
		int gIndex;
		for(gIndex=0; gIndex<numOfGroups;gIndex++) {
			if(groups[gIndex].getGroupName().equals(groupName)) {
				System.out.printf("\t\t    %s Parking Lot %d\n",groupName,lotNumber);
				groups[gIndex].getLot(gIndex).carCheckOut(paidAmount,hours,groups[gIndex].getDiscount());
				break;
			}
		}
	}
	
	public boolean carCheckInAt(String gName, int lotIndex) {
		Group g=getGroup(gName);
		if(g!=null) {
			System.out.printf("\t\t    %s Parking Lot %d \n",gName,lotIndex);
			g.getLot(lotIndex).carCheckIn();
		}
		return true;
	}



/*public double getPaidAmount() {
	double duration = randomDouble(0.5,24);
	//System.out.println("flow="+flows);
	double paidAmount =duration*g[gIndex].getPricePerHour()*0.98 + random.nextInt(10);
}*/

	static double randomDouble(double min, double max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("ERROR Min have to be less than Max");
	    }
	    Random r = new Random();
	    return min + (max - min) * r.nextDouble();
	}

	public Group getGroupHasLowestAvailableLot() {
		double min=1000;
		Group tempG=null;
		for(int gI=0;gI<3;gI++) {
			if(groups[gI].hasAvailableLot()>=0) {
				if(groups[gI].getPricePerHour()<min) {
					min=groups[gI].getPricePerHour();
					tempG=groups[gI];
				}
			}
		}
		if(tempG!=null) {
			return tempG;
		}
		else
			return null;
	}
	private Group getGroup(String gName) {
		for(int gIndex=0; gIndex<numOfGroups;gIndex++) {
			if(gName.equals(groups[gIndex].getGroupName()))
				return groups[gIndex];
		}
		return null;
	}
}