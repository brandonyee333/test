<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/add_menu/init.jsp" %>

<%
List<AddMenuItem> addMenuItems = (List<AddMenuItem>)request.getAttribute("liferay-frontend:add-menu:addMenuItems");
%>

<c:choose>
	<c:when test="<%= addMenuItems.size() == 1 %>">

		<%
		AddMenuItem addMenuItem = addMenuItems.get(0);

		String id = addMenuItem.getId();

		if (Validator.isNull(id)) {
			id = "menuItem";
		}

		String title = addMenuItem.getLabel();

		if (Validator.isNull(title)) {
			title = LanguageUtil.get(request, "new-item");
		}
		%>

		<a <%= AUIUtil.buildData(addMenuItem.getAnchorData()) %> class="btn btn-action btn-bottom-right btn-primary" data-placement="left" data-qa-id="addButton" data-toggle="tooltip" href="<%= HtmlUtil.escapeAttribute(addMenuItem.getUrl()) %>" id="<%= namespace + id %>" title="<%= HtmlUtil.escapeAttribute(title) %>">
			<aui:icon image="plus" markupView="lexicon" />
		</a>

		<aui:script sandbox="<%= true %>">
			$(document).ready(
				function() {
					$('[data-toggle="tooltip"]').tooltip();
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="btn-action-secondary btn-bottom-right dropdown">
			<button aria-expanded="false" class="btn btn-primary" data-qa-id="addButton" data-toggle="dropdown" type="button">
				<aui:icon image="plus" markupView="lexicon" />
			</button>

			<ul class="dropdown-menu dropdown-menu-left-side-bottom">

				<%
				for (int i = 0; i < addMenuItems.size(); i++) {
					AddMenuItem addMenuItem = addMenuItems.get(i);

					String id = addMenuItem.getId();

					if (Validator.isNull(id)) {
						id = "menuItem" + i;
					}
				%>

					<li>
						<a <%= AUIUtil.buildData(addMenuItem.getAnchorData()) %> href="<%= HtmlUtil.escapeAttribute(addMenuItem.getUrl()) %>" id="<%= namespace + id %>"><%= HtmlUtil.escape(addMenuItem.getLabel()) %></a>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</c:otherwise>
</c:choose>