import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from './Icon';

class NoResultsDisplay extends Component {
	render() {
		const {
			horizontal,
			icon,
			message,
			multiplier,
			size,
			title
		} = this.props;

		const backgroundClass = getCN('no-results-background', `${size}-${multiplier}x`);
		const classes = getCN('no-results-container text-center', {horizontal});

		return (
			<div class={classes}>
				<div class={backgroundClass}>
					<Icon multiplier={multiplier} name={icon} size={size} />
				</div>

				{title && !horizontal &&
					<div class="title">
						{title}
					</div>
				}

				{message &&
					<div class="message">
						{message}
					</div>
				}
			</div>
		);
	}
}

NoResultsDisplay.PROPS = {
	horizontal: Config.bool().value(false),
	icon: Config.string().value('streams'),
	message: Config.any(),
	multiplier: Config.number().value(2),
	size: Config.oneOf(['large', 'small', 'smallest']).value('large'),
	title: Config.oneOfType(
		[
			Config.array(),
			Config.string()
		]
	)
};

export default NoResultsDisplay;