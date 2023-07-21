<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long groupId = BeanParamUtil.getLong(journalDisplayContext.getArticle(), request, "groupId", scopeGroupId);
long classNameId = ParamUtil.getLong(request, "classNameId");

DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");
DDMTemplate ddmTemplate = (DDMTemplate)request.getAttribute("edit_article.jsp-template");
%>

<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
<aui:input name="ddmStructureKey" type="hidden" value="<%= ddmStructure.getStructureKey() %>" />
<aui:input name="ddmTemplateKey" type="hidden" value="<%= (ddmTemplate != null) ? ddmTemplate.getTemplateKey() : StringPool.BLANK %>" />

<div class="article-structure">
	<liferay-ui:message key="structure" />:

	<span id="<portlet:namespace />structureNameLabel">
		<c:choose>
			<c:when test="<%= DDMStructurePermission.contains(permissionChecker, ddmStructure, JournalPortletKeys.JOURNAL, ActionKeys.UPDATE) %>">
				<aui:a href="javascript:;" id="editDDMStructure" label="<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>" />
			</c:when>
			<c:otherwise>
				<%= HtmlUtil.escape(ddmStructure.getName(locale)) %>
			</c:otherwise>
		</c:choose>
	</span>

	<c:if test="<%= classNameId == JournalArticleConstants.CLASSNAME_ID_DEFAULT %>">
		<div class="button-holder">
			<aui:button id="selectStructure" value="select" />
		</div>
	</c:if>
</div>

<div class="article-template">
	<liferay-ui:message key="template" />:

	<span id="<portlet:namespace />templateNameLabel">
		<c:if test="<%= (ddmTemplate != null) && ddmTemplate.isSmallImage() %>">
			<img alt="" class="article-template-image" id="<portlet:namespace />templateImage" src="<%= HtmlUtil.escapeAttribute(ddmTemplate.getTemplateImageURL(themeDisplay)) %>" />
		</c:if>

		<c:choose>
			<c:when test="<%= (ddmTemplate != null) && DDMTemplatePermission.contains(permissionChecker, scopeGroupId, ddmTemplate, JournalPortletKeys.JOURNAL, ActionKeys.UPDATE) %>">
				<aui:a href="javascript:;" id="editDDMTemplate" label="<%= HtmlUtil.escape(ddmTemplate.getName(locale)) %>" />
			</c:when>
			<c:otherwise>
				<%= (ddmTemplate != null) ? HtmlUtil.escape(ddmTemplate.getName(locale)) : LanguageUtil.get(request, "none") %>
			</c:otherwise>
		</c:choose>
	</span>

	<div class="button-holder">
		<aui:button id="selectTemplate" value="select" />
	</div>
</div>