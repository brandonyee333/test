/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.provisioning.trial.internal.servlet;

import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.license.generator.KeyGenerator;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.customer.provisioning.trial.internal.configuration.ProvisioningTrialConfigurationValues;
import com.liferay.osb.customer.provisioning.trial.internal.util.TrialLicenseUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

					String licenseXML = _trialLicenseUtil.getLicenseXML(
						ProductEntryConstants.PRODUCT_ID_PORTAL, versionLabel,
						dayHash);

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

	private static final Log _log = LogFactoryUtil.getLog(
		DXPTrialLicenseServlet.class);

	@Reference
	private KeyGenerator _keyGenerator;

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private TrialLicenseUtil _trialLicenseUtil;

}