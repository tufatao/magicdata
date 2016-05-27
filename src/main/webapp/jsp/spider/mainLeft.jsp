<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id='content-left' >
            <div id="sidebar">

                <div class="col-lg-12">

                    <div class="panel panel-default">

                        <div class="panel-body inbox-menu">

                            <div class="btn btn-danger btn-block ">去采集</div>

                            <ul>
                            <li>
                                    <a href="<%=basePath%>spiderAction!toRunProject">项目采集</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>spiderAction!toProcessTask">采集数据</a>
                                </li>
                                
                                <li>
                                    <a href="<%=basePath%>taskAction!taskPage">任务测试<span class="label label-danger">8</span></a>
                                </li>

                            </ul>

                        </div>

                    </div>

                    <div class="panel panel-default">

                        <div class="panel-body contacts">

                            <div class="btn btn-primary btn-block" > 项目管理</div>

                            <ul><li>
                                    <a href="<%=basePath%>conAction!conPage">信息列表<span class="label label-danger">8</span></a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>proAction!toProList">项目列表<span class="label label-danger">2</span></a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>taskAction!taskPage">任务列表<span class="label label-danger">8</span></a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>cusAction!cusPage">客户列表</a>
                                </li>
                                <li><span class="label label-danger"></span> <a href="<%=basePath%>keyAction!keyPage"> Key列表</a></li>
                                <li><span class="label label-success"></span> <a href="<%=basePath%>groupAction!groupPage"> 分组列表</a></li>
                               
                            </ul>

                        </div>

                    </div>

                </div>


            </div>
        </div>

