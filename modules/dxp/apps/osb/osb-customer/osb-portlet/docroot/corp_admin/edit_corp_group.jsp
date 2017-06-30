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

long corpGroupId = ParamUtil.getLong(request, "corpGroupId");

CorpGroup corpGroup = CorpGroupLocalServiceUtil.fetchCorpGroup(corpGroupId);

String encodedLogo = ParamUtil.getString(request, "encodedLogo");

List<CorpEntry> corpEntries = CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(corpGroupId);

long[] corpEntryIds = ParamUtil.getLongValues(request, "corpEntryIds", StringUtil.split(ListUtil.toString(corpEntries, "corpEntryId"), 0L));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (corpGroup != null) ? corpGroup.getName() : "new-business-group" %>'
/>

<portlet:actionURL name="updateCorpGroup" var="updateCorpGroupURL" />

<aui:form action="<%= updateCorpGroupURL %>" cssClass="jsp-edit-corp-group" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/corp_admin/edit_corp_group.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="corpGroupId" type="hidden" value="<%= String.valueOf(corpGroupId) %>" />
	<aui:input name="corpEntryIds" type="hidden" value="<%= StringUtil.merge(corpEntryIds) %>" />

	<liferay-ui:error exception="<%= CorpGroupDescriptionException.class %>" message="please-enter-a-description-for-the-business-group" />
	<liferay-ui:error exception="<%= CorpGroupEmailAddressException.class %>" message="please-enter-a-valid-email-for-the-business-group" />

	<liferay-ui:error exception="<%= CorpGroupLogoException.class %>">

		<%
		CorpGroupLogoException cgle = (CorpGroupLogoException)errorException;
		%>

		<c:if test="<%= cgle.getType() == CorpGroupLogoException.TYPE_LOGO_EXTENSION %>">
			<liferay-ui:message key="logos-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(PortletPropsValues.CORP_GROUP_LOGO_EXTENSIONS, StringPool.COMMA_AND_SPACE) %>.
		</c:if>

		<c:if test="<%= cgle.getType() == CorpGroupLogoException.TYPE_LOGO_INVALID %>">
			<liferay-ui:message key="please-upload-a-logo" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= CorpGroupNameException.class %>" message="please-enter-a-valid-business-group-name" />

	<aui:model-context bean="<%= corpGroup %>" model="<%= CorpGroup.class %>" />

	<aui:fieldset>
		<div class="float-container">
			<div class="column-1">
				<aui:field-wrapper helpMessage="logos-must-at-most-be-a-220-by-220-pixel-png-file" label='<%= (corpGroup != null) ? LanguageUtil.get(pageContext, "upload-a-new-logo") : LanguageUtil.get(pageContext, "upload-a-logo") %>'>
					<c:choose>
						<c:when test="<%= corpGroup != null %>">
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia" var="logoURL">
								<portlet:param name="assetAttachmentId" value="<%= String.valueOf(corpGroup.getLogoId()) %>" />
							</liferay-portlet:resourceURL>

							<img id="<portlet:namespace />corpGroupLogo" src="<%= Validator.isNotNull(encodedLogo) ? HtmlUtil.escapeAttribute(encodedLogo) : logoURL %>" />
						</c:when>
						<c:otherwise>
							<img id="<portlet:namespace />corpGroupLogo" src="<%= HtmlUtil.escapeAttribute(encodedLogo) %>" />
						</c:otherwise>
					</c:choose>

					<aui:input accept="image/*" label="" name="logo" type="file" />
				</aui:field-wrapper>

				<aui:input label="friendly-display-name" name="name" />

				<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
					<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "business-group-description") %>' />
					<liferay-util:param name="required" value="<%= String.valueOf(true) %>" />
					<liferay-util:param name="name" value="description" />
					<liferay-util:param name="value" value="<%= (corpGroup != null) ? corpGroup.getDescription() : StringPool.BLANK %>" />
				</liferay-util:include>

				<aui:input name="website" />

				<aui:input label="email" name="emailAddress" />
			</div>

			<div class="column-2">
				<div class="corp-entries" id="<portlet:namespace />corpEntries">
					<div class="title" style="<%= corpEntries.isEmpty() ? "display: none;" : "" %>">
						<liferay-ui:message key="associated-business-accounts" />
					</div>

					<%
					for (CorpEntry corpEntry : corpEntries) {
					%>

						<div class="corp-entry-container" data-corpEntryId="<%= corpEntry.getCorpEntryId() %>">
							<img class="remove-icon" src="<%= themeDisplay.getPathThemeImages() + "/arrows/02_x.png" %>" />

							<img class="logo" src="<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia"><portlet:param name="assetAttachmentId" value="<%= String.valueOf(corpEntry.getLogoId()) %>" /></liferay-portlet:resourceURL>" />

							<div class="name">
								<%= HtmlUtil.escape(corpEntry.getName()) %>
							</div>

							<%
							Address address = AddressLocalServiceUtil.fetchAddress(corpEntry.getAddressId());
							%>

							<div class="country">
								<%= (address != null) ? HtmlUtil.escape(address.getCountry().getName()) : "" %>
							</div>
						</div>

					<%
					}
					%>

				</div>

				<aui:input cssClass="add-corp-entry" label="" name="addCorpEntry" placeholder="associate-business-account" type="text" />
			</div>

			<div class="column-3"><!-- --></div>
		</div>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-base,autocomplete-highlighters,autocomplete-list,datasource,liferay-corp-admin">
	var TPL_CORP_ENTRY =
		'<div class="corp-entry-container" data-corpEntryId="{corpEntryId}">' +
			'<img class="remove-icon" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />' +
			'<img class="logo" src="{logoURL}" />' +
			'<div class="name">' +
				'{name}' +
			'</div>' +
			'<div class="country">' +
				'{country}' +
			'</div>' +
		'</div>';

	new A.AutoCompleteList(
		{
			inputNode: '#<portlet:namespace/>addCorpEntry',
			on: {
				select: function(event) {
					event.preventDefault();

					this.hide();

					var corpEntries = A.one('#<portlet:namespace />corpEntries');

					corpEntries.append(
						A.Lang.sub(
							TPL_CORP_ENTRY,
							{
								corpEntryId: event.result.raw.corpEntryId,
								country: Liferay.CorpAdmin.Address.getCountryName(event.result.raw.address.countryId),
								logoURL: '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia" />&<portlet:namespace />assetAttachmentId=' + event.result.raw.logoId,
								name: ((event.result.raw.name.length > 30) ? Liferay.Util.escapeHTML(event.result.raw.name.substring(0, 30) + '...') : Liferay.Util.escapeHTML(event.result.raw.name))
							}
						)
					);

					document.getElementById('<portlet:namespace />addCorpEntry').value = '';

					<portlet:namespace />setCorpEntryIds();
				}
			},
			render: true,
			requestTemplate: function(query) {
				return '&<portlet:namespace />keywords=' + query + '&<portlet:namespace />status=<%= WorkflowConstants.STATUS_APPROVED %>&<portlet:namespace />notCorpEntryIds=' + document.getElementById("<portlet:namespace />corpEntryIds").value + '&<portlet:namespace />start=0&<portlet:namespace />end=5';
			},
			resultFormatter: function(query, results) {
				return A.Array.map(
					results, function(result) {
						return A.Highlight.all(result.raw.name, query) + '<span class="status-label">' + Liferay.CorpAdmin.WorkflowConstants.toLabel(result.raw.status) + '</span>';
					}
				);
			},
			resultHighlighter: 'phraseMatch',
			resultListLocator: function(response) {
				return A.JSON.parse(response[0].responseText);
			},
			resultTextLocator: 'name',
			source: new A.DataSource.IO(
				{
					source: '<liferay-portlet:resourceURL id="serveCorpEntries" />'
				}
			)
		}
	);

	A.one('#<portlet:namespace />corpEntries').delegate(
		'click',
		function(event) {
			this._node.parentNode.remove();

			<portlet:namespace />setCorpEntryIds();
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
					document.getElementById('<portlet:namespace />corpGroupLogo').src = event.target.result;
				}
			}
		},
		'#<portlet:namespace />logo'
	);

	function <portlet:namespace />setCorpEntryIds() {
		var corpEntryIds = [];

		A.all('#<portlet:namespace />corpEntries .corp-entry-container').each(
			function(item, index, collection) {
				corpEntryIds.push(item.attr('data-corpEntryId'));
			}
		);

		document.getElementById('<portlet:namespace />corpEntryIds').value = corpEntryIds.join(',');

		if (corpEntryIds.length > 0) {
			document.getElementById('<portlet:namespace />corpEntries').children[0].style.display = '';
		}
		else {
			document.getElementById('<portlet:namespace />corpEntries').children[0].style.display = 'none';
		}
	}
</aui:script>