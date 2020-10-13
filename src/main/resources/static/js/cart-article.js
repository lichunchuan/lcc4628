var goods3 = document.getElementsByClassName('article-index')[0];
      // 用于保存购物车商品信息
      var carList3 = [];
      // 先获取当前cookie
      var cookies3 = document.cookie.split(';');
      for(var i3=0;i3<cookies3.length;i3++){
          var arr3 = cookies3[i3].split('=');
          if(arr3[0] === 'carlist'){
              carList3 = JSON.parse(arr3[1]);
          }
      }
  // 事件委托
  goods3.onclick = function(e3){
  e3 = e3 || window.event;
          var target = e3.target || e3.srcElement;
          // 添加到购物车
          if(target.className === 'article-car'){
          // 获取当前li （Li-div-button)
          var currentLi3 = target.parentElement.parentElement.parentElement;
          var name3 =  currentLi3.getElementsByClassName("name3");
          var img3 =  currentLi3.getElementsByClassName("img3");
          var price3 =  currentLi3.getElementsByClassName("article-price");
          var currentGUID4 = currentLi3.getAttribute('id');
          // 先创建一个对象保存当前商品信息
          var goodsObj3 = {};
          goodsObj3.id = currentGUID4;
          goodsObj3.qty = 1;
          goodsObj3.name = name3[0].innerHTML;
          goodsObj3.price = price3[0].innerHTML;
          goodsObj3.imgUrl = img3[0].src;
          // 如果cookie为空，则直接添加
          if(carList3.length===0){
          // 添加到carList
          carList3.push(goodsObj3);
          }else{
          // 先判断cookie中有无相同的id商品
              for(var i3=0;i3<carList3.length;i3++){
              // 如果商品已经存在cookie中，则数量+1
                  if(carList3[i3].id === currentGUID4){
                      //carList2[i2].qty++;
                      break;
                  }
              }
      // 如果原cookie中没有当前商品
          if(i3===carList3.length){
          // 添加到carList
          carList3.push(goodsObj3);
          }
          }
          // 存入cookie
          // 把对象/数组转换诚json字符串：JSON.stringify()
          document.cookie = 'carlist=' + JSON.stringify(carList3);
           document.getElementsByClassName("article-car")[0].innerHTML="已添加";  
          }
      }