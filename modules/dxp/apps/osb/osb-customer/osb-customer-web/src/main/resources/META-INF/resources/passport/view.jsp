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

	<liferay-ui:error exception="<%= EmailAddressDomainException.class %>" message="end-user-email-address-domain-must-match-your-own-email-address-domain" />
	<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="please-fill-out-all-required-fields" />

	<br />

	<h1><liferay-ui:message key="liferay-university-passport-access" /></h1>

	<br />

	<h3><liferay-ui:message key="all-access-pass-to-liferay-university" /></h3>

	<liferay-ui:message key="get-access-to-all-courses-updates-and-new-offerings-for-one-year" />

	<aui:fieldset-group>
		<aui:fieldset>
			<aui:input name="endUserFirstName">
				<aui:validator name="required" />
			</aui:input>

			<aui:input name="endUserLastName">
				<aui:validator name="required" />
			</aui:input>

			<aui:input helpMessage="you-may-request-liferay-university-passport-access-for-yourself-or-a-member-of-your-liferay-project-team" name="endUserEmailAddress">
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
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>