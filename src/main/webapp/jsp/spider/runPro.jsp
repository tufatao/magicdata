<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<title>执行采集任务</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=basePath%>Font-Awesome-master/css/font-awesome.min.css"
	rel="stylesheet">

<link href="<%=basePath%>css/fantom-base.min.css" rel="stylesheet">
<link href="<%=basePath%>css/add-ons.min.css" rel="stylesheet">
<link href="<%=basePath%>css/fantom-mainStyle.css" rel="stylesheet">


<!-- jQuery -->
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
    <script src="js/forAjax.js"></script>
    <script language="javascript" type="text/javascript" src="<%=basePath%>js/datePicker/WdatePicker.js"></script>

<!-- page scripts -->
<script
	src="<%=basePath%>assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
<script src="<%=basePath%>assets/plugins/chosen/js/chosen.jquery.min.js"></script>
<script
	src="<%=basePath%>assets/plugins/autosize/jquery.autosize.min.js"></script>
<script
	src="<%=basePath%>assets/plugins/placeholder/jquery.placeholder.min.js"></script>
<script
	src="<%=basePath%>assets/plugins/wizard/jquery.bootstrap.wizard.min.js"></script>
<script
	src="<%=basePath%>assets/plugins/maskedinput/jquery.maskedinput.min.js"></script>

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
							<li><i class="fa fa-home"></i><a href="index.html">Home</a>
							</li>
							<li><i class="fa fa-list-alt"></i><a href="#">任务管理</a></li>
							<li><i class="fa fa-indent"></i>执行采集任务</li>
						</ol>
					</div>
				</div>

				<div class="row">

					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h2>
									<i class="fa fa-tags red"></i><strong>执行任务</strong>
								</h2>
								<div class="panel-actions">
									<a href="form-elements.html#" class="btn-setting"><i
										class="fa fa-rotate-right"></i> </a> <a href="form-elements.html#"
										class="btn-minimize"><i class="fa fa-chevron-up"></i> </a> <a
										href="form-elements.html#" class="btn-close"><i
										class="fa fa-times"></i> </a>
								</div>
							</div>
							<div class="panel-body">

								<div id="wizard1" class="wizard-type1">
									<ul class="steps nav nav-pills">
										<li class="active"><a href="form-wizard.html#tab11"
											data-toggle="tab"><span class="badge badge-info"><i
													class="fa fa-star"></i> </span> 指定任务</a></li>
									</ul>
									<div class="progress thin">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="0" aria-valuemin="0"
											aria-valuemax="100" style="width: 25%;"></div>
									</div>

									<div class="tab-content">
										<div class="tab-pane active" id="tab11">

											<form action="<%=basePath%>spiderAction!runProject"
												method="post">
												<div class="row">

													<div class="form-group col-sm-4">
														<label for="ccmonth-w1">项目</label> <select
															class="form-control" name="pid" id="ccmonth-w1">
															<option value="0">请选择</option>
															<c:forEach items="${proList}" var="pro"
																varStatus="status">
																<option value="${pro.pid}">${pro.name}</option>
															</c:forEach>
														</select>
													</div>

													<div class="form-group col-sm-4">
														<label for="postal-code-w1">开始日期</label> <input
															type="text" class="form-control" onclick="WdatePicker()" name="minDate" />
													</div>

													<div class="form-group col-sm-4">
														<label for="postal-code-w1">结束日期</label> <input
															type="text" class="form-control" onclick="WdatePicker()" name="maxDate" />
													</div>

													<div class="form-group col-sm-4">
														<label for="postal-code-w1">重试次数</label> <input
															type="text" class="form-control" name="retry" />
													</div>
													<div class="form-group col-sm-4">
														<label for="postal-code-w1">并行数</label> <input
															type="text" class="form-control" name="taskNum" />
													</div>

													<div class="form-group col-sm-4">
														<label for="postal-code-w1">超时时长</label> <input
															type="text" class="form-control" name="timeOut" />
													</div>

													<div class="form-group col-sm-4">
														<label for="postal-code-w1">间隔时间</label> <input
															type="text" class="form-control" name="sleepTime" />
													</div>
													<div class="form-group col-sm-4">
														<label for="postal-code-w1">任务名</label> <input type="text"
															class="form-control" id="postal-code-w1"
															placeholder="Postal Code">
													</div>

												</div>
												<!--/row-->
												<div class="form-group form-actions">
													<button type="submit" class="btn btn-sm btn-success btn-tou">
														开始采集</button>
												</div>

												<div class="row">
													<div class="col-lg-12">

														<c:forEach items="${proList}" var="pro" varStatus="sta">

															<div class="panel panel-default">
																<div class="panel-heading">
																	<h2>
																		<i class="fa fa-table red"></i><span class="break"></span><strong>${pro.name}</strong>
																	</h2>
																	<div class="panel-actions">
																		<a href="table.html#" class="btn-setting"><i
																			class="fa fa-rotate-right"></i> </a> <a
																			href="table.html#" class="btn-minimize"><i
																			class="fa fa-chevron-up"></i> </a> <a href="table.html#"
																			class="btn-close"><i class="fa fa-times"></i> </a>
																	</div>
																</div>
																<div class="panel-body">
																	<table
																		class="table table-striped table-bordered bootstrap-datatable datatable">
																		<thead>
																			<tr>
																				<th><input class="checkAll" type="checkbox"
																					onClick="selectAll()" />全选</th>
																				<th>序号</th>
																				<th>任务名</th>
																				<th>网站</th>
																				<th>编码</th>
																				<th>状态</th>
																				<th>操作</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach items="${pro.bidTasks}" var="task"
																				varStatus="status">

																				<tr>
																					<td><input type="checkbox" class="tid"
																						name="tid" value="${task.tid}"></td>
																					<td>【${status.count + pageSize * (currentPage
																						- 1)}】</td>
																					<td>${task.name}</td>
																					<td>${task.webname}</td>
																					<td>${task.charset}</td>
																					<td><span class="label label-warning">${task.str1}</span>
																					</td>
																					<td><a class="btn btn-success"
																						href="table.html#"> <i
																							class="fa fa-search-plus "></i> </a> <a
																						class="btn btn-info" href="table.html#"> <i
																							class="fa fa-edit "></i> </a> <a
																						class="btn btn-danger" href="table.html#"> <i
																							class="fa fa-trash-o "></i> </a>
																					</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</c:forEach>
													</div>
													<!--/col-->
												</div>
											</form>

										</div>
									</div>

								</div>

							</div>

						</div>

					</div>
					<!--/col-->
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
		function selectAll() {
			$(".checkAll").click(function() {
				var subchecks = $(this).parents("table").find(":checkbox");
				for ( var i = 0; i < subchecks.length; i++) {
					if (subchecks[i].checked != this.checked) {
						subchecks[i].click();
					}
				}
			});
		}
	</script>
	<!-- script end -->
</body>
</html>

