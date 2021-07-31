package com.example.demo;

public class Constant {
	public static final String apiKey_Val = "dJmoW1juVc8jT1wTwAb1mEeF8dKOc3Z8" ;
	
	public static final String empty_Str = "";
	
	public static final String search_Param = "?";
	public static final String search_And = "&";
	public static final String search_OwnedBy = "ownedBy=";
	public static final String search_PageNumber = "pageNumber=";
	public static final String search_PageSize = "pageSize=";
	
	public static final String command_EnterOwner = "Please enter the username of the owner you want to find: (if want to find all, Press Enter) \r\n";
	public static final String command_NextAction = "Please select the action: \r\n";
	public static final String command_EnterPage = "Please enter the page number \r\n";
	public static final String command_EnterNoOfRec = "Please enter the no of records to be shown in the page \r\n";
	public static final String command_WrongAction = "Wrong Action! Please enter the correct action\r\n";
	public static final String command_WrongInt = "Input is not an Integer \r\n";
	public static final String command_PageLargerZero = "Page Number must be larger than 0 \r\n";
	public static final String command_NoOfRecLargerZero = "Number of Records must be between 1 and 50 zrzn";
	public static final String command_1 = "1 - Next Page \r\n";
	public static final String command_2 = "2 - Select Page Number \r\n";
	public static final String command_3 = "3 - Change the Number of Records shown \r\n";
	public static final String command_4 = "4 - Change the Owner of User to find \r\n";
	public static final String command_0 = "0 - Exit the Application \r\n";
	
	public static final String degree_Cel = " C";
	public static final String degree_Fah = " F";
	public static final String degree_Kel = " K";
	
	public static final String label_ML = "ml";
	public static final String label_L = "l";
	public static final String label_UL = "ul";
	
	public static final String label_Total = "There are Total %d records\r\n";
	public static final String label_Page = "This is Page %d (%d Records in each page)\r\n";
	public static final String label_PageTotal = "%d Records in this Page: \r\n";
	public static final String label_Sample = "Sample %d: \r\n";
	public static final String label_GlobalId = "Global ID: %s \r\n" ;
	public static final String label_Name = "Name: %s \r\n" ;
	public static final String label_Description = "Description: %s \r\n" ;
	public static final String label_Type = "Type: %s \r\n" ;
	public static final String label_CreatedBy = "Created By: %s \r\n" ;
	public static final String label_CreatedDate = "Created Date: %s \r\n" ;
	public static final String label_ModifiedBy = "Modified By: %s \r\n" ;
	public static final String label_ModifiedDate = "Modified Date: %s \r\n" ;
	public static final String label_ExpiryDate = "Expiry Date: %s \r\n" ;
	public static final String label_OwnedBy = "Owned By: %s \r\n" ;
	public static final String label_SampleSource = "Sample Source: %s \r\n" ;
	public static final String label_StorageTemp = "Storage Temperature: %.3f%s to %.3f%s \r\n";
	public static final String label_Quantity = "Total Quantity: %.3f%s \r\n";
	
	public static final String label_ConnFailed = "Failed Connection";
	public static final String label_URLFailed = "Set Up URL Error";
	public static final String label_RetrieveFailed = "Error in retrieving samples";
	public static final String label_ParsingFailed = "Data Parsing Error";
	public static final String label_ParsingDetailFailed = "Parsing Records Detail Failed";
	
	public static final String label_Between = " to ";
	public static final String label_Expire = "##";
	
	public static final String json_TotalHits = "totalHits";
	public static final String json_Samples = "samples";
	public static final String json_GlobalId = "globalId";
	public static final String json_Name = "name";
	public static final String json_Description = "description";
	public static final String json_Type = "type";
	public static final String json_CreatedBy = "createdBy";
	public static final String json_ModifiedBy = "modifiedBy";
	public static final String json_SampleSource = "sampleSource";
	public static final String json_Owner = "owner";
	public static final String json_Username = "username";
	public static final String json_StorageTempMax = "storageTempMax";
	public static final String json_StorageTempMin = "storageTempMin";
	public static final String json_Quantity = "quantity";
	public static final String json_NumericValue = "numericValue";
	public static final String json_UnitId = "unitId";
	public static final String json_ExpiryDate = "expiryDate";
	public static final String json_Created = "created";
}
