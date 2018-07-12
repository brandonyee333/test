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

package com.liferay.lcs.rest.client;

import com.liferay.lcs.rest.client.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.lcs.rest.client.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.lcs.rest.client.exception.RequiredLCSClusterNodeNameException;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSClusterNodeClient {

	public LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description, String key,
			String location, int portalBuildNumber, int processorCoresTotal)
		throws DuplicateLCSClusterNodeNameException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException,
			   NoSuchLCSSubscriptionEntryException,
			   RequiredLCSClusterNodeNameException;

	public LCSClusterNode fetchLCSClusterNode(String key)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException;

}