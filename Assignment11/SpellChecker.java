import java.io.*;
import java.util.*;
public class SpellChecker
{
	public static void main (String [] args) throws IOException
	{
		Scanner fileReader = new Scanner (new File ("words.txt"));
		Hashtable <Integer, String> dictionary = new Hashtable <Integer, String> ();
		
		while (fileReader.hasNext())
		{
			String word = fileReader.next();
			int hashCode = word.hashCode();
			dictionary.put(hashCode, word);
		}
		
		System.out.print("Type a lowercase word: ");
		
		Scanner inputReader = new Scanner (System.in);		
		String inputWord = inputReader.next();
		
		if (checkWord (dictionary, inputWord))
		{
			System.out.println(inputWord + " was spelled correctly. No mistakes found.");
		}
		
		else
		{
			System.out.println("Your word was spelled incorrectly. Here are a few suggestions: ");
			
			// If the word is missing one letter from the beginning
			for (int letter = 97; letter <= 122; letter++)
			{
				String word = (char)letter + inputWord;
				
				if (checkWord (dictionary, word))
				{
					System.out.println(word);
				}
			}
			
			// If the word is missing one letter from the end
			for (int letter = 97; letter <= 122; letter++)
			{
				String word = inputWord + (char)letter ;
				
				if (checkWord (dictionary, word))
				{
					System.out.println(word);
				}
			}
			
			// If the word has an extra letter at the beginning
			if (checkWord(dictionary, inputWord.substring(1)))
			{
				System.out.println(inputWord.substring(1));
			}
				
			// If the word has an extra letter at the beginning
			if (checkWord(dictionary, inputWord.substring(0, inputWord.length()-1)))
			{
				System.out.println(inputWord.substring(0, inputWord.length()-1));
			}	
			
			// If two adjacent characters are swapped
			for (int i = 1; i < inputWord.length(); i++)
			{
				String before = inputWord.substring(0, i-1);
				String after = inputWord.substring(i+1);
				
				String word = before + inputWord.charAt(i) + inputWord.charAt(i-1) + after;

				if (checkWord (dictionary, word))
				{
					System.out.println(word);
				}
			}
				
		}
	}
	
	public static boolean checkWord (Hashtable <Integer, String> dictionary, String word)
	{
		if (dictionary.containsKey(word.hashCode()) && dictionary.containsValue(word))
		{
			return true;
		}
		return false;
	}
}