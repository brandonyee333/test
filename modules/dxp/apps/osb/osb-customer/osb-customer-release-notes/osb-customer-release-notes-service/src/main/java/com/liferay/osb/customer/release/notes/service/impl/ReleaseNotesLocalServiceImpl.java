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

package com.liferay.osb.customer.release.notes.service.impl;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.jira.exception.DuplicateJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.jira.exception.RequiredJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.jira.exception.RequiredNameException;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.service.JIRAIssueLocalService;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.osb.customer.release.notes.service.base.ReleaseNotesLocalServiceBaseImpl;
import com.liferay.osb.customer.release.notes.util.ReleaseNotesCacheUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Alan Zhang
 */
public class ReleaseNotesLocalServiceImpl
	extends ReleaseNotesLocalServiceBaseImpl {

	public ReleaseNotes addReleaseNotes(
			long userId, String name, String jiraIssueKeys)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		jiraIssueKeys = normalize(jiraIssueKeys);
		Date now = new Date();

		validate(0, name, jiraIssueKeys);

		long releaseNotesId = counterLocalService.increment();

		ReleaseNotes releaseNotes = releaseNotesPersistence.create(
			releaseNotesId);

		releaseNotes.setUserId(userId);
		releaseNotes.setUserName(user.getFullName());
		releaseNotes.setCreateDate(now);
		releaseNotes.setModifiedDate(now);
		releaseNotes.setName(name);
		releaseNotes.setJiraIssueKeys(jiraIssueKeys);

		return releaseNotesPersistence.update(releaseNotes);
	}

	@Override
	public ReleaseNotes deleteReleaseNotes(long releaseNotesId)
		throws PortalException {

		ReleaseNotes releaseNotes = releaseNotesPersistence.findByPrimaryKey(
			releaseNotesId);

		return deleteReleaseNotes(releaseNotes);
	}

	@Override
	public ReleaseNotes deleteReleaseNotes(ReleaseNotes releaseNotes) {
		clearCacheFile(releaseNotes);

		return releaseNotesPersistence.remove(releaseNotes);
	}

	public ReleaseNotes fetchReleaseNotes(String name) {
		return releaseNotesPersistence.fetchByName(name);
	}

	public ReleaseNotes getReleaseNotesByUuid(String uuid)
		throws PortalException {

		List<ReleaseNotes> releaseNotes = releaseNotesPersistence.findByUuid(
			uuid);

		if (releaseNotes.isEmpty()) {
			throw new NoSuchReleaseNotesException();
		}
		else {
			return releaseNotes.get(0);
		}
	}

	public List<ReleaseNotes> search(String name, int start, int end) {
		if (Validator.isNotNull(name)) {
			name = CustomSQLUtil.keywords(name)[0];

			return releaseNotesPersistence.findByLikeName(name, start, end);
		}
		else {
			return releaseNotesPersistence.findAll(start, end);
		}
	}

	public int searchCount(String name) {
		if (Validator.isNotNull(name)) {
			name = CustomSQLUtil.keywords(name)[0];

			return releaseNotesPersistence.countByLikeName(name);
		}
		else {
			return releaseNotesPersistence.countAll();
		}
	}

	public ReleaseNotes updateReleaseNotes(
			long releaseNotesId, String name, String jiraIssueKeys)
		throws PortalException {

		jiraIssueKeys = normalize(jiraIssueKeys);

		validate(releaseNotesId, name, jiraIssueKeys);

		ReleaseNotes releaseNotes = releaseNotesPersistence.findByPrimaryKey(
			releaseNotesId);

		releaseNotes.setModifiedDate(new Date());
		releaseNotes.setName(name);
		releaseNotes.setJiraIssueKeys(jiraIssueKeys);

		releaseNotes = releaseNotesPersistence.update(releaseNotes);

		clearCacheFile(releaseNotes);

		return releaseNotes;
	}

	protected void clearCacheFile(ReleaseNotes releaseNotes) {
		String cacheFilePath = ReleaseNotesCacheUtil.getCacheFilePath(
			releaseNotes.getUuid(), ReleaseNotesCacheUtil.CACHE_DIR_ISSUE);

		ReleaseNotesCacheUtil.clearCacheFile(cacheFilePath);
	}

	protected String normalize(String jiraIssueKeys) {
		Set<String> jiraIssueKeysSet = new TreeSet<>();

		String[] jiraIssueKeysArray = StringUtil.split(jiraIssueKeys);

		for (String jiraIssueKey : jiraIssueKeysArray) {
			jiraIssueKey = StringUtil.toUpperCase(jiraIssueKey.trim());

			JIRAIssue jiraIssue = jiraIssueLocalService.getJIRAIssue(
				jiraIssueKey);

			if (jiraIssue != null) {
				jiraIssueKeysSet.add(jiraIssueKey);
			}
		}

		return StringUtil.merge(jiraIssueKeysSet);
	}

	protected void validate(
			long releaseNotesId, String name, String jiraIssueKeys)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new RequiredNameException();
		}

		if (Validator.isNull(jiraIssueKeys)) {
			throw new RequiredJIRAIssueKeysException();
		}

		ReleaseNotes releaseNotes =
			releaseNotesPersistence.fetchByJIRAIssueKeys(jiraIssueKeys);

		if ((releaseNotes != null) &&
			(releaseNotes.getReleaseNotesId() != releaseNotesId)) {

			throw new DuplicateJIRAIssueKeysException();
		}
	}

	@ServiceReference(type = JIRAIssueLocalService.class)
	protected JIRAIssueLocalService jiraIssueLocalService;

}