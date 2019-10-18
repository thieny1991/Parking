
public class Lot {
	private boolean detectionSensor;
	private int lotID;
	static int totalLot=0;
	
	{
		totalLot=totalLot+1;
	}
	
	
	public Lot() {
		lotID=totalLot-1;
		detectionSensor=false;;
	}
	
	public void setLotID(int ID) {
		lotID=ID;
	}
	

	
	public boolean isOccupied() {
		return detectionSensor;
	}
	public int getLotID() {
		return lotID;
	}
	public void setOccupied(boolean available) {
		detectionSensor=available;
	}
}
