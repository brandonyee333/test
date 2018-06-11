<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/side_navigation/init.jsp" %>

<%
Layout rootLayout = null;

boolean relatedArticlesToggleValue = GetterUtil.getBoolean(SessionClicks.get(request, renderResponse.getNamespace() + "related_articles_toggle_value", "true"));
boolean sideNavToggleValue = GetterUtil.getBoolean(SessionClicks.get(request, renderResponse.getNamespace() + "side_nav_toggle_value", "true"));

boolean viewjournalArticle = OSBCustomerArticlePermission.contains(permissionChecker, OSBCustomerActionKeys.VIEW_JOURNAL_ARTICLE);
%>

<div id="<portlet:namespace />navigationContainer">
	<div class="side-nav-wrapper">
		<div class="side-nav-toggle <%= sideNavToggleValue ? "expanded" : StringPool.BLANK %>">
			<span>
				<c:choose>
					<c:when test="<%= sideNavToggleValue %>">
						<liferay-ui:message key="close" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="browse" />
					</c:otherwise>
				</c:choose>
			</span>
		</div>

		<div class="side-nav-content <%= !sideNavToggleValue ? "hide" : StringPool.BLANK %>">
			<aui:form cssClass="version-selector" name="fm">
				<aui:select label="" name="version" onChange='<%= renderResponse.getNamespace() + "updateNavigation(this.value);" %>'>

					<%
					Map<String, List<Layout>> kbArticleLayouts = KBArticleUtil.getKBArticleLayouts(themeDisplay.getScopeGroupId());

					for (Map.Entry<String, List<Layout>> entry : kbArticleLayouts.entrySet()) {
					%>

						<optgroup label="<%= entry.getKey() %>">

							<%
							for (Layout curRootLayout : entry.getValue()) {
								if (curRootLayout.isHidden()) {
									continue;
								}

								if ((rootLayout == null) || ancestorLayouts.contains(curRootLayout)) {
									rootLayout = curRootLayout;
								}
							%>

								<aui:option label="<%= curRootLayout.getName() %>" selected="<%= rootLayout.equals(curRootLayout) %>" value="<%= curRootLayout.getPlid() %>" />

							<%
							}
							%>

						</optgroup>

					<%
					}
					%>

				</aui:select>
			</aui:form>

			<div class="hide side-nav" id="<portlet:namespace />navigation">
				<ul class="side-nav-tree">

					<%
					request.setAttribute("layout_navigation.jsp-selLayout", rootLayout);
					%>

					<liferay-util:include page="/side_navigation/layout_navigation.jsp" servletContext="<%= application %>" />

					<%
					Layout sideNavigationLayout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/side-navigation");
					%>

					<c:if test="<%= (sideNavigationLayout != null) && sideNavigationLayout.hasChildren() %>">

						<%
						for (Layout childLayout : sideNavigationLayout.getChildren()) {
						%>

							<li class="tree-node <%= childLayout.equals(layout) ? "selected" : StringPool.BLANK %>">
								<a class="<%= childLayout.equals(layout) ? "selectedLink" : StringPool.BLANK %> has-child" href="<%= PortalUtil.getLayoutURL(childLayout, themeDisplay) %>" <%= PortalUtil.getLayoutTarget(childLayout) %>>
									<span><%= childLayout.getName(locale) %></span>
								</a>
							</li>

						<%
						}
						%>

					</c:if>
				</ul>
			</div>

			<%
			Layout kbLayout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/documentation/knowledge-base");
			%>

			<c:if test="<%= viewjournalArticle && (kbLayout != null) %>">

				<%
				String kbLinkHREF = StringPool.SLASH;

				Layout kbLinkLayout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(themeDisplay.getScopeGroupId(), false, "/documentation/search/knowledge-base");

				if (kbLinkLayout != null) {
					kbLinkHREF = PortalUtil.getLayoutURL(kbLinkLayout, themeDisplay);
				}
				%>

				<div class="hide side-nav tree-view">
					<ul class="side-nav-tree">
						<li class="related-articles tree-node <%= (relatedArticlesToggleValue && !kbLayout.equals(layout)) ? "expanded" : StringPool.BLANK %>" id="<portlet:namespace />relatedArticlesNode">
							<div class="tree-node-content">
								<i class="cursor-pointer tree-hitarea <%= (relatedArticlesToggleValue && !kbLayout.equals(layout)) ? "icon-plus" : "icon-minus" %>" onclick="<portlet:namespace />toggleIcon();"></i>

								<span>
									<a class="<%= kbLayout.equals(layout) ? "selected" : StringPool.BLANK %>" href="<%= kbLinkHREF %>">
										<span><%= kbLayout.getName(locale) %></span>
									</a>
								</span>
							</div>

							<ul>
								<li class="tree-node-leaf" id="<portlet:namespace />relatedArticles">
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</c:if>
		</div>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />navigationReady',
		function(event) {
			var A = AUI();

			var navigationContainer = A.one('#<portlet:namespace />navigationContainer');

			if (navigationContainer) {
				var treeView = new A.TreeView(
					{
						contentBox: '.side-nav-tree',
						selectOnToggle: true,
						type: 'normal'
					}
				).render('#<portlet:namespace />navigation');

				var sideNavTree = navigationContainer.one('.side-nav-tree');

				if (sideNavTree) {
					var selected = sideNavTree.all('a.selected');

					selected.each(
						function(item, index, collection) {
							var selectedNode = treeView.getNodeByChild(item);

							selectedNode.expand();
						}
					);
				}

				A.all('.side-nav').show();
			}
		},
		['aui-tree']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />toggleIcon',
		function() {
			var relatedArticlesNode = AUI().one('#<portlet:namespace />relatedArticlesNode');

			var expanded = relatedArticlesNode.hasClass('expanded');

			relatedArticlesNode.toggleClass('expanded', !expanded);

			var icon = relatedArticlesNode.getElementsByTagName('i');

			if (expanded && icon.hasClass('icon-plus')) {
				icon.removeClass('icon-plus');
				icon.addClass('icon-minus');
			}
			else if (!expanded && icon.hasClass('icon-minus')) {
				icon.removeClass('icon-minus');
				icon.addClass('icon-plus');
			}

			Liferay.Store('<portlet:namespace />related_articles_toggle_value', !expanded);
		},
		['liferay-store']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateNavigation',
		function(rootLayoutPlid) {
			var A = AUI();

			A.io.request(
				'<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/side_navigation/layout_navigation.jsp" /></portlet:renderURL>',
				{
					data: {
						<portlet:namespace />kbArticleId: '<%= (kbArticle != null) ? kbArticle.getKbArticleId() : StringPool.BLANK %>',
						<portlet:namespace />redirect: '<%= HtmlUtil.escapeJS(redirect) %>',
						<portlet:namespace />selLayoutPlid: rootLayoutPlid
					},
					on: {
						success: function() {
							var response = this.get('responseData');

							if (response.startsWith('<li')) {
								var sideNavTree = A.one('#<portlet:namespace />navigation .side-nav-tree');

								if (sideNavTree) {
									sideNavTree.html(response);
								}

								<portlet:namespace />navigationReady();
							}
							else {
								window.location.reload();
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>

<aui:script use="anim,aui-base,aui-tree,liferay-store">
	var navigationContainer = A.one('#<portlet:namespace />navigationContainer');

	if (navigationContainer) {
		function <portlet:namespace />initLayout(expanded) {
			<portlet:namespace />updateColumns(expanded);
		}

		function <portlet:namespace />updateColumns(expanded) {
			var columnOne = A.one('#column-1');

			if (columnOne) {
				columnOne.toggleClass('col-md-1', expanded);
				columnOne.toggleClass('col-md-3', !expanded);
			}

			var columnTwo = A.one('#column-2');

			if (columnTwo) {
				columnTwo.toggleClass('col-md-9', !expanded);
				columnTwo.toggleClass('col-md-11', expanded);
			}
		}

		<portlet:namespace />initLayout(!<%= sideNavToggleValue %>);

		var sideNavToggle = navigationContainer.one('.side-nav-toggle');

		if (sideNavToggle) {
			sideNavToggle.on(
				'click',
				function(event) {
					var expanded = sideNavToggle.hasClass('expanded');

					sideNavToggle.toggleClass('expanded', !expanded);

					var sideNavToggleText = sideNavToggle.one('span');

					if (sideNavToggleText) {
						var toggleText = '<liferay-ui:message key="close" />';

						if (expanded) {
							toggleText = '<liferay-ui:message key="browse" />';
						}

						sideNavToggleText.text(toggleText);
					}

					A.getBody().toggleClass('side-nav-hidden', expanded);

					var sideNavContent = navigationContainer.one('.side-nav-content');

					if (sideNavContent) {
						sideNavContent.toggle(!expanded);
					}

					<portlet:namespace />updateColumns(expanded);

					Liferay.Store('<portlet:namespace />side_nav_toggle_value', !expanded);
				}
			);
		}
	}

	<portlet:namespace />navigationReady();
</aui:script>

<c:if test="<%= viewjournalArticle && (kbLayout != null) %>">
	<aui:script use="aui-io-request">

		<%
		String articleId = ParamUtil.getString(renderRequest, "articleId");

		StringBundler sb = new StringBundler();

		if (kbArticle != null) {
			sb.append(kbArticle.getTitle());

			KBArticle parentKBArticle = kbArticle.getParentKBArticle();

			while (parentKBArticle != null) {
				sb.append(StringPool.SPACE);
				sb.append(parentKBArticle.getTitle());

				parentKBArticle = parentKBArticle.getParentKBArticle();
			}
		}

		if (Validator.isNotNull(articleId)) {
			try {
				JournalArticle journalArticle = JournalArticleServiceUtil.getLatestArticle(themeDisplay.getScopeGroupId(), articleId, WorkflowConstants.STATUS_APPROVED);

				if (journalArticle != null) {
					sb.append(journalArticle.getTitle(locale));
				}
			}
			catch (Exception e) {
			}
		}

		ExpandoBridge expandoBridge = layout.getExpandoBridge();

		String layoutKeywords = GetterUtil.getString(expandoBridge.getAttribute("Search Keywords", false));

		if (Validator.isNotNull(layoutKeywords)) {
			if (sb.length() != 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(layoutKeywords);
		}
		%>

		A.io.request(
			'<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/side_navigation/search.jsp" /></portlet:renderURL>',
			{
				data: {
					<portlet:namespace />keywords: '<%= HtmlUtil.escapeJS(ArticleSearchUtil.cleanKeywords(sb.toString())) %>',
					<portlet:namespace />redirect: '<%= HtmlUtil.escapeJS(redirect) %>'
				},
				on: {
					success: function(event) {
						var response = this.get('responseData');

						var relatedArticles = A.one('#<portlet:namespace />relatedArticles');

						if (relatedArticles) {
							relatedArticles.html(response);
						}
					}
				}
			}
		);
	</aui:script>
</c:if>