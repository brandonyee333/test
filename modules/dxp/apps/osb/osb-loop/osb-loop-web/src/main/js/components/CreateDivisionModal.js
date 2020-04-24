import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Form from './form';
import LoadingWrapper from './LoadingWrapper';
import LoopConstants from '../lib/loop-constants';
import MarkdownEditor from './MarkdownEditor';
import Modal from './modal';
import sendRequest from '../lib/request';
import {addAlert, alertTypes} from '../actions/alerts';
import {addDivision} from '../actions/divisions';
import {assert} from '../lib/form-validators';
import {clearLists} from '../actions/lists';
import {lang} from '../lib/lang-util';

const {person, team} = LoopConstants.types;

const validatorObj = {
	customValidator: name => sendRequest(
		{
			controller: 'divisions',
			controllerMethod: 'validate.json',
			data: {name}
		}
	).then(
		({reason, valid}) => assert(valid, reason)
	),
	required: true
};

class CreateDivisionModal extends Component {
	created() {
		bindAll(
			this,
			'fetchPeople_',
			'handleMarkdownChange_',
			'handleSubmit_',
			'handleTypeChange_'
		);
	}

	fetchPeople_(input) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: input,
					searchLimit: 5,
					type: person
				}
			}
		);
	}

	handleMarkdownChange_(newVal) {
		this.state.description_ = newVal;
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				addDivision,
				clearLists,
				hideModal
			},
			refs: {form},
			state: {description_}
		} = this;

		this.state.loading_ = true;

		form.validate().then(
			({leads, name, preferredName, subtype, type}) => addDivision(
				{
					comments: description_,
					leadLoopPersonIds: leads.map(item => item.entityClassPK).toString(),
					name,
					preferredName,
					subtype,
					type
				}
			)
		).then(
			({data}) => {
				const {displayURL, name} = data;

				clearLists();

				hideModal();

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(
							Liferay.Language.get('x-was-successfully-created'),
							[<ExternalLink href={displayURL} key={0}>{name}</ExternalLink>]
						)
					}
				);
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

	render() {
		const {hideModal} = this.props;
		const {description_, loading_, type_} = this.state;

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
				location
			}
		} = LoopConstants;

		let subtypeArray = [];

		let initialSubtypeValue = functional;

		if (type_ === location) {
			initialSubtypeValue = office;

			subtypeArray = [
				<option key="office" value={office}>{Liferay.Language.get('office')}</option>,
				<option key="region" value={region}>{Liferay.Language.get('region')}</option>,
				<option key="remote" value={remote}>{Liferay.Language.get('remote')}</option>
			];
		}
		else if (type_ === department) {
			subtypeArray = [
				<option key="functional" value={functional}>{Liferay.Language.get('functional')}</option>,
				<option key="regional" value={regional}>{Liferay.Language.get('regional')}</option>
			];
		}

		return (
			<LoadingWrapper elementClasses="create-division-modal-container" loading={loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('create-group')}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						<Form.Input label={Liferay.Language.get('name')} name="name" validator={validatorObj} />

						<Form.Input label={Liferay.Language.get('preferred-name')} name="preferredName" />

						<Form.Select label={Liferay.Language.get('type')} name="type" onChange={this.handleTypeChange_} validator="required">
							<option value={team}>{Liferay.Language.get('team')}</option>
							<option value={department}>{Liferay.Language.get('department')}</option>
							<option value={location}>{Liferay.Language.get('location')}</option>
						</Form.Select>

						{!!subtypeArray.length &&
							<Form.Select
								initialValue={initialSubtypeValue}
								label={Liferay.Language.get('subtype')}
								name="subtype"
								showBlankOption={false}
								validator="required"
							>
								{subtypeArray}
							</Form.Select>
						}

						<label class="label-container">{Liferay.Language.get('add-lead')}</label>

						<Form.InputList dataSource={this.fetchPeople_} name="leads" />
					</Form>

					<label class="label-container">{Liferay.Language.get('description')}</label>

					<MarkdownEditor
						onChange={this.handleMarkdownChange_}
						placeholder={Liferay.Language.get('give-more-info-about-this-group')}
						value={description_}
					/>
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={hideModal} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('create')}</Button>
				</Modal.Footer>
			</LoadingWrapper>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	addDivision: Config.func(),
	clearLists: Config.func()
};

CreateDivisionModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

CreateDivisionModal.STATE = {
	description_: Config.value(''),
	loading_: Config.value(false),
	type_: Config.value('')
};

export default connect(
	null,
	{
		addAlert,
		addDivision,
		clearLists
	}
)(CreateDivisionModal);