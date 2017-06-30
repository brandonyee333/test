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

<%@ include file="/marketplace/init.jsp" %>

<%
long[] appEntryIds = ParamUtil.getLongValues(request, "appEntryIds");

List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(user.getUserId());
%>

<div class="marketplace-server accept-contract-entry">
	<aui:form method="post" name="contractEntryFm">
		<aui:input name="appEntryIds" type="hidden" value="<%= StringUtil.merge(appEntryIds) %>" />

		<div class="portlet-msg-error aui-helper-hidden" id="<portlet:namespace />errorMessageContainer">
			<liferay-ui:message key="your-request-failed-to-complete" />
		</div>

		<ul>

			<%
			for (long appEntryId : appEntryIds) {
				AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

				AppVersion appVersion = appEntry.getApprovedAppVersion();
			%>

				<li>
					<%= appEntry.getTitle() %>

					<br />

					<%
					ContractEntry eulaContractEntry = appVersion.getContractEntry();
					%>

					<liferay-portlet:renderURL var="eulaContractEntryURL">
						<portlet:param name="mvcPath" value="/marketplace/view_contract_entry.jsp" />
						<portlet:param name="contractEntryId" value="<%= String.valueOf(eulaContractEntry.getContractEntryId()) %>" />
					</liferay-portlet:renderURL>

					<%
					Object[] arguments = {"<a href=\"" + eulaContractEntryURL.toString() + "\" target=\"_blank\">", "</a>"};
					%>

					<%= LanguageUtil.format(pageContext, "read-the-end-license-user-agreement", arguments) %>
				</li>

			<%
			}
			%>

			<li>
				<liferay-ui:message key="marketplace-terms-of-service" />

				<br />

				<%
				ContractEntry tosContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TERMS_OF_SERVICE);
				%>

				<liferay-portlet:renderURL var="tosURL">
					<portlet:param name="mvcPath" value="/marketplace/view_contract_entry.jsp" />
					<portlet:param name="contractEntryId" value="<%= String.valueOf(tosContractEntry.getContractEntryId()) %>" />
				</liferay-portlet:renderURL>

				<%
				Object[] arguments = {"<a href=\"" + tosURL.toString() + "\" target=\"_blank\">", "</a>"};
				%>

				<%= LanguageUtil.format(pageContext, "read-the-terms-of-service", arguments) %>
			</li>
		</ul>

		<aui:fieldset cssClass="agreement">
			<aui:input cssClass="accept-contract" label="i-have-read-and-agree-to-all-the-end-user-license-agreements-and-marketplace-terms-of-service" name="acceptContract" type="checkbox" />
		</aui:fieldset>

		<aui:fieldset cssClass="purchase-type">
			<aui:input name="ownerClassName" type="hidden" />

			<p>
				<liferay-ui:message key="i-am-accepting-on-the-behalf-of" />:
			</p>

			<aui:input label="myself" name="ownerClassNameRadio" type="radio" value="<%= User.class.getName() %>" />

			<aui:input label="my-company" name="ownerClassNameRadio" type="radio" value="<%= CorpProject.class.getName() %>" />

			<c:choose>
				<c:when test="<%= !corpProjects.isEmpty() %>">
					<aui:select cssClass="company-name" disabled="<%= true %>" label="company" name="ownerClassPK" showEmptyOption="<%= true %>">

						<%
						for (CorpProject corpProject : corpProjects) {
						%>

							<aui:option label="<%= HtmlUtil.escape(corpProject.getName()) %>" value="<%= corpProject.getCorpProjectId() %>" />

						<%
						}
						%>

					</aui:select>
				</c:when>
				<c:otherwise>
					<aui:input cssClass="legal-name" disabled="<%= true %>" helpMessage="please-be-sure-to-use-your-companys-full-legal-entity-name" name="legalEntityName" type="text" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>

		<div class="cleared"></div>

		<div class="fr ha-r">
			<aui:button cssClass="install-btn" disabled="<%= true %>" name="install-btn" type="submit" value="install" />
		</div>
	</aui:form>
</div>

<aui:script use="aui-alert,aui-base,aui-dialog,aui-io,json-parse">
	var form = A.one('#<portlet:namespace />contractEntryFm');

	form.on(
		'submit',
		function(event) {
			event.halt();

			var acceptContract = A.one('#<portlet:namespace />acceptContract').val();

			if (acceptContract == 'false') {
				return;
			}

			A.io.request(
				'<liferay-portlet:actionURL name="acceptContractEntries" />',
					{
						dataType: 'JSON',
						form: {
							id: form
						},
						method: 'POST',
						on: {
							success: function(event) {
								var response = this.get('responseData');

								if (response.message == 'fail') {
									A.one('#<portlet:namespace />errorMessageContainer').show();
								}
								else {
									var appEntryIdsInput = form.one('input[name=<portlet:namespace />appEntryIds]');

									var appEntryIds = appEntryIdsInput.val().split(',');

									if (appEntryIds.length == 1) {
										<portlet:namespace />updateAppEntry(appEntryIds[0]);
									}
									else {
										<portlet:namespace />updateAllAppEntries();
									}

									A.DialogManager.closeByChild(A.one('.marketplace-server.accept-contract-entry'));
								}
							}
						}
					}
			);
		}
	);

	form.delegate(
		'change',
		function(event) {
			var installButton = A.one('#<portlet:namespace />install-btn');

			var radio = A.one('.marketplace-server .purchase-type input[type=radio]:checked');

			if (!radio) {
				installButton.attr('disabled', true);

				return;
			}

			var ownerClassName = A.one('#<portlet:namespace />ownerClassName');

			var corpProject = A.one('#<portlet:namespace /><%= !corpProjects.isEmpty() ? "ownerClassPK" : "legalEntityName" %>');

			if (radio.val() == '<%= User.class.getName() %>') {
				ownerClassName.val('<%= User.class.getName() %>');

				corpProject.val('');

				corpProject.attr('disabled', true);
			}
			else if (radio.val() == '<%= CorpProject.class.getName() %>') {
				ownerClassName.val('<%= corpProjects.isEmpty() ? User.class.getName() : CorpProject.class.getName() %>');

				corpProject.removeAttribute('disabled');
			}

			if (!A.one('.marketplace-server .accept-contract input[type=checkbox]').get('checked')) {
				installButton.attr('disabled', true);

				return;
			}

			if (radio.val() == '<%= CorpProject.class.getName() %>') {
				if (corpProject.val() == '') {
					installButton.attr('disabled', true);

					return;
				}
			}

			installButton.attr('disabled', false);
		},
		'input,select'
	);
</aui:script>