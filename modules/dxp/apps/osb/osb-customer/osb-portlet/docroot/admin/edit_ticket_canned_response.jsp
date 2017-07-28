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

String backURL = ParamUtil.getString(request, "backURL", redirect);

long ticketCannedResponseId = ParamUtil.getLong(request, "ticketCannedResponseId");

TicketCannedResponse ticketCannedResponse = null;

try {
	ticketCannedResponse = TicketCannedResponseLocalServiceUtil.getTicketCannedResponse(ticketCannedResponseId);
}
catch (NoSuchTicketCannedResponseException nstcre) {
}

String languageId = LanguageUtil.getLanguageId(request);

String defaultLanguageId = StringPool.BLANK;

String name = ParamUtil.getString(request, "name");
String content = ParamUtil.getString(request, "content");

if (ticketCannedResponse != null) {
	defaultLanguageId = ticketCannedResponse.getDefaultLocale();

	name = ticketCannedResponse.getName(languageId, false);
	content = ticketCannedResponse.getContent(languageId, false);
}
%>

<portlet:actionURL name="updateTicketCannedResponse" var="updateTicketCannedResponseURL">
	<portlet:param name="mvcPath" value="/admin/edit_ticket_canned_response.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTicketCannedResponseURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="ticketCannedResponseId" type="hidden" value="<%= ticketCannedResponseId %>" />
	<aui:input name="defaultLanguageId" type="hidden" value="<%= defaultLanguageId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="canned-response"
	/>

	<liferay-ui:error exception="<%= DuplicateTicketCannedResponseException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= TicketCannedResponseContentException.class %>" message="please-enter-valid-contents" />
	<liferay-ui:error exception="<%= TicketCannedResponseNameException.class %>" message="please-enter-a-valid-name" />

	<table class="lfr-table">

	<c:if test="<%= ticketCannedResponse != null %>">
		<tr>
			<td>
				<liferay-ui:message key="default-language" />
			</td>
			<td>
				<%= LocaleUtil.fromLanguageId(defaultLanguageId).getDisplayName(locale) %>
			</td>
		</tr>
	</c:if>

	<tr>
		<td>
			<liferay-ui:message key='<%= (ticketCannedResponse != null) ? "language" : "default-language" %>' />
		</td>
		<td>
			<select name="<portlet:namespace />languageId" onChange="<%= (ticketCannedResponse != null) ? (renderResponse.getNamespace() + "updateLanguage(this.value);") : "" %>">

				<%
				Locale[] locales = LanguageUtil.getAvailableLocales();

				for (int i = 0; i < locales.length; i++) {
				%>

					<option <%= languageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

				<%
				}
				%>

			</select>

			<c:if test="<%= ticketCannedResponse != null %>">
				<c:choose>
					<c:when test="<%= !languageId.equals(defaultLanguageId) && ArrayUtil.contains(ticketCannedResponse.getAvailableLocales(), languageId) %>">
						<input onClick="<portlet:namespace />setDefaultLocale('<%= languageId %>');" type="button" value="<liferay-ui:message key="set-default" />" />

						<input onClick="<portlet:namespace />removeCannedResponseLocale();" type="button" value="<liferay-ui:message key="remove" />" />
					</c:when>
					<c:when test="<%= !ArrayUtil.contains(ticketCannedResponse.getAvailableLocales(), languageId) %>">
						(<liferay-ui:message key="new" />)
					</c:when>
				</c:choose>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<input class="lfr-input-text" name="<portlet:namespace />name" onChange="<portlet:namespace />contentChanged();" type="text" value="<%= HtmlUtil.escape(name) %>">
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="content" /> <liferay-ui:icon-help message="canned-response-content-help" />
		</td>
		<td>
			<textarea class="lfr-textarea" name="<portlet:namespace />content" onChange="<portlet:namespace />contentChanged();"><%= HtmlUtil.escape(content) %></textarea>
		</td>
	</tr>
	</table>

	<br />

	<div>
		<input type="submit" value="<liferay-ui:message key="save" />" />

		<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
	</div>
</aui:form>

<script type="text/javascript">
	var <portlet:namespace />contentChangedFlag = false;

	function <portlet:namespace />contentChanged() {
		<portlet:namespace />contentChangedFlag = true;
	}

	function <portlet:namespace />getLanguageViewURL(languageId) {
		return "<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_ticket_canned_response.jsp" /><portlet:param name="redirect" value="<%= redirect %>" /><portlet:param name="ticketCannedResponseId" value="<%= String.valueOf(ticketCannedResponseId) %>" /></portlet:renderURL>&<portlet:namespace />languageId=" + languageId;
	}

	function <portlet:namespace />removeCannedResponseLocale() {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-deactivate-this-language'))) {
			document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '<%= Constants.DELETE_TRANSLATION %>';
			document.<portlet:namespace />fm.<portlet:namespace />redirect.value = <portlet:namespace />getLanguageViewURL('<%= defaultLanguageId %>');
			document.<portlet:namespace />fm.submit();
		}
	}

	function <portlet:namespace />setDefaultLocale(languageId) {
		if (confirm(Liferay.Language.get('are-you-sure-you-want-to-set-the-default-language-to-this-language'))) {
			document.<portlet:namespace />fm.<portlet:namespace />defaultLanguageId.value = languageId;
			document.<portlet:namespace />fm.<portlet:namespace />redirect.value = <portlet:namespace />getLanguageViewURL(languageId);
			document.<portlet:namespace />fm.submit();
		}
	}

	function <portlet:namespace />updateLanguage(languageId) {
		document.<portlet:namespace />fm.<portlet:namespace />languageId.value = '<%= languageId %>';

		if (<portlet:namespace />contentChangedFlag) {
			if (!confirm(Liferay.Language.get('would-you-like-to-save-the-changes-made-to-this-language'))) {
				if (!confirm(Liferay.Language.get('are-you-sure-you-want-to-switch-the-languages-view'))) {
					return;
				}
				else {
					document.<portlet:namespace />fm.<portlet:namespace />cmd.value = "";
				}
			}
		}
		else {
			document.<portlet:namespace />fm.<portlet:namespace />cmd.value = "";
		}

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = <portlet:namespace />getLanguageViewURL(languageId);
		document.<portlet:namespace />fm.submit();
	}
</script>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>