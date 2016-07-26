$("#myTab li").click(function (e) {
	$(this).siblings(".actives").removeClass("actives");
	var id = $(this).attr("id");
	$(this).addClass("actives");
	/*$("#"+classes).siblings("div").fadeOut("fast");
	$("#"+classes).fadeIn("fast");*/
});

$("#b1").click(function(){
	$("#p1").html("变变变！！！")
});