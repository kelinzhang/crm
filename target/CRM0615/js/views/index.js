$(function () {

    $("a[data-cmd]").on("click", function () {
        var methods = $(this).data("cmd");
        if (methods) {
            cmdObj[methods]();
        }
    });
    var cmdObj = {

        petMethod: function () {
            document.getElementById("indexManager").src = 'http://localhost/petService'
        },
        addMember: function () {
            $('#win').window("open");
            $('#win').window('refresh', '/productStock');
        }
    };

    $("#tabpage2 li").click(function () {
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected");
        });
        $(this).addClass("selected");
        var title = $(this.children).data("title");
        var url = $(this.children).data("url");
        if ($("#mtTabs").tabs("exists", title)) {
            $("#mtTabs").tabs("select", title);
        } else {
            //往选项卡里添加面板
            $("#mtTabs").tabs("add", {
                title: title,
                closable: true,
                content: '<iframe src="' + url + '" style="width:100%;height:100%" frameborder="0"></iframe>'
            });
        }
    });

});

function jumpCashJsp() {


    if ($("#mtTabs").tabs("exists", '收银管理')) {
        $("#mtTabs").tabs("select", '收银管理');
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: '收银管理',
            closable: true,
            content: '<iframe src="' + '/cash' + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}
function stock(data) {
    console.log(data);
    if ($("#mtTabs").tabs("exists", $(data).data("title"))) {
        $("#mtTabs").tabs("select", $(data).data("title"));
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: $(data).data("title"),
            closable: true,
            content: '<iframe src="' + $(data).data("url") + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}
function cashRecord() {
    if ($("#mtTabs").tabs("exists", '收银记录管理')) {
        $("#mtTabs").tabs("select", '收银记录管理');
    } else {
        //往选项卡里添加面板
        $("#mtTabs").tabs("add", {
            title: '收银记录管理',
            closable: true,
            content: '<iframe src="' + '/cashRecord' + '" style="width:100%;height:100%" frameborder="0"></iframe>'
        });
    }
}