<%--
  Created by IntelliJ IDEA.
  User: 15613
  Date: 2020/12/20
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, maximum-scale=1">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/main.css">
        <link rel="icon" href="images/favicon.ico">
        <script src="./js/jquery-3.5.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/main.js"></script>
        <title>OCS_NCU</title>
    </head>
    <body onload="initAJAX();showMain(); setInterval(getNewRequest, 200);">

        <div id="div-login-btn">
            <button class="btn btn-primary btn-lg btn-login" type="button" data-toggle="modal" data-target="#LoginOrLogon">登录</button>
        </div>

        <div id="div-main">
            <div>查找并添加好友</div>
            <select id="search-method">
                <option value="id">根据账号查找</option>
                <option value="name">根据名称查找</option>
            </select>
            <input type="text" id="search-text">
            <button class="btn btn-primary" onclick="search()">查找</button>
            <div id="search-list"></div>
        </div>
        <!--模态框登录及注册页面-->
        <div class="modal fade" role="dialog" id="LoginOrLogon">
            <div class="modal-dialog modal-sm" role="document">
                <div class="modal-content">
                    <!--关闭模态框按键-->
                    <button type="button" class="btn btn-danger btn-close" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </button>

                    <div>
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#login" aria-controls="login" role="tab" data-toggle="tab">登录</a></li>
                            <li role="presentation"><a href="#logon" aria-controls="logon" role="tab" data-toggle="tab">注册</a></li>
                        </ul>

                        <div class="tab-content">
                            <!--登录页面-->
                            <div role="tabpanel" class="tab-pane active" id="login">
                                <!--登录表单-->
                                <form class="log-form" id="login-form">
                                    <div class="form-group">
                                        <label for="id">账号</label>
                                        <input type="text" required class="form-control" id="id" name="id" placeholder="请输入账号">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">密码</label>
                                        <input type="password" required class="form-control" id="password" name="password" onchange="hideLoginWarn()" placeholder="请输入密码">
                                    </div>
                                    <div class="text-danger bg-danger" id="login-fail-warn">
                                    </div>
                                    <button type="button" class="btn btn-primary btn-block" onclick="login()">登录</button>
                                </form><!--登录表单-->
                            </div><!--登录页面-->
                            <!--注册页面-->
                            <div role="tabpanel" class="tab-pane" id="logon">
                                <form action="logon" class="log-form" id="logon-form" method="post">
                                    <div class="form-group">
                                        <label for="code">邀请码</label>
                                        <input type="text" required class="form-control" id="code" name="logon-code" placeholder="请输入邀请码">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">用户名</label>
                                        <input type="text" required class="form-control" id="name" name="logon-name" placeholder="请输入用户名" maxlength="15">
                                    </div>
                                    <div class="form-group">
                                        <label for="logon-password">密码</label>
                                        <input type="password" required class="form-control" id="logon-password" name="logon-pwd" placeholder="请输入密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="conform-password">确认密码</label>
                                        <input type="password" required class="form-control" id="conform-password" onchange="hidePwdWarn()" placeholder="请重复密码">
                                    </div>
                                    <div class="text-danger bg-danger" id="pwd-diff-warn">
                                        <p>两次密码不一致！</p>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" required name="logon-sex" id="sex-male" value="male">
                                            男
                                        </label>
                                        <label>
                                            <input type="radio" required name="logon-sex" id="sex-female" value="female">
                                            女
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label for="birthday">出生日期</label>
                                        <input type="date" required class="form-control" id="birthday" name="logon-birthday">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block" id="logon-btn">注册</button>
                                </form>
                            </div><!--注册页面-->
                        </div><!--/.tab-content-->
                    </div>
                </div><!--/.modal-content-->
            </div><!--/.modal-dialog-->
        </div><!--./modal-->
    </body>

    <script>
        $("#conform-password").bind
        (
            "input propertychange",
            function ()
            {
                if ($("#logon-password").val() != $("#conform-password").val())
                {
                    $("#pwd-diff-warn").show();
                    document.getElementById("logon-btn").disabled = true;
                }
                else
                {
                    $("#pwd-diff-warn").hide();
                    document.getElementById("logon-btn").disabled = false;
                }
            }
        )

        function search()
        {
            let text = document.getElementById("search-text").value;
            if (text === '' || text === ' ')
            {
                return;
            }
            else if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
            {
                setTimeout(search, 100);
            }
            let method = document.getElementById("search-method").value;
            let url;
            if (method === 'id')
            {
                url = "getUser?method=getUserById&id="+text;
            }
            else
            {
                url = "getUser?method=getUserByName&name="+text;
            }
            xmlHttp.open("POST",url,true);
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState === 4)
                {
                    let list = xmlHttp.responseText;
                    list = JSON.parse(list);
                    let html = '';
                    for (let i = 0; i < list.length; i++)
                    {
                        let sex;
                        if (list[i].sex === 'M')
                        {
                            sex = '男';
                        }
                        else
                        {
                            sex = '女';
                        }
                        if (list[i].id != sessionStorage.getItem("user.id"))
                        {
                            html += `<div class="search-list-item">` + '账号：' + list[i].id + ' 名称：'
                                + list[i].name + ' 性别：' + sex + ' ' + `<button class="btn btn-primary" onclick="addFriend(this)" id=`
                                + 'btn-'+list[i].id + `>` + '添加好友' +`</button></div>
    `                   }
                        else
                        {
                            html += `<div class="search-list-item">` + '账号：' + list[i].id + ' 名称：'
                                + list[i].name + ' 性别：' + sex + ' ' + `</div>`
                        }
                    }
                    document.getElementById("search-list").innerHTML = html;
                }
            }
            xmlHttp.send();
        }

        function addFriend(data)
        {
            if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
            {
                setTimeout(addFriend(data), 100);
                return;
            }
            let date = new Date();
            let url = "getRelation?method=add&id=0&from="+sessionStorage.getItem("user.id")+"&to="
                +data.id.split('-')[1] + '&time=' + Date2YMD(date) + '&accepted=NEW';
            xmlHttp.open("POST", url, true);
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState === 4)
                {
                    alert("已发送好友请求");
                }
            }
            xmlHttp.send();
        }

        function getNewRequest()
        {
            if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
            {
                return;
            }
            let url = "getRelation?method=getRelationByAccepted&accepted=NEW&userType=to&user="+sessionStorage.getItem("user.id");
            xmlHttp.open("POST", url, true);
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState === 4)
                {
                    let list = xmlHttp.responseText;
                    list = JSON.parse(list);
                    for (let i = 0; i<list.length;i++)
                    {
                        let text = "你收到来自账号为：" + list[i].from + "的好友请求，是否接受？";
                        let f = confirm(text);
                        if (f)
                        {
                            Accept(list[i].id, 'ACCEPTED');
                        }
                        else
                        {
                            Accept(list[i].id, 'REFUSED');
                        }
                    }
                }
            }
            xmlHttp.send();
        }

        function Accept(id, data)
        {
            if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
            {
                setTimeout(Accept(id, data), 90);
                return;
            }
            let url = "getRelation?method=setAccepted&accepted="+data+"&id="+id;
            xmlHttp.open("POST",url,true)
            xmlHttp.onreadystatechange = function ()
            {
                if (xmlHttp.readyState === 4)
                {

                }
            }
            xmlHttp.send();
        }
    </script>
</html>
