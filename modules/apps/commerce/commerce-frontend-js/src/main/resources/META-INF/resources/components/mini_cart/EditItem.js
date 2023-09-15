/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import ClayForm from '@clayui/form';
import {useLiferayState} from '@liferay/frontend-js-state-web';
import {fetch, sub} from 'frontend-js-web';
import React, {useContext, useEffect, useMemo, useState} from 'react';

import {CommerceContext} from '../../index';
import skuOptionsAtom from '../../utilities/atoms/skuOptionsAtom';
import {CHANNEL_RESOURCE_ENDPOINT} from '../../utilities/constants';
import {isNonnull} from '../price/util/index';
import ProductOptionRadio from '../product_options/ProductOptionRadio';
import ProductOptionSelect from '../product_options/ProductOptionSelect';
import MiniCartContext from './MiniCartContext';

const getProductOptions = (channelId, productId) => {
	const url = new URL(
		`${themeDisplay.getPathContext()}${CHANNEL_RESOURCE_ENDPOINT}/${channelId}/products/${productId}/product-options`,
		themeDisplay.getPortalURL()
	);

	return url.toString();
};

function EditItem() {
	const [options, setOptions] = useState([]);
	const [skuOptionsAtomState] = useLiferayState(skuOptionsAtom);

	const {namespace} = skuOptionsAtomState;

	const {
		cartState: {
			cartItems,
			channel: {channel},
		},
		editedItem,
		setEditedItem,
	} = useContext(MiniCartContext);

	const backLabel = sub(
		Liferay.Language.get('go-to-x'),
		Liferay.Language.get('products')
	);

	const selectedItem = useMemo(
		() => cartItems.find((item) => item.productId === editedItem.productId),
		[cartItems, editedItem]
	);

	const {price} = selectedItem;

	useEffect(() => {
		const url = getProductOptions(channel.id, editedItem.productId);

		fetch(url)
			.then((response) => response.json())
			.then((data) => setOptions(data))
			.catch((error) => console.error(error));
	}, [channel.id, editedItem.productId]);

	const hasDiscount = isNonnull(price.discountPercentage);
	const hasPromoPrice = isNonnull(price.promoPrice);

	return (
		<>
			<div className="d-flex flex-column h-100 mini-cart-edit-item">
				<div className="align-items-center d-flex mini-cart-header px-4">
					<ClayButtonWithIcon
						aria-label={backLabel}
						displayType="unstyled"
						onClick={() => setEditedItem(null)}
						symbol="angle-left"
						title={backLabel}
					/>

					<span className="font-weight-bold ml-2 text-5">
						{editedItem.name}
					</span>
				</div>

				<div className="flex-grow-1 p-4">
					{options?.items?.length > 0 ? (
						<ClayForm>
							<Options
								channelId={channel.id}
								namespace={namespace}
								options={options.items}
								productId={editedItem.productId}
								selectedItem={selectedItem}
							/>
						</ClayForm>
					) : null}

					<div className="mini-cart-prices pt-2">
						<PriceRow
							priceName={Liferay.Language.get('price-list')}
						>
							<span className="price-line-through">
								{price.priceFormatted}
							</span>
						</PriceRow>

						{hasPromoPrice ? (
							<PriceRow
								priceName={Liferay.Language.get('promo-price')}
							>
								<span className="price-line-through">
									{price.promoPriceFormatted}
								</span>
							</PriceRow>
						) : null}

						{hasDiscount ? (
							<PriceRow
								priceName={Liferay.Language.get('discount')}
							>
								<span className="price-discount">
									{`-${price.discountPercentage}%`}
								</span>
							</PriceRow>
						) : null}

						<PriceRow
							priceName={Liferay.Language.get(
								'price-as-configurated'
							)}
						>
							<span className="text-7">
								{price.finalPriceFormatted}
							</span>
						</PriceRow>
					</div>
				</div>

				<div className="mini-cart-footer px-4 py-2 text-right">
					<ClayButton
						className="mr-3"
						displayType="secondary"
						onClick={() => setEditedItem(null)}
					>
						{Liferay.Language.get('cancel')}
					</ClayButton>

					<ClayButton>{Liferay.Language.get('save')}</ClayButton>
				</div>
			</div>
		</>
	);
}

export default EditItem;

const Options = ({channelId, namespace, options, productId, selectedItem}) =>
	options.map((option) => {
		let Component = ProductOptionSelect;

		if (option.fieldType === 'radio') {
			Component = ProductOptionRadio;
		}

		return (
			<Component
				accountId={CommerceContext.account.accountId}
				channelId={channelId}
				componentId={`${namespace}_${option.id}`}
				isFromMiniCart={true}
				json={selectedItem.options}
				key={option.id}
				minQuantity={selectedItem.quantity}
				namespace={namespace}
				productId={productId}
				productOption={option}
				sku={{skuId: selectedItem.skuId}}
			/>
		);
	});

const PriceRow = ({children, priceName}) => {
	return (
		<div className="align-items-baseline d-flex justify-content-between mb-2">
			<span className="text-2">{priceName}</span>

			{children}
		</div>
	);
};
