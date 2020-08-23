# mobile_automation_framework

This framework is created using Testng, Maven, Java and Appium, to run test on native apps on android platform.

Installation: below software required
- JDK
- ADB to run test on android
- Appium
- Node
- eclipse

How to Run
- Clone the given repo
- Open the project in eclipse editor
- Set the JAVA_HOME and ANDROID-HOME env throug run configuration
- Right click on pom.xml and do maven intall (this will install all required dependencies)
- Make sure you are connected to android device
- Right click on testng.xml and run testNGSuite
- This will run all the testcases tagged with @Test.

Framework Details:
- Followed POM approach (MobileAutomation/src/test/java/com/project)
- Reading environment setup related data from properties file and testdata from an JSON file
- All these files and apk file is stored insdie other_files folder
- Once the testcase get executed after each test pass or fail screenshot will get generated insdie screenshot folder at the root level
- Framework generates different kind of reports which can be seen under test-output folder with test pass or fail status and other details.
- Exception handling is also done wherever required.
- TestNG assertions has been used to validate test
- Server logs has been capture inside Serverlogs folder.

TestCase:
- Have created 3 testscenarios 
    login, search for Item and purchase Item
- Validation has been done for each test
- Also validating the title and price of item selected in search scenario on the checkout page (in purchaseItem test)
- Runnig test at class level from testng

