<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group liveGroup = (Group)request.getAttribute("site.liveGroup");
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="display-settings"
/>

<h4 class="text-default"><liferay-ui:message key="language" /></h4>

<%
UnicodeProperties typeSettingsProperties = null;

if (liveGroup != null) {
	typeSettingsProperties = liveGroup.getTypeSettingsProperties();
}
else {
	typeSettingsProperties = new UnicodeProperties();
}

boolean inheritLocales = GetterUtil.getBoolean(typeSettingsProperties.getProperty(GroupConstants.TYPE_SETTINGS_KEY_INHERIT_LOCALES), true);

LayoutSet publicLayoutSet = liveGroup.getPublicLayoutSet();
LayoutSet privateLayoutSet = liveGroup.getPrivateLayoutSet();

boolean disabledLocaleInput = false;

if ((publicLayoutSet.isLayoutSetPrototypeLinkEnabled() || privateLayoutSet.isLayoutSetPrototypeLinkEnabled()) && !siteAdminConfiguration.enableCustomLanguagesWithTemplatePropagation()) {
	disabledLocaleInput = true;
}
%>

<c:if test="<%= disabledLocaleInput %>">
	<p class="text-muted">
		<liferay-ui:message key="the-language-settings-cannot-be-edited-while-propagation-of-changes-from-the-site-template-is-enabled" />
	</p>
</c:if>

<aui:input checked="<%= inheritLocales %>" disabled="<%= disabledLocaleInput %>" id="<%= GroupConstants.TYPE_SETTINGS_KEY_INHERIT_LOCALES %>" label="use-the-default-language-options" name="TypeSettingsProperties--inheritLocales--" type="radio" value="<%= true %>" />

<aui:input checked="<%= !inheritLocales %>" disabled="<%= disabledLocaleInput %>" id="customLocales" label="define-a-custom-default-language-and-additional-available-languages-for-this-site" name="TypeSettingsProperties--inheritLocales--" type="radio" value="<%= false %>" />

<aui:fieldset id='<%= renderResponse.getNamespace() + "inheritLocalesFieldset" %>'>
	<aui:fieldset cssClass="default-language">
		<h4 class="text-muted"><liferay-ui:message key="default-language" /></h4>

		<%
		User user2 = company.getDefaultUser();

		Locale defaultLocale = user2.getLocale();
		%>

		<p class="text-muted">
			<%= defaultLocale.getDisplayName(locale) %>
		</p>
	</aui:fieldset>

	<aui:fieldset cssClass="available-languages">
		<h4 class="text-muted"><liferay-ui:message key="available-languages" /></h4>

		<p class="text-muted">
			<%= StringUtil.merge(LocaleUtil.toDisplayNames(LanguageUtil.getAvailableLocales(), locale), StringPool.COMMA_AND_SPACE) %>
		</p>
	</aui:fieldset>
</aui:fieldset>

<aui:fieldset id='<%= renderResponse.getNamespace() + "customLocalesFieldset" %>'>
	<liferay-ui:error exception="<%= LocaleException.class %>">

		<%
		LocaleException le = (LocaleException)errorException;
		%>

		<c:choose>
			<c:when test="<%= le.getType() == LocaleException.TYPE_DEFAULT %>">
				<liferay-ui:message key="you-cannot-remove-a-language-that-is-the-current-default-language" />
			</c:when>
			<c:when test="<%= le.getType() == LocaleException.TYPE_DISPLAY_SETTINGS %>">
				<liferay-ui:message arguments="<%= StringUtil.merge(LocaleUtil.toDisplayNames(le.getSourceAvailableLocales(), locale), StringPool.COMMA_AND_SPACE) %>" key="please-select-the-available-languages-of-the-site-among-the-available-languages-of-the-portal-x" translateArguments="<%= false %>" />
			</c:when>
		</c:choose>
	</liferay-ui:error>

	<%
	Set<Locale> siteAvailableLocales = LanguageUtil.getAvailableLocales(liveGroup.getGroupId());
	%>

	<aui:fieldset cssClass="default-language">
		<h4 class="text-default"><liferay-ui:message key="default-language" /></h4>

		<aui:select disabled="<%= disabledLocaleInput %>" label="" name="TypeSettingsProperties--languageId--" title="language">

			<%
			Locale siteDefaultLocale = PortalUtil.getSiteDefaultLocale(liveGroup.getGroupId());

			for (Locale siteAvailableLocale : siteAvailableLocales) {
			%>

				<aui:option label="<%= siteAvailableLocale.getDisplayName(locale) %>" lang="<%= LocaleUtil.toW3cLanguageId(siteAvailableLocale) %>" selected="<%= siteDefaultLocale.getLanguage().equals(siteAvailableLocale.getLanguage()) && siteDefaultLocale.getCountry().equals(siteAvailableLocale.getCountry()) %>" value="<%= LocaleUtil.toLanguageId(siteAvailableLocale) %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>

	<aui:fieldset cssClass="available-languages">
		<h4 class="text-default"><liferay-ui:message key="available-languages" /></h4>

		<aui:input name='<%= "TypeSettingsProperties--" + PropsKeys.LOCALES + "--" %>' type="hidden" value="<%= StringUtil.merge(LocaleUtil.toLanguageIds(siteAvailableLocales)) %>" />

		<%

		// Left list

		List leftList = new ArrayList();

		String groupLanguageIds = typeSettingsProperties.getProperty(PropsKeys.LOCALES);

		if (groupLanguageIds != null) {
			for (Locale currentLocale : LocaleUtil.fromLanguageIds(StringUtil.split(groupLanguageIds))) {
				leftList.add(new KeyValuePair(LanguageUtil.getLanguageId(currentLocale), currentLocale.getDisplayName(locale)));
			}
		}
		else {
			for (Locale siteAvailableLocale : siteAvailableLocales) {
				leftList.add(new KeyValuePair(LocaleUtil.toLanguageId(siteAvailableLocale), siteAvailableLocale.getDisplayName(locale)));
			}
		}

		// Right list

		List rightList = new ArrayList();

		for (Locale availableLocale : LanguageUtil.getAvailableLocales()) {
			if (!siteAvailableLocales.contains(availableLocale)) {
				rightList.add(new KeyValuePair(LocaleUtil.toLanguageId(availableLocale), availableLocale.getDisplayName(locale)));
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<liferay-ui:input-move-boxes
			leftBoxName="currentLanguageIds"
			leftList="<%= leftList %>"
			leftReorder="<%= Boolean.TRUE.toString() %>"
			leftTitle="current"
			rightBoxName="availableLanguageIds"
			rightList="<%= rightList %>"
			rightTitle="available"
		/>
	</aui:fieldset>
</aui:fieldset>

<aui:script>
	Liferay.Util.toggleRadio('<portlet:namespace />customLocales', '<portlet:namespace />customLocalesFieldset', '<portlet:namespace />inheritLocalesFieldset');
	Liferay.Util.toggleRadio('<portlet:namespace />inheritLocales', '<portlet:namespace />inheritLocalesFieldset', '<portlet:namespace />customLocalesFieldset');

	function <portlet:namespace />saveLocales() {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('<%= PropsKeys.LOCALES %>').val(Liferay.Util.listSelect(form.fm('currentLanguageIds')));
	}
</aui:script>