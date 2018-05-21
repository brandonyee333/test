<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

<div class="container-fluid-1280">
	<aui:row>
		<aui:form action="<%= mergeLicenseKeySetURL %>" cssClass="column-md-12" method="post">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="toLicenseKeySetId" type="hidden" value="<%= licenseKeySetId %>" />
			<aui:input name="fromLicenseKeySetId" type="hidden" value="" />

			<div class="clearfix section">
				<div class="pull-right">
					<aui:button onClick="<%= backURL %>" value="back-to-previous-page" />
				</div>
			</div>

			Merge License for: <%= HtmlUtil.escape(licenseKeySet.getName()) %>

			<h1 class="section-heading">
				<liferay-ui:message key="merge-with-license" />
			</h1>

			<div class="merge-license">
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

					<liferay-ui:search-iterator
						markupView="lexicon"
					/>
				</liferay-ui:search-container>
			</div>

			<aui:button onClick="<%= backURL %>" value="cancel" />
		</aui:form>
	</aui:row>
</div>