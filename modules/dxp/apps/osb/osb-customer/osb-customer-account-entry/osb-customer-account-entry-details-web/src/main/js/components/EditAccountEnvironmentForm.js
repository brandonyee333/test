import React from 'react';
import PropTypes from 'prop-types';

import {Formik} from 'formik';
import * as yup from 'yup';

import Alert from './Alert';
import Button from './Button';

const NOT_REQUIRED_SCHEMA = yup.string().notRequired();

const REQUIRED_SCHEMA = yup
	.string()
	.required(Liferay.Language.get('this-field-is-required'));

const SELECT_LABEL = Liferay.Language.get('select');

export default class EditAccountEnvironmentForm extends React.Component {
	editEnvironmentFormRef = React.createRef();
	formikInstanceRef = React.createRef();

	static propTypes = {
		addEnvironmentURL: PropTypes.string.isRequired,
		environment: PropTypes.object,
		environmentConfiguration: PropTypes.object.isRequired,
		handleCloseModal: PropTypes.func.isRequired,
		namespace: PropTypes.string.isRequired
	};

	state = {
		configurations: {
			customOS: false,
			enterprise: false
		},
		formValues: {
			envAS: '',
			envBrowser: '',
			envCommerce: '',
			envCS: '',
			envDB: '',
			envJVM: '',
			envLFR: '',
			envOS: '',
			envSearch: [],
			name: '',
			productEntryId: ''
		},
		selectedOptions: {
			selectedCommerceVersion: null,
			selectedLFRVersion: null,
			selectedProduct: null
		}
	};

	componentDidMount() {
		const {environment, environmentConfiguration} = this.props;
		const {
			envCommerce,
			envLFRVersions,
			products
		} = environmentConfiguration;

		if (environment) {
			const currentProduct = products.find(
				product => product.productEntryId === environment.productEntryId
			);

			let currentCommerceValue = '';
			let currentCommerceVersion = '';
			let currentLFRValue = '';
			let currentLFRVersion = '';

			if (currentProduct.envCommerce) {
				currentCommerceValue = currentProduct.envCommerce.find(
					version => version.name === environment.envCommerceLabel
				).value;

				currentCommerceVersion = envCommerce.envCommerceVersions.find(
					version => version[currentCommerceValue]
				)[currentCommerceValue];

				currentLFRValue = currentCommerceVersion.envLFR.find(
					version => version.name === environment.envLFRLabel
				).value;

				currentLFRVersion = envCommerce.envLFRVersions.find(
					version => version[currentLFRValue]
				)[currentLFRValue];
			} else {
				currentLFRValue = currentProduct.envLFR.find(
					version => version.name === environment.envLFRLabel
				).value;

				currentLFRVersion = envLFRVersions.find(
					version => version[currentLFRValue]
				)[currentLFRValue];
			}

			this.setState({
				configurations: {
					customOS: false,
					enterprise: currentProduct.enterpriseSearch
				},
				formValues: {
					envAS: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envAS',
						environment.envASLabel
					),
					envBrowser: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envBrowser',
						environment.envBrowserLabel
					),
					envCommerce: currentCommerceValue,
					envCS: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envCS',
						environment.envCSLabel
					),
					envDB: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envDB',
						environment.envDBLabel
					),
					envJVM: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envJVM',
						environment.envJVMLabel
					),
					envLFR: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envLFR',
						environment.envLFRLabel
					),
					envOS: this.getSelectionValueFromLabel(
						currentLFRVersion,
						'envOS',
						environment.envOSLabel
					),
					envSearch: this.getSearchValues(
						currentLFRVersion,
						environment.envSearchLabels
					),
					name: environment.name,
					productEntryId: environment.productEntryId
				},
				selectedOptions: {
					selectedCommerceVersion: currentCommerceVersion,
					selectedLFRVersion: currentLFRVersion,
					selectedProduct: currentProduct
				}
			});
		}
	}

	handleDeleteFile = fileRef => {
		const {formValues} = this.state;

		const fileInput = fileRef.current;

		const fieldName = this.updateFieldName(fileInput.name);

		fileInput.value = null;

		this.setState({
			formValues: {
				...formValues,
				[fieldName]: null
			}
		});

		this.formikInstanceRef.current.setFieldValue(
			this.props.namespace + fieldName,
			''
		);
	};

	handleFileChange = event => {
		const {formValues} = this.state;

		const {files, name} = event.target;
		const fieldName = this.updateFieldName(name);

		if (files.length) {
			this.setState({
				formValues: {
					...formValues,
					[fieldName]: files[0].name
				}
			});
		}

		this.formikInstanceRef.current.handleChange(event);
	};

	handleInputChange = event => {
		const {formValues} = this.state;

		const fieldName = this.updateFieldName(event.target.name);

		this.setState({
			formValues: {
				...formValues,
				[fieldName]: event.target.value
			}
		});

		this.formikInstanceRef.current.handleChange(event);
	};

	handleOSChange = event => {
		const {configurations, formValues} = this.state;

		const {options} = event.target;
		const {label} = options[options.selectedIndex];

		this.setState({
			configurations: {
				...configurations,
				customOS: label === 'Other'
			},
			formValues: {
				...formValues,
				envOS: event.target.value
			}
		});

		this.formikInstanceRef.current.handleChange(event);
	};

	handleSelectChange = event => {
		const {
			envCommerce,
			envLFRVersions,
			products
		} = this.props.environmentConfiguration;

		const {configurations, formValues, selectedOptions} = this.state;

		const {name, options} = event.target;
		const {value} = options[options.selectedIndex];

		if (name === `${this.props.namespace}productEntryId`) {
			const currentProduct = products.find(
				product => product.productEntryId === value
			);

			this.setState({
				configurations: {
					...configurations,
					enterprise: !!(
						currentProduct && currentProduct.enterpriseSearch
					)
				},
				formValues: {
					...formValues,
					envCommerce: '',
					envLFR: '',
					productEntryId: event.target.value
				},
				selectedOptions: {
					selectedCommerceVersion: null,
					selectedLFRVersion: null,
					selectedProduct: currentProduct
				}
			});
		} else if (name === `${this.props.namespace}envCommerce`) {
			const currentCommerceVersion = envCommerce.envCommerceVersions.find(
				version => version[value]
			);

			this.setState({
				formValues: {
					...formValues,
					envAS: '',
					envBrowser: '',
					envCommerce: event.target.value,
					envCS: '',
					envDB: '',
					envJVM: '',
					envLFR: '',
					envOS: '',
					envSearch: []
				},
				selectedOptions: {
					...selectedOptions,
					selectedCommerceVersion: currentCommerceVersion
						? currentCommerceVersion[value]
						: null
				}
			});
		} else if (name === `${this.props.namespace}envLFR`) {
			const {selectedProduct} = selectedOptions;

			let currentLFRVersion = envLFRVersions.find(
				version => version[value]
			);

			if (selectedProduct.envCommerce) {
				currentLFRVersion = envCommerce.envLFRVersions.find(
					version => version[value]
				);
			}

			this.setState({
				formValues: {
					...formValues,
					envAS: '',
					envBrowser: '',
					envCS: '',
					envDB: '',
					envJVM: '',
					envLFR: event.target.value,
					envOS: '',
					envSearch: []
				},
				selectedOptions: {
					...selectedOptions,
					selectedLFRVersion: currentLFRVersion
						? currentLFRVersion[value]
						: null
				}
			});
		} else if (name === `${this.props.namespace}envSearch`) {
			const selectedSearchOptions = [];

			for (let i = 0; i < options.length; i++) {
				if (options[i].selected) {
					selectedSearchOptions.push(options[i].value);
				}
			}

			this.setState({
				formValues: {
					...formValues,
					envSearch: selectedSearchOptions
				}
			});
		} else {
			const fieldName = this.updateFieldName(name);

			this.setState({
				formValues: {
					...formValues,
					[fieldName]: event.target.value
				}
			});
		}

		this.formikInstanceRef.current.handleChange(event);
	};

	handleSubmit = () => this.editEnvironmentFormRef.current.submit();

	getSearchValues = (selectedLFRVersion, searchLabelsArray) => {
		const {environment} = this.props;

		const {enterprise} = this.state.configurations;

		const searchValuesArray = [];

		if (selectedLFRVersion && selectedLFRVersion.envSearch) {
			const environmentVersionName = environment.envLFRLabel;

			const searchType = selectedLFRVersion.envSearch.find(
				search => search[enterprise ? 'enterprise' : 'standard']
			)[enterprise ? 'enterprise' : 'standard'];

			const selectedVersionName = selectedLFRVersion.envLFR.find(
				version => version
			).name;

			if (selectedVersionName === environmentVersionName) {
				let searchValue;

				for (let i = 0; i < searchLabelsArray.length; i++) {
					searchValue = searchType.find(
						type => type.name === searchLabelsArray[i]
					).value;

					searchValuesArray.push(searchValue);
				}
			}
		}

		return searchValuesArray;
	};

	getSelectionValueFromLabel = (selectedLFRVersion, envType, label) => {
		if (selectedLFRVersion && selectedLFRVersion[envType]) {
			const selectedVersionTypeLabel = selectedLFRVersion[envType].find(
				type => type.name === label
			);

			return selectedVersionTypeLabel
				? selectedVersionTypeLabel.value
				: '';
		}
	};

	updateFieldName = name => {
		const {namespace} = this.props;

		return name.replace(namespace, '');
	};

	render() {
		const {
			addEnvironmentURL,
			environment,
			environmentConfiguration,
			handleCloseModal,
			namespace
		} = this.props;

		const {configurations, formValues, selectedOptions} = this.state;

		const {products} = environmentConfiguration;

		const {
			selectedCommerceVersion,
			selectedLFRVersion,
			selectedProduct
		} = selectedOptions;
		const {customOS, enterprise} = configurations;

		const actionURL = environment
			? environment.editAccountEnvironmentURL
			: addEnvironmentURL;
		const commerceProduct = selectedProduct && selectedProduct.envCommerce;
		const renderEnvCS = selectedLFRVersion && selectedLFRVersion.envCS;
		const renderEnvOSCustom = customOS;
		const renderEnvSearch =
			selectedLFRVersion &&
			selectedProduct &&
			'enterpriseSearch' in selectedProduct;
		const selectedLRProduct = commerceProduct
			? selectedCommerceVersion
			: selectedProduct;

		const initialValues = {
			[`${namespace}envAS`]: formValues.envAS,
			[`${namespace}envBrowser`]: formValues.envBrowser,
			[`${namespace}envCommerce`]: formValues.envCommerce,
			[`${namespace}envCS`]: formValues.envCS,
			[`${namespace}envDB`]: formValues.envDB,
			[`${namespace}envJVM`]: formValues.envJVM,
			[`${namespace}envLFR`]: formValues.envLFR,
			[`${namespace}envOS`]: formValues.envOS,
			[`${namespace}envSearch`]: formValues.envSearch,
			[`${namespace}name`]: formValues.name,
			[`${namespace}productEntryId`]: formValues.productEntryId
		};

		const validationSchema = yup.object().shape({
			[`${namespace}envAS`]: REQUIRED_SCHEMA,
			[`${namespace}envCommerce`]: commerceProduct
				? REQUIRED_SCHEMA
				: NOT_REQUIRED_SCHEMA,
			[`${namespace}envDB`]: REQUIRED_SCHEMA,
			[`${namespace}envJVM`]: REQUIRED_SCHEMA,
			[`${namespace}envLFR`]: REQUIRED_SCHEMA,
			[`${namespace}envOS`]: REQUIRED_SCHEMA,
			[`${namespace}name`]: REQUIRED_SCHEMA,
			[`${namespace}productEntryId`]: REQUIRED_SCHEMA
		});

		return (
			<Formik
				ref={this.formikInstanceRef}
				enableReinitialize
				initialValues={initialValues}
				onSubmit={this.handleSubmit}
				render={({
					errors,
					handleBlur,
					handleChange,
					handleSubmit,
					isSubmitting,
					touched,
					values
				}) => (
					<form
						ref={this.editEnvironmentFormRef}
						action={actionURL}
						encType="multipart/form-data"
						method="post"
						onSubmit={handleSubmit}
					>
						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}accountEnvironmentName`}
									>
										{Liferay.Language.get('name')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<input
										className="form-control"
										id={`${namespace}accountEnvironmentName`}
										name={`${namespace}name`}
										onBlur={handleBlur}
										onChange={this.handleInputChange}
										type="text"
										value={formValues.name}
									/>
								</div>

								{touched[`${namespace}name`] &&
									errors[`${namespace}name`] && (
										<Alert type="danger">
											{errors[`${namespace}name`]}
										</Alert>
									)}
							</div>

							<div
								className={
									commerceProduct ? 'col-md-12' : 'col-md-9'
								}
							>
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}accountEnvironmentProduct`}
									>
										{Liferay.Language.get('product')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										id={`${namespace}accountEnvironmentProduct`}
										name={`${namespace}productEntryId`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.productEntryId}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{products.map(product => (
											<option
												key={product.productEntryId}
												id={
													'product-' +
													product.productEntryId
												}
												label={product.displayName}
												value={product.productEntryId}
											>
												{product.displayName}
											</option>
										))}
									</select>
								</div>

								{touched[`${namespace}productEntryId`] &&
									errors[`${namespace}productEntryId`] && (
										<Alert type="danger">
											{
												errors[
													`${namespace}productEntryId`
												]
											}
										</Alert>
									)}
							</div>

							{commerceProduct && (
								<div className="col-md-6">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${namespace}envCommerce`}
										>
											{Liferay.Language.get(
												'commerce-version'
											)}

											<svg className="lexicon-icon lexicon-icon-asterisk">
												<use xlinkHref="#asterisk" />
											</svg>
										</label>

										<select
											className="form-control"
											disabled={!selectedProduct}
											id={`${namespace}envCommerce`}
											name={`${namespace}envCommerce`}
											onBlur={handleBlur}
											onChange={this.handleSelectChange}
											value={formValues.envCommerce}
										>
											<option
												label={SELECT_LABEL}
												value=""
											>
												{SELECT_LABEL}
											</option>

											{commerceProduct.map(version => (
												<option
													key={version.value}
													id={
														'envCommerce-' +
														version.value
													}
													label={version.name}
													value={version.value}
												>
													{version.name}
												</option>
											))}
										</select>
									</div>

									{touched[`${namespace}envCommerce`] &&
										errors[`${namespace}envCommerce`] && (
											<Alert type="danger">
												{
													errors[
														`${namespace}envCommerce`
													]
												}
											</Alert>
										)}
								</div>
							)}

							<div
								className={
									commerceProduct ? 'col-md-6' : 'col-md-3'
								}
							>
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envLFR`}
									>
										{Liferay.Language.get(
											'liferay-version'
										)}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLRProduct}
										id={`${namespace}envLFR`}
										name={`${namespace}envLFR`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.envLFR}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLRProduct &&
											selectedLRProduct.envLFR.map(
												version => (
													<option
														key={version.value}
														id={
															'envLFR-' +
															version.value
														}
														label={version.name}
														value={version.value}
													>
														{version.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envLFR`] &&
									errors[`${namespace}envLFR`] && (
										<Alert type="danger">
											{errors[`${namespace}envLFR`]}
										</Alert>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envOS`}
									>
										{Liferay.Language.get(
											'operating-system'
										)}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${namespace}envOS`}
										name={`${namespace}envOS`}
										onBlur={handleBlur}
										onChange={this.handleOSChange}
										value={formValues.envOS}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLFRVersion &&
											selectedLFRVersion.envOS.map(
												envOS => (
													<option
														key={envOS.value}
														id={
															'envOS-' +
															envOS.value
														}
														label={envOS.name}
														value={envOS.value}
													>
														{envOS.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envOS`] &&
									errors[`${namespace}envOS`] && (
										<Alert type="danger">
											{errors[`${namespace}envOS`]}
										</Alert>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envJVM`}
									>
										{Liferay.Language.get('java-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${namespace}envJVM`}
										name={`${namespace}envJVM`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.envJVM}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLFRVersion &&
											selectedLFRVersion.envJVM.map(
												envJVM => (
													<option
														key={envJVM.value}
														id={
															'envJVM-' +
															envJVM.value
														}
														label={envJVM.name}
														value={envJVM.value}
													>
														{envJVM.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envJVM`] &&
									errors[`${namespace}envJVM`] && (
										<Alert type="danger">
											{errors[`${namespace}envJVM`]}
										</Alert>
									)}
							</div>

							{renderEnvOSCustom && (
								<div className="col-md-12">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${namespace}envOSCustom`}
										>
											{Liferay.Language.get(
												'custom-operating-system'
											)}
										</label>

										<input
											className="form-control"
											id={`${namespace}envOSCustom`}
											name={`${namespace}envOSCustom`}
											onBlur={handleBlur}
											onChange={handleChange}
											type="text"
											value={values.envOSCustom}
										/>
									</div>
								</div>
							)}

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envAS`}
									>
										{Liferay.Language.get(
											'application-server'
										)}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${namespace}envAS`}
										name={`${namespace}envAS`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.envAS}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLFRVersion &&
											selectedLFRVersion.envAS.map(
												envAS => (
													<option
														key={envAS.value}
														id={
															'envAS-' +
															envAS.value
														}
														label={envAS.name}
														value={envAS.value}
													>
														{envAS.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envAS`] &&
									errors[`${namespace}envAS`] && (
										<Alert type="danger">
											{errors[`${namespace}envAS`]}
										</Alert>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envDB`}
									>
										{Liferay.Language.get('database')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${namespace}envDB`}
										name={`${namespace}envDB`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.envDB}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLFRVersion &&
											selectedLFRVersion.envDB.map(
												envDB => (
													<option
														key={envDB.value}
														id={
															'envDB-' +
															envDB.value
														}
														label={envDB.name}
														value={envDB.value}
													>
														{envDB.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envDB`] &&
									errors[`${namespace}envDB`] && (
										<Alert type="danger">
											{errors[`${namespace}envDB`]}
										</Alert>
									)}
							</div>

							{renderEnvCS && (
								<div className="col-md-6">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${namespace}envCS`}
										>
											{Liferay.Language.get(
												'cloud-services'
											)}
										</label>

										<select
											className="form-control"
											disabled={!selectedLFRVersion}
											id={`${namespace}envCS`}
											name={`${namespace}envCS`}
											onBlur={handleBlur}
											onChange={this.handleSelectChange}
											value={formValues.envCS}
										>
											<option
												label={SELECT_LABEL}
												value=""
											>
												{SELECT_LABEL}
											</option>

											{selectedLFRVersion &&
												selectedLFRVersion.envCS.map(
													envCS => (
														<option
															key={envCS.value}
															id={
																'envCS-' +
																envCS.value
															}
															label={envCS.name}
															value={envCS.value}
														>
															{envCS.name}
														</option>
													)
												)}
										</select>
									</div>

									{touched[`${namespace}envCS`] &&
										errors[`${namespace}envCS`] && (
											<Alert type="danger">
												{errors[`${namespace}envCS`]}
											</Alert>
										)}
								</div>
							)}

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${namespace}envBrowser`}
									>
										{Liferay.Language.get('browser')}
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${namespace}envBrowser`}
										name={`${namespace}envBrowser`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
										value={formValues.envBrowser}
									>
										<option label={SELECT_LABEL} value="">
											{SELECT_LABEL}
										</option>

										{selectedLFRVersion &&
											selectedLFRVersion.envBrowser.map(
												envBrowser => (
													<option
														key={envBrowser.value}
														id={
															'envBrowser-' +
															envBrowser.value
														}
														label={envBrowser.name}
														value={envBrowser.value}
													>
														{envBrowser.name}
													</option>
												)
											)}
									</select>
								</div>

								{touched[`${namespace}envBrowser`] &&
									errors[`${namespace}envBrowser`] && (
										<Alert type="danger">
											{errors[`${namespace}envBrowser`]}
										</Alert>
									)}
							</div>

							{renderEnvSearch && (
								<div className="col-md-12">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${namespace}envSearch`}
										>
											{Liferay.Language.get('search')}
										</label>

										<select
											className="form-control"
											disabled={!selectedLFRVersion}
											id={`${namespace}envSearch`}
											multiple
											name={`${namespace}envSearch`}
											onBlur={handleBlur}
											onChange={this.handleSelectChange}
											value={formValues.envSearch}
										>
											{selectedLFRVersion.envSearch
												.find(
													search =>
														search[
															enterprise
																? 'enterprise'
																: 'standard'
														]
												)
												[
													enterprise
														? 'enterprise'
														: 'standard'
												].map(envSearch => (
													<option
														key={envSearch.value}
														id={
															'envSearch-' +
															envSearch.value
														}
														label={envSearch.name}
														value={envSearch.value}
													>
														{envSearch.name}
													</option>
												))}
										</select>
									</div>

									{touched[`${namespace}envSearch`] &&
										errors[`${namespace}envSearch`] && (
											<Alert type="danger">
												{
													errors[
														`${namespace}envSearch`
													]
												}
											</Alert>
										)}
								</div>
							)}
						</div>

						<div className="btn-row">
							<Button
								display="outline"
								onClick={handleCloseModal}
								type="button"
							>
								{Liferay.Language.get('cancel')}
							</Button>

							<Button disabled={isSubmitting} type="submit">
								{Liferay.Language.get('save')}
							</Button>
						</div>
					</form>
				)}
				validationSchema={validationSchema}
			/>
		);
	}
}
