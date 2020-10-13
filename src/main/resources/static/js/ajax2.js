function ajax2(method,url,data2,callback)
{
    if(window.XMLHttpRequest)
    {
        var xhr=new XMLHttpRequest();
    }else
    {
        var xhr=new ActiveXObject('Microsoft.XMLHttp');
    }
    xhr.onreadystatechange=function()
    {
        if(xhr.readyState==4)
        {
            if(xhr.status==200)
            {
               callback(xhr.responseText);                
            }else{
                console.log(xhr.status);
            }
        }
    }
    if(method=="POST"&&data2)
    {   
        xhr.open(method,url);
        xhr.setRequestHeader("content-type","application/x-www-form-urlencoded");
        xhr.send(data2);
    }else if(method=="GET"&&data2){
        xhr.open(method,url+"?"+data2);
        xhr.send(null);
    }else{
        xhr.open(method,url);
        xhr.send(null);
    }
}