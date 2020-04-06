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
String portletId = portletDisplay.getRootPortletId();

boolean autoCreate = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":autoCreate"));
String contents = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":contents");
String cssClass = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":cssClass"));
Map<String, Object> data = (Map<String, Object>)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":data");
String editorName = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":editorName");
String initMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":initMethod");
boolean inlineEdit = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":inlineEdit"));
String inlineEditSaveURL = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":inlineEditSaveURL"));
String name = GetterUtil.getString((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":name"));

String onBlurMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onBlurMethod");

if (Validator.isNotNull(onBlurMethod)) {
	onBlurMethod = namespace + onBlurMethod;
}

String onChangeMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onChangeMethod");

if (Validator.isNotNull(onChangeMethod)) {
	onChangeMethod = namespace + onChangeMethod;
}

String onFocusMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onFocusMethod");

if (Validator.isNotNull(onFocusMethod)) {
	onFocusMethod = namespace + onFocusMethod;
}

String onInitMethod = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":onInitMethod");

if (Validator.isNotNull(onInitMethod)) {
	onInitMethod = namespace + onInitMethod;
}

boolean skipEditorLoading = GetterUtil.getBoolean((String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":skipEditorLoading"));
String toolbarSet = (String)request.getAttribute(CKEditorConstants.ATTRIBUTE_NAMESPACE + ":toolbarSet");

if (!inlineEdit) {
	name = namespace + name;
}

JSONObject editorConfigJSONObject = null;

if (data != null) {
	editorConfigJSONObject = (JSONObject)data.get("editorConfig");
}

EditorOptions editorOptions = null;

if (data != null) {
	editorOptions = (EditorOptions)data.get("editorOptions");
}

Map<String, Object> editorOptionsDynamicAttributes = null;

if (editorOptions != null) {
	editorOptionsDynamicAttributes = editorOptions.getDynamicAttributes();
}
%>

<c:if test="<%= !skipEditorLoading %>">
	<liferay-editor:resources
		editorName="<%= editorName %>"
		inlineEdit="<%= inlineEdit %>"
		inlineEditSaveURL="<%= inlineEditSaveURL %>"
	/>
</c:if>

<%
String textareaName = HtmlUtil.escapeAttribute(name);

String modules = "aui-node-base";

if (inlineEdit && Validator.isNotNull(inlineEditSaveURL)) {
	textareaName = textareaName + "_original";

	modules += ",inline-editor-ckeditor";
}
%>

<%
Map<String, Object> defaultEditorData = new HashMap<String, Object>();

defaultEditorData.put("contents", contents);
defaultEditorData.put("className", cssClass);
defaultEditorData.put("editorConfig", Validator.isNotNull(editorConfigJSONObject) ? editorConfigJSONObject : "{}");
defaultEditorData.put("editorName", editorName);
defaultEditorData.put("editorOptions", data.get("editorOptions"));
defaultEditorData.put("initialShowEditor", autoCreate);
defaultEditorData.put("initialToolbarSet", TextFormatter.format(HtmlUtil.escapeJS(toolbarSet), TextFormatter.M));
defaultEditorData.put("initMethod", initMethod);
defaultEditorData.put("inlineEdit", inlineEdit);
defaultEditorData.put("inlineEditSaveURL", inlineEditSaveURL);
defaultEditorData.put("name", name);
defaultEditorData.put("textareaName", textareaName);
%>

<div>
	<react:component
		data="<%= defaultEditorData %>"
		module="editor/DefaultEditor"
	/>
</div>