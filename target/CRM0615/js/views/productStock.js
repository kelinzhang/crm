$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var productStockDatagrid, productStockDialog, productStockForm;
    productStockDatagrid = $("#productStock_datagrid");
    productStockDialog = $("#productStock_dialog");
    productStockForm = $("#productStock_form");
    /*
     * 初始化数据表格
     */
    productStockDatagrid.datagrid({
        url: "/productStock/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#productStock_datagrid_tb'
    });
    /*
     * 初始化新增/编辑对话框
     */
    productStockDialog.dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#productStock_dialog_bt'
    });
    $("#kind").combobox({
        valueField: 'name',
        textField: 'name',
        url: '/productKind/getAll',
        onChange: function (newValue, oldValue) {
            dealChoose();
        }
    });
    $("#brand").combobox({
        valueField: 'brandName',
        textField: 'brandName',
        url: '/productBrand/getAll',
        onChange: function (newValue, oldValue) {
            dealChoose();
        }
    });
    $("#supplier").combobox({
        valueField: 'name',
        textField: 'name',
        url: '/supplier/getAll',
        onChange: function (newValue, oldValue) {
            dealChoose();
        }
    });
    /*
     * 对页面按钮进行统一监听
     */
    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd](this);
        }
    });


    /*
     * 所有的操作封装到cmdObj对象中,方便管理
     */
    var cmdObj = {
        query: function () {
            var proSn = $("[name='proSn']").val();
            var proName = $("[name='proName']").val();
            productStockDatagrid.datagrid("load", {
                proSn: proSn,
                proName: proName
            });
        },
        /*incomeInput: function () {
            window.open("/stockIncome/stockInput");
        },

        income: function () {
            window.open("/stockIncome");
        },
        outcome: function () {
            window.open("/cashRecord");
        },*/
        open:function (data) {
            parent.window.stock(data);
        },
        reload: function () {
            productStockDatagrid.datagrid("reload");
        }
    };

    function dealChoose() {
        var kind = $("#kind").combobox("getValue");
        var brand = $("#brand").combobox("getValue");
        var supplier = $("#supplier").combobox("getValue");
        if(!kind){
            kind = undefined;
        }
        if(!brand){
            brand = undefined;
        }
        if(!supplier){
            supplier = undefined;
        }
        productStockDatagrid.datagrid("load", {
            kind: kind,
            brand: brand,
            supplier: supplier
        });
    }
});
