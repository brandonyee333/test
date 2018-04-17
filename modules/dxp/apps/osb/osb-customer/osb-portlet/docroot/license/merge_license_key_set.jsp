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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long licenseKeySetId = ParamUtil.getLong(request, "licenseKeySetId");

LicenseKeySet licenseKeySet = LicenseKeySetServiceUtil.getLicenseKeySet(licenseKeySetId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/merge_license_key_set.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeySetId", String.valueOf(licenseKeySetId));
%>

<script type="text/javascript">
	function <portlet:namespace />mergeLicenseKeySet(fromLicenseKeySetId) {
		document.<portlet:namespace />fm.<portlet:namespace />fromLicenseKeySetId.value = fromLicenseKeySetId;

		submitForm(document.<portlet:namespace />fm);
	}
</script>

<portlet:actionURL name="mergeLicenseKeySet" var="mergeLicenseKeySetURL">
	<portlet:param name="mvcPath" value="/license/merge_license_key_set.jsp" />
</portlet:actionURL>

<aui:form action="<%= mergeLicenseKeySetURL %>" class="uni-form" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="toLicenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
	<aui:input name="fromLicenseKeySetId" type="hidden" value="" />

	<div class="clearfix section">
		<div class="pull-right">
			<aui:a cssClass="btn" href="<%= backURL %>" label="back-to-previous-page" />
		</div>
	</div>

	Merge License for: <%= HtmlUtil.escape(licenseKeySet.getName()) %>

	<h1 class="section-heading">
		<liferay-ui:message key="merge-with-license" />
	</h1>

	<div class="callout-a">
		<div class="callout-content">
			<%= HtmlUtil.escape(LanguageUtil.format(request, "merge-help-x", licenseKeySet.getName())) %>

			<liferay-ui:search-container
				headerNames="name"
				iteratorURL="<%= portletURL %>"
				total="<%= LicenseKeySetLocalServiceUtil.getAccountEntryLicenseKeySetsCount(licenseKeySet.getAccountEntryId()) %>"
			>
				<liferay-ui:search-container-results
					results="<%= LicenseKeySetLocalServiceUtil.getAccountEntryLicenseKeySets(licenseKeySet.getAccountEntryId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.LicenseKeySet"
					escapedModel="<%= true %>"
					keyProperty="licenseKeySetId"
					modelVar="curLicenseKeySet"
				>

					<%
					PortletURL licenseKeySetURL = renderResponse.createRenderURL();

					licenseKeySetURL.setParameter("mvcPath", "/license/edit_license_key_set.jsp");
					licenseKeySetURL.setParameter("licenseKeySetId", String.valueOf(curLicenseKeySet.getLicenseKeySetId()));
					%>

					<liferay-ui:search-container-column-text
						href="<%= licenseKeySetURL.toString() %>"
						name="name"
						value="<%= curLicenseKeySet.getName() %>"
					/>

					<liferay-ui:search-container-column-text
					>
						<c:if test="<%= curLicenseKeySet.getLicenseKeySetId() != licenseKeySetId %>">
							<aui:button onClick='<%= "javascript:" + renderResponse.getNamespace() + "mergeLicenseKeySet(" + curLicenseKeySet.getLicenseKeySetId() + ");" %>' value="choose" />
						</c:if>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>

	<div>
		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>
</aui:form>