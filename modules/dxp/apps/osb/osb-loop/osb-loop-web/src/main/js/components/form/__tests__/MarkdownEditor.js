jest.mock('../../MarkdownSupportedIcon');

import MarkdownEditor from '../MarkdownEditor';

describe(
	'MarkdownEditor',
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
				component = new MarkdownEditor({name: 'foo'});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set state value',
			() => {
				component = new MarkdownEditor(
					{
						label: 'foo',
						name: 'foo'
					}
				);

				component.state.value_ = false;

				component.handleChange_(true);

				expect(component.state.value_).toBe(true);
			}
		);

		it(
			'should sync value between initialValue and state value',
			() => {
				component = new MarkdownEditor(
					{
						initialValue: 'foo',
						name: 'foo'
					}
				);

				component.props.initialValue = 'bar';

				jest.runAllTimers();

				expect(component.state.value_).toBe('bar');
			}
		);
	}
);