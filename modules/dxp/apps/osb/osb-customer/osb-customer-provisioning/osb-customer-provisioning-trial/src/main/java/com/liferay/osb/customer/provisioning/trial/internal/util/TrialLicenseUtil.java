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