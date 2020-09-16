<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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