var prefix = "/patent/content"
$(function() {
    load();
});

function load() {

    var userId=$("#userId").val()//取出userId
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/mypatent/"+userId, // 服务器数据的加载地址
                showRefresh : true,//显示刷新按钮
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
                        // patentAut : $('#searchName').val()//按作者检索
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                // search : true,//搜索 此搜索是客户端搜索，不会进服务端
                // searchOnEnterKey : true,
                columns : [
                    {
                        checkbox : true
                    },
                    {
                        visible : false,
                        field : 'patentId',
                        title : 'patentId'
                    },
                    {
                        field : 'patentName ',
                        title : '专利名称',
                        width :320,/*跳转到详情展示页面*/

                    },
                    {
                        visible : true,
                        field : 'patentType',
                        title : '专利类型'
                    },
                    {
                        visible : true,
                        field : 'patentAut',
                        title : '专利作者'
                    },
                    {
                        visible : true,
                        field : 'ratio',
                        title : '比例'
                    },
                    {
                        visible : true,
                        field : 'patentNum',
                        title : '专利授权号'
                    },
                    {
                        visible : true,
                        field : 'patentTime',
                        title : '授权时间'
                    },
                    {
                        visible : true,
                        field : 'patentNew',
                        title : '最近更改时间'
                    },
                    {
                        visible : false,
                        field : 'patentImgId',
                        title : 'imgId'
                    },
                    {
                        visible : false,
                        field : 'patentFileId',
                        title : 'fileId'
                    },
                    {
                        visible : false,
                        field : 'userId',
                        title : '负责人id'
                    }
                    //不要info那一列
                 ]
            });
}//属于SEO类搜索引擎会注意到这些  mce_href
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

