                var z_phone=0,z_psword=0;
                /* var z_user=0; */
                /* var user_reg = new RegExp("^[\\d\\.a-z_A-Z]{4,20}|[\u4e00-\u9fa5]$", "i"); */
		        var phone_reg =/^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
                var pass_reg = new RegExp("^.{6,}$", "i");
                /* $('#user').focus(function() {if($(this).val() == '') {
                           $('#r_name').html('<p style="color:#2FA7F8">请输入用户名！</p>');
                           z_user = 0;
                       }}).keyup(function() {
                       if($(this).val() == ''){
                           $('#r_name').html('<p style="color:#2FA7F8">请输入用户名！</p>');
                           z_user = 0;
                       }else if (!user_reg.test($(this).val())){
                           $('#r_name').html('<p style="color:#FE6201">您输入的用户名格式不正确！</p>');
                           z_user = 0;
                       }else{
                           $('#r_name').html('<p style="color:#A6E217">您输入的用户名格式正确！</p>');
                           z_user = 1;
                       }
                       }).blur(function() {
                       if($(this).val() == ''){
                           $('#r_name').html('<p style="color:#2FA7F8">请输入用户名！</p>');
                           z_user = 0;
                       }else if (!user_reg.test($(this).val())){
                           $('#r_name').html('<p style="color:#FE6201">您输入的用户名格式不正确！</p>');
                           z_user = 0;
                       }else {
                           $('#r_name').html('<p style="color:#A6E217">您输入的用户名格式正确！</p>');
                           z_user = 1;
                       }}); */

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
                   $("#login").click(function () {
                     if(z_phone==1 && z_psword==1){
                       alert("登录成功！");
                       return true;
                     }else{
                       alert("登录失败！");
                       $('#phone').val()='';
                       $('#psword').val()='';
                       $('#yzm').val()='';
                       return false;
                     }
                   });
                    /* function login(){
                        var sUserName = $('#user').val();
                        var sPassword = $('#psword').val();
                        if (z_user == 1 && z_psword == 1) {
                            $.ajax({     
                                url:'*******',     
                                type:'post',     
                                data:{
                                    'sUserName':sUserName,
                                    'sPassword':sPassword
                                },
                                async : false, //默认为true 异步     
                                dataType: "json",
                                error:function(){     
                                    layer.msg('error');     
                                },
                                success: function (d, s, r) {
                                    if(d){
                                        if(d.status == 0){
                                            layer.msg(d.message,{icon: 2});	
                                        }else{
                                            var url = "****";
                                            window.location.href = url;
                                        }
                                    }
                                }
                            });
                        }
                    } */