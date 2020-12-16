<%--
  Created by IntelliJ IDEA.
  User: 丁宇翔
  Date: 2020/11/25
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/main.css">
        <link rel="icon" href="images/favicon.ico">
        <script src="./js/jquery-3.5.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/main.js"></script>
        <title>OCS_NCU</title>
    </head>
    <body onload="initAJAX(); login();">
        <!--顶部导航栏-->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">OCS_NCU</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#">首页</a></li>
                        <li><a href="#">好友列表</a></li>
                        <li><a href="#">好友印象</a></li>
                    </ul>
                    <div id="btn-login-div">
                        <button type="submit" class="btn btn-info navbar-btn navbar-right" data-toggle="modal" data-target="#LoginOrLogon" id="btn-login">登录</button>
                    </div>
                    <!--头像-->
                    <div id="avatar-div">
                    </div>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <div>

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
                                <form action="login" method="post" class="log-form" id="login-form">
                                    <div class="form-group">
                                        <label for="id">账号</label>
                                        <input type="text" required class="form-control" id="id" name="id" placeholder="请输入账号">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">密码</label>
                                        <input type="password" required class="form-control" id="password" name="password" placeholder="请输入密码">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">登录</button>
                                </form><!--登录表单-->
                            </div><!--登录页面-->
                            <!--注册页面-->
                            <div role="tabpanel" class="tab-pane" id="logon">
                                <form action="logon" class="log-form" id="logon-form" method="post">
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="email" required class="form-control" id="email" name="logon-email" placeholder="请输入电子邮箱">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">用户名</label>
                                        <input type="text" required class="form-control" id="name" name="logon-name" placeholder="请输入用户名">
                                    </div>
                                    <div class="form-group">
                                        <label for="logon-password">密码</label>
                                        <input type="password" required class="form-control" id="logon-password" name="logon-pwd" placeholder="请输入密码">
                                    </div>
                                    <div class="form-group">
                                        <label for="conform-password">确认密码</label>
                                        <input type="password" required class="form-control" id="conform-password" placeholder="请重复密码">
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

        <div id="book"></div>

        <script>
            function login()
            {
                if ("${sessionScope.logState}" == "SUCCESS")
                {
                    document.getElementById("btn-login-div").style.display = "none";
                    document.getElementById("avatar-div").innerHTML = "<img src='images/1.jpg' class='img-rounded navbar-right' style='height: 40px; height: 40px; margin-top: 5px;'>"
                    showFriendList();
                }
                else if ("${sessionScope.logState}" == "FAIL")
                {
                    alert("登陆失败");
                }
            }

            function showFriendList()
            {
                xmlHttp.open("post", "getUser?method=getFriends&user="+"${sessionScope.user.id}", true);
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState == 4)
                    {
                        var data = xmlHttp.responseText;
                        var obj = JSON.parse(data);
                        var listbook = '';
                        for (var i in obj)
                        {
                            var bookname = obj[i].name;
                            var desc = obj[i].id;
                            if (desc.length > 20)
                            {
                                desc = desc.substring(0, 17)
                            }
                            listbook += `<div class="col-sm-9 col-md-3"><div class="thumbnail" ><img src=`
                                +obj[i].image+
                                `><div class="caption">  <h4>`
                                + bookname +
                                `</h4><p>` + desc +
                                `</p><p><a href="#" class="btn btn-primary" role="button">加入购物车</a> <a href="#" class="btn btn-default" role="button">查看详情` +
                                `</a></p></div></div> </div>`;
                        }
                        document.getElementById("book").innerHTML = listbook;
                    }
                }
                xmlHttp.send();
            }
        </script>
    </body>
</html>
