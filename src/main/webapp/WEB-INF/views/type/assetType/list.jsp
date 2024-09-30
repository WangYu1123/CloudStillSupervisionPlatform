<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/10
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <script id="query-form" type="text/html">
        <!-- 在这个代码块中可以使⽤{{}}来获取⼀些layui的数据，d代表整个数据对象，d.where代表条件查询
    的参数-->
<%--        {{JSON.stringify(d.where)}}--%>
        <form class="layui-form" id="form">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: auto">类型</label>
                <div class="layui-input-inline">
                    <label>
                        <input class="layui-input"  value="{{d.where.assetType}}" type="text" placeholder="请输⼊资产类型" name="assetType" />
                    </label>
                </div>
            </div>
            <div class="layui-inline">
                <shiro:hasPermission name="permission:query">
                    <button type="button"
                            lay-event="query"
                            class="layui-btn ">查询</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="permission:insert">
                    <a href="assets/add/page?pid=${pid}" class="layui-btn ">新增</a>
                </shiro:hasPermission>
            </div>
        </form>
    </script>
    <span class="layui-breadcrumb">
             <a href="assets/list">资产类型维护</a>
             <a href="">资产类型列表</a>
        </span>
    <script type="text/html" id="tool">
        <shiro:hasPermission name="permission:update">
            <a href="assets/edit/page?id={{d.id}}" class="layui-btn layui-btn-warm layui-btn-xs" >修改</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="permission:delete">
            <button type="button" lay-event="delete"
                    class="layui-btn layui-btn-danger layui-btn-xs">删除</button>
        </shiro:hasPermission>
    </script>
    <table id="t" lay-filter="table"></table>


    <!-- 引⼊layui的js⽂件-->
    <script type="text/javascript" src="layui/layui.js"></script>
    <!-- 引⼊jquery的js⽂件-->
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <!-- 引⼊qs参数处理框架 -->
    <script type="text/javascript" src="js/qs.min.js"></script>

    <script type="text/javascript">
        //利⽤layui的api初始化table组件
        layui.use('table',function(){
            //获取表格对象
            var table = layui.table
            //加载静态列表的⽅式，第⼀个参数table代表table上设置的lay-filter的值
            //第⼆个参数按照官⽅⽂档传⼊
            // table.render({ //其它参数在此省略
            //     defaultToolbar: ['print', 'filter', 'exports', {
            //         title: '提示' //标题
            //         ,layEvent: 'LAYTABLE_TIPS' //事件名，⽤于 toolbar 事件中使⽤
            //         ,icon: 'layui-icon-tips' //图标类名
            //     }]
            // });
            table.init('table',{
                //表格的选择器，按照css选择器的⽅式传⼊，这⾥使⽤了table的id
                elem: '#t',
                //表格的⾼度，可以设置数字代表固定值，使⽤full-数字代表动态设置⾼度
                height: 'full-80',
                //代表默认不做静态数据的排序，这⾥是开启后台服务排序的意思
                autoSort: false ,
                //指定自己的模板
                toolbar: '#query-form',
                //表格的表头设置，需要使⽤⼆维数组
                //field代表每⼀列的属性值，与java中的实体对象Sex⼀⼀对应
                //title代表表头展示的内容，sort代表开启排序
                cols:[[
                    {field:'id',title:'主键',sort: true},
                    {field:'assetTypeName',title:'资产类型'},
                    //定义⼯具栏列，只需要定义表头名称，templet代表指定使⽤的模版的选择器关联id为tool的script标签
                    {title:'操作',templet: '#tool'}
                ]],
                //初始数据的属性使⽤json数组，属性和field属性⼀⼀对应
                // data:[
                //     {id:'1',sexName:'男'},
                //     {id:'2',sexName:'⼥'}
                // ],
                //开启分⻚模式，这⾥我们只需要将开关打开即可，因为后续需要使⽤后台实现分⻚
                page:true,
                //然后在这⾥添加url属性，地址就填写controller中的完整路径，http部分已经在basePath中设置好了
                url:'assets/list/page',
                response: {
                    //将返回的默认成功状态码定义为200
                    statusCode: 200
                },
                //配置请求的分⻚参数
                request: {
                    pageName: 'pno', //⻚码的参数名称，默认：page
                    limitName: 'psize' //每⻚数据量的参数名，默认：limit
                },
                //绑定初始的sexName为空值
                where:{
                    assetType:''
                }
            })
            table.on('toolbar(table)',function(obj){
                if(obj.event == 'query'){
                    //获取表单对象并且将其序列化成key=value&key=value
                    var queryForm = $('#form').serialize()
                    //将表单序列化的结果转换成json对象
                    var queryFormObj = Qs.parse(queryForm)
                    console.log(queryFormObj)
                    //调⽤表格重载函数会出发表格重新查询，这⾥需要
                    //在where中传⼊我们的条件查询对象，这个属性就会传到后台
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
                        //提交到删除接⼝的路径并且传⼊id
                        location.href = 'assets/delete?id='+id
                        layer.close(index)
                    })
                }
            })
            //对表格进⾏排序监听table代表lay-filter配置的值
            //当表格某个字段排序⽅式发⽣变化的时候就会触发当前的函数
            //在obj中包含如下内容{field: "id（排序的字段名）", type: "desc/asc"}
            table.on('sort(table)',function(obj){
                console.log(obj)
                //获取当前表单数据
                var queryForm = $('#form').serialize()
                var queryFormObj = Qs.parse(queryForm)
                table.reload('t',{
                    initSort: obj,
                    where:{
                        //这⾥是js的es6写法可以快捷合并对象，主要是为了防⽌切换排序条件的时候当前查询条件消失
                        ...queryFormObj,
                        //传⼊排序依据和排序⽅式
                        sortField:obj.field,
                        sortType:obj.type
                    }
                })
            })
        })
    </script>
</body>
</html>
