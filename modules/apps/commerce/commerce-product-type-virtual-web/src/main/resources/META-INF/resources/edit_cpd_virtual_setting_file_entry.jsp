<%--
/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/META-INF/resources/init.jsp" %>

<%
CPDefinitionVirtualSettingDisplayContext cpDefinitionVirtualSettingDisplayContext = (CPDefinitionVirtualSettingDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinitionVirtualSetting cpDefinitionVirtualSetting = cpDefinitionVirtualSettingDisplayContext.getCPDefinitionVirtualSetting();

CPDVirtualSettingFileEntry cpdVirtualSettingFileEntry = cpDefinitionVirtualSettingDisplayContext.getCPDVirtualSettingFileEntry();
%>

<portlet:actionURL name="/cp_definitions/edit_cpd_virtual_setting_file_entry" var="editCPDVirtualSettingFileEntryActionURL" />

<liferay-frontend:side-panel-content
	title='<%= LanguageUtil.get(request, "insert-the-url-or-select-a-file-of-your-virtual-product") %>'
>
	<aui:form action="<%= editCPDVirtualSettingFileEntryActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= cpdVirtualSettingFileEntry == null ? Constants.ADD : Constants.UPDATE %>" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="className" type="hidden" value="<%= cpDefinitionVirtualSetting.getClassName() %>" />
		<aui:input name="classPK" type="hidden" value="<%= cpDefinitionVirtualSetting.getClassPK() %>" />
		<aui:input name="cpDefinitionVirtualSettingId" type="hidden" value="<%= cpDefinitionVirtualSetting.getCPDefinitionVirtualSettingId() %>" />
		<aui:model-context bean="<%= cpdVirtualSettingFileEntry %>" model="<%= CPDVirtualSettingFileEntry.class %>" />

		<commerce-ui:panel
			title='<%= LanguageUtil.get(request, "details") %>'
		>
			<aui:button name="selectFile" value="select" />

			<aui:input name="url" />
			<aui:input name="version" />
		</commerce-ui:panel>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="save" />

			<aui:button cssClass="btn-lg" type="cancel" />
		</aui:button-row>
	</aui:form>
</liferay-frontend:side-panel-content>