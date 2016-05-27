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
    <title>添加客户</title>
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
                        <li><i class="fa fa-list-alt"></i><a href="#">客户管理</a></li>
                        <li><i class="fa fa-indent"></i>添加客户</li>
                    </ol>
                </div>
            </div>

            <div class="row">

                <div class="col-lg-12">
                    <div class="panel panel-default">
                    <form action="<%=basePath%>cusAction!addCus" method="post">
                        
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>添加客户</strong></h2>
                        </div>
                        <div class="panel-body">
                        <input type="hidden" id="text-input" name="pid" value="${pro.pid}">
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属项目</label>
                                    <input type="text" class="form-control" value="${pro.name}" disabled>
                                </div>
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">名称</label>
				                        <div class="input-group">
				                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
				                        <input type="text" id="username" name="name" class="form-control" placeholder="Username">
				                    </div>
				                    </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">公司</label>
                                    <input type="text" id="text-input" name="company" class="form-control" placeholder="输入分组名">
                                </div>
                                
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">电话</label>
                                <div class="input-group">
									  <span class="input-group-addon"><i class="fa fa-phone"></i></span>
									  <input type="text" id="phone" name="phone" class="form-control">
									</div>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">Email</label>
                                <div class="input-group">
				                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
				                        <input type="email" id="email" name="email" class="form-control" placeholder="Email address">
				                    </div>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">简介</label>
                                    <input type="text" id="text-input" name="brief" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">UrlRegex</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="cusType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
                                    <input type="text" id="text-input" name="cusRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">联系人</label>
                                    <input type="text" id="text-input" name="contact" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">区域</label>
                                    <select id="select" name="group" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="内地">内地</option>
                                        <option value="海外">海外</option>
                                        <c:forEach items="" var="group" varStatus="status">
                                        <option value="内地">内地</option>
                                        </c:forEach>
                                    </select>
                                </div>
                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                            <button type="reset" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> 重置</button>
                        </div>
                            </form>
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

