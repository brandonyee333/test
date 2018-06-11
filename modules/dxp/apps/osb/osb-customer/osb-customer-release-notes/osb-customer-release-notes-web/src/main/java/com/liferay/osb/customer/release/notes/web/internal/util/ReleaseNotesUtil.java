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

package com.liferay.osb.customer.release.notes.web.internal.util;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.jira.service.JIRAComponentLocalServiceUtil;
import com.liferay.osb.customer.release.notes.jira.util.comparator.JIRAComponentComparator;
import com.liferay.osb.customer.release.notes.jira.util.comparator.JIRAIssueComparator;
import com.liferay.osb.customer.release.notes.web.internal.configuration.ReleaseNotesConfigurationValues;
import com.liferay.osb.customer.release.notes.web.internal.util.apichange.APIChangeClass;
import com.liferay.osb.customer.release.notes.web.internal.util.comparator.ClassNameComparator;
import com.liferay.osb.customer.release.notes.web.internal.util.filter.JIRAComponentFilter;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
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

	public static Map<JIRAComponent, Set<JIRAIssue>> filterJIRAComponentMap(
		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap,
		JIRAProjectVersion jiraProjectVersion) {

		for (String className :
				ReleaseNotesConfigurationValues.JIRA_COMPONENT_FILTERS) {

			try {
				Class<JIRAComponentFilter> clazz =
					(Class<JIRAComponentFilter>)Class.forName(className);

				JIRAComponentFilter jiraComponentFilter = clazz.newInstance();

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

					JIRAComponent jiraComponent =
						JIRAComponentLocalServiceUtil.createJIRAComponent(0);

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

	private static final Log _log = LogFactoryUtil.getLog(
		ReleaseNotesUtil.class);

}