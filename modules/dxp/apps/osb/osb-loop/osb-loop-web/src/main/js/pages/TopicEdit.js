import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import BaseLayout from './BaseLayout';
import Button from '../components/Button';
import Card from '../components/card';
import Form from '../components/form';
import LoadingCard from '../components/LoadingCard';
import LoopConstants from '../lib/loop-constants';
import Profile from './Profile';
import sendRequest from '../lib/request';
import {addAlert, alertTypes, updateAlert} from '../actions/alerts';
import {EXPERTS, FOLLOWERS, STREAM} from '../lib/router-constants';
import {getId} from '../lib/router-util';
import {lang} from '../lib/lang-util';
import {updateTopic} from '../actions/topics';

class EditPage extends Component {
	created() {
		this.getTopicEditData_();
	}

	getTopicEditData_() {
		const {addAlert, id} = this.props;

		this.state.loading_ = true;

		return sendRequest(
			{
				controller: 'topics',
				controllerMethod: 'edit.json',
				data: {id}
			}
		).then(
			data => {
				this.setState(
					{
						initialFormValues_: data,
						loading_: false
					}
				);
			}
		).catch(
			error => {
				this.state.loading = false;

				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: error.toString()
					}
				);
			}
		);
	}

	getNavItemsIList_() {
		const {displayURL, expertsTotal, followersTotal} = this.props;

		return List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream')
				},
				{
					href: `${displayURL}/${EXPERTS}`,
					label: Liferay.Language.get('experts'),
					total: expertsTotal
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					total: followersTotal
				}
			]
		);
	}

	render() {
		const {
			props: {id},
			state: {initialFormValues_, loading_}
		} = this;

		return (
			<Profile classNameId={LoopConstants.classNameIds.topics} id={id}>
				<BaseLayout
					content={[
						<BaseLayout.NineColumn key="editForm">
							{loading_ &&
								<LoadingCard />
							}

							{!loading_ &&
								<ConnectedFormContent {...initialFormValues_} id={id} />
							}
						</BaseLayout.NineColumn>
					]}
					navItemsIList={this.getNavItemsIList_()}
				/>
			</Profile>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	displayURL: Config.string(),
	expertsTotal: Config.number(),
	followersTotal: Config.number(),
	id: Config.oneOfType([Config.number(), Config.string()])
};

EditPage.PROPS = {
	...STORE,
	router: Config.object()
};

EditPage.STATE = {
	initialFormValues_: Config.value({}),
	loading_: Config.value(true)
};

export default connect(
	(state, {router}) => {
		const topicsIMap = state.get('topics', Map());

		const id = getId(topicsIMap, router.params.entityId);

		const topicDataIMap = topicsIMap.getIn([id, 'data'], Map());

		return {
			displayURL: topicDataIMap.get('displayURL'),
			expertsTotal: topicDataIMap.get('loopTopicAssignmentsCount'),
			followersTotal: topicDataIMap.get('followersCount'),
			id
		};
	},
	{
		addAlert
	}
)(EditPage);

class FormContent extends Component {
	created() {
		bindAll(
			this,
			'handleCancel_',
			'handleSubmit_'
		);
	}

	handleCancel_() {
		Liferay.Loop.SPA.navigate(this.props.displayURL);
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				id,
				updateAlert,
				updateTopic
			},
			refs: {topicForm}
		} = this;

		if (topicForm.validate()) {
			const pendingAlert = addAlert(
				{
					alertType: alertTypes.PENDING,
					message: Liferay.Language.get('updating-profile')
				}
			);

			updateTopic(
				{
					...topicForm.getValues(),
					id
				}
			).then(
				response => {
					const {displayURL, name} = response.data;

					Liferay.Loop.SPA.navigate(displayURL);

					updateAlert(
						{
							alertType: alertTypes.SUCCESS,
							id: pendingAlert.id,
							message: lang(Liferay.Language.get('x-was-successfully-updated'), [name])
						}
					);
				}
			).catch(
				error => {
					updateAlert(
						{
							alertType: alertTypes.ERROR,
							id: pendingAlert.id,
							message: error.toString()
						}
					);
				}
			);
		}
	}

	render() {
		const {description, name} = this.props;

		return (
			<Card elementClasses="edit-content-container">
				<div class="title">
					{Liferay.Language.get('edit-topic')}
				</div>

				<Form elementClasses="content" ref="topicForm">
					<Form.Input
						disabled={true}
						initialValue={name}
						label={Liferay.Language.get('name')}
						name="name"
						placeholder={Liferay.Language.get('name')}
						validator="required"
					/>

					<Form.MarkdownEditor
						initialValue={description}
						label={Liferay.Language.get('description')}
						name="description"
						placeholder={Liferay.Language.get('description')}
					/>
				</Form>

				<Card.Footer>
					<Button onClick={this.handleCancel_} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('submit')}</Button>
				</Card.Footer>
			</Card>
		);
	}
}

const FORM_STORE = {
	addAlert: Config.func(),
	updateAlert: Config.func(),
	updateTopic: Config.func()
};

FormContent.PROPS = {
	...FORM_STORE,
	description: Config.string(),
	displayURL: Config.string(),
	id: Config.number(),
	name: Config.string()
};

const ConnectedFormContent = connect(
	null,
	{
		addAlert,
		updateAlert,
		updateTopic
	}
)(FormContent);