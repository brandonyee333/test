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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Map;
import java.util.Set;

/**
 * Provides the local service interface for JIRAIssue. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Noah Sherrill
 * @see JIRAIssueLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface JIRAIssueLocalService extends BaseLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAIssueLocalServiceUtil} to access the jira issue local service. Add custom service methods to <code>com.liferay.osb.customer.jira.rest.connector.service.impl.JIRAIssueLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public JSONObject createJIRAIssue(
			String projectKey, String issueType, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getJIRAIssue(String ticketKey) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getJIRAIssues(String jql, int startAt, int maxResults)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public JSONObject updateJIRAIssue(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException;

}