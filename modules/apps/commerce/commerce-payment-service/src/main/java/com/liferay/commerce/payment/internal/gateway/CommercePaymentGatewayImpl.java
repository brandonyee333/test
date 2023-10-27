/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.payment.internal.gateway;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.constants.CommerceOrderPaymentConstants;
import com.liferay.commerce.context.CommerceGroupThreadLocal;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.engine.CommerceOrderEngine;
import com.liferay.commerce.payment.audit.CommercePaymentEntryAuditType;
import com.liferay.commerce.payment.audit.CommercePaymentEntryAuditTypeRegistry;
import com.liferay.commerce.payment.configuration.CommercePaymentEntryAuditConfiguration;
import com.liferay.commerce.payment.constants.CommercePaymentEntryAuditConstants;
import com.liferay.commerce.payment.gateway.CommercePaymentGateway;
import com.liferay.commerce.payment.integration.CommercePaymentIntegration;
import com.liferay.commerce.payment.model.CommercePaymentEntry;
import com.liferay.commerce.payment.service.CommercePaymentEntryAuditLocalService;
import com.liferay.commerce.payment.service.CommercePaymentEntryLocalService;
import com.liferay.commerce.payment.util.CommercePaymentHelper;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceOrderPaymentLocalService;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(service = CommercePaymentGateway.class)
public class CommercePaymentGatewayImpl implements CommercePaymentGateway {

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentEntry authorize(
			HttpServletRequest httpServletRequest,
			CommercePaymentEntry commercePaymentEntry)
		throws PortalException {

		if (!FeatureFlagManagerUtil.isEnabled("COMMERCE-11181")) {
			throw new UnsupportedOperationException();
		}

		CommercePaymentIntegration commercePaymentIntegration =
			_commercePaymentHelper.getCommercePaymentIntegration(
				commercePaymentEntry.getCommerceChannelId(),
				commercePaymentEntry.getPaymentIntegrationKey());

		CommercePaymentEntry authorizedCommercePaymentEntry =
			commercePaymentIntegration.authorize(
				httpServletRequest, commercePaymentEntry);

		commercePaymentEntry =
			_commercePaymentEntryLocalService.updateCommercePaymentEntry(
				commercePaymentEntry.getCommercePaymentEntryId(),
				authorizedCommercePaymentEntry.getErrorMessages(),
				authorizedCommercePaymentEntry.getPaymentStatus(),
				authorizedCommercePaymentEntry.getRedirectURL(),
				authorizedCommercePaymentEntry.getTransactionCode());

		CommercePaymentEntryAuditConfiguration
			commercePaymentEntryAuditConfiguration =
				_getCommercePaymentEntryAuditConfiguration(
					commercePaymentEntry.getCompanyId());


		User currentUser = _portal.getUser(httpServletRequest);

		if (commercePaymentEntryAuditConfiguration.enabled()) {
			CommercePaymentEntryAuditType commercePaymentEntryAuditType =
				_commercePaymentEntryAuditTypeRegistry.
					getCommercePaymentEntryAuditType(
						CommercePaymentEntryAuditConstants.
							TYPE_AUTHORIZE_PAYMENT);

			_commercePaymentEntryAuditLocalService.addCommercePaymentEntryAudit(
				currentUser.getUserId(),
				commercePaymentEntry.getCommercePaymentEntryId(),
				commercePaymentEntry.getAmount(),
				commercePaymentEntry.getCurrencyCode(),
				commercePaymentEntryAuditType.getType(),
				commercePaymentEntryAuditType.getLog(
					HashMapBuilder.<String, Object>put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_NAME_ID,
						commercePaymentEntry.getClassNameId()
					).put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_PK,
						String.valueOf(commercePaymentEntry.getClassPK())
					).build()),
				_createServiceContext(currentUser));
		}

		if (StringUtil.equals(
				commercePaymentEntry.getClassName(),
				CommerceOrder.class.getName())) {

			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(currentUser));

			updateOrderPaymentStatus(
				commercePaymentEntry.getClassPK(),
				commercePaymentEntry.getPaymentStatus(),
				commercePaymentEntry.getTransactionCode(),
				commercePaymentEntry.getErrorMessages());
		}

		return commercePaymentEntry;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentEntry cancel(
			HttpServletRequest httpServletRequest,
			CommercePaymentEntry commercePaymentEntry)
		throws PortalException {

		if (!FeatureFlagManagerUtil.isEnabled("COMMERCE-11181")) {
			throw new UnsupportedOperationException();
		}

		CommercePaymentIntegration commercePaymentIntegration =
			_commercePaymentHelper.getCommercePaymentIntegration(
				commercePaymentEntry.getCommerceChannelId(),
				commercePaymentEntry.getPaymentIntegrationKey());

		CommercePaymentEntry cancelledCommercePaymentEntry =
			commercePaymentIntegration.cancel(
				httpServletRequest, commercePaymentEntry);

		commercePaymentEntry =
			_commercePaymentEntryLocalService.updateCommercePaymentEntry(
				commercePaymentEntry.getCommercePaymentEntryId(),
				cancelledCommercePaymentEntry.getErrorMessages(),
				cancelledCommercePaymentEntry.getPaymentStatus(),
				cancelledCommercePaymentEntry.getRedirectURL(),
				cancelledCommercePaymentEntry.getTransactionCode());

		CommercePaymentEntryAuditConfiguration
			commercePaymentEntryAuditConfiguration =
				_getCommercePaymentEntryAuditConfiguration(
					commercePaymentEntry.getCompanyId());


		User currentUser = _portal.getUser(httpServletRequest);

		if (commercePaymentEntryAuditConfiguration.enabled()) {
			CommercePaymentEntryAuditType commercePaymentEntryAuditType =
				_commercePaymentEntryAuditTypeRegistry.
					getCommercePaymentEntryAuditType(
						CommercePaymentEntryAuditConstants.TYPE_CANCEL_PAYMENT);


			_commercePaymentEntryAuditLocalService.addCommercePaymentEntryAudit(
				currentUser.getUserId(),
				commercePaymentEntry.getCommercePaymentEntryId(),
				commercePaymentEntry.getAmount(),
				commercePaymentEntry.getCurrencyCode(),
				commercePaymentEntryAuditType.getType(),
				commercePaymentEntryAuditType.getLog(
					HashMapBuilder.<String, Object>put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_NAME_ID,
						commercePaymentEntry.getClassNameId()
					).put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_PK,
						String.valueOf(commercePaymentEntry.getClassPK())
					).build()),
				_createServiceContext(currentUser));
		}

		if (StringUtil.equals(
				commercePaymentEntry.getClassName(),
				CommerceOrder.class.getName())) {

			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(currentUser));

			updateOrderPaymentStatus(
				commercePaymentEntry.getClassPK(),
				commercePaymentEntry.getPaymentStatus(),
				commercePaymentEntry.getTransactionCode(),
				commercePaymentEntry.getErrorMessages());
		}

		return commercePaymentEntry;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentEntry capture(
			HttpServletRequest httpServletRequest,
			CommercePaymentEntry commercePaymentEntry)
		throws PortalException {

		if (!FeatureFlagManagerUtil.isEnabled("COMMERCE-11181")) {
			throw new UnsupportedOperationException();
		}

		CommercePaymentIntegration commercePaymentIntegration =
			_commercePaymentHelper.getCommercePaymentIntegration(
				commercePaymentEntry.getCommerceChannelId(),
				commercePaymentEntry.getPaymentIntegrationKey());

		CommercePaymentEntry capturedCommercePaymentEntry =
			commercePaymentIntegration.capture(
				httpServletRequest, commercePaymentEntry);

		commercePaymentEntry =
			_commercePaymentEntryLocalService.updateCommercePaymentEntry(
				commercePaymentEntry.getCommercePaymentEntryId(),
				capturedCommercePaymentEntry.getErrorMessages(),
				capturedCommercePaymentEntry.getPaymentStatus(),
				capturedCommercePaymentEntry.getRedirectURL(),
				capturedCommercePaymentEntry.getTransactionCode());

		CommercePaymentEntryAuditConfiguration
			commercePaymentEntryAuditConfiguration =
				_getCommercePaymentEntryAuditConfiguration(
					commercePaymentEntry.getCompanyId());
		User currentUser = _portal.getUser(httpServletRequest);
		if (commercePaymentEntryAuditConfiguration.enabled()) {
			CommercePaymentEntryAuditType commercePaymentEntryAuditType =
				_commercePaymentEntryAuditTypeRegistry.
					getCommercePaymentEntryAuditType(
						CommercePaymentEntryAuditConstants.
							TYPE_CAPTURE_PAYMENT);



			_commercePaymentEntryAuditLocalService.addCommercePaymentEntryAudit(
				currentUser.getUserId(),
				commercePaymentEntry.getCommercePaymentEntryId(),
				commercePaymentEntry.getAmount(),
				commercePaymentEntry.getCurrencyCode(),
				commercePaymentEntryAuditType.getType(),
				commercePaymentEntryAuditType.getLog(
					HashMapBuilder.<String, Object>put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_NAME_ID,
						commercePaymentEntry.getClassNameId()
					).put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_PK,
						String.valueOf(commercePaymentEntry.getClassPK())
					).build()),
				_createServiceContext(currentUser));
		}

		if (StringUtil.equals(
				commercePaymentEntry.getClassName(),
				CommerceOrder.class.getName())) {
			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(currentUser));
			updateOrderPaymentStatus(
				commercePaymentEntry.getClassPK(),
				commercePaymentEntry.getPaymentStatus(),
				commercePaymentEntry.getTransactionCode(),
				commercePaymentEntry.getErrorMessages());
		}

		return commercePaymentEntry;
	}

	@Override
	@Transactional(
		propagation = Propagation.REQUIRED, readOnly = false,
		rollbackFor = Exception.class
	)
	public CommercePaymentEntry refund(
			HttpServletRequest httpServletRequest,
			CommercePaymentEntry commercePaymentEntry)
		throws PortalException {

		if (!FeatureFlagManagerUtil.isEnabled("COMMERCE-11181")) {
			throw new UnsupportedOperationException();
		}

		CommercePaymentIntegration commercePaymentIntegration =
			_commercePaymentHelper.getCommercePaymentIntegration(
				commercePaymentEntry.getCommerceChannelId(),
				commercePaymentEntry.getPaymentIntegrationKey());

		CommercePaymentEntry refundedCommercePaymentEntry =
			commercePaymentIntegration.refund(
				httpServletRequest, commercePaymentEntry);

		commercePaymentEntry =
			_commercePaymentEntryLocalService.updateCommercePaymentEntry(
				commercePaymentEntry.getCommercePaymentEntryId(),
				refundedCommercePaymentEntry.getErrorMessages(),
				refundedCommercePaymentEntry.getPaymentStatus(),
				refundedCommercePaymentEntry.getRedirectURL(),
				refundedCommercePaymentEntry.getTransactionCode());

		CommercePaymentEntryAuditConfiguration
			commercePaymentEntryAuditConfiguration =
				_getCommercePaymentEntryAuditConfiguration(
					commercePaymentEntry.getCompanyId());

		User currentUser = _portal.getUser(httpServletRequest);


		if (commercePaymentEntryAuditConfiguration.enabled()) {
			CommercePaymentEntryAuditType commercePaymentEntryAuditType =
				_commercePaymentEntryAuditTypeRegistry.
					getCommercePaymentEntryAuditType(
						CommercePaymentEntryAuditConstants.TYPE_REFUND_PAYMENT);


			_commercePaymentEntryAuditLocalService.addCommercePaymentEntryAudit(
				currentUser.getUserId(),
				commercePaymentEntry.getCommercePaymentEntryId(),
				commercePaymentEntry.getAmount(),
				commercePaymentEntry.getCurrencyCode(),
				commercePaymentEntryAuditType.getType(),
				commercePaymentEntryAuditType.getLog(
					HashMapBuilder.<String, Object>put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_NAME_ID,
						commercePaymentEntry.getClassNameId()
					).put(
						CommercePaymentEntryAuditConstants.FIELD_CLASS_PK,
						String.valueOf(commercePaymentEntry.getClassPK())
					).build()),
				_createServiceContext(currentUser));
		}

		if (StringUtil.equals(
				commercePaymentEntry.getClassName(),
				CommerceOrder.class.getName())) {

			PermissionThreadLocal.setPermissionChecker(
				PermissionCheckerFactoryUtil.create(currentUser));

			updateOrderPaymentStatus(
				commercePaymentEntry.getClassPK(),
				commercePaymentEntry.getPaymentStatus(),
				commercePaymentEntry.getTransactionCode(),
				commercePaymentEntry.getErrorMessages());
		}

		return commercePaymentEntry;
	}

	public CommerceOrder updateOrderPaymentStatus(
			long commerceOrderId, int paymentStatus, String transactionId,
			String result)
		throws PortalException {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(commerceOrderId);

		CommerceGroupThreadLocal.set(
			_groupLocalService.fetchGroup(commerceOrder.getGroupId()));

		commerceOrder =
			_commerceOrderLocalService.updatePaymentStatusAndTransactionId(
				commerceOrder.getUserId(), commerceOrderId, paymentStatus,
				transactionId);

		_commerceOrderPaymentLocalService.addCommerceOrderPayment(
			commerceOrderId, paymentStatus, result);

		if ((paymentStatus == CommerceOrderPaymentConstants.STATUS_COMPLETED) &&
			(commerceOrder.getOrderStatus() !=
				CommerceOrderConstants.ORDER_STATUS_PENDING)) {

			long userId = commerceOrder.getUserId();

			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker != null) {
				userId = permissionChecker.getUserId();
			}

			commerceOrder = _commerceOrderEngine.transitionCommerceOrder(
				commerceOrder, CommerceOrderConstants.ORDER_STATUS_PENDING,
				userId, true);
		}

		return commerceOrder;
	}

	private ServiceContext _createServiceContext(User user) {
		return new ServiceContext() {
			{
				setCompanyId(user.getCompanyId());
				setUserId(user.getUserId());
			}
		};
	}

	private CommercePaymentEntryAuditConfiguration
			_getCommercePaymentEntryAuditConfiguration(long companyId)
		throws ConfigurationException {

		return (CommercePaymentEntryAuditConfiguration)
			_configurationProvider.getCompanyConfiguration(
				CommercePaymentEntryAuditConfiguration.class, companyId);
	}

	@Reference
	private CommerceOrderEngine _commerceOrderEngine;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceOrderPaymentLocalService _commerceOrderPaymentLocalService;

	@Reference
	private CommercePaymentEntryAuditLocalService
		_commercePaymentEntryAuditLocalService;

	@Reference
	private CommercePaymentEntryAuditTypeRegistry
		_commercePaymentEntryAuditTypeRegistry;

	@Reference
	private CommercePaymentEntryLocalService _commercePaymentEntryLocalService;

	@Reference
	private CommercePaymentHelper _commercePaymentHelper;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Portal _portal;

}