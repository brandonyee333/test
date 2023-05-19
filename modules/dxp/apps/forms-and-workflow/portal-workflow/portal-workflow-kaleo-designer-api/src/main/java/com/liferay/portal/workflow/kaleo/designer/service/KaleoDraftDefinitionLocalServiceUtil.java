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

package com.liferay.portal.workflow.kaleo.designer.service;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for KaleoDraftDefinition. This utility wraps
 * <code>com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionLocalService
 * @generated
 */
public class KaleoDraftDefinitionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the kaleo draft definition to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDraftDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDraftDefinition the kaleo draft definition
	 * @return the kaleo draft definition that was added
	 */
	public static KaleoDraftDefinition addKaleoDraftDefinition(
		KaleoDraftDefinition kaleoDraftDefinition) {

		return getService().addKaleoDraftDefinition(kaleoDraftDefinition);
	}

	/**
	 * Adds a Kaleo draft definition.
	 *
	 * @param userId the primary key of the Kaleo draft definition's
	 creator/owner
	 * @param groupId the primary key of the Kaleo draft definition's group
	 * @param name the Kaleo draft definition's name
	 * @param titleMap the Kaleo draft definition's locales and localized
	 titles
	 * @param content the content wrapped in XML
	 * @param version the Kaleo draft definition's published version
	 * @param draftVersion the Kaleo draft definition's draft version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the Kaleo draft definition
	 * @throws PortalException if a portal exception occurred
	 */
	public static KaleoDraftDefinition addKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<java.util.Locale, String> titleMap, String content, int version,
			int draftVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addKaleoDraftDefinition(
			userId, groupId, name, titleMap, content, version, draftVersion,
			serviceContext);
	}

	/**
	 * Creates a new kaleo draft definition with the primary key. Does not add the kaleo draft definition to the database.
	 *
	 * @param kaleoDraftDefinitionId the primary key for the new kaleo draft definition
	 * @return the new kaleo draft definition
	 */
	public static KaleoDraftDefinition createKaleoDraftDefinition(
		long kaleoDraftDefinitionId) {

		return getService().createKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	 * Deletes the kaleo draft definition from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDraftDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDraftDefinition the kaleo draft definition
	 * @return the kaleo draft definition that was removed
	 * @throws PortalException
	 */
	public static KaleoDraftDefinition deleteKaleoDraftDefinition(
			KaleoDraftDefinition kaleoDraftDefinition)
		throws PortalException {

		return getService().deleteKaleoDraftDefinition(kaleoDraftDefinition);
	}

	/**
	 * Deletes the kaleo draft definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDraftDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	 * @return the kaleo draft definition that was removed
	 * @throws PortalException if a kaleo draft definition with the primary key could not be found
	 */
	public static KaleoDraftDefinition deleteKaleoDraftDefinition(
			long kaleoDraftDefinitionId)
		throws PortalException {

		return getService().deleteKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	 * Deletes the Kaleo draft definition and its resources matching the name,
	 * published version, and draft version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param draftVersion the Kaleo draft definition's draft version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the deleted Kaleo draft definition
	 * @throws PortalException if a portal exception occurred
	 */
	public static KaleoDraftDefinition deleteKaleoDraftDefinition(
			String name, int version, int draftVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().deleteKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);
	}

	/**
	 * Deletes the kaleo draft definition and its resources matching the name
	 * and version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @throws PortalException if a portal exception occurred
	 */
	public static void deleteKaleoDraftDefinitions(
			String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().deleteKaleoDraftDefinitions(name, version, serviceContext);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static KaleoDraftDefinition fetchKaleoDraftDefinition(
		long kaleoDraftDefinitionId) {

		return getService().fetchKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the kaleo draft definition with the primary key.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	 * @return the kaleo draft definition
	 * @throws PortalException if a kaleo draft definition with the primary key could not be found
	 */
	public static KaleoDraftDefinition getKaleoDraftDefinition(
			long kaleoDraftDefinitionId)
		throws PortalException {

		return getService().getKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	 * Returns the Kaleo draft definition matching the name, published version,
	 * and draft version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param draftVersion the Kaleo draft definition's draft version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the matching Kaleo draft definition
	 * @throws PortalException if a portal exception occurred
	 */
	public static KaleoDraftDefinition getKaleoDraftDefinition(
			String name, int version, int draftVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);
	}

	/**
	 * Returns a range of all the kaleo draft definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @return the range of kaleo draft definitions
	 */
	public static List<KaleoDraftDefinition> getKaleoDraftDefinitions(
		int start, int end) {

		return getService().getKaleoDraftDefinitions(start, end);
	}

	/**
	 * Returns an ordered range of the Kaleo draft definitions matching the name
	 * and version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param start the lower bound of the range of Kaleo draft definitions to
	 return
	 * @param end the upper bound of the range of Kaleo draft definitions to
	 return (not inclusive)
	 * @param orderByComparator the comparator to order the Kaleo draft
	 definitions
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the range of matching Kaleo draft definitions ordered by the
	 comparator
	 */
	public static List<KaleoDraftDefinition> getKaleoDraftDefinitions(
		String name, int version, int start, int end,
		OrderByComparator orderByComparator,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDraftDefinitions(
			name, version, start, end, orderByComparator, serviceContext);
	}

	/**
	 * Returns the number of kaleo draft definitions.
	 *
	 * @return the number of kaleo draft definitions
	 */
	public static int getKaleoDraftDefinitionsCount() {
		return getService().getKaleoDraftDefinitionsCount();
	}

	/**
	 * Returns the number of Kaleo draft definition matching the name and
	 * version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the number of matching Kaleo draft definitions
	 */
	public static int getKaleoDraftDefinitionsCount(
		String name, int version,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {

		return getService().getKaleoDraftDefinitionsCount(
			name, version, serviceContext);
	}

	/**
	 * Returns the latest Kaleo draft definition matching the name and version.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the latest matching Kaleo draft definition
	 * @throws PortalException if a matching Kaleo draft definition could not be
	 found
	 */
	public static KaleoDraftDefinition getLatestKaleoDraftDefinition(
			String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().getLatestKaleoDraftDefinition(
			name, version, serviceContext);
	}

	/**
	 * Returns an ordered range of the latest Kaleo draft definitions matching
	 * the company and version.
	 *
	 * @param companyId the primary key of the Kaleo draft definition's company
	 * @param version the Kaleo draft definition's published version
	 * @param start the lower bound of the range of Kaleo draft definitions to
	 return
	 * @param end the upper bound of the range of Kaleo draft definitions to
	 return (not inclusive)
	 * @param orderByComparator the comparator to order the Kaleo draft
	 definitions
	 * @return the range of matching Kaleo draft definitions ordered by the
	 comparator
	 */
	public static List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, int version, int start, int end,
		OrderByComparator orderByComparator) {

		return getService().getLatestKaleoDraftDefinitions(
			companyId, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of the latest Kaleo draft definitions matching
	 * the company, keywords, and version.
	 *
	 * @param companyId the primary key of the Kaleo draft definition's company
	 * @param keywords the Kaleo draft definition's name or title
	 * @param version the Kaleo draft definition's published version
	 * @param start the lower bound of the range of Kaleo draft definitions to
	 return
	 * @param end the upper bound of the range of Kaleo draft definitions to
	 return (not inclusive)
	 * @param orderByComparator the comparator to order the Kaleo draft
	 definitions
	 * @return the range of matching Kaleo draft definitions ordered by the
	 comparator
	 */
	public static List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, String keywords, int version, int start, int end,
		OrderByComparator orderByComparator) {

		return getService().getLatestKaleoDraftDefinitions(
			companyId, keywords, version, start, end, orderByComparator);
	}

	/**
	 * Returns the number of Kaleo draft definitions matching the company and
	 * version.
	 *
	 * @param companyId the primary key of the Kaleo draft definition's company
	 * @param version the Kaleo draft definition's published version
	 * @return the number of matching Kaleo draft definitions
	 */
	public static int getLatestKaleoDraftDefinitionsCount(
		long companyId, int version) {

		return getService().getLatestKaleoDraftDefinitionsCount(
			companyId, version);
	}

	/**
	 * Returns the number of Kaleo draft definitions matching the company,
	 * keywords, and version.
	 *
	 * @param companyId the primary key of the Kaleo draft definition's company
	 * @param keywords the Kaleo draft definition's name or title
	 * @param version the Kaleo draft definition's published version
	 * @return the number of matching Kaleo draft definitions
	 */
	public static int getLatestKaleoDraftDefinitionsCount(
		long companyId, String keywords, int version) {

		return getService().getLatestKaleoDraftDefinitionsCount(
			companyId, keywords, version);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Adds a Kaleo draft definition with a draft version increment.
	 *
	 * @param userId the primary key of the Kaleo draft definition's
	 creator/owner
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 deifnition.
	 * @return the Kaleo draft definition
	 * @throws PortalException if a portal exception occurred
	 */
	public static KaleoDraftDefinition
			incrementKaleoDraftDefinitionDraftVersion(
				long userId, String name, int version,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().incrementKaleoDraftDefinitionDraftVersion(
			userId, name, version, serviceContext);
	}

	/**
	 * Publishes the Kaleo draft definition.
	 *
	 * @param userId the primary key of the Kaleo draft definition's
	 creator/owner
	 * @param groupId the primary key of the Kaleo draft definition's group
	 * @param name the Kaleo draft definition's name
	 * @param titleMap the Kaleo draft definition's locales and localized
	 titles
	 * @param content the content wrapped in XML
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the published Kaleo draft definition
	 * @throws PortalException if the user did not have the required permissions
	 to publish the Kaleo draft definition or if a portal exception
	 occurred
	 */
	public static KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<java.util.Locale, String> titleMap, String content,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().publishKaleoDraftDefinition(
			userId, groupId, name, titleMap, content, serviceContext);
	}

	/**
	 * Updates the kaleo draft definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect KaleoDraftDefinitionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param kaleoDraftDefinition the kaleo draft definition
	 * @return the kaleo draft definition that was updated
	 */
	public static KaleoDraftDefinition updateKaleoDraftDefinition(
		KaleoDraftDefinition kaleoDraftDefinition) {

		return getService().updateKaleoDraftDefinition(kaleoDraftDefinition);
	}

	/**
	 * Updates the Kaleo draft definition by replacing its content and title and
	 * incrementing the draft version.
	 *
	 * @param userId the primary key of the Kaleo draft definition's
	 creator/owner
	 * @param name the Kaleo draft definition's name
	 * @param titleMap the Kaleo draft definition's locales and localized
	 titles
	 * @param content the content wrapped in XML
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @return the updated Kaleo draft definition
	 * @throws PortalException if the user did not have the required permissions
	 to update the Kaleo draft definition or if a portal exception
	 occurred
	 */
	public static KaleoDraftDefinition updateKaleoDraftDefinition(
			long userId, String name, Map<java.util.Locale, String> titleMap,
			String content, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateKaleoDraftDefinition(
			userId, name, titleMap, content, version, serviceContext);
	}

	public static KaleoDraftDefinitionLocalService getService() {
		return _service;
	}

	public static void setService(KaleoDraftDefinitionLocalService service) {
		_service = service;
	}

	private static volatile KaleoDraftDefinitionLocalService _service;

}