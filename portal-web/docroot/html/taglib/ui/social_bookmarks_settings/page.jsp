<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String displayPosition = (String)request.getAttribute("liferay-ui:social-bookmarks-settings:displayPosition");
String displayStyle = (String)request.getAttribute("liferay-ui:social-bookmarks-settings:displayStyle");
boolean enabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:social-bookmarks-settings:enabled"));
String types = (String)request.getAttribute("liferay-ui:social-bookmarks-settings:types");

String[] displayStyles = PropsUtil.getArray(PropsKeys.SOCIAL_BOOKMARK_DISPLAY_STYLES);

if (Validator.isNull(displayStyle)) {
	displayStyle = displayStyles[0];
}
%>

<aui:fieldset>
	<aui:input name="preferences--enableSocialBookmarks--" type="toggle-switch" value="<%= enabled %>" />

	<div class="social-boomarks-options" id="<portlet:namespace />socialBookmarksOptions">
		<aui:select label="display-style" name="preferences--socialBookmarksDisplayStyle--">

			<%
			for (String curDisplayStyle : PropsUtil.getArray(PropsKeys.SOCIAL_BOOKMARK_DISPLAY_STYLES)) {
			%>

				<aui:option label="<%= curDisplayStyle %>" selected="<%= displayStyle.equals(curDisplayStyle) %>" />

			<%
			}
			%>

		</aui:select>

		<aui:select label="display-position" name="preferences--socialBookmarksDisplayPosition--" value="<%= displayPosition %>">
			<aui:option label="top" />
			<aui:option label="bottom" />
		</aui:select>

		<c:if test="<%= Validator.isNotNull(types) %>">
			<aui:field-wrapper label="social-bookmarks">

				<%
				String[] typesArray = StringUtil.split(types);

				for (String type : PropsUtil.getArray(PropsKeys.SOCIAL_BOOKMARK_TYPES)) {
				%>

					<aui:input checked="<%= ArrayUtil.contains(typesArray, type) %>" id='<%= "socialBookmarksTypes" + type %>' ignoreRequestValue="<%= true %>" label="<%= type %>" name="preferences--socialBookmarksTypes--" type="checkbox" value="<%= type %>" />

				<%
				}
				%>

			</aui:field-wrapper>
		</c:if>
	</div>
</aui:fieldset>

<aui:script use="aui-base">
	Liferay.Util.toggleBoxes('<portlet:namespace />enableSocialBookmarks','<portlet:namespace />socialBookmarksOptions');
</aui:script>