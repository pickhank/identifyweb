$(function(){
	FastClick.attach(document.body);
	$("#order-list-block-show").click(function(){
		if ($(this).is(".order-list-block-xl")) {
			$(this).removeClass("order-list-block-xl").addClass("order-list-block-down");
			$(".order-item.fold").removeClass("hide");
		} else {
			$(this).addClass("order-list-block-xl").removeClass("order-list-block-down");
			$(".order-item.fold").addClass("hide");
		}
	});
	
	$(".up-input").bind("input", function(){
		var v = $(this).val();
		if (v && v.length) {
			$(this).next(".del").removeClass("hide");
		} else {
			$(this).next(".del").addClass("hide");
		}
	});
	$(".op.del").click(function(){
		$(this).parent().find(".up-input").val("");
		$(this).addClass("hide");
	});
});