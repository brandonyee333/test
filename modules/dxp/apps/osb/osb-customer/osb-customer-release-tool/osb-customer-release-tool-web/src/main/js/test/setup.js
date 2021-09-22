import lang from './lang';

window.Liferay = {
	Language: {
		get: lang
	}
};

window.ReleaseToolConstants = {
	availableProduct: {
		headerText: 'Liferay Commerce Release Notes',
		svgId: '#commerce',
		url: '/commerce-release-notes'
	},
	currentProduct: {
		headerText: 'Liferay DXP Release Notes',
		svgId: '#dxp',
		url: '/dxp-release-notes'
	},
	namespace: 'namespace'
};

window.scroll = () => console.log('scroll');

HTMLFormElement.prototype.submit = () => console.log('submit');
