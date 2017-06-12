$(function () {
    var employeeData, btn_edit_quit, employeeDialog, employeeForm;
    employeeData = $("#employeeData");
    btn_edit_quit = $("#btn_edit,#btn_quit");
    employeeDialog = $("#myDialog");
    employeeForm = $("#myForm");
    //表格管理
    employeeData.datagrid({
        url: '/employee/list',
        title: '员工管理',
        fit: true,
        fitColumns: true,
        toolbar: '#employeeData_tb',
        pagination: true,
        singleSelect: true,
        rownumbers: true,
        columns: [[
            {field: 'username', title: '账号', align: 'center', width: 1},
            {field: 'realname', title: '真实姓名', align: 'center', width: 1},
            {field: 'tel', title: '联系方式', align: 'center', width: 1},
            {field: 'email', title: '邮箱', align: 'center', width: 1},
            {field: 'dept', title: '所属部门', align: 'center', width: 1, formatter: deptFormat},
            {field: 'state', title: '状态', align: 'center', width: 1, formatter: stateFormat},
            {field: 'admin', title: '是否管理员', align: 'center', width: 1, formatter: adminFormat}
        ]],
        onClickRow: function (rowIndex, rowData) {
            if (rowData.state == -1) {
                //员工已经离职
                btn_edit_quit.linkbutton("disable");
            } else {
                btn_edit_quit.linkbutton("enable");
            }
        }
    });
    //对话框管理
    employeeDialog.dialog({
        width: 300,
        height: 300,
        buttons: '#myDialog_bt',
        closed: true
    });
    //监听所有的按钮,执行对应的操作
    $("a[data-cmd]").on("click",function () {
        var cmd = $(this).data("cmd");
        if(cmd){
            cmdObj[cmd]();
        }
    });
    var cmdObj = {
        cancel: function () {
            employeeDialog.dialog("close");
        },
        save: function () {
            var url;
            var id = $("[name='id']").val();
            if (id) {
                url = '/employee/update';
            } else {
                url = '/employee/save';
            }
            //获取表单,使用异步提交
            employeeForm.form("submit", {
                url: url,
                onSubmit:function (param) {
                    //获取已选择的角色的ID
                    var ids = $("#emp_role").combobox("getValues");
                    for(var i = 0;i < ids.length;i++){
                        param["roles["+i+"].id"]=ids[i];
                    }
                    return true;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("提示", data.msg, 'info', function () {
                            //关闭对话框
                            employeeDialog.dialog("close");
                            //刷新数据
                            employeeData.datagrid("reload");
                        });
                    }else{
                        $.messager.alert("提示", data.msg, 'error');
                    }
                }
            })
        },

        add: function () {
            //清空表单
            employeeForm.form("clear");
            //设置对话框标题
            employeeDialog.dialog("setTitle", "新增");
            //打开对话框
            employeeDialog.dialog("open");
        },

        edit: function () {
            var rowData = employeeData.datagrid("getSelected");
            if (rowData) {
                //清空表单数据
                employeeForm.form("clear");
                //设置对话框标题
                employeeDialog.dialog("setTitle", "编辑");
                //打开对话框
                employeeDialog.dialog("open");
                //特殊属性的处理
                if (rowData.dept) {
                    rowData["dept.id"] = rowData.dept.id;
                }
                //回显数据
                employeeForm.form("load", rowData);
                //回显角色信息
                $.post('/role/queryRoleByEmp?empId='+rowData.id,function (data) {
                    $("#emp_role").combobox("setValues",data);
                },"json")
            } else {
                $.messager.alert("提示", "请选择需要编辑的员工!", 'warning');
            }
        },

        del: function () {
            var rowData = employeeData.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("提示", "你确定要离职选中的员工吗?", function (mes) {
                    if (mes) {
                        $.get("/employee/quit?id=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("提示", data.msg, 'info', function () {
                                    employeeData.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("提示", data.msg, 'error');
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("提示", "请选择需要离职的员工!", 'warning');
            }
        },

        flush: function () {
            employeeData.datagrid("reload");
        },

        query: function () {
            var keyword = $("[name='keyword']").val();
            employeeData.datagrid("load", {
                keyword: keyword
            });
        }
    }
});
function deptFormat(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}
function stateFormat(value, record, index) {
    if (value == 0) {
        return "<span style='color: green; '>正常</span>";
    } else {
        return "<span style='color: red; '>离职</span>";
    }
}
function adminFormat(value, record, index) {
    if (value) {
        return "<span style='color: green; '>是</span>";
    } else {
        return "<span style='color: red; '>否</span>";
    }
}
