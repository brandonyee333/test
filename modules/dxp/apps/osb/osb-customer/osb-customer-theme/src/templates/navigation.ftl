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
				url: '/'
			}
		]
	};

	const highlightedLinks = {
		className: 'highlighted',
		configs: [
			{
				name: '<@liferay.language key="submit-a-ticket" />',
				svgId: '#ticket',
				url: 'https://help.liferay.com/hc/requests/new'
			},
			{
				name: '<@liferay.language key="contact-us" />',
				svgId: '#contact',
				url: 'https://help.liferay.com/hc/articles/360017784212'
			}
		]
	};

	const locale = {
		alternativeLocales: [
			{
				name: '',
				url: '/'
			}
		],
		currentLocale: {
			name: '${w3c_language_id}',
			url: '/'
		}
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
				configs: [
					{
						description: '<@liferay.language key="products-service-packs-fix-packs-and-security-fixes" />',
						name: '<@liferay.language key="downloads" />',
						svgId: '#downloads',
						url: 'https://customer.liferay.com/downloads'
					},
					{
						description: '<@liferay.language key="articles-on-recurring-topics-issues-and-themes" />',
						name: '<@liferay.language key="knowledge-base" />',
						svgId: '#knowledge-base',
						url: helpCenterBaseURL + 'categories/360000779952'
					},
					{
						description: '<@liferay.language key="product-guides-tutorials-and-reference-docs" />',
						name: '<@liferay.language key="documentation" />',
						svgId: '#documentation',
						url: helpCenterBaseURL + 'categories/360000868172'
					},
					{
						description: '<@liferay.language key="updates-on-products-and-system-improvements" />',
						name: '<@liferay.language key="announcements" />',
						svgId: '#announcements',
						url: helpCenterBaseURL + 'categories/360000892272'
					},
					{
						description: '<@liferay.language key="activate-your-environments-to-start-a-project" />',
						name: '<@liferay.language key="activation-keys" />',
						svgId: '#server-activation',
						url: 'https://customer.liferay.com/activation-key'
					}
				]
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
				configs: [
					{
						description: '<@liferay.language key="how-liferay-approaches-security" />',
						name: '<@liferay.language key="security-overview" />',
						svgId: '#security-overview',
						url: helpCenterBaseURL + 'articles/360016700231'
					},
					{
						description: '<@liferay.language key="information-and-fixes-for-known-vulnerabilities" />',
						name: '<@liferay.language key="security-advisories" />',
						svgId: '#security-advisories',
						url: helpCenterBaseURL + 'articles/360015772992'
					},
					{
						description: '<@liferay.language key="process-for-reporting-security-vulnerabilities" />',
						name: '<@liferay.language key="report-security-issues" />',
						svgId: '#report-security-issues',
						url: helpCenterBaseURL + 'articles/360016700231#Reporting-Security-Issues'
					},
					{
						description: '<@liferay.language key="policy-for-reported-security-issues-in-liferay-products" />',
						name: '<@liferay.language key="security-policies" />',
						svgId: '#security-policy',
						url: helpCenterBaseURL + 'articles/360016700231#Liferay-Security-Policy'
					}
				]
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
				configs: [
					{
						description: '<@liferay.language key="supported-configurations-for-liferay-products" />',
						name: '<@liferay.language key="compatibility-matrix" />',
						svgId: '#compatibility-matrix',
						url: helpCenterBaseURL + 'sections/360002103292'
					},
					{
						description: '<@liferay.language key="overview-of-liferays-support-policies" />',
						name: '<@liferay.language key="support-coverage" />',
						svgId: '#support-coverage',
						url: helpCenterBaseURL + 'articles/360018692652'
					},
					{
						description: '<@liferay.language key="end-of-life-policies-for-liferay-products" />',
						name: '<@liferay.language key="service-life" />',
						svgId: '#end-of-life',
						url: helpCenterBaseURL + 'sections/360002241991'
					},
					{
						description: '<@liferay.language key="answers-to-common-support-related-questions" />',
						name: '<@liferay.language key="support-faqs" />',
						svgId: '#support-faqs',
						url: helpCenterBaseURL + 'articles/360015994791'
					},
					{
						description: '<@liferay.language key="how-liferay-delivers-software-updates" />',
						name: '<@liferay.language key="fix-delivery-method" />',
						svgId: '#fix-delivery-method',
						url: helpCenterBaseURL + 'articles/360016515831'
					},
					{
						description: '<@liferay.language key="benefits-included-with-each-subscription-level" />',
						name: '<@liferay.language key="service-levels" />',
						svgId: '#support-overview',
						url: helpCenterBaseURL + 'articles/360016510271'
					}
				]
			},
			highlightedLinks: highlightedLinks,
			locale: locale,
			name: '<@liferay.language key="support" />'
		},
		document.getElementById('megaMenuSupport')
	);
</script>