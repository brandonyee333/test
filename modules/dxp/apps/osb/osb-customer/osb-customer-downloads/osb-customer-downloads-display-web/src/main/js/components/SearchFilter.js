import React from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/find';

const PORTLET_ID = "com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet";

export default class SearchFilters extends React.Component {
	searchDownloadsFormRef = React.createRef();

	_getFileTypes = (comparator) => {
		const {productsJSONArray} = this.props;

		const productEntry = productsJSONArray.find(
			product => product.value === comparator
		);

		return productEntry ? productEntry.fileTypes : []
	};

	state = {
		availableFileTypes: this._getFileTypes(this.props.currentProduct),
		fileType: this.props.currentFileType,
		product: this.props.currentProduct
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		currentFileType: PropTypes.string.isRequired,
		currentProduct: PropTypes.string.isRequired,
		productsJSONArray: PropTypes.arrayOf(
			PropTypes.shape(
				{
					name: PropTypes.string,
					value: PropTypes.string
				}
			)
		).isRequired,
	};

	handleFileTypeChange = event =>
		this.setState(
			{
				fileType: event.target.value
			},
			() => this.handleUpdate()
		);

	handleProductChange = event => {
		const fileTypes = this._getFileTypes(event.target.value);

		this.setState(
			{
				availableFileTypes: fileTypes,
				fileType: fileTypes.length === 1 ? fileTypes[0].value : '',
				product: event.target.value
			},
			() => this.handleUpdate()
		);
	};

	handleUpdate = () => {
		const {fileType, product} = this.state;

		if (!product || (fileType && product)) {
			this.handleSubmit();
		}
	};

	handleSubmit = () => this.searchDownloadsFormRef.current.submit();

	render() {
		const {actionURL, productsJSONArray} = this.props;
		const {availableFileTypes, fileType, product} = this.state;

		const {namespace} = window.DownloadsConstants;

		return (
			<form ref={this.searchDownloadsFormRef} action={actionURL} method="get">
				<input name="p_p_id" type="hidden" value={PORTLET_ID} />

				<div className="search-filters">
					<Filter
						id={`${namespace}product`}
						label={Liferay.Language.get('product')}
						onSelectChange={this.handleProductChange}
						options={productsJSONArray}
						selectedOption={product}
					/>

					<Filter
						id={`${namespace}fileType`}
						label={Liferay.Language.get('file-type')}
						onSelectChange={this.handleFileTypeChange}
						options={availableFileTypes}
						selectedOption={fileType}
					/>
				</div>
			</form>
		);
	}
};

const Filter = props => (
	<div className="search-filter-container">
		<label className="control-label" htmlFor={props.id}>
			{`${props.label}:`}
		</label>

		<select className="form-control" id={props.id} name={props.id} onChange={props.onSelectChange} value={props.selectedOption}>
			<option value="">{`${Liferay.Language.get('select')} ${props.label}`}</option>

			{props.options.map(
				(option) => {
					return (
						<option key={option.value} label={option.name} value={option.value}>{option.name}</option>
					);
				}
			)}
		</select>
	</div>
);

Filter.propTypes = {
	id: PropTypes.string.isRequired,
	label: PropTypes.string.isRequired,
	onSelectChange: PropTypes.func.isRequired,
	options: PropTypes.array.isRequired,
	selectedOption: PropTypes.string.isRequired
};