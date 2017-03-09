package com.test;

import com.smartbear.ready.cmd.runner.pro.SoapUIProTestCaseRunner;

// Need to override SoapUIProTestCaseRunner implementation because there is conflicts with log4j dependencies if you are using those in your project
public class SoapUIExtensionTestCaseRunner extends SoapUIProTestCaseRunner {

    private String[] testSuiteProperties = {};

    public SoapUIExtensionTestCaseRunner() {
        super();
    }

    public SoapUIExtensionTestCaseRunner(String title) {
        super(title);
    }

    public void setTestSuiteProperties(String[] testSuiteProperties) {
        this.testSuiteProperties = testSuiteProperties;
    }

    @Override
    public void setEndpoint(String endpoint) {
        if (endpoint != null) {
            super.setEndpoint(endpoint);
        }
    }

    @Override
   protected void initGroovyLog() {
        // stubbed to prevent multiple appenders, groovy.log is configured in soapui-log4j.xml
    }

}