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

package com.liferay.lcs.client.internal.advisor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.lcs.activation.LCSClusterEntryTokenContentAdvisor;
import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.exception.LCSClusterEntryTokenDecryptException;
import com.liferay.lcs.client.exception.MissingLCSClusterEntryTokenException;
import com.liferay.lcs.client.exception.MultipleLCSClusterEntryTokenException;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.exception.LCSKeystoreException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryToken;
import com.liferay.lcs.security.KeyStoreAdvisor;
import com.liferay.lcs.security.KeyStoreFactory;
import com.liferay.petra.encryptor.Encryptor;
import com.liferay.petra.encryptor.EncryptorException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;

import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(service = LCSClusterEntryTokenAdvisor.class)
public class LCSClusterEntryTokenAdvisorImpl
	implements LCSClusterEntryTokenAdvisor, LCSEventListener {

	public LCSClusterEntryTokenAdvisorImpl() {
	}

	public LCSClusterEntryTokenAdvisorImpl(
		LCSConfigurationProvider lcsConfigurationProvider,
		LCSEventManager lcsEventManager) {

		_lcsConfigurationProvider = lcsConfigurationProvider;
		_lcsEventManager = lcsEventManager;

		_initLCSEventManager();
	}

	public String getLCSAccessSecret() {
		return _lcsAccessSecret;
	}

	public String getLCSAccessToken() {
		return _lcsAccessToken;
	}

	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
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
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED) ||
			(lcsEvent ==
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID) ||
			(lcsEvent ==
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED)) {

			_deleteLCSCLusterEntryTokenFile();
		}
	}

	public LCSClusterEntryToken processLCSClusterEntryToken()
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException {

		LCSClusterEntryToken lcsClusterEntryToken =
			_processLCSClusterEntryTokenFile(
				LCSClientConstants.LCS_CLIENT_BUILD_NUMBER);

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

	@Activate
	protected void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_keyName = lcsConfiguration.digitalSignatureKeyName();
		_keyStorePath = lcsConfiguration.digitalSignatureKeyStorePath();
		_keyStoreType = lcsConfiguration.digitalSignatureKeyStoreType();

		_initLCSEventManager();
	}

	protected String decrypt(byte[] bytes, int lcsPortletBuildNumber)
		throws EncryptorException {

		KeyStore keyStore = _getLCSKeystore();

		Certificate certificate = _getCertificate(keyStore, _keyName);

		Key key = certificate.getPublicKey();

		byte[] symmetricKeyEncrypted = ArrayUtil.subset(bytes, 0, 256);

		byte[] symmetricKeyBytes = null;

		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Decrypting environment token with default key " +
						_keyName);
			}

			symmetricKeyBytes = Encryptor.decryptUnencodedAsBytes(
				key, symmetricKeyEncrypted);
		}
		catch (EncryptorException ee) {
			KeyStoreAdvisor keyStoreAdvisor = new KeyStoreAdvisor();

			try {
				_keyName = keyStoreAdvisor.getKeyAlias(
					lcsPortletBuildNumber, _keyName, keyStore);
			}
			catch (Exception e) {
				throw new LCSKeystoreException(
					"Unable to resolve key store certificate entry for LCS " +
						"portlet build number " + lcsPortletBuildNumber,
					e);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Decrypting environment token with key " + _keyName);
			}

			certificate = _getCertificate(keyStore, _keyName);

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
					if (name.startsWith("lcs-aatf")) {
						return true;
					}

					return false;
				}

			});

		if (lcsClusterEntryTokenFileNames.length == 0) {
			throw new MissingLCSClusterEntryTokenException(
				"The environment token file is missing. Please download a " +
					"token file from LCS and place it in " + sb.toString());
		}
		else if (lcsClusterEntryTokenFileNames.length > 1) {
			throw new MultipleLCSClusterEntryTokenException(
				"There are multiple environment token files. Only one is " +
					"allowed.");
		}

		sb.append(File.separatorChar);
		sb.append(lcsClusterEntryTokenFileNames[0]);

		return sb.toString();
	}

	private void _deleteLCSCLusterEntryTokenFile() {
		_resetAttributes();

		if (_log.isDebugEnabled()) {
			_log.debug("Deleting environment token file");
		}

		try {
			FileUtil.delete(getLCSClusterEntryTokenFileName());

			if (_log.isWarnEnabled()) {
				_log.warn("Deleted environment token file");
			}
		}
		catch (MissingLCSClusterEntryTokenException mlcscete) {
			_log.error(mlcscete.getMessage());
		}
		catch (MultipleLCSClusterEntryTokenException mlcscete) {
			_log.error(mlcscete.getMessage());
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
				_keyStorePath, _keyStoreType, "_k3y#5t0r3-p45S");
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

	private void _initLCSEventManager() {
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED, this);
	}

	private LCSClusterEntryToken _processLCSClusterEntryTokenFile(
			int lcsPortletBuildNumber)
		throws IOException, LCSClusterEntryTokenDecryptException,
			   MissingLCSClusterEntryTokenException,
			   MultipleLCSClusterEntryTokenException {

		if (_log.isDebugEnabled()) {
			_log.debug("Processing the environment token file");
		}

		String lcsClusterEntryTokenFileName = getLCSClusterEntryTokenFileName();

		File lcsClusterEntryTokenFile = new File(lcsClusterEntryTokenFileName);

		byte[] bytes = FileUtil.getBytes(lcsClusterEntryTokenFile);

		String lcsClusterEntryTokenJSON = null;

		try {
			lcsClusterEntryTokenJSON = decrypt(bytes, lcsPortletBuildNumber);
		}
		catch (EncryptorException ee) {
			throw new LCSClusterEntryTokenDecryptException(
				"Unable to decrypt environment token file. Please " +
					"regenerate, download, and install a new token.",
				ee);
		}

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readValue(
			lcsClusterEntryTokenJSON, LCSClusterEntryToken.class);
	}

	private void _resetAttributes() {
		if (_log.isDebugEnabled()) {
			_log.debug("Resetting the environment token attributes");
		}

		_portalPropertiesBlacklist = null;
		_lcsAccessToken = null;
		_lcsAccessSecret = null;
		_lcsClusterEntryTokenId = 0L;
		_lcsClusterEntryId = 0L;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSClusterEntryTokenAdvisorImpl.class);

	private String _keyName;
	private String _keyStorePath;
	private String _keyStoreType;
	private String _lcsAccessSecret;
	private String _lcsAccessToken;
	private long _lcsClusterEntryId;
	private long _lcsClusterEntryTokenId;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSEventManager _lcsEventManager;

	private String _portalPropertiesBlacklist;

}