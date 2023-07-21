<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
LayoutsPrototypeTreeDisplayContext layoutsTreeDisplayContext = new LayoutsPrototypeTreeDisplayContext(liferayPortletRequest, liferayPortletResponse);
%>

<div class="layout-prototype-tree lfr-tree">
	<div class="tree-container tree-pages">
		<li class="tree-node">
			<div class="tree-node-content <%= layoutsTreeDisplayContext.isLayoutSelected() ? "tree-node-selected" : StringPool.BLANK %>">
				<span class="tree-label">
					<aui:a cssClass="layout-tree" href="<%= layoutsTreeDisplayContext.getLayoutURL() %>" label="<%= HtmlUtil.escape(layoutsTreeDisplayContext.getLayoutName()) %>" />

					<div class="dropdown dropdown-menu-no-arrow layout-tree-options" data-updateable="true">
						<a aria-expanded="false" class="dropdown-toggle icon-monospaced" data-qa-id="pageOptions" data-toggle="dropdown" href="javascript:;">
							<aui:icon image="ellipsis-v" markupView="lexicon" />
						</a>

						<ul class="dropdown-menu dropdown-menu-left-side">
							<li>
								<a class="layout-tree-edit" data-qa-id="editPage" data-updateable="true" href="<%= layoutsTreeDisplayContext.getEditLayoutURL() %>">
									<span aria-hidden="true"><liferay-ui:message key="edit" /></span>
									<span class="sr-only"><liferay-ui:message arguments="<%= HtmlUtil.escape(layoutsTreeDisplayContext.getLayoutName()) %>" key="edit-x" /></span>
								</a>
							</li>
						</ul>
					</div>
				</span>
			</div>
		</li>
	</div>
</div>