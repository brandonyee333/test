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

package com.liferay.lcs.advisor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.lcs.activation.LCSClusterEntryTokenContentAdvisor;
import com.liferay.lcs.exception.LCSClusterEntryTokenDecryptException;
import com.liferay.lcs.exception.LCSHandshakeException;
import com.liferay.lcs.exception.LCSKeystoreException;
import com.liferay.lcs.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.internal.event.LCSEventListener;
import com.liferay.lcs.rest.client.LCSClusterEntryToken;
import com.liferay.lcs.security.KeyStoreAdvisor;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.lcs.util.LCSAlert;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.util.Encryptor;
import com.liferay.util.EncryptorException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.crypto.spec.SecretKeySpec;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryTokenAdvisor implements LCSEventListener {

	public void checkLCSClusterEntryTokenError(LCSHandshakeException lcshe) {
		if (lcshe.getErrorCode() == 200) {
			_lcsAlertAdvisor.add(LCSAlert.ERROR_INVALID_TOKEN);
		}
		else if (lcshe.getErrorCode() == 201) {
			_lcsAlertAdvisor.add(LCSAlert.ERROR_ENVIRONMENT_MISMATCH);
		}
	}

	public String getLCSAccessSecret() {
		return _lcsAccessSecret;
	}

	public String getLCSAccessToken() {
		return _lcsAccessToken;
	}

	public long getLcsClusterEntryTokenId() {
		return _lcsClusterEntryTokenId;
	}

	public String getPortalPropertiesBlacklist() {
		return _portalPropertiesBlacklist;
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if ((lcsEvent ==
				LCSEvent.HANDSHAKE_FAILED_LCS_CLUSTER_ENTRY_TOKEN_CORRUPTED) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED)) {

			_deleteLCSCLusterEntryTokenFile();
		}
	}

	public LCSClusterEntryToken processLCSClusterEntryToken(
			int lcsPortletBuildNumber)
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException {

		LCSClusterEntryToken lcsClusterEntryToken =
			processLCSCLusterEntryTokenFile(lcsPortletBuildNumber);

		LCSClusterEntryTokenContentAdvisor lcsClusterEntryTokenContentAdvisor =
			new LCSClusterEntryTokenContentAdvisor(
				lcsClusterEntryToken.getContent());

		_lcsAccessSecret = lcsClusterEntryTokenContentAdvisor.getAccessSecret();
		_lcsAccessToken = lcsClusterEntryTokenContentAdvisor.getAccessToken();

		_lcsClusterEntryId = lcsClusterEntryToken.getLcsClusterEntryId();
		_lcsClusterEntryTokenId =
			lcsClusterEntryToken.getLcsClusterEntryTokenId();

		_portalPropertiesBlacklist =
			lcsClusterEntryTokenContentAdvisor.getPortalPropertiesBlacklist();

		return lcsClusterEntryToken;
	}

	public void setLCSAlertAdvisor(LCSAlertAdvisor lcsAlertAdvisor) {
		_lcsAlertAdvisor = lcsAlertAdvisor;
	}

	protected String decrypt(byte[] bytes, int lcsPortletBuildNumber)
		throws EncryptorException {

		KeyStore keyStore = _getLCSKeystore();

		String keyName = PortletPropsValues.DIGITAL_SIGNATURE_KEY_NAME;

		Certificate certificate = _getCertificate(keyStore, keyName);

		Key key = certificate.getPublicKey();

		byte[] symmetricKeyEncrypted = ArrayUtil.subset(bytes, 0, 256);

		byte[] symmetricKeyBytes = null;

		try {
			if (_log.isDebugEnabled()) {
				_log.debug("Decrypt with default key " + keyName);
			}

			symmetricKeyBytes = Encryptor.decryptUnencodedAsBytes(
				key, symmetricKeyEncrypted);
		}
		catch (EncryptorException ee) {
			KeyStoreAdvisor keyStoreAdvisor = new KeyStoreAdvisor();

			try {
				keyName = keyStoreAdvisor.getKeyAlias(
					lcsPortletBuildNumber,
					PortletPropsValues.DIGITAL_SIGNATURE_KEY_NAME, keyStore);
			}
			catch (Exception e) {
				throw new LCSKeystoreException(
					"Unable to resolve key store certificate entry for LCS " +
						"portlet build number " + lcsPortletBuildNumber,
					e);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Decrypt with key " + keyName);
			}

			certificate = _getCertificate(keyStore, keyName);

			key = certificate.getPublicKey();

			symmetricKeyBytes = Encryptor.decryptUnencodedAsBytes(
				key, symmetricKeyEncrypted);
		}

		Key symmetricKey = new SecretKeySpec(symmetricKeyBytes, "AES");

		return Encryptor.decryptUnencodedAsString(
			symmetricKey, ArrayUtil.subset(bytes, 256, bytes.length));
	}

	protected String getLCSClusterEntryTokenFileName()
		throws MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException {

		StringBundler sb = new StringBundler(5);

		sb.append(_getLiferayHome());
		sb.append(File.separatorChar);
		sb.append("data");

		File liferayDataDir = new File(sb.toString());

		String[] lcsClusterEntryTokenFileNames = liferayDataDir.list(
			new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					if (name.startsWith("lcs-cluster-entry-token")) {
						if (_log.isWarnEnabled()) {
							StringBundler sb = new StringBundler(7);

							sb.append("LCS activation token file name ");
							sb.append(name);
							sb.append(" is deprecated and will not be ");
							sb.append("supported in the next version. Please ");
							sb.append("download the LCS activation token ");
							sb.append("file again and replace the old file ");
							sb.append("with the new one.");

							_log.warn(sb.toString());
						}

						return true;
					}

					if (name.startsWith("lcs-aatf")) {
						return true;
					}

					return false;
				}

			});

		if (lcsClusterEntryTokenFileNames.length == 0) {
			_lcsAlertAdvisor.add(LCSAlert.ERROR_MISSING_TOKEN);

			throw new MissingLCSClusterEntryTokenException(
				"The LCS activation token file is missing from directory " +
					sb.toString());
		}
		else if (lcsClusterEntryTokenFileNames.length > 1) {
			_lcsAlertAdvisor.add(LCSAlert.ERROR_MULTIPLE_TOKENS);

			throw new MultipleLCSClusterEntryTokenException(
				"Only one LCS activation token file is allowed");
		}

		sb.append(File.separatorChar);
		sb.append(lcsClusterEntryTokenFileNames[0]);

		return sb.toString();
	}

	protected LCSClusterEntryToken processLCSCLusterEntryTokenFile(
			int lcsPortletBuildNumber)
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException {

		if (_log.isDebugEnabled()) {
			_log.debug("Detecting LCS activation token file");
		}

		LCSClusterEntryToken lcsClusterEntryToken = null;

		String lcsClusterEntryTokenFileName = getLCSClusterEntryTokenFileName();

		File lcsClusterEntryTokenFile = new File(lcsClusterEntryTokenFileName);

		byte[] bytes = FileUtil.getBytes(lcsClusterEntryTokenFile);

		String lcsClusterEntryTokenJSON = null;

		try {
			lcsClusterEntryTokenJSON = decrypt(bytes, lcsPortletBuildNumber);
		}
		catch (EncryptorException ee) {
			_deleteLCSCLusterEntryTokenFile();

			_lcsAlertAdvisor.add(LCSAlert.ERROR_INVALID_TOKEN);

			StringBundler sb = new StringBundler(6);

			sb.append("Unable to decrypt the LCS activation token file ");
			sb.append(lcsClusterEntryTokenFileName);
			sb.append(". The LCS activation token file will be deleted. ");
			sb.append("Please make sure that the LCS portlet is compatible ");
			sb.append("with the LCS platform version where the file was ");
			sb.append("generated.");

			throw new LCSClusterEntryTokenDecryptException(sb.toString(), ee);
		}

		ObjectMapper objectMapper = new ObjectMapper();

		lcsClusterEntryToken = objectMapper.readValue(
			lcsClusterEntryTokenJSON, LCSClusterEntryToken.class);

		return lcsClusterEntryToken;
	}

	private void _deleteLCSCLusterEntryTokenFile() {
		_resetAttributes();

		if (_log.isDebugEnabled()) {
			_log.debug("Deleting LCS activation token file");
		}

		try {
			FileUtil.delete(getLCSClusterEntryTokenFileName());
		}
		catch (MissingLCSClusterEntryTokenException mlcscete) {
			if (_log.isDebugEnabled()) {
				_log.debug("LCS activation token file is not present");
			}
		}
		catch (MultipleLCSClusterEntryTokenException mlcscete) {
			_log.error(
				"Multiple LCS activation token files detected. Please delete " +
					"the redundant files manually.",
				mlcscete);
		}
	}

	private Certificate _getCertificate(KeyStore keyStore, String keyName) {
		try {
			return keyStore.getCertificate(keyName);
		}
		catch (KeyStoreException kse) {
			throw new LCSKeystoreException(
				"Unable to locate LCS certificate " + keyName, kse);
		}
	}

	private KeyStore _getLCSKeystore() {
		try {
			return KeyStoreFactory.getInstance(
				PortletPropsValues.DIGITAL_SIGNATURE_KEY_STORE_PATH,
				PortletPropsValues.DIGITAL_SIGNATURE_KEY_STORE_TYPE,
				"_k3y#5t0r3-p45S");
		}
		catch (Exception e) {
			throw new LCSKeystoreException(
				"Unable to instantiate LCS keystore", e);
		}
	}

	private String _getLiferayHome() {
		String liferayHome = PropsUtil.get("liferay.home");

		if (liferayHome.endsWith(File.separator)) {
			return liferayHome.substring(0, liferayHome.length() - 1);
		}

		return liferayHome;
	}

	private void _resetAttributes() {
		if (_log.isDebugEnabled()) {
			_log.debug("Reset attributes");
		}

		_portalPropertiesBlacklist = null;
		_lcsAccessToken = null;
		_lcsAccessSecret = null;
		_lcsClusterEntryTokenId = 0L;
		_lcsClusterEntryId = 0L;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClusterEntryTokenAdvisor.class);

	private String _lcsAccessSecret;
	private String _lcsAccessToken;
	private LCSAlertAdvisor _lcsAlertAdvisor;
	private long _lcsClusterEntryId;
	private long _lcsClusterEntryTokenId;
	private String _portalPropertiesBlacklist;

}