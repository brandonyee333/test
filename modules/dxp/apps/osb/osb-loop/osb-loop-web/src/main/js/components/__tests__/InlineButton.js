import InlineButton from '../InlineButton';

describe(
	'InlineButton',
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
				component = new InlineButton();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render children',
			() => {
				component = new InlineButton(
					{
						children: [<div class="test" key={0}>{'test'}</div>]
					}
				);

				expect(component.element.querySelector('.test')).toBeTruthy();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should pass through other props to element',
			() => {
				component = new InlineButton(
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