import Component from 'metal-jsx';
import {List, Map, Range} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import Card from '../card';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import SortableList from '../sortable-list';

const {getPage, getPerson} = LoopAssets;

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
		name: 'User',
		value: 'creator'
	},
	secondaryInfo: {
		name: 'Date',
		value: 'date'
	},
	title: {
		name: 'File Name',
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

const styles = {
	maxWidth: '849px',
	width: '100%'
};

class SortableListKit extends Component {
	created() {
		this.handleItemClick_ = this.handleItemClick_.bind(this);
	}

	handleItemClick_(value) {
		alert(`You clicked Item: ${value}`);
	}

	render() {
		return (
			<Kit card={false} header="SortableList (visual only)">
				<ElementContainer header="6 Item List">
					<Card style={styles}>
						<Provider store={mockStore()}>
							<Card.Body>
								<SortableList
									hasMoreItems={false}
									headerPropsIMap={headerPropsIMap}
									idAttribute="entityClassPK"
									itemsIList={itemsIList}
									loading={false}
									onFetchItems={noop}
									onItemClick={this.handleItemClick_}
								/>
							</Card.Body>
						</Provider>
					</Card>
				</ElementContainer>

				<ElementContainer header="Loading List">
					<Card style={styles}>
						<Provider store={mockStore()}>
							<Card.Body>
								<SortableList
									headerPropsIMap={headerPropsIMap}
									idAttribute="entityClassPK"
									itemsIList={List()}
									loading={true}
									onFetchItems={noop}
								/>
							</Card.Body>
						</Provider>
					</Card>
				</ElementContainer>
			</Kit>
		);
	};
}

export default SortableListKit;