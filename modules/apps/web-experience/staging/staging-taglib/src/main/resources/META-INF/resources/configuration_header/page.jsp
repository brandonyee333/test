<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/configuration_header/init.jsp" %>

<aui:fieldset cssClass="options-group" label="<%= label %>" markupView="lexicon">
	<c:choose>
		<c:when test='<%= SessionMessages.contains(liferayPortletRequest, portletDisplay.getId() + "name") %>'>
			<aui:model-context bean="<%= null %>" model="<%= ExportImportConfiguration.class %>" />

			<%
			String description = (String)SessionMessages.get(liferayPortletRequest, portletDisplay.getId() + "description");
			String name = (String)SessionMessages.get(liferayPortletRequest, portletDisplay.getId() + "name");
			%>

			<aui:input label="title" name="name" showRequiredLabel="<%= true %>" value="<%= HtmlUtil.escape(name) %>">
				<aui:validator name="required" />
			</aui:input>

			<aui:input label="description" name="description" value="<%= HtmlUtil.escape(description) %>" />
		</c:when>
		<c:otherwise>
			<aui:model-context bean="<%= exportImportConfiguration %>" model="<%= ExportImportConfiguration.class %>" />

			<aui:input label="title" name="name" showRequiredLabel="<%= true %>">
				<aui:validator name="required" />
			</aui:input>

			<aui:input label="description" name="description" />
		</c:otherwise>
	</c:choose>
</aui:fieldset>