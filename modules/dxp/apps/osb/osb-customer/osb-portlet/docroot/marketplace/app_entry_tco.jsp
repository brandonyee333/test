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

AppVersion appVersion = appEntry.getApprovedAppVersion();
%>

<div class="app-entry-tco">
	<div class="chart"></div>

	<div class="legend">
		<div class="keys">
			<ul>
				<li>
					<span class="perpetual key"></span>

					<span>
						<c:choose>
							<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>">
								<liferay-ui:message key="perpetual-license" />
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="non-perpetual-license" />
							</c:otherwise>
						</c:choose>
					</span>
				</li>

				<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appVersion.isSupported() %>">
					<li>
						<span class="subscription key"></span>

						<span>
							<liferay-ui:message key="subscription-services" />
						</span>
					</li>
				</c:if>
			</ul>
		</div>

		<div class="description">
			<ul>
				<li>
					<c:choose>
						<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>">
							<liferay-ui:message key="assumes-one-instance-unit-and-ongoing-subscription-services-if-offered" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="assumes-one-instance-unit-and-ongoing-app-renewal" />
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<liferay-ui:message arguments='<%= "/products/liferay-portal/get-price-quote" %>' key="plus-the-cost-of-liferay-portal-ee-subscription-if-required" />
				</li>
				<li>
					<liferay-ui:message arguments='<%= "/products/liferay-portal/get-price-quote" %>' key="information-is-based-on-the-developers-current-pricing-and-subject-to-change-without-notice" />
				</li>
			</ul>
		</div>
	</div>
</div>

<%
AssetLicense assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);

if (assetLicense == null) {
	assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);
}

AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.fetchAppPricingItem(appVersion.getAppVersionId(), storeCountryId, assetLicense.getAssetLicenseId());

double initialCost = appPricingItem.getPrice();
double renewalCost = initialCost;

if (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) {
	CountryAppPricing countryAppPricing = CountryAppPricingLocalServiceUtil.fetchCountryAppPricing(appVersion.getAppVersionId(), storeCountryId);

	AppPricing appPricing = countryAppPricing.getAppPricing();

	renewalCost = appPricing.getSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD);
}

double totalCost = initialCost + (renewalCost * 2);

String currencySymbol = CurrencyUtil.getSymbol(appPricingItem.getCurrencyCode());
%>

<aui:script use="charts">
	var chart = new A.Chart(
		{
			axes: {
				majorTicks: {
					display: 'none'
				},
				minorTicks: {
					display: 'none'
				}
			},
			dataProvider: [
				{
					category: '<liferay-ui:message key="year-1" unicode="<%= true %>" />',
					label: {
						currencyFormatted: '<%= currencySymbol + numberFormat.format(initialCost) %>'
					},
					values: <%= (int)initialCost %>
				},
				{
					category: '2',
					label: {
						currencyFormatted: '<%= currencySymbol + numberFormat.format(renewalCost) %>'
					},
					values: <%= (int)renewalCost %>
				},
				{
					category: '3',
					label: {
						currencyFormatted: '<%= currencySymbol + numberFormat.format(renewalCost) %>'
					},
					values: <%= (int)renewalCost %>
				},
				{
					category: '<liferay-ui:message key="3-year-total" unicode="<%= true %>" />',
					label: {
						currencyFormatted: '<%= currencySymbol + numberFormat.format(totalCost) %>'
					},
					values: <%= (int)totalCost %>
				}
			],
			showAreaFill: false,
			showLines: false,
			showMarkers: false,
			stacked: true,
			type: 'column',
			tooltip: {
				styles: {
					display: 'none'
				}
			},
			styles: {
				axes: {
					category: {
						label: {
							color: '#000'
						}
					}
				},
				graph: {
					background: {
						border: {
							color: '#FFF'
						},
						fill: {
							color: '#FFF'
						}
					}
				},
				series: {
					values : {
						fill: {
							color: '#3C90CE'
						},
						width: 50
					}
				}
			}
		}
	);

	chart.after(
		'render',
		function() {
			var instance = this;

			var data = instance.get('dataProvider');

			var chartValues = this.getSeries('values');

			var chartMarkers = chartValues.get('markers');

			var getChartMarkerHeight = function(markerIndex) {
				var sum = 0;

				for (var i = 0; i <= markerIndex; i++) {
					var marker = A.one(chartMarkers[i].node);

					sum += parseInt(marker.getAttribute('height'));
				}

				return sum;
			};

			var setLabel = function(index, offsetY) {
				var currentMarker = A.one(chartMarkers[index].node);

				var text = document.createElementNS('http://www.w3.org/2000/svg', 'text');

				text.setAttribute('text-anchor', 'middle');
				text.setAttribute('transform', 'translate(0, -' + offsetY +')');

				var currentMarkerX = parseInt(currentMarker.getAttribute('x')) + 25;

				text.setAttribute('x', currentMarkerX);

				var currentMarkerY = parseInt(currentMarker.getAttribute('y')) - 5;

				text.setAttribute('y', currentMarkerY);

				text.textContent = data[index].label.currencyFormatted;

				var parentMarker = currentMarker.get('parentNode');

				parentMarker.insertBefore(text, currentMarker);
			};

			var setMarkerPosition = function(markerIndex) {
				var currentMarker = chartMarkers[markerIndex].node;

				currentMarker.setAttribute('transform', 'translate(0, -' + getChartMarkerHeight(markerIndex - 1) + ')');
			};

			A.each(
				data,
				function(item, index) {
					var currentChartMarker = chartMarkers[index];

					if (currentChartMarker) {
						var currentChartMarkerNode = currentChartMarker.node;

						if (item.category != '<liferay-ui:message key="3-year-total" unicode="<%= true %>" />') {
							<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appVersion.isSupported() %>">
								if (index != 0) {
									currentChartMarkerNode.setAttribute('class', currentChartMarkerNode.getAttribute('class').concat(' renewal-cost'));
								}
							</c:if>

							setMarkerPosition(index);
							setLabel(index, getChartMarkerHeight(index - 1));
						}
						else {
							currentChartMarkerNode.setAttribute('class', currentChartMarkerNode.getAttribute('class').concat(' three-year-total-cost'));

							setLabel(index, 0);
						}
					}
				}
			);
		}
	);

	chart.render('.marketplace .app-entry-tco .chart');
</aui:script>