<%@page import="java.io.File"%>
<%@page import="java.io.FilenameFilter"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
   <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
   <script type="text/javascript">
      
   $(function(){
      const SRCPTRN = "%A?%N=%V";   
      console.log($("form")[0]);
      const action = $("form")[0].action;
//      console.log($("#image"));
      $("#image").on("change", function(event){
         $("#imageArea").empty();
         //console.log(this.value);
         //console.log($(this).val());
         //this.form.submit();
         var imgs = [] 
         // img 태그 생성
         var name = this.name;   // $(this).attr("name")
         var values = $(this).val();
         
         $(values).each(function(idx, value){
            var src = SRCPTRN.replace("%A", action)
                         .replace("%N", name)
                         .replace("%V", value);
            var img = $("<img>").attr("src",src);
            imgs.push(img);
         });
         // console.log(value);
         // imageArea에 img 태그를 innerHTML로 삽입
         $("#imageArea").html(imgs);
         
      });
   });
   </script>
</head>
<body>
<h4><%=new Date() %></h4>
<form action = '<%=request.getContextPath() %>/01/image.do'>
<input name="_method" value="put" type="hidden">
<%
String[] children = (String[])request.getAttribute("children");
   StringBuffer options = new StringBuffer();

   for (String child : children) {
      options.append(String.format("<option>%s</option>", child));
   }
   
%>
<select name="image" id="image" multiple>
<%=options %>
</select>
<input type="submit" value="전송" style = "background-color:pink;">
</form>
<div id="imageArea"></div>
<script type="text/javascript">

//   var select = document.querySelectio("#image");
//   select.onchange = function(event){
//      event.target.form.submit();
//   }

</script>
</body>
</html>