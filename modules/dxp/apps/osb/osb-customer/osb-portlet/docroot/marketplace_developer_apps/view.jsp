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

<%@ include file="/marketplace_developer_apps/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_developer_apps/view.jsp");
%>

<div class="marketplace-developer-apps view">
	<liferay-ui:header
		title="apps"
	/>

	<liferay-portlet:renderURL var="addAppEntryURL">
		<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
	</liferay-portlet:renderURL>

	<c:if test="<%= developerEntry.getStatus() == WorkflowConstants.STATUS_APPROVED %>">
		<a class="btn" href="<%= addAppEntryURL %>"><liferay-ui:message key="add-new-app" /></a>
	</c:if>

	<div class="heading-apps">
		<liferay-ui:message key="under-review" />
	</div>

	<div class="product-list">

		<%
		boolean showAwaitingResponseAppFlagMessage = false;
		%>

		<liferay-util:buffer var="html">
			<liferay-ui:search-container
				emptyResultsMessage="no-apps-were-found"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results>

					<%
					SearchContext searchContext = SearchContextFactory.getInstance(request);

					LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

					params.put("appVersionStatus", _SECTION_UNDER_REVIEW_STATUSES);
					params.put("developerEntryId", developerEntry.getDeveloperEntryId());

					searchContext.setAttribute("params", params);

					searchContext.setSorts(SortFactoryUtil.getDefaultSorts());

					Indexer indexer = IndexerRegistryUtil.getIndexer(AppEntry.class);

					Hits hits = indexer.search(searchContext);

					for (int i = 0; i < hits.getDocs().length; i++) {
						Document doc = hits.doc(i);

						long classPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

						AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

						results.add(appEntry);
					}

					total = hits.getLength();

					pageContext.setAttribute("results", results);
					pageContext.setAttribute("total", total);
					%>

				</liferay-ui:search-container-results>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.AppEntry"
					escapedModel="<%= true %>"
					keyProperty="appEntryId"
					modelVar="appEntry"
				>

					<%
					AppVersion appVersion = appEntry.getActionableAppVersion();
					%>

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
						<portlet:param name="backURL" value="<%= currentURL %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</liferay-portlet:renderURL>

					<div class="asset container">
						<div class="item">
							<div class="icon">
								<a href="<%= rowURL %>">
									<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
										<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appEntry.getIconImageId()) %>" />
									</liferay-portlet:resourceURL>

									<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
								</a>
							</div>

							<ul class="info">
								<li class="title">
									<a href="<%= rowURL %>">
										<%= HtmlUtil.escape(MarketplaceUtil.shortenString(appEntry.getTitle(), _APP_TITLE_LENGTH)) %>
									</a>
								</li>
								<li>
									<liferay-ui:message key="version" />: <%= HtmlUtil.escape(MarketplaceUtil.shortenString(appVersion.getVersion(), _APP_VERSION_LENGTH)) %>
								</li>
								<li>
									<liferay-ui:message arguments="<%= appEntry.getDownloadCount() %>" key="x-downloads" />
								</li>
							</ul>
						</div>

						<%
						AppFlag appFlag = AppFlagLocalServiceUtil.fetchAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_AWAITING_RESPONSE);

						if (appFlag != null) {
							showAwaitingResponseAppFlagMessage = true;
						}
						%>

						<div class="status-bar">
							<c:choose>
								<c:when test="<%= appVersion.getStatus() == WorkflowConstants.STATUS_DENIED %>">
									<%@ include file="/marketplace_developer_apps/status_bar_denied.jspf" %>
								</c:when>
								<c:when test="<%= appVersion.getStatus() == WorkflowConstants.STATUS_PENDING %>">
									<c:choose>
										<c:when test="<%= appFlag != null %>">
											<%@ include file="/marketplace_developer_apps/status_bar_pending_flagged.jspf" %>
										</c:when>
										<c:otherwise>
											<%@ include file="/marketplace_developer_apps/status_bar_pending.jspf" %>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="<%= (appVersion.getStatus() == WorkflowConstants.STATUS_PENDING_QA) || (appVersion.getStatus() == WorkflowConstants.STATUS_PENDING_AUTO_QA) %>">
									<c:choose>
										<c:when test="<%= appFlag != null %>">
											<%@ include file="/marketplace_developer_apps/status_bar_pending_qa_flagged.jspf" %>
										</c:when>
										<c:otherwise>
											<%@ include file="/marketplace_developer_apps/status_bar_pending_qa.jspf" %>
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>
						</div>
					</div>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="false" />
			</liferay-ui:search-container>
		</liferay-util:buffer>

		<c:if test="<%= showAwaitingResponseAppFlagMessage %>">
			<div class="attention-needed">
				<img class="icon" src="<%= PortalUtil.getPathContext(request) %>/marketplace_developer_apps/images/error_button.svg" />

				<div class="message">
					<liferay-ui:message key="one-or-more-apps-require-your-attention-before-we-can-continue-with-the-review" />
				</div>
			</div>
		</c:if>

		<%= html %>

		<div class="heading-apps">
			<liferay-ui:message key="apps" />
		</div>

		<liferay-ui:search-container
			emptyResultsMessage="no-apps-were-found"
			iteratorURL="<%= portletURL %>"
		>
			<liferay-ui:search-container-results>

				<%
				SearchContext searchContext = SearchContextFactory.getInstance(request);

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put("appVersionStatus", _SECTION_APPS_STATUSES);
				params.put("developerEntryId", developerEntry.getDeveloperEntryId());

				searchContext.setAttribute("params", params);

				searchContext.setSorts(SortFactoryUtil.getDefaultSorts());

				Indexer indexer = IndexerRegistryUtil.getIndexer(AppEntry.class);

				Hits hits = indexer.search(searchContext);

				for (int i = 0; i < hits.getDocs().length; i++) {
					Document doc = hits.doc(i);

					long classPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

					AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

					results.add(appEntry);
				}

				total = hits.getLength();

				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AppEntry"
				escapedModel="<%= true %>"
				keyProperty="appEntryId"
				modelVar="appEntry"
			>

				<%
				AppVersion appVersion = appEntry.getActionableAppVersion();
				%>

				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
					<portlet:param name="backURL" value="<%= currentURL %>" />
					<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
				</liferay-portlet:renderURL>

				<div class="asset container">
					<div class="item">
						<div class="icon">
							<a href="<%= rowURL %>">
								<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
									<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appEntry.getIconImageId()) %>" />
								</liferay-portlet:resourceURL>

								<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />

								<%
								boolean showApprovedButton = false;

								if (appVersion.isApproved()) {
									int daysApproved = DateUtil.getDaysBetween(appEntry.getStatusDate(), new Date());

									if (daysApproved <= _APPROVAL_OVERLAY_DISPLAY_DAYS) {
										showApprovedButton = true;
									}
								}
								%>

								<c:if test="<%= showApprovedButton %>">
									<img class="approved-button-overlay" src="<%= PortalUtil.getPathContext(request) %>/marketplace_developer_apps/images/approved_button.svg" />
								</c:if>
							</a>
						</div>

						<ul class="info">
							<li class="title">
								<a href="<%= rowURL %>">
									<%= HtmlUtil.escape(MarketplaceUtil.shortenString(appEntry.getTitle(), _APP_TITLE_LENGTH)) %>
								</a>
							</li>
							<li>
								<liferay-ui:message key="version" />: <%= HtmlUtil.escape(MarketplaceUtil.shortenString(appVersion.getVersion(), _APP_VERSION_LENGTH)) %>
							</li>
							<li>
								<liferay-ui:message arguments="<%= appEntry.getDownloadCount() %>" key="x-downloads" />
							</li>
							<li>
								<c:choose>
									<c:when test="<%= (appEntry.getStatus() != appVersion.getStatus()) && (appEntry.getStatus() != WorkflowConstants.STATUS_EXPIRED) %>">
										<liferay-ui:message key="<%= appVersion.getStatusLabel() %>" />
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="<%= appEntry.getStatusLabel() %>" />
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div>
				</div>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="false" />
		</liferay-ui:search-container>
	</div>
</div>

<%!
private static int _APP_TITLE_LENGTH = 34;

private static int _APP_VERSION_LENGTH = 14;

private static int _APPROVAL_OVERLAY_DISPLAY_DAYS = 14;

private static Integer[] _SECTION_APPS_STATUSES = new Integer[] {
	WorkflowConstants.STATUS_APPROVED,
	WorkflowConstants.STATUS_APPROVED_HIDDEN,
	WorkflowConstants.STATUS_DRAFT,
	WorkflowConstants.STATUS_EXPIRED
};

private static Integer[] _SECTION_UNDER_REVIEW_STATUSES = new Integer[] {
	WorkflowConstants.STATUS_DENIED,
	WorkflowConstants.STATUS_PENDING,
	WorkflowConstants.STATUS_PENDING_AUTO_QA,
	WorkflowConstants.STATUS_PENDING_QA
};
%>