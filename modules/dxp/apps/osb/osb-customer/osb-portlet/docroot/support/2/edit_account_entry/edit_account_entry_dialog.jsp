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
				<aui:input name="modified" type="hidden" value="false" />
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
					<aui:button cssClass="aui-button-input buttons" onClick='<%= renderResponse.getNamespace() + "submit();" %>' value="save" />

					<aui:button cssClass="aui-button-input buttons fl" onClick='<%= renderResponse.getNamespace() + "closePopup();" %>' value="cancel" />
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
				<aui:input name="modified" type="hidden" value="false" />
				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />

				<div class="field-group">
					<aui:input bean="<%= accountEntry %>" label="special-instructions" model="<%= AccountEntry.class %>" name="instructions" />
				</div>

				<div align="right">
					<aui:button cssClass="aui-button-input buttons" onClick='<%= renderResponse.getNamespace() + "submit();" %>' value="save" />

					<aui:button cssClass="aui-button-input buttons fl" onClick='<%= renderResponse.getNamespace() + "closePopup();" %>' value="cancel" />
				</div>
			</aui:form>
		</div>
	</c:when>
</c:choose>

<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) || OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS) %>">
	<%@ include file="/support/2/common/javascript/ticket_entry_validator_js.jspf" %>

	<aui:script>
		function <portlet:namespace />closePopup() {
			var modifiedNode = document.getElementById('<portlet:namespace />modified');

			var modified = modifiedNode && (modifiedNode.value == 'true');

			var cancelEdit = false;

			if (modified) {
				cancelEdit = confirm('<%= UnicodeLanguageUtil.get(request, "you-have-unsaved-changes-on-this-project.-are-you-sure-you-want-to-cancel-editing") %>');
			}

			if (!modified || (modified && cancelEdit)) {
				Liferay.Util.getWindow().close();
			}
		}

		Liferay.provide(
			window,
			'<portlet:namespace />reveal',
			function(id) {
				var A = AUI();

				var tab = A.one('.details .tabs #<portlet:namespace />' + id);

				if (tab) {
					A.all('.details .tab-content-tab').hide();

					var tabContent = A.one('.details .tab-content #<portlet:namespace />' + id);

					if (tabContent) {
						tabContent.show();
					}

					A.all('.details .tabs span').removeClass('selected');

					tab.addClass('selected');

					Liferay.QueryStringUtil.updateParam('<portlet:namespace />detailTab', id);
				}
				else {
					<portlet:namespace />reveal('accountInformation');
				}

				window.scroll(0, 0);
			},
			['aui-base', 'querystring-util']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />submit',
			function() {
				var A = AUI();

				var firstNode = null;

				var requiredFields = A.all('#<portlet:namespace />editAccountDetails input[data-field-required-status="false"], select[data-field-required-status="false"], textarea[data-field-required-status="false"]');

				if (requiredFields.size()) {
					requiredFields.each(
						function(requiredField) {
							if (!<portlet:namespace />validateRequiredField(requiredField) && !firstNode) {
								firstNode = requiredField;
							}
						}
					);

					if (firstNode) {
						<portlet:namespace />reveal('editAccountDetails');

						firstNode.scrollIntoView();
					}
				}

				submitForm(document.<portlet:namespace />fm);
			},
			['aui-base']
		);
	</aui:script>

	<aui:script use="aui-base">
		var onChange = function(event) {
			var name = event.currentTarget.getAttribute('name');

			var label = A.one('label#' + name + 'Label');

			if (label) {
				label.addClass('field-modified');
			}

			var labelAncestor = label.ancestor('.tab-content-tab');

			if (labelAncestor) {
				var tab = A.one('span#' + labelAncestor.getAttribute('id'));

				if (tab && (tab.html().indexOf('Modified') == -1)) {
					tab.append('(Modified)'.bold());

					tab.addClass('field-modified');
				}
			}

			if (this.hasAttribute('data-field-required-status')) {
				<portlet:namespace />validateRequiredField(this);
			}

			document.getElementById('<portlet:namespace />modified').value = 'true';
		};

		var ticketTabContent = A.one('#<portlet:namespace />ticketTabContent');

		if (ticketTabContent) {
			ticketTabContent.delegate('change', onChange, 'input[type=checkbox], select');
			ticketTabContent.delegate('keyup', onChange, 'input[type=text], textarea');
		}

		window.addEventListener(
			'keydown',
			function(A) {
				if (A.keyCode === 27) {
					Liferay.Util.getWindow().show();

					<portlet:namespace />closePopup();
				}
			}
		);
	</aui:script>
</c:if>