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

package com.liferay.lcs.client.internal.platform.http.internal;

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
		catch (Exception exception) {
			throw new RuntimeException(exception);
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