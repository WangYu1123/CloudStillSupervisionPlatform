<%--
  Created by IntelliJ IDEA.
  User: zhangyunpeng
  Date: 2021/5/28
  Time: 11:43 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="../../basepath.jsp"%>

    <title>用户列表</title>
    <link href="layui/css/layui.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/common.css?v=<%=Math.random()%>">

</head>
<body>
    <span class="layui-breadcrumb">
    <%-- <a href="">⾸⻚</a>--%>
     <a href="user/dept/list">部⻔员⼯管理</a>
     <a >部⻔⽤户列表</a>
    </span>
    <table id="table" lay-filter="test" lay-size="lg"></table>

    <br>
    <%--${page}--%>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/qs.min.js"></script>
    <script type="text/html" id="btns">
        <shiro:hasPermission name="permission:update">
            <a lay-event="edit" href="user/dept/page?id={{d.id}}" class="layui-btn layui-btn-warm layui-btn-xs">关联部⻔</a>
        </shiro:hasPermission>
    </script>
    <script type="text/html" id="toolbarDemo">
        <form class="layui-form " id="queryForm" lay-filter="queryFrom">
            <div class="layui-inline">
                <div class="layui-form-label">
                    ⽤户名
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="username" class="layui-input " value="
    {{d.where.username==undefined?'':d.where.username}}" placeholder="请输⼊⽤户名">
                </div>
            </div>
            <div class="layui-inline">
                <shiro:hasPermission name="permission:query">
                    <button type="button" class="layui-btn layui-btn-sm" lay-event="query">查询</button>
                </shiro:hasPermission>
            </div>
        </form>
    </script>
    <script type="text/javascript">
        var layer
        layui.use('layer',function(){
            layer = layui.layer
        })
        layui.use('table', function(){
            var table = layui.table;
            console.log(table)
            //第⼀个实例
            let t = table.render({
                elem: '#table',
                height: 'full-60',
                url: 'user/list/page', //数据接⼝
                page: true, //开启分⻚
                limit:10,
                limits:[10,20,30,50],
                autoSort:false,
                request:{
                    pageName:"pno",
                    limitName:"psize"
                },
                where:{
                    username:''
                },
                response:{
                    statusCode:200
                },
                toolbar:'#toolbarDemo',
                // defaultToolbar: ['filter', 'print', 'exports', {
                // title: '提示' //标题
                // ,layEvent: 'del' //事件名，⽤于 toolbar 事件中使⽤
                // ,icon: 'layui-icon-tips' //图标类名
                // }],
                cols: [[ //表头
                    {field: 'id', title: '主键', width:80},
                    {field: 'username', title: '⽤户名'},
                    {field: 'roleName', title: '⻆⾊名称'},
                    {field: 'deptName', title: '部⻔名称'},
                    {field: 'nickname', title: '昵称'},
                    {field: '',title:'操作',toolbar:'#btns',fixed:'right'}
                ]],
            });
            table.on('tool(test)',function(obj){
                console.log(obj)
                var id = obj.data.id
            })
            table.on('toolbar(test)',function(obj){
                if(obj.event == 'query') {
                    var queryForm = $("#queryForm").serialize()
                    var formData = Qs.parse(queryForm)
                    table.reload('table', {
                        initSort: obj,
                        where: formData
                    })
                }
            })
            table.on('sort(test)',function(res){
                table.reload('table',{
                    initSort: res //记录初始排序，如果不设的话，将⽆法标记表头的排序状态。
                    ,where: { //请求参数（注意：这⾥⾯的参数可任意定义，并⾮下⾯固定的格式）
                        sortField: res.field //排序字段
                        ,sortType: res.type //排序⽅式
                    }
                })
            })
        });
    </script>
</body>
</html>
