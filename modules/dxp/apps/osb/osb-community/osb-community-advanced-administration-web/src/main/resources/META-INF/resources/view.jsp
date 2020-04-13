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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= portletURL.toString() %>" label="sites" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<div class="alert alert-warning">
		<liferay-ui:message key="regenerating-a-site-will-reset-any-configurations-made-to-any-pages-and-portlets" />
	</div>

	<portlet:actionURL name="/regenerate_site" var="regenerateSiteURL">
		<portlet:param name="mvcRenderCommandName" value="/view" />
	</portlet:actionURL>

	<aui:form action="<%= regenerateSiteURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<div class="lfr-form-content">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:select label="site" name="groupId">

						<%
						Group guestGroup = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST);
						%>

						<aui:option label="<%= guestGroup.getName() %>" value="<%= guestGroup.getGroupId() %>" />

						<%
						List<Group> docProjectGroups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), DocProject.class.getName(), GroupConstants.DEFAULT_PARENT_GROUP_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

						for (Group docProjectGroup : docProjectGroups) {
						%>

							<aui:option label="<%= docProjectGroup.getName() %>" value="<%= docProjectGroup.getGroupId() %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select label="generator" name="siteGeneratorKey">

						<%
						Collection<SiteGenerator> siteGenerators = SiteGeneratorRegistryUtil.getSiteGenerators();

						for (SiteGenerator siteGenerator : siteGenerators) {
						%>

							<aui:option label="<%= siteGenerator.getName() %>" value="<%= siteGenerator.getKey() %>" />

						<%
						}
						%>

					</aui:select>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="regenerate" />
		</aui:button-row>
	</aui:form>
</div>