import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Form from './form';
import LoadingWrapper from './LoadingWrapper';
import Modal from './modal';
import {addAlert, alertTypes} from '../actions/alerts';
import {addJobTitle} from '../actions/job-titles';
import {clearLists} from '../actions/lists';
import {lang} from '../lib/lang-util';

class CreateJobTitleModal extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);
	}

	handleMarkdownChange_(newVal) {
		this.state.description_ = newVal;
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				addJobTitle,
				clearLists,
				hideModal
			},
			refs: {form}
		} = this;

		this.state.loading_ = true;

		form.validate().then(
			({description, name}) => addJobTitle(
				{
					description,
					name
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
						message: lang(Liferay.Language.get('x-was-successfully-created'), [<ExternalLink href={displayURL} key={0}>{name}</ExternalLink>])
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
			<LoadingWrapper elementClasses="create-job-title-modal-container" loading={this.state.loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('create-job-title')}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						<Form.Input label={Liferay.Language.get('name')} name="name" validator="required" />

						<Form.Textarea label={Liferay.Language.get('description')} name="description" />
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
	addJobTitle: Config.func(),
	clearLists: Config.func()
};

CreateJobTitleModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

CreateJobTitleModal.STATE = {
	loading_: Config.value(false)
};

export default connect(
	null,
	{
		addAlert,
		addJobTitle,
		clearLists
	}
)(CreateJobTitleModal);