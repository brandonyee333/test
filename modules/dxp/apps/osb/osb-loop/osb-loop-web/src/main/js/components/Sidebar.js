import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IconLabel from './IconLabel';
import LoopConstants from '../lib/loop-constants';
import {fetchFollowing} from '../actions/people';
import {getIconName} from '../lib/util';
import {getRel} from '../lib/selectors';
import {lang} from '../lib/lang-util';
import {modalTypes, showModal} from '../actions/modals';

const MAX_ITEMS = 5;

const {classNameIds, urls} = LoopConstants;

const SidebarItem = ({children}) => <li role="presentation">{children}</li>;

const FollowList = ({itemsIList, seeMoreClick, total}) => (
	<ul class="follow-list">
		{
			itemsIList.toJS().map(
				item => (
					<li key={item.entityClassPK}>
						<a href={item.displayURL}>
							<IconLabel
								label={item.name}
								name={getIconName(item.type)}
								ref={item.entityClassPK}
								size="small"
								spacing="medium"
							/>
						</a>
					</li>
				)
			)
		}

		{total > MAX_ITEMS &&
			<li>
				<a class="see-more" href="javascript:;" onClick={seeMoreClick}>
					{lang(Liferay.Language.get('plus-x-more'), [total - MAX_ITEMS])}
				</a>
			</li>
		}
	</ul>
);

const SidebarHeader = ({icon, label, url}) => (
	<h2 class="sidebar-header">
		<a href={url}>
			<IconLabel
				display="primary"
				label={label}
				name={icon}
				spacing="large"
			/>
		</a>
	</h2>
);

class Sidebar extends Component {
	created() {
		bindAll(
			this,
			'_showDivisionsModal',
			'_showTopicsModal',
			'_showModal'
		);

		this._fetchFollowing('topics');
		this._fetchFollowing('divisions');
	}

	_fetchFollowing(classNameId, start = 0, end = MAX_ITEMS) {
		const {fetchFollowing, id} = this.props;

		return fetchFollowing(
			{
				classNameId,
				end,
				id,
				start
			}
		);
	}

	_showDivisionsModal() {
		this._showModal('divisions');
	}

	_showModal(classNameId) {
		this._fetchFollowing(classNameId, -1, -1).then(
			response => {
				if (response.error) {
					return;
				}

				let iconName = 'groups';
				let title = Liferay.Language.get('groups');

				if (classNameId === classNameIds.topics) {
					iconName = getIconName('topic');
					title = Liferay.Language.get('topics');
				}

				this.props.showModal(
					{
						modalProps: {
							iconName,
							items: response.data.results,
							title
						},
						modalType: modalTypes.ENTITY_LIST
					}
				);
			}
		);
	}

	_showTopicsModal() {
		this._showModal('topics');
	}

	render() {
		return (
			<ul class="sidebar-container" role="menubar">
				<SidebarItem>
					<SidebarHeader
						icon="house"
						label={Liferay.Language.get('home')}
						url={urls.home}
					/>
				</SidebarItem>

				<SidebarItem>
					<SidebarHeader
						icon="persons"
						label={Liferay.Language.get('people')}
						url={urls.people}
					/>
				</SidebarItem>

				<SidebarItem>
					<SidebarHeader
						icon="groups"
						label={Liferay.Language.get('groups')}
						url={urls.groups}
					/>

					<FollowList
						itemsIList={this.props.divisionsIList.take(MAX_ITEMS)}
						seeMoreClick={this._showDivisionsModal}
						total={this.props.divisionsTotal}
					/>
				</SidebarItem>

				<SidebarItem>
					<SidebarHeader
						icon="balloon-topic"
						label={Liferay.Language.get('topics')}
						url={urls.topics}
					/>

					<FollowList
						itemsIList={this.props.topicsIList.take(MAX_ITEMS)}
						seeMoreClick={this._showTopicsModal}
						total={this.props.topicsTotal}
					/>
				</SidebarItem>
			</ul>
		);
	}
}

const STORE = {
	divisionsIList: Config.instanceOf(List),
	divisionsTotal: Config.number(),
	fetchFollowing: Config.func(),
	showModal: Config.func(),
	topicsIList: Config.instanceOf(List),
	topicsTotal: Config.number()
};

Sidebar.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	(state, ownProps) => {
		const following = state.getIn(['people', ownProps.id, 'following'], Map());

		return {
			divisionsIList: getRel('divisions', state, following.getIn(['divisions', 'data']), List()),
			divisionsTotal: following.getIn(['divisions', 'total'], 0),
			topicsIList: getRel('topics', state, following.getIn(['topics', 'data']), List()),
			topicsTotal: following.getIn(['topics', 'total'], 0)
		};
	},
	{
		fetchFollowing,
		showModal
	}
)(Sidebar);