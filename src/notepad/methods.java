/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

/**
 *
 * @author ANKIT
 */
public class methods {
    /* Java program to count no of words
from given input string. */

	static final int OUT = 0;
	static final int IN = 1;
	
	// returns number of words in str
	static int countWords(String str)
	{
		int state = OUT;
		int wc = 0; // word count
		int i = 0;
		
		// Scan all characters one by one
		while (i < str.length())
		{
			if (str.charAt(i) == ' ' || str.charAt(i) == '\n'
					|| str.charAt(i) == '\t')
				state = OUT;
				
	
			
			else if (state == OUT)
			{
				state = IN;
				++wc;
			}
	
			// Move to next character
			++i;
		}
		return wc;
	}
        
        

static int countOccurences(String str, String word)
{
	// split the string by spaces in a
        str=str.toLowerCase();
        word=word.toLowerCase();
	String a[] = str.split(" ");

	// search for pattern in a
	int count = 0;
	for (int i = 0; i < a.length; i++)
	{
	// if match found increase count
	if (word.equals(a[i]))
		count++;
	}

	return count;
}


}
