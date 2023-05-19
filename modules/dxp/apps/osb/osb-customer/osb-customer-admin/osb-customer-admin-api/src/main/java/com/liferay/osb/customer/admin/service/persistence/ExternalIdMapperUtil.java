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

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the external ID mapper service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.ExternalIdMapperPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperPersistence
 * @generated
 */
public class ExternalIdMapperUtil {

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
	public static void clearCache(ExternalIdMapper externalIdMapper) {
		getPersistence().clearCache(externalIdMapper);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ExternalIdMapper> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ExternalIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ExternalIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ExternalIdMapper> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ExternalIdMapper update(ExternalIdMapper externalIdMapper) {
		return getPersistence().update(externalIdMapper);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ExternalIdMapper update(
		ExternalIdMapper externalIdMapper, ServiceContext serviceContext) {

		return getPersistence().update(externalIdMapper, serviceContext);
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C(
		long classNameId, long classPK) {

		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper[] findByC_C_PrevAndNext(
			long externalIdMapperId, long classNameId, long classPK,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_PrevAndNext(
			externalIdMapperId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching external ID mappers
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type) {

		return getPersistence().findByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_T(
			classNameId, classPK, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_C_T_First(
			long classNameId, long classPK, int type,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_T_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_C_T_First(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_C_T_Last(
			long classNameId, long classPK, int type,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_T_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_C_T_Last(
			classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper[] findByC_C_T_PrevAndNext(
			long externalIdMapperId, long classNameId, long classPK, int type,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_C_T_PrevAndNext(
			externalIdMapperId, classNameId, classPK, type, orderByComparator);
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 */
	public static void removeByC_C_T(long classNameId, long classPK, int type) {
		getPersistence().removeByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param type the type
	 * @return the number of matching external ID mappers
	 */
	public static int countByC_C_T(long classNameId, long classPK, int type) {
		return getPersistence().countByC_C_T(classNameId, classPK, type);
	}

	/**
	 * Returns all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, String externalId) {

		return getPersistence().findByC_T_EI(classNameId, type, externalId);
	}

	/**
	 * Returns a range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, String externalId, int start, int end) {

		return getPersistence().findByC_T_EI(
			classNameId, type, externalId, start, end);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, String externalId, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().findByC_T_EI(
			classNameId, type, externalId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching external ID mappers
	 */
	public static List<ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, String externalId, int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_T_EI(
			classNameId, type, externalId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_T_EI_First(
			long classNameId, int type, String externalId,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_T_EI_First(
			classNameId, type, externalId, orderByComparator);
	}

	/**
	 * Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_T_EI_First(
		long classNameId, int type, String externalId,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_T_EI_First(
			classNameId, type, externalId, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper
	 * @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper findByC_T_EI_Last(
			long classNameId, int type, String externalId,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_T_EI_Last(
			classNameId, type, externalId, orderByComparator);
	}

	/**
	 * Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	 */
	public static ExternalIdMapper fetchByC_T_EI_Last(
		long classNameId, int type, String externalId,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().fetchByC_T_EI_Last(
			classNameId, type, externalId, orderByComparator);
	}

	/**
	 * Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param externalIdMapperId the primary key of the current external ID mapper
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper[] findByC_T_EI_PrevAndNext(
			long externalIdMapperId, long classNameId, int type,
			String externalId,
			OrderByComparator<ExternalIdMapper> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByC_T_EI_PrevAndNext(
			externalIdMapperId, classNameId, type, externalId,
			orderByComparator);
	}

	/**
	 * Removes all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 */
	public static void removeByC_T_EI(
		long classNameId, int type, String externalId) {

		getPersistence().removeByC_T_EI(classNameId, type, externalId);
	}

	/**
	 * Returns the number of external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param type the type
	 * @param externalId the external ID
	 * @return the number of matching external ID mappers
	 */
	public static int countByC_T_EI(
		long classNameId, int type, String externalId) {

		return getPersistence().countByC_T_EI(classNameId, type, externalId);
	}

	/**
	 * Caches the external ID mapper in the entity cache if it is enabled.
	 *
	 * @param externalIdMapper the external ID mapper
	 */
	public static void cacheResult(ExternalIdMapper externalIdMapper) {
		getPersistence().cacheResult(externalIdMapper);
	}

	/**
	 * Caches the external ID mappers in the entity cache if it is enabled.
	 *
	 * @param externalIdMappers the external ID mappers
	 */
	public static void cacheResult(List<ExternalIdMapper> externalIdMappers) {
		getPersistence().cacheResult(externalIdMappers);
	}

	/**
	 * Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	 *
	 * @param externalIdMapperId the primary key for the new external ID mapper
	 * @return the new external ID mapper
	 */
	public static ExternalIdMapper create(long externalIdMapperId) {
		return getPersistence().create(externalIdMapperId);
	}

	/**
	 * Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper that was removed
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper remove(long externalIdMapperId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().remove(externalIdMapperId);
	}

	public static ExternalIdMapper updateImpl(
		ExternalIdMapper externalIdMapper) {

		return getPersistence().updateImpl(externalIdMapper);
	}

	/**
	 * Returns the external ID mapper with the primary key or throws a <code>NoSuchExternalIdMapperException</code> if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper
	 * @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper findByPrimaryKey(long externalIdMapperId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchExternalIdMapperException {

		return getPersistence().findByPrimaryKey(externalIdMapperId);
	}

	/**
	 * Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param externalIdMapperId the primary key of the external ID mapper
	 * @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	 */
	public static ExternalIdMapper fetchByPrimaryKey(long externalIdMapperId) {
		return getPersistence().fetchByPrimaryKey(externalIdMapperId);
	}

	/**
	 * Returns all the external ID mappers.
	 *
	 * @return the external ID mappers
	 */
	public static List<ExternalIdMapper> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @return the range of external ID mappers
	 */
	public static List<ExternalIdMapper> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of external ID mappers
	 */
	public static List<ExternalIdMapper> findAll(
		int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the external ID mappers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ExternalIdMapperModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of external ID mappers
	 * @param end the upper bound of the range of external ID mappers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of external ID mappers
	 */
	public static List<ExternalIdMapper> findAll(
		int start, int end,
		OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the external ID mappers from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of external ID mappers.
	 *
	 * @return the number of external ID mappers
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ExternalIdMapperPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ExternalIdMapperPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ExternalIdMapperPersistence _persistence;

}