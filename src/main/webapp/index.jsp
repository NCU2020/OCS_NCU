<%--
  Created by IntelliJ IDEA.
  User: 丁宇翔
  Date: 2020/11/25
  Time: 16:51
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
    <body onload="initAJAX();showCurrPage();">
        <div style="display: flex;height: 100%;">
            <!--导航条-->
            <div class="navbar-o">
                <div class="list-group" id="navbar">
                    <div class="list-group-item active" ondblclick="navClick(this)" id="navbar-main"><span class="glyphicon glyphicon glyphicon-star" aria-label="首页"></span></div>
                    <div class="list-group-item" ondblclick="navClick(this)" id="navbar-friendList"><span class="glyphicon glyphicon glyphicon-user" aria-label="好友列表"></span></div>
                    <div class="list-group-item" ondblclick="navClick(this)" id="navbar-impression"><span class="glyphicon glyphicon glyphicon-comment" aria-label="好友印象"></span></div>
                </div>
            </div>

            <div class="main">
                <iframe height="100%" width="100%" style="border: none;" src="main.jsp" id="iframe">

                </iframe>
            </div>
        </div>

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
