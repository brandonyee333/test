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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "app-notification");

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="app-notification,legal"
	param="tabs1"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />

	<c:choose>
		<c:when test='<%= tabs1.equals("app-notification") %>'>

			<%
			String currentLanguageId = LanguageUtil.getLanguageId(request);

			String emailAppEntrySubject = PrefsParamUtil.getString(portletPreferences, request, "emailAppEntrySubject_" + currentLanguageId, StringPool.BLANK);
			String emailAppEntryBody = PrefsParamUtil.getString(portletPreferences, request, "emailAppEntryBody_" + currentLanguageId, StringPool.BLANK);

			Locale[] locales = LanguageUtil.getAvailableLocales();
			%>

			<span class="portlet-msg-info">
				<liferay-ui:message key="enter-custom-values-or-leave-it-blank-to-use-the-default-portal-settings" />
			</span>

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:message key="language" />
				</td>
				<td>
					<select name="<portlet:namespace />languageId" onChange="<portlet:namespace />updateLanguage(this);">

						<%
						for (int i = 0; i < locales.length; i++) {
							String optionStyle = StringPool.BLANK;

							if (Validator.isNotNull(portletPreferences.getValue("emailAppEntrySubject_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK)) ||
								Validator.isNotNull(portletPreferences.getValue("emailAppEntryBody_" + LocaleUtil.toLanguageId(locales[i]), StringPool.BLANK))) {

								optionStyle = "style=\"font-weight: bold;\"";
							}
						%>

							<option <%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) ? "selected" : "" %> <%= optionStyle %> value="<%= LocaleUtil.toLanguageId(locales[i]) %>"><%= locales[i].getDisplayName(locale) %></option>

						<%
						}
						%>

					</select>
				</td>
			</tr>
			</table>

			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="subject" name='<%= "emailAppEntrySubject_" + currentLanguageId %>' value="<%= emailAppEntrySubject %>" />

				<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "emailAppEntryBody_" + currentLanguageId %>' type="textarea" value="<%= emailAppEntryBody %>" />
			</aui:fieldset>

			<div class="definition-of-terms">
				<h4>
					<liferay-ui:message key="definition-of-terms" />
				</h4>

				<dl>
					<dt>
						[$APP_ENTRY_STATUS$]
					</dt>
					<dd>
						The status of the app entry
					</dd>
					<dt>
						[$APP_ENTRY_TITLE$]
					</dt>
					<dd>
						The app entry's title
					</dd>
					<dt>
						[$APP_ENTRY_URL$]
					</dt>
					<dd>
						The app entry's url
					</dd>
					<dt>
						[$USER_FULL_NAME$]
					</dt>
					<dd>
						The developer's full name
					</dd>
				</dl>
			</div>

			<script type="text/javascript">
				function <portlet:namespace />updateLanguage() {
					document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = '';

					submitForm(document.<portlet:namespace />fm);
				}
			</script>
		</c:when>
		<c:otherwise>

			<%
			String eulaMinimumTermsArticleId = PrefsParamUtil.getString(portletPreferences, request, "eulaMinimumTermsArticleId", StringPool.BLANK);
			%>

			<aui:input cssClass="lfr-input-text-container" label="eula-minimum-terms-article-id" name="eulaMinimumTermsArticleId" value="<%= eulaMinimumTermsArticleId %>" />
		</c:otherwise>
	</c:choose>

	<div class="separator"><!-- --></div>

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>