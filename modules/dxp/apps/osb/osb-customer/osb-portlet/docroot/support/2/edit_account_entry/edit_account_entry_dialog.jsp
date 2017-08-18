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

<%@ include file="/support/2/init.jsp" %>

<%
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		<aui:script>
			parent.location.reload();
		</aui:script>
	</c:when>
	<c:when test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">

		<%
		AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		request.setAttribute("edit_account_entry_dialog.jsp-accountEntry", accountEntry);
		%>

		<portlet:actionURL name="updateAccountEntry" var="updateAccountEntryURL">
			<portlet:param name="mvcPath" value="/support/2/edit_account_entry/edit_account_entry_dialog.jsp" />
		</portlet:actionURL>

		<div class="tab-view-dialog">
			<aui:form action="<%= updateAccountEntryURL %>" method="post" name="fm">
				<input id="<portlet:namespace />modified" name="<portlet:namespace />modified" type="hidden" value="false" />
				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

				<liferay-ui:error exception="<%= AccountEntryCodeException.class %>" message="please-enter-a-valid-code" />
				<liferay-ui:error exception="<%= AccountEntryIndustryException.class %>" message="please-enter-a-valid-industry" />
				<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />
				<liferay-ui:error exception="<%= AccountEntryNameException.class %>" message="please-enter-a-valid-name" />
				<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
				<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-address-line" />
				<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
				<liferay-ui:error exception="<%= DuplicateAccountEntryException.class %>" message="please-enter-a-unique-code" />

				<div class="details" id="<portlet:namespace />editAccountDetails">
					<div class="tabs" id="<portlet:namespace />accountTabsDetails">
						<div>
							<span class="first selected" id="<portlet:namespace />accountDetails" onClick="<portlet:namespace />reveal('accountDetails');"><liferay-ui:message key="project-details" /></span>

							<span id="<portlet:namespace />addressDetails" onClick="<portlet:namespace />reveal('addressDetails');"><liferay-ui:message key="address-details" /></span>
						</div>
					</div>

					<div class="tab-content" id="<portlet:namespace />ticketTabContent">
						<liferay-util:include page="/support/2/edit_account_entry/edit_account_details.jsp" servletContext="<%= application %>" />

						<liferay-util:include page="/support/2/edit_account_entry/edit_address_details.jsp" servletContext="<%= application %>" />
					</div>
				</div>

				<div align="right">
					<input class="aui-button-input buttons" onClick="<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="save" />" />

					<input class="aui-button-input buttons fl" onClick="<portlet:namespace />closePopup();" type="button" value="<liferay-ui:message key="cancel" />" />
				</div>
			</aui:form>
		</div>
	</c:when>
	<c:when test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS) %>">

		<%
		AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		request.setAttribute("edit_account_entry_dialog.jsp-accountEntry", accountEntry);
		%>

		<portlet:actionURL name="updateAccountEntryInstructions" var="updateAccountEntryInstructionsURL">
			<portlet:param name="mvcPath" value="/support/2/edit_account_entry/edit_account_entry_dialog.jsp" />
		</portlet:actionURL>

		<div class="tab-view-dialog">
			<aui:form action="<%= updateAccountEntryInstructionsURL %>" method="post" name="fm">
				<input id="<portlet:namespace />modified" name="<portlet:namespace />modified" type="hidden" value="false" />
				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

				<div class="field-group">
					<label id="<portlet:namespace />instructionsLabel"><liferay-ui:message key="special-instructions" /></label>

					<aui:input bean="<%= accountEntry %>" label="" model="<%= AccountEntry.class %>" name="instructions" />
				</div>

				<div align="right">
					<input class="aui-button-input buttons" onClick="<portlet:namespace />submit();" type="button" value="<liferay-ui:message key="save" />" />

					<input class="aui-button-input buttons fl" onClick="<portlet:namespace />closePopup();" type="button" value="<liferay-ui:message key="cancel" />" />
				</div>
			</aui:form>
		</div>
	</c:when>
</c:choose>

<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) || OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS) %>">
	<%@ include file="/support/2/common/javascript/ticket_entry_validator_js.jspf" %>

	<aui:script use="node">
		var onChange = function(e) {
			var name = e.currentTarget.getAttribute('name');
			var label = A.one('label#' + name + 'Label');

			var labelAncestor = label.ancestor('.tab-content-tab');
			var tab = A.one('span#' + labelAncestor.getAttribute('id'));

			label.addClass('field-modified');

			var modified = '(Modified)'.bold();

			if (tab.html().indexOf('Modified') == -1) {
				tab.append(modified);
				tab.addClass('field-modified');
			}

			if (this.hasAttribute('data-field-required-status')) {
				<portlet:namespace />validateRequiredField(this);
			}

			document.getElementById('<portlet:namespace />modified').value = 'true';
		};

		A.one('#<portlet:namespace />ticketTabContent').delegate('change', onChange, 'input[type=checkbox], select');
		A.one('#<portlet:namespace />ticketTabContent').delegate('keyup', onChange, 'input[type=text], textarea');
	</aui:script>

	<aui:script>
		window.addEventListener(
			'keydown',
			function(A) {
				if (A.keyCode === 27) {
					Liferay.Util.getWindow().show();

					<portlet:namespace />closePopup();
				}
			}
		);

		function <portlet:namespace />closePopup() {
			var modified = document.getElementById('<portlet:namespace />modified');

			if (modified.value == 'true') {
				var cancelEdit = confirm('<%= UnicodeLanguageUtil.get(request, "you-have-unsaved-changes-on-this-project.-are-you-sure-you-want-to-cancel-editing") %>');

				if (cancelEdit) {
					Liferay.Util.getWindow().close();
				}
			}
			else {
				Liferay.Util.getWindow().close();
			}
		}

		function <portlet:namespace />submit() {
			var firstNode = null;
			var requiredFields = AUI().all('#<portlet:namespace />editAccountDetails input[data-field-required-status="false"], select[data-field-required-status="false"], textarea[data-field-required-status="false"]');

			if (requiredFields.size() > 0) {
				requiredFields.each(
					function(requiredField) {
						if (!<portlet:namespace />validateRequiredField(requiredField) && !firstNode) {
							firstNode = requiredField;
						}
					}
				)

				if (firstNode) {
					<portlet:namespace />reveal('editAccountDetails');

					firstNode.scrollIntoView();

					return false;
				}
			}

			submitForm(document.<portlet:namespace />fm);
		}

		Liferay.provide(
			window,
			'<portlet:namespace />reveal',
			function(id) {
				var A = AUI();

				var tab = A.one(".details .tabs #<portlet:namespace />" + id);

				if (tab) {
					A.all(".details .tab-content-tab").hide();

					var tabContent = A.one(".details .tab-content #<portlet:namespace />" + id);

					tabContent.show();

					A.all(".details .tabs span").removeClass("selected");

					tab.addClass("selected");

					Liferay.QueryStringUtil.updateParam('<portlet:namespace />detailTab', id);
				}
				else {
					<portlet:namespace />reveal('accountInformation');
				}

				window.scroll(0, 0);
			},
			['querystring-util']
		);
	</aui:script>
</c:if>