/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.provisioning.trial.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class ProvisioningTrialConfigurationValues {

	public static final String COMMERCE_TRIAL_EXPIRED_PAGE =
		GetterUtil.getString(
			ProvisioningTrialConfigurationUtil.get(
				"commerce.trial.expired.page"));

	public static final String[] COMMERCE_TRIAL_VERSIONS = StringUtil.split(
		ProvisioningTrialConfigurationUtil.get("commerce.trial.versions"));

	public static final String DXP_TRIAL_EXPIRED_PAGE = GetterUtil.getString(
		ProvisioningTrialConfigurationUtil.get("dxp.trial.expired.page"));

	public static final String[] DXP_TRIAL_VERSIONS = StringUtil.split(
		ProvisioningTrialConfigurationUtil.get("dxp.trial.versions"));

}