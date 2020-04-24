import Header from '../Header';

describe(
	'Header',
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
				component = new Header();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders children',
			() => {
				component = new Header(
					{
						children: [<span class="child-element" key="1" />]
					}
				);

				expect(component.element.querySelector('.child-element')).toBeTruthy();
			}
		);
	}
);