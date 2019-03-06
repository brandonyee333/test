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

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<h2>
						<liferay-ui:message key="tab-description-journal-article-ids" />
					</h2>

					<aui:input label="highlights" name="preferences--highlightsJournalArticleId--" type="text" value="<%= highlightsJournalArticleId %>" />

					<aui:input label="changelog" name="preferences--changelogJournalArticleId--" type="text" value="<%= changelogJournalArticleId %>" />

					<aui:input label="module-changes" name="preferences--moduleChangesJournalArticleId--" type="text" value="<%= moduleChangesJournalArticleId %>" />
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>