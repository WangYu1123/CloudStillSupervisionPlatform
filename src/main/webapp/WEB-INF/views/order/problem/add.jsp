<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/16
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css?v=
<%=Math.random()%>"/>
</head>
<body>
    <span class="layui-breadcrumb">
     <%-- <a href="">⾸⻚</a>--%>
     <a href="order/problem/list/single">故障上报</a>
     <a > 新增故障信息</a>
     </span>
    <form class="layui-form" lay-filter="form" action="order/problem/add"
          method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">故障设备</label>
            <div class="layui-input-block">
                <select class="layui-select" name="equipmentId" lay-search>
                    <option value="">请选择</option>
                    <c:forEach items="${equipmentList}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">故障描述</label>
            <div class="layui-input-block">
     <textarea class="layui-textarea" name="description"
               placeholder="请输⼊故障描述"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
     <textarea class="layui-textarea" name="remark"
           placeholder="请输⼊故障备注"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">处理⼈</label>
            <div class="layui-input-block">
                <select class="layui-select" name="targetUserId"
                        lay-search
                >
                    <option value="">请选择</option>
                    <c:forEach items="${userList}" var="item">
                        <option value="${item.id}">${item.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">故障时间</label>
            <div class="layui-input-block">
                <input class="layui-input" placeholder="请选择时间" readonly
                       type="text" name="problemTime" id="time" />
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重
                    置</button>
            </div>
        </div>
    </form>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/qs.min.js"></script>
    <script>
        layui.use('laydate', function(){
            var laydate = layui.laydate;
            //执⾏⼀个laydate实例
            laydate.render({
                elem: '#time', //指定元素
                type:'datetime',
                format:'yyyy-MM-dd HH:mm:ss'
            });
        });
    </script>
</body>
</html>
