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

long zendeskCategoryId = ParamUtil.getLong(request, "zendeskCategoryId");

ZendeskCategory zendeskCategory = null;

if (zendeskCategoryId > 0) {
	zendeskCategory = ZendeskCategoryLocalServiceUtil.getZendeskCategory(zendeskCategoryId);
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
%>

<portlet:actionURL name="addZendeskCategory" var="addZendeskCategoryURL">
	<portlet:param name="mvcPath" value="/admin/edit_zendesk_category.jsp" />
</portlet:actionURL>

<aui:form action="<%= addZendeskCategoryURL %>" cssClass="container-fluid-1280" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:model-context bean="<%= zendeskCategory %>" model="<%= ZendeskCategory.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input disabled="<%= zendeskCategory != null %>" label="documentation-guide-zip-file" name="documentationKey" />

			<aui:input disabled="<%= zendeskCategory != null %>" label="zendesk-id" name="remoteId" />
		</aui:fieldset>
	</aui:fieldset-group>

	<c:if test="<%= zendeskCategory != null %>">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-zendesk-sections"
			headerNames="section,zendesk-id,zendesk-url"
		>

			<%
			searchContainer.setResults(ZendeskSectionLocalServiceUtil.getZendeskSections(zendeskCategoryId));
			searchContainer.setTotal(ZendeskSectionLocalServiceUtil.getZendeskSectionsCount(zendeskCategoryId));
			%>

			<liferay-ui:search-container-row
				className="com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection"
				escapedModel="<%= true %>"
				keyProperty="zendeskSectionId"
				modelVar="zendeskSection"
			>
				<liferay-ui:search-container-column-text
					name="section"
					property="documentationKey"
				/>

				<liferay-ui:search-container-column-text
					name="zendesk-id"
					property="remoteId"
				/>

				<liferay-ui:search-container-column-text
					name="zendesk-url"
				>
					<aui:a href="<%= zendeskSection.getRemoteHtmlURL() %>" label="<%= zendeskSection.getRemoteHtmlURL() %>" target="blank" />
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</c:if>

	<c:if test="<%= zendeskCategory == null %>">
		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</c:if>
</aui:form>