import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';
import {bindAll} from 'lodash';

import MenuList from '../menu-list';
import {modalTypes, showModal} from '../../actions/modals';
import {removeOwnTopic, setOwnTopic} from '../../actions/topics';
import {topicSelector} from '../../lib/selectors';

class TopicProfileMenu extends Component {
	created() {
		bindAll(
			this,
			'handleMergeModal_',
			'toggleExpert_'
		);
	}

	handleMergeModal_() {
		const {showModal, topicIMap} = this.props;

		showModal(
			{
				hideOnBlur: false,
				modalProps: {
					topic: topicIMap.toJS()
				},
				modalType: modalTypes.MERGE_TOPICS
			}
		);
	}

	toggleExpert_() {
		const {
			id,
			removeOwnTopic,
			setOwnTopic,
			topicIMap
		} = this.props;

		if (topicIMap.get('expert')) {
			removeOwnTopic(id);
		}
		else {
			setOwnTopic(id);
		}
	}

	render() {
		const {displayURL, expert, permissionSetParent} = this.props.topicIMap.toJS();

		return (
			<MenuList>
				<MenuList.Item href={`${displayURL}/edit`}>
					{Liferay.Language.get('edit-topic')}
				</MenuList.Item>

				<MenuList.Item onClick={this.toggleExpert_}>
					{expert ? Liferay.Language.get('oops-i-am-not-an-expert') : Liferay.Language.get('i-am-an-expert')}
				</MenuList.Item>

				{permissionSetParent &&
					<MenuList.Item onClick={this.handleMergeModal_}>
						{Liferay.Language.get('merge-with-another-topic')}
					</MenuList.Item>
				}
			</MenuList>
		);
	}
}

const STORE = {
	showModal: Config.func(),
	topicIMap: Config.instanceOf(Map)
};

TopicProfileMenu.PROPS = {
	...STORE,
	id: Config.number()
};

export default connect(
	topicSelector,
	{
		removeOwnTopic,
		setOwnTopic,
		showModal
	}
)(TopicProfileMenu);