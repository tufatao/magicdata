<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id='content-left' >
            <div id="sidebar">

                <div class="col-lg-12">

                    <div class="panel panel-default">

                        <div class="panel-body inbox-menu">

                            <div class="btn btn-danger btn-block ">数据管理</div>

                            <ul>
                            <li>
                                    <a href="<%=basePath%>bidAction!startTask">导入数据</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>bidAction!toKeyCheck">Key审核</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>bidAction!toCusCheck">Cus审核</a>
                                </li>

                            </ul>

                        </div>

                    </div>

                    <div class="panel panel-default">

                        <div class="panel-body contacts">

                            <div class="btn btn-primary btn-block" > 推送管理</div>

                            <ul>
                                <li>
                                    <a href="<%=basePath%>bidAction!toPushInfo">平台推送</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>bidAction!toMailInfo">邮件推送</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>bidAction!toCountShow">统计报表</a>
                                </li>
                            </ul>

                        </div>

                    </div>

                </div>


            </div>
        </div>

