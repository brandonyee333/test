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

<%@ include file="/support/init.jsp" %>

<%
String languageId = LanguageUtil.getLanguageId(request);

Map<Locale, String> gamePlanMap = SupportUtil.getCommentGamePlanMap(SupportUtil.getPortletPreferences());

String gamePlan = GetterUtil.get(gamePlanMap.get(locale), gamePlanMap.get(LocaleUtil.getDefault()));
%>

<div class="unit" style="width: 100%;">
	<div class="unit-content">
		<liferay-ui:tabs names="game-plan" />

		<aui:select inlineField="true" label="language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this.value);" %>'>

			<%
			Locale[] locales = LanguageUtil.getAvailableLocales();

			for (int i = 0; i < locales.length; i++) {
			%>

				<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= languageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

			<%
			}
			%>

		</aui:select>

		<div class="callout-a">
			<div id="<portlet:namespace />gamePlanDisplay">
				<pre><%= HtmlUtil.escape(gamePlan) %></pre>
			</div>

			<input id="<portlet:namespace />gamePlan" type="hidden" value="<%= HtmlUtil.escapeAttribute(gamePlan) %>" />
		</div>
	</div>

	<div>
		<input class="aui-button-input" onClick="<portlet:namespace />chooseGamePlan();" type="button" value="<liferay-ui:message key="choose" />" />

		<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
	</div>
</div>

<aui:script>
	function <portlet:namespace />chooseGamePlan() {
		var gamePlan = document.getElementById('<portlet:namespace />gamePlan').value;

		opener.<portlet:namespace />selectGamePlan(gamePlan);

		window.close();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />updateLanguage',
		function(languageId) {
			var A = AUI();

			var gamePlanDisplay = A.one('#<portlet:namespace />gamePlanDisplay');

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="gamePlan" />',
				{
					data: {
						<portlet:namespace />languageId: languageId
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response && response.gamePlan) {
								gamePlanDisplay.html('<pre>' + response.gamePlan + '</pre>');

								A.one('#<portlet:namespace />gamePlan').val(response.gamePlan);
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>