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

package com.liferay.osb.customer.zendesk.documentation.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for ZendeskArticle. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleLocalServiceUtil
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskArticleLocalServiceBaseImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ZendeskArticleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskArticleLocalServiceUtil} to access the zendesk article local service. Add custom service methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.service.impl.ZendeskArticleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public ZendeskArticle addZendeskArticle(long zendeskSectionId,
		String documentationKey, String documentationOriginalURL,
		Map<String, String> titleMap, Map<String, String> bodyMap,
		int position, String[] labelNames, Map<String, byte[]> attachments)
		throws PortalException;

	/**
	* Adds the zendesk article to the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskArticle addZendeskArticle(ZendeskArticle zendeskArticle);

	/**
	* Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	*
	* @param zendeskArticleId the primary key for the new zendesk article
	* @return the new zendesk article
	*/
	@Transactional(enabled = false)
	public ZendeskArticle createZendeskArticle(long zendeskArticleId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskArticle deleteZendeskArticle(long zendeskArticleId)
		throws PortalException;

	/**
	* Deletes the zendesk article from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public ZendeskArticle deleteZendeskArticle(ZendeskArticle zendeskArticle)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticle fetchZendeskArticle(long zendeskArticleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticle fetchZendeskArticle(long zendeskCategoryId,
		String documentationKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticle fetchZendeskArticle(String documentationOriginalURL);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the zendesk article with the primary key.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article
	* @throws PortalException if a zendesk article with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ZendeskArticle getZendeskArticle(long zendeskArticleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getZendeskArticleCount(long zendeskSectionId);

	/**
	* Returns a range of all the zendesk articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of zendesk articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ZendeskArticle> getZendeskArticles(int start, int end);

	/**
	* Returns the number of zendesk articles.
	*
	* @return the number of zendesk articles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getZendeskArticlesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ZendeskArticle> getZendeskCategoryArticles(
		long zendeskCategoryId);

	public ZendeskArticle updateZendeskArticle(long zendeskArticleId,
		long zendeskSectionId, String documentationKey,
		String documentationOriginalURL, Map<String, String> titleMap,
		Map<String, String> bodyMap, int position, String[] labelNames,
		Map<String, byte[]> attachments) throws PortalException;

	/**
	* Updates the zendesk article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticle the zendesk article
	* @return the zendesk article that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ZendeskArticle updateZendeskArticle(ZendeskArticle zendeskArticle);
}