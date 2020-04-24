import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {isUndefined} from 'lodash';
import {Map, OrderedMap} from 'immutable';

import AnnouncementCarouselCard from './AnnouncementCarouselCard';
import Carousel from './Carousel';
import LoopConstants from '../lib/loop-constants';
import {fetchPosts} from '../actions/feeds';

class AnnouncementsCarousel extends Component {
	attached() {
		if (!this.props.postsIOMap.size) {
			this.getPosts_();
		}
	}

	rendered() {
		if (isUndefined(this.props.loading)) {
			this.getPosts_();
		}
	}

	getPosts_() {
		const {fetchPosts, id, loading} = this.props;

		if (!loading) {
			fetchPosts(
				{
					end: -1,
					id,
					sessionTime: Date.now(),
					start: -1,
					stickyOnly: true,
					type: LoopConstants.postTypes.announcement
				}
			);
		}
	}

	render() {
		const {href, loading = true, postsIOMap} = this.props;

		return (
			<div class="announcement-carousel-container">
				{!loading && !!postsIOMap.size &&
					<Carousel href={href} title={Liferay.Language.get('pinned-announcements')}>
						{
							postsIOMap.toArray().map(
								(item, i) => (
									<Carousel.Item key={`items${i}`}>
										<AnnouncementCarouselCard id={item.get('id')} key={item.get('id')} />
									</Carousel.Item>
								)
							)
						}
					</Carousel>
				}
			</div>
		);
	}
}

const STORE = {
	fetchPosts: Config.func(),
	loading: Config.bool(),
	postsIOMap: Config.instanceOf(OrderedMap)
};

AnnouncementsCarousel.PROPS = {
	...STORE,
	href: Config.string(),
	id: Config.number()
};

export default connect(
	(state, ownProps) => {
		const currentFeed = state.getIn(['feeds', ownProps.id], Map());

		return {
			loading: currentFeed.get('loading'),
			postsIOMap: currentFeed.getIn(['posts'], OrderedMap())
		};
	},
	{
		fetchPosts
	}
)(AnnouncementsCarousel);