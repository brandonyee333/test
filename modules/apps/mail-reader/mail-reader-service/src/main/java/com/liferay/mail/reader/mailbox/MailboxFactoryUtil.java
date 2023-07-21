/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.mailbox;

import com.liferay.mail.reader.constants.MailPortletKeys;
import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.service.AccountLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Scott Lee
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + MailPortletKeys.MAIL,
	service = MailboxFactoryUtil.class
)
public class MailboxFactoryUtil {

	public static Mailbox getMailbox(
			long userId, long accountId, String password)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		Account account = _accountLocalService.getAccount(accountId);

		MailboxFactory mailboxFactory = _mailboxFactories.get(
			account.getProtocol());

		if (mailboxFactory == null) {
			throw new IllegalArgumentException(
				"Invalid protocol " + account.getProtocol());
		}

		return mailboxFactory.getMailbox(user, account, password);
	}

	public static Mailbox getMailbox(long userId, String protocol)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		MailboxFactory mailboxFactory = _mailboxFactories.get(protocol);

		if (mailboxFactory == null) {
			throw new IllegalArgumentException("Invalid protocol " + protocol);
		}

		return mailboxFactory.getMailbox(user, protocol);
	}

	public static List<MailboxFactory> getMailboxFactories() {
		Set<String> protocols = _mailboxFactories.keySet();

		List<MailboxFactory> mailboxFactories = new ArrayList<>();

		for (String protocol : protocols) {
			mailboxFactories.add(_mailboxFactories.get(protocol));
		}

		return mailboxFactories;
	}

	public void setMailboxFactories(
		Map<String, MailboxFactory> mailboxFactories) {

		_mailboxFactories = mailboxFactories;
	}

	@Reference(unbind = "-")
	protected void setAccountLocalService(
		AccountLocalService accountLocalService) {

		_accountLocalService = accountLocalService;
	}

	@Reference(unbind = "-")
	protected void setMailboxFactory(MailboxFactory mailboxFactory) {
		_addMailboxFactory(
			mailboxFactory.getMailboxFactoryName(), mailboxFactory);
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private void _addMailboxFactory(
		String mailboxFactoryName, MailboxFactory mailboxFactory) {

		if (_mailboxFactories == null) {
			_mailboxFactories = new ConcurrentHashMap<>();
		}

		_mailboxFactories.put(mailboxFactoryName, mailboxFactory);
	}

	private static AccountLocalService _accountLocalService;
	private static Map<String, MailboxFactory> _mailboxFactories;
	private static UserLocalService _userLocalService;

}