<#assign
	permission_checker = themeDisplay.getPermissionChecker()

	is_group_admin = permission_checker.isGroupAdmin(group_id)
	is_omniadmin = permission_checker.isOmniadmin()

	show_dockbar = is_group_admin || is_omniadmin
/>

<#if show_dockbar>
	<#assign
		css_class = css_class + " show-dockbar"
		wrapper_class_name = ""
	/>
<#else>
	<#assign wrapper_class_name = "hide-dockbar" />
</#if>

<#assign
	the_title = languageUtil.get(locale, "incident-report")

	company_name = languageUtil.get(locale, "watson")
/>