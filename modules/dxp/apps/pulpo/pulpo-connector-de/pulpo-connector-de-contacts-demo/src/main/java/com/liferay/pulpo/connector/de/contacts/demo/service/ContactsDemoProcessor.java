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

package com.liferay.pulpo.connector.de.contacts.demo.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = ContactsDemoProcessor.class)
public class ContactsDemoProcessor {

	@Activate
	public void activate() {
		int processors = Runtime.getRuntime().availableProcessors();

		_executorService = Executors.newFixedThreadPool(processors);
	}

	public void createUsers(long total, long companyId) {
		for (int i = 0; i < total; i++) {
			_createUser(companyId);
		}
	}

	private void _createUser(long companyId) {
		final PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		final String name = PrincipalThreadLocal.getName();

		CompletableFuture<User> response = CompletableFuture.supplyAsync(
			() -> {
				try {
					PermissionThreadLocal.setPermissionChecker(
						permissionChecker);

					PrincipalThreadLocal.setName(name);

					return _contactsDemoService.createUser(companyId);
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			},
			_executorService);

		response.thenAccept(
			user -> {
				if (_log.isInfoEnabled()) {
					_log.info(
						"The user " + user.getUserId() +
							" has been succesfully created");
				}
			}
		).exceptionally(
			throwable -> {
				_log.error("Can't create a user ", throwable);

				return null;
			}
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsDemoProcessor.class);

	@Reference
	private ContactsDemoService _contactsDemoService;

	private ExecutorService _executorService;

}