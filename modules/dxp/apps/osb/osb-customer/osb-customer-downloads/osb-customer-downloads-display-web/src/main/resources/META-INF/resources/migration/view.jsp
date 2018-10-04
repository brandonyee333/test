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

<portlet:actionURL name="migrateJournalArticles" var="migrateJournalArticlesURL" />

<div class="container-fluid-1280">
	<aui:form action="<%= migrateJournalArticlesURL %>" method="post" name="fm">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="groupId" />

				<aui:input name="folderId" />

				<aui:button type="submit" value="migrate" />
			</aui:fieldset>
		</aui:fieldset-group>
	</aui:form>
</div>