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
import com.liferay.osb.customer.zendesk.connector.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.util.ZendeskModelListenerUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

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

			String zendeskUserId = ZendeskModelListenerUtil.getExternalId(
				User.class, accountCustomer.getUserId());

			ZendeskUser zendeskUser = null;

			JSONArray tagsJSONArray = getAddAccountCustomerTags(
				accountCustomer);

			if (Validator.isNotNull(zendeskUserId)) {
				zendeskUser = getZendeskUser(accountCustomer, null, user);
			}
			else {
				zendeskUser = getZendeskUser(
					accountCustomer, tagsJSONArray, user);
			}

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.user.create.or.update",
				zendeskUser.toJSONObject());

			if (Validator.isNotNull(zendeskUserId)) {
				JSONObject jsonObject =
					ZendeskModelListenerUtil.getTagsJSONObject(
						tagsJSONArray, "users", Long.valueOf(zendeskUserId));

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

			String zendeskOrganizationId =
				ZendeskModelListenerUtil.getExternalId(
					AccountEntry.class, accountCustomer.getAccountEntryId());
			String zendeskUserId = ZendeskModelListenerUtil.getExternalId(
				User.class, accountCustomer.getUserId());

			jsonObject.put(
				"organization_id", Long.valueOf(zendeskOrganizationId));

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.membership.delete", jsonObject);

			JSONArray jsonArray = getDeleteAccountCustomerTags(
				accountCustomer, zendeskOrganizationId);

			jsonObject = ZendeskModelListenerUtil.getTagsJSONObject(
				jsonArray, "users", Long.valueOf(zendeskUserId));

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
			String zendeskOrganizationId =
				ZendeskModelListenerUtil.getExternalId(
					AccountEntry.class, accountCustomer.getAccountEntryId());
			String zendeskUserId = ZendeskModelListenerUtil.getExternalId(
				User.class, accountCustomer.getUserId());

			JSONArray addTagsJSONArray = JSONFactoryUtil.createJSONArray();
			JSONArray removeTagsJSONArray = JSONFactoryUtil.createJSONArray();

			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				addTagsJSONArray.put(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));

				int accountEntryCount =
					AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
						accountCustomer.getUserId());

				if ((accountEntryCount == 1) ||
					!ZendeskModelListenerUtil.hasActiveSupportOffering(
						accountCustomer)) {

					removeTagsJSONArray.put(ZendeskTagConstants.OSB_CUSTOMER);
				}
			}
			else {
				if (ZendeskModelListenerUtil.hasActiveSupportOffering(
						accountCustomer.getAccountEntry())) {

					addTagsJSONArray.put(ZendeskTagConstants.OSB_CUSTOMER);
				}

				removeTagsJSONArray.put(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}

			JSONObject addTagsJSONObject =
				ZendeskModelListenerUtil.getTagsJSONObject(
					addTagsJSONArray, "users", Long.valueOf(zendeskUserId));

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.tag.add", addTagsJSONObject);

			JSONObject removeTagsJSONObject =
				ZendeskModelListenerUtil.getTagsJSONObject(
					removeTagsJSONArray, "users", Long.valueOf(zendeskUserId));

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.tag.delete", removeTagsJSONObject);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected JSONArray getAddAccountCustomerTags(
		AccountCustomer accountCustomer) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_WATCHER) {

			String zendeskOrganizationId =
				ZendeskModelListenerUtil.getExternalId(
					AccountEntry.class, accountCustomer.getAccountEntryId());

			jsonArray.put(
				ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			if (ZendeskModelListenerUtil.hasActiveSupportOffering(
					accountCustomer)) {

				jsonArray.put(ZendeskTagConstants.OSB_CUSTOMER);
			}
		}

		jsonArray.put(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

		return jsonArray;
	}

	protected JSONArray getDeleteAccountCustomerTags(
		AccountCustomer accountCustomer, String zendeskOrganizationId) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getUserAccountEntries(
				accountCustomer.getUserId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		if (accountEntries.isEmpty()) {
			jsonArray.put(ZendeskTagConstants.OSB_CUSTOMER);
			jsonArray.put(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			jsonArray.put(
				ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
		}
		else {
			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				jsonArray.put(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}
			else {
				for (int i = 0; i < accountEntries.size(); i++) {
					AccountEntry accountEntry = accountEntries.get(i);

					if (accountEntry.hasActiveSupportOffering()) {
						break;
					}

					if (i == (accountEntries.size() - 1)) {
						jsonArray.put(ZendeskTagConstants.OSB_CUSTOMER);
					}
				}
			}
		}

		return jsonArray;
	}

	protected ZendeskUser getZendeskUser(
			AccountCustomer accountCustomer, JSONArray tags, User user)
		throws PortalException {

		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmailAddress(user.getEmailAddress());
		zendeskUser.setExternalId(user.getUuid());

		String locale = ZendeskModelListenerUtil.convertToZendeskLocale(
			user.getLanguageId());

		zendeskUser.setLocale(locale);

		zendeskUser.setName(user.getFullName());

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(
				accountCustomer.getAccountEntryId());

		zendeskUser.setOrganizationName(accountEntry.getName());

		if (tags != null) {
			zendeskUser.setTags(tags);
		}

		return zendeskUser;
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

}