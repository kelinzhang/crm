$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var roleDatagrid, roleDialog, roleForm, allPermissions, selfPermissions, roleMenuTree, roleMenuTitle;
    roleDatagrid = $("#role_datagrid");
    roleDialog = $("#role_dialog");
    roleForm = $("#role_form");
    allPermissions = $("#allPermissions");
    selfPermissions = $("#selfPermissions");
    roleMenuTree = $("#role_menuTree");
    roleMenuTitle = $("#role_menuTitle")
    /*
     *初始化菜单树
     */
    roleMenuTree.tree({
        url: '/systemMenu/roleMenu',
        checkbox: true
    });
    /*
     * 初始化数据表格
     */
    roleDatagrid.datagrid({
        url: "/role/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#role_datagrid_tb',
        onClickRow: function (rowIndex, rowData) {
            roleMenuTitle.panel("setTitle", "角色[" + rowData.name + "]的菜单");
            //回显角色菜单
            var allMenus = roleMenuTree.tree("getRoots");
            $.post("/role/getRoleMenuId", {roleId: rowData.id}, function (data) {
                var menuIds = data;
                checkMenu(allMenus, menuIds);
            }, "json");
        }
    });
    /*
     * 初始化新增/编辑对话框
     */
    roleDialog.dialog({
        width: 600,
        height: 500,
        closed: true,
        buttons: '#role_dialog_bt'
    });
    /*
     * 对页面按钮进行统一监听
     */
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });
    /*
     * 权限编辑
     */
    var allData;
    allPermissions.datagrid({
        width: 250,
        height: 350,
        url: '/permission/queryForRoleForm',
        title: '所有权限',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: 'name', title: '权限名称', align: 'center', width: 1}
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            selfPermissions.datagrid("appendRow", rowData);
            allPermissions.datagrid("deleteRow", rowIndex);
        },
        onLoadSuccess: function (data) {
            allData = $.extend(true, {}, data);
        }

    });
    selfPermissions.datagrid({
        width: 250,
        height: 350,
        title: '自身权限',
        rownumbers: true,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: 'name', title: '权限名称', align: 'center', width: 1}
        ]],
        onDblClickRow: function (rowIndex, rowData) {
            allPermissions.datagrid("appendRow", rowData);
            selfPermissions.datagrid("deleteRow", rowIndex);
        },
        onLoadSuccess: function (data) {
            var ids = $.map(data.rows, function (item) {
                return item.id;
            });
            if (ids) {
                for (var i = (allData.rows.length - 1); i >= 0; i--) {
                    if ($.inArray(allData.rows[i].id, ids) >= 0) {
                        allPermissions.datagrid("deleteRow", i);
                    }
                }
            }
        }
    });
    /*
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        addMenu: function () {
            $.messager.confirm("提示", "你确定要给角色[]添加菜单吗?", function (yes) {
                if (yes) {
                    var row = roleDatagrid.datagrid("getSelected");
                    if (row) {
                        var roleId = row.id;
                        var treeNodes = roleMenuTree.tree("getChecked");
                        var treeNodes1 = roleMenuTree.tree("getChecked", 'indeterminate');
                        var ids = [];
                        $.each(treeNodes, function (index, value) {
                            ids.push(value.id);
                        });
                        $.each(treeNodes1, function (index, value) {
                            ids.push(value.id);
                        });
                        $.post("/role/addMenu", {ids: ids, roleId: roleId}, function (data) {
                            if (data.success) {
                                $.messager.alert("提示", data.msg, "Info");
                            } else {
                                $.messager.alert("提示", data.msg, "error");
                            }
                        }, "json");
                    } else {
                        $.messager.alert("提示", "请选择需要添加菜单的角色!", "warning");
                    }
                }
            })

        },
        add: function () {
            allPermissions.datagrid("loadData", allData);
            selfPermissions.datagrid("loadData", {rows: []});
            roleForm.form("clear");
            roleDialog.dialog("setTitle", "新增");
            roleDialog.dialog("open");
        },
        edit: function () {
            var rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                allPermissions.datagrid("loadData", allData);
                selfPermissions.datagrid("loadData", {rows: []});
                selfPermissions.datagrid("options").url = "/permission/queryByRole?roleId=" + rowData.id;
                selfPermissions.datagrid("load");
                roleForm.form("clear");
                roleDialog.dialog("setTitle", "编辑");
                roleDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                roleForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = roleDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/role/delete?roleId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    roleDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }, "json")
                    }
                });
            } else {
                $.messager.alert("温馨提示", "请选择需要删除的数据!", "warining");
            }
        },
        reload: function () {
            roleDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/role/update"
            } else {
                url = "/role/save";
            }
            roleForm.form("submit", {
                url: url,
                onSubmit: function (param) {
                    var rows = selfPermissions.datagrid("getRows");
                    if (rows) {
                        for (var i = 0; i < rows.length; i++) {
                            param["permissions[" + i + "].id"] = rows[i].id;
                        }
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            roleDialog.dialog("close");
                            roleDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            roleDialog.dialog("close");
        }
    }

    function checkMenu(allMenus, menuIds) {
        for(var i = 0;i<allMenus.length;i++){
            if($.inArray(allMenus[i].id,menuIds)>=0){
                roleMenuTree.tree("check",allMenus[i].target);
            }else{
                roleMenuTree.tree("uncheck",allMenus[i].target);
            }
            var children = roleMenuTree.tree("getChildren",allMenus[i].target);
            if(children){
                arguments.callee(children,menuIds);
            }
        }
    }
});
