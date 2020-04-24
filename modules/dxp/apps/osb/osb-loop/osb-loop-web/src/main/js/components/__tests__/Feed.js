import Promise from 'metal-promise';
import {Map, OrderedMap} from 'immutable';
import {noop} from 'lodash';

import mockStore from '../../tests/mock-store';
import FeedContainer, {Feed} from '../Feed';
import {fetchPosts, filterRemovedPosts} from '../../actions/feeds';

const actionNoop = {
	type: 'NO_OP'
};

describe(
	'Feed',
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
				component = new FeedContainer(
					{
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls filterRemovedPosts on detached',
			() => {
				component = new FeedContainer(
					{
						store: mockStore()
					}
				);

				expect(filterRemovedPosts).not.toBeCalled();

				component.components.child.detach();

				expect(filterRemovedPosts).toBeCalled();
			}
		);

		it(
			'LoadingCard should be displayed when loading',
			() => {
				component = new Feed(
					{
						feedPostsIOMap: OrderedMap(),
						filterRemovedPosts: noop,
						loading: true,
						onScrollEnd: noop,
						store: mockStore()
					}
				);

				jest.runAllTimers();

				expect(component.element.querySelector('.loading-card-container')).toBeTruthy();
			}
		);

		it(
			'NoResultsDisplay should be displayed when not loading and no posts',
			() => {
				component = new Feed(
					{
						feedPostsIOMap: OrderedMap(),
						filterRemovedPosts: noop,
						loading: true,
						noResultsMessage: 'test',
						onScrollEnd: noop
					}
				);

				let NoResultsDisplay = component.element.querySelector('.no-results-container');

				expect(NoResultsDisplay).toBeFalsy();

				component.props.loading = false;

				jest.runAllTimers();

				NoResultsDisplay = component.element.querySelector('.no-results-container');

				expect(NoResultsDisplay).toBeTruthy();
			}
		);

		it(
			'should render an UndoCard when a post has removedBy set to true',
			() => {
				component = new Feed(
					{
						feedPostsIOMap: OrderedMap(),
						filterRemovedPosts: noop,
						loading: true,
						noResultsMessage: 'test'
					}
				);

				component.renderUndoCard_ = jest.fn();
				component.renderPost_ = jest.fn();

				component.props.feedPostsIOMap = OrderedMap([[0, Map()]]);

				jest.runAllTimers();

				expect(component.renderUndoCard_).not.toBeCalled();
				expect(component.renderPost_).toBeCalled();

				component.props.feedPostsIOMap = OrderedMap([[0, Map({removedBy: true})]]);

				jest.runAllTimers();

				expect(component.renderUndoCard_).toBeCalled();
			}
		);

		it(
			'should make a fetchPosts request',
			() => {
				component = new Feed(
					{
						feedPostsIOMap: OrderedMap(),
						filterRemovedPosts: noop,
						loading: true
					}
				);

				component.fetch_ = jest.fn();

				component.state.hasMoreResults_ = false;

				component.props.loading = undefined;

				jest.runAllTimers();

				expect(component.fetch_).toBeCalled();
			}
		);

		it(
			'should not make a fetchPosts request',
			() => {
				component = new Feed(
					{
						feedPostsIOMap: OrderedMap(),
						filterRemovedPosts: noop,
						loading: true
					}
				);

				component.fetch_ = jest.fn();

				component.state.hasMoreResults_ = false;

				component.props.loading = false;

				jest.runAllTimers();

				expect(component.fetch_).not.toBeCalled();
			}
		);
	}
);