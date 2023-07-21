<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<%
int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
boolean showAssetCount = dataJSONObject.getBoolean("showAssetCount", true);

String[] values = new String[0];

if (dataJSONObject.has("values")) {
	JSONArray valuesJSONArray = dataJSONObject.getJSONArray("values");

	values = new String[valuesJSONArray.length()];

	for (int i = 0; i < valuesJSONArray.length(); i++) {
		values[i] = valuesJSONArray.getString(i);
	}
}
%>

<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">
			<liferay-ui:message key="asset-entries" />
		</div>
	</div>

	<div class="panel-body">
		<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
			<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" type="hidden" value="<%= fieldParam %>" />

			<ul class="asset-type list-unstyled">
				<li class="default facet-value">
					<a class="<%= Validator.isNull(fieldParam) ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
				</li>

				<%
				List<String> assetTypes = new SortedArrayList<String>(new ModelResourceComparator(locale));

				for (String className : values) {
					if (assetTypes.contains(className) || !ArrayUtil.contains(values, className)) {
						continue;
					}

					assetTypes.add(className);
				}

				for (String assetType : assetTypes) {
					TermCollector termCollector = facetCollector.getTermCollector(assetType);

					int frequency = 0;

					if (termCollector != null) {
						frequency = termCollector.getFrequency();
					}

					if (frequencyThreshold > frequency) {
						continue;
					}

					AssetRendererFactory<?> assetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(assetType);
				%>

					<li class="facet-value">
						<a class="<%= fieldParam.equals(termCollector.getTerm()) ? "text-primary" : "text-default" %>" data-value="<%= HtmlUtil.escapeAttribute(assetType) %>" href="javascript:;">
							<%= assetRendererFactory.getTypeName(locale) %>

							<c:if test="<%= showAssetCount %>">
								<span class="frequency">(<%= frequency %>)</span>
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