<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--
        ===
        This comment should NOT be removed.

        Fantom v1.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit-usman
        ===
    -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>分组列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link href="<%=basePath%>Font-Awesome-master/css/font-awesome.min.css" rel="stylesheet">

    <link href="<%=basePath%>css/fantom-base.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/add-ons.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/fantom-mainStyle.css" rel="stylesheet">


    <!-- jQuery -->
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/fantom-base.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
    <!-- javascript -->
    <script type="text/javascript">
        function toFirst(){
            document.location.href="<%=basePath%>groupAction!groupPaging?pageMethod=first&currentPage=${currentPage}";
        }
        function toPrevious(){
            document.location.href="<%=basePath%>groupAction!groupPaging?pageMethod=previous&currentPage=${currentPage}";
        }
        function toNext(){
            document.location.href="<%=basePath%>groupAction!groupPaging?pageMethod=next&currentPage=${currentPage}";
        }
        function toEnd(){
            document.location.href="<%=basePath%>groupAction!groupPaging?pageMethod=last&currentPage=${currentPage}";
        }
        function toWhere(toPage){
            document.location.href="<%=basePath%>groupAction!groupPaging?pageMethod=refresh("+toPage+")&currentPage="+toPage;
        }
    </script>


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
<div id='container'>
    <!-- header -->
    
    <jsp:include page="header.jsp" flush="true"></jsp:include>
    <!-- content -->
    <div id='content'>
        <!-- content-left -->
        
    <jsp:include page="mainLeft.jsp" flush="true"></jsp:include>
        <!-- content-mid -->
        <div id='content-mid'>

            <div class="row">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
                        <li><i class="fa fa-list-alt"></i><a href="#">任务管理</a></li>
                        <li><i class="fa fa-indent"></i>任务列表</li>
                    </ol>
                </div>
            </div>

            <div class="row">

                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h2><i class="fa fa-table red"></i><span class="break"></span><strong>任务列表</strong></h2>
                            <div class="panel-actions">
                                <a href="table.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                                <a href="table.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                                <a href="table.html#" class="btn-close"><i class="fa fa-times"></i></a>
                            </div>
                        </div>
                        <div class="panel-body">
                            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>分组名</th>
                                    <th>简介</th>
                                    <th>上级分组</th>
                                    <th>类型</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${groupList}" var="group" varStatus="status">
                                
                                <tr>
                                    <td>【${status.count + 4 * (currentPage - 1)}】</td>
                                    <td>${group.name}</td>
                                    <td>${group.brief}</td>
                                    <td>${null == group.bidGroup ? 顶级分组 : group.bidGroup.name}</td>
                                    <td>
                                        <span class="label label-warning">Pending</span>
                                    </td>
                                    <td>
                                        <a class="btn btn-success" href="table.html#">
                                            <i class="fa fa-search-plus "></i>
                                        </a>
                                        <a class="btn btn-info" href="table.html#">
                                            <i class="fa fa-edit "></i>
                                        </a>
                                        <a class="btn btn-danger" href="table.html#">
                                            <i class="fa fa-trash-o "></i>

                                        </a>
                                    </td>
                                </tr>
                                </c:forEach>
                                
                                </tbody>
                            </table>
                            <ul class="pagination">
                                <li><a href="javascript:void(0);" onClick="toFirst()">首页</a></li>
                                <li>
                                    <c:if test="${1 < currentPage}"><a href="javascript:void(0);" onClick="toPrevious()">上一页</a></c:if>
                                </li>
                                <li><c:if test="${2 < currentPage}"><a href="javascript:void(0);" onClick="toWhere(${currentPage - 2})">${currentPage -2}</a></c:if></li>
                                <li><c:if test="${1 < currentPage}"><a href="javascript:void(0);" onClick="toWhere(${currentPage - 1})">${currentPage -1}</a></c:if></li>
                                <li class="active"><a>${currentPage}</a></li>
                                <li><c:if test="${totalPages > currentPage}"><a href="javascript:void(0);" onClick="toWhere(${currentPage + 1})">${currentPage + 1}</a></c:if></li>
                                <li><c:if test="${totalPages > currentPage+1}"><a href="javascript:void(0);" onClick="toWhere(${currentPage + 2})">${currentPage + 2}</a></c:if></li>
                                <li><c:if test="${totalPages > currentPage}"><a href="javascript:void(0);" onClick="toNext()">下一页</a></c:if></li>
                                <li><a href="javascript:void(0);" onClick="toEnd()">尾页</a></li>
                                <li><a>共${totalPages}页</a></li>
                                <li><a>共${rowNum}条</a></li>
                                
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- content-right  <div id='content-right'>content-right</div> -->
        <div class="clear"></div>
    </div>
    <!-- bottom -->
    
    <jsp:include page="bottom.jsp" flush="true"></jsp:include>
</div>
</body>
</html>