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
    // var hobbyStr = getHobbyStr();
    // $("#hobby").val(hobbyStr);
    // if($("#basicInfoForm").valid()){//先不验证
    console.log("111");
    $.ajax({
        cache: true,
        type: "POST",
        url: prefix + "/updatePaper",
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

/*
function getHobbyStr(){
    var hobbyStr ="";
    $(".hobby").each(function () {
        if($(this).is(":checked")){
            hobbyStr+=$(this).val()+";";
        }
    });
   return hobbyStr;
}*/
