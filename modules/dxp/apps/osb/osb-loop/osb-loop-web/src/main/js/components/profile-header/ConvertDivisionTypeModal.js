import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Button from '../Button';
import Form from '../form';
import LoadingWrapper from '../LoadingWrapper';
import LoopConstants from '../../lib/loop-constants';
import Modal from '../modal';
import sendRequest from '../../lib/request';
import {addAlert, alertTypes} from '../../actions/alerts';
import {getDepartmentSubtypeLabel, getLocationSubtypeLabel, getTypeLabel} from '../../lib/lang-util';
import {lang} from '../../lib/lang-util';
import {setType} from '../../actions/divisions';

const {
	departmentSubtypes: {
		functional,
		regional
	},
	locationSubtypes: {
		office,
		region,
		remote
	},
	types: {
		department,
		location,
		team
	}
} = LoopConstants;

const SUBTYPE_OPTIONS_MAP = {
	[department]: {
		func: getDepartmentSubtypeLabel,
		subtypes: [functional, regional]
	},
	[location]: {
		func: getLocationSubtypeLabel,
		subtypes: [office, region, remote]
	}
};

const TYPE_ARRAY = [department, location, team];

class ConvertDivisionTypeModal extends Component {
	created() {
		bindAll(
			this,
			'handleInputChange_',
			'handleSubmit_',
			'handleTypeChange_'
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
					type: this.state.type_
				}
			}
		);
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				division: {
					entityClassPK,
					name
				},
				hideModal,
				setType
			},
			refs: {form}
		} = this;

		form.validate().then(
			({parentDivisions, subtype, type}) => {
				const parentDivision = parentDivisions[0];

				this.state.loading_ = true;

				return setType(
					{
						id: entityClassPK,
						parentDivisionId: parentDivision && parentDivision.entityClassPK ? parentDivision.entityClassPK : null,
						subtype,
						type
					}
				);
			}
		).then(
			({data}) => {
				this.state.loading_ = false;

				hideModal();

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('you-successfully-updated-x-type'), [name])
					}
				);

				Liferay.Loop.SPA.navigate(data.childLoopDivisionCompositeJSONObject.displayURL);
			}
		).catch(
			({message}) => {
				this.state.loading_ = false;

				if (message && message.length) {
					addAlert(
						{
							alertType: alertTypes.ERROR,
							message
						}
					);
				}
			}
		);
	}

	handleTypeChange_(newType) {
		this.state.type_ = Number(newType);
	}

	renderOption_(type, langFunc) {
		return [<option key={type} value={type}>{langFunc(type)}</option>];
	}

	renderSubtypeSelect_(type) {
		const typeOptions = SUBTYPE_OPTIONS_MAP[type];

		let retVal;

		if (typeOptions) {
			retVal = [
				<Form.Select
					key="subtypeSelect"
					label={Liferay.Language.get('subtype')}
					name="subtype"
					validator="required"
				>
					{
						typeOptions.subtypes.map(
							subtype => this.renderOption_(subtype, typeOptions.func)
						)
					}
				</Form.Select>
			];
		}

		return retVal;
	}

	renderTypeSelect_() {
		return [
			<Form.Select
				key="typeSelect"
				label={Liferay.Language.get('type')}
				name="type"
				onChange={this.handleTypeChange_}
				validator="required"
			>
				{
					TYPE_ARRAY.filter(
						type => {
							return this.props.division.type !== type;
						}
					).map(
						type => this.renderOption_(type, getTypeLabel)
					)
				}
			</Form.Select>
		];
	}

	render() {
		const {
			props: {
				division: {name},
				hideModal
			},
			state: {loading_, type_}
		} = this;

		return (
			<LoadingWrapper elementClasses="convert-division-type-modal-container" loading={loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{lang(Liferay.Language.get('convert-x-type'), [name])}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						{this.renderTypeSelect_()}

						{this.renderSubtypeSelect_(type_)}

						{!!type_ &&
							<div>
								<label class="label-container">{Liferay.Language.get('assign-parent-division')}</label>

								<Form.InputList dataSource={this.handleInputChange_} name="parentDivisions" />
							</div>
						}
					</Form>
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
	setType: Config.func()
};

ConvertDivisionTypeModal.PROPS = {
	...STORE,
	division: Config.object(),
	hideModal: Config.func()
};

ConvertDivisionTypeModal.STATE = {
	loading_: Config.bool().value(false),
	type_: Config.number()
};

export default connect(
	null,
	{
		addAlert,
		setType
	}
)(ConvertDivisionTypeModal);