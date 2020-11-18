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

<%@ include file="/html/taglib/init.jsp" %>

<%
boolean autoFocus = GetterUtil.getBoolean(request.getAttribute("liferay-ui:input-search:autoFocus"));
String buttonLabel = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:buttonLabel"));
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:cssClass"));
String id = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:id"));
String name = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:name"));
String placeholder = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:placeholder"));
boolean showButton = GetterUtil.getBoolean(request.getAttribute("liferay-ui:input-search:showButton"), true);
String title = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-search:title"));

boolean useNamespace = GetterUtil.getBoolean(request.getAttribute("liferay-ui:input-search:useNamespace"), true);

if (!useNamespace) {
	namespace = StringPool.BLANK;
}

String value = ParamUtil.getString(request, name);
%>

<div class="<%= cssClass %> basic-search input-group">
	<div class="input-group-input">
		<label class="hide-accessible" for="<%= namespace + id %>"><%= title %></label>

		<div class="basic-search-slider">
			<button class="basic-search-close btn btn-default" type="button"><aui:icon image="times" markupView="lexicon" /><span class="sr-only"><%= buttonLabel %></span></button>

			<input class="form-control search-query" data-qa-id="searchInput" id="<%= namespace + id %>" name="<%= namespace + name %>" placeholder="<%= placeholder %>" title="<%= title %>" type="text" value="<%= HtmlUtil.escapeAttribute(value) %>" />
		</div>
	</div>

	<c:if test="<%= showButton %>">
		<div class="input-group-btn">
			<button class="btn btn-default" data-qa-id="searchButton" type="submit">
				<aui:icon image="search" markupView="lexicon" />
			</button>
		</div>
	</c:if>
</div>

<c:if test="<%= autoFocus %>">
	<aui:script>
		Liferay.Util.focusFormField('#<%= namespace %><%= id %>');
	</aui:script>
</c:if>

<aui:script>
	var searchInput = $('#<%= namespace %><%= id %>');

	var searchForm = searchInput.closest('form');

	searchForm.on(
		'submit',
		function(event) {
			var keywords = searchInput.val();

			var searchURL = $(this).attr('action');

			if (searchURL.includes('?')) {
				searchURL = searchURL.concat('&<%= namespace %>keywords=' + keywords);
			}
			else {
				searchURL = searchURL.concat('?<%= namespace %>keywords=' + keywords);
			}

			$(this).attr('action', searchURL);
		}
	);

	var viewLinks = $("a[id*='view']");

	viewLinks.each(
		function() {
			var href = $(this).attr('href');

			var keywordsParameterName = '<%= namespace %>keywords';

			var keywordsRegex = new RegExp('%26' + keywordsParameterName + '(%3D[^&]*)?|^' + keywordsParameterName + '(%3D[^&]*)?&?', 'g');

			var hrefKeywordsParameterRemoved = href.replace(keywordsRegex, '');

			$(this).attr('href', hrefKeywordsParameterRemoved);
		}
	);
</aui:script>