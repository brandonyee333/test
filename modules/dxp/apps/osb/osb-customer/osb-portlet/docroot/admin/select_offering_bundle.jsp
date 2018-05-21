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