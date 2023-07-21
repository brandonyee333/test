/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.constants.AccountEnvironmentConstants;
import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
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

	public String getEnvCommerceLabel() {
		return AccountEnvironmentConstants.getEnvLabel(getEnvCommerce());
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