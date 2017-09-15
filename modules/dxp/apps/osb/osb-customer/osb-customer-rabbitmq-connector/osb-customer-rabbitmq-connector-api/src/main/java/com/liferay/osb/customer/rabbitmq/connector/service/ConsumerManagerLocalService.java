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

package com.liferay.osb.customer.rabbitmq.connector.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerBag;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Map;

/**
 * Provides the local service interface for ConsumerManager. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Amos Fong
 * @see ConsumerManagerLocalServiceUtil
 * @see com.liferay.osb.customer.rabbitmq.connector.service.base.ConsumerManagerLocalServiceBaseImpl
 * @see com.liferay.osb.customer.rabbitmq.connector.service.impl.ConsumerManagerLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ConsumerManagerLocalService extends BaseLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ConsumerManagerLocalServiceUtil} to access the consumer manager local service. Add custom service methods to {@link com.liferay.osb.customer.rabbitmq.connector.service.impl.ConsumerManagerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void activateConsumer(java.lang.String rabbitMQConsumerKey)
		throws java.lang.Exception;

	public void consumeMessage();

	public void consumeMessages(java.lang.String queue, long messageCount,
		java.lang.Object rabbitMQConsumer) throws java.lang.Exception;

	public void deactivateConsumer(java.lang.String rabbitMQConsumerKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<java.lang.String, ConsumerBag> getConsumersMap();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public java.lang.String registerConsumer(java.lang.String queue,
		int prefetchCount, java.lang.Object rabbitMQConsumer)
		throws java.lang.Exception;

	public void resetChannels() throws java.lang.Exception;

	public void unregisterConsumer(java.lang.String rabbitMQConsumerKey);
}