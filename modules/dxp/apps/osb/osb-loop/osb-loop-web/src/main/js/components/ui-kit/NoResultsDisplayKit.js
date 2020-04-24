import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import NoBookmarksMessage from '../NoBookmarksMessage';
import NoResultsDisplay from '../NoResultsDisplay';
import {lang} from '../../lib/lang-util';

class NoResultsDisplayKit extends Component {
	render() {
		return (
			<Kit header="NoResultsDisplay">
				<ElementContainer header="Horizontal display">
					<NoResultsDisplay
						horizontal={true}
						icon="magnifier"
						message="this is a message"
						multiplier={1}
						size="small"
						title="this is a title"
					/>
				</ElementContainer>

				<ElementContainer header="Title and Message">
					<NoResultsDisplay message="This is the message." title="This is the Title" />
				</ElementContainer>

				<ElementContainer header="Title Only">
					<NoResultsDisplay title="This is the Title" />
				</ElementContainer>

				<ElementContainer header="Message Only">
					<NoResultsDisplay message={lang(Liferay.Language.get('we-could-not-find-any-x-for-x'), [Liferay.Language.get('posts'), <span class="query" key="query">{'search query'}</span>])} />
				</ElementContainer>

				<ElementContainer header="Message with Icon Passed In">
					<NoResultsDisplay
						icon="magnifier"
						message={lang(Liferay.Language.get('we-could-not-find-any-results-for-x'), [<span class="query" key="query">{'search query'}</span>])}
					/>
				</ElementContainer>

				<ElementContainer header="Message with small Icon Passed In">
					<NoResultsDisplay
						icon="magnifier"
						message={lang(Liferay.Language.get('we-could-not-find-any-results-for-x'), [<span class="query" key="query">{'search query'}</span>])}
						size="small"
					/>
				</ElementContainer>

				<ElementContainer header="No Bookmarks Message">
					<NoResultsDisplay
						message={<NoBookmarksMessage />}
						title={Liferay.Language.get('you-do-not-have-any-posts-bookmarked-yet')}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default NoResultsDisplayKit;