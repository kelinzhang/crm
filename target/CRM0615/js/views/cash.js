$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var cashDatagrid, cashDialog, cashForm, proData, itemDialog;
    cashDatagrid = $("#cash_datagrid");
    cashDialog = $("#cash_dialog");
    cashForm = $("#cash_form");
    proData = $("#pro_data");
    itemDialog = $("#item_dialog");
    /*
     * 初始化数据表格
     */
    var oldAmount = 0.0;
    cashDatagrid.datagrid({
        title:'消费明细',
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#cash_datagrid_tb',
        onClickCell: function (index, field, value) {
            if ('delete' == field) {
                cashDatagrid.datagrid("deleteRow",index);
                /*var rowData = cashDatagrid.datagrid("getSelected");
                if (rowData) {
                    $.get("/cash/delete?cashId=" + rowData.id, function (data) {
                        if (data.success) {
                            $.messager.alert("提示", data.msg, 'info', function () {
                                cashDatagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert("提示", data.msg, 'error');
                        }
                    }, "json")
                }*/
            } else {
                if (endEditing()) {
                    cashDatagrid.datagrid('selectRow', index)
                        .datagrid('editCell', {index: index, field: field});
                    editIndex = index;
                }
            }
        },
        onLoadSuccess: function (data) {
            $("#price").val(oldSale);
            $("#discountPrice").val(newSale);
        },
        onBeforeEdit: function (rowIndex, rowData) {
            if (rowData.amount) {
                oldAmount = parseInt(rowData.amount);
            } else {
                oldAmount = 0.0;
            }
        },
        onAfterEdit: onAfterEdit,
        columns: [[
            {field: 'proSn', title: '商品编码', align: 'center', width: 1},
            {field: 'proName', title: '商品名称', align: 'center', width: 1},
            {field: 'kind', title: '商品种类', width: 1, align: 'center'},
            {field: 'brand', title: '商品品牌', width: 1, align: 'center'},
            {field: 'supplier', title: '商品供应商', width: 1, align: 'center'},
            {field: 'costPrice', title: '原价', align: 'center', width: 1, formatter: getSale},
            {field: 'salePrice', title: '会员价', align: 'center', width: 1, formatter: getVip},
            {field: 'amount', title: '数量', align: 'center', width: 1, editor: {type: 'numberbox'}},
            {field: 'totalCostPrice', title: '原价(总)', align: 'center', width: 1, formatter: getTotalSale},
            {field: 'totalSalePrice', title: '会员价(总)', align: 'center', width: 1, formatter: getTotalVip},
            {field: 'delete', title: '操作', align: 'center', width: 1, formatter: deleteFormat}
        ]]
    });
    /*
     * 初始化新增/编辑对话框
     */
    itemDialog.dialog({
        width: 800,
        height: 400,
        closed: true
    });
    proData.datagrid({
        fit: true,
        url: '/productStock/list',
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#pro_data_bt',
        columns: [[
            {field: 'proSn', title: '商品编码', width: 1, align: 'center'},
            {field: 'proName', title: '商品名称', width: 1, align: 'center'},
            {field: 'kind', title: '商品种类', width: 1, align: 'center'},
            {field: 'brand', title: '商品品牌', width: 1, align: 'center'},
            {field: 'supplier', title: '商品供应商', width: 1, align: 'center'},
            {field: 'stockNumber', title: '库存数量', width: 1, align: 'center'},
            {field: 'stockPrice', title: '库存价格', width: 1, align: 'center'},
            {field: 'stockAmount', title: '库存总价值', width: 1, align: 'center'}
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
                cashDatagrid.datagrid("appendRow", {
                    proSn: rowData.proSn,
                    proName: rowData.proName,
                    kind: rowData.kind,
                    brand: rowData.brand,
                    supplier: rowData.supplier,
                    stockNumber: rowData.stockNumber,
                    stockPrice: rowData.stockPrice,
                    stockAmount: rowData.stockAmount,
                    costPrice: rowData.stockPrice
                })

            }
        },
        add: function () {
            cashForm.form("clear");
            cashDialog.dialog("setTitle", "新增");
            cashDialog.dialog("open");
        },
        edit: function () {
            var rowData = cashDatagrid.datagrid("getSelected");
            if (rowData) {
                cashForm.form("clear");
                cashDialog.dialog("setTitle", "编辑");
                cashDialog.dialog("open");
                if (rowData.dept)
                    rowData["dept.id"] = rowData.dept.id;
                cashForm.form("load", rowData);
            } else {
                $.messager.alert("温馨提示", "请选择需要编辑的数据!", "warining");
            }
        },
        del: function () {
            var rowData = cashDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/cash/delete?cashId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    cashDatagrid.datagrid("reload");
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
            cashDatagrid.datagrid("reload");
        },
        save: function () {
            var url;
            var idVal = $("[name='id']").val();
            if (idVal) {
                url = "/cash/update"
            } else {
                url = "/cash/save";
            }
            cashForm.form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            cashDialog.dialog("close");
                            cashDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        cancel: function () {
            cashDialog.dialog("close");
        },
        cash:function () {
            parent.window.cash();
        }
    }
    /*输入会员号回显对应的一系列数据*/
    $("#number").blur(function () {
        var nowNumber = $("#number").val();
        $.post("/member/selectOne?number=" + nowNumber, function (data) {
            if (data) {
                disCount = data.level.levelProducteDisCount;
                servDiscount = data.level.levelServiceDisCount;
                var rowId = $("#number").val();
                $("[name='name']").val(data.name);
                $("[name='balance']").val(data.balance);
                $("[name='levelName']").val(data.level.levelName);
                $("[name='levelProducteDisCount']").val(data.level.levelProducteDisCount);
                $("[name='levelServiceDisCount']").val(data.level.levelServiceDisCount);
                cashDatagrid.datagrid("options").url = '/cash/listNumber?number=' + rowId;
                cashDatagrid.datagrid("load");
            } else {
                $("[name='name']").val("");
                $("[name='balance']").val("");
                $("[name='levelName']").val("");
                $("[name='levelProducteDisCount']").val("");
                $("[name='levelServiceDisCount']").val("");
            }

        });
    })
    /*会员结账*/
    $("#payMoney").click(function () {
        $("#cash_form").form("submit", {
            //会员结账时把数据保存到cash表中
            url: '/cashRecord/save',
            onSubmit: function (param) {
                var total = $("#discountPrice").val();
                var balance = $("[name='balance']").val();
                if ((balance - total) < 0) {
                    $.messager.alert("提示", "余额不足,请充值", 'warning');
                    return false;
                }
                //选中所有的行
                var rows = cashDatagrid.datagrid("getRows");
                var disCount = $("input[name='levelProducteDisCount']").val();
                var number = $("input[name='number']").val();
                if (rows) {
                    param["number"] = number;
                    for (var i = 0; i < rows.length; i++) {
                        var kind;
                        if (!rows[i].kind) {
                            kind = undefined;
                        } else {
                            kind = rows[i].kind;
                        }
                        param["items[" + i + "].proSn"] = rows[i].proSn;
                        param["items[" + i + "].proName"] = rows[i].proName;
                        param["items[" + i + "].kind"] = kind;
                        param["items[" + i + "].brand"] = rows[i].brand;
                        param["items[" + i + "].supplier"] = rows[i].supplier;
                        param["items[" + i + "].salePrice"] = rows[i].costPrice;
                        param["items[" + i + "].saleNumber"] = rows[i].amount;
                        param["items[" + i + "].saleAmount"] = rows[i].totalSalePrice;
                        param["items[" + i + "].discount"] = disCount;
                    }
                }
            },
            //成功时调用次方法
            success: function (data) {
                //把字符串数据转换中json数据
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("提示", "结账成功", "info", function () {
                        cashDatagrid.datagrid("reload");
                    });
                } else {
                    $.messager.alert("提示", data.msg, "error");
                }
            }

        });

    })
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
        if (cashDatagrid.datagrid('validateRow', editIndex)) {
            cashDatagrid.datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    //点击编辑时修改数量
    function onAfterEdit(index, row, changes) {
        var totalSale = (row.costPrice * 1.5 * changes.amount);
        var vipSale = totalSale * disCount / 100;
        if (changes.amount) {
            var oldTotalSale = (parseFloat(row.costPrice) * 1.5 * parseFloat(oldAmount));
            var oldVipSale = oldTotalSale * disCount / 100;
            cashDatagrid.datagrid('updateRow', {
                index: index,
                row: {
                    proSn: row.proSn,
                    proName: row.proName,
                    kind: row.kind,
                    brand: row.brand,
                    supplier: row.supplier,
                    costPrice: row.costPrice,
                    salePrice: row.salePrice,
                    amount: changes.amount,
                    totalCostPrice: totalSale,
                    totalSalePrice: vipSale,
                    delete: row.delete
                }
            });
            /*应收金额和实收金额*/
            var data = cashDatagrid.datagrid("getRows");
            if (data) {
                for (row in data) {
                    if (data[row].totalCostPrice && data[row].totalSalePrice) {
                        oldSale += (data[row].totalCostPrice - oldTotalSale);
                        newSale += (data[row].totalSalePrice - oldVipSale);
                    }
                }
                $("#price").val(oldSale);
                $("#discountPrice").val(newSale);
            }
        }


    }
});
/**点击跳转**/
$(function () {
    $(".btn_cash").click(function () {
        parent.window.cashRecord();
    });
});
/*删除按钮*/
function deleteFormat(value, record, index) {

    return "<img src='/images/ud/delete.png'>"
}
//原价
function getSale(value, record, index) {
    var kind = record.kind;
    if (kind) {
        salePrice = value * 1.5;
    } else {
        salePrice = value;
    }
    return salePrice;
}
//会员价
function getVip(value, record, index) {
    var kind = record.kind;
    var result;
    if (kind) {
        result = salePrice * disCount / 100;
    } else {
        result = salePrice * servDiscount / 100;
    }
    return result;
}
var disCount = 1.0;
var salePrice = 1.0;
var servDiscount = 1.0;
var oldSale = 0.0;
var newSale = 0.0;
//原价总价
function getTotalSale(value, record, index) {
    var amount = record.amount;
    if (amount) {
        var kind = record.kind;
        var result = salePrice * amount;
        if (!kind) {
            oldSale = oldSale + result;
        }
        return result;
    }
    return amount;
}
//会员总价

function getTotalVip(value, record, index) {
    var amount = record.amount;
    if (amount) {
        var kind = record.kind;
        var result;
        if (kind) {
            result = salePrice * amount * disCount / 100;
        } else {
            result = salePrice * amount * servDiscount / 100;
            newSale = newSale + result;
        }
        return result;
    }
    return amount;
}