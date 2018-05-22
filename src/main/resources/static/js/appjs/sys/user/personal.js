var prefix = "/sys/user"
$(function () {
    laydate({
        elem : '#birth'
    });
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {
    // var hobbyStr = getHobbyStr();
    // $("#hobby").val(hobbyStr);
    if($("#basicInfoForm").valid()){
            $.ajax({
                cache : true,
                type : "POST",
                url :"/sys/user/updatePeronal",
                data : $('#basicInfoForm').serialize(),
                async : false,
                error : function(request) {
                    laryer.alert("连接错误");
                },
                success : function(data) {
                    // console.log("111")
                    if (data.code === 0) {
                        // console.log("222")
                        parent.layer.msg("更新成功");
                        // layer.msg("更新成功");
                        parent.location.href = '/index';
                    } else {
                        parent.layer.alert(data.msg)
                        // layer.alert(data.msg)
                        parent.location.href = '/index';
                    }
                }
            });
        }

});
$("#pwd_save").click(function () {
    if($("#modifyPwd").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :"/sys/user/resetPwd",
            data : $('#modifyPwd').serialize(),
            async : false,
            error : function(request) {
                parent.laryer.alert("Connection error");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("更新密码成功");
                    $("#photo_info").click();
                    parent.location.href = '/index';
                } else {
                    parent.layer.alert(data.msg)
                    parent.location.href = '/index';
                }
            }
        });
    }
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
