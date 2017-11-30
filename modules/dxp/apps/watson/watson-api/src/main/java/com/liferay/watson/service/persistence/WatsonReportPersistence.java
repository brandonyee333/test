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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchReportException;
import com.liferay.watson.model.WatsonReport;

/**
 * The persistence interface for the watson report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonReportPersistenceImpl
 * @see WatsonReportUtil
 * @generated
 */
@ProviderType
public interface WatsonReportPersistence extends BasePersistence<WatsonReport> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonReportUtil} to access the watson report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson report in the entity cache if it is enabled.
	*
	* @param watsonReport the watson report
	*/
	public void cacheResult(WatsonReport watsonReport);

	/**
	* Caches the watson reports in the entity cache if it is enabled.
	*
	* @param watsonReports the watson reports
	*/
	public void cacheResult(java.util.List<WatsonReport> watsonReports);

	/**
	* Creates a new watson report with the primary key. Does not add the watson report to the database.
	*
	* @param watsonReportId the primary key for the new watson report
	* @return the new watson report
	*/
	public WatsonReport create(long watsonReportId);

	/**
	* Removes the watson report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report that was removed
	* @throws NoSuchReportException if a watson report with the primary key could not be found
	*/
	public WatsonReport remove(long watsonReportId)
		throws NoSuchReportException;

	public WatsonReport updateImpl(WatsonReport watsonReport);

	/**
	* Returns the watson report with the primary key or throws a {@link NoSuchReportException} if it could not be found.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report
	* @throws NoSuchReportException if a watson report with the primary key could not be found
	*/
	public WatsonReport findByPrimaryKey(long watsonReportId)
		throws NoSuchReportException;

	/**
	* Returns the watson report with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonReportId the primary key of the watson report
	* @return the watson report, or <code>null</code> if a watson report with the primary key could not be found
	*/
	public WatsonReport fetchByPrimaryKey(long watsonReportId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonReport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson reports.
	*
	* @return the watson reports
	*/
	public java.util.List<WatsonReport> findAll();

	/**
	* Returns a range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @return the range of watson reports
	*/
	public java.util.List<WatsonReport> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson reports
	*/
	public java.util.List<WatsonReport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonReport> orderByComparator);

	/**
	* Returns an ordered range of all the watson reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson reports
	* @param end the upper bound of the range of watson reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson reports
	*/
	public java.util.List<WatsonReport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson reports from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson reports.
	*
	* @return the number of watson reports
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}