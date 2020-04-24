jest.unmock('../../../lib/router-util');
jest.unmock('../../../lib/selectors');
jest.unmock('../../../lib/util');

import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import mockStore from '../../../tests/mock-store';
import sendRequest from '../../../lib/request';
import Toolbar from '../index';
import {isEmpty, range} from 'lodash';

class ToolbarExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<Toolbar ref="toolbar" />
			</Provider>
		);
	}
}

describe(
	'Toolbar',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				sendRequest.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new ToolbarExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call sendRequest on search_',
			() => {
				component = new ToolbarExample();

				const connectFn = component.components.toolbar;

				const toolbarComponent = connectFn.components.child;

				toolbarComponent.search_('test');

				expect(sendRequest).toBeCalled();
			}
		);

		it(
			'should set state searchResults_ value on sortSearchResults_',
			() => {
				const mockSearchResults = [
					...range(0, 5).map(item => LoopAssets.getDivision(item)),
					...range(0, 2).map(item => LoopAssets.getPerson(item)),
					...range(0, 10).map(item => LoopAssets.getTopic(item))
				];

				component = new ToolbarExample();

				const connectFn = component.components.toolbar;

				const toolbarComponent = connectFn.components.child;

				expect(isEmpty(toolbarComponent.state.searchResults_)).toBe(true);

				toolbarComponent.sortSearchResults_(mockSearchResults);

				const searchResults = toolbarComponent.state.searchResults_;

				expect(searchResults.divisions.length).toEqual(5);
				expect(searchResults.people.length).toEqual(2);
				expect(searchResults.topics.length).toEqual(10);
			}
		);

		it(
			'should call Liferay.store.get on getRecentSeaches',
			() => {
				Liferay.Store.get = jest.fn();

				component = new ToolbarExample();

				const connectFn = component.components.toolbar;

				const toolbarComponent = connectFn.components.child;

				toolbarComponent.getRecentSearches_();

				jest.runAllTimers();

				expect(Liferay.Store.get).toBeCalled();
			}
		);
	}
);