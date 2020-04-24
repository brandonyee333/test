import Component, {Config} from 'metal-jsx';

import Toggle from '../Toggle';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

const LABEL = Liferay.Language.get('label');

class ToggleKit extends Component {
	created() {
		this.handleToggleChange_ = this.handleToggleChange_.bind(this);
	}

	handleToggleChange_(event) {
		const name = event.delegateTarget.getAttribute('name');

		const {checked_} = this.state;

		checked_[name] = !checked_[name];

		this.state.checked_ = checked_;
	}

	render() {
		const {checked_} = this.state;

		return (
			<Kit header="Toggle">
				<ElementContainer>
					<ElementDisplay description="With Label">
						<Toggle
							checked={checked_[1]}
							label={LABEL}
							name="1"
							onChange={this.handleToggleChange_}
						/>
					</ElementDisplay>

					<ElementDisplay description="No Label">
						<Toggle
							checked={checked_[2]}
							name="2"
							onChange={this.handleToggleChange_}
						/>
					</ElementDisplay>

					<ElementDisplay description="{checked: true}">
						<Toggle
							checked={checked_[3]}
							label={LABEL}
							name="3"
							onChange={this.handleToggleChange_}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

ToggleKit.STATE = {
	checked_: Config.value(
		{
			1: false,
			2: false,
			3: true
		}
	)
};

export default ToggleKit;