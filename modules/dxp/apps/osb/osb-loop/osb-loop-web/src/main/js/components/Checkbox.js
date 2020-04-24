import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {uniqueId} from 'lodash';

import Icon from './Icon';

class Checkbox extends Component {
	created() {
		this._id = uniqueId('checkbox');
	}

	render() {
		const {
			checked,
			disabled,
			iconNameOff,
			iconNameOn,
			iconSize,
			label,
			onChange
		} = this.props;

		const classes = getCN(
			'checkbox-container',
			{
				checked,
				disabled
			}
		);

		return (
			<div class={classes}>
				<input
					{...this.otherProps()}
					checked={checked ? 'checked' : null}
					class="checkbox-input"
					disabled={disabled ? 'disabled' : null}
					id={this._id}
					onChange={onChange}
					type="checkbox"
				/>

				{!iconNameOn &&
					<div class="checkbox">
						{checked &&
							<Icon display="secondary" name="check" size="small" />
						}
					</div>
				}

				{iconNameOn &&
					<Icon
						display={checked ? 'primary' : null}
						elementClasses="toggle-icon"
						name={checked ? iconNameOn : iconNameOff}
						size={iconSize}
					/>
				}

				{label &&
					<label class="checkbox-label" for={this._id}>{label}</label>
				}
			</div>
		);
	}
}

Checkbox.PROPS = {
	checked: Config.bool().value(false),
	disabled: Config.bool().value(false),
	iconNameOff: Config.string(),
	iconNameOn: Config.string(),
	iconSize: Config.oneOf(['small', 'large']).value('large'),
	label: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	),
	onChange: Config.func()
};

export default Checkbox;