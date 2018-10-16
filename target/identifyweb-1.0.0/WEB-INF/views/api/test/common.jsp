<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script>
	$(function() {
		bindForm();
	});
</script>
<div id="tabs-common" class="" style="overflow-y: auto;">
	<fieldset> 
	    <legend><span class="step-counter ui-widget-header ui-corner-all">1</span>附件上传</legend> 
        <form action="<spring:url value="/common/upload/index"></spring:url>"  method="post" class="binary-form" enctype="multipart/form-data" >
			<table class="editor">
				<tr>
					<td class="label">选择文件: </td>
					<td class="field"><input type="file" name="filedata" />
						<br><font color="red">注意这个接口的地址跟其他的有区别，不需要前缀/service/index</font>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="shake" value="上传" /></div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
	    <legend><span class="step-counter ui-widget-header ui-corner-all">2</span>版本检查更新</legend> 
        <form action="<spring:url value="/service/index/common/checkVersion"></spring:url>" method="post" class="service-form" >
			<table class="editor">
				<tr>
					<td class="label">客户端类型: </td>
					<td class="field">
						<input type="radio"  style="width: 40px;" name="clientType" checked="checked" id="clientTypeIos" value="iOS" /> <label for="clientTypeIos" >iOS</label> &nbsp;
						<input type="radio"  style="width: 40px;" name="clientType" id="clientTypeAndroid" value="Android" /> <label for="clientTypeAndroid" >Android</label> &nbsp;
					</td>
				</tr>
				<tr>
					<td class="label">客户端版本: </td>
					<td class="field">
						<input type="text" name="clientVersion" value="1.0.0" />
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="shake" value="提交" /></div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>