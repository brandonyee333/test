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

package com.liferay.pulpo.connector.de.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.base.ConnectorTransactionLocalServiceBaseImpl;

import java.util.Date;
import java.util.UUID;

/**
 * The implementation of the connector entity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionLocalServiceBaseImpl
 * @see com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalServiceUtil
 */
public class ConnectorTransactionLocalServiceImpl
	extends ConnectorTransactionLocalServiceBaseImpl {

	@Override
	public ConnectorTransaction addConnectorTransaction(
			long userId, long classNameId, long classPK, String status,
			String operation)
		throws PortalException {

		ConnectorTransaction connectorTransaction =
			connectorTransactionPersistence.fetchByC_C(classNameId, classPK);

		Date now = new Date();

		if (connectorTransaction == null) {
			User user = userLocalService.getUserById(userId);

			long connectorTransactionId = counterLocalService.increment();

			connectorTransaction = connectorTransactionPersistence.create(
				connectorTransactionId);

			connectorTransaction.setCompanyId(user.getCompanyId());
			connectorTransaction.setUserId(user.getUserId());
			connectorTransaction.setUserName(user.getFullName());
			connectorTransaction.setCreateDate(now);
			connectorTransaction.setClassNameId(classNameId);
			connectorTransaction.setClassPK(classPK);
		}

		connectorTransaction.setModifiedDate(now);
		connectorTransaction.setConnectorTransactionUuid(
			UUID.randomUUID().toString());
		connectorTransaction.setOperation(operation);
		connectorTransaction.setStatus(status);

		connectorTransactionPersistence.update(connectorTransaction);

		return connectorTransaction;
	}

	@Override
	public ConnectorTransaction fetchConnectorTransaction(
		String connectorTransactionUuid) {

		return connectorTransactionPersistence.fetchByConnectorTransactionUUID(
			connectorTransactionUuid);
	}

	@Override
	public ConnectorTransaction updateConnectorTransaction(
			long connectorTransactionId, String status)
		throws PortalException {

		ConnectorTransaction connectorTransaction =
			connectorTransactionPersistence.findByPrimaryKey(
				connectorTransactionId);

		connectorTransaction.setModifiedDate(new Date());
		connectorTransaction.setStatus(status);

		connectorTransactionPersistence.update(connectorTransaction);

		return connectorTransaction;
	}

}