import Component, {Config} from 'metal-jsx';
import {bindAll, each, pickBy} from 'lodash';
import {connect} from 'metal-redux';
import {List, Map} from 'immutable';

import AuthTokens from '../components/AuthTokens';
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
import {updatePerson} from '../actions/people';

import {
	ABOUT,
	FOLLOWERS,
	GROUPS,
	NETWORK,
	STREAM
} from '../lib/router-constants';

const {
	classNameIds,
	employmentTypes,
	socialUrls,
	types
} = LoopConstants;

class EditPage extends Component {
	created() {
		this.getPersonEditData_();
	}

	getNavItemsIList_() {
		const {displayURL, followersTotal, loopParticipantAssignmentsCount} = this.props;

		return List(
			[
				{
					href: `${displayURL}/${STREAM}`,
					label: Liferay.Language.get('stream')
				},
				{
					href: `${displayURL}/${NETWORK}`,
					label: Liferay.Language.get('network')
				},
				{
					href: `${displayURL}/${GROUPS}`,
					label: Liferay.Language.get('groups'),
					total: loopParticipantAssignmentsCount
				},
				{
					href: `${displayURL}/${FOLLOWERS}`,
					label: Liferay.Language.get('followers'),
					total: followersTotal
				},
				{
					href: `${displayURL}/${ABOUT}`,
					label: Liferay.Language.get('about')
				}
			]
		);
	}

	getPersonEditData_() {
		const {addAlert, id} = this.props;

		this.state.loading_ = true;

		return sendRequest(
			{
				controller: 'people',
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

	render() {
		const {
			props: {id},
			state: {initialFormValues_, loading_}
		} = this;

		return (
			<Profile classNameId={classNameIds.people} id={id}>
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
	followersTotal: Config.number(),
	id: Config.oneOfType([Config.number(), Config.string()]),
	loopParticipantAssignmentsCount: Config.number()
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
		const peopleIMap = state.get('people', Map());

		const id = getId(peopleIMap, router.params.entityId);

		const personDataIMap = peopleIMap.getIn([id, 'data'], Map());

		return {
			displayURL: personDataIMap.get('displayURL'),
			followersTotal: personDataIMap.get('followersCount', 0),
			id,
			loopParticipantAssignmentsCount: personDataIMap.get('loopParticipantAssignmentsCount', 0)
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
			'getName_',
			'handleCancel_',
			'handleSubmit_',
			'handleTabIndexChange_'
		);
	}

	fieldDisabled(permission) {
		return this.props.loopPersonDisabledFieldsJSONArray.includes(permission);
	}

	filterPermissioned_(items) {
		return pickBy(
			items,
			(val, key) => !this.props.loopPersonDisabledFieldsJSONArray.some(
				item => key.startsWith(item)
			)
		);
	}

	getId_(items) {
		return items.map(
			({entityClassPK}) => entityClassPK
		).toString();
	}

	getName_(items) {
		return items.map(
			({name}) => name
		).toString();
	}

	handleCancel_() {
		Liferay.Loop.SPA.navigate(this.props.displayURL);
	}

	handleSubmit_() {
		const {workForm} = this.refs;

		if (workForm.validate()) {
			const {
				addAlert,
				id,
				updateAlert,
				updatePerson
			} = this.props;

			const {birthday, startDate, ...others} = workForm.getValues();

			let data = {
				...others,
				...birthday,
				...startDate,
				id
			};

			data = this.filterPermissioned_(data);
			data = this.processAdditionalEmails_(data);
			data = this.processPhoneNumbers_(data);

			const pendingAlert = addAlert(
				{
					alertType: alertTypes.PENDING,
					message: Liferay.Language.get('updating-profile')
				}
			);

			updatePerson(
				{
					...data,
					id
				}
			).then(
				({data}) => {
					const {displayURL, name} = data;

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

	handleTabIndexChange_(newTab) {
		this.state.tabIndex_ = newTab;
	}

	processAdditionalEmails_(items) {
		const emailAddresses = [];

		each(
			items,
			(val, key) => {
				if (val && val.length && key.startsWith('additionalEmailAddress')) {
					emailAddresses.push(val);

					delete items[key];
				}
			}
		);

		return {
			...items,
			emailAddresses: JSON.stringify(emailAddresses)
		};
	}

	processPhoneNumbers_(items) {
		const phones = [];

		each(
			items,
			(val, key) => {
				if (val && val.length && key.startsWith('phoneNumber') && !key.startsWith('phoneNumberType')) {
					const index = key.replace('phoneNumber', '');

					const typeKey = `phoneNumberType${index}`;

					phones.push(
						{
							number: val,
							typeId: items[typeKey]
						}
					);

					delete items[key];
					delete items[typeKey];
				}
			}
		);

		return {
			...items,
			phones: JSON.stringify(phones)
		};
	}

	render() {
		const {
			birthday,
			departmentLoopDivisionCompositesJSONArray,
			description,
			emailAddress,
			emailAddressesJSONArray,
			employmentType,
			facebookSn,
			firstName,
			gitHubSn,
			hireDate,
			jobResponsibilities,
			jobTitle,
			languages,
			lastName,
			linkedInSn,
			locationLoopDivisionCompositesJSONArray,
			loopJobTitleId,
			loopTopicCompositesJSONArray,
			male,
			managerLoopPersonCompositesJSONArray,
			phonesJSONArray,
			phoneTypesJSONArray,
			preferredName,
			primaryManagerLoopPersonCompositesJSONArray,
			showAge,
			showBirthday,
			skypeSn,
			teamLoopDivisionCompositesJSONArray,
			twitterSn,
			userId
		} = this.props;

		return (
			<Card elementClasses="edit-content-container">
				<div class="title">
					{Liferay.Language.get('edit-user-information')}
				</div>

				<Form ref="workForm">
					<Tabs index={this.state.tabIndex_} onIndexChange={this.handleTabIndexChange_} renderAll={true}>
						<Tabs.Content name={Liferay.Language.get('work')}>
							<Form.Select
								disabled={this.fieldDisabled('employmentType')}
								initialValue={employmentType}
								label={Liferay.Language.get('employment-type')}
								name="employmentType"
								validator="required"
							>
								{
									employmentTypes.map(
										({label, value}) => <option key={value} selected={employmentType == value} value={value}>{label}</option>
									)
								}
							</Form.Select>

							<Form.JobTitleSelect disabled={this.fieldDisabled('loopJobTitleId')} initialValue={{id: loopJobTitleId, name: jobTitle}} />

							<Form.DatePicker
								disabled={this.fieldDisabled('startDateYear')}
								initialValue={hireDate}
								label={Liferay.Language.get('hire-date')}
								name="startDate"
							/>

							<Form.MarkdownEditor
								disabled={this.fieldDisabled('jobResponsibilities')}
								initialValue={jobResponsibilities}
								label={Liferay.Language.get('job-responsibilities')}
								name="jobResponsibilities"
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('loopTopicNames')}
								initialValue={loopTopicCompositesJSONArray}
								label={Liferay.Language.get('expertise')}
								name="loopTopicNames"
								processValue={this.getName_}
								type={types.topic}
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('primaryManagerLoopPersonIds')}
								initialValue={primaryManagerLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('primary-manager')}
								multiple={false}
								name="primaryManagerLoopPersonIds"
								processValue={this.getId_}
								type={types.person}
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('managerLoopPersonIds')}
								initialValue={managerLoopPersonCompositesJSONArray}
								label={Liferay.Language.get('managers')}
								name="managerLoopPersonIds"
								processValue={this.getId_}
								type={types.person}
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('departmentLoopDivisionIds')}
								initialValue={departmentLoopDivisionCompositesJSONArray}
								label={Liferay.Language.get('departments')}
								name="departmentLoopDivisionIds"
								processValue={this.getId_}
								type={types.department}
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('teamLoopDivisionIds')}
								initialValue={teamLoopDivisionCompositesJSONArray}
								label={Liferay.Language.get('teams')}
								name="teamLoopDivisionIds"
								processValue={this.getId_}
								type={types.team}
							/>

							<Form.EntityInputList
								disabled={this.fieldDisabled('locationLoopDivisionIds')}
								initialValue={locationLoopDivisionCompositesJSONArray}
								label={Liferay.Language.get('locations')}
								name="locationLoopDivisionIds"
								processValue={this.getId_}
								type={types.location}
							/>

							<Form.Input
								disabled={this.fieldDisabled('languages')}
								initialValue={languages}
								label={Liferay.Language.get('spoken-languages')}
								name="languages"
								placeholder={Liferay.Language.get('languages')}
							/>
						</Tabs.Content>

						<Tabs.Content name={Liferay.Language.get('about')}>
							<Form.Input
								disabled={this.fieldDisabled('firstName')}
								initialValue={firstName}
								label={Liferay.Language.get('first-name')}
								name="firstName"
								placeholder={Liferay.Language.get('first')}
								validator="required"
							/>

							<Form.Input
								disabled={this.fieldDisabled('lastName')}
								initialValue={lastName}
								label={Liferay.Language.get('last-name')}
								name="lastName"
								placeholder={Liferay.Language.get('last')}
								validator="required"
							/>

							<Form.Input
								disabled={this.fieldDisabled('preferredName')}
								initialValue={preferredName}
								label={Liferay.Language.get('preferred-name')}
								name="preferredName"
							/>

							<Form.MarkdownEditor
								disabled={this.fieldDisabled('description')}
								initialValue={description}
								label={Liferay.Language.get('short-bio')}
								name="description"
								placeholder={Liferay.Language.get('tell-us-about-yourself')}
							/>

							<Form.Select
								disabled={this.fieldDisabled('male')}
								initialValue={male}
								label={Liferay.Language.get('gender')}
								name="male"
								showBlankOption={false}
							>
								<option selected={male} value="true">{Liferay.Language.get('male')}</option>

								<option selected={!male} value="false">{Liferay.Language.get('female')}</option>
							</Form.Select>

							<Form.DatePicker
								disabled={this.fieldDisabled('birthday')}
								initialValue={birthday}
								label={Liferay.Language.get('birthday')}
								name="birthday"
								placeholder={Liferay.Language.get('mm-dd-yyyy')}
							/>

							<Form.Checkbox
								disabled={this.fieldDisabled('showBirthday')}
								initialValue={showBirthday}
								label={Liferay.Language.get('display-my-birthday')}
								name="showBirthday"
							/>

							<Form.Checkbox
								disabled={this.fieldDisabled('showAge')}
								initialValue={showAge}
								label={Liferay.Language.get('display-my-age')}
								name="showAge"
							/>
						</Tabs.Content>

						<Tabs.Content name={Liferay.Language.get('contact')}>
							<Form.Input
								disabled={this.fieldDisabled('emailAddress')}
								initialValue={emailAddress}
								label={Liferay.Language.get('email')}
								name="emailAddress"
								placeholder={Liferay.Language.get('email')}
								validator={{
									email: true,
									required: true
								}}
							/>

							<Form.MultiInput
								initialValue={emailAddressesJSONArray}
								label={Liferay.Language.get('other-email-addresses')}
								name="additionalEmailAddress"
								placeholder={Liferay.Language.get('email')}
								selector="address"
							/>

							<Form.MultiInput
								initialValue={phonesJSONArray}
								label={Liferay.Language.get('phone-numbers')}
								name="phoneNumber"
								placeholder={Liferay.Language.get('number')}
								selector="number"
								types={
									phoneTypesJSONArray.map(
										({listTypeId, name}) => ({
											label: name,
											value: listTypeId
										})
									)
								}
								typeSelector="typeId"
							/>

							<Form.Input
								disabled={this.fieldDisabled('skypeSn')}
								initialValue={skypeSn}
								label={Liferay.Language.get('skype')}
								name="skypeSn"
								placeholder={Liferay.Language.get('username')}
							/>
						</Tabs.Content>

						<Tabs.Content name={Liferay.Language.get('elsewhere')}>
							<Form.Input
								disabled={this.fieldDisabled('twitterSn')}
								initialValue={twitterSn}
								label={Liferay.Language.get('twitter')}
								name="twitterSn"
								placeholder={Liferay.Language.get('username')}
								prefix={`${socialUrls.twitter}/`}
							/>

							<Form.Input
								disabled={this.fieldDisabled('facebookSn')}
								initialValue={facebookSn}
								label={Liferay.Language.get('facebook')}
								name="facebookSn"
								placeholder={Liferay.Language.get('username')}
								prefix={`${socialUrls.facebook}/`}
							/>

							<Form.Input
								disabled={this.fieldDisabled('gitHubSn')}
								initialValue={gitHubSn}
								label={Liferay.Language.get('github')}
								name="gitHubSn"
								placeholder={Liferay.Language.get('username')}
								prefix={`${socialUrls.github}/`}
							/>

							<Form.Input
								disabled={this.fieldDisabled('linkedInSn')}
								initialValue={linkedInSn}
								label={Liferay.Language.get('linkedin')}
								name="linkedInSn"
								placeholder={Liferay.Language.get('username')}
								prefix={`${socialUrls.linkedin}/`}
							/>
						</Tabs.Content>

						<Tabs.Content name={Liferay.Language.get('devices')}>
							<AuthTokens filterId={userId} />
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
	birthday: Config.string(),
	departmentLoopDivisionCompositesJSONArray: Config.array(),
	description: Config.string(),
	emailAddress: Config.string(),
	emailAddressesJSONArray: Config.array(),
	employmentType: Config.number(),
	id: Config.number(),
	jobResponsibilities: Config.string(),
	jobTitle: Config.string(),
	languages: Config.string(),
	lastName: Config.string(),
	locationLoopDivisionCompositesJSONArray: Config.array(),
	loopJobTitleId: Config.number(),
	loopPersonDisabledFieldsJSONArray: Config.array().value([]),
	loopTopicCompositesJSONArray: Config.array(),
	male: Config.bool(),
	managerLoopPersonCompositesJSONArray: Config.array(),
	phonesJSONArray: Config.array(),
	phoneTypesJSONArray: Config.array().value([]),
	primaryManagerLoopPersonCompositesJSONArray: Config.array(),
	showAge: Config.bool(),
	showBirthday: Config.bool(),
	skypeSn: Config.string(),
	startDate: Config.string(),
	teamLoopDivisionCompositesJSONArray: Config.array(),
	twitterSn: Config.string(),
	userId: Config.number()
};

FormContent.STATE = {
	tabIndex_: Config.value(0)
};

const ConnectedFormContent = connect(
	null,
	{
		addAlert,
		updateAlert,
		updatePerson
	}
)(FormContent);