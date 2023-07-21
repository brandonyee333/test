/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.cache.CacheRegistryItem;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.security.lang.PortalSecurityManager;
import com.liferay.portal.security.lang.SecurityManagerUtil;

import java.security.AccessController;
import java.security.BasicPermission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raymond Augé
 */
public class PACLUtil {

	public static Class<?> getClass(Object object) {
		Class<?> clazz = object.getClass();

		if (object instanceof Class) {
			clazz = (Class<?>)object;
		}

		if (ProxyUtil.isProxyClass(clazz) || !clazz.isInterface()) {
			clazz = _getInterface(clazz);
		}

		return clazz;
	}

	public static PACLPolicy getPACLPolicy() {
		SecurityManager securityManager = System.getSecurityManager();

		try {
			securityManager.checkPermission(_permission);

			return PACLPolicyThreadLocal.get();
		}
		finally {
			PACLPolicyThreadLocal.set(null);
		}
	}

	public static String getServiceInterfaceName(String serviceClassName) {
		int pos = serviceClassName.indexOf(".impl.");

		if (pos != -1) {
			serviceClassName =
				serviceClassName.substring(0, pos + 1) +
					serviceClassName.substring(pos + 6);
		}

		if (serviceClassName.endsWith("Impl")) {
			serviceClassName = serviceClassName.substring(
				0, serviceClassName.length() - 4);
		}

		return serviceClassName;
	}

	public static boolean hasSameOrigin(Class<?> callerClass) {
		PACLPolicy paclPolicy = getPACLPolicy();

		if (paclPolicy == null) {
			return true;
		}

		ProtectionDomain protectionDomain = AccessController.doPrivileged(
			new ProtectionDomainPrivilegedAction(callerClass));

		PortalSecurityManager portalSecurityManager =
			SecurityManagerUtil.getPortalSecurityManager();

		Policy portalPolicy = portalSecurityManager.getPolicy();

		PermissionCollection permissionCollection = portalPolicy.getPermissions(
			protectionDomain);

		return _hasSameOrigin(
			protectionDomain, permissionCollection, paclPolicy);
	}

	public static boolean isTrustedCaller(
		Class<?> callerClass, java.security.Permission permission,
		PACLPolicy paclPolicy) {

		ProtectionDomain protectionDomain = AccessController.doPrivileged(
			new ProtectionDomainPrivilegedAction(callerClass));

		return _isTrustedCaller(protectionDomain, permission, paclPolicy);
	}

	public static class Permission extends BasicPermission {

		public Permission() {
			super("getPACLPolicy");
		}

	}

	private static Class<?> _getInterface(Class clazz) {
		Class<?>[] interfaces = clazz.getInterfaces();

		for (Class<?> currentInterface : interfaces) {
			clazz = currentInterface;

			if (!_ignoredInterfaces.contains(clazz)) {
				break;
			}
		}

		return clazz;
	}

	private static boolean _hasSameOrigin(
		ProtectionDomain protectionDomain,
		PermissionCollection permissionCollection, PACLPolicy paclPolicy) {

		PACLPolicy callerPACLPolicy = null;

		if (permissionCollection instanceof PortalPermissionCollection) {
			PortalPermissionCollection portalPermissionCollection =
				(PortalPermissionCollection)permissionCollection;

			callerPACLPolicy = portalPermissionCollection.getPACLPolicy();
		}
		else {
			callerPACLPolicy = PACLPolicyManager.getPACLPolicy(
				protectionDomain);
		}

		if (paclPolicy == callerPACLPolicy) {
			return true;
		}

		return false;
	}

	private static boolean _isTrustedCaller(
		ProtectionDomain protectionDomain, java.security.Permission permission,
		PACLPolicy paclPolicy) {

		PortalSecurityManager portalSecurityManager =
			SecurityManagerUtil.getPortalSecurityManager();

		Policy portalPolicy = portalSecurityManager.getPolicy();

		PermissionCollection permissionCollection = portalPolicy.getPermissions(
			protectionDomain);

		if (!_hasSameOrigin(
				protectionDomain, permissionCollection, paclPolicy) &&
			permissionCollection.implies(permission)) {

			return true;
		}

		return false;
	}

	private static final Set<Class<?>> _ignoredInterfaces = new HashSet<>(
		Arrays.asList(CacheRegistryItem.class, IdentifiableOSGiService.class));
	private static final Permission _permission = new PACLUtil.Permission();

	private static class ProtectionDomainPrivilegedAction
		implements PrivilegedAction<ProtectionDomain> {

		public ProtectionDomainPrivilegedAction(Class<?> clazz) {
			_clazz = clazz;
		}

		@Override
		public ProtectionDomain run() {
			return _clazz.getProtectionDomain();
		}

		private final Class<?> _clazz;

	}

}