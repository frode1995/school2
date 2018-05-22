var prefix = "/paper/content"


$(function() {
    load();
});

function load() {
    var userId=$("#userId").val()//取出userId
    $('#exampleTable')
        .bootstrapTable(
            {//在list页面处理一下  数据字典5 6 的问题
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/mypaper/"+userId, // 服务器数据的加载地址  加userId的
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
                        // paperJournal : $('#searchName').val()//按第一作者检索
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
                        field : 'paperId',
                        title : 'paperId'
                    },
                    {
                        field : 'paperName',
                        title : '论文名称',
                        width :320,/*跳转到详情展示页面*/
                        // sortable : true,
                        formatter:function (value,row,index) {
                            return '<a href="#" onclick="preview(\''+ row.paperId+ '\')">'+row.paperName+'</a>';
                        }
                    },
                    {
                        visible : true,
                        field : 'paperJournal',
                        title : '发表刊物/会议',
                        sortable:true
                    },
                    {
                        field : 'paperAuthor',
                        title : '作者'
                        // sortable : true
                    },
                    {
                        visible : false,
                        field : 'paperComAuthor',
                        title : '通讯作者'
                    },
                    {
                        visible : false,
                        field : 'paperSign',
                        title : '署名情况'
                    },
                    {
                        visible : false,
                        field : 'firAutDept',
                        title : '第一作者单位'
                    },
                    {
                        visible : false,
                        field : 'myDept',
                        title : '本人第一单位'
                    },
                    {
                        visible : false,
                        field : 'publishTime',
                        title : '发表年月'
                    },
                    {
                        visible : false,
                        field : 'paperImgId',
                        title : 'imgId'
                    },
                    {
                        visible : false,
                        field : 'paperFileId',
                        title : 'fileId'
                    },
                    {
                        visible : true,
                        field : 'paperNew',
                        title : '记录最后更改时间'
                    },
                    {
                        visible : false,
                        field : 'isJournal',
                        title : '是否是期刊'
                    },
                    {
                        visible : false,
                        field : 'sci',
                        title : 'sci'
                    },
                    {
                        visible : false,
                        field : 'eij',
                        title : 'eij'
                    },
                    {
                        visible : false,
                        field : 'eim',
                        title : 'eim'
                    },
                    {
                        visible : false,
                        field : 'cscd',
                        title : 'cscd'
                    },
                    {
                        visible : false,
                        field : 'cnCore',
                        title : 'cnCore'
                    },
                    {
                        visible : false,
                        field : 'cpci',
                        title : 'cpci'
                    },
                    {
                        visible : false,
                        field : 'ssci',
                        title : 'ssci'
                    },
                    {
                        visible : false,
                        field : 'ahci',
                        title : 'ahci'
                    },
                    {
                        visible : true,
                        field : 'sciNum',
                        title : 'sci检索号'
                    },
                    {
                        visible : true,
                        field : 'eiNum',
                        title : 'EI检索号'
                    },
                    {
                        visible : true,
                        field : 'issnNum',
                        title : 'issn检索号'
                    },
                    {
                        visible : false,
                        field : 'paperInfo',
                        title : '论文详情'
                    },
                    {
                        visible : false,
                        field : 'userId',
                        title : '作者id'
                    },

                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',//这里s_edit_h带权限部分待改
                        formatter : function(value, row, index) {
                            // var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.paperId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm " href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.paperId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="编辑详情"  mce_href="#" onclick="editAll(\''
                                + row.paperId
                                + '\')"><i class="fa fa-comment"></i></a> ';
                            return e + d +f;
                        }
                    } ]
            });
}//属于SEO类搜索引擎会注意到这些  mce_href
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function preview(paperId) {
    window.open("/blog/open/post/"+paperId);//在新窗口打开预览页面
    //window.location.href="/blog/open/post/"+id;
}

function add() {
    var addPage = layer.open({
        type : 2,
        title : '增加',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/add' // iframe的url 跳转到添加页面
    });
    layer.full(addPage);
}
function edit(paperId) {//
    var editPage = layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/edit/' + paperId // iframe的url
    });
    layer.full(editPage);
}
function remove(paperId) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'paperId' : paperId
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

function editAll(paperId) {// 编辑详情
    var editPage = layer.open({
        type : 2,
        title : '编辑详情',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '520px' ],
        content : prefix + '/editall/' + paperId // iframe的url
    });
    layer.full(editPage);
    //window.open("/blog/open/post/"+paperId);
    //window.location.href="/blog/open/post/"+id;//无用
}
function batchRemove() {//改到这里
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn : [ '确定', '取消' ]
        // 按钮
    }, function() {
        var paperIds = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function(i, row) {
            paperIds[i] = row['paperId'];
        });
        $.ajax({
            type : 'POST',
            data : {
                "paperIds" : paperIds
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