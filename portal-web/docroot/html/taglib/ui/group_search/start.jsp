<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<portlet:defineObjects />

<%
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:group-search:portletURL");
RowChecker rowChecker = (RowChecker)request.getAttribute("liferay-ui:group-search:rowChecker");
LinkedHashMap<String, Object> groupParams = (LinkedHashMap<String, Object>)request.getAttribute("liferay-ui:group-search:groupParams");

GroupSearch searchContainer = new GroupSearch(renderRequest, portletURL);

request.setAttribute(WebKeys.SEARCH_CONTAINER, searchContainer);

searchContainer.setRowChecker(rowChecker);

GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

int total = GroupLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), groupParams);

searchContainer.setTotal(total);

List<Group> results = GroupLocalServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), groupParams, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);

searchContainer.setTotal(total);
%>

<liferay-ui:input-search />