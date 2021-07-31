# Demo Application
## Description
This is the application to retrieve the records

# Set up of the Application
## Run in Eclipse
1. Download the code 
2. Using Eclipse and Choose open Project from File System
3. Choose the Project
4. Click Run to Run the Demo Application

## Run in command line (Need to install JRE for Java 11)
1. Download the jar file
2. Open the Window Commond Line Interface
3. Move to the folder by is cd <Path Name>
4. Execute the jar by java -jar DemoApplication.jar

# Function of the Application
1. It will ask to put the owner username to serch records (Press Enter if you want to search all records)
2. The first 10 records data will be shown
3. Users can choose the following function
- Press 1 to see the next page
- Press 2 to change the page number you want to have a look
- Press 3 to change the number of records in the page
- Press 4 to search for different owner
- Press 0 to Exit the Application

# Design Decisions
1. The samples are retrieved from online by using API
2. It is using the spring and RestTemplate to retrieve the samples and parse them by using JSON
3. The records then print out and user can choose different functions provided
4. There is pagination in order to have a easier look to the users
5. If the expiry date is in the next seven days, there will be ## before the expiry date for the remark
