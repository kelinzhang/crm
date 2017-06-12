$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var stockIncomeDatagrid, stockIncomeDialog, stockIncomeForm, itemDialog, itemData;
    stockIncomeDatagrid = $("#stockIncome_datagrid");
    stockIncomeDialog = $("#stockIncome_dialog");
    stockIncomeForm = $("#stockIncome_form");
    itemDialog = $("#item_dialog");
    itemData = $("#item_data");

    /*
     * 初始化数据表格
     */
    var incomeId = 0;
    stockIncomeDatagrid.datagrid({
        url: "/stockIncome/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#stockIncome_datagrid_tb',
        onClickCell: function (rowIndex, field, value) {
            if ('itemSize' == field) {
                stockIncomeDatagrid.datagrid("selectRow", rowIndex);
                var rowData = stockIncomeDatagrid.datagrid("getSelected");
                if (rowData) {
                    incomeId = rowData.id;
                    itemDialog.dialog("open");
                }
            }
        },
        columns: [[
            {field: 'sn', title: '入库编号', width: 1, align: 'center'},
            {field: 'totalNumber', title: '入库总数', width: 1, align: 'center'},
            {field: 'totalAmount', title: '入库总价值', width: 1, align: 'center'},
            {field: 'inputUser', title: '操作人', width: 1, align: 'center', formatter: getUser},
            {field: 'vdate', title: '入库时间', width: 1, align: 'center'},
            {field: 'itemSize', title: '入库明细', width: 1, align: 'center', formatter: viewItem}
        ]]
    });
    /**
     * 明细查看
     */
    itemDialog.dialog({
        title:'入库明细',
        width: 650,
        height: 300,
        closed: true,
        onOpen:function () {
            itemData.datagrid("options").url = '/stockIncome/listItem?id=' + incomeId;
            itemData.datagrid("load");
        }
    });
    itemData.datagrid({
        fit: true,
        //url: '/stockIncome/listItem?id=' + incomeId,
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
            {field: 'incomeNumber', title: '入库数量', width: 1, align: 'center'},
            {field: 'incomePrice', title: '入库价格', width: 1, align: 'center'},
            {field: 'incomeAmount', title: '入库总价值', width: 1, align: 'center'}
        ]]
    });
    /*
     * 初始化新增/编辑对话框
     */
    stockIncomeDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#stockIncome_dialog_bt'
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
        /*add: function () {
            window.open("/stockIncome/stockInput");
            /!*stockIncomeForm.form("clear");
             stockIncomeDialog.dialog("setTitle","新增");
             stockIncomeDialog.dialog("open");*!/
        },
        edit: function () {
            var rowData = stockIncomeDatagrid.datagrid("getSelected");
            if (rowData) {
                stockIncomeForm.form("clear");
                stockIncomeDialog.dialog("setTitle", "编辑");
                stockIncomeDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                stockIncomeForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = stockIncomeDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/stockIncome/delete?stockIncomeId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    stockIncomeDatagrid.datagrid("reload");
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
        },*/
        reload: function () {
            stockIncomeDatagrid.datagrid("reload");
        },
        /*save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/stockIncome/update"
            } else {
                url = "/stockIncome/save";
            }
            stockIncomeForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            stockIncomeDialog.dialog("close");
                            stockIncomeDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },*/
        cancel: function () {
            stockIncomeDialog.dialog("close");
        }
    }
});
function getUser(value, row, index) {
    if (value) {
        return value.username;
    }
    return value;
}
function viewItem(value, row, index) {
    return "<img src='/images/search.png' name='查看明细'>";
}
