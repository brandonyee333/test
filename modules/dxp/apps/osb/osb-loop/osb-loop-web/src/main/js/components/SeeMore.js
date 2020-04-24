import Component, {Config} from 'metal-jsx';
import {Map} from 'immutable';

class SeeMore extends Component {
	created() {
		this.onSeeMoreClick_ = this.onSeeMoreClick_.bind(this);
	}

	onSeeMoreClick_(event) {
		const {ctrlKey, metaKey, shiftKey} = event;

		if (!ctrlKey && !metaKey && !shiftKey) {
			event.preventDefault();

			event.stopImmediatePropagation();

			this.props.onSeeMore();
		}
	}

	render() {
		const {
			href,
			messageInfoIMap,
			truncated
		} = this.props;

		let seeMore = null;

		if (truncated) {
			seeMore = (
				<a
					class="see-more-container"
					href={href}
					key="seeMore"
					onClick={this.onSeeMoreClick_}
				>
					{messageInfoIMap.get('truncated') ? Liferay.Language.get('see-all') : Liferay.Language.get('see-more')}
				</a>
			);
		}

		return seeMore;
	}
}

SeeMore.PROPS = {
	href: Config.string().value('#'),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	onSeeMore: Config.func(),
	truncated: Config.bool()
};

export default SeeMore;