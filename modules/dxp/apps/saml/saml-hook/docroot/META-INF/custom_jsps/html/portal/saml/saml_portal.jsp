<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/init.jsp" %>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insert
	flush="false"
	template="/html/common/themes/portal.jsp"
>
	<tiles:put
		name="content"
		value='<%= (String)request.getAttribute("tilesContent") %>'
	/>

	<tiles:put
		name="pop_up"
		value='<%= (String)request.getAttribute("tilesPopUp") %>'
	/>

	<tiles:put
		name="title"
		value='<%= (String)request.getAttribute("tilesTitle") %>'
	/>
</tiles:insert>