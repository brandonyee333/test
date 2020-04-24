import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import Button from '../Button';
import InputList from '../InputList';
import LoadingWrapper from '../LoadingWrapper';
import LoopConstants from '../../lib/loop-constants';
import Modal from '../modal';
import sendRequest from '../../lib/request';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearLists} from '../../actions/lists';
import {fetchChildDivisions, setChildDivisions} from '../../actions/divisions';
import {getRel} from '../../lib/selectors';
import {getTypeLabel, lang} from '../../lib/lang-util';

const SUBTYPE = LoopConstants.departmentSubtypes.none;

class AssignChildrenModal extends Component {
	created() {
		bindAll(
			this,
			'handleInputChange_',
			'handleGroupChange_',
			'handleSubmit_'
		);

		const {division, fetchChildDivisions} = this.props;

		fetchChildDivisions(
			{
				end: -1,
				id: division.entityClassPK,
				start: -1,
				subtype: SUBTYPE
			}
		);
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
			setChildDivisions
		} = this.props;

		setChildDivisions(
			{
				childDivisions: this.state.items_,
				id: entityClassPK
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

	syncChildDivisionsIList(childDivisionsIList) {
		if (childDivisionsIList && childDivisionsIList.size && !this.props.loading) {
			this.state.items_ = Array.map(
				childDivisionsIList.toJS(),
				childDivision => {
					return {
						...childDivision,
						removable: true
					};
				}
			);
		}
	}

	render() {
		const {division, hideModal, loading} = this.props;

		const typeLabel = getTypeLabel(division.type, true);

		return (
			<LoadingWrapper elementClasses="assign-parent-modal-container" loading={loading} mask={true}>
				<Modal.Header onClose={hideModal}>
					{lang(Liferay.Language.get('x-contains'), [division.name])}
				</Modal.Header>

				<Modal.Body>
					<InputList
						dataSource={this.handleInputChange_}
						items={this.state.items_}
						label={typeLabel}
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
	childDivisionsIList: Config.instanceOf(List).value(List()),
	clearLists: Config.func(),
	fetchChildDivisions: Config.func(),
	loading: Config.bool().value(true),
	setChildDivisions: Config.func()
};

AssignChildrenModal.PROPS = {
	...STORE,
	division: Config.object(),
	hideModal: Config.func()
};

AssignChildrenModal.STATE = {
	items_: Config.value([])
};

export default connect(
	(state, {division}) => {
		const childDivisionsIMap = state.getIn(['divisions', division.entityClassPK, 'network', 'childDivisions', SUBTYPE], Map());

		return {
			childDivisionsIList: getRel('divisions', state, childDivisionsIMap.get('data', List()).toJS(), []),
			loading: childDivisionsIMap.get('loading', true)
		};
	},
	{
		addAlert,
		clearLists,
		fetchChildDivisions,
		setChildDivisions
	}
)(AssignChildrenModal);