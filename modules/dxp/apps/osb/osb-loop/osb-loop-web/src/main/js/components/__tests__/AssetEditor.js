jest.mock('../Editor');

jest.unmock('../../lib/util');
jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import AssetEditor from '../AssetEditor';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import sendRequest from '../../lib/request';
import {addAlert} from '../../actions/alerts';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';
import {fetchLeadingDivisions} from '../../actions/people';
import {updatePost} from '../../actions/posts';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const actionNoop = {
	type: 'NO_OP'
};

const ID = 1;

const state = fromJS(
	{
		dirtyState: {
			[ID]: {}
		},
		people: Map().setIn(
			[currentPersonClassPK, 'data'],
			fromJS(LoopAssets.getPerson(currentPersonClassPK))
		)
	}
);

class AssetEditorExample extends Component {
	render() {
		return (
			<Provider store={mockStore(state)}>
				<AssetEditor
					feedId={ID}
					onSubmit={noop}
					post={LoopAssets.getPost(currentPersonClassPK)}
					ref="assetEditor"
				/>
			</Provider>
		);
	}
}

describe(
	'AssetEditor',
	() => {
		let component;

		fetchLeadingDivisions.mockImplementation(sendRequest);

		addDirtyState.mockReturnValue(actionNoop);
		removeDirtyState.mockReturnValue(actionNoop);
		updatePost.mockReturnValue(actionNoop);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addAlert.mockClear();
				addDirtyState.mockClear();
				removeDirtyState.mockClear();
				updatePost.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new AssetEditorExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should return a string',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				const textContent = assetEditorComponent.getTextContent_();

				expect(typeof textContent).toBe('string');
			}
		);

		it(
			'should call addAlert',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.handleAnnouncementTitleAlert_();

				expect(addAlert).toBeCalled();
			}
		);

		it(
			'should set showPreview_ to be true',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.handlePreview_();

				expect(assetEditorComponent.state.showPreview_).toBe(true);
			}
		);

		it(
			'should set showPreview_ to be false',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.handlePreviewCancel_();

				expect(assetEditorComponent.state.showPreview_).toBe(false);
			}
		);

		it(
			'should return a promise when post is an announcement without a title',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.state.postData_ = {
					...assetEditorComponent.state.postData_,
					announcement: true,
					title: ''
				};

				const retVal = assetEditorComponent.handleSubmit_();

				expect(retVal instanceof Promise).toBe(true);
			}
		);

		it(
			'should call handleUpdatePostAction_ when submiting a valid post',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				const spy = jest.fn();

				assetEditorComponent.handleUpdatePostAction_ = spy;

				assetEditorComponent.handleSubmit_();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should call setState',
			() => {
				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				const spy = jest.fn();

				assetEditorComponent.setState = spy;

				assetEditorComponent.setPostData_();

				expect(spy).toBeCalled();
			}
		);

		it(
			'should create an error alert on promise reject',
			() => {
				updatePost.mockReturnValue(Promise.reject({reason: 'tests'}));

				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.handleUpdatePostAction_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(assetEditorComponent.state.loading_).toBeFalsy();
			}
		);

		it(
			'should not create an error alert on promise resolve',
			() => {
				updatePost.mockReturnValue(Promise.resolve());

				component = new AssetEditorExample();

				const connectFn = component.components.assetEditor;

				const assetEditorComponent = connectFn.components.child;

				assetEditorComponent.handleUpdatePostAction_();

				jest.runAllTimers();

				expect(addAlert).not.toBeCalled();
			}
		);
	}
);