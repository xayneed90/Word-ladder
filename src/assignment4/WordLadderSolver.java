/*
Zain Rasheed Rajput (zr2352)
Katelyn Ge
Thurs - 11:30am section
 */

package assignment4;

import java.util.ArrayList;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
    // delcare class members here.
	Dictionary dictionary;
	//initializes the two arraylists that are used in the word ladder solver
	ArrayList<String> Final_List = new ArrayList<String>();
	ArrayList<String> Discarded_list = new ArrayList<String>();
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
    if(startWord.length() != 5 || endWord.length() != 5 || startWord.matches("[a-zA-Z]+") == false || endWord.matches("[a-zA-Z]+") == false){
		throw new NoSuchLadderException("One or more of the following words was incorrect: " + startWord + " " + endWord);
	}
    else if (startWord == endWord){
    	Final_List.add(startWord);
    	Final_List.add(endWord);
    	return Final_List;
    }
	else{
		startWord.toLowerCase();
		endWord.toLowerCase();
		Final_List.add(startWord);
		//calls makeLadder with parameters and an index of -1 in order to denote the first word
		if(makeLadder(startWord,endWord,-1))
		{
			return Final_List;
		}
		else
		{
			throw new NoSuchLadderException("There is no possible ladder between these words.");
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
    	int new_index = toWord.length() - 1;
    	ArrayList<ArrayList<String>> possible_words = dictionary.getPossibleWords(index,fromWord,dictionary.get_same_char_dict(index,fromWord));    	
    	while(new_index >= 0){
    		if(new_index != index)
    		{   
    			int n = 0;
    			while( n < possible_words.get(new_index).size())
    			{
    				if(!Final_List.contains(possible_words.get(new_index).get(n)) && !Discarded_list.contains(possible_words.get(new_index).get(n)))
    				{
    					
	    				Final_List.add(possible_words.get(new_index).get(n));  				
	    				Final_result = makeLadder(possible_words.get(new_index).get(n), toWord, new_index);
	    				if(Final_result){ return Final_result; }
	    				else
	    				{ 
	    					Final_List.remove(possible_words.get(new_index).get(n));
	    					Discarded_list.add(possible_words.get(new_index).get(n));
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

