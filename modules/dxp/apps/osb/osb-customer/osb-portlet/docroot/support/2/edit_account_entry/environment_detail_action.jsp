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

long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

AccountEnvironment accountEnvironment = AccountEnvironmentLocalServiceUtil.fetchAccountEnvironment(accountEnvironmentId);

Map<String, String> dropDownList = new TreeMap<String, String>();

if (OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.UPDATE)) {
	PortletURL buttonURL = renderResponse.createRenderURL();

	buttonURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
	buttonURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
	buttonURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));
	buttonURL.setParameter("offeringEntryId", String.valueOf(offeringEntryId));
	buttonURL.setWindowState(LiferayWindowState.POP_UP);

	StringBundler sb = new StringBundler(8);

	sb.append(renderResponse.getNamespace());
	sb.append("openDialog('");
	sb.append(LanguageUtil.get(request, "edit-environment-configuration"));
	sb.append("', '");
	sb.append(buttonURL.toString());
	sb.append("', '");
	sb.append(renderResponse.getNamespace());
	sb.append("updateAccountEnvironment');");

	dropDownList.put("edit", sb.toString());
}

if (OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.DELETE)) {
	PortletURL buttonURL = renderResponse.createActionURL();

	buttonURL.setParameter(ActionRequest.ACTION_NAME, "deleteAccountEnvironment");
	buttonURL.setParameter("redirect", portletURL.toString());
	buttonURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));

	StringBundler sb = new StringBundler(7);

	sb.append("javascript:if (confirm('");
	sb.append(UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this"));
	sb.append("')) { submitForm(document.");
	sb.append(renderResponse.getNamespace());
	sb.append("fm, '");
	sb.append(buttonURL.toString());
	sb.append("'); } else { return false; }");

	dropDownList.put("delete", sb.toString());
}
%>

<c:if test="<%= !dropDownList.isEmpty() %>">
	<span class="three-dot">
		<span class="three-dot-icon">
			<span style="top: 6px;"></span>
			<span style="top: 14px;"></span>
			<span style="top: 22px;"></span>
		</span>

		<div class="drop-down-menu">
			<div class="drop-down-arrow">
				<div></div>
			</div>

			<ul>

				<%
				for (Map.Entry<String, String> entry : dropDownList.entrySet()) {
					String curLabel = entry.getKey();
					String curOnClick = entry.getValue();

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"javascript:;\" onClick=\"");
					sb.append(curOnClick);
					sb.append("\">");
					sb.append(LanguageUtil.get(request, curLabel));
					sb.append("</a>");
				%>

					<li>
						<%= sb.toString() %>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</span>
</c:if>