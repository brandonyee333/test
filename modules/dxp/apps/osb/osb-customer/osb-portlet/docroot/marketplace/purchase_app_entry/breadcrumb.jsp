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
String purchaseStep = ParamUtil.getString(request, "purchaseStep", "project");

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings();

if (ecDocumentEntryId > 0) {
	ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.getECDocumentEntry(ecDocumentEntryId);

	ecDocumentEntryExtraSettings.setExtraSettingsProperties(ecDocumentEntry.getExtraSettingsProperties());
}

long appEntryId = ParamUtil.getLong(request, "appEntryId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace/purchase_app_entry.jsp");
portletURL.setParameter("ecDocumentEntryId", String.valueOf(ecDocumentEntryId));
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
%>

<ul class="purchase-breadcrumbs">
	<li>

		<%
		portletURL.setParameter("purchaseStep", "project");

		String cssClass = "purchase-breadcrumb purchase-project";

		if (purchaseStep.equals("end-user") || purchaseStep.equals("project")) {
			cssClass += " current";
		}
		else {
			cssClass += " active";
		}
		%>

		<a class="<%= cssClass %>"<%= cssClass.endsWith(" active") ? " href=\"" + portletURL + "\"" : StringPool.BLANK %>>
			<div class="sprite account"></div>

			<liferay-ui:message key="account" />
		</a>
	</li>
	<li>
		<div class="sprite arrow"></div>
	</li>
	<li>

		<%
		portletURL.setParameter("purchaseStep", "license");

		cssClass = "purchase-breadcrumb purchase-license";

		if (purchaseStep.equals("coterm") || purchaseStep.equals("license")) {
			cssClass += " current";
		}
		else if (ecDocumentEntryExtraSettings.getAppEntryId() > 0) {
			cssClass += " active";
		}
		else {
			cssClass += " inactive";
		}
		%>

		<a class="<%= cssClass %>"<%= cssClass.endsWith(" active") ? " href=\"" + portletURL + "\"" : StringPool.BLANK %>>
			<div class="sprite license"></div>

			<liferay-ui:message key="license" />
		</a>
	</li>
	<li>
		<div class="sprite arrow"></div>
	</li>
	<li>

		<%
		portletURL.setParameter("purchaseStep", "destination");

		cssClass = "purchase-breadcrumb purchase-destination";

		if (purchaseStep.equals("destination")) {
			cssClass += " current";
		}
		else if (ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(ecDocumentEntryId) > 0) {
			cssClass += " active";
		}
		else {
			cssClass += " inactive";
		}
		%>

		<a class="<%= cssClass %>"<%= cssClass.endsWith(" active") ? " href=\"" + portletURL + "\"" : StringPool.BLANK %>>
			<div class="sprite destination"></div>

			<liferay-ui:message key="destination" />
		</a>
	</li>
	<li>
		<div class="sprite arrow"></div>
	</li>
	<li>

		<%
		portletURL.setParameter("purchaseStep", "summary");

		cssClass = "purchase-breadcrumb purchase-summary";

		if (purchaseStep.equals("summary")) {
			cssClass += " current";
		}
		else if (ecDocumentEntryExtraSettings.getAddressId() > 0) {
			cssClass += " active";
		}
		else {
			cssClass += " inactive";
		}
		%>

		<a class="<%= cssClass %>"<%= cssClass.endsWith(" active") ? " href=\"" + portletURL + "\"" : StringPool.BLANK %>>
			<div class="sprite summary"></div>

			<liferay-ui:message key="summary" />
		</a>
	</li>
</ul>