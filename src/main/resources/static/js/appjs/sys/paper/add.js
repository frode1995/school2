var prefix = "/paper/content"
$(function(){
    laydate({
        elem : '#publishTime'//需要用到时间
    });
});

$().ready(function() {
    validateRule();
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {


    console.log("111");

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
                // layer.msg("更新成功");
                parent.location.href = '/paper/content';//应该回到paper列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                // layer.alert(data.msg)
                parent.location.href = '/paper/content';
            }
        }
    });
// }
});

function validateRule() {

    $("#basicInfoForm").validate({//验证是否输入了指定内容
        rules : {
            paperName : "required",
            paperJournal : "required",
            paperAuthor : "required"
        },
        messages : {
            paperName : "请填写论文名称",
            paperJournal : "请填写论文期刊",
            paperAuthor : "请填写论文作者"
        }
    });
}
