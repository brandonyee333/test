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

import ClayButton from '@clayui/button';
import ClayChart from '@clayui/charts';
import classNames from 'classnames';
import {ClaySelect} from '@clayui/form';
import ClayIcon from '@clayui/icon';
import React, {useState} from 'react';

import Header from '../../../common/components/header';

const PRODUCT_LIST = [
	'All',
	'Auto',
	'General Liability',
	'Professional Liability',
	'Workers Compensation',
	'Business Owners Policy',
];

const TIME_PERIODS = ['YTD', '3 MO', '6 MO'];


type BarChartPerformanceTypes = {
    colors: string[],
	dataColumns: string[],
	height: number,
    groups: string[],
	labelColumns: string[],
	showLegend: boolean,
	showTooltip: boolean,
	titleTotal: boolean,
	totalSum: number,
	width: number,
}

const colors:string[] = [
    '#DCF1FD',
    '#55C2FF',
    '#FFD76E',
];

const dataColumn: any = {
	yearly: {
		jan:[
			{goals: 200,
			currentValue: 100,}
			
		],
		feb:[
			{goals: 210,
			currentValue: 25}
		],
		mar:[
			{goals: 250,
			currentValue: 25}
		],
		apr:[
			{goals: 320,
			currentValue: 25}
		],
		may:[
			{goals: 200,
			currentValue: 25}
		],
		jun:[
			{goals: 120,
			currentValue: 25}
		],
		jul:[
			{goals: 320,
			currentValue: 25}
		],
		aug:[
			{goals: 220,
			currentValue: 25}
		],
		sep:[
			{goals: 20,
			currentValue: 25}
		],
		oct:[
			{goals: 320,
			currentValue: 25}
		],
		nov:[
			{goals: 420,
			currentValue: 25}
		],
		dec:[
			{goals: 210,
			currentValue: 25}
		],

	}	,
	threeMonths: {

		
	},
	sixMonths: {

		
	}	
}


const chart=  {
	data: {
		columns: [
		["goal", 200, 200, 200, 400, 150, 250],
		["exceeded", 30, 100, 100, 200, 150, 50],
		["achieved", 230, 200, 200, 300, 250, 250]
		], 
		groups: [
			[
				'goal',
				'exceeded',
				'achieved'
			]
		],
		type: "bar", 
		},
	grid:{
		y: {
			lines: [
			{
				value: 0
			}
			]
			}
		},
};


const teste: any = {

	data:{

		columns: [
			
			["goal", dataColumn.yearly.jan[0].goals, dataColumn.yearly.feb[0].goals ],
			["exceeded", dataColumn.yearly.jan[0].currentValue, dataColumn.yearly.feb[0].currentValue ]
		],
		groups: [
			[
				'goal',
				'exceeded',
				
			]
		],
		
	}
}
const labelColumns = [
	'Jan 2022',
	'Feb 2022',
	'Mar 2022',
	'Apr 2022',
	'May 2022',
	'Jun 2022',
	'Jul 2022',
	'Ago 2022',
	'Sep 2022',
	'Oct 2022',
	'Nov 2022',
	'Dec 2022',
];


const BarChartPerformancee: BarChartPerformanceTypes  = {
    colors:[],
	dataColumns:[],
	height : 300,
    groups : [''],
	labelColumns:[],
	showLegend : true,
	showTooltip : true,
	titleTotal : true,
	totalSum : 0,
	width : 450,
}


const ProductPerformance = () => {
	const [choosenProduct, setChoosenProduct] = useState(PRODUCT_LIST[0]);
	const [timePeriod, setTimePeriod] = useState(TIME_PERIODS[0]);
	console.log(dataColumn.yearly.jan[0])
	console.log(chart.data.groups)
	console.log('teste')
	console.log(teste.columns)
	return (
		<div className="d-flex flex-wrap ray-dashboard-product-performance">
			<div className="col-5 left-container px-0">
				<Header
					className="header-row px-4 py-3"
					title="Product Performance"
				/>

				<div className="p-5"></div>
			</div>

			<div className="col-7 px-0 right-container">
				<div className="align-items-center d-flex header-row justify-content-between px-4 py-3">
					<p className="m-0 text-paragraph">
						<ClayButton
							className={classNames('general-filter mr-1', {
								'font-weight-bolder': choosenProduct === 'All',
							})}
							displayType="unstyled"
							onClick={() => setChoosenProduct('All')}
						>
							All
						</ClayButton>

						{choosenProduct !== 'All' && (
							<>
								<ClayIcon
									className="font-weight-bolder mr-1"
									symbol="angle-right-small"
								/>

								<span className="font-weight-bolder">{`${choosenProduct}`}</span>
							</>
						)}
					</p>

					<ClaySelect
						className="product-performance-select"
						onChange={({target}) => {
							setTimePeriod(target.value);
						}}
						sizing="sm"
						value={timePeriod}
					>
						{TIME_PERIODS.map((timePeriod, index) => (
							<ClaySelect.Option
								key={index}
								label={timePeriod}
								value={timePeriod}
							/>
						))}
					</ClaySelect>
				</div>

				<div className="p-5"> 

						{/* <ClayChart
							data={{
							columns: chart.data.columns,
							groups: chart.data.groups,
							type: chart.data.type,
							}}
						/> */}
				
				<ClayChart
				axis={{
					x: {
						type: 'category',
						show: true,
						categories: [...labelColumns]

					},
					y: {
						show: true,

					},
				}}
				bar={{
					radius: {
						ratio: 0.2,
					},
					width: {
						data: 20,
					},

				}}
				data={{

					columns:teste.data.columns,
                    groups: teste.data.groups,
					type: chart.data.type,
					labels: {						
						colors: '#272898',
						position: {
							y: -10,
						},

					},
				}}
				legend={{
					show: BarChartPerformancee.showLegend,
				}}
				size={{
					height: BarChartPerformancee.height,
					width: BarChartPerformancee.width
				}}
				tooltip={{
					show: BarChartPerformancee.showTooltip,
				}}
			/>
					</div>


				</div>
			</div>
	);
};

export default ProductPerformance;
