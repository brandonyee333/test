<#assign
	google_tag_manager_id = themeDisplay.getThemeSetting("google-tag-manager-id")
/>

<#if show_sign_in>
	<#assign
		sign_in_text = languageUtil.get(locale, "Log in")
	/>
</#if>