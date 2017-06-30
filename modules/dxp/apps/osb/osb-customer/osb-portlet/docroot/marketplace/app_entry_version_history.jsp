<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/marketplace/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);
%>

<div class="app-entry-version-history">
	<div class="callout-b-head">
		<div class="callout-content">
			<h2 class="title">
				<liferay-ui:message key="version-history" />
			</h2>
		</div>
	</div>

	<div class="callout-e pseudo-portlet-content">
		<div class="version-history">
			<div>
				<div class="heading">
					<span class="version-name">
						<liferay-ui:message key="version" />
					</span>

					<span class="change-log">
						<liferay-ui:message key="change-log" />
					</span>

					<span class="supported-framework-versions">
						<liferay-ui:message key="supported-framework-versions" />
					</span>

					<span class="date-added">
						<liferay-ui:message key="date-added" />
					</span>
				</div>

				<div class="body">
					<ul>

						<%
						List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntry.getAppEntryId(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppVersionVersionOrderComparator(true));

						for (AppVersion appVersion : appVersions) {
						%>

							<li class="lfr-panel lfr-collapsible lfr-collapsed">
								<div class="version-data">
									<span class="version-name">
										<%= appVersion.getVersion() %>
									</span>

									<span class="change-log">

										<%
										String changeLog = HtmlUtil.escape(appVersion.getChangeLog(themeDisplay.getLanguageId()));
										%>

										<c:if test="<%= Validator.isNotNull(changeLog) %>">
											<span class="change-log-toggle">
												<a class="lfr-panel-titlebar">
													<liferay-ui:message key="change-log" />
												</a>
											</span>
										</c:if>
									</span>

									<span class="supported-framework-versions">

										<%
										List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

										for (AppPackage appPackage : appPackages) {
										%>

											<span class="app-package"><%= PortalReleaseUtil.getVersionName(appPackage) %></span>

											<br />

										<%
										}
										%>

									</span>

									<span class="date-added">
										<%= longDateFormatDate.format(appVersion.getReleaseDate()) %>
									</span>
								</div>

								<c:if test="<%= Validator.isNotNull(changeLog) %>">
									<div class="change-log-content lfr-panel-content">
										<c:choose>
											<c:when test="<%= appEntry.isDeveloperEntryLiferayInc() %>">
												<ul>

													<%
													String[] jiraIssueKeys = StringUtil.split(changeLog.replace(StringPool.NEW_LINE, StringPool.SPACE), StringPool.SPACE);

													List<JIRAIssue> jiraIssues = JIRAIssueLocalServiceUtil.getJIRAIssues(jiraIssueKeys);

													for (JIRAIssue jiraIssue : jiraIssues) {
														if (jiraIssue == null) {
															continue;
														}
													%>

														<li>
															<a href="<%= PortletPropsValues.JIRA_BROWSE_URL + jiraIssue.getKey() %>" target="_blank"><%= jiraIssue.getKey() %></a> <%= HtmlUtil.escape(MarketplaceUtil.shortenString(jiraIssue.getSummary(), 65)) %>
														</li>

													<%
													}
													%>

												</ul>
											</c:when>
											<c:otherwise>
												<%= changeLog.replace(StringPool.NEW_LINE, "<br />") %>
											</c:otherwise>
										</c:choose>
									</div>
								</c:if>
							</li>

						<%
						}
						%>

					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script use="aui-base">
	A.one('.marketplace .version-history .body').delegate(
		'click',
		function(event) {
			var target = event.currentTarget;

			var listItem = target.ancestor('li');

			if (listItem.hasClass('lfr-collapsed')) {
				listItem.removeClass('lfr-collapsed');
			}
			else {
				listItem.addClass('lfr-collapsed');
			}
		},
		'.change-log-toggle'
	);
</aui:script>