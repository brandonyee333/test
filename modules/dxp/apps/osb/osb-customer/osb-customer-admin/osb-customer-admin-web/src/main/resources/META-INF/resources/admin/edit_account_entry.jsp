<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute(CustomerAdminWebKeys.ACCOUNT_ENTRY);

String detailTab = ParamUtil.getString(request, "detailTab");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

	backURL = portletURL.toString();
}

long accountEntryId = BeanParamUtil.getLong(accountEntry, request, "accountEntryId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry.jsp");
portletURL.setParameter("detailTab", detailTab);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<portlet:actionURL name="updateAccountEntry" var="updateAccountEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAccountEntryURL %>" enctype="multipart/form-data" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="project"
	/>

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

	<liferay-ui:error exception="<%= AccountEntryLanguageIdException.class %>" message="please-select-valid-support-languages" />
	<liferay-ui:error exception="<%= AccountEntrySupportRegionException.class %>" message="please-select-valid-support-regions" />

	<c:if test='<%= SessionMessages.contains(renderRequest, "lcsSyncFailed") %>'>
		<div class="portlet-msg-alert">
			<liferay-ui:message key="there-was-an-error-syncing-with-lcs" />
		</div>
	</c:if>

	<aui:model-context bean="<%= accountEntry %>" model="<%= AccountEntry.class %>" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="koroneiki-account-key" />
			</td>
			<td>
				<aui:input label="" name="koroneikiAccountKey" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="dossiera-account-key" />
			</td>
			<td>
				<aui:input label="" name="dossieraAccountKey" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="project-name" />
			</td>
			<td>
				<%= HtmlUtil.escape(accountEntry.getName()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="code" />
			</td>
			<td>
				<%= HtmlUtil.escape(accountEntry.getCode()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="parent-account" />
			</td>
			<td>
				parent account here
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="lcs-support-admin" />
			</td>
			<td>
				<span id="<portlet:namespace />lcsSupportAdmin">
					<c:if test="<%= accountEntry.getCorpProjectId() > 0 %>">
						<aui:a href="<%= CustomerAdminWebConfigurationValues.LCS_SUPPORT_ADMIN_LINK + accountEntry.getCorpProjectId() %>" label="lcs-support-admin" target="_blank" />
					</c:if>
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="partner" />
			</td>
			<td>
				partner here
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="partner-first-line-support" />
			</td>
			<td>
				partner fls here
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="description" />
			</td>
			<td>
				<%= HtmlUtil.escape(accountEntry.getDescription()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="additional-notes" />
			</td>
			<td>
				notes here
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="tier" />
			</td>
			<td>
				<%= HtmlUtil.escape(accountEntry.getTier()) %>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="addresses" />
			</td>
			<td>
				<aui:row>
				</aui:row>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="special-instructions" />
			</td>
			<td>
				<aui:input label="" name="instructions" />
			</td>
		</tr>

		<c:if test="<%= accountEntry != null %>">
			<tr>
				<td>
					<liferay-ui:message key="oem-instructions" />
				</td>
				<td>

					<%
					List<AccountAttachment> accountAttachments = AccountAttachmentLocalServiceUtil.getAccountAttachments(accountEntryId, 0, AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

					AccountAttachment accountAttachment = null;

					if (!accountAttachments.isEmpty()) {
						accountAttachment = accountAttachments.get(0);

						LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

						accountAttachmentURL.setCopyCurrentRenderParameters(false);
						accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
						accountAttachmentURL.setResourceID("accountAttachment");
					%>

						<div id="<portlet:namespace />accountAttachment">
							<aui:input name="accountAttachmentId" type="hidden" value="<%= accountAttachment.getAccountAttachmentId() %>" />
							<aui:input name="deleteAccountAttachment" type="hidden" />

							<aui:a href="<%= accountAttachmentURL.toString() %>" label="<%= accountAttachment.getFileName() %>" target="_blank" />

							<aui:button onClick='<%= renderResponse.getNamespace() + "removeAccountAttachment();" %>' value="remove" />
						</div>

					<%
					}
					%>

					<aui:input id="accountAttachmentField" inputCssClass='<%= (accountAttachment != null) ? "hide" : "" %>' label="" name="accountAttachment" type="file" />
				</td>
			</tr>
		</c:if>
	</table>

	<br />

	<div>
		<aui:button onClick='<%= "submitForm(document." + renderResponse.getNamespace() + "fm);" %>' value="save" />

		<c:if test="<%= accountEntry != null %>">
			<c:if test="<%= accountEntry.getCorpProjectId() > 0 %>">
				<portlet:actionURL name="syncToLCS" var="syncToLCSURL">
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
				</portlet:actionURL>

				<aui:a cssClass="btn btn-default" href="<%= syncToLCSURL %>" label="sync-to-lcs" />
			</c:if>

			<portlet:actionURL name="syncToZendesk" var="syncToZendeskURL">
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
			</portlet:actionURL>

			<aui:a cssClass="btn btn-default" href="<%= syncToZendeskURL %>" label="sync-to-zendesk" />
		</c:if>

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>

	<br />

	<%
	request.setAttribute("edit_account_entry.jsp-detailTab", detailTab);
	request.setAttribute("edit_account_entry.jsp-portletURL", portletURL);
	%>

	<liferay-util:include page="/admin/edit_account_entry/details_tabs.jsp" servletContext="<%= application %>" />
</aui:form>

<aui:script>
	function <portlet:namespace />addColumn(row, html) {
		var cell = row.insertCell(-1);

		cell.innerHTML = html;
	}

	function <portlet:namespace />removeAccountAttachment() {
		var A = AUI();

		document.<portlet:namespace />fm.<portlet:namespace />deleteAccountAttachment.value = true;

		A.one('#<portlet:namespace />accountAttachment').hide();
		A.one('#<portlet:namespace />accountAttachmentField').show();
	}

	function <portlet:namespace />removeRow(inputName, value, tableId, row) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		values = values.replace(value + ',', '');

		document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

		var table = document.getElementById(tableId).getElementsByTagName('tbody')[0];

		table.removeChild(row.parentNode.parentNode);
	}

	function <portlet:namespace />selectRow(inputName, value, tableId, columnValues) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		if (values.indexOf(value + ',') == -1) {
			values += value + ',';

			document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

			var table = document.getElementById(tableId);

			table.parentNode.parentNode.className = 'lfr-search-container';

			var tBody = table.getElementsByTagName('tbody')[0];

			var row = tBody.insertRow(-1);

			row.className = 'results-row';

			for (var i = 0; i < columnValues.length; i++) {
				<portlet:namespace />addColumn(row, columnValues[i]);
			}

			<portlet:namespace />addColumn(row, '<input class="btn btn-default" onClick="<portlet:namespace />removeRow(\'' + inputName + '\', \'' + value + '\', \'' + tableId + '\', this);" type="button" value="<liferay-ui:message key="remove" />" />');
		}
	}

	function <portlet:namespace />selectSupportRegion(supportRegionId, supportRegionName) {
		<portlet:namespace />selectRow('supportRegionIds', supportRegionId, '<portlet:namespace />supportRegionSearchContainer', [supportRegionName]);
	}
</aui:script>