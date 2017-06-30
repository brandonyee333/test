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
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title="create-a-business-account"
/>

<liferay-portlet:renderURL var="editCorpEntryURL">
	<portlet:param name="mvcPath" value="/corp_admin/edit_corp_entry.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="backURL" value="<%= currentURL %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= editCorpEntryURL %>" cssClass="jsp-add-corp-entry" method="post" name="fm">
	<liferay-ui:message key="check-if-your-company-already-exists" />

	<aui:fieldset>
		<div class="corp-entry-search-container">
			<div class="results">

				<%
				String taglibOnKeyUp = "if (this.value == '') { document.getElementById('" + renderResponse.getNamespace() + "addButton').style.display = 'none'; } else { document.getElementById('" + renderResponse.getNamespace() + "addButton').style.display = ''; }";
				%>

				<aui:input name="name" onKeyUp="<%= taglibOnKeyUp %>" />

				<div class="add-button" id="<portlet:namespace />addButton" style="display: none;">

					<%
					String taglibHREF = "javascript:submitForm(document." + renderResponse.getNamespace() + "fm);";
					%>

					<aui:a href="<%= taglibHREF %>">
						<span class="plus-sign">+</span>

						<span class="label"><liferay-ui:message key="add" /></span>
					</aui:a>
				</div>
			</div>
		</div>

		<div class="corp-entry" id="<portlet:namespace />corpEntry"><!-- --></div>
	</aui:fieldset>
</aui:form>

<aui:script>
	var TPL_CORP_ENTRY =
		'<div class="corp-entry-container">' +
			'<img class="logo" src="{logoURL}" />' +
			'<div class="name">' +
				'{name}' +
			'</div>' +
			'<div class="status">' +
				'{status}' +
			'</div>' +
			'<div class="website">' +
				'{website}' +
			'</div>' +
			'<div class="description">' +
				'{description}' +
			'</div>' +
			'<div class="email">' +
				'<span class="label"><%= LanguageUtil.get(pageContext, "email") %>:</span>' +
				'{contactEmailAddress}' +
			'</div>' +
			'<div class="country">' +
				'<span class="label"><%= LanguageUtil.get(pageContext, "country") %>:</span>' +
				'{country}' +
			'</div>' +
			'<div class="region" style="display: {regionDisplay};">' +
				'<span class="label"><%= LanguageUtil.get(pageContext, "region") %>:</span>' +
				'{region}' +
			'</div>'
		'</div>';

	Liferay.provide(
		window,
		'<portlet:namespace />displayCorpEntry',
		function(event) {
			var A = AUI();

			if (event.newVal != null) {
				var corpEntryId = event.newVal._data.result.raw.corpEntryId;

				var corpEntry = A.one('#<portlet:namespace />corpEntry');

				A.io.request(
					'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveCorpEntry" />',
					{
						data: {
							<portlet:namespace />corpEntryId: corpEntryId
						},
						dataType: 'json',
						method: 'GET',
						on: {
							success:function() {
								var responseData = this.get('responseData')[0];

								corpEntry._node.innerHTML = '';

								corpEntry.append(
									A.Lang.sub(
										TPL_CORP_ENTRY,
										{
											contactEmailAddress: (responseData.contactEmailAddress.length > 0) ? Liferay.Util.escapeHTML(responseData.contactEmailAddress) : '',
											country: Liferay.CorpAdmin.Address.getCountryName(responseData.address.countryId),
											description: (responseData.descriptionCurrentValue.length > 600) ? Liferay.Util.escapeHTML(responseData.descriptionCurrentValue.substring(0, 600) + '...') : Liferay.Util.escapeHTML(responseData.descriptionCurrentValue),
											logoURL: '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentMedia" />' + '&<portlet:namespace />assetAttachmentId=' + responseData.logoId,
											name: Liferay.Util.escapeHTML(responseData.name),
											region: (responseData.address.regionId != 0) ? Liferay.CorpAdmin.Address.getRegionName(responseData.address.regionId) : '',
											regionDisplay: ((responseData.address.regionId != 0) ? '' : 'none;'),
											status: Liferay.CorpAdmin.WorkflowConstants.toLabel(responseData.status),
											website: (responseData.website.length > 0) ? Liferay.Util.escapeHTML(responseData.website) : ''
										}
									)
								);
							}
						}
					}
				);

				corpEntry.on(
					'click',
					function(event) {
						window.location.href = '<%= editCorpEntryURL %>&<portlet:namespace />corpEntryId=' + corpEntryId;
					}
				);
			}
		},
		['aui-base', 'aui-io-request', 'liferay-corp-admin']
	);
</aui:script>

<aui:script use="aui-base,autocomplete-highlighters,autocomplete-list,datasource,liferay-corp-admin">
	new A.AutoCompleteList(
		{
			alwaysShowList: true,
			inputNode: '#<portlet:namespace/>name',
			on: {
				activeItemChange: function(event) {
					<portlet:namespace />displayCorpEntry(event);
				},
				hoveredItemChange: function(event) {
					<portlet:namespace />displayCorpEntry(event);
				},
				select: function(event) {
					window.location.href = '<%= editCorpEntryURL %>&<portlet:namespace />corpEntryId=' + event.result.raw.corpEntryId;
				}
			},
			render: true,
			requestTemplate: '&<portlet:namespace />keywords={query}&<portlet:namespace />status=<%= WorkflowConstants.STATUS_ANY %>&<portlet:namespace />start=0&<portlet:namespace />end=5',
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

	A.one('html').delegate(
		'click',
		function(event) {
			var corpEntry = A.one('#<portlet:namespace />corpEntry');

			if (corpEntry && corpEntry._node) {
				corpEntry._node.innerHTML = '';
			}
		},
		'body'
	);
</aui:script>