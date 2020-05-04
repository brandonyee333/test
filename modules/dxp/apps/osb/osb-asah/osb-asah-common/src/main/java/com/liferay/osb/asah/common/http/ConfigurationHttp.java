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

package com.liferay.osb.asah.common.http;

import org.json.JSONObject;

/**
 * @author Shinn Lok
 */
public interface ConfigurationHttp {

	public void addConfiguration(JSONObject jsonObject, String providerType);

	public void deleteConfiguration(JSONObject jsonObject, String providerType);

	public String getState(JSONObject jsonObject, String providerType);

	public String refreshConfiguration(
		JSONObject jsonObject, String providerType);

	public void updateConfiguration(JSONObject jsonObject, String providerType)
		throws Exception;

}