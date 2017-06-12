$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var cashRecordDatagrid, cashRecordDialog, cashRecordForm, itemDialong, itemData, queryCardRecord;
    cashRecordDatagrid = $("#cashRecord_datagrid");
    cashRecordDialog = $("#cashRecord_dialog");
    cashRecordForm = $("#cashRecord_form");
    itemDialong = $("#item_dialog");
    itemData = $("#item_data");
    queryCardRecord = $("#query_cardRecord");
    /*
     * 初始化数据表格
     */
    var cashRecordId = 0;
    cashRecordDatagrid.datagrid({
        url: "/cashRecord/list",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        onDblClickCell: function (rowIndex, field, value) {
            if ('state' == field) {
                cashRecordDatagrid.datagrid("selectRow", rowIndex);
                var rowData = cashRecordDatagrid.datagrid("getSelected");
                if (rowData) {
                    cashRecordId = rowData.id;
                    itemDialong.dialog('open');
                }
            }

        },
        columns: [[
            {field: 'proSn', title: '收银编号', align: 'center', width: 1},
            {field: 'number', title: '会员卡号', align: 'center', width: 1},
            {field: 'inputTime', title: '订单时间', align: 'center', width: 1},
            {field: 'shopAmount', title: '商品总数', align: 'center', width: 1},
            {field: 'consume', title: '消费金额(元)', align: 'center', width: 1},
            {field: 'inputUser', title: '操作人', align: 'center', width: 1, formatter: userFormat},
            {field: 'state', title: '查看', align: 'center', width: 1, formatter: stateFormat}
        ]]


    });

    /*收银记录明细*/
    itemDialong.dialog({
        title: '收银明细',
        width: 700,
        height: 300,
        closed: true,
        onOpen: function () {
            itemData.datagrid("options").url = '/cashRecord/listItem?id=' + cashRecordId;
            itemData.datagrid("load");
        }
    })
    itemData.datagrid({

        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        columns: [[
            {field: 'proSn', title: '商品编码', width: 1, align: 'center'},
            {field: 'proName', title: '商品名称', width: 1, align: 'center'},
            {field: 'kind', title: '商品种类', width: 1, align: 'center'},
            {field: 'brand', title: '商品品牌', width: 1, align: 'center'},
            {field: 'supplier', title: '商品供应商', width: 1, align: 'center'},
            {field: 'discount', title: '折扣', width: 1, align: 'center'},
            {field: 'salePrice', title: '售价', width: 1, align: 'center'},
            {field: 'saleNumber', title: '销售数量', width: 1, align: 'center'},
            {field: 'saleAmount', title: '销售总额', width: 1, align: 'center'},
            {field: 'profix', title: '销售利润', width: 1, align: 'center'}

        ]]
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
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        add: function () {
            cashRecordForm.form("clear");
            cashRecordDialog.dialog("setTitle", "新增");
            cashRecordDialog.dialog("open");
        },
        edit: function () {
            var rowData = cashRecordDatagrid.datagrid("getSelected");
            if (rowData) {
                cashRecordForm.form("clear");
                cashRecordDialog.dialog("setTitle", "编辑");
                cashRecordDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                cashRecordForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = cashRecordDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/cashRecord/delete?cashRecordId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    cashRecordDatagrid.datagrid("reload");
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
            cashRecordDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/cashRecord/update"
            } else {
                url = "/cashRecord/save";
            }
            cashRecordForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            cashRecordDialog.dialog("close");
                            cashRecordDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            cashRecordDialog.dialog("close");
        },
        query: function () {
            var number = $("[name='number']").val();
            var startTime = $("#start").datebox("getValue");
            var endTime = $("#end").datebox("getValue");
            cashRecordDatagrid.datagrid("load", {
                number: number,
                startTime: startTime,
                endTime: endTime
            })
        }
    }
});
function userFormat(value, record, index) {
    if (value) {
        return value.username;
    }
    return value;
}
function stateFormat(value, record, index) {
    if (value == 1) {
        return "<img src='/images/ud/search.png'/>";
    }
    return value;
}
