import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from '../Icon';

class Option extends Component {
	created() {
		this.handleContainerClick_ = this.handleContainerClick_.bind(this);
	}

	handleContainerClick_() {
		if (!this.checked) {
			this.props.onChange(this.props.value);
		}
	}

	render() {
		const {
			checked,
			label,
			name,
			value
		} = this.props;

		const classes = getCN(
			'radio-group-option-container',
			{
				'radio-checked': checked
			}
		);

		return (
			<div class={classes} onClick={this.handleContainerClick_}>
				<input
					{...this.otherProps()}
					checked={checked ? 'checked' : null}
					class="radio"
					hidden={true}
					name={name}
					readOnly={true}
					type="radio"
					value={value}
				/>

				{label &&
					<span class="radio-label">{label}</span>
				}

				{checked &&
					<Icon display="primary" name="check" size="small" />
				}
			</div>
		);
	}
}

Option.PROPS = {
	checked: Config.bool(),
	label: Config.string(),
	name: Config.string(),
	onChange: Config.func(),
	value: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	)
};

export default Option;