import Component, {Config} from 'metal-jsx';
import Transition from 'metal-css-transitions';
import {bindAll} from 'lodash';

import Button from './Button';
import Checkbox from './Checkbox';

class ConfirmMask extends Component {
	created() {
		bindAll(
			this,
			'handleCheckboxChange_',
			'handleConfirmClick_'
		);
	}

	handleCheckboxChange_() {
		this.state.checked_ = !this.state.checked_;
	}

	handleConfirmClick_() {
		this.props.onConfirm(this.state.checked_);
	}

	render() {
		const {
			active,
			children,
			message,
			onCancel
		} = this.props;

		const {checked_} = this.state;

		return (
			<div {...this.otherProps()} class="confirm-mask-container">
				{children}

				<Transition name="transition-slide-up">
					{active &&
						<div class="confirm-mask">
							<div class="confirm-mask-content">
								<div class="message">
									{message}
								</div>

								<div class="controls">
									<Button onClick={onCancel} role="secondary">
										{Liferay.Language.get('cancel')}
									</Button>

									<Button onClick={this.handleConfirmClick_} role="primary">
										{Liferay.Language.get('apply')}
									</Button>
								</div>

								<Checkbox
									checked={checked_}
									label={Liferay.Language.get('do-not-show-this-message-again')}
									onChange={this.handleCheckboxChange_}
								/>
							</div>
						</div>
					}
				</Transition>
			</div>
		);
	}
}

ConfirmMask.PROPS = {
	active: Config.bool(),
	message: Config.string(),
	onCancel: Config.func(),
	onConfirm: Config.func()
};

ConfirmMask.STATE = {
	checked_: Config.value(false)
};

export default ConfirmMask;