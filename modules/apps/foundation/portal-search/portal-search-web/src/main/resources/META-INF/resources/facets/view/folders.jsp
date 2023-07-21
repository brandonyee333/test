<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<%
if (termCollectors.isEmpty()) {
	return;
}

int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
int maxTerms = dataJSONObject.getInt("maxTerms", 10);
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);

Indexer<?> indexer = FolderSearcher.getInstance();

SearchContext searchContext = SearchContextFactory.getInstance(request);
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<liferay-ui:message key="folders" />
		</div>
	</div>

	<div class="panel-body">
		<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" type="hidden" value="<%= fieldParam %>" />

			<ul class="folders list-unstyled">
				<li class="default facet-value">
					<a class="<%= Validator.isNull(fieldParam) ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
				</li>

				<%
				long folderId = GetterUtil.getLong(fieldParam);

				for (int i = 0; i < termCollectors.size(); i++) {
					TermCollector termCollector = termCollectors.get(i);

					long curFolderId = GetterUtil.getLong(termCollector.getTerm());

					if (curFolderId == 0) {
						continue;
					}

					searchContext.setAssetCategoryIds(null);
					searchContext.setFolderIds(new long[] {curFolderId});
					searchContext.setKeywords(StringPool.BLANK);

					Hits results = indexer.search(searchContext);

					if (results.getLength() == 0) {
						continue;
					}

					Document document = results.doc(0);

					Field title = document.getField(Field.TITLE);

					if (((maxTerms > 0) && (i >= maxTerms)) || ((frequencyThreshold > 0) && (frequencyThreshold > termCollector.getFrequency()))) {
						break;
					}
				%>

					<li class="facet-value">
						<a class="<%= (folderId == curFolderId) ? "text-primary" : "text-default" %>" data-value="<%= curFolderId %>" href="javascript:;">
							<%= HtmlUtil.escape(title.getValue()) %>

							<c:if test="<%= showAssetCount %>">
								<span class="frequency">(<%= termCollector.getFrequency() %>)</span>
							</c:if>
						</a>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</div>
</div>