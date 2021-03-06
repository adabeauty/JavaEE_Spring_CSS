<%@ page import="com.tw.entity.Course" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: hgwang
  Date: 7/17/15
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>课程管理</title>

  <%--<link href="./lib/css/bootstrap.css" rel="stylesheet"/>--%>
  <link href="./styles/courses.css"rel="stylesheet"/>


  <script src="./lib/js/jquery-1.11.1.min.js"></script>
  <%--<script src="./lib/js/bootstrap.min.js"></script>--%>

  <script src="./js/courses.js"></script>
</head>
<body>
  <%@ include file="navBar.jsp" %>

  <div class="container">

    <table class="courses_table">
      <caption><h2>课程信息表</h2></caption>
      <tr>
        <th>名称</th>
        <th>教师</th>
        <th>修改</th>
        <th>删除</th>
      </tr>
      <%
        List<Course> courses = (List<Course>)request.getAttribute("courses");

        for(int i=0; i<courses.size(); i++){
      %>
      <tr>
        <td><%= courses.get(i).getName()%></td>
        <td><%= courses.get(i).getEmployee().getName()%></td>
        <td>
          <a href="./courses/update/<%= courses.get(i).getId()%>">修改</a>
        </td>
        <td>
          <a type="button" class="deleteCourse" data-id="<%= courses.get(i).getId()%>">删除</a>
        </td>
      </tr>
      <%
        }
      %>
    </table>

    <div>
      <a class="addCourse" type="button" href="./courses/creation">添加课程</a>
    </div>
  </div>
</body>
</html>
