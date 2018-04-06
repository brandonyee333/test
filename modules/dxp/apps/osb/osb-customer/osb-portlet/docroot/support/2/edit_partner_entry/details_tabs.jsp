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

<%@ include file="/support/2/init.jsp" %>

<%
PartnerEntry partnerEntry = (PartnerEntry)request.getAttribute("edit_partner_entry.jsp-partnerEntry");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_partner_entry.jsp-portletURL");

String detailTab = ParamUtil.getString(request, "detailTab", "contacts");

List<PartnerWorker> partnerWorkers = partnerEntry.getPartnerWorkers();
List<PartnerEntry> childPartnerEntries = partnerEntry.getChildPartnerEntries(true);
List<AccountEntry> accountEntries = partnerEntry.getAccountEntries();
%>

<div class="details partner tab-view">
	<div class="tabs" id="<portlet:namespace />tabsDetails">
		<div>
			<span class="first" id="<portlet:namespace />contacts" onClick="<portlet:namespace />revealDetail('contacts');"><liferay-ui:message key="contacts" />(<%= partnerWorkers.size() %>)</span>

			<span id="<portlet:namespace />phones" onClick="<portlet:namespace />revealDetail('phones');"><liferay-ui:message key="phones" /></span>

			<span id="<portlet:namespace />addresses" onClick="<portlet:namespace />revealDetail('addresses');"><liferay-ui:message key="addresses" /></span>

			<span id="<portlet:namespace />accounts" onClick="<portlet:namespace />revealDetail('accounts');"><liferay-ui:message key="projects" /></span>

			<c:if test="<%= liferayIncOrg %>">
				<span id="<portlet:namespace />additionalNotes" onClick="<portlet:namespace />revealDetail('additionalNotes');"><liferay-ui:message key="additional-notes" /></span>
			</c:if>

			<span id="<portlet:namespace />childPartners" onClick="<portlet:namespace />revealDetail('childPartners');"><liferay-ui:message key="child-partners" /></span>
		</div>
	</div>

	<div class="tab-content">
		<div>
			<div class="hide tab-content-tab" id="<portlet:namespace />contentContacts">
				<liferay-ui:search-container
					headerNames="name,email-address,phone-number,role"
					total="<%= partnerWorkers.size() %>"
				>
					<liferay-ui:search-container-results
						results="<%= partnerWorkers %>"
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
							value="<%= HtmlUtil.escape(curUser.getFullName()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="email-address"
							value="<%= curUser.getEmailAddress() %>"
						/>

						<liferay-ui:search-container-column-text
							name="phone-number"
						>

							<%
							List<Phone> phones = curUser.getPhones();

							for (Phone phone : phones) {
								ListType phoneType = phone.getType();
							%>

								<%= LanguageUtil.get(request, phoneType.getName()) %>: <%= phone.getNumber() %> <%= phone.getExtension() %> <br />

							<%
							}
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="role"
							value="<%= LanguageUtil.get(request, PartnerWorkerConstants.getRoleLabel(partnerWorker.getRole())) %>"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentPhones">

				<%
				request.setAttribute("phones.className", PartnerEntry.class.getName());
				request.setAttribute("phones.classPK", partnerEntry.getPartnerEntryId());
				%>

				<div id="phones">
					<liferay-util:include page="/html/portlet/directory/organization/phone_numbers.jsp" />
				</div>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentAddresses">

				<%
				request.setAttribute("addresses.className", PartnerEntry.class.getName());
				request.setAttribute("addresses.classPK", partnerEntry.getPartnerEntryId());
				%>

				<div id="addresses">
					<liferay-util:include page="/html/portlet/directory/organization/addresses.jsp" />
				</div>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentAccounts">
				<liferay-ui:search-container
					total="<%= accountEntries.size() %>"
				>
					<liferay-ui:search-container-results
						results="<%= accountEntries %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.osb.model.AccountEntry"
						escapedModel="<%= true %>"
						keyProperty="accountEntryId"
						modelVar="accountEntry"
					>
						<portlet:renderURL var="rowURL">
							<portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" />
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
							value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>'
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>
			</div>

			<c:if test="<%= liferayIncOrg %>">
				<div class="hide tab-content-tab" id="<portlet:namespace />contentAdditionalNotes">
					<%= SupportUtil.getHTML(partnerEntry.getNotes()) %>
				</div>
			</c:if>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentChildPartners">

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
							<portlet:param name="mvcPath" value="/support/2/edit_partner_entry.jsp" />
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="partnerEntryId" value="<%= String.valueOf(childPartnerEntry.getPartnerEntryId()) %>" />
						</portlet:renderURL>

						<a href="<%= partnerEntryURL %>">
							<h4><%= HtmlUtil.escape(childPartnerEntry.getCode()) %></h4>
						</a>

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
									<portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" />
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
									value='<%= accountEntry.isPartnerManagedSupport() ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>'
								/>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator
								paginate="<%= false %>"
							/>
						</liferay-ui:search-container>
					</div>

				<%
				}
				%>

			</div>
		</div>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />revealDetail',
		function(id) {
			var A = AUI();

			var tab = A.one('.details .tabs #<portlet:namespace />' + id);

			if (tab) {
				A.all('.details .tab-content-tab').hide();

				var contentId = id.charAt(0).toUpperCase() + id.substr(1);

				var tabContent = A.one('.details .tab-content #<portlet:namespace />content' + contentId);

				tabContent.show();

				A.all('.details .tabs span').removeClass('selected');

				tab.addClass('selected');
			}
			else {
				<portlet:namespace />revealDetail('contacts');
			}

			window.scroll(0, 0);
		},
		['aui-base']
	);

	<portlet:namespace />revealDetail('<%= HtmlUtil.escape(detailTab) %>');
</aui:script>