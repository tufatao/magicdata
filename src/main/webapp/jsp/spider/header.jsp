<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id='header'>
        <!-- header-left -->
        <div id='header-left'>
            <!-- logo -->
            <div id='logo'><h1>Magic_Data</h1></div>
        </div>
        <!-- header-right -->
        <div id='header-right'></div>
        <!-- navigation -->
        <div id='navigation'><ul><li><a href="<%=basePath%>index.jsp">
        首页</a></li><li><a href="<%=basePath%>spiderAction!toRunProject">
        采集器</a></li><li><a href="<%=basePath%>bidAction!startTask">招投标</a></li>
        </ul></div>

    </div>