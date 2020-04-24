import Component, {Config} from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import Editor, {PLACEHOLDER_MAP} from '../Editor';

const styles = {
	border: '1px solid',
	padding: '20px'
};

class EditorKit extends Component {
	created() {
		this.handleEditorState_ = this.handleEditorState_.bind(this);
	}

	handleEditorState_(editorState) {
		this.state.editorState_ = editorState;
	}

	render() {
		return (
			<Kit header="Editor">
				<ElementContainer style={styles}>
					<Editor
						editorState={this.state.editorState_}
						onEditorStateChange={this.handleEditorState_}
						placeholder={PLACEHOLDER_MAP.text}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

EditorKit.STATE = {
	editorState_: Config.value(undefined)
};

EditorKit.SYNC_UPDATES = true;

export default EditorKit;