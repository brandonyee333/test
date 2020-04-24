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
import com.liferay.alloy.mvc.json.web.service.JSONWebServiceMethod;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.osb.loop.asset.sharing.service.AssetSharingEntryLocalServiceUtil;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.LoopStreamEntry;
import com.liferay.osb.loop.model.LoopTopic;
import com.liferay.osb.loop.model.LoopTopicAssignment;
import com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl;
import com.liferay.osb.loop.service.LoopTopicLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.composite.LoopTopicComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopStreamConstants;
import com.liferay.osb.loop.web.internal.constants.LoopStreamEntryConstants;
import com.liferay.osb.loop.web.internal.constants.LoopTopicConstants;
import com.liferay.osb.loop.web.internal.indexer.LoopTopicIndexer;
import com.liferay.osb.loop.web.internal.util.LoopCompositeUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.osb.loop.web.internal.util.LoopStatsEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopStreamEntryUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopTopicUtil;
import com.liferay.osb.loop.web.internal.util.LoopUserNotificationSubscriptionUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.portlet.PortletRequest;

/**
 * @author Timothy Bell
 */
public class TopicsController extends LoopAlloyControllerImpl {

	public TopicsController() {
		setAlloyServiceInvokerClass(LoopTopic.class);
		setFeaturedPreferenceKey("featuredLoopTopicIds");
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAdd();

		String name = ParamUtil.getString(request, "name");

		AssetTagLocalServiceUtil.addTag(
			themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(), name,
			getServiceContext());

		LoopTopic loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
			themeDisplay.getCompanyId(), name);

		String description = ParamUtil.getString(request, "description");

		LoopTopicLocalServiceUtil.updateLoopTopic(
			loopTopic.getUserId(), loopTopic.getName(), description);

		pollBaseModel = loopTopic;
		pollHitsLength = 1;

		respondWith(_getLoopTopicCompositeJSONObject(loopTopic));
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateDelete(loopTopic);

		_deleteLoopTopic(loopTopic);

		respondWith("The topic was successfully deleted.");
	}

	public void edit() throws Exception {
		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateEdit(loopTopic);

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		if (isRespondingTo("json")) {
			respondWith(loopTopicComposite.getJSONObject());

			return;
		}

		renderRequest.setAttribute("loopTopicComposite", loopTopicComposite);
	}

	@Override
	public BaseModel<?> fetchBaseModel() {
		return LoopTopicUtil.fetchLoopTopic(request, themeDisplay);
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, Serializable> attributes = new HashMap<>();

		long createTime = ParamUtil.getLong(request, "createTime");

		attributes.put("createTime", createTime);

		String sort = ParamUtil.getString(request, "sort");

		int sortType = Sort.STRING_TYPE;

		if (Objects.equals(sort, "createDate_Sortable") ||
			Objects.equals(sort, "followersCount_sortable") ||
			Objects.equals(sort, "score_sortable")) {

			sortType = Sort.LONG_TYPE;
		}

		boolean reverseSort = ParamUtil.getBoolean(request, "reverseSort");

		Sort[] sorts = {
			new Sort(sort, sortType, reverseSort),
			new Sort("score_sortable", Sort.LONG_TYPE, true)
		};

		JSONObject jsonObject = doSearch(
			indexer, alloyServiceInvoker, attributes, sorts);

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			themeDisplay);

		jsonObject.put(
			"permissionCreate", loopTopicComposite.getPermissionCreate());

		respondWith(jsonObject);
	}

	public void removeOwnTopic() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateRemoveOwnTopic(loopTopic);

		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		LoopTopicAssignment loopTopicAssignment =
			LoopTopicAssignmentUtil.fetchLoopTopicAssignment(
				curLoopPerson.getLoopPersonId(), loopTopic.getLoopTopicId());

		if (loopTopicAssignment != null) {
			LoopTopicAssignmentUtil.deleteLoopTopicAssignment(
				curLoopPerson.getLoopPersonId(), loopTopic.getLoopTopicId());
		}

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		JSONObject jsonObject = loopTopicComposite.getJSONObject();

		if (loopTopicAssignment != null) {
			long loopTopicAssignmentsCount = jsonObject.getLong(
				"loopTopicAssignmentsCount");

			jsonObject.put(
				"loopTopicAssignmentsCount", loopTopicAssignmentsCount - 1);
		}

		respondWith(jsonObject);
	}

	public void removeParent() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateRemoveParent(loopTopic);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		List<Long> childLoopTopicIds = LoopTopicUtil.getChildLoopTopicIds(
			loopTopic.getLoopTopicId());

		_setParentLoopTopic(childLoopTopicIds, 0);

		jsonObject.put(
			"childLoopTopicCompositesJSONArray",
			LoopCompositeUtil.getCompositesJSONArray(
				childLoopTopicIds, LoopTopicComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}));

		LoopTopicComposite parentLoopTopicComposite = new LoopTopicComposite(
			loopTopic.getLoopTopicId(), themeDisplay);

		jsonObject.put(
			"parentLoopTopicCompositeJSONObject",
			parentLoopTopicComposite.getJSONObject());

		respondWith(jsonObject);
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

	public void setCoverImage() throws Exception {
		_setImage(
			"coverImagePayload", LoopWebConfigurationValues.IMAGE_COVER_TYPES,
			LoopWebConfigurationKeys.IMAGE_COVER_MAX_SIZE);
	}

	public void setOwnTopic() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateSetOwnTopic(loopTopic);

		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		LoopTopicAssignment loopTopicAssignment =
			LoopTopicAssignmentUtil.fetchLoopTopicAssignment(
				curLoopPerson.getLoopPersonId(), loopTopic.getLoopTopicId());

		if (loopTopicAssignment == null) {
			LoopTopicAssignmentUtil.addLoopTopicAssignment(
				this, curLoopPerson.getLoopPersonId(),
				loopTopic.getLoopTopicId(), true);
		}

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		JSONObject jsonObject = loopTopicComposite.getJSONObject();

		if (loopTopicAssignment == null) {
			long loopTopicAssignmentsCount = jsonObject.getLong(
				"loopTopicAssignmentsCount");

			jsonObject.put(
				"loopTopicAssignmentsCount", loopTopicAssignmentsCount + 1);
		}

		respondWith(jsonObject);
	}

	public void setParent() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateSetParent();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		List<Long> childLoopTopicIds = ListUtil.toList(
			ParamUtil.getLongValues(request, "childLoopTopicIds"));

		long parentLoopTopicId = ParamUtil.getLong(
			request, "parentLoopTopicId");

		_setLoopTopicAssignments(childLoopTopicIds, parentLoopTopicId);

		_setLoopTopicFollowers(childLoopTopicIds, parentLoopTopicId);

		_setParentLoopTopic(childLoopTopicIds, parentLoopTopicId);

		jsonObject.put(
			"childLoopTopicCompositesJSONArray",
			LoopCompositeUtil.getCompositesJSONArray(
				childLoopTopicIds, LoopTopicComposite.class,
				new Class<?>[] {Long.class, ThemeDisplay.class},
				new Object[] {themeDisplay}));

		LoopTopicComposite parentLoopTopicComposite = new LoopTopicComposite(
			parentLoopTopicId, themeDisplay);

		jsonObject.put(
			"parentLoopTopicCompositeJSONObject",
			parentLoopTopicComposite.getJSONObject());

		respondWith(jsonObject);
	}

	public void setProfileImage() throws Exception {
		_setImage(
			"profileImagePayload",
			LoopWebConfigurationValues.IMAGE_PROFILE_TYPES,
			LoopWebConfigurationKeys.IMAGE_PROFILE_MAX_SIZE);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateUpdate(loopTopic);

		String name = ParamUtil.getString(request, "name");

		AssetTag assetTag = AssetTagLocalServiceUtil.getTag(
			themeDisplay.getCompanyGroupId(), name);

		AssetTagLocalServiceUtil.updateTag(
			themeDisplay.getUserId(), assetTag.getTagId(), name,
			getServiceContext());

		loopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
			themeDisplay.getCompanyId(), name);

		String description = ParamUtil.getString(request, "description");

		LoopTopicLocalServiceUtil.updateLoopTopic(
			loopTopic.getUserId(), loopTopic.getName(), description);

		respondWith(_getLoopTopicCompositeJSONObject(loopTopic));
	}

	public void uploadCoverImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		uploadImage(uploadPortletRequest, LoopTopic.class);
	}

	public void uploadProfileImage() throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		uploadImage(uploadPortletRequest, LoopTopic.class);
	}

	public void validate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, String[]> parameterMap = request.getParameterMap();

		if (parameterMap.containsKey("name")) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String name = ParamUtil.getString(request, "name");

			long count = alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"companyId", themeDisplay.getCompanyId(), "name", name
				});

			if (count > 0) {
				jsonObject.put("reason", translate("the-name-already-exists"));
				jsonObject.put("valid", false);
			}
			else if (name.length() > LoopConstants.LOOP_TOPIC_NAME_MAX_LENGTH) {
				jsonObject.put("reason", translate("the-name-is-too-long"));
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
		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateView(loopTopic);

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		if (!isRespondingTo("json")) {
			renderRequest.setAttribute(
				"loopTopicComposite", loopTopicComposite);

			return;
		}

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

		JSONObject loopTopicCompositeJSONObject =
			loopTopicComposite.getJSONObject(
				new String[] {
					"descriptionMarkdownHTML", "permissionEdit",
					"permissionSetParent"
				});

		if (getAPIVersion() < 6) {
			responseJSONObject = loopTopicCompositeJSONObject;
		}
		else {
			responseJSONObject.put(
				"loopTopicCompositeJSONObject", loopTopicCompositeJSONObject);

			LoopTopic parentLoopTopic =
				LoopTopicLocalServiceUtil.fetchLoopTopic(
					loopTopic.getParentLoopTopicId());

			if (parentLoopTopic != null) {
				LoopTopicComposite parentLoopTopicComposite =
					new LoopTopicComposite(parentLoopTopic, themeDisplay);

				responseJSONObject.put(
					"parentLoopTopicCompositeJSONObject",
					parentLoopTopicComposite.getJSONObject(
						new String[] {
							"descriptionMarkdownHTML", "permissionEdit",
							"permissionSetParent"
						}));
			}
		}

		respondWith(responseJSONObject);
	}

	public void viewChildren() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateViewChildren(loopTopic);

		List<LoopTopicComposite> childLoopTopicComposites = new ArrayList<>();

		List<Long> childLoopTopicIds = LoopTopicUtil.getAllChildLoopTopicIds(
			loopTopic.getLoopTopicId());

		for (long childLoopTopicId : childLoopTopicIds) {
			childLoopTopicComposites.add(
				new LoopTopicComposite(childLoopTopicId, themeDisplay));
		}

		respondWith(
			LoopCompositeUtil.getCompositesJSONArray(childLoopTopicComposites));
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE)
	public void viewFeatured() throws Exception {
		super.viewFeatured();
	}

	public void viewPendingExperts() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateViewPendingExperts(loopTopic);

		respondWith(
			_getLoopTopicAssignmentLoopPersonCompositesJSONArray(
				loopTopic, LoopTopicConstants.STATUS_PENDING));
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		parameterNames = {"end", "id", "start"},
		parameterTypes = {Integer.class, Long.class, Integer.class}
	)
	public void viewVerifiedExperts() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateViewVerifiedExperts(loopTopic);

		JSONArray jsonArray =
			_getLoopTopicAssignmentLoopPersonCompositesJSONArray(
				loopTopic, LoopTopicConstants.STATUS_VERIFIED);

		if (getAPIVersion() == 1) {
			respondWith(jsonArray);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("results", jsonArray);

			Map<String, Map<String, Object[]>> whereConditions =
				LoopSQLUtil.createWhereConditions(
					LoopTopicAssignmentModelImpl.TABLE_NAME, "loopTopicId",
					loopTopic.getLoopTopicId(), "status",
					LoopTopicConstants.STATUS_VERIFIED);

			jsonObject.put(
				"total",
				LoopPersonUtil.getLoopPersonCount(
					LoopTopicAssignmentModelImpl.TABLE_NAME, "loopPersonId",
					whereConditions));

			respondWith(jsonObject);
		}
	}

	@JSONWebServiceMethod(
		lifecycle = PortletRequest.RENDER_PHASE,
		methodName = "viewVerifiedExperts", parameterNames = "id",
		parameterTypes = Long.class
	)
	public void viewVerifiedExperts2() throws Exception {
		viewVerifiedExperts();
	}

	@Override
	protected Indexer buildIndexer() {
		return LoopTopicIndexer.getInstance();
	}

	@Override
	protected JSONObject getModelJSONObject(long classPK) throws Exception {
		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			classPK, themeDisplay);

		return loopTopicComposite.getJSONObject();
	}

	private void _deleteLoopTopic(LoopTopic loopTopic) throws Exception {
		AssetSharingEntryLocalServiceUtil.deleteSharedToAssetSharingEntries(
			PortalUtil.getClassNameId(LoopTopic.class),
			loopTopic.getLoopTopicId());
		LoopStatsEntryUtil.deleteLoopStatsEntries(
			PortalUtil.getClassNameId(LoopTopic.class),
			loopTopic.getLoopTopicId());
		LoopStreamEntryUtil.deleteLoopStreamEntries(
			this, PortalUtil.getClassNameId(LoopTopic.class),
			loopTopic.getLoopTopicId());
		LoopTopicAssignmentUtil.deleteLoopTopicAssignmentsByLoopTopicId(
			loopTopic.getLoopTopicId());
		LoopUserNotificationSubscriptionUtil.
			deleteLoopUserNotificationSubscriptions(
				PortalUtil.getClassNameId(LoopTopic.class),
				loopTopic.getLoopTopicId());

		AssetTag assetTag = AssetTagLocalServiceUtil.getTag(
			themeDisplay.getCompanyGroupId(), loopTopic.getName());

		AssetTagLocalServiceUtil.deleteAssetTag(assetTag);

		deleteModel(loopTopic);

		for (long childLoopTopicId :
				LoopTopicUtil.getAllChildLoopTopicIds(
					loopTopic.getLoopTopicId())) {

			LoopTopic childLoopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				childLoopTopicId);

			_deleteLoopTopic(childLoopTopic);
		}
	}

	private JSONArray _getLoopTopicAssignmentLoopPersonCompositesJSONArray(
			LoopTopic loopTopic, int status)
		throws Exception {

		int start = ParamUtil.getInteger(request, "start");

		int end = ParamUtil.getInteger(request, "end");

		if (end == 0) {
			end = LoopWebConfigurationValues.LOOP_PAGE_DEFAULT_DELTA;
		}

		return LoopCompositeUtil.getCompositesJSONArray(
			LoopTopicAssignmentUtil.getLoopTopicAssignmentLoopPersonComposites(
				themeDisplay, loopTopic.getLoopTopicId(), status, start, end));
	}

	private JSONObject _getLoopTopicCompositeJSONObject(LoopTopic loopTopic)
		throws Exception {

		LoopTopicComposite loopTopicComposite = new LoopTopicComposite(
			loopTopic, themeDisplay);

		return loopTopicComposite.getJSONObject();
	}

	private void _setImage(
			String keyword, String[] imageTypes, String imageTypeKey)
		throws Exception {

		if (!isRespondingTo("json")) {
			return;
		}

		LoopTopic loopTopic = LoopTopicUtil.fetchLoopTopic(
			request, themeDisplay);

		_validateSetImage(loopTopic);

		loopTopic = (LoopTopic)setImage(
			loopTopic, keyword, imageTypes, imageTypeKey);

		respondWith(_getLoopTopicCompositeJSONObject(loopTopic));
	}

	private void _setLoopTopicAssignments(
			List<Long> childLoopTopicIds, long parentLoopTopicId)
		throws Exception {

		if ((childLoopTopicIds == null) || childLoopTopicIds.isEmpty()) {
			return;
		}

		for (long childLoopTopicId : childLoopTopicIds) {
			List<LoopPersonComposite> loopPersonComposites =
				LoopTopicAssignmentUtil.
					getLoopTopicAssignmentLoopPersonComposites(
						themeDisplay, childLoopTopicId,
						LoopTopicConstants.STATUS_VERIFIED, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

			for (LoopPersonComposite loopPersonComposite :
					loopPersonComposites) {

				LoopTopicAssignmentUtil.addLoopTopicAssignment(
					this, loopPersonComposite.getLoopPersonId(),
					parentLoopTopicId, false);
			}

			_setLoopTopicAssignments(
				LoopTopicUtil.getChildLoopTopicIds(childLoopTopicId),
				parentLoopTopicId);
		}
	}

	private void _setLoopTopicFollowers(
			List<Long> childLoopTopicIds, long parentLoopTopicId)
		throws Exception {

		if ((childLoopTopicIds == null) || childLoopTopicIds.isEmpty()) {
			return;
		}

		for (long childLoopTopicId : childLoopTopicIds) {
			List<LoopPersonComposite> loopPersonComposites =
				LoopStreamEntryUtil.getFollowers(
					themeDisplay, PortalUtil.getClassNameId(LoopTopic.class),
					childLoopTopicId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (LoopPersonComposite loopPersonComposite :
					loopPersonComposites) {

				LoopStreamEntry loopStreamEntry =
					LoopStreamEntryUtil.fetchLoopStreamEntry(
						loopPersonComposite.getLoopPersonId(),
						LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
						PortalUtil.getClassNameId(LoopTopic.class),
						parentLoopTopicId);

				if (loopStreamEntry != null) {
					continue;
				}

				LoopStreamEntryUtil.updateLoopStreamEntry(
					this, loopPersonComposite.getLoopPersonId(),
					LoopStreamConstants.LOOP_STREAM_ID_FOLLOWING,
					PortalUtil.getClassNameId(LoopTopic.class),
					parentLoopTopicId, true,
					LoopStreamEntryConstants.TYPE_FOLLOWING_FULL);
			}

			_setLoopTopicFollowers(
				LoopTopicUtil.getChildLoopTopicIds(childLoopTopicId),
				parentLoopTopicId);
		}
	}

	private void _setParentLoopTopic(
			List<Long> childLoopTopicIds, long parentLoopTopicId)
		throws Exception {

		for (long childLoopTopicId : childLoopTopicIds) {
			LoopTopic childLoopTopic = LoopTopicLocalServiceUtil.getLoopTopic(
				childLoopTopicId);

			updateModelIgnoreRequest(
				childLoopTopic, "parentLoopTopicId", parentLoopTopicId,
				"mergeTime", System.currentTimeMillis());
		}
	}

	private void _validateAdd() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-topic-name-is-not-set");
		}

		if (name.length() > LoopConstants.LOOP_TOPIC_NAME_MAX_LENGTH) {
			throw new AlloyException("the-topic-name-is-too-long");
		}

		if (Validator.isDigit(name.charAt(0))) {
			throw new AlloyException(
				"the-topic-name-contains-invalid-characters");
		}

		for (char c : name.toCharArray()) {
			if (!Validator.isChar(c) && !Validator.isDigit(c) &&
				(c != CharPool.UNDERLINE)) {

				throw new AlloyException(
					"the-topic-name-contains-invalid-characters");
			}
		}

		long loopTopicsCount = alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {
				"companyId", themeDisplay.getCompanyId(), "name", name
			});

		if (loopTopicsCount > 0) {
			throw new AlloyException("the-topic-name-already-exists");
		}
	}

	private void _validateDelete(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);

		if (loopTopic.getParentLoopTopicId() > 0) {
			throw new AlloyException("cannot-delete-a-child-topic");
		}
	}

	private void _validateEdit(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateLoopTopic(LoopTopic loopTopic) throws Exception {
		if (loopTopic == null) {
			throw new AlloyException("the-topic-does-not-exist");
		}
	}

	private void _validateRemoveOwnTopic(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateRemoveParent(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateSetImage(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateSetOwnTopic(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateSetParent() throws Exception {
		long parentLoopTopicId = ParamUtil.getLong(
			request, "parentLoopTopicId");

		LoopTopic parentLoopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
			parentLoopTopicId);

		_validateLoopTopic(parentLoopTopic);

		LoopTopicComposite parentLoopTopicComposite = new LoopTopicComposite(
			parentLoopTopic, themeDisplay);

		if (!parentLoopTopicComposite.getPermissionSetParent()) {
			throw new AlloyException(
				"you-do-not-have-permission-to-access-the-requested-resource");
		}

		if (parentLoopTopic.getParentLoopTopicId() > 0) {
			throw new AlloyException(
				"the-topic-cannot-merge-with-child-topics");
		}

		long[] childLoopTopicIds = ParamUtil.getLongValues(
			request, "childLoopTopicIds");

		for (long childLoopTopicId : childLoopTopicIds) {
			if (childLoopTopicId == parentLoopTopicId) {
				throw new AlloyException("the-topic-cannot-merge-with-itself");
			}

			LoopTopic childLoopTopic = LoopTopicLocalServiceUtil.fetchLoopTopic(
				childLoopTopicId);

			_validateLoopTopic(childLoopTopic);

			if ((childLoopTopic.getParentLoopTopicId() > 0) &&
				(childLoopTopic.getParentLoopTopicId() != parentLoopTopicId)) {

				throw new AlloyException(
					"the-topic-cannot-merge-with-child-topics");
			}
		}
	}

	private void _validateUpdate(LoopTopic loopTopic) throws Exception {
		if (loopTopic == null) {
			throw new AlloyException("the-topic-does-not-exist");
		}

		String name = ParamUtil.getString(request, "name");

		if (!StringUtil.equalsIgnoreCase(loopTopic.getName(), name)) {
			throw new AlloyException("the-topic-name-cannot-be-updated");
		}
	}

	private void _validateView(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateViewChildren(LoopTopic loopTopic) throws Exception {
		_validateLoopTopic(loopTopic);
	}

	private void _validateViewPendingExperts(LoopTopic loopTopic)
		throws Exception {

		_validateLoopTopic(loopTopic);
	}

	private void _validateViewVerifiedExperts(LoopTopic loopTopic)
		throws Exception {

		_validateLoopTopic(loopTopic);
	}

}