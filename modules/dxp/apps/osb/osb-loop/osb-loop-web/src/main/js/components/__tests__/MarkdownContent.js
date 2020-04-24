import MarkdownContent from '../MarkdownContent';

describe(
	'MarkdownContent',
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
				component = new MarkdownContent();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should not escape html content',
			() => {
				component = new MarkdownContent(
					{
						message: '<h1 class="my-header">Hello World</h1>'
					}
				);

				expect(component.element.querySelector('.my-header')).toBeTruthy();
			}
		);
	}
);