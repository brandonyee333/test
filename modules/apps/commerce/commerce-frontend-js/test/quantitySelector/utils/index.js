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

import * as Utils from '../../../src/main/resources/META-INF/resources/components/quantity_selector/utils/index'

describe('generateOption`s working', () => {
    
    // it('should return allowed values if length > 0', () => {
        
    // })

    it('if allowedQuantities.length > 0 should return allowed values', () => {
        const allowed = [
            2,
            4,
            65,
            33,
            913,
            267,
            323,
            122,
            90,
            113,
        ];
        expect(Utils.generateOptions(allowed)).toEqual(allowed);

    })

    it('if allowedQuantities.length === 0', () => {
        expect(Utils.generateOptions([], 5,2)).toEqual([2,3,4,5])
    })

    it('if allowed is not empty and max and min are setted, should return allowed', () => {
        expect(Utils.generateOptions([3,5,7,9], 1, 9, 2)).toEqual([3,5,7,9])
    })

    it('if max < min swap the correct sorted values', () => {
        expect(Utils.generateOptions([], 1, 4, 2)).toEqual([2, 4, 6, 8])
    })

    it('if multiple > 1 all values should be * multiple', () => {
        expect(Utils.generateOptions([], 4, 1, 2)).toEqual([2,4,6,8])
    })

    it('if allowed is not set and max, min and muliple are not set', () => {
        expect(Utils.generateOptions()).toEqual([1])
    })
})