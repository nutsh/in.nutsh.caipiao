<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
            #divleft {
            float:left;
                margin: 0 10px;
            }
            #divright {
                float: right;
                width: 200px;
                margin-right:20px;
            }
</style>

<title>组合计算</title>
<script src="jquery/jquery.js" type="text/javascript"></script>
<script>

function showResult(){
	$.ajax({
		 url:"./CalculatorServlet1?size=" + $("#size").val() + "&num1=" + $("#nums1").val()+ "&num2=" + $("#nums2").val()+ "&num3=" + $("#nums3").val()+ "&num4=" + $("#nums4").val()+ "&num5=" + $("#nums5").val()+ "&num6=" + $("#nums6").val()+ "&num7=" + $("#nums7").val() , 
		 async: false,
		 dataType: 'html',
		 success:function(data){
			 $("#serverResponse").html(data);
			 $("#serverResponse").show();
		 }
	 });
	
	$.ajax({
		 url:"./TearServlet?size=" + $("#size").val() + "&num1=" + $("#nums1").val()+ "&num2=" + $("#nums2").val()+ "&num3=" + $("#nums3").val()+ "&num4=" + $("#nums4").val()+ "&num5=" + $("#nums5").val()+ "&num6=" + $("#nums6").val()+ "&num7=" + $("#nums7").val() , 
		 async: false,
		 dataType: 'html',
		 success:function(data){
			 $("#tearResponse").html(data);
			 $("#tearResponse").show();
		 }
	 });
}


</script>
</head>
<body>

<div id="divleft">
	<table>
		<tr>
			<td>个数</td>
			<td><input type="text" id="size" value="5"></td>
		</tr>
		<tr>
			<td colspan="2">组合选项(一组或多组)</td>
		</tr>
		<tr>
			<td>组合1</td>
			<td><input type="text" id="nums1"></td>
		</tr>
		<tr>
			<td>组合2</td>
			<td><input type="text" id="nums2"></td>
		</tr>
		<tr>
			<td>组合3</td>
			<td><input type="text" id="nums3"></td>
		</tr>
		<tr>
			<td>组合4</td>
			<td><input type="text" id="nums4"></td>
		</tr>
		<tr>
			<td>组合5</td>
			<td><input type="text" id="nums5"></td>
		</tr>
		<tr>
			<td>组合6</td>
			<td><input type="text" id="nums6"></td>
		</tr>
		<tr>
			<td>组合7</td>
			<td><input type="text" id="nums7"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="提交"
				onclick="showResult();"></td>
		</tr>
	</table>
	<div id="serverResponse"></div>
</div>
<div id="divright">
	<div id="tearResponse"></div>
</div>
	
	
</body>
</html>