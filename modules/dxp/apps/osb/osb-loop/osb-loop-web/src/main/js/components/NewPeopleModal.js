import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import EntityListModal from './EntityListModal';
import {fetchNewPeople} from '../actions/people';
import {getRel} from '../lib/selectors';

export class NewPeopleModal extends Component {
	created() {
		this._request = this.props.fetchNewPeople().catch(noop);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		const {hideModal, loading, peopleIList} = this.props;

		return (
			<EntityListModal
				hideModal={hideModal}
				items={peopleIList.toJS()}
				loading={loading}
				title={Liferay.Language.get('new-hires')}
			/>
		);
	}
}

const STORE = {
	fetchNewPeople: Config.func(),
	loading: Config.bool().value(true),
	peopleIList: Config.instanceOf(List)
};

NewPeopleModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

export default connect(
	state => (
		{
			loading: state.getIn(['lists', 'newPeople', 'loading']),
			peopleIList: getRel('people', state, state.getIn(['lists', 'newPeople', 'data'], OrderedSet()))
		}
	),
	{
		fetchNewPeople
	}
)(NewPeopleModal);