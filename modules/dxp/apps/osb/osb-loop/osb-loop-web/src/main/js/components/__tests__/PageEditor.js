jest.unmock('../../actions/crud');
jest.unmock('../../lib/selectors');

import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import PageEditor from '../PageEditor';
import sendRequest from '../../lib/request';
import {addAlert} from '../../actions/alerts';
import {addPage, fetchPage, updatePage} from '../../actions/pages';

class PageEditorExample extends Component {
	render() {
		const {id} = this.props;

		const state = fromJS(
			{
				pages: Map().set(1, fromJS({data: LoopAssets.getPage(1), loading: false}))
			}
		);

		return (
			<Provider store={mockStore(state)}>
				<PageEditor
					{...this.otherProps()}
					displayURL="/pages"
					id={id}
					ownerClassNameId={LoopConstants.classNameIds.divisions}
					ownerId={2}
					ref="pageEditor"
				/>
			</Provider>
		);
	}
}

Config.PROPS = {
	id: Config.number()
};

describe(
	'PageEditor',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addPage.mockClear();
				fetchPage.mockClear();
				updatePage.mockClear();
			}
		);

		it(
			'renders',
			() => {
				fetchPage.mockReturnValue(Promise.resolve());

				component = new PageEditorExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders submit button',
			() => {
				component = new PageEditorExample();

				const pageEditorComponent = component.components.pageEditor.components.child;

				pageEditorComponent.refs.title.value = 'test title';
				pageEditorComponent.refs.content.value = 'test content';

				pageEditorComponent.handleContentChange_();
				pageEditorComponent.handleTitleChange_();

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders Title Character Count Warning',
			() => {
				component = new PageEditorExample();

				const pageEditorComponent = component.components.pageEditor.components.child;

				pageEditorComponent.refs.title.value = 'Lorem ipsum dolor sit amet, vel aliquip legimus dissentiunt ea. An per elitr veritus';

				pageEditorComponent.handleTitleChange_();

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should be in edit mode on handleEditClick_',
			() => {
				component = new PageEditorExample();

				sendRequest.mockReturnValue(Promise.resolve('test'));

				const pageEditorComponent = component.components.pageEditor.components.child;

				pageEditorComponent.handlePreviewClick_();

				jest.runAllTimers();

				pageEditorComponent.handleEditClick_();

				jest.runAllTimers();

				expect(component).toMatchSnapshot();

				sendRequest.mockClear();
			}
		);

		it(
			'should be in preview mode on handlePreviewClick_',
			() => {
				component = new PageEditorExample();

				sendRequest.mockReturnValue(Promise.resolve('test'));

				const pageEditorComponent = component.components.pageEditor.components.child;

				pageEditorComponent.handlePreviewClick_();

				jest.runAllTimers();

				expect(component).toMatchSnapshot();

				sendRequest.mockClear();
			}
		);

		it(
			'renders with content',
			() => {
				fetchPage.mockReturnValue(
					Promise.resolve(
						{
							data: {
								payload: {rawMessage: 'test content'},
								title: 'test title'
							}
						}
					)
				);

				component = new PageEditorExample({id: 1});

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call addAlert on handleSubmit_ failure',
			() => {
				addAlert.mockReturnValue(Promise.resolve());
				updatePage.mockReturnValue(Promise.reject({reason: 'test'}));

				component = new PageEditorExample({id: 1});

				component.components.pageEditor.components.child.handleSubmit_();

				jest.runAllTimers();

				expect(updatePage).toBeCalled();
				expect(addAlert).toBeCalled();
			}
		);

		it(
			'should call addAlert on handleSubmit_ failure',
			() => {
				addAlert.mockReturnValue(Promise.resolve());
				addPage.mockReturnValue(Promise.reject({reason: 'test'}));

				component = new PageEditorExample();

				component.components.pageEditor.components.child.handleSubmit_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
				expect(addPage).toBeCalled();
			}
		);

		it(
			'should call addPage on handleSubmit_',
			() => {
				addPage.mockReturnValue(Promise.resolve());

				component = new PageEditorExample();

				component.components.pageEditor.components.child.handleSubmit_();

				jest.runAllTimers();

				expect(addPage).toBeCalled();
			}
		);

		it(
			'should call updatePage on handleSubmit_',
			() => {
				updatePage.mockReturnValue(Promise.resolve());

				component = new PageEditorExample({id: 1});

				component.components.pageEditor.components.child.handleSubmit_();

				jest.runAllTimers();

				expect(updatePage).toBeCalled();
			}
		);
	}
);