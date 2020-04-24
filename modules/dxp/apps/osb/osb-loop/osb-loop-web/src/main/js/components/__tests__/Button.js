import Button from '../Button';

describe(
	'Button',
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
			'should render',
			() => {
				component = new Button();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render children',
			() => {
				component = new Button(
					{
						children: [<div class="test" key={0}>{'test'}</div>]
					}
				);

				expect(component.element.querySelector('.test')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render active class',
			() => {
				component = new Button(
					{
						active: true
					}
				);

				expect(component.element.classList.contains('active')).toBe(true);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should pass through other props to element',
			() => {
				component = new Button(
					{
						title: 'test'
					}
				);

				expect(component.element.title).toBe('test');

				expect(component).toMatchSnapshot();
			}
		);
	}
);