<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/16
  Time: 16:15
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
     <a href="order/problem/list/single">故障上报</a>
     <a href="">已上报列表</a>
     </span>
    <table id="t" lay-filter="table"></table>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript"  src="js/jquery.min.js"></script>
    <script type="text/javascript"  src="js/qs.min.js"></script>
    <script id="query-form" type="text/html">
        <form class="layui-form" id="form">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: auto">⼯单状态</label>
                <div class="layui-input-inline">
                    <select class="layui-select" name="orderStatusId">
                        <option value="">请选择</option>
                        <c:forEach items="${orderStatusList}" var="item">
                            <option value="${item.id}"
                                    {{d.where.orderStatusId==${item.id}?'selected':''}} >${item.statusName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-inline">
                <shiro:hasPermission name="permission:query">
                    <button type="button"
                            lay-event="query"
                            class="layui-btn ">查询</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="permission:insert">
                    <a href="order/problem/add/page" class="layui-btn ">新增</a>
                </shiro:hasPermission>
            </div>
        </form>
    </script>
    <script type="text/html" id="tool">
        <shiro:hasPermission name="permission:update">
            <a href="order/problem/edit/page?id={{d.id}}" class="layui-btn layui-btn-warm layui-btn-xs" >修改</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="permission:delete">
            <button type="button" lay-event="delete"
                    class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
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
                    {field:'description',title:'故障描述'},
                    {field:'equipmentName',title:'故障设备'},
                    {field:'publicUsername',title:'发布⼈'},
                    {field:'targetUsername',title:'处理⼈'},
                    {field:'handleTime',title:'处理时间'},
                    {field:'orderStatusName',title:'处理状态'},
                    {field:'insertTime',title:'⼯单时间'},
                    {title:'操作',templet: '#tool'}
                ]],
                // data:[
                // {id:'1',sexName:'男'},
                // {id:'2',sexName:'⼥'}
                // ],
                page:true,
                url:'order/problem/list/page',
                response: {
                    statusCode: 200
                },
                request: {
                    pageName: 'pno', //⻚码的参数名称，默认：page
                    limitName: 'psize' //每⻚数据量的参数名，默认：limit
                },
                where:{
                    orderStatusId:''
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
            table.on('tool(table)',function(obj){
                //当点击了删除按钮时触发的事件
                if(obj.event == 'delete'){
                    //获取本⾏数据的id
                    var id = obj.data.id
                    console.log(id)
                    layer.confirm('正在删除当前数据是否继续?',{
                        icon:3,
                        title:'提示'
                    },function(index){
                        location.href = 'order/problem/delete?id='+id
                        layer.close(index)
                    })
                }
            })
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
