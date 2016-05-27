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
    <title>执行任务</title>
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
                        <li><i class="fa fa-indent"></i>执行采集任务</li>
                    </ol>
                </div>
            </div>

            <div class="row">
				
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-tags red"></i><strong>执行任务</strong></h2>
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
										
								<form >
										<div class="row">

									  		<div class="form-group col-sm-4">
										    	<label for="ccmonth-w1">项目</label>
									    		<select class="form-control" id="ccmonth-w1">
												  	<option>1</option>
												  	<option>2</option>
												  	<option>3</option>											
												</select>
										  	</div>
										  	<div class="form-group col-sm-4">
										    	<label for="ccmonth-w1">分组</label>
									    		<select class="form-control" id="ccmonth-w1">
												  	<option>招投标</option>
												  	<option>电商资讯</option>											
												</select>
										  	</div>

											<div class="form-group col-sm-4">
										    	<label for="postal-code-w1">任务名</label>
										    	<input type="text" class="form-control" id="postal-code-w1" placeholder="Postal Code">
										  	</div>

										</div><!--/row-->
										<div class="form-group form-actions">
				                    <button type="submit" class="btn btn-sm btn-success"> Submit</button>
				                </div>
										
							<div class="row">		
				<div class="col-lg-12">
				
                                <c:forEach items="${proList}" var="pro" varStatus="sta">
				
					<div class="panel panel-default">
						<div class="panel-heading">
							<h2><i class="fa fa-table red"></i><span class="break"></span><strong>${pro.name}</strong></h2>
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
                                <th><input class="checkAll" type="checkbox" onClick="selectAll()"/>全选</th>
                                    <th>序号</th>
                                    <th>任务名</th>
                                    <th>网站</th>
                                    <th>编码</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pro.bidTasks}" var="task" varStatus="status">
                                
                                <tr>
                                <td><input type="checkbox" class="tid" name="tid" value="${task.tid}"></td>
                                    <td>【${status.count + pageSize * (currentPage - 1)}】</td>
                                    <td>${task.name}</td>
                                    <td>${task.webname}</td>
                                    <td>${task.charset}</td>
                                    <td><span class="label label-warning">${task.str1}</span></td>
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
						</div>
					</div>
                                </c:forEach>
				</div><!--/col-->
								</div>
								</form>
			
			</div>
								    <div class="tab-pane" id="tab12">
								 		<div class="row">

											<div class="col-sm-12">

												<div class="form-group has-feedback">
											    	<label for="name-w1">Name</label>
											    	<input type="text" class="form-control" id="name-w1" placeholder="Enter your name">
													<span class="fa fa-asterisk form-control-feedback"></span>
											  	</div>

											</div>

										</div><!--/row-->

										<div class="row">

											<div class="col-sm-12">

												<div class="form-group has-feedback">
											    	<label for="ccnumber-w1">Credit Card Number</label>
											    	<input type="text" class="form-control" id="ccnumber-w1" placeholder="0000 0000 0000 0000">
													<span class="fa fa-asterisk form-control-feedback"></span>
											  	</div>

											</div>

										</div><!--/row-->

										<div class="row">

									  		<div class="form-group col-sm-4">
									    		<label for="ccmonth-w1">Month</label>
									    		<select class="form-control" id="ccmonth-w1">
												  	<option>1</option>
												  	<option>2</option>
												  	<option>3</option>
												  	<option>4</option>
												  	<option>5</option>
													<option>6</option>
												  	<option>7</option>
												  	<option>8</option>
												  	<option>9</option>
												  	<option>10</option>
													<option>11</option>
													<option>12</option>
													<option>13</option>
													<option>14</option>
													<option>15</option>
													<option>16</option>
													<option>17</option>
													<option>18</option>
													<option>19</option>
													<option>20</option>
													<option>21</option>
													<option>22</option>
													<option>23</option>
													<option>24</option>
													<option>25</option>
													<option>26</option>
													<option>27</option>
													<option>28</option>
													<option>29</option>
													<option>30</option>
													<option>31</option>													
												</select>
									  		</div>

											<div class="form-group col-sm-4">
									    		<label for="ccyear-w1">Year</label>
									    		<select class="form-control" id="ccyear-w1">
												  	<option>2014</option>
												  	<option>2015</option>
												  	<option>2016</option>
												  	<option>2017</option>
												  	<option>2018</option>
													<option>2019</option>
												  	<option>2020</option>
												  	<option>2021</option>
												  	<option>2022</option>
												  	<option>2023</option>
													<option>2024</option>
													<option>2025</option>
												</select>
									  		</div>

											<div class="col-sm-4">

												<div class="form-group has-feedback">
										    		<label for="cvv-w1">CVV/CVC</label>
										    		<input type="text" class="form-control" id="cvv-w1" placeholder="123">
													<span class="fa fa-asterisk form-control-feedback"></span>
										  		</div>

											</div>

										</div><!--/row-->
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

