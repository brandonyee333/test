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
String redirect = ParamUtil.getString(request, "redirect");
%>

<portlet:actionURL name="registerLicenseKey" var="registerLicenseKeyURL">
	<portlet:param name="mvcPath" value="/license_form/view.jsp" />
</portlet:actionURL>

<aui:form action="<%= registerLicenseKeyURL %>">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= CaptchaMaxChallengesException.class %>" message="maximum-number-of-captcha-attempts-exceeded" />
	<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />
	<liferay-ui:error exception="<%= ContactFirstNameException.class %>" message="please-enter-a-valid-first-name" rowBreak='<%= _errorCss(renderResponse, "firstNameLabel") %>' />
	<liferay-ui:error exception="<%= ContactLastNameException.class %>" message="please-enter-a-valid-last-name" rowBreak='<%= _errorCss(renderResponse, "lastNameLabel") %>' />
	<liferay-ui:error exception="<%= EmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= LicenseKeySingleUseException.class %>" message="a-license-key-is-already-registered-to-that-email-address" />

	<liferay-ui:error exception="<%= RequiredFieldException.class %>">

		<%
		RequiredFieldException rfe = (RequiredFieldException)errorException;
		%>

		<%= _errorCss(renderResponse, rfe.getLabelId()) %>

		<liferay-ui:message key="please-fill-out-the-following-fields" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= ReservedUserEmailAddressException.class %>" message="the-email-address-you-requested-is-reserved" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" rowBreak='<%= _errorCss(renderResponse, "emailAddressLabel") %>' />

	<fieldset class="fieldset">
		<p class="field-group-grid">
			<span class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />emailAddress" id="<portlet:namespace />emailAddressLabel">
							<liferay-ui:message key="email-address" /><span class="field-required-label">*</span>

							<liferay-ui:icon-help message="please-enter-a-valid-email-so-we-can-send-you-the-30-day-trial-license-key" />
						</label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<liferay-ui:input-field
							cssClass="field-input field-input-text pull-left"
							field="emailAddress"
							model="<%= User.class %>"
						/>
					</span>
				</span>
			</span>
		</p>

		<p class="field-group-grid">
			<span class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />firstName" id="<portlet:namespace />firstNameLabel"><liferay-ui:message key="first-name" /><span class="field-required-label">*</span></label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<liferay-ui:input-field
							cssClass="field-input field-input-text"
							field="firstName"
							model="<%= Contact.class %>"
						/>
					</span>
				</span>
			</span>
		</p>

		<p class="field-group-grid">
			<span class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />lastName" id="<portlet:namespace />lastNameLabel"><liferay-ui:message key="last-name" /><span class="field-required-label">*</span></label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<liferay-ui:input-field
							cssClass="field-input field-input-text"
							field="lastName"
							model="<%= Contact.class %>"
						/>
					</span>
				</span>
			</span>
		</p>

		<div class="field-group-grid">
			<div class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" id="<portlet:namespace />companyLabel"><liferay-ui:message key="company" /><span class="field-required-label">*</span></label>
					</span>
				</span>

				<div class="unit">
					<div class="unit-content">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							name="osbCompany"
						/>
					</div>
				</div>
			</div>
		</div>

		<div class="field-group-grid">
			<div class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label"><liferay-ui:message key="department" /></label>
					</span>
				</span>

				<div class="unit">
					<div class="unit-content">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							name="osbDepartment"
						/>
					</div>
				</div>
			</div>
		</div>

		<div class="field-group-grid">
			<div class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label"><liferay-ui:message key="role" /></label>
					</span>
				</span>

				<div class="unit">
					<div class="unit-content">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							name="osbCompanyRole"
						/>
					</div>
				</div>
			</div>
		</div>

		<div class="field-group-grid">
			<div class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label"><liferay-ui:message key="industry" /></label>
					</span>
				</span>

				<div class="unit">
					<div class="unit-content">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							name="osbIndustry"
						/>
					</div>
				</div>
			</div>
		</div>

		<div class="field-group-grid">
			<div class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" id="<portlet:namespace />countryLabel"><liferay-ui:message key="country" /><span class="field-required-label">*</span></label>
					</span>
				</span>

				<div class="unit">
					<div class="unit-content">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							name="osbCountry"
						/>
					</div>
				</div>
			</div>
		</div>

		<p class="field-group-grid">
			<span class="field field-text">
				<span class="unit w-110">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />number"><liferay-ui:message key="phone-number" /></label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<liferay-ui:input-field
							cssClass="field-input field-input-text"
							field="number"
							model="<%= Phone.class %>"
						/>
					</span>
				</span>
			</span>
			<span class="field field-text">
				<span class="unit">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />number"><liferay-ui:message key="extension" /></label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<liferay-ui:input-field
							cssClass="field-input field-input-text"
							field="extension"
							model="<%= Phone.class %>"
						/>
					</span>
				</span>
			</span>
			<span class="field field-text">
				<span class="unit">
					<span class="unit-content">
						<label class="field-label" for="<portlet:namespace />number"><liferay-ui:message key="type" /></label>
					</span>
				</span>
				<span class="unit">
					<span class="unit-content">
						<select name="<portlet:namespace />typeId">

							<%
							List<ListType> phoneTypes = ListTypeServiceUtil.getListTypes(ListTypeConstants.CONTACT_PHONE);

							for (ListType suffix : phoneTypes) {
							%>

								<option value="<%= suffix.getListTypeId() %>"><liferay-ui:message key="<%= suffix.getName() %>" /></option>

							<%
							}
							%>

						</select>
					</span>
				</span>
			</span>
		</p>

		<div class="field-group-grid">
			<div class="unit">
				<aui:input label="i-agree-to-allow-liferay-and-its-affiliates-to-contact-me-via-email" name="agreedToContact" type="checkbox" />

				<div class="contact-agreement-notification hide" id="<portlet:namespace />contact-agreement-notification">
					<strong><liferay-ui:message key="liferay-may-send-me-email-regarding-the-following" />:</strong>

					<div class="ctrl-holder">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							label="<%= true %>"
							name="osbAgreedToContactSales"
						/>
					</div>

					<div class="ctrl-holder">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							label="<%= true %>"
							name="osbAgreedToContactTrainings"
						/>
					</div>

					<div class="ctrl-holder">
						<liferay-expando:custom-attribute
							className="com.liferay.portal.kernel.model.User"
							classPK="<%= 0 %>"
							editable="<%= true %>"
							label="<%= true %>"
							name="osbAgreedToContactEvents"
						/>
					</div>
				</div>
			</div>
		</div>

		<h2>
			<liferay-ui:message key="please-accept-the-following-terms" />:
		</h2>

		<div class="field-group-grid">
			<div class="unit">
				<liferay-ui:input-field
					field="agreedToTermsOfUse"
					model="<%= User.class %>"
				/>

				<label id="<portlet:namespace />termsOfUseLabel"><liferay-ui:message key="i-understand-and-accept-the-terms-and-conditions-of-the-following-agreements" /></label>:

				<a class="txt-u" onClick="var eulaWindow = window.open('/c/portal/register_trial_license?eula=evaluation-license-agreement', 'eula', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); eulaWindow.focus();">
					<liferay-ui:message key="evaluation-license-agreement" />
				</a>,

				<a class="txt-u" onClick="var eulaWindow = window.open('/c/portal/register_trial_license?eula=liferay-developer-studio', 'eula', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); eulaWindow.focus();">
					<liferay-ui:message key="eula-for-liferay-developer-studio" />
				</a>,

				<%
				JournalArticle article = null;

				try {
					Group guestGroup = GroupLocalServiceUtil.getGroup(company.getCompanyId(), GroupConstants.GUEST);

					article = JournalArticleLocalServiceUtil.getArticleByUrlTitle(guestGroup.getGroupId(), "about-us-privacy-policy-legal-text");
				}
				catch (Exception e) {
				}
				%>

				<a class="txt-u" onClick="var eulaWindow = window.open('/c/journal/view_article_content?groupId=<%= article.getGroupId() %>&articleId=<%= article.getArticleId() %>&version=<%= article.getVersion() %>', 'eula', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); void(''); eulaWindow.focus();">
					<liferay-ui:message key="privacy-policy" />
				</a>
			</div>
		</div>

		<div class="field-group-grid">
			<div class="unit">
				<liferay-ui:input-checkbox
					param="agreedToContactTrialLicenses"
				/>

				<label id="<portlet:namespace />contactTrialLicensesLabel">
					<liferay-ui:message key="i-agree-to-allow-liferay-and-its-affiliates-to-contact-me-via-email-and-send-me-a-trial-license-key-to-my-email-address" />
				</label>
			</div>
		</div>
	</fieldset>

	<fieldset class="fieldset">
		<span class="field field-button field-submit">
			<input class="field-input field-input-button field-input-submit" type="submit" value="<liferay-ui:message key="submit" />" />
		</span>

		<p>
			<small>* <liferay-ui:message key="required-field" /></small>
		</p>
	</fieldset>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />emailAddress);
	</aui:script>
</c:if>

<aui:script use="aui-base">
	var A = AUI();

	var agreedToContactCheckBox = A.one('#<portlet:namespace />agreedToContactCheckbox');

	if (agreedToContactCheckBox.get('checked')) {
		A.one('#<portlet:namespace />contact-agreement-notification').show();
	}

	agreedToContactCheckBox.on(
		'click',
		function() {
			if (this.get('checked')) {
				A.one('#<portlet:namespace />contact-agreement-notification').show();
			}
			else {
				A.one('#<portlet:namespace />contact-agreement-notification').hide();

				var agreedToContactCheckBoxes = A.all('#<portlet:namespace />contact-agreement-notification input[type=checkbox]');

				agreedToContactCheckBoxes.each(
					function(item, index, collection) {
						item.set('checked', false);
						item.previous().val('false');
					}
				);
			}
		}
	);
</aui:script>

<%!
private String _errorCss(PortletResponse renderResponse, String fieldIds) {
	StringBuilder sb = new StringBuilder();

	sb.append("<style type=\"text/css\">");

	String[] fieldIdsArray = StringUtil.split(fieldIds);

	for (int i = 0; i < fieldIdsArray.length; i++) {
		String fieldId = fieldIdsArray[i];

		if (i > 0) {
			sb.append(",");
		}

		sb.append("#");
		sb.append(renderResponse.getNamespace());
		sb.append(fieldId);
	}

	sb.append(" { color: red } </style>");

	return sb.toString();
}
%>