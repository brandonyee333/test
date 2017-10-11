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

package com.liferay.osb.customer.release.notes.remote.util;

import com.liferay.osb.customer.release.notes.remote.configuration.ReleaseNotesConfigurationValues;
import com.liferay.osb.customer.release.notes.remote.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.remote.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.remote.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.remote.model.impl.JIRAComponentImpl;
import com.liferay.osb.customer.release.notes.remote.service.JIRAComponentLocalServiceUtil;
import com.liferay.osb.customer.release.notes.remote.util.apichange.APIChangeClass;
import com.liferay.osb.customer.release.notes.remote.util.comparator.ClassNameComparator;
import com.liferay.osb.customer.release.notes.remote.util.comparator.JIRAComponentComparator;
import com.liferay.osb.customer.release.notes.remote.util.comparator.JIRAIssueComparator;
import com.liferay.osb.customer.release.notes.remote.util.filter.JIRAComponentFilter;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Samuel Kong
 */
public class ReleaseNotesUtil {

	public static final String CACHE_DIR_ISSUE = "issue";

	public static final String CACHE_DIR_LABEL = "label";

	public static final String CACHE_DIR_PROJECT_VERSION = "projectVersion";

	public static void clearCacheFile(String filePath) {
		FileUtil.delete(filePath);
	}

	public static Map<JIRAComponent, Set<JIRAIssue>> filterJIRAComponentMap(
		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap,
		JIRAProjectVersion jiraProjectVersion) {

		for (String className :
				ReleaseNotesConfigurationValues.JIRA_COMPONENT_FILTERS) {

			try {
				JIRAComponentFilter jiraComponentFilter =
					(JIRAComponentFilter)Class.forName(className).newInstance();

				jiraComponentMap = jiraComponentFilter.processFilter(
					jiraComponentMap, jiraProjectVersion);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e.getMessage(), e);
				}
			}
		}

		return jiraComponentMap;
	}

	public static String[] filterJIRAIssueKeys(
		String[] jiraIssueKeys, boolean allowed) {

		List<String> filteredJIRAIssueKeys = new ArrayList<>();

		for (String jiraIssueKey : jiraIssueKeys) {
			String projectKey = jiraIssueKey.split(StringPool.DASH)[0];

			if (ArrayUtil.contains(
					ReleaseNotesConfigurationValues.JIRA_PROJECT_KEYS_ALLOWED,
					projectKey) == allowed) {

				filteredJIRAIssueKeys.add(jiraIssueKey);
			}
		}

		return filteredJIRAIssueKeys.toArray(new String[0]);
	}

	public static Collection<APIChangeClass> getAPIChanges(
			List<JIRAIssue> jiraIssues)
		throws Exception {

		Map<String, APIChangeClass> apiChangeClasses = new TreeMap<>(
			new ClassNameComparator());

		for (JIRAIssue jiraIssue : jiraIssues) {
			String apiChangeText = jiraIssue.getAPIChange();

			if (Validator.isNull(apiChangeText)) {
				continue;
			}

			String[] tokens = apiChangeText.split("Class:");

			for (String token : tokens) {
				token = token.trim();

				if (Validator.isNull(token)) {
					continue;
				}

				APIChangeClass apiChangeClass = new APIChangeClass(
					token, jiraIssue);

				String className = apiChangeClass.getClassName();

				if (apiChangeClasses.containsKey(className)) {
					APIChangeClass storedAPIChangeClass = apiChangeClasses.get(
						className);

					storedAPIChangeClass.mergeWith(apiChangeClass);
				}
				else {
					apiChangeClasses.put(className, apiChangeClass);
				}
			}
		}

		return apiChangeClasses.values();
	}

	public static String getCacheFilePath(String file, String directory) {
		StringBundler sb = new StringBundler(5);

		sb.append(_CACHE_DIR);
		sb.append(sterilizeFilePath(directory));
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(sterilizeFilePath(file));
		sb.append(".html");

		return sb.toString();
	}

	public static List<JIRAIssue> getJIRAIssuesWithUpgradeNote(
		List<JIRAIssue> jiraIssues) {

		List<JIRAIssue> jiraIssuesWithUpgradeNotes = new ArrayList<>();

		for (JIRAIssue jiraIssue : jiraIssues) {
			if (Validator.isNotNull(jiraIssue.getUpgradeNote())) {
				jiraIssuesWithUpgradeNotes.add(jiraIssue);
			}
		}

		return jiraIssuesWithUpgradeNotes;
	}

	public static Map<JIRAComponent, Set<JIRAIssue>> sortJIRAIssues(
		List<JIRAIssue> jiraIssues) {

		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap = new TreeMap<>(
			new JIRAComponentComparator());

		for (JIRAIssue jiraIssue : jiraIssues) {
			try {
				List<JIRAComponent> jiraComponents =
					JIRAComponentLocalServiceUtil.getJIRAIssueJIRAComponents(
						jiraIssue.getJiraIssueId());

				if (jiraComponents.isEmpty()) {
					jiraComponents = new ArrayList<>();

					JIRAComponent jiraComponent = new JIRAComponentImpl();

					jiraComponent.setJiraComponentId(0);
					jiraComponent.setName("None");

					jiraComponents.add(jiraComponent);
				}

				for (JIRAComponent jiraComponent : jiraComponents) {
					Set<JIRAIssue> jiraIssueSet = jiraComponentMap.get(
						jiraComponent);

					if (jiraIssueSet == null) {
						jiraIssueSet = new TreeSet<>(new JIRAIssueComparator());

						jiraComponentMap.put(jiraComponent, jiraIssueSet);
					}

					jiraIssueSet.add(jiraIssue);
				}
			}
			catch (SystemException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se.getMessage());
				}
			}
		}

		return jiraComponentMap;
	}

	protected static String sterilizeFilePath(String filePath) {
		return StringUtil.replace(
			filePath,
			new String[] {
				StringPool.BACK_SLASH, StringPool.COLON,
				StringPool.DOUBLE_QUOTE, StringPool.GREATER_THAN,
				StringPool.LESS_THAN, StringPool.PERCENT, StringPool.PERIOD,
				StringPool.PIPE, StringPool.QUESTION, StringPool.SLASH,
				StringPool.STAR
			},
			new String[] {
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE, StringPool.UNDERLINE
			});
	}

	private static final String _CACHE_DIR =
		SystemProperties.get(SystemProperties.TMP_DIR) +
			"/liferay/releasenotes/";

	private static final Log _log = LogFactoryUtil.getLog(
		ReleaseNotesUtil.class);

}