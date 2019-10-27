import java.awt.Color;

import javax.swing.JButton;

public class Lot {
	private boolean detectionSensor;
	private int lotID;
	//private String spotID;
	protected JButton spotAreaBtn;
	static int totalLot=0;
	
	{
		totalLot=totalLot+1;
	}
	
	
	public Lot() {
		lotID=totalLot-1;
		detectionSensor=false;;
		spotAreaBtn=new JButton();
		spotAreaBtn.setBackground(Color.GREEN);
		//spotID=groupID+parkingLotID+lotID;
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
	
	public void changeLotStatus(int ID) {
		if(spotAreaBtn.getBackground()==Color.GREEN) {
			spotAreaBtn.setBackground(Color.RED);
		}
		else if(spotAreaBtn.getBackground()==Color.RED)
			spotAreaBtn.setBackground(Color.GREEN);
	}
}
