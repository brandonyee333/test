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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopParticipantAssignment;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopParticipantAssignmentConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.osb.loop.web.internal.constants.LoopPersonRelConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.exportimport.UserImporter;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Timothy Bell
 */
@SuppressWarnings("unused")
public class LoopSyncUtil {

	public static void invokeTransaction(
			String methodName, Object[] parameters, Class<?>[] parameterTypes)
		throws Exception {

		Method invokeStaticMethod = ReflectionUtil.getDeclaredMethod(
			LoopSyncUtil.class, "invokeStaticMethod", Method.class,
			Object[].class);

		Method crudMethod = ReflectionUtil.getDeclaredMethod(
			LoopSyncUtil.class, methodName, parameterTypes);

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			null, LoopSyncUtil.class, invokeStaticMethod,
			new Object[] {crudMethod, parameters},
			new String[] {"transactionAdvice"});
	}

	protected static User fetchUser(
		AlloyController alloyController, JSONObject jsonObject) {

		String uuid = jsonObject.getString("id");

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		return UserLocalServiceUtil.fetchUserByUuidAndCompanyId(
			uuid, themeDisplay.getCompanyId());
	}

	protected static LoopDivision getLoopDivision(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		String id = jsonObject.getString("id");

		return LoopDivisionUtil.getLoopDivision(
			themeDisplay.getCompanyId(), id);
	}

	protected static LoopDivision getParentLoopDivision(
			AlloyController alloyController, String name)
		throws Exception {

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		if (Validator.isNull(name)) {
			return LoopDivisionUtil.getRootLoopDivision(alloyController);
		}

		return LoopDivisionUtil.getLoopDivision(
			themeDisplay.getCompanyId(), name);
	}

	protected static User getUser(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		String uuid = jsonObject.getString("id");

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		return UserLocalServiceUtil.getUserByUuidAndCompanyId(
			uuid, themeDisplay.getCompanyId());
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	protected static void invokeStaticMethod(
			Method method, Object... parameters)
		throws Exception {

		method.invoke(null, parameters);
	}

	protected static void setEmploymentType(
			LoopPerson loopPerson, String employmentTypeLabel)
		throws Exception {

		String extraData = loopPerson.getExtraData();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(extraData);

		int employmentType = LoopPersonConstants.getEmploymentLabelType(
			employmentTypeLabel);

		jsonObject.put("employmentType", employmentType);

		loopPerson.setExtraData(jsonObject.toString());
	}

	protected static void setHireDateTime(
			LoopPerson loopPerson, long hireDateTime)
		throws Exception {

		String extraData = loopPerson.getExtraData();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(extraData);

		jsonObject.put("startTime", hireDateTime);

		loopPerson.setExtraData(jsonObject.toString());
	}

	protected static void setLoopJobTitleId(
			AlloyController alloyController, LoopPerson loopPerson, String name)
		throws Exception {

		if (Validator.isNull(name)) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.getLoopJobTitle(
			alloyController.getThemeDisplay(), name);

		loopPerson.setLoopJobTitleId(loopJobTitle.getLoopJobTitleId());
	}

	protected static void syncActivateLoopPerson(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		User user = getUser(alloyController, jsonObject);

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
				user.getUserId());

		LoopPersonUtil.updateStatus(
			alloyController, loopPerson, WorkflowConstants.STATUS_APPROVED,
			new ServiceContext());
	}

	protected static void syncAddLoopDivision(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.createLoopDivision(0);

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		User user = UserLocalServiceUtil.getDefaultUser(
			themeDisplay.getCompanyId());

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		LoopDivision parentLoopDivision = getParentLoopDivision(
			alloyController, dataJSONObject.getString("parentName"));

		String name = dataJSONObject.getString("name");

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				user.getUserId(), parentLoopDivision.getOrganizationId(), name,
				OrganizationConstants.TYPE_ORGANIZATION, 0, 0,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, null, false,
				null);

		LoopUtil.deleteUserGroupRole(
			user.getUserId(), organization.getGroupId(),
			themeDisplay.getCompanyId(), RoleConstants.ORGANIZATION_OWNER);

		int subtype = 0;

		int type = 0;

		String entityName = jsonObject.getString("entityName");

		if (entityName.equals("branch")) {
			subtype = LoopDivisionConstants.SUBTYPE_DEPARTMENT_FUNCTIONAL;

			type = LoopDivisionConstants.TYPE_DEPARTMENT;
		}
		else if (entityName.equals("location")) {
			subtype = LoopDivisionConstants.SUBTYPE_LOCATION_OFFICE;

			type = LoopDivisionConstants.TYPE_LOCATION;
		}

		alloyController.updateModelIgnoreRequest(
			loopDivision, "organizationId", organization.getOrganizationId(),
			"parentLoopDivisionId", parentLoopDivision.getLoopDivisionId(),
			"subtype", subtype, "type", type);

		LoopDivisionUtil.sendRabbitMQMessage(
			alloyController.getThemeDisplay(),
			LoopWebConfigurationValues.RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_ADD,
			loopDivision);
	}

	protected static void syncAddLoopJobTitle(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopJobTitle loopJobTitle =
			LoopJobTitleLocalServiceUtil.createLoopJobTitle(0);

		updateLoopJobTitle(alloyController, loopJobTitle, jsonObject);
	}

	protected static void syncAddLoopPerson(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		User user = fetchUser(alloyController, jsonObject);

		if (user == null) {
			ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

			JSONObject dataJSONObject = jsonObject.getJSONObject("data");

			String emailAddress = dataJSONObject.getString("emailAddress");

			UserImporter userImporter =
				_instance._userImporterServiceTracker.getService();

			user = userImporter.importUser(
				themeDisplay.getCompanyId(), emailAddress, StringPool.BLANK);

			if (user == null) {
				throw new Exception(
					"No user found with email address " + emailAddress);
			}
		}

		updateLoopPerson(alloyController, user, jsonObject);
	}

	protected static void syncDeleteLoopDivision(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopDivision loopDivision = getLoopDivision(
			alloyController, jsonObject);

		LoopDivisionUtil.deleteLoopDivision(alloyController, loopDivision);
	}

	protected static void syncDeleteLoopJobTitle(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.getLoopJobTitle(
			alloyController.getThemeDisplay(), jsonObject.getString("id"));

		LoopJobTitleLocalServiceUtil.deleteLoopJobTitle(loopJobTitle);
	}

	protected static void syncDeleteLoopPerson(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		User user = getUser(alloyController, jsonObject);

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
				user.getUserId());

		LoopPersonUtil.updateStatus(
			alloyController, loopPerson, WorkflowConstants.STATUS_INACTIVE,
			new ServiceContext());
	}

	protected static void syncUpdateLoopDivision(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopDivision loopDivision = getLoopDivision(
			alloyController, jsonObject);

		JSONObject oldLoopDivisionJSONObjectComposite =
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				alloyController.getThemeDisplay(), loopDivision);

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		if (dataJSONObject.has("parentName")) {
			LoopDivisionUtil.setParentLoopDivision(
				alloyController, loopDivision.getLoopDivisionId(),
				getParentLoopDivision(
					alloyController, dataJSONObject.getString("parentName")));
		}

		if (dataJSONObject.has("name")) {
			updateOrganizationName(
				loopDivision, dataJSONObject.getString("name"));
		}

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			LoopDivision.class);

		indexer.reindex(loopDivision);

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_UPDATE,
			loopDivision.getOrganizationId(),
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				alloyController.getThemeDisplay(), loopDivision),
			oldLoopDivisionJSONObjectComposite);
	}

	protected static void syncUpdateLoopJobTitle(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.getLoopJobTitle(
			alloyController.getThemeDisplay(), jsonObject.getString("id"));

		updateLoopJobTitle(alloyController, loopJobTitle, jsonObject);
	}

	protected static void syncUpdateLoopPerson(
			AlloyController alloyController, JSONObject jsonObject)
		throws Exception {

		User user = getUser(alloyController, jsonObject);

		updateLoopPerson(alloyController, user, jsonObject);
	}

	protected static void updateLoopJobTitle(
			AlloyController alloyController, LoopJobTitle loopJobTitle,
			JSONObject jsonObject)
		throws Exception {

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Iterator<String> iterator = dataJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (key.equals("active")) {
				int status = WorkflowConstants.STATUS_APPROVED;

				if (!dataJSONObject.getBoolean(key)) {
					status = WorkflowConstants.STATUS_INACTIVE;
				}

				loopJobTitle.setStatus(status);
			}
			else if (key.equals("description")) {
				loopJobTitle.setDescription(dataJSONObject.getString(key));
			}
			else if (key.equals("name")) {
				loopJobTitle.setName(dataJSONObject.getString(key));
			}
		}

		alloyController.updateModelIgnoreRequest(loopJobTitle);
	}

	protected static void updateLoopParticipantAssignments(
			AlloyController alloyController, LoopPerson loopPerson, String name,
			String oldDepartmentName, int type)
		throws Exception {

		if (Validator.isNull(name)) {
			LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
				alloyController, new ArrayList<Long>(),
				loopPerson.getLoopPersonId(), loopPerson.getCompanyId(), type);

			return;
		}

		Set<Long> loopDivisionIds = new HashSet<>();

		LoopDivision loopDivision = LoopDivisionUtil.getLoopDivision(
			loopPerson.getCompanyId(), name);

		loopDivisionIds.add(loopDivision.getLoopDivisionId());

		if (type == LoopDivisionConstants.TYPE_DEPARTMENT) {
			AlloyServiceInvoker loopParticipantAssignmentAlloyServiceInvoker =
				new AlloyServiceInvoker(
					LoopParticipantAssignment.class.getName());

			DynamicQuery loopParticipantAssignmentDynamicQuery =
				loopParticipantAssignmentAlloyServiceInvoker.buildDynamicQuery(
					new Object[] {
						"loopPersonId", loopPerson.getLoopPersonId()
					});

			Property loopDivisionIdProperty = PropertyFactoryUtil.forName(
				"loopDivisionId");

			loopParticipantAssignmentDynamicQuery.setProjection(
				loopDivisionIdProperty);

			Criterion typeCriterion = RestrictionsFactoryUtil.not(
				RestrictionsFactoryUtil.eq(
					"type", LoopParticipantAssignmentConstants.TYPE_INHERITED));

			loopParticipantAssignmentDynamicQuery.add(typeCriterion);

			AlloyServiceInvoker loopDivisionAlloyServiceInvoker =
				new AlloyServiceInvoker(LoopDivision.class.getName());

			DynamicQuery loopDivisionDynamicQuery =
				loopDivisionAlloyServiceInvoker.buildDynamicQuery(
					new Object[] {
						"type", LoopDivisionConstants.TYPE_DEPARTMENT
					});

			loopDivisionDynamicQuery.setProjection(loopDivisionIdProperty);

			loopParticipantAssignmentDynamicQuery.add(
				loopDivisionIdProperty.in(loopDivisionDynamicQuery));

			List<Long> departmentLoopDivisionIds =
				loopParticipantAssignmentAlloyServiceInvoker.
					executeDynamicQuery(loopParticipantAssignmentDynamicQuery);

			loopDivisionIds.addAll(departmentLoopDivisionIds);

			if (Validator.isNotNull(oldDepartmentName)) {
				LoopDivision oldLoopDivision = LoopDivisionUtil.getLoopDivision(
					loopPerson.getCompanyId(), oldDepartmentName);

				loopDivisionIds.remove(oldLoopDivision.getLoopDivisionId());
			}
		}

		LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
			alloyController, new ArrayList<>(loopDivisionIds),
			loopPerson.getLoopPersonId(), loopPerson.getCompanyId(), type);
	}

	protected static void updateLoopPerson(
			AlloyController alloyController, User user, JSONObject jsonObject)
		throws Exception {

		String emailAddress = user.getEmailAddress();

		Contact contact = user.getContact();

		Boolean male = contact.isMale();

		Date birthday = contact.getBirthday();

		long birthdayTime = birthday.getTime();

		LoopPerson loopPerson =
			LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
				user.getUserId());

		JSONObject dataJSONObject = jsonObject.getJSONObject("data");

		Iterator<String> iterator = dataJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (key.equals("additionalManagerUuids")) {
				updateLoopPersonRels(
					alloyController, dataJSONObject.getJSONArray(key),
					loopPerson.getLoopPersonId(),
					LoopPersonRelConstants.TYPE_MANAGER);
			}
			else if (key.equals("birthDateTime")) {
				if (LoopWebConfigurationValues.
						RABBIT_MQ_SYNC_LOOP_PERSON_BIRTHDAY_ENABLED) {

					birthdayTime = dataJSONObject.getLong(key);
				}
			}
			else if (key.equals("department")) {
				JSONObject oldDataJSONObject = jsonObject.getJSONObject(
					"oldData");

				String oldDepartmentName = StringPool.BLANK;

				if (oldDataJSONObject != null) {
					oldDepartmentName = oldDataJSONObject.getString(
						"department");
				}

				updateLoopParticipantAssignments(
					alloyController, loopPerson, dataJSONObject.getString(key),
					oldDepartmentName, LoopDivisionConstants.TYPE_DEPARTMENT);
			}
			else if (key.equals("emailAddress")) {
				emailAddress = dataJSONObject.getString(key);
			}
			else if (key.equals("employmentTypeLabel")) {
				setEmploymentType(loopPerson, dataJSONObject.getString(key));
			}
			else if (key.equals("hireDateTime")) {
				setHireDateTime(loopPerson, dataJSONObject.getLong(key));
			}
			else if (key.equals("jobTitle")) {
				setLoopJobTitleId(
					alloyController, loopPerson, dataJSONObject.getString(key));
			}
			else if (key.equals("location")) {
				updateLoopParticipantAssignments(
					alloyController, loopPerson, dataJSONObject.getString(key),
					null, LoopDivisionConstants.TYPE_LOCATION);
			}
			else if (key.equals("male")) {
				male = dataJSONObject.getBoolean(key);
			}
			else if (key.equals("primaryManagerUuid")) {
				updateLoopPersonRel(
					alloyController, dataJSONObject.getString(key),
					loopPerson.getLoopPersonId(),
					LoopPersonRelConstants.TYPE_PRIMARY_MANAGER);
			}
		}

		Calendar calendar = CalendarFactoryUtil.getCalendar(birthdayTime);

		UserLocalServiceUtil.updateUser(
			user.getUserId(), null, null, null, false, null, null,
			user.getScreenName(), emailAddress, user.getFacebookId(),
			user.getOpenId(), false, null, user.getLanguageId(),
			user.getTimeZoneId(), user.getGreeting(), user.getComments(),
			user.getFirstName(), user.getMiddleName(), user.getLastName(),
			contact.getPrefixId(), contact.getSuffixId(), male,
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, null, null, user.getRoleIds(), null, null, null);

		alloyController.updateModelIgnoreRequest(loopPerson);
	}

	protected static void updateLoopPersonRel(
			AlloyController alloyController, String primaryManagerUuid,
			long loopPersonId, int type)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (Validator.isNotNull(primaryManagerUuid)) {
			jsonArray.put(primaryManagerUuid);

			updateLoopPersonRels(
				alloyController, jsonArray, loopPersonId, type);
		}
	}

	protected static void updateLoopPersonRels(
			AlloyController alloyController, JSONArray jsonArray,
			long loopPersonId, int type)
		throws Exception {

		List<Long> loopPersonIds = new ArrayList<>();

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		for (int i = 0; i < jsonArray.length(); i++) {
			String uuid = jsonArray.getString(i);

			User user = UserLocalServiceUtil.getUserByUuidAndCompanyId(
				uuid, themeDisplay.getCompanyId());

			LoopPerson loopPerson =
				LoopPersonLocalServiceUtil.getLoopPersonByPersonUserId(
					user.getUserId());

			loopPersonIds.add(loopPerson.getLoopPersonId());
		}

		LoopPersonRelUtil.setLoopPersonRel(
			alloyController, loopPersonId, loopPersonIds, type);
	}

	protected static void updateOrganizationName(
			LoopDivision loopDivision, String name)
		throws Exception {

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		OrganizationLocalServiceUtil.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(), null,
			false, null, false, null);
	}

	private LoopSyncUtil() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		_userImporterServiceTracker = new ServiceTracker<>(
			bundleContext, UserImporter.class, null);

		_userImporterServiceTracker.open();
	}

	private static final LoopSyncUtil _instance = new LoopSyncUtil();

	private final ServiceTracker<UserImporter, UserImporter>
		_userImporterServiceTracker;

}