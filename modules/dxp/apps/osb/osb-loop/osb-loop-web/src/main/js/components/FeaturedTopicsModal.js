import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import EntityListModal from './EntityListModal';
import {fetchFeaturedTopics} from '../actions/topics';
import {getRel} from '../lib/selectors';

class FeaturedTopicsModal extends Component {
	created() {
		this._request = this.props.fetchFeaturedTopics().catch(noop);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		const {hideModal, loading, topicsIList} = this.props;

		return (
			<EntityListModal
				hideModal={hideModal}
				items={topicsIList.toJS()}
				loading={loading}
				title={Liferay.Language.get('featured-topics')}
			/>
		);
	}
}

const STORE = {
	fetchFeaturedTopics: Config.func(),
	loading: Config.bool().value(true),
	topicsIList: Config.instanceOf(List)
};

FeaturedTopicsModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

export default connect(
	state => (
		{
			loading: state.getIn(['lists', 'featuredTopics', 'loading']),
			topicsIList: getRel('topics', state, state.getIn(['lists', 'featuredTopics', 'data'], OrderedSet()))
		}
	),
	{
		fetchFeaturedTopics
	}
)(FeaturedTopicsModal);