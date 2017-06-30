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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "standard-licenses");

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(assetReceipt.getProductClassPK());

int usageType = 0;

if (tabs1.equals("developer-licenses")) {
	usageType = AssetLicenseConstants.USAGE_TYPE_DEVELOPER;
}
else if (tabs1.equals("standard-licenses")) {
	usageType = AssetLicenseConstants.USAGE_TYPE_STANDARD;
}
else if (tabs1.equals("trial-licenses")) {
	usageType = AssetLicenseConstants.USAGE_TYPE_TRIAL;
}

List<AssetReceiptLicense> assetReceiptLicenses = Collections.emptyList();

if (!assetReceipt.hasRenewedAssetReceiptLicenses(usageType)) {
	assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(assetReceiptId, usageType, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_admin/edit_asset_receipt_licenses.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("assetReceiptId", String.valueOf(assetReceiptId));
%>

<div class="edit-asset-receipt">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="edit-purchased-licenses"
	/>

	<c:if test="<%= appEntry != null %>">
		<h3>
			<%= HtmlUtil.escape(appEntry.getTitle()) %>
		</h3>
	</c:if>

	<portlet:actionURL name="updateAssetReceiptLicense" var="updateAssetReceiptLicenseURL" />

	<aui:form action="<%= updateAssetReceiptLicenseURL %>" enctype="multipart/form-data" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_admin/edit_asset_receipt_licenses.jsp" />
		<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="assetReceiptId" type="hidden" value="<%= String.valueOf(assetReceiptId) %>" />
		<aui:input name="usageType" type="hidden" value="<%= String.valueOf(usageType) %>" />

		<liferay-ui:tabs
			names="standard-licenses,developer-licenses,trial-licenses"
			param="tabs1"
			url="<%= portletURL.toString() %>"
			value="<%= tabs1 %>"
		/>

		<aui:fieldset>
			<div class="field-wrapper">
				<span class="aui-field aui-field-date">
					<span class="aui-field-content">
						<label class="aui-field-label"><liferay-ui:message key="start-date" /></label>

						<%
						Calendar startDateCalendar = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

						if (!assetReceiptLicenses.isEmpty()) {
							AssetReceiptLicense assetReceiptLicense = assetReceiptLicenses.get(0);

							startDateCalendar.setTime(assetReceiptLicense.getStartDate());
						}

						int startDateDay = ParamUtil.getInteger(request, "startDateDay", startDateCalendar.get(Calendar.DATE));
						int startDateMonth = ParamUtil.getInteger(request, "startDateMonth", startDateCalendar.get(Calendar.MONTH));
						int startDateYear = ParamUtil.getInteger(request, "startDateYear", startDateCalendar.get(Calendar.YEAR));
						int startDateYearRangeEnd = startDateCalendar.get(Calendar.YEAR) + 25;
						int startDateYearRangeStart = startDateCalendar.get(Calendar.YEAR) - 25;
						%>

						<span class="aui-field-element">
							<liferay-ui:input-date cssClass="start-date"
								dayParam='<%= "startDateDay" %>'
								dayValue="<%= startDateDay %>"
								monthParam='<%= "startDateMonth" %>'
								monthValue="<%= startDateMonth %>"
								yearParam='<%= "startDateYear" %>'
								yearRangeEnd="<%= startDateYearRangeEnd %>"
								yearRangeStart="<%= startDateYearRangeStart %>"
								yearValue="<%= startDateYear %>"
							/>
						</span>
					</span>
				</span>
			</div>

			<div class="field-wrapper">
				<span class="aui-field aui-field-date">
					<span class="aui-field-content">
						<label class="aui-field-label"><liferay-ui:message key="end-date" /></label>

						<%
						Calendar endDateCalendar = CalendarFactoryUtil.getCalendar(themeDisplay.getTimeZone(), locale);

						endDateCalendar.setTime(new Date());

						if (assetReceipt.hasActiveAssetReceiptLicenses(usageType)) {
							endDateCalendar.setTime(assetReceipt.getActiveAssetReceiptLicensesEndDate(usageType));
						}

						int endDateDay = ParamUtil.getInteger(request, "endDateDay", endDateCalendar.get(Calendar.DATE));
						int endDateMonth = ParamUtil.getInteger(request, "endDateMonth", endDateCalendar.get(Calendar.MONTH));
						int endDateYear = ParamUtil.getInteger(request, "endDateYear", endDateCalendar.get(Calendar.YEAR));
						int endDateYearRangeEnd = endDateCalendar.get(Calendar.YEAR) + 25;
						int endDateYearRangeStart = endDateCalendar.get(Calendar.YEAR) - 25;
						%>

						<span class="aui-field-element">
							<liferay-ui:input-date
								dayParam='<%= "endDateDay" %>'
								dayValue="<%= endDateDay %>"
								monthParam='<%= "endDateMonth" %>'
								monthValue="<%= endDateMonth %>"
								yearParam='<%= "endDateYear" %>'
								yearRangeEnd="<%= endDateYearRangeEnd %>"
								yearRangeStart="<%= endDateYearRangeStart %>"
								yearValue="<%= endDateYear %>"
							/>
						</span>
					</span>
				</span>
			</div>

			<aui:button-row>
				<aui:button type="submit" />

				<aui:button href="<%= redirect %>" type="cancel" />
			</aui:button-row>
		</aui:fieldset>

		<liferay-ui:search-container
			emptyResultsMessage="no-active-licenses-were-found"
		>
			<liferay-ui:search-container-results
				results="<%= assetReceiptLicenses %>"
				total="<%= assetReceiptLicenses.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AssetReceiptLicense"
				escapedModel="<%= true %>"
				keyProperty="assetReceiptLicenseId"
				modelVar="assetReceiptLicense"
			>
				<liferay-ui:search-container-column-text
					name="order-id"
					value="<%= assetReceiptLicense.getUuid() %>"
				/>

				<liferay-ui:search-container-column-text
					name="start-date"
					translate="<%= false %>"
					value="<%= longDateFormatDate.format(assetReceiptLicense.getStartDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="end-date"
					translate="<%= false %>"
					value="<%= longDateFormatDate.format(assetReceiptLicense.getEndDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="purchased"
					value="<%= longDateFormatDate.format(assetReceiptLicense.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="last-modified"
					value="<%= longDateFormatDate.format(assetReceiptLicense.getModifiedDate()) %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</aui:form>
</div>