import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Button from '../Button';
import InputList from '../InputList';
import LoadingWrapper from '../LoadingWrapper';
import LoopConstants from '../../lib/loop-constants';
import Modal from '../modal';
import sendRequest from '../../lib/request';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearLists} from '../../actions/lists';
import {fetchParentDivision, setParentDivision} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';
import {getTypeLabel, lang} from '../../lib/lang-util';

class AssignParentModal extends Component {
	created() {
		bindAll(
			this,
			'handleInputChange_',
			'handleGroupChange_',
			'handleSubmit_'
		);

		const {division, fetchParentDivision} = this.props;

		fetchParentDivision(division.entityClassPK);
	}

	handleInputChange_(query) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: query,
					searchLimit: 5,
					type: this.props.division.type
				}
			}
		);
	}

	handleGroupChange_(items) {
		this.state.items_ = items;
	}

	handleSubmit_() {
		const {
			addAlert,
			clearLists,
			division: {
				entityClassPK,
				name
			},
			hideModal,
			setParentDivision
		} = this.props;

		const parentDivision = this.state.items_[0];

		setParentDivision(
			{
				id: entityClassPK,
				parentDivisionId: parentDivision ? parentDivision.entityClassPK : 0
			}
		).then(
			() => {
				clearLists();

				hideModal();

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('you-successfully-updated-x-network'), [name])
					}
				);
			}
		).catch(
			err => {
				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: err.toString()
					}
				);
			}
		);
	}

	syncParentDivisionIMap(parentDivisionIMap) {
		if (parentDivisionIMap && !parentDivisionIMap.isEmpty() && parentDivisionIMap.get('type') !== LoopConstants.types.root && !this.props.loading) {
			this.state.items_ = [
				{
					...parentDivisionIMap.toJS(),
					removable: true
				}
			];
		}
	}

	render() {
		const {division, hideModal, loading} = this.props;

		const typeLabel = getTypeLabel(division.type);

		return (
			<LoadingWrapper elementClasses="assign-parent-modal-container" loading={loading} mask={true}>
				<Modal.Header onClose={hideModal}>
					{lang(Liferay.Language.get('x-belongs-to'), [division.name])}
				</Modal.Header>

				<Modal.Body>
					<InputList
						dataSource={this.handleInputChange_}
						items={this.state.items_}
						label={typeLabel}
						multiple={false}
						onChange={this.handleGroupChange_}
						placeholder={lang(Liferay.Language.get('assign-x'), [typeLabel]).join(' ')}
					/>
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={hideModal} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('save')}</Button>
				</Modal.Footer>
			</LoadingWrapper>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearLists: Config.func(),
	fetchParentDivision: Config.func(),
	loading: Config.bool().value(true),
	parentDivisionIMap: Config.instanceOf(Map).value(Map()),
	setParentDivision: Config.func()
};

AssignParentModal.PROPS = {
	...STORE,
	division: Config.object(),
	hideModal: Config.func()
};

AssignParentModal.STATE = {
	items_: Config.value([])
};

export default connect(
	(state, {division}) => {
		const parentDivisionIMap = state.getIn(['divisions', division.entityClassPK, 'network', 'parentDivision'], Map());

		return {
			loading: parentDivisionIMap.get('loading', true),
			parentDivisionIMap: getRel('divisions', state, parentDivisionIMap.get('data', -1), Map())
		};
	},
	{
		addAlert,
		clearLists,
		fetchParentDivision,
		setParentDivision
	}
)(AssignParentModal);