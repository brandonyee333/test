/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WSRPConsumer service. Represents a row in the &quot;WSRP_WSRPConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerModel
 * @generated
 */
@ImplementationClassName("com.liferay.wsrp.model.impl.WSRPConsumerImpl")
@ProviderType
public interface WSRPConsumer extends PersistedModel, WSRPConsumerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.wsrp.model.impl.WSRPConsumerImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPConsumer, Long> WSRP_CONSUMER_ID_ACCESSOR =
		new Accessor<WSRPConsumer, Long>() {

			@Override
			public Long get(WSRPConsumer wsrpConsumer) {
				return wsrpConsumer.getWsrpConsumerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WSRPConsumer> getTypeClass() {
				return WSRPConsumer.class;
			}

		};

	public oasis.names.tc.wsrp.v2.types.RegistrationContext
		getRegistrationContext();

	public com.liferay.portal.kernel.util.UnicodeProperties
		getRegistrationProperties();

	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext);

	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties
			registrationProperties);

}