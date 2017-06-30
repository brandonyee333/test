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

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.fetchAssetReceipt(assetReceiptId);

long defaultPurchaseUserId = 0;

if (assetReceipt != null) {
	defaultPurchaseUserId = assetReceipt.getUserId();
}

long purchasedByUserId = ParamUtil.getLong(request, "purchasedByUserId", defaultPurchaseUserId);
String ownerClassName = BeanParamUtil.getString(assetReceipt, request, "ownerClassName", User.class.getName());
long ownerClassPK = BeanParamUtil.getLong(assetReceipt, request, "ownerClassPK");
String productClassName = BeanParamUtil.getString(assetReceipt, request, "productClassName");
long productClassPK = BeanParamUtil.getLong(assetReceipt, request, "productClassPK");

AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(productClassName, productClassPK);

String assetEntryTitle = StringPool.BLANK;

if (assetEntry != null) {
	assetEntryTitle = assetEntry.getTitle();
}
%>

<div class="edit-asset-receipt">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="new-purchase"
	/>

	<portlet:actionURL name="updateAssetReceipt" var="updateAssetReceiptURL" />

	<aui:form action="<%= updateAssetReceiptURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_admin/edit_asset_receipt.jsp" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
		<aui:input name="purchasedByUserId" type="hidden" value="<%= purchasedByUserId %>" />

		<liferay-ui:error exception="<%= AssetReceiptLegalEntityNameException.class %>" message="please-enter-a-valid-legal-entity-name" />
		<liferay-ui:error exception="<%= NoSuchCorpProjectException.class %>" message="please-select-a-valid-owner" />
		<liferay-ui:error exception="<%= NoSuchEntryException.class %>" message="please-select-a-valid-asset" />
		<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="please-select-a-valid-owner" />

		<aui:model-context bean="<%= assetReceipt %>" model="<%= AssetReceipt.class %>" />

		<aui:fieldset>
			<aui:input disabled="<%= true %>" name="purchaseBy" type="text" value="<%= PortalUtil.getUserName(purchasedByUserId, StringPool.BLANK) %>" />

			<span class="field app-input">
				<span class="field-content">
					<label class="field-label">
						<liferay-ui:message key="app" />
					</label>

					<span class="field-element">
						<aui:input name="productClassName" type="hidden" value="<%= productClassName %>" />
						<aui:input name="productClassPK" type="hidden" value="<%= productClassPK %>" />

						<input id="<portlet:namespace />assetEntryTitle" type="text" value="<%= HtmlUtil.escape(assetEntryTitle) %>" />

						<input type="button" value="<liferay-ui:message key="choose" />" />
					</span>
				</span>
			</span>

			<aui:select label="owner" name="ownerClassPK">
				<optgroup label="<liferay-ui:message key="individual" />">
					<aui:option label="<%= PortalUtil.getUserName(purchasedByUserId, StringPool.BLANK) %>" value="0" />
				</optgroup>

				<optgroup label="<liferay-ui:message key="project" />">

					<%
					List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(purchasedByUserId);

					for (CorpProject corpProject : corpProjects) {
					%>

						<aui:option label="<%= HtmlUtil.escape(corpProject.getName()) %>" value="<%= corpProject.getCorpProjectId() %>" />

					<%
					}
					%>

				</optgroup>
			</aui:select>

			<aui:input name="legalEntityName" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button onClick="<%= backURL %>" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />selectAssetEntry(entryId, productClassName, productClassPK, type, title, author, status, visible, iconURL, assetListType) {
		document.<portlet:namespace />fm.<portlet:namespace />productClassName.value = productClassName;
		document.<portlet:namespace />fm.<portlet:namespace />productClassPK.value = productClassPK;

		var A = AUI();

		var assetEntryTitle = A.one('#<portlet:namespace />assetEntryTitle');

		assetEntryTitle.setAttribute('value', title);
	}
</aui:script>

<aui:script use="aui-base">
	var selectAssetEntry = function(event) {
		event.target.blur();

		<portlet:renderURL var="selectAssetEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace_admin/select_asset_entry.jsp" />
			<portlet:param name="className" value="<%= AppEntry.class.getName() %>" />
		</portlet:renderURL>

		var assetEntryWindow = window.open('<%= selectAssetEntryURL %>', 'asset_entry', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=900');

		void('');

		assetEntryWindow.focus();
	}

	var appInputs = A.one('.osb-portlet-marketplace-admin .app-input');

	appInputs.delegate('click', selectAssetEntry, 'input');
	appInputs.delegate('focus', selectAssetEntry, 'input');
</aui:script>