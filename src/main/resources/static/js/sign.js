		var z_phone=0,z_psword=0;
		var phone_reg =/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var pass_reg = new RegExp("^.{6,}$", "i");
		var Phone = document.getElementById('phone');
		Phone.onclick=function()
		{
			Phone.onmouseout = function (){
    ajax2("GET","/findUserById","id="+Phone.value,function(theshy){
              buname.innerHTML=theshy;
            });
            }
  		}
		$('#phone').focus(function() {if($(this).val() == '') {
	           	$('#r_phone').html('<p style="color:#2FA7F8">请输入手机号！</p>');
	           	z_phone = 0;
	       	}}).keyup(function() {
	       	if($(this).val() == ''){
	       		$('#r_phone').html('<p style="color:#2FA7F8">请输入手机号！</p>');
	       		z_phone = 0;
	       	}else if (!phone_reg.test($(this).val())){
	       		$('#r_phone').html('<p style="color:#FE6201">您输入的手机号格式不正确！</p>');
	       		z_phone = 0;
	       	}else{
	       		$('#r_phone').html('<p style="color:#A6E217">您输入的手机号格式正确！</p>');
	       		z_phone = 1;
	       	}
	   		}).blur(function() {
	       	if($(this).val() == ''){
	       		$('#r_phone').html('<p style="color:#2FA7F8">请输入手机号！</p>');
	       		z_phone = 0;
	       	}else if (!phone_reg.test($(this).val())){
	       		$('#r_phone').html('<p style="color:#FE6201">您输入的手机号格式不正确！</p>');
	       		z_phone = 0;
	       	}else {
	       		$('#r_phone').html('<p style="color:#A6E217">您输入的手机号格式正确！</p>');
	       		z_phone = 1;
			   }});
			   
			   $('#psword').focus(function() {if($(this).val() == '') {
				$('#r_psword').html('<p style="color:#2FA7F8">请输入密码！</p>');
				z_psword = 0;
			}}).keyup(function() {
			if($(this).val() == ''){
				$('#r_psword').html('<p style="color:#2FA7F8">请输入密码！</p>');
				z_psword = 0;
			}else if (!pass_reg.test($(this).val())){
				$('#r_psword').html('<p style="color:#FE6201">您输入的密码长度格式不正确！</p>');
				z_psword = 0;
			}else {
				$('#r_psword').html('<p style="color:#A6E217">您输入的密码格式正确！</p>');
				z_psword = 1;
			}
			}).blur(function() {
			if($(this).val() == ''){
				$('#r_psword').html('<p style="color:#2FA7F8">请输入密码！</p>');
				z_psword = 0;
			}else if (!pass_reg.test($(this).val())){
				$('#r_psword').html('<p style="color:#FE6201">您输入的密码长度格式不正确！</p>');
				z_psword = 0;
			}else {
				$('#r_psword').html('<p style="color:#A6E217">您输入的密码格式正确！</p>');
				z_psword = 1;
			}});
           $("#sign").click(function () {
             if(z_phone==1 && z_psword==1){
               alert("注册成功！");
               return true;
             }else{
               alert("注册失败！");
               $('#phone').val()='';
               $('#psword').val()='';
               $('#yzm').val()='';
			   return false;
             }
		   });