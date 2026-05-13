package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import tests.BaseTest;

public class TestListener implements ITestListener
{
	@Override
	public void onTestSuccess(ITestResult result)
	{
		// Take screenshot on SUCCESS
	    ScreenshotUtil.captureSceeenshot(BaseTest.getDriver(),"PASS");	    
    }

	@Override
    public void onTestFailure(ITestResult result)
    {
        // Take screenshot on FAILURE
		 ScreenshotUtil.captureSceeenshot(BaseTest.getDriver(),"FAIL");
    }

}
