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

<c:if test="<%= liferayIncOrg %>">

	<%
	AccountEntry accountEntry = (AccountEntry)request.getAttribute("edit_account_entry.jsp-accountEntry");
	PortletURL portletURL = (PortletURL)request.getAttribute("edit_account_entry.jsp-portletURL");
	boolean ticketWorker = (Boolean)request.getAttribute("edit_account_entry.jsp-ticketWorker");

	String additionalInfoTab = ParamUtil.getString(request, "additionalInfoTab", "callLog");

	AccountInformationDisplay accountInformationDisplay = AccountInformationLocalServiceUtil.getAccountInformationDisplay(accountEntry.getAccountEntryId());

	boolean hasUpdateAccountInfoPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE_ACCOUNT_INFO);
	%>

	<div class="account-additional-info tab-view">
		<div class="tabs">
			<div>
				<span class="first" id="<portlet:namespace />callLog" onClick="<portlet:namespace />revealAdditionalInfo('callLog');"><liferay-ui:message key="call-log" /></span>

				<span id="<portlet:namespace />casInformation" onClick="<portlet:namespace />revealAdditionalInfo('casInformation');"><liferay-ui:message key="cas-information" /></span>

				<span id="<portlet:namespace />salesInformation" onClick="<portlet:namespace />revealAdditionalInfo('salesInformation');"><liferay-ui:message key="sales-information" /></span>

				<span id="<portlet:namespace />supportInstructions" onClick="<portlet:namespace />revealAdditionalInfo('supportInstructions');"><liferay-ui:message key="support-instructions" /></span>

				<span id="<portlet:namespace />projectNotes" onClick="<portlet:namespace />revealAdditionalInfo('projectNotes');"><liferay-ui:message key="project-notes" /></span>
			</div>
		</div>

		<div class="tab-content">
			<div>
				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />contentCallLog">

					<%
					List<AccountCall> accountCalls = AccountCallLocalServiceUtil.getAccountCalls(accountEntry.getAccountEntryId());

					Format accountCallFormat = FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd hh:mm a", locale, timeZone);
					%>

					<c:choose>
						<c:when test="<%= !accountCalls.isEmpty() %>">
							<div class="account-calls" id="<portlet:namespace />accountCallsContainer">
								<table class="taglib-search-iterator">
									<tr class="portlet-section-header results-header">
										<th />

										<th>
											<liferay-ui:message key="date" />
										</th>
										<th>
											<liferay-ui:message key="summary" />
										</th>
									</tr>

									<%
									for (AccountCall accountCall : accountCalls) {
										StringBundler sb = new StringBundler(5);

										sb.append("javascript:");
										sb.append(renderResponse.getNamespace());
										sb.append("toggleAccountCall('");
										sb.append(accountCall.getAccountCallId());
										sb.append("');");

										String rowHREF = sb.toString();
									%>

										<tr class="collapsed" id="<portlet:namespace />accountCall_<%= accountCall.getAccountCallId() %>">
											<td class="col">
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
											<td class="col">
												<a href="<%= rowHREF %>"><%= accountCallFormat.format(accountCall.getCallDate()) %></a>
											</td>
											<td class="col">
												<a href="<%= rowHREF %>"><%= HtmlUtil.escape(StringUtil.shorten(accountCall.getSummary(), 115)) %></a>
											</td>
										</tr>
										<tr class="account-calls collapsed" id="<portlet:namespace />accountCallDetails_<%= accountCall.getAccountCallId() %>">
											<td colspan="3">
												<div class="aui-helper-clearfix tab-section">
													<div class="aui-helper-clearfix customer-info">
														<div class="aui-w33 fl">
															<span class="customer-info-label"><liferay-ui:message key="call-type" /></span>:

															<span class="txt-sb"><%= accountCall.getTypeLabel() %></span>
														</div>

														<div class="aui-w33 fl">
															<span class="customer-info-label"><liferay-ui:message key="call-date" /></span>:

															<span class="txt-sb"><%= accountCallFormat.format(accountCall.getCallDate()) %></span>
														</div>

														<div class="aui-w33 fl">
															<span class="customer-info-label"><liferay-ui:message key="call-length" /></span>:

															<span class="txt-sb"><%= accountCall.getCallLengthLabel() %></span>
														</div>
													</div>

													<br />

													<div class="aui-helper-clearfix customer-info">
														<div class="aui-w15 fl">
															<span class="customer-info-label"><liferay-ui:message key="summary" />:</span>
														</div>

														<div class="aui-w85 fr">
															<div class="customer-info-display">
																<pre><%= HtmlUtil.escape(accountCall.getSummary()) %></pre>
															</div>
														</div>
													</div>

													<div class="aui-helper-clearfix customer-info">
														<div class="aui-w15 fl">
															<span class="customer-info-label"><liferay-ui:message key="clients-present" />:</span>
														</div>

														<div class="aui-w85 fr">
															<div class="customer-info-display">
																<pre><%= HtmlUtil.escape(accountCall.getClientsPresent()) %></pre>
															</div>
														</div>
													</div>

													<div class="aui-helper-clearfix customer-info">
														<div class="aui-w15 fl">
															<span class="customer-info-label"><liferay-ui:message key="notes" />:</span>
														</div>

														<div class="aui-w85 fr">
															<div class="customer-info-display">
																<pre><%= HtmlUtil.escape(accountCall.getNotes()) %></pre>
															</div>
														</div>
													</div>

													<div class="aui-helper-clearfix customer-info">
														<div class="aui-w15 fl">
															<span class="customer-info-label"><liferay-ui:message key="action-items" />:</span>
														</div>

														<div class="aui-w85 fr">
															<div class="customer-info-display">
																<pre><%= HtmlUtil.escape(accountCall.getActionItems()) %></pre>
															</div>
														</div>
													</div>

													<c:if test="<%= hasUpdateAccountInfoPermission %>">
														<div class="aui-helper-clearfix fr">

															<%
															PortletURL editAccountCallURL = renderResponse.createRenderURL();

															editAccountCallURL.setParameter("mvcPath", "/support/edit_account_call.jsp");
															editAccountCallURL.setParameter("accountCallId", String.valueOf(accountCall.getAccountCallId()));
															editAccountCallURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
															editAccountCallURL.setWindowState(LiferayWindowState.POP_UP);
															%>

															<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-project-call" />', '<%= editAccountCallURL.toString() %>', '<portlet:namespace />updateAccountCall');" type="button" value="<liferay-ui:message key="edit" />" />

															<portlet:actionURL name="deleteAccountCall" var="deleteAccountCallURL">
																<portlet:param name="redirect" value="<%= currentURL %>" />
																<portlet:param name="accountCallId" value="<%= String.valueOf(accountCall.getAccountCallId()) %>" />
															</portlet:actionURL>

															<input class="aui-button-input" onClick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this-call-log") %>')) { location.href='<%= deleteAccountCallURL %>'; } else { self.focus(); }" type="button" value="<liferay-ui:message key="delete" />" />
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
						</c:when>
						<c:otherwise>
							<div class="tab-section">
								<liferay-ui:message key="there-are-no-call-logs" />
							</div>
						</c:otherwise>
					</c:choose>

					<c:if test="<%= hasUpdateAccountInfoPermission %>">
						<div class="aui-helper-clearfix foot-details">
							<div class="fr">

								<%
								PortletURL addAccountCallURL = renderResponse.createRenderURL();

								addAccountCallURL.setParameter("mvcPath", "/support/2/edit_account_call.jsp");
								addAccountCallURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
								addAccountCallURL.setWindowState(LiferayWindowState.POP_UP);
								%>

								<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="add-call-log" />', '<%= addAccountCallURL.toString() %>', '<portlet:namespace />updateAccountCall')" type="button" value="<liferay-ui:message key="add-call-log" />" />
							</div>
						</div>
					</c:if>
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />contentCasInformation">

					<%
					String casSectionLabel = "sectionDisplay_" + AccountInformationConstants.SECTION_ADVOCACY_SPECIALIST;
					String casSectionEditLabel = "sectionEditDisplay_" + AccountInformationConstants.SECTION_ADVOCACY_SPECIALIST;
					%>

					<div id="<portlet:namespace /><%= casSectionLabel %>">

						<%
						for (int fieldId : AccountInformationConstants.SECTION_ADVOCACY_SPECIALIST_FIELD_IDS) {
						%>

							<div class="aui-helper-clearfix customer-info">
								<div class="aui-w20 customer-info-label fl">
									<span class="customer-info-label"><liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />:</span>
								</div>

								<div class="aui-w80 fl">
									<div class="customer-info-display">
										<pre><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></pre>
									</div>
								</div>
							</div>

						<%
						}
						%>

						<div class="aui-helper-clearfix">
							<div class="fr">
								<c:if test="<%= accountInformationDisplay.getModifiedDate(AccountInformationConstants.FIELD_REASONS_FOR_STRATEGIC) != null %>">
									<liferay-ui:message arguments="<%= new Object[] {accountInformationDisplay.getModifiedUserName(AccountInformationConstants.FIELD_REASONS_FOR_STRATEGIC), fullDateFormatDateTime.format(accountInformationDisplay.getModifiedDate(AccountInformationConstants.FIELD_REASONS_FOR_STRATEGIC))} %>" key="x-on-x" />
								</c:if>

								<c:if test="<%= hasUpdateAccountInfoPermission %>">
									<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= casSectionLabel %>', '<%= casSectionEditLabel %>');" type="button" value="<liferay-ui:message key="edit" />" />
								</c:if>
							</div>
						</div>
					</div>

					<c:if test="<%= hasUpdateAccountInfoPermission %>">
						<div class="aui-helper-hidden" id="<portlet:namespace /><%= casSectionEditLabel %>">

							<%
							for (int fieldId : AccountInformationConstants.SECTION_ADVOCACY_SPECIALIST_FIELD_IDS) {
							%>

								<div class="aui-helper-clearfix">
									<div class="aui-w20 customer-info-label fl">
										<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
									</div>

									<div class="aui-w80 customer-info fl">
										<textarea maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />field--<%= fieldId %>" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></textarea>
									</div>
								</div>

							<%
							}
							%>

							<div class="aui-helper-clearfix">
								<div class="fr">
									<input class="aui-button-input" onClick="<portlet:namespace />updateAccountInformation('<%= AccountInformationConstants.SECTION_ADVOCACY_SPECIALIST %>', 'casInformation');" type="button" value="<liferay-ui:message key="save" />" />

									<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= casSectionEditLabel %>', '<%= casSectionLabel %>');" type="button" value="<liferay-ui:message key="cancel" />" />
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />contentSalesInformation">

					<%
					String salesSectionLabel = "sectionDisplay_" + AccountInformationConstants.SECTION_SALES;
					String salesSectionEditLabel = "sectionEditDisplay_" + AccountInformationConstants.SECTION_SALES;
					%>

					<div id="<portlet:namespace /><%= salesSectionLabel %>">

						<%
						for (int fieldId : AccountInformationConstants.SECTION_SALES_FIELD_IDS) {
						%>

							<div class="aui-helper-clearfix customer-info">
								<div class="aui-w20 fl">
									<span class="customer-info-label"><liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />:</span>
								</div>

								<div class="aui-w80 fl">
									<div class="customer-info-display">
										<pre><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></pre>
									</div>
								</div>
							</div>

						<%
						}
						%>

						<div class="aui-helper-clearfix">
							<div class="fr">
								<c:if test="<%= accountInformationDisplay.getModifiedDate(AccountInformationConstants.FIELD_LICENSE_INFO) != null %>">
									<liferay-ui:message arguments="<%= new Object[] {accountInformationDisplay.getModifiedUserName(AccountInformationConstants.FIELD_LICENSE_INFO), fullDateFormatDateTime.format(accountInformationDisplay.getModifiedDate(AccountInformationConstants.FIELD_LICENSE_INFO))} %>" key="x-on-x" />
								</c:if>

								<c:if test="<%= hasUpdateAccountInfoPermission %>">
									<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= salesSectionLabel %>', '<%= salesSectionEditLabel %>');" type="button" value="<liferay-ui:message key="edit" />" />
								</c:if>
							</div>
						</div>
					</div>

					<c:if test="<%= hasUpdateAccountInfoPermission %>">
						<div class="aui-helper-hidden" id="<portlet:namespace /><%= salesSectionEditLabel %>">

							<%
							for (int fieldId : AccountInformationConstants.SECTION_SALES_FIELD_IDS) {
							%>

								<div class="aui-helper-clearfix">
									<div class="aui-w20 customer-info-label fl">
										<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
									</div>

									<div class="aui-w80 customer-info fl">
										<textarea maxlength="<%= OSBConstants.TEXTAREA_MAX_LENGTH %>" name="<portlet:namespace />field--<%= fieldId %>" onKeyDown="Liferay.Util.checkTab(this); Liferay.Util.disableEsc();"><%= HtmlUtil.escape(accountInformationDisplay.getData(fieldId)) %></textarea>
									</div>
								</div>

							<%
							}
							%>

							<div class="aui-helper-clearfix">
								<div class="fr">
									<input class="aui-button-input" onClick="<portlet:namespace />updateAccountInformation('<%= AccountInformationConstants.SECTION_SALES %>', 'salesInformation');" type="button" value="<liferay-ui:message key="save" />" />

									<input class="aui-button-input" onClick="<portlet:namespace />toggleSection('<%= salesSectionEditLabel %>', '<%= salesSectionLabel %>');" type="button" value="<liferay-ui:message key="cancel" />" />
								</div>
							</div>
						</div>
					</c:if>
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />contentSupportInstructions">
					<div class="tab-section">
						<c:choose>
							<c:when test="<%= Validator.isNotNull(accountEntry.getInstructions()) %>">
								<pre><%= SupportUtil.getHTML(accountEntry.getInstructions()) %></pre>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="no-messages-were-found" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="aui-helper-hidden tab-content-tab" id="<portlet:namespace />contentProjectNotes">
					<div class="tab-section">
						<c:choose>
							<c:when test="<%= Validator.isNotNull(accountEntry.getNotes()) %>">
								<pre><%= SupportUtil.getHTML(accountEntry.getNotes()) %></pre>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="no-messages-were-found" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>

	<aui:script use="aui-base">
		<portlet:namespace />revealAdditionalInfo('<%= HtmlUtil.escape(additionalInfoTab) %>');
	</aui:script>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />revealAdditionalInfo',
			function(id) {
				var tab = AUI().one('.account-additional-info .tabs #<portlet:namespace />' + id);

				if (tab) {
					<portlet:namespace />reveal('.account-additional-info', id);
				}
				else {
					<portlet:namespace />reveal('.account-additional-info', 'callLog');
				}
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

			A.one('#<portlet:namespace />' + hideId).hide();
			A.one('#<portlet:namespace />' + showId).show();
		}

		function <portlet:namespace />updateAccountInformation(section, additionalInfoTab) {
			document.<portlet:namespace />fm1.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
			document.<portlet:namespace />fm1.<portlet:namespace />additionalInfoTab.value = additionalInfoTab;
			document.<portlet:namespace />fm1.<portlet:namespace />section.value = section;

			submitForm(document.<portlet:namespace />fm1);
		}
	</aui:script>
</c:if>