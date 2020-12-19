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
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, maximum-scale=1">
        <link rel="stylesheet" href="./css/bootstrap.min.css">
        <link rel="stylesheet" href="./css/main.css">
        <link rel="icon" href="images/favicon.ico">
        <script src="./js/jquery-3.5.1.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/main.js"></script>
        <title>OCS_NCU</title>
    </head>
    <body onload="initAJAX();">
        <!--导航条-->
        <div class="navbar-o">
            <div class="list-group" id="navbar">
                <div class="list-group-item active"><span class="glyphicon glyphicon glyphicon-star" aria-label="首页"></span></div>
                <div class="list-group-item"><span class="glyphicon glyphicon glyphicon-user" aria-label="好友列表"></span></div>
                <div class="list-group-item"><span class="glyphicon glyphicon glyphicon-comment" aria-label="好友印象"></span></div>
            </div>
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

        <script>
            /* 获取登陆状态 */
            function getLogState()
            {
                return "${sessionScope.logState}";
            }

            function getUserId()
            {
                return ${sessionScope.user.id};
            }
        </script>
    </body>
</html>
