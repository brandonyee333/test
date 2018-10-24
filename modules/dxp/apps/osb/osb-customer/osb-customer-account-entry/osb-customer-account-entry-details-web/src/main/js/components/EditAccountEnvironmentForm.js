import React from 'react';
import PropTypes from 'prop-types';

import {Formik} from 'formik';
import * as yup from 'yup';

import Alert from './Alert';
import Button from './Button';

const REQUIRED_SCHEMA = yup.string().required(Liferay.Language.get('this-field-is-required'));

const SELECT_LABEL = Liferay.Language.get('select');

export default class EditAccountEnvironmentForm extends React.Component {
	editEnvironmentFormRef = React.createRef();
	formikInstanceRef = React.createRef();
	patchLevelRef = React.createRef();
	portalExtRef = React.createRef();

	state = {
		configurations: {
			customOS: false,
			enterprise: false
		},
		formValues: {
			envAS: '',
			envBrowser: '',
			envCS: '',
			envDB: '',
			envJVM: '',
			envLFR: '',
			envOS: '',
			envSearch: [],
			name: '',
			productEntryId: ''
		},
		inputFileField: {
			patchLevel: null,
			portalExt: null
		},
		selectedOptions: {
			selectedLFRVersion: null,
			selectedProduct: null
		}
	};

	static propTypes = {
		addEnvironmentURL: PropTypes.string.isRequired,
		environment: PropTypes.object,
		environmentConfiguration: PropTypes.object.isRequired,
		handleCloseModal: PropTypes.func.isRequired,
		namespace: PropTypes.string.isRequired
	};

	componentDidMount() {
		const {environment, environmentConfiguration} = this.props;
		const {envLFRVersions, products} = environmentConfiguration;

		const {configurations, selectedOptions, formValues} = this.state;

		if (environment) {
			const currentProduct = products.find(product => product.productEntryId == environment.productEntryId);
			const currentLFRValue = currentProduct.envLFR.find(version => version.name == environment.envLFRLabel).value;
			const currentLFRVersion = envLFRVersions.find(version => version[currentLFRValue])[currentLFRValue];

			this.setState(
				{
					configurations: {
						...configurations,
						enterprise: currentProduct.enterpriseSearch
					},
					formValues: {
						...formValues,
						envAS: this.getValueFromLabel(currentLFRVersion, 'envAS', environment.envASLabel),
						envBrowser: this.getValueFromLabel(currentLFRVersion, 'envBrowser', environment.envBrowserLabel),
						envCS: this.getValueFromLabel(currentLFRVersion, 'envCS', environment.envCSLabel),
						envDB: this.getValueFromLabel(currentLFRVersion, 'envDB', environment.envDBLabel),
						envJVM: this.getValueFromLabel(currentLFRVersion, 'envJVM', environment.envJVMLabel),
						envLFR: this.getValueFromLabel(currentLFRVersion, 'envLFR', environment.envLFRLabel),
						envOS: this.getValueFromLabel(currentLFRVersion, 'envOS', environment.envOSLabel),
						envSearch: this.getSearchValues(currentLFRVersion, environment.envSearchLabels),
						name: environment.name,
						productEntryId: environment.productEntryId
					},
					selectedOptions: {
						...selectedOptions,
						selectedLFRVersion: currentLFRVersion,
						selectedProduct: currentProduct
					}
				}
			);
		}
	};

	handleDeleteFile = fileRef => {
		const {inputFileField} = this.state;

		const fileInput = fileRef.current;

		const fieldName = this.updateFieldName(fileInput.name);

		fileInput.value = null;

		this.setState(
			{
				inputFileField: {
					...inputFileField,
					[fieldName]: null
				}
			}
		);

		this.formikInstanceRef.current.setFieldValue(this.props.namespace + fieldName, '');
	};

	handleFileChange = event => {
		const {inputFileField} = this.state;

		const {files, name} = event.target;
		const fieldName = this.updateFieldName(name);

		if (files.length) {
			this.setState(
				{
					inputFileField: {
						...inputFileField,
						[fieldName]: files[0].name
					}
				}
			);
		}

		this.formikInstanceRef.current.handleChange(event);
	};

	handleInputChange = event => {
		const {formValues} = this.state;

		const fieldName = this.updateFieldName(event.target.name);

		this.setState(
			{
				formValues: {
					...formValues,
					[fieldName]: event.target.value
				}
			}
		);

		this.formikInstanceRef.current.handleChange(event);
	};

	handleOSChange = event => {
		const {configurations, formValues} = this.state;

		const {options} = event.target;
		const {label} = options[options.selectedIndex];

		this.setState(
			{
				configurations: {
					...configurations,
					customOS: label === 'Other'
				},
				formValues: {
					...formValues,
					envOS: event.target.value
				}
			}
		);

		this.formikInstanceRef.current.handleChange(event);
	};

	handleSelectChange = event => {
		const {envLFRVersions, products} = this.props.environmentConfiguration;

		const {configurations, formValues, selectedOptions} = this.state;

		const {name, options} = event.target;
		const {value} = options[options.selectedIndex];

		if (name === `${this.props.namespace}productEntryId`) {
			const currentProduct = products.find(product => product.productEntryId === value);

			this.setState(
				{
					configurations: {
						...configurations,
						enterprise: !!currentProduct && currentProduct.enterpriseSearch
					},
					formValues: {
						...formValues,
						envLFR: '',
						productEntryId: event.target.value
					},
					selectedOptions: {
						...selectedOptions,
						selectedLFRVersion: null,
						selectedProduct: currentProduct
					}
				}
			);
		}
		else if (name === `${this.props.namespace}envLFR`) {
			const currentLFRVersion = envLFRVersions.find(version => version[value]);

			this.setState(
				{
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
						selectedLFRVersion: currentLFRVersion ? currentLFRVersion[value] : null
					}
				}
			);
		}
		else if (name === `${this.props.namespace}envSearch`) {
			const selectedSearchOptions = [];

			for (var i = 0; i < options.length; i++) {
				if (options[i].selected) {
					selectedSearchOptions.push(options[i].value);
				}
			}

			this.setState(
				{
					formValues: {
						...formValues,
						envSearch: selectedSearchOptions
					}
				}
			);
		}
		else {
			const fieldName = this.updateFieldName(name);

			this.setState(
				{
					formValues: {
						...formValues,
						[fieldName]: event.target.value
					}
				}
			);
		};

		this.formikInstanceRef.current.handleChange(event);
	};

	handleSubmit = () => this.editEnvironmentFormRef.current.submit();

	getSearchValues = (selectedLFRVersion, searchLabelsArray) => {
		const {environment} = this.props;

		const {enterprise} = this.state.configurations;

		const searchValuesArray = [];

		if (selectedLFRVersion && selectedLFRVersion.envSearch) {
			const environmentVersionName = environment.envLFRLabel;
			const searchType = selectedLFRVersion.envSearch.find(search => search[enterprise ? 'enterprise' : 'standard'])[enterprise ? 'enterprise' : 'standard'];
			const selectedVersionName = selectedLFRVersion.envLFR.find(version => version).name;

			if (selectedVersionName == environmentVersionName) {
				for (var i = 0; i < searchLabelsArray.length; i++) {
					const searchValue = searchType.find(type => type.name == searchLabelsArray[i]).value;

					searchValuesArray.push(searchValue);
				}
			}
		}

		return searchValuesArray;
	};

	getValueFromLabel = (selectedLFRVersion, envType, label) => {
		if (selectedLFRVersion && selectedLFRVersion[envType]) {
			const selectedVersionTypeLabel = selectedLFRVersion[envType].find(type => type.name == label);

			return selectedVersionTypeLabel ? selectedVersionTypeLabel.value : '';
		}
	};

	updateFieldName = (name) => {
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

		const {
			configurations,
			formValues,
			inputFileField,
			selectedOptions
		} = this.state;

		const {products} = environmentConfiguration;

		const {selectedLFRVersion, selectedProduct} = selectedOptions;
		const {customOS, enterprise} = configurations;
		const {patchLevel, portalExt} = inputFileField;

		const actionURL = environment ? environment.editAccountEnvironmentURL : addEnvironmentURL;
		const renderEnvCS = selectedLFRVersion && selectedLFRVersion.envCS;
		const renderEnvOSCustom = customOS;
		const renderEnvSearch = selectedLFRVersion && selectedProduct && 'enterpriseSearch' in selectedProduct;

		const initialValues = {
			[`${namespace}envAS`]: formValues.envAS,
			[`${namespace}envBrowser`]: formValues.envBrowser,
			[`${namespace}envCS`]: formValues.envCS,
			[`${namespace}envDB`]: formValues.envDB,
			[`${namespace}envJVM`]: formValues.envJVM,
			[`${namespace}envLFR`]: formValues.envLFR,
			[`${namespace}envOS`]: formValues.envOS,
			[`${namespace}envSearch`]: formValues.envSearch,
			[`${namespace}name`]: formValues.name,
			[`${namespace}patchLevel`]: '',
			[`${namespace}portalExt`]: '',
			[`${namespace}productEntryId`]: formValues.productEntryId
		};

		const validationSchema = yup.object().shape(
			{
				[`${namespace}envAS`]: REQUIRED_SCHEMA,
				[`${namespace}envDB`]: REQUIRED_SCHEMA,
				[`${namespace}envJVM`]: REQUIRED_SCHEMA,
				[`${namespace}envLFR`]: REQUIRED_SCHEMA,
				[`${namespace}envOS`]: REQUIRED_SCHEMA,
				[`${namespace}name`]: REQUIRED_SCHEMA,
				[`${namespace}patchLevel`]: REQUIRED_SCHEMA,
				[`${namespace}portalExt`]: REQUIRED_SCHEMA,
				[`${namespace}productEntryId`]: REQUIRED_SCHEMA
			}
		);

		return (
			<Formik
				enableReinitialize={true}
				initialValues={initialValues}
				onSubmit={this.handleSubmit}
				ref={this.formikInstanceRef}
				render={({
					errors,
					handleBlur,
					handleChange,
					handleSubmit,
					isSubmitting,
					touched,
					values
				}) => (
					<form action={actionURL} encType="multipart/form-data" method="post" onSubmit={handleSubmit} ref={this.editEnvironmentFormRef}>
						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}accountEnvironmentName`}>
										{Liferay.Language.get('name')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<input className="form-control" id={`${namespace}accountEnvironmentName`} name={`${namespace}name`} onBlur={handleBlur} onChange={this.handleInputChange} type="text" value={formValues.name} />
								</div>

								{touched[`${namespace}name`] && errors[`${namespace}name`] && (
									<Alert type="danger">
										{errors[`${namespace}name`]}
									</Alert>
								)}
							</div>

							<div className="col-md-9">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}accountEnvironmentProduct`}>
										{Liferay.Language.get('product')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" id={`${namespace}accountEnvironmentProduct`} name={`${namespace}productEntryId`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.productEntryId}>
										<option label={SELECT_LABEL} value="" />

										{products.map(
											(product) => (
												<option key={product.productEntryId} id={'product-' + product.productEntryId} label={product.displayName} value={product.productEntryId} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}productEntryId`] && errors[`${namespace}productEntryId`] && (
									<Alert type="danger">
										{errors[`${namespace}productEntryId`]}
									</Alert>
								)}
							</div>

							<div className="col-md-3">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envLFR`}>
										{Liferay.Language.get('liferay-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" disabled={!selectedProduct} id={`${namespace}envLFR`} name={`${namespace}envLFR`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envLFR}>
										<option label={SELECT_LABEL} value="" />

										{selectedProduct && selectedProduct.envLFR.map(
											(version) => (
												<option key={version.value} id={'envLFR-' + version.value} label={version.name} value={version.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envLFR`] && errors[`${namespace}envLFR`] && (
									<Alert type="danger">
										{errors[`${namespace}envLFR`]}
									</Alert>
								)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envOS`}>
										{Liferay.Language.get('operating-system')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envOS`} name={`${namespace}envOS`} onBlur={handleBlur} onChange={this.handleOSChange} value={formValues.envOS}>
										<option label={SELECT_LABEL} value="" />

										{selectedLFRVersion && selectedLFRVersion.envOS.map(
											(envOS) => (
												<option key={envOS.value} id={'envOS-' + envOS.value} label={envOS.name} value={envOS.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envOS`] && errors[`${namespace}envOS`] && (
									<Alert type="danger">
										{errors[`${namespace}envOS`]}
									</Alert>
								)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envJVM`}>
										{Liferay.Language.get('java-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envJVM`} name={`${namespace}envJVM`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envJVM}>
										<option label={SELECT_LABEL} value="" />

										{selectedLFRVersion && selectedLFRVersion.envJVM.map(
											(envJVM) => (
												<option key={envJVM.value} id={'envJVM-' + envJVM.value} label={envJVM.name} value={envJVM.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envJVM`] && errors[`${namespace}envJVM`] && (
									<Alert type="danger">
										{errors[`${namespace}envJVM`]}
									</Alert>
								)}
							</div>

							{renderEnvOSCustom && (
								<div className="col-md-12">
									<div className="form-group">
										<label className="control-label" htmlFor={`${namespace}envOSCustom`}>
											{Liferay.Language.get('custom-operating-system')}
										</label>

										<input className="form-control" id={`${namespace}envOSCustom`} name={`${namespace}envOSCustom`} onBlur={handleBlur} onChange={handleChange} type="text" value={values.envOSCustom} />
									</div>
								</div>
							)}

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envAS`}>
										{Liferay.Language.get('application-server')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envAS`} name={`${namespace}envAS`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envAS}>
										<option label={SELECT_LABEL} value="" />

										{selectedLFRVersion && selectedLFRVersion.envAS.map(
											(envAS) => (
												<option key={envAS.value} id={'envAS-' + envAS.value} label={envAS.name} value={envAS.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envAS`] && errors[`${namespace}envAS`] && (
									<Alert type="danger">
										{errors[`${namespace}envAS`]}
									</Alert>
								)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envDB`}>
										{Liferay.Language.get('database')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envDB`} name={`${namespace}envDB`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envDB}>
										<option label={SELECT_LABEL} value="" />

										{selectedLFRVersion && selectedLFRVersion.envDB.map(
											(envDB) => (
												<option key={envDB.value} id={'envDB-' + envDB.value} label={envDB.name} value={envDB.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envDB`] && errors[`${namespace}envDB`] && (
									<Alert type="danger">
										{errors[`${namespace}envDB`]}
									</Alert>
								)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envBrowser`}>
										{Liferay.Language.get('browser')}
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envBrowser`} name={`${namespace}envBrowser`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envBrowser}>
										<option label={SELECT_LABEL} value="" />

										{selectedLFRVersion && selectedLFRVersion.envBrowser.map(
											(envBrowser) => (
												<option key={envBrowser.value} id={'envBrowser-' + envBrowser.value} label={envBrowser.name} value={envBrowser.value} />
											)
										)}
									</select>
								</div>

								{touched[`${namespace}envBrowser`] && errors[`${namespace}envBrowser`] && (
									<Alert type="danger">
										{errors[`${namespace}envBrowser`]}
									</Alert>
								)}
							</div>

							{renderEnvCS && (
								<div className="col-md-6">
									<div className="form-group">
										<label className="control-label" htmlFor={`${namespace}envCS`}>
											{Liferay.Language.get('cloud-services')}
										</label>

										<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envCS`} name={`${namespace}envCS`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envCS}>
											<option label={SELECT_LABEL} value="" />

											{selectedLFRVersion && selectedLFRVersion.envCS.map(
												(envCS) => (
													<option key={envCS.value} id={'envCS-' + envCS.value} label={envCS.name} value={envCS.value} />
												)
											)}
										</select>
									</div>

									{touched[`${namespace}envCS`] && errors[`${namespace}envCS`] && (
										<Alert type="danger">
											{errors[`${namespace}envCS`]}
										</Alert>
									)}
								</div>
							)}

							{renderEnvSearch && (
								<div className="col-md-12">
									<div className="form-group">
										<label className="control-label" htmlFor={`${namespace}envSearch`}>
											{Liferay.Language.get('search')}
										</label>

										<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envSearch`} multiple={true} name={`${namespace}envSearch`} onBlur={handleBlur} onChange={this.handleSelectChange} value={formValues.envSearch}>
											{selectedLFRVersion.envSearch.find(
												search => search[enterprise ? 'enterprise' : 'standard'])[enterprise ? 'enterprise' : 'standard'].map(
													(envSearch) => (
														<option key={envSearch.value} id={'envSearch-' + envSearch.value} label={envSearch.name} value={envSearch.value} />
													)
												)
											}
										</select>
									</div>

									{touched[`${namespace}envSearch`] && errors[`${namespace}envSearch`] && (
										<Alert type="danger">
											{errors[`${namespace}envSearch`]}
										</Alert>
									)}
								</div>
							)}

							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}portalExt`}>
										{Liferay.Language.get('portal-ext')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>

										<div className="uploadedPortalExt">
											{environment &&
												<a href={environment.portalExtAccountEnvironmentAttachmentURL}>
													{environment.portalExtAccountEnvironmentAttachmentFileName}
												</a>
											}
										</div>
									</label>

									<div className="upload-dropzone">
										<input className="form-control" id={`${namespace}portalExt`} name={`${namespace}portalExt`} onChange={this.handleFileChange} ref={this.portalExtRef} type="file" />

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-file-here')}
										</span>
									</div>

									{portalExt && (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													<span className="attachment-name">{portalExt}</span>

													<span className="icon-delete-file" onClick={() => this.handleDeleteFile(this.portalExtRef)}>
														<svg className="lexicon-icon lexicon-icon-times">
															<use xlinkHref="#times" />
														</svg>
													</span>
												</li>
											</ul>
										</div>
									)}
								</div>

								{touched[`${namespace}portalExt`] && errors[`${namespace}portalExt`] && (
									<Alert type="danger">
										{errors[`${namespace}portalExt`]}
									</Alert>
								)}
							</div>

							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}patchLevel`}>
										{Liferay.Language.get('patch-info')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>

										<div className="uploadedPatchLevel">
											{environment &&
												<a href={environment.patchLevelAccountEnvironmentAttachmentURL}>
													{environment.patchLevelAccountEnvironmentAttachmentFileName}
												</a>
											}
										</div>
									</label>

									<div className="upload-dropzone">
										<input className="form-control" id={`${namespace}patchLevel`} name={`${namespace}patchLevel`} onChange={this.handleFileChange} ref={this.patchLevelRef} type="file" />

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-file-here')}
										</span>
									</div>

									{patchLevel && (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													<span className="attachment-name">{patchLevel}</span>

													<span className="icon-delete-file" onClick={() => this.handleDeleteFile(this.patchLevelRef)}>
														<svg className="lexicon-icon lexicon-icon-times">
															<use xlinkHref="#times" />
														</svg>
													</span>
												</li>
											</ul>
										</div>
									)}
								</div>

								{touched[`${namespace}patchLevel`] && errors[`${namespace}patchLevel`] && (
									<Alert type="danger">
										{errors[`${namespace}patchLevel`]}
									</Alert>
								)}
							</div>
						</div>

						<div className="btn-row">
							<Button
								display="outline"
								onClick={handleCloseModal}
								type="button"
							>
								{Liferay.Language.get('cancel')}
							</Button>

							<Button
								disabled={isSubmitting}
								type="submit"
							>
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