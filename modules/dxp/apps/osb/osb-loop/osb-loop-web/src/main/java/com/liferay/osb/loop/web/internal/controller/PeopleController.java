/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.web.internal.controller;

import com.liferay.alloy.mvc.AlloyException;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.json.web.service.JSONWebServiceMethod;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopPersonRel;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.impl.LoopDivisionModelImpl;
import com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl;
import com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl;
import com.liferay.osb.loop.model.impl.LoopTopicModelImpl;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonRelConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.indexer.LoopPersonIndexer;
import com.liferay.osb.loop.web.internal.messaging.LoopPersonControllerMessageListener;
import com.liferay.osb.loop.web.internal.messaging.LoopPersonSchedulerMessageListener;
import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.osb.loop.web.internal.util.LoopDivisionUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonExtraDataParser;
import com.liferay.osb.loop.web.internal.util.LoopPersonRelUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.EmailAddressException;
import com.liferay.portal.kernel.exception.GroupFriendlyURLException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.PhoneNumberException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.io.File;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletRequest;

/**
 * @author Timothy Bell
 */
public class PeopleController extends LoopAlloyControllerImpl {

	public PeopleController() {
		setAlloyServiceInvokerClass(LoopPerson.class);
		setPermissioned(true);
	}

	public void activate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateActivate(loopPerson);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), portletRequest);

		LoopPersonUtil.updateStatus(
			this, loopPerson, WorkflowConstants.STATUS_APPROVED,
			serviceContext);

		respondWith("The person was successfully activated.");
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAdd();

		User personUser = null;

		boolean autoPassword = true;

		String password = ParamUtil.getString(request, "password");

		if (Validator.isNotNull(password)) {
			autoPassword = false;
		}

		String emailAddress = ParamUtil.getString(request, "emailAddress");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		int birthdayMonth = ParamUtil.getInteger(
			request, "birthdayMonth", Calendar.JANUARY);
		int birthdayDay = ParamUtil.getInteger(request, "birthdayDay", 1);
		int birthdayYear = ParamUtil.getInteger(request, "birthdayYear", 1970);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), portletRequest);

		try {
			personUser = UserLocalServiceUtil.addUser(
				user.getUserId(), themeDisplay.getCompanyId(), autoPassword,
				password, password, true, StringPool.BLANK, emailAddress, 0,
				StringPool.BLANK, LocaleUtil.getDefault(), firstName,
				StringPool.BLANK, lastName, 0, 0, true, birthdayMonth,
				birthdayDay, birthdayYear, StringPool.BLANK,
				new long[] {themeDisplay.getScopeGroupId()}, new long[0],
				_getRoleIds(), new long[0], false, serviceContext);
		}
		catch (Exception e) {
			String message = _translateUserException(e);

			renderError(message);

			return;
		}

		updateModel(personUser.getContact());

		LoopPerson loopPerson = LoopPersonUtil.getLoopPerson(
			personUser.getUserId());

		long loopJobTitleId = ParamUtil.getLong(request, "loopJobTitleId");

		updateModel(
			loopPerson, "extraData", _getExtraData(loopPerson),
			"loopJobTitleId", loopJobTitleId);

		pollBaseModel = loopPerson;
		pollHitsLength = 1;

		respondWith(_getLoopPersonCompositeJSONObject(loopPerson));
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateDelete(loopPerson);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), portletRequest);

		LoopPersonUtil.updateStatus(
			this, loopPerson, WorkflowConstants.STATUS_INACTIVE,
			serviceContext);

		respondWith("The person was successfully deleted.");
	}

	public void edit() throws Exception {
		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateEdit(loopPerson);

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		if (isRespondingTo("json")) {
			JSONObject jsonObject = loopPersonComposite.getJSONObject(
				new String[] {
					"birthday", "departmentLoopDivisionCompositesJSONArray",
					"emailAddressesJSONArray", "jobResponsibilities",
					"locationLoopDivisionCompositesJSONArray",
					"loopJobTitleCompositesJSONArray",
					"loopPersonDisabledFieldsJSONArray",
					"loopTopicCompositesJSONArray",
					"managerLoopPersonCompositesJSONArray", "phonesJSONArray",
					"phoneTypesJSONArray",
					"primaryManagerLoopPersonCompositesJSONArray",
					"teamLoopDivisionCompositesJSONArray"
				});

			respondWith(jsonObject);

			return;
		}

		renderRequest.setAttribute("loopPersonComposite", loopPersonComposite);
	}

	public void importExtraData() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		File file = uploadPortletRequest.getFile("file");

		String extension = FileUtil.getExtension(file.getName());

		if (!extension.equals("xls") && !extension.equals("xlt")) {
			renderError("the-file-must-be-of-type-xls-or-xlt");

			return;
		}

		LoopPersonExtraDataParser.parse(this, file);

		addSuccessMessage();

		String redirect = ParamUtil.getString(uploadPortletRequest, "redirect");

		redirectTo(redirect);
	}

	public void importFile() {
		renderRequest.setAttribute("controller", controllerPath);

		render("import_file");
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);

		long createTime = ParamUtil.getLong(request, "createTime");

		attributes.put("createTime", createTime);

		Sort[] sorts = {
			new Sort("firstName_sortable", false),
			new Sort("lastName_sortable", false)
		};

		JSONObject jsonObject = doSearch(
			indexer, alloyServiceInvoker, attributes, sorts);

		LoopPerson loopPerson = LoopPersonUtil.getLoopPerson(user.getUserId());

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		jsonObject.put(
			"permissionCreate", loopPersonComposite.getPermissionCreate());

		respondWith(jsonObject);
	}

	public void removeManager() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		long managerLoopPersonId = ParamUtil.getLong(
			request, "managerLoopPersonId");

		LoopPerson managerLoopPerson =
			LoopPersonLocalServiceUtil.fetchLoopPerson(managerLoopPersonId);

		_validateRemoveManager(loopPerson, managerLoopPerson);

		LoopPersonRelUtil.deleteLoopPersonRel(
			loopPerson.getLoopPersonId(), managerLoopPersonId);

		respondWith("The manager was successfully unassigned.");
	}

	public void removeSubordinate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		long subordinateLoopPersonId = ParamUtil.getLong(
			request, "subordinateLoopPersonId");

		LoopPerson subordinateLoopPerson =
			LoopPersonLocalServiceUtil.fetchLoopPerson(subordinateLoopPersonId);

		_validateRemoveSubordinate(loopPerson, subordinateLoopPerson);

		LoopPersonRelUtil.deleteLoopPersonRel(
			subordinateLoopPersonId, loopPerson.getLoopPersonId());

		respondWith("The subordinate was successfully unassigned.");
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "keywords", "start"},
		parameterTypes = {Integer.class, String.class, Integer.class}
	)
	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Sort[] sorts = {
			new Sort("userStatusPriority_sortable", Sort.INT_TYPE, false),
			new Sort(null, Sort.SCORE_TYPE, false)
		};

		respondWith(doSearch(indexer, alloyServiceInvoker, null, sorts));
	}

	public void setCoverImage() throws Exception {
		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateSetCoverImage(loopPerson);

		_setImage(
			loopPerson, "coverImagePayload",
			LoopWebConfigurationValues.IMAGE_COVER_TYPES,
			LoopWebConfigurationKeys.IMAGE_COVER_MAX_SIZE);
	}

	public void setManagers() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		List<Long> managerLoopPersonIds = _getLongList("managerLoopPersonIds");

		_validateSetManagers(loopPerson, managerLoopPersonIds);

		LoopPersonRelUtil.setLoopPersonRel(
			this, loopPerson.getLoopPersonId(), managerLoopPersonIds,
			LoopPersonRelConstants.TYPE_MANAGER);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"managerLoopPersonCompositesJSONObject",
			LoopCompositeUtil.getCompositesJSONArray(
				managerLoopPersonIds, LoopPersonComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}));
		jsonObject.put(
			"subordinateLoopPersonCompositeJSONObject",
			_getLoopPersonCompositeJSONObject(loopPerson));

		respondWith(jsonObject);
	}

	public void setProfileImage() throws Exception {
		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateSetProfileImage(loopPerson);

		_setImage(
			loopPerson, "profileImagePayload",
			LoopWebConfigurationValues.IMAGE_PROFILE_TYPES,
			LoopWebConfigurationKeys.IMAGE_PROFILE_MAX_SIZE);
	}

	public void setSubordinates() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<Long> subordinateLoopPersonIds = _getLongList(
			"subordinateLoopPersonIds");

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateSetSubordinates(subordinateLoopPersonIds, loopPerson);

		LoopPersonRelUtil.setLoopPersonRel(
			this, subordinateLoopPersonIds, loopPerson.getLoopPersonId(),
			LoopPersonRelConstants.TYPE_MANAGER);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"managerLoopPersonCompositeJSONObject",
			_getLoopPersonCompositeJSONObject(loopPerson));
		jsonObject.put(
			"subordinateLoopPersonCompositesJSONObject",
			LoopCompositeUtil.getCompositesJSONArray(
				subordinateLoopPersonIds, LoopPersonComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}));

		respondWith(jsonObject);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateUpdate(loopPerson);

		User personUser = null;

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		List<String> loopPersonDisabledFields =
			LoopPersonUtil.getLoopPersonDisabledFields();

		String emailAddress = ParamUtil.getString(request, "emailAddress");

		if (loopPersonDisabledFields.contains("emailAddress")) {
			emailAddress = loopPersonComposite.getEmailAddress();
		}

		String firstName = ParamUtil.getString(request, "firstName");

		if (loopPersonDisabledFields.contains("firstName")) {
			firstName = loopPersonComposite.getFirstName();
		}

		String middleName = ParamUtil.getString(request, "middleName");

		String lastName = ParamUtil.getString(request, "lastName");

		if (loopPersonDisabledFields.contains("lastName")) {
			lastName = loopPersonComposite.getLastName();
		}

		int prefixId = ParamUtil.getInteger(request, "prefixId");
		int suffixId = ParamUtil.getInteger(request, "suffixId");
		boolean male = ParamUtil.getBoolean(request, "male");
		int birthdayMonth = ParamUtil.getInteger(
			request, "birthdayMonth", Calendar.JANUARY);
		int birthdayDay = ParamUtil.getInteger(request, "birthdayDay", 1);
		int birthdayYear = ParamUtil.getInteger(request, "birthdayYear", 1970);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), portletRequest);

		try {
			personUser = UserLocalServiceUtil.getUser(
				loopPerson.getPersonUserId());

			personUser = UserLocalServiceUtil.updateUser(
				personUser.getUserId(), null, null, null, false, null, null,
				personUser.getScreenName(), emailAddress,
				personUser.getFacebookId(), personUser.getOpenId(), false, null,
				personUser.getLanguageId(), personUser.getTimeZoneId(),
				personUser.getGreeting(), personUser.getComments(), firstName,
				middleName, lastName, prefixId, suffixId, male, birthdayMonth,
				birthdayDay, birthdayYear, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
				StringPool.BLANK, null, null, _getRoleIds(), null, null,
				serviceContext);
		}
		catch (Exception e) {
			String message = _translateUserException(e);

			renderError(message);

			return;
		}

		updateModel(personUser.getContact());

		LoopUtil.addUserRole(
			loopPerson.getPersonUserId(), themeDisplay.getCompanyId(),
			LoopRoleConstants.LOOP_PERSON);

		if (!loopPersonDisabledFields.contains("managerLoopPersonIds")) {
			List<Long> managerLoopPersonIds = _getLongList(
				"managerLoopPersonIds");

			LoopPersonRelUtil.setLoopPersonRel(
				this, loopPerson.getLoopPersonId(), managerLoopPersonIds,
				LoopPersonRelConstants.TYPE_MANAGER);
		}

		if (!loopPersonDisabledFields.contains("primaryManagerLoopPersonIds")) {
			List<Long> primaryManagerLoopPersonIds = _getLongList(
				"primaryManagerLoopPersonIds");

			LoopPersonRelUtil.setLoopPersonRel(
				this, loopPerson.getLoopPersonId(), primaryManagerLoopPersonIds,
				LoopPersonRelConstants.TYPE_PRIMARY_MANAGER);
		}

		if (!loopPersonDisabledFields.contains("departmentLoopDivisionIds")) {
			List<Long> departmentLoopDivisionIds = _getLongList(
				"departmentLoopDivisionIds");

			LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
				this, departmentLoopDivisionIds, loopPerson.getLoopPersonId(),
				themeDisplay.getCompanyId(),
				LoopDivisionConstants.TYPE_DEPARTMENT);
		}

		if (!loopPersonDisabledFields.contains("locationLoopDivisionIds")) {
			List<Long> locationLoopDivisionIds = _getLongList(
				"locationLoopDivisionIds");

			LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
				this, locationLoopDivisionIds, loopPerson.getLoopPersonId(),
				themeDisplay.getCompanyId(),
				LoopDivisionConstants.TYPE_LOCATION);
		}

		if (!loopPersonDisabledFields.contains("teamLoopDivisionIds")) {
			List<Long> teamLoopDivisionIds = _getLongList(
				"teamLoopDivisionIds");

			LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
				this, teamLoopDivisionIds, loopPerson.getLoopPersonId(),
				themeDisplay.getCompanyId(), LoopDivisionConstants.TYPE_TEAM);
		}

		List<Long> loopTopicIds = new ArrayList<>();

		String[] loopTopicNames = StringUtil.split(
			ParamUtil.getString(request, "loopTopicNames"));

		for (String loopTopicName : loopTopicNames) {
			LoopTopic loopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
				company.getCompanyId(), loopTopicName);

			if (loopTopic == null) {
				AssetTagLocalServiceUtil.addTag(
					themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(),
					loopTopicName, getServiceContext());

				loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
					company.getCompanyId(), loopTopicName);
			}

			loopTopicIds.add(loopTopic.getLoopTopicId());
		}

		LoopTopicAssignmentUtil.setLoopTopicAssignments(
			this, loopPerson.getLoopPersonId(), loopTopicIds);

		long loopJobTitleId = ParamUtil.getLong(request, "loopJobTitleId");

		if (loopPersonDisabledFields.contains("loopJobTitleId")) {
			loopJobTitleId = loopPersonComposite.getLoopJobTitleId();
		}

		updateModel(
			loopPerson, "extraData", _getExtraData(loopPerson),
			"loopJobTitleId", loopJobTitleId);

		_updateUserFields(personUser);

		respondWith(_getLoopPersonCompositeJSONObject(loopPerson));
	}

	public void uploadCoverImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			uploadPortletRequest, themeDisplay);

		_validateUploadCoverImage(loopPerson);

		uploadImage(uploadPortletRequest, LoopPerson.class);
	}

	public void uploadProfileImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			uploadPortletRequest, themeDisplay);

		_validateUploadProfileImage(loopPerson);

		uploadImage(uploadPortletRequest, LoopPerson.class);
	}

	public void validate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, String[]> parameterMap = request.getParameterMap();

		if (parameterMap.containsKey("emailAddress")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String emailAddress = ParamUtil.getString(request, "emailAddress");

			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				themeDisplay.getCompanyId(), emailAddress);

			if (user != null) {
				jsonObject.put(
					"reason", translate("the-email-address-already-exists"));
				jsonObject.put("valid", false);
			}
			else {
				jsonObject.put("valid", true);
			}

			respondWith(jsonObject);
		}
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE, parameterNames = "id",
		parameterTypes = Long.class
	)
	public void view() throws Exception {
		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateView(loopPerson);

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		if (isRespondingTo("json")) {
			JSONObject jsonObject = loopPersonComposite.getJSONObject(
				new String[] {
					"descriptionMarkdownHTML",
					"jobResponsibilitiesMarkdownHTML",
					"otherEmailAddressesJSONArray", "otherPhonesJSONArray",
					"permissionEdit", "permissionDelete"
				});

			if (getAPIVersion() < 5) {
				String jobResponsibilitiesMarkdownHTML =
					(String)jsonObject.remove(
						"jobResponsibilitiesMarkdownHTML");

				jsonObject.put(
					"jobResponsibilities", jobResponsibilitiesMarkdownHTML);
			}

			respondWith(jsonObject);

			return;
		}

		renderRequest.setAttribute("loopPersonComposite", loopPersonComposite);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE)
	public void viewCurrentLoopPerson() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		respondWith(_getLoopPersonCompositeJSONObject(curLoopPerson));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start", "type"},
		parameterTypes = {
			Integer.class, Long.class, Integer.class, Integer.class
		}
	)
	public void viewDivisions() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewDivisions(loopPerson);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		int type = ParamUtil.getInteger(request, "type");
		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		List<Long> loopDivisionIds =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionIds(
					loopPerson.getCompanyId(), loopPerson.getLoopPersonId(),
					type, true, start, end);

		for (long loopDivisionId : loopDivisionIds) {
			LoopDivision loopDivision =
				LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

			LoopDivisionComposite loopDivisionComposite =
				new LoopDivisionComposite(loopDivision, themeDisplay);

			jsonArray.put(loopDivisionComposite.getJSONObject());
		}

		if (getAPIVersion() == 1) {
			respondWith(jsonArray);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("results", jsonArray);
			jsonObject.put(
				"total",
				LoopParticipantAssignmentUtil.
					getLoopParticipantAssignmentLoopDivisionCount(
						loopPerson.getCompanyId(), loopPerson.getLoopPersonId(),
						type, true));

			respondWith(jsonObject);
		}
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewFollowingDivisions() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewFollowingDivisions(loopPerson);

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
				loopPerson.getLoopPersonId(), "classNameId",
				PortalUtil.getClassNameId(LoopDivision.class), "following",
				true);

		List<OrderByComparator> obcs = new ArrayList<>();

		obcs.add(
			OrderByComparatorFactoryUtil.create(
				LoopDivisionModelImpl.TABLE_NAME, "type_", true));
		obcs.add(
			OrderByComparatorFactoryUtil.create("Organization_", "name", true));

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		List<Long> loopDivisionIds = LoopDivisionUtil.getLoopDivisionIds(
			LoopStreamEntryModelImpl.TABLE_NAME, "classPK", whereConditions,
			obcs, start, end);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"results",
			LoopCompositeUtil.getCompositesJSONArray(
				loopDivisionIds, LoopDivisionComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}, false));
		jsonObject.put(
			"total",
			LoopDivisionUtil.getLoopDivisionCount(
				LoopStreamEntryModelImpl.TABLE_NAME, "classPK",
				whereConditions));

		respondWith(jsonObject);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewFollowingPeople() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewFollowingPeople(loopPerson);

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopStreamEntryModelImpl.TABLE_NAME, "loopPersonId",
				loopPerson.getLoopPersonId(), "classNameId",
				PortalUtil.getClassNameId(LoopPerson.class), "following", true);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopStreamEntryModelImpl.TABLE_NAME, "classPK",
				whereConditions, start, end);

		JSONArray jsonArray = LoopCompositeUtil.getCompositesJSONArray(
			loopPersonComposites);

		if (getAPIVersion() < 8) {
			respondWith(jsonArray);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("results", jsonArray);
			jsonObject.put(
				"total",
				LoopPersonUtil.getLoopPersonCount(
					LoopStreamEntryModelImpl.TABLE_NAME, "classPK",
					whereConditions));

			respondWith(jsonObject);
		}
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewFollowingTopics() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewFollowingTopics(loopPerson);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			LoopTopicModelImpl.TABLE_NAME, "name", true);

		List<Long> loopTopicIds = LoopTopicUtil.getFollowingLoopTopicIds(
			loopPerson.getLoopPersonId(), start, end, obc);

		jsonObject.put(
			"results",
			LoopCompositeUtil.getCompositesJSONArray(
				loopTopicIds, LoopTopicComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}, false));

		jsonObject.put(
			"total",
			LoopTopicUtil.getFollowingLoopTopicCount(
				loopPerson.getLoopPersonId()));

		respondWith(jsonObject);
	}

	public void viewLeadingDivisions() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewLeadingDivisions(loopPerson);

		List<LoopDivisionComposite> loopDivisionComposites =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionCompositesByLead(
					themeDisplay, loopPerson.getLoopPersonId());

		respondWith(
			LoopCompositeUtil.getCompositesJSONArray(loopDivisionComposites));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewManagers() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewManagers(loopPerson);

		respondWith(
			_getLoopPersonRelJSONObject(
				loopPerson.getLoopPersonId(), "parentLoopPersonId",
				"childLoopPersonId", true));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "start", "startTime"},
		parameterTypes = {Integer.class, Integer.class, Long.class}
	)
	public void viewNewPeople() throws Exception {
		Map<String, Serializable> attributes = new HashMap<>();

		long startTime = ParamUtil.getLong(
			request, "startTime", System.currentTimeMillis());

		attributes.put("startTimeMax", startTime);
		attributes.put(
			"startTimeMin",
			startTime -
				(LoopWebConfigurationValues.NEW_HIRE_AGE_THRESHOLD * Time.DAY));

		Sort[] sorts = {
			new Sort("startTime_sortable", Sort.LONG_TYPE, true),
			new Sort("firstName_sortable", false),
			new Sort("lastName_sortable", false)
		};

		respondWith(doSearch(indexer, alloyServiceInvoker, attributes, sorts));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE, methodName = "viewNewPeople",
		parameterNames = "startTime", parameterTypes = Long.class
	)
	public void viewNewPeople1() throws Exception {
		viewNewPeople();
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewSubordinates() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewSubordinates(loopPerson);

		respondWith(
			_getLoopPersonRelJSONObject(
				loopPerson.getLoopPersonId(), "childLoopPersonId",
				"parentLoopPersonId", false));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE, parameterNames = "id",
		parameterTypes = Long.class
	)
	public void viewVerifiedLoopTopics() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		_validateViewVerifiedLoopTopics(loopPerson);

		List<LoopTopicComposite> loopTopicComposites =
			LoopTopicAssignmentUtil.getLoopTopicAssignmentLoopTopicComposites(
				themeDisplay, loopPerson.getLoopPersonId(),
				LoopTopicConstants.STATUS_VERIFIED);

		respondWith(
			LoopCompositeUtil.getCompositesJSONArray(loopTopicComposites));
	}

	@Override
	protected MessageListener buildControllerMessageListener() {
		return LoopPersonControllerMessageListener.getInstance(this);
	}

	@Override
	protected Indexer buildIndexer() {
		return LoopPersonIndexer.getInstance();
	}

	@Override
	protected MessageListener buildSchedulerMessageListener() {
		return LoopPersonSchedulerMessageListener.getInstance(this);
	}

	@Override
	protected String getControllerDestinationName() {
		return LoopPersonConstants.CONTROLLER_DESTINATION_NAME;
	}

	@Override
	protected Trigger getSchedulerTrigger() {
		return TriggerFactoryUtil.createTrigger(
			getSchedulerJobName(), getMessageListenerGroupName(),
			LoopWebConfigurationValues.LOOP_CRON_TRIGGER_PEOPLE_CONTROLLER);
	}

	private EmailAddress _getEmailAddress(
			long classNameId, long classPK, String address)
		throws Exception {

		AlloyServiceInvoker emailAddressAlloyServiceInvoker =
			new AlloyServiceInvoker(EmailAddress.class.getName());

		List<EmailAddress> emailAddresses =
			emailAddressAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"classNameId", classNameId, "classPK", classPK, "address",
					address
				});

		if (!emailAddresses.isEmpty()) {
			return emailAddresses.get(0);
		}

		EmailAddress emailAddress =
			EmailAddressLocalServiceUtil.createEmailAddress(0);

		emailAddress.setAddress(address);
		emailAddress.setTypeId(ListTypeConstants.CONTACT_EMAIL_ADDRESS_DEFAULT);

		return emailAddress;
	}

	private String _getExtraData(LoopPerson loopPerson) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			loopPerson.getExtraData());

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		String description = ParamUtil.getString(request, "description");

		jsonObject.put("description", description);

		int employmentType = ParamUtil.getInteger(request, "employmentType");

		List<String> loopPersonDisabledFields =
			LoopPersonUtil.getLoopPersonDisabledFields();

		if (loopPersonDisabledFields.contains("employmentType")) {
			employmentType = loopPersonComposite.getEmploymentType();
		}

		jsonObject.put("employmentType", employmentType);

		String gitHubSn = ParamUtil.getString(request, "gitHubSn");

		jsonObject.put("gitHubSn", gitHubSn);

		String jobResponsibilities = ParamUtil.getString(
			request, "jobResponsibilities");

		if (loopPersonDisabledFields.contains("jobResponsibilities")) {
			jobResponsibilities = loopPersonComposite.getJobResponsibilities();
		}

		jsonObject.put("jobResponsibilities", jobResponsibilities);

		String languages = ParamUtil.getString(request, "languages");

		jsonObject.put("languages", languages);

		String linkedInSn = ParamUtil.getString(request, "linkedInSn");

		jsonObject.put("linkedInSn", linkedInSn);

		String preferredName = ParamUtil.getString(request, "preferredName");

		if (loopPersonDisabledFields.contains("preferredName")) {
			preferredName = loopPersonComposite.getPreferredName();
		}

		jsonObject.put("preferredName", preferredName);

		boolean showAge = ParamUtil.getBoolean(request, "showAge");

		jsonObject.put("showAge", showAge);

		boolean showBirthday = ParamUtil.getBoolean(request, "showBirthday");

		jsonObject.put("showBirthday", showBirthday);

		int startDateDay = ParamUtil.getInteger(request, "startDateDay");
		int startDateMonth = ParamUtil.getInteger(request, "startDateMonth");
		int startDateYear = ParamUtil.getInteger(request, "startDateYear");

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear);

		if (loopPersonDisabledFields.contains("startDateDay")) {
			Calendar startDateCalendar = loopPersonComposite.getStartDate();

			startDate = startDateCalendar.getTime();
		}

		if (startDate != null) {
			jsonObject.put("startTime", startDate.getTime());
		}

		return jsonObject.toString();
	}

	private List<Long> _getLongList(String paramName) {
		long[] ids = ParamUtil.getLongValues(request, paramName);

		return ListUtil.toList(ids);
	}

	private JSONObject _getLoopPersonCompositeJSONObject(LoopPerson loopPerson)
		throws Exception {

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			loopPerson, themeDisplay);

		return loopPersonComposite.getJSONObject();
	}

	private JSONObject _getLoopPersonRelJSONObject(
			long loopPersonId, String loopPersonIdColumnName,
			String whereConditionColumnName, boolean showType)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopPersonRelModelImpl.TABLE_NAME, whereConditionColumnName,
				loopPersonId);

		List<LoopPersonComposite> loopPersonComposites =
			LoopPersonUtil.getLoopPersonComposites(
				themeDisplay, LoopPersonRelModelImpl.TABLE_NAME,
				loopPersonIdColumnName, whereConditions, start, end);

		JSONArray loopPersonCompositesJSONArray =
			LoopCompositeUtil.getCompositesJSONArray(loopPersonComposites);

		if (showType) {
			JSONArray updatedloopPersonCompositesArray =
				JSONFactoryUtil.createJSONArray();

			for (int i = 0; i < loopPersonCompositesJSONArray.length(); i++) {
				JSONObject loopPersonCompositeJSONObject =
					loopPersonCompositesJSONArray.getJSONObject(i);

				long entityClassPK = loopPersonCompositeJSONObject.getLong(
					"entityClassPK");

				LoopPersonRel loopPersonRel =
					LoopPersonRelUtil.getLoopPersonRel(
						loopPersonId, entityClassPK);

				loopPersonCompositeJSONObject.put(
					"managerTypeLabel",
					translate(
						LoopPersonRelConstants.getTypeLabel(
							loopPersonRel.getType())));

				updatedloopPersonCompositesArray.put(
					loopPersonCompositeJSONObject);
			}

			jsonObject.put("results", updatedloopPersonCompositesArray);
		}
		else {
			jsonObject.put("results", loopPersonCompositesJSONArray);
		}

		jsonObject.put(
			"total",
			LoopPersonUtil.getLoopPersonCount(
				LoopPersonRelModelImpl.TABLE_NAME, loopPersonIdColumnName,
				whereConditions));

		return jsonObject;
	}

	private Phone _getPhone(
			long classNameId, long classPK, JSONObject phoneJSONObject,
			boolean primary)
		throws Exception {

		AlloyServiceInvoker phoneAlloyServiceInvoker = new AlloyServiceInvoker(
			Phone.class.getName());

		String number = phoneJSONObject.getString("number");
		long typeId = phoneJSONObject.getLong("typeId");

		List<Phone> phones = phoneAlloyServiceInvoker.executeDynamicQuery(
			new Object[] {
				"classNameId", classNameId, "classPK", classPK, "number",
				number, "typeId", typeId, "primary", primary
			});

		if (!phones.isEmpty()) {
			return phones.get(0);
		}

		Phone phone = PhoneLocalServiceUtil.createPhone(0);

		phone.setNumber(number);
		phone.setTypeId(typeId);
		phone.setPrimary(primary);

		return phone;
	}

	private long[] _getRoleIds() throws Exception {
		Set<Long> roleIds = new HashSet<>();

		LoopPerson loopPerson = LoopPersonUtil.fetchLoopPerson(
			request, themeDisplay);

		if (loopPerson != null) {
			User personUser = UserLocalServiceUtil.getUser(
				loopPerson.getPersonUserId());

			roleIds.addAll(ListUtil.toList(personUser.getRoleIds()));
		}

		Role role = RoleLocalServiceUtil.getRole(
			themeDisplay.getCompanyId(), LoopRoleConstants.LOOP_PERSON);

		roleIds.add(role.getRoleId());

		LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
			themeDisplay);

		if (loopPersonComposite.getPermissionSetRole()) {
			String[] roleNames = StringUtil.split(
				ParamUtil.getString(request, "roleNames"));

			for (String roleName : roleNames) {
				role = RoleLocalServiceUtil.getRole(
					themeDisplay.getCompanyId(), roleName);

				roleIds.add(role.getRoleId());
			}
		}

		return ArrayUtil.toLongArray(roleIds);
	}

	private void _setImage(
			LoopPerson loopPerson, String keyword, String[] imageTypes,
			String imageTypeKey)
		throws Exception {

		if (!isRespondingTo("json")) {
			return;
		}

		loopPerson = (LoopPerson)setImage(
			loopPerson, keyword, imageTypes, imageTypeKey);

		if (keyword.equals("profileImagePayload")) {
			JSONObject imagePayloadJSONObject =
				JSONFactoryUtil.createJSONObject(loopPerson.getImagePayload());

			JSONObject profileImagePayloadJSONObject =
				imagePayloadJSONObject.getJSONObject("profileImagePayload");

			JSONObject fileEntryIdsJSONObject =
				profileImagePayloadJSONObject.getJSONObject("fileEntryIds");

			long fileEntryId = fileEntryIdsJSONObject.getLong(
				LoopConstants.IMAGE_TYPE_WEB);

			DLFileEntry dlFileEntry =
				DLFileEntryLocalServiceUtil.getDLFileEntry(fileEntryId);

			UserLocalServiceUtil.updatePortrait(
				loopPerson.getPersonUserId(),
				FileUtil.getBytes(dlFileEntry.getContentStream()));
		}

		respondWith(_getLoopPersonCompositeJSONObject(loopPerson));
	}

	private String _translateEmailAddressException(Exception e)
		throws Exception {

		if (e instanceof EmailAddressException) {
			return "please-enter-a-valid-email-address";
		}
		else if (e instanceof NoSuchListTypeException) {
			return "please-select-a-type";
		}

		throw e;
	}

	private String _translatePhoneException(Exception e) throws Exception {
		if (e instanceof PhoneNumberException) {
			return "please-enter-a-valid-phone-number";
		}
		else if (e instanceof NoSuchListTypeException) {
			return "please-select-a-type";
		}

		throw e;
	}

	private String _translateUserException(Exception e) throws Exception {
		if (e instanceof UserEmailAddressException.MustNotBeDuplicate) {
			return "the-email-address-you-requested-is-already-taken";
		}
		else if (e instanceof UserScreenNameException.MustNotBeDuplicate) {
			return "the-screen-name-you-requested-is-already-taken";
		}
		else if (e instanceof GroupFriendlyURLException) {
			GroupFriendlyURLException gfurle = (GroupFriendlyURLException)e;

			if (gfurle.getType() == GroupFriendlyURLException.DUPLICATE) {
				return "the-screen-name-you-requested-is-associated-with-an-" +
					"existing-friendly-url";
			}
		}
		else if (e instanceof UserEmailAddressException.MustNotBeReserved) {
			return "the-email-address-you-requested-is-reserved";
		}
		else if (e instanceof UserScreenNameException.MustNotBeReserved) {
			return "the-screen-name-you-requested-is-reserved";
		}
		else if (e instanceof UserEmailAddressException) {
			return "please-enter-a-valid-email-address";
		}
		else if (e instanceof UserScreenNameException) {
			return "please-enter-a-valid-screen-name";
		}

		throw e;
	}

	private void _updateUserFields(User user) throws Exception {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		try {
			PermissionChecker tempPermissionChecker =
				PermissionCheckerFactoryUtil.create(user);

			PermissionThreadLocal.setPermissionChecker(tempPermissionChecker);

			try {
				List<EmailAddress> emailAddresses = new ArrayList<>();

				String emailAddressesString = ParamUtil.getString(
					request, "emailAddresses");

				JSONArray emailAddressesJSONArray =
					JSONFactoryUtil.createJSONArray(emailAddressesString);

				for (int i = 0; i < emailAddressesJSONArray.length(); i++) {
					EmailAddress emailAddress = _getEmailAddress(
						PortalUtil.getClassNameId(Contact.class),
						user.getContactId(),
						emailAddressesJSONArray.getString(i));

					emailAddresses.add(emailAddress);
				}

				UsersAdminUtil.updateEmailAddresses(
					Contact.class.getName(), user.getContactId(),
					emailAddresses);
			}
			catch (Exception e) {
				String message = _translateEmailAddressException(e);

				throw new AlloyException(message);
			}

			try {
				boolean primary = true;

				List<Phone> phones = new ArrayList<>();

				String phonesString = ParamUtil.getString(request, "phones");

				JSONArray phonesJSONArray = JSONFactoryUtil.createJSONArray(
					phonesString);

				for (int i = 0; i < phonesJSONArray.length(); i++) {
					Phone phone = _getPhone(
						PortalUtil.getClassNameId(Contact.class),
						user.getContactId(), phonesJSONArray.getJSONObject(i),
						primary);

					phones.add(phone);

					primary = false;
				}

				UsersAdminUtil.updatePhones(
					Contact.class.getName(), user.getContactId(), phones);
			}
			catch (Exception e) {
				String message = _translatePhoneException(e);

				throw new AlloyException(message);
			}
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(permissionChecker);
		}
	}

	private void _validateActivate(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);
	}

	private void _validateAdd() throws Exception {
		_validateLastName();
		_validateLoopJobTitleId();
		_validateRoleName();
	}

	private void _validateDelete(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);

		long loopPersonsCount = alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {
				"companyId", themeDisplay.getCompanyId(), "managerLoopPersonId",
				loopPerson.getLoopPersonId()
			});

		if (loopPersonsCount > 0) {
			throw new AlloyException("you-cannot-delete-a-manager");
		}
	}

	private void _validateEdit(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);
	}

	private void _validateLastName() throws Exception {
		List<String> loopPersonDisabledFields =
			LoopPersonUtil.getLoopPersonDisabledFields();

		if (loopPersonDisabledFields.contains("lastName")) {
			return;
		}

		String lastName = ParamUtil.getString(request, "lastName");

		if (Validator.isNull(lastName)) {
			throw new AlloyException("the-last-name-is-invalid");
		}
	}

	private void _validateLoopJobTitleId() throws Exception {
		long loopJobTitleId = ParamUtil.getLong(request, "loopJobTitleId");

		if (loopJobTitleId > 0) {
			LoopJobTitle loopJobTitle =
				LoopJobTitleLocalServiceUtil.fetchLoopJobTitle(loopJobTitleId);

			if (loopJobTitle == null) {
				throw new AlloyException(
					"the-job-title-with-id-x-does-not-exist",
					new Object[] {loopJobTitleId});
			}
		}
	}

	private void _validateLoopPerson(LoopPerson loopPerson) throws Exception {
		if (loopPerson == null) {
			throw new AlloyException("the-person-does-not-exist");
		}
	}

	private void _validateRemoveManager(
			LoopPerson loopPerson, LoopPerson managerLoopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
		_validateLoopPerson(managerLoopPerson);
	}

	private void _validateRemoveSubordinate(
			LoopPerson loopPerson, LoopPerson subordinateLoopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
		_validateLoopPerson(subordinateLoopPerson);
	}

	private void _validateRoleName() throws Exception {
		String roleName = ParamUtil.getString(request, "roleName");

		if (Validator.isNull(roleName)) {
			return;
		}

		List<String> loopRoles = Arrays.asList(LoopRoleConstants.LOOP_ROLES);

		if (!loopRoles.contains(roleName)) {
			throw new AlloyException("the-user-role-is-invalid");
		}
	}

	private void _validateSetCoverImage(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateSetManagers(
			LoopPerson childLoopPerson, List<Long> parentLoopPersonIds)
		throws Exception {

		_validateLoopPerson(childLoopPerson);

		if (parentLoopPersonIds.contains(childLoopPerson.getLoopPersonId())) {
			throw new AlloyException(
				"people-cannot-be-a-manager-of-themselves");
		}

		for (long parentLoopPersonId : parentLoopPersonIds) {
			LoopPerson parentLoopPerson =
				LoopPersonLocalServiceUtil.fetchLoopPerson(parentLoopPersonId);

			_validateLoopPerson(parentLoopPerson);
		}
	}

	private void _validateSetProfileImage(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateSetSubordinates(
			List<Long> childLoopPersonIds, LoopPerson parentLoopPerson)
		throws Exception {

		_validateLoopPerson(parentLoopPerson);

		if (childLoopPersonIds.contains(parentLoopPerson.getLoopPersonId())) {
			throw new AlloyException(
				"people-cannot-be-a-manager-of-themselves");
		}

		for (long childLoopPersonId : childLoopPersonIds) {
			LoopPerson childLoopPerson =
				LoopPersonLocalServiceUtil.fetchLoopPerson(childLoopPersonId);

			_validateLoopPerson(childLoopPerson);
		}
	}

	private void _validateUpdate(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);

		Map<String, String[]> parameterMap = request.getParameterMap();

		List<String> loopPersonDisabledFields =
			LoopPersonUtil.getLoopPersonDisabledFields();

		for (String loopPersonDisabledField : loopPersonDisabledFields) {
			if (parameterMap.containsKey(loopPersonDisabledField)) {
				throw new AlloyException(
					"you-do-not-have-permission-to-access-the-requested-" +
						"resource");
			}
		}

		String[] loopTopicNames = StringUtil.split(
			ParamUtil.getString(request, "loopTopicNames"));

		for (String loopTopicName : loopTopicNames) {
			LoopTopic loopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
				company.getCompanyId(), loopTopicName);

			if ((loopTopic != null) && (loopTopic.getParentLoopTopicId() > 0)) {
				throw new AlloyException(
					"you-cannot-assign-yourself-as-an-expert-of-a-child-topic");
			}
		}

		_validateLastName();
		_validateLoopJobTitleId();
		_validateRoleName();
	}

	private void _validateUploadCoverImage(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateUploadProfileImage(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateView(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);
	}

	private void _validateViewDivisions(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewFollowingDivisions(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewFollowingPeople(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewFollowingTopics(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewLeadingDivisions(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewManagers(LoopPerson loopPerson) throws Exception {
		_validateLoopPerson(loopPerson);
	}

	private void _validateViewSubordinates(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

	private void _validateViewVerifiedLoopTopics(LoopPerson loopPerson)
		throws Exception {

		_validateLoopPerson(loopPerson);
	}

}