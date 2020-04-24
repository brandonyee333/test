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
import com.liferay.alloy.mvc.AlloySearchResult;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.alloy.mvc.json.web.service.JSONWebServiceMethod;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopExternalReferenceRel;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopExternalReferenceRelLocalServiceUtil;
import com.liferay.osb.loop.service.LoopPersonLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.osb.loop.web.internal.constants.LoopParticipantAssignmentConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.indexer.LoopDivisionIndexer;
import com.liferay.osb.loop.web.internal.messaging.LoopDivisionControllerMessageListener;
import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.osb.loop.web.internal.util.LoopDivisionUtil;
import com.liferay.osb.loop.web.internal.util.LoopExternalReferenceRelUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ServiceBeanMethodInvocationFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Timothy Bell
 */
public class DivisionsController extends LoopAlloyControllerImpl {

	public DivisionsController() {
		setAlloyServiceInvokerClass(LoopDivision.class);
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAdd();

		int type = ParamUtil.getInteger(request, "type");
		int subtype = ParamUtil.getInteger(request, "subtype");

		LoopDivision loopDivision = _add(type, subtype);

		_setLoopDivisionLeads(loopDivision);

		pollBaseModel = loopDivision;

		pollHitsLength = 1;

		LoopDivisionUtil.sendRabbitMQMessage(
			themeDisplay,
			LoopWebConfigurationValues.RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_ADD,
			loopDivision);

		respondWith(_getLoopDivisionCompositeJSONObject(loopDivision));
	}

	public void addExternalReference() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAddExternalReference();

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		String externalReferenceName = ParamUtil.getString(
			request, "externalReferenceName");
		String externalReferencePK = ParamUtil.getString(
			request, "externalReferencePK");

		LoopExternalReferenceRelUtil.addLoopExternalReferenceRel(
			this, PortalUtil.getClassNameId(LoopDivision.class),
			loopDivision.getLoopDivisionId(), externalReferenceName,
			externalReferencePK);

		respondWith("The external reference was successfully added.");
	}

	public void addRegionalDepartment() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAddRegionalDepartment();

		LoopDivision loopDivision = _add(
			LoopDivisionConstants.TYPE_DEPARTMENT,
			LoopDivisionConstants.SUBTYPE_DEPARTMENT_REGIONAL);

		long departmentLoopDivisionId = ParamUtil.getLong(
			request, "departmentLoopDivisionId");
		long locationLoopDivisionId = ParamUtil.getLong(
			request, "locationLoopDivisionId");

		List<Long> loopPersonIds = _getCommonLoopPersonIds(
			departmentLoopDivisionId, locationLoopDivisionId);

		for (long loopPersonId : loopPersonIds) {
			LoopParticipantAssignmentUtil.addLoopParticipantAssignment(
				this, loopDivision.getLoopDivisionId(), loopPersonId,
				LoopParticipantAssignmentConstants.TYPE_MEMBER);
		}

		respondWith(_getLoopDivisionCompositeJSONObject(loopDivision));
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateDelete(loopDivision);

		LoopDivisionUtil.deleteLoopDivision(this, loopDivision);

		respondWith("The division was successfully deleted.");
	}

	public void deleteDepartment() throws Exception {
		delete();
	}

	public void deleteTeam() throws Exception {
		delete();
	}

	public void edit() throws Exception {
		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateEdit(loopDivision);

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		if (isRespondingTo("json")) {
			JSONObject jsonObject = loopDivisionComposite.getJSONObject(
				new String[] {
					"directMemberLoopPersonCompositesJSONArray",
					"inheritedMemberLoopPersonCompositesJSONArray",
					"leadAssistantLoopPersonCompositesJSONArray",
					"leadLoopPersonCompositesJSONArray",
					"permissionSetGitHubRepositories"
				});

			respondWith(jsonObject);

			return;
		}

		renderRequest.setAttribute(
			"loopDivisionComposite", loopDivisionComposite);
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(
			"parentLoopDivisionId",
			LoopDivisionUtil.getRootLoopDivisionId(this));

		boolean reverseSort = ParamUtil.getBoolean(request, "reverseSort");

		Sort[] sorts = {new Sort("name_sortable", reverseSort)};

		JSONObject jsonObject = doSearch(
			indexer, alloyServiceInvoker, attributes, sorts);

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			themeDisplay);

		jsonObject.put(
			"permissionCreate", loopDivisionComposite.getPermissionCreate());

		respondWith(jsonObject);
	}

	public void removeExternalReference() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateRemoveExternalReference();

		String externalReferenceName = ParamUtil.getString(
			request, "externalReferenceName");
		String externalReferencePK = ParamUtil.getString(
			request, "externalReferencePK");

		LoopExternalReferenceRel loopExternalReferenceRel =
			LoopExternalReferenceRelUtil.fetchLoopExternalReferenceRel(
				externalReferenceName, externalReferencePK);

		LoopExternalReferenceRelLocalServiceUtil.deleteLoopExternalReferenceRel(
			loopExternalReferenceRel);

		respondWith("The external reference was successfully removed.");
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

		respondWith(doSearch(indexer, alloyServiceInvoker, null, null));
	}

	public void setChildLoopDivisions() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		String childLoopDivisionIdStrings = ParamUtil.getString(
			request, "childLoopDivisionIds");

		List<Long> childLoopDivisionIds = ListUtil.toList(
			StringUtil.split(childLoopDivisionIdStrings, 0L));

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		JSONObject oldLoopDivisionCompositeJSONObject =
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, loopDivision);

		_validateSetChildLoopDivisions(childLoopDivisionIds, loopDivision);

		Class<?> clazz = getClass();

		Method setChildLoopDivisionsMethod = clazz.getDeclaredMethod(
			"_setChildLoopDivisions", List.class, LoopDivision.class);

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, clazz, setChildLoopDivisionsMethod,
			new Object[] {childLoopDivisionIds, loopDivision},
			new String[] {"transactionAdvice"});

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_CHILD,
			loopDivision.getOrganizationId(),
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, loopDivision),
			oldLoopDivisionCompositeJSONObject);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"childLoopDivisionCompositesJSONArray",
			LoopCompositeUtil.getCompositesJSONArray(
				childLoopDivisionIds, LoopDivisionComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}));
		jsonObject.put(
			"loopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(loopDivision));

		respondWith(jsonObject);
	}

	public void setCoverImage() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateSetCoverImage(loopDivision);

		loopDivision = (LoopDivision)setImage(
			loopDivision, "coverImagePayload",
			LoopWebConfigurationValues.IMAGE_COVER_TYPES,
			LoopWebConfigurationKeys.IMAGE_COVER_MAX_SIZE);

		respondWith(_getLoopDivisionCompositeJSONObject(loopDivision));
	}

	public void setParentLoopDivision() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision oldLoopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		_validateSetParentLoopDivision(oldLoopDivision, parentLoopDivision);

		JSONObject oldLoopDivisionJSONObjectComposite =
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, oldLoopDivision);

		Class<?> clazz = getClass();

		Method setParentLoopDivisionMethod = clazz.getDeclaredMethod(
			"_setParentLoopDivision", long.class, LoopDivision.class);

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, clazz, setParentLoopDivisionMethod,
			new Object[] {
				oldLoopDivision.getLoopDivisionId(), parentLoopDivision
			},
			new String[] {"transactionAdvice"});

		LoopDivision newLoopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(
				oldLoopDivision.getLoopDivisionId());

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARENT,
			newLoopDivision.getOrganizationId(),
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, newLoopDivision),
			oldLoopDivisionJSONObjectComposite);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"loopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(newLoopDivision));
		jsonObject.put(
			"parentLoopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(parentLoopDivision));

		respondWith(jsonObject);
	}

	public void setProfileImage() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateSetProfileImage(loopDivision);

		loopDivision = (LoopDivision)setImage(
			loopDivision, "profileImagePayload",
			LoopWebConfigurationValues.IMAGE_PROFILE_TYPES,
			LoopWebConfigurationKeys.IMAGE_PROFILE_MAX_SIZE);

		respondWith(_getLoopDivisionCompositeJSONObject(loopDivision));
	}

	public void setType() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateSetType(loopDivision);

		Class<?> clazz = getClass();

		Method setTypeMethod = clazz.getDeclaredMethod(
			"_setType", LoopDivision.class, LoopDivision.class);

		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		ServiceBeanMethodInvocationFactoryUtil.proceed(
			this, clazz, setTypeMethod,
			new Object[] {loopDivision, parentLoopDivision},
			new String[] {"transactionAdvice"});

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"childLoopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(loopDivision));
		jsonObject.put(
			"parentLoopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(parentLoopDivision));

		respondWith(jsonObject);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision oldLoopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		JSONObject oldLoopDivisionJSONObjectComposite =
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, oldLoopDivision);

		Class<?> clazz = getClass();

		Method updateMethod = clazz.getDeclaredMethod("_update");

		LoopDivision loopDivision =
			(LoopDivision)ServiceBeanMethodInvocationFactoryUtil.proceed(
				this, clazz, updateMethod, null,
				new String[] {"transactionAdvice"});

		LoopDivisionUtil.sendRabbitMQMessage(
			LoopWebConfigurationValues.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_UPDATE,
			loopDivision.getOrganizationId(),
			LoopDivisionUtil.getRabbitMQLoopDivisionCompositeJSONObject(
				themeDisplay, loopDivision),
			oldLoopDivisionJSONObjectComposite);

		respondWith(_getLoopDivisionCompositeJSONObject(loopDivision));
	}

	public void uploadCoverImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			uploadPortletRequest, themeDisplay);

		_validateUploadCoverImage(loopDivision);

		uploadImage(uploadPortletRequest, LoopDivision.class);
	}

	public void uploadProfileImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			uploadPortletRequest, themeDisplay);

		_validateUploadProfileImage(loopDivision);

		uploadImage(uploadPortletRequest, LoopDivision.class);
	}

	public void validate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, String[]> parameterMap = request.getParameterMap();

		if (parameterMap.containsKey("name")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String name = ParamUtil.getString(request, "name");

			long organizationId =
				OrganizationLocalServiceUtil.getOrganizationId(
					themeDisplay.getCompanyId(), name);

			if (organizationId > 0) {
				jsonObject.put("reason", translate("the-name-already-exists"));
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
		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateView(loopDivision);

		LoopDivisionComposite parentLoopDivisionComposite = null;

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		if (loopDivisionComposite.getType() !=
				LoopDivisionConstants.TYPE_ROOT) {

			parentLoopDivisionComposite = new LoopDivisionComposite(
				loopDivision.getParentLoopDivisionId(), themeDisplay);
		}

		if (isRespondingTo("json")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"loopDivisionCompositeJSONObject",
				loopDivisionComposite.getJSONObject(
					new String[] {
						"descriptionMarkdownHTML", "pagesCount",
						"permissionAddPages", "permissionCreatePages",
						"permissionEdit", "permissionSetChildLoopDivisions",
						"permissionSetType"
					}));

			if (parentLoopDivisionComposite != null) {
				jsonObject.put(
					"parentLoopDivisionCompositeJSONObject",
					parentLoopDivisionComposite.getJSONObject());
			}

			respondWith(jsonObject);

			return;
		}

		renderRequest.setAttribute(
			"loopDivisionComposite", loopDivisionComposite);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"createTime", "end", "id", "start", "subtype"},
		parameterTypes = {
			Long.class, Integer.class, Long.class, Integer.class, Integer.class
		}
	)
	public void viewChildLoopDivisions() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateViewChildLoopDivisions(loopDivision);

		Map<String, Serializable> attributes = _getAttributes(
			loopDivision.getLoopDivisionId(), loopDivision.getType());

		Sort[] sorts = {new Sort("name_sortable", false)};

		JSONObject jsonObject = doSearch(
			indexer, alloyServiceInvoker, attributes, sorts);

		if (getAPIVersion() == 1) {
			respondWith(jsonObject.getJSONArray("results"));
		}
		else {
			respondWith(jsonObject);
		}
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewChildLoopDivisions", parameterNames = "id",
		parameterTypes = Long.class
	)
	public void viewChildLoopDivisions2() throws Exception {
		viewChildLoopDivisions();
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewChildLoopDivisions",
		parameterNames = {"createTime", "end", "id", "start"},
		parameterTypes = {Long.class, Integer.class, Long.class, Integer.class}
	)
	public void viewChildLoopDivisions3() throws Exception {
		viewChildLoopDivisions();
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewLeads() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateViewLeads(loopDivision);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"results",
			_getLoopDivisionLoopPersonCompositesJSONArray(
				loopDivision, LoopParticipantAssignmentConstants.TYPE_LEAD));
		jsonObject.put(
			"total",
			LoopParticipantAssignmentUtil.getLoopParticipantAssignmentsCount(
				loopDivision, LoopParticipantAssignmentConstants.TYPE_LEAD));

		respondWith(jsonObject);
	}

	public void viewLoopDivisionHierarchy() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		if (loopDivision == null) {
			loopDivision = LoopDivisionUtil.getRootLoopDivision(this);
		}

		_validateViewLoopDivisionHierarchy(loopDivision);

		JSONArray childLoopDivisionJSONArray =
			JSONFactoryUtil.createJSONArray();

		int type = ParamUtil.getInteger(
			request, "type", loopDivision.getType());

		List<LoopDivision> childLoopDivisions =
			alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"companyId", themeDisplay.getCompanyId(),
					"parentLoopDivisionId", loopDivision.getLoopDivisionId(),
					"type", type
				});

		for (LoopDivision childLoopDivision : childLoopDivisions) {
			childLoopDivisionJSONArray.put(
				_getHierarchyJSONObject(
					childLoopDivision, JSONFactoryUtil.createJSONArray()));
		}

		JSONObject loopDivisionHierarchyJSONObject = _getHierarchyJSONObject(
			loopDivision, childLoopDivisionJSONArray);

		while (loopDivision.getType() != LoopDivisionConstants.TYPE_ROOT) {
			LoopDivision parentLoopDivision =
				LoopDivisionLocalServiceUtil.fetchLoopDivision(
					loopDivision.getParentLoopDivisionId());

			loopDivisionHierarchyJSONObject = _getHierarchyJSONObject(
				parentLoopDivision,
				_getSiblingLoopDivisionCompositeJSONArray(
					loopDivision, loopDivisionHierarchyJSONObject));

			loopDivision = parentLoopDivision;
		}

		respondWith(loopDivisionHierarchyJSONObject);
	}

	public void viewLoopDivisionTypeCounts() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(
			"parentLoopDivisionId",
			LoopDivisionUtil.getRootLoopDivisionId(this));

		for (int type : LoopDivisionConstants.TYPES) {
			attributes.put(Field.TYPE, type);

			AlloySearchResult alloySearchResult = search(
				attributes, null, null, 0, 0);

			jsonObject.put(String.valueOf(type), alloySearchResult.getSize());
		}

		respondWith(jsonObject);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewMembers() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateViewMembers(loopDivision);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"results",
			_getLoopDivisionLoopPersonCompositesJSONArray(
				loopDivision, LoopParticipantAssignmentConstants.TYPE_MEMBER));
		jsonObject.put(
			"total",
			LoopParticipantAssignmentUtil.getLoopParticipantAssignmentsCount(
				loopDivision, LoopParticipantAssignmentConstants.TYPE_MEMBER));

		respondWith(jsonObject);
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"createTime", "end", "start"},
		parameterTypes = {Long.class, Integer.class, Integer.class}
	)
	public void viewNewDivisions() throws Exception {
		Map<String, Serializable> attributes = new HashMap<>();

		long createTime = ParamUtil.getLong(
			request, "createTime", System.currentTimeMillis());

		attributes.put("createTimeMax", createTime);
		attributes.put(
			"createTimeMin",
			createTime -
				(LoopWebConfigurationValues.NEW_DIVISION_AGE_THRESHOLD *
					Time.DAY));

		Sort[] sorts = {
			new Sort("createDate_sortable", Sort.LONG_TYPE, true),
			new Sort("name_sortable", false)
		};

		respondWith(doSearch(indexer, alloyServiceInvoker, attributes, sorts));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewNewDivisions", parameterNames = "createTime",
		parameterTypes = Long.class
	)
	public void viewNewDivisions1() throws Exception {
		viewNewDivisions();
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE, parameterNames = "id",
		parameterTypes = Long.class
	)
	public void viewParentLoopDivision() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateViewParentLoopDivisions(loopDivision);

		JSONObject parentLoopDivisionCompositeJSONObject =
			JSONFactoryUtil.createJSONObject();

		if (loopDivision.getParentLoopDivisionId() > 0) {
			LoopDivision parentLoopDivision =
				LoopDivisionLocalServiceUtil.getLoopDivision(
					loopDivision.getParentLoopDivisionId());

			parentLoopDivisionCompositeJSONObject =
				_getLoopDivisionCompositeJSONObject(parentLoopDivision);
		}

		respondWith(parentLoopDivisionCompositeJSONObject);
	}

	@Override
	protected MessageListener buildControllerMessageListener() {
		return LoopDivisionControllerMessageListener.getInstance(this);
	}

	@Override
	protected Indexer buildIndexer() {
		return LoopDivisionIndexer.getInstance();
	}

	@Override
	protected String getControllerDestinationName() {
		return LoopDivisionConstants.CONTROLLER_DESTINATION_NAME;
	}

	private LoopDivision _add(int type, int subtype) throws Exception {
		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.createLoopDivision(0);

		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		String name = ParamUtil.getString(request, "name");
		String comments = ParamUtil.getString(request, "comments");

		Organization organization =
			OrganizationLocalServiceUtil.addOrganization(
				user.getUserId(), parentLoopDivision.getOrganizationId(), name,
				OrganizationConstants.TYPE_ORGANIZATION, 0, 0,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, comments, false,
				null);

		loopDivision.setOrganizationId(organization.getOrganizationId());

		loopDivision.setParentLoopDivisionId(
			parentLoopDivision.getLoopDivisionId());

		LoopUtil.deleteUserGroupRole(
			user.getUserId(), organization.getGroupId(),
			themeDisplay.getCompanyId(), RoleConstants.ORGANIZATION_OWNER);

		updateModel(
			loopDivision, "extraData", _getExtraData(loopDivision), "subtype",
			subtype, "type", type);

		String externalReferenceName = ParamUtil.getString(
			request, "externalReferenceName");
		String externalReferencePK = ParamUtil.getString(
			request, "externalReferencePK");

		if (Validator.isNotNull(externalReferenceName) &&
			Validator.isNotNull(externalReferencePK)) {

			LoopExternalReferenceRelUtil.addLoopExternalReferenceRel(
				this, PortalUtil.getClassNameId(LoopDivision.class),
				loopDivision.getLoopDivisionId(), externalReferenceName,
				externalReferencePK);
		}

		return loopDivision;
	}

	private LoopDivision _fetchParentLoopDivision() throws Exception {
		String parentId = ParamUtil.getString(request, "parentId");

		if (Validator.isNull(parentId) ||
			(Validator.isNumber(parentId) && (Long.valueOf(parentId) == 0))) {

			return LoopDivisionUtil.getRootLoopDivision(this);
		}
		else if (Validator.isNumber(parentId)) {
			return LoopDivisionLocalServiceUtil.fetchLoopDivision(
				Long.valueOf(parentId));
		}

		return LoopDivisionUtil.fetchLoopDivision(
			themeDisplay.getCompanyId(), parentId);
	}

	private List<Long> _getAncestorLoopDivisionIds(LoopDivision loopDivision) {
		List<Long> ancestorLoopDivisionIds = new ArrayList<>();

		while (loopDivision != null) {
			ancestorLoopDivisionIds.add(loopDivision.getLoopDivisionId());

			loopDivision = LoopDivisionLocalServiceUtil.fetchLoopDivision(
				loopDivision.getParentLoopDivisionId());
		}

		return ancestorLoopDivisionIds;
	}

	private Map<String, Serializable> _getAttributes(
		long parentLoopDivisionId, int type) {

		Map<String, Serializable> attributes = new HashMap<>();

		attributes.put(Field.TYPE, type);

		long createTime = ParamUtil.getLong(request, "createTime");

		attributes.put("createTimeMax", createTime);

		attributes.put("parentLoopDivisionId", parentLoopDivisionId);

		int subtype = ParamUtil.getInteger(request, "subtype");

		attributes.put("subtype", subtype);

		return attributes;
	}

	private List<Long> _getCommonLoopPersonIds(long... loopDivisionIds)
		throws Exception {

		List<Long> loopPersonIds = new ArrayList<>();

		for (long loopDivisionId : loopDivisionIds) {
			List<Long> loopParticipantAssignmentLoopPersonIds =
				LoopParticipantAssignmentUtil.
					getLoopParticipantAssignmentLoopPersonIds(loopDivisionId);

			if (loopPersonIds.isEmpty()) {
				loopPersonIds.addAll(loopParticipantAssignmentLoopPersonIds);
			}
			else {
				loopPersonIds.retainAll(loopParticipantAssignmentLoopPersonIds);
			}
		}

		return loopPersonIds;
	}

	private String _getExtraData(LoopDivision loopDivision) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			loopDivision.getExtraData());

		String gitHubRepositories = jsonObject.getString("gitHubRepositories");

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		if (loopDivisionComposite.getPermissionSetGitHubRepositories()) {
			gitHubRepositories = ParamUtil.getString(
				request, "gitHubRepositories",
				jsonObject.getString("gitHubRepositories"));
		}

		jsonObject.put("gitHubRepositories", gitHubRepositories);

		String preferredName = ParamUtil.getString(
			request, "preferredName", jsonObject.getString("preferredName"));

		jsonObject.put("preferredName", preferredName);

		return jsonObject.toString();
	}

	private JSONObject _getHierarchyJSONObject(
			LoopDivision loopDivision,
			JSONArray childLoopDivisionCompositeJSONArray)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"childLoopDivisionCompositeJSONArray",
			childLoopDivisionCompositeJSONArray);
		jsonObject.put(
			"loopDivisionCompositeJSONObject",
			_getLoopDivisionCompositeJSONObject(loopDivision));

		return jsonObject;
	}

	private JSONObject _getLoopDivisionCompositeJSONObject(
			LoopDivision loopDivision)
		throws Exception {

		LoopDivisionComposite loopDivisionComposite = new LoopDivisionComposite(
			loopDivision, themeDisplay);

		return loopDivisionComposite.getJSONObject();
	}

	private JSONArray _getLoopDivisionLoopPersonCompositesJSONArray(
			LoopDivision loopDivision, int type)
		throws Exception {

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		return LoopCompositeUtil.getCompositesJSONArray(
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopPersonComposites(
					themeDisplay, loopDivision.getLoopDivisionId(),
					loopDivision, type, start, end));
	}

	private JSONArray _getSiblingLoopDivisionCompositeJSONArray(
			LoopDivision loopDivision,
			JSONObject loopDivisionHierarchyJSONObject)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		AlloyServiceInvoker loopDivisionAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopDivision.class.getName());

		List<LoopDivision> siblingLoopDivisions =
			loopDivisionAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"parentLoopDivisionId",
					loopDivision.getParentLoopDivisionId(), "type",
					loopDivision.getType()
				});

		for (LoopDivision siblingLoopDivision : siblingLoopDivisions) {
			if (siblingLoopDivision.getLoopDivisionId() ==
					loopDivision.getLoopDivisionId()) {

				jsonArray.put(loopDivisionHierarchyJSONObject);
			}
			else {
				jsonArray.put(
					_getHierarchyJSONObject(
						siblingLoopDivision,
						JSONFactoryUtil.createJSONArray()));
			}
		}

		return jsonArray;
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	private void _setChildLoopDivisions(
			List<Long> childLoopDivisionIds, LoopDivision loopDivision)
		throws Exception {

		DynamicQuery loopDivisionDynamicQuery =
			alloyServiceInvoker.buildDynamicQuery(
				new Object[] {
					"companyId", themeDisplay.getCompanyId(),
					"parentLoopDivisionId", loopDivision.getLoopDivisionId()
				});

		Projection loopDivisionIdProjection = ProjectionFactoryUtil.property(
			"loopDivisionId");

		loopDivisionDynamicQuery.setProjection(
			ProjectionFactoryUtil.distinct(loopDivisionIdProjection));

		List<Long> currentChildLoopDivisionIds =
			alloyServiceInvoker.executeDynamicQuery(
				loopDivisionDynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<Long> unsetChildLoopDivisionIds = new ArrayList<>(
			currentChildLoopDivisionIds);

		unsetChildLoopDivisionIds.removeAll(childLoopDivisionIds);

		for (long unsetChildLoopDivisionId : unsetChildLoopDivisionIds) {
			LoopDivisionUtil.setParentLoopDivision(
				this, unsetChildLoopDivisionId,
				LoopDivisionUtil.getRootLoopDivision(this));
		}

		List<Long> newChildLoopDivisionIds = new ArrayList<>(
			childLoopDivisionIds);

		newChildLoopDivisionIds.removeAll(currentChildLoopDivisionIds);

		for (long newChildLoopDivisionId : newChildLoopDivisionIds) {
			LoopDivisionUtil.setParentLoopDivision(
				this, newChildLoopDivisionId, loopDivision);
		}
	}

	private void _setLoopDivisionLeads(LoopDivision loopDivision)
		throws Exception {

		long[] leadLoopPersonIds = ParamUtil.getLongValues(
			request, "leadLoopPersonIds");

		LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
			this, loopDivision.getLoopDivisionId(),
			ListUtil.toList(leadLoopPersonIds),
			LoopParticipantAssignmentConstants.TYPE_LEAD);

		long[] leadAssistantLoopPersonIds = ParamUtil.getLongValues(
			request, "leadAssistantLoopPersonIds");

		leadLoopPersonIds = ArrayUtil.append(
			leadLoopPersonIds, leadAssistantLoopPersonIds);

		LoopUtil.setUserGroupRoles(
			leadLoopPersonIds, loopDivision,
			LoopRoleConstants.LOOP_DIVISION_LEAD);
	}

	private void _setLoopDivisionMembers(LoopDivision loopDivision)
		throws Exception {

		long[] memberLoopPersonIds = ParamUtil.getLongValues(
			request, "memberLoopPersonIds");

		LoopParticipantAssignmentUtil.setLoopParticipantAssignments(
			this, loopDivision.getLoopDivisionId(),
			ListUtil.toList(memberLoopPersonIds),
			LoopParticipantAssignmentConstants.TYPE_MEMBER);
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	private void _setParentLoopDivision(
			long childLoopDivisionId, LoopDivision parentLoopDivision)
		throws Exception {

		LoopDivisionUtil.setParentLoopDivision(
			this, childLoopDivisionId, parentLoopDivision);
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	private void _setType(
			LoopDivision loopDivision, LoopDivision parentLoopDivision)
		throws Exception {

		int subtype = ParamUtil.getInteger(request, "subtype");
		int type = ParamUtil.getInteger(request, "type");

		List<LoopDivision> loopDivisions = new ArrayList<>();

		loopDivisions.add(loopDivision);
		loopDivisions.addAll(
			LoopDivisionUtil.getNetworkLoopDivisions(loopDivision));

		for (LoopDivision curLoopDivision : loopDivisions) {
			curLoopDivision.setSubtype(subtype);
			curLoopDivision.setType(type);

			updateModelIgnoreRequest(curLoopDivision);
		}

		LoopDivisionUtil.setParentLoopDivision(
			this, loopDivision.getLoopDivisionId(), parentLoopDivision);
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class
	)
	private LoopDivision _update() throws Exception {
		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateUpdate(loopDivision);

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(
				loopDivision.getOrganizationId());

		String name = ParamUtil.getString(request, "name");
		String comments = ParamUtil.getString(request, "comments");

		OrganizationLocalServiceUtil.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(), name,
			organization.getType(), organization.getRegionId(),
			organization.getCountryId(), organization.getStatusId(), comments,
			false, null, false, null);

		updateModel(loopDivision, "extraData", _getExtraData(loopDivision));

		_setLoopDivisionLeads(loopDivision);
		_setLoopDivisionMembers(loopDivision);

		return loopDivision;
	}

	private void _validateAdd() throws Exception {
		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		_validateLoopDivision(parentLoopDivision);

		int type = ParamUtil.getInteger(request, "type");

		if (!ArrayUtil.contains(LoopDivisionConstants.TYPES, type)) {
			throw new AlloyException("the-group-type-is-invalid");
		}

		int subtype = ParamUtil.getInteger(request, "subtype");

		_validateSubtype(type, subtype);

		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			if (type == LoopDivisionConstants.TYPE_DEPARTMENT) {
				throw new AlloyException("the-department-name-is-not-set");
			}
			else if (type == LoopDivisionConstants.TYPE_LOCATION) {
				throw new AlloyException("the-location-name-is-not-set");
			}

			throw new AlloyException("the-team-name-is-not-set");
		}

		long existingOrganizationId =
			OrganizationLocalServiceUtil.getOrganizationId(
				themeDisplay.getCompanyId(), name);

		if (existingOrganizationId > 0) {
			if (type == LoopDivisionConstants.TYPE_DEPARTMENT) {
				throw new AlloyException("the-department-name-already-exists");
			}
			else if (type == LoopDivisionConstants.TYPE_LOCATION) {
				throw new AlloyException("the-location-name-already-exists");
			}

			throw new AlloyException("the-team-name-already-exists");
		}

		_validateLoopPersonIds("leadAssistantLoopPersonIds");
		_validateLoopPersonIds("leadLoopPersonIds");
	}

	private void _validateAddExternalReference() throws Exception {
		LoopDivision loopDivision = LoopDivisionUtil.fetchLoopDivision(
			request, themeDisplay);

		_validateLoopDivision(loopDivision);

		String externalReferenceName = ParamUtil.getString(
			request, "externalReferenceName");

		if (Validator.isNull(externalReferenceName)) {
			throw new AlloyException("the-external-reference-name-is-not-set");
		}

		String externalReferencePK = ParamUtil.getString(
			request, "externalReferencePK");

		if (Validator.isNull(externalReferencePK)) {
			throw new AlloyException(
				"the-external-reference-primary-key-is-not-set");
		}

		LoopExternalReferenceRel loopExternalReferenceRel =
			LoopExternalReferenceRelUtil.fetchLoopExternalReferenceRel(
				externalReferenceName, externalReferencePK);

		if (loopExternalReferenceRel != null) {
			throw new AlloyException("the-external-reference-is-already-set");
		}
	}

	private void _validateAddRegionalDepartment() throws Exception {
		long departmentLoopDivisionId = ParamUtil.getLong(
			request, "departmentLoopDivisionId");

		LoopDivision departmentLoopDivision =
			LoopDivisionLocalServiceUtil.fetchLoopDivision(
				departmentLoopDivisionId);

		_validateLoopDivision(departmentLoopDivision);

		long locationLoopDivisionId = ParamUtil.getLong(
			request, "locationLoopDivisionId");

		LoopDivision locationLoopDivision =
			LoopDivisionLocalServiceUtil.fetchLoopDivision(
				locationLoopDivisionId);

		_validateLoopDivision(locationLoopDivision);

		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		_validateLoopDivision(parentLoopDivision);
	}

	private void _validateDelete(LoopDivision loopDivision) throws Exception {
		_validateLoopDivision(loopDivision);
	}

	private void _validateEdit(LoopDivision loopDivision) throws Exception {
		_validateLoopDivision(loopDivision);
	}

	private void _validateLoopDivision(LoopDivision loopDivision)
		throws Exception {

		if (loopDivision == null) {
			throw new AlloyException("the-division-does-not-exist");
		}
	}

	private void _validateLoopPersonIds(String paramName) throws Exception {
		long[] loopPersonIds = ParamUtil.getLongValues(request, paramName);

		for (long loopPersonId : loopPersonIds) {
			LoopPerson loopPerson = LoopPersonLocalServiceUtil.fetchLoopPerson(
				loopPersonId);

			if (loopPerson == null) {
				throw new AlloyException(
					translate(
						"the-person-with-id-x-does-not-exist", loopPersonId));
			}
		}
	}

	private void _validateRemoveExternalReference() throws Exception {
		String externalReferenceName = ParamUtil.getString(
			request, "externalReferenceName");

		if (Validator.isNull(externalReferenceName)) {
			throw new AlloyException("the-external-reference-name-is-not-set");
		}

		String externalReferencePK = ParamUtil.getString(
			request, "externalReferencePK");

		if (Validator.isNull(externalReferencePK)) {
			throw new AlloyException(
				"the-external-reference-primary-key-is-not-set");
		}

		LoopExternalReferenceRel loopExternalReferenceRel =
			LoopExternalReferenceRelUtil.fetchLoopExternalReferenceRel(
				externalReferenceName, externalReferencePK);

		if (loopExternalReferenceRel == null) {
			throw new AlloyException("the-external-reference-does-not-exist");
		}
	}

	private void _validateSetChildLoopDivisions(
			List<Long> childLoopDivisionIds, LoopDivision parentLoopDivision)
		throws Exception {

		_validateLoopDivision(parentLoopDivision);

		LoopDivisionComposite parentLoopDivisionComposite =
			new LoopDivisionComposite(parentLoopDivision, themeDisplay);

		List<Long> ancestorLoopDivisionIds = _getAncestorLoopDivisionIds(
			parentLoopDivision);

		for (long childLoopDivisionId : childLoopDivisionIds) {
			LoopDivision childLoopDivision =
				LoopDivisionLocalServiceUtil.fetchLoopDivision(
					childLoopDivisionId);

			_validateLoopDivision(childLoopDivision);

			if (!parentLoopDivisionComposite.
					getPermissionSetChildLoopDivisions()) {

				LoopDivisionComposite childLoopDivisionComposite =
					new LoopDivisionComposite(childLoopDivision, themeDisplay);

				if (!childLoopDivisionComposite.
						getPermissionSetChildLoopDivisions()) {

					throw new AlloyException(
						"you-do-not-have-permission-to-access-the-requested-" +
							"resource");
				}
			}

			if (ancestorLoopDivisionIds.contains(childLoopDivisionId)) {
				throw new AlloyException(
					"divisions-cannot-have-a-child-division-that-is-also-its-" +
						"parent");
			}
		}
	}

	private void _validateSetCoverImage(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateSetParentLoopDivision(
			LoopDivision loopDivision, LoopDivision parentLoopDivision)
		throws Exception {

		if (loopDivision.getType() == LoopDivisionConstants.TYPE_ROOT) {
			throw new AlloyException(
				"a-parent-cannot-be-assigned-to-the-root-group");
		}

		List<Long> loopDivisionIds = new ArrayList<>();

		loopDivisionIds.add(loopDivision.getLoopDivisionId());

		_validateSetChildLoopDivisions(loopDivisionIds, parentLoopDivision);
	}

	private void _validateSetProfileImage(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateSetType(LoopDivision loopDivision) throws Exception {
		_validateLoopDivision(loopDivision);

		if (loopDivision.getType() == LoopDivisionConstants.TYPE_ROOT) {
			throw new AlloyException(
				"the-type-of-the-root-group-cannot-be-set");
		}

		int type = ParamUtil.getInteger(request, "type");

		if (!ArrayUtil.contains(LoopDivisionConstants.TYPES, type) ||
			(loopDivision.getType() == type)) {

			throw new AlloyException("the-group-type-is-invalid");
		}

		int subtype = ParamUtil.getInteger(request, "subtype");

		_validateSubtype(type, subtype);

		LoopDivision parentLoopDivision = _fetchParentLoopDivision();

		_validateSetParentLoopDivision(loopDivision, parentLoopDivision);

		if ((parentLoopDivision.getType() != LoopDivisionConstants.TYPE_ROOT) &&
			(parentLoopDivision.getType() != type)) {

			throw new AlloyException(
				"the-parent-group's-type-does-not-match-the-type-you-" +
					"specified");
		}
	}

	private void _validateSubtype(int type, int subtype) throws Exception {
		if (((type == LoopDivisionConstants.TYPE_DEPARTMENT) &&
			 !ArrayUtil.contains(
				 LoopDivisionConstants.SUBTYPES_DEPARTMENT, subtype)) ||
			((type == LoopDivisionConstants.TYPE_LOCATION) &&
			 !ArrayUtil.contains(
				 LoopDivisionConstants.SUBTYPES_LOCATION, subtype)) ||
			((type == LoopDivisionConstants.TYPE_TEAM) &&
			 !ArrayUtil.contains(
				 LoopDivisionConstants.SUBTYPES_TEAM, subtype))) {

			throw new AlloyException("the-subtype-is-invalid");
		}
	}

	private void _validateUpdate(LoopDivision loopDivision) throws Exception {
		_validateLoopDivision(loopDivision);

		String name = ParamUtil.getString(request, "name");

		long existingOrganizationId =
			OrganizationLocalServiceUtil.getOrganizationId(
				themeDisplay.getCompanyId(), name);

		if (existingOrganizationId > 0) {
			List<LoopDivision> loopDivisions =
				alloyServiceInvoker.executeDynamicQuery(
					new Object[] {
						"companyId", themeDisplay.getCompanyId(),
						"organizationId", existingOrganizationId
					});

			if (loopDivisions.isEmpty()) {
				throw new AlloyException(
					"the-division-name-is-invalid-due-to-a-system-conflict");
			}

			LoopDivision existingLoopDivision = loopDivisions.get(0);

			if (existingLoopDivision.getLoopDivisionId() !=
					loopDivision.getLoopDivisionId()) {

				throw new AlloyException("the-division-name-already-exists");
			}
		}

		int subtype = ParamUtil.getInteger(request, "subtype");

		_validateSubtype(loopDivision.getType(), subtype);

		_validateLoopPersonIds("leadAssistantLoopPersonIds");
		_validateLoopPersonIds("leadLoopPersonIds");
		_validateLoopPersonIds("memberLoopPersonIds");
	}

	private void _validateUploadCoverImage(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateUploadProfileImage(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateView(LoopDivision loopDivision) throws Exception {
		_validateLoopDivision(loopDivision);
	}

	private void _validateViewChildLoopDivisions(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateViewLeads(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateViewLoopDivisionHierarchy(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateViewMembers(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

	private void _validateViewParentLoopDivisions(LoopDivision loopDivision)
		throws Exception {

		_validateLoopDivision(loopDivision);
	}

}