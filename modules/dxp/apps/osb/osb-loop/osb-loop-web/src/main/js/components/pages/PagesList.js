import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map, OrderedSet} from 'immutable';
import {map, noop} from 'lodash';

import Card from '../card';
import LoopConstants from '../../lib/loop-constants';
import Page from './Page';
import SortableList from '../sortable-list';
import {addAlert, alertTypes} from '../../actions/alerts';
import {fetchPages} from '../../actions/pages';
import {getOrderDirection} from '../../lib/util';
import {getPages} from '../../lib/selectors';

const {classNameIds} = LoopConstants;

const ITEMS_PER_PAGE = 12;

const SORTABLE_COLUMNS = {
	creator: {
		name: Liferay.Language.get('created-by'),
		value: 'creatorName_sortable'
	},
	secondaryInfo: {
		name: Liferay.Language.get('modified'),
		value: 'modifiedTime_sortable'
	},
	title: {
		name: Liferay.Language.get('name'),
		value: 'title_sortable'
	}
};

const SORTABLE_COLUMNS_ARRAY = map(SORTABLE_COLUMNS, column => column.value);

class PagesList extends Component {
	created() {
		this.handleFetchPages_ = this.handleFetchPages_.bind(this);
	}

	handleFetchPages_() {
		const {
			addAlert,
			fetchPages,
			ownerId,
			pagesIList,
			reverseSort,
			selectedField
		} = this.props;

		const pagesCount = pagesIList.size;

		return fetchPages(
			{
				end: pagesCount + ITEMS_PER_PAGE,
				ownerClassNameId: classNameIds.divisions,
				ownerId,
				reverseSort,
				sortFieldName: selectedField,
				start: pagesCount
			}
		).catch(
			({message}) => addAlert(
				{
					alertType: alertTypes.ERROR,
					message: message ? message : Liferay.Language.get('unable-to-fetch-pages')
				}
			)
		);
	}

	render() {
		const {
			displayURL,
			hasMorePages,
			id,
			loading,
			onSortClick,
			pagesIList,
			reverseSort,
			selectedField
		} = this.props;

		return (
			<Card.Body>
				{id &&
					<Page displayURL={displayURL} id={id} />
				}

				{!id &&
					<SortableList
						displayURL={displayURL}
						hasMoreItems={hasMorePages}
						headerPropsIMap={Map(
							{
								onSortClick,
								reverseSort,
								selectedField,
								sortableColumns: SORTABLE_COLUMNS
							}
						)}
						itemsIList={pagesIList}
						loading={loading}
						onFetchItems={this.handleFetchPages_}
					/>
				}
			</Card.Body>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	fetchPages: Config.func(),
	hasMorePages: Config.bool(),
	loading: Config.bool(),
	pagesIList: Config.instanceOf(List)
};

PagesList.PROPS = {
	...STORE,
	displayURL: Config.string(),
	id: Config.number(),
	loading: Config.bool(),
	onFetchPages: Config.func(),
	onSortClick: Config.func().value(noop),
	ownerId: Config.number(),
	pagesIList: Config.instanceOf(List),
	reverseSort: Config.bool(),
	selectedField: Config.oneOf(SORTABLE_COLUMNS_ARRAY).value(SORTABLE_COLUMNS.title.value)
};

export default connect(
	(state, ownProps) => {
		const {
			ownerId,
			reverseSort,
			selectedField
		} = ownProps;

		const divisionIMap = state.getIn(['divisions', ownerId], Map());

		const orderedPagesIMap = divisionIMap.getIn(['pages', selectedField, getOrderDirection(reverseSort)], Map());

		const pagesIList = getPages(state, orderedPagesIMap.get('data', OrderedSet()));

		return {
			hasMorePages: pagesIList.size < divisionIMap.getIn(['data', 'pagesCount'], 0),
			loading: orderedPagesIMap.get('loading', true),
			pagesIList
		};
	},
	{
		addAlert,
		fetchPages
	}
)(PagesList);