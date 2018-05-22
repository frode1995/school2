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
            // console.log("111")
            if (data.code === 0) {
                // console.log("222")
                parent.layer.msg("添加成功");

                window.location.href='/paper/content/papermy';

            } else {
                parent.layer.alert(data.msg)
                window.location.href='/paper/content/papermy';
                // layer.alert(data.msg)
                // parent.location.href = '/paper/content';
            }
        }
    });
// }
});


