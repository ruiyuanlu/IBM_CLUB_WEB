<%@ page language="java" import="java.util.*,com.istc.Entities.Entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
List<Person> interviewees = (List<Person>)session.getAttribute("interviewList");
Person curPerson=new Person();
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>面试jsp页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="selfIntroduction" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  <font color="red"><s:fielderror fieldName="getIntervieweeError"/></font>
  <%if(interviewees != null &&  interviewees.size() > 0){ %>
       <form action="intervieweeCheck" method="post">
       <%for (Person p: interviewees){%>
       <%=p.getName()%>(<%=p.getID()%>)
       <input type="checkbox" name="isPassed" value=<%=p.getID()%>></input>通过
       <br/>
        <%}%>
        
	    <input type="submit" value="提交"/>   
    </form>
  <%}%>
  </body>
</html>
