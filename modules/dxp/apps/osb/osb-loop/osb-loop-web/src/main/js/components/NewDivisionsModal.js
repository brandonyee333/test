import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {List, OrderedSet} from 'immutable';
import {noop} from 'lodash';

import EntityListModal from './EntityListModal';
import {fetchNewDivisions} from '../actions/divisions';
import {getRel} from '../lib/selectors';

export class NewDivisionsModal extends Component {
	created() {
		this._request = this.props.fetchNewDivisions().catch(noop);
	}

	disposed() {
		this._request.cancel();
	}

	render() {
		const {divisionsIList, hideModal, loading} = this.props;

		return (
			<EntityListModal
				hideModal={hideModal}
				items={divisionsIList.toJS()}
				loading={loading}
				title={Liferay.Language.get('new-groups')}
			/>
		);
	}
}

const STORE = {
	divisionsIList: Config.instanceOf(List),
	fetchNewDivisions: Config.func(),
	loading: Config.bool().value(true)
};

NewDivisionsModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

export default connect(
	state => (
		{
			divisionsIList: getRel('divisions', state, state.getIn(['lists', 'newDivisions', 'data'], OrderedSet())),
			loading: state.getIn(['lists', 'newDivisions', 'loading'])
		}
	),
	{
		fetchNewDivisions
	}
)(NewDivisionsModal);