import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import LinkPreviewEditor from '../LinkPreviewEditor';
import Kit from './Kit';

class LinkPreviewEditorKit extends Component {
	created() {
		bindAll(
			this,
			'handleDataChange_',
			'handleInputChange_'
		);
	}

	handleDataChange_(linkData) {
		this.state.linkData_ = linkData;

		if (!linkData) {
			this.state.inputValue_ = '';
		}
	}

	handleInputChange_(inputValue) {
		this.state.inputValue_ = inputValue;
	}

	render() {
		const {inputValue_, linkData_} = this.state;

		return (
			<Kit header="LinkPreviewEditor">
				<LinkPreviewEditor
					inputValue={inputValue_}
					linkData={linkData_}
					onDataChange={this.handleDataChange_}
					onInputChange={this.handleInputChange_}
				/>
			</Kit>
		);
	}
}

LinkPreviewEditorKit.STATE = {
	inputValue_: Config.value(''),
	linkData_: Config.value(null)
};

export default LinkPreviewEditorKit;