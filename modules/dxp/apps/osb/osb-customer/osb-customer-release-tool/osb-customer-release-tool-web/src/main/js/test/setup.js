import lang from './lang';

window.Liferay = {
	Language: {
		get: lang
	}
};

window.ReleaseToolConstants = {
	namespace: 'namespace'
};

window.scroll = () => console.log('scroll');

HTMLFormElement.prototype.submit = () => console.log('submit');