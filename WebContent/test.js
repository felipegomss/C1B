$(document).ready(function(){
    $("#btnPopUp").click(function(){
        $(".tela-agendar").css("display", "flex");
    });
 });

$(document).ready(function(){
	$(".ntfcc").click(function(){
        $(".popUp-notificacao").css("display", "flex");
    });
 });

 $(document).ready(function(){
    $(".buttonAgend").click(function(){
        $(".tela-agendar").css("display", "none");
    });
 });
 
 $(document).ready(function(){
	    $("#cancelarCorte").click(function(){
	        $(".popUp-notificacao").css("display", "none");
	    });
	 });

 $(document).ready(function(){
    $("buttonCancel").click(function(){
        $(".tela-agendar").css("display", "none");
    });
 });
 
 $(document).ready(function(){
	    $("#fecharTela").click(function(){
	        $(".popUp-notificacao").css("display", "none");
	    });
	 });
