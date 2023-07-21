<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.exception.TrashPermissionException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.search.BaseModelSearchResult" %><%@
page import="com.liferay.portal.kernel.search.SortFactoryUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.trash.TrashHandler" %><%@
page import="com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.trash.TrashRenderer" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portlet.trash.model.impl.TrashEntryImpl" %><%@
page import="com.liferay.trash.kernel.exception.RestoreEntryException" %><%@
page import="com.liferay.trash.kernel.exception.TrashEntryException" %><%@
page import="com.liferay.trash.kernel.model.TrashEntry" %><%@
page import="com.liferay.trash.kernel.model.TrashEntryList" %><%@
page import="com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil" %><%@
page import="com.liferay.trash.kernel.service.TrashEntryServiceUtil" %><%@
page import="com.liferay.trash.kernel.util.TrashUtil" %><%@
page import="com.liferay.trash.web.internal.constants.TrashWebKeys" %><%@
page import="com.liferay.trash.web.internal.dao.search.TrashResultRowSplitter" %><%@
page import="com.liferay.trash.web.internal.display.context.TrashContainerModelDisplayContext" %><%@
page import="com.liferay.trash.web.internal.display.context.TrashDisplayContext" %><%@
page import="com.liferay.trash.web.internal.search.EntrySearch" %><%@
page import="com.liferay.trash.web.internal.search.EntrySearchTerms" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
TrashDisplayContext trashDisplayContext = new TrashDisplayContext(request, liferayPortletResponse);

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

String trashEntriesMaxAgeTimeDescription = LanguageUtil.getTimeDescription(locale, TrashUtil.getMaxAge(themeDisplay.getScopeGroup()) * Time.MINUTE, true);

String description = LanguageUtil.get(request, "javax.portlet.description.com_liferay_trash_web_portlet_TrashPortlet") + LanguageUtil.format(request, "entries-that-have-been-in-the-recycle-bin-for-more-than-x-are-automatically-deleted", StringUtil.toLowerCase(trashEntriesMaxAgeTimeDescription), false);

portletDisplay.setDescription(description);

portletDisplay.setShowStagingIcon(false);
%>

<%@ include file="/init-ext.jsp" %>