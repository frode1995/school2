var prefix = "/project/content"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                showRefresh : true,
                // showToggle : true,
                showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                //showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
                // "server"

                queryParams : function(params) {
                    return {
                        // 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit : params.limit,
                        offset : params.offset,
                        projectLeader : $('#searchName').val()//按第一作者检索

                    };
                },

                columns : [
                    {
                        checkbox : true
                    },
                    {
                        visible : false,
                        field : 'projectId',
                        title : 'projectId'
                    },
                    {
                        visible : true,
                        field : 'projectNum',
                        title : '项目编号'
                    },
                    {
                        field : 'projectName ',
                        title : '项目名称',
                        width :320,/*跳转到详情展示页面*/

                    },
                    {
                        visible : true,
                        field : 'projectLeader',
                        title : '项目负责人'
                    },
                    {
                        visible : true,
                        field : 'projectFunds',
                        title : '项目经费'
                    },
                    {
                        visible : true,
                        field : 'projectStart',
                        title : '项目开始时间'
                    },
                    {
                        visible : true,
                        field : 'projectEnd',
                        title : '项目结束时间'
                    },
                    {
                        visible : true,
                        field : 'projectNew',
                        title : '最近更改时间'
                    },
                    {
                        visible : false,
                        field : 'projectImgId',
                        title : 'imgId'
                    },
                    {
                        visible : false,
                        field : 'projectFileId',
                        title : 'fileId'
                    },
                    {
                        visible : false,
                        field : 'userId',
                        title : '负责人id'
                    },

                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',//这里s_edit_h带权限部分待改
                        formatter : function(value, row, index) {

                            var f = '<a class="btn btn-success btn-sm" href="#" title="查看详情"  mce_href="#" onclick="editAll(\''
                                + row.projectId
                                + '\')"><i class="fa fa-comment"></i></a> ';
                            return f;
                        }
                    } ]
            });
}//属于SEO类搜索引擎会注意到这些  mce_href
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}


/*function editAll(projectId) {// 编辑详情
    var editPage = layer.open({
        type : 2,
        title : '编辑详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/editall/' + projectId // iframe的url
    });
    layer.full(editPage);
    //window.open("/blog/open/post/"+projectId);
    //window.location.href="/blog/open/post/"+id;//无用
}*/
