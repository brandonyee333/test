import Component, {Config} from 'metal-jsx';
import {bindAll, isUndefined} from 'lodash';
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
import Tabs from '../components/Tabs';
import {addAlert, alertTypes, updateAlert} from '../actions/alerts';
import {getId} from '../lib/router-util';
import {lang} from '../lib/lang-util';

import {
	FOLLOWERS,
	MEMBERS,
	NETWORK,
	PAGES,
	STREAM
} from '../lib/router-constants';

const {classNameIds, types} = LoopConstants;

class EditPage extends Component {
	created() {
		this.getDivisionEditData_();
	}

	getDivisionEditData_() {
		const {addAlert, id} = this.props;

		this.state.loading_ = true;

		return sendRequest(
			{
				controller: 'divisions',
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
				this.state.loading_ = false;

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
		const {
			childLoopDivisionsCount,
			displayURL,
			followersTotal,
			membersTotal,
			pagesTotal,
			type
		} = this.props;

		let navItemsIList = List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream')
				},
				{
					href: `${displayURL}/${MEMBERS}`,
					label: Liferay.Language.get('members'),
					total: membersTotal
				},
				{
					href: `${displayURL}/${NETWORK}`,
					label: Liferay.Language.get('network'),
					total: childLoopDivisionsCount
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					total: followersTotal
				}
			]
		);

		if (type === types.department) {
			navItemsIList = navItemsIList.push(
				{
					href: `${displayURL}/${PAGES}`,
					label: Liferay.Language.get('pages'),
					total: pagesTotal
				}
			);
		}

		return navItemsIList;
	}

	render() {
		const {
			props: {id},
			state: {initialFormValues_, loading_}
		} = this;

		return (
			<Profile classNameId={classNameIds.divisions} id={id}>
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
	childLoopDivisionsCount: Config.number(),
	displayURL: Config.string(),
	followersTotal: Config.number(),
	id: Config.oneOfType([Config.number(), Config.string()]),
	membersTotal: Config.number(),
	pagesTotal: Config.number(),
	type: Config.number()
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
		const divisionsIMap = state.get('divisions', Map());

		const id = getId(divisionsIMap, router.params.entityId);

		const divisionIMap = divisionsIMap.get(id, Map());

		const divisionDataIMap = divisionIMap.get('data', Map());

		return {
			childLoopDivisionsCount: divisionDataIMap.get('childLoopDivisionsCount', 0),
			displayURL: divisionDataIMap.get('displayURL'),
			followersTotal: divisionDataIMap.get('followersCount', 0),
			id,
			membersTotal: divisionDataIMap.get('loopParticipantAssignmentsCount', 0),
			pagesTotal: divisionDataIMap.get('pagesCount', 0),
			type: divisionDataIMap.get('type')
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
			'getId_',
			'handleCancel_',
			'handleSubmit_',
			'handleTabIndexChange_'
		);
	}

	getId_(items) {
		return items.map(
			({entityClassPK}) => entityClassPK
		).toString();
	}

	handleCancel_() {
		Liferay.Loop.SPA.navigate(this.props.displayURL);
	}

	handleSubmit_() {
		const {addAlert, id, updateAlert} = this.props;

		const pendingAlert = addAlert(
			{
				alertType: alertTypes.PENDING,
				message: Liferay.Language.get('updating-profile')
			}
		);

		sendRequest(
			{
				controller: 'divisions',
				controllerMethod: 'update.json',
				data: {
					...this.refs.workForm.getValues(),
					id
				}
			}
		).then(
			({displayURL, name}) => {
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

	handleTabIndexChange_(newTab) {
		this.state.tabIndex_ = newTab;
	}

	render() {
		const {
			description,
			directMemberLoopPersonCompositesJSONArray,
			gitHubRepositories,
			inheritedMemberLoopPersonCompositesJSONArray,
			leadAssistantLoopPersonCompositesJSONArray,
			leadLoopPersonCompositesJSONArray,
			name,
			preferredName,
			setGitHubRepoPermissions,
			subtype,
			type
		} = this.props;

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

		if (type === location) {
			initialSubtypeValue = office;

			subtypeArray = [
				<option key="office" selected={subtype === office} value={office}>{Liferay.Language.get('office')}</option>,
				<option key="region" selected={subtype === region} value={region}>{Liferay.Language.get('region')}</option>,
				<option key="remote" selected={subtype === remote} value={remote}>{Liferay.Language.get('remote')}</option>
			];
		}
		else if (type === department) {
			subtypeArray = [
				<option key="functional" selected={subtype === functional} value={functional}>{Liferay.Language.get('functional')}</option>,
				<option key="regional" selected={subtype === regional} value={regional}>{Liferay.Language.get('regional')}</option>
			];
		}

		return (
			<Card elementClasses="edit-content-container">
				<div class="title">
					{Liferay.Language.get('edit-group-information')}
				</div>

				<Form ref="workForm">
					<Tabs index={this.state.tabIndex_} onIndexChange={this.handleTabIndexChange_} renderAll={true}>
						<Tabs.Content name={Liferay.Language.get('about')}>
							<Form.Input
								initialValue={name}
								label={Liferay.Language.get('name')}
								name="name"
								placeholder={Liferay.Language.get('name')}
								validator="required"
							/>

							<Form.Input initialValue={preferredName} label={Liferay.Language.get('preferred-name')} name="preferredName" />

							{!!subtypeArray.length &&
								<Form.Select
									initialValue={isUndefined(subtype) ? initialSubtypeValue : subtype}
									label={Liferay.Language.get('subtype')}
									name="subtype"
									showBlankOption={false}
									validator="required"
								>
									{subtypeArray}
								</Form.Select>
							}

							<Form.MarkdownEditor
								initialValue={description}
								label={Liferay.Language.get('description')}
								name="comments"
								placeholder={Liferay.Language.get('description')}
							/>

							<Form.Textarea
								disabled={setGitHubRepoPermissions}
								initialValue={gitHubRepositories}
								label={Liferay.Language.get('github-repositories')}
								name="gitHubRepositories"
								placeholder={Liferay.Language.get('github-repositories')}
							/>
						</Tabs.Content>

						<Tabs.Content name={Liferay.Language.get('members')}>
							<Form.EntityInputList
								initialValue={leadLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('group-leads')}
								name="leadLoopPersonIds"
								processValue={this.getId_}
								type={LoopConstants.types.person}
							/>

							<Form.EntityInputList
								initialValue={leadAssistantLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('group-assistants')}
								name="leadAssistantLoopPersonIds"
								processValue={this.getId_}
								type={LoopConstants.types.person}
							/>

							<Form.EntityInputList
								initialValue={directMemberLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('group-members')}
								name="memberLoopPersonIds"
								processValue={this.getId_}
								type={LoopConstants.types.person}
							/>

							<Form.EntityInputList
								disabled={true}
								initialValue={inheritedMemberLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('inherited-group-members')}
								name="inheritedMemberLoopPersonIds"
								processValue={this.getId_}
								type={LoopConstants.types.person}
							/>
						</Tabs.Content>
					</Tabs>
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
	updateAlert: Config.func()
};

FormContent.PROPS = {
	...FORM_STORE,
	description: Config.string(),
	directMemberLoopPersonCompositesJSONArray: Config.array(),
	displayURL: Config.string(),
	gitHubRepositories: Config.string(),
	inheritedMemberLoopPersonCompositesJSONArray: Config.array(),
	leadAssistantLoopPersonCompositesJSONArray: Config.array(),
	leadLoopPersonCompositesJSONArray: Config.array(),
	name: Config.string(),
	preferredName: Config.string(),
	setGitHubRepoPermissions: Config.bool(),
	subType: Config.number(),
	type: Config.number()
};

FormContent.STATE = {
	tabIndex_: Config.value(0)
};

const ConnectedFormContent = connect(
	null,
	{
		addAlert,
		updateAlert
	}
)(FormContent);