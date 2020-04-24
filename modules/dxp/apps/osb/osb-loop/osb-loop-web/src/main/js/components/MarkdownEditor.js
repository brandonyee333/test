import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';

import MarkdownContent from './MarkdownContent';
import MarkdownEditorHeader from './MarkdownEditorHeader';
import MarkdownSupportedIcon from './MarkdownSupportedIcon';
import sendRequest from '../lib/request';

class MarkdownEditor extends Component {
	created() {
		bindAll(
			this,
			'handleChange_',
			'handleEditClick_',
			'handlePreviewClick_'
		);

		if (this.props.disabled) {
			this.getMarkdown_();
		}
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	getMarkdown_() {
		this._request = sendRequest(
			{
				controller: 'home',
				controllerMethod: 'viewMarkdownHTML.json',
				data: {
					markdownSource: this.props.value
				}
			}
		).then(
			markdown => this.setState(
				{
					markdownContent_: markdown,
					preview_: true
				}
			)
		);
	}

	handleChange_() {
		const {
			props: {onChange},
			refs: {textarea}
		} = this;

		textarea.style.height = 'auto';
		textarea.style.height = `${textarea.scrollHeight}px`;

		onChange(textarea.value);
	}

	handleEditClick_() {
		this.setState(
			{preview_: false},
			this.handleChange_
		);
	}

	handlePreviewClick_() {
		if (!this.state.preview_) {
			this.getMarkdown_();
		}
	}

	render() {
		const {
			props: {disabled, placeholder, value},
			state: {markdownContent_, preview_}
		} = this;

		return (
			<div class={getCN('markdown-editor-container', {disabled})}>
				<MarkdownEditorHeader
					disabled={disabled}
					onEditClick={this.handleEditClick_}
					onPreviewClick={this.handlePreviewClick_}
					preview={preview_}
				/>

				{!disabled && !preview_ &&
					<textarea
						onInput={this.handleChange_}
						placeholder={placeholder}
						ref="textarea"
						resize={false}
						value={value}
					/>
				}

				{preview_ &&
					<MarkdownContent message={markdownContent_} />
				}

				<MarkdownSupportedIcon openNewWindow={true} />
			</div>
		);
	}
}

MarkdownEditor.PROPS = {
	disabled: Config.bool().value(false),
	onChange: Config.func().required(),
	placeholder: Config.string().value(''),
	value: Config.string().required()
};

MarkdownEditor.STATE = {
	markdownContent_: Config.string().value(''),
	preview_: Config.bool().value(false)
};

export default MarkdownEditor;