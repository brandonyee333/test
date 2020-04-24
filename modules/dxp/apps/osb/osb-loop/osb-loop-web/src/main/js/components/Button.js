import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

class Button extends Component {
	render() {
		const {
			active,
			children,
			display,
			fill,
			large,
			role,
			shape,
			textDisplay,
			type,
			useAnchor
		} = this.props;

		const buttonSizeClass = shape === 'badge' ? 'btn-badge-large' : 'btn-large';

		const classes = getCN(
			'btn',
			'btn-container',
			{
				[`btn-${display}`]: display,
				[`btn-${fill}`]: fill,
				[`btn-${role}`]: role,
				[`btn-${shape}`]: shape,
				[`btn-text-${textDisplay}`]: textDisplay,
				active,
				['btn-anchor']: useAnchor,
				[buttonSizeClass]: large
			}
		);

		const buttonConfigs = {
			...this.otherProps(),
			class: classes,
			type
		};

		return useAnchor ? <a {...buttonConfigs}>{children}</a> : <button {...buttonConfigs}>{children}</button>;
	}
}

const BUTTON_TYPES = [
	'danger',
	'dark',
	'following',
	'info',
	'success',
	'secondary',
	'warning'
];

Button.PROPS = {
	active: Config.bool(),
	display: Config.oneOf(BUTTON_TYPES),
	fill: Config.oneOf(['inverted', 'empty']),
	large: Config.bool(),
	role: Config.oneOf(['cancel', 'following', 'primary', 'secondary']),
	shape: Config.oneOf(['badge', 'pill']),
	textDisplay: Config.oneOf([...BUTTON_TYPES, 'primary']),
	type: Config.oneOf(['button', 'menu', 'reset', 'submit']).value('button'),
	useAnchor: Config.bool().value(false)
};

export default Button;