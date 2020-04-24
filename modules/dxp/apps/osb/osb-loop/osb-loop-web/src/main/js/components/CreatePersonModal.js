import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Form from './form';
import LoadingWrapper from './LoadingWrapper';
import LoopConstants from '../lib/loop-constants';
import Modal from './modal';
import sendRequest from '../lib/request';
import {addAlert, alertTypes} from '../actions/alerts';
import {addPerson} from '../actions/people';
import {assert} from '../lib/form-validators';
import {clearLists} from '../actions/lists';
import {lang} from '../lib/lang-util';

const {employmentTypes} = LoopConstants;

const validatorObj = {
	customValidator: emailAddress => sendRequest(
		{
			controller: 'people',
			controllerMethod: 'validate.json',
			data: {emailAddress}
		}
	).then(
		({reason, valid}) => assert(valid, reason)
	),
	email: true,
	required: true
};

class CreatePersonModal extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				addPerson,
				clearLists,
				hideModal
			},
			refs: {form}
		} = this;

		this.state.loading_ = true;

		form.validate().then(
			({startDate, ...others}) => addPerson(
				{
					...others,
					...startDate
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

	render() {
		const {hideModal} = this.props;

		return (
			<LoadingWrapper elementClasses="create-person-modal-container" loading={this.state.loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('create-person')}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						<Form.Input label={Liferay.Language.get('email-address')} name="emailAddress" validator={validatorObj} />

						<Form.Input label={Liferay.Language.get('first-name')} name="firstName" validator="required" />

						<Form.Input label={Liferay.Language.get('last-name')} name="lastName" validator="required" />

						<Form.Input label={Liferay.Language.get('preferred-name')} name="preferredName" />

						<Form.Select label={Liferay.Language.get('employment-type')} name="employmentType" validator="required">
							{
								employmentTypes.map(
									({label, value}) => <option key={value} value={value}>{label}</option>
								)
							}
						</Form.Select>

						<Form.JobTitleSelect label={Liferay.Language.get('job-title')} name="loopJobTitleId" />

						<Form.DatePicker
							initialValue={new Date().toLocaleDateString()}
							label={Liferay.Language.get('hire-date')}
							name="startDate"
							placeholder="MM/DD/YYYY"
						/>
					</Form>
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
	addPerson: Config.func(),
	clearLists: Config.func()
};

CreatePersonModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

CreatePersonModal.STATE = {
	jobTitles_: Config.value([]),
	loading_: Config.value(false)
};

export default connect(
	null,
	{
		addAlert,
		addPerson,
		clearLists
	}
)(CreatePersonModal);