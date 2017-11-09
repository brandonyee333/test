<!DOCTYPE html>

<#include init />

<html class="<@liferay.language key="lang.dir" /> ${locale} legacy responsive" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="utf-8" />
	<meta content="minimum-scale=1.0, width=device-width" name="viewport" />

	<#if page_description?has_content>
		<meta content="${page_description}" property="og:description" />
	</#if>

	<title>${the_title} | Liferay</title>

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<a class="hide-accessible" href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

<@liferay_util["include"] page=body_top_include />

<#if can_view_control_panel>
	<@liferay.control_menu />
</#if>

<div id="wrapper">
	<div class="content">
		<#if display_header_footer>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>

		<div id="contentWrapper">
			<section id="mainContent">
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}

					${portletDisplay.setTitle(the_title)}

					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>

				<#if display_header_footer>
					<footer class="doc-footer">
						<p class="copyright">
							&copy; ${the_year} Liferay Inc. <@liferay.language key="all-rights-reserved" />
						</p>
					</footer>
				</#if>
			</section>
		</div>
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>