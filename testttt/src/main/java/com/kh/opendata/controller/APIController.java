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
public class APIController {

	private static final String serviceKey = "HWTS10u3V5oqV%2FkfC8oPQXrUseovkL6%2FnJCt7ZqN8uvwyXONrEcfOgKGurSdQPw%2F92nqhOvfq6xFcWxY7OKjrg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "air.do", produces = "application/json; charset=UTF-8")
	public String airPollution(String location) throws IOException {
		
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		
		// url에 parameter 정보들을 추가하시오.
		url += "?serviceKey=" + serviceKey;
		url += "&returnType=json";
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
}
