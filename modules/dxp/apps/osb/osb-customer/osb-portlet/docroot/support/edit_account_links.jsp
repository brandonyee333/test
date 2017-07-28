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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			window.close();
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

		AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		String url1 = ParamUtil.getString(request, "url1");
		String url2 = ParamUtil.getString(request, "url2");
		String url3 = ParamUtil.getString(request, "url3");

		PortletURL portletURL = renderResponse.createRenderURL();

		List<AccountLink> accountLinks = AccountLinkLocalServiceUtil.getAccountLinks(accountEntryId);
		%>

		<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">
			<portlet:actionURL name="addAccountLink" var="addAccountLinkURL">
				<portlet:param name="mvcPath" value="/support/edit_account_links.jsp" />
			</portlet:actionURL>

			<aui:form action="<%= addAccountLinkURL %>" method="post" name="fm">
				<input name="<portlet:namespace />accountEntryId" type="hidden" value="<%= accountEntryId %>" />

				<div class="cleared section">
					<div class="fl">
						Edit Links for Account: <%= HtmlUtil.escape(accountEntry.getName()) %>
					</div>
				</div>

				<liferay-ui:error exception="<%= AccountLinkURLException.class %>" message="please-enter-a-valid-url" />

				<h1 class="section-heading">
					<liferay-ui:message key="links" />
				</h1>

				<div class="callout-a">
					<div class="callout-content">
						<liferay-ui:search-container
							emptyResultsMessage="there-are-no-links"
							headerNames="url"
						>
							<liferay-ui:search-container-results
								results="<%= accountLinks %>"
								total="<%= accountLinks.size() %>"
							/>

							<liferay-ui:search-container-row
								className="com.liferay.osb.model.AccountLink"
								escapedModel="<%= true %>"
								keyProperty="accountLinkId"
								modelVar="accountLink"
							>
								<liferay-ui:search-container-column-text
									href="<%= accountLink.getUrl() %>"
									name="url"
									value="<%= StringUtil.shorten(accountLink.getUrl(), 115) %>"
								/>

								<liferay-ui:search-container-column-text align="right">
									<portlet:actionURL name="deleteAccountLink" var="deleteURL">
										<portlet:param name="mvcPath" value="/support/edit_account_links.jsp" />
										<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntryId) %>" />
										<portlet:param name="accountLinkId" value="<%= String.valueOf(accountLink.getAccountLinkId()) %>" />
									</portlet:actionURL>

									<liferay-ui:icon-delete label="<%= true %>" url="<%= deleteURL %>" />
								</liferay-ui:search-container-column-text>
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator paginate="<%= false %>" />
						</liferay-ui:search-container>

						<legend><liferay-ui:message key="add-link" /></legend>

						<table class="lfr-table">
							<tr>
								<td>
									<liferay-ui:message key="url" /> 1
								</td>
								<td>
									<input class="lfr-input-text" name="<portlet:namespace />url1" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url1) %>" />
								</td>
							</tr>
							<tr>
								<td>
									<liferay-ui:message key="url" /> 2
								</td>
								<td>
									<input class="lfr-input-text" name="<portlet:namespace />url2" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url2) %>" />
								</td>
							</tr>
							<tr>
								<td>
									<liferay-ui:message key="url" /> 3
								</td>
								<td>
									<input class="lfr-input-text" name="<portlet:namespace />url3" style="width: 350px;" type="text" value="<%= HtmlUtil.escapeAttribute(url3) %>" />
								</td>
							</tr>
						</table>

						<div class="button-holder">
							<input class="aui-button-input" type="submit" value="<liferay-ui:message key="save" />" />

							<input class="aui-button-input" onClick="javascript:window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
						</div>
					</div>
				</div>
			</aui:form>
		</c:if>
	</c:otherwise>
</c:choose>