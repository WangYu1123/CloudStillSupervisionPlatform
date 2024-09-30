<%--
  Created by IntelliJ IDEA.
  User: zhangyunpeng
  Date: 2021/8/6
  Time: 8:51 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
     <%--  <a href="">首页</a>--%>
        <a href="room/equipment/list?roomId=${formData.id}">机房设备管理</a>
        <a > 添加机房设备 </a>
      </span>
  <form class="layui-form" lay-filter="form" action="room/equipment/add" method="post">
    <input type="hidden" name="roomId" value="${formData.id}"/>
    <div class="layui-form-item">
      <label class="layui-form-label">机房名称</label>
      <div class="layui-input-block">
        <input class="layui-input"
               required
        <%-- name="name"--%>
               readonly
               autocomplete="off"
               value="${formData.name}"
               type="text">
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">设备列表</label>
      <div class="layui-input-block">
        <select class="layui-select" name="id"
                required
                lay-verify="required"
                lay-verType="tips"
                lay-reqText="请选择用户"
                lay-search
                autocomplete="off">
          <option value="">请选择</option>
          <c:forEach items="${equipmentList}" var="item">
            <option value="${item.id}" >${item.name}</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交
        </button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      </div>
    </div>
  </form>
  <script type="text/javascript" src="layui/layui.js"></script>
  <script type="text/javascript"  src="js/jquery.min.js"></script>
  <script type="text/javascript"  src="js/qs.min.js"></script>
</body>
</html>