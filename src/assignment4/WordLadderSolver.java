/*
Zain Rasheed Rajput (zr2352)
Katelyn Ge (kbg488)
Thurs - 11:30am section
 */

package assignment4;

import java.util.ArrayList;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // declare class members here.
	Dictionary dictionary;
	//initializes the two ArrayLists that are used in the word ladder solver
	ArrayList<String> Final_List = new ArrayList<String>();
	ArrayList<String> Discarded_List = new ArrayList<String>();
	boolean Final_result = false;
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver(Dictionary d)
	{
		dictionary = d;
	}
    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    { 
    //check to see if startWord and endWord are five letters and are in the dictionary
    if (startWord.length() != 5 || endWord.length() != 5 || startWord.matches("[a-zA-Z]+") == false
				|| endWord.matches("[a-zA-Z]+") == false || !dictionary.current_dict.contains(startWord)
				|| !dictionary.current_dict.contains(endWord)) 	{
		System.out.println("For the input words \"" + startWord + "\" and \"" + endWord + "\"");
		throw new NoSuchLadderException("At least one of the words \"" + startWord + "\" and \"" + endWord + "\" are not legitimate 5 letter words from the dictionary");
	
	}
    else if (startWord.equals(endWord)){
    	Final_List.add(startWord);
    	Final_List.add(endWord);
    	System.out.println("For the input words \"" + startWord + "\" and \"" + endWord + "\" the following word ladder was found: ");
    	return Final_List;
    }
	else{
		startWord.toLowerCase();
		endWord.toLowerCase();
		Final_List.add(startWord);
		//calls makeLadder with parameters and an index of -1 in order to denote the first word
		if(makeLadder(startWord,endWord,-1))
		{
			System.out.println("For the input words \"" + startWord + "\" and \"" + endWord + "\" the following word ladder was found:");
			List<String> temp_List = new ArrayList<String>(Final_List);
			Final_List.clear();
			Discarded_List.clear();
			return temp_List;
		
		}
		else
		{
			Final_List.clear();
			Discarded_List.clear();
			System.out.println("For the input words \"" + startWord + "\" and \"" + endWord + "\"");
			throw new NoSuchLadderException("There is no word ladder between \""+ startWord + "\" and \"" + endWord + "\"!");
	
		}
	}
   
}

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public Boolean makeLadder(String fromWord, String toWord, int index)
    {	
    	if(toWord.equals(fromWord)){ 
    		return true;
    	}
    	int new_index = toWord.length()-1;
    	ArrayList<ArrayList<String>> possible_words = dictionary.get_one_char_diff_words(index,fromWord,dictionary.get_same_char_dict(index,fromWord));    	
    	while(new_index >=0){
    		if(new_index != index)
    		{   
    			int n = 0;
    			while( n < possible_words.get(new_index).size())
    			{
    				if(!Final_List.contains(possible_words.get(new_index).get(n)) && !Discarded_List.contains(possible_words.get(new_index).get(n)))
    				{
    					
	    				Final_List.add(possible_words.get(new_index).get(n));  				
	    				Final_result = makeLadder(possible_words.get(new_index).get(n), toWord, new_index);
	    				if(Final_result){ return Final_result; }
	    				else
	    				{ 
	    					Final_List.remove(possible_words.get(new_index).get(n));
	    					Discarded_List.add(possible_words.get(new_index).get(n));
	    				}
    				}
    				n++;
    			}
    			
    		}
    		new_index--;
    	}
    	return false;
    }
}

