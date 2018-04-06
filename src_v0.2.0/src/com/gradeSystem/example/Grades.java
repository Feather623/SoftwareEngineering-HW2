package com.gradeSystem.example;


/*
 * Class for every students, each one has their own scores and average.
*/
public class Grades {
	
	public String ID, name;
	public int lab1, lab2, lab3, midTerm, finalExam;
	public float totalGrade;
	public float Average = 0;
	public Grades() {
		
	}
	
	// Set up a new student with his/her scores.
	public Grades(String _ID, String _name, int _lab1, int _lab2, int _lab3, int _midTerm, int _finalExam) {
		ID = _ID; name = _name;
		lab1 = _lab1; lab2 = _lab2; lab3 = _lab3;
		midTerm = _midTerm; finalExam = _finalExam;
	}	
}
