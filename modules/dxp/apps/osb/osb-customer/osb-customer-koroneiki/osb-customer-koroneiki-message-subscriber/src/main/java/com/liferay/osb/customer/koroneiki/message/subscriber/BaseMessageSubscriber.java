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

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected abstract void doReceive(Message message) throws Exception;

	protected long getCorpProjectId(ExternalLink[] externalLinks) {
		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				String domain = externalLink.getDomain();

				if (domain.equals(ExternalLinkDomain.LCS)) {
					String entityName = externalLink.getEntityName();

					if (entityName.equals(
							ExternalLinkEntityName.LCS_CORP_PROJECT)) {

						return Long.valueOf(externalLink.getEntityId());
					}
				}
			}
		}

		return 0;
	}

	protected String getCorpProjectUuid(ExternalLink[] externalLinks) {
		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				String domain = externalLink.getDomain();

				if (domain.equals(ExternalLinkDomain.WEB)) {
					String entityName = externalLink.getEntityName();

					if (entityName.equals(
							ExternalLinkEntityName.WEB_CORP_PROJECT)) {

						return externalLink.getEntityId();
					}
				}
			}
		}

		return StringPool.BLANK;
	}

	protected String getDossieraAccountKey(ExternalLink[] externalLinks) {
		if (externalLinks != null) {
			for (ExternalLink externalLink : externalLinks) {
				String domain = externalLink.getDomain();

				if (domain.equals(ExternalLinkDomain.DOSSIERA)) {
					String entityName = externalLink.getEntityName();

					if (entityName.equals(
							ExternalLinkEntityName.DOSSIERA_ACCOUNT)) {

						return externalLink.getEntityId();
					}
				}
			}
		}

		return StringPool.BLANK;
	}

	protected List<ProductPurchase> getProductPurchases(String accountKey)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(accountKey);
		sb.append("' and state eq 'active'");

		return productPurchaseWebService.search(sb.toString(), 1, 1000);
	}

	protected int getStatus(String label) {
		String statusLabel = StringUtil.toLowerCase(
			StringUtil.replace(label, CharPool.SPACE, CharPool.DASH));

		return WorkflowConstants.getLabelStatus(statusLabel);
	}

	protected boolean isSyncAccount(Account account) throws Exception {
		Date now = new Date();

		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			if (isSyncProduct(product)) {
				if (productPurchase.getPerpetual()) {
					return true;
				}

				Date endDate = productPurchase.getEndDate();

				if (now.before(endDate)) {
					return true;
				}
			}
		}

		return false;
	}

	protected boolean isSyncProduct(Product product) {
		String name = product.getName();

		if (name.equals(ProductConstants.NAME_GOLD) ||
			name.equals(ProductConstants.NAME_LIMITED) ||
			name.equals(ProductConstants.NAME_PLATINUM) ||
			name.equals(ProductConstants.NAME_SILVER)) {

			return true;
		}

		if (name.startsWith("Digital Enterprise") ||
			name.startsWith("Liferay Commerce") || name.startsWith("Portal")) {

			return true;
		}

		if (name.contains("DXP Cloud")) {
			return true;
		}

		return false;
	}

	protected void sendMessage(
		String destination, String topic, String payload) {

		com.liferay.portal.kernel.messaging.Message message =
			new com.liferay.portal.kernel.messaging.Message();

		message.put("topic", topic);
		message.setPayload(payload);

		MessageBusUtil.sendMessage(destination, message);
	}

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected ProductPurchaseWebService productPurchaseWebService;

	@Reference(target = "(provider=web)")
	protected UserIdentityProvider userIdentityProvider;

	@Reference
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}