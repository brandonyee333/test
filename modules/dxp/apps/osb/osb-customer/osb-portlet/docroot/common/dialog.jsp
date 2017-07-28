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
long dialogId = ParamUtil.getLong(request, "id", 0);

boolean border = ParamUtil.getBoolean(request, "border", Boolean.TRUE);
boolean centered = ParamUtil.getBoolean(request, "centered");
boolean close = ParamUtil.getBoolean(request, "close", Boolean.TRUE);
String cssClass = ParamUtil.getString(request, "cssClass");
boolean draggable = ParamUtil.getBoolean(request, "draggable", Boolean.TRUE);
int height = ParamUtil.getInteger(request, "height");
boolean modal = ParamUtil.getBoolean(request, "modal");
String mvcPath = ParamUtil.getString(request, "mvcPath");
String[] paramNames = ParamUtil.getParameterValues(request, "paramNames");
String[] paramValues = ParamUtil.getParameterValues(request, "paramValues");
boolean shadow = ParamUtil.getBoolean(request, "shadow", Boolean.TRUE);
String title = ParamUtil.getString(request, "title");
boolean visible = ParamUtil.getBoolean(request, "visible");
int width = ParamUtil.getInteger(request, "width");

StringBundler contentStyleSB = new StringBundler(6);

if (height > 0) {
	contentStyleSB.append("height: ");
	contentStyleSB.append(height);
	contentStyleSB.append("px;");
}

if (width > 0) {
	contentStyleSB.append(" width: ");
	contentStyleSB.append(width);
	contentStyleSB.append("px;");
}
%>

<c:if test="<%= modal %>">
	<div class="dialog-overlay" id="<%= renderResponse.getNamespace() + "dialogOverlay" + dialogId %>"></div>
</c:if>

<div class="dialog <%= cssClass %>" id="<%= renderResponse.getNamespace() + "dialog" + dialogId %>">
	<div class="<%= border ? "border" : "" %> <%= shadow ? "shadow" : "" %>">
		<c:if test="<%= close || draggable || Validator.isNotNull(title) %>">
			<div class="titlebar" id="<%= renderResponse.getNamespace() + "titlebar" + dialogId %>">
				<c:if test="<%= Validator.isNotNull(title) %>">
					<div class="title" style="<%= (width > 0) ? "max-width: " + (width - 45) + "px" : "" %>"><%= HtmlUtil.escape(title) %></div>
				</c:if>

				<c:if test="<%= close %>">
					<span class="close-button" onClick="<portlet:namespace />closeDialog(<%= dialogId %>);" type="button"></span>
				</c:if>
			</div>
		</c:if>

		<div class="content" id="<%= renderResponse.getNamespace() + "dialogContent" + dialogId %>" style="<%= contentStyleSB.toString() %>">
			<c:if test="<%= Validator.isNotNull(mvcPath) %>">
				<liferay-util:include page="<%= mvcPath %>" servletContext="<%= application %>">
					<c:if test="<%= paramNames.length == paramValues.length %>">

						<%
						for (int i = 0; i < paramNames.length; i++) {
						%>

							<liferay-util:param name="<%= paramNames[i] %>" value="<%= paramValues[i] %>" />

						<%
						}
						%>

					</c:if>
				</liferay-util:include>
			</c:if>
		</div>
	</div>
</div>

<aui:script>
	var dialogX = 0;
	var dialogY = 0
	var cursorX = 0;
	var cursorY = 0;
	var selected = null;

	<c:if test="<%= centered %>">
		<portlet:namespace />centerDialog(<%= dialogId %>);

		window.addEventListener(
			'resize',
			function() {
				<portlet:namespace />centerDialog(<%= dialogId %>);
			}
		);
	</c:if>

	<c:if test="<%= visible %>">
		<portlet:namespace />openDialog(<%= dialogId %>);
	</c:if>

	<c:if test="<%= draggable %>">
		var titlebar = document.getElementById('<portlet:namespace />titlebar' + <%= dialogId %>);

		titlebar.addEventListener(
			'mousedown',
			function() {
				<portlet:namespace />dragInit(<%= dialogId %>);
			}
		);

		window.addEventListener(
			'mousemove',
			function() {
				<portlet:namespace />dragSelectedDialog();
			}
		);

		window.addEventListener(
			'mouseup',
			function() {
				<portlet:namespace />clearDrag(<%= dialogId %>);
			}
		);
	</c:if>

	function <portlet:namespace />centerDialog(dialogId) {
		var dialog = document.getElementById('<portlet:namespace />dialog' + dialogId);

		dialog.classList.add('center');

		dialog.style.marginLeft = -(dialog.offsetWidth / 2) + 'px';
		dialog.style.marginTop = -(dialog.offsetHeight / 2) + 'px';
	}

	function <portlet:namespace />closeDialog(dialogId) {
		var dialog = document.getElementById('<portlet:namespace />dialog' + dialogId);
		var dialogOverlay = document.getElementById('<portlet:namespace />dialogOverlay' + dialogId);

		dialog.classList.remove('visible');

		<c:if test="<%= modal %>">
			dialogOverlay.classList.remove('visible');
		</c:if>
	}

	function <portlet:namespace />clearDrag(dialogId) {
		var titlebar = document.getElementById('<portlet:namespace />titlebar' + dialogId);

		selected = null;

		titlebar.classList.remove('dragging');
	}

	function <portlet:namespace />dragInit(dialogId) {
		var dialog = document.getElementById('<portlet:namespace />dialog' + dialogId);
		var titlebar = document.getElementById('<portlet:namespace />titlebar' + dialogId);

		selected = dialog;

		dialogX = cursorX - selected.offsetLeft;
		dialogY = cursorY - selected.offsetTop;

		titlebar.classList.add('dragging');
	}

	function <portlet:namespace />dragSelectedDialog() {
		cursorX = window.event.clientX;
		cursorY = window.event.clientY;

		if (selected !== null) {
			selected.style.margin = 0;

			selected.style.left = (cursorX - dialogX) + 'px';
			selected.style.top = (cursorY - dialogY) + 'px';
		}
	}

	function <portlet:namespace />openDialog(dialogId) {
		var dialog = document.getElementById('<portlet:namespace />dialog' + dialogId);
		var dialogOverlay = document.getElementById('<portlet:namespace />dialogOverlay' + dialogId);

		<c:if test="<%= modal %>">
			dialogOverlay.classList.add('visible');
		</c:if>

		dialog.classList.add('visible');
	}
</aui:script>