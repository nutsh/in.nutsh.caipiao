<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组合计算</title>
<script type="text/javascript">
	var xmlHttp;
	//创建xmlHttp  
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		}
	}

	//拼出要发送的姓名数据  
	function createQueryString() {
		var nums = document.getElementById("nums").value.trim();
		var size = document.getElementById("size").value.trim();

		var queryString = "nums=" + nums + "&size=" + size;
		return queryString;
	}

	function showResult() {
		createXMLHttpRequest();
		var queryString = "./CalculatorServelt?";
		queryString = queryString + createQueryString() + "&timeStamp="
				+ new Date().getTime();
		xmlHttp.onreadystatechange = handleStateChange;
		xmlHttp.open("GET", queryString, true);
		xmlHttp.send(null);
	}

	function handleStateChange() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				parseResults();
			}
		}
	}
	//解析返回值  
    function parseResults()  
    {  
     var responseDiv=document.getElementById("serverResponse");  
     responseDiv.innerHTML = xmlHttp.responseText;
    }  
</script>
</head>
<body>
	<table>
		<tr>
			<td>数字(1,2,3,4,5,6)</td>
			<td><input type="text" id="nums"></td>
		</tr>
		<tr>
			<td>个数</td>
			<td><input type="text" id="size" value="5"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="提交"
				onclick="showResult();"></td>
		</tr>
	</table>
	<div id="serverResponse">
	</div>
</body>
</html>