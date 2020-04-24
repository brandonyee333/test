import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {Provider} from 'metal-redux';

import BookmarksFeed from '../BookmarksFeed';
import mockStore from '../../tests/mock-store';
import {fetchPosts, filterRemovedPosts} from '../../actions/feeds';

const actionNoop = {
	type: 'NO_OP'
};

class BookmarksFeedExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<BookmarksFeed id={1} itemsPerPage={7} type={0} />
			</Provider>
		);
	}
}

describe(
	'BookmarksFeed',
	() => {
		let component;

		fetchPosts.mockReturnValue(Promise.resolve());
		filterRemovedPosts.mockReturnValue(actionNoop);

		afterEach(
			() => {
				if (component) {
					component.dispose();

					fetchPosts.mockClear();
					filterRemovedPosts.mockClear();
				}
			}
		);

		it(
			'renders',
			() => {

				component = new BookmarksFeedExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);