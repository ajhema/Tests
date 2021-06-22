package pages;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CreateMeeting {
	
    public void setup() {
    //Set DesiredCapabilities
	DesiredCapabilities cap= new DesiredCapabilities();
	cap.setCapability("deviceName", "Emulator");
	cap.setCapability("platformVersion", "11.0");
	cap.setCapability("platformName", "Android");
	cap.setCapability("appActivity","");
	AndroidDriver<AndroidElement> driver=new AndroidDriver<>(new URL("http://127.0.0.1.4723/wd/hub"), cap);
	driver.manage().timeouts.implicitlyWait(15, TimeUnit.SECONDS);
    }
    /*Each screen in the app corresponds to one java class file in page object model. Due to time constraint I have
     *coded all steps below in single java class file. Flow of execution: feature file->stepdefinition->utility->pages.
     */
    //create a recurring meeting in google calendar app
    driver.findElement(By.className("android.widget.ImageButton")).click();
    driver.findElementByAccessibilityId("Create new event").click();
    //Add title for meeting
    driver.findElementByXPath("//android.widget.EditText[@text='Add title']").sendKeys("workshop");
    driver.findElementByXPath("//android.widget.TextView[1]").click();
    //Set workshop meeting if it's friday
    if(driver.findElementByXPath("//android.widget.TextView[contains(@text,'Fri')]"))
    {
    	driver.findElementByAndroidUIAutomator("new UiSelector().selected(true)").click();
    }	
    
    driver.findElementByXPath("//android.widget.Button[@text='OK']")).click();
    //Set start time for meeting
    driver.findElementByXPath("//android.widget.TextView[1][@content-desc*='Start time']").click();
    driver.findElementByAccessibilityId("Switch to text input mode for the time input.").click();
    driver.findElementByAccessibilityId("android:id/input_hour").sendKeys("9");
    driver.findElementByAccessibilityId("android:id/input_minute").sendKeys("30");
    driver.findElementByAccessibilityId("android:id/button1").click();
    //Set end time for meeting
    driver.findElementByXPath("//android.widget.TextView[2][@content-desc*='End time']").click();
    driver.findElementByAccessibilityId("Switch to text input mode for the time input.").click();
    driver.findElementByAccessibilityId("android:id/input_hour").sendKeys("01");
    driver.findElementByAccessibilityId("android:id/input_minute").sendKeys("30");
    driver.findElementByAccessibilityId("android:id/am_pm_spinner").click();
    driver.findElementByXPath("//android.widget.CheckedTextView[@text='PM']").click();
    driver.findElementByAccessibilityId("android:id/button1").click();
    //Set event as recurring(every week)
    driver.findElementByAccessibilityId("com.google.android.calendar:id/container").click();
    driver.findElementByXPath("//android.widget.CheckedTextView[@text='Every week']").click();
    //Invite people to meeting
    driver.findElement(By.className("android.widget.FrameLayout")).click();
    driver.findElementByAccessibilityId("com.google.android.calendar:id/toolbar").sendKeys("xx@gmail.com");
    driver.findElementByXPath("//android.widget.Button[@text='Done']").click();
    //Save the meeting
    driver.findElementByXPath("//android.widget.Button[@text='Save']").click();
    //Verify meeting is created
    Assert.assertTrue(driver.findElementByXPath("//android.view.View[@content-desc='Workshop, 9:30 AM – 1:30 PM']").isPresent());
}
}
