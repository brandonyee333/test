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
String editorId = ParamUtil.getString(request, "editorId");
boolean fieldRequired = ParamUtil.getBoolean(request, "fieldRequired");
String fieldRequiredMessage = ParamUtil.getString(request, "fieldRequiredMessage");
boolean fieldRequiredStatus = ParamUtil.getBoolean(request, "fieldRequiredStatus");
int height = ParamUtil.getInteger(request, "height");
String name = ParamUtil.getString(request, "name");
boolean showCounter = ParamUtil.getBoolean(request, "showCounter");

StringBundler enforceTextAreaMaxLengthSB = new StringBundler(4);

enforceTextAreaMaxLengthSB.append(renderResponse.getNamespace());
enforceTextAreaMaxLengthSB.append("enforceTextAreaMaxLength('");
enforceTextAreaMaxLengthSB.append(HtmlUtil.escapeAttribute(editorId));
enforceTextAreaMaxLengthSB.append("');");

StringBundler updateCharactersRemainingSB = new StringBundler(8);

updateCharactersRemainingSB.append(renderResponse.getNamespace());
updateCharactersRemainingSB.append("updateCharactersRemaining('");
updateCharactersRemainingSB.append(HtmlUtil.escapeAttribute(editorId));
updateCharactersRemainingSB.append("', 'charactersRemaining_");
updateCharactersRemainingSB.append(HtmlUtil.escapeAttribute(editorId));
updateCharactersRemainingSB.append("', ");
updateCharactersRemainingSB.append(ModelHintsConstants.TEXTAREA_MAX_LENGTH);
updateCharactersRemainingSB.append(");");

String onInputHandler = StringPool.BLANK;
String onPropertyChangeHandler = StringPool.BLANK;
String textInputHandler = StringPool.BLANK;

if (BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 9)) {
	onPropertyChangeHandler = enforceTextAreaMaxLengthSB.toString();

	if (showCounter) {
		onPropertyChangeHandler += " " + updateCharactersRemainingSB.toString();
	}
}
else if (BrowserSnifferUtil.isSafari(request) && (BrowserSnifferUtil.getMajorVersion(request) < 5)) {
	textInputHandler = enforceTextAreaMaxLengthSB.toString();

	if (showCounter) {
		textInputHandler += " " + updateCharactersRemainingSB.toString();
	}
}
else {
	onInputHandler = enforceTextAreaMaxLengthSB.toString();

	if (showCounter) {
		onInputHandler += " " + updateCharactersRemainingSB.toString();
	}
}

StringBundler sb = new StringBundler(16);

if (Validator.isNotNull(onInputHandler)) {
	sb.append("onInput=\"");
	sb.append(onInputHandler);
	sb.append("\" ");
}

sb.append("onKeyDown=\"Liferay.Util.checkTab(this); Liferay.Util.disableEsc();");

if (showCounter && BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 10)) {
	sb.append(StringPool.SPACE);
	sb.append(updateCharactersRemainingSB.toString());
}

sb.append(StringPool.QUOTE);

if (showCounter && BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 10)) {
	sb.append(" onKeyUp=\"");
	sb.append(updateCharactersRemainingSB.toString());
	sb.append("\"");
}

if (Validator.isNotNull(onPropertyChangeHandler)) {
	sb.append(" onPropertyChange=\"");
	sb.append(onPropertyChangeHandler);
	sb.append("\"");
}

if (Validator.isNotNull(textInputHandler)) {
	sb.append(" textInput=\"");
	sb.append(textInputHandler);
	sb.append("\"");
}

String textareaEventHandlers = StringUtil.trim(sb.toString());
%>

<div class="common-textarea form-group">
	<textarea class="form-control" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(editorId) %>" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(name) %>" <%= textareaEventHandlers %> style="<%= (height > 0) ? ("height: " + height + "px;") : "" %>" wrap="soft"><%= HtmlUtil.escape(content) %></textarea>

	<c:if test="<%= showCounter %>">
		<div class="pull-right">
			<liferay-ui:message key="characters-remaining" />: <span id="<portlet:namespace />charactersRemaining_<%= HtmlUtil.escapeAttribute(editorId) %>"><%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %></span>
		</div>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace />enforceTextAreaMaxLength(textAreaId) {
		var textArea = document.getElementById('<portlet:namespace />' + textAreaId);

		if (textArea.value.length > <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>) {
			textArea.value = textArea.value.substring(0, <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>);
		}
	}

	function <portlet:namespace />updateCharactersRemaining(textAreaId, charactersRemainingId, maxLength) {
		var textArea = document.getElementById('<portlet:namespace />' + textAreaId);
		var charactersRemaining = document.getElementById('<portlet:namespace />' + charactersRemainingId);

		var count = maxLength - textArea.value.length;

		charactersRemaining.innerHTML = count;
	}

	<c:if test="<%= showCounter %>">
		<portlet:namespace />updateCharactersRemaining("<%= HtmlUtil.escape(editorId) %>", "charactersRemaining_<%= HtmlUtil.escape(editorId) %>", <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>);
	</c:if>
</aui:script>