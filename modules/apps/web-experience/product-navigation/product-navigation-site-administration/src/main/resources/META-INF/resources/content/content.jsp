<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
PanelCategory panelCategory = (PanelCategory)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY);
PanelCategoryHelper panelCategoryHelper = (PanelCategoryHelper)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY_HELPER);
%>

<liferay-application-list:panel-category
	panelCategory="<%= panelCategory %>"
	showBody="<%= false %>"
>

	<%
	Group curSite = themeDisplay.getSiteGroup();

	List<Layout> scopeLayouts = LayoutLocalServiceUtil.getScopeGroupLayouts(curSite.getGroupId());
	%>

	<c:choose>
		<c:when test="<%= scopeLayouts.isEmpty() %>">
			<liferay-application-list:panel-category-body
				panelCategory="<%= panelCategory %>"
			/>
		</c:when>
		<c:otherwise>

			<%
			PanelAppRegistry panelAppRegistry = (PanelAppRegistry)request.getAttribute(ApplicationListWebKeys.PANEL_APP_REGISTRY);

			List<PanelApp> panelApps = panelAppRegistry.getPanelApps(panelCategory, themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroup());
			%>

			<c:if test="<%= !panelApps.isEmpty() %>">
				<ul class="nav nav-equal-height nav-nested">
					<li>
						<div class="scope-selector">

							<%
							Group curScopeGroup = themeDisplay.getScopeGroup();
							%>

							<span class="scope-name">
								<c:choose>
									<c:when test="<%= curScopeGroup.isLayout() %>">
										<%= curScopeGroup.getDescriptiveName(locale) %> (<liferay-ui:message key="scope" />)
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="default-scope" />
									</c:otherwise>
								</c:choose>
							</span>
							<span class="nav-equal-height-heading-field">
								<div class="dropdown">
									<a aria-expanded="false" class="dropdown-toggle icon-monospaced" data-toggle="dropdown" href="javascript:;">
										<aui:icon image="cog" markupView="lexicon" />
									</a>

									<%
									String portletId = themeDisplay.getPpid();

									if (Validator.isNull(portletId) || !panelCategoryHelper.containsPortlet(portletId, PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT, permissionChecker, curSite)) {
										portletId = panelCategoryHelper.getFirstPortletId(PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT, permissionChecker, curSite);
									}

									PortletURL portletURL = PortalUtil.getControlPanelPortletURL(request, curSite, portletId, 0, 0, PortletRequest.RENDER_PHASE);
									%>

									<ul class="dropdown-menu dropdown-menu-center">
										<li class="<%= (curScopeGroup.getGroupId() == curSite.getGroupId()) ? "active" : StringPool.BLANK %>">
											<a class="truncate-text" href="<%= portletURL.toString() %>">
												<liferay-ui:message key="default-scope" />
											</a>
										</li>

										<%
										for (Layout curScopeLayout : scopeLayouts) {
											Group scopeGroup = curScopeLayout.getScopeGroup();

											if (Validator.isNull(portletId) || !panelCategoryHelper.containsPortlet(portletId, PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT, permissionChecker, scopeGroup)) {
												portletId = panelCategoryHelper.getFirstPortletId(PanelCategoryKeys.SITE_ADMINISTRATION_CONTENT, permissionChecker, scopeGroup);
											}

											if (Validator.isNull(portletId)) {
												continue;
											}

											portletURL = PortalUtil.getControlPanelPortletURL(request, scopeGroup, portletId, 0, 0, PortletRequest.RENDER_PHASE);
										%>

											<li class="<%= (curScopeGroup.getGroupId() == scopeGroup.getGroupId()) ? "active" : StringPool.BLANK %>">
												<a class="truncate-text" href="<%= portletURL.toString() %>">
													<liferay-ui:message key="<%= HtmlUtil.escape(curScopeLayout.getName(locale)) %>" />
												</a>
											</li>

										<%
										}
										%>

									</ul>
								</div>
							</span>
						</div>

						<liferay-application-list:panel-category-body
							panelCategory="<%= panelCategory %>"
						/>
					</li>
				</ul>
			</c:if>
		</c:otherwise>
	</c:choose>
</liferay-application-list:panel-category>