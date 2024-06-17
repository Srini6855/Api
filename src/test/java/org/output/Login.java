package org.output;

import java.util.ArrayList;
import java.util.List;

import org.omrbranch.pojo.RootPojo;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.omrbranch.address.AddAddress_Input_Pojo;
import com.omrbranch.address.AddAddress_Output_Pojo;
import com.omrbranch.address.DeleteAddress_Input_Pojo;
import com.omrbranch.address.DeleteAddress_Output_Pojo;
import com.omrbranch.address.GetAddress_Output_pojo;
import com.omrbranch.address.UpdateAddress_Input_Pojo;
import com.omrbranch.address.UpdateAddress_Output_Pojo;
import com.omrbranch.baseclass.Baseclass;
import com.omrbranch.city.CityList_Input_Pojo;
import com.omrbranch.city.CityList_Output_Pojo;
import com.omrbranch.products.SearchProduct_Input_Pojo;
import com.omrbranch.products.SearchProduct_Output_Pojo;
import com.omrbranch.state.Datum;
import com.omrbranch.state.StateList_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Login extends Baseclass {
	String logToken;
	String adressId;
	String stateId;
	String cityId;
	int stateIdS;
	int cityIdS;

	@Test(priority = 1)
	public void login() {
		addHeader("accept", "application/json");
		addBasicAuth("srinipmps@gmail.com", "abEuKW6S@asg3Nv");
		Response reqType = addReqType("POST", "https://www.omrbranch.com/api/postmanBasicAuthLogin");
		System.out.println(getResStatusCode(reqType));
		System.out.println(getResponseBodyAsPrettyString(reqType));
		Assert.assertEquals(getResStatusCode(reqType), 200, "Verify the Response Code");
		RootPojo as = reqType.as(RootPojo.class);
		String first_name = as.getData().getFirst_name();
		logToken = as.getData().getLogtoken();
		Assert.assertEquals(first_name, "Srini", "Verify the first Name");

	}

	@Test(priority = 4)
	public void addUserAddress() {
		List<Header> header = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type:", "application/json'");
		header.add(h1);
		header.add(h2);
		header.add(h3);
		Headers headers = new Headers(header);
		addHeaders(headers);
		AddAddress_Input_Pojo input_Pojo = new AddAddress_Input_Pojo("Srinivasu", "A", "9876543210", "123", stateIdS,
				cityIdS, 12, "642129", "Pollachoi", "Home");
		addRequestBody(input_Pojo);
		Response response = addReqType("POST", "https://omrbranch.com/api/addUserAddress");
		System.out.println(getResStatusCode(response));
		System.out.println(getResponseBodyAsPrettyString(response));
		System.out.println(logToken);
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
		AddAddress_Output_Pojo as = response.as(AddAddress_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "Address added successfully", "Verify the Address added successfully");
		int address_id = as.getAddress_id();
		adressId = Integer.toString(address_id);

	}

	@Test(priority = 5)
	public void updateUserAddress() {
		List<Header> headers = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type:", "application/json'");
		headers.add(h1);
		headers.add(h2);
		headers.add(h3);
		Headers headers2 = new Headers(headers);
		addHeaders(headers2);
		UpdateAddress_Input_Pojo input_Pojo = new UpdateAddress_Input_Pojo(adressId, "Srinivasu", "A", "9876567890",
				"12", stateIdS, cityIdS, 12, "642129", "AKK", "Office");
		addRequestBody(input_Pojo);
		Response response = addReqType("PUT", "https://omrbranch.com/api/updateUserAddress");
		System.out.println(getResStatusCode(response));
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
		System.out.println(getResponseBodyAsPrettyString(response));
		UpdateAddress_Output_Pojo as = response.as(UpdateAddress_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "Address updated successfully", "Verify the Address updated successfully");

	}

	@Test(priority = 6)
	public void getUserAddress() {
		List<Header> header = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		header.add(h1);
		header.add(h2);
		Headers headers = new Headers(header);
		addHeaders(headers);
		Response response = addReqType("GET", "https://omrbranch.com/api/getUserAddress");
		System.out.println(getResStatusCode(response));
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
//		System.out.println(getResponseBodyAsPrettyString(response));
		GetAddress_Output_pojo as = response.as(GetAddress_Output_pojo.class);
		Assert.assertEquals(as.getMessage(), "OK", "Verify the Address listed successfully");

	}

	@Test(priority = 7)
	public void deleteAddress() {
		List<Header> headers = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type:", "application/json'");
		headers.add(h1);
		headers.add(h2);
		headers.add(h3);
		Headers headers2 = new Headers(headers);
		addHeaders(headers2);
		DeleteAddress_Input_Pojo input_Pojo = new DeleteAddress_Input_Pojo(adressId);
		addRequestBody(input_Pojo);
		Response response = addReqType("DELETE", "https://omrbranch.com/api/deleteAddress");
		System.out.println(getResStatusCode(response));
		System.out.println(getResponseBodyAsPrettyString(response));
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
		DeleteAddress_Output_Pojo as = response.as(DeleteAddress_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "Address deleted successfully", "Verify The address deleted Successfully");

	}

	@Test(priority = 8)
	public void searchProduct() {
		List<Header> headers = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h3 = new Header("Content-Type:", "application/json'");
		headers.add(h1);
		headers.add(h3);
		Headers headers2 = new Headers(headers);
		addHeaders(headers2);
		SearchProduct_Input_Pojo pojo = new SearchProduct_Input_Pojo("nuts");
		addRequestBody(pojo);
		Response response = addReqType("POST", "https://omrbranch.com/api/searchProduct");
		System.out.println(getResStatusCode(response));
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
		System.out.println(getResponseBodyAsPrettyString(response));
		SearchProduct_Output_Pojo as = response.as(SearchProduct_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "OK", "Verify the sucess message after search");

	}

	@Test(priority = 2)
	public void stateList() {
		addHeader("accept", "application/json");
		Response response = addReqType("GET", "https://omrbranch.com/api/stateList");
		System.out.println(getResStatusCode(response));
		System.out.println(getResponseBodyAsPrettyString(response));
		Assert.assertEquals(getResStatusCode(response), 200, "Verify the Response Code");
		StateList_Output_Pojo as = response.as(StateList_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "OK", "Verify the sucess message after search");
		ArrayList<Datum> data = as.getData();
		for (Datum datum : data) {
			String name = datum.getName();
			if (name.equals("Tamil Nadu")) {
				stateIdS = datum.getId();
				stateId = Integer.toString(stateIdS);
				break;
			}
		}
		System.out.println(stateId);

	}

	@Test(priority = 3)
	public void cityList() {
		List<Header> headers = new ArrayList<>();
		Header h1 = new Header("accept", "application/json'");
		Header h2 = new Header("Content-Type:", "application/json'");
		headers.add(h1);
		headers.add(h2);
		Headers headers2 = new Headers(headers);
		addHeaders(headers2);
		CityList_Input_Pojo input_Pojo = new CityList_Input_Pojo(stateId);
		addRequestBody(input_Pojo);
		Response response = addReqType("POST", "https://omrbranch.com/api/cityList");
		System.out.println(getResStatusCode(response));
		System.out.println(getResponseBodyAsPrettyString(response));
		CityList_Output_Pojo as = response.as(CityList_Output_Pojo.class);
		Assert.assertEquals(as.getMessage(), "OK", "Verify the sucess message after search");
		ArrayList<com.omrbranch.city.Datum> datum = new ArrayList<>();
		for (com.omrbranch.city.Datum datum2 : datum) {
			String name = datum2.getName();
			if (name.equals("Coimbatore")) {
				cityIdS = datum2.getId();
				cityId = Integer.toString(cityIdS);
			}
		}

	}

}
