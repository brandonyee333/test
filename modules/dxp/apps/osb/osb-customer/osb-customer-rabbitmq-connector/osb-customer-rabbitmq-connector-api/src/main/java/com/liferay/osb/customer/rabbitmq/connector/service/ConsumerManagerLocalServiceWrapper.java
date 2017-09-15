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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ConsumerManagerLocalService}.
 *
 * @author Amos Fong
 * @see ConsumerManagerLocalService
 * @generated
 */
@ProviderType
public class ConsumerManagerLocalServiceWrapper
	implements ConsumerManagerLocalService,
		ServiceWrapper<ConsumerManagerLocalService> {
	public ConsumerManagerLocalServiceWrapper(
		ConsumerManagerLocalService consumerManagerLocalService) {
		_consumerManagerLocalService = consumerManagerLocalService;
	}

	@Override
	public void activateConsumer(java.lang.String rabbitMQConsumerKey)
		throws java.lang.Exception {
		_consumerManagerLocalService.activateConsumer(rabbitMQConsumerKey);
	}

	@Override
	public void consumeMessage() {
		_consumerManagerLocalService.consumeMessage();
	}

	@Override
	public void consumeMessages(java.lang.String queue, long messageCount,
		java.lang.Object rabbitMQConsumer) throws java.lang.Exception {
		_consumerManagerLocalService.consumeMessages(queue, messageCount,
			rabbitMQConsumer);
	}

	@Override
	public void deactivateConsumer(java.lang.String rabbitMQConsumerKey) {
		_consumerManagerLocalService.deactivateConsumer(rabbitMQConsumerKey);
	}

	@Override
	public java.util.Map<java.lang.String, com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerBag> getConsumersMap() {
		return _consumerManagerLocalService.getConsumersMap();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _consumerManagerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String registerConsumer(java.lang.String queue,
		int prefetchCount, java.lang.Object rabbitMQConsumer)
		throws java.lang.Exception {
		return _consumerManagerLocalService.registerConsumer(queue,
			prefetchCount, rabbitMQConsumer);
	}

	@Override
	public void resetChannels() throws java.lang.Exception {
		_consumerManagerLocalService.resetChannels();
	}

	@Override
	public void unregisterConsumer(java.lang.String rabbitMQConsumerKey) {
		_consumerManagerLocalService.unregisterConsumer(rabbitMQConsumerKey);
	}

	@Override
	public ConsumerManagerLocalService getWrappedService() {
		return _consumerManagerLocalService;
	}

	@Override
	public void setWrappedService(
		ConsumerManagerLocalService consumerManagerLocalService) {
		_consumerManagerLocalService = consumerManagerLocalService;
	}

	private ConsumerManagerLocalService _consumerManagerLocalService;
}