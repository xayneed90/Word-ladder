package assignment4;

import java.util.ArrayList;
import java.util.Collections;

public class Dictionary 
{
	ArrayList<String> current_dict = new ArrayList<String>();
	
	public Dictionary(ArrayList<String> input_dict_class)
	{
		current_dict = input_dict_class;
		Collections.sort(current_dict);
	}
	
	public ArrayList<String> getDictionary()
	{
		return current_dict;
	}
	
	// Compare words that have the same characters in the dictionary and create a dictionary of those words
	
	public ArrayList<String> get_same_char_dict(int index, String word)
	{
		ArrayList<String> same_char_dict = new ArrayList<String>();
		
		if(index == -1) //if index is -1, return the original current_dict
		{
			same_char_dict = current_dict;
			return same_char_dict;
		}
		int i=0;
		while( i<current_dict.size()) //else return all words with same character as the given index of the given word
		{
			if(word.charAt(index) == current_dict.get(i).charAt(index))
			{
				same_char_dict.add(current_dict.get(i));
			}
			i++;
		}
		
		return same_char_dict;
	}
	
	/**
	*go through the list of words (ArrayList<String> input_dict_class)
	*to find possibleWords, i.e the words that differ from the given word by only one character
	*@parameter index: the character at this index for the word being compared should be same
	*@parameter word: the word that every other word in the current_dict is compared against
	*@parameter input_dict_class: the sub-current_dict that is searched to find possibleWords ()
	*@return An ArrayList of 5 (length of the word) different ArrayList. The index of each list specifies the index of the character 
	*        that is different between the parameter word and the word in the same_char_dict
	*/
	public ArrayList<ArrayList<String>> getPossibleWords(int index, String word, ArrayList<String> input_dict_class)
	{
		ArrayList<ArrayList<String>>possibleWords= new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i<word.length(); i++) //Add n (length of the word) array lists
		{
			possibleWords.add(new ArrayList<String>()); 
		
		}
		
		int count = 0;
		int indexOfChange = 0;
		
		for(int i=0; i<input_dict_class.size();i++) //traverse the dictionay
		{
			int j=0;
			while( j<word.length()) //inc. count for each character that is different
			{
				if(j != index)
				{
					if(input_dict_class.get(i).charAt(j) != word.charAt(j))
					{
						count++;
						indexOfChange = j;
					}
				}
				
				if(j == index && (input_dict_class.get(i).charAt(j) != word.charAt(j))) //break if the character at the index of the 'word' and the word in current_dict is not same
				{
					//should never reach here
					//the current_dict input_dict_class should have gotten rid of all the words that will make this condition true
					count = 0;
					break;
				}
				j++;	
			}
			
			if(count == 1) // Adds the word in the possibleWords if change of characters is 1
			{
				
				possibleWords.get(indexOfChange).add(input_dict_class.get(i));
	
			}
			count = 0;
		}
		
		return possibleWords; //words that can be considered as the next state 
	}
		
}
