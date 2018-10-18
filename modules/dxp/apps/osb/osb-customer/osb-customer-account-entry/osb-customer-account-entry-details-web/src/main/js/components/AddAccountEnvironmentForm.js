import React from 'react';
import PropTypes from 'prop-types';

import {Formik} from 'formik';
import * as yup from 'yup';

import Alert from './Alert';
import Button from './Button';

const REQUIRED_SCHEMA = yup.string().required(Liferay.Language.get('this-field-is-required'));

const SELECT_LABEL = Liferay.Language.get('select');

export default class AddAccountEnvironmentForm extends React.Component {
	addEnvironmentFormRef = React.createRef();
	patchLevelRef = React.createRef();
	portalExtRef = React.createRef();

	state = {
		configurations: {
			customOS: false,
			enterprise: false
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
		environmentConfiguration: PropTypes.object.isRequired,
		handleCloseModal: PropTypes.func.isRequired,
		namespace: PropTypes.string.isRequired
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

		this.refs.formikInstanceRef.setFieldValue(this.props.namespace + fieldName, '');
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

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleOSChange = event => {
		const {configurations} = this.state;

		const {options} = event.target;
		const {label} = options[options.selectedIndex];

		this.setState(
			{
				configurations: {
					...configurations,
					customOS: label === 'Other'
				}
			}
		);

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSelectChange = event => {
		const {envLFRVersions, products} = this.props.environmentConfiguration;

		const {configurations, selectedOptions} = this.state;

		const {name, options} = event.target;
		const {value} = options[options.selectedIndex];

		if (name === `${this.props.namespace}productEntryId`) {
			const curSelectedProduct = products.find(product => product.productEntryId === value);

			this.setState(
				{
					configurations: {
						...configurations,
						enterprise: !!curSelectedProduct && curSelectedProduct.enterpriseSearch
					},
					selectedOptions: {
						...selectedOptions,
						selectedLFRVersion: null,
						selectedProduct: curSelectedProduct
					}
				}
			);
		}
		else {
			const curLFRVersion = envLFRVersions.find(version => version[value]);

			this.setState(
				{
					selectedOptions: {
						...selectedOptions,
						selectedLFRVersion: curLFRVersion ? curLFRVersion[value] : null
					}
				}
			);
		}

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSubmit = () => this.addEnvironmentFormRef.current.submit();

	updateFieldName = (name) => {
		const {namespace} = this.props;

		return name.replace(namespace, '');
	}

	render() {
		const {
			addEnvironmentURL,
			environmentConfiguration,
			handleCloseModal,
			namespace
		} = this.props;

		const {
			configurations,
			inputFileField,
			selectedOptions
		} = this.state;

		const {products} = environmentConfiguration;

		const {selectedLFRVersion, selectedProduct} = selectedOptions;
		const {customOS, enterprise} = configurations;
		const {patchLevel, portalExt} = inputFileField;

		const renderEnvCS = selectedLFRVersion && selectedLFRVersion.envCS;
		const renderEnvOSCustom = customOS;
		const renderEnvSearch = selectedLFRVersion && selectedProduct && 'enterpriseSearch' in selectedProduct;

		const initialValues = {
			[`${namespace}envAS`]: '',
			[`${namespace}envBrowser`]: '',
			[`${namespace}envCS`]: '',
			[`${namespace}envDB`]: '',
			[`${namespace}envJVM`]: '',
			[`${namespace}envLFR`]: '',
			[`${namespace}envOS`]: '',
			[`${namespace}envOSCustom`]: '',
			[`${namespace}envSearch`]: '',
			[`${namespace}name`]: '',
			[`${namespace}patchLevel`]: '',
			[`${namespace}portalExt`]: '',
			[`${namespace}productEntryId`]: ''
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
				initialValues={initialValues}
				onSubmit={this.handleSubmit}
				ref="formikInstanceRef"
				render={({
					errors,
					handleBlur,
					handleChange,
					handleSubmit,
					isSubmitting,
					touched,
					values
				}) => (
					<form action={addEnvironmentURL} encType="multipart/form-data" method="post" onSubmit={handleSubmit} ref={this.addEnvironmentFormRef}>
						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}accountEnvironmentName`}>
										{Liferay.Language.get('name')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<input className="form-control" id={`${namespace}accountEnvironmentName`} name={`${namespace}name`} onBlur={handleBlur} onChange={handleChange} type="text" value={values.name} />
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

									<select className="form-control" id={`${namespace}accountEnvironmentProduct`} name={`${namespace}productEntryId`} onBlur={handleBlur} onChange={this.handleSelectChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedProduct} id={`${namespace}envLFR`} name={`${namespace}envLFR`} onBlur={handleBlur} onChange={this.handleSelectChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envOS`} name={`${namespace}envOS`} onBlur={handleBlur} onChange={this.handleOSChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envJVM`} name={`${namespace}envJVM`} onBlur={handleBlur} onChange={handleChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envAS`} name={`${namespace}envAS`} onBlur={handleBlur} onChange={handleChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envDB`} name={`${namespace}envDB`} onBlur={handleBlur} onChange={handleChange}>
										<option value="" label={SELECT_LABEL} />

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

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envBrowser`} name={`${namespace}envBrowser`} onBlur={handleBlur} onChange={handleChange}>
										<option value="" label={SELECT_LABEL} />

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

										<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envCS`} name={`${namespace}envCS`} onBlur={handleBlur} onChange={handleChange}>
											<option value="" label={SELECT_LABEL} />

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

										<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envSearch`} multiple={true} name={`${namespace}envSearch`} onBlur={handleBlur} onChange={handleChange}>
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