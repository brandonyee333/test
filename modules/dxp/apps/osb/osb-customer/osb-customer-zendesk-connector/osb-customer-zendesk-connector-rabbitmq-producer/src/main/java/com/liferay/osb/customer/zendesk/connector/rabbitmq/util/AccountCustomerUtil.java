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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.util;

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.HashSet;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountCustomerUtil.class)
public class AccountCustomerUtil {

	public void addAccountCustomer(AccountCustomer accountCustomer)
		throws PortalException {

		try {
			User user = UserLocalServiceUtil.getUser(
				accountCustomer.getUserId());

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			ZendeskUser zendeskUser = null;

			Set<String> tags = getAddAccountCustomerTags(accountCustomer);

			if (zendeskUserId != 0) {
				zendeskUser = ZendeskModelListenerUtil.getZendeskUser(
					accountCustomer.getAccountEntry(), null, user);
			}
			else {
				zendeskUser = ZendeskModelListenerUtil.getZendeskUser(
					accountCustomer.getAccountEntry(), tags, user);
			}

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.user.create.or.update",
				zendeskUser.toJSONObject());

			if (zendeskUserId != 0) {
				JSONObject jsonObject =
					ZendeskModelListenerUtil.getTagsJSONObject(
						tags, "users", zendeskUserId);

				_messagePublisher.sendMessage(
					ZendeskConnectorConfigurationValues.
						RABBITMQ_MESSAGE_EXCHANGE_NAME,
					"zendesk.service.tag.add", jsonObject);
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	protected Set<String> getAddAccountCustomerTags(
			AccountCustomer accountCustomer)
		throws PortalException {

		Set<String> tags = new HashSet<>();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());

			tags.add(ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
		}

		if (AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
				accountCustomer.getUserId())) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
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
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}