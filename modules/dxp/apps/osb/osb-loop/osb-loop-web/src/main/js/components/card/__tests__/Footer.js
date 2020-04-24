import Footer from '../Footer';

describe(
	'Footer',
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
				component = new Footer();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders children',
			() => {
				component = new Footer(
					{
						children: [<span class="child-element" key="1" />]
					}
				);

				expect(component.element.querySelector('.child-element')).toBeTruthy();
			}
		);
	}
);