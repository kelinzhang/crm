$(function () {
    //抽取所有需要用得元素.
    var petServiceDatagrid, petServiceDialog, petServiceForm;
    petServiceDatagrid = $("#petService_datagrid");
    petServiceDialog = $("#petService_dialog");
    addServiceDialog = $("#addService_dialog");
    addServiceForm = $("#addService_form");
    petServiceForm = $("#petService_form");
    petMenuTree = $("#pet_menuTree");
    BuildingId = $("#BuildingId2"), $("#BuildingId3");
    //初始化菜单树
    petMenuTree.tree({
        url: '/petMenu/queryTree',
        lines: true,
        onLoadSuccess: function () {
            //初始化状态是折叠所有节点
            petMenuTree.tree('collapseAll');
        }
    });
    petMenuTree.tree({
        onContextMenu: function (e, node) {
            e.preventDefault();
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY,
                onClick: function (item) {
                    var father = petMenuTree.tree('getParent', node.target);
                    $('#addService_form').form('clear');
                    if (item.text == 'New') {
                        if(!father){
                            $('#BuildingId').combobox("select", node.id);
                        }else{
                            $('#BuildingId').combobox("select", father.id);
                        }
                        addServiceDialog.dialog('open');
                    }
                    if (item.text == 'Edit') {
                        if(father){
                            $.post('petMenu/selectByPrimaryKey?id='+node.id,function (data) {
                                $('#BuildingId').combobox("select", data.id);
                                $('#addService_form').form('load',data);
                            });
                            addServiceDialog.dialog('open');
                        }
                    }
                    if (item.text == 'Delete') {
                        if(father){
                            $.get("/petMenu/delete?id=" + node.id, function (data) {
                                console.log(data);
                                if (data.success) {
                                    $.messager.alert("提示", data.msg, 'info', function () {
                                        petMenuTree.tree('reload');
                                    });
                                } else {
                                    $.messager.alert("提示", data.msg, 'error');
                                }
                            }, "json")
                        }
                    }
                }
            });
        }
    });
    //初始化数据表格
    petServiceDatagrid.datagrid({
        url: "/petService/list",
        fit: true,
        fitColumns: true,
        rownumbers: true, //显示行号
        checkOnSelect: false,
        striped: true, //是否斑马条效果
        pagination: true,//显示分页工具栏
        toolbar: '#petService_datagrid_tb',
        columns: [[
            {field: 'cb', checkbox: true},
            {field: 'petName', title: '宠物名', align: 'center', width: 80},
            {field: 'personName', title: '主人名', align: 'center', width: 80},
            {field: 'tel', title: '联系方式', align: 'center', width: 100},
            {field: 'serviceProject', title: '服务项目', align: 'center', width: 80},
            {field: 'currentState', title: '当前状态', align: 'center', width: 55, formatter: currentStateFormat},
            {field: 'state', title: '是否付款', align: 'center', width: 80, formatter: stateFormat},
            {field: 'startDateTime', title: '实际开始时间', align: 'center', width: 120},
            {field: 'endDateTime', title: '实际结束时间', align: 'center', width: 120},
            {field: 'times', title: '寄养时间/剩余次数', align: 'center', width: 100},
            {field: 'servicePrice', title: '服务价格', align: 'center', width: 80},
            {field: 'beforePrice', title: '预付款项', align: 'center', width: 80},
            {field: 'afterPrice', title: '尾款', align: 'center', width: 80},
            {field: 'log', title: '日志', align: 'center', width: 50, formatter: logFormat}
        ]]
    });
    petServiceDatagrid.datagrid({
        onCheck: function (index, rowData) {
            petServiceDatagrid.datagrid('selectRow', index);
            if (rowData.state == 1) {
                $('#btn_pay').linkbutton('disable');
            } else {
                $('#btn_pay').linkbutton('enable');
            }
            if (rowData.currentState == 1) {
                $('#btn_over').linkbutton('disable');
            } else {
                $('#btn_over').linkbutton('enable');
            }
        },
        onUncheck: function (index) {
            petServiceDatagrid.datagrid('unselectRow', index);
            $('#btn_pay').linkbutton('enable');
            $('#btn_over').linkbutton('enable');
        },
        onClickRow: function (rowIndex) {
            $(this).datagrid('unselectRow', rowIndex);
        }
    });
    //初始化对话框
    petServiceDialog.dialog({
        title: '宠物登记',
        width: 550,
        height: 450,
        modal: true,
        closed: true,
        buttons: '#petService_dialog_bt'
    });
    addServiceDialog.dialog({
        title: '添加新项目',
        width: 300,
        height: 300,
        modal: true,
        closed: true,
        buttons: '#petService_saveProject_dialog_bt'
    });
    $('#BuildingId3').combobox({
        onSelect: function (data) {
            $("#priceInput").val(data.servicePrice);
        }
    });
    $('#BuildingId2').combobox({
        onSelect: function () {
            var data = $('#BuildingId2').combobox('getValue');
            $('#BuildingId3').combobox({
                valueField: 'id',
                textField: 'text',
                url: '/petMenu/queryChildrenTree?parentId=' + data
            });
        }
    });
    $('#kindParent').combobox({
        onSelect: function () {
            var data = $('#kindParent').combobox('getValue');
            $('#kindChildren').combobox({
                valueField: 'id',
                textField: 'text',
                url: 'petBreed/selectForChildren?parentId=' + data
            })
        }
    });
    $('#queryBycombobox').combobox({
        onChange:function (data) {
            var keyCurState = $("[name=keyCurState]").val();
            console.log(keyCurState);
            $('#petService_datagrid').datagrid("load",{
                keyCurState:keyCurState
            });
        }
    });
    //对页面按钮进行统一监听
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    //所有的操作封装到cmdObj对象中,方便管理
    var cmdObj = {
        add: function () {
            petServiceForm.form("clear");
            $("[name='beforePrice']").val(0);
            $('#BuildingId2').combobox({
                valueField: 'id',
                textField: 'text',
                url: '/petMenu/queryTree',
                onLoadSuccess: function (data) {
                    $('#BuildingId2').combobox("select", data[0].id);
                    $('#BuildingId3').combobox({
                        valueField: 'id',
                        textField: 'text',
                        url: '/petMenu/queryChildrenTree?parentId=' + data[0].id,
                        onLoadSuccess: function (data) {
                            $('#BuildingId3').combobox("select", data[0].id);
                            $("#priceInput").val(data[0].servicePrice);
                        }
                    });
                }
            });
            $('#kindParent').combobox({
                valueField: 'id',
                textField: 'text',
                url: 'petBreed/selectForFather',
                onLoadSuccess: function (data) {
                    $('#kindParent').combobox("select", data[0].id);
                    $('#kindChildren').combobox({
                        valueField: 'id',
                        textField: 'text',
                        url: 'petBreed/selectForChildren?parentId=' + data[0].id,
                        onLoadSuccess: function (data) {
                            $('#kindChildren').combobox("select", data[0].id);
                        }
                    });
                }
            });
            petServiceDialog.dialog("open");
        },
        addService: function () {
            addServiceForm.form("clear");
            var data = $('#BuildingId').combobox('getData');
            if (data.length > 0) {
                $('#BuildingId').combobox('select', data[0].id);
            }
            addServiceDialog.dialog("open");
        },
        reload: function () {
            petServiceDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            url = "/petService/save";
            petServiceForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            petServiceDialog.dialog("close");
                            petServiceDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        pay: function () {
            var rowData = petServiceDatagrid.datagrid("getSelected");
            if (rowData) {
                if (rowData.state == 0) {
                    $.post('/cash/savePetService?rowData=' + rowData.id);
                    parent.window.jumpCashJsp();
                    petServiceDatagrid.datagrid('reload');
                }
            } else {
                $.messager.show({
                    title: '提示',
                    msg: '请进行正确操作,亲',
                    timeout: 1500
                });
            }
        },
        queryByVipNumber: function () {
            var value = $('#VipNumberInput').val();
            $.post('/member/queryBack?tel=' + value, function (data) {
                var name = data.pets[0];
                $('#personNameInput').val(data.name);
                $('#selfTel').val(data.number);
                $('#petNameInput').val(name.petName);
                if (name.sex == 0) {
                    $("#unknow").prop('checked', true);
                }
                if (name.sex == 1) {
                    $("#man").prop('checked', true);
                }
                if (name.sex == 2) {
                    $("#woman").prop('checked', true);
                }
            })
        },
        close: function () {
            addServiceDialog.dialog('close');
            petServiceDialog.dialog("close");
        },
        saveProject: function () {
            var url;
            var id = $("#addServiceId").val();
            if (id) {
                url = '/petMenu/update';
            } else {
                url = '/petMenu/save';
            }
            addServiceForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            addServiceDialog.dialog("close");
                            petMenuTree.tree('reload');
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        highquery: function () {
            var keyname = $("[name='keyname']").val();
            var keytel = $("[name='keytel']").val();
            petServiceDatagrid.datagrid("load", {
                keyname: keyname,
                keytel: keytel
            });
        },
        returnservice: function () {
            petServiceDatagrid.datagrid('load');
            $("[name=keyname]").prop('value', "");
            $("[name=keytel]").prop('value', "");
            var keyname = $("[name='keyname']").val();
            var keytel = $("[name='keytel']").val();
            petServiceDatagrid.datagrid("load", {
                keyname: keyname,
                keytel: keytel,
                keyCurState: ''
            });
        },
        endService: function () {
            var rowData = petServiceDatagrid.datagrid("getSelected");
            if (rowData) {
                if(rowData.currentState==0){
                    $.get('petService/update?id='+rowData.id);
                    petServiceDatagrid.datagrid("reload");
                }
            } else {
                $.messager.show({
                    title: '提示',
                    msg: '请进行正确操作,亲',
                    timeout: 1500
                });
            }
        }
    }
});
function stateFormat(value, record, index) {
    if (value == 0) {
        return "<span style='color:red;'>未付款</span>";
    } else {
        return "<span style='color:green;'>已付款</span>";
    }
}
function currentStateFormat(value, record, index) {
    if (value == 0) {
        return "<span style='color:green;'>进行中</span>";
    } else {
        return "<span style='color:red;'>已结束</span>";
    }
}
function logFormat(valule, record, index) {
    return "<a href='javascript:showLog(" + record.id + ")'><img src='/images/pet/log2.png'/></a>"
}
function showLog(record) {
    alert("模块维护中......");
}