import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import EntityListCard from '../../components/EntityListCard';
import NoResultsDisplay from '../../components/NoResultsDisplay';
import PageListCard from '../../components/PageListCard';
import {lang} from '../../lib/lang-util';
import {setParamValue} from '../../lib/router-util';

import {
	DIVISIONS,
	GROUPS,
	PAGES,
	PEOPLE,
	ROUTER_URLS_MAP,
	TOPICS
} from '../../lib/router-constants';

const {SEARCH_URL} = ROUTER_URLS_MAP;

class EntitiesSummary extends Component {
	render() {
		const {
			divisions,
			itemsPerPage,
			keywords,
			loading,
			pages,
			people,
			topics
		} = this.props;

		const divisionResultsIList = divisions.get('results', List()).take(itemsPerPage);
		const pagesResultsIList = pages.get('results', List()).take(itemsPerPage);
		const peopleResultsIList = people.get('results', List()).take(itemsPerPage);
		const topicResultsIList = topics.get('results', List()).take(itemsPerPage);

		const divisionsTotal = divisions.get('total', 0);
		const pagesTotal = pages.get('total', 0);
		const peopleTotal = people.get('total', 0);
		const topicsTotal = topics.get('total', 0);

		const divisionsResultsCount = divisionResultsIList.size;
		const pagesResultsCount = pagesResultsIList.size;
		const peopleResultsCount = peopleResultsIList.size;
		const topicsResultsCount = topicResultsIList.size;

		return (
			<div class="search-entities-summary-container">
				{!!peopleResultsCount &&
					<EntityListCard
						entitiesIList={peopleResultsIList}
						loading={loading}
						seeMoreLink={setParamValue(`${SEARCH_URL}/${PEOPLE}`, 'q', keywords)}
						seeMoreMessage={peopleTotal > itemsPerPage ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('people')}
						total={peopleTotal}
					/>
				}

				{!!divisionsResultsCount &&
					<EntityListCard
						entitiesIList={divisionResultsIList}
						loading={loading}
						seeMoreLink={setParamValue(`${SEARCH_URL}/${GROUPS}`, 'q', keywords)}
						seeMoreMessage={divisionsTotal > itemsPerPage ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('groups')}
						total={divisionsTotal}
					/>
				}

				{!!topicsResultsCount &&
					<EntityListCard
						entitiesIList={topicResultsIList}
						loading={loading}
						seeMoreLink={setParamValue(`${SEARCH_URL}/${TOPICS}`, 'q', keywords)}
						seeMoreMessage={topicsTotal > itemsPerPage ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('topics')}
						total={topicsTotal}
					/>
				}

				{!!pagesResultsCount &&
					<PageListCard
						loading={loading}
						pagesIList={pagesResultsIList}
						seeMoreLink={setParamValue(`${SEARCH_URL}/${PAGES}`, 'q', keywords)}
						seeMoreMessage={pagesTotal > itemsPerPage ? Liferay.Language.get('see-all') : null}
						title={Liferay.Language.get('pages')}
						total={pagesTotal}
					/>
				}

				{!divisionsResultsCount && !pagesResultsCount && !peopleResultsCount && !topicsResultsCount &&
					<NoResultsDisplay icon="magnifier" message={lang(Liferay.Language.get('we-could-not-find-any-x-for-x'), [Liferay.Language.get('page-person-group-or-topic'), <span class="query" key="query">{keywords}</span>])} />
				}
			</div>
		);
	}
}

const STORE = {
	divisions: Config.object(),
	loading: Config.bool(),
	pages: Config.object(),
	people: Config.object(),
	topics: Config.object()
};

EntitiesSummary.PROPS = {
	...STORE,
	itemsPerPage: Config.number().value(5),
	keywords: Config.string()
};

function updateEntityInSearch(state, schema) {
	return state.getIn(['search', schema], Map()).update(
		'results',
		List(),
		ids => ids.map(
			id => state.getIn([schema, id, 'data'])
		)
	);
}

export default connect(
	state => (
		{
			divisions: updateEntityInSearch(state, DIVISIONS),
			loading: state.getIn(['search', 'loading'], true),
			pages: updateEntityInSearch(state, PAGES),
			people: updateEntityInSearch(state, PEOPLE),
			topics: updateEntityInSearch(state, TOPICS)
		}
	)
)(EntitiesSummary);