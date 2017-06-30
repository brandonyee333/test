<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.osb.marketplace.util.MarketplaceCotermUtil" %><%@
page import="com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessage" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessageDisplay" %><%@
page import="com.liferay.portlet.messageboards.model.MBThread" %><%@
page import="com.liferay.portlet.messageboards.model.MBTreeWalker" %><%@
page import="com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil" %><%@
page import="com.liferay.portlet.ratings.NoSuchEntryException" %><%@
page import="com.liferay.portlet.ratings.model.RatingsEntry" %><%@
page import="com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil" %><%@
page import="com.liferay.releasenotes.model.JIRAIssue" %><%@
page import="com.liferay.releasenotes.service.JIRAIssueLocalServiceUtil" %>

<%@ page import="java.util.regex.Matcher" %><%@
page import="java.util.regex.Pattern" %>

<%
String mvcPathParam = MarketplaceUtil.getMVCPathParam(request);

ExpandoBridge userExpandoBridge = user.getExpandoBridge();

Country storeCountry = (Country)request.getAttribute(OSBWebKeys.OSB_STORE_COUNTRY);

long storeCountryId = 0;

if (storeCountry != null) {
	storeCountryId = storeCountry.getCountryId();
}
%>