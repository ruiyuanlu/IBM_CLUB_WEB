<%@ page language="java" import="java.util.*,club.istc.bean.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
List<Person> interviewees=new ArrayList<Person>();
if(session.getAttribute("interviewList")!=null)
	interviewees=(ArrayList<Person>)session.getAttribute("interviewList");
Person curPerson=new Person();
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'interview.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  <font color="red"><s:fielderror fieldName="getIntervieweeError"/></font>
  <%if(interviewees.size()!=0){ %>
       <form action="intervieweeCheck" method="post">
       <%for (int i=0;i<interviewees.size();i++){%>
       <%=interviewees.get(i).getName()%>(<%=interviewees.get(i).getID()%>)
       <input type="checkbox" name="passed" value=<%=interviewees.get(i).getID()%>></input>通过
       <br/>
        <%}%>
        
	    <input type="submit" value="提交"/>   
    </form>
  <%}%>  
  </body>
</html>
