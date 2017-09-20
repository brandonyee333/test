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

<%
String content = ParamUtil.getString(request, "content");
String editorId = renderResponse.getNamespace() + ParamUtil.getString(request, "editorId");
String name = renderResponse.getNamespace() + ParamUtil.getString(request, "name");
String onFocus = ParamUtil.getString(request, "onFocus");
String placeholder = ParamUtil.getString(request, "placeholder");
String resize = ParamUtil.getString(request, "resize", "both");
%>

<div class="edit editor" id="<%= HtmlUtil.escape(editorId) %>">
	<aui:input cssClass="body" label="<%= content %>" maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name="<%= name %>" onFocus="<%= onFocus %>" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();" placeholder="<%= Validator.isNotNull(placeholder) ? placeholder : StringPool.BLANK %>" style='<%= "resize:" + resize %>' type="textarea" wrap="soft" />

	<div class="preview-container">
		<div class="preview-content"></div>
	</div>

	<div class="lfr-toolbar">
		<div class="bb-code-icons">
			<a class="lfr-button" href="javascript:<portlet:namespace />formatText('<%= HtmlUtil.escape(name) %>', 'b');" title="<liferay-ui:message key="bold" />">
				<img alt="<liferay-ui:message key="bold" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/bold.png" />
			</a>

			<a class="lfr-button" href="javascript:<portlet:namespace />formatText('<%= HtmlUtil.escape(name) %>', 'i');" title="<liferay-ui:message key="italic" />">
				<img alt="<liferay-ui:message key="italic" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/italic.png" />
			</a>

			<a class="lfr-button" href="javascript:<portlet:namespace />formatText('<%= HtmlUtil.escape(name) %>', 'u');" title="<liferay-ui:message key="underline" />">
				<img alt="<liferay-ui:message key="underline" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/underline.png" />
			</a>

			<a class="lfr-button" href="javascript:<portlet:namespace />insertURL('<%= HtmlUtil.escape(name) %>', 'url');" title="<liferay-ui:message key="url" />">
				<img alt="<liferay-ui:message key="url" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/link.png" />
			</a>

			<a class="lfr-button " href="javascript:<portlet:namespace />formatText('<%= HtmlUtil.escape(name) %>', 'quote');" title="<liferay-ui:message key="quote" />">
				<img alt="<liferay-ui:message key="quote" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/quote.png" />
			</a>

			<a class="lfr-button" href="javascript:<portlet:namespace />formatText('<%= HtmlUtil.escape(name) %>', 'code');" title="<liferay-ui:message key="code" />">
				<img alt="<liferay-ui:message key="code" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/code.png" />
			</a>

			<a class="lfr-button" onClick="var commentHelpWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/2/ticket_comment_help.jsp" /></portlet:renderURL>', 'comment_help', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); commentHelpWindow.focus();" title='<liferay-ui:message key="help" />'>
				<img alt="<liferay-ui:message key="help" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/help.png" />
			</a>

			<span class="edit-icon">
				<a class="lfr-button" href="javascript:<portlet:namespace />toggleEditorPreview('<%= HtmlUtil.escape(editorId) %>', false);" title="<liferay-ui:message key="edit" />">
					<img alt="<liferay-ui:message key="edit" />" src="<%= themeDisplay.getPathThemeImages() %>/common/edit.png" />
				</a>
			</span>
			<span class="preview-icon">
				<a class="lfr-button" href="javascript:<portlet:namespace />toggleEditorPreview('<%= HtmlUtil.escape(editorId) %>', true);" title="<liferay-ui:message key="preview" />">
					<img alt="<liferay-ui:message key="preview" />" src="<%= PortalUtil.getPathContext(request) %>/support/2/images/preview.png" />
				</a>
			</span>
		</div>
	</div>
</div>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("bbcode_editor.jsp-javaScriptIncluded")) %>'>

	<%
	request.setAttribute("bbcode_editor.jsp-javaScriptIncluded", Boolean.TRUE.toString());
	%>

	<%@ include file="/support/2/javascript/bbcode_editor_js.jspf" %>
</c:if>