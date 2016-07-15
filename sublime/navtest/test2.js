$("#myTab li").click(function (e) {
	$(this).siblings(".actives").removeClass("actives");
	var classes = $(this).attr("class");
	$(this).addClass("actives");
	$("#"+classes).siblings("div").fadeOut("fast");
	$("#"+classes).fadeIn("fast");
});