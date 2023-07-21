<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dropdown/init.jsp" %>

<div class="dropdown ${cssClass}">
	<aui:a cssClass="dropdown-toggle manage-page-button testray-tooltip-trigger" data-toggle="dropdown" href="javascript:;" title="${label}">
		<aui:icon image="${icon}" />
	</aui:a>

	<ul class="dropdown-menu dropdown-menu-right testray-menu">