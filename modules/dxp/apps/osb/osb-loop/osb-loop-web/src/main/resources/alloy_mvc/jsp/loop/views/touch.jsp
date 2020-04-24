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

	if (matcher.find()) {
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

<%
	}
}
%>