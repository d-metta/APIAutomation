package com.pageobjects;

public class SamplePage {
	
	
	public Boolean isDsiplayed() throws Exception {
		Boolean flag = false;
		try {	
				Boolean expDisplay = false;
				
				if(expDisplay.equals(true)) {
					return true;
				}else {
					return flag;
				}
			}catch(Exception ex) {
				throw new IllegalStateException();
			}
		}
	
	public Boolean verifyClassBooked() throws Exception {
		Boolean flag = false;
		try {	
				Boolean expDisplay = true;
				
				if(expDisplay.equals(true)) {
					return true;
				}else {
					return flag;
				}
			}catch(Exception ex) {
				throw new IllegalStateException();
			}
		}

}
