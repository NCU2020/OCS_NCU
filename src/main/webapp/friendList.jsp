<%--
  Created by IntelliJ IDEA.
  User: 丁宇翔
  Date: 2020/12/20
  Time: 0:06
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
        <style>
            html,body
            {
                height: 100%;
            }
        </style>
        <title>OCS_NCU</title>
    </head>
    <body onload="initAJAX();showFriendList();">
    <div style="display: flex;flex-direction: row;height: 100%;">
        <div style="display: flex;flex-direction: column; height: 100%;width: 150px">
            <div class="list-group" id="friend-list">

            </div>
        </div>

        <div id="message-div">
            <div id="message-list"></div>
            <div id="message-input">
                <textarea id="message-content"></textarea>
                <button class="btn btn-primary" id="send-btn">发送</button>
            </div>
        </div>
    </div>
    </body>
</html>
