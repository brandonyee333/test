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

package com.liferay.lcs.rest.client.internal;

import com.liferay.lcs.rest.client.LCSClientError;
import com.liferay.lcs.rest.client.LCSClusterNode;
import com.liferay.lcs.rest.client.LCSClusterNodeClient;
import com.liferay.lcs.rest.client.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.lcs.rest.client.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.lcs.rest.client.exception.RequiredLCSClusterNodeNameException;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceSerializeException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeClientImpl implements LCSClusterNodeClient {

	@Override
	public LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			int buildNumber, String key, String location,
			int processorCoresTotal)
		throws DuplicateLCSClusterNodeNameException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException,
			   NoSuchLCSSubscriptionEntryException,
			   RequiredLCSClusterNodeNameException {

		validate(lcsClusterEntryId, name);

		if ((description != null) && description.equals("")) {
			description = null;
		}

		if ((location != null) && location.equals("")) {
			location = null;
		}

		try {
			return _jsonWebServiceClient.doPostToObject(
				LCSClusterNode.class, _URL_LCS_CLUSTER_NODE, "buildNumber",
				String.valueOf(buildNumber), "name", name, "description",
				description, "lcsClusterEntryId",
				String.valueOf(lcsClusterEntryId), "location", location, "key",
				key, "processorCoresTotal",
				String.valueOf(processorCoresTotal));
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (LCSClientError.getRESTError(jsonwsie) ==
					LCSClientError.NO_SUCH_LCS_SUBSCRIPTION_ENTRY) {

				throw new NoSuchLCSSubscriptionEntryException(jsonwsie);
			}

			throw jsonwsie;
		}
	}

	@Override
	public LCSClusterNode fetchLCSClusterNode(String key)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		try {
			return _jsonWebServiceClient.doGetToObject(
				LCSClusterNode.class, _URL_LCS_CLUSTER_NODE, "key", key);
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			if (jsonwsie.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				return null;
			}

			throw jsonwsie;
		}
	}

	@Override
	public List<LCSClusterNode> getLCSClusterEntryLCSClusterNodes(
			long lcsClusterEntryId)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException {

		List<LCSClusterNode> remoteLCSClusterNodes = null;

		StringBuilder sb = new StringBuilder(5);

		sb.append(_URL_LCS_CLUSTER_NODE);
		sb.append("/find/");
		sb.append(-1);
		sb.append("/");
		sb.append(-1);

		remoteLCSClusterNodes = _jsonWebServiceClient.doGetToList(
			LCSClusterNode.class, _URL_LCS_CLUSTER_NODE, "lcsClusterEntryId",
			String.valueOf(lcsClusterEntryId));

		List<LCSClusterNode> lcsClusterNodes = new ArrayList<>();

		for (LCSClusterNode lcsClusterNode : remoteLCSClusterNodes) {
			lcsClusterNodes.add(lcsClusterNode);
		}

		return lcsClusterNodes;
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected void validate(long lcsClusterEntryId, String lcsClusterNodeName)
		throws DuplicateLCSClusterNodeNameException,
			   JSONWebServiceInvocationException,
			   JSONWebServiceSerializeException,
			   JSONWebServiceTransportException,
			   RequiredLCSClusterNodeNameException {

		if ((lcsClusterNodeName == null) || lcsClusterNodeName.equals("")) {
			throw new RequiredLCSClusterNodeNameException();
		}

		List<LCSClusterNode> lcsClusterNodes =
			getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (StringUtil.equalsIgnoreCase(
					lcsClusterNodeName, lcsClusterNode.getName())) {

				throw new DuplicateLCSClusterNodeNameException();
			}
		}
	}

	private static final String _URL_LCS_CLUSTER_NODE =
		"/osb-lcs-portlet/lcs/jsonws/v1_4/LCSClusterNode";

	private JSONWebServiceClient _jsonWebServiceClient;

}