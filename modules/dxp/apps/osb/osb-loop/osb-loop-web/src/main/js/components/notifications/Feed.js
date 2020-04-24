import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Avatar from '../Avatar';
import Icon from '../Icon';
import MarkdownContent from '../MarkdownContent';
import Spinner from '../Spinner';
import {formatTime} from '../../lib/util';

class Item extends Component {
	created() {
		this.onClick_ = this.onClick_.bind(this);
	}

	onClick_() {
		this.props.onClick(this.props.item.id);
	}

	render() {
		const {
			displayCompositeJSONObject,
			message,
			read,
			summary,
			timestamp
		} = this.props.item;

		return (
			<div class={getCN('item', {read})} onClick={this.onClick_}>
				<div class="entity-item">
					<Avatar entity={displayCompositeJSONObject} size="small" />

					<div class="entity-item-info">
						<MarkdownContent elementClasses="title" message={summary} />

						<div class="details">{message}</div>

						<span class="display-time">
							{this.props.item.private &&
								<Icon display="default" name="lock" size="small" />
							}

							{formatTime(timestamp)}
						</span>
					</div>
				</div>
			</div>
		);
	}
}

Item.PROPS = {
	item: Config.object(),
	onClick: Config.func()
};

class Feed extends Component {
	render() {
		const {
			hasMoreItems,
			items,
			loading,
			onItemClick,
			onLoadMore
		} = this.props;

		return (
			<div class="notification-feed-container">
				{
					items.map(
						item => <Item item={item} key={item.id} onClick={onItemClick} />
					)
				}

				{hasMoreItems &&
					<div class="text-center view-more-wrapper">
						{loading &&
							<Spinner />
						}

						{!loading &&
							<a class="view-more" href="javascript:;" onClick={onLoadMore}>{`${Liferay.Language.get('load-older-notifications')}...`}</a>
						}
					</div>
				}
			</div>
		);
	}
}

Feed.PROPS = {
	hasMoreItems: Config.bool(),
	items: Config.array().required(),
	loading: Config.bool(),
	onItemClick: Config.func().required(),
	onLoadMore: Config.func().required()
};

export default Feed;