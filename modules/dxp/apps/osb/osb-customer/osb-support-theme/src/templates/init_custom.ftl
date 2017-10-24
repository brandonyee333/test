<#assign
	portal_permission_util = serviceLocator.findService("com.liferay.portal.kernel.service.permission.PortalPermission")

	display_header_footer = true

	content_only_urls = stringUtil.split(theme.getSetting("content-only-urls"))

	current_url = request.getAttribute("CURRENT_COMPLETE_URL")
	struts_action = httpUtil.getParameter(current_url, "_58_struts_action", false)
/>

<#if arrayUtil.contains(content_only_urls, httpUtil.getPath(current_url)) || (struts_action == "%2Flogin%2Flogin")>
	<#assign display_header_footer = false />
</#if>

<#assign can_view_control_panel = false />

<#if is_signed_in && portal_permission_util.contains(theme_display.getPermissionChecker(), "VIEW_CONTROL_PANEL")>
	<#assign can_view_control_panel = true />
<#else>
	<#assign
		css_class = stringUtil.replace(css_class, "has-control-menu", "")
		css_class = stringUtil.replace(css_class, "open", "")
	/>
</#if>

<#if !show_add_content??>
	<#assign css_class = stringUtil.replace(css_class, "controls-visible", "controls-hidden") />
</#if>