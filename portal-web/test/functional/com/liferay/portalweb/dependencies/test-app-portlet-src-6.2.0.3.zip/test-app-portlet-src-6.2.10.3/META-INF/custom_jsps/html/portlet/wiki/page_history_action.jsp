<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the applicable
 * Liferay software end user license agreement ("License Agreement")
 * found on www.liferay.com/legal/eulas. You may also contact Liferay, Inc.
 * for a copy of the License Agreement. You may not use this file except in
 * compliance with the License Agreement.
 * See the License Agreement for the specific language governing
 * permissions and limitations under the License Agreement, including
 * but not limited to distribution rights of the Software.
 *
 */
--%>

<%@ include file="/html/portlet/wiki/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WikiPage wikiPage = (WikiPage)row.getObject();
%>

<c:if test="<%= (wikiPage.getStatus() == WorkflowConstants.STATUS_APPROVED) && WikiPagePermission.contains(permissionChecker, wikiPage, ActionKeys.UPDATE) && !_isSpam(wikiPage) && !_isPendingApproval(wikiPage) %>">
	<liferay-util:include page="/html/portlet/wiki/page_history_action.portal.jsp" />
</c:if>