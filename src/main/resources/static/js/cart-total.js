var goods6 = document.getElementsByTagName('body')[0];
var total=0;
var cookies6 = document.cookie.split(';');
for(var i=0;i<cookies6.length;i++){
var arr6 = cookies6[i].split('=');
if(arr6[0] === 'carlist'){
    var carList6 = JSON.parse(arr6[1]);
}
}
for(var i6=0;i6<carList6.length;i6++)
{
    total++;
}
    var ta=document.getElementById("totalid");
    ta.innerHTML=total;
    /* 点击按钮时再次更改 */
goods6.onclick = function(e6){
e6 = e6 || window.event;
var target6 = e6.target || e6.srcElement;
if(target6.tagName.toLowerCase() === 'button'){
var carList6;
var total=0;
var cookies6 = document.cookie.split(';');
for(var i=0;i<cookies6.length;i++){
var arr6 = cookies6[i].split('=');
if(arr6[0] === 'carlist'){
carList6 = JSON.parse(arr6[1]);
}
}
for(var i6=0;i6<carList6.length;i6++)
{
    total++;
}
    var ta=document.getElementById("totalid");
    ta.innerHTML=total;
}
}


for(var i6=0;i6<carList6.length;i6++)
{
    if(document.getElementById(carList6[i6].id).getElementsByClassName("price")[0]){
    document.getElementById(carList6[i6].id).getElementsByClassName("price")[0].innerHTML="已添加";
    }
    if(document.getElementById(carList6[i6].id).getElementsByClassName("article-car")[0]){
    document.getElementById(carList6[i6].id).getElementsByClassName("article-car")[0].innerHTML="已添加";
    }
}