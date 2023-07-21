<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String refererPortletName = ParamUtil.getString(request, "refererPortletName");

JournalArticle article = journalContentDisplayContext.getArticle();
%>

<aui:input id='<%= refererPortletName + "ddmTemplateKey" %>' name='<%= refererPortletName + "preferences--ddmTemplateKey--" %>' type="hidden" useNamespace="<%= false %>" value="<%= journalContentDisplayContext.isDefaultTemplate() ? StringPool.BLANK : journalContentDisplayContext.getDDMTemplateKey() %>" />

<div class="article-preview row">
	<div class="col-md-3 col-sm-6 col-xs-12">
		<p class="text-muted"><liferay-ui:message key="layout.types.article" /></p>

		<div class="article-preview-content-container">
			<c:if test="<%= article != null %>">
				<liferay-util:include page="/journal_article_resources.jsp" servletContext="<%= application %>" />
			</c:if>
		</div>

		<div class="button-holder">
			<aui:button cssClass="web-content-selector" name="webContentSelector" value='<%= Validator.isNull(article) ? "select" : "change" %>' />
		</div>
	</div>
</div>

<c:if test="<%= article != null %>">
	<div class="row template-preview">
		<liferay-util:include page="/journal_template.jsp" servletContext="<%= application %>" />
	</div>

	<div class="configuration-options-container row">
		<div class="col-md-6 col-sm-6 col-xs-12">
			<aui:fieldset>
				<aui:field-wrapper label="user-tools">
					<liferay-ui:asset-addon-entry-selector
						assetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getEnabledUserToolAssetAddonEntries() %>"
						hiddenInput="preferences--userToolAssetAddonEntryKeys--"
						id="userToolsAssetAddonEntriesSelector"
						selectedAssetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getSelectedUserToolAssetAddonEntries() %>"
						title="select-user-tools"
					/>
				</aui:field-wrapper>

				<aui:field-wrapper label="content-metadata">
					<liferay-ui:asset-addon-entry-selector
						assetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getEnabledContentMetadataAssetAddonEntries() %>"
						hiddenInput="preferences--contentMetadataAssetAddonEntryKeys--"
						id="contentMetadataAssetAddonEntriesSelector"
						selectedAssetAddonEntries="<%= (List<AssetAddonEntry>)(List<?>)journalContentDisplayContext.getSelectedContentMetadataAssetAddonEntries() %>"
						title="select-content-metadata"
					/>
				</aui:field-wrapper>

				<aui:input label="view-count-increment" name="preferences--enableViewCountIncrement--" type="toggle-switch" value="<%= journalContentDisplayContext.isEnableViewCountIncrement() %>" />
			</aui:fieldset>
		</div>
	</div>
</c:if>