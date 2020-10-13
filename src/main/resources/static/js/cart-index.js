var goods2 = document.getElementsByClassName('liebiao')[0];
      // 用于保存购物车商品信息
      var carList2 = [];
      // 先获取当前cookie
      var cookies2 = document.cookie.split(';');
      for(var i2=0;i2<cookies2.length;i2++){
          var arr2 = cookies2[i2].split('=');
          if(arr2[0] === 'carlist'){
              carList2 = JSON.parse(arr2[1]);
          }
      }
  // 事件委托
  goods2.onclick = function(e2){
  e2 = e2 || window.event;
          var target = e2.target || e2.srcElement;
          // 添加到购物车
          if(target.tagName.toLowerCase() === 'button'){
          // 获取当前li （Li-div-button)
          var currentLi2 = target.parentElement.parentElement;
          var name2 =  currentLi2.getElementsByClassName("name2");
          var img2 =  currentLi2.getElementsByClassName("img2");
          var price2 =  currentLi2.getElementsByClassName("price");
          var currentGUID2 = currentLi2.getAttribute('id');
          // 先创建一个对象保存当前商品信息
          var goodsObj2 = {};
          goodsObj2.id = currentGUID2;
          goodsObj2.qty = 1;
          goodsObj2.name = name2[0].innerHTML;
          goodsObj2.price = price2[0].innerHTML;
          goodsObj2.imgUrl = img2[0].src;
          // 如果cookie为空，则直接添加
          if(carList2.length===0){
          // 添加到carList
          carList2.push(goodsObj2);
          }else{
          // 先判断cookie中有无相同的id商品
              for(var i2=0;i2<carList2.length;i2++){
              // 如果商品已经存在cookie中，则数量+1
                  if(carList2[i2].id === currentGUID2){
                      //carList2[i2].qty++;
                      break;
                  }
              }
      // 如果原cookie中没有当前商品
          if(i2===carList2.length){
          // 添加到carList
          carList2.push(goodsObj2);
          }
          }
          // 存入cookie
          // 把对象/数组转换诚json字符串：JSON.stringify()
          document.cookie = 'carlist=' + JSON.stringify(carList2);
          }
          price2[0].innerHTML="已添加";
      }