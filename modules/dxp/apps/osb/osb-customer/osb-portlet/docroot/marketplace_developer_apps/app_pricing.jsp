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

AppVersion appVersion = appEntry.getUnreleasedAppVersion();

long appPricingId = ParamUtil.getLong(request, "appPricingId");

AppPricing appPricing = AppPricingLocalServiceUtil.getAppPricing(appPricingId);

NumberFormat appPricingCurrencyNumberFormat = null;

if (appPricing.getCurrencyEntryId() > 0) {
	appPricingCurrencyNumberFormat = NumberFormat.getInstance(locale);

	CurrencyEntry currencyEntry = CurrencyEntryLocalServiceUtil.getCurrencyEntry(appPricing.getCurrencyEntryId());

	Currency currency = Currency.getInstance(currencyEntry.getCurrencyCode());

	appPricingCurrencyNumberFormat.setMaximumFractionDigits(10);
	appPricingCurrencyNumberFormat.setMinimumFractionDigits(currency.getDefaultFractionDigits());
}
%>

<div class="pricing-table mp-field-group" data-appPricingId="<%= appPricing.getAppPricingId() %>">
	<div class="mp-field-group-heading">
		<input class="edit-pricing name" type="text" value="<%= appPricing.getName() %>" />

		<c:if test="<%= appPricing.getRank() > 1 %>">
			<span class="fr">
				<a class="delete-pricing" href="javascript:;">
					<liferay-ui:message key="delete" />
				</a>
			</span>
		</c:if>
	</div>

	<div class="mp-field-group-body">
		<div class="currency">
			<span class="aui-field-label">
				<liferay-ui:message key="currency" />
			</span>

			<select class="currency-select edit-pricing">
				<option />

				<%
				List<CurrencyEntry> currencyEntries = CurrencyEntryLocalServiceUtil.getCurrencyEntries(true);

				for (CurrencyEntry currencyEntry : currencyEntries) {
				%>

					<aui:option label="<%= currencyEntry.getCurrencyCode() %>" selected="<%= appPricing.getCurrencyEntryId() == currencyEntry.getCurrencyEntryId() %>" value="<%= currencyEntry.getCurrencyEntryId() %>" />

				<%
				}
				%>

			</select>
		</div>

		<div class="prices">
			<div class="column details">
				<span class="aui-field-label">
					<liferay-ui:message key="licenses" />

					<liferay-ui:icon-help message="price-for-each-instance-bundle" />
				</span>
			</div>

			<%
			List<AssetLicense> standardAssetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			List<AssetLicense> assetLicenses = new ArrayList<AssetLicense>(standardAssetLicenses.size() + 1);

			assetLicenses.addAll(standardAssetLicenses);

			AssetLicense unlimitedAssetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, AssetLicenseConstants.LICENSE_TYPE_UNLIMITED, AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED, WorkflowConstants.STATUS_APPROVED);

			if (unlimitedAssetLicense != null) {
				assetLicenses.add(unlimitedAssetLicense);
			}
			%>

			<c:if test="<%= !assetLicenses.isEmpty() %>">
				<div class="column standard">
					<span class="aui-field-label price-label">
						<liferay-ui:message key="standard" />
					</span>

					<%@ include file="/marketplace_developer_apps/app_pricing_item.jspf" %>
				</div>
			</c:if>

			<%
			List<AssetLicense> developerAssetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			assetLicenses = new ArrayList<AssetLicense>(developerAssetLicenses.size() + 1);

			assetLicenses.addAll(developerAssetLicenses);

			unlimitedAssetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, AssetLicenseConstants.LICENSE_TYPE_UNLIMITED, AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED, WorkflowConstants.STATUS_APPROVED);

			if (unlimitedAssetLicense != null) {
				assetLicenses.add(unlimitedAssetLicense);
			}
			%>

			<c:if test="<%= !assetLicenses.isEmpty() %>">
				<div class="column developer">
					<span class="aui-field-label price-label">
						<liferay-ui:message key="developer" />
					</span>

					<%@ include file="/marketplace_developer_apps/app_pricing_item.jspf" %>
				</div>
			</c:if>
		</div>

		<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appEntry.getSupported() %>">
			<div class="support">
				<div class="column details">
					<span class="aui-field-label">
						<liferay-ui:message key="subscription-services" />

						<liferay-ui:icon-help message="price-for-one-instance-per-year" />
					</span>
				</div>

				<div class="column">
					<span class="aui-field-label price-label">
						<liferay-ui:message key="standard" />
					</span>

					<%
					String standardPrice = StringPool.BLANK;

					if (appPricingCurrencyNumberFormat != null) {
						standardPrice = appPricingCurrencyNumberFormat.format(appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD));
					}
					else if (appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD) > 0) {
						standardPrice = String.valueOf(appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD));
					}
					%>

					<input class="edit-pricing standard-support-price" type="text" value="<%= standardPrice %>" />
				</div>

				<div class="column">
					<span class="aui-field-label price-label">
						<liferay-ui:message key="developer" />
					</span>

					<%
					String developerPrice = StringPool.BLANK;

					if (appPricingCurrencyNumberFormat != null) {
						developerPrice = appPricingCurrencyNumberFormat.format(appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_DEVELOPER));
					}
					else if (appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_DEVELOPER) > 0) {
						developerPrice = String.valueOf(appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_DEVELOPER));
					}
					%>

					<input class="edit-pricing developer-support-price" type="text" value="<%= developerPrice %>" />
				</div>
			</div>
		</c:if>

		<div class="countries">
			<span class="aui-field-label">
				<liferay-ui:message key="countries" />
			</span>

			<span class="aui-field-help">
				<liferay-ui:message key="drag-countries-here-to-sell-at-these-prices" />
			</span>

			<div>
				<a class="list-selector select-all" href="javascript:;"><liferay-ui:message key="select-all" /></a>

				<a class="list-selector deselect-all" href="javascript:;"><liferay-ui:message key="deselect-all" /></a>
			</div>

			<ul class="draggable-countries">

				<%
				List<CountryAppPricing> countryAppPricings = CountryAppPricingLocalServiceUtil.getCountryAppPricings(appPricingId);

				for (CountryAppPricing countryAppPricing : countryAppPricings) {
					Country country = CountryServiceUtil.getCountry(countryAppPricing.getCountryId());
				%>

					<li class="country" data-countryId="<%= country.getCountryId() %>"><%= country.getName() %></li>

				<%
				}
				%>

			</ul>
		</div>
	</div>
</div>