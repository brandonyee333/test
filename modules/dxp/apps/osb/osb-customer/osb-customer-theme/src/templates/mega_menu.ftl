<script>
	var menuItems = document.querySelectorAll('.header-menu-list .list-item');

	Array.prototype.forEach.call(
		menuItems,
		function(item) {
			item.classList.remove('hide');
		}
	);

	var accountLinks = {
		className: 'account-links',
		configs: [
			{
				name: '<@liferay.language key="my-requests" />',
				url: '${zendesk_url}/hc/requests'
			},
			{
				name: '<@liferay.language key="project-details" />',
				url: '/project-details'
			}
		]
	};

	var highlightedLinks = {
		className: 'highlighted',
		configs: [
			{
				name: '<@liferay.language key="submit-a-ticket" />',
				svgId: '#ticket',
				url: '${zendesk_url}/hc/requests/new'
			},
			{
				name: '<@liferay.language key="contact-us" />',
				svgId: '#contact',
				url: '${zendesk_url}/hc/articles/360017784212'
			}
		]
	};

	var locale = {
		alternativeLocales: [
			<#list available_locales as locale>
				<#if locale != current_locale>
					{
						name: '${locale.getDisplayLanguage(locale)}',
						url: '/${locale.getLanguage()}${theme_display.getURLCurrent()}'
					},
				</#if>
			</#list>
		],
		currentLocale: {
			name: '${current_locale.getDisplayLanguage(current_locale)}',
			url: '/${current_locale.getLanguage()}${theme_display.getURLCurrent()}'
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
						url: '${zendesk_url}/hc/categories/360000872531'
					},
					{
						name: '<@liferay.language key="dxp-cloud" />',
						svgId: '#dxp-cloud-logo',
						url: '${zendesk_url}/hc/categories/360000813091'
					},
					{
						name: '<@liferay.language key="dxp" /> 7.0',
						svgId: '#7-0-logo',
						url: '${zendesk_url}/hc/categories/360000867932'
					},
					{
						name: '<@liferay.language key="commerce" />',
						svgId: '#commerce-logo',
						url: '${zendesk_url}/hc/categories/360000867952'
					},
					{
						name: '<@liferay.language key="analytics-cloud" />',
						svgId: '#analytics-cloud-logo',
						url: '${zendesk_url}/hc/categories/360000872551'
					},
					{
						name: '<@liferay.language key="portal" /> 6.2',
						svgId: '#6-2-logo',
						url: '${zendesk_url}/hc/categories/360000867972'
					},
					{
						name: '<@liferay.language key="portal" /> 6.1',
						svgId: '#6-1-logo',
						url: '${zendesk_url}/hc/categories/360000872571'
					},
					{
						name: '<@liferay.language key="portal" /> 6.0',
						svgId: '#6-0-logo',
						url: '${zendesk_url}/hc/categories/360000867992'
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
						url: '${zendesk_url}/hc/categories/360000779952'
					},
					{
						description: '<@liferay.language key="product-guides-tutorials-and-reference-docs" />',
						name: '<@liferay.language key="documentation" />',
						svgId: '#documentation',
						url: '${zendesk_url}/hc/categories/360000868172'
					},
					{
						description: '<@liferay.language key="updates-on-products-and-system-improvements" />',
						name: '<@liferay.language key="announcements" />',
						svgId: '#announcements',
						url: '${zendesk_url}/hc/categories/360000892272'
					},
					{
						description: '<@liferay.language key="learn-more-about-designated-contacts-for-your-project" />',
						name: '<@liferay.language key="account-support" />',
						svgId: '#account-support',
						url: '${zendesk_url}/hc/articles/360018414031'
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
						url: '${zendesk_url}/hc/articles/360016700231'
					},
					{
						description: '<@liferay.language key="information-and-fixes-for-known-vulnerabilities" />',
						name: '<@liferay.language key="security-advisories" />',
						svgId: '#security-advisories',
						url: '${zendesk_url}/hc/articles/360015772992'
					},
					{
						description: '<@liferay.language key="process-for-reporting-security-vulnerabilities" />',
						name: '<@liferay.language key="report-security-issues" />',
						svgId: '#report-security-issues',
						url: '${zendesk_url}/hc/articles/360016700231#Reporting-Security-Issues'
					},
					{
						description: '<@liferay.language key="policy-for-reported-security-issues-in-liferay-products" />',
						name: '<@liferay.language key="security-policies" />',
						svgId: '#security-policy',
						url: '${zendesk_url}/hc/articles/360016700231#Liferay-Security-Policy'
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
						url: '${zendesk_url}/hc/sections/360002103292'
					},
					{
						description: '<@liferay.language key="overview-of-liferays-support-policies" />',
						name: '<@liferay.language key="support-coverage" />',
						svgId: '#support-coverage',
						url: '${zendesk_url}/hc/articles/360018692652'
					},
					{
						description: '<@liferay.language key="end-of-life-policies-for-liferay-products" />',
						name: '<@liferay.language key="service-life" />',
						svgId: '#service-life',
						url: '${zendesk_url}/hc/sections/360002241991'
					},
					{
						description: '<@liferay.language key="answers-to-common-support-related-questions" />',
						name: '<@liferay.language key="support-faqs" />',
						svgId: '#support-faqs',
						url: '${zendesk_url}/hc/articles/360015994791'
					},
					{
						description: '<@liferay.language key="how-liferay-delivers-software-updates" />',
						name: '<@liferay.language key="fix-delivery-method" />',
						svgId: '#fix-delivery-method',
						url: '${zendesk_url}/hc/articles/360016515831'
					},
					{
						description: '<@liferay.language key="benefits-included-with-each-subscription-level" />',
						name: '<@liferay.language key="service-levels" />',
						svgId: '#service-levels',
						url: '${zendesk_url}/hc/articles/360016510271'
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