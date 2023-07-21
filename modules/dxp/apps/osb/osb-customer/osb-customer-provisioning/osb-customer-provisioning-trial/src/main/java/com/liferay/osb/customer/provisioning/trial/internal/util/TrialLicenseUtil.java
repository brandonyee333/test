/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.provisioning.trial.internal.util;

import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.license.generator.KeyGenerator;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.customer.provisioning.trial.internal.configuration.ProvisioningTrialConfigurationValues;
import com.liferay.osb.customer.provisioning.trial.internal.license.TrialLicenseManager;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = TrialLicenseUtil.class)
public class TrialLicenseUtil {

	public String getLicenseXML(
			String productId, String versionLabel, long dayHash)
		throws Exception {

		if (!isValidVersion(productId, versionLabel)) {
			return null;
		}

		if (!_licenseManagerMap.containsKey(versionLabel)) {
			int version = GetterUtil.getInteger(
				StringUtil.extractDigits(versionLabel));

			_licenseManagerMap.put(
				versionLabel,
				new TrialLicenseManager(
					_keyGenerator, _licenseKeyExporter, productId, version,
					versionLabel));
		}

		TrialLicenseManager trialLicenseManager = _licenseManagerMap.get(
			versionLabel);

		return trialLicenseManager.getLicenseXML(dayHash);
	}

	public boolean isValidVersion(String productId, String versionLabel) {
		if (productId.equals(ProductEntryConstants.PRODUCT_ID_COMMERCE)) {
			if (ArrayUtil.contains(
					ProvisioningTrialConfigurationValues.
						COMMERCE_TRIAL_VERSIONS,
					versionLabel)) {

				return true;
			}
		}
		else {
			if (ArrayUtil.contains(
					ProvisioningTrialConfigurationValues.DXP_TRIAL_VERSIONS,
					versionLabel)) {

				return true;
			}
		}

		return false;
	}

	@Reference
	private KeyGenerator _keyGenerator;

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	private final Map<String, TrialLicenseManager> _licenseManagerMap =
		new ConcurrentHashMap<>();

}