<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员管理系统</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4/themes/icon.css" />
<style type="text/css">
	em{color:red;font-weight:bold;font-style:normal;}
</style>
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<div>
    <table id="userList" title="会员列表"
	       data-options="singleSelect:false,collapsible:true,pagination:true,
	       url:'/user/list',method:'post',pageSize:5,toolbar:'#seacher',pageList:[2,5,10]">
	    <thead>
	        <tr>
	        	<th data-options="field:'ck',checkbox:true"></th>
	        	<th data-options="field:'id',width:40,align:'center',sortable:true">ID</th>
	            <th data-options="field:'userName',width:70,align:'center'">用户名</th>
	            <th data-options="field:'name',width:70,align:'center'">姓名</th>
	            <th data-options="field:'age',width:40,align:'center',sortable:true">年龄</th>
	            <th data-options="field:'sex',width:40,align:'center',formatter:formatSet">性别</th>
	            <th data-options="field:'note',width:250,align:'center'">备注</th>
	            <th data-options="field:'birthday',width:70,align:'ceneter',formatter:formatBirthday,sortable:true">出生日期</th>
	            <th data-options="field:'created',width:130,align:'center',formatter:formatDate,sortable:true">创建日期</th>
	            <th data-options="field:'updated',width:130,align:'center',formatter:formatDate">更新日期</th>
	        </tr>
	    </thead>
	</table>
	</div>
	<!-- 搜索条begin -->
 	<div id="seacher" style="padding:5px;height:auto">
		<form id="searchForm" action="/solr/query" method="post">
			年龄From:<input class="easyui-numberbox" id="minAge" style="width:50px" data-options="min:1,max:100,precision:0">
			TO: <input class="easyui-numberbox" id="maxAge" style="width:50px" data-options="min:1,max:100,precision:0">
			性别：<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="_sex">
				<option value="" selected="selected">全部</option>
				<option value="1">男</option>
				<option value="2">女</option>
			</select>
			生日From:<input class="easyui-datebox" type="text" style="width:100px" id="minBirthday" />
			生日TO:<input class="easyui-datebox" type="text" style="width:100px" id="maxBirthday"  />
			
			关键字:<input class="easyui-textbox" type="text" id="keyword" style="width:120px" />
			<a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">搜索</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doclear()">重置</a>
		</form>
	</div> 
	<!-- 搜索条end -->
	
	<div id="userAdd" class="easyui-window" title="新增会员" 
		data-options="modal:true,closed:true,iconCls:'icon-save',href:'/page/user-add'" 
		style="width:500px;height:400px;padding:10px;">
	        The window content.
	</div>
	<div id="userEdit" class="easyui-window" title="编辑会员" 
		data-options="modal:true,closed:true,iconCls:'icon-edit',href:'/page/user-edit'" 
		style="width:500px;height:400px;padding:10px;">      
	</div>
	
	<form  id="exportForm" action="/user/export/excel" method="post">
		<input hidden="true"  name='page' />
		<input hidden="true"  name='rows' />
	</form>	
<script type="text/javascript">
	function formatDate(val,row){
		var now = new Date(val);
		return now.format("yyyy-MM-dd hh:mm:ss");
	}
	function formatBirthday(val,row){
		var now = new Date(val);
		return now.format("yyyy-MM-dd");
	}
	function formatSet(val,row){
		if(val == 1){
			return "男";
		}else if(val == 2){
			return "女";
		}else{
			return "未知";
		}
	}
	function getSelectionsIds(){
		var userList = $("#userList");
		var sels = userList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}
	function doclear(){
		$("#searchForm").form("clear");
		$('#userList').datagrid('reload');
	}
	function doSearch(){
		// 这里先获取Datagrid对象，然后调用了load方法，传递请求参数并重新加载数据。
		$('#userList').datagrid('reload',{
			// 准备搜索条件参数：
			minAge: $('#minAge').val(),
			maxAge: $('#maxAge').val(),
			minBirthday: $('#minBirthday').datebox("getValue"),
			maxBirthday: $('#maxBirthday').datebox("getValue"),
			sex: $('#_sex').combobox("getValue"),
			keyword: $('#keyword').val(),
		});
		$("#searchForm").submit();
	}
	var toolbar = [{
	    text:'新增',
	    iconCls:'icon-add',
	    handler:function(){
	    	$('#userAdd').window('open');
	    }
	},{
	    text:'编辑',
	    iconCls:'icon-edit',
	    handler:function(){
	    	var rows = $('#userList').datagrid('getSelections');
	    	if(rows.length != 1){
		    	$.messager.alert('提示','请您选中一行用户!');
		    	return;
	    	} 
	    	rows[0].birthday = new Date(rows[0].birthday).format("yyyy-MM-dd");
    		$('#userEdit').window({
    			onLoad:function(){
			    	$("#editForm").form('load',rows[0]);
    			}
    		}).window('open');
	    	
	    }
	},{
	    text:'删除',
	    iconCls:'icon-cancel',
	    handler:function(){
	    	var ids = getSelectionsIds();
	    	if(ids.length == 0){
	    		$.messager.alert('提示','未选中用户!');
	    		return ;
	    	}
	    	$.messager.confirm('确认','确定删除ID为 '+ids+' 的会员吗？',function(r){
	    	    if (r){
	            	$.post("/user/delete",{'ids':ids}, function(data){
	        			if(data.status == 200){
	        				$.messager.alert('提示','删除会员成功!',undefined,function(){
	        					$("#userList").datagrid("reload");
	        				});
	        			}
	        		});
	    	    }
	    	});
	    }
	},'-',{
	    text:'导出',
	    iconCls:'icon-save',
	    handler:function(){
	    	// 获取当前页的分页信息
	    	var optins = $("#userList").datagrid("getPager").data("pagination").options;
	    	var page = optins.pageNumber;
	    	var rows = optins.pageSize;
	    	
	    	//赋值并提交表单
	    	$("input[name='page']").val(page);
	    	$("input[name='rows']").val(rows);
	    	$("#exportForm").submit();
	    	
	    }
	}];

	// 给分页工具条添加新按钮
	// 获取分页工具条
	var _pager = $('#userList').datagrid().datagrid('getPager');
	// 添加新的按钮
	_pager.pagination({
		buttons:toolbar
	});

</script>
</body>
</html>