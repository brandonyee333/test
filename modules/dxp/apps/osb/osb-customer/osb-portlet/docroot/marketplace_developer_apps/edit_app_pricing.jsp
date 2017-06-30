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
%>

<div class="edit-app-pricing marketplace-developer-apps">
	<liferay-ui:header
		title="app-pricing"
	/>

	<liferay-util:include page="/marketplace_developer_apps/app_breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
	</liferay-util:include>

	<portlet:actionURL name="updateAppEntryPricing" var="updateAppEntryPricingURL" />

	<aui:form action="<%= updateAppEntryPricingURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_developer_apps/edit_app_pricing.jsp" />

		<portlet:renderURL var="viewMyAppEntryURL">
			<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="<%= viewMyAppEntryURL %>" />

		<aui:input name="appEntryId" type="hidden" value="<%= appEntryId %>" />

		<div class="aui-helper-hidden communication-error portlet-msg-error">
			<liferay-ui:message key="there-was-an-unexpected-communication-error.-please-refresh-this-page" />
		</div>

		<liferay-ui:error exception="<%= AppPricingCountryException.class %>" message="please-update-your-pricing,-your-app-must-be-available-for-sale-in-a-country" />
		<liferay-ui:error exception="<%= AppPricingItemCurrencyEntryException.class %>" message="please-select-only-valid-currencies" />

		<liferay-ui:error exception="<%= AppPricingItemPriceException.class %>">
			<p>
				<liferay-ui:message key="please-enter-valid-prices-for-each-license-in-every-pricing-table" />
			</p>

			<p>
				<liferay-ui:message key="every-price-entered-must-be-greater-than-the-amount-displayed-below-according-to-their-currency" /><br />
			</p>

			<ul>

				<%
				List<CurrencyEntry> currencyEntries = CurrencyEntryLocalServiceUtil.getCurrencyEntries(true);

				for (CurrencyEntry currencyEntry : currencyEntries) {
				%>

					<li>
						<%= currencyEntry.getCurrencyCode() %>: <%= CurrencyUtil.format(locale, currencyEntry.getCountryId(), currencyEntry.getMarketplaceMinPrice()) %>
					</li>

				<%
				}
				%>

			</ul>
		</liferay-ui:error>

		<liferay-ui:error exception="<%= AppPricingPriceException.class %>" message="please-update-your-pricing,-license-and-support-prices-cannot-be-free" />
		<liferay-ui:error exception="<%= NoSuchCurrencyEntryException.class %>" message="please-update-your-pricing,-all-pricing-tables-must-specify-a-currency" />
		<liferay-ui:error exception="<%= RequiredAppPricingItemException.class %>" message="please-enter-valid-prices-for-each-license-in-every-pricing-table" />

		<div class="container pricing">
			<span class="aui-field-label">
				<liferay-ui:message key="pricing-and-availability" />
			</span>

			<span class="aui-field-help">
				<liferay-ui:message key="set-your-prices-in-table-below-then-drag-the-countries-from-the-list-on-the-right" />
			</span>

			<aui:layout>
				<aui:column columnWidth="<%= 70 %>" first="<%= true %>">
					<div class="pricing-tables">

						<%
						List<AppPricing> appPricings = AppPricingLocalServiceUtil.getAppPricings(appVersion.getAppVersionId());

						for (AppPricing appPricing : appPricings) {
						%>

							<liferay-util:include page="/marketplace_developer_apps/app_pricing.jsp" servletContext="<%= application %>">
								<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
								<liferay-util:param name="appPricingId" value="<%= String.valueOf(appPricing.getAppPricingId()) %>" />
							</liferay-util:include>

						<%
						}

						if (appPricings.isEmpty()) {
							AppPricing appPricing = AppPricingServiceUtil.addAppPricing(appVersion.getAppVersionId(), LanguageUtil.get(pageContext, "default-pricing"));
						%>

							<liferay-util:include page="/marketplace_developer_apps/app_pricing.jsp" servletContext="<%= application %>">
								<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
								<liferay-util:param name="appPricingId" value="<%= String.valueOf(appPricing.getAppPricingId()) %>" />
							</liferay-util:include>

						<%
						}
						%>

					</div>

					<div>
						<a class="add-pricing btn" href="javascript:;"><liferay-ui:message key="add-pricing-table" /></a>
					</div>
				</aui:column>

				<aui:column columnWidth="<%= 30 %>" last="<%= true %>">
					<div class="country-availability mp-field-group">
						<div class="mp-field-group-heading">
							<liferay-ui:message key="available-countries" />
						</div>

						<div>
							<a class="list-selector select-all" href="javascript:;"><liferay-ui:message key="select-all" /></a>

							<a class="list-selector deselect-all" href="javascript:;"><liferay-ui:message key="deselect-all" /></a>
						</div>

						<ul class="draggable-countries">

							<%
							List<Country> countries = ListUtil.copy(ECCountryLocalServiceUtil.getCountries());

							List<CountryAppPricing> countryAppPricings = CountryAppPricingLocalServiceUtil.getAppVersionCountryAppPricings(appVersion.getAppVersionId());

							for (CountryAppPricing countryAppPricing : countryAppPricings) {
								Country country = CountryServiceUtil.getCountry(countryAppPricing.getCountryId());

								countries.remove(country);
							}

							for (Country country : countries) {
							%>

								<li class="country" data-countryId="<%= country.getCountryId() %>"><%= country.getName() %></li>

							<%
							}
							%>

						</ul>
					</div>
				</aui:column>
			</aui:layout>
		</div>

		<aui:button-row>
			<liferay-portlet:renderURL var="editAppLicenseURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_license.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			</liferay-portlet:renderURL>

			<aui:button cssClass="btn" onClick="<%= editAppLicenseURL %>" value="back" />

			<aui:button cssClass="btn" type="submit" value="next" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base,aui-io,marketplace-drag-drop">
	var developerApps = A.one('.marketplace-developer-apps');

	var marketplaceDragDrop = new Liferay.MarketplaceDragDrop(
		{
			container: '.marketplace-developer-apps .container.pricing',
			draggables: '.draggable-countries li.country',
			dropTargets: '.draggable-countries',
			on: {
				drophit: function(event) {
					var draggables = event.draggables;
					var dropTarget = event.dropTarget;

					var pricingTableId = 0;

					var pricingTable = dropTarget.ancestor('.pricing-table');

					if (pricingTable) {
						pricingTableId = pricingTable.attr('data-appPricingId');
					}

					A.io.request(
						'<liferay-portlet:actionURL name="updateCountryAppPricings" />',
						{
							data: {
								<portlet:namespace />appEntryId: <%= appEntry.getAppEntryId() %>,
								<portlet:namespace />appPricingId: pricingTableId,
								<portlet:namespace />countryIds: draggables.attr('data-countryId'),
							},
							dataType: 'json',
							method: 'post',
							on: responseHandler
						}
					);

					sortCountryList(dropTarget);

					updateCountryAvailabilitySize();
				}
			},
			selectable: true
		}
	);

	var responseHandler = {
		failure: function(event, id, obj) {
			developerApps.one('.communication-error').removeClass('aui-helper-hidden');
		},
		success: function(event, id, obj) {
			var response = this.get('responseData');

			if (!response || !response.message) {
				developerApps.one('.communication-error').removeClass('aui-helper-hidden');
			}
		}
	};

	var sortCountryList = function(countryList) {
		var countries = countryList.all('.country');

		countries.remove();

		countries._nodes.sort(
			function(country1, country2) {
				var name1 = A.one(country1).text().toLowerCase();
				var name2 = A.one(country2).text().toLowerCase();

				if (name1 > name2) {
					return 1;
				}
				else if (name1 < name2) {
					return -1;
				}

				return 0;
			}
		);

		countryList.append(countries);
	};

	var updateCountryAvailabilityLocation = function() {
		var countryAvailability = developerApps.one('.country-availability');

		var countryAvailabilityHeight = countryAvailability.height();

		var pricingTables = developerApps.one('.pricing-tables');

		var pricingTablesHeight = parseInt(pricingTables.getComputedStyle('height'));

		if (pricingTablesHeight > countryAvailabilityHeight) {
			var banner = A.one('#banner');

			var bannerHeight = 0;

			if (banner != null) {
				bannerHeight = banner.height();
			}

			var scrollLocation = A.getWin().attr('scrollTop') + bannerHeight;

			var scrollLowerLimit = pricingTables.getY() - 23;
			var scrollUpperLimit = pricingTablesHeight + scrollLowerLimit - countryAvailabilityHeight - 10;

			if (scrollLocation > scrollUpperLimit) {
				countryAvailability.setStyle('position', 'relative');
				countryAvailability.setStyle('top', pricingTablesHeight - countryAvailabilityHeight - 23);
			}
			else if (scrollLocation > scrollLowerLimit) {
				countryAvailability.setStyle('position', 'fixed');
				countryAvailability.setStyle('top', bannerHeight + 10);
			}
			else if (scrollLocation <= scrollLowerLimit) {
				countryAvailability.setStyle('position', 'static');
				countryAvailability.setStyle('top', '');
			}
		}
		else {
			countryAvailability.setStyle('position', 'static');
			countryAvailability.setStyle('top', 0);
		}
	};

	A.getWin().on('scroll', updateCountryAvailabilityLocation);

	var updateCountryAvailabilitySize = function() {
		var windowHeight = A.getWin().height() - 43;

		var countryAvailability = developerApps.one('.country-availability');

		countryAvailability.setStyle('height', 'auto');

		var countryAvailabilityHeight = countryAvailability.height();

		if (countryAvailabilityHeight > windowHeight) {
			countryAvailabilityHeight = windowHeight;
		}

		countryAvailability.setStyle('height', countryAvailabilityHeight);

		updateCountryAvailabilityLocation();
	};

	A.getWin().on('resize', updateCountryAvailabilitySize);

	updateCountryAvailabilitySize();

	developerApps.one('.add-pricing').on(
		'click',
		function(event) {
			A.io.request(
				'<liferay-portlet:actionURL name="addAppPricing" />',
				{
					data: {
						<portlet:namespace />appEntryId: <%= appEntry.getAppEntryId() %>
					},
					dataType: 'html',
					method: 'post',
					on: {
						failure: responseHandler.failure,
						success: function(event, id, obj) {
							var response = this.get('responseData');

							var pricingTable = A.Node.create(response);

							if (pricingTable.hasClass('pricing-table')) {
								developerApps.one('.pricing-tables').append(pricingTable);

								marketplaceDragDrop.addDropTarget(pricingTable.one('.draggable-countries'));

								updateCountryAvailabilityLocation();
							}
							else {
								responseHandler.failure(event, id, obj);
							}
						}
					}
				}
			);
		}
	);

	developerApps.delegate(
		'click',
		function(event) {
			if (!confirm('<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />')) {
				return;
			}

			var deleteLink = event.currentTarget;

			var pricingTable = deleteLink.ancestor('.pricing-table');

			A.io.request(
				'<liferay-portlet:actionURL name="deleteAppPricing" />',
				{
					data: {
						<portlet:namespace />appPricingId: pricingTable.attr('data-appPricingId')
					},
					dataType: 'json',
					method: 'post',
					on: {
						failure: responseHandler.failure,
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (!response || (response.message != 'success')) {
								developerApps.one('.communication-error').removeClass('aui-helper-hidden');

								return;
							}

							var availableCountries = developerApps.one('.country-availability .draggable-countries');

							var countries = pricingTable.all('.country');

							availableCountries.append(countries);

							sortCountryList(availableCountries);

							pricingTable.remove();

							updateCountryAvailabilityLocation();
						}
					}
				}
			);
		},
		'.delete-pricing'
	);

	developerApps.delegate(
		'blur',
		function(event) {
			var eventInput = event.currentTarget;

			var pricingTable = eventInput.ancestor('.pricing-table');

			var currencyNode = pricingTable.one('.edit-pricing.currency-select');
			var nameNode = pricingTable.one('.edit-pricing.name');

			var data = {
				<portlet:namespace />appPricingId: pricingTable.attr('data-appPricingId'),
				<portlet:namespace />currencyEntryId: currencyNode.attr('value'),
				<portlet:namespace />name: nameNode.attr('value')
			};

			var developerSupportPriceNode = pricingTable.one('.edit-pricing.developer-support-price');

			if (developerSupportPriceNode) {
				data.<portlet:namespace />developerSupportPrice = developerSupportPriceNode.attr('value');
			}

			var standardSupportPriceNode = pricingTable.one('.edit-pricing.standard-support-price');

			if (standardSupportPriceNode) {
				data.<portlet:namespace />standardSupportPrice = standardSupportPriceNode.attr('value');
			}

			A.io.request(
				'<liferay-portlet:actionURL name="updateAppPricing" />',
				{
					data: data,
					dataType: 'json',
					method: 'post',
					on: responseHandler
				}
			);
		},
		'.edit-pricing'
	);

	developerApps.delegate(
		'blur',
		function(event) {
			var priceInput = event.currentTarget;

			var pricingTable = priceInput.ancestor('.pricing-table');
			var priceNode = priceInput.ancestor('.price');

			A.io.request(
				'<liferay-portlet:actionURL name="updateAppPricingItem" />',
				{
					data: {
						<portlet:namespace />appPricingId: pricingTable.attr('data-appPricingId'),
						<portlet:namespace />assetLicenseId: priceNode.attr('data-assetLicenseId'),
						<portlet:namespace />price: priceInput.attr('value')
					},
					dataType: 'json',
					method: 'post',
					on: responseHandler
				}
			);
		},
		'.edit-pricing-item'
	);

	developerApps.delegate(
		'click',
		function(event) {
			var selectorLink = event.currentTarget;

			var container = selectorLink.ancestor('.pricing-table');

			if (!container) {
				container = selectorLink.ancestor('.country-availability');
			}

			var countries = container.one('.draggable-countries');

			var countryNodes = countries.all('.country');

			if (selectorLink.hasClass('select-all')) {
				countryNodes.addClass('selected');
			}
			else if (selectorLink.hasClass('deselect-all')) {
				countryNodes.removeClass('selected');
			}
		},
		'.list-selector'
	);
</aui:script>