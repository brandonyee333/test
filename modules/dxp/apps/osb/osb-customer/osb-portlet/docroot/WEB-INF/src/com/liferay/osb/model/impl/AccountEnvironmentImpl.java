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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin Cui
 */
public class AccountEnvironmentImpl extends AccountEnvironmentBaseImpl {

	public String getEnvASLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvAS());
	}

	public String getEnvBrowserLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvBrowser());
	}

	public String getEnvCSLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvCS());
	}

	public String getEnvDBLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvDB());
	}

	public String getEnvJVMLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvJVM());
	}

	public String getEnvLFRLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvLFR());
	}

	public String getEnvOSLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvOS());
	}

	public List<String> getEnvSearchLabels() {
		long[] envSearches = StringUtil.split(
			getEnvSearch(), StringPool.NEW_LINE, 0L);

		List<String> envSearchLabels = new ArrayList<>();

		for (long envSearch : envSearches) {
			envSearchLabels.add(
				AccountEnvironmentConstants.getEnvLabel(envSearch));
		}

		return envSearchLabels;
	}

	public String getSupportPhaseLabel() {
		return ProductEntryConstants.getSupportPhaseLabel(getEnvLFR());
	}

}