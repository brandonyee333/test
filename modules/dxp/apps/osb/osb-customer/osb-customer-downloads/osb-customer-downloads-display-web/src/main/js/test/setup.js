import lang from './lang';

window.DownloadsConstants = {
	namespace: 'namespace'
};

window.Liferay = {
	Language: {
		get: lang
	}
}

beforeEach(() => {
	jest.spyOn(console, 'error');

	console.error.mockImplementation(() => {});
});

afterEach(() => {
	console.error.mockRestore();
});