import bridge from 'metal-react';
import JSXComponent, {Config} from 'metal-jsx';
import ReactTooltip from 'react-tooltip';

const MetalTooltip = bridge(ReactTooltip);

export default class Tooltip extends JSXComponent {
	render() {
		const {
			delayHide,
			effect,
			htmlValue,
			id,
			key,
			multiline,
			placement,
			scrollHide,
			tooltipLabel
		} = this.props;

		return (
			<div class="tooltip-wrapper">
				<MetalTooltip
					class="stayOpen"
					delayHide={delayHide}
					effect={effect}
					html={htmlValue}
					id={id}
					multiline={multiline}
					place={placement}
					scrollHide={scrollHide}
				>
					<span key={key}>{tooltipLabel}</span>
				</MetalTooltip>
			</div>
		);
	}
}

Tooltip.PROPS = {
	delayHide: Config.number().value(650),
	effect: Config.string().value('solid'),
	htmlValue: Config.bool().value(false),
	id: Config.string(),
	key: Config.number(),
	multiline: Config.bool().value(false),
	placement: Config.string().value('right'),
	scrollHide: Config.bool().value(true),
	tooltipLabel: Config.string().value('')
};