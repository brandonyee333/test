jest.unmock('../../../lib/selectors');

import Component from 'metal-jsx';
import {List, Map, Range} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import SortableList from '../index';

const {getPage, getPerson} = LoopAssets;

const displayURL = '/pages';

const itemsIList = Range(0, 6).map(
	i => (
		{
			creatorIMap: Map(getPerson(i)),
			itemIMap: Map(getPage(i)).set('id', i)
		}
	)
);

const SORTABLE_COLUMNS = {
	creator: {
		name: Liferay.Language.get('created-by'),
		value: 'userName'
	},
	secondaryInfo: {
		name: Liferay.Language.get('modified'),
		value: 'modifiedDate'
	},
	title: {
		name: Liferay.Language.get('name'),
		value: 'title'
	}
};

const headerPropsIMap = Map(
	{
		id: 0,
		onSortClick: noop,
		reverseSort: true,
		selectedField: 'title',
		sortableColumns: SORTABLE_COLUMNS
	}
);

class SortableListExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<SortableList {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'SortableList',
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
				component = new SortableListExample(
					{
						displayURL,
						hasMoreItems: false,
						headerPropsIMap,
						idAttribute: 'entityClassPK',
						itemsIList: List(itemsIList),
						loading: false,
						onFetchItems: noop
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders loading',
			() => {
				component = new SortableListExample(
					{
						displayURL,
						headerPropsIMap,
						idAttribute: 'entityClassPK',
						itemsIList: List(),
						loading: true,
						onFetchItems: noop
					}
				);

				expect(component).toMatchSnapshot();
			}
		);
	}
);