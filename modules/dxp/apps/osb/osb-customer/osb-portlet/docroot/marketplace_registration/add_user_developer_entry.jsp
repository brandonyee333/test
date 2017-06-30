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

<%@ include file="/init.jsp" %>

<%
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

DeveloperEntry developerEntry = null;

if (themeDisplay.isSignedIn()) {
	developerEntry = DeveloperEntryLocalServiceUtil.fetchUserDeveloperEntry(themeDisplay.getUserId());
}
%>

<div class="marketplace-registration-wrapper">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="individual-account-details"
	/>

	<c:choose>
		<c:when test="<%= !themeDisplay.isSignedIn() %>">
			<p>
				<liferay-ui:message key="please-sign-in-before-registering-to-become-a-developer" />
			</p>
		</c:when>
		<c:when test="<%= (developerEntry != null) && developerEntry.isApproved() %>">
			<p>
				<liferay-ui:message key="this-account-is-already-registered-to-be-a-developer" />
			</p>
		</c:when>
		<c:when test="<%= developerEntry != null %>">
			<p>

				<%
				String argument = "<a href=\"mailto:marketplace-admin@liferay.com\">marketplace-admin@liferay.com</a>";
				%>

				<liferay-ui:message arguments="<%= argument %>" key="your-developer-account-was-deactivated" />
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<liferay-ui:message key="to-publish-apps-on-the-liferay-marketplace-you-must-first-create-a-developer-profile-by-filling-in-the-fields-below-and-agree-to-the-developer-agreement" />
			</p>

			<portlet:actionURL name="addUserDeveloperEntry" var="addUserDeveloperEntryURL">
				<portlet:param name="mvcPath" value="/marketplace_registration/add_user_developer_entry.jsp" />
			</portlet:actionURL>

			<aui:form action="<%= addUserDeveloperEntryURL %>" method="post" name="fm">
				<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" />
				<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" />
				<liferay-ui:error exception="<%= DuplicateUserEmailAddressException.class %>" message="the-email-address-you-requested-is-already-taken" />
				<liferay-ui:error exception="<%= DuplicateUserScreenNameException.class %>" message="the-screen-name-you-requested-is-already-taken" />
				<liferay-ui:error exception="<%= NoSuchContractEntryException.class %>" message="please-accept-the-liferay-marketplace-developer-agreement" />
				<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
				<liferay-ui:error exception="<%= PhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />
				<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
				<liferay-ui:error exception="<%= ReservedUserScreenNameException.class %>" message="the-screen-name-you-requested-is-reserved" />
				<liferay-ui:error exception="<%= RestrictedCountryException.class %>" message="under-us-export-laws-the-exportation-sale-or-supply-directly-or-indirectly-of-app-or-services-to-the-selected-country-is-strictly-prohibited-without-prior-authorization-by-the-us-government" />
				<liferay-ui:error exception="<%= RestrictedLiferayEmailAddressException.class %>" message="liferay-employees-are-not-allowed-to-change-their-email-address" />
				<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
				<liferay-ui:error exception="<%= UserScreenNameException.class %>" message="please-enter-a-valid-screen-name" />

				<aui:fieldset>
					<aui:layout cssClass="marketplace-registration-developer-information">
						<aui:column columnWidth="<%= 45 %>">
							<h3>
								<liferay-ui:message key="step-1" />
							</h3>

							<p>
								<liferay-ui:message key="fill-out-your-developer-information" />
							</p>

							<div>
								<aui:input cssClass="required" label="screen-name" name="screenName" value="<%= user.getScreenName() %>" />

								<liferay-ui:icon-help message="this-will-be-your-unique-screen-name" />
							</div>

							<div>
								<aui:input cssClass="required" label="email-address" name="emailAddress" value="<%= user.getEmailAddress() %>" />

								<liferay-ui:icon-help message="you-will-receive-a-confirmation-email-indicating-the-registration-process-is-complete" />
							</div>

							<aui:input cssClass="required" label="first-name" name="firstName" value="<%= user.getFirstName() %>" />

							<aui:input label="middle-name" name="middleName" value="<%= user.getMiddleName() %>" />

							<aui:input cssClass="required" label="last-name" name="lastName" value="<%= user.getLastName() %>" />

							<aui:select cssClass="required" label="country" name="countryId" showEmptyOption="true">

								<%
								for (Country country : CountryServiceUtil.getCountries()) {
								%>

									<aui:option label="<%= country.getName() %>" value="<%= country.getCountryId() %>" />

								<%
								}
								%>

							</aui:select>

							<div class="phone-number required">
								<liferay-ui:custom-attribute
									className="com.liferay.portal.model.User"
									classPK="<%= user.getUserId() %>"
									editable="<%= true %>"
									label="<%= true %>"
									name="osbPhoneNumber"
								/>

								<liferay-ui:icon-help message="please-include-the-plus-sign-your-country-code-and-the-area-code" />
							</div>
						</aui:column>

						<aui:column columnWidth="<%= 45 %>">
							<h3>
								<liferay-ui:message key="step-2" />
							</h3>

							<p>
								<liferay-ui:message key="developer-agreement" />
							</p>

							<div class="terms" id="<portlet:namespace />terms">

								<%
								ContractEntry contractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_DEVELOPER_AGREEMENT);

								String content = HtmlUtil.escape(contractEntry.getContent(themeDisplay.getLanguageId()));

								content = content.replace(StringPool.NEW_LINE, "<br />");
								%>

								<%= content %>
							</div>

							<p>
								<liferay-ui:message key="you-must-scroll-through-the-entire-agreement-below-in-order-to-accept-the-developer-terms-of-agreement" />
							</p>

							<p>
								<aui:input cssClass="fs-11 required" disabled="true" label="i-have-read-and-agree-to-the-liferay-marketplace-developer-terms-of-agreement" name="acceptContractEntry" type="checkbox" />
							</p>
						</aui:column>
					</aui:layout>

					<aui:layout cssClass="rt-align">
						<aui:button-row>

							<%
							long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
							%>

							<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
								<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
							</liferay-portlet:renderURL>

							<aui:button onClick="<%= marketplaceURL %>" value="cancel" />

							<aui:button type="submit" value="submit-request" />
						</aui:button-row>
					</aui:layout>
				</aui:fieldset>
			</aui:form>

			<aui:script use="aui-base,aui-debounce">
				var terms = A.one('#<portlet:namespace />terms');
				var accept = A.one('#<portlet:namespace />acceptContractEntryCheckbox');

				var acceptScrollHeight = terms.attr('scrollHeight') - (terms.height() + 100);

				if (acceptScrollHeight > 0) {
					var handle = terms.on(
						'scroll',
						A.debounce(
							function() {
								if (terms.attr('scrollTop') > acceptScrollHeight) {
									accept.attr('disabled', false);

									if (handle) {
										handle.detach();
									}
								}
							},
							200
						)
					);
				}
				else {
					accept.removeClass('disabled');
				}
			</aui:script>
		</c:otherwise>
	</c:choose>
</div>