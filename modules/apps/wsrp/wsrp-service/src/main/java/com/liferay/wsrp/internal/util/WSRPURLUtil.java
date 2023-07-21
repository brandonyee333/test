/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringPool;

import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tomas Polesovsky
 */
@Component(immediate = true, service = WSRPURLUtil.class)
public class WSRPURLUtil {

	public String encodeWSRPAuth(long companyId, String wsrpAuth)
		throws Exception {

		Company company = _companyLocalService.getCompany(companyId);

		Key key = company.getKeyObj();

		byte[] hmacSHA = getHMACSha(
			key.getEncoded(), wsrpAuth.getBytes(StringPool.UTF8));

		return Base64.encodeToURL(hmacSHA);
	}

	protected byte[] getHMACSha(byte[] key, byte[] data) throws Exception {
		Mac mac = Mac.getInstance(_ALGORITHM);

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, _ALGORITHM);

		mac.init(secretKeySpec);

		return mac.doFinal(data);
	}

	private static final String _ALGORITHM = "HmacSHA1";

	@Reference
	private CompanyLocalService _companyLocalService;

}