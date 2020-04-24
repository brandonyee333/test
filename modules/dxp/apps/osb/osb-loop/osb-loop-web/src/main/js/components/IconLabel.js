import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from './Icon';

class IconLabel extends Component {
	render() {
		const {
			iconClasses,
			label,
			responsiveWrap,
			spacing,
			title
		} = this.props;

		const classNames = getCN(
			'loop-icon-label-container',
			{
				[`loop-icon-label-spacing-${spacing}`]: spacing,
				'responsive-wrap': responsiveWrap
			}
		);

		return (
			<span class={classNames} data-tooltip title={title}>
				<Icon {...this.otherProps()} elementClasses={iconClasses} />

				<span class="loop-icon-label">{label}</span>
			</span>
		);
	}
}

IconLabel.PROPS = {
	iconClasses: Config.string(),
	label: Config.oneOfType(
		[
			Config.array(),
			Config.number(),
			Config.string()
		]
	),
	responsiveWrap: Config.bool(),
	spacing: Config.oneOf(
		[
			'default',
			'large',
			'medium',
			'small'
		]
	),
	title: Config.oneOfType(
		[
			Config.number(),
			Config.string()
		]
	)
};

export default IconLabel;