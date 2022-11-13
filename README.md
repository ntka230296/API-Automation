# API-Automation-Testing---Login-Create-new-work-information
1. This project uses Java.
2. I'm mainly working on Eclipse.
3. Need TestNG & POM libraries.
~~~~~~Steps to initialize project on Eclipse~~~~~~
Step 1: Create a new folder in the project with the name: Configuration.
Step 2: Add 2 files configs.properties & token.properties to Configuration folder.
Step 3: In src/main/java folder, create new package: com.api.auto.utils .
Step 4: Add PropertiesFileUtils.java to com.api.auto.utils package.
Step 5: In src/test/java folder, create new package: com.api.auto.testcase .
Step 6: Add TC_API_Login.java & TC_API_CreateWork.java to com.api.auto.testcase package.
Step 7: Change the order of class in testng.xml so that TC_API_Login.java will be executed first.
Step 8: Run the test using testng.xml .
Completed.
