import MarkdownContentCard from '../MarkdownContentCard';

describe(
	'MarkdownContentCard',
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
				component = new MarkdownContentCard(
					{
						content: 'test'
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set expanded_ as true',
			() => {
				component = new MarkdownContentCard(
					{
						content: 'test'
					}
				);

				component.expandContent_();

				expect(component.state.expanded_).toBe(true);
			}
		);

		it(
			'should render truncated content',
			() => {
				component = new MarkdownContentCard(
					{
						content: 'foobar',
						iconName: 'test',
						truncatedContent: 'baz'
					}
				);

				component.expandContent_();

				jest.runAllTimers();

				expect(component.element.innerHTML).toContain('foobar');
			}
		);
	}
);