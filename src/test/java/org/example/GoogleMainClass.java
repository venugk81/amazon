package org.example;

import org.testng.TestNG;

public class GoogleMainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNG runner = new TestNG();
		System.out.println("Execution started");
		runner.setTestClasses(new Class[] {scripts.GoogleScript.class});
		runner.run();
		System.out.println("Ended..");
	}

}
