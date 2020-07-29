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

<aui:input name="languageIds" type="hidden" value="<%= languageIdsValue %>" />

<div class="account details tab-view">
	<ul class="lfr-nav nav nav-tabs">
		<li class="tab" data-content="<portlet:namespace />supportLanguagesContent" id="<portlet:namespace />supportLanguages">
			<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('supportLanguages');" %>' label="support-languages" />
		</li>
	</ul>

	<div class="tab-content">
		<div class="hide tab-content-tab" id="<portlet:namespace />supportLanguagesContent">
			<portlet:renderURL var="selectSupportLanguageURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/admin/select_language.jsp" />
			</portlet:renderURL>

			<%
			String taglibSelectSupportLanguage = "var supportLanguageWindow = window.open('" + selectSupportLanguageURL + "', 'support-language', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); supportLanguageWindow.focus();";
			%>

			<aui:button onClick="<%= taglibSelectSupportLanguage %>" value="add-support-language" />

			<br />

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

					<liferay-ui:search-container-column-text>
						<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('languageIds', '" + languageId + "', '" + renderResponse.getNamespace() + "languageSearchContainer', this);" %>' value="remove" />
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