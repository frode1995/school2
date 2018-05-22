var prefix = "/paper/content"
$(function(){
    laydate({
        elem : '#publishTime'//需要用到时间
    });
});


/**
 * 基本信息提交
 */
$("#base_save").click(function () {




    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/savePaper",
        data: $('#basicInfoForm').serialize(),
        async: false,
        error: function (request) {
            laryer.alert("连接错误");
        },
        success: function (data) {

            if (data.code === 0) {

                parent.layer.msg("添加成功");

                window.location.href='/paper/content/patentmy';//返回我的列表
                // parent.location.href = '/paper/content';//应该回到paper列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                window.location.href='/paper/content/patentmy';

            }
        }
    });
// }
});

