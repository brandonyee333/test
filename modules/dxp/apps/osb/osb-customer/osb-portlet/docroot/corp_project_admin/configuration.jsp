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
String cmd = ParamUtil.getString(request, Constants.CMD, Constants.UPDATE);

String tabs1 = ParamUtil.getString(request, "tabs1", "project-messages");

String redirect = ParamUtil.getString(request, "redirect");

String currentLanguageId = LanguageUtil.getLanguageId(request);

Locale[] locales = LanguageUtil.getAvailableLocales();
%>

<script type="text/javascript">
	function <portlet:namespace />updateForm() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';
		submitForm(document.<portlet:namespace />fm);
	}
</script>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />

	<liferay-ui:error key="titleInvalid" message="please-enter-a-valid-title" />

	<liferay-ui:tabs
		names="project-messages"
		param="tabs1"
		url="<%= portletURL %>"
	/>

	<%
	String template = ParamUtil.getString(request, "template", "warning");

	String projectTitleKey = CorpProjectAdminUtil.getPreferenceKey("projectTitle_", template, currentLanguageId);

	String projectTitle = PrefsParamUtil.getString(portletPreferences, request, projectTitleKey, StringPool.BLANK);
	%>

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="language" />
		</td>
		<td>
			<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateForm();">

				<%
				for (int i = 0; i < locales.length; i++) {
				%>

					<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

				<%
				}
				%>

			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="template" />
		</td>
		<td>
			<select name="<portlet:namespace />template" onChange="<portlet:namespace />updateForm();">
				<option <%= template.equals("warning") ? "selected" : "" %> value="warning"><liferay-ui:message key="subscription-expiration-warning" /></option>
				<option <%= template.equals("pastDue") ? "selected" : "" %> value="pastDue"><liferay-ui:message key="subscription-expiration-past-due" /></option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="title" />
		</td>
		<td>
			<aui:input ignoreRequestValue="true" label="" maxLength='<%= ModelHintsUtil.getMaxLength(CorpProjectMessage.class.getName(), "title") %>' name="projectTitle" value="<%= projectTitle %>" />
		</td>
	</tr>
	</table>

	<%
	String projectMessageKey = CorpProjectAdminUtil.getPreferenceKey("projectMessage_", template, currentLanguageId);

	String projectMessage = PrefsParamUtil.getString(portletPreferences, request, projectMessageKey, StringPool.BLANK);
	%>

	<aui:fieldset>
		<aui:input cssClass="lfr-textarea-container" ignoreRequestValue="true" label="message" name="projectMessage" type="textarea" value="<%= projectMessage %>" />
	</aui:fieldset>

	<div class="definition-of-terms">
		<h4>
			<liferay-ui:message key="definition-of-terms" />
		</h4>

		<dl>
			<dt>
				[$SERVICES_END_DATE$]
			</dt>
			<dd>
				30 days after the support end date
			</dd>
			<dt>
				[$SUPPORT_END_DATE$]
			</dt>
			<dd>
				The project's latest support end date
			</dd>
		</dl>
	</div>

	<input type="submit" value="<liferay-ui:message key="save" />" />
</aui:form>