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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.listener;

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
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
@Component(immediate = true, service = ModelListener.class)
public class AccountCustomerModelListener
	extends BaseModelListener<AccountCustomer> {

	@Override
	public void onAfterCreate(AccountCustomer accountCustomer)
		throws ModelListenerException {

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
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountCustomer.getAccountEntryId());
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				accountCustomer.getUserId());

			JSONArray organizationIdsJSONArray =
				JSONFactoryUtil.createJSONArray();

			organizationIdsJSONArray.put(Long.valueOf(zendeskOrganizationId));

			jsonObject.put("organization_ids", organizationIdsJSONArray);

			jsonObject.put("user_id", zendeskUserId);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.membership.bulk.delete",
				jsonObject);

			Set<String> tags = getDeleteAccountCustomerTags(
				accountCustomer, zendeskOrganizationId);

			jsonObject = ZendeskModelListenerUtil.getTagsJSONObject(
				tags, "users", zendeskUserId);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.tag.delete", jsonObject);
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

			JSONObject addTagsJSONObject =
				ZendeskModelListenerUtil.getTagsJSONObject(
					addTags, "users", zendeskUserId);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.tag.add", addTagsJSONObject);

			JSONObject removeTagsJSONObject =
				ZendeskModelListenerUtil.getTagsJSONObject(
					removeTags, "users", zendeskUserId);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.tag.delete", removeTagsJSONObject);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
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
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}