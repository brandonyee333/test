import Component, {Config} from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import RadioGroup from '../radio-group';

class RadioGroupKit extends Component {
	created() {
		this.handleRadioChange_ = this.handleRadioChange_.bind(this);
	}

	handleRadioChange_(value) {
		this.state.checked_ = value;
	}

	render() {
		return (
			<Kit header="RadioGroup">
				<ElementContainer>
					<RadioGroup checked={this.state.checked_} name="testradio" onChange={this.handleRadioChange_}>
						<RadioGroup.Option label="Option 1" value={0} />
						<RadioGroup.Option label="Option 2" value={1} />
						<RadioGroup.Option label="Option 3" value={2} />
					</RadioGroup>
				</ElementContainer>
			</Kit>
		);
	}
}

RadioGroupKit.STATE = {
	checked_: Config.value(1)
};

export default RadioGroupKit;