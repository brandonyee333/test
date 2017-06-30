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

<%@ include file="/corp_admin/init.jsp" %>

<%
themeDisplay.setIncludeServiceJs(true);

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(corpEntryId);

String encodedLogo = ParamUtil.getString(request, "encodedLogo");
long regionId = BeanParamUtil.getLong(corpEntry, request, "regionId");
long countryId = BeanParamUtil.getLong(corpEntry, request, "countryId");

List<CorpGroup> corpGroups = CorpGroupLocalServiceUtil.getCorpEntryCorpGroups(corpEntryId);

long[] corpGroupIds = ParamUtil.getLongValues(request, "corpGroupIds", StringUtil.split(ListUtil.toString(corpGroups, "corpGroupId"), 0L));
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (corpEntry != null) ? corpEntry.getName() : "new-business-account" %>'
/>

<portlet:actionURL name="updateCorpEntry" var="updateCorpEntryURL" />

<aui:form action="<%= updateCorpEntryURL %>" cssClass="jsp-edit-corp-entry" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/corp_admin/edit_corp_entry.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="corpEntryId" type="hidden" value="<%= String.valueOf(corpEntryId) %>" />
	<aui:input name="corpGroupIds" type="hidden" value="<%= StringUtil.merge(corpGroupIds) %>" />

	<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
	<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
	<liferay-ui:error exception="<%= CorpEntryContactEmailAddressException.class %>" message="please-enter-a-valid-company-email-address" />
	<liferay-ui:error exception="<%= CorpEntryDescriptionException.class %>" message="please-enter-a-description-of-your-company" />
	<liferay-ui:error exception="<%= CorpEntryFaxNumberException.class %>" message="please-enter-a-valid-fax-number" />

	<liferay-ui:error exception="<%= CorpEntryLogoException.class %>">

		<%
		CorpEntryLogoException cele = (CorpEntryLogoException)errorException;
		%>

		<c:if test="<%= cele.getType() == CorpEntryLogoException.TYPE_LOGO_EXTENSION %>">
			<liferay-ui:message key="logos-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PortletPropsValues.CORP_ENTRY_LOGO_EXTENSIONS, StringPool.COMMA_AND_SPACE) %>.
		</c:if>

		<c:if test="<%= cele.getType() == CorpEntryLogoException.TYPE_LOGO_INVALID %>">
			<liferay-ui:message key="please-upload-a-logo" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= CorpEntryNameException.class %>" message="please-enter-a-valid-company-name" />
	<liferay-ui:error exception="<%= CorpEntryProfileEmailAddressException.class %>" message="please-enter-a-valid-profile-email-address" />
	<liferay-ui:error exception="<%= EmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-enter-a-valid-country" />
	<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-valid-region" />
	<liferay-ui:error exception="<%= PhoneNumberException.class %>" message="please-enter-a-valid-phone-number" />
	<liferay-ui:error exception="<%= RestrictedCountryException.class %>" message="under-us-export-laws-the-exportation-sale-or-supply-directly-or-indirectly-of-app-or-services-to-the-selected-country-is-strictly-prohibited-without-prior-authorization-by-the-us-government" />
	<liferay-ui:error exception="<%= WebsiteURLException.class %>" message="please-enter-a-valid-homepage-url" />

	<c:if test="<%= corpEntry != null %>">

		<%
		Group corpEntryGroup = corpEntry.getGroup();
		%>

		<c:if test="<%= corpEntry.isApproved() && corpEntryGroup.isSite() %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="organization-site" />: <aui:a href="<%= PortalUtil.getGroupFriendlyURL(corpEntry.getGroup(), false, themeDisplay) %>" label="<%= HtmlUtil.escape(corpEntry.getName()) %>" target="_blank" />
			</div>
		</c:if>

		<div class="corp-entry-status">
			<liferay-ui:message key="status" />: <liferay-ui:message key="<%= WorkflowConstants.toLabel(corpEntry.getStatus()) %>" />
		</div>
	</c:if>

	<aui:model-context bean="<%= corpEntry %>" model="<%= CorpEntry.class %>" />

	<aui:fieldset>
		<div class="float-container">
			<div class="column-1">
				<aui:input label="full-company-legal-name" name="name" />

				<aui:input label="headquarters-address" name="street1" type="text" />

				<aui:input label="" name="street2" type="text" />

				<aui:input label="" name="street3" type="text" />

				<aui:select label="country" name="countryId" />

				<aui:select label="region" name="regionId" />

				<aui:input label="city" name="city" type="text" />

				<aui:input label="postal-code" name="zip" type="text" />

				<aui:input label="phone" name="phoneNumber" />

				<aui:input label="fax" name="faxNumber" />
			</div>

			<div class="column-2">
				<aui:field-wrapper helpMessage="logos-must-at-most-be-a-220-by-220-pixel-png-file" label='<%= (corpEntry != null) ? LanguageUtil.get(pageContext, "upload-a-new-logo") : LanguageUtil.get(pageContext, "upload-a-logo") %>'>
					<c:choose>
						<c:when test="<%= corpEntry != null %>">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia" var="logoURL">
								<portlet:param name="assetAttachmentId" value="<%= String.valueOf(corpEntry.getLogoId()) %>" />
							</liferay-portlet:resourceURL>

							<img id="<portlet:namespace />corpEntryLogo" src="<%= Validator.isNotNull(encodedLogo) ? HtmlUtil.escapeAttribute(encodedLogo) : logoURL %>" />
						</c:when>
						<c:otherwise>
							<img id="<portlet:namespace />corpEntryLogo" src="<%= HtmlUtil.escapeAttribute(encodedLogo) %>" />
						</c:otherwise>
					</c:choose>

					<aui:input accept="image/*" label="" name="logo" type="file" />
				</aui:field-wrapper>

				<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
					<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "company-description") %>' />
					<liferay-util:param name="required" value="<%= String.valueOf(true) %>" />
					<liferay-util:param name="name" value="description" />
					<liferay-util:param name="value" value="<%= (corpEntry != null) ? corpEntry.getDescription() : StringPool.BLANK %>" />
				</liferay-util:include>

				<aui:input label="homepage-url" name="website" />

				<aui:input label="email" name="profileEmailAddress" />

				<aui:input label="administrator-email" name="contactEmailAddress" />

				<aui:input label="dossiera-account-key" name="dossieraAccountKey" />
			</div>

			<div class="column-3">
				<div class="corp-groups" id="<portlet:namespace />corpGroups">
					<div class="title" style="<%= corpGroups.isEmpty() ? "display: none;" : "" %>">
						<liferay-ui:message key="associated-business-groups" />
					</div>

					<%
					for (CorpGroup corpGroup : corpGroups) {
					%>

						<div class="corp-group-container" data-corpGroupId="<%= corpGroup.getCorpGroupId() %>">
							<img class="remove-icon" src="<%= themeDisplay.getPathThemeImages() + "/arrows/02_x.png" %>" />

							<img class="logo" src="<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia"><portlet:param name="assetAttachmentId" value="<%= String.valueOf(corpGroup.getLogoId()) %>" /></liferay-portlet:resourceURL>" />

							<div class="name">
								<%= HtmlUtil.escape(corpGroup.getName()) %>
							</div>

							<div class="email">
								<%= HtmlUtil.escape(corpGroup.getEmailAddress()) %>
							</div>
						</div>

					<%
					}
					%>

				</div>

				<aui:input cssClass="add-corp-group" label="" name="addCorpGroup" placeholder="associate-business-group" type="text" />
			</div>
		</div>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,autocomplete-highlighters,autocomplete-list,datasource,liferay-dynamic-select">
	var TPL_CORP_GROUP =
		'<div class="corp-group-container" data-corpGroupId="{corpGroupId}">' +
			'<img class="remove-icon" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />' +
			'<img class="logo" src="{logoURL}" />' +
			'<div class="name">' +
				'{name}' +
			'</div>' +
			'<div class="email">' +
				'{email}' +
			'</div>' +
		'</div>';

	new A.AutoCompleteList(
		{
			inputNode: '#<portlet:namespace/>addCorpGroup',
			on: {
				select: function(event) {
					event.preventDefault();

					this.hide();

					var corpGroups = A.one('#<portlet:namespace />corpGroups');

					corpGroups.append(
						A.Lang.sub(
							TPL_CORP_GROUP,
							{
								corpGroupId: event.result.raw.corpGroupId,
								email: Liferay.Util.escapeHTML(event.result.raw.emailAddress),
								logoURL: '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia" />&<portlet:namespace />assetAttachmentId=' + event.result.raw.logoId,
								name: (event.result.raw.name.length > 30) ? Liferay.Util.escapeHTML(event.result.raw.name.substring(0, 30) + '...') : Liferay.Util.escapeHTML(event.result.raw.name)
							}
						)
					);

					document.getElementById('<portlet:namespace />addCorpGroup').value = '';

					<portlet:namespace />setCorpGroupIds();
				}
			},
			render: true,
			requestTemplate: function(query) {
				return '&<portlet:namespace />keywords=' + query + '&<portlet:namespace />notCorpGroupIds=' + document.getElementById("<portlet:namespace />corpGroupIds").value + '&<portlet:namespace />start=0&<portlet:namespace />end=5';
			},
			resultHighlighter: 'phraseMatch',
			resultListLocator: function(response) {
				return A.JSON.parse(response[0].responseText);
			},
			resultTextLocator: 'name',
			source: new A.DataSource.IO(
				{
					source: '<liferay-portlet:resourceURL id="serveCorpGroups" />'
				}
			)
		}
	);

	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />countryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'name',
				selectId: 'countryId',
				selectVal: '<%= countryId %>'
			},
			{
				select: '<portlet:namespace />regionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= regionId %>'
			}
		]
	);

	A.one('#<portlet:namespace />corpGroups').delegate(
		'click',
		function(event) {
			this._node.parentNode.remove();

			<portlet:namespace />setCorpGroupIds();
		},
		'.remove-icon'
	);

	A.one('#p_p_id<portlet:namespace />').delegate(
		'change',
		function() {
			if (this._node.files && this._node.files[0]) {
				var fileReader = new FileReader();

				fileReader.readAsDataURL(this._node.files[0]);

				fileReader.onload = function(event) {
					document.getElementById('<portlet:namespace />corpEntryLogo').src = event.target.result;
				}
			}
		},
		'#<portlet:namespace />logo'
	);

	function <portlet:namespace />setCorpGroupIds() {
		var corpGroupIds = [];

		A.all('#<portlet:namespace />corpGroups .corp-group-container').each(
			function(item, index, collection) {
				corpGroupIds.push(item.attr('data-corpGroupId'));
			}
		);

		document.getElementById('<portlet:namespace />corpGroupIds').value = corpGroupIds.join(',');

		if (corpGroupIds.length > 0) {
			document.getElementById('<portlet:namespace />corpGroups').children[0].style.display = '';
		}
		else {
			document.getElementById('<portlet:namespace />corpGroups').children[0].style.display = 'none';
		}
	}
</aui:script>