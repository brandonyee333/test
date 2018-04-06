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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry.jsp-accountEntry");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry.jsp-portletURL");
String redirect = (String)request.getAttribute("edit_account_entry.jsp-redirect");
boolean ticketWorker = (Boolean)request.getAttribute("edit_account_entry.jsp-ticketWorker");

String detailTab = ParamUtil.getString(request, "detailTab", "contacts");

List<AccountCustomer> accountCustomers = accountEntry.getAccountCustomers();
%>

<div class="account details tab-view">
	<div class="tabs" id="<portlet:namespace />tabsDetails">
		<div>
			<c:if test="<%= ticketWorker %>">
				<span class="first" id="<portlet:namespace />contacts" onClick="<portlet:namespace />revealDetail('contacts');"><liferay-ui:message key="contacts" /> (<%= accountCustomers.size() %>)</span>
			</c:if>

			<span id="<portlet:namespace />offerings" onClick="<portlet:namespace />revealDetail('offerings');"><liferay-ui:message key="offerings" /></span>

			<span id="<portlet:namespace />environmentDetails" onClick="<portlet:namespace />revealDetail('environmentDetails');"><liferay-ui:message key="environment-details" /></span>

			<c:if test="<%= liferayIncOrg %>">
				<span id="<portlet:namespace />attachments" onClick="<portlet:namespace />revealDetail('attachments');"><liferay-ui:message key="attachments" /></span>
			</c:if>

			<span id="<portlet:namespace />accountDetails" onClick="<portlet:namespace />revealDetail('accountDetails');"><liferay-ui:message key="project-details" /></span>
		</div>
	</div>

	<div class="tab-content">
		<div>
			<c:if test="<%= ticketWorker %>">
				<div class="hide tab-content-tab" id="<portlet:namespace />contentContacts">
					<div>
						<span class="txt-b txt-up"><liferay-ui:message key="maximum-contacts" />:</span>

						<%= accountEntry.getMaxCustomers() %>
					</div>

					<br />

					<liferay-ui:search-container
						headerNames="name,email-address,phone-number,role"
					>
						<liferay-ui:search-container-results
							results="<%= accountCustomers %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.AccountCustomer"
							escapedModel="<%= true %>"
							keyProperty="accountCustomerId"
							modelVar="curAccountCustomer"
						>

							<%
							User curUser = UserLocalServiceUtil.getUser(curAccountCustomer.getUserId());

							if (!curUser.isActive()) {
								row.setClassName("inactive");
							}
							%>

							<liferay-ui:search-container-column-text
								name="name"
								value="<%= curUser.getFullName() %>"
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
								%>

									<%= LanguageUtil.get(request, phone.getType().getName()) %>: <%= phone.getNumber() %> <%= phone.getExtension() %> <br />

								<%
								}
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="role"
							>
								<%= LanguageUtil.get(request, curAccountCustomer.getRoleLabel()) %>
							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator
							paginate="<%= false %>"
						/>
					</liferay-ui:search-container>
				</div>
			</c:if>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentOfferings">

				<%
				PortletURL searchURL = renderResponse.createRenderURL();

				searchURL.setParameter("mvcPath", "/support/2/edit_account_entry.jsp");
				searchURL.setParameter("redirect", redirect);
				searchURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
				%>

				<liferay-ui:search-container
					deltaParam="offeringEntryDelta"
					searchContainer="<%= new OfferingEntrySearch(renderRequest, searchURL) %>"
				>

					<%
					OfferingEntryDisplayTerms displayTerms = (OfferingEntryDisplayTerms)searchContainer.getDisplayTerms();
					%>

					<c:if test="<%= (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) %>">
						<%@ include file="/support/2/offering_entry_search.jspf" %>
					</c:if>

					<liferay-ui:search-container-results>

						<%
						LinkedHashMap params = new LinkedHashMap();

						if (accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL) {
							params.put("user", new String[] {displayTerms.getFirstName(), displayTerms.getMiddleName(), displayTerms.getLastName(), displayTerms.getScreenName(), displayTerms.getEmailAddress()});

							results = SupportUtil.getOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true, searchContainer.getStart(), searchContainer.getEnd());
							total = OfferingEntryLocalServiceUtil.searchCount(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
						}
						else {
							results = SupportUtil.getOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);
							total = results.size();
						}

						searchContainer.setResults(results);
						searchContainer.setTotal(total);
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

						key = StringUtil.replace(key, CharPool.COMMA, CharPool.UNDERLINE);
						key = StringUtil.replace(key, CharPool.EQUAL, CharPool.UNDERLINE);
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
							<%= offeringEntryGroup.getTicketEntriesCount() %> / <%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(request, "unlimited") : "0" %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="type"
							value="<%= LanguageUtil.get(request, OfferingEntryConstants.getTypeLabel(offeringEntryGroup.getType())) %>"
						/>

						<liferay-ui:search-container-column-text
							name="status"
						>

							<%
							String statusOnBlur = StringPool.BLANK;
							String statusOnClick = StringPool.BLANK;

							if ((offeringEntryGroup.getStatus() != OfferingEntryConstants.STATUS_PENDING) && OSBOfferingEntryPermission.contains(permissionChecker, offeringEntryGroup.getFirstOfferingEntry(), OSBActionKeys.UPDATE)) {
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
								<div class="status-<%= offeringEntryGroup.getStatus() %>" id="<portlet:namespace />statusDisplay_<%= key %>">
									<%= LanguageUtil.get(request, OfferingEntryConstants.getStatusLabel(offeringEntryGroup.getStatus())) %>
								</div>

								<div id="<portlet:namespace />status_<%= key %>" style="display: none;">
									<c:if test="<%= Validator.isNotNull(statusOnClick) %>">
										<aui:select name='<%= renderResponse.getNamespace() + "status_" + key %>' onBlur="<%= statusOnBlur %>" onChange='<%= renderResponse.getNamespace() + "updateOfferingEntry('" + key + "', '" + StringUtil.merge(offeringEntryGroup.getOfferingEntryIds()) + "');" %>'>

											<%
											for (int i = 1; i <= 3; i++) {
											%>

												<aui:option label="<%= OfferingEntryConstants.getStatusLabel(i) %>" selected="<%= offeringEntryGroup.getStatus() == i %>" value="<%= i %>" />

											<%
											}
											%>

										</aui:select>
									</c:if>
								</div>
							</div>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentEnvironmentDetails">
				<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) %>">

					<%
					PortletURL addAccountEnvironmentURL = renderResponse.createRenderURL();

					addAccountEnvironmentURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
					addAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
					addAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);

					String taglibCreateEnvironment = renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "create-environment-configuration") + "', '" + addAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "updateAccountEnvironment');";
					%>

					<aui:button onClick="<%= taglibCreateEnvironment %>" value="create-environment" />

					<br /><br />
				</c:if>

				<liferay-ui:search-container
					headerNames="product,name,lr-version,application-server,database,operating-system,java,portal-ext,patch-info"
				>

					<%
					LinkedHashMap params = new LinkedHashMap();

					params.put("validTicket", StringPool.BLANK);

					List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true);

					for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
						ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
						OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

						List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntry.getAccountEntryId(), curProductEntry.getProductEntryId());
					%>

						<liferay-ui:search-container-results
							results="<%= accountEnvironments %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.osb.model.AccountEnvironment"
							escapedModel="<%= true %>"
							keyProperty="accountEnvironmentId"
							modelVar="accountEnvironment"
						>
							<liferay-ui:search-container-column-text
								name="product"
							>
								<%= curProductEntry.getName() %>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="name"
							/>

							<liferay-ui:search-container-column-text
								name="lr-version"
							>
								<%= LanguageUtil.get(request, accountEnvironment.getEnvLFRLabel()) %>

								<c:if test="<%= Validator.isNotNull(accountEnvironment.getSupportPhaseLabel()) %>">
									<span class="support-phase-label">(<%= LanguageUtil.get(request, accountEnvironment.getSupportPhaseLabel()) %>)</span>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="application-server"
								value="<%= LanguageUtil.get(request, accountEnvironment.getEnvASLabel()) %>"
							/>

							<liferay-ui:search-container-column-text
								name="database"
								value="<%= LanguageUtil.get(request, accountEnvironment.getEnvDBLabel()) %>"
							/>

							<liferay-ui:search-container-column-text
								name="operating-system"
							>
								<%= LanguageUtil.get(request, accountEnvironment.getEnvOSLabel()) %>

								<c:if test="<%= Validator.isNotNull(accountEnvironment.getEnvOSCustom()) %>">
									- <%= HtmlUtil.escape(accountEnvironment.getEnvOSCustom()) %>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="java"
								value="<%= LanguageUtil.get(request, accountEnvironment.getEnvJVMLabel()) %>"
							/>

							<liferay-ui:search-container-column-text
								name="portal-ext"
							>

								<%
								AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
								%>

								<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">

									<%
									LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

									accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
									accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
									accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
									%>

									<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank">
										<img class="ticket-img" src="<%= PortalUtil.getPathContext(request) %>/images/attachment.png" />
									</a>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="patch-info"
							>

								<%
								AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
								%>

								<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">

									<%
									LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

									accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
									accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
									accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
									%>

									<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank">
										<img class="ticket-img" src="<%= PortalUtil.getPathContext(request) %>/images/attachment.png" />
									</a>
								</c:if>
							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text>
								<liferay-util:include page="/support/2/edit_account_entry/environment_detail_action.jsp" servletContext="<%= application %>">
									<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
									<liferay-util:param name="offeringEntryId" value="<%= String.valueOf(curOfferingEntry.getOfferingEntryId()) %>" />
								</liferay-util:include>
							</liferay-ui:search-container-column-text>
						</liferay-ui:search-container-row>

					<%
					}
					%>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
					/>
				</liferay-ui:search-container>
			</div>

			<c:if test="<%= liferayIncOrg %>">
				<div class="hide tab-content-tab" id="<portlet:namespace />contentAttachments">

					<%
					List<AccountAttachment> accountAttachments = AccountAttachmentServiceUtil.getAccountAttachments(accountEntry.getAccountEntryId(), AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID, AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);
					%>

					<c:choose>
						<c:when test="<%= !accountAttachments.isEmpty() %>">

							<%
							AccountAttachment accountAttachment = accountAttachments.get(0);

							LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountAttachmentURL.setCopyCurrentRenderParameters(false);
							accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
							accountAttachmentURL.setResourceID("accountAttachment");
							%>

							<liferay-ui:message key="<%= accountAttachment.getTypeLabel() %>" />: <a href="<%= accountAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(accountAttachment.getFileName()) %></a>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="this-project-does-not-have-any-file-attachments" />
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<div class="hide tab-content-tab" id="<portlet:namespace />contentAccountDetails">

				<%
				PartnerEntry partnerEntry = accountEntry.getPartnerEntry();
				%>

				<div class="clearfix">
					<div class="content-column w33">
						<div class="content-column-content left-column">
							<liferay-ui:message key="advocacy-specialists" />:

							<span class="txt-sb"><%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) %></span>
						</div>
					</div>

					<div class="content-column w33">
						<div class="content-column-content middle-column">
							<liferay-ui:message key="sales-reps" />:

							<span class="txt-sb"><%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_SALES) %></span>
						</div>
					</div>

					<div class="content-column w33">
						<div class="content-column-content right-column">
							<liferay-ui:message key="managed-services" />:

							<span class="txt-sb"><%= SupportUtil.getAccountWorkers(accountEntry.getAccountEntryId(), AccountWorkerConstants.ROLE_MANAGED_SERVICES) %></span>
						</div>
					</div>
				</div>

				<br />

				<div class="clearfix">
					<div class="content-column w33">
						<div class="content-column-content left-column">
							<liferay-ui:message key="industry" />:

							<span class="txt-sb"><%= LanguageUtil.get(request, accountEntry.getIndustryLabel()) %></span>
						</div>
					</div>

					<div class="content-column w33">
						<div class="content-column-content middle-column">
							<liferay-ui:message key="partner" />:

							<c:if test="<%= accountEntry.isPartnerManagedSupport() %>">
								<liferay-ui:icon
									image="telephone"
									message="partner-first-line-support"
								/>
							</c:if>

							<c:if test="<%= partnerEntry != null %>">
								<liferay-portlet:renderURL varImpl="partnerURL">
									<portlet:param name="mvcPath" value="/support/2/edit_partner_entry.jsp" />
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
								</liferay-portlet:renderURL>

								<span class="txt-sb"><a href="<%= partnerURL %>"><%= HtmlUtil.escape(partnerEntry.getCode()) %></a></span>
							</c:if>
						</div>
					</div>
				</div>

				<%
				Address address = accountEntry.getAddress();
				%>

				<c:if test="<%= address != null %>">
					<br />

					<div class="clearfix">
						<div class="content-column left-column">
							<liferay-ui:message key="address" />:
						</div>

						<div class="content-column">
							<span class="txt-sb">
								<c:if test="<%= Validator.isNotNull(address.getStreet1()) %>">
									<%= HtmlUtil.escape(address.getStreet1()) %><br />
								</c:if>

								<c:if test="<%= Validator.isNotNull(address.getStreet2()) %>">
									<%= HtmlUtil.escape(address.getStreet2()) %><br />
								</c:if>

								<c:if test="<%= Validator.isNotNull(address.getStreet3()) %>">
									<%= HtmlUtil.escape(address.getStreet3()) %><br />
								</c:if>

								<%
								StringBundler sb = new StringBundler(5);

								if (Validator.isNotNull(address.getCity())) {
									sb.append(address.getCity());
								}

								if (address.getRegionId() > 0) {
									Region region = RegionServiceUtil.getRegion(address.getRegionId());

									if (sb.index() > 0) {
										sb.append(StringPool.COMMA_AND_SPACE);
									}

									sb.append(region.getName());
								}

								if (Validator.isNotNull(address.getZip())) {
									if (sb.index() > 0) {
										sb.append(StringPool.COMMA_AND_SPACE);
									}

									sb.append(address.getZip());
								}
								%>

								<c:if test="<%= Validator.isNotNull(sb.toString()) %>">
									<%= HtmlUtil.escape(sb.toString()) %><br />
								</c:if>

								<c:if test="<%= address.getCountryId() > 0 %>">

									<%
									Country country = CountryServiceUtil.getCountry(address.getCountryId());
									%>

									<%= HtmlUtil.escape(country.getName()) %>
								</c:if>
							</span>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />openDialog(title, url, popupId) {
		Liferay.Util.openWindow(
			{
				cache: false,
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					centered: true,
					close: false,
					cssClass: 'edit-account-dialog',
					draggable: false,
					modal: true,
					resizable: false,
					width: 760
				},
				id: popupId,
				title: title,
				uri: url
			}
		);
	}

	function <portlet:namespace />updateOfferingEntry(key, offeringEntryIds) {

		<%
		String detailTabURL = HttpUtil.addParameter(portletURL.toString(), "detailTab", "offerings");
		%>

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = '<%= detailTabURL %>';
		document.<portlet:namespace />fm.<portlet:namespace />key.value = key;
		document.<portlet:namespace />fm.<portlet:namespace />offeringEntryIds.value = offeringEntryIds;

		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="updateOfferingEntry"><portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" /></portlet:actionURL>');
	}

	Liferay.provide(
		window,
		'<portlet:namespace />revealDetail',
		function(id) {
			var A = AUI();

			var tab = A.one('.details .tabs #<portlet:namespace />' + id);

			var revealId = 'contacts';

			if (tab) {
				revealId = id;
			}

			<portlet:namespace />reveal('.details', revealId);
		},
		['aui-base']
	);

	<portlet:namespace />revealDetail('<%= HtmlUtil.escape(detailTab) %>');
</aui:script>

<aui:script use="aui-tooltip">
	A.all('.status-<%= OfferingEntryConstants.STATUS_PENDING %>').each(
		function(item) {
			new A.Tooltip(
				{
					align: {
						node: null,
						points: ['tl', 'bl']
					},
					arrow: 'tl',
					bodyContent: '<liferay-ui:message key="the-pending-status-can-only-be-set-automatically-through-workflow-tasks" />',
					hideDelay: 0,
					trigger: item
				}
			).render();
		}
	);
</aui:script>