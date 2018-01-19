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

package com.liferay.pulpo.connector.de.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.pulpo.connector.de.model.ConnectorTransaction;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the connector transaction service. This utility wraps {@link com.liferay.pulpo.connector.de.service.persistence.impl.ConnectorTransactionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConnectorTransactionPersistence
 * @see com.liferay.pulpo.connector.de.service.persistence.impl.ConnectorTransactionPersistenceImpl
 * @generated
 */
@ProviderType
public class ConnectorTransactionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ConnectorTransaction connectorTransaction) {
		getPersistence().clearCache(connectorTransaction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ConnectorTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ConnectorTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ConnectorTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ConnectorTransaction update(
		ConnectorTransaction connectorTransaction) {
		return getPersistence().update(connectorTransaction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ConnectorTransaction update(
		ConnectorTransaction connectorTransaction, ServiceContext serviceContext) {
		return getPersistence().update(connectorTransaction, serviceContext);
	}

	/**
	* Returns all the connector transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the connector transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @return the range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the connector transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the connector transactions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first connector transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByUuid_First(java.lang.String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first connector transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the connector transactions before and after the current connector transaction in the ordered set where uuid = &#63;.
	*
	* @param connectorTransactionId the primary key of the current connector transaction
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next connector transaction
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction[] findByUuid_PrevAndNext(
		long connectorTransactionId, java.lang.String uuid,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(connectorTransactionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the connector transactions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of connector transactions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching connector transactions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the connector transactions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @return the range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the connector transactions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the connector transactions before and after the current connector transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param connectorTransactionId the primary key of the current connector transaction
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next connector transaction
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction[] findByUuid_C_PrevAndNext(
		long connectorTransactionId, java.lang.String uuid, long companyId,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(connectorTransactionId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the connector transactions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of connector transactions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching connector transactions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching connector transactions
	*/
	public static List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK) {
		return getPersistence().findByC_C_C(companyId, classNameId, classPK);
	}

	/**
	* Returns a range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @return the range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end) {
		return getPersistence()
				   .findByC_C_C(companyId, classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .findByC_C_C(companyId, classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching connector transactions
	*/
	public static List<ConnectorTransaction> findByC_C_C(long companyId,
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_C_C(companyId, classNameId, classPK, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByC_C_C_First(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByC_C_C_First(companyId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the first connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByC_C_C_First(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_C_First(companyId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction
	* @throws NoSuchConnectorTransactionException if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction findByC_C_C_Last(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByC_C_C_Last(companyId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the last connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching connector transaction, or <code>null</code> if a matching connector transaction could not be found
	*/
	public static ConnectorTransaction fetchByC_C_C_Last(long companyId,
		long classNameId, long classPK,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence()
				   .fetchByC_C_C_Last(companyId, classNameId, classPK,
			orderByComparator);
	}

	/**
	* Returns the connector transactions before and after the current connector transaction in the ordered set where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param connectorTransactionId the primary key of the current connector transaction
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next connector transaction
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction[] findByC_C_C_PrevAndNext(
		long connectorTransactionId, long companyId, long classNameId,
		long classPK, OrderByComparator<ConnectorTransaction> orderByComparator)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence()
				   .findByC_C_C_PrevAndNext(connectorTransactionId, companyId,
			classNameId, classPK, orderByComparator);
	}

	/**
	* Removes all the connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public static void removeByC_C_C(long companyId, long classNameId,
		long classPK) {
		getPersistence().removeByC_C_C(companyId, classNameId, classPK);
	}

	/**
	* Returns the number of connector transactions where companyId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param companyId the company ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching connector transactions
	*/
	public static int countByC_C_C(long companyId, long classNameId,
		long classPK) {
		return getPersistence().countByC_C_C(companyId, classNameId, classPK);
	}

	/**
	* Caches the connector transaction in the entity cache if it is enabled.
	*
	* @param connectorTransaction the connector transaction
	*/
	public static void cacheResult(ConnectorTransaction connectorTransaction) {
		getPersistence().cacheResult(connectorTransaction);
	}

	/**
	* Caches the connector transactions in the entity cache if it is enabled.
	*
	* @param connectorTransactions the connector transactions
	*/
	public static void cacheResult(
		List<ConnectorTransaction> connectorTransactions) {
		getPersistence().cacheResult(connectorTransactions);
	}

	/**
	* Creates a new connector transaction with the primary key. Does not add the connector transaction to the database.
	*
	* @param connectorTransactionId the primary key for the new connector transaction
	* @return the new connector transaction
	*/
	public static ConnectorTransaction create(long connectorTransactionId) {
		return getPersistence().create(connectorTransactionId);
	}

	/**
	* Removes the connector transaction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction that was removed
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction remove(long connectorTransactionId)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence().remove(connectorTransactionId);
	}

	public static ConnectorTransaction updateImpl(
		ConnectorTransaction connectorTransaction) {
		return getPersistence().updateImpl(connectorTransaction);
	}

	/**
	* Returns the connector transaction with the primary key or throws a {@link NoSuchConnectorTransactionException} if it could not be found.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction
	* @throws NoSuchConnectorTransactionException if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction findByPrimaryKey(
		long connectorTransactionId)
		throws com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException {
		return getPersistence().findByPrimaryKey(connectorTransactionId);
	}

	/**
	* Returns the connector transaction with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param connectorTransactionId the primary key of the connector transaction
	* @return the connector transaction, or <code>null</code> if a connector transaction with the primary key could not be found
	*/
	public static ConnectorTransaction fetchByPrimaryKey(
		long connectorTransactionId) {
		return getPersistence().fetchByPrimaryKey(connectorTransactionId);
	}

	public static java.util.Map<java.io.Serializable, ConnectorTransaction> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the connector transactions.
	*
	* @return the connector transactions
	*/
	public static List<ConnectorTransaction> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the connector transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @return the range of connector transactions
	*/
	public static List<ConnectorTransaction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the connector transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of connector transactions
	*/
	public static List<ConnectorTransaction> findAll(int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the connector transactions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConnectorTransactionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of connector transactions
	* @param end the upper bound of the range of connector transactions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of connector transactions
	*/
	public static List<ConnectorTransaction> findAll(int start, int end,
		OrderByComparator<ConnectorTransaction> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the connector transactions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of connector transactions.
	*
	* @return the number of connector transactions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ConnectorTransactionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConnectorTransactionPersistence, ConnectorTransactionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ConnectorTransactionPersistence.class);
}