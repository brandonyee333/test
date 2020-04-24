import {EditorState} from 'draft-js';
import Component from 'metal-jsx';

class Editor extends Component {
	render() {
		return (
			<div class="editor-container" />
		);
	}
}

export const createEmptyEditorState = jest.fn(() => EditorState.createEmpty());
export const PLACEHOLDER_MAP = {};

export default Editor;