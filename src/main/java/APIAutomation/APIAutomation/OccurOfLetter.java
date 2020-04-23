package APIAutomation.APIAutomation;

import java.util.Scanner;

import org.testng.annotations.Test;


public class OccurOfLetter {
	
	static String str = "geeksforgeeks"; 
	
	@Test
	public static class NoOfOccurenceOfCharacters { 
	    static final int MAX_CHAR = 256; 
	  
	    public void getOccuringChar() 
	    { 
	        // Create an array of size 256 i.e. ASCII_SIZE 
	    	int count[] = new int[MAX_CHAR]; 
	    	  
	        int len = str.length(); 
	  
	        // Initialize count array index 
	        for (int i = 0; i < len; i++) 
	            count[str.charAt(i)]++; 
	  
	        // Create an array of given String size 
	        char ch[] = new char[str.length()]; 
	        for (int i = 0; i < len; i++) { 
	            ch[i] = str.charAt(i); 
	            int find = 0; 
	            for (int j = 0; j <= i; j++) { 
	  
	                // If any matches found 
	                if (str.charAt(i) == ch[j])  
	                    find++;                 
	            } 
	  
	            if (find == 1)  
	                System.out.println("Number of Occurrence of " + 
	                 str.charAt(i) + " is:" + count[str.charAt(i)]);            
	        } 
	    } 
	    
	   
	} 

}
