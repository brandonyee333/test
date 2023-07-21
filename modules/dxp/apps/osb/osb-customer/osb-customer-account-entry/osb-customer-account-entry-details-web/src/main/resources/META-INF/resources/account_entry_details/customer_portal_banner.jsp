<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />customerPortalBanner"></div>

<aui:script>
	AccountDetails.render(
		AccountDetails.CustomerPortalBanner,
		{
			customerPortalURL: '<%= accountEntryDetailsConfiguration.customerPortalURL() %>',
			videoTutorialURL: '<%= accountEntryDetailsConfiguration.videoTutorialURL() %>'
		},
		document.getElementById('<portlet:namespace />customerPortalBanner')
	);
</aui:script>