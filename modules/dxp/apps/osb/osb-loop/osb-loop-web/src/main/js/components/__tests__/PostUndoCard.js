import Promise from 'metal-promise';
import {fromJS} from 'immutable';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PostUndoCard from '../PostUndoCard';
import {addAlert} from '../../actions/alerts';
import {bookmarkPost, followPost} from '../../actions/posts';
import {removePost} from '../../actions/feeds';

describe(
	'PostUndoCard',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new PostUndoCard(
					{
						feedId: 0,
						postIMap: fromJS(LoopAssets.getPost()),
						removedBy: 'bookmark',
						store: mockStore()
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'calls the bookmarkPost action and the addAlert action',
			() => {
				bookmarkPost.mockReturnValue(Promise.resolve());

				component = new PostUndoCard(
					{
						feedId: 0,
						postIMap: fromJS(LoopAssets.getPost()),
						removedBy: 'bookmark',
						store: mockStore()
					}
				);

				const childComponent = component.components.child;

				childComponent.handleUndo_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(bookmarkPost).toBeCalled();
				expect(followPost).not.toBeCalled();

				bookmarkPost.mockClear();
			}
		);

		it(
			'calls the followPost action and the addAlert action',
			() => {
				followPost.mockReturnValue(Promise.resolve());

				component = new PostUndoCard(
					{
						feedId: 0,
						postIMap: fromJS(LoopAssets.getPost()),
						removedBy: 'follow',
						store: mockStore()
					}
				);

				const childComponent = component.components.child;

				childComponent.handleUndo_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(bookmarkPost).not.toBeCalled();
				expect(followPost).toBeCalled();

				followPost.mockClear();
			}
		);

		it(
			'calls the removePost action',
			() => {
				component = new PostUndoCard(
					{
						feedId: 0,
						postIMap: fromJS(LoopAssets.getPost()),
						removedBy: 'follow',
						store: mockStore()
					}
				);

				const childComponent = component.components.child;

				childComponent.handleClose_();

				jest.runAllTimers();

				expect(removePost).toBeCalled();

				removePost.mockClear();
			}
		);
	}
);