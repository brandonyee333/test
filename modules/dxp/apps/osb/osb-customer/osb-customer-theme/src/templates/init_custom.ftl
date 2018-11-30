<#assign
	layout_local_service = serviceLocator.findService("com.liferay.portal.kernel.service.LayoutLocalService")
	portal_permission = serviceLocator.findService("com.liferay.portal.kernel.service.permission.PortalPermission")
	role_local_service = serviceLocator.findService("com.liferay.portal.kernel.service.RoleLocalService")

	theme_display_permission_checker = theme_display.getPermissionChecker()

	has_mega_menu = false
	has_view_control_panel = is_signed_in && portal_permission.contains(theme_display_permission_checker, "VIEW_CONTROL_PANEL")

	available_locales = languageUtil.getAvailableLocales()
	current_locale = themeDisplay.getLocale()
	locale_path = theme_display.getI18nPath()!""

	user_avatar = themeDisplay.getUser().getPortraitURL(themeDisplay)
	user_initials = themeDisplay.getUser().getInitials()

	osb_account_admin = role_local_service.hasUserRoles(user_id, company_id, ['OSB Account Admin'], true)
	osb_administrator = role_local_service.hasUserRoles(user_id, company_id, ['OSB Administrator'], true)
	osb_support_admin = role_local_service.hasUserRoles(user_id, company_id, ['OSB Support Admin'], true)
	liferay_employee = role_local_service.hasUserRoles(user_id, company_id, ['Liferay Employee'], true)

	site_logo_url = themeDisplay.getThemeSetting("site-logo-url")
	site_name = themeDisplay.getThemeSetting("site-name")
	site_name_url = themeDisplay.getThemeSetting("site-name-url")
	social_link_one_name = themeDisplay.getThemeSetting("social-link-one-name")
	social_link_one_url = themeDisplay.getThemeSetting("social-link-one-url")
	social_link_three_name = themeDisplay.getThemeSetting("social-link-three-name")
	social_link_three_url = themeDisplay.getThemeSetting("social-link-three-url")
	social_link_two_name = themeDisplay.getThemeSetting("social-link-two-name")
	social_link_two_url = themeDisplay.getThemeSetting("social-link-two-url")
	zendesk_url = themeDisplay.getThemeSetting("zendesk-url")
/>

<#if is_signed_in && serviceLocator.findService("com.liferay.osb.customer.help.center.web.util.HelpCenterThemeUtil")?? && serviceLocator.findService("com.liferay.osb.customer.help.center.web.util.HelpCenterThemeUtil").hasMegaMenu(user.getUserId())>
	<#assign has_mega_menu = true />
</#if>

<#function convert_to_zendesk_locale locale>
	<#local zendesk_locale = "" />

	<#if stringUtil.equals(locale, "en_US")>
		<#local zendesk_locale = "en-US" />
	<#elseif stringUtil.equals(locale, "es_ES")>
		<#local zendesk_locale = "es" />
	<#elseif stringUtil.equals(locale, "ja_JP")>
		<#local zendesk_locale = "ja" />
	<#elseif stringUtil.equals(locale, "pt_BR")>
		<#local zendesk_locale = "pt" />
	<#elseif stringUtil.equals(locale, "zh_CN")>
		<#local zendesk_locale = "zh-CN" />
	</#if>

	<#return zendesk_locale>
</#function>