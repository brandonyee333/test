import Component from 'metal-jsx';

import Spinner from './Spinner';

class EditorSpinner extends Component {
	render() {
		return (
			<div class="editor-spinner-container">
				<Spinner />
			</div>
		);
	}
}

export default EditorSpinner;