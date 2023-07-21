<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@
taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%@
taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/testray" prefix="testray" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.osb.testray.taglib.util.MarkdownUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<%!
public class AlloyLanguageUtil extends LanguageUtil {

	public AlloyLanguageUtil(HttpServletRequest request) {
		_request = request;
	}

	public String format(String pattern, Object... arguments) {
		return format(_request, pattern, arguments);
	}

	public String get(String key) {
		return get(_request, key);
	}

	public String getUnicode(String key) {
		return UnicodeLanguageUtil.get(_request, key);
	}

	private final HttpServletRequest _request;

}
%>

<c:set value="<%= new AlloyLanguageUtil(request) %>" var="AlloyLanguageUtil" />

<c:set scope="request" value="false" var="aui:form:useNamespace" />

<c:set value="" var="htmlNamespace" />

<c:set value="<%= windowState.equals(LiferayWindowState.POP_UP) %>" var="popup" />