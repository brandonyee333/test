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

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings();

if (ecDocumentEntryId > 0) {
	ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

	ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);
}

boolean resale = ParamUtil.getBoolean(request, "resale", ecDocumentEntryExtraSettings.isResale());
boolean trial = ParamUtil.getBoolean(request, "trial");
%>

<div class="purchase-app-entry-project">
	<portlet:actionURL name="purchaseAppEntryProject" var="purchaseAppEntryProjectURL" />

	<aui:form action="<%= purchaseAppEntryProjectURL %>" method="post" name="fm">
		<aui:input name="<%= mvcPathParam %>" type="hidden" value="/marketplace/purchase_app_entry.jsp" />
		<aui:input name="ecDocumentEntryId" type="hidden" value="<%= ecDocumentEntryId %>" />
		<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />
		<aui:input name="ownerClassName" type="hidden" value="<%= ecDocumentEntryExtraSettings.getOwnerClassName() %>" />
		<aui:input name="ownerClassPK" type="hidden" value="<%= String.valueOf(ecDocumentEntryExtraSettings.getOwnerClassPK()) %>" />
		<aui:input name="countryId" type="hidden" value="<%= storeCountryId %>" />
		<aui:input name="trial" type="hidden" value="<%= trial %>" />

		<liferay-ui:error exception="<%= AssetReceiptOwnerClassNameException.class %>" message="please-select-who-will-purchase-this-app" />
		<liferay-ui:error exception="<%= AssetReceiptOwnerClassNameException.class %>" message="please-select-who-will-purchase-this-app" />
		<liferay-ui:error exception="<%= ExpiredAppPricingItemException.class %>" message="were-sorry-the-apps-pricing-was-updated-please-try-again" />
		<liferay-ui:error exception="<%= NoSuchCorpProjectException.class %>" message="please-select-a-valid-project" />
		<liferay-ui:error exception="<%= TrialLicenseException.class %>" message="trial-licenses-are-unavailable-for-this-account-because-you-may-have-previously-request-one-or-you-may-have-already-purchased-a-valid-license-for-this-app" />

		<div class="select-owner">
			<h3>
				<liferay-ui:message key="select-a-project-for-this-purchase" />
			</h3>

			<span class="aui-field-help">
				<liferay-ui:message key="select-a-project-below-or-create-a-new-project-to-associate-your-licenses-apps-designated-for-personal-use-only-cannot-be-shared-and-will-not-be-accessible-by-other-people" />
			</span>

			<c:if test="<%= PortletPropsValues.DEVELOPER_MODE_ENABLED && !appEntry.isFree() %>">
				<aui:input cssClass="resale-checkbox" label="select-if-you-are-a-reseller-purchasing-on-behalf-of-an-end-user" name="resale" type="checkbox" value="<%= resale %>" />
			</c:if>

			<div class="card-container">

				<%
				List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(user.getUserId());

				for (CorpProject corpProject : corpProjects) {
					if (!OSBCorpProjectPermission.contains(permissionChecker, corpProject, OSBActionKeys.PURCHASE_ASSET)) {
						continue;
					}
				%>

					<div class="card project" data-ownerClassName="<%= CorpProject.class.getName() %>" data-ownerClassPK="<%= corpProject.getCorpProjectId() %>">
						<div class="card-body">
							<div class="name">
								<%= HtmlUtil.escape(corpProject.getName()) %>
							</div>

							<div class="owner">
								<%= StringUtil.shorten(HtmlUtil.escape(PortalUtil.getUserName(corpProject)), 20, StringPool.BLANK) %>
							</div>

							<liferay-util:include page="/marketplace/purchase_app_entry/project_licenses.jsp" servletContext="<%= application %>">
								<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
								<liferay-util:param name="ownerClassName" value="<%= CorpProject.class.getName() %>" />
								<liferay-util:param name="ownerClassPK" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
							</liferay-util:include>
						</div>

						<div class="card-action">
							<a class="btn" href="javascript:;"><liferay-ui:message key="select" /></a>
						</div>
					</div>

				<%
				}
				%>

				<div class="card new-project">
					<div class="card-body">
						<div class="name">
							<liferay-ui:message key="add-a-new-project" />
						</div>
					</div>
				</div>

				<div class="cleared"></div>

				<c:if test="<%= appEntry.isFree() || !ArrayUtil.contains(PortletPropsValues.MARKETPLACE_PROJECTS_ONLY_PURCHASE_COUNTRIES, storeCountry.getA2()) %>">
					<div class="card user<%= resale ? " disabled" : StringPool.BLANK %>" data-ownerClassName="<%= User.class.getName() %>" data-ownerClassPK="<%= themeDisplay.getUserId() %>">
						<div class="card-body">
							<div class="name">
								<liferay-ui:message key="for-personal-use-only" />
							</div>

							<div class="owner">
								<%= StringUtil.shorten(HtmlUtil.escape(user.getFullName()), 25, StringPool.BLANK) %>
							</div>

							<liferay-util:include page="/marketplace/purchase_app_entry/project_licenses.jsp" servletContext="<%= application %>">
								<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
								<liferay-util:param name="ownerClassName" value="<%= User.class.getName() %>" />
								<liferay-util:param name="ownerClassPK" value="<%= String.valueOf(user.getUserId()) %>" />
							</liferay-util:include>
						</div>

						<c:if test="<%= !resale %>">
							<div class="card-action">
								<a class="btn" href="javascript:;"><liferay-ui:message key="select" /></a>
							</div>
						</c:if>
					</div>

					<div class="cleared"></div>
				</c:if>
			</div>
		</div>

		<c:if test="<%= trial || appEntry.isFree() %>">

			<%
			String addressCompanyName = StringPool.BLANK;

			String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();
			%>

			<%@ include file="/marketplace/purchase_app_entry/contract_entries.jspf" %>
		</c:if>

		<c:if test="<%= trial %>">
			<div>
				<liferay-ui:message key="by-clicking-purchase-the-developer-may-follow-up-with-you-regarding-your-trial-experience" />
			</div>
		</c:if>

		<aui:button-row>
			<portlet:renderURL var="viewAppEntryURL">
				<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view_app_entry.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			</portlet:renderURL>

			<c:choose>
				<c:when test="<%= ecDocumentEntryId > 0 %>">
					<portlet:actionURL name="cancelPurchaseAppEntry" var="cancelPurchaseAppEntryURL">
						<portlet:param name="redirect" value="<%= viewAppEntryURL %>" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
					</portlet:actionURL>

					<aui:button href="<%= cancelPurchaseAppEntryURL %>" value="cancel" />
				</c:when>
				<c:otherwise>
					<a class="btn" href="<%= viewAppEntryURL %>"><liferay-ui:message key="cancel" /></a>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="<%= trial || appEntry.isFree() %>">
					<aui:button type="submit" value="purchase" />
				</c:when>
				<c:otherwise>
					<aui:button type="submit" value="next" />
				</c:otherwise>
			</c:choose>
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base,aui-dialog,aui-io">
	<c:if test="<%= PortletPropsValues.DEVELOPER_MODE_ENABLED && !appEntry.isFree() %>">
		A.one('.resale-checkbox .aui-field-input-choice').on(
			'change',
			function(event) {
				var width = 480;

				var target = event.target;

				if (!target.attr('checked')) {
					<liferay-portlet:renderURL var="projectURL">
						<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
						<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
						<portlet:param name="resale" value="<%= String.valueOf(false) %>" />
						<portlet:param name="purchaseStep" value="project" />
					</liferay-portlet:renderURL>

					window.location = '<%= projectURL %>';

					return;
				}

				<liferay-portlet:renderURL var="viewResaleOptionURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="<%= mvcPathParam %>" value="/marketplace/view_resale_project_options.jsp" />
					<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
					<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-portlet:renderURL>

				var dialog = new A.Dialog(
					{
						destroyOnClose: true,
						draggable: true,
						height: 400,
						modal: true,
						resizable: false,
						stack: true,
						title: '<liferay-ui:message key="resale-options" unicode="<%= true %>" />',
						width: width,
						x: (document.documentElement.clientWidth / 2) - (width / 2),
						y: 100
					}
				).plug(
					A.Plugin.IO,
					{
						uri: '<%= viewResaleOptionURL %>'
					}
				).render();
			}
		);
	</c:if>

	var cardContainer = A.one('.marketplace .card-container');

	var card = cardContainer.one('.card[data-ownerClassName=<%= ecDocumentEntryExtraSettings.getOwnerClassName() %>][data-ownerClassPK=<%= ecDocumentEntryExtraSettings.getOwnerClassPK() %>]');

	if (card && !card.hasClass('disabled')) {
		card.addClass('selected');
	}

	cardContainer.delegate(
		'click',
		function(event) {
			var target = event.target;

			if (!target.hasClass('btn') && !target.hasClass('card-body') && !target.ancestor('.card-body')) {
				return;
			}

			var card = event.currentTarget;

			if (card.hasClass('disabled')) {
				return;
			}

			cardContainer.all('.card').removeClass('selected');

			card.addClass('selected');

			document.<portlet:namespace />fm.<portlet:namespace />ownerClassName.value = card.getAttribute('data-ownerClassName');
			document.<portlet:namespace />fm.<portlet:namespace />ownerClassPK.value = card.getAttribute('data-ownerClassPK');

			<c:if test="<%= trial || appEntry.isFree() %>">
				var legalEntityName = A.one('.marketplace .legal-entity-name');

				if (card.hasClass('user')) {
					legalEntityName.hide();
				}
				else {
					legalEntityName.show();
				}
			</c:if>
		},
		'.card'
	);

	cardContainer.one('.new-project').on(
		'click',
		function(event) {
			var width = 435;

			var popup = new A.Dialog(
				{
					destroyOnClose: true,
					draggable: true,
					modal: true,
					resizable: false,
					stack: true,
					title: '<liferay-ui:message key="new-project" />',
					width: width,
					x: document.documentElement.clientWidth/2 - width/2,
					y: 300
				}
			).plug(
				A.Plugin.IO,
				{
					data: {
						<portlet:namespace />redirect: '<liferay-portlet:renderURL><portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /><portlet:param name="trial" value="<%= String.valueOf(trial) %>" /></liferay-portlet:renderURL>'
					},
					uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="<%= mvcPathParam %>" value="/marketplace/edit_corp_project.jsp" /></liferay-portlet:renderURL>'
				}
			).render();
		}
	);
</aui:script>