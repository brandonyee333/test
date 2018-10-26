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
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL officialDocumentationSyncURL = renderResponse.createRenderURL();

		officialDocumentationSyncURL.setParameter("tabs1", "official-documentation-sync");
		%>

		<aui:nav-item href="<%= officialDocumentationSyncURL.toString() %>" label="official-documentation-sync" selected='<%= tabs1.equals("official-documentation-sync") %>' />

		<%
		PortletURL autotranslatorURL = renderResponse.createRenderURL();

		autotranslatorURL.setParameter("tabs1", "autotranslator");
		%>

		<aui:nav-item href="<%= autotranslatorURL.toString() %>" label="autotranslator" selected='<%= tabs1.equals("autotranslator") %>' />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test='<%= tabs1.equals("autotranslator") %>'>
			<portlet:actionURL name="autoTranslate" var="autoTranslateURL" />

			<aui:form action="<%= autoTranslateURL %>" method="post">
				<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset>
						<p>
							<liferay-ui:message key="this-will-iterate-through-all-the-specified-type-and-autotranslate-anything-applicable" />
						</p>

						<aui:select label="source-type" name="sourceType">
							<aui:option label="" />
							<aui:option label="<%= ZendeskTranslationConstants.SOURCE_TYPE_ARTICLE %>" />
							<aui:option label="<%= ZendeskTranslationConstants.SOURCE_TYPE_CATEGORY %>" />
							<aui:option label="<%= ZendeskTranslationConstants.SOURCE_TYPE_SECTION %>" />
						</aui:select>

						<aui:button type="submit" value="submit" />
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>
		</c:when>
		<c:otherwise>
			<h1>
				<liferay-ui:message key="zendesk-categories" />
			</h1>

			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-zendesk-categories"
				headerNames="documentation-guide-zip-file,zendesk-id"
				iteratorURL="<%= portletURL %>"
			>

				<%
				searchContainer.setResults(ZendeskCategoryLocalServiceUtil.getZendeskCategories(searchContainer.getStart(), searchContainer.getEnd()));
				searchContainer.setTotal(ZendeskCategoryLocalServiceUtil.getZendeskCategoriesCount());
				%>

				<liferay-ui:search-container-row
					className="com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory"
					escapedModel="<%= true %>"
					keyProperty="zendeskCategoryId"
					modelVar="zendeskCategory"
				>
					<portlet:renderURL var="rowURL">
						<portlet:param name="mvcPath" value="/admin/edit_zendesk_category.jsp" />
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="documentation-guide-zip-file"
						property="documentationKey"
					/>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="article-labels"
					>
						<%= StringUtil.replace(zendeskCategory.getArticleLabels(), StringPool.NEW_LINE, "<br />") %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="zendesk-id"
						property="remoteId"
					/>

					<liferay-ui:search-container-column-text
						align="right"
					>
						<liferay-ui:icon-menu>
							<portlet:renderURL var="documentationImportURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
								<portlet:param name="mvcPath" value="/admin/documentation_import.jsp" />
								<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
								<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
							</portlet:renderURL>

							<liferay-ui:icon
								message="import-documentation-guide"
								url="<%= documentationImportURL %>"
							/>

							<c:if test="<%= ZendeskSectionLocalServiceUtil.getZendeskSectionsCount(zendeskCategory.getZendeskCategoryId()) <= 0 %>">
								<portlet:actionURL name="deleteZendeskCategory" var="deleteZendeskCategoryURL">
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon-delete
									message="remove"
									url="<%= deleteZendeskCategoryURL %>"
								/>
							</c:if>
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>

			<liferay-frontend:add-menu>
				<portlet:renderURL var="editZendeskCategoryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/admin/edit_zendesk_category.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				</portlet:renderURL>

				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add-zendesk-category") %>'
					url="<%= editZendeskCategoryURL.toString() %>"
				/>
			</liferay-frontend:add-menu>
		</c:otherwise>
	</c:choose>
</div>