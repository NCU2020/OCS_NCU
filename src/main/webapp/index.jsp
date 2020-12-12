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
        <link rel="icon" href="./images/favicon.ico">
        <title>OCS_NCU</title>
    </head>
    <body>
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
                    <button type="submit" class="btn btn-info navbar-btn navbar-right" data-toggle="modal" data-target="#LoginOrLogon" id="btn-login">登录</button>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

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
                                <form method="post" class="log-form" id="login-form">
                                    <div class="form-group">
                                        <label for="id">账号</label>
                                        <input type="text" required class="form-control" id="id" placeholder="请输入账号">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">密码</label>
                                        <input type="password" required class="form-control" id="password" placeholder="请输入密码">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">登录</button>
                                </form><!--登录表单-->
                            </div><!--登录页面-->
                            <!--注册页面-->
                            <div role="tabpanel" class="tab-pane" id="logon">
                                <form class="log-form" id="logon-form" method="post">
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input type="email" required class="form-control" id="email" placeholder="请输入电子邮箱">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">用户名</label>
                                        <input type="text" required class="form-control" id="name" placeholder="请输入用户名">
                                    </div>
                                    <div class="form-group">
                                        <label for="logon-password">密码</label>
                                        <input type="password" required class="form-control" id="logon-password" placeholder="请输入密码">
                                    </div>
                                    <div class="form-group">
                                        <label>确认密码</label>
                                        <input type="password" required class="form-control" id="conform-password" placeholder="请重复密码">
                                    </div>
                                    <div class="text-danger bg-danger" id="pwd-diff-warn">
                                        <p>两次密码不一致！</p>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" required name="sex" id="sex-male" value="male">
                                            男
                                        </label>
                                        <label>
                                            <input type="radio" required name="sex" id="sex-female" value="female">
                                            女
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label for="birthday">出生日期</label>
                                        <input type="date" required class="form-control" id="birthday">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block" id="logon-btn">注册</button>
                                </form>
                            </div><!--注册页面-->
                        </div><!--/.tab-content-->
                    </div>
                </div><!--/.modal-content-->
            </div><!--/.modal-dialog-->
        </div><!--./modal-->

        <script src="./js/jquery-3.5.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>

        <script>
            $("#logon-password").bind
            (
                "input propertychange",
                function(event)
                {
                    console.log($("#logon-password").val());
                }
            );
            $("#conform-password").bind
            (
                "input propertychange",
                function (event)
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
        </script>
    </body>
</html>
