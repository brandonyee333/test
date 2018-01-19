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

package com.liferay.pulpo.connector.de.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ConnectorTransaction service. Represents a row in the &quot;PULPO_ConnectorTransaction&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionModel
 * @see com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionImpl
 * @see com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionImpl")
@ProviderType
public interface ConnectorTransaction extends ConnectorTransactionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.pulpo.connector.de.model.impl.ConnectorTransactionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ConnectorTransaction, Long> CONNECTOR_TRANSACTION_ID_ACCESSOR =
		new Accessor<ConnectorTransaction, Long>() {
			@Override
			public Long get(ConnectorTransaction connectorTransaction) {
				return connectorTransaction.getConnectorTransactionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ConnectorTransaction> getTypeClass() {
				return ConnectorTransaction.class;
			}
		};
}