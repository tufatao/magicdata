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
    <title>添加任务</title>
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
                        <li><i class="fa fa-indent"></i>添加采集任务</li>
                    </ol>
                </div>
            </div>

            <div class="row">
				
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-tags red"></i><strong>添加采集任务</strong></h2>
							<div class="panel-actions">
								<a href="form-elements.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
								<a href="form-elements.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
								<a href="form-elements.html#" class="btn-close"><i class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="panel-body">
							
							<div id="wizard1" class="wizard-type1">
								<ul class="steps nav nav-pills">
								  	<li class="active"><a href="form-wizard.html#tab11" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-star"></i></span> 指定任务</a></li>
									<li><a href="form-wizard.html#tab12" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-credit-card"></i></span> 指定关键字</a></li>
									<li><a href="form-wizard.html#tab13" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-building"></i></span> 指定数据库</a></li>
									<li><a href="form-wizard.html#tab14" data-toggle="tab"><span class="badge badge-info"><i class="fa fa-check"></i></span> wait</a></li>
								</ul>
								<div class="progress thin">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 25%;">
									</div>
								</div>								
								
								<div class="tab-content">
								    <div class="tab-pane active" id="tab11">
										
								<form action="<%=basePath%>taskAction!addTask" method="post">
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>添加任务</strong></h2>
                        </div>
                        <div class="panel-body">
                        <input type="hidden" id="text-input" name="pid" value="${pro.pid}">
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属项目</label>
                                    <input type="text" class="form-control" value="${pro.name}" disabled>
                                </div>
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">任务名称</label>
                                    <input type="text" id="text-input" name="name" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">网站名称</label>
                                    <input type="text" id="text-input" name="webname" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="nf-password">Charset</label>
                                    <select id="select" name="charset" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="UTF-8">UTF-8</option>
                                        <option value="GBK">GBK</option>
                                    </select>
                                </div>
                                
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">起始URL</label>
                                    <input type="text" id="text-input" name="startUrl" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">Cookie</label>
                                    <input type="text" id="text-input" name="cookie" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">UserAgent</label>
                                    <input type="text" id="text-input" name="userAgent" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">UrlRegex</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="urlType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
                                    <input type="text" id="text-input" name="urlRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">UrlRegionRegex</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="urlRegionType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
                                    <input type="text" id="text-input" name="urlRegionRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
								  <label for="text-input">UrlListRegex</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="listType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
                                    <input type="text" id="text-input" name="listRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                            <button type="reset" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> 重置</button>
                        </div>
                            </form>
			
			</div>
								    <div class="tab-pane" id="tab12">
								 		<form action="<%=basePath%>taskAction!addFilter" method="post">
                        <div class="panel-heading">
                            <h2><i class="fa fa-indent red"></i><strong>添加Filter</strong></h2>
                        </div>
                        <div class="panel-body">
                        <input type="hidden" id="text-input" name="tid" value="${task.tid}">
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label class="control-label">所属任务</label>
                                    <input type="text" class="form-control" value="${task.name}" disabled>
                                </div>
                        <div class="form-group col-lg-8 col-lg-offset-1">
                                    <label for="text-input">Filter名称</label>
                                    <input type="text" id="text-input" name="name" class="form-control" placeholder="输入分组名">
                                </div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
								  <label class="control-label" for="select">题目规则</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="titleType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
										<input type="text" id="text-input" name="titleRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                <div class="form-group col-lg-8 col-lg-offset-1">
								  <label class="control-label" for="select">正文规则</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="conType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
										<input type="text" id="text-input" name="conRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
								<div class="form-group col-lg-8 col-lg-offset-1">
								  <label class="control-label" for="select">发布日期规则</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="pubDateType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
										<input type="text" id="text-input" name="pubDateRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
								<div class="form-group col-lg-8 col-lg-offset-1">
								  <label class="control-label" for="select">截止日期规则</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="endDateType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
										<input type="text" id="text-input" name="endDateRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
								<div class="form-group col-lg-8 col-lg-offset-1">
								  <label class="control-label" for="select">招标单位规则</label>
								  <div class="controls">
									<div class="input-group">
										<select id="select" name="unitType" class="form-control" size="1">
                                        <option value="0">Please select</option>
                                        <option value="1">正则</option>
                                        <option value="2">Xpath</option>
                                        <option value="3">Css</option>
                                    </select>
										<input type="text" id="text-input" name="unitRegex" class="form-control" placeholder="输入分组名">
									</div>	
								  </div>
								</div>
                                
                        </div>
                        <div class="panel-footer">
                            <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> 提交</button>
                            <button type="reset" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> 重置</button>
                        </div>
                            </form>

								    </div>
									<div class="tab-pane" id="tab13">
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
									<input type="button" class="btn btn-default button-previous disabled" name="prev" value="Prev">
									<input type="button" class="btn btn-success button-next pull-right" name="next" value="Next">
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
		
	</script>
<!-- script end -->
</body>
</html>

