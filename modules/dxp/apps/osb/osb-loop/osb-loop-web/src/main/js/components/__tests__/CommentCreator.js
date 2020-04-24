jest.mock('../Editor');

jest.unmock('../../lib/asset-entry-set-utils');
jest.unmock('../../lib/selectors');

import {fromJS, Map} from 'immutable';

import CommentCreator from '../CommentCreator';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import {addDirtyState, removeDirtyState} from '../../actions/dirty-state';

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

describe(
	'CommentCreator',
	() => {
		const storeState = fromJS(
			{
				dirtyState: Map(),
				people: Map().mergeIn(
					[currentPersonClassPK, 'data'],
					fromJS(LoopAssets.getPerson(currentPersonClassPK))
				),
				posts: Map().mergeIn(
					[1, 'data'],
					fromJS(LoopAssets.getPost(0))
				)
			}
		);

		const store = mockStore(storeState);

		function getComponent(config) {
			return new CommentCreator(
				{
					parentId: 0,
					store,
					submitMethod: jest.fn(),
					...config
				}
			);
		}

		let component;

		const actionNoop = {
			type: 'NO_OP'
		};

		addDirtyState.mockReturnValue(actionNoop);
		removeDirtyState.mockReturnValue(actionNoop);

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addDirtyState.mockClear();
				removeDirtyState.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = getComponent();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call submitMethod on handleSubmit_',
			() => {
				const promise = Promise.resolve(true);

				const spy = jest.fn().mockReturnValue(promise);

				component = getComponent(
					{
						submitMethod: spy
					}
				);

				const commentCreator = component.components.child;

				commentCreator.hasText = jest.fn().mockReturnValue(true);

				expect(spy).not.toBeCalled();

				commentCreator.handleSubmit_({});

				expect(spy).toBeCalled();

				return promise;
			}
		);

		it(
			'should return false for initial state',
			() => {
				component = getComponent();

				expect(component.components.child.hasText()).toBe(false);
			}
		);

		it(
			'should render existing post',
			() => {
				const id = 1;

				const text = storeState.getIn(['posts', id, 'data', 'payload', 'rawMessage']);

				component = getComponent({id});

				const content = component.refs.child.refs.editor.props.editorState
					.getCurrentContent()
					.getPlainText();

				expect(component.components.child.hasText()).toBe(true);
				expect(content).toContain(text);
			}
		);

		it(
			'should add an entity on insertMention',
			() => {
				const id = 444;

				component = getComponent();

				const commentCreatorComponent = component.refs.child;

				commentCreatorComponent.insertMention(Map(LoopAssets.getPerson(id)));

				const content = commentCreatorComponent.refs.editor.props.editorState
					.getCurrentContent()
					.getPlainText();

				expect(commentCreatorComponent.hasText()).toBe(true);
				expect(content).toContain(id);
			}
		);
	}
);