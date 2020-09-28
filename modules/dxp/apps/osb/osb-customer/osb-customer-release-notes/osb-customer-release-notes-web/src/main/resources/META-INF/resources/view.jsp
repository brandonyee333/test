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

						<a href="javascript:<portlet:namespace />viewReleaseNotes('<%= HtmlUtil.escapeJS(viewReleaseNotesURL.toString()) %>')"><liferay-ui:message arguments="<%= jiraProjectVersion.getName() %>" key="liferay-portal-x-release-notes" /></a><br />

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