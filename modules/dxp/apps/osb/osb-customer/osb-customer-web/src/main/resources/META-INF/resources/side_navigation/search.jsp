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
String articleId = ParamUtil.getString(renderRequest, "articleId");

SearchContext searchContext = SearchContextFactory.getInstance(request);

String[] searchStructureIds = ArticleSearchUtil.getSearchStructureIds(themeDisplay.getScopeGroupId());

Hits hits = ArticleSearchUtil.search(searchContext, new String[] {JournalArticle.class.getName()}, new long[0], new String[0], new long[0], searchStructureIds, themeDisplay.getLanguageId(), 0, 8);

int count = 0;

for (Document document : hits.getDocs()) {
	try {
		String entryClassName = GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME));
		long entryClassPK = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

		AssetRendererFactory assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(entryClassName);

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(entryClassPK);

		JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(entryClassPK, WorkflowConstants.STATUS_ANY, false);

		if ((journalArticle == null) || articleId.equals(journalArticle.getArticleId())) {
			continue;
		}

		long displayPlid = PortalUtil.getPlidFromPortletId(themeDisplay.getScopeGroupId(), OSBCustomerPortletKeys.DISPLAY);

		PortletURL displayPortletURL = PortletURLFactoryUtil.create(request, OSBCustomerPortletKeys.DISPLAY, displayPlid, PortletRequest.RENDER_PHASE);

		displayPortletURL.setParameter("mvcPath", "/display/view.jsp");
		displayPortletURL.setParameter("articleId", journalArticle.getArticleId());
		displayPortletURL.setParameter("redirect", redirect);
		displayPortletURL.setWindowState(WindowState.NORMAL);
%>

		<li class="tree-node">
			<span><a href="<%= displayPortletURL.toString() %>"><%= journalArticle.getTitle(themeDisplay.getLanguageId(), true) %></a></span>
		</li>

<%
	}
	catch (Exception e) {
		_log.error(e, e);
	}

	count++;

	if (count >= 7) {
		break;
	}
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_osb_customer_knowledge_base_web.side_navigation.search_jsp");
%>