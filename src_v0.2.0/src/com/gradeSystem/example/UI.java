package com.gradeSystem.example;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UI {


	private GradeSystems aGradeSystem;
	private final static Scanner scanner = new Scanner(System.in);
	private String sid;

	
	public UI() throws NoSuchIDExceptions , NoSuchCommandExceptions{									
			aGradeSystem = new GradeSystems();
			aGradeSystem.ReadData();
			
			// Check whether user wants to quit.
			// Keep accepting command until user type "Q" 
			while(!(sid = promptID()).equals("Q")) {

				if(checkID(sid)) {
					showWelcomeMsg();				
					while(promptCommand());
				}

			}
			
			showFinishMsg();
			scanner.close();
		
	}
	
	//Check whether it is a valid ID;
	public boolean checkID(String ID) throws NoSuchIDExceptions {
		if(aGradeSystem.containsID(ID)) {
			return true;
		}else {
			System.out.println("******************");
			System.out.println("It is an invalid ID! Please type again or QUIT.");
			System.out.println("******************");
			//			throw new NoSuchIDExceptions();
			return false;

		}	
	}
	
	// Get the ID that user type. 
	public String promptID() {
		System.out.print("Input ID or Q (Finish Using)?");
		return scanner.next();
	}

	// Get the Command that user type.	
	public boolean promptCommand(){
		String cmd = scanner.next();
		if (!cmd.equals("G") && !cmd.equals("R") && !cmd.equals("W") && !cmd.equals("A") && !cmd.equals("E")) {
			System.out.println("This command is invalid!");
			
			aGradeSystem.showCommandTips();
			//			throw new NoSuchCommandExceptions();
		}
		if(cmd.equals("E")) return false;
		else {	
			switch(cmd) {
			case "G": aGradeSystem.showGrade(sid);
				break;
			case "R": aGradeSystem.showRank(sid);
				break;
			case "A": aGradeSystem.showAverage();
				break;
			case "W": aGradeSystem.updateWeights();
				break;
			}
			return true;
		}
	}
	
	public void showFinishMsg() {
		System.out.println("Thank for using the gradeSystem, byebye!");
	}
	
	public void showWelcomeMsg() {
		System.out.println("Welcome,"+aGradeSystem.User.name+"!");
		aGradeSystem.showCommandTips();
	}

}
