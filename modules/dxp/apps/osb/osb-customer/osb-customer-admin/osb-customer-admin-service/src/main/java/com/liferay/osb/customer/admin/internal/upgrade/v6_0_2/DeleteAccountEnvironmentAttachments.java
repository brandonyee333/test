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

package com.liferay.osb.customer.admin.internal.upgrade.v6_0_2;

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.osb.customer.admin.service.AccountEnvironmentAttachmentLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(service = DeleteAccountEnvironmentAttachments.class)
public class DeleteAccountEnvironmentAttachments extends UpgradeProcess {

	protected void deleteAccountEnvironmentAttachments() throws Exception {
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments =
			_accountEnvironmentAttachmentLocalService.
				getAccountEnvironmentAttachments(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEnvironmentAttachment accountEnvironmentAttachment :
				accountEnvironmentAttachments) {

			_accountEnvironmentAttachmentLocalService.
				deleteAccountEnvironmentAttachment(
					accountEnvironmentAttachment);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		deleteAccountEnvironmentAttachments();
	}

	@Reference
	private AccountEnvironmentAttachmentLocalService
		_accountEnvironmentAttachmentLocalService;

}