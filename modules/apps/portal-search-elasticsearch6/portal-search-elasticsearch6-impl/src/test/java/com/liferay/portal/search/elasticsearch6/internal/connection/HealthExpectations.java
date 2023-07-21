/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.connection;

import com.liferay.petra.string.StringBundler;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

/**
 * @author André de Oliveira
 */
public class HealthExpectations {

	public HealthExpectations() {
	}

	public HealthExpectations(ClusterHealthResponse clusterHealthResponse) {
		activePrimaryShards = clusterHealthResponse.getActivePrimaryShards();
		activeShards = clusterHealthResponse.getActiveShards();
		numberOfDataNodes = clusterHealthResponse.getNumberOfDataNodes();
		numberOfNodes = clusterHealthResponse.getNumberOfNodes();
		status = clusterHealthResponse.getStatus();
		timedOut = clusterHealthResponse.isTimedOut();
		unassignedShards = clusterHealthResponse.getUnassignedShards();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{activePrimaryShards=");
		sb.append(activePrimaryShards);
		sb.append(", activeShards=");
		sb.append(activeShards);
		sb.append(", numberOfDataNodes=");
		sb.append(numberOfDataNodes);
		sb.append(", numberOfNodes=");
		sb.append(numberOfNodes);
		sb.append(", status=");
		sb.append(status);
		sb.append(", timedOut=");
		sb.append(timedOut);
		sb.append(", unassignedShards=");
		sb.append(unassignedShards);
		sb.append("}");

		return sb.toString();
	}

	public int activePrimaryShards;
	public int activeShards;
	public int numberOfDataNodes;
	public int numberOfNodes;
	public ClusterHealthStatus status;
	public boolean timedOut;
	public int unassignedShards;

}