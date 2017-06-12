$(function () {
    /*
     * 抽取所有需要用得元素.
     */
    var editPicForm,uploadPicDialog, excelForm, excelDialog, memberDatagrid, memberDialog, memberForm, petDialog, petForm, depositDialog, depositForm, rechargeDatagrid, rechargeDialog;
    memberDatagrid = $("#member_datagrid");
    memberDialog = $("#member_dialog");
    memberForm = $("#member_form");
    petDialog = $("#pet_dialog");
    petForm = $("#pet_form");
    depositDialog = $("#deposit_dialog");
    depositForm = $("#deposit_form");
    rechargeDatagrid = $("#recharge_datagrid");
    rechargeDialog = $("#recharge_dialog");
    excelDialog=$("#excel_dialog");
    excelForm=$("#excel_form");
    uploadPicDialog=$("#uploadPic_dialog");
    editPicForm=$("#editpic_form");
    /*
     * 初始化数据表格
     */
    memberDatagrid.datagrid({
        url: "/pet/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#member_datagrid_tb',
        onRowContextMenu: function (e, rowIndex, rowData) { //右键时触发事件
            //三个参数：e里面的内容很多，真心不明白，rowIndex就是当前点击时所在行的索引，rowData当前行的数据
            e.preventDefault(); //阻止浏览器捕获右键事件
            $(this).datagrid("clearSelections"); //取消所有选中项
            $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
            $('#petCRUD').menu('show', {
                //显示右键菜单
                left: e.pageX,//在鼠标点击处显示菜单
                top: e.pageY
            });
            e.preventDefault(); //阻止浏览器自带的右键菜单弹出
        }
    });
    /*
     * 初始化充值记录数据表格
     */
    rechargeDatagrid.datagrid({
        url: "/recharge/list",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#recharge_datagrid_tb'
    });
    /*
     * 初始化新增会员对话框
     */
    memberDialog.dialog({
        width: 1010,
        height: 400,
        closed: true,
        buttons: '#member_dialog_bt'
    });
    /*
     * 初始化新增宠物对话框
     */
    petDialog.dialog({
        width: 600,
        height: 300,
        closed: true,
        buttons: '#pet_dialog_bt'
    });
    /*
     * 初始化充值对话框
     */
    depositDialog.dialog({
        width: 800,
        height: 400,
        closed: true,
        buttons: '#deposit_dialog_bt'
    });
    /*
     * 初始化充值记录对话框
     */
    rechargeDialog.dialog({
        width: 1000,
        height: 400,
        closed: true,
        buttons: '#recharge_dialog_bt'
    });
    /*
     * 初始化导入会员对话框
     */
    excelDialog.dialog({
        width: 1000,
        height: 400,
        closed: true,
        buttons: '#excel_dialog_bt'
    });
    /*
     * 初始化修改宠物图片对话框
     */
    uploadPicDialog.dialog({
        width: 500,
        height: 500,
        closed: true,
        buttons: '#pic_dialog_bt'
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
        //添加会员
        addMember: function () {
            memberForm.form("clear");
            memberDialog.dialog("setTitle", "添加会员");
            $("#saveOrUpdate").text("添加会员");
            $("#saveOrUpdate").data("cmd","saveMember")
            $("[name='balance']").prop("name", "credit").prop("readonly", false);
            $("#money").text("首冲金额:");
            memberDialog.dialog("open");
            var data = $("#level1").combobox("getData");
            if (data.length > 0) {
                $("#level1").combobox("select", data[0].id);
            }
            var data = $("#breed").combobox("getData");
            if (data.length > 0) {
                $("#breed").combobox("select", data[0].id);
            }
            var timestamp = Date.parse(new Date());
            var rechargeId = "BY" + timestamp;
            rechargeId = rechargeId.substring(0, 12);
            $("[name='sn']").val(rechargeId);
        },
        //编辑会员
        editMember: function () {
            var rowData = memberDatagrid.datagrid("getSelected");
            console.log(rowData);
            if (rowData) {
                $("#saveOrUpdate").text("修改会员");
                memberDialog.dialog("setTitle", "修改会员");
                $("#saveOrUpdate").data("cmd","updateMember");
                memberDialog.dialog("open");
                $("#breed").combobox("setValue", rowData.breed.id);
                /*  var data = $("#level1").combobox("getData");
                 if (data.length > 0) {
                 $("#level1").combobox("select", data[0].id);
                 }
                 var data = $("#breed").combobox("getData");
                 if (data.length > 0) {
                 $("#breed").combobox("select", data[0].id);
                 }*/
                $.post('/pet/queryLevelByPet?petId=' + rowData.id, function (data) {
                    $("#level1").combobox("setValue", data.id);
                    $("[name='serviceDisCount']").text(data.levelServiceDisCount);
                    $("[name='productDisCount']").text(data.levelProducteDisCount);

                }, "json")
                $("[name='number']").val(rowData.member.number);
                $.post('/member/selectById?memberId=' + rowData.member.id, function (data) {
                    $("[name='name']").val(data.name);
                    $("[name='credit']").prop("name", "balance").prop("readonly", true);
                    $("[name='balance']").val(data.balance);
                    if (data.gender == 1) {
                        $("#boy").prop("checked", true);
                    } else if (data.gender == 2) {
                        $("#girl").prop("checked", true);
                    } else {
                        $("#noKnow").prop("checked", true);
                    }
                    $("[name='address']").val(data.address);
                    $("[name='level.id']").val(data.level.id);
                    $("#money").text("剩余金额:");
                })


                memberForm.form("load", rowData);
                var timestamp = Date.parse(new Date());
                var rechargeId = "BY" + timestamp;
                rechargeId = rechargeId.substring(0, 12);
                $("[name='sn']").val(rechargeId);
            }
        },
        //充值
        deposit: function () {
            var rowData = memberDatagrid.datagrid("getSelected");
            if (rowData) {
                depositForm.form("clear");
                depositDialog.dialog("setTitle", "充值");
                depositDialog.dialog("open");
                if (rowData.member)
                /* rowData["member.number"] = rowData.member.number;
                 rowData["member.balance"] = rowData.member.balance;
                 rowData["member.consume"] = rowData.member.consume; */
                    rowData["member.id"] = rowData.member.id;
                $("[name='number']").text(rowData.member.number);
                $("[name='balance']").text(rowData.member.balance);
                $("[name='consume']").text(rowData.member.consume);
                depositForm.form("load", rowData);
                $.post('/pet/queryLevelByPet?petId=' + rowData.id, function (data) {
                    $("#level").combobox("setValue", data.id);
                    $("[name='serviceDisCount1']").text(data.levelServiceDisCount);
                    $("[name='productDisCount1']").text(data.levelProducteDisCount);

                }, "json")
                var data = $("#emp_depo").combobox("getData");
                if (data.length > 0) {
                    $("#emp_depo").combobox("select", data[0].id);
                }
                var timestamp = Date.parse(new Date());
                var rechargeId = "BY" + timestamp;
                rechargeId = rechargeId.substring(0, 12);
                $("[name='sn']").val(rechargeId);
            } else {
                $.messager.alert("温馨提示", "请选择会员!", "warining");
            }
        },
        //添加宠物
        addPet: function () {
            var rowData = memberDatagrid.datagrid("getSelected");
            if (rowData) {
                petForm.form("clear");
                petDialog.dialog("setTitle", "添加宠物");
                petDialog.dialog("open");
                //特殊属性的处理
                if (rowData.member)
                    rowData["member.number"] = rowData.member.number;
                rowData["member.id"] = rowData.member.id;
                petForm.form("load", rowData);
                //清空名字
                $("[name='petName']").val("");
                //清空日期
                $("#birth").datebox('setValue', '');
                //清空血统
                $("[name='ancestry']").val("");
                //设置默认选中弟弟
                $("#didi").prop("checked", true);
                //清空颜色
                $("[name='color']").val("");
                var data = $("#breed1").combobox("getData");
                if (data.length > 0) {
                    $("#breed1").combobox("select", data[0].id);
                }
                $("[name='member.number']").attr("disabled", false);
            } else {
                $.messager.alert("温馨提示", "请选择会员!", "warining");
            }
        },
        //充值记录
        showRecharge: function () {
            rechargeDialog.dialog("setTitle", "充值记录");
            rechargeDialog.dialog("open");
            //打开后重新加载表格
            rechargeDatagrid.datagrid("reload");
            //打开后清空高级查询中的数据
            $("#query_recharge input").val("");
            //特殊属性的处理
            /*if (rowData.member)
             rowData["member.number"] = rowData.member.number;
             rowData["member.id"] = rowData.member.id;*/
        },
        //删除宠物
        deletePet: function () {
            var rowData = memberDatagrid.datagrid("getSelected");
            if (rowData) {
                $.messager.confirm("温馨提示", "您确定需要删除选中数据吗？", function (yes) {
                    if (yes) {
                        $.get("/pet/delete?petId=" + rowData.id, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    memberDatagrid.datagrid("reload");
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
        //刷新页面
        reload: function () {
            memberDatagrid.datagrid("reload");
        },
        //保存添加会员
        saveMember: function () {
            memberForm.form("submit", {
                url: "/pet/savePetAndMember",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            memberDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //保存导入会员
        saveExcel: function () {
            excelForm.form("submit", {
                url: "/pet/savePetAndMemberExcel",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            excelDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //更新会员信息
        updateMember: function () {
            memberForm.form("submit", {
                url: "/pet/updatePetAndMember",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            memberDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //保存添加宠物
        savePet: function () {
            petForm.form("submit", {
                url: "/pet/save",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            petDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //保存修改图片
        updatePic: function () {
            editPicForm.form("clear");
            editPicForm.form("submit", {
                url: "/pet/updatePic",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            uploadPicDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //保存充值
        saveDeposit: function () {
            depositForm.form("submit", {
                url: "/member/credit",
                onSubmit: function (param) {
                    param["recharge.remark"] = $("[name='remark']").val();
                    param["recharge.pay"] = $("[name='pay']").val();
                    param["recharge.sn"] = $("[name='sn']").val();
                    return true;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info", function () {
                            depositDialog.dialog("close");
                            memberDatagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                }
            });
        },
        //取消
        cancel: function () {
            memberDialog.dialog("close");
            petDialog.dialog("close");
            depositDialog.dialog("close");
            rechargeDialog.dialog("close");
            excelDialog.dialog("close");
            uploadPicDialog.dialog("close");
        },
        //会员的高级查询
        query: function () {
            var keyword = $("[name='keyword']").val();
            var phone = $("[name='phone']").val();
            memberDatagrid.datagrid("load", {
                keyword: keyword,
                phone: phone
            });
        },
        //导入会员
        channel: function () {
            excelDialog.dialog("setTitle", "导入会员");
            excelDialog.dialog("open");
        },
        //充值记录的高级查询
        select: function () {
            var rechargeId = $("[name='rechargeId']").val();
            var tel = $("[name='tel']").val();
            var memberName = $("[name='memberName']").val();
            rechargeDatagrid.datagrid("load", {
                rechargeId: rechargeId,
                tel: tel,
                memberName: memberName
            });
        }
    }
})
;
//定义获取选择会员等级的事件
$(function () {
    /*   $("#level1").combobox({
     onLoadSuccess: function () {
     var level = $("#level1").combobox("getValue");
     $.post('/vipLevel/queryLevel?levelId=' + level, function (data) {
     $("[name='serviceDisCount']").text(data.levelServiceDisCount);
     $("[name='productDisCount']").text(data.levelProducteDisCount);
     }, "json")
     }
     });*/
    $("#level1").combobox({
        onSelect: function () {
            var level = $("#level1").combobox("getValue");
            $.post('/vipLevel/queryLevel?levelId=' + level, function (data) {
                $("[name='serviceDisCount']").text(data.levelServiceDisCount);
                $("[name='productDisCount']").text(data.levelProducteDisCount);
            }, "json")
        }
    });
    /*memberDatagrid.datagrid()
     var data = $("#petBreed").combobox("getData");
     if (data.length > 0) {
     $("#breed1").combobox("select", data[0].id);
     }*/
});

function numberFormat(value, record, index) {
    if (value) {
        return value.number;
    }
    return value;
}
function balanceFormat(value, record, index) {
    if (value) {
        return value.balance;
    }
    return value;
}
function addressFormat(value, record, index) {
    if (value) {
        return value.address;
    }
    return value;
}
function picFormat(value, record, index) {
        return '<a  onclick="uploadPic('+index+')">'+'<image src="/images/login/1.png"></image>'+'</a>';
}
function breedFormat(value, record, index) {
    if (value) {
        return value.text;
    }
    return value;
}
function nameFormat(value, record, index) {
    if (value) {
        return value.name;
    }
    return value;
}
function sexFormat(value, record, index) {
    if (value == 1) {
        return "弟弟";
    } else if (value == 2) {
        return "妹妹";
    } else {
        return "不详";
    }
}
//修改宠物图片
function uploadPic(index) {
    $("#member_datagrid").datagrid("selectRow",index);//点击行的这个图片后就会选中该行
    var row = $("#member_datagrid").datagrid("getSelected");//获得该行信息
    if(row){
        $("#uploadPic_dialog").dialog("open").dialog("setTitle","修改宠物图片");
    }
}
