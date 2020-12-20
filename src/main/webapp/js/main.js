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
            let date = xmlHttp.responseText;
            let friendList = JSON.parse(date);
            let html = '';

            for (let i in friendList)
            {
                html += `<div class="list-group-item friend-list-item" ondblclick="friendDClick(this)" id=` + friendList[i].id + `>`+friendList[i].name+`</div>`
            }
            document.getElementById("friend-list").innerHTML = html;
        }
    }
    xmlHttp.send();
}

function friendDClick(data)
{
    let list = document.getElementsByClassName("friend-list-item active");
    for (let i = 0; i<list.length; i++)
    {
        list[i].setAttribute("class", "list-group-item friend-list-item");
    }
    document.getElementById(data.id).setAttribute("class", "list-group-item friend-list-item active");
    console.log(data.id);
}