                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      $(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var stockDatagrid, itemDialog, incomeFirm, proData;
    stockDatagrid = $("#stock_datagrid");
    incomeFirm = $("#incomeFirm");
    itemDialog = $("#item_dialog");
    proData = $("#pro_data");
    /*
     * 初始化数据表格
     */
    stockDatagrid.datagrid({
        fit: true,
        rownumbers: true,
        singleSelect: true,
        title: '订单明细',
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#stock_datagrid_tb',
        onClickCell: onClickCell,
        onAfterEdit: onAfterEdit,
        columns: [[
            {field: 'proSn', title: '商品编码', width: 1, align: 'center'},
            {field: 'proName', title: '商品名称', width: 1, align: 'center'},
            {field: 'kind', title: '商品种类', width: 1, align: 'center'},
            {field: 'brand', title: '商品品牌', width: 1, align: 'center'},
            {
                field: 'supplier', title: '商品供应商', width: 1, align: 'center',
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'name',
                        textField: 'name',
                        url: '/supplier/getAll'
                    }
                }
            },
            {field: 'stockNumber', title: '入库数量', width: 1, align: 'center', editor: {type: 'numberbox'}},
            {field: 'stockPrice', title: '入库价格', width: 1, align: 'center'},
            {field: 'stockAmount', title: '入库总价值', width: 1, align: 'center'}
        ]]
    });

    /*
     * 初始化新增/编辑对话框
     */
    itemDialog.dialog({
        title:'商品入库',
        width: 600,
        height: 400,
        closed: true
    });
    proData.datagrid({
        fit: true,
        url: '/product/list',
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#pro_data_bt',
        columns: [[
            {field: 'id', title: '商品编码', width: 1, align: 'center'},
            {field: 'proName', title: '商品名称', width: 1, align: 'center'},
            {field: 'kind', title: '商品种类', width: 1, align: 'center', formatter: getKind},
            {field: 'brand', title: '商品品牌', width: 1, align: 'center', formatter: getBrand},
            {field: 'costNumber', title: '商品价格', width: 1, align: 'center'}
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
        addItem: function () {
            itemDialog.dialog("open");
        },
        choose: function () {
            var rowData = proData.datagrid("getSelected");
            if (rowData) {
                itemDialog.dialog("close");
                stockDatagrid.datagrid("appendRow", {
                    proSn: rowData.id,
                    proName: rowData.proName,
                    kind: rowData.kind.name,
                    brand: rowData.brand.brandName,
                    stockPrice: rowData.saleNumber
                })

            }
        },
        removeItem:function () {
            var rowdata = stockDatagrid.datagrid("getSelected");
            var index = stockDatagrid.datagrid("getRowIndex",rowdata);
            if(rowdata){
                $.messager.confirm("温馨提示", "您确定需要删除选中的明细吗？", function (yes) {
                    if (yes) {
                        stockDatagrid.datagrid("deleteRow",index);
                    }
                });
            }else{
                $.messager.alert("提示","请选择要删除的明细!","warning");
            }
        },
        save: function () {

            var idVal = $("[name='sn']").val();
            incomeFirm.form("submit", {
                url: "/stockIncome/save",
                onSubmit: function (param) {
                    var rows = stockDatagrid.datagrid("getRows");
                    if (rows) {
                        for (var i = 0; i < rows.length; i++) {
                            param["items[" + i + "].proSn"] = rows[i].proSn;
                            param["items[" + i + "].proName"] = rows[i].proName;
                            param["items[" + i + "].kind"] = rows[i].kind;
                            param["items[" + i + "].brand"] = rows[i].brand;
                            param["items[" + i + "].supplier"] = rows[i].supplier;
                            param["items[" + i + "].incomeNumber"] = rows[i].stockNumber;
                            param["items[" + i + "].incomePrice"] = rows[i].stockPrice;
                            param["items[" + i + "].incomeAmount"] = rows[i].stockAmount;
                        }
                    }
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            /*roleDialog.dialog("close");
                            roleDatagrid.datagrid("reload");*/
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        }
    };
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
    var editIndex = undefined;

    function endEditing() {
        if (editIndex == undefined) {
            return true
        }
        if (stockDatagrid.datagrid('validateRow', editIndex)) {
            stockDatagrid.datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing()) {
            stockDatagrid.datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }

    function onAfterEdit(index, row, changes) {
       /* console.log(index);
        console.log(row);
        console.log(changes);*/
        if (changes.stockNumber) {
                stockDatagrid.datagrid('updateRow', {
                    index: index,
                    row: {
                        proSn: row.proSn,
                        proName: row.proName,
                        kind: row.kind,
                        brand: row.brand,
                        stockNumber: changes.stockNumber,
                        stockPrice: row.stockPrice,
                        stockAmount: (changes.stockNumber * row.stockPrice)
                    }
                });
        }


    }
});
function getKind(value, row, index) {
    if (value) {
        return value.name;
    }
    return value;
}
function getBrand(value, row, index) {
    if (value) {
        return value.brandName;
    }
    return value;
}
