<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/12
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
     <a href="room/list">机房管理</a>
     <a > 关联责任⼈ </a>
     </span>
    <!-- 表单部分 -->
    <form class="layui-form" lay-filter="form" action="room/edit" method="post">
        <input type="hidden" name="id" value="${formData.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">机房名称</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="name"
                       readonly
                       autocomplete="off"
                       value="${formData.name}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">责任⼈</label>
            <div class="layui-input-block">
                <select class="layui-select" name="userId"
                        required
                        lay-verify="required"
                        lay-verType="tips"
                        lay-reqText="请选择⽤户"
                        lay-search
                        autocomplete="off">
                    <option value="">请选择</option>
                    <c:forEach items="${userList}" var="item">
                        <option value="${item.id}" ${formData.userId == item.id?'selected':''}>${item.username}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">⼿机号码</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="phone"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输⼊⼿机号码"
                       lay-reqText="请输⼊⼿机号码"
                       value="${formData.phone}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/qs.min.js"></script>
</body>
</html>
