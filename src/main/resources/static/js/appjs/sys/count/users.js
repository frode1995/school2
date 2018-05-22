var cs=document.getElementById("cs");
var art=document.getElementById("art")
var xindian=document.getElementById("xindian")
var guanli=document.getElementById("guanli")
var imgUrl=document.getElementById("imgUrl")
var prefix="/paper/content/count/"//公用前缀 都放在paper里

/*$().ready(function () {
    
})*/

cs.onclick=function () {
    $.ajax({
        type: "get",
        url: prefix + '6',//计算机学院是6
        dataType: 'json',

        error: function (request) {
            console.log("连接失败")
        },
        success: function (data) {
            if (data.status==="success") {//生成图表成功后就可以把src设置为table里面的柱状图
                // imgUrl.src=data.college.toString();
                // window.location.href="/paper/content/count"
                // imgUrl.src="../../img/table/barchart.jpg";
                // imgUrl.style.visibility="visible"
                // window.location.href= "/paper/content/count2/"+6;
                if(data.deptId==="6"){
                    // window.location.href="/paper/content/count/cs"
                    window.open("/paper/content/count/cs")
                }


            } else {
                console.log("错误")
            }
         }
        }

    )
}


/*
art.onclick=function () {
    $.ajax({
            type: "get",
            url: prefix + '9',//艺术学院是9
            dataType: 'json',

            error: function (request) {
                console.log("连接失败")
            },
            success: function (data) {
                if (data.status==="success") {//生成图表成功后就可以把src设置为table里面的柱状图
                    // imgUrl.src=data.college.toString();
                    // window.location.href="/paper/content/count"
                    // imgUrl.src="../../img/table/barchart.jpg";
                    // imgUrl.style.visibility="visible"
                    // window.location.href= "/paper/content/count2/"+6;

                    window.location.href="/paper/content/count/art"

                } else {
                    console.log("错误")
                }
            }
        }

    )
}
*/

guanli.onclick=function () {
    $.ajax({
            type: "get",
            url: prefix + '13',//管理学院
            dataType: 'json',

            error: function (request) {
                console.log("连接失败")
            },
            success: function (data) {
                if (data.status==="success") {//生成图表成功后就可以把src设置为table里面的柱状图
                    // imgUrl.src=data.college.toString();
                    // window.location.href="/paper/content/count"
                    // imgUrl.src="../../img/table/barchart.jpg";
                    // imgUrl.style.visibility="visible"
                    // window.location.href= "/paper/content/count2/"+6;
                    if(data.deptId==="13"){
                        window.open("/paper/content/count/guanli")
                        // window.location.href="/paper/content/count/guanli"
                    }


                } else {
                    console.log("错误")
                }
            }
        }

    )
}

/*
xindian.onclick=function () {
    $.ajax({
            type: "get",
            url: prefix + '11',//信电学院是6
            dataType: 'json',

            error: function (request) {
                console.log("连接失败")
            },
            success: function (data) {
                if (data.status==="success") {//生成图表成功后就可以把src设置为table里面的柱状图
                    // imgUrl.src=data.college.toString();
                    // window.location.href="/paper/content/count"
                    // imgUrl.src="../../img/table/barchart.jpg";
                    // imgUrl.style.visibility="visible"
                    // window.location.href= "/paper/content/count2/"+6;
                    window.location.href="/paper/content/count/xindian"

                } else {
                    console.log("错误")
                }
            }
        }

    )
}*/
