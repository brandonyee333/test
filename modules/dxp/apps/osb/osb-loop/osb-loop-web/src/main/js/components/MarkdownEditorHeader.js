import Component, {Config} from 'metal-jsx';

class MarkdownEditorHeader extends Component {
	render() {
		const {
			disabled,
			onEditClick,
			onPreviewClick,
			preview
		} = this.props;

		return (
			<ul class="markdown-editor-header-container">
				{!disabled &&
					<li class={preview ? '' : 'active'} onClick={onEditClick}>
						{Liferay.Language.get('write')}
					</li>
				}

				<li class={preview ? 'active' : ''} onClick={onPreviewClick}>
					{Liferay.Language.get('preview-markdown')}
				</li>
			</ul>
		);
	}
}

MarkdownEditorHeader.PROPS = {
	disabled: Config.bool().value(false),
	onEditClick: Config.func(),
	onPreviewClick: Config.func(),
	preview: Config.bool().value(false)
};

export default MarkdownEditorHeader;