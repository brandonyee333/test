import Component, {Config} from 'metal-jsx';

import Checkbox from '../Checkbox';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

const LABEL = Liferay.Language.get('label');

class CheckboxKit extends Component {
	created() {
		this.handleCheckboxChange_ = this.handleCheckboxChange_.bind(this);
	}

	handleCheckboxChange_() {
		this.state.checked_ = !this.state.checked_;
	}

	render() {
		const {handleCheckboxChange_} = this;

		const {checked_} = this.state;

		return (
			<Kit header="Checkbox">
				<ElementContainer>
					<ElementDisplay description={'standard'}>
						<Checkbox checked={checked_} label={LABEL} onChange={handleCheckboxChange_} />
					</ElementDisplay>

					<ElementDisplay description={'icon'}>
						<Checkbox
							checked={checked_}
							iconNameOff="bell-off"
							iconNameOn="bell"
							onChange={handleCheckboxChange_}
						/>
					</ElementDisplay>

					<ElementDisplay description={'disabled'}>
						<Checkbox
							checked={checked_}
							disabled
							label={LABEL}
							onChange={handleCheckboxChange_}
						/>
					</ElementDisplay>
				</ElementContainer>
			</Kit>
		);
	}
}

CheckboxKit.STATE = {
	checked_: Config.value(false)
};

export default CheckboxKit;