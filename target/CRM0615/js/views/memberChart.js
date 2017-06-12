$(function(){
    $("#memberChart_datagrid").datagrid({
        url: "/member/memberChart",
        fit: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        toolbar:'#memberChart_datagrid_tb',
        columns: [[
            {field: 'number', title: '会员卡号', align: 'center', width: 1},
            {field: 'consume', title: '消费金额(元)', align: 'center', width: 1},
        ]]
    });
    //图表分析
    $(".btn_chart").click(function () {
        window.showModalDialog("/memberChart/viewByLine","","dialogHeight:600px;dialogWidth:600px");
    });
    $(".btn_query").click(function () {
        var startTime = $("#start").datebox("getValue");
        var endTime = $("#end").datebox("getValue");
        $("#memberChart_datagrid").datagrid("load", {
            startTime: startTime,
            endTime: endTime
        })
    })
})