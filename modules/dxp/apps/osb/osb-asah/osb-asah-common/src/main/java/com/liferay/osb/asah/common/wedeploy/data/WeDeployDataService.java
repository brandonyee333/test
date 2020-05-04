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

package com.liferay.osb.asah.common.wedeploy.data;

/**
 * @author Marcellus Tavares
 */
public enum WeDeployDataService {

	OSB_ASAH_CEREBRO_INFO("osbasahcerebroinfo"),
	OSB_ASAH_CEREBRO_RAW("osbasahcerebroraw"),
	OSB_ASAH_DXP_RAW("osbasahdxpraw"), OSB_ASAH_FARO_INFO("osbasahfaroinfo"),
	OSB_ASAH_SALESFORCE_RAW("osbasahsalesforceraw");

	@Override
	public String toString() {
		return _serviceName;
	}

	private WeDeployDataService(String serviceName) {
		_serviceName = serviceName;
	}

	private final String _serviceName;

}