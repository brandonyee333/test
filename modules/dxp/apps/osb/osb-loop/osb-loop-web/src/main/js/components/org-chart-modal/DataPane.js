import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {bindAll} from 'lodash';
import {List, Map, OrderedSet} from 'immutable';

import EntityDisplay from '../EntityDisplay';
import InfiniteScroll from '../InfiniteScroll';
import LoopConstants from '../../lib/loop-constants';
import NoResultsDisplay from '../NoResultsDisplay';
import Spinner from '../Spinner';
import {classNameIdToSchema} from '../../lib/util';
import {fetchLeads, fetchMembers} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';

const ITEMS_PER_PAGE = 15;

class DataPane extends Component {
	created() {
		bindAll(
			this,
			'provideScrollElement',
			'handleMoreMembers'
		);
	}

	syncId(newId) {
		this.props.fetchLeads(
			{
				end: -1,
				id: newId,
				start: -1
			}
		);
	}

	handleMoreMembers() {
		const {fetchMembers, id, membersIList} = this.props;

		const start = membersIList.size;

		return fetchMembers(
			{
				end: start + ITEMS_PER_PAGE,
				id,
				start
			}
		);
	}

	provideScrollElement() {
		return this.refs.scrollElement;
	}

	render() {
		const {
			divisionIMap,
			leadsIList,
			leadsLoading,
			membersIList,
			membersLoading,
			membersTotal
		} = this.props;

		return (
			<div class="org-chart-data-pane-container">
				<div>
					<EntityDisplay entity={divisionIMap.toJS()} externalLink={true} />
				</div>

				<div ref="scrollElement">
					<div class="category-title">
						{Liferay.Language.get('leads')}

						<span>{leadsIList.size}</span>
					</div>

					{!!leadsIList.size &&
						leadsIList.toJS().map(
							entity => <EntityDisplay entity={entity} externalLink={true} key={entity.entityClassPK} />
						)
					}

					{!leadsLoading && !leadsIList.size &&
						<NoResultsDisplay
							horizontal={true}
							icon="person"
							message={Liferay.Language.get('no-leads')}
							multiplier={1}
							size="small"
						/>
					}

					<div class="category-title">
						{Liferay.Language.get('members')}

						<span>{membersTotal}</span>
					</div>

					<InfiniteScroll
						attachToElement={this.provideScrollElement}
						hasMoreResults={membersTotal !== membersIList.size}
						onScrollEnd={this.handleMoreMembers}
						scrollOffset={200}
					>
						{!!membersIList.size &&
							membersIList.toJS().map(
								entity => <EntityDisplay entity={entity} externalLink={true} key={entity.entityClassPK} />
							)
						}

						{!membersLoading && !membersIList.size &&
							<NoResultsDisplay
								horizontal={true}
								icon="person"
								message={Liferay.Language.get('no-members')}
								multiplier={1}
								size="small"
							/>
						}

						{membersLoading &&
							<Spinner key="spinner" />
						}
					</InfiniteScroll>
				</div>
			</div>
		);
	}
}

const STORE = {
	divisionIMap: Config.instanceOf(Map),
	leadsIList: Config.instanceOf(List),
	leadsLoading: Config.bool(),
	membersIList: Config.instanceOf(List),
	membersLoading: Config.bool(),
	membersTotal: Config.number()
};

DataPane.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, {id}) => {
		const divisionNetworkIMap = state.getIn([classNameIdToSchema(LoopConstants.classNameIds.divisions), id, 'network'], Map());

		return {
			divisionIMap: state.getIn([classNameIdToSchema(LoopConstants.classNameIds.divisions), id, 'data'], Map()),
			leadsIList: getRel('people', state, divisionNetworkIMap.getIn(['leads', 'data'], OrderedSet())),
			leadsLoading: divisionNetworkIMap.getIn(['leads', 'loading'], true),
			membersIList: getRel('people', state, divisionNetworkIMap.getIn(['members', 'data'], OrderedSet())),
			membersLoading: divisionNetworkIMap.getIn(['members', 'loading'], true),
			membersTotal: divisionNetworkIMap.getIn(['members', 'total'])
		};
	},
	{
		fetchLeads,
		fetchMembers
	}
)(DataPane);