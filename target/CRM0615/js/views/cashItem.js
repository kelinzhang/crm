$(function () {
    var cashItemDatagrid;
    cashItemDatagrid = $("#cashItem_datagrid");
    //表格管理
    cashItemDatagrid.datagrid({
        url: '/cashRecord/list',
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        rownumbers: true
    });

})