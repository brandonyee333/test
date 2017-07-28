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

<aui:form action="<%= mergeLicenseKeySetURL %>" class="uni-form" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />toLicenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
	<input name="<portlet:namespace />fromLicenseKeySetId" type="hidden" value="" />

	<div class="cleared section">
		<div class="fr">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	Merge License for: <%= HtmlUtil.escape(licenseKeySet.getName()) %>

	<h1 class="section-heading">
		<liferay-ui:message key="merge-with-license" />
	</h1>

	<div class="callout-a">
		<div class="callout-content">
			<%= HtmlUtil.escape(LanguageUtil.format(pageContext, "merge-help-x", licenseKeySet.getName())) %>

			<liferay-ui:search-container
				headerNames="name"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= LicenseKeySetLocalServiceUtil.getAccountEntryLicenseKeySets(licenseKeySet.getAccountEntryId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= LicenseKeySetLocalServiceUtil.getAccountEntryLicenseKeySetsCount(licenseKeySet.getAccountEntryId()) %>"
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
							<input class="aui-button-input" onClick="javascript:<portlet:namespace />mergeLicenseKeySet(<%= curLicenseKeySet.getLicenseKeySetId() %>);" type="button" value="<liferay-ui:message key="choose" />" />
						</c:if>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>

	<div>
		<input class="aui-button-input" onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
	</div>
</aui:form>