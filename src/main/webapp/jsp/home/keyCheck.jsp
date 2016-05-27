<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <title>审核信息</title>
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
    <script type="text/javascript" src="<%=basePath%>js/forAjax.js"></script>
    <script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
    <!-- javascript -->
    <script type="text/javascript">
        function toFirst(){
            document.location.href="<%=basePath%>conAction!conPaging?pageMethod=first&currentPage=${currentPage}";
        }
        function toPrevious(){
            document.location.href="<%=basePath%>conAction!conPaging?pageMethod=previous&currentPage=${currentPage}";
        }
        function toNext(){
            document.location.href="<%=basePath%>conAction!conPaging?pageMethod=next&currentPage=${currentPage}";
        }
        function toEnd(){
            document.location.href="<%=basePath%>conAction!conPaging?pageMethod=last&currentPage=${currentPage}";
        }
        function toWhere(toPage){
            document.location.href="<%=basePath%>conAction!conPaging?pageMethod=refresh("+toPage+")&currentPage="+toPage;
        }
        
        function keyExpress(kid){
        var objS = document.getElementById("keySelect");
        var kid = objS.options[objS.selectedIndex].value;
        	document.location.href="<%=basePath%>bidAction!keyExpressB?kid=" + kid;
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
                        <li><i class="fa fa-list-alt"></i><a href="#">信息管理</a></li>
                        <li><i class="fa fa-indent"></i>审核信息</li>
                    </ol>
                </div>
            </div>

            <div class="row">

                <div class="col-lg-12">
                    <div class="panel panel-default">
                    <form action="<%=basePath%>keyAction!addKey" method="post">
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>关键字详情</strong></h2>
                        </div>
                        <div class="panel-body">
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属项目</label>
                                    <input type="text" class="form-control" value="${pro.name}" disabled>
                                </div>
                          
                        <div class="form-group col-lg-12 col-lg-offset-1">
                        <div class="row">
                        <div class="col-lg-1"><button type="button" class="btn btn-primary" onclick="javascript:window.location.href='<%=basePath%>bidAction!preKeyB'"
                        <c:if test="${indexKey<1}">value disabled</c:if> >Pre</button></div>
                        
                        <div class="col-lg-1 col-lg-offset-1"><label class="control-label">关键字</label></div>
                        <div class="col-lg-2"><select id="keySelect" name="kid" size="1" onchange="keyExpress()">
                                        <option value="${curKey.kid}">${curKey.name}</option>
                                        <c:forEach items="${myKeys}" var="key" varStatus="status">
                                        <option value="${key.kid}">${key.name}</option>
                                        </c:forEach>
                                    </select></div>
                        <div class="col-lg-1 col-lg-offset-1"><button type="button" class="btn btn-success" onclick="javascript:window.location.href='<%=basePath%>bidAction!nextKeyB'"
                        <c:if test="${indexKey>=fn:length(myKeys)-1}">value disabled</c:if> >Next</button></div>
                        </div></div>
                             <div class="form-group col-lg-8 col-lg-offset-1">
                             
                             <table class="table table-bordered table-striped">
								<tbody>
								<c:forEach items="${cons}" var="con" varStatus="status">
								
							<tr>
							<td>【${status.count + pageSize * (currentPage - 1)}】</td>
									<td><label class="switch switch-danger">
										      <input type="checkbox" class="switch-input switch-key" value="${con.cid }" <c:if test="${1 == con.delFlag}">checked</c:if>>
										      <span class="switch-label" data-on="On" data-off="Off" ></span>
										      <span class="switch-handle"></span>
										    </label></td>
									<td>
										<a href='#'>${con.title }</a>
									</td>
								</tr>
								</c:forEach>
							</tbody></table>
                             
                             </div>
                             <div class="form-group col-lg-10 col-lg-offset-1">
                             
                             <hr/>
                        <div class="row">
                             
                                    <div class="col-lg-1"><button type="button" class="btn btn-primary preArt" 
                                    >Pre</button></div>
                                    <div class="col-lg-8 col-lg-offset-1"><h4 class='infoTit'>${curCon.title }</h4>
                                    </div>
                                    <div class="col-lg-1"><button type="button"  
                                     class="btn btn-success nextArt">Next</button>
                                    </div>
                             </div>
                             <div>
                             <br/>
                             <label class="switch switch-danger">
										      <input type="checkbox" class="switch-input switch-key" value="${curCon.cid }" 
										       checked >
										      <span class="switch-label" data-on="On" data-off="Off"></span>
										      <span class="switch-handle"></span>
										    </label>
										    
                             <div id='infoCon'>
                             <p>
                             ${curCon.content }
                             </p>
                             </div>
                             
                             <br/>
                             <label class="switch switch-danger" >
										      <input type="checkbox" class="switch-input switch-key" value="${curCon.cid }"
										       checked >
										      <span class="switch-label" data-on="On" data-off="Off"></span>
										      <span class="switch-handle"></span>
										    </label>
                             </div>
                             <br>
                        <div class="row">
                             
                                    <div class="col-lg-1"><button type="button" class="btn btn-primary preArt" 
                                    >Pre</button></div>
                                    <div class="col-lg-8 col-lg-offset-1"><h4 class='infoTit'>${curCon.title }</h4>
                                    </div>
                                    <div class="col-lg-1"><button type="button"  
                                     class="btn btn-success nextArt">Next</button>
                                    </div>
                             </div>  
                        </div>
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

