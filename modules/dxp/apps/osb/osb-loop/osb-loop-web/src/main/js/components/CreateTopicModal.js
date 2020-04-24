import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';

import Button from './Button';
import ExternalLink from './ExternalLink';
import Form from './form';
import LoadingWrapper from './LoadingWrapper';
import MarkdownEditor from './MarkdownEditor';
import Modal from './modal';
import sendRequest from '../lib/request';
import {addAlert, alertTypes} from '../actions/alerts';
import {addTopic} from '../actions/topics';
import {assert} from '../lib/form-validators';
import {clearLists} from '../actions/lists';
import {lang} from '../lib/lang-util';

const validatorObj = {
	customValidator: name => sendRequest(
		{
			controller: 'topics',
			controllerMethod: 'validate.json',
			data: {name}
		}
	).then(
		({reason, valid}) => assert(valid, reason)
	),
	required: true
};

class CreateTopicModal extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);
		this.handleMarkdownChange_ = this.handleMarkdownChange_.bind(this);
	}

	handleMarkdownChange_(newVal) {
		this.state.description_ = newVal;
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				addTopic,
				clearLists,
				hideModal
			},
			refs: {form},
			state: {description_}
		} = this;

		this.state.loading_ = true;

		form.validate().then(
			({name}) => addTopic(
				{
					description: description_,
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
		const {description_, loading_} = this.state;

		return (
			<LoadingWrapper elementClasses="create-topic-modal-container" loading={loading_} mask={true}>
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('create-topic')}
				</Modal.Header>

				<Modal.Body>
					<Form ref="form">
						<Form.Input label={Liferay.Language.get('name')} name="name" validator={validatorObj} />
					</Form>

					<label class="label-container">{Liferay.Language.get('description')}</label>

					<MarkdownEditor onChange={this.handleMarkdownChange_} placeholder={Liferay.Language.get('give-more-info-about-this-topic')} value={description_} />
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
	addTopic: Config.func(),
	clearLists: Config.func()
};

CreateTopicModal.PROPS = {
	...STORE,
	hideModal: Config.func()
};

CreateTopicModal.STATE = {
	description_: Config.value(''),
	loading_: Config.value(false)
};

export default connect(
	null,
	{
		addAlert,
		addTopic,
		clearLists
	}
)(CreateTopicModal);