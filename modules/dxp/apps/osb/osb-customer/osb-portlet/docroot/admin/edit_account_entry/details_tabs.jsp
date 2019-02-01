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
AccountEntry accountEntry = (AccountEntry)request.getAttribute(OSBWebKeys.ACCOUNT_ENTRY);
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
			<li class="tab" data-content="<portlet:namespace />activeOfferingsContent" id="<portlet:namespace />activeOfferings">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('activeOfferings');" %>' label="active-offerings" />
			</li>
			<li class="tab" data-content="<portlet:namespace />offeringsContent" id="<portlet:namespace />offerings">
				<aui:a href='<%= "javascript:" + renderResponse.getNamespace() + "revealTab('offerings');" %>' label="all-offerings" />
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
			<div class="hide tab-content-tab" id="<portlet:namespace />activeOfferingsContent">
				<liferay-util:include page="/admin/edit_account_entry/active_offerings.jsp" servletContext="<%= application %>" />
			</div>

			<div class="hide tab-content-tab" id="<portlet:namespace />offeringsContent">
				<liferay-util:include page="/admin/edit_account_entry/all_offerings.jsp" servletContext="<%= application %>" />
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

								<c:if test="<%= (auditEntry.getFieldClassNameId() == PortalUtil.getClassNameId(OfferingEntry.class)) && Validator.isNotNull(auditEntry.getDescription()) %>">
									<span>
										(<%= HtmlUtil.escape(auditEntry.getDescription()) %>)
									</span>
								</c:if>
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

<c:if test="<%= accountEntry != null %>">
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />updateOfferingEntry',
			function(key, accountEntryId, offeringEntryIds, newStatusValue, oldStatusValue, oldStatusLabel) {
				var A = AUI();

				var statusElement = A.one('#<portlet:namespace />status_' + key);

				var newStatusLabel = A.one('#<portlet:namespace />status_' + key + ' option:selected').html();

				if (confirm(A.Lang.sub('<liferay-ui:message key="are-you-sure-you-want-to-modify-the-status-from-x-to-x" />', [oldStatusLabel, newStatusLabel.trim()]))) {
					A.io.request(
						'<portlet:actionURL name="updateOfferingEntryStatus" />',
						{
							data: {
								<portlet:namespace />accountEntryId: accountEntryId,
								<portlet:namespace />offeringEntryIds: offeringEntryIds,
								<portlet:namespace />status: newStatusValue
							},
							dataType: 'json',
							method: 'post',
							on: {
								failure: function(event, id, obj) {
									new Liferay.Notice(
										{
											animationConfig:
											{
												duration: 1,
												top: '0px'
											},
											closeText: false,
											content: '<liferay-ui:message key="an-unexpected-error-occurred" /><button class="close" type="button">&times;</button>',
											noticeClass: 'osb-portlet-admin-alert error',
											toggleText: false,
											type: 'warning',
											useAnimation: true
										}
									);

									A.later(10000, A.one('.osb-portlet-admin-alert'), 'hide');

									statusElement.val(oldStatusValue);
								},
								success: function(event, id, obj) {
									var response = this.get('responseData');

									if (response.error) {
										new Liferay.Notice(
											{
												animationConfig:
												{
													duration: 1,
													top: '0px'
												},
												closeText: false,
												content: response.error + '<button class="close" type="button">&times;</button>',
												noticeClass: 'osb-portlet-admin-alert error',
												toggleText: false,
												type: 'warning',
												useAnimation: true
											}
										);

										A.later(10000, A.one('.osb-portlet-admin-alert'), 'hide');

										statusElement.val(oldStatusValue);
									}
									else {
										new Liferay.Notice(
											{
												animationConfig:
												{
													duration: 1,
													top: '0px'
												},
												closeText: false,
												content: '<liferay-ui:message key="your-request-processed-successfully" /><button class="close" type="button">&times;</button>',
												noticeClass: 'osb-portlet-admin-alert success',
												toggleText: false,
												type: 'notice',
												useAnimation: true
											}
										);

										A.later(5000, A.one('.osb-portlet-admin-alert'), 'hide');

										<portlet:namespace />refreshTab('activeOfferingsContent', '/admin/edit_account_entry/active_offerings.jsp');
										<portlet:namespace />refreshTab('offeringsContent', '/admin/edit_account_entry/all_offerings.jsp');
									}
								}
							}
						}
					);
				}
				else {
					statusElement.val(oldStatusValue);
				}
			},
			['aui-base', 'aui-io', 'liferay-notice']
		);

		Liferay.provide(
			window,
			'<portlet:namespace />refreshTab',
			function(tabId, mvcPath) {
				var A = AUI();

				A.io.request(
					'<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" /></portlet:renderURL>',
					{
						data: {
							<portlet:namespace />mvcPath: mvcPath
						},
						dataType: 'json',
						method: 'get',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var tabElement = A.one('#<portlet:namespace />' + tabId);

								tabElement.html(response);
							}
						}
					}
				);
			},
			['aui-base', 'aui-io']
		);
	</aui:script>
</c:if>

<aui:script>
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