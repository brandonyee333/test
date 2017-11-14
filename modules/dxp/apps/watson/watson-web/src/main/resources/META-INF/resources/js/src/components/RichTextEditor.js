import {bindAll} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import ReactQuill from 'react-quill';

const MetalQuill = bridge(ReactQuill);

class RichTextEditor extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleOnChange'
		);
	}

	handleOnChange(newValue, delta, source) {
		const {disabled, inputId, onChange} = this.props;

		const emptyEditorValue = newValue === '<p><br></p>';

		if (!disabled && onChange && !emptyEditorValue && source === 'user') {
			onChange(newValue, inputId);
		}
	}

	render() {
		const {autoFocus = false, disabled, value} = this.props;

		const {formats, modules} = this.state;

		return (
			<div autoFocus={autoFocus} class="rich-editor-wrapper">
				<MetalQuill
					className="watson-input rich-editor"
					formats={formats}
					modules={modules}
					onChange={this.handleOnChange}
					placeHolder={Liferay.Language.get('write-your-content-here')}
					readOnly={disabled}
					theme="snow"
					value={value}
				/>
			</div>
		);
	}
}

RichTextEditor.PROPS = {
	disabled: Config.bool().value(false),
	loading: Config.bool().value(false),
	value: Config.string().value('')
};

RichTextEditor.STATE = {
	formats: Config.array().value(
		[
			'header',
			'size',
			'bold',
			'italic',
			'underline',
			'strike',
			'blockquote',
			'list',
			'bullet',
			'indent',
			'color',
			'background',
			'link'
		]
	),
	modules: Config.object().value(
		{
			toolbar: [
				[
					{'size': [false, 'large', 'huge']}
				],
				[
					'bold',
					'italic',
					'underline',
					'strike'
				],
				[
					{
						color: []
					},
					{
						background: []
					}
				],
				[
					'blockquote',
					'link'
				],
				[
					{'list': 'ordered'},
					{'list': 'bullet'},
					{'indent': '-1'},
					{'indent': '+1'}
				],
				['clean']
			]
		}
	)
};

RichTextEditor.SYNC_UPDATES = true;

export default RichTextEditor;