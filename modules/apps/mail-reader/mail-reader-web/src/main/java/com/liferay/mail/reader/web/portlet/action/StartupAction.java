/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.web.portlet.action;

import com.liferay.mail.reader.mailbox.MailboxFactory;
import com.liferay.mail.reader.mailbox.MailboxFactoryUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Scott Lee
 * @author Peter Fellwock
 */
@Component(
	immediate = true, property = "key=application.startup.events",
	service = LifecycleAction.class
)
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			initializeMailboxFactories();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void initializeMailboxFactories() throws PortalException {
		List<MailboxFactory> mailboxFactories =
			MailboxFactoryUtil.getMailboxFactories();

		for (MailboxFactory mailboxFactory : mailboxFactories) {
			mailboxFactory.initialize();
		}
	}

	@Reference(unbind = "-")
	protected void setMailboxFactoryUtil(
		MailboxFactoryUtil mailboxFactoryUtil) {
	}

}