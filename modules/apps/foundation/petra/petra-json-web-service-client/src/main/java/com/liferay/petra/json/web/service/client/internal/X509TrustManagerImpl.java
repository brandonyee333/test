/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.json.web.service.client.internal;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class X509TrustManagerImpl implements X509TrustManager {

	public X509TrustManagerImpl() {
		try {
			X509TrustManager x509TrustManager = null;

			TrustManagerFactory trustManagerFactory =
				TrustManagerFactory.getInstance(
					TrustManagerFactory.getDefaultAlgorithm());

			trustManagerFactory.init((KeyStore)null);

			for (TrustManager trustManager :
					trustManagerFactory.getTrustManagers()) {

				if (trustManager instanceof X509TrustManager) {
					x509TrustManager = (X509TrustManager)trustManager;

					break;
				}
			}

			_x509TrustManager = x509TrustManager;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void checkClientTrusted(
			X509Certificate[] x509Certificates, String authType)
		throws CertificateException {

		if (x509Certificates.length != 1) {
			_x509TrustManager.checkClientTrusted(x509Certificates, authType);
		}
	}

	@Override
	public void checkServerTrusted(
			X509Certificate[] x509Certificates, String authType)
		throws CertificateException {

		if (x509Certificates.length != 1) {
			_x509TrustManager.checkServerTrusted(x509Certificates, authType);
		}
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return _x509TrustManager.getAcceptedIssuers();
	}

	private final X509TrustManager _x509TrustManager;

}