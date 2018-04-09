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
String tabs1 = ParamUtil.getString(request, "tabs1", "based-on-project");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", tabs1);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<portlet:renderURL var="projectURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="tabs1" value="based-on-project" />
	</portlet:renderURL>

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= projectURL %>" label="based-on-project" selected='<%= tabs1.equals("based-on-project") %>' />
	</aui:nav>

	<portlet:renderURL var="issueURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="tabs1" value="based-on-issue" />
	</portlet:renderURL>

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= issueURL %>" label="based-on-issue" selected='<%= tabs1.equals("based-on-issue") %>' />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test='<%= tabs1.equals("based-on-project") %>'>

			<%
			JIRAProject jiraProject = null;

			try {
				jiraProject = JIRAProjectLocalServiceUtil.getJIRAProject(jiraProjectKey);
			}
			catch (NoSuchJIRAProjectException nsjpe) {
			}
			%>

			<c:choose>
				<c:when test="<%= jiraProject == null %>">
					<div class="portlet-msg-error">Invalid JIRA project selected.</div>
				</c:when>
				<c:otherwise>

					<%
					List<JIRAProjectVersion> jiraProjectVersions = JIRAProjectVersionLocalServiceUtil.getJIRAProjectJIRAProjectVersion(jiraProject.getJiraProjectId(), jiraProjectVersionNamePrefix + StringPool.PERCENT);

					for (JIRAProjectVersion jiraProjectVersion : jiraProjectVersions) {
						jiraProjectVersion = jiraProjectVersion.toEscapedModel();

						ResourceURL viewReleaseNotesURL = renderResponse.createResourceURL();

						viewReleaseNotesURL.setParameter("jiraProjectVersionId", String.valueOf(jiraProjectVersion.getJiraProjectVersionId()));
					%>

						<a href="javascript:<portlet:namespace/>viewReleaseNotes('<%= HtmlUtil.escapeJS(viewReleaseNotesURL.toString()) %>')"><liferay-ui:message arguments="<%= jiraProjectVersion.getName() %>" key="liferay-portal-x-release-notes" /></a><br />

					<%
					}
					%>

				</c:otherwise>
			</c:choose>
		</c:when>
		<c:when test='<%= tabs1.equals("based-on-issue") %>'>
			<%@ include file="/view_release_notes.jspf" %>
		</c:when>
	</c:choose>

	<liferay-util:html-bottom>
		<script type="text/javascript">
			function <portlet:namespace />viewReleaseNotes(url) {
				window.open(url);
			}
		</script>
	</liferay-util:html-bottom>
</div>