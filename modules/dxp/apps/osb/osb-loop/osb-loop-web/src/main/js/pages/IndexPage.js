import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {List} from 'immutable';

import BaseLayout from './BaseLayout';
import CreateEntityCard from '../components/CreateEntityCard';
import IndexPagination from '../components/IndexPagination';
import {classNameIdToSchema} from '../lib/util';
import {fetchIndex} from '../actions/lists';

const ITEMS_PER_PAGE = 24;

class IndexPage extends Component {
	created() {
		bindAll(
			this,
			'fetch_'
		);

		this._createTime = Date.now();
	}

	getModalObj() {
		return {
			hideOnBlur: false,
			modalProps: {},
			modalType: this.props.modalType
		};
	}

	fetch_() {
		const {
			classNameId,
			entitiesIList,
			fetchIndex,
			listName,
			requestParams,
			status
		} = this.props;

		const start = entitiesIList.size;

		return fetchIndex(
			{
				...requestParams,
				classNameId,
				createTime: start ? this._createTime : Date.now(),
				end: start + ITEMS_PER_PAGE,
				listName: listName || classNameIdToSchema(classNameId),
				start,
				status
			}
		).then(
			({data}) => {
				this.state.hasMoreResults_ = data.results.length > 0;
			}
		);
	}

	syncClassNameId() {
		this.state.hasMoreResults_ = true;
	}

	syncListName() {
		this.state.hasMoreResults_ = true;
	}

	render() {
		const {
			props: {
				bottomNavContent,
				createLabel,
				createPermission,
				elementClasses,
				entitiesIList,
				hasBeenRequested,
				loading,
				navItemsIList
			},
			state: {hasMoreResults_}
		} = this;

		return (
			<BaseLayout
				bottomNavContent={bottomNavContent}
				content={[
					<BaseLayout.NineColumn key="nineColumn">
						<IndexPagination
							entitiesIList={entitiesIList}
							hasMoreResults={hasMoreResults_ || !hasBeenRequested}
							infiniteScroll={true}
							loading={loading}
							onScrollEnd={this.fetch_}
						/>
					</BaseLayout.NineColumn>
				]}
				elementClasses={`index-container ${elementClasses}`}
				leftContent={createPermission ? [<CreateEntityCard key="createEntityCard" label={createLabel} modalConfig={this.getModalObj()} />] : null}
				navItemsIList={navItemsIList}
			/>
		);
	}
}

const STORE = {
	entitiesIList: Config.instanceOf(List),
	hasBeenRequested: Config.bool(),
	loading: Config.bool()
};

IndexPage.PROPS = {
	...STORE,
	bottomNavContent: Config.any(),
	classNameId: Config.number(),
	createLabel: Config.string(),
	createPermission: Config.bool(),
	listName: Config.string(),
	modalType: Config.string(),
	navItemsIList: Config.instanceOf(List),
	requestParams: Config.object(),
	status: Config.number()
};

IndexPage.STATE = {
	hasMoreResults_: Config.bool().value(true)
};

export default connect(
	(state, {classNameId, listName}) => {
		const schema = classNameIdToSchema(classNameId);

		if (!listName) {
			listName = schema;
		}

		return {
			classNameId,
			entitiesIList: state.getIn(
				['lists', listName, 'data'],
				List()
			).map(
				classPK => state.getIn(
					[schema, classPK, 'data'],
					List()
				)
			),
			hasBeenRequested: !!state.getIn(['lists', listName]),
			loading: state.getIn(['lists', listName, 'loading'], true)
		};
	},
	{
		fetchIndex
	}
)(IndexPage);