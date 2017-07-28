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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/view.jsp");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

AccountInformationDisplay accountInformationDisplay = AccountInformationLocalServiceUtil.getAccountInformationDisplay(accountEntryId);

boolean hasUpdateAccountInfoPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO);

Format accountCallFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd hh:mm a", locale, timeZone);
%>

<c:if test="<%= liferayIncOrg %>">
	<portlet:actionURL name="updateAccountInformation" var="updateAccountInformationURL">
		<portlet:param name="mvcPath" value="/support/edit_account_entry.jsp" />
		<portlet:param name="tabs1" value="project-details" />
	</portlet:actionURL>

	<aui:form action="<%= updateAccountInformationURL.toString() %>" method="post" name="fm">
		<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(currentURL) %>" />
		<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
		<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />
		<input name="<portlet:namespace />section" type="hidden" />

		<div class="callout-a <%= (!screenShareMode && (accountEntry.getTier() != AccountEntryConstants.TIER_REGULAR)) ? "tier-" + AccountEntryConstants.getTierLabel(accountEntry.getTier()) : StringPool.BLANK %>">
			<div class="callout-content cleared">
				<div class="fl">
					<div class="txt-b txt-up">
						<liferay-ui:message key="project-name" />
					</div>

					<div class="txt-b txt-h1">
						<div>

							<%
							long customerPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

							PortletURL accountEntryprojectDetailsURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_SUPPORT, customerPlid, PortletRequest.RENDER_PHASE);

							accountEntryprojectDetailsURL.setParameter("mvcPath", "/support/edit_account_entry.jsp");
							accountEntryprojectDetailsURL.setParameter("tabs1", "project-details");
							accountEntryprojectDetailsURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
							accountEntryprojectDetailsURL.setParameter("friendly", String.valueOf(Boolean.TRUE));
							%>

							<a href="<%= accountEntryprojectDetailsURL.toString() %>"><%= HtmlUtil.escape(accountEntry.getName()) %></a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<%
		for (String section : AccountInformationConstants.SECTIONS_CUSTOMER_INFO) {
		%>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id='<%= section + "Panel" %>' persistState="<%= true %>" title="<%= section %>">
				<div class="callout-a">
					<c:choose>
						<c:when test="<%= section.equals(AccountInformationConstants.SECTION_CALL_LOG) %>">

							<%
							List<AccountCall> accountCalls = AccountCallLocalServiceUtil.getAccountCalls(accountEntryId);
							%>

							<c:choose>
								<c:when test="<%= !accountCalls.isEmpty() %>">
									<div class="account-calls" id="<portlet:namespace />accountCallsContainer">
										<div class="callout-content">
											<table class="taglib-search-iterator">
												<tr class="portlet-section-header results-header">
													<th class="col-1 first" />

													<th class="col-2">
														<liferay-ui:message key="date" />
													</th>
													<th class="col-3">
														<liferay-ui:message key="summary" />
													</th>
												</tr>

												<%
												for (AccountCall accountCall : accountCalls) {
													StringBuilder sb = new StringBuilder();

													sb.append("javascript:");
													sb.append(renderResponse.getNamespace());
													sb.append("toggleAccountCall('");
													sb.append(accountCall.getAccountCallId());
													sb.append("');");

													String rowHREF = sb.toString();
												%>

													<tr class="collapsed" id="<portlet:namespace />accountCall_<%= accountCall.getAccountCallId() %>">
														<td class="col col-1 first">
															<liferay-ui:icon
																cssClass="down"
																image="../arrows/05_down"
																label="<%= false %>"
																url="<%= rowHREF %>"
															/>

															<liferay-ui:icon
																cssClass="up"
																image="../arrows/05_up"
																label="<%= false %>"
																url="<%= rowHREF %>"
															/>
														</td>
														<td class="col col-2">
															<a href="<%= rowHREF %>"><%= accountCallFormat.format(accountCall.getCallDate()) %></a>
														</td>
														<td class="col col-3">
															<a href="<%= rowHREF %>"><%= HtmlUtil.escape(StringUtil.shorten(accountCall.getSummary(), 115)) %></a>
														</td>
													</tr>
													<tr class="account-calls collapsed" id="<portlet:namespace />accountCallDetails_<%= accountCall.getAccountCallId() %>">
														<td class="panel" colspan="3">
															<div class="callout-a">
																<div class="aui-helper-clearfix callout-content">
																	<div class="aui-w33 content-column">
																		<div class="customer-info-label">
																			<liferay-ui:message key="call-type" />
																		</div>

																		<%= accountCall.getTypeLabel() %>
																	</div>

																	<div class="aui-w33 content-column">
																		<div class="customer-info-label">
																			<liferay-ui:message key="call-date" />
																		</div>

																		<%= accountCallFormat.format(accountCall.getCallDate()) %>
																	</div>

																	<div class="aui-w33 content-column">
																		<div class="customer-info-label">
																			<liferay-ui:message key="call-length" />
																		</div>

																		<%= accountCall.getCallLengthLabel() %>
																	</div>
																</div>

																<div class="aui-helper-clearfix callout-content">
																	<div class="aui-w15 content-column customer-info-label">
																		<liferay-ui:message key="summary" />
																	</div>

																	<div class="aui-w85 content-column customer-info">
																		<div class="customer-info-display">
																			<pre><%= HtmlUtil.escape(accountCall.getSummary()) %></pre>
																		</div>
																	</div>
																</div>

																<div class="aui-helper-clearfix callout-content">
																	<div class="aui-w15 content-column customer-info-label">
																		<liferay-ui:message key="clients-present" />
																	</div>

																	<div class="aui-w85 content-column customer-info">
																		<div class="customer-info-display">
																			<pre><%= HtmlUtil.escape(accountCall.getClientsPresent()) %></pre>
																		</div>
																	</div>
																</div>

																<div class="aui-helper-clearfix callout-content">
																	<div class="aui-w15 content-column customer-info-label">
																		<liferay-ui:message key="notes" />
																	</div>

																	<div class="aui-w85 content-column customer-info">
																		<div class="customer-info-display">
																			<pre><%= HtmlUtil.escape(accountCall.getNotes()) %></pre>
																		</div>
																	</div>
																</div>

																<div class="aui-helper-clearfix callout-content">
																	<div class="aui-w15 content-column customer-info-label">
																		<liferay-ui:message key="action-items" />
																	</div>

																	<div class="aui-w85 content-column customer-info">
																		<div class="customer-info-display">
																			<pre><%= HtmlUtil.escape(accountCall.getActionItems()) %></pre>
																		</div>
																	</div>
																</div>

																<c:if test="<%= hasUpdateAccountInfoPermission %>">
																	<div class="aui-helper-clearfix">
																		<div class="callout-content fr">

																			<%
																			PortletURL editAccountCallURL = renderResponse.createRenderURL();

																			editAccountCallURL.setParameter("mvcPath", "/support/edit_account_call.jsp");
																			editAccountCallURL.setParameter("accountCallId", String.valueOf(accountCall.getAccountCallId()));
																			editAccountCallURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
																			editAccountCallURL.setWindowState(LiferayWindowState.POP_UP);
																			%>

																			<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-project-call" />', '<%= editAccountCallURL.toString() %>', '<portlet:namespace />updateAccountCall')" type="button" value="<liferay-ui:message key="edit" />" />

																			<portlet:actionURL name="deleteAccountCall" var="deleteAccountCallURL">
																				<portlet:param name="redirect" value="<%= currentURL %>" />
																				<portlet:param name="accountCallId" value="<%= String.valueOf(accountCall.getAccountCallId()) %>" />
																			</portlet:actionURL>

																			<input class="aui-button-input" onClick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this-call-log") %>')) { location.href='<%= deleteAccountCallURL %>'; } else { self.focus(); }" type="button" value="<liferay-ui:message key="delete" />" />
																		</div>
																	</div>
																</c:if>
															</div>
														</td>
													</tr>

												<%
												}
												%>

											</table>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="callout-content">
										<liferay-ui:message key="there-are-no-call-logs" />
									</div>
								</c:otherwise>
							</c:choose>

							<c:if test="<%= hasUpdateAccountInfoPermission %>">
								<div class="aui-helper-clearfix">
									<div class="callout-content fr">

										<%
										PortletURL addAccountCallURL = renderResponse.createRenderURL();

										addAccountCallURL.setParameter("mvcPath", "/support/edit_account_call.jsp");
										addAccountCallURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
										addAccountCallURL.setWindowState(LiferayWindowState.POP_UP);
										%>

										<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="add-call-log" />', '<%= addAccountCallURL.toString() %>', '<portlet:namespace />updateAccountCall')" type="button" value="<liferay-ui:message key="add-call-log" />" />
									</div>
								</div>
							</c:if>
						</c:when>
						<c:otherwise>

							<%
							int[] sectionFieldIds = AccountInformationConstants.getSectionFieldIds(section);
							%>

							<div id="<%= renderResponse.getNamespace() + "sectionDisplay_" + section %>">

								<%
								for (int fieldId : sectionFieldIds) {
								%>

									<div class="aui-helper-clearfix callout-content">
										<div class="aui-w20 content-column customer-info-label">
											<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
										</div>

										<div class="aui-w80 content-column customer-info">
											<div class="customer-info-display">
												<pre><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></pre>
											</div>
										</div>
									</div>

								<%
								}
								%>

								<div class="aui-helper-clearfix">
									<div class="callout-content fr">
										<c:if test="<%= accountInformationDisplay.getModifiedDate(sectionFieldIds[0]) != null %>">
											<liferay-ui:message arguments="<%= new Object[] {accountInformationDisplay.getModifiedUserName(sectionFieldIds[0]), fullDateFormatDateTime.format(accountInformationDisplay.getModifiedDate(sectionFieldIds[0]))} %>" key="x-on-x" />
										</c:if>

										<c:if test="<%= hasUpdateAccountInfoPermission %>">
											<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= renderResponse.getNamespace() + "sectionDisplay_" + section %>', '<%= renderResponse.getNamespace() + "sectionEditDisplay_" + section %>');" type="button" value="<liferay-ui:message key="edit" />" />
										</c:if>
									</div>
								</div>
							</div>

							<c:if test="<%= hasUpdateAccountInfoPermission %>">
								<div class="aui-helper-hidden" id="<%= renderResponse.getNamespace() + "sectionEditDisplay_" + section %>">

									<%
									for (int fieldId : sectionFieldIds) {
									%>

										<div class="aui-helper-clearfix callout-content">
											<div class="aui-w20 content-column customer-info-label">
												<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
											</div>

											<div class="aui-w80 content-column customer-info">
												<textarea maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />field--<%= fieldId %>" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></textarea>
											</div>
										</div>

									<%
									}
									%>

									<div class="aui-helper-clearfix">
										<div class="callout-content fr">
											<input class="aui-button-input" onClick="<portlet:namespace />updateAccountInformation('<%= section %>');" type="button" value="<liferay-ui:message key="save" />" />

											<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= renderResponse.getNamespace() + "sectionEditDisplay_" + section %>', '<%= renderResponse.getNamespace() + "sectionDisplay_" + section %>');" type="button" value="<liferay-ui:message key="cancel" />" />
										</div>
									</div>
								</div>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</liferay-ui:panel>

		<%
		}
		%>

	</aui:form>

	<aui:script>
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
							resizable: false,
							width: 870
						},
						id: popupId,
						title: title,
						uri: url
					}
				);
			}
		);

		function <portlet:namespace />toggleAccountCall(accountCallId) {
			var A = AUI();

			var accountCall = A.one('#<portlet:namespace />accountCall_' + accountCallId);
			var accountCallDetails = A.one('#<portlet:namespace />accountCallDetails_' + accountCallId);

			if (accountCall.hasClass('collapsed')) {
				A.all("#<portlet:namespace />accountCallsContainer tr").each(
					function(item, index, collection) {
						item.addClass('collapsed');
					}
				);

				accountCall.removeClass('collapsed');
				accountCallDetails.removeClass('collapsed');
			}
			else {
				accountCall.addClass('collapsed');
				accountCallDetails.addClass('collapsed');
			}
		}

		function <portlet:namespace />toggleSection(hideId, showId) {
			var A = AUI();

			A.one("#" + hideId).hide();
			A.one("#" + showId).show();
		}

		function <portlet:namespace />updateAccountInformation(section) {
			document.<portlet:namespace />fm.<portlet:namespace />section.value = section;

			submitForm(document.<portlet:namespace />fm);
		}
	</aui:script>
</c:if>