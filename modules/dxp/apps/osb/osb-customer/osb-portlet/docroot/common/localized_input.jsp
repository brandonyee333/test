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
String defaultLanguageId = ParamUtil.getString(request, "defaultLanguageId");
boolean disabled = ParamUtil.getBoolean(request, "disabled");
String label = ParamUtil.getString(request, "label");
String name = ParamUtil.getString(request, "name");
boolean required = ParamUtil.getBoolean(request, "required");
boolean textarea = ParamUtil.getBoolean(request, "textarea", true);
String value = ParamUtil.getString(request, "value");

Locale[] locales = LanguageUtil.getAvailableLocales();

Locale currentLocale = locale;

if (Validator.isNotNull(value)) {
	String defaultLocale = LocalizationUtil.getDefaultLocale(value);

	currentLocale = LocaleUtil.fromLanguageId(defaultLocale);
}

String[] availableLocales = LocalizationUtil.getAvailableLocales(value);

if (availableLocales.length == 0) {
	availableLocales = ArrayUtil.append(availableLocales, currentLocale.toString());
}

boolean localized = false;

if (availableLocales.length > 1) {
	localized = true;
}
%>

<span class="localized-input localized-input-<%= name %>">
	<input class="current-language-id-input" name="<portlet:namespace />currentLanguageId" type="hidden" value="<%= LocaleUtil.toLanguageId(currentLocale) %>" />

	<span class="aui-field-text<%= required ? " required" : StringPool.BLANK %>">
		<label class="aui-field-label">
			<%= HtmlUtil.escape(label) %>
		</label>
	</span>
	<span class="current-language-container<%= localized ? StringPool.BLANK : " aui-helper-hidden" %>">
		<span><%= currentLocale.getDisplayName(locale) %></span>

		<a class="aui-helper-hidden remove-translation" href="javascript:;"><liferay-ui:message key="remove" /></a>
	</span>

	<c:choose>
		<c:when test="<%= textarea %>">
			<aui:input cssClass="localized-input-field" disabled="<%= disabled %>" name="<%= name %>" type="textarea" value="<%= LocalizationUtil.getLocalization(value, currentLocale.toString(), true) %>" />
		</c:when>
		<c:otherwise>
			<aui:input cssClass="localized-input-field" disabled="<%= disabled %>" name="<%= name %>" type="text" value="<%= LocalizationUtil.getLocalization(value, currentLocale.toString(), true) %>" />
		</c:otherwise>
	</c:choose>

	<div class="language-container<%= localized ? StringPool.BLANK : " aui-helper-hidden" %>">
		<c:choose>
			<c:when test="<%= Validator.isNull(defaultLanguageId) %>">
				<aui:select cssClass="default-language-id-select" disabled="<%= disabled %>" label="default-language" name='<%= name + "DefaultLanguageId" %>'>

					<%
					for (Locale curLocale : locales) {
					%>

						<aui:option label="<%= curLocale.getDisplayName(locale) %>" selected="<%= currentLocale.equals(curLocale) %>" value="<%= LocaleUtil.toLanguageId(curLocale) %>" />

					<%
					}
					%>

				</aui:select>
			</c:when>
			<c:otherwise>
				<input class="default-language-id-input" name="<portlet:namespace /><%= name + "DefaultLanguageId" %>" type="hidden" value="<%= HtmlUtil.escape(defaultLanguageId) %>" />
			</c:otherwise>
		</c:choose>

		<span class="lfr-translation-manager-add-menu">
			<liferay-ui:icon-menu
				cssClass="add-translations-menu"
				direction="down"
				disabled="<%= disabled %>"
				icon='<%= themeDisplay.getPathThemeImages() + "/common/add.png" %>'
				message="add-translation"
				showArrow="<%= true %>"
				showWhenSingleIcon="<%= true %>"
			>

				<%
				for (Locale curLocale : locales) {
					String languageId = LocaleUtil.toLanguageId(curLocale);

					String cssClassName = "language-id-" + languageId;

					if (ArrayUtil.contains(availableLocales, languageId)) {
						cssClassName += " hide";
					}
				%>

					<liferay-ui:icon
						cssClass="<%= cssClassName %>"
						image='<%= "../language/" + languageId %>'
						message="<%= curLocale.getDisplayName(locale) %>"
						onClick="javascript:;"
					/>

				<%
				}
				%>

			</liferay-ui:icon-menu>
		</span>

		<div class="available-translations">
			<label>
				<liferay-ui:message key="available-translations" />
			</label>

			<%
			for (Locale curLocale : locales) {
				String languageId = LocaleUtil.toLanguageId(curLocale);

				String cssClassName = "lfr-token language-id-" + languageId;

				if (!ArrayUtil.contains(availableLocales, languageId)) {
					cssClassName += " hide";
				}
			%>

				<aui:input data-displayName="<%= curLocale.getDisplayName(locale) %>" data-languageId="<%= languageId %>" name='<%= name + "_" + languageId %>' type="hidden" value="<%= LocalizationUtil.getLocalization(value, languageId, false) %>" />

				<a class="<%= cssClassName %>" data-languageId="<%= languageId %>" href="javascript:;">
					<img src="<%= themeDisplay.getPathThemeImages() + "/language/" + languageId + ".png" %>" />

					<%= curLocale.getDisplayName(locale) %>
				</a>

			<%
			}
			%>

		</div>
	</div>

	<aui:input cssClass="enable-localized-input" disabled="<%= disabled %>" label="localized" name='<%= name + "Localized" %>' type="checkbox" value="<%= localized %>" />
</span>

<aui:script use="localized-input">
	new Liferay.LocalizedInput('.localized-input-<%= HtmlUtil.escapeJS(name) %>');
</aui:script>