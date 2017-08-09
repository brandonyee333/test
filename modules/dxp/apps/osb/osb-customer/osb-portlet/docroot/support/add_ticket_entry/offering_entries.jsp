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
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = null;

try {
	if (accountEntryId > 0) {
		accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
	}
}
catch (NoSuchAccountEntryException nsaee) {
}

long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");
long productEntryId = ParamUtil.getLong(request, "productEntryId");

LinkedHashMap params = new LinkedHashMap();

params.put("validTicket", StringPool.BLANK);

List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true);
%>

<h1 class="section-heading">
	<liferay-ui:message key="choose-support" />
</h1>

<div class="offering-entries" id="<portlet:namespace />offeringEntriesContainer">
	<div class="callout-a">
		<div class="callout-content">
			<div class="aui-helper-hidden portlet-msg-info" id="<portlet:namespace />supportMessageDisplay">
				<span>
					<liferay-ui:message key="important" />
				</span>

				<br />

				<span class="txt-n">
					<liferay-ui:message arguments='<%= new Object[] {"<a href=\"/group/customer/products/portal/support/service-life\">", "</a>"} %>' key="the-end-of-service-life-for-liferay-portal-6.0-ee-will-be-on-september-10-2017" />
				</span>
			</div>

			<c:if test="<%= !offeringEntryGroups.isEmpty() %>">
				<table class="taglib-search-iterator">
				<tr class="portlet-section-header results-header">
					<th class="col-1 first" />

					<th class="col-2">
						<liferay-ui:message key="product" />
					</th>
					<th class="col-3">
						<liferay-ui:message key="support" />
					</th>
					<th class="col-4">
						<liferay-ui:message key="start-date" />
					</th>
					<th class="col-5">
						<liferay-ui:message key="support-end-date" />
					</th>
					<th class="col-6">
						<liferay-ui:message key="tickets-used" />
					</th>
					<th class="col-7">
						<liferay-ui:message key="support-region" />
					</th>
					<th class="col-8 last" />
				</tr>

				<%
				boolean addTicketPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_TICKET);
				boolean updateAccountEnvironmentPermission = OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE);
				boolean digitalEnterprise = false;
				boolean offeringSelected = false;

				List<SupportRegion> supportRegions = accountEntry.getSupportRegions();

				for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
					ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
					SupportResponse curSupportResponse = offeringEntryGroup.getSupportResponse();

					long availableOfferingEntryId = 0;

					String cssClassName = StringPool.BLANK;

					if (productEntryId != curProductEntry.getProductEntryId()) {
						cssClassName = "collapsed";
					}

					String rowHREF = null;

					if (addTicketPermission && (offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_ACTIVE) && offeringEntryGroup.hasAvailableSupportTickets()) {
						OfferingEntry availableOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

						availableOfferingEntryId = availableOfferingEntry.getOfferingEntryId();

						Date now = new Date();

						if (now.after(availableOfferingEntry.getSupportEndDate())) {
							cssClassName += " expired-row";
						}

						StringBuilder sb = new StringBuilder();

						sb.append("javascript:");
						sb.append(renderResponse.getNamespace());
						sb.append("toggleAccountEnvironments('");
						sb.append(availableOfferingEntryId);
						sb.append("');");

						rowHREF = sb.toString();

						if (availableOfferingEntryId == offeringEntryId) {
							offeringSelected = true;

							if (curProductEntry.isDigitalEnterprise()) {
								digitalEnterprise = true;
							}
						}
					}
					%>

					<tr class="offering-entry <%= cssClassName %>" id="<portlet:namespace />offeringEntry_<%= availableOfferingEntryId %>">
						<td class="col col-1 first" onClick="<%= rowHREF %>">
							<c:choose>
								<c:when test="<%= rowHREF != null %>">
									<liferay-ui:icon
										cssClass="down"
										image="../arrows/05_down"
										label="<%= false %>"
									/>

									<liferay-ui:icon
										cssClass="up"
										image="../arrows/05_up"
										label="<%= false %>"
									/>
								</c:when>
								<c:otherwise>
									<liferay-ui:icon
										image="../arrows/02_x"
										label="<%= false %>"
									/>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="col col-2" onClick="<%= rowHREF %>">
							<%= HtmlUtil.escape(curProductEntry.getName()) %>
						</td>
						<td class="col col-3" onClick="<%= rowHREF %>">
							<%= HtmlUtil.escape(curSupportResponse.getName()) %>
						</td>
						<td class="col col-4" onClick="<%= rowHREF %>">
							<%= longDateFormatDate.format(offeringEntryGroup.getStartDate()) %>
						</td>
						<td class="col col-5" onClick="<%= rowHREF %>">
							<%= longDateFormatDate.format(offeringEntryGroup.getSupportEndDate()) %>
						</td>
						<td class="col col-6" onClick="<%= rowHREF %>">
							<c:choose>
								<c:when test="<%= offeringEntryGroup.getStatus() == OfferingEntryConstants.STATUS_ON_HOLD %>">
									<liferay-ui:icon
										image="lock"
										label="<%= true %>"
										message="on-hold"
									/>
								</c:when>
								<c:otherwise>
									<%= offeringEntryGroup.getTicketEntriesCount() %> / <%= offeringEntryGroup.isSupportTickets() ? LanguageUtil.get(pageContext, "unlimited") : "0" %>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="col col-7">
							<c:choose>
								<c:when test="<%= supportRegions.size() > 1 %>">
									<select id="<portlet:namespace />supportRegion_<%= availableOfferingEntryId %>" name="<portlet:namespace />supportRegion_<%= availableOfferingEntryId %>">
										<option value="0"></option>

										<%
										for (SupportRegion curSupportRegion : supportRegions) {
										%>

											<option value="<%= curSupportRegion.getSupportRegionId() %>"><%= HtmlUtil.escape(curSupportRegion.getName()) %></option>

										<%
										}
										%>

									</select>
								</c:when>
								<c:otherwise>

									<%
									long curSupportRegionId = 0;
									String curSupportRegionName = "";

									if (supportRegions.size() == 1) {
										SupportRegion curSupportRegion = supportRegions.get(0);

										curSupportRegionId = curSupportRegion.getSupportRegionId();
										curSupportRegionName = curSupportRegion.getName();
									}
									%>

									<input id="<portlet:namespace />supportRegion_<%= availableOfferingEntryId %>" name="<portlet:namespace />supportRegion_<%= availableOfferingEntryId %>" type="hidden" value="<%= curSupportRegionId %>" />

									<%= HtmlUtil.escape(curSupportRegionName) %>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="col col-8">
							<c:if test="<%= rowHREF != null %>">

								<%
								StringBundler sb = new StringBundler();

								sb.append("javascript:");
								sb.append(renderResponse.getNamespace());
								sb.append("selectAccountEnvironment('");
								sb.append(availableOfferingEntryId);
								sb.append("');");

								String updateURL = sb.toString();
								%>

								<input class="aui-button-input create-ticket-button" <%= (availableOfferingEntryId == offeringEntryId) ? "disabled" : StringPool.BLANK %> id="<%= availableOfferingEntryId %>" onClick="<%= (availableOfferingEntryId == offeringEntryId) ? updateURL : rowHREF %>" type="button" value="<%= (availableOfferingEntryId == offeringEntryId) ? LanguageUtil.get(request, "selected") : LanguageUtil.get(request, "choose") %>">
							</c:if>
						</td>
					</tr>
					<tr class="account-environments <%= (productEntryId != curProductEntry.getProductEntryId()) ? "collapsed" : "" %>" id="<portlet:namespace />accountEnvironments_<%= availableOfferingEntryId %>">
						<td></td>
						<td class="panel" colspan="7">

							<%
							List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntryId, curProductEntry.getProductEntryId());

							for (AccountEnvironment curAccountEnvironment : accountEnvironments) {
							%>

								<table class="callout-a">
								<tr>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

										<%= HtmlUtil.escape(curAccountEnvironment.getName()) %>
									</td>
									<td class="env-details-cell" colspan="2" />
								</tr>
								<tr>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="liferay-version" />:</span>

										<%= LanguageUtil.get(pageContext, curAccountEnvironment.getEnvLFRLabel()) %>

										<c:if test="<%= Validator.isNotNull(curAccountEnvironment.getSupportPhaseLabel()) %>">
											<span class="support-phase-label">(<%= LanguageUtil.get(pageContext, curAccountEnvironment.getSupportPhaseLabel()) %>)</span>
										</c:if>
									</td>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="application-server" />:</span>

										<%= LanguageUtil.get(pageContext, curAccountEnvironment.getEnvASLabel()) %>
									</td>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="database" />:</span>

										<%= LanguageUtil.get(pageContext, curAccountEnvironment.getEnvDBLabel()) %>
									</td>
								</tr>
								<tr>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="operating-system" />:</span>

										<%= LanguageUtil.get(pageContext, curAccountEnvironment.getEnvOSLabel()) %>

										<c:if test="<%= Validator.isNotNull(curAccountEnvironment.getEnvOSCustom()) %>">
											- <%= HtmlUtil.escape(curAccountEnvironment.getEnvOSCustom()) %>
										</c:if>
									</td>
									<td class="env-details-cell">
										<span class="txt-b txt-up"><liferay-ui:message key="java-virtual-machine" />:</span>

										<%= LanguageUtil.get(pageContext, curAccountEnvironment.getEnvJVMLabel()) %>
									</td>
									<td class="env-details-cell" />
								</tr>
								<tr>
									<td class="env-details-cell" colspan="2">
										<span class="txt-b txt-up"><liferay-ui:message key="portal-ext" />:</span>

										<%
										AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(curAccountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
										%>

										<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">
											<c:choose>
												<c:when test="<%= portalExtAccountEnvironmentAttachment.fileExists() %>">

													<%
													LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

													accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
													accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
													accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
													%>

													<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %></a>

													<i><liferay-ui:message arguments="<%= longDateFormatDateTime.format(portalExtAccountEnvironmentAttachment.getModifiedDate()) %>" key="last-updated-on-x" /></i>
												</c:when>
												<c:otherwise>
													<span class="portlet-msg-error" style="display: inline;"><liferay-ui:message arguments="<%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %>" key="file-x-is-missing-please-replace-it" /></span>
												</c:otherwise>
											</c:choose>
										</c:if>
									</td>
									<td class="env-details-cell">
										<input class="aui-button-input fr" onClick="javascript:<portlet:namespace />toggleSelect('<%= availableOfferingEntryId %>', '<%= curAccountEnvironment.getAccountEnvironmentId() %>', '<%= curProductEntry.isTicketComponentRequired() %>');" type="button" value="<liferay-ui:message key="select" />" />
									</td>
								</tr>
								<tr>
									<td class="env-details-cell" colspan="2">
										<span class="txt-b txt-up"><liferay-ui:message key="patch-level" />:</span>

										<%
										AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(curAccountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
										%>

										<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">
											<c:choose>
												<c:when test="<%= patchLevelAccountEnvironmentAttachment.fileExists() %>">

													<%
													LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

													accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
													accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
													accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
													%>

													<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= patchLevelAccountEnvironmentAttachment.getFileName() %></a>

													<i><liferay-ui:message arguments="<%= longDateFormatDateTime.format(patchLevelAccountEnvironmentAttachment.getModifiedDate()) %>" key="last-updated-on-x" /></i>
												</c:when>
												<c:otherwise>
													<span class="portlet-msg-error" style="display: inline;"><liferay-ui:message arguments="<%= HtmlUtil.escape(patchLevelAccountEnvironmentAttachment.getFileName()) %>" key="file-x-is-missing-please-replace-it" /></span>
												</c:otherwise>
											</c:choose>
										</c:if>
									</td>
									<td align="right" class="env-details-cell">
										<c:if test="<%= updateAccountEnvironmentPermission %>">

											<%
											PortletURL editEnvironmentDetailsURL = renderResponse.createRenderURL();

											editEnvironmentDetailsURL.setParameter("mvcPath", "/support/edit_account_environment.jsp");
											editEnvironmentDetailsURL.setParameter("accountEnvironmentId", String.valueOf(curAccountEnvironment.getAccountEnvironmentId()));
											editEnvironmentDetailsURL.setParameter("offeringEntryId", String.valueOf(availableOfferingEntryId));
											editEnvironmentDetailsURL.setWindowState(LiferayWindowState.POP_UP);
											%>

											<input class="aui-button-input fr" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-environment-details" />', '<%= editEnvironmentDetailsURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" type="button" value="<liferay-ui:message key="edit" />" />
										</c:if>
									</td>
								</tr>
								</table>

							<%
							}

							PortletURL addAccountEnvironmentURL = renderResponse.createRenderURL();

							addAccountEnvironmentURL.setParameter("mvcPath", "/support/edit_account_environment.jsp");
							addAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
							addAccountEnvironmentURL.setParameter("offeringEntryId", String.valueOf(availableOfferingEntryId));
							addAccountEnvironmentURL.setParameter("productEntryId", String.valueOf(curProductEntry.getProductEntryId()));
							addAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
							%>

							<div>
								<c:if test="<%= accountEnvironments.isEmpty() %>">
									<span class="empty-environments-message">
										<liferay-ui:message key="there-are-no-saved-environment-details.-please-add-environment-details-or-continue-without-adding" />
									</span>
								</c:if>

								<span class="actions">
									<input class="aui-button-input" onClick="javascript:<portlet:namespace />toggleSelect('<%= availableOfferingEntryId %>', '0', '<%= curProductEntry.isTicketComponentRequired() %>');" type="button" value="<liferay-ui:message key="continue-without-adding" />" />

									<c:if test="<%= updateAccountEnvironmentPermission %>">
										<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="add-environment-details" />', '<%= addAccountEnvironmentURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" type="button" value="<liferay-ui:message key="add" />" />
									</c:if>
								</span>
							</div>
						</td>
					</tr>

				<%
				}
				%>

				</table>

				<c:if test="<%= !digitalEnterprise && offeringSelected %>">

					<%
					PortletPreferences preferences = SupportUtil.getPortletPreferences();

					String dxpTitle = GetterUtil.getString(preferences.getValue("dxpTitle", StringPool.BLANK));
					String dxpMessage = GetterUtil.getString(preferences.getValue("dxpMessage", StringPool.BLANK));
					%>

					<div>
						<div class="dxp-message" data-pop-display="#<portlet:namespace />dxpMessage" id="<portlet:namespace />dxpTitle">
							<%= HtmlUtil.escape(dxpTitle) %>
						</div>
					</div>

					<div class="aui-helper-hidden page-pop-up" data-overlay="true" id="<portlet:namespace />dxpMessage">
						<div id="closePopUp">&nbsp;</div>

						<div class="pop-up-content">
							<%= HtmlUtil.escape(dxpMessage) %>
						</div>
					</div>
				</c:if>
			</c:if>
		</div>
	</div>
</div>

<aui:script>
	AUI().ready(
		'aui-overlay-mask',
		'node',
		function(A) {
			var centerOnPage = function(node) {
				var WIN = A.getWin();

				var winHeight = WIN.get('innerHeight');

				if (winHeight == undefined) {
					winHeight = document.documentElement.clientHeight;
				}

				var winWidth = WIN.get('innerWidth');

				if (winWidth == undefined) {
					winWidth = document.documentElement.clientWidth;
				}

				var nodeHeight = node.get('clientHeight');
				var nodeWidth = node.get('clientWidth');

				var xCenter = (winWidth / 2) - (nodeWidth / 2);
				var yCenter = (winHeight / 2) - (nodeHeight / 2);

				node.setStyle('left', xCenter);
				node.setStyle('top', yCenter / 2);
			};

			var createOverlayMask = function() {
				var bindUI = function() {
					var overlayMask = A.one('.aui-overlaymask');

					if (overlayMask) {
						overlayMask.on(
							'click',
							function() {
								overlayMask.remove(true);
							}
						);
					}
				};

				var init = function() {
					var overlay = new A.OverlayMask().render();

					overlay.set('z-index', 20);
					overlay.show();

					bindUI();
				};

				return init();
			};

			var displayPopUp = function(node) {
				node.removeClass('aui-helper-hidden');

				centerOnPage(node);

				var popUpContent = node.one('.pop-up-content');

				if (node.getAttribute('data-overlay')) {
					createOverlayMask();
				}

				popUpContent.on(
					'clickoutside',
					function(event) {
						var overlayMask = A.one('.aui-overlaymask');

						if (overlayMask) {
							overlayMask.remove();
						}

						node.addClass('aui-helper-hidden');

						popUpContent.detach('clickoutside');
					}
				);
			};

			A.all('.close-announcement').on(
				'click',
				function(event) {
					var announcementContainer = event.currentTarget.ancestor();

					announcementContainer.hide();
				}
			);

			var iOS = /iPad/i.test(navigator.userAgent) || /iPhone/i.test(navigator.userAgent);
			var mouseEvent = 'click';

			if (iOS) {
				mouseEvent = 'mousemove';
			}

			A.one('#<portlet:namespace />dxpTitle').on(
				mouseEvent,
				function(event) {
					event.stopPropagation();

					var popDisplay = event.currentTarget.getAttribute("data-pop-display");

					var popUp = A.one(popDisplay);

					displayPopUp(popUp);
				}
			);
		}
	);

	function <portlet:namespace />toggleAccountEnvironments(offeringEntryId) {
		var A = AUI();

		var accountEnvironments = A.one('#<portlet:namespace />accountEnvironments_' + offeringEntryId);
		var offeringEntry = A.one('#<portlet:namespace />offeringEntry_' + offeringEntryId);

		if (accountEnvironments.hasClass('collapsed')) {
			A.all("#<portlet:namespace />offeringEntriesContainer tr").each(
				function(item, index, collection) {
					item.addClass('collapsed');
				}
			);

			accountEnvironments.removeClass('collapsed');
			offeringEntry.removeClass('collapsed');
		}
		else {
			accountEnvironments.addClass('collapsed');
			offeringEntry.addClass('collapsed');
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />openDialog',
		function(title, url, popupId) {
			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						centered: true,
						resizable: true
					},
					id: popupId,
					title: title,
					uri: url
				}
			);
		}
	);
</aui:script>