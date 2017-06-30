<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

String ownerClassName = BeanParamUtil.getString(assetReceipt, request, "ownerClassName", User.class.getName());
long ownerClassPK = BeanParamUtil.getLong(assetReceipt, request, "ownerClassPK");

String ownerName = assetReceipt.getOwnerName();

AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(assetReceipt.getAssetEntryId());
%>

<portlet:actionURL name="updateAssetReceiptOwner" var="updateAssetReceiptOwnerURL">
	<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt_owner.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAssetReceiptOwnerURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="assetReceiptId" type="hidden" value="<%= assetReceiptId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="order"
	/>

	<liferay-ui:error exception="<%= DuplicateAssetReceiptException.class %>" message="the-asset-may-not-be-assigned-to-that-owner" />

	<table class="lfr-table">
	<tr>
		<td>
			<strong><liferay-ui:message key="asset" /></strong>
		</td>
		<td>
			<span id="<portlet:namespace />assetEntryTitle">
				<%= HtmlUtil.escape(assetEntry.getTitle()) %>
			</span>
		</td>
	</tr>
	<tr>
		<td>
			<strong><liferay-ui:message key="current-owner" /></strong>
		</td>
		<td>
			<%= HtmlUtil.escape(assetReceipt.getOwnerName()) %>

			<c:if test="<%= assetReceipt.isOwnerUser() && Validator.isNotNull(assetReceipt.getLegalEntityName()) %>">
				- <%= HtmlUtil.escape(assetReceipt.getLegalEntityName()) %>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td>
			<strong><liferay-ui:message key="owner-type" /></strong>
		</td>
		<td>
			<select name="<portlet:namespace />ownerClassName" onChange="<portlet:namespace />updateOwnerType(this.value);">
				<option <%= ownerClassName.equals(User.class.getName()) ? "selected" : StringPool.BLANK %> value="<%= User.class.getName() %>"><liferay-ui:message key="user" /></option>
				<option <%= ownerClassName.equals(CorpProject.class.getName()) ? "selected" : StringPool.BLANK %> value="<%= CorpProject.class.getName() %>"><liferay-ui:message key="project" /></option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<strong><liferay-ui:message key="owner" /></strong>
		</td>
		<td>
			<aui:input name="ownerClassPK" type="hidden" value="<%= ownerClassPK %>" />

			<span class="<%= ownerClassName.equals(CorpProject.class.getName()) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />corpProject">
				<input class="aui-button-input" onClick="var corpProjectWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/marketplace_admin/select_corp_project.jsp" /><portlet:param name="callback" value="selectCorpProject" /></portlet:renderURL>', 'corp_project', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900'); void(''); corpProjectWindow.focus();" type="button" value="<liferay-ui:message key="choose" />" />
			</span>

			<span class="<%= ownerClassName.equals(User.class.getName()) ? StringPool.BLANK : "aui-helper-hidden" %>" id="<portlet:namespace />user">
				<input class="aui-button-input" onClick="var userWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/marketplace_admin/select_user.jsp" /><portlet:param name="callback" value="selectUser" /></portlet:renderURL>', 'user', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900'); void(''); userWindow.focus();" type="button" value="<liferay-ui:message key="choose" />" />
			</span>

			<span id="<portlet:namespace />ownerName">
				<%= HtmlUtil.escape(ownerName) %>
			</span>
		</td>
	</tr>
	</table>

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<aui:script>
	function <portlet:namespace />selectCorpProject(corpProjectId, corpProjectName) {
		document.<portlet:namespace />fm.<portlet:namespace />ownerClassPK.value = corpProjectId;

		var ownerNameEl = document.getElementById("<portlet:namespace />ownerName");

		ownerNameEl.innerHTML = corpProjectName;
	}

	function <portlet:namespace />selectUser(userId, userName) {
		document.<portlet:namespace />fm.<portlet:namespace />ownerClassPK.value = userId;

		var ownerNameEL = document.getElementById("<portlet:namespace />ownerName");

		ownerNameEL.innerHTML = userName;
	}

	function <portlet:namespace />updateOwnerType(ownerClassName) {
		var A = AUI();

		if (ownerClassName == '<%= User.class.getName() %>') {
			A.one('#<portlet:namespace />corpProject').hide();
			A.one('#<portlet:namespace />user').show();
		}
		else {
			A.one('#<portlet:namespace />corpProject').show();
			A.one('#<portlet:namespace />user').hide();
		}

		A.one('#<portlet:namespace />ownerName').html('');
	}
</aui:script>