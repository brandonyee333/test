<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute(CustomerAdminWebKeys.ACCOUNT_ENTRY);
String detailTab = (String)request.getAttribute("edit_account_entry.jsp-detailTab");

String[] languageIds = StringUtil.split(ParamUtil.getString(request, "languageIds"));

if (ArrayUtil.isEmpty(languageIds) && (accountEntry != null)) {
	languageIds = accountEntry.getLanguageIds();
}

String languageIdsValue = StringPool.BLANK;

for (String languageId : languageIds) {
	languageIdsValue += languageId + StringPool.COMMA;
}
%>

<div class="account details tab-view">
	<ul class="lfr-nav nav nav-tabs">
		<li class="tab" data-content="<portlet:namespace />supportLanguagesContent" id="<portlet:namespace />supportLanguages">
			<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('supportLanguages');" %>' label="support-languages" />
		</li>
	</ul>

	<div class="tab-content">
		<div class="hide tab-content-tab" id="<portlet:namespace />supportLanguagesContent">
			<liferay-ui:search-container
				headerNames="language"
				id="language"
			>
				<liferay-ui:search-container-results
					results="<%= ListUtil.fromArray(languageIds) %>"
				/>

				<liferay-ui:search-container-row
					className="java.lang.String"
					modelVar="languageId"
				>
					<liferay-ui:search-container-column-text
						name="language"
					>
						<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageId)) %>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					paginate="<%= false %>"
				/>
			</liferay-ui:search-container>
		</div>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />reveal',
		function(tab) {
			var A = AUI();

			A.all('.details .tab-content-tab').hide();

			var tabContent = A.one('#' + tab.attr('data-content'));

			tabContent.show();

			A.all('.details .nav-tabs .tab').removeClass('active');

			tab.addClass('active');
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />revealTab',
		function(tabName) {
			var A = AUI();

			var tab;

			if (tabName) {
				tab = A.one('#<portlet:namespace />' + tabName);
			}

			if (!tab) {
				tab = A.one('.details .nav-tabs .tab');
			}

			<portlet:namespace />reveal(tab);
		},
		['aui-base']
	);

	<portlet:namespace />revealTab('<%= HtmlUtil.escape(detailTab) %>');
</aui:script>