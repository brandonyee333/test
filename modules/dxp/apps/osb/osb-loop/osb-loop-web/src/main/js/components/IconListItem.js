import Component, {Config} from 'metal-jsx';

import Icon from './Icon';

class IconListItem extends Component {
	render() {
		const {
			copyData = {},
			icon,
			label,
			url
		} = this.props;

		const {
			clipboardText,
			iconTitle,
			labelTitle,
			tooltipResponse
		} = copyData;

		return (
			<div class="loop-icon-list-item-container">
				<Icon
					data-clipboard-text={clipboardText}
					data-tooltip-response={tooltipResponse}
					display="secondary"
					name={icon}
					title={iconTitle}
				/>

				<span class="list-item-label" data-tooltip title={labelTitle}>
					<a href={url}>{label}</a>
				</span>
			</div>
		);
	}
}

IconListItem.PROPS = {
	copyData: Config.object().value({}),
	icon: Config.string(),
	label: Config.string(),
	url: Config.string()
};

export default IconListItem;