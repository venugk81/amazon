package org.example;

import org.testng.TestNG;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestNG runner = new TestNG();
		runner.setTestClasses(new Class[] {scripts.profile.AmazonProfileScript.class});
		runner.run();

	}

}
