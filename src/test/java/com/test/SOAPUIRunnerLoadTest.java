package com.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import com.eviware.soapui.impl.wsdl.loadtest.WsdlLoadTest;
import com.eviware.soapui.impl.wsdl.panels.support.MockTestRunner;
import java.util.*;
import java.io.*;
import com.eviware.soapui.impl.wsdl.WsdlProjectPro;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.support.types.StringToObjectMap;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.impl.wsdl.WsdlProject; 
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase; 
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCaseRunner; 
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext; 
import com.eviware.soapui.model.testsuite.LoadTest; 
import com.eviware.soapui.model.testsuite.TestCase; 
import com.eviware.soapui.model.testsuite.TestStep; 
import com.eviware.soapui.model.testsuite.TestStepResult; 
import com.eviware.soapui.model.testsuite.TestSuite; 
import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus;
import com.eviware.soapui.model.testsuite.LoadTestRunner; 
import com.eviware.soapui.impl.wsdl.loadtest.data.actions.ExportLoadTestLogAction; 
import com.eviware.soapui.impl.wsdl.loadtest.data.actions.ExportStatisticsAction; 
import com.eviware.soapui.impl.wsdl.loadtest.log.LoadTestLog; 
import com.eviware.soapui.impl.wsdl.loadtest.log.LoadTestLogEntry; 
import com.eviware.soapui.support.StringUtils; 
import com.eviware.soapui.SoapUI; 

public class SOAPUIRunnerLoadTest {


	
	@Test
    public void test() {
		
		  try { 
                    String projectFile = "/var/lib/jenkins/workspace/App_Exec_LoadTest_TestNG/Pizzas-soapui-project.xml";
					WsdlProject project = new WsdlProject(projectFile); 
					for (TestSuite testSuite : project.getTestSuiteList()) { 

					System.out.println("TestSuiteProject size......" + project.getTestSuiteList().size()); 
					System.out.println("TestSuiteProject......" + testSuite.getName()); 

							for (TestCase testCase : testSuite.getTestCaseList()) { 

									System.out.println("TestCases........." + testCase.getName()); 
									

									

									StringToObjectMap properties = new StringToObjectMap(); 
									//properties.put("username", userName); 
									//properties.put("password", passWord); 
									//properties.put("localhost", localHost); 
									//properties.put("HttpDefaultPort", port); 
									List<TestStep> testSteps = testCase.getTestStepList(); 


									// TestRunner runner = testCase.run(properties, false); 
									WsdlTestCaseRunner wsdlTestCaseRunner = new WsdlTestCaseRunner((WsdlTestCase) testCase, properties); 
									WsdlTestRunContext wsdlTestRunContext = wsdlTestCaseRunner.createContext(properties); 
									wsdlTestCaseRunner.run(); 


                                    
									WsdlTestCase wsdlTestCase = wsdlTestCaseRunner.getTestRunnable(); 
									System.out.println("Test case " + wsdlTestCase); 
									

                                    
									List<LoadTest> loadtest = wsdlTestCase.getLoadTestList(); 
									System.out.println("Load test cases " + loadtest); 
									Iterator<LoadTest> loadIterator = loadtest.iterator(); 
									System.out.println("Load test size...." + loadtest.size()); 

									while (loadIterator.hasNext()) { 
											LoadTest loadTest = loadIterator.next(); 

											System.out.println("Load Test " + loadTest.getName()); 
											LoadTestRunner runner = loadTest.run(); 
											
											// wait for test to finish 
											while (!runner.hasStopped()) { 
												if (runner.getStatus() == Status.RUNNING) { 
													System.out.println("LoadTest [" + loadTest.getName() + "] progress: " + runner.getProgress() + ", " 
															+ runner.getRunningThreadCount()); 
												} 
												Thread.sleep(1000); 
											} 
								 
											System.out.println("LoadTest [" + loadTest.getName() + "] finished with status " + runner.getStatus().toString()); 
                                            
 
											//loadTest.getStatisticsModel().finish(); 
							                 
											//Assert.assertEquals( LoadTestRunner.Status.FAILED , runner.getStatus()); 
											Assert.assertEquals( LoadTestRunner.Status.FINISHED , runner.getStatus()); 
											
											System.out.println("Exporting log and statistics for LoadTest [" + loadTest.getName() + "]"); 
											 
											
											 
									} //end of while of loadRunner
                                    
									
							} 

					    //System.exit(1); 
					} 

			  } catch (Exception e) { 
			         e.printStackTrace(); 
			  } 




	   } 
	   
	      

							
							
	}
	
	


