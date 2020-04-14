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

package com.liferay.osb.email.blacklist.internal.email.service;

import com.liferay.mail.kernel.model.Filter;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.email.blacklist.internal.util.EmailBlacklistEntryThreadLocal;
import com.liferay.osb.email.blacklist.model.BlacklistEntry;
import com.liferay.osb.email.blacklist.service.BlacklistEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Iterator;
import java.util.List;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Haote Chou
 * @author Jamie Sammons
 */
@Component(
	immediate = true,
	service = {BlacklistEmailServiceImpl.class, MailService.class}
)
public class BlacklistEmailServiceImpl implements MailService {

	@Override
	public void addForward(
		long companyId, long userId, List<Filter> filters,
		List<String> emailAddresses, boolean leaveCopy) {

		_originalMailService.addForward(
			companyId, userId, filters, emailAddresses, leaveCopy);
	}

	@Override
	public void addUser(
		long companyId, long userId, String password, String firstName,
		String middleName, String lastName, String emailAddress) {

		_originalMailService.addUser(
			companyId, userId, password, firstName, middleName, lastName,
			emailAddress);
	}

	@Override
	public void addVacationMessage(
		long companyId, long userId, String emailAddress,
		String vacationMessage) {

		_originalMailService.addVacationMessage(
			companyId, userId, emailAddress, vacationMessage);
	}

	@Override
	public void clearSession() {
		_originalMailService.clearSession();
	}

	@Override
	public void deleteEmailAddress(long companyId, long userId) {
		_originalMailService.deleteEmailAddress(companyId, userId);
	}

	@Override
	public void deleteUser(long companyId, long userId) {
		_originalMailService.deleteUser(companyId, userId);
	}

	public MailService getOriginalMailService() {
		return _originalMailService;
	}

	@Override
	public Session getSession() {
		return _originalMailService.getSession();
	}

	@Override
	public void sendEmail(MailMessage mailMessage) {
		if (EmailBlacklistEntryThreadLocal.isVerify()) {
			InternetAddress[] to = _filterInternetAddresses(
				mailMessage.getTo());

			mailMessage.setTo(to);

			InternetAddress[] cc = _filterInternetAddresses(
				mailMessage.getCC());

			mailMessage.setCC(cc);

			InternetAddress[] bcc = _filterInternetAddresses(
				mailMessage.getBCC());

			mailMessage.setBCC(bcc);

			InternetAddress[] bulkAddresses = _filterInternetAddresses(
				mailMessage.getBulkAddresses());

			mailMessage.setBulkAddresses(bulkAddresses);

			if (ArrayUtil.isEmpty(to) && ArrayUtil.isEmpty(cc) &&
				ArrayUtil.isEmpty(bcc) && ArrayUtil.isEmpty(bulkAddresses)) {

				return;
			}
		}

		_originalMailService.sendEmail(mailMessage);
	}

	@Override
	public void updateBlocked(
		long companyId, long userId, List<String> blocked) {

		_originalMailService.updateBlocked(companyId, userId, blocked);
	}

	@Override
	public void updateEmailAddress(
		long companyId, long userId, String emailAddress) {

		_originalMailService.updateEmailAddress(
			companyId, userId, emailAddress);
	}

	@Override
	public void updatePassword(long companyId, long userId, String password) {
		_originalMailService.updatePassword(companyId, userId, password);
	}

	private InternetAddress[] _filterInternetAddresses(
		InternetAddress[] internetAddresses) {

		if (internetAddresses == null) {
			return null;
		}

		List<InternetAddress> filteredInternetAddresses = ListUtil.fromArray(
			internetAddresses);

		Iterator<InternetAddress> itr = filteredInternetAddresses.iterator();

		while (itr.hasNext()) {
			InternetAddress internetAddress = itr.next();

			try {
				BlacklistEntry blacklistEntry =
					_blacklistEntryLocalService.fetchBlacklistEntry(
						internetAddress.getAddress());

				if (blacklistEntry != null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Removing blacklisted email address " +
								internetAddress.getAddress());
					}

					itr.remove();
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return filteredInternetAddresses.toArray(new InternetAddress[0]);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlacklistEmailServiceImpl.class);

	@Reference
	private BlacklistEntryLocalService _blacklistEntryLocalService;

	@Reference(target = "(original.bean=true)")
	private MailService _originalMailService;

}