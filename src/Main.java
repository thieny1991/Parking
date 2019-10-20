import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;






/**ENTRY TO PROGRAM
 * @author nguyen
 *
 */
public class Main {
		public static int MAX_NUM_OF_LOTS=100;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		try {
				//just for testing purpose. We can change to SystemLookAndFeel Later
	            final UIManager.LookAndFeelInfo[] infos =
	                    UIManager.getInstalledLookAndFeels();
	            UIManager.setLookAndFeel(infos[3].getClassName()); 
	            
	        } catch (ClassNotFoundException ex) {
	        } catch (InstantiationException ex) {
	        } catch (IllegalAccessException ex) {
	        } catch (UnsupportedLookAndFeelException ex) {
	        	System.out.println("NO SUPPORT FOR UI");
	        } catch (Exception system) {
	            system.printStackTrace();
	        }
		
		welcome();
		String fileName=getFileName();
		ParkingSystem ps= new ParkingSystem(MAX_NUM_OF_LOTS);
		ps.show();
		double price = ps.getBasicPrice();
		int flowsInOut=0;

		File day=new File(fileName);
		boolean exits=day.exists();
		if(exits&&day.length()!=0) {
			System.out.println("Start a new day working");
				startNewDay(ps,day);
		}
		else {
				System.out.println("Test file is not exists or is empty. Creating a new test file.");
				try {
					flowsInOut=getFlows();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(createFlowOfCars(day,flowsInOut,price)) {
					System.out.println("Create test file success");
				}
				else
					System.out.println("Fail to create the test file ");
				
				startNewDay(ps,day);
		}
	}
	
	
	static String getFileName() {
		String s="";
		String fileName;
		s=s+"*\t Please enter a fileName. For example day6.txt \t\t\t*\n\r";
		System.out.println(s);
		Scanner keyboard = new Scanner(System.in);
		fileName=keyboard.next();
		return fileName;
	}
	
	
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
	static void startNewDay(ParkingSystem ps, File day) {
		System.out.println("-------------------Start new day----------------\n\n");
		String wait="";
		double duration=0;
		double paidAmount=0;
		try {
			Scanner sc=new Scanner(day);
			
			while(sc.hasNext()) {
			
				wait=sc.next();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(wait.equals("enter")) {
					ps.carCheckIn();
				}
				else if(wait.equals("exit")) {
				
					duration=sc.nextDouble();
					paidAmount=sc.nextDouble();
					ps.carCheckOut(paidAmount,duration);
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
	static boolean createFlowOfCars(File file,int flows,double price){
		Random random = new Random();
		int enter=0;
		int exit=0;
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
	            for(int i=0; i<flows;i++) {
	            	isEnter= random.nextInt(3);
	            	if(isEnter==0 && exit<=enter) {
	            		exit++;
	            		double duration = randomDouble(0.5,24);
	            		double paidAmount =duration*price*0.98 + random.nextInt(10);
	            		bw.write("exit\t"+f.format(duration)+"\t");
	            		bw.write(f.format(paidAmount)+"\n");
	            		f = new DecimalFormat("#");
	            	}
	            	else {
	            		bw.write("enter\n");
	            		enter++;
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
		s=s+"*\t Assumming that this parking has 100 parking lots\t\t*\n";
		s=s+"*\t Each parking space is equiped with detection sensor\t\t*\n";
		s=s+"*\t The basic price per hour is 5$/hour\t\t\t\t*\n";
		s=s+"*\t To test program:\t\t\t\t\t\t*\n";
		s=s+"*\t There are 5 pre-created test file day1.txt,day2.txt and so on \t*\n\r";
		s=s+"*\t You can create a new file or use the exit test files as listed *\n\r";
		System.out.print(s);
	}
}
