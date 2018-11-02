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

package com.liferay.lcs.internal.event;

/**
 * @author Igor Beslic
 */
public enum LCSEvent {

	HANDSHAKE_FAILED, HANDSHAKE_SUCCESS, LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED,
	LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS,
	LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED,
	LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH,
	LCS_CLUSTER_ENTRY_TOKEN_INVALID,
	LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS,
	LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED, LCS_CLUSTER_NODE_UNREGISTERED,
	LCS_GATEWAY_AVAILABLE, LCS_GATEWAY_UNAVAILABLE, SIGNOFF_FAILED,
	SIGNOFF_SUCCESS

}