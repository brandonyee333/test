import Component from 'metal-jsx';

import Icon from './Icon';
import {lang} from '../lib/lang-util';

class NoBookmarksMessage extends Component {
	render() {
		return (
			<span>
				{Liferay.Language.get('save-posts-that-you-think-are-important-or-that-you-would-like-to-read-later')}
				<br />
				{lang(Liferay.Language.get('hover-over-the-x-on-a-post-and-click-bookmark-post-to-save-it-as-a-bookmark'), [<Icon key="ellipsis" name="ellipsis" />])}
			</span>
		);
	};
}

export default NoBookmarksMessage;