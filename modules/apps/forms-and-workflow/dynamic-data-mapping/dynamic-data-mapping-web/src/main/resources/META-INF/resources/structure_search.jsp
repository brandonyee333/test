<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
StructureDisplayTerms displayTerms = new StructureDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_ddm_structure_search"
	markupView="lexicon"
>
	<aui:fieldset cssClass="lfr-ddm-search-form">
		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" inlineField="<%= true %>" name="<%= StructureDisplayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

		<aui:input inlineField="<%= true %>" name="<%= StructureDisplayTerms.DESCRIPTION %>" size="20" value="<%= displayTerms.getDescription() %>" />

		<c:choose>
			<c:when test="<%= Validator.isNull(storageTypeValue) %>">
				<aui:select inlineField="<%= true %>" name="storageType">

					<%
					for (String storageType : ddmDisplayContext.getStorageTypes()) {
					%>

						<aui:option label="<%= storageType %>" selected="<%= storageType.equals(displayTerms.getStorageType()) %>" value="<%= storageType %>" />

					<%
					}
					%>

				</aui:select>
			</c:when>
			<c:otherwise>
				<aui:input name="storageType" type="hidden" value="<%= storageTypeValue %>" />
			</c:otherwise>
		</c:choose>
	</aui:fieldset>
</liferay-ui:search-toggle>