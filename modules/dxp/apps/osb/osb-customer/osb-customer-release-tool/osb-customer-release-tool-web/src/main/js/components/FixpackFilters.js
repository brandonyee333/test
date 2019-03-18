import React, {Component, Fragment} from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/find';
import 'core-js/fn/array/find-index';

import {filtersJSONObject} from '../types/highlights';

import Button from './Button';

const PORTLET_ID = 'com_liferay_osb_customer_release_tool_web_portlet_ReleaseToolPortlet';

export default class FixpackFilters extends Component {
	fixpackFiltersFormRef = React.createRef();

	static defaultProps = {
		fixpackURL: '',
		fromFixPackVersion: '',
		productVersion: '',
		toFixPackVersion: ''
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		filtersJSON: filtersJSONObject.isRequired,
		fixpackURL: PropTypes.string,
		fromFixPackVersion: PropTypes.string,
		productVersion: PropTypes.string,
		toFixPackVersion: PropTypes.string
	};

	determineVersion = version => {
		return version === '0.0' ? '' : version;
	};

	state = {
		fixpackURL: this.props.fixpackURL === 'null' ? '' : this.props.fixpackURL,
		fromFixPackVersion: this.determineVersion(this.props.fromFixPackVersion),
		productVersion: this.determineVersion(this.props.productVersion),
		toFixPackVersion: this.determineVersion(this.props.toFixPackVersion)
	};

	getNameFromVersion = version => {
		const fixpacks = this.lookupFixPacksByProduct();

		const fixpack = fixpacks.find(
			item => item.version === version
		);

		return fixpack ? fixpack.name : '';
	};

	handleFromFixpackOnChange = target => {
		this.setState(
			{
				fixpackURL: '',
				fromFixPackVersion: target,
				toFixPackVersion: ''
			}
		);
	};

	handleProductVersionOnChange = target => {
		this.setState(
			{
				fixpackURL: '',
				fromFixPackVersion: '',
				productVersion: target,
				toFixPackVersion: ''
			}
		);
	}

	handleSubmit = target => {
		if (target !== '') {
			this.fixpackFiltersFormRef.current.submit();
		}
	}

	lookupFixPacksByProduct = (isToFixPackFilter = false) => {
		const {filtersJSON} = this.props;
		const {fromFixPackVersion, productVersion} = this.state;

		const currentProduct = filtersJSON.find(
			item => item.version === productVersion
		);

		let fixpacks = currentProduct ? currentProduct.fixPacks : [];

		if (isToFixPackFilter) {
			const index = fixpacks.findIndex(
				item => item.version === fromFixPackVersion
			);

			fixpacks = fixpacks.slice(index);
		}

		return fixpacks;
	};

	render() {
		const {actionURL, filtersJSON} = this.props;
		const {
			fixpackURL,
			fromFixPackVersion,
			productVersion,
			toFixPackVersion
		} = this.state;

		const {namespace} = window.ReleaseToolConstants;

		const fixpackDownloadLinkDescription = (
			<Fragment>
				{`${Liferay.Language.get('get')} ${this.determineVersion(toFixPackVersion) ? this.getNameFromVersion(toFixPackVersion) : ''
				}`}{' '}
				<svg className="lexicon-icon lexicon-icon-arrow-right">
					<use xlinkHref="#arrow-right" />
				</svg>
			</Fragment>
		);

		return (
			<Fragment>
				<form ref={this.fixpackFiltersFormRef} action={actionURL} method="get">
					<input name="p_p_id" type="hidden" value={PORTLET_ID} />
					<input name={`${namespace}product`} type="hidden" value="dxp" />

					<div className="search-filters">
						<div className="search-filter-container">
							<Filter
								id={`${namespace}productVersion`}
								label={Liferay.Language.get('product')}
								options={filtersJSON}
								onChange={this.handleProductVersionOnChange}
								placeholder={Liferay.Language.get('select-product')}
								selected={productVersion}
							/>
						</div>

						<div className="search-filter-container">
							<Filter
								autopopulate
								disabled={!productVersion}
								id={`${namespace}fromFixPackVersion`}
								label={Liferay.Language.get('from')}
								options={this.lookupFixPacksByProduct()}
								onChange={this.handleFromFixpackOnChange}
								placeholder={Liferay.Language.get('select-release')}
								selected={fromFixPackVersion}
							/>
						</div>

						<div className="search-filter-container">
							<Filter
								disabled={!fromFixPackVersion || !productVersion}
								id={`${namespace}toFixPackVersion`}
								label={Liferay.Language.get('to')}
								options={this.lookupFixPacksByProduct(!!fromFixPackVersion)}
								onChange={this.handleSubmit}
								placeholder={Liferay.Language.get('select-release')}
								selected={toFixPackVersion}
							/>
						</div>
					</div>
				</form>

				{fixpackURL && (
					<Button
						children={fixpackDownloadLinkDescription}
						display="link"
						href={fixpackURL}
						size="lg"
					/>
				)}
			</Fragment>
		);
	}
}

class Filter extends Component {
	static defaultProps = {
		autopopulate: false,
		disabled: false,
		label: '',
		selected: ''
	};

	static propTypes = {
		autopopulate: PropTypes.bool,
		disabled: PropTypes.bool,
		id: PropTypes.string.isRequired,
		label: PropTypes.string,
		onChange: PropTypes.func.isRequired,
		options: PropTypes.array.isRequired,
		placeholder: PropTypes.string.isRequired,
		selected: PropTypes.string
	};

	displayCurrentValue = () => {
		const {autopopulate, options} = this.props;

		let currentValue = '';

		if (autopopulate && options.length === 1) {
			currentValue = options[0].version;
		}

		return currentValue;
	}

	handleChange = (event) => {
		const {onChange} = this.props;

		onChange(event.currentTarget.value);
	}

	render() {
		const {disabled, id, label, options, placeholder, selected} = this.props;

		return (
			<div className="filter">
				{label && (
					<label className="control-label" htmlFor={id}>
						{`${label}:`}
					</label>
				)}

				<select className="form-control" disabled={disabled} id={id} name={id} onChange={this.handleChange} value={selected || this.displayCurrentValue()}>
					<option value="">{placeholder}</option>

					{!!options.length && options.map(
						option => {
							return (
								<option
									key={option.version}
									label={option.name}
									value={option.version}
								>
									{option.name}
								</option>
							);
						}
					)}
				</select>
			</div>
		);
	}
}