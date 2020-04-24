import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {fromJS} from 'immutable';

import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import {Modal} from '../modal';

const modalStoreStates = {
	closed: fromJS(
		{
			hideOnBlur: true,
			modalProps: {},
			modalType: null,
			open: false
		}
	),
	long: fromJS(
		{
			hideOnBlur: true,
			modalProps: {
				url: 'http://www.liferay.com'
			},
			modalType: 'SHARE_LINK',
			open: true
		}
	)
};

class ShareLinkKit extends Component {
	created() {
		bindAll(
			this,
			'hideModal_',
			'showShareLinkModal_',
		);
	}

	hideModal_() {
		this.state.storeState_ = 'closed';
	}

	showShareLinkModal_() {
		this.state.storeState_ = 'long';
	}

	render() {
		const modalIMap = modalStoreStates[this.state.storeState_];

		return (
			<Kit header="Share Link">
				<Modal hideModal={this.hideModal_} modalIMap={modalIMap} />

				<ElementContainer header="Share Link">
					<Button onClick={this.showShareLinkModal_} role="primary">{'Share Link'}</Button>
				</ElementContainer>
			</Kit>
		);
	}
}

ShareLinkKit.STATE = {
	storeState_: Config.value('closed')
};

export default ShareLinkKit;