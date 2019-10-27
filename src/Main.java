/**
 * Description: This is a simple program that demonstrate how a 
 * parking system work using OOP
 * Class:Fall-COSC 4353 Assignment3
 * @author nguyen
 * @version 10/15/2019
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;


/**ENTRY TO PROGRAM
 * @author nguyen
 *
 */
public class Main {
		public static int MAX_NUM_OF_LOTS=100;
		final static int numOfGroups=3;
		
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		welcome();
		//creating groups of parking lots
		Group []gs=new Group[3];
		
		//POLICY SOURCE:https://jayfencing.com/employee-parking-lot-policy-template-tips/
		Group groupA= new Group("Ungated",3,2);
		groupA.setPolicy(new File("UngatedPolicy.txt"));
		System.out.println("\n Ungated Parking Lots Policy\n"+groupA.getPolicy());
		
		Group groupB = new Group("Gated",5,2);
		groupB.setDiscount(0.15);
		groupB.setPolicy(new File("GatedPolicy.txt"));
		System.out.println("\n Gated Parking Lots Policy\n"+groupB.getPolicy());
		
		Group groupC= new Group("Garage",10,2);
		groupC.setDiscount(0.25);
		groupC.setPolicy(new File("GaragePolicy.txt"));
		System.out.println("\n Garage Parking Lots Policy\n"+groupC.getPolicy());
		gs[0]=groupA;
		gs[1]=groupB;
		gs[2]=groupC;
		
		
		String fileName=getFileName();
		ParkingManagement pm=new ParkingManagement(gs,numOfGroups);
		
		int flowsInOut=0;
		File day=new File(fileName);
		boolean exits=day.exists();
		if(exits&&day.length()!=0) {
			System.out.println("Start a new day working");
				startNewDay(pm,day);
		}
		else {
				System.out.println("Test file is not exists or is empty. Creating a new test file.");
				try {
					flowsInOut=getFlows();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(createFlowOfCars(day,flowsInOut)) {
					System.out.println("Create test file success");
				}
				else
					System.out.println("Fail to create the test file ");
				
				startNewDay(pm,day);
		}
	}
	
	
	/**This function prompt to user and get a file name from user
	 * @return String is a name of file
	 */
	static String getFileName() {
		String s="";
		String fileName;
		s=s+"*\t Please enter a fileName. For example day6.txt \t\t\t*\n\r";
		System.out.println(s);
		Scanner keyboard = new Scanner(System.in);
		fileName=keyboard.next();
		return fileName;
	}
	
	
	/**This function prompt to user and get an integer number 
	 * which is a total of cars in and out
	 * @return
	 * @throws IOException
	 */
	static int getFlows() throws IOException {
		Scanner keyboard=new Scanner(System.in);
		int flow=0;
		System.out.println("* Please enter how many cars will be in and out.For example 300:\t*\n\r");
		while(!keyboard.hasNextInt()) {
			System.out.println("\t\tFlow of in and out cars should be an int\n");
			keyboard.nextLine();
		}
		flow=keyboard.nextInt();
		System.out.println("**********************************START PROGRAM*********************************\n");
		return flow;
	}
		
	
	
	
	/**This function read the flow of car from a file
	 * and start implement the Parking System
	 * @param ps
	 * @param day
	 */
	/**
	 * @param ps
	 * @param day
	 */
	static void startNewDay(ParkingManagement pm, File day) {
		System.out.println("-------------------Start new day----------------\n\n");
		String wait="";
		double duration=0;
		double paidAmount=0;
		String groupName="";
		int lotIndex=0;
		try {
			Scanner sc=new Scanner(day);
			
			while(sc.hasNext()) {
			
				wait=sc.next();
			
				if(wait.equals("enter")) {
					groupName=sc.next();
					if(!groupName.equals("LA")) {
						lotIndex=sc.nextInt();
						pm.carCheckInAt(groupName,lotIndex);
					}
					else {
						Group g=pm.getGroupHasLowestAvailableLot();
						if(g!=null) {
							lotIndex=g.hasAvailableLot();
							String s="";
							s=s+"\tThe lowest available lot is at "+g.getGroupName();
							s=s+" Parking Lot "+lotIndex;
							s=s+"\n\tPrice Per Hour = $"+g.getPricePerHour()+ " /h";
							System.out.println(s);
							pm.carCheckInAt(g.getGroupName(),lotIndex);
						}
					}
				}
				if(wait.equals("exit")) {
					groupName=sc.next();
					//System.out.println(groupName);
					lotIndex=sc.nextInt();
					duration=sc.nextDouble();
					paidAmount=sc.nextDouble();
					pm.carCheckOut(groupName,lotIndex,paidAmount,duration);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**This function generates a test file
	 * @param file
	 * @param flows
	 * @param price
	 * @return
	 */
	static boolean createFlowOfCars(File file,int flows){
		Random random = new Random();
		int  [][]enter= {{0,0},{0,0},{0,0}};
		int [][]exit= {{0,0},{0,0},{0,0}};
		Group []g=new Group[3];
		//System.out.println("break");
		Group groupA= new Group("Ungated",3,2);
		//System.out.println("break");
		Group groupB = new Group("Gated",5,2);
		//System.out.println("break");
		Group groupC= new Group("Garage",10,2);
		//System.out.println("break");
		g[0]=groupA;
		g[1]=groupB;
		g[2]=groupC;
		 DecimalFormat f = new DecimalFormat("##.00");
		 try{
	            // Create new file
	            // If file doesn't exists, then create it
	            if (!file.exists()) {
	                file.createNewFile();
	            }

	            FileWriter fw = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw);

	            // Write in file
	            int isEnter=0;
	            int gIndex=0;
	            int lot=0;
	            
	          
	            for(int j=0;j<flows;j++) {
	            	
	            	//enter or exit
	            	isEnter= random.nextInt(3);
	            	//pick a random Group
	            	gIndex=random.nextInt(3);
	            	//pick up a lot
  	            	lot = random.nextInt(2);
	  	            	
	  	            	
	            	if(isEnter==0 && exit[gIndex][lot]<=enter[gIndex][lot]) {
	            		exit[gIndex][lot]=exit[gIndex][lot]+1;
	            		
	            		double duration = randomDouble(0.5,24);
	            		double paidAmount =duration*g[gIndex].getPricePerHour()*0.98 + random.nextInt(10);
	            		bw.write("exit\t"+g[gIndex].getGroupName()+"\t"+lot+"\t"+f.format(duration)+"\t");
	            		bw.write(f.format(paidAmount)+"\n");
	            		f = new DecimalFormat("#");
	            	}
	            
	            	else {
	            	   	gIndex=random.nextInt(4);
	            		bw.write("enter\t");
	            		if(gIndex<3) {
	            			bw.write(g[gIndex].getGroupName()+"\t"+lot+"\n");
	            			enter[gIndex][lot]++;
	            		}
		            	else {
		            		//GET THE LOWEST AVAILABLE LOT
		            		bw.write("LA\n");
		            			
		            	}
	            		
	            	}
            }
      
            bw.close();
            return true;
	        }
	        catch(Exception e){
	            System.out.println(e);
	            return  false;
	        }
	    }
	
	static double randomDouble(double min, double max) {
	    if (min >= max) {
	        throw new IllegalArgumentException("ERROR Min have to be less than Max");
	    }
	    Random r = new Random();
	    return min + (max - min) * r.nextDouble();
	}
	static void welcome() {
		String s="";
		s="**************************WELCOME TO MY PROGRAM**************************\n";
		s=s+"*\t This program will be able to generate a random test file\t*\n";
		s=s+"*\t Assumming that this parking system has 3 groups of parking lots\t*\n";
		s=s+"*\t Ungated Group has two parking lots: Lot 1 and Lot 2. Price = $3/h\t*\n";
		s=s+"*\t Gated Group has two parking lots: Lot 1 and Lot 2. Price = $5/h\t*\n";
		s=s+"*\t Garage Group has two parking lots: Lot 1 and Lot 2. Price = $10/h\t*\n";
		s=s+"*\t In this example of parking system model, each parking lot has 25 spots\t*\n";
		s=s+"*\t Each parking spot is equiped with detection sensor\t\t*\n";
		s=s+"*\t To test program:\t\t\t\t\t\t*\n";
		s=s+"*\t There are 5 pre-created test file day1.txt,day2.txt and so on \t*\n\r";
		s=s+"*\t You can create a new file or use the exit test files as listed *\n\r";
		System.out.print(s);
	}
}
