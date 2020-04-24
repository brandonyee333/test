import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from './Icon';

class Input extends Component {
	created() {
		this.focus = this.focus.bind(this);
	}

	attached() {
		if (this.props.focusOnAttached) {
			this.focus();
		}
	}

	focus() {
		this.setFocus(true);

		this.refs.input.focus();
	}

	setFocus(val) {
		return () => {
			this.state.focus_ = val;
		};
	}

	render() {
		const {
			icon,
			prefix,
			role,
			row,
			type
		} = this.props;

		return (
			<div
				class={
					getCN(
						'input-container',
						{
							[`input-${role}`]: role,
							focus: this.state.focus_,
							'input-icon': icon,
							prefix,
							row
						}
					)
				}
			>
				{icon &&
					<Icon name={icon} size="small" />
				}

				{prefix &&
					<span class="prefix-wrapper">{prefix}</span>
				}

				<input
					{...this.otherProps()}
					onBlur={this.setFocus(false)}
					onFocus={this.setFocus(true)}
					ref="input"
					type={type}
				/>
			</div>
		);
	}
}

Input.PROPS = {
	focusOnAttached: Config.bool(),
	icon: Config.string(),
	role: Config.string().oneOf(['default', 'error', 'success']),
	row: Config.bool().value(false),
	type: Config.string().value('text')
};

Input.STATE = {
	focus_: Config.value(false)
};

export default Input;