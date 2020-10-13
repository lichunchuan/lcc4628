var z_searchf=0,z_searchb=0;
		var oSearch = new RegExp("^[\\d\\.a-z_A-Z]{4,20}|[\u4e00-\u9fa5]$", "i");
    $('#search').focus(function() {if($(this).val() == '') {
	           	z_search = 0;
	       	}}).keyup(function() {
	       	if($(this).val() == ''){
	       		z_searchf = 0;
	       	}else if (!oSearch.test($(this).val())){
	       		z_searchf = 0;
	       	}else{
	       		z_searchf = 1;
	       	}
	   		}).blur(function() {
	       	if($(this).val() == ''){
	       		z_searchb = 0;
	       	}else if (!oSearch.test($(this).val())){
	       		z_searchb = 0;
	       	}else {
	       		z_searchb = 1;
	       	}});
           $('#search').click(function(){
           if(z_searchf==1&&z_searchb==1){
             return true;
           }else{
             alert("格式不正确，请重新输入！");
             return false;
           }})