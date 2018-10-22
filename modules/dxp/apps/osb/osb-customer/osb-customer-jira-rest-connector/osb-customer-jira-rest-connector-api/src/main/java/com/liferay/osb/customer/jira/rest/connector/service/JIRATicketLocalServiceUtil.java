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
 * Provides the local service utility for JIRATicket. This utility wraps
 * {@link com.liferay.osb.customer.jira.rest.connector.service.impl.JIRATicketLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Noah Sherrill
 * @see JIRATicketLocalService
 * @see com.liferay.osb.customer.jira.rest.connector.service.base.JIRATicketLocalServiceBaseImpl
 * @see com.liferay.osb.customer.jira.rest.connector.service.impl.JIRATicketLocalServiceImpl
 * @generated
 */
@ProviderType
public class JIRATicketLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.jira.rest.connector.service.impl.JIRATicketLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONObject createJIRATicket(
		String projectKey, String issueType, String summary,
		String description, String assigneeName, java.util.Set<String> labels,
		java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .createJIRATicket(projectKey, issueType, summary,
			description, assigneeName, labels, customFields, status);
	}

	public static com.liferay.portal.kernel.json.JSONObject getJIRATicket(
		String ticketKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJIRATicket(ticketKey);
	}

	public static com.liferay.portal.kernel.json.JSONObject getJIRATickets(
		String jql) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getJIRATickets(jql);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject updateJIRATicket(
		String projectKey, String ticketKey, String summary,
		String description, String assigneeName, java.util.Set<String> labels,
		java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateJIRATicket(projectKey, ticketKey, summary,
			description, assigneeName, labels, customFields, status);
	}

	public static JIRATicketLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<JIRATicketLocalService, JIRATicketLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(JIRATicketLocalService.class);

		ServiceTracker<JIRATicketLocalService, JIRATicketLocalService> serviceTracker =
			new ServiceTracker<JIRATicketLocalService, JIRATicketLocalService>(bundle.getBundleContext(),
				JIRATicketLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}