/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.Event;
import com.liferay.sync.engine.document.library.model.SyncContext;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncUser;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncUserService;
import com.liferay.sync.engine.util.GetterUtil;
import com.liferay.sync.engine.util.JSONUtil;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;

/**
 * @author Shinn Lok
 */
public class GetSyncContextHandler extends BaseJSONHandler {

	public GetSyncContextHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		doProcessResponse(response);
	}

	protected SyncContext doProcessResponse(String response) throws Exception {
		SyncContext syncContext = JSONUtil.readValue(
			response, SyncContext.class);

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			getSyncAccountId());

		SyncUser remoteSyncUser = syncContext.getSyncUser();

		if (remoteSyncUser == null) {
			throw new HttpResponseException(
				HttpStatus.SC_UNAUTHORIZED, "Authenticated access required");
		}

		SyncUser localSyncUser = SyncUserService.fetchSyncUser(
			syncAccount.getSyncAccountId());

		if (localSyncUser == null) {
			remoteSyncUser.setSyncAccountId(getSyncAccountId());

			localSyncUser = SyncUserService.update(remoteSyncUser);
		}

		if ((localSyncUser.getUserId() > 0) &&
			(localSyncUser.getUserId() != remoteSyncUser.getUserId())) {

			syncAccount.setState(SyncAccount.STATE_DISCONNECTED);
			syncAccount.setUiEvent(
				SyncAccount.UI_EVENT_AUTHENTICATION_EXCEPTION);

			SyncAccountService.update(syncAccount);

			return syncContext;
		}

		if (!syncAccount.isOAuthEnabled()) {
			String login = syncAccount.getLogin();

			if (login == null) {
				login = "";
			}

			String authType = syncContext.getAuthType();

			if (authType.equals(SyncContext.AUTH_TYPE_EMAIL_ADDRESS)) {
				if (!login.equals(remoteSyncUser.getEmailAddress())) {
					syncAccount.setLogin(remoteSyncUser.getEmailAddress());
				}
			}
			else if (authType.equals(SyncContext.AUTH_TYPE_SCREEN_NAME)) {
				if (!login.equals(remoteSyncUser.getScreenName())) {
					syncAccount.setLogin(remoteSyncUser.getScreenName());
				}
			}
			else if (authType.equals(SyncContext.AUTH_TYPE_USER_ID)) {
				if (!login.equals(String.valueOf(remoteSyncUser.getUserId()))) {
					syncAccount.setLogin(
						String.valueOf(remoteSyncUser.getUserId()));
				}
			}
		}

		remoteSyncUser.setSyncAccountId(localSyncUser.getSyncAccountId());
		remoteSyncUser.setSyncUserId(localSyncUser.getSyncUserId());

		SyncUserService.update(remoteSyncUser);

		Map<String, String> portletPreferencesMap =
			syncContext.getPortletPreferencesMap();

		int authenticationRetryInterval = GetterUtil.getInteger(
			portletPreferencesMap.get(
				SyncContext.PREFERENCE_KEY_AUTHENTICATION_RETRY_INTERVAL),
			60);

		syncAccount.setAuthenticationRetryInterval(authenticationRetryInterval);

		int batchFileMaxSize = GetterUtil.getInteger(
			portletPreferencesMap.get(
				SyncContext.PREFERENCE_KEY_BATCH_FILE_MAX_SIZE));

		syncAccount.setBatchFileMaxSize(batchFileMaxSize);

		syncAccount.setLanCertificate(syncContext.getLanCertificate());
		syncAccount.setLanEnabled(syncContext.getLanEnabled());
		syncAccount.setLanKey(syncContext.getLanKey());
		syncAccount.setLanServerUuid(syncContext.getLanServerUuid());

		int maxConnections = GetterUtil.getInteger(
			portletPreferencesMap.get(
				SyncContext.PREFERENCE_KEY_MAX_CONNECTIONS),
			1);

		syncAccount.setMaxConnections(maxConnections);

		int maxDownloadRate = GetterUtil.getInteger(
			portletPreferencesMap.get(
				SyncContext.PREFERENCE_KEY_MAX_DOWNLOAD_RATE));

		syncAccount.setMaxDownloadRate(maxDownloadRate);

		int maxUploadRate = GetterUtil.getInteger(
			portletPreferencesMap.get(
				SyncContext.PREFERENCE_KEY_MAX_UPLOAD_RATE));

		syncAccount.setMaxUploadRate(maxUploadRate);

		syncAccount.setPluginVersion(syncContext.getPluginVersion());

		int pollInterval = GetterUtil.getInteger(
			portletPreferencesMap.get(SyncContext.PREFERENCE_KEY_POLL_INTERVAL),
			5);

		syncAccount.setPollInterval(pollInterval);

		syncAccount.setSocialOfficeInstalled(
			syncContext.isSocialOfficeInstalled());

		SyncAccountService.update(syncAccount);

		return syncContext;
	}

	@Override
	protected void logResponse(String response) {
		super.logResponse("");
	}

}