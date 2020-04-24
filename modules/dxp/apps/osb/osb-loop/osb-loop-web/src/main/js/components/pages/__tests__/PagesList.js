jest.unmock('../../../actions/crud');
jest.unmock('../../../lib/selectors');

import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {Provider} from 'metal-redux';

import mockStore from '../../../tests/mock-store';
import PagesList from '../PagesList';
import {addAlert} from '../../../actions/alerts';
import {fetchPage, fetchPages} from '../../../actions/pages';

class PagesExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PagesList
					displayURL="/pages"
					hasMorePages={false}
					id={this.props.id}
					ownerId={0}
					ref="pagesList"
					reverseSort={true}
					selectedField="title_sortable"
				/>
			</Provider>
		);
	}
}

PagesExample.PROPS = {
	id: Config.number()
};

describe(
	'Pages',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				addAlert.mockClear();
				fetchPage.mockClear();
				fetchPages.mockClear();
			}
		);

		it(
			'renders',
			() => {
				fetchPages.mockReturnValue(Promise.resolve());

				component = new PagesExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should renders with Page',
			() => {
				fetchPage.mockReturnValue(Promise.resolve());

				component = new PagesExample({id: 1});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should fail to render with Page and call addAlert',
			() => {
				addAlert.mockReturnValue(Promise.resolve());
				fetchPage.mockReturnValue(Promise.reject({reason: 'test'}));

				component = new PagesExample({id: 1});

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
			}
		);

		it(
			'should call fetchPages on handleFetchPages_',
			() => {
				fetchPages.mockReturnValue(Promise.resolve());

				component = new PagesExample();

				component.components.pagesList.components.child.handleFetchPages_();

				expect(fetchPages).toBeCalled();
			}
		);

		it(
			'should call addAlert on handleFetchPages_ failure',
			() => {
				addAlert.mockReturnValue(Promise.resolve());
				fetchPages.mockReturnValue(Promise.reject({reason: 'test'}));

				component = new PagesExample();

				component.components.pagesList.components.child.handleFetchPages_();

				jest.runAllTimers();

				expect(addAlert).toBeCalled();
			}
		);
	}
);