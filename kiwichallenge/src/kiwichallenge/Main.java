package kiwichallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

class Main {
	
	static int numOfZones;
	static String departureAirport;
	static String currentAirport;
	int dayCounter = 0;
	int totalPrice = 0;
	static String curLine="";
	Boolean [] zones;
	String [] routeTaken;
	static String [] allZones;
	static String [] allCitys;
	int [][] dayOfFlight;
	int [][] priceOfFlight;
	static String leaving;
	static String going;
	static int date;
	static int cost;
	
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
		allZones = new String [numOfZones];
		Vector<String> allCitys = new Vector<String>();
		for(int i =0; i<numOfZones;i++)
		{
			allZones[i]=null;
		}	
		departureAirport = sc.next();
		sc.nextLine();
		
	
		
		for(int i =0; i<numOfZones*2;i++)
		{
		
			if(i%2==0)
			{
				
				allZones[findZoneNull()]=sc.nextLine();
			}
			else {
				curLine=sc.nextLine();
				int x = count_Words(curLine);
				if (x!=1)
				{
					String[] split= curLine.split("\\s+");
					System.out.println(split.length);
					for(int j =0; j<split.length;j++)
					{
						System.out.println(split[j]);
						allCitys.add(split[j]);
					}
				
				}
				else
				{
					allCitys.add(curLine);
				}
			}
			
		}	
		
		curLine=sc.nextLine();
		
		
		
		
		
		for(int i =0; i<numOfZones;i++)
		{
			System.out.println(allZones[i]);
		}
		for(int i =0; i<allCitys.size();i++)
		{
			System.out.println(allCitys.elementAt(i));
		}
		
	
	}
	
	
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
	


}
