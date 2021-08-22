package org.ericeagan.vvorlds;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"org.ericeagan.vvorlds.repositories", 
				 "org.ericeagan.vvorlds.selenium",
				 "org.ericeagan.vvorlds.services"})
public class VVorldsTestRunner {
}
