/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.release.notes.service.impl;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.osb.customer.release.notes.DuplicateJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.RequiredJIRAIssueKeysException;
import com.liferay.osb.customer.release.notes.RequiredNameException;
import com.liferay.osb.customer.release.notes.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.osb.customer.release.notes.service.base.ReleaseNotesLocalServiceBaseImpl;
import com.liferay.osb.customer.release.notes.util.ReleaseNotesUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

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
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
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

		return releaseNotesPersistence.update(releaseNotes, false);
	}

	@Override
	public ReleaseNotes deleteReleaseNotes(long releaseNotesId)
		throws PortalException, SystemException {

		ReleaseNotes releaseNotes = releaseNotesPersistence.findByPrimaryKey(
			releaseNotesId);

		return deleteReleaseNotes(releaseNotes);
	}

	@Override
	public ReleaseNotes deleteReleaseNotes(ReleaseNotes releaseNotes)
		throws SystemException {

		clearCacheFile(releaseNotes);

		return releaseNotesPersistence.remove(releaseNotes);
	}

	public ReleaseNotes fetchReleaseNotes(String name) throws SystemException {
		return releaseNotesPersistence.fetchByName(name);
	}

	public ReleaseNotes getReleaseNotesByUuid(String uuid)
		throws PortalException, SystemException {

		List<ReleaseNotes> releaseNotes = releaseNotesPersistence.findByUuid(
			uuid);

		if (releaseNotes.isEmpty()) {
			throw new NoSuchReleaseNotesException();
		}
		else {
			return releaseNotes.get(0);
		}
	}

	public List<ReleaseNotes> search(String name, int start, int end)
		throws SystemException {

		if (Validator.isNotNull(name)) {
			name = CustomSQLUtil.keywords(name)[0];

			return releaseNotesPersistence.findByLikeName(name, start, end);
		}
		else {
			return releaseNotesPersistence.findAll(start, end);
		}
	}

	public int searchCount(String name) throws SystemException {
		return releaseNotesPersistence.countByName(name);
	}

	public ReleaseNotes updateReleaseNotes(
			long releaseNotesId, String name, String jiraIssueKeys)
		throws PortalException, SystemException {

		jiraIssueKeys = normalize(jiraIssueKeys);

		validate(releaseNotesId, name, jiraIssueKeys);

		ReleaseNotes releaseNotes = releaseNotesPersistence.findByPrimaryKey(
			releaseNotesId);

		releaseNotes.setModifiedDate(new Date());
		releaseNotes.setName(name);
		releaseNotes.setJiraIssueKeys(jiraIssueKeys);

		releaseNotes = releaseNotesPersistence.update(releaseNotes, false);

		clearCacheFile(releaseNotes);

		return releaseNotes;
	}

	protected void clearCacheFile(ReleaseNotes releaseNotes) {
		String cacheFilePath = ReleaseNotesUtil.getCacheFilePath(
			releaseNotes.getUuid(), ReleaseNotesUtil.CACHE_DIR_ISSUE);

		ReleaseNotesUtil.clearCacheFile(cacheFilePath);
	}

	protected String normalize(String jiraIssueKeys) throws SystemException {
		Set<String> jiraIssueKeysSet = new TreeSet<String>();

		String[] jiraIssueKeysArray = StringUtil.split(jiraIssueKeys);

		for (String jiraIssueKey : jiraIssueKeysArray) {
			jiraIssueKey = jiraIssueKey.trim().toUpperCase();

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
		throws PortalException, SystemException {

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

}