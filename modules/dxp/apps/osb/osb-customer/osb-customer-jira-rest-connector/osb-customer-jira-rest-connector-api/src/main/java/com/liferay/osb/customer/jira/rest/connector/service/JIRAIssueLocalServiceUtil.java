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

package com.liferay.osb.customer.jira.rest.connector.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for JIRAIssue. This utility wraps
 * <code>com.liferay.osb.customer.jira.rest.connector.service.impl.JIRAIssueLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Noah Sherrill
 * @see JIRAIssueLocalService
 * @generated
 */
@ProviderType
public class JIRAIssueLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.jira.rest.connector.service.impl.JIRAIssueLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject createJIRAIssue(
			String projectKey, String issueType, String summary,
			String description, String assigneeName,
			java.util.Set<String> labels,
			java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createJIRAIssue(
			projectKey, issueType, summary, description, assigneeName, labels,
			customFields, status);
	}

	public static com.liferay.portal.kernel.json.JSONObject getJIRAIssue(
			String ticketKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getJIRAIssue(ticketKey);
	}

	public static com.liferay.portal.kernel.json.JSONObject getJIRAIssues(
			String jql, String expand, String fields, int startAt,
			int maxResults)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getJIRAIssues(
			jql, expand, fields, startAt, maxResults);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject updateJIRAIssue(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName,
			java.util.Set<String> labels,
			java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateJIRAIssue(
			projectKey, ticketKey, summary, description, assigneeName, labels,
			customFields, status);
	}

	public static JIRAIssueLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRAIssueLocalService, JIRAIssueLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JIRAIssueLocalService.class);

		ServiceTracker<JIRAIssueLocalService, JIRAIssueLocalService>
			serviceTracker =
				new ServiceTracker
					<JIRAIssueLocalService, JIRAIssueLocalService>(
						bundle.getBundleContext(), JIRAIssueLocalService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}