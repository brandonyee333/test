<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/article/init.jsp" %>

<%
String tabsNames = "general,display-settings";

kbArticlePortletInstanceConfiguration = ParameterMapUtil.setParameterMap(KBArticlePortletInstanceConfiguration.class, kbArticlePortletInstanceConfiguration, request.getParameterMap(), "preferences--", "--");
%>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="preferences--resourcePrimKey--" type="hidden" value="<%= kbArticlePortletInstanceConfiguration.resourcePrimKey() %>" />

	<div class="portlet-configuration-body-content">
		<liferay-ui:tabs
			names="<%= tabsNames %>"
			refresh="<%= false %>"
			type="tabs nav-tabs-default"
		>
			<liferay-ui:section>
				<div class="container-fluid-1280">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<div class="form-group">

								<%
								String title = StringPool.BLANK;

								KBArticle kbArticle = KBArticleServiceUtil.fetchLatestKBArticle(kbArticlePortletInstanceConfiguration.resourcePrimKey(), WorkflowConstants.STATUS_APPROVED);

								if (kbArticle != null) {
									title = kbArticle.getTitle();
								}
								%>

								<aui:input label="article" name="configurationKBObject" type="resource" value="<%= title %>" />

								<aui:button name="selectKBArticleButton" value="select" />
							</div>
						</aui:fieldset>
					</aui:fieldset-group>
				</div>
			</liferay-ui:section>

			<liferay-ui:section>
				<div class="container-fluid-1280">
					<aui:fieldset-group markupView="lexicon">
						<aui:fieldset>
							<aui:input label="enable-description" name="preferences--enableKBArticleDescription--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleDescription() %>" />

							<aui:input label="enable-ratings" name="preferences--enableKBArticleRatings--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleRatings() %>" />

							<aui:input label="show-asset-entries" name="preferences--showKBArticleAssetEntries--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.showKBArticleAssetEntries() %>" />

							<aui:input label="show-attachments" name="preferences--showKBArticleAttachments--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.showKBArticleAttachments() %>" />

							<aui:input label="enable-related-assets" name="preferences--enableKBArticleAssetLinks--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleAssetLinks() %>" />

							<aui:input label="enable-view-count-increment" name="preferences--enableKBArticleViewCountIncrement--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleViewCountIncrement() %>" />

							<aui:input label="enable-subscriptions" name="preferences--enableKBArticleSubscriptions--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleSubscriptions() %>" />

							<aui:input label="enable-history" name="preferences--enableKBArticleHistory--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticleHistory() %>" />

							<aui:input label="enable-print" name="preferences--enableKBArticlePrint--" type="checkbox" value="<%= kbArticlePortletInstanceConfiguration.enableKBArticlePrint() %>" />

							<liferay-ui:social-bookmarks-settings
								displayPosition="<%= kbArticlePortletInstanceConfiguration.socialBookmarksDisplayPosition() %>"
								displayStyle="<%= kbArticlePortletInstanceConfiguration.socialBookmarksDisplayStyle() %>"
								enabled="<%= kbArticlePortletInstanceConfiguration.enableSocialBookmarks() %>"
								types="<%= kbArticlePortletInstanceConfiguration.socialBookmarksTypes() %>"
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

<aui:script>
	AUI.$('#<portlet:namespace />selectKBArticleButton').on(
		'click',
		function(event) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						destroyOnHide: true,
						modal: true,
						width: 600
					},
					id: '<portlet:namespace />selectKBObject',
					title: '<liferay-ui:message key="select-article" />',

					<liferay-portlet:renderURL portletName="<%= portletResource %>" var="selectKBObjectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcPath" value="/article/select_parent.jsp" />
						<portlet:param name="eventName" value='<%= liferayPortletResponse.getNamespace() + "selectKBObject" %>' />
						<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(KBArticleConstants.getClassName())) %>" />
						<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbArticlePortletInstanceConfiguration.resourcePrimKey()) %>" />
						<portlet:param name="originalParentResourcePrimKey" value="<%= String.valueOf(kbArticlePortletInstanceConfiguration.resourcePrimKey()) %>" />
						<portlet:param name="selectableClassNameIds" value="<%= String.valueOf(PortalUtil.getClassNameId(KBArticleConstants.getClassName())) %>" />
					</liferay-portlet:renderURL>

					uri: '<%= HtmlUtil.escapeJS(selectKBObjectURL) %>'
				},
				function(event) {
					var kbArticleData = {
						idString: 'resourcePrimKey',
						idValue: event.resourceprimkey,
						nameString: 'configurationKBArticle',
						nameValue: event.title
					};

					Liferay.Util.selectFolder(kbArticleData, '<portlet:namespace />');
				}
			);
		}
	);
</aui:script>