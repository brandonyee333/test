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

package com.liferay.osb.service.http;

import com.liferay.osb.service.CorpProjectServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.CorpProjectServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.CorpProjectSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.CorpProject}, that is translated to a
 * {@link com.liferay.osb.model.CorpProjectSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpProjectServiceHttp
 * @see       com.liferay.osb.model.CorpProjectSoap
 * @see       com.liferay.osb.service.CorpProjectServiceUtil
 * @generated
 */
public class CorpProjectServiceSoap {
	public static com.liferay.osb.model.CorpProjectSoap addCorpProject(
		java.lang.String name) throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.addCorpProject(name);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap addCorpProject(
		java.lang.String userUuid, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.addCorpProject(userUuid,
					name);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap addCorpProject(
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.addCorpProject(dossieraProjectKey,
					salesforceProjectKey, name);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws RemoteException {
		try {
			CorpProjectServiceUtil.addCorpProjectUsers(corpProjectId, userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids) throws RemoteException {
		try {
			CorpProjectServiceUtil.addCorpProjectUsers(corpProjectId, userUuids);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addUserCorpProjectRoles(long corpProjectId,
		long[] userIds, long roleId) throws RemoteException {
		try {
			CorpProjectServiceUtil.addUserCorpProjectRoles(corpProjectId,
				userIds, roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws RemoteException {
		try {
			CorpProjectServiceUtil.addUserCorpProjectRoles(corpProjectId,
				userUuids, roleName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap deleteCorpProject(
		long corpProjectId) throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.deleteCorpProject(corpProjectId);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteUserCorpProjectRoles(long corpProjectId,
		long[] userIds, long roleId) throws RemoteException {
		try {
			CorpProjectServiceUtil.deleteUserCorpProjectRoles(corpProjectId,
				userIds, roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, long roleId) throws RemoteException {
		try {
			CorpProjectServiceUtil.deleteUserCorpProjectRoles(corpProjectId,
				userUuids, roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap getCorpProject(
		long corpProjectId) throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.getCorpProject(corpProjectId);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap[] getUserCorpProjects(
		java.lang.String userUuid, java.lang.String roleName)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.CorpProject> returnValue = CorpProjectServiceUtil.getUserCorpProjects(userUuid,
					roleName);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean hasUserCorpProject(long userId, long corpProjectId)
		throws RemoteException {
		try {
			boolean returnValue = CorpProjectServiceUtil.hasUserCorpProject(userId,
					corpProjectId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean hasUserCorpProject(java.lang.String userUuid,
		long corpProjectId) throws RemoteException {
		try {
			boolean returnValue = CorpProjectServiceUtil.hasUserCorpProject(userUuid,
					corpProjectId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean hasUserCorpProjectRole(java.lang.String userUuid,
		long corpProjectId, java.lang.String roleName)
		throws RemoteException {
		try {
			boolean returnValue = CorpProjectServiceUtil.hasUserCorpProjectRole(userUuid,
					corpProjectId, roleName);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws RemoteException {
		try {
			CorpProjectServiceUtil.unsetCorpProjectUsers(corpProjectId, userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsetCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids) throws RemoteException {
		try {
			CorpProjectServiceUtil.unsetCorpProjectUsers(corpProjectId,
				userUuids);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap updateCorpProject(
		long corpProjectId, java.lang.String name) throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.updateCorpProject(corpProjectId,
					name);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpProjectSoap updateCorpProject(
		long corpProjectId, java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name,
		long accountEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.CorpProject returnValue = CorpProjectServiceUtil.updateCorpProject(corpProjectId,
					dossieraProjectKey, salesforceProjectKey, name,
					accountEntryId);

			return com.liferay.osb.model.CorpProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CorpProjectServiceSoap.class);
}