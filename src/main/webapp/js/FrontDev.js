
$(document).ready(function() {

function sub(){

   $("form").submit();
}

	/**
	 * ɾ��ɸѡ����
	 */
   $("#showTag span").each(function(i){
var dom=$(this);
var str=dom.children("input").attr("name").substring(0,3);
if(str=="bra"){
$("#brand").hide();
}
if(str=="tjm"){
$("#tjml").hide();
}
if(str=="vth"){
$("#veri").hide();
}
   dom.click(function(){
//ȥ����Ӧѡ��
var title = dom.text();

$("#params span").each(function(){
var obj = $(this);
var tit = obj.text();
if(title == tit){
var input = obj.children("input");
input.removeAttr("checked");
}
}); 
   dom.parent().remove();
   sub();
 });  
   });

   //��ǩ���⴦��
$("#showTag dl").each(function(i){
var dom=$(this);
var len=dom.children("span").length;
if(len>0){
dom.children("b").show();
}
else{
dom.children("b").hide();
}
});

$("#params span").each(function(){
var dom = $(this);
dom.click(function(){
var input = dom.children("input");
   //alert(input.attr("value"));
   if(true == input.attr("checked")){
   input.removeAttr("checked");
   }
   else{
   input.attr("checked",true);
   }
   //�ύ
   sub();
});

});

 });
 
	
 