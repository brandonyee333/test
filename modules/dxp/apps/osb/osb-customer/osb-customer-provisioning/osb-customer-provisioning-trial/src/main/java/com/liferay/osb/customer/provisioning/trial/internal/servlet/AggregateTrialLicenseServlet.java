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

import com.liferay.osb.customer.admin.constants.ProductEntryConstants;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.customer.provisioning.trial.internal.configuration.ProvisioningTrialConfigurationValues;
import com.liferay.osb.customer.provisioning.trial.internal.util.TrialLicenseUtil;
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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.osb.provisioning.trial.internal.servlet.AggregateTrialLicenseServlet",
		"osgi.http.whiteboard.servlet.pattern=/trial/*"
	},
	service = Servlet.class
)
public class AggregateTrialLicenseServlet extends HttpServlet {

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

				if (((pathArray.length - 1) % 3) == 0) {
					List<String> licenseXMLs = new ArrayList<>();

					for (int i = 0; i < (pathArray.length - 1); i += 3) {
						String product = pathArray[i + 1];
						String version = pathArray[i + 2];
						long dayHash = GetterUtil.getLong(pathArray[i + 3]);

						licenseXMLs.add(
							_getLicenseXML(product, version, dayHash));
					}

					if (!licenseXMLs.isEmpty()) {
						String licenseXML = _licenseKeyExporter.aggregateXMLs(
							ArrayUtil.toStringArray(licenseXMLs.toArray()));

						ServletResponseUtil.sendFile(
							httpServletRequest, httpServletResponse,
							"activation-key-commerce-dxp-trial.xml",
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
			ProvisioningTrialConfigurationValues.COMMERCE_TRIAL_EXPIRED_PAGE);
	}

	private String _getLicenseXML(String product, String version, long dayHash)
		throws Exception {

		if (product.equals("commerce") &&
			_trialLicenseUtil.isValidVersion(
				ProductEntryConstants.PRODUCT_ID_COMMERCE, version)) {

			return _trialLicenseUtil.getLicenseXML(
				ProductEntryConstants.PRODUCT_ID_COMMERCE, version, dayHash);
		}
		else if (product.equals("dxp") &&
				 _trialLicenseUtil.isValidVersion(
					 ProductEntryConstants.PRODUCT_ID_PORTAL, version)) {

			return _trialLicenseUtil.getLicenseXML(
				ProductEntryConstants.PRODUCT_ID_PORTAL, version, dayHash);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AggregateTrialLicenseServlet.class);

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private TrialLicenseUtil _trialLicenseUtil;

}