package APIAutomation.APIAutomation;

import java.util.ArrayList;
import java.util.Scanner;
import org.testng.annotations.Test;

public class JavaMethods {

	//@Test(testName = "Extract numerics from string")
	public static void extractNumerics(String[] args) {
	    String str = "abc d 1234567890pqr 54897";
	    StringBuilder myNumbers = new StringBuilder();
	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isDigit(str.charAt(i))) {
	            myNumbers.append(str.charAt(i));
	            System.out.println(str.charAt(i) + " is a digit.");
	        } else {
	            System.out.println(str.charAt(i) + " not a digit.");
	        }
	    }
	    System.out.println("Your numbers: " + myNumbers.toString());
	}
	
	//@Test(testName = "reverseStringWithoutFunctions")
	public static void reverseStringWithoutFunctions() {
		
		
		Scanner sc=new Scanner(System.in);
		String str = sc.nextLine();
 
		//Solution 1.
		int l=str.length();
		String reverse="";
		for(int i=l-1;i>=0;i--)
			reverse= reverse + str.charAt(i);
		System.out.println(reverse);
	}
	
	//@Test(testName = "reverseStringWithFunction")
	public static void reverseStringWithFunction() {
		
		Scanner sc1=new Scanner(System.in);
		String str1 = sc1.nextLine();
		
		String reverse1="";
		StringBuilder sb= new StringBuilder(str1);
		reverse1 = sb.reverse().toString();
		System.out.println(reverse1);
	}
	
	@Test(testName = "sortWithoutFunctions")
	public static void sortWithoutFunctions(){
		String str = "jdkoepacmbtr";
	      System.out.println("Before Sorting:" + str);
	      int j = 0;
	      char temp = 0;
	      char[] chars = str.toCharArray();
	      for(int i=0; i < chars.length; i++) {
	         for(j=0; j < chars.length; j++) {
	            if(chars[j] > chars[i]) {
	               temp = chars[i];
	               chars[i] = chars[j];
	               chars[j] = temp;
	            }
	         }
	      }
	      System.out.println("After Sorting:");
	      for(int k=0; k < chars.length; k++) {
	         System.out.print(chars[k]);
	      }
	      
	}
	
	
	// Java program to demonstrate working of split(regex,limit) with high limit. 
	@Test(testName = "splitFunctioanlity")
	    public static void splitFunction() 
	    { 
	        String str = "geekss@for@geekss"; 
	        String[] arrOfStr = str.split("s", 5); 
	  
	        for (String a : arrOfStr) 
	            System.out.println(a); 
	    	}
	    
	    //Duplicate/Get Common Elements from two Arrays
	@Test(testName = "duplicateElementsFromTwoArrays")
	    public static void commonElementsFromArrays(){
	        int[] arr1 = {4,7,3,9,2};
	        int[] arr2 = {3,2,12,9,40,32,4};
	        for(int i=0;i<arr1.length;i++){
	            for(int j=0;j<arr2.length;j++){
	                if(arr1[i]==arr2[j]){
	                    System.out.println(arr1[i]);
	                }
	            }
	        }
	    }
	    
	    
	    //Duplicate/Get Common Elements from one Array
	
	    @Test(testName = "duplicateElementsFromOneArrays")
	    public static void commonElementsFromOneArray() 
	    {
	        int[] my_array = {1, 2, 5, 5, 6, 6, 7, 2};
	 
	        for (int i = 0; i < my_array.length-1; i++)
	        {
	            for (int j = i+1; j < my_array.length; j++)
	            {
	                if ((my_array[i] == my_array[j]) && (i != j))
	                {
	                    System.out.println("Duplicate Element : "+my_array[j]);
	                }
	            }
	        }
	    }  
	    
	    
	    static String str = "geeksforgeeks"; 
		
		@Test(testName = "occuranceOfLettersinaString")
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
