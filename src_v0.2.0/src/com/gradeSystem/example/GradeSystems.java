package com.gradeSystem.example;
//import java.util.ArrayList;
//import java.util.List;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GradeSystems {
	
	List<Grades> list = new ArrayList<>();
	Grades User = new Grades();
	private String outString;
	public float[] Weight = {10f,10f,10f,30f,40f};
	public int[] Ranking = new int[63];
	
	public GradeSystems() {
	}
	
	public void showCommandTips() {
		System.out.println("        1) [G] Show Grade (Grade)");
		System.out.println("        2) [R] Show Rank (Rank)");
		System.out.println("        3) [A] Show Average (Average)");
		System.out.println("        4) [W] Update Weight (Weight)");
		System.out.println("        5) [E] Exit Menu (Exit)");		
	}	
	
	//Read data from gradeinput.txt
	public void ReadData() {
		File file = new File("src/gradeinput.txt");
		
		try {
			Scanner in = new Scanner(file);
			List<Integer> temp = new ArrayList<Integer>();
			int ind = 0;
			int round=0;
			String _ID ="";
			String _name="";
			while(in.hasNext()){
				round++;
				if(in.hasNext()) {
					_ID = in.next();
				}
				while(in.hasNext()&& !in.hasNextInt()) {
					_name += in.next();
				}
				for(int i=0;i<5;i++) {
					if(in.hasNextInt()) {
					int score = in.nextInt();
					temp.add(score);
					}
				}
				Grades g = new Grades(_ID,_name,temp.get(ind++),temp.get(ind++),temp.get(ind++),temp.get(ind++),temp.get(ind++));
				g.Average = Math.round( g.lab1*Weight[0]/100.0f + g.lab2*Weight[1]/100.0f + g.lab3*Weight[2]/100.0f
							+g.midTerm*Weight[3]/100.0f + g.finalExam*Weight[4]/100.0f) ;
				list.add(g);			 
				_name = "";
			}
			
		

			in.close();				
		} catch (IOException e) {
			e.printStackTrace();
		}		

	}	
	
	//Check whether the ID is valid.
	public boolean containsID(String ID) {
		for(int i=0; i<63; i++) {			
			if(ID.equals(list.get(i).ID)) {
				User = list.get(i);
				return true;
			}
			else {
				User = null;
			}
		}

			return false;
	}

	//Show the user's grades.
	public void showGrade(String ID) {
		System.out.println(User.name+"'s Grades：");	
		System.out.println("Lab1："+User.lab1);
		System.out.println("Lab2："+User.lab2);
		System.out.println("Lab3："+User.lab3);
		System.out.println("Mid-term："+User.midTerm);
		System.out.println("Final Exam："+User.finalExam);
		System.out.println("Total Grade："+User.Average);		
	}
	
	//Show the user's ranking.
	public void showRank(String ID) {
		int Rank=1;
		for(int i=0; i<63; i++) {
			if(User.Average < list.get(i).Average) {
				Rank++;
			}
		}
		
		System.out.println(User.name+"'s Ranking is No."+Rank+" .");
		
	}
	
	//Show the user's average.
	public void showAverage() {
		float Total_Avg = 0f;
		for(int i=0; i<63; i++) {
			float Stu_Avg =  (list.get(i).lab1*Weight[0]/100.0f + list.get(i).lab2*Weight[1]/100.0f + 
							list.get(i).lab3*Weight[2]/100.0f +list.get(i).midTerm*Weight[3]/100.0f + list.get(i).finalExam*Weight[4]/100.0f);
			Total_Avg += Stu_Avg;
		}
		
		Total_Avg = Math.round(Total_Avg/63.0f);
		
		System.out.println("Average of the whole class："+Total_Avg);
	}

	//Update the weights of every tests.
	public void updateWeights() {
		System.out.println("Original Weights:");		
		showWeight();
		
		System.out.println("Please type new weights(5 integers):");		
		
		Scanner in=new Scanner(System.in);
		
		for(int i=0; i<5; i++) {
			if(in.hasNextInt()){
			Weight[i] = in.nextInt();
			}
		}
		
		int sum=0;
		boolean isValid = true;
		for(int i=0; i<5; i++) {
			if(Weight[i]<0) {
				isValid=false;
				break;
			}			
			sum+=Weight[i];
		}
		
		if(sum!=100)
			isValid=false;		
		
		if(!isValid) {
			System.out.println("*****");			
			System.out.println("WARNING");
			System.out.println("*****");			
			System.out.println("New weights is invalid! Please set again.");
			System.out.println("*****");			
			
			updateWeights();
		}
		
		System.out.println("Update Done!");
		
		System.out.println("New Weights：");
		
		showWeight();
		System.out.println("--------------------");
		averageUpdate();
		showCommandTips();
	}

	//Show the current weights of every tests.
	void showWeight() {
		System.out.println("lab1："+Math.round(Weight[0]));
		System.out.println("lab2："+Math.round(Weight[1]));
		System.out.println("lab3："+Math.round(Weight[2]));
		System.out.println("midTerm："+Math.round(Weight[3]));
		System.out.println("finalExam："+Math.round(Weight[4]));		
	}
	
	//re-calculate user's average. It's called every time the weights is updated.
	void averageUpdate() {
		for(int i=0; i<63; i++) {
			Grades Stu = list.get(i);
			Stu.Average = (Stu.lab1*Weight[0]/100 + Stu.lab2*Weight[1]/100 + Stu.lab3*Weight[2]/100 + Stu.midTerm*Weight[3]/100 + Stu.finalExam*Weight[4]/100);
		}
	}
	
	
}
