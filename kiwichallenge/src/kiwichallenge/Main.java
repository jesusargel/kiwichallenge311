package kiwichallenge;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.*;

class Main {
	
	static int numOfZones;
	static String departureAirport;
	String currentAirport;
	int dayCounter = 0;
	int totalPrice = 0;
	Boolean [] zones;
	String [] routeTaken;
	int [][] dayOfFlight;
	int [][] priceOfFlight;
	
	public static void main(String[] args) {
		File file = new File("src/file.txt");
		Scanner sc = new Scanner("it");
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numOfZones = sc.nextInt();
		departureAirport = sc.next(); 
		System.out.println(numOfZones);
		System.out.println(departureAirport);
	}

}
