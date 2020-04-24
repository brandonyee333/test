jest.mock('../../Editor');

jest.unmock('../../../lib/asset-entry-set-utils');
jest.unmock('../../../lib/util');

import Component from 'metal-jsx';
import {ContentState, EditorState} from 'draft-js';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import mockStore from '../../../tests/mock-store';
import {createEmptyPostData, Creator} from '../index';

class CreatorExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Creator
					dirtyState={Map({dirtyState: {}})}
					onPostDataChange={jest.fn()}
					postData={createEmptyPostData()}
					ref="creator"
				/>
			</Provider>
		);
	}
}

describe(
	'Creator',
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
				component = new CreatorExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should add editor mentions to sharedTo array',
			() => {
				component = new CreatorExample();

				const mock = jest.fn();

				const creatorComponent = component.components.creator;

				creatorComponent.handleNewPostData_ = mock;

				creatorComponent.props.postData.sharedTo = [
					{fixed: true},
					{removable: false},
					{removable: true}
				];

				jest.runAllTimers();

				creatorComponent.handleEditorMentions_([{removable: false}]);

				expect(mock).toBeCalledWith(
					{
						sharedTo: [
							{removable: false},
							{fixed: true},
							{removable: true}
						]
					}
				);
			}
		);

		it(
			'should call addFiles',
			() => {
				component = new CreatorExample();

				const creatorComponent = component.components.creator;

				creatorComponent.refs.toolbar.components.child.addFiles = jest.fn();

				creatorComponent.handleFileDrop_(
					{
						dataTransfer: {'test': 'test'}
					}
				);

				expect(creatorComponent.refs.toolbar.components.child.addFiles).toBeCalled();
			}
		);

		it(
			'should call onPostDataChange',
			() => {
				component = new CreatorExample();

				const mock = jest.fn();

				const creatorComponent = component.components.creator;

				creatorComponent.props.onPostDataChange = mock;

				creatorComponent.handlePrivatePostToggle_(false);

				expect(mock).toBeCalled();
			}
		);

		it(
			'should check for url on paste',
			() => {
				const pastedText = 'http://www.yahoo.com';

				component = new CreatorExample();

				const mock = jest.fn();

				const creatorComponent = component.components.creator;

				creatorComponent.handleNewPostData_ = mock;

				creatorComponent.handleEditorPaste_('foo bar');

				expect(mock).not.toBeCalled();

				creatorComponent.handleEditorPaste_(pastedText);

				expect(mock).toBeCalled();
			}
		);

		it(
			'should set linkInputValue to empty string',
			() => {
				component = new CreatorExample();

				const mock = jest.fn();

				const creatorComponent = component.components.creator;

				creatorComponent.handleNewPostData_ = mock;

				creatorComponent.handleLinkDataChange_();

				expect(mock).toBeCalledWith({linkData: undefined, linkInputValue: ''});
			}
		);

		it(
			'should return true if there is imageData',
			() => {
				component = new CreatorExample();

				const creatorComponent = component.components.creator;

				expect(creatorComponent.hasContent_()).toBe(false);

				creatorComponent.props.postData.imageData = [0];

				expect(creatorComponent.hasContent_()).toBeTruthy();
			}
		);

		it(
			'should return true if there is linkData',
			() => {
				component = new CreatorExample();

				const creatorComponent = component.components.creator;

				expect(creatorComponent.hasContent_()).toBe(false);

				creatorComponent.props.postData.linkData = {};

				expect(creatorComponent.hasContent_()).toBeTruthy();
			}
		);

		it(
			'should return true if there is text content',
			() => {
				component = new CreatorExample();

				const creatorComponent = component.components.creator;

				expect(creatorComponent.hasContent_()).toBe(false);

				creatorComponent.props.postData.editorState = EditorState.createWithContent(
					ContentState.createFromText('test')
				);

				expect(creatorComponent.hasContent_()).toBeTruthy();
			}
		);
	}
);