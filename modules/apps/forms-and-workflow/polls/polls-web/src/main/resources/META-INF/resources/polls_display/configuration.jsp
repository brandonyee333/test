<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/polls_display/init.jsp" %>

<%
questionId = ParamUtil.getLong(request, "questionId", questionId);

List<PollsQuestion> questions = PollsQuestionLocalServiceUtil.getQuestions(scopeGroupId);

if (scopeGroupId != themeDisplay.getCompanyGroupId()) {
	questions = ListUtil.copy(questions);

	questions.addAll(PollsQuestionLocalServiceUtil.getQuestions(themeDisplay.getCompanyGroupId()));
}
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<liferay-ui:error exception="<%= NoSuchQuestionException.class %>" message="the-question-could-not-be-found" />

			<c:choose>
				<c:when test="<%= !questions.isEmpty() %>">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<aui:select label="title" name="preferences--questionId--">
								<aui:option value="" />

								<%
								for (PollsQuestion question : questions) {
									question = question.toEscapedModel();
								%>

									<aui:option label="<%= question.getTitle(locale) %>" selected="<%= questionId == question.getQuestionId() %>" value="<%= question.getQuestionId() %>" />

								<%
								}
								%>

							</aui:select>
						</aui:fieldset>
					</aui:fieldset-group>
				</c:when>
				<c:otherwise>
					<div class="alert alert-info">
						<liferay-ui:message key="there-are-no-available-questions-for-selection" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" disabled="<%= questions.isEmpty() %>" type="submit" />
		<aui:button cssClass="btn-lg" type="cancel" />
	</aui:button-row>
</aui:form>