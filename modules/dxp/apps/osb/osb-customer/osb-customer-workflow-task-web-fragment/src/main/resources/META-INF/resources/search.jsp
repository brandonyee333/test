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
String tabs1 = ParamUtil.getString(renderRequest, "tabs1");
%>

<c:choose>
	<c:when test='<%= tabs1.equals("other-assignees") %>'>

		<%
		String accountEntryCode = ParamUtil.getString(request, "accountEntryCode");
		String accountEntryName = ParamUtil.getString(request, "accountEntryName");
		String assigneeClassName = ParamUtil.getString(request, "assigneeClassName");
		String roleName = ParamUtil.getString(request, "roleName");
		int salesforceOpportunityType = ParamUtil.getInteger(request, "salesforceOpportunityType");
		String userEmailAddress = ParamUtil.getString(request, "userEmailAddress");

		Boolean completed = null;

		String completedString = ParamUtil.getString(request, "completed");

		if (Validator.isNotNull(completedString)) {
			completed = GetterUtil.getBoolean(completedString);
		}
		%>

		<liferay-ui:search-toggle
			buttonLabel="search"
			displayTerms="<%= new DisplayTerms(request) %>"
			id="toggle_id_workflow_tasks_search"
			markupView="lexicon"
		>
			<aui:fieldset>
				<aui:input inlineField="<%= true %>" label="project-code" name="accountEntryCode" size="20" value="<%= accountEntryCode %>" />

				<aui:input inlineField="<%= true %>" label="project-name" name="accountEntryName" size="20" value="<%= accountEntryName %>" />

				<aui:select inlineField="<%= true %>" label="assignee-type" name="assigneeClassName" onChange='<%= renderResponse.getNamespace() + "selectAssigneeClassName(this.value);" %>'>
					<aui:option label="" value="" />
					<aui:option label="role" selected="<%= assigneeClassName.equals(Role.class.getName()) %>" value="<%= Role.class.getName() %>" />
					<aui:option label="user" selected="<%= assigneeClassName.equals(User.class.getName()) %>" value="<%= User.class.getName() %>" />
				</aui:select>

				<div class='<%= assigneeClassName.equals(Role.class.getName()) ? "" : "hide" %>' id="<portlet:namespace />assigneeClassNameRole">
					<aui:input inlineField="<%= true %>" name="roleName" size="20" value="<%= roleName %>" />
				</div>

				<div class='<%= assigneeClassName.equals(User.class.getName()) ? "" : "hide" %>' id="<portlet:namespace />assigneeClassNameUser">
					<aui:input inlineField="<%= true %>" name="userEmailAddress" size="20" value="<%= userEmailAddress %>" />
				</div>

				<aui:select inlineField="<%= true %>" label="completed" name="completed">
					<aui:option label="" value="" />
					<aui:option label="yes" selected="<%= (completed != null) && completed %>" value="<%= true %>" />
					<aui:option label="no" selected="<%= (completed != null) && !completed %>" value="<%= false %>" />
				</aui:select>

				<aui:select inlineField="<%= true %>" label="opportunity-type" name="salesforceOpportunityType">
					<aui:option label="" value="" />
					<aui:option label="existing-business" selected="<%= salesforceOpportunityType == 1 %>" value="1" />
					<aui:option label="new-business" selected="<%= salesforceOpportunityType == 2 %>" value="2" />
					<aui:option label="new-project-existing-business" selected="<%= salesforceOpportunityType == 3 %>" value="3" />
					<aui:option label="renewal" selected="<%= salesforceOpportunityType == 4 %>" value="4" />
				</aui:select>
			</aui:fieldset>
		</liferay-ui:search-toggle>

		<aui:script>
			function <portlet:namespace />selectAssigneeClassName(assigneeClassName) {
				var A = AUI();

				if (assigneeClassName == '<%= Role.class.getName() %>') {
					A.one('#<portlet:namespace />assigneeClassNameRole').show();
					A.one('#<portlet:namespace />assigneeClassNameUser').hide();
				}
				else if (assigneeClassName == '<%= User.class.getName() %>') {
					A.one('#<portlet:namespace />assigneeClassNameRole').hide();
					A.one('#<portlet:namespace />assigneeClassNameUser').show();
				}
				else {
					A.one('#<portlet:namespace />assigneeClassNameRole').hide();
					A.one('#<portlet:namespace />assigneeClassNameUser').hide();
				}
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/search.portal.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>