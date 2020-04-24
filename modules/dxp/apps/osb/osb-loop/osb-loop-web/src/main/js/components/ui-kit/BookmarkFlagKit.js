import Component, {Config} from 'metal-jsx';

import BookmarkFlag from '../BookmarkFlag';
import Card from '../card';
import ElementContainer from './ElementContainer';
import ElementDisplay from './ElementDisplay';
import Kit from './Kit';

class BookmarkFlagKit extends Component {
	created() {
		this.handleBookmarkFlagClick_ = this.handleBookmarkFlagClick_.bind(this);
	}

	handleBookmarkFlagClick_(event) {
		const name = event.delegateTarget.getAttribute('name');

		const {bookmarked} = this.state;

		bookmarked[name] = !bookmarked[name];

		this.state.bookmarked = bookmarked;
	}

	render() {
		const {handleBookmarkFlagClick_} = this;

		const {bookmarked} = this.state;

		return (
			<Kit card={false} elementClasses="bookmark-flag-kit" header="BookmarkFlag">
				<Card>
					<ElementContainer>
						<ElementDisplay description="{bookmarked: false}">
							<BookmarkFlag
								bookmarked={bookmarked[1]}
								name="1"
								onClick={handleBookmarkFlagClick_}
							/>
						</ElementDisplay>
					</ElementContainer>
				</Card>

				<Card>
					<ElementContainer>
						<ElementDisplay description="{bookmarked: true}">
							<BookmarkFlag
								bookmarked={bookmarked[2]}
								name="2"
								onClick={handleBookmarkFlagClick_}
							/>
						</ElementDisplay>
					</ElementContainer>
				</Card>
			</Kit>
		);
	}
}

BookmarkFlagKit.STATE = {
	bookmarked: Config.value(
		{
			1: false,
			2: true
		}
	)
};

export default BookmarkFlagKit;