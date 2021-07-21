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

package com.liferay.osb.customer.admin.internal.servlet;

import com.liferay.osb.customer.admin.constants.AccountAttachmentConstants;
import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountAttachmentLocalService;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.File;
import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.osb.customer.admin.internal.servlet.AccountAttachmentServlet",
		"osgi.http.whiteboard.servlet.pattern=/account-attachment"
	},
	service = Servlet.class
)
public class AccountAttachmentServlet extends HttpServlet {

	@Activate
	protected void activate() {
		_portalCache =
			(PortalCache<TokenKey, String>)_multiVMPool.getPortalCache(
				CACHE_NAME);
	}

	protected void checkPermission(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		_expungeExpiredTokens(System.currentTimeMillis());

		String token = ParamUtil.getString(httpServletRequest, "apiToken");

		TokenKey key = new TokenKey(token);

		String cacheToken = _portalCache.get(key);

		if (Validator.isNull(token) || (cacheToken == null)) {
			throw new PrincipalException();
		}

		_portalCache.remove(key);
	}

	@Deactivate
	protected void deactivate() {
		_multiVMPool.removePortalCache(CACHE_NAME);
	}

	@Override
	protected void doGet(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			String cmd = ParamUtil.getString(httpServletRequest, Constants.CMD);

			if (cmd.equals("token")) {
				sendToken(httpServletRequest, httpServletResponse);
			}
			else {
				sendAccountAttachment(httpServletRequest, httpServletResponse);
			}
		}
		catch (PrincipalException pe) {
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			httpServletResponse.setStatus(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	protected void doOptions(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader(
			"Access-Control-Allow-Methods", "GET, POST");

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doPost(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			UploadServletRequest uploadServletRequest =
				_portal.getUploadServletRequest(httpServletRequest);

			checkPermission(uploadServletRequest, httpServletResponse);

			String koroneikiAccountKey = ParamUtil.getString(
				uploadServletRequest, "koroneikiAccountKey");

			AccountEntry accountEntry =
				_accountEntryLocalService.getKoroneikiAccountEntry(
					koroneikiAccountKey);

			File file = uploadServletRequest.getFile("oemInstructions");

			if ((file == null) || (file.length() <= 0)) {
				return;
			}

			List<AccountAttachment> accountAttachments =
				_accountAttachmentLocalService.getAccountAttachments(
					accountEntry.getAccountEntryId(), 0,
					AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

			for (AccountAttachment accountAttachment : accountAttachments) {
				_accountAttachmentLocalService.deleteAccountAttachment(
					accountAttachment.getAccountAttachmentId());
			}

			String fileName = uploadServletRequest.getFileName(
				"oemInstructions");

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				fileName, file);

			_accountAttachmentLocalService.addAccountAttachment(
				OSBCustomerConstants.USER_DEFAULT_USER_ID,
				accountEntry.getAccountEntryId(), 0, ovp,
				AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

			String redirect = ParamUtil.getString(
				uploadServletRequest, "redirect");

			httpServletResponse.sendRedirect(redirect);
		}
		catch (PrincipalException pe) {
			httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			httpServletResponse.setStatus(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	protected void sendAccountAttachment(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		checkPermission(httpServletRequest, httpServletResponse);

		long accountAttachmentId = ParamUtil.getLong(
			httpServletRequest, "accountAttachmentId");

		AccountAttachment accountAttachment =
			_accountAttachmentLocalService.getAccountAttachment(
				accountAttachmentId);

		ServletResponseUtil.sendFile(
			httpServletRequest, httpServletResponse,
			accountAttachment.getFileName(),
			_accountAttachmentLocalService.getFileAsStream(accountAttachment),
			accountAttachment.getContentLength(),
			MimeTypesUtil.getContentType(accountAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void sendToken(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		long userId = GetterUtil.getLong(PrincipalThreadLocal.getName());

		if (!_roleLocalService.hasUserRole(
				userId, OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}

		long currentTime = System.currentTimeMillis();

		long expireTime = currentTime + Time.HOUR;

		_expungeExpiredTokens(currentTime);

		String token = PortalUUIDUtil.generate();

		_portalCache.put(new TokenKey(token), token);

		while (_tokens.putIfAbsent(expireTime, token) != null) {
			expireTime++;
		}

		ServletResponseUtil.write(httpServletResponse, token);
	}

	protected static final String CACHE_NAME =
		AccountAttachmentServlet.class.getName();

	private void _expungeExpiredTokens(long currentTime) {
		SortedMap<Long, String> headMap = _tokens.headMap(currentTime);

		for (Map.Entry<Long, String> token : headMap.entrySet()) {
			_portalCache.remove(new TokenKey(token.getValue()));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountAttachmentServlet.class);

	private static PortalCache<TokenKey, String> _portalCache;
	private static final ConcurrentNavigableMap<Long, String> _tokens =
		new ConcurrentSkipListMap<>();

	@Reference
	private AccountAttachmentLocalService _accountAttachmentLocalService;

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private MultiVMPool _multiVMPool;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

	private static class TokenKey implements Serializable {

		@Override
		public boolean equals(Object obj) {
			TokenKey tokenKey = (TokenKey)obj;

			if (Objects.equals(tokenKey._token, _token)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return HashUtil.hash(0, _token);
		}

		private TokenKey(String token) {
			_token = token;
		}

		private final String _token;

	}

}