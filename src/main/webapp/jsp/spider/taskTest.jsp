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
    <title>任务采集测试</title>
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
    <script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    
	<!-- page scripts -->
	<script src="<%=basePath%>assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
	<script src="<%=basePath%>assets/plugins/chosen/js/chosen.jquery.min.js"></script>
	<script src="<%=basePath%>assets/plugins/autosize/jquery.autosize.min.js"></script>
	<script src="<%=basePath%>assets/plugins/placeholder/jquery.placeholder.min.js"></script>
	<script src="<%=basePath%>assets/plugins/wizard/jquery.bootstrap.wizard.min.js"></script>
	<script src="<%=basePath%>assets/plugins/maskedinput/jquery.maskedinput.min.js"></script>
	
	<!-- theme scripts -->
	<script src="<%=basePath%>assets/js/SmoothScroll.js"></script>
	<script src="<%=basePath%>assets/js/jquery.mmenu.min.js"></script>
	<script src="<%=basePath%>assets/js/core.min.js"></script>
	
	<!-- inline scripts related to this page -->
	<script src="<%=basePath%>assets/js/pages/form-wizard.js"></script>
  
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
                        <li><i class="fa fa-list-alt"></i><a href="#">任务管理</a></li>
                        <li><i class="fa fa-indent"></i>任务采集测试</li>
                    </ol>
                </div>
            </div>

            <div class="row">
				
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-tags red"></i><strong>任务采集测试</strong></h2>
							<div class="panel-actions">
								<a href="form-elements.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
								<a href="form-elements.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								<a href="form-elements.html#" class="btn-close"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							
							<div id="wizard1" class="wizard-type1">
								<ul class="steps nav nav-pills">
								  	<li class="active"><a href="form-wizard.html#tab11" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-star"></i></span> Step1:指定关键字</a></li>
									<li><a href="form-wizard.html#tab12" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-credit-card"></i></span> Step2:Url列表</a></li>
									<li><a href="form-wizard.html#tab13" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-building"></i></span> Step3:抓取结果</a></li>
									<li><a href="form-wizard.html#tab14" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-check"></i></span> wait</a></li>
								</ul>
								<div class="progress thin">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 25%;">
									</div>
								</div>								
								
								<div class="tab-content">
								    <div class="tab-pane active" id="tab11">
										
								<form action="<%=basePath%>testSpiderAction!processTask" method="post">
                        
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>指定关键字</strong></h2>
                        </div>
                        <div class="panel-body">
                        <input type="hidden" id="text-input" name="tid" value="${task.tid}">
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属任务</label>
                                    <input type="text" class="form-control" value="${task.name}" disabled>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">关键字名称</label>
                                    <input type="text" id="text-input" name="name" class="form-control" placeholder="输入分组名">
                                </div>
                                

                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                            <button type="reset" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> 重置</button>
                        </div>
                            </form>
			
			</div>
								    <div class="tab-pane" id="tab12">
								 		<table class="table table-striped table-bordered bootstrap-datatable datatable">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>类型</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${urls}" var="url" varStatus="status">
                                
                                <tr>
                                    <td>【${status.count + pageSize * (currentPage - 1)}】</td>
                                    <td><a href="${url}" target="_blank">${url}</a></td>
                                    <td>
                                        <span class="label label-warning">Pending</span>
                                    </td>
                                    <td>
                                        <a class="btn btn-success" target="_blank" href="<%=basePath%>testSpiderAction!toTestDetail?tid=${task.tid}&url=${url}">
                                            <i class="fa fa-search-plus "></i>
                                        </a>
                                        <a class="btn btn-info" href="">
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
								    </div>
									<div class="tab-pane" id="tab13">
										<form action="<%=basePath%>conAction!conUpdate" method="post">
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>信息详情</strong></h2>
                        </div>
                        <div class="panel-body">
                        <input type="hidden" id="text-input" name="pid" value="${con.cid}">
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">标题</label>
                                    <h3>${con.title}</h3>
                                    </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">发布日期</label>
                                    ${con.pubDate}
                                    </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">正文</label>
                                    ${con.content}
                                    </div>
                                    <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属项目</label>
                                    <input type="text" class="form-control" value="${pro.name}" disabled>
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">采集日期</label>
                                    ${con.startDate}
                                    </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="nf-password">链接</label>
                                    ${con.sourceUrl}
                                </div>
                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                            <a class="btn btn-danger" href="<%=basePath%>conAction!delCon?cid=${con.cid}">
                                            <i class="fa fa-trash-o "></i>

                                        </a>
                        </div>
                            </form>
								    </div>
									<div class="tab-pane" id="tab14">
										<div class="form-group">
									    	<label for="company-w1">Company</label>
									    	<input type="text" class="form-control" id="company-w1" placeholder="Company name">
									  	</div>

										<div class="form-group">
									    	<label for="vat-w1">VAT</label>
									    	<input type="text" class="form-control" id="vat-w1" placeholder="040.0253.001">
									  	</div>

										<div class="form-group">
									    	<label for="street-w1">Street</label>
									    	<input type="text" class="form-control" id="street-w1" placeholder="Street">
									  	</div>

										<div class="row">

									  		<div class="form-group col-sm-8">
										    	<label for="city-w1">City</label>
										    	<input type="text" class="form-control" id="city-w1" placeholder="City">
										  	</div>

											<div class="form-group col-sm-4">
										    	<label for="postal-code-w1">Postal Code</label>
										    	<input type="text" class="form-control" id="postal-code-w1" placeholder="Postal Code">
										  	</div>

										</div><!--/row-->

										<div class="form-group">
									    	<label for="country-w1">Country</label>
									    	<input type="text" class="form-control" id="country-w1" placeholder="Country">
									  	</div>
								    </div>
						
								</div>
								
								<div class="actions">								
									<input type="button" class="btn btn-default button-previous disabled" name="prev" value="上一步">
									<input type="button" class="btn btn-success button-next pull-right" name="next" value="下一步">
									<input type="button" class="btn btn-primary button-finish pull-right" name="finish" value="Finish" style="display: none;">
								</div>
									
							</div>

						</div>

					</div>
					
				</div><!--/col-->
			</div>
        </div>
        <!-- content-right  <div id='content-right'>content-right</div> -->
        <div class="clear"></div>
    </div>
    <!-- bottom -->
    
    <jsp:include page="bottom.jsp" flush="true"></jsp:include>
</div>

<!-- script start -->
	<script type="text/javascript">
	function selectAll(){
	 $(".checkAll").click(function() {
	 var subchecks = $(this).parents("table").find(":checkbox");
	 for(var i=0;i<subchecks.length;i++){
                if(subchecks[i].checked!=this.checked){
                    subchecks[i].click();
                }}});
	}
	
	function myAjax(url){
	$.ajax({ 
           type: "post", 
           url: "<%=basePath%>testSpiderAction!testUrls", 
           dataType: "json", 
           success: function (data) { 
           $("input#showTime").val(data[0].demoData); 
           }, 
           error: function (XMLHttpRequest, textStatus, errorThrown) { 
           alert(errorThrown); 
           } 
           });}
		
	</script>
<!-- script end -->
</body>
</html>

