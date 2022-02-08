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

<liferay-util:buffer
	var="html"
>
	<liferay-util:include page="/html/portlet/wiki/view.portal.jsp" />
</liferay-util:buffer>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WebKeys.WIKI_PAGE);
%>

<c:if test="<%= (wikiPage != null) && WikiPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_NODE) %>">
	<liferay-util:buffer
		var="customHTML"
	>
		<c:choose>
			<c:when test="<%= _isSpam(wikiPage) %>">
				<portlet:actionURL var="notSpamURL">
					<portlet:param name="struts_action" value="/wiki/edit_page" />
					<portlet:param name="<%= Constants.CMD %>" value="updateSummary" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="pageId" value="<%= String.valueOf(wikiPage.getPageId()) %>" />
					<portlet:param name="spam" value="<%= String.valueOf(Boolean.FALSE) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../mail/compose"
					label="<%= true %>"
					message="not-spam"
					url="<%= notSpamURL %>"
				/>
			</c:when>
			<c:when test="<%= !_isPendingApproval(wikiPage) %>">
				<portlet:actionURL var="markAsSpamURL">
					<portlet:param name="struts_action" value="/wiki/edit_page" />
					<portlet:param name="<%= Constants.CMD %>" value="updateSummary" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="pageId" value="<%= String.valueOf(wikiPage.getPageId()) %>" />
					<portlet:param name="spam" value="<%= String.valueOf(Boolean.TRUE) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					image="../mail/delete"
					label="<%= true %>"
					message="mark-as-spam"
					url="<%= markAsSpamURL %>"
				/>
			</c:when>
		</c:choose>
	</liferay-util:buffer>

	<%
	int x = html.indexOf("<div class=\"page-actions top-actions\">");

	if (x > 0) {
		StringBundler sb = new StringBundler(3);

		sb.append(html.substring(0, x + 38));
		sb.append(customHTML);
		sb.append(html.substring(x + 38));

		html = sb.toString();
	}
	%>

</c:if>

<c:if test="<%= (_isSpam(wikiPage) || _isPendingApproval(wikiPage)) && !themeDisplay.isSignedIn() %>">
	<liferay-util:buffer
		var="spamContentEndHTML"
	>
		<span id="<portlet:namespace />spamContentEnd"></span>
	</liferay-util:buffer>

	<liferay-util:buffer
		var="spamContentStartHTML"
	>
		<span id="<portlet:namespace />spamContentStart"></span>
	</liferay-util:buffer>

	<%
	int x = html.indexOf(spamContentStartHTML.trim());
	int y = html.indexOf(spamContentEndHTML.trim());

	if ((x > 0) && (y > 0)) {
		String trimmedSpamContentEndHTML = spamContentEndHTML.trim();

		html = html.substring(
			0, x
		).concat(
			html.substring(y + trimmedSpamContentEndHTML.length())
		);
	}
	%>

</c:if>

<%= html %>