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

String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2");

String redirect = ParamUtil.getString(request, "redirect");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);

PartnerEntry partnerEntry = accountEntry.getPartnerEntry();
boolean partnerManagedSupport = accountEntry.isPartnerManagedSupport();

boolean ticketWorker = false;

if (liferayIncOrg) {
	ticketWorker = true;
}
else if (accountEntry.isPartnerManagedSupport()) {
	if (PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
		ticketWorker = true;
	}
}

List<SupportRegion> supportRegions = null;

Address address = null;

if (accountEntry != null) {
	supportRegions = accountEntry.getSupportRegions();

	address = accountEntry.getAddress();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_account_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<script type="text/javascript">
	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = "";
		document.getElementById(hideId).style.display = "none";
	}

	function <portlet:namespace />updateAccountTier() {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updateAccountTier"><portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" /></portlet:actionURL>');
	}

	function <portlet:namespace />updateOfferingEntry(key, offeringEntryIds) {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
		document.<portlet:namespace />fm.<portlet:namespace />key.value = key;
		document.<portlet:namespace />fm.<portlet:namespace />offeringEntryIds.value = offeringEntryIds;
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updateOfferingEntry"><portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" /></portlet:actionURL>');
	}
</script>

<portlet:renderURL var="editAccountEntryURL">
	<portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" />
</portlet:renderURL>

<aui:form action="<%= editAccountEntryURL %>" class="uni-form" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>" />
	<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />
	<input name="<portlet:namespace />key" type="hidden" value="" />
	<input name="<portlet:namespace />offeringEntryIds" type="hidden" value="" />

	<div class="callout-a <%= (!screenShareMode && liferayIncOrg && (accountEntry.getTier() != AccountEntryConstants.TIER_REGULAR)) ? "tier-" + AccountEntryConstants.getTierLabel(accountEntry.getTier()) : StringPool.BLANK %>">
		<div class="callout-content cleared">
			<div class="fl">
				<div class="txt-b txt-up">
					<liferay-ui:message key="project-name" />
				</div>

				<div class="txt-b txt-h1">
					<div>

						<%
						long customerPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

						PortletURL accountEntryURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_SUPPORT, customerPlid, PortletRequest.RENDER_PHASE);

						accountEntryURL.setParameter("mvcPath", "/support/edit_account_entry.jsp");
						accountEntryURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
						accountEntryURL.setParameter("friendly", String.valueOf(Boolean.TRUE));
						%>

						<a href="<%= accountEntryURL.toString() %>"><%= HtmlUtil.escape(accountEntry.getName()) %></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="aui-helper-clearfix aui-w100">
		<div class="aui-w50 content-column">
			<div class="content-column-content left-column">
				<div class="callout-a">
					<div class="aui-helper-clearfix callout-content">
						<div class="aui-w15 content-column">
							<div class="content-column-content left-column txt-b txt-up">
								<liferay-ui:message key="code" />
							</div>
						</div>

						<div class="aui-w85 content-column ticket-value">
							<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
								<%= accountEntry.getCode() %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<c:if test="<%= liferayIncOrg %>">
			<div class="aui-w50 content-column">
				<div class="content-column-content right-column">

					<%
					String tierOnClick = StringPool.BLANK;

					if (OSBAccountEntryPermission.contains(themeDisplay.getPermissionChecker(), accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_TIER)) {
						tierOnClick = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "tierDisplay', '" + renderResponse.getNamespace() + "tier');";
					}
					%>

					<div class="callout-a" onClick="<%= tierOnClick %>">
						<div class="aui-helper-clearfix callout-content">
							<div class="aui-w15 content-column">
								<div class="content-column-content left-column txt-b txt-up">
									<liferay-ui:message key="tier" />
								</div>
							</div>

							<div class="aui-w85 content-column ticket-value">
								<div class="content-column-content right-column txt-b txt-h1-12 txt-h1-9 txt-h3-4 txt-h3-6 txt-up">
									<div class="<%= (!screenShareMode && liferayIncOrg && (accountEntry.getTier() != AccountEntryConstants.TIER_REGULAR)) ? "tier-" + AccountEntryConstants.getTierLabel(accountEntry.getTier()) + "-text" : StringPool.BLANK %>" id="<portlet:namespace />tierDisplay">
										<%= LanguageUtil.get(pageContext, AccountEntryConstants.getTierLabel(accountEntry.getTier())) %>
									</div>

									<select id="<portlet:namespace />tier" name="<portlet:namespace />tier" onChange="<portlet:namespace />updateAccountTier();" style="display: none;">

										<%
										for (int tier : AccountEntryConstants.TIERS) {
										%>

											<option <%= (accountEntry.getTier() == tier) ? "selected" : "" %> value="<%= tier %>"><%= LanguageUtil.get(pageContext, AccountEntryConstants.getTierLabel(tier)) %></option>

										<%
										}
										%>

									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>

	<c:if test="<%= ticketWorker %>">
		<h2 class="section-heading">
			<liferay-ui:message key="project-info" />
		</h2>

		<div class="callout-a">
			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w33 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up"><liferay-ui:message key="advocacy-specialists" />:</span>

						<%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) %>
					</div>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content middle-column">
						<span class="txt-b txt-up"><liferay-ui:message key="sales-reps" />:</span>

						<%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_SALES) %>
					</div>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up"><liferay-ui:message key="managed-services" />:</span>

						<%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_MANAGED_SERVICES) %>
					</div>
				</div>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w33 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up"><liferay-ui:message key="industry" />:</span>

						<%= LanguageUtil.get(pageContext, accountEntry.getIndustryLabel()) %>
					</div>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content middle-column">
						<span class="txt-b txt-up"><liferay-ui:message key="partner" />:</span>

						<c:if test="<%= accountEntry.isPartnerManagedSupport() %>">
							<liferay-ui:icon
								image="telephone"
								message="partner-first-line-support"
							/>
						</c:if>

						<c:if test="<%= partnerEntry != null %>">
							<liferay-portlet:renderURL varImpl="partnerURL">
								<portlet:param name="mvcPath" value="/support/edit_partner_entry.jsp" />
								<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
							</liferay-portlet:renderURL>

							<a href="<%= partnerURL %>"><%= HtmlUtil.escape(partnerEntry.getCode()) %></a>
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<h2 class="section-heading">
			<liferay-ui:message key="contacts" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<div class="unit-content">
					<span class="txt-b txt-up"><liferay-ui:message key="maximum-contacts" />:</span>

					<%= accountEntry.getMaxCustomers() %>
				</div>

				<br />

				<%
				LinkedHashMap userParams = new LinkedHashMap();

				userParams.put("status", WorkflowConstants.STATUS_ANY);

				OSBCustomSQLParam osbCustomSQLParam = new OSBCustomSQLParam("usersAccountCustomers", CustomSQLUtil.get("com.liferay.portal.kernel.service.persistence.UserFinder.joinByAccountCustomer"), new Object[] {accountEntry.getAccountEntryId(), AccountCustomerConstants.ROLES});

				userParams.put("usersAccountCustomers", osbCustomSQLParam);
				%>

				<liferay-ui:user-search
					portletURL="<%= portletURL %>"
					userParams="<%= userParams %>"
				>

					<%
					SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);
					%>

					<liferay-ui:search-container
						headerNames="name,email-address,phone-number,role"
						searchContainer="<%= userSearchContainer %>"
					>
						<liferay-ui:search-container-results
							results="<%= userSearchContainer.getResults() %>"
							total="<%= userSearchContainer.getTotal() %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portal.kernel.model.User"
							escapedModel="<%= true %>"
							keyProperty="userId"
							modelVar="curUser"
						>

							<%
							if (!curUser.isActive()) {
								row.setClassName("inactive");
							}

							AccountCustomer accountCustomer = null;

							int role = 0;

							try {
								accountCustomer = AccountCustomerLocalServiceUtil.getAccountCustomer(curUser.getUserId(), accountEntryId);

								role = accountCustomer.getRole();
							}
							catch (Exception e) {
								row.setSkip(true);
							}
							%>

							<liferay-ui:search-container-column-text
								name="name"
								property="fullName"
							/>

							<liferay-ui:search-container-column-text
								name="email-address"
								property="emailAddress"
							/>

							<liferay-ui:search-container-column-text
								name="phone-number"
							>

								<%
								List<Phone> phones = curUser.getPhones();

								for (Phone phone : phones) {
								%>

									<%= LanguageUtil.get(pageContext, phone.getType().getName()) %>: <%= phone.getNumber() %> <%= phone.getExtension() %> <br />

								<%
								}
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="role"
							>
								<%= LanguageUtil.get(pageContext, AccountCustomerConstants.getRoleLabel(role)) %>
							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:user-search>
			</div>
		</div>
	</c:if>

	<c:if test="<%= liferayIncOrg %>">
		<h2 class="section-heading">
			<liferay-ui:message key="special-instructions" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<pre><%= SupportUtil.getHTML(accountEntry.getInstructions()) %></pre>
			</div>
		</div>

		<%
		AccountAttachment accountAttachment = null;

		if (liferayIncOrg) {
			List<AccountAttachment> accountAttachments = AccountAttachmentServiceUtil.getAccountAttachments(accountEntryId, AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID, AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

			if (!accountAttachments.isEmpty()) {
				accountAttachment = accountAttachments.get(0);
			}
		}
		%>

		<c:if test="<%= accountAttachment != null %>">
			<h2 class="section-heading">
				<liferay-ui:message key="additional-attachments" />
			</h2>

			<div class="callout-a">
				<div class="callout-content">

					<%
					LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					accountAttachmentURL.setCopyCurrentRenderParameters(false);
					accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
					accountAttachmentURL.setResourceID("accountAttachment");
					%>

					<table class="additional-attachments">
						<tr>
							<td>
								<a href="<%= accountAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(accountAttachment.getFileName()) %></a>
							</td>
							<td>
								<liferay-ui:message key="<%= accountAttachment.getTypeLabel() %>" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</c:if>

		<h2 class="section-heading">
			<liferay-ui:message key="additional-notes" />
		</h2>

		<div class="callout-a">
			<div class="callout-content">
				<%= SupportUtil.getHTML(accountEntry.getNotes()) %>
			</div>
		</div>
	</c:if>

	<h2 class="section-heading">
		<liferay-ui:message key="addresses" />
	</h2>

	<div class="callout-a">
		<c:if test="<%= address != null %>">
			<div class="aui-helper-clearfix callout-content">
				<c:if test="<%= address.getCountryId() > 0 %>">
					<div class="aui-w33 content-column">
						<div class="content-column-content left-column">
							<span class="txt-b txt-up"><liferay-ui:message key="country" />:</span>

							<%
							Country country = CountryServiceUtil.getCountry(address.getCountryId());
							%>

							<%= HtmlUtil.escape(country.getName()) %>
						</div>
					</div>
				</c:if>

				<c:if test="<%= address.getRegionId() > 0 %>">
					<div class="aui-w33 content-column">
						<div class="content-column-content middle-column">
							<span class="txt-b txt-up"><liferay-ui:message key="state-province" />:</span>

							<%
							Region region = RegionServiceUtil.getRegion(address.getRegionId());
							%>

							<%= HtmlUtil.escape(region.getName()) %>
						</div>
					</div>
				</c:if>

				<c:if test="<%= Validator.isNotNull(address.getCity()) %>">
					<div class="aui-w33 content-column">
						<div class="content-column-content right-column">
							<span class="txt-b txt-up"><liferay-ui:message key="city" />:</span>

							<%= HtmlUtil.escape(address.getCity()) %>
						</div>
					</div>
				</c:if>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<c:if test="<%= Validator.isNotNull(address.getStreet1()) %>">
					<div class="aui-w66 content-column">
						<div class="content-column-content left-column">
							<span class="txt-b txt-up"><liferay-ui:message key="address-line" />:</span>

							<%= HtmlUtil.escape(address.getStreet1()) %>
						</div>
					</div>
				</c:if>

				<c:if test="<%= Validator.isNotNull(address.getZip()) %>">
					<div class="aui-w33 content-column">
						<div class="content-column-content right-column">
							<span class="txt-b txt-up"><liferay-ui:message key="postal-code" />:</span>

							<%= HtmlUtil.escape(address.getZip()) %>
						</div>
					</div>
				</c:if>
			</div>
		</c:if>

		<br />

		<div class="aui-helper-clearfix callout-content">
			<div class="aui-w100 content-column">
				<div class="content-column-content">
					<span class="txt-b txt-up"><liferay-ui:message key="support-region" />:</span>

					<%= HtmlUtil.escape(ListUtil.toString(supportRegions, "name")) %>
				</div>
			</div>
		</div>
	</div>

	<div>
		<input class="aui-button-input" onClick="location.href = '<portlet:renderURL><portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="add-ticket" />" />

		<input class="aui-button-input" onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.NORMAL.toString() %>"><portlet:param name="mvcPath" value="/support/view.jsp" /><portlet:param name="tabs1" value="tickets" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="view-tickets" />" />
	</div>

	<c:if test="<%= ticketWorker && (OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntriesCount(accountEntryId) > 0) %>">
		<div class="separator"></div>

		<liferay-ui:tabs
			names="offerings,environment-details"
			param="tabs2"
			url="<%= portletURL.toString() %>"
		/>

		<c:choose>
			<c:when test='<%= tabs2.equals("environment-details") %>'>
				<liferay-util:include page="/support/view_account_environments.jsp" servletContext="<%= application %>">
					<liferay-util:param name="redirect" value="<%= currentURL %>" />
					<liferay-util:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:otherwise>

				<%
				PortletURL searchURL = renderResponse.createRenderURL();

				searchURL.setParameter("mvcPath", "/support/edit_account_entry.jsp");
				searchURL.setParameter("redirect", redirect);
				searchURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
				%>

				<liferay-ui:search-container
					deltaParam="offeringEntryDelta"
					searchContainer="<%= new OfferingEntrySearch(renderRequest, searchURL) %>"
				>

					<%
					OfferingEntryDisplayTerms displayTerms = (OfferingEntryDisplayTerms)searchContainer.getDisplayTerms();
					%>

					<c:if test="<%= (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) %>">
						<%@ include file="/support/offering_entry_search.jspf" %>
					</c:if>

					<liferay-ui:search-container-results>

						<%
						LinkedHashMap params = new LinkedHashMap();

						if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
							params.put("user", new String[] {displayTerms.getFirstName(), displayTerms.getMiddleName(), displayTerms.getLastName(), displayTerms.getScreenName(), displayTerms.getEmailAddress()});

							results = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, searchContainer.getStart(), searchContainer.getEnd());
							total = OfferingEntryLocalServiceUtil.searchCount(0, accountEntryId, new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
						}
						else {
							List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);

							results = ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd());
							total = offeringEntryGroups.size();
						}

						pageContext.setAttribute("results", results);
						pageContext.setAttribute("total", total);
						%>

					</liferay-ui:search-container-results>

					<liferay-ui:search-container-row
						className="com.liferay.osb.model.OfferingEntryGroup"
						modelVar="offeringEntryGroup"
					>

						<%
						String userName = StringPool.BLANK;

						try {
							User curUser = UserLocalServiceUtil.getUser(offeringEntryGroup.getUserId());

							userName = curUser.getEmailAddress();
						}
						catch (Exception e) {
							userName = offeringEntryGroup.getUserName();
						}

						ProductEntry productEntry = offeringEntryGroup.getProductEntry();
						SupportResponse supportResponse = offeringEntryGroup.getSupportResponse();

						String key = offeringEntryGroup.getKey();
						%>

						<c:choose>
							<c:when test="<%= accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL %>">
								<liferay-ui:search-container-column-text
									name="owner"
									value="<%= HtmlUtil.escape(userName) %>"
								/>
							</c:when>
							<c:otherwise>
								<liferay-ui:search-container-column-text
									name="product"
									value="<%= productEntry.getName() %>"
								/>
							</c:otherwise>
						</c:choose>

						<liferay-ui:search-container-column-text
							name="sla"
							value="<%= supportResponse.getName() %>"
						/>

						<liferay-ui:search-container-column-text
							name="start-date"
							value="<%= longDateFormatDate.format(offeringEntryGroup.getActualStartDate()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="support-end-date"
							value="<%= longDateFormatDate.format(offeringEntryGroup.getSupportEndDate()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="licenses"
						>
							<%= offeringEntryGroup.getLicenseKeysCount() %> / <%= offeringEntryGroup.getQuantity() %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="tickets"
						>
							<%= offeringEntryGroup.getTicketEntriesCount() %> / <%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(pageContext, "unlimited") : "0" %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="type"
							value="<%= LanguageUtil.get(pageContext, OfferingEntryConstants.getTypeLabel(offeringEntryGroup.getType())) %>"
						/>

						<liferay-ui:search-container-column-text
							name="status"
						>

							<%
							String statusOnBlur = StringPool.BLANK;
							String statusOnClick = StringPool.BLANK;

							if (OSBOfferingEntryPermission.contains(permissionChecker, offeringEntryGroup.getFirstOfferingEntry(), OSBActionKeys.UPDATE)) {
								statusOnBlur = renderResponse.getNamespace() + "toggleForm('" + renderResponse.getNamespace() + "status_" + key + "', '" + renderResponse.getNamespace() + "statusDisplay_" + key + "');";

								StringBuilder sb = new StringBuilder();

								sb.append(renderResponse.getNamespace());
								sb.append("toggleForm('");
								sb.append(renderResponse.getNamespace());
								sb.append("statusDisplay_");
								sb.append(key);
								sb.append("', '");
								sb.append(renderResponse.getNamespace());
								sb.append("status_");
								sb.append(key);
								sb.append("'); document.");
								sb.append(renderResponse.getNamespace());
								sb.append("fm.");
								sb.append(renderResponse.getNamespace());
								sb.append("status_");
								sb.append(key);
								sb.append(".focus();");

								statusOnClick = sb.toString();
							}
							%>

							<div onClick="<%= statusOnClick %>">
								<div id="<portlet:namespace />statusDisplay_<%= key %>">
									<%= LanguageUtil.get(pageContext, OfferingEntryConstants.getStatusLabel(offeringEntryGroup.getStatus())) %>
								</div>

								<div id="<portlet:namespace />status_<%= key %>" style="display: none;">
									<c:if test="<%= Validator.isNotNull(statusOnClick) %>">
										<select name="<portlet:namespace />status_<%= key %>" onBlur="<%= statusOnBlur %>" onChange="<portlet:namespace />updateOfferingEntry('<%= key %>', '<%= StringUtil.merge(offeringEntryGroup.getOfferingEntryIds()) %>');">

											<%
											for (int i = 1; i <= 3; i++) {
											%>

												<option <%= (offeringEntryGroup.getStatus() == i) ? "selected" : "" %> value="<%= i %>"><%= LanguageUtil.get(pageContext, OfferingEntryConstants.getStatusLabel(i)) %></option>

											<%
											}
											%>

										</select>
									</c:if>
								</div>
							</div>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<div class="table-report">
						<liferay-ui:search-iterator />
					</div>
				</liferay-ui:search-container>
			</c:otherwise>
		</c:choose>
	</c:if>
</aui:form>

<%
String tierMessage = HtmlUtil.escapeJS(SupportUtil.getPreferenceValue(locale, "tierMessage_" + accountEntry.getTier()));
%>

<aui:script>
	<c:if test="<%= Validator.isNotNull(tierMessage) %>">
		AUI().ready(
			'aui-tooltip',
			function(A) {
				new A.Tooltip(
					{
						bodyContent: '<%= tierMessage %>',
						trigger: '#<portlet:namespace />tierDisplay'
					}
				).render();
			}
		);
	</c:if>
</aui:script>