package kiwichallenge;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;

class Main {
	
	static int numOfZones;
	static String departureAirport;
	static String currentAirport;
	int dayCounter = 0;
	int totalPrice = 0;
	static Boolean [] zones;
	String [] routeTaken;
	int [][] dayOfFlight;
	int [][] priceOfFlight;
	
	public static void main(String[] args) throws IOException {
		File file = new File("src/file.txt");
		Scanner sc = new Scanner("it");
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		numOfZones = sc.nextInt();
		departureAirport = sc.next();
		currentAirport = departureAirport;
		zones = new Boolean[numOfZones];
		System.out.println("Number of zones = " + numOfZones);
		System.out.println("Departure Airport = " + departureAirport);
		System.out.println("Current Airport = " + currentAirport);
		for(int i = 0; i < numOfZones; i++) {
			zones[i] = false;
			System.out.println("Zones = " + zones[i]);
		}

		System.out.println(" ");

		BufferedReader in = new BufferedReader(new FileReader(file)); 
		String text = in.readLine(); 

		System.out.println(text); 
		try {
			while (in.ready()) { 
				  text = in.readLine(); 
				  System.out.println(text); 
				}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}
