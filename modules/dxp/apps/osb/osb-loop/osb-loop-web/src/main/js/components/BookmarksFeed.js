import Component, {Config} from 'metal-jsx';

import Feed from './Feed';
import NoBookmarksMessage from './NoBookmarksMessage';

class BookmarksFeed extends Component {
	render() {
		const {id, itemsPerPage, type} = this.props;

		return (
			<Feed
				id={id}
				itemsPerPage={itemsPerPage}
				noResultsMessage={<NoBookmarksMessage />}
				noResultsTitle={Liferay.Language.get('you-do-not-have-any-posts-bookmarked-yet')}
				type={type}
			/>
		);
	}
}

BookmarksFeed.PROPS = {
	id: Config.number(),
	itemsPerPage: Config.number(),
	type: Config.number()
};

export default BookmarksFeed;