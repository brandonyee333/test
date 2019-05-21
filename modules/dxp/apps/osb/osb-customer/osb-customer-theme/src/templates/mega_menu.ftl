<script>
	var menuItems = document.querySelectorAll('.header-menu-list .list-item');

	Array.prototype.forEach.call(
		menuItems,
		function(item) {
			item.classList.remove('hide');
		}
	);

	function addLocaleParamToURI(locale, urn) {
		return '${zendesk_url}/hc/${zendesk_locale_util.convertToZendeskLocale(locale)}/' + urn;
	}

	var accountLinks = {
		className: 'account-links',
		configs: [
			{
				name: '<@liferay.language key="my-requests" />',
				url: addLocaleParamToURI('${current_locale}', 'requests')
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
				url: addLocaleParamToURI('${current_locale}', 'requests/new')
			},
			{
				name: '<@liferay.language key="contact-us" />',
				svgId: '#contact',
				url: addLocaleParamToURI('${current_locale}', 'articles/360017784212')
			}
		]
	};

	var locale = {
		alternativeLocales: [
			<#list available_locales as locale>
				<#if locale != current_locale>
					{
						name: '${locale.getDisplayLanguage(locale)?cap_first}',
						url: '/${locale.getLanguage()}${theme_display.getURLCurrent()?remove_beginning(locale_path)}'
					},
				</#if>
			</#list>
		],
		currentLocale: {
			name: '${current_locale.getDisplayLanguage(current_locale)?cap_first}',
			url: '/${current_locale.getLanguage()}${theme_display.getURLCurrent()?remove_beginning(locale_path)}'
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
						url: addLocaleParamToURI('${current_locale}', 'categories/360000872531')
					},
					{
						name: '<@liferay.language key="dxp-cloud" />',
						svgId: '#dxp-cloud-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000868032')
					},
					{
						name: '<@liferay.language key="dxp" /> 7.0',
						svgId: '#7-0-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000867932')
					},
					{
						name: '<@liferay.language key="commerce" />',
						svgId: '#commerce-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000867952')
					},
					{
						name: '<@liferay.language key="analytics-cloud" />',
						svgId: '#analytics-cloud-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000872551')
					},
					{
						name: '<@liferay.language key="portal" /> 6.2',
						svgId: '#6-2-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000867972')
					},
					{
						name: '<@liferay.language key="portal" /> 6.1',
						svgId: '#6-1-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000872571')
					},
					{
						name: '<@liferay.language key="portal" /> 6.0',
						svgId: '#6-0-logo',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000867992')
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
						url: addLocaleParamToURI('${current_locale}', 'categories/360000779952')
					},
					{
						description: '<@liferay.language key="product-guides-tutorials-and-reference-docs" />',
						name: '<@liferay.language key="documentation" />',
						svgId: '#documentation',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000868172')
					},
					{
						description: '<@liferay.language key="reference-the-latest-changes-for-liferay-dxp" />',
						name: '<@liferay.language key="dxp-release-notes" />',
						svgId: '#fixpack',
						url: 'https://customer.liferay.com/dxp-release-notes'
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
						url: addLocaleParamToURI('${current_locale}', 'articles/360016700231')
					},
					{
						description: '<@liferay.language key="information-and-fixes-for-known-vulnerabilities" />',
						name: '<@liferay.language key="security-advisories" />',
						svgId: '#security-advisories',
						url: addLocaleParamToURI('${current_locale}', 'articles/360018875952')
					},
					{
						description: '<@liferay.language key="process-for-reporting-security-vulnerabilities" />',
						name: '<@liferay.language key="report-security-issues" />',
						svgId: '#report-security-issues',
						url: addLocaleParamToURI('${current_locale}', 'articles/360016700231#Reporting-Security-Issues')
					},
					{
						description: '<@liferay.language key="policy-for-reported-security-issues-in-liferay-products" />',
						name: '<@liferay.language key="security-policies" />',
						svgId: '#security-policy',
						url: addLocaleParamToURI('${current_locale}', 'articles/360016700231#Liferay-Security-Policy')
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
						url: addLocaleParamToURI('${current_locale}', 'sections/360002103292')
					},
					{
						description: '<@liferay.language key="overview-of-liferays-support-policies" />',
						name: '<@liferay.language key="support-coverage" />',
						svgId: '#support-coverage',
						url: addLocaleParamToURI('${current_locale}', 'categories/360000892912')
					},
					{
						description: '<@liferay.language key="end-of-life-policies-for-liferay-products" />',
						name: '<@liferay.language key="service-life" />',
						svgId: '#service-life',
						url: addLocaleParamToURI('${current_locale}', 'sections/360002241991')
					},
					{
						description: '<@liferay.language key="answers-to-common-support-related-questions" />',
						name: '<@liferay.language key="support-faqs" />',
						svgId: '#support-faqs',
						url: addLocaleParamToURI('${current_locale}', 'articles/360015994791')
					},
					{
						description: '<@liferay.language key="how-liferay-delivers-software-updates" />',
						name: '<@liferay.language key="fix-delivery-method" />',
						svgId: '#fix-delivery-method',
						url: addLocaleParamToURI('${current_locale}', 'articles/360016515831')
					},
					{
						description: '<@liferay.language key="benefits-included-with-each-subscription-level" />',
						name: '<@liferay.language key="service-levels" />',
						svgId: '#service-levels',
						url: addLocaleParamToURI('${current_locale}', 'articles/360016510271')
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