var prefix = "/prize/content"
$(function(){
    laydate({
        elem : '#prizeTime'//需要用到时间
    });
});

/*$().ready(function() {
    validateRule();
});*/
/**
 * 基本信息提交
 */
$("#base_save").click(function () {


    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/savePrize",
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
                window.location.href = '/prize/content';//应该回到prize列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                // layer.alert(data.msg)
                window.location.href = '/prize/content';
            }
        }
    });
// }
});

/*function validateRule() {

    $("#basicInfoForm").validate({//验证是否输入了指定内容
        rules : {
            prizeName : "required",
            prizeLeader : "required"
        },
        messages : {
            prizeName : "请填写获奖名称",
            prizeLeader : "请填写获奖负责人"
        }
    });
}*/
