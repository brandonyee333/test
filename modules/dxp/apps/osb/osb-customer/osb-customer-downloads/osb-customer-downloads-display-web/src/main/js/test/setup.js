import lang from './lang';

window.DownloadsConstants = {
	namespace: 'namespace'
};

window.Liferay = {
	Language: {
		get: lang
	}
}

// Mock console.error to suppress jsdom console warning instead of setting omitJSDOMErrors to true

beforeEach(() => {
	jest.spyOn(console, 'error');

	console.error.mockImplementation(() => {});
});

afterEach(() => {
	console.error.mockRestore();
});