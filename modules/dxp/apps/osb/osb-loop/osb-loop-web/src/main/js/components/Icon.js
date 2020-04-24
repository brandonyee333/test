import getCN from 'classnames';
import Component, {Config} from 'metal-jsx';

class Icon extends Component {
	render() {
		const {
			display,
			invertedColor,
			multiplier,
			name,
			onClick,
			size,
			title
		} = this.props;

		const classes = getCN(
			'loop-icon-container',
			`loop-icon-${name}`,
			`loop-icon-${size}-${multiplier}x`,
			{
				[`loop-icon-${display}`]: display,
				inverted: invertedColor
			}
		);

		let sizeName = `${name}-24`;

		if (size !== 'large') {
			sizeName = `${name}-16`;
		}

		const href = {
			'href': `#loop-icon-${sizeName}`
		};

		return (
			<svg
				{...this.otherProps()}
				class={classes}
				data-tooltip
				onClick={onClick}
				title={title}
			>
				<use {...href} />
			</svg>
		);
	}
}

Icon.PROPS = {
	display: Config.oneOf(
		[
			'danger',
			'dark',
			'default',
			'inactive',
			'info',
			'primary',
			'secondary',
			'success',
			'warning'
		]
	),
	invertedColor: Config.bool().value(false),
	multiplier: Config.oneOf([1, 2, 3, 4]).value(1),
	name: Config.string(),
	onClick: Config.func(),
	size: Config.oneOf(['large', 'small', 'tiny']).value('large'),
	title: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	)
};

export default Icon;