import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import Icon from './Icon';
import {createModalURL} from '../lib/util';
import {modalTypes, showModal} from '../actions/modals';

class MarkdownSupportedIcon extends Component {
	created() {
		this.openCheatsheet_ = this.openCheatsheet_.bind(this);
	}

	openCheatsheet_() {
		const {openNewWindow, showModal} = this.props;

		const modalConfig = {
			modalProps: {},
			modalType: modalTypes.MARKDOWN_CHEATSHEET
		};

		if (openNewWindow) {
			const href = createModalURL(
				{
					...modalConfig,
					hideOnBlur: false,
					redirectURL: window.location.href
				}
			);

			window.open(href);
		}
		else {
			showModal(modalConfig);
		}
	}

	render() {
		return (
			<Icon
				display="dark"
				elementClasses="markdown-supported-icon-container"
				key="markdown"
				name="markdown"
				onClick={this.openCheatsheet_}
				title={Liferay.Language.get('markdown-supported')}
			/>
		);
	}
}

const STORE = {
	showModal: Config.func()
};

MarkdownSupportedIcon.PROPS = {
	...STORE,
	openNewWindow: Config.bool().value(false)
};

export default connect(
	null,
	{
		showModal
	}
)(MarkdownSupportedIcon);