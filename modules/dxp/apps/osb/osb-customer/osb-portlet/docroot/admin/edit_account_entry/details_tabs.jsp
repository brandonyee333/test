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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry.jsp-accountEntry");
String detailTab = (String)request.getAttribute("edit_account_entry.jsp-detailTab");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry.jsp-portletURL");

long corpProjectId = BeanParamUtil.getLong(accountEntry, request, "corpProjectId");

String[] languageIds = StringUtil.split(ParamUtil.getString(request, "languageIds"));

if (ArrayUtil.isEmpty(languageIds) && (accountEntry != null)) {
	languageIds = accountEntry.getLanguageIds();
}

String languageIdsValue = StringPool.BLANK;

for (String languageId : languageIds) {
	languageIdsValue += languageId + StringPool.COMMA;
}

long[] supportRegionIds = StringUtil.split(ParamUtil.getString(request, "supportRegionIds"), 0L);

List<SupportRegion> supportRegions = new ArrayList<SupportRegion>();

for (long supportRegionId : supportRegionIds) {
	supportRegions.add(SupportRegionLocalServiceUtil.getSupportRegion(supportRegionId));
}

if (supportRegions.isEmpty() && (accountEntry != null)) {
	supportRegions = accountEntry.getSupportRegions();

	supportRegionIds = new long[supportRegions.size()];

	for (int i = 0; i < supportRegions.size(); i++) {
		SupportRegion supportRegion = supportRegions.get(i);

		supportRegionIds[i] = supportRegion.getSupportRegionId();
	}
}

String supportRegionsValue = StringPool.BLANK;

for (SupportRegion supportRegion : supportRegions) {
	supportRegionsValue += supportRegion.getSupportRegionId() + StringPool.COMMA;
}
%>

<aui:input name="languageIds" type="hidden" value="<%= languageIdsValue %>" />
<aui:input name="supportRegionIds" type="hidden" value="<%= supportRegionsValue %>" />

<div class="account details tab-view">
	<ul class="lfr-nav nav nav-tabs">
		<c:if test="<%= accountEntry != null %>">
			<li class="tab" data-content="<portlet:namespace />offeringsContent" id="<portlet:namespace />offerings">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('offerings');" %>' label="offerings" />
			</li>
			<li class="tab" data-content="<portlet:namespace />environmentDetailsContent" id="<portlet:namespace />environmentDetails">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('environmentDetails');" %>' label="environment-details" />
			</li>
			<li class="tab" data-content="<portlet:namespace />projectMessagesContent" id="<portlet:namespace />projectMessages">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('projectMessages');" %>' label="project-messages" />
			</li>
		</c:if>

		<li class="tab" data-content="<portlet:namespace />supportRegionsContent" id="<portlet:namespace />supportRegions">
			<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('supportRegions');" %>' label="support-regions" />
		</li>
		<li class="tab" data-content="<portlet:namespace />supportLanguagesContent" id="<portlet:namespace />supportLanguages">
			<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('supportLanguages');" %>' label="support-languages" />
		</li>

		<c:if test="<%= accountEntry != null %>">
			<li class="tab" data-content="<portlet:namespace />historyContent" id="<portlet:namespace />history">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('history');" %>' label="history" />
			</li>
		</c:if>
	</ul>

	<div class="tab-content">
		<c:if test="<%= accountEntry != null %>">
			<div class="hide tab-content-tab" id="<portlet:namespace />offeringsContent">
				<liferay-ui:search-container
					deltaParam="offeringEntryDelta"
					searchContainer="<%= new OfferingEntrySearch(renderRequest, portletURL) %>"
				>

					<%
					OfferingEntryDisplayTerms displayTerms = (OfferingEntryDisplayTerms)searchContainer.getDisplayTerms();
					%>

					<c:if test="<%= accountEntry.getType() == AccountEntryConstants.TYPE_INDIVIDUAL %>">
						<%@ include file="/admin/offering_entry_search.jspf" %>
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
							List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, params, true);

							results = ListUtil.subList(offeringEntryGroups, searchContainer.getStart(), searchContainer.getEnd());
							total = offeringEntryGroups.size();
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

						<liferay-ui:search-container-column-text
							name="status"
						>
							<aui:select label="" name='<%= "status_" + key %>' onChange='<%= renderResponse.getNamespace() + "updateOfferingEntry('" + key + "', '" + StringUtil.merge(offeringEntryGroup.getOfferingEntryIds()) + "', '" + OfferingEntryConstants.getStatusLabel(offeringEntryGroup.getStatus()) + "');" %>'>

								<%
								for (int i = 1; i <= 3; i++) {
								%>

									<aui:option label="<%= OfferingEntryConstants.getStatusLabel(i) %>" selected="<%= offeringEntryGroup.getStatus() == i %>" value="<%= i %>" />

								<%
								}
								%>

							</aui:select>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="type"
							value="<%= LanguageUtil.get(request, OfferingEntryConstants.getTypeLabel(offeringEntryGroup.getType())) %>"
						/>

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
							value="<%= longDateFormatDate.format((offeringEntryGroup.getActualStartDate() != null) ? offeringEntryGroup.getActualStartDate() : offeringEntryGroup.getStartDate()) %>"
						/>

						<liferay-ui:search-container-column-text
							name="support-end-date"
							value="<%= longDateFormatDate.format(offeringEntryGroup.getSupportEndDate()) %>"
						/>

						<%
						long licensePlid = PortalUtil.getPlidFromPortletId(user.getGroupId(), OSBPortletKeys.OSB_LICENSE);
						%>

						<liferay-portlet:renderURL plid="<%= licensePlid %>" portletName="<%= OSBPortletKeys.OSB_LICENSE %>" var="viewLicensesURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<liferay-portlet:param name="mvcPath" value="/license/view.jsp" />
							<liferay-portlet:param name="tabs1" value="portal" />
							<liferay-portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<liferay-portlet:param name="offeringEntryIds" value="<%= StringUtil.merge(offeringEntryGroup.getOfferingEntryIds()) %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:search-container-column-text
							href="<%= viewLicensesURL %>"
							name="licenses"
						>
							<%= offeringEntryGroup.getLicenseKeysCount() %> / <%= offeringEntryGroup.getQuantity() %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="tickets"
						>
							<%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(request, "unlimited") : "0" %>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<div class="table-report">
						<liferay-ui:search-iterator
							markupView="lexicon"
						/>
					</div>
				</liferay-ui:search-container>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />projectMessagesContent">
				<c:choose>
					<c:when test="<%= corpProjectId > 0 %>">

						<%
						List<CorpProjectMessage> corpProjectMessages = CorpProjectMessageLocalServiceUtil.getCorpProjectMessages(corpProjectId);
						%>

						<liferay-ui:search-container
							emptyResultsMessage="there-are-no-messages"
							headerNames="message,type,severity,display-cp,display-lcs"
						>
							<liferay-ui:search-container-results
								results="<%= corpProjectMessages %>"
							/>

							<liferay-ui:search-container-row
								className="com.liferay.osb.model.CorpProjectMessage"
								escapedModel="<%= true %>"
								keyProperty="corpProjectMessageId"
								modelVar="corpProjectMessage"
							>
								<liferay-ui:search-container-column-text
									name="title"
									value="<%= corpProjectMessage.getTitle() %>"
								/>

								<liferay-ui:search-container-column-text
									name="message"
									value="<%= StringUtil.shorten(corpProjectMessage.getContent(), 150) %>"
								/>

								<liferay-ui:search-container-column-text
									name="type"
									translate="<%= true %>"
									value="<%= corpProjectMessage.getTypeLabel() %>"
								/>

								<liferay-ui:search-container-column-text
									name="severity"
									translate="<%= true %>"
									value="<%= corpProjectMessage.getSeverityLevelLabel() %>"
								/>

								<liferay-ui:search-container-column-text
									name="display-cp"
								>
									<c:if test="<%= corpProjectMessage.isDisplayCP() %>">
										<liferay-ui:icon
											image="checked"
										/>
									</c:if>
								</liferay-ui:search-container-column-text>

								<liferay-ui:search-container-column-text
									name="display-lcs"
								>
									<c:if test="<%= corpProjectMessage.isDisplayLCS() %>">
										<liferay-ui:icon
											image="checked"
										/>
									</c:if>
								</liferay-ui:search-container-column-text>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator
								markupView="lexicon"
								paginate="<%= false %>"
							/>
						</liferay-ui:search-container>
					</c:when>
					<c:otherwise>
						<div class="portlet-msg-info">
							<liferay-ui:message key="this-support-project-is-not-linked-to-a-corp-project" />
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />environmentDetailsContent">
				<div class="account-environment">
					<div class="environment-detail">
						<ul class="list">

							<%
							Map<String, List<AccountEnvironment>> accountEnvironmentsMap = AccountEnvironmentLocalServiceUtil.getAccountEnvironmentsMap(accountEntry.getAccountEntryId());

							for (String productEntryName : accountEnvironmentsMap.keySet()) {
							%>

								<div class="subheader">
									<%= HtmlUtil.escape(productEntryName) %>
								</div>

								<%
								ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntryByName(productEntryName);

								List<AccountEnvironment> accountEnvironments = accountEnvironmentsMap.get(productEntryName);

								for (AccountEnvironment accountEnvironment : accountEnvironments) {
								%>

									<li>
										<div class="data">
											<table class="lfr-table">
												<tr>
													<td colspan="3">
														<liferay-ui:message key="name" />:

														<span class="field">
															<%= HtmlUtil.escape(accountEnvironment.getName()) %>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														<liferay-ui:message key='<%= productEntry.isSocialOffice() ? "social-office-version" : "liferay-version" %>' />:

														<span class="field">
															<%= LanguageUtil.get(request, accountEnvironment.getEnvLFRLabel()) %>
														</span>
													</td>
													<td>
														<liferay-ui:message key="application-server" />:

														<span class="field">
															<%= LanguageUtil.get(request, accountEnvironment.getEnvASLabel()) %>
														</span>
													</td>
													<td>
														<liferay-ui:message key="database" />:

														<span class="field">
															<%= LanguageUtil.get(request, accountEnvironment.getEnvDBLabel()) %>
														</span>
													</td>
												</tr>
												<tr>
													<td>
														<liferay-ui:message key="operating-system" />:

														<span class="field">
															<%= LanguageUtil.get(request, accountEnvironment.getEnvOSLabel()) %>

															<c:if test="<%= Validator.isNotNull(accountEnvironment.getEnvOSCustom()) %>">
																- <%= HtmlUtil.escape(accountEnvironment.getEnvOSCustom()) %>
															</c:if>
														</span>
													</td>
													<td>
														<liferay-ui:message key="java-virtual-machine" />:

														<span class="field">
															<%= LanguageUtil.get(request, accountEnvironment.getEnvJVMLabel()) %>
														</span>
													</td>
													<td />
												</tr>
												<tr>
													<td colspan="3">
														<liferay-ui:message key="portal-ext" />:

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

															<span class="field">
																<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= portalExtAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
															</span>
														</c:if>

														<br />
													</td>
												</tr>
												<tr>
													<td>
														<liferay-ui:message key="patch-level" />:

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

															<span class="field">
																<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= patchLevelAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
															</span>
														</c:if>
													</td>
													<td />

													<td align="right">

														<%
														PortletURL editAccountEnvironmentURL = renderResponse.createRenderURL();

														editAccountEnvironmentURL.setParameter("mvcPath", "/admin/edit_account_environment.jsp");
														editAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
														editAccountEnvironmentURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));
														editAccountEnvironmentURL.setParameter("productEntryId", String.valueOf(productEntry.getProductEntryId()));
														editAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
														%>

														<aui:button onClick='<%= renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "edit-environment-details") + "', '" + editAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "updateAccountEnvironment')" %>' value="edit" />
													</td>
												</tr>
											</table>
										</div>
									</li>

							<%
								}
							}
							%>

						</ul>
					</div>
				</div>
			</div>
		</c:if>

		<div class="hide tab-content-tab" id="<portlet:namespace />supportRegionsContent">
			<div>
				<portlet:renderURL var="selectSupportRegionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/admin/select_support_region.jsp" />
					<portlet:param name="callback" value="selectSupportRegion" />
				</portlet:renderURL>

				<%
				String taglibSelectSupportRegion = "var categoryWindow = window.open('" + selectSupportRegionURL + "', 'category', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); categoryWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectSupportRegion %>" value="add-support-region" />
			</div>

			<br />

			<liferay-ui:search-container
				headerNames="name,,"
				id="supportRegion"
			>
				<liferay-ui:search-container-results
					results="<%= supportRegions %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.SupportRegion"
					escapedModel="<%= true %>"
					keyProperty="supportRegionId"
					modelVar="supportRegion"
				>
					<liferay-ui:search-container-column-text
						property="name"
					/>

					<liferay-ui:search-container-column-text>
						<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('supportRegionIds', '" + supportRegion.getSupportRegionId() + "', '" + renderResponse.getNamespace() + "supportRegionSearchContainer', this);" %>' value="remove" />
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					paginate="<%= false %>"
				/>
			</liferay-ui:search-container>
		</div>

		<div class="hide tab-content-tab" id="<portlet:namespace />supportLanguagesContent">
			<portlet:renderURL var="selectSupportLanguageURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value="/admin/select_language.jsp" />
			</portlet:renderURL>

			<%
			String taglibSelectSupportLanguage = "var supportLanguageWindow = window.open('" + selectSupportLanguageURL + "', 'support-language', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); supportLanguageWindow.focus();";
			%>

			<aui:button onClick="<%= taglibSelectSupportLanguage %>" value="add-support-language" />

			<br />

			<liferay-ui:search-container
				headerNames="language"
				id="language"
			>
				<liferay-ui:search-container-results
					results="<%= ListUtil.fromArray(languageIds) %>"
				/>

				<liferay-ui:search-container-row
					className="java.lang.String"
					modelVar="languageId"
				>
					<liferay-ui:search-container-column-text
						name="language"
					>
						<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageId)) %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text>
						<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('languageIds', '" + languageId + "', '" + renderResponse.getNamespace() + "languageSearchContainer', this);" %>' value="remove" />
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
					paginate="<%= false %>"
				/>
			</liferay-ui:search-container>
		</div>

		<c:if test="<%= accountEntry != null %>">
			<div class="hide history tab-content-tab" id="<portlet:namespace />historyContent">

				<%
				List<List<AuditEntry>> auditEntrySets = AuditEntryLocalServiceUtil.getAuditEntrySets(PortalUtil.getClassNameId(AccountEntry.class.getName()), accountEntry.getAccountEntryId(), new int[] {VisibilityConstants.ADMIN, VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC, VisibilityConstants.WORKERS});

				for (int i = 0; i < auditEntrySets.size(); i++) {
					List<AuditEntry> auditEntries = auditEntrySets.get(i);

					AuditEntry auditEntry = auditEntries.get(0);
				%>

					<div class="audit-entry-set" id="<portlet:namespace />auditSet<%= auditEntry.getAuditSetId() %>">
						<div class="header" id="<portlet:namespace />audit<%= auditEntry.getAuditEntryId() %>">
							<div class="user-display">

								<%
								String portraitURL = StringPool.BLANK;

								String auditEntryUserName = StringPool.BLANK;

								if (auditEntry.getUserId() != OSBConstants.USER_DEFAULT_USER_ID) {
									User auditEntryUser = UserLocalServiceUtil.getUser(auditEntry.getUserId());

									portraitURL = auditEntryUser.getPortraitURL(themeDisplay);

									auditEntryUserName = auditEntryUser.getFullName();
								}
								else {
									auditEntryUserName = "Auto";
								}
								%>

								<div class="audit user-avatar" style="background-image: url('<%= portraitURL %>&height=30&width=30')"></div>

								<span>
									<%= HtmlUtil.escape(auditEntryUserName) %>
								</span>
							</div>

							&gt;

							<span class="summary">
								<liferay-ui:message key="<%= auditEntry.getActionLabel() %>" />

								<liferay-ui:message key="<%= auditEntry.getFieldClassNameIdLabel() %>" />
							</span>

							<div class="create-date">
								<span title="<%= fullDateFormatDateTime.format(auditEntry.getCreateDate()) %>">
									<%= shortDateFormatDate.format(auditEntry.getCreateDate()) %> <%= shortDateFormatTime.format(auditEntry.getCreateDate()) %>
								</span>
							</div>
						</div>

						<div class="content">
							<c:if test="<%= auditEntry.getAction() != AuditEntryConstants.ACTION_AUDIT %>">
								<div class="column txt-sb w20">
									<div class="left-column">
										<liferay-ui:message key="field" />
									</div>
								</div>

								<div class="column txt-sb w40">
									<div class="middle-column">
										<liferay-ui:message key="original-value" />
									</div>
								</div>

								<div class="column txt-sb w40">
									<div class="content-column-content right-column">
										<liferay-ui:message key="new-value" />
									</div>
								</div>
							</c:if>

							<%
							for (int j = 0; j < auditEntries.size(); j++) {
								AuditEntry curAuditEntry = auditEntries.get(j);

								String oldLabel = curAuditEntry.getOldLabel();

								if (Validator.isNull(oldLabel)) {
									oldLabel = curAuditEntry.getOldValue();
								}

								String newLabel = curAuditEntry.getNewLabel();

								if (Validator.isNull(newLabel)) {
									newLabel = curAuditEntry.getNewValue();
								}
							%>

								<div class="clearfix"></div>

								<c:choose>
									<c:when test="<%= curAuditEntry.getAction() == AuditEntryConstants.ACTION_AUDIT %>">

										<%
										int[] outOfSyncFields = StringUtil.split(curAuditEntry.getOldValue(), 0);
										%>

										<c:choose>
											<c:when test="<%= Validator.isNotNull(curAuditEntry.getOldLabel()) %>">
												<liferay-ui:message key="<%= curAuditEntry.getOldLabel() %>" />
											</c:when>
											<c:when test="<%= ArrayUtil.isNotEmpty(outOfSyncFields) %>">
												<liferay-ui:message key="the-following-fields-are-out-of-sync-with-dossiera" />

												<%
												for (int k = 0; k < outOfSyncFields.length; k++) {
													int field = outOfSyncFields[k];
												%>

													<liferay-ui:message key="<%= AuditEntryConstants.getFieldLabel(field) %>" /><%= ((k + 1) < outOfSyncFields.length) ? StringPool.COMMA : "" %>

												<%
												}
												%>

											</c:when>
											<c:otherwise>
												<liferay-ui:message key="project-information-has-been-verified" />
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<div class="column txt-sb w20">
											<div class="left-column">
												<liferay-ui:message key="<%= curAuditEntry.getFieldLabel() %>" />
											</div>
										</div>

										<div class="column w40">
											<div class="middle-column">
												<c:choose>
													<c:when test="<%= Validator.isNull(oldLabel) %>">
														<%= AuditEntryConstants.NOT_AVAILABLE %>
													</c:when>
													<c:when test="<%= curAuditEntry.getI18n() %>">
														<liferay-ui:message key="<%= HtmlUtil.escape(oldLabel) %>" />
													</c:when>
													<c:otherwise>
														<%= HtmlUtil.escape(oldLabel) %>
													</c:otherwise>
												</c:choose>
											</div>
										</div>

										<div class="column w40">
											<div class="right-column">
												<c:choose>
													<c:when test="<%= Validator.isNull(newLabel) %>">
														<%= AuditEntryConstants.NOT_AVAILABLE %>
													</c:when>
													<c:when test="<%= curAuditEntry.getI18n() %>">
														<liferay-ui:message key="<%= HtmlUtil.escape(newLabel) %>" />
													</c:when>
													<c:otherwise>
														<%= HtmlUtil.escape(newLabel) %>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</c:otherwise>
								</c:choose>

							<%
							}
							%>

						</div>
					</div>

				<%
				}
				%>

			</div>
		</c:if>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateOfferingEntry',
		function(key, offeringEntryIds, oldStatusLabel) {
			var A = AUI();

			var newStatusLabel = A.one('#<portlet:namespace />status_' + key + ' option:selected').html();

			if (confirm(Liferay.Language.get('are-you-sure-you-want-to-modify-the-status-from-x-to-x', [oldStatusLabel, newStatusLabel.trim()]))) {
				var form = A.one('#<portlet:namespace />fm');

				if (form) {
					form.one('#<portlet:namespace />key').val(key);
					form.one('#<portlet:namespace />offeringEntryIds').val(offeringEntryIds);
					form.one('#<portlet:namespace />redirect').val('<%= portletURL.toString() %>');

					submitForm(form, '<portlet:actionURL name="updateOfferingEntryStatus"><portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" /></portlet:actionURL>');
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />reveal',
		function(tab) {
			var A = AUI();

			A.all('.details .tab-content-tab').hide();

			var tabContent = A.one('#' + tab.attr('data-content'));

			tabContent.show();

			A.all('.details .nav-tabs .tab').removeClass('active');

			tab.addClass('active');
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />revealTab',
		function(tabName) {
			var A = AUI();

			var tab;

			if (tabName) {
				tab = A.one('#<portlet:namespace />' + tabName);
			}

			if (!tab) {
				tab = A.one('.details .nav-tabs .tab');
			}

			<portlet:namespace />reveal(tab);
		},
		['aui-base']
	);

	<portlet:namespace />revealTab('<%= HtmlUtil.escape(detailTab) %>');
</aui:script>