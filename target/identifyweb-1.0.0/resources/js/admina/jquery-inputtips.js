// JavaScript Document
(function($){
	$.fn.extend({
		"changeTips":function(opts){
			opts = $.extend({
				url:"",
				data:{},
				idDiv:""
			}, opts)
			var xhr = null;
			var $this = $(this);
			var tipTop = $this.offset().top + $this.height();
			var tipLeft = $this.offset().left;
			var tipWidth = $this.width();
			var $tipDiv = $("<ul>").addClass("ajax_tip_div").css({
				width:tipWidth + "px",
				position:"absolute",
				top:tipTop + "px",
				left:tipLeft + "px",
				"list-style":"none",
				background:"#FFF",
				border:"1px solid #000",
				display:"none",
				height:"200px",
				"overflow-y":"auto",
				"overflow-x":"hide"
			});
			var $loadingEl = $("<b>").css({
				position:"fixed",
				top: (tipTop - 20) + "px",
				left:(tipLeft + tipWidth - 20) + "px",
				width: "15px",
				height: "15px",
				"z-index": 99,
				display:"none"//,
				//background:"url('data:img/jpg;base64,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') no-repeat"
			});
			$this.after($tipDiv).before($loadingEl);
			$("head").append("<style type='text/css'>li.inputdrop{margin:3px;} li.active{ background:#CEE7FF;} li.hover{ background:#CEE7FF;}</style>");
			var indexLi = 0;
			//点击document隐藏下拉层
			$(document).click(function(event) {
				blus();
			});
			//鼠标点击LI
			var handleLiClick = function(event) {
				var liVal = $(event.target).text();
				var liId = $(event.target).attr('data');
				$this.val(liVal);
				$(opts.idDiv).val(liId);
			};
			
			//鼠标悬停LI
			var handleLiHover = function(){
				indexLi = $(this).index();//获取当前鼠标悬停时的LI索引值;
				$(this).addClass("active").siblings().removeClass("active");
			};
			
			//隐藏下拉层
			function blus(){
				$tipDiv.hide();
				if (xhr) xhr.abort();
				$loadingEl.hide();
			}
			function show() {
				var tipTop = $this.offset().top + $this.height() + 5;
				var tipLeft = $this.offset().left;
				var tipWidth = $this.width();
				$tipDiv.css({
					width:tipWidth + "px",
					top:tipTop + "px",
					left:tipLeft + "px"
				}).show();
			}
			function showloading() {
				var tipTop = $this.offset().top + $this.height();
				var tipLeft = $this.offset().left;
				var tipWidth = $this.width();
				$loadingEl.css({
					top: (tipTop - 20) + "px",
					left:(tipLeft + tipWidth - 20) + "px"
				}).show();
			}
			//键盘上下执行的函数
			function keychang(up){
				if(up == "up"){
					if(indexLi == 0) {
						indexLi = $tipDiv.children().length - 1;
					} else {
						indexLi--;
					}
				}else{
					if(indexLi == $tipDiv.children().length - 1) {
						indexLi = 0;
					} else {
						indexLi++;
					}
				}
				$tipDiv.children().eq(indexLi).addClass("active").siblings().removeClass("active");
			}
			
			//值发生改变时
			function valChange(){
				var tex = $this.val();//输入框的值
				//让提示层显示，并对里面的LI遍历
				if(tex.length >= 2){
					if (xhr) {
						xhr.abort();
						$loadingEl.hide();
					}
					showloading();
					var d = $.extend(opts.data, {keywords: tex});
					xhr = jQuery.ajax({
						url:opts.url,
						type:"POST",
						dataType:"JSON",
						data: d,
						success:function(data) {
							console.log(data);
							$tipDiv.empty();
							var hasValue = false;
							$.each(data, function(index, item){
								var liEl = $("<li>").addClass("inputdrop").attr('data', item.id).append(item.name);
								$tipDiv.append(liEl);
								hasValue = true;
							});
							console.log(hasValue);
							$tipDiv.children().click(handleLiClick).hover(handleLiHover);
							hasValue && show();
							indexLi = -1;
							$loadingEl.hide();
						}
						
					});
				}else{
					blus();
				}
			}
			
			//输入框值发生改变的时候执行函数，这里的事件用判断处理浏览器兼容性;
			
			if(false && $.browser.msie){
				$(this).bind("propertychange",function(){
					valChange();
				})
			} else {
				$(this).bind("input",function(){
					valChange();
				})
			}
		
			//按键盘的上下移动LI的背景色
			$this.keydown(function(event){
				if(event.which == 38){//向上
					keychang("up")
				}else if(event.which == 40){//向下
					keychang()
				}else if(event.which == 13){ //回车
					if (indexLi != -1) {
						var liVal = $tipDiv.children().eq(indexLi).text();
						var liId = $tipDiv.children().eq(indexLi).attr('data');
						$this.val(liVal);
						$(opts.idDiv).val(liId);
					}
					blus();
				}
			})
		}
	})
})(jQuery)