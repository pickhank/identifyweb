var defset={},newfiles=[],focusVal,newselect=0,errorfiles=0,upokfiles=0,canuploads=0,currfiles=0,smanager_field,smanager_bfunc,
	dhua=function(){
		var E={ie:0,opera:0,gecko:0,webkit:0,mobile:null,air:0,caja:0},F=navigator.userAgent,D;
		if((/KHTML/).test(F)){E.webkit=1}
		D=F.match(/AppleWebKit\/([^\s]*)/);
		if(D&&D[1]){
			E.webkit=parseFloat(D[1]);
			if(/ Mobile\//.test(F)){E.mobile="Apple"}else{
				D=F.match(/NokiaN[^\/]*/);
				if(D){E.mobile=D[0]}
			}
			D=F.match(/AdobeAIR\/([^\s]*)/);
			if(D){E.air=D[0]}
		}
		if(!E.webkit){
			D=F.match(/Opera[\s\/]([^\s]*)/);
			if(D&&D[1]){
				E.opera=parseFloat(D[1]);
				D=F.match(/Opera Mini[^;]*/);
				if(D){E.mobile=D[0]}
			}else{
				D=F.match(/MSIE\s([^;]*)/);
				if(D&&D[1]){E.ie=parseFloat(D[1])}else{
					D=F.match(/Gecko\/([^\s]*)/);
					if(D){
						E.gecko=1;
						D=F.match(/rv:([^\s\)]*)/);
						if(D&&D[1]){E.gecko=parseFloat(D[1])}
					}
				}
			}
		}
		D=F.match(/Caja\/([^\s]*)/);
		if(D&&D[1]){E.caja=parseFloat(D[1])}
		return E
	}();
function trim(str){
	return str?str.replace(/^\s+|\s+$/,''):'';
}
function remark(){
	this.value=this.value.replace(/\|/g,'');
};
function checkcounts(){
	var ret=true;
	if(!canuploads){
		if(!newselect)
			ret=false;
		else{
			var tb=tbItems[1].getElementsByTagName('table')[0];
			tb.deleteRow(tb.rows.length-1);
			canuploads++;
			newselect=0;
		}
	}
	return ret;
}

function severmanager(field,func){
	smanager_field = field;
	if((smanager_bfunc = func)&&!checkcounts())return alert('\u5df2\u8fbe\u5230\u9644\u4ef6\u6700\u5927\u6570\u91cf\uff01');
	if(dhua.ie){
		var posLeft = window.event.clientX-100;
		var posTop = window.event.clientY; 
	}
	else{
		var posLeft = 100;
		var posTop = 100;
	}
	window.open(base+"include/filemanager/browser.html?Type=" + (uploadtype.charAt(uploadtype.length-1)=='s'?uploadtype.substr(0,uploadtype.length-1):uploadtype) + "&Connector=connector.php", "FileManagerWin", "scrollbars=yes,resizable=yes,statebar=no,width=600,height=400,left=" + posLeft + ", top=" + posTop);
}
function seturl(url){
	if(!remoteType(url)){
		alert('\u65e0\u6548\u7684\u9644\u4ef6\u7c7b\u578b\uff01');
	}else{
		smanager_field.value = smanager_bfunc ? smanager_bfunc.call(smanager_field,url) : url;
	}
}

function listen(dom,event,action){
	if(dom.attachEvent){
		var func=action;action=function(){func.apply(dom,arguments)};
		dom.attachEvent('on'+event,action);
	}else if(dom.addEventListener){
		dom.addEventListener(event,action,false);
	}else{
		if(!dom.listens)dom.listens=[];
		var x,e=dom.listens[event];
		if(!e){
			e=dom.listens[event]=[];
			if(dom['on'+event])e.push(dom['on'+event]);
			dom['on'+event]=function(m){
				for(var i=0,l=e.length;i<l;i++)e[i].call(dom,m);
			}
		}
		e.push(action);
	}
}
function getReturn(){
	var w, k,rmk,ply,i=0,ret=[],retId=[],retName=[];
	if(issingle){
		ply=retVal[1].play?retVal[1].play.options[retVal[1].play.selectedIndex].value:'';
		ret=retVal[1].path.value+(ply?('|'+ply):'');
		retId = retVal[1].fileid.value;
		retName = retVal[1].fileName.value;
	}else{
		for(k in retVal){
			if(++i>maxcount)break;
			rmk=retVal[k].mark.onfocus?'':retVal[k].mark.value.replace(/\|/g,'');
			ply=retVal[k].play?retVal[k].play.options[retVal[k].play.selectedIndex].value:'';
			if(ply)rmk+='|'+ply;
//			ret.push(k+'|'+retVal[k].path.value+(rmk?('|'+rmk):''));
			ret.push(retVal[k].path.value+(rmk?('|'+rmk):''));
			retId.push(retVal[k].fileid.value);
			retName.push(retVal[k].fileName.value);
		}
		if(ret.length<mincount)return alert('\u9644\u4ef6\u6570\u91cf\u4e0d\u80fd\u5c0f\u4e8e ' + mincount + ' \u4e2a\uff01');
	}
	if(opt.apply){
		try{opt(ret)}catch(e){}
	}else{
		opt.value = issingle ? ret : ret.join('\n');
		optId.value =  issingle ? retId : retId.join(',');
		if(optName) {
			optName.value =  issingle ? retName : retName.join(',');
		}
	}
	try{
		w = window.parent;
		if(win_id)w = w.CWindow.getWindow(win_id);
		w.uploadCallback && w.uploadCallback();
	}catch(e){}
	winclose();
}
function setReturn(){
	var k,ps,tmp,val=trim(opt.value), valId=trim(optId.value), valName=(optName ? trim(optName.value) : null);
	if(issingle){
		ps=val.split('|');
		addfileitem(1,ps[0],'',ps[1]?ps[1]:'',valId, valName);
	}else{
		tmp=val.split('\n');
		tmp2=valId.split(',');
		tmp3=valName.split(',');
		var idx = 0;
		for(k in tmp){
			ps=tmp[k].split('|');/*
			if(parseInt(ps[0])>=FileIndex)FileIndex=parseInt(ps[0])+1;
			addfileitem(ps[0],ps[1],ps[2]?ps[2]:'',ps[3]?ps[3]:'');*/
			addfileitem(FileIndex++,ps[0],ps[1]?ps[1]:'',ps[2]?ps[2]:'',tmp2[idx], tmp3[idx]);
			idx++;
		}
		canuploads=maxcount-filenumber;
		tbButtons[filenumber?0:1].onclick()
	}
}
function remoteType(url){
	if(url.indexOf('http://') ==0 && url.indexOf('.') == -1) {
		return 1;
	}
	if(url.lastIndexOf('.') < url.lastIndexOf(',')) {
		url = url.substr(0, url.lastIndexOf(','));
	}
	return 1;
	var i,x,type=trim(url.substr(url.lastIndexOf('.')+1)).toLowerCase();
	for(i in filelimit)if(i==type){x=1;break}
	return x;
}
function pathType(){
	this.value=trim(this.value);
	if(this.value&&!remoteType(this.value)){this.focus();alert('\u8f93\u5165\u6587\u4ef6\u7c7b\u578b\u4e0d\u7b26\u5408\uff01')}
}
function checkType(file){
	var x=remoteType(file.value);
	if(!x){
		alert('\u8bf7\u9009\u62e9...\u6587\u4ef6\u7c7b\u578b\u4e0d\u7b26\u5408\uff01');
		f=file.form;
		i=file.parentNode;
		if(f.btdelete)a=f.btdelete.onclick;
		b=f.Filedata.onchange;
		i.innerHTML=i.innerHTML;
		f.Filedata.onchange=b;
		if(a)f.btdelete.onclick=a;
	}
	return x;
}
function addfileitem(ix,aps,rmk,ply, id, filename){
	if(filenumber>=maxcount)return;
	if(!remoteType(aps=trim(aps))){
		if(issingle)aps='';else return;
	}
	if(rmk===undefined)rmk=defset.defremark.value;
	rmk=rmk?trim(rmk.replace(/\|/g,'')):'';
	if(ply===undefined)ply=defset.player?defset.player.options[defset.player.selectedIndex].value:'';
	var T,form,ret,tb=tbItems[0].getElementsByTagName('table')[0],
		dd=document.documentElement,
		tbx=tb.rows.length,
		cell=tb.insertRow(tbx).insertCell(0);
	cell.innerHTML=tb.rows[0].cells[0].innerHTML.replace(/ for=("?\w+)/g,' for=$1'+ix);
	form=cell.getElementsByTagName('form')[0];
	addplayer(form,ply,'\u8bf7\u9009\u62e9...\u64ad\u653e\u5668\uff1a');
	retVal[ix]=focusVal=ret={
		path:form.Filepath,
		fileid:form.FileId,
		fileName:form.FileName,
		mark:form.Fileremark,
		play:form.player,
		_tbx:tbx
	};
	if(uploadtype=='images'||uploadtype=='image'){
		form._img=document.createElement('img');
		form._img.onmouseover=function(){clearTimeout(T)};
		form._img.onclick=function(){window.open(this.src,'_blank')};
		form._img.width=120;
		form._img.height=90;
		form.wmid.id = form.wmid.name + ix;
		with(form._img.style){
			position='absolute';
			width='120px';
			height='90px';
			border='solid 1px #ccc';
			backgroundColor='#fff';
			cursor='pointer';
			display='none';
		}
		document.body.appendChild(form._img);

		ret.path.onmouseover=function(e){
			clearTimeout(T);
			e=e||event;
			with(form._img.style){
				left=dd.scrollLeft+e.clientX+9+'px';
				top=dd.scrollTop+e.clientY+9+'px';
			}
			var val=form.Filepath.value,err='images/default/loaderror.gif',src=form._img.src.substr(form._img.src.length-form.Filepath.value.length);
			if(!val)return;
			if(val!=src&&err!=src){
				form._img.onerror=function(){
					form._img.onerror=null;
					form._img.src=(base?base:'')+err;
				}
				form._img.src=(val.indexOf('://')!=-1?'':base?base:'')+val;
			}
			form._img.style.display='';
		};
		ret.path.onmouseout=form._img.onmouseout=function(){T=setTimeout(function(){form._img.style.display='none'},100)};
	}
	form.className='oldupload';
	form.onsubmit=function(){onefileupload(this);return false};
	form.id=ix;
	form.Filedata.id = form.Filedata.name + ix;
	ret.path.id+=ix;
	ret.fileid.id+=ix;
	ret.fileName.id+=ix;
	
	ret.mark.id+=ix;
	form.Filepath=ret.path;
	//form.FileId=ret.fileid;
	form.Filedata.onchange=function(){checkType(this)};
	ret.path.onblur=pathType;
	
	ret.path.value=aps||'';
	ret.fileid.value=id||'';
	ret.fileName.value=filename||'';
	
	form.btmanager && (form.btmanager.onclick=function(){severmanager(ret.path)});
	if(issingle){
		form.btdelete.style.display=
		ret.mark.parentNode.style.display='none';
		form.Filedata.parentNode.style.display='';
		return 1;
	}
	form.btdelete.onclick=function(){
		var k,x=retVal[ix]._tbx;
		if(form._img)document.body.removeChild(form._img);
		tb.deleteRow(x);
		delete retVal[ix];
		for(k in retVal)if(retVal[k]._tbx>x)retVal[k]._tbx--;
		filenumber--;
		if(canuploads++==0)addnewfile();
	};
	if(!rmk){
		ret.mark.style.color='#ccc';
		ret.mark.onfocus=function(){
			ret.mark.onfocus=null;
			ret.mark.value='';
			ret.mark.style.color='';
		}
	}
	ret.mark.onblur=function(){remark.apply(ret.mark)};
	ret.mark.value=rmk || '\u8bf7\u8f93\u5165\u63cf\u8ff0...';
	filenumber++;
	return 1;
}
function onefileupload(form){
	if(!form.Filedata.value)return;
	form.btsubmit.disabled=true;
	form.target='onefileupload'+form.id;
	if(dhua.ie){
		try{ // createElement:iframe, ie9之前,与其它浏览器不同; ie9后,又与ff,google类似了
			form._ifm=document.createElement('<iframe id="'+form.target+'" name="'+form.target+'"></iframe>');
		}catch(ie9){
			form._ifm=document.createElement('iframe');
			form._ifm.id=form._ifm.name=form.target;
		}
	}else{
		form._ifm=document.createElement('iframe');
		form._ifm.id=form._ifm.name=form.target;
	}
	with(form._ifm.style){
		width='0px';
		height='0px';
		display='none';
	}
	document.body.appendChild(form._ifm);
	listen(form._ifm,'load',function(){
		var val=form._ifm.contentWindow.document.body.firstChild,tmp=form.Filedata.parentNode,func=form.btdelete.onclick;
		try{
			val=val.data.split('|');
			if(val[0]!='0')throw val[0];
			form.Filepath.value=val[1].substring(0, val[1].indexOf(','));
			var fileId = val[1].substring(val[1].indexOf(',') + 1);
			form.FileId.value=fileId.substring(0, fileId.indexOf(','));
			form.FileName.value=fileId.substring(fileId.indexOf(',') + 1);
			tmp.innerHTML=tmp.innerHTML;
			form.Filedata.onchange=function(){checkType(this)};
			form.btdelete.onclick=func;
		}catch(e){
			alert(e=='-1'?'\u672a\u767b\u9646\u6216\u5df2\u8d85\u65f6\uff0c\u8bf7\u767b\u9646\u540e\u518d\u8bd5\uff01':e=='1'?'\u8d85\u51fa\u9644\u4ef6\u7a7a\u95f4\u5927\u5c0f\u6216\u4e0a\u4f20\u6743\u9650\u4e0d\u8db3\uff01':e=='2'?'\u4e0a\u4f20\u7684\u6587\u4ef6\u7c7b\u578b\u65e0\u6548\uff01':e=='3'?'\u8d85\u51fa\u8fd9\u4e2a\u7c7b\u578b\u5927\u5c0f\u9650\u5236\uff01':'\u4e0a\u4f20\u7684\u9644\u4ef6\u4e0d\u7b26\u5408\uff0c\u6ca1\u6709\u4e0a\u4f20\u6210\u529f\uff01');
		}
		form.btsubmit.disabled=false;
		document.body.removeChild(form._ifm);
	});
	form.submit();
}

function addnewfile(){
	if(canuploads==0||newselect)return;
	var form,tb=tbItems[1].getElementsByTagName('table')[0],ix=(new Date).getTime(),
		cell=tb.insertRow(tb.rows.length).insertCell(0);
	cell.innerHTML=tb.rows[0].cells[0].innerHTML.replace(/ for=("?\w+)/g,' for=$1'+ix);
	form=cell.getElementsByTagName('form')[0];
	form.className='oldupload';
	form.onsubmit=function(){return false};
	form.id = ix;
	form.Filedata.id = form.Filedata.name + ix;
	form.wmid.id = form.wmid.name + ix;
	form.Filedata.onchange=function(){
		if(!checkType(this))return;
		var i,val=form.Filedata.value,ret=form.getElementsByTagName('p');
		form._tbx=tb.rows.length-1;
		i=val.lastIndexOf('\\');
		if(i<0)i=val.lastIndexOf('/');
		if(i>0)val=val.substr(i+1);
		for(i=0;i<ret.length;i++){
			if(ret[i].id=='name')ret[i].innerHTML=ret[i].innerHTML.replace('%s',val.replace(/&/g,'&amp;').replace(/ /g,'&nbsp;'));
			else if(ret[i].id=='state')form._state=ret[i];
			ret[i].style.display=ret[i].style.display?'':'none';
		}
		form.btdelete.onclick=function(){
			var i,x=form._tbx;
			tb.deleteRow(x);
			if(canuploads++==0)addnewfile();
			currfiles--;
			for(i=0;i<newfiles.length;i++)
				if(newfiles[i]._tbx==x){
					newfiles.splice(i--,1);
				}else if(newfiles[i]._tbx>x){
					newfiles[i]._tbx--;
				}
			$$('nomUpload').disabled = !newfiles.length;
		};
		newfiles.push(form);
		newselect=0;
		$$('nomUpload').disabled = !newfiles.length;
		currfiles++;
		addnewfile()
	};
	newselect=1;
	canuploads--;
}
function newfileupload(btsubmit){
	function reset(){
		if(form){
			newfiles.unshift(form);
			form.btdelete.disabled=false;
		}
		if(newfiles.length)$$('nomUpload').disabled=false;
		upokfiles=0;
		errorfiles=0;
	}
	var form=newfiles.shift();if(!form){
		if(upokfiles)tbButtons[0].onclick();
		if(errorfiles)alert(errorfiles+'\u4e2a\u6587\u4ef6\u4e0d\u7b26\u5408\uff0c\u6ca1\u6709\u4e0a\u4f20\u6210\u529f\uff01');
		reset();
		return
	}
	if(btsubmit)btsubmit.disabled=true;
	form.btdelete.disabled=true;
	form._state.innerHTML='\u4e0a\u4f20\u6b63\u5728\u8fdb\u884c'+'...';
	form.target='newfileupload'+form.id;
	if(dhua.ie){
		try{ // ie9bug
			form._ifm=document.createElement('<iframe id="'+form.target+'" name="'+form.target+'"></iframe>');
		}catch(ie9bug){
			form._ifm=document.createElement('iframe');
			form._ifm.id=form._ifm.name=form.target;
		}
	}else{
		form._ifm=document.createElement('iframe');
		form._ifm.id=form._ifm.name=form.target;
	}
	with(form._ifm.style){
		width='0px';
		height='0px';
		display='none';
	}
	document.body.appendChild(form._ifm);
	listen(form._ifm,'load',function(){
		var i,val=form._ifm.contentWindow.document.body.firstChild;
		document.body.removeChild(form._ifm);
		currfiles--;
		try{
			val=val.data.split('|');
			var vals = val[1].split(',');
			if(val[0]!='0')throw val[0];
			i=$$('nomStatus');
			i.innerHTML=parseInt(i.innerHTML)+1;
			addfileitem(FileIndex++,vals[0], null, null, vals[1], vals[2]);
			upokfiles++;
		}catch(e){
			if(e=='-1'||e=='1'){
				reset();
				alert(e=='1'?'\u8d85\u51fa\u9644\u4ef6\u7a7a\u95f4\u5927\u5c0f\u6216\u4e0a\u4f20\u6743\u9650\u4e0d\u8db3\uff01':'\u672a\u767b\u9646\u6216\u5df2\u8d85\u65f6\uff0c\u8bf7\u767b\u9646\u540e\u518d\u8bd5\uff01');
				return;
			}
			if(canuploads++==0)addnewfile();
			errorfiles++;
		}
		tbItems[1].getElementsByTagName('table')[0].deleteRow(1);
		for(i=0;i<newfiles.length;i++)newfiles[i]._tbx--;
		newfileupload();
	});
	form.submit();
}

function canzipupload(zip){
	if(!checkcounts()){
		var form=tbItems[3].getElementsByTagName('form')[0],tmp=form.Filedata.parentNode,func=form.Filedata.onchange;
		tmp.innerHTML=tmp.innerHTML;
		form.Filedata.onchange=func;
		alert('\u5df2\u8fbe\u5230\u9644\u4ef6\u6700\u5927\u6570\u91cf\uff01');
	}else{
		currfiles=1;
	}
}
function delzipupload(del){
	var tmp=del.parentNode;
	tmp.innerHTML=tmp.innerHTML;
	currfiles=0;
}
function zipfileupload(btsubmit){
	var form=tbItems[3].getElementsByTagName('form')[0];
	if(!form.Filedata.value)return;
	btsubmit.disabled=true;
	form.target='zipfileupload';
	if(dhua.ie){
		form._ifm=document.createElement('<iframe id="'+form.target+'" name="'+form.target+'"></iframe>');
	}else{
		form._ifm=document.createElement('iframe');
		form._ifm.id=form._ifm.name=form.target;
	}
	with(form._ifm.style){
		width='0px';
		height='0px';
		display='none';
	}
	document.body.appendChild(form._ifm);
	listen(form._ifm,'load',function(){
		var i,val=form._ifm.contentWindow.document.body.firstChild,tmp=form.Filedata.parentNode,func=form.Filedata.onchange;
		currfiles=0;
		if(val){
			try{
				val=val.data.split('|');
				if(val[0]!='0')throw val[0];
				for(i=2;i<val.length;i++){
					if(canuploads>0){
						canuploads--;
					}else if(newselect){
						canuploads--;
						newselect=0;
					}else{
						break;
					}
					addfileitem(FileIndex++,val[i]);
				}
				val.length>2 && tbButtons[0].onclick();
				i=parseInt(val[1])-i+2;
				if(canuploads<0){
					var tb=tbItems[1].getElementsByTagName('table')[0];
					tb.deleteRow(tb.rows.length-1);
					canuploads=0;
				}
				alert('\u538b\u7f29\u5305\u5168\u90e8'+' '+val[1]+' '+'\u4e2a\u6587\u4ef6\uff0c'+(i?(i+' \u4e2a\u6587\u4ef6\u4e0d\u7b26\u5408\u6ca1\u6709\u4fdd\u5b58(\u5305\u62ec\u8d85\u51fa\u7a7a\u95f4\u5927\u5c0f\u6ca1\u6709\u4fdd\u5b58)\uff01'):'\u5168\u90e8\u4fdd\u5b58\u6210\u529f\uff01'));
			}catch(e){
				alert(e=='-1'?'\u672a\u767b\u9646\u6216\u5df2\u8d85\u65f6\uff0c\u8bf7\u767b\u9646\u540e\u518d\u8bd5\uff01':e=='-2'?'ZIP\u4e0d\u5305\u542b\u6709\u6548\u7684\u9644\u4ef6\u6216\u4e0d\u662f\u4e00\u4e2a\u652f\u6301\u7684ZIP\u5305\uff01':((e=='1'?'\u670d\u52a1\u5668\u4e0a\u4f20\u9519\u8bef':'\u4e0a\u4f20\u7684\u4e34\u65f6\u6587\u4ef6\u65e0\u6cd5\u8bfb\u53d6')+'\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\uff01'));
			}
		}else{
			alert('\u4e0a\u4f20\u7684\u4e0d\u662f\u53ef\u652f\u6301\u7684\u538b\u7f29\u5305\uff01');
		}
		btsubmit.disabled=false;
		tmp.innerHTML=tmp.innerHTML;
		form.Filedata.onchange=func;
		document.body.removeChild(form._ifm);
	});
	form.submit();
}

function makeString(form){
	if(!canuploads)return alert('\u5df2\u8fbe\u5230\u9644\u4ef6\u6700\u5927\u6570\u91cf\uff01');
	var s,rmk,ret='',rmtpath=trim(form.rmtpath.value),url=trim(form.subrmt.value),
		rmks=trim(form.subrmk.value),ply=defset.player?defset.player.options[defset.player.selectedIndex].value:'',
		from=trim(form.subfrom.value),to=trim(form.subto.value),length=parseInt(trim(form.subnum.value)),word='0';
	if(isNaN(length))length=0;
	if(url.indexOf('(?)')<0||!/^\d+$/.test(from)||!/^\d+$/.test(to)||from==to)return alert('\u6279\u91cf\u5730\u5740\u89c4\u5219\u8f93\u5165\u6709\u8bef');
	rmtpath+=(rmtpath?'\n':'');
	url=url.split('(?)');
	if(rmks)rmks=trim(rmks.replace(/\|/g,'')).split('(?)');else rmks=[trim(defset.defremark.value.replace(/\|/g,''))];
	from=parseInt(from);to=parseInt(to);
	s=from<to?1:-1;to+=s;
	while(word.length<length)word+=word;
	for(;from!=to;from+=s){
		ret+='\n'+url[0]+(length?word.substr(0,length-from.toString().length):'')+from+url[1];
		rmk=rmks[1]?(rmks[0]+from+rmks[1]):rmks[0];
		if(ply)rmk+='|'+ply;
		if(rmk)ret+='|'+rmk;
	}
	form.rmtpath.value=rmtpath+ret.substr(1);
	setTimeout(function(){form.rmtpath.scrollTop=form.rmtpath.scrollHeight},50);
}
function managerfunc(url){
	var ret=trim(this.value),rmk=trim(defset.defremark.value.replace(/\|/g,'')),ply=defset.player?defset.player.options[defset.player.selectedIndex].value:'';
	if(ply)rmk+='|'+ply;
	if(rmk)url+='|'+rmk;
	return ret+(ret?'\n':'')+url;
}
function addrmtfiles(){
	var tmp,add=0,bad=0,rmt=tbItems[4].getElementsByTagName('form')[0].rmtpath,val=trim(rmt.value);
	if(val){
		val=val.split('\n');
		for(i=0;i<val.length;i++){
			if(checkcounts()){
				if(canuploads)canuploads--;
			}else{
				bad+=val.length-i;
				break;
			}
			tmp=val[i].split('|');
			if(addfileitem(FileIndex++,tmp[0],tmp[1]?tmp[1]:'',tmp[2]?tmp[2]:0))add++;else bad++;
		}
		rmt.value='';
	}
	if(add||!bad)tbButtons[0].onclick();
	if(bad)alert(bad+' '+'\u4e2a\u6587\u4ef6\u4e0d\u7b26\u5408\u6ca1\u6709\u6dfb\u52a0\uff01');
}
function addplayer(form,val,txt){
	if(players.length){
		var i,idx=0,p=document.createElement('p'),slc=document.createElement('select');
		for(i=0;i<players.length;i++){
			if(players[i][0]==val)idx=i;
			slc.options[i]=new Option(players[i][1],players[i][0]);
		}
		slc.id='player';
		slc.selectedIndex=idx;
		p.appendChild(document.createTextNode(txt));
		p.appendChild(slc);
		form.appendChild(p);
	}
}
function winclose(){
	try{opt.focus()}catch(e){}
	try{window.close();}catch(e){}
	try{var w=window.parent;w.floatwin('close_' + win_id,0,0,0,0,0,1);w.$id('floatwin_' + win_id + '_content').innerHTML=''}catch(e){}
}

function fileDialogStart(){
	if(newselect){
		var tb=tbItems[1].getElementsByTagName('table')[0];
		tb.deleteRow(tb.rows.length-1);
		canuploads++;
		newselect=0;
	}
	var limit = currfiles + canuploads;
	this.setFileUploadLimit(limit>0?limit:1);
}

function Cookie(key, value, expires, path, domain, secure){
	key = encodeURIComponent(key);
	var t = expires, r = (new RegExp('(?:^|;)\\s*' + key + '=(.*?)(?:;|$)')).exec(document.cookie), e, f;
	if(value !== undefined){
		if(t && !(t instanceof Date)){
			e = t;t = new Date();
			if(value === null){
				value = '';
			}else{
				e = /^([+-]?)(\d+)([YMWDHIS]?)$/i.exec(e) || [,,0];
				e[3] && (e[3] = e[3].toUpperCase());
				e[3] == 'W' && (e[3] = 'D') && (e[2] *= 7);
				f = {Y : 'FullYear', M : 'Month', D : 'Date', H : 'Hours', I : 'Minutes', S : 'Seconds'}[e[3] || 'I'];
				eval('t.set' + f + '(t.get' + f + '()' + (e[1] || '+') + e[2] + ')')
			}
		}
		document.cookie = key + '=' + encodeURIComponent(value)
						+ (t ? ';expires=' + t.toGMTString() : '')
						+ '; path=' + (typeof path != 'string' ? '/' : path)
						+ (domain ? '; domain=' + domain : '')
						+ (secure ? '; secure' : '')
	}
	return r ? decodeURIComponent(r[1]) : r
}

SWFUpload.onload = function () {
	var i,l,currTable,oldTable,filetype='';
	for(i in filelimit){
		l=filelimit[i];
		l[0]*=1024;
		l[1]*=1024;
		filetype+=';*.'+i;
	}
	filetype=filetype.substr(1);
	var settings = {
		flash_url : swfurl,
		upload_url: swfuurl,
		post_params: $params,
		file_post_name: "Filedata",
		file_types : filetype,
		file_types_description : '\u5141\u8bb8\u7c7b\u578b',
		file_upload_limit : maxcount,
		custom_settings : {
			progressTarget : "fsUploadProgress",
			selectButtonId : 'swfSelect',
			uploadButtonId : 'swfUpload',
			cancelButtonId : "swfCancel"
		},
		debug: false,

		// Button Settings
		button_placeholder_id : "spanButtonPlaceholder",
		button_width: 60,
		button_height: 22,
		button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
//		button_cursor: SWFUpload.CURSOR.HAND,

		// The event handler functions are defined in handlers.js
		swfupload_loaded_handler : swfUploadLoaded,
		file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		file_dialog_start_handler : fileDialogStart,
		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete,
		queue_complete_handler : queueComplete,	// Queue plugin event
		
		// SWFObject settings
		minimum_flash_version : "9.0.28",
		swfupload_pre_load_handler : swfUploadPreLoad,
		swfupload_load_failed_handler : swfUploadLoadFailed
	},tmp;

	swfu = new SWFUpload(settings);

	tbButtons=$$('selectbutton').childNodes;
	tbItems=[
		$$('divOLDUploadUI'),
		$$('divNOMUploadUI'),
		$$('divSWFUploadUI'),
		$$('divZIPUploadUI'),
		$$('divRMTUploadUI'),
		$$('divSETUploadUI')
	];
	if(!canbrowser){
		tmp = $$('divOLDUploadUI').getElementsByTagName('form')[0].btmanager;
		tmp && (tmp.style.display='none');
		tmp = $$('divRMTUploadUI').getElementsByTagName('form')[0].btmanager;
		tmp && (tmp.style.display='none');
	}
	defset=tbItems[5].getElementsByTagName('form')[0];
	addplayer(defset,0,'\u9ed8\u8ba4\u64ad\u653e\u5668\uff1a');
	tbButtons[0].className='selectitem1';
	for(i=0,l=2;i<l;i++)!function(i){
		if(issingle){
			if(i){
				tbButtons[i].style.display='none';
			}else{
				tbButtons[i].className='selecteditem';
				tbItems[i].style.display='';
			}
			return;
		}
		tbButtons[i].href='javascript:;';
		tbButtons[i].onfocus=function(){tbButtons[i].blur()};
		tbButtons[i].onclick=function(){
			if(currTable===i)return;
			if(currTable>0&&currTable<4&&currfiles){
				alert('\u8bf7\u4e0a\u4f20\u540e\u518d\u505a\u5176\u5b83\u64cd\u4f5c\uff01');
				return;
			}
			if(i&&i<5&&!checkcounts()&&oldTable!==i){
				alert('\u5df2\u8fbe\u5230\u9644\u4ef6\u6700\u5927\u6570\u91cf\uff01');
				return;
			}
			if(i==1){
				var tb=tbItems[1].getElementsByTagName('table')[0];
				if(!newselect){
					newfiles=[];
					addnewfile();
				}
			}
			if(currTable||currTable===0){
				var o=tbButtons[currTable];
				setTimeout(function(){o.className=''},20);
				tbItems[currTable].style.display='none';
				if(!i)oldTable=currTable;
			}
			currTable=i;
			this.className='selecteditem';
			tbItems[i].style.display='';
			if(i==0&&focusVal)focusVal.mark.focus();
		};
		tbButtons[i].onmouseover=function(){
			if(currTable!=i){
				var o=tbButtons[currTable];
				o.className='';
				this.onmouseout=function(){
					this.onmouseout=null;
					if(currTable!=i)o.className='selecteditem';
				};
			}
		};
	}(i);
	if(canupload){
		$$('loading').style.display='none';
		$$('selectbutton').style.display='';
		setReturn();
	}else{
		$$('loading').innerHTML='\u8bf7\u767b\u9646\u540e\u518d\u4e0a\u4f20\uff01<a href="javascript:;" onclick="winclose()">[\u5173\u95ed]</a>';
	}
}
