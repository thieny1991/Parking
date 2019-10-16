
public class Lot {
	private boolean detectionSensor=false;;
	private int lotID;
	static int totalLot=0;
	
	{
		totalLot=totalLot+1;
	}
	
	
	public Lot() {
		lotID=totalLot;
	}
	
	void setLotID(int ID) {
		lotID=ID;
	};
	int getLotID;
	boolean isOccupied() {
		return detectionSensor;
	}
}
