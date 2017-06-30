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

<%@ include file="/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getActionableAppVersion();

boolean firstSubmission = appEntry.isFirstSubmission();
%>

<div class="marketplace-developer-apps edit-app-license">
	<liferay-ui:header
		title="license"
	/>

	<liferay-util:include page="/marketplace_developer_apps/app_breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
	</liferay-util:include>

	<portlet:actionURL name="updateAssetLicenses" var="updateAssetLicensesURL" />

	<aui:form action="<%= updateAssetLicensesURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveAssetLicenses();" %>'>
		<aui:input name="mvcPath" type="hidden" value="/marketplace_developer_apps/edit_app_license.jsp" />

		<portlet:renderURL var="editAppPricingURL">
			<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_pricing.jsp" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= editAppPricingURL %>" />

		<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

		<liferay-ui:error exception="<%= AppEntryLicenseLifetimeException.class %>" message="please-select-a-license-term" />
		<liferay-ui:error exception="<%= AssetLicenseLicenseTypeAllotmentException.class %>" message="please-enter-a-valid-quantity" />
		<liferay-ui:error exception="<%= MaximumAssetLicenseException.class %>" message="please-reduce-the-number-of-licenses-per-category-to-10-or-less" />
		<liferay-ui:error exception="<%= MinimumAssetLicenseException.class %>" message="please-add-one-or-more-non-trial-licenses" />
		<liferay-ui:error exception="<%= RequiredAssetLicenseException.class %>" message="for-each-set-of-licenses-you-would-like-to-provide-please-add-a-1-instance-license-option" />

		<aui:fieldset cssClass="create-license-field">
			<div class="license-lifetime container">
				<span class="aui-field-label">
					<liferay-ui:message key="license-term" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="a-perpetual-license-is-permitted-to-run-without-expiration" />
				</span>

				<span class="aui-field-help">

					<%
					String taglibDevFaq = "<a href=\"/web/developer/marketplace/faq\" target=\"_blank\">";
					%>

					<liferay-ui:message arguments='<%= new String[] {taglibDevFaq, "</a>"} %>' key="if-you-are-a-developer-residing-outside-of-the-united-states-non-perpetual-license-sales-are-subject-to-a-30-withholding-to-the-us-government" />
				</span>

				<div class="aui-field-input-container">
					<aui:input checked="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>" disabled="<%= !firstSubmission %>" label="perpetual" name="licenseLifetime" type="radio" value="<%= AssetLicenseConstants.LIFETIME_PERPETUAL %>" />
					<aui:input checked="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>" disabled="<%= !firstSubmission %>" label="non-perpetual" name="licenseLifetime" type="radio" value="<%= AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>" />
				</div>
			</div>

			<div class="support container">
				<span class="aui-field-label">
					<liferay-ui:message key="subscription-services" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="if-subscription-services-are-offered-for-your-app-customers-can-only-receive-support-maintenance-and-updates-during-the-paid-subscription-period" />

					<ul>
						<li>
							<liferay-ui:message key="perpetual-license" />: <liferay-ui:message key="if-you-opt-to-offer-subscription-services-you-will-be-asked-to-price-it-on-a-per-instance-unit-per-year-basis" />
						</li>
						<li>
							<liferay-ui:message key="non-perpetual-license" />: <liferay-ui:message key="if-you-opt-to-offer-subscription-services-the-price-of-this-added-service-should-be-built-into-the-per-instance-unit-per-year-price-of-the-non-perpetual-license" />
						</li>
					</ul>
				</span>

				<div class="aui-field-input-container">
					<aui:input checked="<%= appEntry.isSupported() %>" disabled="<%= !firstSubmission %>" label="offer-subscription-services-with-app-license" name="supported" type="checkbox" />
				</div>
			</div>

			<div class="license-options container">
				<span class="aui-field-label">
					<liferay-ui:message key="license-options" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="the-standard-and-developer-options-created-here-will-be-priced-on-the-next-page" />
				</span>

				<div class="aui-field-licenses-container">
					<aui:layout>
						<aui:column columnWidth="<%= 50 %>" first="<%= true %>">
							<div class="license-option-field standard">
								<aui:input name="standardQuantities" type="hidden" />
								<aui:input name="standardUnlimited" type="hidden" />

								<span class="aui-field-label">
									<liferay-ui:message key="standard-licenses" />
								</span>

								<div class="aui-field-input-container">
									<input class="quantity-field" type="text" />

									<a class="btn" href="javascript:;"><liferay-ui:message key="add-quantity" /></a>
								</div>

								<%
								List<AssetLicense> assetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

								boolean hasUnlimitedAssetLicense = false;

								if (AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, AssetLicenseConstants.LICENSE_TYPE_UNLIMITED, WorkflowConstants.STATUS_APPROVED) > 0) {
									hasUnlimitedAssetLicense = true;
								}
								%>

								<%@ include file="/marketplace_developer_apps/asset_license_quantity_list.jspf" %>
							</div>
						</aui:column>

						<aui:column columnWidth="<%= 50 %>" last="<%= true %>">
							<div class="license-option-field developer">
								<aui:input name="developerQuantities" type="hidden" />
								<aui:input name="developerUnlimited" type="hidden" />

								<span class="aui-field-label">
									<liferay-ui:message key="developer-licenses" />
								</span>

								<div class="aui-field-input-container">
									<input class="quantity-field" type="text" />

									<a class="btn" href="javascript:;"><liferay-ui:message key="add-quantity" /></a>
								</div>

								<%
								List<AssetLicense> assetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

								boolean hasUnlimitedAssetLicense = false;

								if (AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, AssetLicenseConstants.LICENSE_TYPE_UNLIMITED, WorkflowConstants.STATUS_APPROVED) > 0) {
									hasUnlimitedAssetLicense = true;
								}
								%>

								<%@ include file="/marketplace_developer_apps/asset_license_quantity_list.jspf" %>
							</div>
						</aui:column>
					</aui:layout>
				</div>
			</div>

			<div class="trial container">
				<span class="aui-field-label">
					<liferay-ui:message key="trial-license" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="a-free-30-day-trial-will-appear-as-an-option-for-customers-to-select-during-the-purchase-process" />
				</span>

				<%
				boolean taglibChecked = appVersion.hasTrialLicense();

				if (firstSubmission && (AssetLicenseLocalServiceUtil.getAssetLicensesCount(AppVersion.class.getName(), appVersion.getAppVersionId(), WorkflowConstants.STATUS_ANY) <= 0)) {
					taglibChecked = true;
				}
				%>

				<div class="aui-field-input-container">
					<aui:input checked="<%= taglibChecked %>" label="offer-a-free-30-day-trial-license-for-your-app" name="trial" type="checkbox" />
				</div>
			</div>
		</aui:fieldset>

		<aui:button-row>
			<c:choose>
				<c:when test="<%= appVersion.isApproved() || (appVersion.getReleaseType() == AppVersionConstants.RELEASE_TYPE_PRICING) %>">
					<liferay-portlet:renderURL var="viewAppEntryURL">
						<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</liferay-portlet:renderURL>

					<aui:button cssClass="btn" onClick="<%= viewAppEntryURL %>" value="cancel" />
				</c:when>
				<c:otherwise>
					<liferay-portlet:renderURL var="editAppVersionURL">
						<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_version.jsp" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</liferay-portlet:renderURL>

					<aui:button cssClass="btn" onClick="<%= editAppVersionURL %>" value="back" />
				</c:otherwise>
			</c:choose>

			<aui:button cssClass="btn" onClick='<%= renderResponse.getNamespace() + "saveAsDraft();" %>' value="save-as-draft" />

			<aui:button cssClass="btn" type="submit" value="next" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />saveAsDraft() {
		<portlet:renderURL var="viewURL">
			<portlet:param name="mvcPath" value="/marketplace_developer_apps/view.jsp" />
			<portlet:param name="tabs1" value="apps" />
		</portlet:renderURL>

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= viewURL %>';

		<portlet:namespace />saveAssetLicenses();
	}

	function <portlet:namespace />saveAssetLicenses() {
		var A = AUI();

		var developerQuantityContainer = A.one('.marketplace-developer-apps .license-options .license-option-field.developer');
		var standardQuantityContainer = A.one('.marketplace-developer-apps .license-options .license-option-field.standard');

		var developerQuantities = developerQuantityContainer.all('li.quantity');
		var standardQuantities = standardQuantityContainer.all('li.quantity');

		if (!developerQuantities.isEmpty()) {
			developerQuantityContainer.one('#<portlet:namespace />developerQuantities').val(developerQuantities.attr('data-quantity'));
		}

		if (!standardQuantities.isEmpty()) {
			standardQuantityContainer.one('#<portlet:namespace />standardQuantities').val(standardQuantities.attr('data-quantity'));
		}

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<aui:script use="aui-base">
	var digitsRegex = /^[1-9]\d*$/;

	var addQuantity = function(event) {
		event.preventDefault();

		var eventNode = event.currentTarget;

		var quantityContainer = eventNode.ancestor('.license-option-field');
		var quantityField = quantityContainer.one('input.quantity-field');
		var quantityList = quantityContainer.one('ul.quantities');

		var quantity = quantityField.val();

		quantityField.val('');

		if (!digitsRegex.test(quantity)) {
			return;
		}

		var quantityItems = quantityList.all('li.quantity');

		if (quantityItems.size() >= 10) {
			return;
		}

		var quantityItem = quantityList.one('li[data-quantity="' + quantity + '"]');

		if (quantityItem) {
			return;
		}

		var languageKey;

		if (quantity == 1) {
			languageKey = Liferay.Language.get('x-instance-unit', quantity);
		}
		else {
			languageKey = Liferay.Language.get('x-instance-units', quantity);
		}

		quantityItem = A.Node.create('<li class="quantity" data-quantity="' + quantity + '"><a class="action"><liferay-ui:message key="delete" /></a>' + languageKey + '</li>');

		quantityList.appendChild(quantityItem);

		quantityItems = quantityList.all('li.quantity');

		quantityItems.remove();

		quantityItems._nodes.sort(
			function(license1, license2) {
				var quantity1 = A.one(license1).attr('data-quantity');
				var quantity2 = A.one(license2).attr('data-quantity');

				quantity1 = parseInt(quantity1);
				quantity2 = parseInt(quantity2);

				if (quantity1 > quantity2) {
					return 1;
				}
				if (quantity1 < quantity2) {
					return -1;
				}

				return 0;
			}
		);

		quantityList.append(quantityItems);
	}

	A.all('.marketplace-developer-apps .license-option-field .btn').on('click', addQuantity);
	A.all('.marketplace-developer-apps .license-option-field .quantity-field').on('key', addQuantity, 'enter');

	A.one('.marketplace-developer-apps .license-options').delegate(
		'click',
		function(event) {
			var actionElem = event.currentTarget;

			var quantityList = actionElem.ancestor('ul');
			var quantityItem = actionElem.ancestor('li');

			quantityList.removeChild(quantityItem);
		},
		'li.quantity a.action'
	);
</aui:script>