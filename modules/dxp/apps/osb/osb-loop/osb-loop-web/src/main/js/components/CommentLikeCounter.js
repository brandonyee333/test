import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from './Icon';
import InlineButton from './InlineButton';

class CommentLikeCounter extends Component {
	render() {
		const {
			count,
			liked,
			onClick,
			onCountClick
		} = this.props;

		return (
			<span class="comment-like-counter-container">
				<InlineButton elementClasses={getCN('like-action', {liked})} onClick={onClick}>
					{liked ? Liferay.Language.get('liked') : Liferay.Language.get('like')}
				</InlineButton>

				<a
					class="counter"
					data-tooltip
					href="javascript:;"
					onClick={onCountClick}
					title={Liferay.Language.get('click-to-see-likes')}
				>
					{!!count &&
						<Icon name="heart-full" size="small" />
					}

					{count || null}
				</a>
			</span>
		);
	}
}

CommentLikeCounter.PROPS = {
	count: Config.number(),
	liked: Config.bool(),
	onClick: Config.func(),
	onCountClick: Config.func()
};

export default CommentLikeCounter;