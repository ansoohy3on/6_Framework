package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenAPIController {

	// 발급받은 인증키 변수 처리
	public static final String SERVICEKEY = "HWTS10u3V5oqV%2FkfC8oPQXrUseovkL6%2FnJCt7ZqN8uvwyXONrEcfOgKGurSdQPw%2F92nqhOvfq6xFcWxY7OKjrg%3D%3D";
	
	
	// json 형식으로 대기오염 OpenAPI 활용하기
	@ResponseBody
	// @RequestMapping(value = "air", produces = "application/json; charset=UTF-8")
	public String airMethod(String location) throws IOException {
		
		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICEKEY; // 서비스키 추가
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8"); // 지역명 추가(한글이 들어가면 인코딩 처리해야 함)
		url += "&returnType=json"; // 리턴 타입
		url += "&numOfRows=10"; // 결과 개수
		
		// 1. 작성된 url 정보를 넣어서 url 객체 생성
		URL requestUrl = new URL(url);
		
		// 2. 생성된 URL 객체로 URLConnection 생성
		HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();
		
		// 3. 요청 시 필요한 Header 설정하기
		urlConn.setRequestMethod("GET");
		
		// 4. 해당 OpenAPI 서버로 요청 후 입력 스트림을 통해서 응답데이터 읽어 오기
		BufferedReader br = new BufferedReader( new InputStreamReader( urlConn.getInputStream() ) );
		
		String responseText = "";
		String line;
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		
		// 5. 다 사용한 스트림 반납 및 연결 해제
		br.close();
		urlConn.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
	
	// xml 형식으로 대기오염 OpenAPI 활용하기
	@ResponseBody
	@RequestMapping(value="air", produces="text/xml; charset=UTF-8")
	public String airPollution(String location) throws IOException {
		
		// OpenAPI 서버로 요청하고자 하는 url 작성
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICEKEY; // 서비스키 추가
		url += "&sidoName=" + URLEncoder.encode(location, "UTF-8"); // 지역명 추가(한글이 들어가면 인코딩 처리해야 함)
		url += "&returnType=xml"; // 리턴 타입
		url += "&numOfRows=20"; // 결과 개수
		
		// 1. 요청할 주소를 전달해서 java.net.URL 객체 생성하기
		URL requestUrl = new URL(url);
		
		// 2. 생성된 URL 객체로 URLConnection 생성
		HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();
		
		// 3. 요청 시 필요한 Header 설정하기
		urlConn.setRequestMethod("GET");
		
		// 4. 해당 OpenAPI 서버로 요청 후 입력 스트림을 통해서 응답데이터 읽어 오기
		BufferedReader br = new BufferedReader( new InputStreamReader( urlConn.getInputStream() ) );
		
		String responseText = "";
		String line;
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		
		// 5. 다 사용한 스트림 반납 및 연결 해제
		br.close();
		urlConn.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
	
	// xml 형식으로 지진해일 대피소 OpenAPI 활용하기
	@ResponseBody
	@RequestMapping(value = "shelterList", produces = "application/json; charset=UTF-8")
	public String shelterList() throws IOException {
		
		System.out.println("test");
		String url = "http://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		url += "?ServiceKey=" + SERVICEKEY;
		url += "&pageNo=1";
		url += "&numOfRows=20";
		url += "&type=json";
		
		URL requestUrl = new URL(url);
		
		HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();
		
		urlConn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader( new InputStreamReader(urlConn.getInputStream()) );
		
		String responseText = "";
		String line;
		
		while((line=br.readLine()) != null) {
			responseText += line;
		}
		
		br.close();
		urlConn.disconnect();
		
		System.out.println(responseText);
		
		
		return responseText;
	}

}
