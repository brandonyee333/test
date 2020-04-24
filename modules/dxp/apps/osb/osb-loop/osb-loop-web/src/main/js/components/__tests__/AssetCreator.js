jest.mock('../Editor');

jest.unmock('../../actions/dirty-state');
jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/loop-constants');
jest.unmock('../../lib/util');

import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import AssetCreator from '../AssetCreator';
import mockStore from '../../tests/mock-store';
import {createEmptyPostData} from '../creator';
import {createPost} from '../../actions/posts';
import {addAlert} from '../../actions/alerts';
import {showModal} from '../../actions/modals';

const state = Map().setIn(
	['dirtyState', 0],
	Map()
);

class AssetCreatorExample extends Component {
	render() {
		return (
			<Provider store={mockStore(state)}>
				<AssetCreator ref="assetCreator" streamId={0} />
			</Provider>
		);
	}
}

describe(
	'AssetCreator',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addAlert.mockClear();
				createPost.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new AssetCreatorExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should return an empty state object',
			() => {
				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				const emptyState = assetCreatorComponent.getEmptyState_();

				expect(emptyState.toString()).toEqual(createEmptyPostData().toString());
			}
		);

		it(
			'should call addAlert',
			() => {
				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.handleAnnouncementTitleAlert_();

				expect(addAlert).toBeCalled();
			}
		);

		it(
			'should not create an error alert on promise resolve',
			() => {
				createPost.mockReturnValue(Promise.resolve());

				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.handleCreatePostAction_();

				jest.runAllTimers();

				expect(addAlert).not.toBeCalled();
				expect(assetCreatorComponent.state.loading_).toBeFalsy();
			}
		);

		it(
			'should create an error alert promise reject',
			() => {
				createPost.mockReturnValue(Promise.reject({reason: 'test'}));

				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.handleCreatePostAction_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(assetCreatorComponent.state.loading_).toBeFalsy();
			}
		);

		it(
			'should toggle minimize',
			() => {
				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.toggleMinimize_();

				expect(assetCreatorComponent.state.minimized_).toBe(false);
			}
		);

		it(
			'should call showModal',
			() => {
				showModal.mockReturnValue({type: ''});

				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.handlePreview_();

				expect(showModal).toBeCalled();

				showModal.mockClear();
			}
		);

		it(
			'should set minimized to false',
			() => {
				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				assetCreatorComponent.maximize_();

				expect(assetCreatorComponent.state.minimized_).toBe(false);
			}
		);

		it(
			'should process post data for API request',
			() => {
				component = new AssetCreatorExample();

				const connectFn = component.components.assetCreator;

				const assetCreatorComponent = connectFn.components.child;

				const retVal = assetCreatorComponent.processData_();

				const expectRet = {
					creatorClassNameId: 3,
					creatorClassPK: 100,
					id: null,
					parentAssetEntrySetId: 0,
					payload: '{"assetTagNames":"","imageData":null,"linkData":null,"message":"","sendEmailNotifications":false,"sharedTo":[],"type":"text"}',
					privateAssetEntrySet: false,
					stickyTime: null,
					title: '',
					type: 0
				};

				expect(retVal).toEqual(expectRet);
			}
		);
	}
);