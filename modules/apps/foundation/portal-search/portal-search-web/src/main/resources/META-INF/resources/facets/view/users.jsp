<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/facets/init.jsp" %>

<c:if test="<%= !termCollectors.isEmpty() %>">

	<%
	int frequencyThreshold = dataJSONObject.getInt("frequencyThreshold");
	int maxTerms = dataJSONObject.getInt("maxTerms", 10);
	%>

	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<liferay-ui:message key="users" />
			</div>
		</div>

		<div class="panel-body">
			<div class="<%= cssClass %>" data-facetFieldName="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" id="<%= randomNamespace %>facet">
				<aui:input autocomplete="off" name="<%= HtmlUtil.escapeAttribute(facet.getFieldId()) %>" type="hidden" value="<%= fieldParam %>" />

				<ul class="list-unstyled users">
					<li class="default facet-value">
						<a class="<%= Validator.isNull(fieldParam) ? "text-primary" : "text-default" %>" data-value="" href="javascript:;"><liferay-ui:message key="<%= HtmlUtil.escape(facetConfiguration.getLabel()) %>" /></a>
					</li>

					<%
					String userName = GetterUtil.getString(fieldParam);

					for (int i = 0; i < termCollectors.size(); i++) {
						TermCollector termCollector = termCollectors.get(i);

						String curUserName = GetterUtil.getString(termCollector.getTerm());

						if (((maxTerms > 0) && (i >= maxTerms)) || ((frequencyThreshold > 0) && (frequencyThreshold > termCollector.getFrequency()))) {
							break;
						}
					%>

						<li class="facet-value">
							<a class="<%= userName.equals(curUserName) ? "text-primary" : "text-default" %>" data-value="<%= HtmlUtil.escapeAttribute(curUserName) %>" href="javascript:;">
								<%= HtmlUtil.escape(curUserName) %>

								<c:if test='<%= dataJSONObject.getBoolean("showAssetCount", true) %>'>
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
</c:if>