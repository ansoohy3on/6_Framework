<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

<title>지진해일 대피소</title>
</head>
<body>

	<button id="btn">조회</button>

	<table border="1" id="result">
		<thead>
			<tr>
				<th>일련번호</th>
				<th>시도명</th>
				<th>시군구명</th>
				<th>대피지구명</th>
				<th>대피장소명</th>
				<th>주소</th>
				<th>경도</th>
				<th>위도</th>
				<th>수용가능인원수</th>
				<th>해변으로부터거리</th>
				<th>대피소 분류명</th>
				<th>해발높이</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>

	<script>
	
		$(function(){
			$("#btn").click(function(){
				$.ajax({
					
					url : "shelter",
					data : {},
					success : function(result){
						
						const shelterArr = $(result).find("row");
						
						let value;
						shelterArr.each(function(index, shelter){
							
							value += "<tr>"
									+ "<td>" + $(shelter).find("id").text() + "</td>"
									+ "<td>" + $(shelter).find("sido_name").text() + "</td>"
									+ "<td>" + $(shelter).find("sigungu_name").text() + "</td>"
									+ "<td>" + $(shelter).find("remarks").text() + "</td>"
									+ "<td>" + $(shelter).find("shel_nm").text() + "</td>"
									+ "<td>" + $(shelter).find("address").text() + "</td>"
									+ "<td>" + $(shelter).find("lon").text() + "</td>"
									+ "<td>" + $(shelter).find("lat").text() + "</td>"
									+ "<td>" + $(shelter).find("shel_av").text() + "</td>"
									+ "<td>" + $(shelter).find("lenth").text() + "</td>"
									+ "<td>" + $(shelter).find("shel_div_type").text() + "</td>"
									+ "<td>" + $(shelter).find("height").text() + "</td>"
									+ "</tr>"
									
						});
							
						$("#result > tbody").html(value);
						
					},
					error : function(){
						console.log("에러 발생");
					}
				})
			})
		})
	
	</script>
</body>
</html>
