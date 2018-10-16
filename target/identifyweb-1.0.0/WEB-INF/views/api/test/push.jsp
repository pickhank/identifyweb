<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script>
	$(function() {
		bindForm();
	});
</script>
<div id="tabs-userlogin" style="overflow-y: auto;">
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">0</span>推送消息</legend> 
		<form action="<spring:url value="/service/index/push/notify"></spring:url>" method="post" class="service-form">
			<table class="editor">
				<tr>
					<td class="label">推送标题（IOS不需要传）:</td>
					<td class="field"><input type="text" name="title" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送内容:</td>
					<td class="field"><input type="text" name="description" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送分类:</td>
					<td class="field"><input type="text" name="type" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送额外消息（不显示到通知栏）:</td>
					<td class="field"><input type="text" name="extra" value="" /></td>
				</tr>
				<tr>
					<td class="label">包名（BundleId）:</td>
					<td class="field"><input type="text" name="packageName" value="" /></td>
				</tr>
				<tr>
					<td class="label">百度userId:</td>
					<td class="field">
						<input type="text" name="bdUserId" value="" />
						<br>新接口不需要此参数
					</td>
				</tr>
				<tr>
					<td class="label">百度channelId:</td>
					<td class="field"><input type="text" name="bdChannelId" value="" /></td>
				</tr>
				<tr>
					<td class="label">设备类型:</td>
					<td class="field">
						<select name="deviceType">
							<option value="IOS">IOS</option>
							<option value="ANDROID">ANDROID</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label">推送类型:</td>
					<td class="field">
						<select name="messageType">
							<option value="0">message</option>
							<option value="1">notification</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset> 
		<legend><span class="step-counter ui-widget-header ui-corner-all">1</span>推送消息(多条)</legend> 
		<form action="<spring:url value="/service/index/push/notifys"></spring:url>" method="post" class="service-form payload-data">
			<table class="editor">
				<tr>
					<td class="label">消息:</td>
					<td class="field">
						<textarea name="datas" style="width:90%;" rows="10">[{
	"title": null,
	"description": null,
	"type": null,
	"extra": null,
	"packageName": null,
	"bdUserId": null,
	"bdChannelId": null,
	"deviceType": "IOS",
	"messageType": "0"
}]</textarea>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<fieldset>
		<legend><span class="step-counter ui-widget-header ui-corner-all">2</span>推送所有消息</legend> 
		<form action="<spring:url value="/service/index/push/notifyAll"></spring:url>" method="post" class="service-form">
			<table class="editor">
				<tr>
					<td class="label">推送标题（IOS不需要传）:</td>
					<td class="field"><input type="text" name="title" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送内容:</td>
					<td class="field"><input type="text" name="description" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送分类:</td>
					<td class="field"><input type="text" name="type" value="" /></td>
				</tr>
				<tr>
					<td class="label">推送额外消息（不显示到通知栏）:</td>
					<td class="field"><input type="text" name="extra" value="" /></td>
				</tr>
				<tr>
					<td class="label">包名（BundleId）:</td>
					<td class="field"><input type="text" name="packageName" value="" /></td>
				</tr>
				<tr>
					<td class="label">设备类型:</td>
					<td class="field">
						<select name="deviceType">
							<option value="IOS">IOS</option>
							<option value="ANDROID">ANDROID</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label">推送类型:</td>
					<td class="field">
						<select name="messageType">
							<option value="0">message</option>
							<option value="1">notification</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="label" colspan='2'><div style="float: right;"><input type='submit' class="animated pulse" value="提交" />	</div></td>
				</tr>
			</table>
		</form>
	</fieldset>
</div>