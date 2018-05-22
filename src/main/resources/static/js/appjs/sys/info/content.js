var prefix = "/info/content"

$(function() {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/list", // 服务器数据的加载地址
                // showRefresh : true,
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
                        author : $('#searchName').val()//按最后编辑者搜索
                        // cId : cId
                        // deptId : deptId
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
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
                        field : 'infoId',//
                        title : ''
                    },
                    {
                        field : 'title',
                        title : '标题',
                        width :320,
                        // sortable : true,
                        formatter:function (value,row,index) {//这里有可能报错 infoId
                            return '<a href="#" onclick="preview(\''+ row.infoId+ '\')">'+row.title+'</a>';
                        }
                    },
                    {
                        field : 'author',
                        title : '最后编辑者',
                        // sortable : true
                    },
                    {
                        visible : true,//是否默认显示
                        field : 'created',
                        title : '创建人id'//这个就是原始作者
                    },
                    {
                        visible : true,
                        field : 'modified',
                        title : '最近修改人id'
                    },
                    {
                        visible : true,
                        field : 'gtmModified',
                        title : '最近修改时间'
                    },
                    {
                        visible : false,
                        field : 'content',
                        title : '内容'
                    },
                    {
                        visible : false,
                        field : 'type',
                        title : '类型'//文章
                    },
                    {
                        visible : false,
                        field : 'tags',
                        title : '标签'
                    },
                    {
                        visible : true,
                        field : 'categories',
                        title : '分类'//奖项 论文  项目
                    },
                    {
                        field : 'status',
                        title : '状态',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if (value == '0') {//如果返回值是0
                                return '<span class="label label-danger">草稿</span>';
                            } else if (value == '1') {
                                return '<span class="label label-primary">发布</span>';
                            }
                        }
                    },
                    {
                        title : '操作',
                        field : 'id',//这里可能报错
                        align : 'center',
                        formatter : function(value, row, index) {
                            // var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                            var e = '<a class="btn btn-primary btn-sm '+''+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.infoId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+''+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.infoId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="编辑详情"  mce_href="#" onclick="preview(\''
                                + row.infoId
                                + '\')"><i class="fa fa-rocket"></i></a> ';
                            return e + d +f;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    var addPage = layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url
    });
    layer.full(addPage);
}
function edit(infoId) {
    var editPage = layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + infoId // iframe的url
    });
    layer.full(editPage);
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function preview(id) {//改为编辑详情
    window.open("/blog/open/post/"+id);
    //window.location.href="/blog/open/post/"+id;
}
function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var infoIds = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            infoIds[i] = row['infoId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "infoIds" : infoIds
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