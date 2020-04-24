import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IndexPagination from '../../components/IndexPagination';
import NoResultsDisplay from '../../components/NoResultsDisplay';
import {classNameIdToSchema} from '../../lib/util';
import {getClassNameIdLabel, lang} from '../../lib/lang-util';

const ITEMS_PER_PAGE = 24;

class EntityTab extends Component {
	created() {
		this.fetch_ = this.fetch_.bind(this);
	}

	fetch_() {
		const {entitiesIList, query, searchMethod} = this.props;

		const start = entitiesIList.size;

		return searchMethod(
			{
				end: start + ITEMS_PER_PAGE,
				itemsPerPage: ITEMS_PER_PAGE,
				keywords: query,
				start
			}
		);
	}

	render() {
		const {
			classNameId,
			entitiesIList,
			hasMoreResults,
			loading,
			query,
			total
		} = this.props;

		return (
			<div>
				{!!total &&
					<IndexPagination
						entitiesIList={entitiesIList}
						hasMoreResults={hasMoreResults}
						infiniteScroll={true}
						loading={loading}
						onScrollEnd={this.fetch_}
					/>
				}

				{!total &&
					<NoResultsDisplay icon="magnifier" message={lang(Liferay.Language.get('we-could-not-find-any-x-for-x'), [getClassNameIdLabel(classNameId), <span class="query" key="query">{`'${query}'`}</span>])} />
				}
			</div>
		);
	}
}

const STORE = {
	entitiesIList: Config.instanceOf(List),
	hasMoreResults: Config.bool(),
	loading: Config.bool(),
	total: Config.number()
};

EntityTab.PROPS = {
	...STORE,
	classNameId: Config.number(),
	query: Config.string(),
	searchMethod: Config.func()
};

EntityTab.STATE = {
	hasMoreResults_: Config.value(true)
};

export default connect(
	(state, {classNameId}) => {
		const schema = classNameIdToSchema(classNameId);

		const entitySearchIMap = state.getIn(['search', schema], Map());

		const entitiesIList = entitySearchIMap.get('results', List()).map(
			id => state.getIn([schema, id, 'data'])
		);

		const total = entitySearchIMap.get('total');

		return {
			entitiesIList,
			hasMoreResults: total > entitiesIList.size,
			loading: entitySearchIMap.get('loading', false),
			total
		};
	}
)(EntityTab);