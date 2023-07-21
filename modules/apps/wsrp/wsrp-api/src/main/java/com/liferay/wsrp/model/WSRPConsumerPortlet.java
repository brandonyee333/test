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
 * The extended model interface for the WSRPConsumerPortlet service. Represents a row in the &quot;WSRP_WSRPConsumerPortlet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletModel
 * @generated
 */
@ImplementationClassName("com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl")
@ProviderType
public interface WSRPConsumerPortlet
	extends PersistedModel, WSRPConsumerPortletModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WSRPConsumerPortlet, Long>
		WSRP_CONSUMER_PORTLET_ID_ACCESSOR =
			new Accessor<WSRPConsumerPortlet, Long>() {

				@Override
				public Long get(WSRPConsumerPortlet wsrpConsumerPortlet) {
					return wsrpConsumerPortlet.getWsrpConsumerPortletId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<WSRPConsumerPortlet> getTypeClass() {
					return WSRPConsumerPortlet.class;
				}

			};

}