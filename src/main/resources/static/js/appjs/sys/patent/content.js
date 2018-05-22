var prefix = "/patent/content"
$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
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
                        patentAut : $('#searchName').val()//按作者检索
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
                        // sortable : true,

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
                    },
                    //不要info那一列
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',//这里s_edit_h带权限部分待改
                        formatter : function(value, row, index) {
                            // var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.patentId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm " href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.patentId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="编辑详情"  mce_href="#" onclick="editAll(\''
                                + row.patentId
                                + '\')"><i class="fa fa-comment"></i></a> ';
                            return e + d +f;
                        }
                    } ]
            });
}//属于SEO类搜索引擎会注意到这些  mce_href
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function preview(patentId) {
    window.open("/blog/open/post/"+patentId);//在新窗口打开预览页面
    //window.location.href="/blog/open/post/"+id;
}

function add() {
    var addPage = layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : '/patent/content/add' // iframe的url 跳转到添加页面
    });
    layer.full(addPage);
}
function edit(patentId) {//
    var editPage = layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + patentId // iframe的url
    });
    layer.full(editPage);
}
function remove(patentId) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'patentId' : patentId
            },
            success : function(r) {
                if (r.code === 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function editAll(patentId) {// 编辑详情
    var editPage = layer.open({
        type : 2,
        title : '编辑详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/editall/' + patentId // iframe的url
    });
    layer.full(editPage);
    //window.open("/blog/open/post/"+patentId);
    //window.location.href="/blog/open/post/"+id;//无用
}
function batchRemove() {//改到这里
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length === 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var patentIds = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            patentIds[i] = row['patentId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "patentIds" : patentIds
            },
            url : prefix + '/batchRemove',
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function() {

    });
}