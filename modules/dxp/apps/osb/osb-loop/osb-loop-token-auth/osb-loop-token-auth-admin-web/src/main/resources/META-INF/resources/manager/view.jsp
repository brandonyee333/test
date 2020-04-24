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
PortletURL portletURL = renderResponse.createRenderURL();

List<TokenAuthEntry> tokenAuthEntries = TokenAuthEntryLocalServiceUtil.getTokenAuthEntries(user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new TokenAuthEntryCreateDateComparator(true));
%>

<h3><liferay-ui:message key="authorization-tokens" /></h3>

<liferay-ui:search-container
	emptyResultsMessage="no-tokens-were-found"
	iteratorURL="<%= portletURL %>"
	total="<%= tokenAuthEntries.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= tokenAuthEntries %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.osb.loop.token.auth.model.TokenAuthEntry"
		escapedModel="<%= true %>"
		keyProperty="tokenAuthEntryId"
		modelVar="tokenAuthEntry"
	>
		<liferay-ui:search-container-column-text
			name="device"
		>
			<div class="device" id="token_<%= tokenAuthEntry.getTokenAuthEntryId() %>">
				<%= tokenAuthEntry.getDevice() %>
			</div>

			<div class="hide" id="token_<%= tokenAuthEntry.getTokenAuthEntryId() %>_tooltip">
				<liferay-ui:message key="created" />: <%= dateFormatDateTime.format(tokenAuthEntry.getCreateDate()) %>

				<br />

				<liferay-ui:message key="last-login" />: <%= (tokenAuthEntry.getLoginDate() != null) ? dateFormatDateTime.format(tokenAuthEntry.getLoginDate()) : "" %>

				<br />

				<liferay-ui:message key="last-ip-address" />: <%= tokenAuthEntry.getLoginIP() %>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="token"
		>
			<input onClick="select();" readOnly size="10" type="text" value="<%= tokenAuthEntry.getToken() %>" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/entry_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<div>
		<aui:button id='<%= renderResponse.getNamespace() + "addTokenButton" %>' onClick='<%= renderResponse.getNamespace() + "showAddTokenForm();" %>' value="add-token" />

		<div class="hide" id="<portlet:namespace />addTokenForm">
			<liferay-ui:message key="enter-a-device-name" />: <aui:input inlineField="<%= true %>" label="" name="device" type="text" />

			<aui:button onClick='<%= renderResponse.getNamespace() + "generateToken();" %>' value="generate" />
		</div>
	</div>

	<br />

	<liferay-ui:search-iterator
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>

<aui:script>
	function <portlet:namespace />generateToken() {
		var A = AUI();

		var device = A.one('#<portlet:namespace />device');

		if (device.val() == '') {
			alert('<liferay-ui:message key="please-enter-a-device-name" />');

			return;
		}

		var actionURL = '<portlet:actionURL name="generateTokenAuthEntry"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>&<portlet:namespace />device=' + device.val();

		if (window.location.hash) {
			actionURL += window.location.hash;
		}

		submitForm(document.hrefFm, actionURL);
	}

	function <portlet:namespace />showAddTokenForm() {
		var A = AUI();

		A.one('#<portlet:namespace />addTokenButton').hide();
		A.one('#<portlet:namespace />addTokenForm').show();
	}
</aui:script>

<aui:script use="aui-tooltip">
	A.all('.device').each(
		function(item, index, collection) {
			new A.Tooltip(
				{
					align: {
						node: item,
						points: [ 'tl', 'bl' ]
					},
					arrow: 'tl',
					bodyContent: A.one('#' + item.get('id') + '_tooltip').html(),
					hideDelay: 0,
					trigger: item
				}
			).render();
		}
	);
</aui:script>