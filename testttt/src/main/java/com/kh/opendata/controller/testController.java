package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

	private static final String serviceKey = "HWTS10u3V5oqV%2FkfC8oPQXrUseovkL6%2FnJCt7ZqN8uvwyXONrEcfOgKGurSdQPw%2F92nqhOvfq6xFcWxY7OKjrg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "shelter", produces = "text/xml; charset=UTF-8")
	public String shelterMethod() throws IOException {
		
		String url = "http://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		url += "?serviceKey=" + serviceKey;
		url += "&pageNo=1";
		url += "&numOfRows=2";
		url += "&type=xml";
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		
		urlConnection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = null;
		String line;
		
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
	}

}
