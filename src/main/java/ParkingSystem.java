import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class ParkingSystem extends JFrame implements ActionListener {
	//---------------------------------------------gui -----------------
	
	public static int MAX_NUM_OF_parkingSpots=100;
	int emptyparkingSpots;
	int occupiedparkingSpots;
	private Lot[] parkingSpots;
	protected EntranceManager checkInMachine;
	protected EntranceManager checkOutMachine;
	private FinanceManager accounter;
	
	private JSplitPane splitPane= new JSplitPane();
	private JPanel top_panel = new JPanel();
	private JPanel bottom_panel = new JPanel();
	private int numparkingSpots;
	private JButton[] button;
	private JButton enter= new JButton("Check In");

	private JButton exit= new JButton("Check Out");
	private JButton report = new JButton("Show Revenue");
	private JTextArea inMessage = new JTextArea();
	private JTextArea outMessage = new JTextArea("");
	private JTextField revenue =new JTextField("");
	private JTextField flowStats =new JTextField("");
	private JTextField terminal = new JTextField("");
	
	
	
	
	/**Constructor
	 * @param MAX
	 */
	public ParkingSystem(int MAX) {
		super("PARKING SYSTEM");
		this.MAX_NUM_OF_parkingSpots=MAX;
		emptyparkingSpots=MAX_NUM_OF_parkingSpots;
		occupiedparkingSpots=0;
		parkingSpots= new Lot[MAX_NUM_OF_parkingSpots];
		for (int i=0;i<MAX_NUM_OF_parkingSpots;i++) {
			parkingSpots[i]=new Lot();
		}
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		checkOutMachine= new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		createFrame();
	}
	/**Constructor 
	 * @param MAX
	 * @param price
	 */
	public ParkingSystem(int MAX,double price) {
		super("PARKING SYSTEM");
		emptyparkingSpots=MAX_NUM_OF_parkingSpots;
		occupiedparkingSpots=0;
		parkingSpots= new Lot[MAX_NUM_OF_parkingSpots];
		accounter= new FinanceManager(price);
		checkInMachine = new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		checkOutMachine= new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		createFrame();
	}
	/**
	 * Default constructor
	 */
	public ParkingSystem() {
		super("PARKING SYSTEM");
		MAX_NUM_OF_parkingSpots=100;
		emptyparkingSpots=MAX_NUM_OF_parkingSpots;
		occupiedparkingSpots=0;
		parkingSpots= new Lot[MAX_NUM_OF_parkingSpots];
		accounter= new FinanceManager();
		checkInMachine = new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		checkOutMachine= new EntranceManager(accounter,MAX_NUM_OF_parkingSpots);
		createFrame();
	}
	

	
	/**
	 * GUI
	 */
	protected void createFrame() {
		this.setPreferredSize(new Dimension(1500,650));
		button = new JButton[MAX_NUM_OF_parkingSpots];
		Font font = new Font("Arial", Font.BOLD, 15);
		revenue.setFont(font);
		revenue.setForeground(Color.RED);
		revenue.addActionListener(this);
		revenue.setEditable(false);
		
		exit.setPreferredSize(new Dimension(30,5));
		enter.setPreferredSize(new Dimension(30,5));
		report.setPreferredSize(new Dimension(30,5));
		//getContentPane().setLayout(new GridLayout() );
		//getContentPane().setLayout(new BorderLayout() );
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(220);//devide with proprotion 2/3 1/3
		splitPane.setTopComponent(top_panel);
		splitPane.setBottomComponent(bottom_panel);
		
		
		numparkingSpots=MAX_NUM_OF_parkingSpots;
		for(int i = 0; i < numparkingSpots; ++i){
			button[i]=new JButton("L"+i);
	        button[i].setBackground(Color.GREEN);
	        bottom_panel.add(button[i]);
	    }
		getContentPane().add(splitPane,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 						//no idea what this is but without it, menu bar won't display on the frame
		//top_panel.setLayout(new GridLayout());
		top_panel.setLayout(new GridLayout(4,2));
		top_panel.add(enter);
		enter.addActionListener(this);
		//gs.gridy=1;
		top_panel.add(inMessage);

		top_panel.add(exit);
		//gs.gridy=1;
		top_panel.add(outMessage);
		
	
		top_panel.add(report);
		//gs.gridy=1;
		top_panel.add(revenue);

		terminal.setSize(30,10);
		top_panel.add(terminal);
		top_panel.add(flowStats);
		terminal();
		
		this.setSize(800,600);
		Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();//screenSize 
		setBounds((int)(0.5*(screenSize.width-getWidth())),
				(int)(0.5*(screenSize.height-getHeight())),getWidth(),getHeight());
		this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
	}
	
	
	public  void carCheckIn() {
		//inMessage.setText("Please");
		enter.doClick();
		//report.doClick();
		
		checkInMachine.checkIn();
		accounter.getReport(revenue);
		
		if(checkInMachine.checkIn(inMessage))
				changeparkingSpotstatus(findSpot());
		terminal();
	}
	public  void carCheckOut(double amount, double duration) {
		exit.doClick();
		checkOutMachine.checkOut(amount, duration);
		
		//report.doClick();
		accounter.getReport(revenue);
		
		if(checkOutMachine.checkOut(amount,duration,outMessage))
			changeparkingSpotstatus(carLeaveID());
		terminal();
	}
	
	/**
	 * This function shows the current status of parking lot
	 * it will show the number of empty spots,occupied spot;
	 * it also will count how many cars have checked in,
	 * and how many car have checked out during the day.
	 */
	public void terminal() {
		String s="\tIN: "+checkInMachine.getTotalEnter();
		s=s+"\tOUT: "+checkOutMachine.getTotalExit();
		
		System.out.println(s);
		flowStats.setText(s);
		Font font = new Font("Arial", Font.BOLD, 15);
		flowStats.setFont(font);
		flowStats.setForeground(Color.BLUE);
		s="           Empty parkingSpots: "+checkInMachine.getEmptySpots();
		s=s+"   ----   Occupied parkingSpots:"+checkInMachine.getOccupied();
		terminal.setFont(font);
		terminal.setForeground(Color.BLACK);
		terminal.setText(s);
		
	}
	public double getBasicPrice() {
		return accounter.getPrice();
	}
	public double getLotID(int i) {
		return parkingSpots[i].getLotID();
	}
	
	/**This function changes the color of the sensor in the parking lot
	 * @param ID
	 */
	public void changeparkingSpotstatus(int ID) {
		if(button[ID].getBackground()==Color.GREEN) {
			button[ID].setBackground(Color.RED);
		}
		else if(button[ID].getBackground()==Color.RED)
			button[ID].setBackground(Color.GREEN);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()=="enter") {
			checkInMachine.checkIn(inMessage);
		}
		if(e.getSource()=="report") {
			accounter.getReport(revenue);
		}
		// TODO Auto-generated method stub
	}
	
	
	
	/**This function will randomly choose a parking lot
	 * for a incoming car
	 * @return
	 */
	public int findSpot() {
		Random r=new Random();
		int ID=-1;
		for(int i=0;i<10;i++) {
			ID=r.nextInt(MAX_NUM_OF_parkingSpots);
			if(!parkingSpots[ID].isOccupied()) {
				parkingSpots[ID].setOccupied(true);
				break;
			}
			else
				ID=-1;
		}
		if(ID!=-1) {
			System.out.println(ID);
			return ID;
		}
		else {
			for(int i=0;i<MAX_NUM_OF_parkingSpots;i++) {
				if(!parkingSpots[i].isOccupied()) {
					parkingSpots[i].setOccupied(true);
					ID=i;
					break;
				}
			}
		}
		System.out.println(ID);
		return ID;
	}
	
	
/**This function get a random car that will be the next leaving
 * by the lot id
 * @return
 */
public int carLeaveID() {
		
		Random r=new Random();
		int ID=-1;
		for(int i=0;i<20;i++) {
			ID=r.nextInt(MAX_NUM_OF_parkingSpots);
			if(this.parkingSpots[ID].isOccupied()) {
				this.parkingSpots[ID].setOccupied(false);
				
				break;
			}
			else
				ID=-1;
		}
		if(ID!=-1) {
			System.out.println("Car at lot "+ID+" is leaving");
			return ID;
		}
		else {
			for(int i=0;i<MAX_NUM_OF_parkingSpots;i++) {
				if(this.parkingSpots[i].isOccupied()) {
					this.parkingSpots[i].setOccupied(false);
					ID=i;
					break;
				}
			}
		}
		return ID;
	}
}
