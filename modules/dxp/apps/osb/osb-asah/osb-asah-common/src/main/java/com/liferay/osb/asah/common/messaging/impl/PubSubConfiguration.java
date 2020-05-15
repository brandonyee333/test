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

package com.liferay.osb.asah.common.messaging.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.net.URI;

import java.util.function.Supplier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
@MonolithExclude
public class PubSubConfiguration {

	@Bean(name = "managedChannelSupplier")
	@ConditionalOnGoogleApplicationCredentials
	public Supplier<ManagedChannel> computeEngineManagedChannelSupplier() {
		return () -> null;
	}

	@Bean(name = "managedChannelSupplier")
	@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
	public Supplier<ManagedChannel> emulatorManagedChannelSupplier() {
		return () -> {
			String target = ServiceConstants.URL_PUBSUB_EMULATOR;

			try {
				URI uri = new URI(target);

				target = uri.getAuthority();
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			ManagedChannelBuilder managedChannelBuilder =
				ManagedChannelBuilder.forTarget(target);

			managedChannelBuilder.usePlaintext();

			return managedChannelBuilder.build();
		};
	}

	private static final Log _log = LogFactory.getLog(
		PubSubConfiguration.class);

}