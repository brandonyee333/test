import {bindAll} from 'lodash';
import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import ReactToggle from 'react-switch';

const MetalToggle = bridge(ReactToggle);

class Toggle extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleOnChange',
			'onFocusChange'
		);

		let checkedValue = false;

		const {checked} = this.props;

		if ((checked === 'true' && checked !== 'false') || checked === true) {
			checkedValue = true;
		}

		this.setState({checked: checkedValue});
	}

	onFocusChange({focused}) {
		this.setState({focused});
	}

	render() {
		const {checked} = this.state;
		const {cssClass = '', disabled, label = ['', '']} = this.props;

		const cssClasses = `watson-toggle ${cssClass}`;

		return (
			<div class={cssClasses}>
				<label>
					<span>{label[0]}</span>

					<MetalToggle
						checked={checked}
						checkedIcon={false}
						disabled={disabled}
						icons={false}
						offColor="#93A8AC"
						onChange={this._handleOnChange}
						onColor="#3FE49F"
						uncheckedIcon={false}
					/>

					<span>{label[1]}</span>
				</label>
			</div>
		);
	}

	_handleOnChange() {
		const {checked} = this.state;
		const {onChange} = this.props;

		this.setState({checked: !checked});

		if (onChange) {
			onChange(!checked);
		}
	}

	syncChecked(newState) {
		if (newState !== undefined) {
			let checkedValue = false;

			if ((newState === 'true' && newState !== 'false') || newState === true) {
				checkedValue = true;
			}

			this.setState({checked: checkedValue});
		}
	}
}

Toggle.PROPS = {
	checked: Config.any().value(false)
};

Toggle.STATE = {
	checked: Config.bool().value(false),
	focused: Config.bool()
};

export default Toggle;