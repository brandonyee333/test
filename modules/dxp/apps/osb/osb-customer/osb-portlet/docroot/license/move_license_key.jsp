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

long licenseKeyId = ParamUtil.getLong(request, "licenseKeyId");

LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);

OfferingEntry offeringEntry = licenseKey.getOfferingEntry();
ProductEntry productEntry = ProductEntryLocalServiceUtil.fetchProductEntry(offeringEntry.getProductEntryId());

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/license/move_license_key.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("licenseKeyId", String.valueOf(licenseKeyId));
%>

<script type="text/javascript">
	function <portlet:namespace />moveLicenseKey(offeringEntryId) {
		document.<portlet:namespace />fm.<portlet:namespace />offeringEntryId.value = offeringEntryId;
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<portlet:actionURL name="updateLicenseKey" var="updateLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license/move_license_key.jsp" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:row>
		<aui:form action="<%= updateLicenseKeyURL %>" cssClass="col-md-12" method="post">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="licenseKeyId" type="hidden" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
			<aui:input name="licenseKeySetId" type="hidden" value="<%= String.valueOf(licenseKey.getLicenseKeySetId()) %>" />
			<aui:input name="active" type="hidden" value="<%= String.valueOf(licenseKey.getActive()) %>" />
			<aui:input name="offeringEntryId" type="hidden" value="" />

			<div class="clearfix section">
				<div class="pull-right">
					<aui:button onClick="<%= backURL %>" value="back-to-previous-page" />
				</div>
			</div>

			Move License: <%= HtmlUtil.escape(licenseKey.getDescription()) %>

			<h1 class="section-heading">
				<liferay-ui:message key="choose-license" />
			</h1>

			<%
			LinkedHashMap params = new LinkedHashMap();

			params.put("validLicense", new Long[] {0L, 0L});

			List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, licenseKey.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
			%>

			<liferay-ui:search-container
				delta="<%= 10 %>"
				headerNames="name,type,start-date,lifetime,license-keys-available"
				iteratorURL="<%= portletURL %>"
				total="<%= offeringEntryGroups.size() %>"
			>
				<liferay-ui:search-container-results
					results="<%= ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.OfferingEntryGroup"
					modelVar="offeringEntryGroup"
				>

					<%
					ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();

					String key = offeringEntryGroup.getKey();

					String rowHREF = null;

					if (!key.equals(offeringEntry.getKey()) && offeringEntryGroup.hasAvailableServers() && ((productEntry == null) || (productEntry.getEnvironment() == curProductEntry.getEnvironment()))) {
						OfferingEntry availableOfferingEntry = offeringEntryGroup.getAvailableLicenseOfferingEntry();

						StringBuilder sb = new StringBuilder();

						sb.append("javascript:");
						sb.append(renderResponse.getNamespace());
						sb.append("moveLicenseKey(");
						sb.append(availableOfferingEntry.getOfferingEntryId());
						sb.append(");");

						rowHREF = sb.toString();
					}
					%>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="product"
						value="<%= curProductEntry.getName() %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="lifetime"
					>
						<%= (offeringEntryGroup.getLicenseLifetime() / Time.DAY) + " Days" %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="license-keys-available"
					>
						<%= offeringEntryGroup.getQuantity() - offeringEntryGroup.getLicenseKeysCount() %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
						name="type"
						value="<%= LanguageUtil.get(request, OfferingEntryConstants.getTypeLabel(offeringEntryGroup.getType())) %>"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowHREF %>"
					>
						<c:choose>
							<c:when test="<%= key.equals(offeringEntry.getKey()) %>">
								<liferay-ui:icon
									image="checked"
									label="<%= true %>"
									message="current"
								/>
							</c:when>
							<c:when test="<%= offeringEntryGroup.hasAvailableServers() && ((productEntry == null) || (productEntry.getEnvironment() == curProductEntry.getEnvironment())) %>">
								<aui:button onClick="<%= rowHREF %>" value="choose" />
							</c:when>
						</c:choose>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</aui:form>
	</aui:row>
</div>