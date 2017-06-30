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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

String companyName = ParamUtil.getString(request, "companyName");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_registration/view_company_developer_entries.jsp");
%>

<div class="marketplace-registration-wrapper join-a-company">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="join-a-company"
	/>

	<p>
		<liferay-ui:message key="thank-you-for-your-interest-in-representing-your-company-on-the-liferay-marketplace" />
	</p>

	<h3>
		<liferay-ui:message key="step-1" />
	</h3>

	<%
	List<DeveloperEntry> developerEntries = DeveloperEntryLocalServiceUtil.getDeveloperEntries(user.getUserId(), DeveloperEntryConstants.TYPE_COMPANY, WorkflowConstants.STATUS_PENDING, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	%>

	<c:if test="<%= !developerEntries.isEmpty() %>">
		<div class="aui-field-label">
			<liferay-ui:message key="pending-registrations" />
		</div>

		<div class="clear container developer-entry-container">

			<%
			for (DeveloperEntry developerEntry : developerEntries) {
			%>

				<div class="developer-entry-item">
					<span class="title">
						<liferay-portlet:renderURL var="editDeveloperEntryURL">
							<portlet:param name="mvcPath" value="/marketplace_registration/edit_company_developer_entry.jsp" />
							<portlet:param name="backURL" value="<%= backURL %>" />
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntry.getDeveloperEntryId()) %>" />
						</liferay-portlet:renderURL>

						<a class="developer-entry-name" href="<%= editDeveloperEntryURL %>"><%= HtmlUtil.escape(developerEntry.getName()) %></a>
					</span>

					<span class="date">
						<liferay-ui:message arguments="<%= longDateFormatDate.format(developerEntry.getCreateDate()) %>" key="submitted-on-x" />
					</span>

					<a class="btn" href="<%= editDeveloperEntryURL %>"><liferay-ui:message key="edit" /></a>

					<div class="clear"></div>
				</div>

			<%
			}
			%>

		</div>
	</c:if>

	<aui:form action="<%= portletURL %>" cssClass="container" method="post" name="fm">
		<div class="search-company-name">
			<span class="col search-company-name-col">
				<aui:input label="search-for-your-company" name="companyName" type="text" value="<%= companyName %>" />
			</span>

			<span class="col search-company-button-col">
				<aui:button type="submit" value="search" />
			</span>
		</div>
	</aui:form>

	<c:choose>
		<c:when test="<%= Validator.isNotNull(companyName) %>">

			<%
			SearchContext searchContext = SearchContextFactory.getInstance(request);

			searchContext.setAttribute(Field.NAME, String.valueOf(companyName));
			searchContext.setAttribute("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
			searchContext.setAttribute("type", DeveloperEntryConstants.TYPE_COMPANY);
			searchContext.setEnd(10);
			searchContext.setStart(0);

			Indexer indexer = IndexerRegistryUtil.getIndexer(DeveloperEntry.class);

			Hits results = indexer.search(searchContext);
			%>

			<p class="corp-entry-result">
				<liferay-ui:message arguments="<%= results.getLength() %>" key="your-search-returned-x-results" />
			</p>

			<div class="aui-helper-hidden corp-request-message portlet-msg-success">
				<liferay-ui:message key="your-request-was-sent" />
			</div>

			<div class="corp-entry-container container clear">

				<%
				for (int i = 0; i < results.getDocs().length; i++) {
					Document document = results.doc(i);

					long developerEntryId = GetterUtil.getLong(document.get(Field.CLASS_PK));

					DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

					CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(developerEntry.getDossieraAccountKey());

					if (corpEntry == null) {
						continue;
					}

					Group group = corpEntry.getGroup();
				%>

					<div class="corp-entry-item">
						<span class="title">
							<a class="corp-entry-name" href="<%= PortalUtil.getGroupFriendlyURL(group, false, themeDisplay) %>" target="_blank"><%= HtmlUtil.escape(corpEntry.getName()) %></a>
						</span>

						<span class="membership-status">
							<c:choose>
								<c:when test="<%= themeDisplay.isSignedIn() && UserLocalServiceUtil.hasOrganizationUser(corpEntry.getOrganizationId(), user.getUserId()) %>">
									<liferay-ui:message key="currently-a-member" />
								</c:when>
								<c:otherwise>
									<liferay-portlet:renderURL var="corpEntryMemebershipRequestURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/marketplace_registration/corp_entry_membership_request.jsp" />
										<portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntry.getCorpEntryId()) %>" />
									</liferay-portlet:renderURL>

									<%
									String taglibCorpEntryMemebershipRequestOnClick = renderResponse.getNamespace() + "openCorpEntryMembershipRequest('" + HtmlUtil.escapeJS(corpEntryMemebershipRequestURL) + "');";
									%>

									<aui:button onClick="<%= taglibCorpEntryMemebershipRequestOnClick %>" value="request-to-join-this-company" />
								</c:otherwise>
							</c:choose>
						</span>

						<div class="clear"></div>
					</div>

				<%
				}
				%>

			</div>

			<div class="clear"></div>
		</c:when>
		<c:otherwise>
			<div class="existing-companies">

				<%
				List<CorpEntry> userCorpEntries = CorpEntryLocalServiceUtil.getUserCorpEntries(user.getUserId(), null, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				%>

				<c:if test="<%= !userCorpEntries.isEmpty() %>">
					<p class="corp-entry-result">
						<liferay-ui:message key="you-are-a-member-of-the-following-companies" />
					</p>

					<div class="corp-entry-container container clear">

						<%
						for (CorpEntry userCorpEntry : userCorpEntries) {
							DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(userCorpEntry.getDossieraAccountKey());

							if (developerEntry != null) {
								continue;
							}

							Group group = userCorpEntry.getGroup();
						%>

							<div class="corp-entry-item">
								<span class="title">
									<a class="corp-entry-name" href="<%= PortalUtil.getGroupFriendlyURL(group, false, themeDisplay) %>" target="_blank"><%= HtmlUtil.escape(userCorpEntry.getName()) %></a>
								</span>

								<span>
									<liferay-portlet:renderURL var="corpEntryUpgradeRequestURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
										<portlet:param name="mvcPath" value="/marketplace_registration/corp_entry_upgrade_request.jsp" />
										<portlet:param name="backURL" value="<%= backURL %>" />
										<portlet:param name="corpEntryId" value="<%= String.valueOf(userCorpEntry.getCorpEntryId()) %>" />
									</liferay-portlet:renderURL>

									<%
									String taglibCorpEntryUpgradeOnClick = renderResponse.getNamespace() +"openCorpEntryUpgradeRequest('" + HtmlUtil.escapeJS(corpEntryUpgradeRequestURL) + "');";
									%>

									<aui:button onClick="<%= taglibCorpEntryUpgradeOnClick %>" value="register-this-company" />
								</span>

								<div class="clear"></div>
							</div>

						<%
						}
						%>

					</div>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="rt-align">
		<p>
			<liferay-ui:message key="if-your-company-does-not-exist-go-to-the-next-step-to-register-your-company" />
		</p>

		<aui:button-row>

			<%
			long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
			%>

			<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
				<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
			</liferay-portlet:renderURL>

			<aui:button href="<%= marketplaceURL %>" value="cancel" />

			<liferay-portlet:renderURL var="createCorpURL">
				<portlet:param name="mvcPath" value="/marketplace_registration/edit_company_developer_entry.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="companyName" value="<%= companyName %>" />
			</liferay-portlet:renderURL>

			<c:choose>
				<c:when test="<%= themeDisplay.isSignedIn() %>">
					<aui:button onClick="<%= createCorpURL %>" value="register-your-company" />
				</c:when>
				<c:otherwise>
					<aui:button onClick='<%= themeDisplay.getURLSignIn() + "&redirect=" + HtmlUtil.escapeURL(createCorpURL) %>' value="register-your-company" />
				</c:otherwise>
			</c:choose>
		</aui:button-row>
	</div>

	<div class="clear"></div>

	<aui:script>
		Liferay.provide(
			window,
			'corpRequestCallback',
			function(success) {
				if (success) {
					AUI().one('.osb-portlet-marketplace-registration .corp-request-message').show();
				}
			},
			['aui-dialog', 'aui-io']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />openPopup',
			function(title, url) {
				var A = AUI();

				var dialogWidth = 435;

				var dialogX = (A.getBody().width() - dialogWidth) / 2;

				new A.Dialog(
					{
						destroyOnClose: true,
						modal: true,
						resizable: false,
						title: title,
						width: dialogWidth,
						xy: [dialogX, 80]
					}
				).plug(
					A.Plugin.IO,
					{
						uri: url
					}
				).render();
			},
			['aui-dialog', 'aui-io']
		);

		function <portlet:namespace />openCorpEntryMembershipRequest(url) {
			<portlet:namespace />openPopup('<liferay-ui:message key="request-membership" unicode="<%= true %>" />', url);
		}

		function <portlet:namespace />openCorpEntryUpgradeRequest(url) {
			<portlet:namespace />openPopup('<liferay-ui:message key="register-company" unicode="<%= true %>" />', url);
		}
	</aui:script>
</div>