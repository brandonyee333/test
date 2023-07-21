<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
						<liferay-ui:message key="product" />
					</h2>

					<aui:select name="preferences--productName--" required="<%= true %>">
						<aui:option label="<%= ProductConstants.DXP %>" selected="<%= productName.equals(ProductConstants.DXP) %>" value="<%= ProductConstants.DXP %>" />

						<aui:option label="<%= ProductConstants.COMMERCE %>" selected="<%= productName.equals(ProductConstants.COMMERCE) %>" value="<%= ProductConstants.COMMERCE %>" />
					</aui:select>
				</aui:fieldset>

				<aui:fieldset>
					<h2>
						<liferay-ui:message key="tab-description-journal-article-ids" />
					</h2>

					<aui:input label="highlights" name="preferences--highlightsJournalArticleId--" type="text" value="<%= highlightsJournalArticleId %>" />

					<aui:input label="changelog" name="preferences--changelogJournalArticleId--" type="text" value="<%= changelogJournalArticleId %>" />

					<aui:input label="module-changes" name="preferences--moduleChangesJournalArticleId--" type="text" value="<%= moduleChangesJournalArticleId %>" />

					<aui:input label="module-changes-cta" name="preferences--moduleChangesCTAJournalArticleId--" type="text" value="<%= moduleChangesCTAJournalArticleId %>" />
				</aui:fieldset>
			</aui:fieldset-group>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>