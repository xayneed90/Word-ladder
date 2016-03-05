package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assign4Driver
{
	public static final String filename = "A4words.dat";
	static ArrayList<String> Dict_Array = new ArrayList<String>();
	
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
    	Dictionary_alter();
    	Dictionary new_Dict_array = new Dictionary(Dict_Array);
        Assignment4Interface wordLadderSolver = new WordLadderSolver(new_Dict_array);

        // Read file in
        // for each line in the file call compute ladder
        try 
        {
        	//call compute ladder with start word and end word to see if there is a word ladder in between them
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            for(String s: result)
            {
            	//print results
            	System.out.println(s);
            }
            System.out.println("**********");
            
        } 
        
        catch (NoSuchLadderException e) 
        {
           e.printStackTrace();
        }
    }
	
	/**
	 * Opens the file specified in String filename, reads each line in it
	 * then place the content into an arrayList of strings
	 * @param filename - the name of the file that needs to be read
	 */
	public static void Dictionary_alter () 
			throws FileNotFoundException, IOException 
	{ 
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				if(s.charAt(0) != '*')
				{
					Dict_Array.add(s.substring(0,5)); 
				}
			}
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			System.exit(-1);
		} 
        catch (IOException e) 
		{
			System.exit(-1);
		}
	}

}
