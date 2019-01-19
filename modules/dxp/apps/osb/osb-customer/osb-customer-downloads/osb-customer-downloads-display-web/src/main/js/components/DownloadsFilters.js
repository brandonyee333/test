import React from 'react';
import PropTypes from 'prop-types';

const portletId = "com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet";

export default class DownloadsFilters extends React.Component {
	searchDownloadsFormRef = React.createRef();

	state = {
		availableFileTypes: [],
		fileType: this.props.currentFileType,
		product: this.props.currentProduct
	};

	static propTypes = {
		actionURL: PropTypes.string.isRequired,
		currentFileType: PropTypes.string.isRequired,
		currentProduct: PropTypes.string.isRequired,
		productsJSONArray: PropTypes.array.isRequired
	};

	componentDidMount() {
		const {currentProduct, productsJSONArray} = this.props;

		if (currentProduct) {
			this.setState(
				{
					availableFileTypes: productsJSONArray.find(product => product.value == currentProduct).fileTypes
				}
			);
		}
	}

	handleFileTypeChange = event =>
		this.setState(
			{
				fileType: event.target.value
			},
			() => {
				this.handleUpdate();
			}
		);

	handleProductChange = event => {
		const {productsJSONArray} = this.props;

		let autoSelectFileType = false;
		let fileTypes = [];

		if (event.target.value) {
			fileTypes = productsJSONArray.find(product => product.value == event.target.value).fileTypes;

			if (fileTypes.length == 1) {
				autoSelectFileType = true;
			}
		}

		this.setState(
			{
				availableFileTypes: fileTypes,
				fileType: autoSelectFileType ? fileTypes[0].value : '',
				product: event.target.value
			},
			() => {
				this.handleUpdate();
			}
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
		const {namespace} = window.DownloadsConstants;

		const {actionURL, productsJSONArray} = this.props;

		const {availableFileTypes, fileType, product} = this.state;

		return (
			<form action={actionURL} method="get" ref={this.searchDownloadsFormRef}>
				<input name="p_p_id" type="hidden" value={portletId} />

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
	<div className="downloads-search form-group form-group-inline form-inline input-select-wrapper search-filter-container">
		<label for={props.id} className="control-label">
			{props.label}
		</label>

		<span className="prefix">&#58;</span>

		<select className="form-control" id={props.id} name={props.id} onChange={props.onSelectChange} value={props.selectedOption}>
			<option value="">{Liferay.Language.get('select') + ' ' + props.label}</option>

			{props.options.map(
				(option) => {
					return (
						<option key={option.value} id={'product-' + option.value} label={option.name} value={option.value}>{option.name}</option>
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