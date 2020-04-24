jest.unmock('../../pages/ErrorPage');

import ErrorWrapper from '../ErrorWrapper';

describe(
	'ErrorWrapper',
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
				component = new ErrorWrapper();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render error page',
			() => {
				component = new ErrorWrapper(
					{
						children: '<div>tests</div>',
						error: true,
						loading: false
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render children',
			() => {
				component = new ErrorWrapper(
					{
						error: true,
						loading: false
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);