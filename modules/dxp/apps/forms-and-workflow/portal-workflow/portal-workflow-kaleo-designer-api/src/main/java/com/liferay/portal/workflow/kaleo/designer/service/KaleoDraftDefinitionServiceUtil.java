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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service utility for KaleoDraftDefinition. This utility wraps
 * <code>com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionService
 * @generated
 */
public class KaleoDraftDefinitionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.workflow.kaleo.designer.service.impl.KaleoDraftDefinitionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	 * @throws PortalException if the user did not have the required permissions
	 to create the Kaleo draft definition or if a portal exception
	 occurred
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
	 * Deletes the Kaleo draft definition and its resources.
	 *
	 * @param name the Kaleo draft definition's name
	 * @param version the Kaleo draft definition's published version
	 * @param serviceContext the service context to be applied. This can set
	 guest permissions and group permissions for the Kaleo draft
	 definition.
	 * @throws PortalException if the user did not have the required permissions
	 to delete the Kaleo draft definition or if a portal exception
	 occurred
	 */
	public static void deleteKaleoDraftDefinitions(
			String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		getService().deleteKaleoDraftDefinitions(name, version, serviceContext);
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
	 * @throws PortalException if the user did not have the required permissions
	 to access the Kaleo draft definition or if a portal exception
	 occurred
	 */
	public static KaleoDraftDefinition getKaleoDraftDefinition(
			String name, int version, int draftVersion,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);
	}

	/**
	 * Returns the Kaleo draft definitions.
	 *
	 * @return the Kaleo draft definitions
	 * @throws PortalException if a portal exception occurred
	 */
	public static List<KaleoDraftDefinition> getKaleoDraftDefinitions()
		throws PortalException {

		return getService().getKaleoDraftDefinitions();
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
	 * @throws PortalException if a matching kaleo draft definition could not be
	 found or if the user did not have the required permissions to
	 access the Kaleo draft definition
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
	 * @param end the upper bound of the range of Kkaleo draft definitions to
	 return (not inclusive)
	 * @param orderByComparator the comparator to order the Kaleo draft
	 definitions
	 * @return the range of matching Kaleo draft definitions ordered by the
	 comparator
	 * @throws PortalException if a portal exception occurred
	 */
	public static List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
			long companyId, int version, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

		return getService().getLatestKaleoDraftDefinitions(
			companyId, version, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of the latest Kaleo draft definitions matching
	 * the company, version, and keywords.
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
	 * @throws PortalException if a portal exception occurred
	 */
	public static List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
			long companyId, String keywords, int version, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

		return getService().getLatestKaleoDraftDefinitions(
			companyId, keywords, version, start, end, orderByComparator);
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
	 guest permissions and group permissions for the Kkaleo draft
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

	public static KaleoDraftDefinitionService getService() {
		return _service;
	}

	public static void setService(KaleoDraftDefinitionService service) {
		_service = service;
	}

	private static volatile KaleoDraftDefinitionService _service;

}