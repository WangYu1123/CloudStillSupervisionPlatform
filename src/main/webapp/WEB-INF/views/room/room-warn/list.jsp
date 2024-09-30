<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/16
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 引⼊本⻚⾯的basePath⽂件，设置项⽬的根路径 -->
    <%@include file="../../basepath.jsp"%>
    <title>Title</title>
    <!-- 引⼊layui框架的样式⽂件 -->
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <!-- 引⼊全局的⾃定义css⽂件 -->
    <link rel="stylesheet" type="text/css" href="css/common.css?v=<%=Math.random()%>"/>

</head>
<body>
    <span class="layui-breadcrumb">
          <a href="room-warn/list">机房告警管理</a>
          <a href="">机房告警配置列表</a>
        </span>
    <table id="t" lay-filter="table"></table>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript"  src="js/jquery.min.js"></script>
    <script type="text/javascript"  src="js/qs.min.js"></script>
    <script id="query-form" type="text/html">
        <form class="layui-form" id="form">
            <div class="layui-inline">
                <shiro:hasPermission name="permission:query">
                    <button type="button"
                            lay-event="query"
                            class="layui-btn ">查询</button>
                </shiro:hasPermission>
            </div>
        </form>
    </script>
    <script type="text/html" id="tool">
        <shiro:hasPermission name="permission:update">
            <a href="room-warn/edit/page?roomId={{d.roomId}}" class="layui-btn layui-btn-warm layui-btn-xs" >告警配置</a>
        </shiro:hasPermission>
    </script>
    <script type="text/javascript">
        layui.use('table',function(){
            var table = layui.table
            table.init('table',{
                elem: '#t',
                height: 'full-80',
                autoSort: false ,
                toolbar: '#query-form',
                cols:[[
                    {field:'id',title:'主键',sort: true},
                    {field:'name',title:'机房名称'},
                    {field:'maxTemperature',title:'最大温度'},
                    {field:'lowPower',title:'最低电压'},
                    {field:'maxRequest',title:'最大访问'},
                    {field:'maxDisk',title:'最大存储'},
                    {field:'maxCpuUse',title:'cpu峰值'},
                    {title:'操作',templet: '#tool'}
                ]],
                page:true,
                url:'room-warn/list/page',
                response: {
                    statusCode: 200
                },
                request: {
                    pageName: 'pno', //页码的参数名称，默认：page
                    limitName: 'psize' //每页数据量的参数名，默认：limit
                },
                where:{
                }
            })
            table.on('toolbar(table)',function(obj){
                if(obj.event == 'query'){
                    var queryForm = $('#form').serialize()
                    var queryFormObj = Qs.parse(queryForm)
                    console.log(queryFormObj)
                    table.reload('t',{
                        where:queryFormObj
                    })
                }
            })
            var layer = layui.layer
            table.on('sort(table)',function(obj){
                console.log(obj)
                var queryForm = $('#form').serialize()
                var queryFormObj = Qs.parse(queryForm)
                table.reload('t',{
                    initSort: obj,
                    where:{
                        ...queryFormObj,
                        sortField:obj.field,
                        sortType:obj.type
                    }
                })
            })
        })
    </script>
</body>
</html>
