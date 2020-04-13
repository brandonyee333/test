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

<%@ include file="/blogs/init.jsp" %>

<liferay-portlet:runtime
	portletName="com_liferay_asset_categories_navigation_web_portlet_AssetCategoriesNavigationPortlet_INSTANCE_XtIsb3vetNum"
/>

<%
String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName");

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/blogs/view");
%>

<portlet:actionURL name="/blogs/edit_entry" var="restoreTrashEntriesURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
</portlet:actionURL>

<liferay-trash:undo
	portletURL="<%= restoreTrashEntriesURL %>"
/>

<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

<%
int pageDelta = GetterUtil.getInteger(blogsPortletInstanceConfiguration.pageDelta());

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, pageDelta, currentURLObj, null, null);

searchContainer.setDelta(pageDelta);
searchContainer.setDeltaConfigurable(false);

int total = 0;
List results = null;

int notPublishedEntriesCount = BlogsEntryServiceUtil.getGroupUserEntriesCount(scopeGroupId, themeDisplay.getUserId(), new int[] {WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_PENDING, WorkflowConstants.STATUS_SCHEDULED});

if ((assetCategoryId != 0) || Validator.isNotNull(assetTagName)) {
	SearchContainerResults<AssetEntry> searchContainerResults = BlogsUtil.getSearchContainerResults(searchContainer);

	searchContainer.setTotal(searchContainerResults.getTotal());

	results = searchContainerResults.getResults();
}
else if ((notPublishedEntriesCount > 0) && mvcRenderCommandName.equals("/blogs/view_not_published_entries")) {
	total = notPublishedEntriesCount;

	searchContainer.setTotal(total);

	results = BlogsEntryServiceUtil.getGroupUserEntries(scopeGroupId, themeDisplay.getUserId(), new int[] {WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_PENDING, WorkflowConstants.STATUS_SCHEDULED}, searchContainer.getStart(), searchContainer.getEnd(), new EntryModifiedDateComparator());

	searchContainer.setResults(results);
}
else {
	int status = WorkflowConstants.STATUS_APPROVED;

	total = BlogsEntryServiceUtil.getGroupEntriesCount(scopeGroupId, status);

	searchContainer.setTotal(total);

	results = BlogsEntryServiceUtil.getGroupEntries(scopeGroupId, status, searchContainer.getStart(), searchContainer.getEnd());
}

searchContainer.setResults(results);
%>

<div class="container">
	<c:if test="<%= notPublishedEntriesCount > 0 %>">
		<aui:nav-bar markupView="lexicon">
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item href="<%= portletURL.toString() %>" label="published" selected='<%= !mvcRenderCommandName.equals("/blogs/view_not_published_entries") %>' />

				<portlet:renderURL var="viewNotPublishedEntriesURL">
					<portlet:param name="mvcRenderCommandName" value="/blogs/view_not_published_entries" />
				</portlet:renderURL>

				<aui:nav-item href="<%= viewNotPublishedEntriesURL %>" label='<%= LanguageUtil.format(resourceBundle, "not-published-x", notPublishedEntriesCount, false) %>' selected='<%= mvcRenderCommandName.equals("/blogs/view_not_published_entries") %>' />
			</aui:nav>
		</aui:nav-bar>
	</c:if>
</div>

<%@ include file="/blogs/view_entries.jspf" %>