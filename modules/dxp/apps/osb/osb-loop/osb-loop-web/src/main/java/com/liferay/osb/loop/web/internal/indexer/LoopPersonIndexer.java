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

package com.liferay.osb.loop.web.internal.indexer;

import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopPersonConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.util.LoopMarkdownUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Timothy Bell
 */
public class LoopPersonIndexer extends BaseLoopIndexer {

	public static LoopPersonIndexer getInstance() {
		return _instance;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		super.postProcessContextBooleanFilter(
			contextBooleanFilter, searchContext);

		int administratorType = GetterUtil.getInteger(
			searchContext.getAttribute("administratorType"),
			LoopPersonConstants.TYPE_ALL);

		if (administratorType == LoopPersonConstants.TYPE_ADMINISTRATOR) {
			contextBooleanFilter.addRequiredTerm("loopAdmin", true);
		}
		else if (administratorType ==
					LoopPersonConstants.TYPE_NON_ADMINISTRATOR) {

			contextBooleanFilter.addRequiredTerm("loopAdmin", false);
		}

		long createTime = GetterUtil.getLong(
			searchContext.getAttribute("createTime"));

		if (createTime > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();

			booleanFilter.addRangeTerm("createDate_sortable", 0, createTime);

			contextBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
		}

		long startTimeMax = GetterUtil.getLong(
			searchContext.getAttribute("startTimeMax"));

		if (startTimeMax > 0) {
			BooleanFilter booleanFilter = new BooleanFilter();

			long startTimeMin = GetterUtil.getLong(
				searchContext.getAttribute("startTimeMin"));

			booleanFilter.addRangeTerm(
				"createDate_sortable", startTimeMin, startTimeMax);

			contextBooleanFilter.add(booleanFilter, BooleanClauseOccur.MUST);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter booleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.DESCRIPTION, true);
		addSearchTerm(
			searchQuery, searchContext, "emailAddress_sortable", true);
		addSearchTerm(searchQuery, searchContext, "jobTitle", true);
		addSearchTerm(searchQuery, searchContext, Field.NAME, true);
		addSearchTerm(searchQuery, searchContext, "preferredName", true);
		addSearchTerm(searchQuery, searchContext, "skypeSn_sortable", true);
	}

	@Override
	protected Document doGetDocument(BaseModel<?> baseModel) throws Exception {
		LoopPerson loopPerson = (LoopPerson)baseModel;

		Document document = getBaseModelDocument(null, loopPerson);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			loopPerson.getExtraData());

		String description = jsonObject.getString("description");

		description = LoopMarkdownUtil.markdownToHtml(
			loopPerson.getCompanyId(), description, false, false);

		document.addText(Field.DESCRIPTION, HtmlUtil.extractText(description));

		User user = UserLocalServiceUtil.getUser(loopPerson.getPersonUserId());

		document.addText(Field.NAME, user.getFullName());
		document.addKeyword("name_sortable", user.getFullName());

		document.addKeyword(Field.STATUS, user.getStatus());
		document.addKeyword("emailAddress_sortable", user.getEmailAddress());
		document.addKeyword("firstName_sortable", user.getFirstName());

		LoopJobTitle loopJobTitle =
			LoopJobTitleLocalServiceUtil.fetchLoopJobTitle(
				loopPerson.getLoopJobTitleId());

		if (loopJobTitle != null) {
			document.addText("jobTitle", loopJobTitle.getName());
		}

		document.addKeyword("lastName_sortable", user.getLastName());

		boolean loopAdmin = UserLocalServiceUtil.hasRoleUser(
			loopPerson.getCompanyId(), LoopRoleConstants.LOOP_ADMIN,
			loopPerson.getPersonUserId(), false);

		document.addKeyword("loopAdmin", loopAdmin);

		document.addKeyword(
			"managerPersonId", loopPerson.getManagerLoopPersonId());
		document.addKeyword("middleName_sortable", user.getMiddleName());
		document.addKeyword("personUserId", loopPerson.getPersonUserId());
		document.addText(
			"preferredName", jsonObject.getString("preferredName"));

		Contact personContact = user.getContact();

		document.addKeyword("skypeSn_sortable", personContact.getSkypeSn());

		document.addNumber("startTime", jsonObject.getLong("startTime"));

		int userStatusPriority = 0;

		if (user.getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			userStatusPriority = 1;
		}

		document.addNumber("userStatusPriority", userStatusPriority);

		return document;
	}

	private LoopPersonIndexer() {
		setClassName(LoopPerson.class.getName());
	}

	private static LoopPersonIndexer _instance = new LoopPersonIndexer();

}