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

<%@ include file="/init.jsp" %>

<%
Map<String, MetricsModel<?>> metricsModelsMap = MetricsModelRegistryUtil.getMetricsModelsMap();
%>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-models"
		emptyResultsMessageCssClass="taglib-empty-result-message-header"
		headerNames="model-class-name"
		total="<%= metricsModelsMap.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ListUtil.fromCollection(metricsModelsMap.entrySet()) %>"
		/>

		<liferay-ui:search-container-row
			className="java.util.Map.Entry"
			modelVar="metricsModelEntry"
		>

			<%
			MetricsModel metricsModel = (MetricsModel)metricsModelEntry.getValue();
			String modelClassName = (String)metricsModelEntry.getKey();
			%>

			<liferay-ui:search-container-column-text
				value="<%= modelClassName %>"
			/>

			<liferay-ui:search-container-column-text>
				<portlet:actionURL name="syncModel" var="syncModelURL">
					<portlet:param name="modelClassName" value="<%= modelClassName %>" />
				</portlet:actionURL>

				<aui:button onClick="<%= syncModelURL %>" value="sync" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text>
				<c:if test="<%= metricsModel.allowDeleteAll() %>">
					<portlet:actionURL name="cleanAndSyncModel" var="cleanAndSyncModelURL">
						<portlet:param name="modelClassName" value="<%= modelClassName %>" />
					</portlet:actionURL>

					<aui:button onClick="<%= cleanAndSyncModelURL %>" value="clean-and-sync" />
				</c:if>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</div>