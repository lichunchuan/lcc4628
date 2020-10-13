var oCarList = document.getElementById('carList');
    var oSubPrice = oCarList.nextElementSibling;
    var btnClear = document.getElementById('btnClear');
    var carList;
    var cookies = document.cookie.split(';');
    for(var i=0;i<cookies.length;i++){
    var arr = cookies[i].split('=');
    if(arr[0] === 'carlist'){
    carList = JSON.parse(arr[1]);
    }
    }
    var subPrice = 0;
        //动态的根据cookie的内容创建列表
    if(carList){
        var tableRef = document.getElementById('mytable').getElementsByTagName('tbody')[0];
                // Insert a row in the table at the last row
        for(var i=0;i<carList.length;i++) {
            //插入一行
            var newRow = tableRef.insertRow(tableRef.rows.length);
            newRow.setAttribute('id',carList[i].id)
            //插入4列
            var cell0 = newRow.insertCell(0);
            var cell1 = newRow.insertCell(1);
            var cell2 = newRow.insertCell(2);
            var cell3 = newRow.insertCell(3);
            var cell4 = newRow.insertCell(4);
            // Append a text node to the cell
            var title = document.createTextNode(carList[i].name);
            cell0.appendChild(title);
            var price = document.createTextNode(carList[i].price);
            cell1.appendChild(price);
            var number=document.createTextNode(carList[i].qty);
            cell2.appendChild(number)
            var img = document.createElement('img');
            img.src = carList[i].imgUrl;
            cell3.appendChild(img)
            var btnClose = document.createElement('span');
            btnClose.innerHTML = '删除';
            btnClose.className = 'btn-close';
            cell4.appendChild(btnClose)
        // 计算总价
            subPrice += carList[i].price*carList[i].qty;
    //        li.appendChild(title);
    //        li.appendChild(price);
    //        li.appendChild(img);
    //        li.appendChild(btnClose);
    //        ul.appendChild(li);
        }
        // 写入页面
    //    oCarList.appendChild(ul);
        // 写入总价
        // toFixed(n)获取小数点后n位（自动四舍五入，Number类型的方法）
    oSubPrice.innerHTML = '<span class="price2">' + subPrice.toFixed(2) + '</span>';
    }
    // 删除商品
    oCarList.onclick = function(e){
    e = e || window.event;
    var target = e.target || e.srcElement;
    // 是否点击了删除按钮
    if(target.className === 'btn-close'){
    var currentLi = target.parentElement.parentElement;
    var currentGUID = currentLi.getAttribute('id');
    // 删除cookie中对应的商品
    // 根据guid取对比
    for(var i=0;i<carList.length;i++){
    // 找出要删除的商品
    if(carList[i].id === currentGUID){
    carList.splice(i,1);
    break;
    }
    }
    // 更新cookie
    document.cookie = 'carlist=' + JSON.stringify(carList);
    // 删除li节点
    currentLi.parentElement.removeChild(currentLi);
    location.reload()
    }
    }
    // 清空购物车
    // 1、删除DOM节点
    // 2、删除cookie
    btnClear.onclick = function(){
    oCarList.innerHTML = '';
    oSubPrice.innerHTML = '';
    var total=0;
    var ta=document.getElementById("totalid");
    ta.innerHTML=total;
    // 利用设置有效期位过期事件来达到删除cookie的效果
    var now = new Date();
    now.setDate(now.getDate()-7);
    document.cookie = 'carlist=xx;expires=' + now;
    alert("已购买成功！");
    }