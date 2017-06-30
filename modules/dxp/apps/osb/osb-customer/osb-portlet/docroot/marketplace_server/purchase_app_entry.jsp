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
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

int compatibility = ParamUtil.getInteger(request, "compatibility");

AppPackage appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED);

ContractEntry eulaContractEntry = appVersion.getContractEntry();
ContractEntry tosContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(user.getUserId());
%>

<div class="marketplace-server purchase-app-entry">
	<div class="callout-b-head">
		<div class="callout-content">
			<h1 class="title">
				<liferay-ui:message key="agreement" />
			</h1>
		</div>
	</div>

	<div class="callout-e pseudo-portlet-content">
		<portlet:actionURL name="purchaseAppEntryProject" var="purchaseAppEntryProjectURL" />

		<aui:form action="<%= purchaseAppEntryProjectURL %>" method="post" name="fm">
			<aui:input name="mvcPath" type="hidden" value="/marketplace_server/purchase_app_entry.jsp" />

			<liferay-portlet:renderURL var="viewPurchasedURL">
				<portlet:param name="mvcPath" value="/marketplace_server/view_purchased.jsp" />
				<portlet:param name="updateAppEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<aui:input name="redirect" type="hidden" value="<%= viewPurchasedURL %>" />

			<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

			<liferay-ui:error exception="<%= AssetReceiptOwnerClassNameException.class %>" message="please-select-who-will-purchase-this-app" />
			<liferay-ui:error exception="<%= NoSuchContractEntryException.class %>" message="please-accept-the-end-user-license-agreement-and-terms-of-service" />
			<liferay-ui:error exception="<%= NoSuchCorpProjectException.class %>" message="please-select-a-valid-company" />
			<liferay-ui:error exception="<%= TrialLicenseException.class %>" message="trial-licenses-are-unavailable-for-this-account-because-you-may-have-previously-request-one-or-you-may-have-already-purchased-a-valid-license-for-this-app" />

			<div class="purchase-form form">
				<div class="asset aui-w33">
					<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
						<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
						<liferay-util:param name="classPK" value="<%= String.valueOf(appEntryId) %>" />
						<liferay-util:param name="displayStyle" value="5" />
						<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
					</liferay-util:include>
				</div>

				<aui:fieldset cssClass="agreement">

					<%
					PortletURL eulaURL = renderResponse.createRenderURL();

					eulaURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
					eulaURL.setParameter("contractEntryId", String.valueOf(eulaContractEntry.getContractEntryId()));
					eulaURL.setWindowState(LiferayWindowState.EXCLUSIVE);

					PortletURL tosURL = renderResponse.createRenderURL();

					tosURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
					tosURL.setParameter("contractEntryId", String.valueOf(tosContractEntry.getContractEntryId()));
					tosURL.setWindowState(LiferayWindowState.EXCLUSIVE);

					Object[] arguments = {"<a href=\"" + eulaURL.toString() + "\" target=\"_blank\">", "</a>", "<a href=\"" + tosURL.toString() + "\" target=\"_blank\">", "</a>"};

					String taglibContractEntriesLabel = LanguageUtil.format(pageContext, "i-have-read-and-agree-to-the-end-user-license-agreement-and-the-terms-of-service", arguments);
					%>

					<aui:input cssClass="accept-contract" label="<%= taglibContractEntriesLabel %>" name="acceptContract" type="checkbox" />
				</aui:fieldset>

				<aui:fieldset cssClass="purchase-type">
					<aui:input name="ownerClassName" type="hidden" />

					<p>
						<liferay-ui:message key="i-am-accepting-on-the-behalf-of" />:
					</p>

					<aui:input label="myself" name="ownerClassNameRadio" type="radio" value="0" />

					<aui:input label="my-company" name="ownerClassNameRadio" type="radio" value="1" />

					<c:choose>
						<c:when test="<%= !corpProjects.isEmpty() %>">
							<aui:select cssClass="company-name" disabled="<%= true %>" label="company" name="ownerClassPK">
								<aui:option />

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
					<a class="install-btn btn"><liferay-ui:message key="install" /></a>
				</div>
			</div>
		</aui:form>
	</div>
</div>

<aui:script use="aui-dialog,aui-io,liferay-dynamic-select">
	var toggleLegalEntity = function() {
		var radio = A.one('.marketplace .purchase-type input[type=radio]:checked');

		if (!radio) {
			return;
		}

		var ownerClassName = A.one('#<portlet:namespace />ownerClassName');

		var corpProject = A.one('#<portlet:namespace /><%= !corpProjects.isEmpty() ? "ownerClassPK" : "legalEntityName" %>');

		if (radio.val() == 0) {
			ownerClassName.val('<%= User.class.getName() %>');

			corpProject.attr('disabled', true);

			<%= corpProjects.isEmpty() ? "corpProject.val('');" : "corpProject.val(0);" %>
		}
		else if (radio.val() == 1) {
			ownerClassName.val('<%= corpProjects.isEmpty() ? User.class.getName() : CorpProject.class.getName() %>');

			corpProject.removeAttribute('disabled');
		}
	};

	A.one('.marketplace .purchase-type').delegate('change', toggleLegalEntity, 'input[type=radio]');

	toggleLegalEntity();

	A.all('.accept-contract a').on(
		'click',
		function(event) {
			event.preventDefault();

			var node = event.currentTarget;

			var dialogWidth = 600;

			var dialogX = (A.getBody().width() - dialogWidth) / 2;
			var dialogY = 0;

			var dialog = new A.Dialog(
				{
					destroyOnClose: true,
					draggable: false,
					height: 375,
					resizable: false,
					title: node.text(),
					width: dialogWidth,
					xy: [dialogX, dialogY]
				}
			).plug(
				A.Plugin.IO,
				{
					uri: node.attr('href')
				}
			).render();
		}
	);

	A.one('.install-btn').on(
		'click',
		function(event) {
			<c:choose>
				<c:when test="<%= appPackage.isPortalRestartRequired() %>">
					if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-install-this-app" unicode="<%= true %>" />')) {
						submitForm(document.<portlet:namespace />fm);
					}
				</c:when>
				<c:otherwise>
					submitForm(document.<portlet:namespace />fm);
				</c:otherwise>
			</c:choose>
		}
	);
</aui:script>