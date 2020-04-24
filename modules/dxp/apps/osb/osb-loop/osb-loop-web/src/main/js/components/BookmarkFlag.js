import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import Icon from './Icon';
import InlineButton from './InlineButton';

class BookmarkFlag extends Component {
	render() {
		const {bookmarked, onClick} = this.props;

		const bookmarkClasses = getCN(
			'bookmark-flag-container',
			{visible: bookmarked}
		);

		return (
			<InlineButton
				{...this.otherProps()}
				data-tooltip
				elementClasses={bookmarkClasses}
				onClick={onClick}
				title={Liferay.Language.get('remove-from-bookmarks')}
			>
				<Icon display="warning" name="bookmark" size="large" />
			</InlineButton>
		);
	}
}

BookmarkFlag.PROPS = {
	bookmarked: Config.bool(),
	onClick: Config.func()
};

export default BookmarkFlag;