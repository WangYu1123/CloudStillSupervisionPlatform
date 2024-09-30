<%--
  Created by IntelliJ IDEA.
  User: 王禹
  Date: 2024/9/11
  Time: 18:16
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
     <a href="user/dept/list">部⻔员⼯管理</a>
        <a > 关联部⻔ </a>
    </span>
    <form class="layui-form" lay-filter="form" action="user/bind/dept" method="post">
        <input type="hidden" name="id" value="${formData.id}"/>
        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input class="layui-input"
                       required
                       name="username"
                       value="${formData.username}"
                       readonly
                       autocomplete="off"
                       type="text">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">部⻔</label>
            <div class="layui-input-block" id="select-container" lay-verify="checkRequired">
                <!-- 这⾥使⽤了c:if标签来判断如果下拉菜单有数据代表已经有默认部⻔存在，就读取后台整理好的select标签 -->
                <c:if test="${select != ''}">
                    ${select}
                </c:if>
                <!-- 如果是新添加部⻔的情况就是⽤⽹⻚中⾃带的标签 -->
                <c:if test="${select == ''}">
                    <select id="dept-base" lay-filter="dept-base" class="layui-select"></select>
                </c:if>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">⽴即提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/qs.min.js"></script>
    <script type="text/javascript">
        //获取下来菜单
        //el代表要加载数据的select标签
        //pid代表传⼊的pid
        //callback是加载完数据之后的回调函数
        function getDeptListByPid(el,pid,callback){
            //发送ajax请求调⽤后台定义的根据pid查询部⻔列表
            $.ajax({
                url:'dept/list/pid',
                data:{
                    pid:pid
                },
                method:'post',
                //请求成功触发的函数
                success:function(res){
                    //当后台的状态码为200的时候处理逻辑
                    if(res.code == 200){
                        //如果后台返回的list⻓度⼤于0代表有数据
                        if(res.data.length>0){
                            $(el).append('<option value="" >请选择</option>')
                            //遍历列表将数据转换成下拉菜单的option
                            res.data.forEach(function(item){
                                $(el).append('<option value="'+item.id+'" data-isLeaf="'+item.isLeaf+'">'+item.name+'</option>')
                            })
                            //由于layui内部将option结构重构了所以需要调⽤layui.form.render来重新渲染下拉菜单
                            layui.form.render('select')
                        }else{
                            //如果返回的数据是空值我们就将刚才创建的select菜单删除保证不会阻塞流程进⾏
                            $(el).remove()
                        }
                    }
                    //逻辑完成之后调⽤回调函数
                    if(callback){
                        callback(res)
                    }
                }
            })
        }
        //⾸次加载第⼀个下拉菜单
        // getDeptListByPid('#dept-base',-1,function(res){})
        ${select == ""?"getDeptListByPid('#dept-base',-1,function(res){})":""}
        //定义动态创建的select的id
        var baseId = 'dept-base'
        //定义计数器
        var count = 1;
        layui.use('form',function(){
            //加载form对象
            var form = layui.form;
            //当下拉菜单切换选项时触发该函数
            form.on('select(dept-base)',function(data){
                //data.elem代表下拉菜单的dom对象，获取下拉菜单在当前标签结构中的下标
                var baseIndex = $(data.elem).index()
                //点击当前下拉时先删除他后⾯动态⽣成的，由于layui的下拉组件会产⽣两个平级的标签对象
                //所以这⾥需要隔两个之后删除
                $('#select-container').children().each(function(index,item){
                    if(index>=baseIndex+2){
                        $(item).remove()
                    }
                })
                //遍历当前select的⼦元素
                $(data.elem).children().each(function(index,item){
                    //判断如果选中的值和其中⼦元素的值相等时
                    if(data.value == item.value){
                        // console.dir(item.dataset.isleaf)
                        //如果当前选中的节点不是最后⼀层
                        if(item.dataset.isleaf == 0){
                            //⽣成新的id
                            var newId = (baseId+count)
                            //动态创建select标签
                            $(data.elem).after('<select id="'+newId+'" class="layui-select select-after" lay-filter="dept-base"></select>')
                            count++
                            //调⽤ajax渲染select标签
                            getDeptListByPid('#'+newId,item.value,function(res){

                                if(res.data.length == 0){
                                    console.log($(data.elem))
                                    $('select').removeAttr('name')
                                    $(data.elem).attr('name','deptId')
                                }
                            })
                        }else{
                            //如果选中的是最后⼀层节点就将这层节点的select设置name属性⽤来提交
                            console.log($(data.elem))
                            $('select').removeAttr('name')
                            $(data.elem).attr('name','deptId')
                        }
                    }
                })
            })
            //表单的⾃定义校验
            form.verify({
                checkRequired:function(){
                    if($('[name="deptId"]').length == 0||$('[name="deptId"]').val()==''){
                        return "请⾄少选择⼀个部⻔"
                    }else{
                        return false
                    }
                }
            })
        })
    </script>
</body>
</html>
