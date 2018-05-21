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
String portletId = PortalUtil.getPortletId(request);

String name = GetterUtil.getString(portletPreferences.getValue("name", null));
String fileName = GetterUtil.getString(portletPreferences.getValue("fileName", null));
String releaseNotesURL = GetterUtil.getString(portletPreferences.getValue("releaseNotesURL", null));
String footerText = GetterUtil.getString(portletPreferences.getValue("footerText", null));
%>

<h2 class="section-heading">
	<%= HtmlUtil.escape(name) %>
</h2>

<div class="unit-content">
	<div class="callout-a callout-download clearfix">
		<div class="sub-grid">
			<div class="u-img unit">
				<img src="<%= PortalUtil.getPathContext(request) %>/images/security.png" />
			</div>

			<div class="u-nfo unit">
				<h1 class="section-heading">
					<liferay-ui:message key="liferay-portal" />
				</h1>

				<p>
					<%= HtmlUtil.escape(name) %>
				</p>

				<c:if test="<%= Validator.isNotNull(releaseNotesURL) %>">
					<p>
						<span class="url">
							<a href="<%= HtmlUtil.escapeHREF(releaseNotesURL) %>"><liferay-ui:message key="release-notes" /></a>
						</span>
					</p>
				</c:if>
			</div>

			<%
			List<AccountEntry> accountEntries = AccountEntryServiceUtil.getSecurityPatchAccountEntries(portletId);
			%>

			<c:if test="<%= !accountEntries.isEmpty() %>">
				<div class="u-dld unit">
					<p>
						<liferay-ui:message key="choose-project" />

						<br />

						<select onChange="<portlet:namespace />selectAccountEntry(this.value);">
							<option value="0"></option>

							<%
							for (AccountEntry accountEntry : accountEntries) {
							%>

								<option value="<%= accountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(accountEntry.getName()) %></option>

							<%
							}
							%>

						</select>
					</p>

					<p>
						<liferay-ui:message key="security-patch" />

						<br />

						<select id="<portlet:namespace />securityPatch" onChange="<portlet:namespace />selectSecurityPatch(this.value);">
						</select>
					</p>

					<a class="btn hide" href="javascript:;" id="<portlet:namespace />downloadLink"><liferay-ui:message key="download" /></a>
				</div>
			</c:if>
		</div>
	</div>

	<c:if test="<%= Validator.isNotNull(footerText) %>">
		<div class="note">
			<%= HtmlUtil.escape(footerText) %>
		</div>
	</c:if>
</div>

<aui:script>
	function <portlet:namespace />selectSecurityPatch(securityPatchId) {
		var A = AUI();

		var downloadLink = A.one('#<portlet:namespace />downloadLink');

		if (securityPatchId) {
			var href = '<liferay-portlet:actionURL copyCurrentRenderParameters="<%= false %>" windowState="<%= LiferayWindowState.POP_UP.toString() %>" />' + '&<portlet:namespace />securityPatchId=' + securityPatchId;

			downloadLink.set('href', href);

			downloadLink.show();
		}
		else {
			downloadLink.set('href', '');

			downloadLink.hide();
		}
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectAccountEntry',
		function(accountEntryId) {
			var A = AUI();

			var securityPatch = A.one('#<portlet:namespace />securityPatch');

			if (accountEntryId <= 0) {
				securityPatch.empty();

				securityPatch.setData('key', 0);

				A.one('#<portlet:namespace />downloadLink').hide();

				return;
			}

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="securityPatches" />',
				{
					data: {
						<portlet:namespace />accountEntryId: accountEntryId,
						<portlet:namespace />portletId: '<%= portletId %>'
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							if (securityPatch.getData('key') == response["SecurityPatches#key"]) {
								return;
							}

							securityPatch.setData('key', response["SecurityPatches#key"]);

							var selectOptions = [];

							selectOptions.push('<option value=""></option>');

							var selectData = response['SecurityPatches'];

							if (selectData) {
								for (var i = 0; i < selectData.length; i++) {
									var name = selectData[i].name;
									var value = selectData[i].value;

									selectOptions.push('<option value="' + value + '">' + name + '</option>');
								}
							}

							selectOptions = selectOptions.join('');

							securityPatch.empty();
							securityPatch.append(selectOptions);
							securityPatch.val('');

							A.one('#<portlet:namespace />downloadLink').hide();
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>