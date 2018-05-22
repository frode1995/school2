var prefix = "/prize/content"
$(function(){
    laydate({
        elem : '#prizeTime'//需要用到时间 两个时间时
    });
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {

    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/updatePrize",
        data: $('#basicInfoForm').serialize(),
        async: false,
        error: function (request) {
            laryer.alert("连接错误");
        },
        success: function (data) {
            // console.log("111")
            if (data.code === 0) {
                // console.log("222")
                parent.layer.msg("更新成功");
                // layer.msg("更新成功");
                parent.location.href = '/prize/content';//应该回到prize列表  这里填的是controller  然后才是转发到页面
            } else {
                parent.layer.alert(data.msg)
                // layer.alert(data.msg)
                parent.location.href = '/prize/content';
            }
        }
    });
// }

});

