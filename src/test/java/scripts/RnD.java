package scripts;

import java.io.File;

import org.testng.annotations.Test;

public class RnD {
  @Test
  public void f() {
	  
	  String current = System.getProperty("user.dir");		//current directory: C:\github\amazon\amazon
      System.out.println("Current working directory in Java : " + current);
      System.out.println("Home: "+ System.getProperty("user.home"));		///C:\Users\sri
      
      
      System.out.println(File.separator);		///  \
      
      
      
  }
}
