var prefix="/prize/content"
$().ready(function() {
    $('.summernote').summernote({
        height : '220px',
        lang : 'zh-CN',
        callbacks: {
            onImageUpload: function(files, editor, $editable) {
                console.log("onImageUpload");
                sendFile(files);
            }
        }
    });
    var prizeInfo = $("#prizeInfo").val();

    $('#prizeInfo_sn').summernote('code', prizeInfo);
    // validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});
function save(status) {
    $("#status").val(status);
    var prizeInfo_sn = $("#prizeInfo_sn").summernote('code');
    $("#prizeInfo").val(prizeInfo_sn);//summernote里面的值存入变量

    $.ajax({
        cache : true,
        type : "POST",
        url : prefix+"/save",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("错误");
        },
        success : function(data) {
            if (data.code === 0) {
                parent.layer.msg("操作成功");
                // console.log("111")
                parent.reLoad();
                // console.log("222")

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
// function validateRule() {
//     var icon = "<i class='fa fa-times-circle'></i> ";//或许要删掉
// }

function returnList() {
    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);
}

