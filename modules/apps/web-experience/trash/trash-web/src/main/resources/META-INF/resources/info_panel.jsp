<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<TrashEntry> trashEntries = (List<TrashEntry>)request.getAttribute(TrashWebKeys.TRASH_ENTRIES);
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(trashEntries) %>">
		<c:choose>
			<c:when test="<%= trashEntries.size() == 1 %>">

				<%
				TrashEntry trashEntry = trashEntries.get(0);

				TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashEntry.getClassName());

				TrashRenderer trashRenderer = trashHandler.getTrashRenderer(trashEntry.getClassPK());
				%>

				<div class="sidebar-header">
					<ul class="sidebar-header-actions">
						<li>
							<c:choose>
								<c:when test="<%= Validator.isNotNull(trashRenderer.renderActions(renderRequest, renderResponse)) %>">
									<liferay-util:include page="<%= trashRenderer.renderActions(renderRequest, renderResponse) %>" servletContext="<%= application %>" />
								</c:when>
								<c:when test="<%= trashEntry.getRootEntry() == null %>">

									<%
									request.setAttribute(TrashWebKeys.TRASH_ENTRY, trashEntry);
									%>

									<liferay-util:include page="/entry_action.jsp" servletContext="<%= application %>" />
								</c:when>
								<c:otherwise>

									<%
									request.setAttribute(TrashWebKeys.TRASH_RENDERER, trashRenderer);
									%>

									<liferay-util:include page="/view_content_action.jsp" servletContext="<%= application %>" />
								</c:otherwise>
							</c:choose>
						</li>
					</ul>

					<h4><%= HtmlUtil.escape(trashRenderer.getTitle(locale)) %></h4>
				</div>

				<aui:nav-bar cssClass="navbar-no-collapse" markupView="lexicon">
					<aui:nav collapsible="<%= false %>" cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="type" /></h5>

					<p>
						<%= ResourceActionsUtil.getModelResource(locale, trashEntry.getClassName()) %>
					</p>

					<h5><liferay-ui:message key="removed-date" /></h5>

					<p>
						<%= dateFormatDateTime.format(trashEntry.getCreateDate()) %>
					</p>

					<h5><liferay-ui:message key="removed-by" /></h5>

					<p>
						<%= HtmlUtil.escape(trashEntry.getUserName()) %>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="sidebar-header">
					<h4><liferay-ui:message arguments="<%= trashEntries.size() %>" key="x-items-are-selected" /></h4>
				</div>

				<aui:nav-bar cssClass="navbar-no-collapse" markupView="lexicon">
					<aui:nav collapsible="<%= false %>" cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="num-of-items" /></h5>

					<p>
						<%= trashEntries.size() %>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<div class="sidebar-header">
			<h4><liferay-ui:message key="home" /></h4>
		</div>

		<aui:nav-bar cssClass="navbar-no-collapse" markupView="lexicon">
			<aui:nav collapsible="<%= false %>" cssClass="navbar-nav">
				<aui:nav-item label="details" selected="<%= true %>" />
			</aui:nav>
		</aui:nav-bar>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="num-of-items" /></h5>

			<p>
				<%= TrashEntryLocalServiceUtil.getEntriesCount(themeDisplay.getScopeGroupId()) %>
			</p>
		</div>
	</c:otherwise>
</c:choose>