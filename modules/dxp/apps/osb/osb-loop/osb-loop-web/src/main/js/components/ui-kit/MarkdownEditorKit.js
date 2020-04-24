import Component, {Config} from 'metal-jsx';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import MarkdownEditor from '../MarkdownEditor';
import mockStore from '../../tests/mock-store';

class MarkdownEditorKit extends Component {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleChange_(newValue) {
		this.state.content_ = newValue;
	}

	render() {
		return (
			<Kit header="MarkdownEditor">
				<ElementContainer>
					<Provider store={mockStore()}>
						<MarkdownEditor
							onChange={this.handleChange_}
							placeholder="placeholder"
							value={this.state.content_}
						/>
					</Provider>
				</ElementContainer>
			</Kit>
		);
	}
}

MarkdownEditorKit.STATE = {
	content_: Config.value('')
};

export default MarkdownEditorKit;