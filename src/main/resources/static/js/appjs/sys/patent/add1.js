var prefix = "/patent/content"
$(function(){
    laydate({
        elem : '#patentTime'//需要用到时间
    });
});

$().ready(function() {
    validateRule();
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {

    // validateRule();

    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/savePatent",
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
                // layer.msg("更新成功");
                window.location.href = '/patent/content';//应该回到patent列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                // layer.alert(data.msg)
                window.location.href = '/patent/content';
            }
        }
    });
// }
});

function validateRule() {

    $("#basicInfoForm").validate({//验证是否输入了指定内容
        rules : {
            patentAut : "required",
            patentName : "required"
        },
        messages : {
            patentAut  : "请填写专利作者",
            patentName : "请填写专利名称",

        }
    });
}
