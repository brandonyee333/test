/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {applicationsMenuPageTest} from '../../fixtures/applicationsMenuPageTest';
import {commercePagesTest} from '../../fixtures/commercePagesTest';
import {dataApiHelpersTest} from '../../fixtures/dataApiHelpersTest';
import {loginTest} from '../../fixtures/loginTest';
import getRandomString from '../../utils/getRandomString';
import performLogin, {performLogout} from '../../utils/performLogin';

export const test = mergeTests(
	apiHelpersTest,
	applicationsMenuPageTest,
	commercePagesTest,
	dataApiHelpersTest,
	loginTest()
);

test('LPD-15231 Escape account name on admin order details page', async ({
	apiHelpers,
	commerceAdminOrderDetailsPage,
	commerceAdminOrdersPage,
	page,
}) => {
	await page.goto('/');

	const site =
		await apiHelpers.headlessAdminUser.getSiteByFriendlyUrlPath('guest');

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		siteGroupId: site.id,
	});

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: '<img src="x" onError="alert(document.location)">',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
		},
		channel.id
	);

	await commerceAdminOrdersPage.goto();

	await (
		await commerceAdminOrdersPage.tableRowLink({
			colIndex: 1,
			rowValue: cart.id,
		})
	).click();

	await expect(
		commerceAdminOrderDetailsPage.headerDetailsTitle
	).toBeVisible();

	await expect(
		commerceAdminOrderDetailsPage.commerceOrderAccountEntryName
	).toHaveText(account.name);
});

test('LPD-26244 Split order items are shown on admin order details page when show separate order items toggle is enabled', async ({
	apiHelpers,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceAdminOrderDetailsPage,
	commerceAdminOrdersPage,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: getRandomString(),
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	const channel = await apiHelpers.headlessCommerceAdminChannel.postChannel({
		name: getRandomString(),
		siteGroupId: site.id,
	});

	await commerceAdminChannelsPage.goto();

	await (
		await commerceAdminChannelsPage.channelsTableRowLink(channel.name)
	).click();

	await (
		await commerceAdminChannelDetailsPage.showSeparateOrderItemsToggle
	).check();

	await expect(
		await commerceAdminChannelDetailsPage.showSeparateOrderItemsToggle
	).toBeChecked();

	await (await commerceAdminChannelDetailsPage.saveButton).click();

	await expect(
		await commerceAdminChannelDetailsPage.showSeparateOrderItemsToggle
	).toBeChecked();

	const catalog = await apiHelpers.headlessCommerceAdminCatalog.postCatalog({
		name: getRandomString(),
	});

	const product = await apiHelpers.headlessCommerceAdminCatalog.postProduct({
		catalogId: catalog.id,
		name: {en_US: getRandomString()},
	});

	const productSkus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(product.productId)
		.then((product) => {
			return product.skus;
		});

	const sku = productSkus[0];

	const account = await apiHelpers.headlessAdminUser.postAccount({
		name: getRandomString(),
		type: 'person',
	});

	apiHelpers.data.push({id: account.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		account.id,
		['test@liferay.com']
	);

	const cartItem1 = {
		options: '[]',
		quantity: 1,
		replacedSkuId: 0,
		skuId: sku.id,
	};

	const cartItem2 = {
		options: '[]',
		quantity: 10,
		replacedSkuId: 0,
		skuId: sku.id,
	};

	const cart = await apiHelpers.headlessCommerceDeliveryCart.postCart(
		{
			accountId: account.id,
			cartItems: [cartItem1, cartItem2],
		},
		channel.id
	);

	await commerceAdminOrdersPage.goto();

	await (
		await commerceAdminOrdersPage.tableRowLink({
			colIndex: 1,
			rowValue: cart.id,
		})
	).click();

	await expect(
		commerceAdminOrderDetailsPage.headerDetailsTitle
	).toBeVisible();

	await expect(
		(
			await commerceAdminOrderDetailsPage.tableRow(
				2,
				product.name['en_US'],
				true
			)
		).row
	).toBeVisible();

	await expect(
		(
			await commerceAdminOrderDetailsPage.tableRow(
				8,
				cartItem1.quantity,
				true
			)
		).row
	).toBeVisible();

	await expect(
		(
			await commerceAdminOrderDetailsPage.tableRow(
				8,
				cartItem2.quantity,
				true
			)
		).row
	).toBeVisible();
});

test('COMMERCE-11888. As a supplier user, I can edit the order shipments details of an order', async ({
	apiHelpers,
	applicationsMenuPage,
	commerceAdminChannelDetailsPage,
	commerceAdminChannelsPage,
	commerceAdminOrderDetailsPage,
	commerceAdminOrdersPage,
	commerceLayoutsPage,
	page,
}) => {
	const site = await apiHelpers.headlessSite.createSite({
		name: 'Minium',
		templateKey: 'minium-initializer',
		templateType: 'site-initializer',
	});

	apiHelpers.data.push({id: site.id, type: 'site'});

	await commerceLayoutsPage.cleanupSiteInitializerData(apiHelpers, site.name);

	const accountBusiness = await apiHelpers.headlessAdminUser.postAccount({
		name: 'Account Business',
		type: 'business',
	});

	apiHelpers.data.push({id: accountBusiness.id, type: 'account'});

	const phoneNumber = '12345';

	const address = await apiHelpers.headlessCommerceAdminAccount.postAddress(
		accountBusiness.id,
		{phoneNumber, regionISOCode: 'AL'}
	);

	const accountSupplier = await apiHelpers.headlessAdminUser.postAccount({
		name: 'Account Supplier',
		type: 'supplier',
	});

	apiHelpers.data.push({id: accountSupplier.id, type: 'account'});

	await apiHelpers.headlessAdminUser.assignUserToAccountByEmailAddress(
		accountSupplier.id,
		['demo.unprivileged@liferay.com']
	);

	const rolesResponse = await apiHelpers.headlessAdminUser.getAccountRoles(
		accountSupplier.id
	);

	const accountSupplierRole = rolesResponse?.items?.filter((role) => {
		return role.name === 'Account Supplier';
	});

	await apiHelpers.headlessAdminUser.assignAccountRoles(
		accountSupplier.externalReferenceCode,
		accountSupplierRole[0].id,
		'demo.unprivileged@liferay.com'
	);

	const channels =
		await apiHelpers.headlessCommerceAdminChannel.getChannelsPage(
			'Minium Portal'
		);

	await apiHelpers.headlessCommerceAdminChannel.patchChannelWithAccountId(
		accountSupplier.id,
		channels.items[0]
	);

	await commerceAdminChannelsPage.goto();
	await (
		await commerceAdminChannelsPage.channelsTableRowLink(
			channels.items[0].name
		)
	).click();

	await commerceAdminChannelDetailsPage.activateShippingMethod(
		'Variable Rate'
	);

	const miniumProduct =
		await apiHelpers.headlessCommerceAdminCatalog.getProducts(
			new URLSearchParams({
				filter: `name eq 'Abs Sensor'`,
			})
		);

	const miniumProductId = miniumProduct.items[0].productId;

	const productSkus = await apiHelpers.headlessCommerceAdminCatalog
		.getProduct(miniumProductId)
		.then((product) => {
			return product.skus;
		});

	const sku = productSkus[0];

	const order = await apiHelpers.headlessCommerceAdminOrder.postOrder({
		accountId: accountBusiness.id,
		billingAddressId: address.id,
		channelId: channels.items[0].id,
		orderItems: [
			{
				quantity: 2,
				skuId: sku.id,
			},
		],
		orderStatus: '1',
		paymentMethod: 'paypal',
		paymentStatus: '0',
		shippingAddressId: address.id,
	});

	apiHelpers.data.push({id: order.id, type: 'order'});

	await performLogout(page);
	await performLogin(page, 'demo.unprivileged');

	await applicationsMenuPage.goToCommerceOrders(false);

	await (
		await commerceAdminOrdersPage.tableRowLink({
			colIndex: 1,
			rowValue: order.id,
		})
	).click();

	await expect(
		page.getByText('PendingProcessingShippedCompleted')
	).toBeVisible();

	await expect(
		await commerceAdminOrderDetailsPage.editAddressActionLink(
			'Billing Address Edit',
			'Edit'
		)
	).toBeVisible();

	await (
		await commerceAdminOrderDetailsPage.editAddressActionLink(
			'Billing Address Edit',
			'Edit'
		)
	).click();
	await (
		await commerceAdminOrderDetailsPage.editAddressActionLink(
			'Billing Address Edit',
			'Edit'
		)
	).waitFor();

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsModalHeader(
			'Edit Billing Address'
		)
	).toBeVisible();

	const streetName = 'Street1';

	await commerceAdminOrderDetailsPage.editAddress(streetName);

	await expect(
		page.getByText('PendingProcessingShippedCompleted')
	).toBeVisible();

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsAddressDescription(
			'Billing Address',
			'Edit',
			streetName
		)
	).toBeVisible();

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsAddressDescription(
			'Billing Address',
			'Edit',
			streetName
		)
	).toHaveText(streetName);

	await (
		await commerceAdminOrderDetailsPage.editAddressActionLink(
			'Shipping Address Edit',
			'Edit'
		)
	).click();
	await page.waitForLoadState('networkidle');

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsModalHeader(
			'Edit Shipping Address'
		)
	).toBeVisible();

	await commerceAdminOrderDetailsPage.editAddress(streetName);

	await expect(
		page.getByText('PendingProcessingShippedCompleted')
	).toBeVisible();

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsAddressDescription(
			'Shipping Address',
			'Edit',
			streetName
		)
	).toBeVisible();

	await expect(
		await commerceAdminOrderDetailsPage.orderDetailsAddressDescription(
			'Shipping Address',
			'Edit',
			streetName
		)
	).toHaveText(streetName);
});
