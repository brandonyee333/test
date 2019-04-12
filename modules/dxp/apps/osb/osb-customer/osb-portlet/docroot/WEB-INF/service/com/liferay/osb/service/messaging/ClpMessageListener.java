/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.messaging;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountAttachmentServiceUtil;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountCustomerServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLanguageServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentAttachmentServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentServiceUtil;
import com.liferay.osb.service.AccountInformationLocalServiceUtil;
import com.liferay.osb.service.AccountInformationServiceUtil;
import com.liferay.osb.service.AccountProjectLocalServiceUtil;
import com.liferay.osb.service.AccountProjectServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerServiceUtil;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.service.AuditEntryServiceUtil;
import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.CorpProjectMessageServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.service.LCSSubscriptionEntryServiceUtil;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseEntryServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetServiceUtil;
import com.liferay.osb.service.OfferingBundleLocalServiceUtil;
import com.liferay.osb.service.OfferingBundleServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryServiceUtil;
import com.liferay.osb.service.RabbitMQMessageProcessorLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpEntryLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectMessageLocalServiceUtil;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.service.SupportRegionServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.service.SupportResponseServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			AccountAttachmentLocalServiceUtil.clearService();

			AccountAttachmentServiceUtil.clearService();
			AccountCustomerLocalServiceUtil.clearService();

			AccountCustomerServiceUtil.clearService();
			AccountEntryLocalServiceUtil.clearService();

			AccountEntryServiceUtil.clearService();
			AccountEntryLanguageLocalServiceUtil.clearService();

			AccountEntryLanguageServiceUtil.clearService();
			AccountEnvironmentLocalServiceUtil.clearService();

			AccountEnvironmentServiceUtil.clearService();
			AccountEnvironmentAttachmentLocalServiceUtil.clearService();

			AccountEnvironmentAttachmentServiceUtil.clearService();
			AccountInformationLocalServiceUtil.clearService();

			AccountInformationServiceUtil.clearService();
			AccountProjectLocalServiceUtil.clearService();

			AccountProjectServiceUtil.clearService();
			AccountWorkerLocalServiceUtil.clearService();

			AccountWorkerServiceUtil.clearService();
			AuditEntryLocalServiceUtil.clearService();

			AuditEntryServiceUtil.clearService();
			CorpProjectLocalServiceUtil.clearService();

			CorpProjectServiceUtil.clearService();
			CorpProjectMessageLocalServiceUtil.clearService();

			CorpProjectMessageServiceUtil.clearService();
			ExternalIdMapperLocalServiceUtil.clearService();

			LCSSubscriptionEntryLocalServiceUtil.clearService();

			LCSSubscriptionEntryServiceUtil.clearService();
			LicenseEntryLocalServiceUtil.clearService();

			LicenseEntryServiceUtil.clearService();
			LicenseKeyLocalServiceUtil.clearService();

			LicenseKeyServiceUtil.clearService();
			LicenseKeySetLocalServiceUtil.clearService();

			LicenseKeySetServiceUtil.clearService();
			OfferingBundleLocalServiceUtil.clearService();

			OfferingBundleServiceUtil.clearService();
			OfferingDefinitionLocalServiceUtil.clearService();

			OfferingDefinitionServiceUtil.clearService();
			OfferingEntryLocalServiceUtil.clearService();

			OfferingEntryServiceUtil.clearService();
			OrderEntryLocalServiceUtil.clearService();

			OrderEntryServiceUtil.clearService();
			PartnerEntryLocalServiceUtil.clearService();

			PartnerEntryServiceUtil.clearService();
			PartnerWorkerLocalServiceUtil.clearService();

			PartnerWorkerServiceUtil.clearService();
			ProductEntryLocalServiceUtil.clearService();

			ProductEntryServiceUtil.clearService();
			RabbitMQMessageProcessorLocalServiceUtil.clearService();

			RemoteCorpEntryLocalServiceUtil.clearService();

			RemoteCorpProjectLocalServiceUtil.clearService();

			RemoteCorpProjectMessageLocalServiceUtil.clearService();

			RemoteUserLocalServiceUtil.clearService();

			SupportRegionLocalServiceUtil.clearService();

			SupportRegionServiceUtil.clearService();
			SupportResponseLocalServiceUtil.clearService();

			SupportResponseServiceUtil.clearService();
		}
	}
}