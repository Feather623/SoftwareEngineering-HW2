package com.gradeSystem.example;

public class Main extends Object{
	
	
	public static void main(String args[]) {
		try {

			new UI();
		}
		catch(NoSuchIDExceptions e1) {System.out.println("Oops the ID is wrong!!");}
		catch(NoSuchCommandExceptions e2) {System.out.println("Ops the command is not found!!");}
		
	}
	
}
