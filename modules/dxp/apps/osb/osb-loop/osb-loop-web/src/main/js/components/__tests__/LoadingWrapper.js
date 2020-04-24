import LoadingWrapper from '../LoadingWrapper';

describe(
	'LoadingWrapper',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new LoadingWrapper();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders spinner when loading is true',
			() => {
				component = new LoadingWrapper();

				expect(component.element.querySelector('.spinner-container')).toBeTruthy();

				component.props.loading = false;

				jest.runAllTimers();

				expect(component.element.querySelector('.spinner-container')).toBeFalsy();
			}
		);

		it(
			'renders content when loading is false',
			() => {
				component = new LoadingWrapper();

				expect(component.element.querySelector('.loading-content')).toBeFalsy();

				component.props.loading = false;

				jest.runAllTimers();

				expect(component.element.querySelector('.loading-content')).toBeTruthy();
			}
		);

		it(
			'renders content when loading is true or false when mask is set to true',
			() => {
				component = new LoadingWrapper(
					{
						mask: true
					}
				);

				expect(component.element.querySelector('.loading-content')).toBeTruthy();

				component.props.loading = false;

				jest.runAllTimers();

				expect(component.element.querySelector('.loading-content')).toBeTruthy();
			}
		);

		it(
			'renders mask when loading is false and mask is set to true',
			() => {
				component = new LoadingWrapper(
					{
						mask: true
					}
				);

				expect(component.element.querySelector('.mask')).toBeTruthy();

				component.props.loading = false;

				jest.runAllTimers();

				expect(component.element.querySelector('.mask')).toBeFalsy();
			}
		);
	}
);