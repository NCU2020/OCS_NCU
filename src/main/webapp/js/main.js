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