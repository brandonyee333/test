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

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />

<%
String redirect = ParamUtil.getString(request, "redirect");

if (Validator.isNull(redirect)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcRenderCommandName", "/blogs/view");

	redirect = portletURL.toString();
}

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

long entryId = ParamUtil.getLong(request, "entryId", entry.getEntryId());

AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(BlogsEntry.class.getName(), entry.getEntryId());

AssetEntryServiceUtil.incrementViewCounter(assetEntry);

AssetUtil.addLayoutTags(request, AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId()));

request.setAttribute(WebKeys.LAYOUT_ASSET_ENTRY, assetEntry);

request.setAttribute("view_entry_content.jsp-entry", entry);

request.setAttribute("view_entry_content.jsp-assetEntry", assetEntry);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	renderResponse.setTitle(entry.getTitle());
}
%>

<header class="entry-header">
	<div class="container">
		<p class="entry-info">
			<%= HtmlUtil.escape(entry.getUserName()) %> | <%= dateFormatMonth.format(entry.getDisplayDate()) %>
		</p>

		<h1 class="entry-title">
			<%= HtmlUtil.escape(entry.getTitle()) %>
		</h1>
	</div>
</header>

<portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

<aui:form action="<%= editEntryURL %>" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="entryId" type="hidden" value="<%= String.valueOf(entryId) %>" />

	<liferay-util:include page="/blogs/view_entry_content.jsp" servletContext="<%= application %>" />
</aui:form>

<div class="entry-footer">
	<div class="container">
		<c:if test="<%= blogsPortletInstanceConfiguration.enableComments() %>">

			<%
			Discussion discussion = CommentManagerUtil.getDiscussion(user.getUserId(), scopeGroupId, BlogsEntry.class.getName(), entry.getEntryId(), new ServiceContextFunction(request));
			%>

			<c:if test="<%= discussion != null %>">
				<h2>
					<strong><liferay-ui:message arguments="<%= CommentManagerUtil.getCommentsCount(BlogsEntry.class.getName(), entry.getEntryId()) %>" key="x-comments" /></strong>
				</h2>

				<c:if test="<%= PropsValues.BLOGS_TRACKBACK_ENABLED && entry.isAllowTrackbacks() %>">
					<aui:input inlineLabel="left" name="trackbackURL" type="resource" value='<%= PortalUtil.getLayoutFullURL(themeDisplay.getLayout(), themeDisplay, false) + Portal.FRIENDLY_URL_SEPARATOR + "blogs/trackback/" + entry.getUrlTitle() %>' />
				</c:if>

				<liferay-ui:discussion
					className="<%= BlogsEntry.class.getName() %>"
					classPK="<%= entry.getEntryId() %>"
					discussion="<%= discussion %>"
					formName="fm2"
					ratingsEnabled="<%= blogsPortletInstanceConfiguration.enableCommentRatings() %>"
					redirect="<%= currentURL %>"
					userId="<%= entry.getUserId() %>"
				/>
			</c:if>
		</c:if>
	</div>
</div>

<%
PortalUtil.setPageTitle(entry.getTitle(), request);
PortalUtil.setPageDescription(entry.getDescription(), request);

List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(BlogsEntry.class.getName(), entry.getEntryId());

PortalUtil.setPageKeywords(ListUtil.toString(assetTags, AssetTag.NAME_ACCESSOR), request);

PortalUtil.addPortletBreadcrumbEntry(request, entry.getTitle(), currentURL);
%>

<liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#post" />