import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS} from 'immutable';
import {Provider} from 'metal-redux';

import Comment from '../Comment';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {likePost, updatePost} from '../../actions/posts';
import {showModal} from '../../actions/modals';

class CommentExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Comment
					commentFeedId={0}
					creatorIMap={fromJS(LoopAssets.getPerson())}
					expanded={false}
					id={0}
					postIMap={fromJS(LoopAssets.getPost())}
					ref="comment"
				/>
			</Provider>
		);
	}
}

describe(
	'Comment',
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
				component = new CommentExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call likePost action',
			() => {
				component = new CommentExample();

				likePost.mockReturnValue({type: ''});

				const connectFn = component.components.comment;

				const commentComponent = connectFn.components.child;

				commentComponent.handleLikePost_();

				expect(likePost).toBeCalled();

				likePost.mockClear();
			}
		);

		it(
			'should call showModal action',
			() => {
				component = new CommentExample();

				showModal.mockReturnValue({type: ''});

				const connectFn = component.components.comment;

				const commentComponent = connectFn.components.child;

				commentComponent.showLikedModal_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);

		it(
			'should set editing attribute',
			() => {
				component = new CommentExample();

				const connectFn = component.components.comment;

				const commentComponent = connectFn.components.child;

				commentComponent.onEdit_();

				expect(commentComponent.state.editing_).toBe(true);

				commentComponent.endEdit_();

				expect(commentComponent.state.editing_).toBe(false);
			}
		);

		it(
			'should return a promise from handleSubmit_',
			() => {
				updatePost.mockReturnValue(Promise.resolve());

				component = new CommentExample();

				const connectFn = component.components.comment;

				const commentComponent = connectFn.components.child;

				expect(commentComponent.handleSubmit_() instanceof Promise).toBe(true);
			}
		);
	}
);