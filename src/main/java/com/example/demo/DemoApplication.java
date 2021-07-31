package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoApplication {
	private static List<Sample> sampleList = new ArrayList<Sample>();
	private static final String apiKey_Search = "apiKey";
	private static final String url = "https://demos.researchspace.com/api/inventory/v1/samples";
	private static final int defaultPageNo = 1;
	private static final int defaultNoOfRec = 10;

	private static DateTimeFormatter outputDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter parseDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter parseDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	private String owner;
	private int pageNo = defaultPageNo;
	private int noOfRec = defaultNoOfRec;

	private Scanner in = null;

	public void run() {
		boolean running = true;

		try {
			in = new Scanner(System.in);

			System.out.printf(Constant.command_EnterOwner);
			owner = in.nextLine();
			retrieveSample(owner, pageNo, noOfRec);
			while (running) {
				System.out.printf(Constant.command_NextAction);
				System.out.printf(Constant.command_1);
				System.out.printf(Constant.command_2);
				System.out.printf(Constant.command_3);
				System.out.printf(Constant.command_4);
				System.out.printf(Constant.command_0);

				if (!in.hasNextInt()) {
					System.out.printf(Constant.command_WrongAction);
					in.next();
				} else {
					running = processInput(in.nextInt());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (in != null)
				in.close();
		}
	}

	private boolean processInput(int action) throws Exception {
		switch (action) {
		case 1:
			pageNo++;
			retrieveSample(owner, pageNo, noOfRec);
			break;
		case 2:
			System.out.printf(Constant.command_EnterPage);
			if (!in.hasNextInt()) {
				System.out.printf(Constant.command_WrongInt);
				in.next();
			} else {
				int retPageNo = in.nextInt();
				if (retPageNo < 1) {
					System.out.printf(Constant.command_PageLargerZero);
				} else {
					pageNo = retPageNo;
					retrieveSample(owner, pageNo, noOfRec);
				}
				break;
			}
		case 3:
			System.out.printf(Constant.command_EnterNoOfRec);
			if (!in.hasNextInt()) {
				System.out.printf(Constant.command_WrongInt);
				in.next();
				break;
			} else {
				int retNoOfRec = in.nextInt();
				if (retNoOfRec < 1 || retNoOfRec > 50) {
					System.out.printf(Constant.command_NoOfRecLargerZero);
				} else {
					noOfRec = retNoOfRec;
					pageNo = defaultPageNo;
					retrieveSample(owner, pageNo, noOfRec);
				}
				break;
			}
		case 4:
			System.out.printf(Constant.command_EnterOwner);
			in.nextLine();
			owner = in.nextLine();
			pageNo = defaultPageNo;
			retrieveSample(owner, pageNo, noOfRec);
			break;
		case 0:
			return false;
		default:
			break;
		}

		return true;
	}

	private void retrieveSample(String owner, int pageNo, int noOfRecord) throws Exception {
		String newurl = setUpUrl(owner, pageNo, noOfRecord);
		JSONObject jsonObj = retrieveSampleFromURL(newurl);
		if (jsonObj != null) {
			long totalNoOfRec = getTotalNoOfRec(jsonObj);
			sampleList = getSampleDetails(jsonObj);
			printTotal(sampleList, pageNo, noOfRecord, totalNoOfRec);
		} else {
			throw new Exception(Constant.label_RetrieveFailed);
		}
	}

	private void printTotal(List<Sample> sampleList, int pageNo, int noOfRecord, long totalNoOfRec) {
		System.out.printf(Constant.label_Total, totalNoOfRec);
		System.out.printf(Constant.label_Page, pageNo, noOfRecord);
		System.out.printf(Constant.label_PageTotal, sampleList.size());
		for (int i = 0; i < sampleList.size(); i++) {
			int sampleNo = (pageNo - 1) * noOfRecord + i + 1;
			System.out.printf(Constant.label_Sample, sampleNo);
			printContent(sampleList.get(i));

			// Print Empty Line
			System.out.println();
		}
	}

	private void printContent(Sample printSample) {
		System.out.printf(Constant.label_GlobalId, (printSample.getGlobalId()!= null ? printSample.getGlobalId() : ""));
		System.out.printf(Constant.label_Name, (printSample.getName()!= null ? printSample.getName() : ""));
		System.out.printf(Constant.label_Description, (removeHTMLTags(printSample.getDescription()) != null ? removeHTMLTags(printSample.getDescription()) : ""));
		System.out.printf(Constant.label_Type, (printSample.getType()!= null ? printSample.getType() : ""));
		System.out.printf(Constant.label_CreatedBy, (printSample.getCreatedBy()!= null ? printSample.getCreatedBy() : ""));
		System.out.printf(Constant.label_CreatedDate, (printSample.getCreateDateTime() != null ? printSample.getCreateDateTime().format(outputDateTimeFormat) : ""));
		System.out.printf(Constant.label_ModifiedBy, (printSample.getModifiedBy()!= null ? printSample.getModifiedBy() : ""));
		System.out.printf(Constant.label_ModifiedDate, (printSample.getModifiedDateTime() != null ? printSample.getModifiedDateTime().format(outputDateTimeFormat) : ""));
		System.out.printf(Constant.label_OwnedBy, (printSample.getOwnedBy()!= null ? printSample.getOwnedBy() : ""));
		System.out.printf(Constant.label_SampleSource, (printSample.getSampleSource()!= null ? printSample.getSampleSource() : ""));
		if (isSampleExpired(printSample.getExpiryDate()))
			System.out.printf(Constant.label_Expire);
		System.out.printf(Constant.label_ExpiryDate, (printSample.getExpiryDate()!= null ? printSample.getExpiryDate() : ""));
		System.out.printf(Constant.label_StorageTemp ,printSample.getStorageTempMin(), getDegreeUnit(printSample.getStorageTempMinUnit()), 
				printSample.getStorageTempMax(), getDegreeUnit(printSample.getStorageTempMaxUnit()));
		System.out.printf(Constant.label_Quantity , printSample.getQuantityVal(), getVolumeUnit(printSample.getQuantityUnit()));
	}

	private boolean isSampleExpired(LocalDate expiryDate) {
		return expiryDate != null && !expiryDate.isAfter(LocalDate.now().plusDays(7));
	}

	private String removeHTMLTags(String description) {
		if (description != null && !description.isEmpty()) {
			description = description.replaceAll("\\<.*?\\>", "");
			description = description.replaceAll("(?m)^[ \t]*\r?\n", "");
		}
		return description;
	}

	private String getDegreeUnit(int degreeUnitInt) {
		switch (degreeUnitInt) {
		case 8:
			return Constant.degree_Cel;
		case 9:
			return Constant.degree_Kel;
		case 10:
			return Constant.degree_Fah;
		default:
			return null;
		}
	}

	private String getVolumeUnit(int volumeUnitInt) {
		switch (volumeUnitInt) {
		case 2:
			return Constant.label_UL;
		case 3:
			return Constant.label_ML;
		case 4:
			return Constant.label_L;
		default:
			return null;
		}
	}

	private String setUpUrl(String ownedBy, int pageNo, int pageSize) throws UnsupportedEncodingException {
		String newurl = "";
		if (ownedBy != null && !ownedBy.trim().isEmpty()) {
			try {
				ownedBy = URLEncoder.encode(ownedBy, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println(Constant.label_URLFailed);
				throw e;
			}
			newurl = url.concat(Constant.search_Param).concat(Constant.search_OwnedBy).concat(ownedBy)
					.concat(Constant.search_And);
		} else {
			newurl = url.concat(Constant.search_Param);
		}
		newurl = newurl.concat(Constant.search_PageNumber).concat(String.valueOf(pageNo - 1));
		newurl = newurl.concat(Constant.search_And);
		newurl = newurl.concat(Constant.search_PageSize).concat(String.valueOf(pageSize));
		return newurl;
	}

	private JSONObject retrieveSampleFromURL(String newurl) throws Exception {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = null;

		HttpHeaders headers = new HttpHeaders();
		headers.add(apiKey_Search, Constant.apiKey_Val);
		// Create HTTP request with no body
		HttpEntity<?> request = new HttpEntity<>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(newurl, HttpMethod.GET, request, String.class);

		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			System.out.printf(Constant.label_ConnFailed);
			return null;
		} else {
			try {
				String respStr = response.getBody();
				jsonObj = (JSONObject) jsonParser.parse(respStr);
			} catch (ParseException e) {
				System.out.println(Constant.label_ParsingFailed);
				throw e;
			}

		}
		return jsonObj;
	}

	private long getTotalNoOfRec(JSONObject jsonObj) {
		long total = 0;
		total = (long) jsonObj.get(Constant.json_TotalHits);
		return total;
	}

	private List<Sample> getSampleDetails(JSONObject jsonObj) throws Exception {
		List<Sample> sampleList = new ArrayList<Sample>();
		JSONParser jsonParser = new JSONParser();

		try {
			JSONArray allCont = (JSONArray) jsonParser.parse(jsonObj.get(Constant.json_Samples).toString());
			for (int i = 0; i < allCont.size(); i++) {
				JSONObject sampleCont = (JSONObject) jsonParser.parse(allCont.get(i).toString());
				Sample retsample = new Sample();
				retsample.setGlobalId((String) sampleCont.get(Constant.json_GlobalId));
				retsample.setName((String) sampleCont.get(Constant.json_Name));
				retsample.setDescription((String) sampleCont.get(Constant.json_Description));
				retsample.setType((String) sampleCont.get(Constant.json_Type));
				retsample.setCreatedBy((String) sampleCont.get(Constant.json_CreatedBy));
				retsample.setModifiedBy((String) sampleCont.get(Constant.json_ModifiedBy));
				retsample.setSampleSource((String) sampleCont.get(Constant.json_SampleSource));
				JSONObject owner = (JSONObject) jsonParser.parse(sampleCont.get(Constant.json_Owner).toString());
				retsample.setOwnedBy((String) owner.get(Constant.json_Username));
				JSONObject storageTempMax = (JSONObject) jsonParser
						.parse(sampleCont.get(Constant.json_StorageTempMax).toString());
				retsample.setStorageTempMax(((Number) storageTempMax.get(Constant.json_NumericValue)).doubleValue());
				retsample.setStorageTempMaxUnit(((Number) storageTempMax.get(Constant.json_UnitId)).intValue());
				JSONObject storageTempMin = (JSONObject) jsonParser
						.parse(sampleCont.get(Constant.json_StorageTempMin).toString());
				retsample.setStorageTempMin(((Number) storageTempMin.get(Constant.json_NumericValue)).doubleValue());
				retsample.setStorageTempMinUnit(((Number) storageTempMax.get(Constant.json_UnitId)).intValue());
				JSONObject quantity = (JSONObject) jsonParser.parse(sampleCont.get(Constant.json_Quantity).toString());
				retsample.setQuantityVal(((Number) quantity.get(Constant.json_NumericValue)).doubleValue());
				retsample.setQuantityUnit(((Number) quantity.get(Constant.json_UnitId)).intValue());
				String expiryDateStr = (String) sampleCont.get(Constant.json_ExpiryDate);
				if (expiryDateStr != null && !expiryDateStr.isEmpty()) {
					retsample.setExpiryDate(LocalDate.parse(expiryDateStr, parseDateFormat));
				} else {
					retsample.setExpiryDate(null);
				}

				retsample.setCreateDateTime(
						LocalDateTime.parse((String) sampleCont.get(Constant.json_Created), parseDateTimeFormat));
				sampleList.add(retsample);
			}
		} catch (ParseException | DateTimeParseException e) {
			System.out.println(Constant.label_ParsingDetailFailed);
			throw e;
		}
		return sampleList;

	}

	public static void main(String[] args) {
		DemoApplication app = new DemoApplication();
		app.run();
	}
}
