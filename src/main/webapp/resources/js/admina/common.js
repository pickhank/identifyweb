String.prototype.endsWith = function(pattern) {
    var d = this.length - pattern.length;
    return d >= 0 && this.lastIndexOf(pattern) === d;
};
var common = {
	enableBatchMode: function(formSelector, actionTypeSelector, targetActionTypeSelector) {
		actionTypeSelector = actionTypeSelector ? actionTypeSelector : ".batchfields input[name=actionType]:checked";
		targetActionTypeSelector = targetActionTypeSelector ? targetActionTypeSelector : "input[name=actionType]";
		var formObj = $(formSelector);
		var allVals = "";
	     $(actionTypeSelector).each(function() {
	    	 allVals += "," + $(this).val();
	     });
	     if(allVals != '') {
	    	 allVals = allVals.substring(1);
	     }
		formObj.find(targetActionTypeSelector).val(allVals);
		
		return formObj.submit();
	},
	linkageDroplist: function(obj, linkagedName, needRights, dicType) {
		var linkagedObj = $("select[name='"+linkagedName+"']");
		linkagedObj.empty();
		if(linkagedObj.attr('defaultVal')) {
			linkagedObj.append('<option value="">' + linkagedObj.attr('defaultVal') + '</option>').attr("selected","selected");
		}
		var parentCode = $(obj).val();
		parentCode = $.isArray(parentCode) ? parentCode.join(',') : parentCode;
		
		var data = {
			parentCode: parentCode
		};

		if(needRights) {
			data[needRights] = needRights;
			data[dicType] = dicType;
		}
		if(parentCode != '') {
			$.ajax({
				method: 'GET',
				url:  $('#glabolBaseUrl').val() + "/common/loadDics",
				dataType: 'json',
				data: data,
				success: function(json) {
					if(json && json.payload && json.payload.length) {
						for(var i=0; i<json.payload.length; i++) {
							linkagedObj.append('<option value="'+json.payload[i].code+'">'+json.payload[i].name+'</option>');
						}
					}
				}
			});
		}
	},
	unfoldMenuByUrl: function(url) {
		$('.menu-item[href]').each(function() {
			var that = $(this);
			var href = that.attr('href');
			if(url.endsWith(href)) {
				that.addClass('btnok');
				
				var nav1 = null, nav2 = null, nav3 = null;
				if(that.hasClass('menu-action')) {
					nav3 = that;
					var leftMenuId = that.closest('div[parentmenuId]').attr('parentmenuId');
					nav2 = $("a[rel='operate_"+leftMenuId+"']");
					var menuId = nav2.closest('div[parentmenuId]').attr('parentmenuId');
					nav1 = $('#mainmenu_'+menuId);
				}
				else {
					nav2 = that;
					var menuId = that.closest('ul[parentmenuId]').attr('parentmenuId');
					nav1 = $('#mainmenu_'+menuId);
				}
				
				// choose current menu
				nav1.closest('li').addClass('s1');
				var leftmenu = $("ul[id='submenus_" + nav1.attr('rel') + "']");
				leftmenu.show();
				
				// show left menu
				var leftMenuContainer = $('#leftmenu');
				leftMenuContainer.removeClass('col2').removeClass('col1');
				$('#operateitem').hide();
				var operateObj = $("div[id='" + nav2.attr('rel') + "']");
				if(operateObj.length > 0) {
					leftMenuContainer.addClass('col1');
					$('#operateitem').html(operateObj.html());
					$('#operateitem').show();
				}
				else {
					leftMenuContainer.addClass('col2');
				}
				
				// Select correct button
				if(nav3) {
					nav3.closest('li').addClass('btnok');
				}
				$("#main").attr('src', url);
			}
		});
	},
	
	bindAjaxForm: function(formSelector, callback, beforeSubmit) {
		$(formSelector).submit(function() {
			var formObj = $(this);
			if(!beforeSubmit || beforeSubmit(formObj)) {
				$.ajax({
					method: formObj.attr('method'),
					url:  formObj.attr('action'),
					dataType: 'json',
					data: formObj.serialize(),
					success: callback
				});
				return false;
			}
		});
	},
	
	bindToParentColorbox: function(selector, opts) {
		$(selector).click(function() {
			var linkObj = $(this);
			var href = opts && opts.href ? opts.href : linkObj.attr('href');
			window.parent && window.parent.$.colorbox({
				href: href,
				
			});
			return false;
		});
	},
	reloadIframePage: function() {
		$("#main").attr('src', $("#main").attr('src'));
	},
	doExportToExcel: function(obj, targetUrl) {
		targetUrl = targetUrl ? targetUrl : 'exportToExcel';
		
		var formObj = $(obj).parents('form');
		
		var newFormObj = $('<form action="' + targetUrl + '" method="GET"></form>');
		newFormObj.appendTo($('body'));
		
		$.each(formObj.serializeArray(), function(i, field) {
		    var fieldObj = $('<input type="hidden" name="' + field.name + '">');
		    fieldObj.val(field.value);
		    newFormObj.append(fieldObj);
		});
		newFormObj.submit();
	}
};
$(function() {
	var headerNav = 75;
	$(window).resize(function() {
		$('#leftmenu').css('height', $(window).height() - 42 - headerNav + "px");
		$('#main').attr('height', $(window).height() - 42 - headerNav + "px");
	});
	$(window).resize();
	$('.collapse-header-btn').click(function() {
		var that = $(this);
		var headerObj = $('#header');
		if(headerObj.is(':visible')) {
			headerObj.slideUp('fast', function() {
				that[0].style.backgroundPosition = '0 0';
				headerNav = 0;
				$('#leftmenu').css('height', $(window).height() - 42 - headerNav + "px");
				$('#main').attr('height', $(window).height() - 42 - headerNav + "px");
			});
		}
		else {
			headerObj.slideDown('fast', function() {
				headerNav = 75;
				that[0].style.backgroundPosition = '-35px 0';
				$('#leftmenu').css('height', $(window).height() - 42 - headerNav + "px");
				$('#main').attr('height', $(window).height() - 42 - headerNav + "px");
			});
		}
	});
	
	$('#topmenu li a').click(function() {
		$('#leftmenu > ul').hide();
		$('#topmenu li.s1').removeClass('s1');
		var that = $(this);
		that.closest('li').addClass('s1');
		var leftmenu = $("ul[id='submenus_" + that.attr('rel') + "']");
		leftmenu.show();
		var defaultBtn = leftmenu.find('a').eq(0);
		defaultBtn.click();
		if(defaultBtn.attr('href') && defaultBtn.attr('href').indexOf('/') == 0) {
			$('#main').attr('src', defaultBtn.attr('href'));
		}
	});
	
	$('#leftmenu a').click(function() {
		var that = $(this);
		var leftMenu = $('#leftmenu');
		leftMenu.find('.dian0').removeClass('dian0');
		leftMenu.find('.dj0').removeClass('dj0');
		leftMenu.removeClass('col2').removeClass('col1');
		$('#operateitem').hide();
		that.closest('ul').removeClass('treeMenu');
		var operateObj = $("div[id='" + that.attr('rel') + "']");

		that.closest('em').addClass('dj0');
		that.closest('li').addClass('dian0');
		
		if(operateObj.length > 0) {
			leftMenu.addClass('col1');
			var showItemObj = $('#operateitem');
			showItemObj.html(operateObj.html());
			showItemObj.show();
			showItemObj.find('a').click(function() {
				$('#operateitem li.btnok').removeClass('btnok');
				$(this).closest('li').addClass('btnok');
			});
			var defaultBtnObj = showItemObj.find('a').eq(0).click();
			if(defaultBtnObj.attr('href') && defaultBtnObj.attr('href').indexOf('/') == 0) {
				$('#main').attr('src', defaultBtnObj.attr('href'));
			}
			
			that.closest('ul').addClass('treeMenu');
		}
		else {
			leftMenu.addClass('col2');
		}
	});
	
	$('#topmenu li a').eq(0).click();
});
