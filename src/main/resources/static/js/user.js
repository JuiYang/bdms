// user.html
var apiUrl = '/api/users';
var saveUrl;
var type = 'PUT';
var $datagrid = $("#dg");
var $form = $('#fm');
var $dialog = $('#dlg');

loadAndSetGridData();

function loadAndSetGridData() {
    $.ajax({
        url: apiUrl,
        type: 'GET',
        dataType: 'json',
        error: function (xhr, status, err) {
            $datagrid.datagrid("loaded");
            $datagrid.datagrid('loadData', {
                total: 0,
                rows: []
            });
        },
        success: function (res) {
            $datagrid.datagrid("loaded");
            $datagrid.datagrid("loadData", {
                total: res.totalElements,
                rows: res.content
            });
        }
    })
}

function newUser() {
    $dialog.dialog('open').dialog('center').dialog('setTitle','新建用户');
    $form.form('clear');
    saveUrl = apiUrl;
    type = 'PUT';
}

function editUser() {
    var row = $datagrid.datagrid('getSelected');
    if (row){
        $dialog.dialog('open').dialog('center').dialog('setTitle','编辑用户');
        $form.form('load',row);
        saveUrl = apiUrl + '/' + row.id;
        type = 'POST';
    } else {
        $.messager.show({
            title: '消息',
            msg: "请先选择一条记录！"
        });
    }
}

function saveUser() {
    var username = $form.find("input[name='username']").val();
    var password = $form.find("input[name='password']").val();
    var age = $form.find("input[name='age']").val();
    var telephone = $form.find("input[name='telephone']").val();
    $.ajax({
        url: saveUrl,
        type: type,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        data: {
            username: username,
            password: password,
            age: age,
            telephone: telephone
        },
        success: function (res) {
            $.messager.show({
                title: '消息',
                msg: "操作成功！"
            });
            loadAndSetGridData();
            $dialog.dialog('close');
        },
        error: function () {
            $.messager.show({
                title: '消息',
                msg: "操作失败！"
            });
        }
    })
}

function destroyUser(){
    var row = $datagrid.datagrid('getSelected');
    if (row) {
        saveUrl = apiUrl + '/' + row.id;
        $.messager.confirm('确认','确定删除此用户吗？',function (result){
            if (result){
                $.ajax({
                    url: saveUrl,
                    type: "DELETE",
                    success: function () {
                        $.messager.show({
                            title: '消息',
                            msg: "操作成功！"
                        });
                        loadAndSetGridData();
                    },
                    error: function () {
                        $.messager.show({
                            title: '消息',
                            msg: "操作失败！"
                        });
                    }
                });
            }
        });
    } else {
        $.messager.show({
            title: '消息',
            msg: "请先选择一条记录！"
        });
    }
}
