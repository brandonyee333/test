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

String displayStyle = dataJSONObject.getString("displayStyle", "cloud");
int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
int maxTerms = dataJSONObject.getInt("maxTerms", 10);
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<liferay-ui:message key="categories" />
		</div>
	</div>

	<div class="panel-body">
		<div class="asset-tags <%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" type="hidden" value="<%= fieldParam %>" />

			<ul class="<%= (showAssetCount && displayStyle.equals("cloud")) ? "tag-cloud" : "tag-list" %> list-unstyled">
				<li class="default facet-value">
					<a class="<%= Validator.isNull(fieldParam) ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
				</li>

				<%
				int maxCount = 1;
				int minCount = 1;

				if (showAssetCount && displayStyle.equals("cloud")) {

					// The cloud style may not list tags in the order of frequency,
					// so keep looking through the results until we reach the maximum
					// number of terms or we run out of terms.

					for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
						if (j >= maxTerms) {
							break;
						}

						TermCollector termCollector = termCollectors.get(i);

						int frequency = termCollector.getFrequency();

						if (frequencyThreshold > frequency) {
							j--;

							continue;
						}

						maxCount = Math.max(maxCount, frequency);
						minCount = Math.min(minCount, frequency);
					}
				}

				double multiplier = 1;

				if (maxCount != minCount) {
					multiplier = (double)5 / (maxCount - minCount);
				}

				for (int i = 0, j = 0; i < termCollectors.size(); i++, j++) {
					if (j >= maxTerms) {
						break;
					}

					TermCollector termCollector = termCollectors.get(i);

					long assetCategoryId = GetterUtil.getLong(termCollector.getTerm());

					if (assetCategoryId == 0) {
						continue;
					}

					AssetCategory curAssetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(assetCategoryId);
				%>

					<c:if test="<%= (curAssetCategory != null) && AssetCategoryPermission.contains(permissionChecker, curAssetCategory, ActionKeys.VIEW) %>">

						<%
						int popularity = (int)(1 + ((maxCount - (maxCount - (termCollector.getFrequency() - minCount))) * multiplier));

						if (frequencyThreshold > termCollector.getFrequency()) {
							j--;

							continue;
						}
						%>

						<li class="facet-value tag-popularity-<%= popularity %>">
							<a class="<%= fieldParam.equals(termCollector.getTerm()) ? "text-primary" : "text-default" %>" data-value="<%= HtmlUtil.escapeAttribute(String.valueOf(assetCategoryId)) %>" href="javascript:;">
								<%= HtmlUtil.escape(curAssetCategory.getTitle(locale)) %>

								<c:if test="<%= showAssetCount %>">
									<span class="frequency">(<%= termCollector.getFrequency() %>)</span>
								</c:if>
							</a>
						</li>
					</c:if>

				<%
				}
				%>

			</ul>
		</div>
	</div>
</div>