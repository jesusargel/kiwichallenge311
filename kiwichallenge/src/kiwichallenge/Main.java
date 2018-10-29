package kiwichallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

class Main {
	
	static int numOfZones;
	static String departureAirport;
	static String currentAirport;
	int dayCounter = 0;
	static int totalPrice = 0;
	static String curLine="";
	Boolean [] zones;
	String [] routeTaken;
	static String [] allZones;
	static Vector<String> allCitys = new Vector<String>();
	int [][] dayOfFlight;
	int [][] priceOfFlight;
	static String leaving;
	static String going;
	static int date;
	static int cost;
	static int[][][] flightCost;
	static int areaCount=-1;
	static Vector<Integer> citysArea = new Vector<Integer>();
	
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
		/*
		 * phase one is the first line in the file it has the how many zones there are 
		 * and what is the starting area
		 */
		allZones = new String [numOfZones];
		departureAirport = sc.next();
		sc.nextLine();
	/*
	 * allZones is set to the length of numOfZones then allZones is filled with null so 
	 * we can add the zone names in later.
	 */
		for(int i =0; i<numOfZones;i++)
		{
			allZones[i]=null;
		}	
	
		/*
		 * phase two starts after the phase one end. phase two then goes into 
		 * adding names for the zones and then what city's are in the zone 
		 */
	
		
		for(int i =0; i<numOfZones*2;i++)
		{
			/*
			 * the line alternate between zone then city's so when if i%2==0 then we know that
			 * it is a zone when i is equal to 1 then we know it is a city
			 */
		
			if(i%2==0)
			{
				/*
				 * adds the zone into an empty space zone
				 * and increase the areaCount so we can keep track of which cities are in 
				 * what area 
				 */
				allZones[findZoneNull()]=sc.nextLine();
				areaCount++;
			}
			else {
				/*
				 * curLine is equal the city line it checks to see how many cities are there
				 * based on count_Words it does that by looking for spaces in the string
				 */
				curLine=sc.nextLine();
				int x = count_Words(curLine);
				/*
				 * if there are more then one word in the string then we split the string
				 * by how many spaces are in the string then we add them to the allCitys vector
				 * we also add what zone the city in by the citysArea vector
				 */
				if (x!=1)
				{
					String[] split= curLine.split("\\s+");
					for(int j =0; j<split.length;j++)
					{
						allCitys.add(split[j]);
						citysArea.add(areaCount);
					}
				
				}
				/*
				 * if there is only one word in the string we just add the whole string to the 
				 * vector
				 */
				else
				{
					allCitys.add(curLine);	
					citysArea.add(areaCount);
				}
			}
			
		}	
		/*
		 * flightCost is where we store what flights cost
		 * the first [] is the date of which the plane is leaving
		 * the second [] is what city the plane is leaving from
		 * the third [] is what city the plane is going to
		 */
		flightCost= new int [numOfZones+1][allCitys.size()][allCitys.size()];
		/*
		 * phase three starts after phase two. phase three is getting the cost of the flights
		 * the pattern goes string string int int 
		 * first string is the where it is leaving from
		 * second string is where it is going
		 * first int is what day
		 * second int is the cost 
		 */
		while(true)
		{
			/*
			 * the while loop will keep going until there is nothing left in the file
			 * to find that we first try sc.next if there is something there then we know
			 * its a line of data if it fails then we know that the file is done
			 */
			try {
			leaving=sc.next();
			}
			catch(NoSuchElementException e)
			{
				break;
			}
			going=sc.next();
			date=sc.nextInt();
			cost=sc.nextInt();
		
		
			flightCost[date][allCitys.indexOf(leaving)][allCitys.indexOf(going)]=cost;
		}
		
		System.out.println("all of the zones");
		for(int i =0; i<numOfZones;i++)
		{
			System.out.println(allZones[i]);
		}
		System.out.println("all of the citys");
		for(int i =0; i<allCitys.size();i++)
		{
			System.out.println(allCitys.elementAt(i));
		}
		System.out.println("what city in what zone");
		for(int i =0; i<citysArea.size();i++)
		{
			System.out.println(citysArea.elementAt(i));
		}
		System.out.println("what the flights cost looks like");
		for(int i =0; i<numOfZones;i++)
		{
			for(int j =0; j<allCitys.size();j++)
				for(int k =0; k<allCitys.size();k++)
			{
					if(flightCost[i][j][k] != 0) { // All flights has cost
						System.out.println(flightCost[i][j][k] + "   " + allCitys.elementAt(j) + "   " + allCitys.elementAt(k));
					}
			}
		}
		System.out.println("An optimal route is: ");
		System.out.println(findBestRoute());
		System.out.println("The total price for this route is: ");
		System.out.println(totalPrice);
		
	}
	
	
	/*
	 * this looks to see how many words are in the string 
	 * idk the code since it was riped from a website 
	 */
	public static int count_Words(String str)
    {
       int count = 0;
     
       try {
    	   if(!(" ".equals(str.substring(0, 1))) || !(" ".equals(str.substring(str.length() - 1))))
    			   {
    		   
    			   }
       }
       catch(StringIndexOutOfBoundsException e)
       {
    	   return 1;
       }
			
		
        if (!(" ".equals(str.substring(0, 1))) || !(" ".equals(str.substring(str.length() - 1))))
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == ' ')
                {
                    count++;
                }
  
            }
            count = count + 1; 
           
        }
       
        return count; // returns 0 if string starts or ends with space " ".
        
    }
	/*
	 * find where the next null is and returns it to the zone.
	 */
	public static int findZoneNull()
	{
		for(int i =0; i<=numOfZones;i++)
		{
			if(allZones[i]==null)
			{
			return i;
			}	
		}
	 return-1;
	}
	
	public static Vector<String> findBestRoute()
	{
		Vector<String> bestRoute = new Vector<String>();
		bestRoute.addElement(departureAirport);
		int day = 0;
		String bestDestination = null;
		for(int i = 0; i<numOfZones;i++)
		{
			for(int j =0; j<allCitys.size();j++)
			{
				for(int k =0; k<allCitys.size() - 1;k++)
				{
					if(flightCost[i][j][k] != 0 && day == i) {// All flights has cost
						if(day == 0)
						{
							totalPrice += flightCost[day][j][k];
							day = 1;
						}
						else if(flightCost[i][j][k] > flightCost[i][j][k+1])
						{
							bestDestination = allCitys.elementAt(k);							
							totalPrice += flightCost[i][j][k];
							day++;
							break;
						}
						else
						{
							bestDestination = allCitys.elementAt(k+1);
							totalPrice += flightCost[i][j][k];
							day++;
							break;
						}
					}
				}
			}
			if(bestDestination != null )
			{
				bestRoute.addElement(bestDestination);
			}
		}
		bestRoute.addElement(departureAirport);
		return bestRoute;
	}
	


}
