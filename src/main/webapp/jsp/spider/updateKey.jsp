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
    <title>更新关键字</title>
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
                        <li><i class="fa fa-list-alt"></i><a href="#">关键字管理</a></li>
                        <li><i class="fa fa-indent"></i>更新关键字</li>
                    </ol>
                </div>
            </div>

            <div class="row">

                <div class="col-lg-12">
                    <div class="panel panel-default">
                    <form action="<%=basePath%>keyAction!updateKey" method="post">
                        <input type="hidden" id="text-input" name="kid" value="${key.kid}">
                        
                    <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>添加关键字</strong></h2>
                        </div>
                        <div class="panel-body"><div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属项目</label>
                                    <input type="text" class="form-control" value="${pro.name}" disabled>
                                </div>
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">修改关键字</label>
                                    <input type="text" class="form-control" value="${key.name}" disabled>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">编号</label>
                                    <input type="text" id="text-input" name="num" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">所属分组</label>
                                    <select id="select" name="gid" class="form-control" size="1">
                                        <option value="">Please select</option>
                                        <c:forEach items="${groupList}" var="group" varStatus="status">
                                        <option value="${group.gid}">${group.name}</option>
                                        </c:forEach>
                                    </select>
                                    <a href="<%=basePath%>groupAction!toAddGroup"> 添加分组</a>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">筛选规则(符合要求的视为无效！！)</label>
								  <div class="controls">
									<div class="input-group">
									<label for="text-input">Any&nbsp;&nbsp;&nbsp;&nbsp;匹配任一词组或正则表达式(多个词组用逗号隔开)</label>	
                                    <input type="text" id="text-input" name="anyRegex" class="form-control" placeholder="输入分组名">
									</div>
									<div class="input-group">
									<label for="text-input">All&nbsp;&nbsp;&nbsp;&nbsp;匹配全部词组或正则表达式(多个词组用逗号隔开)</label>	
                                    <input type="text" id="text-input" name="allRegex" class="form-control" placeholder="输入分组名">
									</div>
									<div class="input-group">
									<label for="text-input">Not Any&nbsp;&nbsp;&nbsp;&nbsp;未匹配任一全部词组或正则表达式(多个词组用逗号隔开)</label>	
                                    <input type="text" id="text-input" name="anyNRegex" class="form-control" placeholder="输入分组名">
									</div>
									<div class="input-group">
									<label for="text-input">Not All&nbsp;&nbsp;&nbsp;&nbsp;未匹配全部词组或正则表达式(多个词组用逗号隔开)</label>	
                                    <input type="text" id="text-input" name="allNRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="nf-password">描述</label>
                                    <textarea id="textarea-input" name="brief" rows="3" class="form-control" placeholder="输入简单描述"></textarea>
                                   
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

