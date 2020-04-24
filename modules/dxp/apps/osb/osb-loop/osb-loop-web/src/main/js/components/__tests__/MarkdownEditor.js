jest.mock('../MarkdownSupportedIcon');

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
				component = new MarkdownEditor(
					{
						onChange: jest.fn(),
						value: ''
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set node height to its scrollHeight and call onChange prop',
			() => {
				const spy = jest.fn();

				component = new MarkdownEditor(
					{
						onChange: spy,
						value: ''
					}
				);

				component.refs.textarea = {
					scrollHeight: 400,
					style: {height: 300}
				};

				component.handleChange_();

				expect(component.refs.textarea.style.height).toBe('400px');
				expect(spy).toBeCalled();
			}
		);

		it(
			'should set preview_ to false',
			() => {
				component = new MarkdownEditor(
					{
						onChange: jest.fn(),
						value: ''
					}
				);

				component.state.preview_ = true;

				component.handleEditClick_();

				expect(component.state.preview_).toBe(false);
			}
		);
	}
);