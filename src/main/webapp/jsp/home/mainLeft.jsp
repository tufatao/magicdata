<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div id='content-left' >
            <div id="sidebar">

                <div class="col-lg-12">

                    <div class="panel panel-default">

                        <div class="panel-body inbox-menu">

                            <div class="btn btn-danger btn-block ">项目管理</div>

                            <ul>
                            <li>
                                    <a href="<%=basePath%>homeAction!toMyPro">我的项目</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>cusAction!cusPage">客户列表</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>homeAction!toDataOfPro">项目数据</a>
                                </li>
                                <li>
                                    <a href="<%=basePath%>homeAction!toCountShow">统计报表</a>
                                </li>
                            </ul>

                        </div>

                    </div>

                    <div class="panel panel-default">

                        <div class="panel-body contacts">

                            <div class="btn btn-primary btn-block" > 我</div>

                            <ul>
                                <li><span class="label label-danger"></span> <a href="<%=basePath%>homeAction!personal"> 个人信息</a></li>
       
                            </ul>

                        </div>

                    </div>

                </div>


            </div>
        </div>

