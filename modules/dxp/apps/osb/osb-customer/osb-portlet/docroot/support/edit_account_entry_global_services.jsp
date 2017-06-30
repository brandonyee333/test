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

boolean hasUpdateAccountInfoPermission = OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO);

long accountProjectId = ParamUtil.getLong(request, "accountProjectId");
%>

<c:if test="<%= liferayIncOrg %>">
	<h1 class="callout-content section-heading">
		<liferay-ui:message key="consulting-projects" />
	</h1>

	<div class="callout-a">

		<%
		List<AccountProject> accountProjects = AccountProjectLocalServiceUtil.getAccountProjects(accountEntryId);
		%>

		<c:choose>
			<c:when test="<%= !accountProjects.isEmpty() %>">
				<div class="account-projects" id="<portlet:namespace />accountProjectsContainer">
					<div class="callout-content">
						<table class="taglib-search-iterator">
						<tr class="portlet-section-header results-header">
							<th class="col-1 first" />
							<th class="col-2">
								<liferay-ui:message key="project-name" />
							</th>
							<th class="col-3">
								<liferay-ui:message key="last-modified" />
							</th>
						</tr>

						<%
						for (AccountProject accountProject : accountProjects) {
							StringBuilder sb = new StringBuilder();

							sb.append("javascript:");
							sb.append(renderResponse.getNamespace());
							sb.append("toggleAccountProject('");
							sb.append(accountProject.getAccountProjectId());
							sb.append("');");

							String rowHREF = sb.toString();
						%>

							<tr class="collapsed" id="<portlet:namespace />accountProject_<%= accountProject.getAccountProjectId() %>">
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
									<a href="<%= rowHREF %>"><%= HtmlUtil.escape(accountProject.getName()) %></a>
								</td>
								<td class="col col-3">
									<a href="<%= rowHREF %>"><liferay-ui:message arguments="<%= new Object[] {accountProject.getModifiedUserName(), fullDateFormatDateTime.format(accountProject.getModifiedDate())} %>" key="x-on-x" /></a>
								</td>
							</tr>
							<tr class="account-projects collapsed" id="<portlet:namespace />accountProjectDetail_<%= accountProject.getAccountProjectId() %>">
								<td class="panel" colspan="3">
									<div class="callout-a">
										<div class="aui-helper-clearfix callout-content">
											<div class="aui-w20 content-column customer-info-label">
												<liferay-ui:message key="project-name" />
											</div>

											<div class="aui-w80 content-column customer-info">
												<div class="customer-info-display">
													<%= HtmlUtil.escape(accountProject.getName()) %>
												</div>
											</div>
										</div>

										<%
										for (int fieldId : AccountInformationConstants.ACCOUNT_PROJECT_FIELD_IDS) {
										%>

											<div class="aui-helper-clearfix callout-content">
												<div class="aui-w20 content-column customer-info-label">
													<liferay-ui:message key="<%= AccountInformationConstants.getFieldLabel(fieldId) %>" />
												</div>

												<div class="aui-w80 content-column customer-info">
													<div class="customer-info-display">
														<pre><%= HtmlUtil.escape(accountProject.getData(fieldId)) %></pre>
													</div>
												</div>
											</div>

										<%
										}

										List<AccountAttachment> accountAttachments = AccountAttachmentLocalServiceUtil.getAccountAttachments(accountEntryId, accountProject.getAccountProjectId());
										%>

										<c:if test="<%= !accountAttachments.isEmpty() %>">
											<div class="aui-helper-clearfix callout-content">
												<table class="lfr-table">
												<tr>
													<td>
														<strong><liferay-ui:message key="attachments" />:</strong>
													</td>
													<td class="stretch">

														<%
														for (int i = 0; i < accountAttachments.size(); i++) {
															AccountAttachment accountAttachment = accountAttachments.get(i);

															String fileName = accountAttachment.getFileName();
														%>

															<div class="attachment cleared">
																<div class="fl">

																	<%
																	LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

																	accountAttachmentURL.setCopyCurrentRenderParameters(false);
																	accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
																	accountAttachmentURL.setResourceID("accountAttachment");
																	%>

																	<a href="<%= accountAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(fileName) %></a> (<%= TextFormatter.formatKB((double)accountAttachment.getFileSize(), locale) %>k)

																	<c:if test="<%= SupportUtil.hasAttachmentPreview(fileName) %>">

																		<%
																		accountAttachmentURL.setParameter("preview", String.valueOf(Boolean.TRUE));
																		%>

																		<span>
																			<liferay-ui:icon
																				image="preview"
																				target="_blank"
																				url="<%= accountAttachmentURL.toString() %>"
																			/>
																		</span>
																	</c:if>
																</div>

																<div class="fr">
																	<%= HtmlUtil.escape(accountAttachment.getUserName()) %> on <%= fullDateFormatDateTime.format(accountAttachment.getCreateDate()) %>
																</div>

																<%= (i < (accountAttachments.size() - 1)) ? "<br />" : "" %>
															</div>

														<%
														}
														%>

													</td>
												</tr>
												</table>
											</div>
										</c:if>

										<c:if test="<%= hasUpdateAccountInfoPermission %>">
											<div class="aui-helper-clearfix">
												<div class="callout-content fr">
													<input class="aui-button-input" onClick="var manageAttachmentsWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_account_attachments.jsp" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" /><portlet:param name="accountProjectId" value="<%= String.valueOf(accountProject.getAccountProjectId()) %>" /></portlet:renderURL>', 'manage-attachments', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); manageAttachmentsWindow.focus();" type="button" value="<liferay-ui:message key="manage-attachments" />" />

													<%
													PortletURL editAccountProjectURL = renderResponse.createRenderURL();

													editAccountProjectURL.setParameter("mvcPath", "/support/edit_account_project.jsp");
													editAccountProjectURL.setParameter("accountProjectId", String.valueOf(accountProject.getAccountProjectId()));
													editAccountProjectURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
													editAccountProjectURL.setWindowState(LiferayWindowState.POP_UP);
													%>

													<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-project" />', '<%= editAccountProjectURL.toString() %>', '<portlet:namespace />updateAccountProject')" type="button" value="<liferay-ui:message key="edit" />" />

													<portlet:actionURL name="deleteAccountProject" var="deleteAccountProjectURL">
														<portlet:param name="redirect" value="<%= currentURL %>" />
														<portlet:param name="accountProjectId" value="<%= String.valueOf(accountProject.getAccountProjectId()) %>" />
													</portlet:actionURL>

													<input class="aui-button-input" onClick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this-project") %>')) { location.href='<%= deleteAccountProjectURL %>'; } else { self.focus(); }" type="button" value="<liferay-ui:message key="delete" />" />
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
					<liferay-ui:message key="there-are-no-projects" />
				</div>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= hasUpdateAccountInfoPermission %>">
			<div class="aui-helper-clearfix">
				<div class="callout-content fr">

					<%
					PortletURL addAccountProjectURL = renderResponse.createRenderURL();

					addAccountProjectURL.setParameter("mvcPath", "/support/edit_account_project.jsp");
					addAccountProjectURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
					addAccountProjectURL.setWindowState(LiferayWindowState.POP_UP);
					%>

					<input class="aui-button-input" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="add-project" />', '<%= addAccountProjectURL.toString() %>', '<portlet:namespace />updateAccountProject')" type="button" value="<liferay-ui:message key="add-project" />" />
				</div>
			</div>
		</c:if>
	</div>

	<h1 class="callout-content section-heading">
		<liferay-ui:message key="attachments-links" />
	</h1>

	<%
	List<AccountAttachment> accountAttachments = AccountAttachmentLocalServiceUtil.getAccountAttachments(accountEntryId, AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID);
	%>

	<c:if test="<%= !accountAttachments.isEmpty() %>">
		<table class="lfr-table">
		<tr>
			<td>
				<strong><liferay-ui:message key="attachments" />:</strong>
			</td>
			<td class="stretch">

				<%
				for (int i = 0; i < accountAttachments.size(); i++) {
					AccountAttachment accountAttachment = accountAttachments.get(i);

					String fileName = accountAttachment.getFileName();
				%>

					<div class="attachment cleared">
						<div class="fl">

							<%
							LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountAttachmentURL.setCopyCurrentRenderParameters(false);
							accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
							accountAttachmentURL.setResourceID("accountAttachment");
							%>

							<a href="<%= accountAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(fileName) %></a> (<%= TextFormatter.formatKB((double)accountAttachment.getFileSize(), locale) %>k)

							<c:if test="<%= SupportUtil.hasAttachmentPreview(fileName) %>">

								<%
								accountAttachmentURL.setParameter("preview", String.valueOf(Boolean.TRUE));
								%>

								<span>
									<liferay-ui:icon
										image="preview"
										target="_blank"
										url="<%= accountAttachmentURL.toString() %>"
									/>
								</span>
							</c:if>
						</div>

						<div class="fr">
							<%= HtmlUtil.escape(accountAttachment.getUserName()) %> on <%= fullDateFormatDateTime.format(accountAttachment.getCreateDate()) %>
						</div>

						<%= (i < (accountAttachments.size() - 1)) ? "<br />" : "" %>
					</div>

				<%
				}
				%>

			</td>
		</tr>
		</table>
	</c:if>

	<%
	List<AccountLink> accountLinks = AccountLinkLocalServiceUtil.getAccountLinks(accountEntryId);
	%>

	<c:if test="<%= !accountLinks.isEmpty() %>">
		<table class="lfr-table">
		<tr>
			<td>
				<strong><liferay-ui:message key="links" />:</strong>
			</td>
			<td class="stretch">

				<%
				for (AccountLink accountLink : accountLinks) {
				%>

					<div>
						<a href="<%= accountLink.getUrl() %>"><%= HtmlUtil.escape(StringUtil.shorten(accountLink.getUrl(), 115)) %></a>
					</div>

				<%
				}
				%>

			</td>
		</tr>
		</table>

		<br />
	</c:if>

	<c:if test="<%= hasUpdateAccountInfoPermission %>">
		<input class="aui-button-input" onClick="var manageAttachmentsWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_account_attachments.jsp" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" /><portlet:param name="accountProjectId" value="<%= String.valueOf(AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID) %>" /></portlet:renderURL>', 'manage-attachments', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); manageAttachmentsWindow.focus();" type="button" value="<liferay-ui:message key="manage-attachments" />" />

		<input class="aui-button-input" onClick="var manageLinksWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/support/edit_account_links.jsp" /><portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" /></portlet:renderURL>', 'manage-links', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); manageLinksWindow.focus();" type="button" value="<liferay-ui:message key="manage-links" />" />
	</c:if>

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

		function <portlet:namespace />toggleAccountProject(accountProjectId) {
			var A = AUI();

			var accountProject = A.one('#<portlet:namespace />accountProject_' + accountProjectId);
			var accountProjectDetail = A.one('#<portlet:namespace />accountProjectDetail_' + accountProjectId);

			if (accountProject.hasClass('collapsed')) {
				A.all("#<portlet:namespace />accountProjectsContainer tr").each(
					function(item, index, collection) {
						item.addClass('collapsed');
					}
				);

				accountProject.removeClass('collapsed');
				accountProjectDetail.removeClass('collapsed');
			}
			else {
				accountProject.addClass('collapsed');
				accountProjectDetail.addClass('collapsed');
			}
		}
	</aui:script>
</c:if>