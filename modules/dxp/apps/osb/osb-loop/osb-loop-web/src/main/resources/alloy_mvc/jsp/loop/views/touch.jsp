<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %>

<%@ page import="java.util.Set" %><%@
page import="java.util.regex.Matcher" %><%@
page import="java.util.regex.Pattern" %>

<%
Set<String> paths = application.getResourcePaths("/com/liferay/osb/loop/web/internal/controller");

Pattern pattern = Pattern.compile("\\/([a-zA-Z]+)Controller\\.class");

for (String path : paths) {
	Matcher matcher = pattern.matcher(path);
%>

	<c:if test="<%= matcher.find() %>">

		<%
		String controllerName = matcher.group(1);

		String[] controllerNames = controllerName.split("(?=\\p{Upper})");

		for (int i = 0; i < controllerNames.length; i++) {
			controllerNames[i] = StringUtil.lowerCaseFirstLetter(controllerNames[i]);
		}

		controllerName = StringUtil.merge(controllerNames, StringPool.UNDERLINE);
		%>

		<portlet:resourceURL var="resourceURL">
			<portlet:param name="controller" value="<%= controllerName %>" />
			<portlet:param name="action" value="touch" />
		</portlet:resourceURL>

		<iframe height="0" src="<%= resourceURL %>" style="display: none; visibility: hidden;" width="0"></iframe>
	</c:if>

<%
}
%>