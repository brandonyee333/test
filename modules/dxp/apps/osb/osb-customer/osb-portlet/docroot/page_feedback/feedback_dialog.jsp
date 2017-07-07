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
long feedbackEntryId = ParamUtil.getLong(request, "feedbackEntryId");
%>

<portlet:actionURL name="updateFeedbackEntry" var="updateFeedbackEntryURL">
	<portlet:param name="mvcPath" value="/page_feedback/view.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateFeedbackEntryURL %>" method="post" name="fm">
	<input name="<portlet:namespace />feedbackEntryId" type="hidden" value="<%= feedbackEntryId %>" />

	<div class="feedback-container" id="<portlet:namespace />feedbackContainer">
		<div class="comment-container">
			<textarea id="<portlet:namespace />feedbackComments" maxlength="<%= ModelHintsUtil.getMaxLength(FeedbackEntry.class.getName(), "comments") %>" name="<portlet:namespace />comments" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" placeholder="<liferay-ui:message key="optional-please-let-us-know-how-we-can-improve-this-page" />"></textarea>
		</div>

		<div class="button-container">
			<input class="aui-button-input" onClick="Liferay.Util.getWindow().close();" type="button" value="<liferay-ui:message key="no-comment" />" />

			<input class="aui-button-input" onClick="<portlet:namespace />updateFeedbackEntry();" type="button" value="<liferay-ui:message key="submit" />" />
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />updateFeedbackEntry() {
		submitForm(document.<portlet:namespace />fm);

		Liferay.Util.getWindow().close();
	}
</aui:script>