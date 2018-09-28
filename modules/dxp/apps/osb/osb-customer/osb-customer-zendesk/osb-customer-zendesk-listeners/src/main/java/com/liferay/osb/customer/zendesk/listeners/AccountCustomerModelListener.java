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

package com.liferay.osb.customer.zendesk.listeners;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.listeners.util.AccountCustomerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountCustomerModelListener
	extends BaseModelListener<AccountCustomer> {

	@Override
	public void onAfterCreate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			_accountCustomerUtil.addAccountCustomer(accountCustomer);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			_zendeskUserWebService.deleteZendeskUserOrganizationMemberships(
				zendeskUserId, new long[] {zendeskOrganizationId});

			Set<String> tags = getDeleteAccountCustomerTags(
				accountCustomer, zendeskOrganizationId);

			_zendeskUserWebService.deleteZendeskUserTags(zendeskUserId, tags);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			Set<String> addTags = new HashSet<>();
			Set<String> removeTags = new HashSet<>();

			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				addTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

				if (AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
						accountCustomer.getUserId()) == 1) {

					removeTags.add(ZendeskTagConstants.OSB_CUSTOMER);
				}
			}
			else {
				addTags.add(ZendeskTagConstants.OSB_CUSTOMER);

				if (AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
						accountCustomer.getUserId())) {

					addTags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}

				removeTags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}

			_zendeskUserWebService.addZendeskUserTags(zendeskUserId, addTags);

			_zendeskUserWebService.deleteZendeskUserTags(
				zendeskUserId, removeTags);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected Set<String> getDeleteAccountCustomerTags(
		AccountCustomer accountCustomer, long zendeskOrganizationId) {

		Set<String> tags = new HashSet<>();

		int accountEntriesCount =
			AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
				accountCustomer.getUserId());

		if (accountEntriesCount == 0) {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				tags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}
			else {
				if (!AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
						accountCustomer.getUserId())) {

					tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}
			}
		}

		return tags;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private AccountCustomerUtil _accountCustomerUtil;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}