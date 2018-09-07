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
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
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
public class PartnerWorkerModelListener
	extends BaseModelListener<PartnerWorker> {

	@Override
	public void onAfterCreate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				addPartnerWorker(partnerWorker);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				removePartnerWorker(partnerWorker);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(PartnerWorker partnerWorker)
		throws ModelListenerException {

		try {
			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				addPartnerWorker(partnerWorker);
			}
			else {
				removePartnerWorker(partnerWorker);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected void addPartnerWorker(PartnerWorker partnerWorker)
		throws PortalException {

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

			tagsJSONArray.put(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tagsJSONArray.put(ZendeskTagConstants.OSB_PARTNER);

			String zendeskUserId = ZendeskModelListenerUtil.getExternalId(
				User.class, partnerWorker.getUserId());

			if (Validator.isNull(zendeskUserId)) {
				User user = UserLocalServiceUtil.getUser(
					partnerWorker.getUserId());

				ZendeskUser zendeskUser =
					ZendeskModelListenerUtil.getZendeskUser(
						null, tagsJSONArray, user);

				jsonObject.put("userObject", zendeskUser.toJSONObject());
			}
			else {
				JSONObject tagsJSONObject =
					ZendeskModelListenerUtil.getTagsJSONObject(
						tagsJSONArray, "users", Long.valueOf(zendeskUserId));

				_messagePublisher.sendMessage(
					ZendeskConnectorConfigurationValues.
						RABBITMQ_MESSAGE_EXCHANGE_NAME,
					"zendesk.service.tag.add", tagsJSONObject);

				jsonObject.put("user_id", Long.valueOf(zendeskUserId));
			}

			jsonObject.put(
				"organizationsArray", getOrganizationsJSONArray(partnerWorker));

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service.organization.membership.bulk.create",
				jsonObject);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	protected JSONArray getOrganizationsJSONArray(PartnerWorker partnerWorker)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

		for (AccountEntry accountEntry : partnerEntry.getAccountEntries()) {
			String zendeskOrganizationId =
				ZendeskModelListenerUtil.getExternalId(
					AccountEntry.class, accountEntry.getAccountEntryId());

			if (Validator.isNotNull(zendeskOrganizationId)) {
				jsonArray.put(Long.valueOf(zendeskOrganizationId));
			}
		}

		return jsonArray;
	}

	protected void removePartnerWorker(PartnerWorker partnerWorker)
		throws PortalException {

		try {
			JSONArray organizationIdsJSONArray =
				JSONFactoryUtil.createJSONArray();

			PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

			for (AccountEntry accountEntry : partnerEntry.getAccountEntries()) {
				String zendeskOrganizationId =
					ZendeskModelListenerUtil.getExternalId(
						AccountEntry.class, accountEntry.getAccountEntryId());

				if (Validator.isNotNull(zendeskOrganizationId)) {
					organizationIdsJSONArray.put(
						Long.valueOf(zendeskOrganizationId));
				}
			}

			String zendeskUserId = ZendeskModelListenerUtil.getExternalId(
				User.class, partnerWorker.getUserId());

			if (organizationIdsJSONArray.length() > 0) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("organization_ids", organizationIdsJSONArray);

				jsonObject.put("user_id", zendeskUserId);

				_messagePublisher.sendMessage(
					ZendeskConnectorConfigurationValues.
						RABBITMQ_MESSAGE_EXCHANGE_NAME,
					"zendesk.service.organization.membership.bulk.delete",
					jsonObject);
			}

			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
					partnerWorker.getUserId());

			if (partnerWorkers.isEmpty() ||
				(partnerWorker.getRole() ==
					PartnerWorkerConstants.ROLE_WATCHER)) {

				JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

				if (AccountEntryLocalServiceUtil.getUserAccountEntriesCount(
						partnerWorker.getUserId()) == 0) {

					jsonArray.put(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
				}

				jsonArray.put(ZendeskTagConstants.OSB_PARTNER);

				JSONObject jsonObject =
					ZendeskModelListenerUtil.getTagsJSONObject(
						jsonArray, "users", Long.valueOf(zendeskUserId));

				_messagePublisher.sendMessage(
					ZendeskConnectorConfigurationValues.
						RABBITMQ_MESSAGE_EXCHANGE_NAME,
					"zendesk.service.tag.delete", jsonObject);
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
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