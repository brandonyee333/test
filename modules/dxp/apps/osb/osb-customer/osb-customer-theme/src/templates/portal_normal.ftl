<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<#if validator.isNotNull(google_tag_manager_id)>
	<noscript>
		<iframe height="0" src="//www.googletagmanager.com/ns.html?id=$google_tag_manager_id" style="display: none; visibility: hidden;" width="0"></iframe>
	</noscript>
</#if>

<@liferay_util["include"] page=body_top_include />

<#if has_view_control_panel>
	<@liferay.control_menu />
</#if>

<div id="wrapper">
	<#include "${full_templates_path}/icons.ftl" parse=false />

	<header class="header ${has_view_control_panel?string('has-control-panel', '')}" id="banner">
		<#include "${full_templates_path}/navigation.ftl" />
	</header>

	<main role="main">
		<div class="container-fluid container-fluid-max-xl homepage main-content" id="content">
			<@liferay_util["include"] page=content_include />
		</div>
	</main>

	<footer class="container-fluid container-fluid-max-xl footer secondary-font">
		<#include "${full_templates_path}/footer.ftl" />
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<#if has_mega_menu>
	<#include "${full_templates_path}/mega_menu.ftl" />
</#if>
</body>

</html>