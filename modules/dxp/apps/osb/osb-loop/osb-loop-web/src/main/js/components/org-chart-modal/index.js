import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import LoopConstants from '../../lib/loop-constants';
import Modal from '../modal';
import NoResultsDisplay from '../NoResultsDisplay';
import OrgChart from './OrgChart';
import DataPane from './DataPane';
import {fetchChildDivisions, fetchHierarchy} from '../../actions/divisions';
import {filterChildDepartments} from '../../lib/util';
import {getPluralMessage} from '../../lib/lang-util';

const {departmentSubtypes, types} = LoopConstants;

function processChildDepartments(results) {
	let retVal;

	const typesObj = filterChildDepartments(results);

	const functionalDepartments = typesObj[departmentSubtypes.functional];
	const regionalDepartments = typesObj[departmentSubtypes.regional];

	const totalFunctional = functionalDepartments.length;
	const totalRegional = regionalDepartments.length;

	if (totalFunctional && totalRegional) {
		retVal = [
			{
				children: functionalDepartments,
				entityClassPK: 'functional',
				name: getPluralMessage(Liferay.Language.get('x-subdepartment'), Liferay.Language.get('x-subdepartments'), totalFunctional, true),
				type: null
			},
			{
				children: regionalDepartments,
				entityClassPK: 'regional',
				name: getPluralMessage(Liferay.Language.get('x-regional'), Liferay.Language.get('x-regionals'), totalRegional, true),
				type: null
			}
		];
	}
	else if (totalFunctional) {
		retVal = functionalDepartments;
	}
	else if (totalRegional) {
		retVal = regionalDepartments;
	}

	return retVal;
}

function processHierarchy({childLoopDivisionCompositeJSONArray, loopDivisionCompositeJSONObject}) {
	const data = loopDivisionCompositeJSONObject;

	if (childLoopDivisionCompositeJSONArray.length > 0) {
		data.children = processChildDepartments(
			childLoopDivisionCompositeJSONArray.map(
				childHierarchy => processHierarchy(childHierarchy)
			)
		);
	}

	return data;
}

class OrgChartModal extends Component {
	created() {
		bindAll(
			this,
			'handleNodeClick',
			'setSelection',
			'handleInitialLoad'
		);

		const {fetchHierarchy, id} = this.props;

		fetchHierarchy(id, types.department).then(
			({data}) => {
				this.setState(
					{
						data_: processHierarchy(data),
						selectedId_: id ? id : data.loopDivisionCompositeJSONObject.entityClassPK
					}
				);
			}
		);
	}

	handleInitialLoad() {
		this.state.loading_ = false;
	}

	handleNodeClick(id) {
		return this.props.fetchChildDivisions(
			{
				end: -1,
				id,
				start: -1
			}
		).then(
			({data}) => {
				const {results} = data;

				return results ? processChildDepartments(results) : null;
			}
		);
	}

	setSelection(id) {
		this.state.selectedId_ = id;
	}

	render() {
		const {
			props: {hideModal},
			state: {
				loading_,
				selectedId_
			}
		} = this;

		return (
			<div class="org-chart-modal-container">
				<Modal.Header full={true} onClose={hideModal}>
					{Liferay.Language.get('org-chart')}
				</Modal.Header>

				<Modal.Body>
					{this.state.data_ &&
						<OrgChart
							data={this.state.data_}
							onLoaded={this.handleInitialLoad}
							onNodeClick={this.setSelection}
							requestChildren={this.handleNodeClick}
							selectedId={selectedId_}
						/>
					}

					{selectedId_ && !loading_ &&
						<DataPane id={selectedId_} />
					}

					{!selectedId_ && !loading_ &&
						<NoResultsDisplay
							icon="people"
							message={Liferay.Language.get('select-a-department-to-see-members')}
							title={Liferay.Language.get('no-department-selected')}
						/>
					}
				</Modal.Body>
			</div>
		);
	}
}

const STORE = {
	fetchChildDivisions: Config.func(),
	fetchHierarchy: Config.func()
};

OrgChartModal.PROPS = {
	...STORE,
	hideModal: Config.func(),
	id: Config.number()
};

OrgChartModal.STATE = {
	data_: Config.object().value(null),
	loading_: Config.bool().value(true),
	selectedId_: Config.number()
};

export default connect(
	null,
	{
		fetchChildDivisions,
		fetchHierarchy
	}
)(OrgChartModal);