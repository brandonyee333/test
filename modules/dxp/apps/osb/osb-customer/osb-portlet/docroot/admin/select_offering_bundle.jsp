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
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_offering_bundle.jsp");
portletURL.setParameter("callback", callback);
%>

<aui:form method="post">
	<liferay-ui:tabs
		names="offering-bundles"
	/>

	<liferay-ui:search-container
		headerNames="name"
		iteratorURL="<%= portletURL %>"
		total="<%= OfferingBundleLocalServiceUtil.getOfferingBundlesCount() %>"
	>
		<liferay-ui:search-container-results
			results="<%= OfferingBundleLocalServiceUtil.getOfferingBundles(searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.OfferingBundle"
			escapedModel="<%= true %>"
			keyProperty="offeringBundleId"
			modelVar="offeringBundle"
		>

			<%
			StringBundler sb = new StringBundler();

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append(callback);
			sb.append("([");

			List<OfferingDefinition> offeringDefinitions = offeringBundle.getOfferingDefinitions();

			for (int i = 0; i < offeringDefinitions.size(); i++) {
				OfferingDefinition offeringDefinition = offeringDefinitions.get(i);

				sb.append("['");
				sb.append(offeringDefinition.getProductEntryId());
				sb.append("', '");
				sb.append(offeringDefinition.getSupportResponseId());
				sb.append("', '");
				sb.append(UnicodeFormatter.toString(offeringDefinition.getProductDescription()));
				sb.append("', '");
				sb.append(offeringDefinition.isLicenses());
				sb.append("', '");
				sb.append(offeringDefinition.isSupportTickets());
				sb.append("', '");
				sb.append(offeringDefinition.getQuantity());
				sb.append("']");

				if ((i + 1) < offeringDefinitions.size()) {
					sb.append(", ");
				}
			}

			sb.append("]); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>