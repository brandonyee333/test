<div class="container-fluid container-fluid-max-xl">
	<div class="header-inner row">
		<div class="col-md-3 header-content">
			<a class="header-link" href="${site_logo_url}" title="Home">
				<svg alt="<@liferay.language key="liferay-help-center" />" class="header-logo">
					<use xlink:href="#liferay-waffle" />
				</svg>
			</a>

			<a href="${site_name_url}" title="${site_name}">
				<h2 class="site-name">
					${site_name}
				</h2>
			</a>
		</div>

		<div class="col-md-5 header-content header-menu-list secondary-text-color">
			<div class="list-item">
				<h5>
					<@liferay.language key="products" />
				</h5>

				<div class="header-menu" id="megaMenuProducts"></div>
			</div>

			<div class="list-item">
				<h5>
					<@liferay.language key="resources" />
				</h5>

				<div class="header-menu" id="megaMenuResources"></div>
			</div>

			<div class="list-item">
				<h5>
					<@liferay.language key="security" />
				</h5>

				<div class="header-menu" id="megaMenuSecurity"></div>
			</div>

			<div class="list-item">
				<h5>
					<@liferay.language key="support" />
				</h5>

				<div class="header-menu" id="megaMenuSupport"></div>
			</div>
		</div>

		<div class="col-md-4 header-content user-wrapper">
			<div class="user">
				<#if is_signed_in>
					<@liferay.user_personal_bar />
				<#else>
					<a class="btn btn-primary" href="${sign_in_url}">${sign_in_text}</a>
				</#if>
			</div>
		</div>
	</div>
</div>

<script>
	const helpCenterBaseURL = 'https://help.liferay.com/hc/';

	const accountLinks = {
		className: 'account-links',
		configs: [
			{
				name: '<@liferay.language key="my-requests" />',
				url: helpCenterBaseURL + 'requests'
			},
			{
				name: '<@liferay.language key="project-details" />',
				url: ''
			}
		]
	};

	const highlightedLinks = {
		className: 'highlighted',
		configs: [
			{
				name: '<@liferay.language key="contact-us" />',
				svgId: '#contact',
				url: ''
			}
		]
	};

	const locale = {
		currentLocale: {
			name: '${w3c_language_id}',
			url: '/'
		},
		alternativeLocales: [
			{
				name: '',
				url: '/'
			}
		]
	};

	theme.render(
		theme.MegaMenu,
		{
			accountLinks: accountLinks,
			cardMenuItems: {
				className: 'card-menu',
				configs: [
					{
						name: '<@liferay.language key="dxp" /> 7.1',
						svgId: '#dxp-logo',
						url: helpCenterBaseURL + 'categories/360000872531'
					},
					{
						name: '<@liferay.language key="dxp-cloud" />',
						svgId: '#dxp-cloud-logo',
						url: helpCenterBaseURL + 'categories/360000813091'
					},
					{
						name: '<@liferay.language key="dxp" /> 7.0',
						svgId: '#7-0-logo',
						url: helpCenterBaseURL + 'categories/360000867932'
					},
					{
						name: '<@liferay.language key="commerce" />',
						svgId: '#commerce-logo',
						url: helpCenterBaseURL + 'categories/360000867952'
					},
					{
						name: '<@liferay.language key="analytics-cloud" />',
						svgId: '#analytics-cloud-logo',
						url: helpCenterBaseURL + 'categories/360000872551'
					},
					{
						name: '<@liferay.language key="portal" /> 6.2',
						svgId: '#6-2-logo',
						url: helpCenterBaseURL + 'categories/360000867972'
					},
					{
						name: '<@liferay.language key="portal" /> 6.1',
						svgId: '#6-1-logo',
						url: helpCenterBaseURL + 'categories/360000872571'
					},
					{
						name: '<@liferay.language key="portal" /> 6.0',
						svgId: '#6-0-logo',
						url: helpCenterBaseURL + 'categories/360000867992'
					}
				]
			},
			highlightedLinks: highlightedLinks,
			locale: locale,
			name: '<@liferay.language key="products" />'
		},
		document.getElementById('megaMenuProducts')
	);

	theme.render(
		theme.MegaMenu,
		{
			accountLinks: accountLinks,
			cardMenuItems: {
				className: 'card-menu',
				configs: []
			},
			highlightedLinks: highlightedLinks,
			locale: locale,
			name: '<@liferay.language key="resources" />'
		},
		document.getElementById('megaMenuResources')
	);

	theme.render(
		theme.MegaMenu,
		{
			accountLinks: accountLinks,
			cardMenuItems: {
				className: 'card-menu',
				configs: []
			},
			highlightedLinks: highlightedLinks,
			locale: locale,
			name: '<@liferay.language key="security" />'
		},
		document.getElementById('megaMenuSecurity')
	);

	theme.render(
		theme.MegaMenu,
		{
			accountLinks: accountLinks,
			cardMenuItems: {
				className: 'card-menu',
				configs: []
			},
			highlightedLinks: highlightedLinks,
			locale: locale,
			name: '<@liferay.language key="support" />'
		},
		document.getElementById('megaMenuSupport')
	);
</script>