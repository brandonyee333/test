import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

class Toggle extends JSXComponent {
	created() {
		bindAll(
			this,
			'onChange'
		);

		this.setState({checked: this.props.checked});
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
			this.setState({checked: newState});
		}
	}
}

Toggle.PROPS = {
	checked: Config.bool().value(false)
};

Toggle.STATE = {
	checked: Config.bool().value(false)
};

export default Toggle;