import NoResultsDisplay from '../NoResultsDisplay';

describe(
	'NoResultsDisplay',
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
				component = new NoResultsDisplay();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'title container to only be rendered when title is passed in',
			() => {
				const message = 'No results display message.';

				component = new NoResultsDisplay({message});

				expect(component.element.querySelector('.title')).toBeFalsy();

				const title = 'No Results Title';

				component.props.title = title;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'message to be rendered correctly',
			() => {
				const message = 'No results display message.';

				component = new NoResultsDisplay({message});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render in size small',
			() => {
				component = new NoResultsDisplay({size: 'small'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render in size large',
			() => {
				component = new NoResultsDisplay();

				expect(component).toMatchSnapshot();
			}
		);
	}
);