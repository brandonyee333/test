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

<%@ include file="/support/init.jsp" %>

<%
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");

PartnerEntry partnerEntry = null;

try {
	partnerEntry = PartnerEntryServiceUtil.getPartnerEntry(partnerEntryId);
}
catch (NoSuchPartnerEntryException nspee) {
}

List<AccountEntry> accountEntries = Collections.emptyList();
List<PartnerEntry> childPartnerEntries = Collections.emptyList();
List<PartnerWorker> partnerWorkers = Collections.emptyList();

if (partnerEntry != null) {
	accountEntries = partnerEntry.getAccountEntries();
	childPartnerEntries = partnerEntry.getChildPartnerEntries(true);
	partnerWorkers = partnerEntry.getPartnerWorkers();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_partner_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));
%>

<portlet:actionURL name="updatePartnerEntry" var="updatePartnerEntryURL">
	<portlet:param name="mvcPath" value="/support/edit_partner_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updatePartnerEntryURL %>" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
	<input name="<portlet:namespace />partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />

	<div class="cleared section">
		<div class="fr">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<div class="callout-a">
		<div class="callout-content cleared">
			<div class="fl">
				<div class="txt-b txt-up">
					<liferay-ui:message key="name" />
				</div>

				<div class="txt-b txt-h1">
					<%= partnerEntry.getCode() %>
				</div>
			</div>
		</div>
	</div>

	<h2 class="section-heading">
		<liferay-ui:message key="contacts" />
	</h2>

	<div class="callout-a">
		<div class="callout-content">
			<liferay-ui:search-container
				headerNames="name,email-address,role"
			>
				<liferay-ui:search-container-results
					results="<%= partnerWorkers %>"
					total="<%= partnerWorkers.size() %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.PartnerWorker"
					escapedModel="<%= true %>"
					keyProperty="partnerWorkerId"
					modelVar="partnerWorker"
				>

					<%
					User curUser = null;

					try {
						curUser = UserLocalServiceUtil.getUser(partnerWorker.getUserId());
					}
					catch (NoSuchUserException nsue) {
						PartnerWorkerLocalServiceUtil.deletePartnerWorkers(partnerWorker.getUserId());

						curUser = UserLocalServiceUtil.getDefaultUser(company.getCompanyId());

						row.setSkip(true);
					}
					%>

					<liferay-ui:search-container-column-text
						name="name"
					>
						<%= HtmlUtil.escape(curUser.getFullName()) %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="email-address"
					>
						<%= curUser.getEmailAddress() %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="phone-number"
					>

						<%
						List<Phone> phones = curUser.getPhones();

						for (Phone phone : phones) {
							ListType phoneType = phone.getType();
						%>

							<%= LanguageUtil.get(pageContext, phoneType.getName()) %>: <%= phone.getNumber() %> <%= phone.getExtension() %> <br />

						<%
						}
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="role"
					>
						<%= LanguageUtil.get(pageContext, PartnerWorkerConstants.getRoleLabel(partnerWorker.getRole())) %>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" />
			</liferay-ui:search-container>
		</div>
	</div>

	<c:if test="<%= liferayIncOrg %>">
		<h2 class="section-heading">
			<liferay-ui:message key="additional-notes" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<%= SupportUtil.getHTML(partnerEntry.getNotes()) %>
			</div>
		</div>
	</c:if>

	<h2 class="section-heading">
		<liferay-ui:message key="phones" />
	</h2>

	<div class="callout-a">
		<div class="callout-content">

			<%
			request.setAttribute("phones.className", PartnerEntry.class.getName());
			request.setAttribute("phones.classPK", partnerEntryId);
			%>

			<div id="phones">
				<liferay-util:include page="/html/portlet/directory/organization/phone_numbers.jsp" />
			</div>
		</div>
	</div>

	<h2 class="section-heading">
		<liferay-ui:message key="addresses" />
	</h2>

	<div class="callout-a">
		<div class="callout-content">

			<%
			request.setAttribute("addresses.className", PartnerEntry.class.getName());
			request.setAttribute("addresses.classPK", partnerEntryId);
			%>

			<div id="addresses">
				<liferay-util:include page="/html/portlet/directory/organization/addresses.jsp" />
			</div>
		</div>
	</div>

	<div>
		<input class="aui-button-input" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="add-ticket" />" />

		<input class="aui-button-input" onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="mvcPath" value="/support/view.jsp" /><portlet:param name="tabs1" value="tickets" /><portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="view-tickets" />" />
	</div>

	<c:if test="<%= !accountEntries.isEmpty() %>">
		<div class="separator"></div>

		<liferay-ui:tabs
			names="projects"
		/>

		<liferay-ui:search-container>
			<liferay-ui:search-container-results
				results="<%= accountEntries %>"
				total="<%= accountEntries.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AccountEntry"
				escapedModel="<%= true %>"
				keyProperty="accountEntryId"
				modelVar="accountEntry"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
				</portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="name"
					value="<%= accountEntry.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="first-line-support"
					value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(pageContext, "yes") : LanguageUtil.get(pageContext, "no") %>'
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</c:if>

	<c:if test="<%= !childPartnerEntries.isEmpty() %>">
		<div class="child-partners">
			<liferay-ui:tabs
				names="child-partners"
			/>

			<%
			Map<Long, Integer> depthMap = new HashMap<Long, Integer>();

			for (PartnerEntry childPartnerEntry : childPartnerEntries) {
				int depth = 0;

				if (depthMap.containsKey(childPartnerEntry.getParentPartnerEntryId())) {
					depth = depthMap.get(childPartnerEntry.getParentPartnerEntryId()) + 1;
				}

				depthMap.put(childPartnerEntry.getPartnerEntryId(), depth);
			%>

				<div style="padding-left: <%= depth * 25 %>px;">
					<portlet:renderURL var="partnerEntryURL">
						<portlet:param name="mvcPath" value="/support/edit_partner_entry.jsp" />
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="partnerEntryId" value="<%= String.valueOf(childPartnerEntry.getPartnerEntryId()) %>" />
					</portlet:renderURL>

					<h4>
						<a href="<%= partnerEntryURL %>"><%= HtmlUtil.escape(childPartnerEntry.getCode()) %></a>
					</h4>

					<liferay-ui:search-container>
						<liferay-ui:search-container-results
							results="<%= childPartnerEntry.getAccountEntries() %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.AccountEntry"
							escapedModel="<%= true %>"
							keyProperty="accountEntryId"
							modelVar="accountEntry"
						>
							<portlet:renderURL var="rowURL">
								<portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" />
								<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
								<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
							</portlet:renderURL>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="projects"
								value="<%= accountEntry.getName() %>"
							/>

							<liferay-ui:search-container-column-text
								href="<%= rowURL %>"
								name="first-line-support"
								value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(pageContext, "yes") : LanguageUtil.get(pageContext, "no") %>'
							/>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator paginate="<%= false %>" />
					</liferay-ui:search-container>
				</div>

			<%
			}
			%>

		</div>
	</c:if>
</aui:form>