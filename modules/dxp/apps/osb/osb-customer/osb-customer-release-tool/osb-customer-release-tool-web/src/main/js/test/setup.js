import lang from './lang';

window.ReleaseToolConstants = {
	namespace: 'namespace'
};

window.Liferay = {
	Language: {
		get: lang
	}
};

window.AUI = () => ({
	Lang: {
		sub(key) {
			return key;
		}
	}
});