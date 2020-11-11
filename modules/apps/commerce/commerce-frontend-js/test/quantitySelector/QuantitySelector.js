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

import '@testing-library/jest-dom/extend-expect';
import {
    cleanup,
    fireEvent,
    render,
    waitForElementToBeRemoved,
} from '@testing-library/react';
import fetchMock from 'fetch-mock';
import React from 'react';

import QuantitySelector from '../../src/main/resources/META-INF/resources/components/quantity_selector/QuantitySelector'

describe('Quantity Selector', () => {

    describe('When no account is selected', () => {
        const renderComponent
        beforeEach(() => {
            renderedComponent = render(
                <QuantitySelector
                    orderId="43939"
                    productId="43939"
                    spritemap="./assets/icons.svg"
                />
            );
        });

        afterEach(() => {
            cleanup();
        });

        it('if allowedQuantity is different from default [-1] multipleQuantity shuould be set to 1"', () => {
            expect(

            ).toBeInTheDocument();
        });

        it('if allowedQuantity is [-1] the component should display the options from min to max"', () => {
            expect(

            ).toBeInTheDocument();
        });

        it('if multipleQuantity is >= 2, all options should be element * 2', () => {
            expect(
                renderedComponent.getByText('select-account-and-order')
            ).toBeInTheDocument();
        });

        it('check correctness of inputSize attribute', () => {
            expect(
                renderedComponent.getByText('select-account-and-order')
            ).toBeInTheDocument();
        });

        it('if is disabled the input should be disabled', () => {
            expect(
                renderedComponent.getByText('select-account-and-order')
            ).toBeInTheDocument();
        });

    });

});