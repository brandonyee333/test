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
 * The extended model interface for the WSRPProducer service. Represents a row in the &quot;WSRP_WSRPProducer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerModel
 * @generated
 */
@ImplementationClassName("com.liferay.wsrp.model.impl.WSRPProducerImpl")
@ProviderType
public interface WSRPProducer extends PersistedModel, WSRPProducerModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.wsrp.model.impl.WSRPProducerImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPProducer, Long> WSRP_PRODUCER_ID_ACCESSOR =
		new Accessor<WSRPProducer, Long>() {

			@Override
			public Long get(WSRPProducer wsrpProducer) {
				return wsrpProducer.getWsrpProducerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WSRPProducer> getTypeClass() {
				return WSRPProducer.class;
			}

		};

	public String getURL(String prefixURL);

}