<%--
  Created by IntelliJ IDEA.
  User: 丁宇翔
  Date: 2020/12/20
  Time: 11:43
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <body onload="initAJAX();  LoadSendList(); showFriendList(); loadImpression(); loadImS();">
        <div style="display: flex; height: 100%">
            <div>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#Im-R" aria-controls="Im-R" role="tab" data-toggle="tab">我收到的</a></li>
                    <li role="presentation"><a href="#Im-S" aria-controls="Im-S" role="tab" data-toggle="tab">我发送的</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="Im-R"></div>
                    <div role="tabpanel" class="tab-pane" id="Im-S"></div>
                </div>
            </div>
            <div style="margin: 10px;">
                <div>发送好友印象</div>
                <select id="select-friend"></select>
                <textarea id="impression-textarea"></textarea>
                <button class="btn btn-primary" onclick="sendImpression()">发送</button>
            </div>
        </div>
        <script>
            function loadImpression()
            {

                if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
                {
                    setTimeout(loadImpression, 100);
                    return;
                }
                let url = "getImpression?method=getImpressionByTo&to="+sessionStorage.getItem("user.id");

                xmlHttp.open("POST", url, true);
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState === 4)
                    {
                        let list = xmlHttp.responseText;
                        list = JSON.parse(list);
                        let html = '';

                        for (let i = 0; i < list.length; i++)
                        {
                            let name = sessionStorage.getItem("friend"+list[i].from);
                            let date = new Date(list[i].time);
                            html += `<div class="impression-list-item"><div class="impression-title">`+name+' '+ date.toString()
                                +`</div><div class="impression-item-content">`+ list[i].content +`</div></div>`;
                        }
                        document.getElementById("Im-R").innerHTML = html;
                    }
                }
                xmlHttp.send();
            }

            function loadImS()
            {
                if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
                {
                    setTimeout(loadImS, 100);
                    return;
                }
                let url = "getImpression?method=getImpressionByFrom&from="+sessionStorage.getItem("user.id")
                xmlHttp.open("POST", url, true);
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState === 4)
                    {
                        let list = xmlHttp.responseText;
                        list = JSON.parse(list);
                        let html = '';

                        for (let i = 0; i < list.length; i++)
                        {
                            let name = sessionStorage.getItem("friend"+list[i].to);
                            let date = new Date(list[i].time);
                            html += `<div class="impression-list-item"><div class="impression-title">`
                                    + 'To ' +name + ' ' + date.toString()
                                    +`</div><div class="impression-item-content">`+ list[i].content +`</div></div>`;
                        }
                        document.getElementById("Im-S").innerHTML = html;
                    }
                }
                xmlHttp.send();
            }

            function LoadSendList()
            {
                if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
                {
                    setTimeout(LoadSendList, 100);
                    return;
                }
                let url = "getUser?method=getFriends&user="+sessionStorage.getItem("user.id");
                xmlHttp.open("POST", url, true);
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState === 4)
                    {
                        let data = xmlHttp.responseText;
                        let friendList = JSON.parse(data);
                        let html = '';

                        for (let i = 0; i < friendList.length; i++)
                        {
                            sessionStorage.setItem("friend"+friendList[i].id, friendList[i].name);
                            html += `<option value=` + friendList[i].id + `>` + friendList[i].name + `</option>`;
                        }
                        document.getElementById("select-friend").innerHTML = html;
                    }
                }
                xmlHttp.send();
            }

            function sendImpression()
            {
                let content = document.getElementById("impression-textarea").value;
                if (content === '' || content === ' ')
                {
                    return
                }
                else if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
                {
                    setTimeout(sendImpression, 100);
                }

                let to = document.getElementById("select-friend").value;
                let date = new Date();
                document.getElementById("impression-textarea").value = '';
                let url = "getImpression?method=add&from="
                    + sessionStorage.getItem("user.id")
                    + '&to='+to+'&time='+Date2YMD(date).split(' ')[0]+'&content='+content;

                url = encodeURI(url);

                xmlHttp.open("POST", url, true);
                xmlHttp.onreadystatechange = function ()
                {
                    if (xmlHttp.readyState === 4)
                    {
                        loadImS();
                    }
                }

                xmlHttp.send();
            }
        </script>
    </body>
</html>
