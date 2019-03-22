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

<%@ include file="/init.jsp" %>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/artifact_versions" var="refinedArtifactVersionsURL">
	<portlet:param name="changesOnly" value="true" />
	<portlet:param name="fromFixPackVersion" value="2.0" />
	<portlet:param name="keywords" value="apache" />
	<portlet:param name="product" value="dxp" />
	<portlet:param name="productVersion" value="7.0" />
	<portlet:param name="owners" value="1,2" />
	<portlet:param name="toFixPackVersion" value="5.0" />
</liferay-portlet:resourceURL>

<strong>Refinement Endpoint:</strong> <%= refinedArtifactVersionsURL %>

<br />

<strong>Owners Filter:</strong> <%= releaseToolDisplayContext.getArtifactVersionFiltersJSONArray() %>

<h2>
	<liferay-ui:message key="module-changes" />
</h2>

<%
JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchArticle(themeDisplay.getScopeGroupId(), moduleChangesJournalArticleId);
%>

<c:if test="<%= journalArticle != null %>">

	<%
	JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
	%>

	<h5 class="secondary-text-color section-subtitle">
		<%= journalArticleDisplay.getContent() %>
	</h5>
</c:if>

<%
JSONObject jsonObject = artifactVersionSearcher.search(renderRequest, renderResponse);
%>

<strong>Results:</strong> <%= HtmlUtil.escape(jsonObject.toString()) %>