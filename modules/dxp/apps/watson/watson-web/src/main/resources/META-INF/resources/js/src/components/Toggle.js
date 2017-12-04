import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

class Toggle extends JSXComponent {
	created() {
		bindAll(
			this,
			'onChange'
		);

		let checkedValue = false;

		const {checked} = this.props;

		if ((checked === 'true' && checked !== 'false') || checked === true) {
			checkedValue = true;
		}

		this.setState({checked: checkedValue});
	}

	render() {
		const {checked} = this.state;
		const {cssClass = '', disabled, label = ['', '']} = this.props;

		const cssClasses = `watson-toggle ${cssClass}`;

		return (
			<div class={cssClasses}>
				<label>
					<span>{label[0]}</span>

					<input
						checked={checked}
						class="toggle"
						disabled={disabled}
						onChange={this.onChange}
						type="checkbox"
					/>

					<div class="fancy-toggle" />

					<span>{label[1]}</span>
				</label>
			</div>
		);
	}

	onChange() {
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
	checked: Config.bool().value(false)
};

export default Toggle;