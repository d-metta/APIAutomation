package com.testcases;

import org.testng.annotations.Test;

import com.pageobjects.SamplePage;

public class SampleTests {
	
	SamplePage samplePage=null;

	@Test
	public void tesA() {
		try {
			samplePage = new SamplePage();
			//First checkpint
			if(samplePage.isDsiplayed()) {
				System.out.println("The element is displayed successfully");
			}else {
				System.out.println("The element failed to display");
			}
			//Second checkpoint
			if(samplePage.verifyClassBooked()) {
				System.out.println("The class is booked  successfully");
			}else {
				System.out.println("The the class was not booked");
			}
			
		}
		catch(Exception ex) {
			
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	
	
}
