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
       <%--  <a href="">首页</a>--%>
          <a href="room-warn/list">机房告警管理</a>
          <a > 告警配置 </a>
        </span>
    <form class="layui-form" lay-filter="form" action="room-warn/edit" method="post">
        <input type="hidden" name="id" value="${formData.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">机房名称</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       readonly
                       autocomplete="off"
                       value="${room.name}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最低电压（v）</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="lowPower"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输入最低电压"
                       lay-reqText="请输入最低电压"
                       value="${formData.lowPower}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最大温度（C）</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="maxTemperature"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输入最大温度"
                       lay-reqText="请输入最大温度"
                       value="${formData.maxTemperature}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最大存储（%）</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="maxDisk"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输入最大存储"
                       lay-reqText="请输入最大存储"
                       value="${formData.maxDisk}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最大访问</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="maxRequest"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输入最大访问"
                       lay-reqText="请输入最大访问"
                       value="${formData.maxRequest}"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">cpu占用（%）</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="maxCpuUse"
                       autocomplete="off"
                       lay-verify="required"
                       lay-verType="tips"
                       placeholder="请输入cpu占用"
                       lay-reqText="请输入cpu占用"
                       value="${formData.maxCpuUse}"
                       type="text">
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
