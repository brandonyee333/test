import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';

import Button from '../Button';
import ConfirmMask from '../ConfirmMask';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class ConfirmMaskKit extends Component {
	created() {
		bindAll(
			this,
			'handleConfirm_',
			'handleIncrement_',
			'hideConfirm_',
			'incrementCounter_'
		);
	}

	handleConfirm_(checked) {
		this.state.requireConfirm_ = !checked;

		this.incrementCounter_();

		this.hideConfirm_();
	}

	handleIncrement_() {
		if (this.state.requireConfirm_) {
			this.state.confirmActive_ = true;
		}
		else {
			this.incrementCounter_();
		}
	}

	hideConfirm_() {
		this.state.confirmActive_ = false;
	}

	incrementCounter_() {
		this.state.count_++;
	}

	render() {
		return (
			<ConfirmMask
				active={this.state.confirmActive_}
				message="Are you sure you want to increment the counter?"
				onCancel={this.hideConfirm_}
				onConfirm={this.handleConfirm_}
			>
				<Kit header="ConfirmMask">
					<ElementContainer>
						<ElementDisplay>
							<Button onClick={this.handleIncrement_} role="primary">{'Increment'}</Button>
						</ElementDisplay>

						<ElementDisplay>
							{this.state.count_}
						</ElementDisplay>
					</ElementContainer>
				</Kit>
			</ConfirmMask>
		);
	}
}

ConfirmMaskKit.STATE = {
	confirmActive_: Config.value(false),
	count_: Config.value(0),
	requireConfirm_: Config.value(true)
};

export default ConfirmMaskKit;