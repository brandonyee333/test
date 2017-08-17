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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("preferences.jsp-accountEntry");

String redirect = ParamUtil.getString(request, "redirect");

String tabs1 = ParamUtil.getString(request, "tabs1");

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

if (accountEntryId > 0) {
	accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

	request.setAttribute("preferences.jsp-accountEntry", accountEntry);
}

List<AccountEntry> accountEntries = null;

if (liferayIncOrg) {
	accountEntries = AccountEntryLocalServiceUtil.getActiveAccountEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}
else {
	accountEntries = AccountEntryLocalServiceUtil.getUserActiveAccountEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}
%>

<portlet:actionURL name="updateAccountEnvironment" var="updateAccountEnvironmentURL">
	<portlet:param name="mvcPath" value="/support/2/preferences.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAccountEnvironmentURL %>" method="post" name="fm">
	<aui:input name="accountEnvironmentId" type="hidden" value="" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />

	<div class="aui-w50 environment-configurations">
		<h2 class="project-heading">
			<liferay-ui:message key="project" />:
		</h2>

		<c:choose>
			<c:when test="<%= accountEntries.size() == 1 %>">

				<%
				accountEntry = accountEntries.get(0);

				request.setAttribute("preferences.jsp-accountEntry", accountEntry);
				%>

				<strong><%= HtmlUtil.escape(accountEntry.getName()) %></strong>

				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntry.getAccountEntryId() %>" />
			</c:when>
			<c:otherwise>
				<aui:select name="accountEntryId" onChange="submitForm(document.<portlet:namespace />fm, '<portlet:renderURL><portlet:param name="mvcPath" value="/support/2/preferences.jsp" /></portlet:renderURL>');">
					<aui:option value="" />

					<%
					for (AccountEntry curAccountEntry : accountEntries) {
					%>

						<aui:option label="<%= HtmlUtil.escape(curAccountEntry.getName()) %>" selected="<%= (accountEntry != null) && (curAccountEntry.getAccountEntryId() == accountEntry.getAccountEntryId()) %>" value="<%= curAccountEntry.getAccountEntryId() %>" />

					<%
					}
					%>

				</aui:select>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="<%= (accountEntry != null) && SupportUtil.isWatcher(accountEntry.getAccountEntryId(), user.getUserId()) %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="you-are-a-watcher-on-this-project" />
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="<%= accountEntry != null %>">
					<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) %>">

						<%
						PortletURL addAccountEnvironmentURL = renderResponse.createRenderURL();

						addAccountEnvironmentURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
						addAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
						addAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
						%>

						<div class="create-env-button">
							<aui:button cssClass="aui-button-input fr" onClick="<%= renderResponse.getNamespace() %>openDialog('<liferay-ui:message key="create-environment-configuration" />', '<%= addAccountEnvironmentURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" value="create-environment" />
						</div>
					</c:if>

					<%
					List<ProductEntry> productEntries = ProductEntryLocalServiceUtil.getProductEntries(accountEntry.getAccountEntryId());

					String prevDisplayName = StringPool.BLANK;

					boolean environments = false;

					for (ProductEntry productEntry : productEntries) {
						request.setAttribute("preferences.jsp-productEntry", productEntry);
					%>

						<c:if test="<%= !prevDisplayName.equals(productEntry.getLESADisplayName()) && !prevDisplayName.equals(StringPool.BLANK) && !environments %>">
							<div class="portlet-msg-info">
								<liferay-ui:message key="there-are-no-saved-environment-configurations-for-this-product" />
							</div>
						</c:if>

						<%
						if (!prevDisplayName.equals(productEntry.getLESADisplayName())) {
							environments = false;
						}
						%>

						<div>
							<c:if test="<%= !prevDisplayName.equals(productEntry.getLESADisplayName()) %>">
								<div class="product-heading">
									<liferay-ui:message key="<%= productEntry.getLESADisplayName() %>" />
								</div>
							</c:if>

							<%
							List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntry.getAccountEntryId(), productEntry.getProductEntryId());

							if (!accountEnvironments.isEmpty()) {
								environments = true;
							}
							%>

							<c:if test="<%= !accountEnvironments.isEmpty() %>">

								<%
								for (AccountEnvironment accountEnvironment : accountEnvironments) {
								%>

									<div class="environment">
										<liferay-util:include page="/support/2/common/details_environment.jsp" servletContext="<%= application %>">
											<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
											<portlet:param name="validateEnvironment" value="<%= Boolean.FALSE.toString() %>" />
										</liferay-util:include>

										<div class="configuration env-button">
											<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.DELETE) %>">
												<portlet:renderURL var="redirectURL">
													<portlet:param name="mvcPath" value="/support/2/preferences.jsp" />
													<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
													<portlet:param name="tabs1" value="<%= tabs1 %>" />
												</portlet:renderURL>

												<portlet:actionURL name="deleteAccountEnvironment" var="deleteAccountEnvironmentURL">
													<portlet:param name="redirect" value="<%= redirectURL %>" />
													<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
												</portlet:actionURL>

												<aui:button cssClass="aui-button-input fl" onClick="<%= renderResponse.getNamespace() %>deleteEnvironment('<%= deleteAccountEnvironmentURL.toString() %>', '<%= accountEnvironment.getName() %>');" value="delete" />
											</c:if>

											<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE) %>">

												<%
												PortletURL editAccountEnvironmentURL = renderResponse.createRenderURL();

												editAccountEnvironmentURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
												editAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
												editAccountEnvironmentURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));
												editAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
												%>

												<aui:button cssClass="aui-button-input fr" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-environment-configuration" />', '<%= editAccountEnvironmentURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" value="edit" />
											</c:if>
										</div>
									</div>

								<%
								}
								%>

							</c:if>
						</div>

					<%
						prevDisplayName = productEntry.getLESADisplayName();
					}
					%>

					<c:if test="<%= !environments && !productEntries.isEmpty() %>">
						<div class="portlet-msg-info">
							<liferay-ui:message key="there-are-no-saved-environment-configurations-for-this-product" />
						</div>
					</c:if>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />deleteEnvironment(url, name) {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-your-saved-environment-configuration-x-permanently', name))) {
			submitForm(document.<portlet:namespace />fm, url);
		}
	}

	function <portlet:namespace />openDialog(title, url, popupId) {
		Liferay.Util.openWindow(
			{
				cache: false,
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					centered: true,
					close: false,
					draggable: false,
					modal: true,
					resizable: false
				},
				id: popupId,
				title: title,
				uri: url
			}
		);
	}
</aui:script>