import Card from '../index';

describe(
	'Card',
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
				component = new Card();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders children',
			() => {
				component = new Card(
					{
						children: [<span class="child-element" key="1" />]
					}
				);

				expect(component.element.querySelector('.child-element')).toBeTruthy();
			}
		);
	}
);