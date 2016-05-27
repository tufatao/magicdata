
$(document).ready(function() {
	

	$(".preArt").each(function(i){
		var dom=$(this);
		dom.click(function(){
			preArt();
		});
	});

	$(".nextArt").each(function(i){
		var dom=$(this);
		dom.click(function(){
			nextArt();
		});
	});
	
	//透明蒙板
	function touming(dom){
		var hei = document.body.scrollHeight;
		dom.after("<div id='upper'><div id='up-img'><image src='img/loading.gif'></image></div></div>");
		
		$("#upper").height(hei);
		$("#upper").addClass("touming");
		
	}
	
	//添加透明蒙板
	$(".btn-tou").click(function(){
		var dom = $("#container");
		touming(dom);
	});
	
	//下一篇文章
	function nextArt(){
		//var str = "<br/><label class='switch switch-danger'>"
		//			+ "<input type='checkbox' class='switch-input switcher' value='${curCon.cid }' "
		//			+ "<c:if test='${1 == curCon.delFlag}'>checked</c:if> > "
		//			+ "<span class='switch-label' data-on='On' data-off='Off'></span> "
		//			+ "<span class=‘switch-handle’></span></label> ";
		$.ajax({ type: "POST",
			url: "bidAction!nextArt",
			dataType: "json",
			success: function(data){
				$(".switch-art").each(function(i){
					var dom=$(this); 
					if(data.flag){
						dom.prop('checked', 'checked');
						
					}
					else{
						dom.removeAttr('checked');
						
					}
					//判断属性是否存在 
					//if(typeof(dom.attr("checked"))=="undefined")
				});
				$("#infoCon").empty().html("<p>" + data.content + "</p>");
				$(".infoTit").empty().html(data.title);
				
		}});
	}
	
	//上一篇文章
	function preArt(){
		$.ajax({ type: "POST",
			url: "bidAction!preArt",
			dataType: "json",
			success: function(data){
				$(".switch-art").each(function(i){
					var dom=$(this); 
					if(data.flag){
						dom.prop('checked', 'checked');
						
					}
					else{
						dom.removeAttr('checked');
						
					}
				});
				$("#infoCon").empty().html("<p>" + data.content + "</p>");
				$(".infoTit").empty().html(data.title);
				
		}});
	}
	
	//关键字规则筛选
	$(".switch-key").each(function(i){
		var dom=$(this);
		var target;
		dom.click(function(){
			var cid = dom.val();
			var che = dom.attr("checked");
			target = "bidAction!validInfo?cid=" + cid;
			if("checked" == che){
				dom.removeAttr("checked");
			}
			else{
				dom.attr("checked", "checked");
			}

			$.ajax({ type: "POST",
				url: target,
				});
			
		});		
	});
	
	//用户规则筛选，
	$(".switch-cus").each(function(i){
		var dom=$(this);
		var target;
		dom.click(function(){
			var cid = dom.val();
			var che = dom.attr("checked");
			target = "bidAction!removeRelate?cid=" + cid;
			if("checked" == che){
				dom.removeAttr("checked");
			}
			else{
				dom.attr("checked", "checked");
			}

			$.ajax({ type: "POST",
				url: target,
				});
			
		});		
	});
	
	
function sub(){

   $("form").submit();
}

	
 });
 
	
 