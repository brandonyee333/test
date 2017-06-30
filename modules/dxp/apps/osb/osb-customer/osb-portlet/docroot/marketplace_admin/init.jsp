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

<%@ page import="com.liferay.ecommerce.util.comparator.ECDocumentEntryCreateDateComparator" %><%@
page import="com.liferay.osb.marketplaceadmin.search.AppEntryDisplayTerms" %><%@
page import="com.liferay.osb.marketplaceadmin.search.AppEntrySearch" %><%@
page import="com.liferay.osb.marketplaceadmin.search.AppEntrySearchTerms" %><%@
page import="com.liferay.osb.marketplaceadmin.search.CorpEntryDisplayTerms" %><%@
page import="com.liferay.osb.marketplaceadmin.search.CorpEntrySearch" %><%@
page import="com.liferay.osb.marketplaceadmin.search.CorpEntrySearchTerms" %><%@
page import="com.liferay.osb.marketplaceadmin.util.MarketplaceAdminDiscussionUtil" %><%@
page import="com.liferay.osb.marketplaceadmin.util.MarketplaceAdminReportUtil" %><%@
page import="com.liferay.osb.marketplaceadmin.util.MarketplaceAdminUtil" %><%@
page import="com.liferay.osb.util.comparator.AssetReceiptCreateDateComparator" %><%@
page import="com.liferay.osb.util.comparator.DeveloperEntryNameComparator" %><%@
page import="com.liferay.portal.kernel.parsers.bbcode.BBCodeTranslatorUtil" %><%@
page import="com.liferay.portlet.asset.NoSuchEntryException" %><%@
page import="com.liferay.portlet.messageboards.model.MBMessage" %>

<%!
private String _getLocalizedDisplay(Map<Locale, String> map, Locale locale) {
	StringBundler sb = new StringBundler();

	for (Map.Entry<Locale, String> entry : map.entrySet()) {
		Locale curLocale = entry.getKey();
		String curValue = entry.getValue();

		if (Validator.isNull(curValue)) {
			continue;
		}

		sb.append("<div><strong>");
		sb.append(curLocale.getDisplayName(locale));
		sb.append("</strong></div><div>");
		sb.append(HtmlUtil.escape(curValue));
		sb.append("</div>");
	}

	return sb.toString();
}
%>