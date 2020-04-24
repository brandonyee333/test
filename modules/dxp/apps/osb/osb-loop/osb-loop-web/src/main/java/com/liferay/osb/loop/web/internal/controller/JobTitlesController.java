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
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.model.impl.LoopPersonModelImpl;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopJobTitleComposite;
import com.liferay.osb.loop.web.internal.constants.LoopJobTitleConstants;
import com.liferay.osb.loop.web.internal.indexer.LoopJobTitleIndexer;
import com.liferay.osb.loop.web.internal.messaging.LoopJobTitleControllerMessageListener;
import com.liferay.osb.loop.web.internal.util.LoopJobTitleUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.osb.loop.web.internal.util.LoopSQLUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Timothy Bell
 */
public class JobTitlesController extends LoopAlloyControllerImpl {

	public JobTitlesController() {
		setAlloyServiceInvokerClass(LoopJobTitle.class);
		setPermissioned(true);
	}

	public void activate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateActivate(loopJobTitle);

		updateModelIgnoreRequest(
			loopJobTitle, "status", WorkflowConstants.STATUS_APPROVED);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		respondWith(loopJobTitleComposite.getJSONObject());
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateAdd();

		LoopJobTitle loopJobTitle =
			LoopJobTitleLocalServiceUtil.createLoopJobTitle(0);

		updateModel(loopJobTitle);

		pollBaseModel = loopJobTitle;

		pollHitsLength = 1;

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		respondWith(loopJobTitleComposite.getJSONObject());
	}

	public void deactivate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateDeactivate(loopJobTitle);

		updateModelIgnoreRequest(
			loopJobTitle, "status", WorkflowConstants.STATUS_INACTIVE);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		respondWith(loopJobTitleComposite.getJSONObject());
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateDelete(loopJobTitle);

		AlloyServiceInvoker loopPersonAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopPerson.class.getName());

		List<LoopPerson> loopPersons =
			loopPersonAlloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopJobTitleId", loopJobTitle.getLoopJobTitleId()
				});

		for (LoopPerson loopPerson : loopPersons) {
			updateModel(loopPerson, "loopJobTitleId", 0);
		}

		deleteModel(loopJobTitle);

		pollBaseModel = loopJobTitle;
		pollHitsLength = 0;

		respondWith("The job title was successfully deleted.");
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateEdit(loopJobTitle);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		respondWith(loopJobTitleComposite.getJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		Map<String, Serializable> attributes = new HashMap<>();

		int status = ParamUtil.getInteger(request, "status");

		if (status != WorkflowConstants.STATUS_ANY) {
			attributes.put(Field.STATUS, status);
		}

		Sort[] sorts = {
			new Sort("statusPriority_sortable", Sort.INT_TYPE, false),
			new Sort("name_sortable", false)
		};

		JSONObject jsonObject = doSearch(
			indexer, alloyServiceInvoker, attributes, sorts);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			themeDisplay);

		jsonObject.put(
			"permissionCreate", loopJobTitleComposite.getPermissionCreate());

		respondWith(jsonObject);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateUpdate(loopJobTitle);

		updateModel(loopJobTitle);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		respondWith(loopJobTitleComposite.getJSONObject());
	}

	public void view() throws Exception {
		LoopJobTitle loopJobTitle = LoopJobTitleUtil.fetchLoopJobTitle(
			request, themeDisplay);

		_validateView(loopJobTitle);

		LoopJobTitleComposite loopJobTitleComposite = new LoopJobTitleComposite(
			loopJobTitle, themeDisplay);

		if (isRespondingTo("json")) {
			respondWith(loopJobTitleComposite.getJSONObject());

			return;
		}

		renderRequest.setAttribute(
			"loopJobTitleComposite", loopJobTitleComposite);
	}

	@Override
	protected MessageListener buildControllerMessageListener() {
		return LoopJobTitleControllerMessageListener.getInstance(this);
	}

	@Override
	protected Indexer buildIndexer() {
		return LoopJobTitleIndexer.getInstance();
	}

	@Override
	protected String getControllerDestinationName() {
		return LoopJobTitleConstants.CONTROLLER_DESTINATION_NAME;
	}

	private void _validateActivate(LoopJobTitle loopJobTitle) throws Exception {
		_validateLoopJobTitle(loopJobTitle);
	}

	private void _validateAdd() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-job-title-name-is-not-set");
		}

		long loopJobTitlesCount = alloyServiceInvoker.executeDynamicQueryCount(
			new Object[] {
				"companyId", themeDisplay.getCompanyId(), "name", name
			});

		if (loopJobTitlesCount > 0) {
			throw new AlloyException("the-job-title-name-already-exists");
		}
	}

	private void _validateDeactivate(LoopJobTitle loopJobTitle)
		throws Exception {

		_validateLoopJobTitle(loopJobTitle);
		_validateLoopPersonCount(loopJobTitle);
	}

	private void _validateDelete(LoopJobTitle loopJobTitle) throws Exception {
		_validateLoopJobTitle(loopJobTitle);
		_validateLoopPersonCount(loopJobTitle);
	}

	private void _validateEdit(LoopJobTitle loopJobTitle) throws Exception {
		_validateLoopJobTitle(loopJobTitle);
	}

	private void _validateLoopJobTitle(LoopJobTitle loopJobTitle)
		throws Exception {

		if (loopJobTitle == null) {
			throw new AlloyException("the-job-title-does-not-exist");
		}
	}

	private void _validateLoopPersonCount(LoopJobTitle loopJobTitle)
		throws Exception {

		Map<String, Map<String, Object[]>> whereConditions =
			LoopSQLUtil.createWhereConditions(
				LoopPersonModelImpl.TABLE_NAME, "loopJobTitleId",
				loopJobTitle.getLoopJobTitleId());

		int loopPersonCount = LoopPersonUtil.getLoopPersonCount(
			null, null, whereConditions);

		if (loopPersonCount > 0) {
			throw new AlloyException(
				"the-job-title-cannot-be-removed-because-there-are-people-" +
					"assigned-to-it");
		}
	}

	private void _validateStatus(LoopJobTitle loopJobTitle) throws Exception {
		int status = ParamUtil.getInteger(request, "status");

		if ((status != WorkflowConstants.STATUS_APPROVED) &&
			(status != WorkflowConstants.STATUS_INACTIVE)) {

			throw new AlloyException("the-status-is-invalid");
		}

		if (status == WorkflowConstants.STATUS_INACTIVE) {
			_validateLoopPersonCount(loopJobTitle);
		}
	}

	private void _validateUpdate(LoopJobTitle loopJobTitle) throws Exception {
		_validateLoopJobTitle(loopJobTitle);
		_validateStatus(loopJobTitle);
	}

	private void _validateView(LoopJobTitle loopJobTitle) throws Exception {
		_validateLoopJobTitle(loopJobTitle);
	}

}