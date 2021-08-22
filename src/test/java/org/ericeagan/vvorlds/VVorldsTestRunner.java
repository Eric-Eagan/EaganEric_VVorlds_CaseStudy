package org.ericeagan.vvorlds;

import org.ericeagan.vvorlds.suites.RepositoriesSuite;
import org.ericeagan.vvorlds.suites.SeleniumSuite;
import org.ericeagan.vvorlds.suites.ServicesSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class VVorldsTestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
	    		  VVorldsApplicationTests.class,
	    		  RepositoriesSuite.class,
	    		  SeleniumSuite.class,
	    		  ServicesSuite.class);

	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	   }
}
