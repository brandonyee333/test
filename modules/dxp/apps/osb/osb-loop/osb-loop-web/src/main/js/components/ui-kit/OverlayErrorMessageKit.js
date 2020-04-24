import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import OverlayErrorMessage from '../OverlayErrorMessage';
import Button from '../Button';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

class OverlayErrorMessageKit extends Component {
	created() {
		bindAll(
			this,
			'handleShowError_',
			'handleHideError_'
		);
	}

	handleHideError_() {
		this.state.active_ = false;
	}

	handleShowError_() {
		this.state.active_ = true;
	}

	render() {
		return (
			<Kit header="OverlayErrorMessage">
				<ElementContainer>
					<OverlayErrorMessage
						active={this.state.active_}
						message="This is an error message."
						onHide={this.handleHideError_}
					>
						<Button display="danger" onClick={this.handleShowError_}>
							{'Create Error'}
						</Button>
					</OverlayErrorMessage>
				</ElementContainer>
			</Kit>
		);
	}
}

OverlayErrorMessageKit.STATE = {
	active_: Config.value(false)
};

export default OverlayErrorMessageKit;