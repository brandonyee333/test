<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="container-fluid-1280 event-display-portlet">
	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

	<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:panel
			collapsible="<%= true %>"
			extended="<%= true %>"
			persistState="<%= true %>"
			title="display-options"
		>
			<aui:fieldset>
				<aui:select label="how-many-days-to-display" name="preferences--maxDaysDisplayed--" value="<%= maxDaysDisplayed %>">
					<aui:option label="1" value="<%= 1 %>" />
					<aui:option label="2" value="<%= 2 %>" />
					<aui:option label="3" value="<%= 3 %>" />
					<aui:option label="4" value="<%= 4 %>" />
					<aui:option label="5" value="<%= 5 %>" />
				</aui:select>

				<aui:select label="maximum-events-to-display" name="preferences--eventsPerPage--">

					<%
					int[] deltaValues = GetterUtil.getIntegerValues(PrefsPropsUtil.getStringArray(PropsKeys.SEARCH_CONTAINER_PAGE_DELTA_VALUES, StringPool.COMMA));

					for (int pageDeltaValue : deltaValues) {
					%>

						<aui:option label="<%= pageDeltaValue %>" selected="<%= eventsPerPage == pageDeltaValue %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>

		<aui:button type="submit" />
	</aui:form>
</div>