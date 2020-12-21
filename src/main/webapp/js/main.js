var xmlHttp = false;

function initAJAX()
{
    if(window.XMLHttpRequest)
    {
        xmlHttp = new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {
        try
        {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e)
        {
            try
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }catch (e)
            {
                window.alert("不支持ajax")
            }
        }
    }
}

function hidePwdWarn()
{
    let e = document.getElementById("pwd-diff-warn");
    let pwd = document.getElementById("logon-password").value;
    let pwd1 = document.getElementById("conform-password").value;
    if (pwd === pwd1)
    {
        e.style.display = "block";
        document.getElementById("logon-btn").disabled = true;
    }
    else
    {
        e.style.display = "none";
        document.getElementById("logon-btn").disabled = false;
    }
}

function hideLoginWarn ()
{
    document.getElementById("login-fail-warn").style.display = "none";
}

function navClick(data)
{
    if (data.id === null)
    {
        return;
    }
    if (sessionStorage.getItem("logState") != "SUCCESS" && data.id !== 'navbar-main')
    {
        console.log(data.id)
        alert("请先登录！")
        return;
    }
    let list = document.getElementsByClassName("list-group-item active");
    for (let i = 0; i < list.length; i++)
    {
        list[i].setAttribute("class", "list-group-item");
        console.log(list[i])
    }
    document.getElementById(data.id).setAttribute("class", "list-group-item active");
    document.getElementById("iframe").setAttribute("src",data.id.split('-')[1]+'.jsp');
    sessionStorage.setItem("currPage", data.id);
}

function showCurrPage()
{
    let data = [];
    data.id = sessionStorage.getItem("currPage");
    navClick(data);
}

function login()
{
    let id = document.getElementById("id").value;
    let pwd = document.getElementById("password").value;
    let warn = document.getElementById("login-fail-warn");

    if (id === '' || pwd === '')
    {
        warn.innerHTML = `<p>请输入账号及密码</p>`;
        warn.style.display = "block";
        return
    }

    else if (isNaN(id) || id.toString().includes(".") || id.toString().includes("0x") || id.toString().includes("0X"))
    {
        warn.innerHTML = `<p>请输入正确格式的账号</p>`
        warn.style.display = "block";
        return;
    }

    let url = "login?id="+id+"&password="+pwd;
    url = encodeURI(url);
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function ()
    {
        if (xmlHttp.readyState === 4)
        {
            let logState = xmlHttp.responseText;

            if (logState === "FAIL")
            {
                sessionStorage.setItem("logState", "FAIL");
                warn.innerHTML = `<p>账号或密码错误</p>`
                warn.style.display = "block";
            }
            else
            {
                sessionStorage.setItem("logState", "SUCCESS");
                let user = JSON.parse(logState)
                sessionStorage.setItem("user.name", user.name)
                sessionStorage.setItem("user.id", user.id);
                sessionStorage.setItem("user.image", user.image);
                window.location.href = "main.jsp";
            }
        }
    }
    xmlHttp.send();
}

function showMain()
{
    if (sessionStorage.getItem("logState") === "SUCCESS")
    {
        document.getElementById("div-login-btn").style.display = "none";
        document.getElementById("div-avatar").style.display = "flex";
        document.getElementById("div-avatar").style.flexDirection = "column";
        document.getElementsByTagName("img")[0].src=sessionStorage.getItem("user.image");
    }
}

function showFriendList()
{
    let url = "getUser?method=getFriends&user="+sessionStorage.getItem("user.id");
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function ()
    {
        if (xmlHttp.readyState === 4)
        {
            let data = xmlHttp.responseText;
            let friendList = JSON.parse(data);
            let html = '';

            for (let i in friendList)
            {
                let sex;
                if (friendList[i].sex === 'M')
                {
                    sex="男";
                }
                else
                {
                    sex="女";
                }
                html += `<div class="list-group-item friend-list-item" ondblclick="friendDClick(this)" title=`+ "账号："+friendList[i].id+"性别："+sex +` id=` + friendList[i].id + `>`+friendList[i].name+`</div>`
            }
            document.getElementById("friend-list").innerHTML = html;
        }
    }
    xmlHttp.send();
}

function friendDClick(data)
{
    let list = document.getElementsByClassName("friend-list-item active");
    let friendName = document.getElementById(data.id).innerText;
    let userName = sessionStorage.getItem("user.name");
    for (let i = 0; i<list.length; i++)
    {
        list[i].setAttribute("class", "list-group-item friend-list-item");
    }
    document.getElementById(data.id).setAttribute("class", "list-group-item friend-list-item active");
    let url = "getMessage?method=getMessageBetweenTwo&user1="+sessionStorage.getItem("user.id")+"&user2="+data.id;
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange = function ()
    {
        if (xmlHttp.readyState === 4)
        {
            let messageList = xmlHttp.responseText;
            messageList = JSON.parse(messageList);
            let html = '';

            for (let i in messageList)
            {
                let date = new Date(messageList[i].time);
                let name;

                if (sessionStorage.getItem("user.id") == messageList[i].from)
                {
                     name = userName;
                }
                else
                {
                    name = friendName;
                }

                html += `<div class="message-list-item"><div class="message-time">` + name + ' ' + date.toString()+`</div><div class="message-text">`+ messageList[i].content +`</div></div>`;
            }
            document.getElementById("message-list").innerHTML = html;
        }
    }
    xmlHttp.send();
}

function send()
{
    if (document.getElementsByClassName("friend-list-item active").length === 0)
    {
        alert("请先选择要发送消息的好友");
        return;
    }
    let friendId = document.getElementsByClassName("friend-list-item active")[0].id;
    let text = document.getElementById("message-content").value;
    if (text === '' || text === null)
    {
        return;
    }
    document.getElementById("message-content").value = '';
    let div = document.getElementById("message-list");
    let html = div.innerHTML;
    let date = new Date();
    html += `<div class="message-list-item"><div class="message-time">` + sessionStorage.getItem("user.name") + ' ' + date.toString() +`</div><div class="message-text">`+ text +`</div></div>`;
    div.innerHTML = html;

    let url = "getMessage?method=add&id=0&from="+sessionStorage.getItem("user.id")+"&to="+ friendId + "&time="+Date2YMD(date)+"&content="+text+"&read=N";
    url = encodeURI(url);
    xmlHttp.open("POST", url, true);
    xmlHttp.onreadystatechange=function ()
    {
        if (xmlHttp.readyState === 4)
        {
            console.log('发送成功')
        }
    }
    xmlHttp.send();
}

function Date2YMD(date)
{
    let r;
    //let date = new Date();
    r = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds()+"."+date.getMilliseconds();
    return r;
}

function getNewMessage()
{
    if (xmlHttp.readyState != 0 && xmlHttp.readyState != 4)
    {
        return;
    }
    let url = "getMessage?method=getNewMessage&user="+sessionStorage.getItem("user.id");
    xmlHttp.open("POST",url,true)
    xmlHttp.onreadystatechange=function ()
    {
        if (xmlHttp.readyState === 4)
        {
            let data = xmlHttp.responseText;
            let list = JSON.parse(data);

            for (let i = 0; i < list.length; i++ )
            {

                if (document.getElementsByClassName("friend-list-item active").length != 0)
                {
                    let friendId = document.getElementsByClassName("friend-list-item active")[0].id;

                    if (parseInt(friendId) === parseInt(list[i].from))
                    {
                        let div = document.getElementById("message-list");
                        let html = div.innerHTML;
                        let date = new Date(list[i].time)
                        html += `<div class="message-list-item"><div class="message-time">` + getFriendName(list[i].from) + ' ' + date.toString() + `</div><div class="message-text">` + list[i].content + `</div></div>`;
                        div.innerHTML = html;
                        setTimeout(setRead(list[i].id), 2000);
                    }
                }
            }
        }
    }
    xmlHttp.send();
}

function getFriendName(id)
{
    return document.getElementById(id).innerText;
}


function setRead(id)
{
    let url = "getMessage?method=setRead&id="+id+"&read=Y"
    xmlHttp.open("POST",url, true);
    xmlHttp.onreadystatechange=function ()
    {

    }
    xmlHttp.send();
}