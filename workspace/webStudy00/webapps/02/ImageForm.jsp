<%@page import="java.io.File"%>
<%@page import="java.io.FilenameFilter"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js">
</script>
<script type="text/javascript">
$(function() {
    const SRCPTRN = "%A?%N=%V"   // 상수값 (A:servlet Addr / N:name / V: value )
    //console.log($("#iamge"));
    const action = $('form')[0].action;            
    $("#image").on('change', function(event) {   // 익명함수(callBack 함수)
       $('#imageArea').empty();
       /* console.log(this.value);
       console.log($(this).val());*/
       // this == event.target
       //this.form.submit();
       // img 태그 생성
       console.log($('form')[0])
       var name = this.name;      
       var values = $(this).val();
       var imgs = [];
       $(values).each(function(idx,value) {
          var src = SRCPTRN.replace("%A", action)
                       .replace("%N", name)
                       .replace("%V", value)
          var img = $('<img>').attr("src", src);
          imgs.push(img);
       })
       // imageArea 에 img 태그를 innerHTML 로 삽입
       $('#imageArea').html(imgs);
    });
 })

   </script>

</head>

<body>
<h4><%=new Date() %></h4>
    <form action="../01/image.do">
    <%
    String folder = "d:/contents";
	File contents = new File(folder);
	String[] children = contents.list(new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/");
		}
	});
	StringBuffer options = new StringBuffer();
	for (String child : children) {
		options.append(String.format("<option>%s</option>", child));
	}

    %>
    <select name="image" id="image" multiple>
    <%=options %>
    </select>
    <input type="submit" vlaue="전송" style="background: red; color:white">
    </form>
    <div id="imageArea">
    </div>
    <!-- <script type="text/javascript">
    	var select = document.querySelector("#image");
    	select.onchange = function(event){
    		//event.target.//select
    		event.target.form.submit();
    	}
    </script> -->
</body>
</html>