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

package com.liferay.osb.asah.common.upgrade.impl;

import com.liferay.osb.asah.common.upgrade.UpgradeState;
import com.liferay.osb.asah.common.util.ReleaseInfo;

import java.nio.charset.StandardCharsets;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@Profile({"dev", "prod"})
public class UpgradeStateImpl implements UpgradeState {

	@Override
	public void awaitCompletion() {
		RedisConnection redisConnection =
			_lettuceConnectionFactory.getConnection();

		while (true) {
			byte[] upgradeStateByteArray = redisConnection.get(
				"UPGRADE_STATE".getBytes(StandardCharsets.UTF_8));

			if (upgradeStateByteArray != null) {
				return;
			}

			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException interruptedException) {
				Thread thread = Thread.currentThread();

				thread.interrupt();
			}
		}
	}

	@Override
	public void complete() {
		RedisConnection redisConnection =
			_lettuceConnectionFactory.getConnection();

		String releaseInfoVersion = ReleaseInfo.getVersion();

		redisConnection.set(
			"UPGRADE_STATE".getBytes(StandardCharsets.UTF_8),
			releaseInfoVersion.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public boolean isComplete() {
		RedisConnection redisConnection =
			_lettuceConnectionFactory.getConnection();

		byte[] upgradeStateByteArray = redisConnection.get(
			"UPGRADE_STATE".getBytes(StandardCharsets.UTF_8));

		if (upgradeStateByteArray != null) {
			String upgradeState = new String(
				upgradeStateByteArray, StandardCharsets.UTF_8);

			if (Objects.equals(upgradeState, ReleaseInfo.getVersion())) {
				return true;
			}
		}

		return false;
	}

	@Autowired
	private LettuceConnectionFactory _lettuceConnectionFactory;

}