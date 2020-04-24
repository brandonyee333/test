import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {noop} from 'lodash';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Form from './form';
import LoadingWrapper from './LoadingWrapper';
import Modal from './modal';
import sendRequest from '../lib/request';
import {addAlert, alertTypes} from '../actions/alerts';
import {clearLists} from '../actions/lists';
import {lang} from '../lib/lang-util';
import {updateJobTitle} from '../actions/job-titles';

class EditJobTitleModal extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);

		this.getJobTitleEditData_();
	}

	getJobTitleEditData_() {
		const {addAlert, id} = this.props;

		return sendRequest(
			{
				controller: 'job_titles',
				controllerMethod: 'edit.json',
				data: {id}
			}
		).then(
			data => {
				this.state.initialFormValues_ = data;
			}
		).catch(
			error => {
				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: error.toString()
					}
				);
			}
		);
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				clearLists,
				hideModal,
				id,
				updateJobTitle
			},
			refs: {form}
		} = this;

		this.state.loading_ = true;

		form.validate().then(
			({description, name}) => updateJobTitle(
				{
					description,
					id,
					name
				}
			)
		).then(
			({data}) => {
				const {displayURL, name} = data;

				if (name !== this.props.name) {
					clearLists();

					Liferay.Loop.SPA.navigate(displayURL);
				}

				hideModal();

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('x-was-successfully-updated'), [<ExternalLink href={displayURL} key={0}>{name}</ExternalLink>])
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
		const {
			props: {hideModal},
			state: {
				initialFormValues_: {
					description,
					name
				},
				loading_
			}
		} = this;

		return (
			<LoadingWrapper elementClasses="edit-job-title-modal-container" loading={loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('edit-job-title')}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						<Form.Input
							initialValue={name}
							label={Liferay.Language.get('name')}
							name="name"
							validator="required"
						/>

						<Form.Textarea initialValue={description} label={Liferay.Language.get('description')} name="description" />
					</Form>
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={hideModal} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('submit')}</Button>
				</Modal.Footer>
			</LoadingWrapper>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearLists: Config.func(),
	updateJobTitle: Config.func()
};

EditJobTitleModal.PROPS = {
	...STORE,
	hideModal: Config.func(),
	id: Config.number()
};

EditJobTitleModal.STATE = {
	initialFormValues_: Config.value({}),
	loading_: Config.value(false)
};

export default connect(
	noop,
	{
		addAlert,
		clearLists,
		updateJobTitle
	}
)(EditJobTitleModal);