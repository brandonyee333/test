import Spinner from '../Spinner';

describe(
	'Spinner',
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
				component = new Spinner();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render with the class "small" when "small" is passed as a size',
			() => {
				component = new Spinner({size: 'small'});

				const spinner = component.element.querySelector('.spinner');

				expect(spinner.classList.contains('small')).toBe(true);
			}
		);
	}
);