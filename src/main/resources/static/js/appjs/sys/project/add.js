var prefix = "/project/content"
$(function(){
    laydate({
        elem : ['#projectStart','#projectEnd']//需要用到时间
    });
});

$().ready(function() {
    validateRule();
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {


    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/saveProject",
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
                parent.location.href = '/project/content';//应该回到project列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                // layer.alert(data.msg)
                parent.location.href = '/project/content';
            }
        }
    });
// }
});

function validateRule() {

    $("#basicInfoForm").validate({//验证是否输入了指定内容
        rules : {
            projectName : "required",
            projectLeader : "required"
        },
        messages : {
            projectName : "请填写项目名称",
            projectLeader : "请填写项目负责人"
        }
    });
}
