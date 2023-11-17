<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
CPDefinitionVirtualSettingDisplayContext cpDefinitionVirtualSettingDisplayContext = (CPDefinitionVirtualSettingDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CPDefinitionVirtualSetting cpDefinitionVirtualSetting = cpDefinitionVirtualSettingDisplayContext.getCPDefinitionVirtualSetting();

Map<String, String> contextParams = HashMapBuilder.<String, String>put(
	"cpDefinitionId", String.valueOf(cpDefinitionVirtualSetting.getClassPK())
).build();
%>

<%@ include file="/details.jspf" %>