<!DOCTYPE html>

<#include init />

<html class="aui ${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<meta charset="utf-8" />
	<meta content="minimum-scale=1.0, width=device-width" name="viewport" />
	<meta content="liferay" property="fb:admins" />
	<meta content="${page.getDescription(locale)}" property="og:description" />
	<meta content="${open_graph_image}" property="og:image" />
	<meta content="${open_graph_title}" property="og:title" />
	<meta content="website" property="og:type" />
	<meta content="${open_graph_url}" property="og:url" />
	<title>${the_title} | "<@liferay.language key="liferay" /></title>

	<@liferay_util["include"] page=top_head_include />

	<#if theme_settings["google-analytics-id"] != "">
		<script>
			(function(i, s, o, g, r, a, m) {
				i['GoogleAnalyticsObject'] = r;

				i[r] = i[r] || function() {
					(i[r].q = i[r].q || []).push(arguments);
				};

				i[r].l = 1 * new Date();

				a = s.createElement(o);
				m = s.getElementsByTagName(o)[0];

				a.async = 1;
				a.src = g;
				m.parentNode.insertBefore(a, m);
			})(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

			ga('create', '${theme_settings["google-analytics-id"]}', 'auto');
			ga('send', 'pageview');
		</script>
	</#if>
</head>

<body class="${css_class}">

<a class="hide-accessible" href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

<@liferay_util["include"] page=body_top_include />

<#if can_view_control_panel>
	<@liferay.control_menu />
</#if>

<div class="container-fluid" id="wrapper">
	<div class="row-fluid">
		<header class="banner col-md-12" id="banner" role="banner">
			<div class="banner-content">
				<#if getterUtil.getBoolean(theme_settings["custom-site-nav-logo"]) && (theme_settings["custom-site-url"] != "")>
					<div class="${logo_css_class} custom-site-nav">
						<a class="home" href='${theme_settings["liferay-home-url"]}'>
							<#include "${full_templates_path}/svg.ftl" />
						</a>

						<#if theme_settings["custom-site-lang-key"] != "">
							<a class="custom-site-url" href='${theme_settings["custom-site-url"]}'>
								<svg class="nav-separator" preserveAspectRatio="none" viewBox="0 0 2 4">
									<path class="nav-arrow" d="M0 0 L2 2 L0 4" stroke-linejoin="round" vector-effect="non-scaling-stroke" />
								</svg>

								<span class="custom-site-text">
									<@liferay.language key=theme_settings["custom-site-lang-key"] />
								</span>
							</a>
						</#if>
					</div>
				<#else>
					<a class="${logo_css_class}" href='${theme_settings["liferay-home-url"]}'>
						<img alt="Liferay" src="${images_folder}/custom/heading.png" />
					</a>
				</#if>

				<#if has_navigation>
					<#include "${full_templates_path}/navigation.ftl" />
				</#if>
			</div>
		</header>
	</div>

	<div class="row-fluid">
		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</div>

	<div class="row-fluid">
		<footer class="footer col-md-12">
			<div class="row-fluid">
				<div class="social-nav col-md-12">
					<ul class="nav-menu">
						<li class="nav-item parent-item root-item">
							<a href="http://www.facebook.com/pages/Liferay/45119213107" target="_blank">
								<span class="facebook social-img"></span>

								<span class="responsive-hidden"><@liferay.language key="facebook" /></span>
							</a>
						</li>
						<li class="nav-item parent-item root-item">
							<a href="http://www.linkedin.com/company/83609?trk=NUS_CMPY_TWIT" target="_blank">
								<span class="linkedin social-img"></span>

								<span class="responsive-hidden">LinkedIn</span>
							</a>
						</li>
						<li class="nav-item parent-item root-item">
							<a href="http://www.twitter.com/liferay" target="_blank">
								<span class="social-img twitter"></span>

								<span class="responsive-hidden"><@liferay.language key="twitter" /></span>
							</a>
						</li>
					</ul>
				</div>
			</div>

			<div class="responsive-only row-fluid">
				<nav class="${nav_css_class} footer-navigation col-md-12">
					<@print_navigation layout_friendly_url=mobile_footer_nav_friendly_url />
				</nav>
			</div>

			<div class="responsive-hidden row-fluid">
				<nav class="${nav_css_class} footer-navigation col-md-12">
					<@print_navigation layout_friendly_url=footer_nav_friendly_url />
				</nav>
			</div>

			<div class="responsive-hidden row-fluid">
				<nav class="${nav_css_class} social-media-panel">
					<div class="facebook-buttons">
						<script type="text/javascript">
							(function(script, id) {
								function fbLazyLoad() {
									var firstScript = document.getElementsByTagName(script)[0];
									var newScript = document.createElement(script);

									if (!document.getElementById(id)) {
										newScript.id = id;

										newScript.src = '//connect.facebook.net/en_US/all.js#xfbml=1';

										firstScript.parentNode.insertBefore(newScript, firstScript);
									}
								}

								AUI.Env.add(window, 'load', fbLazyLoad, false);
							}('script', 'facebook-jssdk'));
						</script>

						<div class="fb-like" data-action="like" data-href="https://www.facebook.com/liferay" data-layout="button_count" data-show-faces="true"></div>
					</div>
				</nav>
			</div>

			<div class="row-fluid">
				<div class="copyright col-md-12">
					&copy; ${the_year} Liferay Inc. All Rights Reserved
				</div>
			</div>
		</footer>
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>