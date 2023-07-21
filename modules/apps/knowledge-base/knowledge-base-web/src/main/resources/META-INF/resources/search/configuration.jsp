<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/search/init.jsp" %>

<%
kbSearchPortletInstanceConfiguration = ParameterMapUtil.setParameterMap(KBSearchPortletInstanceConfiguration.class, kbSearchPortletInstanceConfiguration, request.getParameterMap(), "preferences--", "--");
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<liferay-ui:tabs
			names="general,display-settings"
			refresh="<%= false %>"
			type="tabs nav-tabs-default"
		>
			<liferay-ui:section>
				<div class="container-fluid-1280">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<aui:input label="show-author-column" name="preferences--showKBArticleAuthorColumn--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleAuthorColumn() %>" />

							<aui:input label="show-create-date-column" name="preferences--showKBArticleCreateDateColumn--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleCreateDateColumn() %>" />

							<aui:input label="show-modified-date-column" name="preferences--showKBArticleModifiedDateColumn--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleModifiedDateColumn() %>" />

							<aui:input label="show-views-column" name="preferences--showKBArticleViewsColumn--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleViewsColumn() %>" />
						</aui:fieldset>
					</aui:fieldset-group>
				</div>
			</liferay-ui:section>

			<liferay-ui:section>
				<div class="container-fluid-1280">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<aui:input label="enable-description" name="preferences--enableKBArticleDescription--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleDescription() %>" />

							<aui:input label="enable-ratings" name="preferences--enableKBArticleRatings--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleRatings() %>" />

							<aui:input label="show-asset-entries" name="preferences--showKBArticleAssetEntries--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleAssetEntries() %>" />

							<aui:input label="show-attachments" name="preferences--showKBArticleAttachments--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.showKBArticleAttachments() %>" />

							<aui:input label="enable-related-assets" name="preferences--enableKBArticleAssetLinks--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleAssetLinks() %>" />

							<aui:input label="enable-view-count-increment" name="preferences--enableKBArticleViewCountIncrement--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleViewCountIncrement() %>" />

							<aui:input label="enable-subscriptions" name="preferences--enableKBArticleSubscriptions--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleSubscriptions() %>" />

							<aui:input label="enable-history" name="preferences--enableKBArticleHistory--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticleHistory() %>" />

							<aui:input label="enable-print" name="preferences--enableKBArticlePrint--" type="checkbox" value="<%= kbSearchPortletInstanceConfiguration.enableKBArticlePrint() %>" />

							<liferay-ui:social-bookmarks-settings
								displayPosition="<%= kbSearchPortletInstanceConfiguration.socialBookmarksDisplayPosition() %>"
								displayStyle="<%= kbSearchPortletInstanceConfiguration.socialBookmarksDisplayStyle() %>"
								enabled="<%= kbSearchPortletInstanceConfiguration.enableSocialBookmarks() %>"
								types="<%= kbSearchPortletInstanceConfiguration.socialBookmarksTypes() %>"
							/>
						</aui:fieldset>
					</aui:fieldset-group>
				</div>
			</liferay-ui:section>
		</liferay-ui:tabs>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn btn-lg btn-primary" type="submit" />
	</aui:button-row>
</aui:form>