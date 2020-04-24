import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import IconLabel from '../IconLabel';
import MenuList from '../menu-list';
import {fetchFollowing} from '../../actions/people';
import {classNameIdToSchema, getIconName} from '../../lib/util';
import {getRel} from '../../lib/selectors';
import {lang} from '../../lib/lang-util';
import {modalTypes, showModal} from '../../actions/modals';

const MAX_ITEMS = 10;

class FollowingListMenu extends Component {
	created() {
		bindAll(
			this,
			'handleOverlayTriggerClick_',
			'showFollowingModal_'
		);

		if (this.props.classNameId) {
			this.fetchFollowing_();
		}
	}

	fetchFollowing_(start = 0, end = MAX_ITEMS) {
		const {classNameId, fetchFollowing, id} = this.props;

		return fetchFollowing(
			{
				classNameId,
				end,
				id,
				start
			}
		);
	}

	showFollowingModal_() {
		const {icon, label, showModal} = this.props;

		this.fetchFollowing_(-1, -1).then(
			response => {
				if (response.error) {
					return;
				}

				showModal(
					{
						modalProps: {
							iconName: icon,
							items: response.data.results,
							title: label
						},
						modalType: modalTypes.ENTITY_LIST
					}
				);
			}
		);
	}

	handleOverlayTriggerClick_() {
		const {overlay} = this.refs;

		if (overlay) {
			overlay.hideOverlay();
		}
	}

	render() {
		const {itemsIList, total} = this.props;

		return (
			<MenuList>
				<MenuList.Item displayOnly={true}>
					{Liferay.Language.get('following')}
				</MenuList.Item>

				{
					itemsIList.toJS().map(
						({displayURL, name, type}) => (
							<MenuList.Item href={displayURL} key={name}>
								<IconLabel label={name} name={getIconName(type)} size="small" spacing="medium" />
							</MenuList.Item>
						)
					)
				}

				{total > MAX_ITEMS &&
					<MenuList.Item onClick={this.showFollowingModal_}>
						{lang(Liferay.Language.get('plus-x-more'), [total - MAX_ITEMS])}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	itemsIList: Config.instanceOf(List),
	total: Config.number()
};

FollowingListMenu.PROPS = {
	...STORE,
	active: Config.bool().value(false),
	classNameId: Config.number(),
	icon: Config.string(),
	id: Config.number(),
	label: Config.string(),
	triggerClasses: Config.string().value(''),
	url: Config.string()
};

export default connect(
	(state, {classNameId, id}) => {
		const following = state.getIn(['people', id, 'following'], Map());

		const schema = classNameIdToSchema(classNameId);

		return {
			itemsIList: getRel(schema, state, following.getIn([schema, 'data']), List()).take(MAX_ITEMS),
			total: following.getIn([schema, 'total'], 0)
		};
	},
	{
		fetchFollowing,
		showModal
	}
)(FollowingListMenu);