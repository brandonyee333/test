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

package com.liferay.osb.customer.provisioning.trial.internal.servlet;

import com.liferay.osb.customer.provisioning.trial.internal.configuration.ProvisioningTrialConfigurationValues;
import com.liferay.osb.customer.provisioning.trial.internal.license.DXPTrialLicenseManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.osb.provisioning.trial.internal.servlet.DXPTrialLicenseServlet",
		"osgi.http.whiteboard.servlet.pattern=/dxp-trial/*"
	},
	service = Servlet.class
)
public class DXPTrialLicenseServlet extends HttpServlet {

	@Override
	protected void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		try {
			String path = GetterUtil.getString(
				httpServletRequest.getPathInfo());

			if (Validator.isNotNull(path)) {
				String[] pathArray = StringUtil.split(path, CharPool.SLASH);

				if (pathArray.length == 3) {
					String versionLabel = pathArray[1];
					long dayHash = GetterUtil.getLong(pathArray[2]);

					String licenseXML = _getLicenseXML(versionLabel, dayHash);

					if (Validator.isNotNull(licenseXML)) {
						ServletResponseUtil.sendFile(
							httpServletRequest, httpServletResponse,
							"activation-key-dxp-trial-" + versionLabel + ".xml",
							licenseXML.getBytes(), ContentTypes.TEXT_XML);

						return;
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		httpServletResponse.sendRedirect(
			ProvisioningTrialConfigurationValues.DXP_TRIAL_EXPIRED_PAGE);
	}

	private String _getLicenseXML(String versionLabel, long dayHash)
		throws Exception {

		if (!_isValidVersion(versionLabel)) {
			return null;
		}

		if (!_licenseManagerMap.containsKey(versionLabel)) {
			int version = GetterUtil.getInteger(
				StringUtil.extractDigits(versionLabel));

			_licenseManagerMap.put(
				versionLabel,
				new DXPTrialLicenseManager(version, versionLabel));
		}

		DXPTrialLicenseManager dxpTrialLicenseManager = _licenseManagerMap.get(
			versionLabel);

		return dxpTrialLicenseManager.getLicenseXML(dayHash);
	}

	private boolean _isValidVersion(String versionLabel) {
		if (ArrayUtil.contains(
				ProvisioningTrialConfigurationValues.DXP_TRIAL_VERSIONS,
				versionLabel)) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DXPTrialLicenseServlet.class);

	private final Map<String, DXPTrialLicenseManager> _licenseManagerMap =
		new ConcurrentHashMap<>();

}