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
String languageId = ParamUtil.getString(request, "languageId");
%>

<style type="text/css">
	html > .portal-popup {
		padding-top: 0;
	}

	html > .portal-popup #breadcrumbs {
		display: none;
	}

	html > .portal-popup .aui-field-input-text {
		width: 350px;
	}

	html > .portal-popup .portlet-msg-info {
		background-image: none;
		margin: 10px 0 5px;
		padding: 6px;
	}
</style>

<aui:form cssClass="edit-marketing-event-translation" name="fm">
	<div class="portlet-msg-info">
		<img alt="" src='<%= HtmlUtil.escapeAttribute(themeDisplay.getPathThemeImages() + "/language/" + languageId + ".png") %>' />

		<%
		Locale selLocale = LocaleUtil.fromLanguageId(languageId);
		%>

		<%= LanguageUtil.format(pageContext, "translating-marketing-event-to-x", selLocale.getDisplayName(locale)) %>
	</div>

	<aui:fieldset>
		<aui:input label="title" name="titleTranslation" />

		<aui:field-wrapper label="summary">
			<liferay-ui:input-editor
				initMethod="initInputEditorSummaryTranslation"
				name="summaryTranslation"
			/>
		</aui:field-wrapper>

		<aui:button-row>

			<%
			String taglibUpdate = renderResponse.getNamespace() + "updateMarketingEventTranslation('" + languageId + "');";
			%>

			<aui:button onClick="<%= taglibUpdate %>" value="update" />

			<aui:button onClick="self.close();" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	var opener = Liferay.Util.getOpener();

	function <portlet:namespace />initInputEditorSummaryTranslation() {
		return opener.document.<portlet:namespace />fm.<portlet:namespace />translation_<%= languageId %>_summary.value;
	}

	function <portlet:namespace />updateMarketingEventTranslation(languageId) {
		var summary = window.<portlet:namespace />summaryTranslation.getHTML();
		var title = window.<portlet:namespace />titleTranslation.value;

		if ((title && (title.trim() != '')) || (summary && (summary.trim() != ''))) {
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_label').style.display = '';
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_option').style.display = 'none';
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_summary').value = summary;
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_title').value = title;

			var translationLanguageIds = opener.document.getElementById('<portlet:namespace />translation_languageIds').value;

			if (translationLanguageIds.indexOf(languageId) == -1) {
				opener.document.getElementById('<portlet:namespace />translation_languageIds').value = translationLanguageIds + ',' + languageId;
			}
		}
		else {
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_label').style.display = 'none';
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_option').style.display = '';
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_summary').value = '';
			opener.document.getElementById('<portlet:namespace />translation_' + languageId + '_title').value = '';

			var translationLanguageIds = opener.document.getElementById('<portlet:namespace />translation_languageIds').value;

			if (translationLanguageIds.indexOf(languageId) != -1) {
				var newTranslationLanguageIds = translationLanguageIds.split(',');

				newTranslationLanguageIds.splice(newTranslationLanguageIds.indexOf(languageId), 1);

				opener.document.getElementById('<portlet:namespace />translation_languageIds').value = newTranslationLanguageIds.join();
			}
		}

		var dialog = Liferay.Util.getWindow();

		if (dialog) {
			dialog.close();
		}
	}

	window.<portlet:namespace />titleTranslation.value = opener.document.<portlet:namespace />fm.<portlet:namespace />translation_<%= languageId %>_title.value;
</aui:script>