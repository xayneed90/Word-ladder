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
	
	//Find words that are only one character different than the first word
	
	public ArrayList<ArrayList<String>> get_one_char_diff_words(int index, String word, ArrayList<String> input_dict_class)
	{
		ArrayList<ArrayList<String>>one_char_diff_words= new ArrayList<ArrayList<String>>();
		int k = 0;
		while( k<word.length()) 								//CHECKING
		{														//  THE
			one_char_diff_words.add(new ArrayList<String>()); 	// WHOLE WORD
			k++;												// FOR SIMILARITIES
		}
		
		int count = 0;
		int indexOfChange = 0;
		
		// Going through the full dicitionary
		
		for(int i=0; i<input_dict_class.size();i++)
		{
			int j=0;
			while( j<word.length()) 										// KEEPING
			{																//   A
				if(j != index)												// COUNT
				{															//   OF
					if(input_dict_class.get(i).charAt(j) != word.charAt(j)) // DIFFERENT
					{														// CHARACTERS
						count++;
						indexOfChange = j;
					}
				}
				
				if(j == index && (input_dict_class.get(i).charAt(j) != word.charAt(j))) //Exit out of the loop if the index of the word and the current_array word are not the same
				{
					count = 0;
					break;
				}
				j++;	
			}
			
			if(count == 1) // Adds the word in the one_char_diff_words if change of characters is 1
			{
				
				one_char_diff_words.get(indexOfChange).add(input_dict_class.get(i));
	
			}
			count = 0;
		}
		
		return one_char_diff_words; //words that can be considered as the next state 
	}
		
}
