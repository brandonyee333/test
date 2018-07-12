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

package com.liferay.lcs.rest;

import com.liferay.lcs.rest.client.LCSClusterNode;
import com.liferay.lcs.rest.client.LCSClusterNodeClient;
import com.liferay.lcs.rest.client.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.lcs.rest.client.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.lcs.rest.client.exception.RequiredLCSClusterNodeNameException;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeClientUtil {

	public static LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			String location, int portalBuildNumber, int processorCoresTotal)
		throws DuplicateLCSClusterNodeNameException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException,
			   NoSuchLCSSubscriptionEntryException,
			   RequiredLCSClusterNodeNameException {

		return _lcsClusterNodeClient.addLCSClusterNode(
			lcsClusterEntryId, name, description, _keyGenerator.getKey(),
			location, portalBuildNumber, processorCoresTotal);
	}

	public static LCSClusterNode fetchLCSClusterNode()
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		return _lcsClusterNodeClient.fetchLCSClusterNode(
			_keyGenerator.getKey());
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		_keyGenerator = keyGenerator;
	}

	public void setLCSClusterNodeService(
		LCSClusterNodeClient lcsClusterNodeClient) {

		_lcsClusterNodeClient = lcsClusterNodeClient;
	}

	private static KeyGenerator _keyGenerator;
	private static LCSClusterNodeClient _lcsClusterNodeClient;

}