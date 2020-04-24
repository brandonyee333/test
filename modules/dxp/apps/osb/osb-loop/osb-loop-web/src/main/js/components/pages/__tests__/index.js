jest.unmock('../../../actions/crud');
jest.unmock('../../../lib/selectors');

import Component, {Config} from 'metal-jsx';
import {Provider} from 'metal-redux';

import mockStore from '../../../tests/mock-store';
import Pages from '../index';
import {fetchPages} from '../../../actions/pages';

class PagesExample extends Component {
	render() {
		const {id, permissionAdd} = this.props;

		return (
			<Provider store={mockStore()}>
				<Pages
					displayURL={'/pages'}
					id={id}
					ownerId={0}
					permissionAdd={permissionAdd}
					ref="pages"
					reverseSort={true}
					selectedField="title_sortable"
				/>
			</Provider>
		);
	}
}

PagesExample.PROPS = {
	id: Config.number(),
	permissionAdd: Config.bool()
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

				fetchPages.mockClear();
			}
		);

		it(
			'renders',
			() => {
				fetchPages.mockReturnValue(Promise.resolve());

				component = new PagesExample({permissionAdd: true});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render list without a create button',
			() => {
				fetchPages.mockReturnValue(Promise.resolve());

				component = new PagesExample({permissionAdd: false});

				expect(component).toMatchSnapshot();
			}
		);
	}
);