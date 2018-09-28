import React from 'react';

import {Formik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

export default class AddAccountEnvironmentForm extends React.Component {
	addEnvironmentFormRef = React.createRef();
	patchLevelRef = React.createRef();
	portalExtRef = React.createRef();

	state = {
		isCustomOS: false,
		isEnterprise: false,
		patchLevel: null,
		portalExt: null,
		selectedLFRVersion: null,
		selectedProduct: null
	};

	handleDeleteFile = fileRef => {
		const fileInput = fileRef.current;
		const fileInputName = fileRef.current.name.replace(`${this.props.namespace}`, '');

		fileInput.value = null;

		this.setState(
			{
				[fileInputName]: null
			}
		);

		this.refs.formikInstanceRef.setFieldValue(`${this.props.namespace + fileInputName}`, '');
	};

	handleFileChange = event => {
		const {files, name} = event.target;
		const fileName = name.replace(`${this.props.namespace}`, '');

		if (files.length) {
			this.setState(
				{
					[fileName]: files[0].name
				}
			);
		}

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleOSChange = event => {
		const {options} = event.target;
		const {label} = options[options.selectedIndex];

		this.setState(
			{
				isCustomOS: label == "Other"
			}
		);

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSelectChange = event => {
		const {envLFRVersions, products} = this.props.environmentConfiguration;
		const {name, options} = event.target;
		const {value} = options[options.selectedIndex];

		const selectedProduct = products.find(product => product.productEntryId == value);

		if (name == `${this.props.namespace}productEntryId`) {
			this.setState(
				{
					isEnterprise: !!selectedProduct && selectedProduct.enterpriseSearch,
					selectedProduct: selectedProduct
				}
			);
		}
		else {
			this.setState(
				{
					selectedLFRVersion: envLFRVersions.find(version => version[value])[value]
				}
			);
		};

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSubmit = () => this.addEnvironmentFormRef.current.submit();

	render() {
		const {
			addEnvironmentURL,
			environmentConfiguration,
			handleCloseModal,
			namespace
		} = this.props;

		const {
			isCustomOS,
			isEnterprise,
			patchLevel,
			portalExt,
			selectedLFRVersion,
			selectedProduct
		} = this.state;

		const {products} = environmentConfiguration;
		const renderEnvCS = selectedLFRVersion && selectedLFRVersion.envCS;
		const renderEnvSearch = selectedLFRVersion && selectedProduct && 'enterpriseSearch' in selectedProduct;
		const renderEnvOSCustom = isCustomOS;

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

		const requiredSchema = yup.string().required(Liferay.Language.get('this-field-is-required'));

		const validationSchema = yup.object().shape({
			[`${namespace}envAS`]: requiredSchema,
			[`${namespace}envDB`]: requiredSchema,
			[`${namespace}envJVM`]: requiredSchema,
			[`${namespace}envLFR`]: requiredSchema,
			[`${namespace}envOS`]: requiredSchema,
			[`${namespace}envOSCustom`]: renderEnvOSCustom ? requiredSchema : yup.mixed().notRequired(),
			[`${namespace}name`]: requiredSchema,
			[`${namespace}patchLevel`]: requiredSchema,
			[`${namespace}portalExt`]: requiredSchema,
			[`${namespace}productEntryId`]: requiredSchema
		});

		return (
			<Formik
				initialValues={initialValues}
				onSubmit={this.handleSubmit}
				ref="formikInstanceRef"
				validationSchema={validationSchema}
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
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}name`]}
									</div>
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
										<option value="" label={Liferay.Language.get('select')} />

										{products.map((product, index) => (
											<option key={'productEntryId-' + index} id={'productEntryId-' + index} label={product.displayName} value={product.productEntryId} />
										))}
									</select>
								</div>

								{touched[`${namespace}productEntryId`] && errors[`${namespace}productEntryId`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}productEntryId`]}
									</div>
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
										<option value="" label={Liferay.Language.get('select')} />

										{selectedProduct && selectedProduct.envLFR.map((version, index) => (
											<option key={'envLFR-' + index} id={'envLFR-' + index} label={version.name} value={version.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envLFR`] && errors[`${namespace}envLFR`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envLFR`]}
									</div>
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
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion && selectedLFRVersion.envOS.map((envOS, index) => (
											<option key={'envOS-' + index} id={'envOS-' + index} label={envOS.name} value={envOS.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envOS`] && errors[`${namespace}envOS`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envOS`]}
									</div>
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
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion && selectedLFRVersion.envJVM.map((envJVM, index) => (
											<option key={'envJVM-' + index} id={'envJVM-' + index} label={envJVM.name} value={envJVM.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envJVM`] && errors[`${namespace}envJVM`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envJVM`]}
									</div>
								)}
							</div>

							{renderEnvOSCustom && (
								<div className="col-md-12">
									<div className="form-group">
										<label className="control-label" htmlFor={`${namespace}envOSCustom`}>
											{Liferay.Language.get('custom-operating-system')}

											<svg className="lexicon-icon lexicon-icon-asterisk">
												<use xlinkHref="#asterisk" />
											</svg>
										</label>

										<input className="form-control" id={`${namespace}envOSCustom`} name={`${namespace}envOSCustom`} onBlur={handleBlur} onChange={handleChange} type="text" value={values.envOSCustom} />
									</div>

									{touched[`${namespace}envOSCustom`] && errors[`${namespace}envOSCustom`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${namespace}envOSCustom`]}
										</div>
									)}
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
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion && selectedLFRVersion.envAS.map((envAS, index) => (
											<option key={'envAS-' + index} id={'envAS-' + index} label={envAS.name} value={envAS.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envAS`] && errors[`${namespace}envAS`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envAS`]}
									</div>
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
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion && selectedLFRVersion.envDB.map((envDB, index) => (
											<option key={'envDB-' + index} id={'envDB-' + index} label={envDB.name} value={envDB.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envDB`] && errors[`${namespace}envDB`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envDB`]}
									</div>
								)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor={`${namespace}envBrowser`}>
										{Liferay.Language.get('browser')}
									</label>

									<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envBrowser`} name={`${namespace}envBrowser`} onBlur={handleBlur} onChange={handleChange}>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion && selectedLFRVersion.envBrowser.map((envBrowser, index) => (
											<option key={'envBrowser-' + index} id={'envBrowser-' + index} label={envBrowser.name} value={envBrowser.value} />
										))}
									</select>
								</div>

								{touched[`${namespace}envBrowser`] && errors[`${namespace}envBrowser`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}envBrowser`]}
									</div>
								)}
							</div>

							{renderEnvCS && (
								<div className="col-md-6">
									<div className="form-group">
										<label className="control-label" htmlFor={`${namespace}envCS`}>
											{Liferay.Language.get('cloud-services')}
										</label>

										<select className="form-control" disabled={!selectedLFRVersion} id={`${namespace}envCS`} name={`${namespace}envCS`} onBlur={handleBlur} onChange={handleChange}>
											<option value="" label={Liferay.Language.get('select')} />

											{selectedLFRVersion && selectedLFRVersion.envCS.map((envCS, index) => (
												<option key={'envCS-' + index} id={'envCS-' + index} label={envCS.name} value={envCS.value} />
											))}
										</select>
									</div>

									{touched[`${namespace}envCS`] && errors[`${namespace}envCS`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${namespace}envCS`]}
										</div>
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
												search => search[isEnterprise ? 'enterprise' : 'standard'])[isEnterprise ? 'enterprise' : 'standard'].map(
													(envSearch, index) => (
														<option key={'envSearch-' + index} id={'envSearch-' + index} label={envSearch.name} value={envSearch.value} />
													)
												)
											}
										</select>
									</div>

									{touched[`${namespace}envSearch`] && errors[`${namespace}envSearch`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${namespace}envSearch`]}
										</div>
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
										<input className="form-control" id={`${namespace}portalExt`} name={`${namespace}portalExt`} onBlur={handleBlur} onChange={this.handleFileChange} ref={this.portalExtRef} type="file" />

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-files-here')}
										</span>
									</div>

									{portalExt && (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<Button
														display="outline"
														onClick={() => this.handleDeleteFile(this.portalExtRef)}
														size="sm"
														type="button"
													>
														<svg className="lexicon-icon lexicon-icon-paperclip">
															<use xlinkHref="#paperclip" />
														</svg>

														<span className="attachment-name">{portalExt}</span>

														<svg className="lexicon-icon lexicon-icon-times">
															<use xlinkHref="#times" />
														</svg>
													</Button>
												</li>
											</ul>
										</div>
									)}
								</div>

								{touched[`${namespace}portalExt`] && errors[`${namespace}portalExt`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}portalExt`]}
									</div>
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
										<input className="form-control" id={`${namespace}patchLevel`} name={`${namespace}patchLevel`} onBlur={handleBlur} onChange={this.handleFileChange} ref={this.patchLevelRef} type="file" />

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-files-here')}
										</span>
									</div>

									{patchLevel && (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<Button
														display="outline"
														onClick={() => this.handleDeleteFile(this.patchLevelRef)}
														size="sm"
														type="button"
													>
														<svg className="lexicon-icon lexicon-icon-paperclip">
															<use xlinkHref="#paperclip" />
														</svg>

														<span className="attachment-name">{patchLevel}</span>

														<svg className="lexicon-icon lexicon-icon-times">
															<use xlinkHref="#times" />
														</svg>
													</Button>
												</li>
											</ul>
										</div>
									)}
								</div>

								{touched[`${namespace}patchLevel`] && errors[`${namespace}patchLevel`] && (
									<div className="alert alert-danger" role="alert">
										{errors[`${namespace}patchLevel`]}
									</div>
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

							<Button disabled={isSubmitting} type="submit">
								{Liferay.Language.get('save')}
							</Button>
						</div>
					</form>
				)}
			/>
		);
	}
}