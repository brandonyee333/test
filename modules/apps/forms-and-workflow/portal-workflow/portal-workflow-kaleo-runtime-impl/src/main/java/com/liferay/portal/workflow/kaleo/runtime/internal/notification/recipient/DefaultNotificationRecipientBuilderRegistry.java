/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.notification.recipient;

import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.definition.RecipientType;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilder;
import com.liferay.portal.workflow.kaleo.runtime.notification.recipient.NotificationRecipientBuilderRegistry;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, service = NotificationRecipientBuilderRegistry.class
)
public class DefaultNotificationRecipientBuilderRegistry
	implements NotificationRecipientBuilderRegistry {

	@Override
	public NotificationRecipientBuilder getNotificationRecipientBuilder(
		RecipientType recipientType) {

		NotificationRecipientBuilder notificationRecipientBuilder =
			_notificationRecipientBuilders.get(recipientType);

		if (notificationRecipientBuilder == null) {
			throw new IllegalArgumentException(
				"No notification recipient builder for " + recipientType);
		}

		return notificationRecipientBuilder;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addNotificationRecipientBuilder(
		NotificationRecipientBuilder notificationRecipientBuilder,
		Map<String, Object> properties) {

		RecipientType recipientType = _getRecipientType(
			notificationRecipientBuilder, properties);

		_notificationRecipientBuilders.put(
			recipientType, notificationRecipientBuilder);
	}

	protected void removeNotificationRecipientBuilder(
		NotificationRecipientBuilder notificationRecipientBuilder,
		Map<String, Object> properties) {

		RecipientType recipientType = _getRecipientType(
			notificationRecipientBuilder, properties);

		_notificationRecipientBuilders.remove(recipientType);
	}

	private RecipientType _getRecipientType(
		NotificationRecipientBuilder notificationRecipientBuilder,
		Map<String, Object> properties) {

		String value = (String)properties.get("recipient.type");

		if (Validator.isNull(value)) {
			throw new IllegalArgumentException(
				"The property \"recipient.type\" is invalid for " +
					ClassUtil.getClassName(notificationRecipientBuilder));
		}

		return RecipientType.valueOf(value);
	}

	private final Map<RecipientType, NotificationRecipientBuilder>
		_notificationRecipientBuilders = new HashMap<>();

}