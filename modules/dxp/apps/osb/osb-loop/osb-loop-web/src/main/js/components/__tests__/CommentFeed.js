import CommentFeed from '../CommentFeed';
import mockStore from '../../tests/mock-store';
import {fetchComments} from '../../actions/feeds';

describe(
	'CommentFeed',
	() => {
		let component;

		fetchComments.mockReturnValue({type: ''});

		afterEach(
			() => {
				if (component) {
					component.dispose();

					fetchComments.mockClear();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new CommentFeed(
					{
						commentFeedId: 0,
						store: mockStore(),
						total: 0
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls fetchComments on loadMoreComments_',
			() => {
				component = new CommentFeed(
					{
						commentFeedId: 0,
						store: mockStore(),
						total: 3
					}
				);

				expect(fetchComments).not.toBeCalled();

				const eventObj = {
					preventDefault: jest.fn(),
					stopImmediatePropagation: jest.fn()
				};

				component.components.child.loadMoreComments_(eventObj);

				expect(fetchComments).toBeCalled();
				expect(eventObj.preventDefault).toBeCalled();
				expect(eventObj.stopImmediatePropagation).toBeCalled();
			}
		);

		it(
			'previousComments not to render when total is less than or equal to displayed',
			() => {
				component = new CommentFeed(
					{
						commentFeedId: 0,
						store: mockStore(),
						total: 0
					}
				);

				const previousComments = component.element.querySelector('.previous-comments');

				expect(previousComments).toBeFalsy();
			}
		);

		it(
			'previousComments to render when total is greater than displayed',
			() => {
				component = new CommentFeed(
					{
						commentFeedId: 0,
						store: mockStore(),
						total: 3
					}
				);

				const previousComments = component.element.querySelector('.previous-comments');

				expect(previousComments).toBeTruthy();
			}
		);
	}
);