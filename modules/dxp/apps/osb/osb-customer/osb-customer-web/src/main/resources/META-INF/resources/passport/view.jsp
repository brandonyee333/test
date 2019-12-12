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
String redirect = ParamUtil.getString(request, "redirect");
%>

<portlet:actionURL name="/submit_passport_access" var="submitPassportAccessURL" />

<aui:form action="<%= submitPassportAccessURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= EmailAddressDomainException.class %>" message="end-user-work-email-address-domain-must-match-your-own-email-address-domain" />
	<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="please-fill-out-all-required-fields" />
	<liferay-ui:error exception="<%= SubscriptionException.class %>" message="we-could-not-identify-any-active-liferay-subscription-or-active-liferay-partnership-for-the-provided-company" />

	<br />

	<h1><liferay-ui:message key="liferay-university-passport-access" /></h1>

	<br />

	<h3><liferay-ui:message key="all-access-pass-to-liferay-university" /></h3>

	<liferay-ui:message key="receive-1-year-access-to-all-courses-updates-and-new-offerings" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="endUserFirstName" value="<%= user.getFirstName() %>">
				<aui:validator name="required" />
			</aui:input>

			<aui:input name="endUserLastName" value="<%= user.getLastName() %>">
				<aui:validator name="required" />
			</aui:input>

			<aui:input name="companyName">
				<aui:validator name="required" />
			</aui:input>

			<aui:input helpMessage="you-may-request-liferay-university-passport-access-for-yourself-or-for-a-member-of-your-liferay-project-team" name="endUserWorkEmailAddress" value="<%= user.getEmailAddress() %>">
				<aui:validator name="email" />
				<aui:validator name="required" />
			</aui:input>

			<%
			DLFolder folder = DLFolderLocalServiceUtil.getFolder(OSBCustomerConstants.GROUP_CUSTOMER_ID, 0, "Liferay University Passport");

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(OSBCustomerConstants.GROUP_CUSTOMER_ID, folder.getFolderId(), "Liferay University Passport Agreement.pdf");

			String url = DLUtil.getPreviewURL(fileEntry, fileEntry.getFileVersion(), themeDisplay, StringPool.BLANK);
			%>

			<aui:input label='<%= LanguageUtil.format(request, "i-have-agreed-to-the-terms-and-conditions-for-liferay-university-passport-access", url) %>' name="agreement" type="checkbox">
				<aui:validator name="required" />
			</aui:input>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" value="submit" />
	</aui:button-row>
</aui:form>