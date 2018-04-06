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

<%@ include file="/support/2/init.jsp" %>

<%
String languageId = LanguageUtil.getLanguageId(request);

Map<Locale, String> gamePlanMap = SupportUtil.getCommentGamePlanMap(SupportUtil.getPortletPreferences());

String gamePlan = GetterUtil.get(gamePlanMap.get(locale), gamePlanMap.get(LocaleUtil.getDefault()));
%>

<div class="unit" style="width: 100%;">
	<div>
		<liferay-ui:tabs
			names="game-plan"
		/>

		<aui:select inlineField="<%= true %>" label="language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage(this.value);" %>'>

			<%
			Set<Locale> localesSet = LanguageUtil.getAvailableLocales();

			Locale[] locales = localesSet.toArray(new Locale[localesSet.size()]);

			for (int i = 0; i < locales.length; i++) {
			%>

				<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= languageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

			<%
			}
			%>

		</aui:select>

		<div>
			<div id="<portlet:namespace />gamePlanDisplay">
				<pre><%= HtmlUtil.escape(gamePlan) %></pre>
			</div>

			<aui:input name="gamePlan" type="hidden" value="<%= gamePlan %>" />
		</div>
	</div>

	<div>
		<aui:button onClick='<%= renderResponse.getNamespace() + "chooseGamePlan();" %>' value="choose" />

		<aui:button onClick="window.close();" value="cancel" />
	</div>
</div>

<aui:script>
	function <portlet:namespace />chooseGamePlan() {
		var gamePlanNode = document.getElementById('<portlet:namespace />gamePlan');

		if (gamePlanNode) {
			var gamePlan = gamePlanNode.value;

			opener.<portlet:namespace />selectGamePlan(gamePlan);

			window.close();
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectGamePlan',
		function(gamePlan) {
			var A = AUI();

			var commentBody = A.one('#<portlet:namespace />commentBody0');

			if (commentBody) {
				var commentBodyValue = commentBody.val();

				if ((commentBodyValue != '') && (commentBodyValue.substr(-1) != '\n')) {
					commentBodyValue += '\n';
				}

				commentBodyValue += gamePlan;

				commentBody.val(commentBodyValue);

				var type = A.one('#<portlet:namespace />type');

				if (type) {
					type.val('<%= TicketCommentConstants.TYPE_GAME_PLAN %>');
				}

				var addCommentButton = A.one('#<portlet:namespace />addCommentButton0');

				if (addCommentButton) {
					addCommentButton.hide();
				}

				var addGamePlanButton = A.one('#<portlet:namespace />addGamePlanButton');

				if (addGamePlanButton) {
					addGamePlanButton.hide();
				}

				var postGamePlanButton = A.one('#<portlet:namespace />postGamePlanButton');

				if (postGamePlanButton) {
					postGamePlanButton.hide();
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateLanguage',
		function(languageId) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="gamePlan" />',
				{
					data: {
						<portlet:namespace />languageId: languageId
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							if (response && response.gamePlan) {
								var gamePlanDisplay = A.one('#<portlet:namespace />gamePlanDisplay');

								if (gamePlanDisplay) {
									gamePlanDisplay.html('<pre>' + response.gamePlan + '</pre>');
								}

								var gamePlanNode = A.one('#<portlet:namespace />gamePlan');

								if (gamePlanNode) {
									gamePlanNode.val(response.gamePlan);
								}
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>