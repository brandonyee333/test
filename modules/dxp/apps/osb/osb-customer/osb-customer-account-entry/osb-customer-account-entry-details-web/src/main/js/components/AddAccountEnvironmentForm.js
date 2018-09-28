import React from 'react';

import {Formik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

export default class AddAccountEnvironmentForm extends React.Component {
	addEnvironmentFormRef = React.createRef();
	patchLevelRef = React.createRef();
	portalExtRef = React.createRef();

	state = {
		isEnterprise: false,
		patchLevel: null,
		portalExt: null,
		selectedLFRVersion: null,
		selectedProduct: null
	};

	handleDeleteFile = fileRef => {
		const fileInput = fileRef.current;
		const fileInputName = fileRef.current.name.replace(
			`${this.props.portletNamespace}`, '');

		fileInput.value = null;

		this.setState(
			{
				[fileInputName]: null
			}
		);

		this.refs.formikInstanceRef.setFieldValue(
			`${this.props.portletNamespace + fileInputName}`, '');
	};

	handleFileChange = event => {
		const {files, name} = event.target;
		const fileName = name.replace(`${this.props.portletNamespace}`, '');

		if (files.length) {
			this.setState(
				{
					[fileName]: files[0].name
				}
			);
		}

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSelectChange = event => {
		const {envLFRVersions, products} = this.props.environmentConfiguration;
		const {name, options} = event.target;
		const {value} = options[options.selectedIndex];

		const selectedProduct = products.find(product =>
			product.productEntryId == value
		);

		if (name == `${this.props.portletNamespace}productEntryId`) {
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
			portletNamespace
		} = this.props;

		const {
			isEnterprise,
			patchLevel,
			portalExt,
			selectedLFRVersion,
			selectedProduct
		} = this.state;

		const initialValues = {
			[`${portletNamespace}envAS`]: '',
			[`${portletNamespace}envBrowser`]: '',
			[`${portletNamespace}envCS`]: '',
			[`${portletNamespace}envDB`]: '',
			[`${portletNamespace}envJVM`]: '',
			[`${portletNamespace}envLFR`]: '',
			[`${portletNamespace}envOS`]: '',
			[`${portletNamespace}envSearch`]: '',
			[`${portletNamespace}name`]: '',
			[`${portletNamespace}patchLevel`]: '',
			[`${portletNamespace}portalExt`]: '',
			[`${portletNamespace}productEntryId`]: ''
		};

		const requiredSchema = yup
			.string()
			.required(Liferay.Language.get('this-field-is-required'));

		const validationSchema = yup.object().shape({
			[`${portletNamespace}envAS`]: requiredSchema,
			[`${portletNamespace}envDB`]: requiredSchema,
			[`${portletNamespace}envJVM`]: requiredSchema,
			[`${portletNamespace}envLFR`]: requiredSchema,
			[`${portletNamespace}envOS`]: requiredSchema,
			[`${portletNamespace}name`]: requiredSchema,
			[`${portletNamespace}patchLevel`]: requiredSchema,
			[`${portletNamespace}portalExt`]: requiredSchema,
			[`${portletNamespace}productEntryId`]: requiredSchema
		});

		const {products} = environmentConfiguration;
		const renderEnvCS = selectedLFRVersion && selectedLFRVersion.envCS;
		const renderEnvSearch = selectedLFRVersion && selectedProduct &&
			'enterpriseSearch' in selectedProduct;

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
					<form
						action={addEnvironmentURL}
						encType="multipart/form-data"
						method="post"
						onSubmit={handleSubmit}
						ref={this.addEnvironmentFormRef}
					>
						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}accountEnvironmentName`}
									>
										{Liferay.Language.get('name')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<input
										className="form-control"
										id={`${portletNamespace}accountEnvironmentName`}
										name={`${portletNamespace}name`}
										onBlur={handleBlur}
										onChange={handleChange}
										type="text"
										value={values.name}
									/>
								</div>

								{touched[`${portletNamespace}name`] &&
									errors[`${portletNamespace}name`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}name`]}
										</div>
									)
								}
							</div>

							<div className="col-md-9">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}accountEnvironmentProduct`}
									>
										{Liferay.Language.get('product')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										id={`${portletNamespace}accountEnvironmentProduct`}
										name={`${portletNamespace}productEntryId`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{products.map((product, index) => (
											<option
												key={'productEntryId-' + index}
												id={'productEntryId-' + index}
												label={product.displayName}
												value={product.productEntryId}
											/>
										))}
									</select>
								</div>

								{touched[`${portletNamespace}productEntryId`] &&
									errors[`${portletNamespace}productEntryId`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}productEntryId`]}
										</div>
									)
								}
							</div>

							<div className="col-md-3">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envLFR`}
									>
										{Liferay.Language.get('liferay-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedProduct}
										id={`${portletNamespace}envLFR`}
										name={`${portletNamespace}envLFR`}
										onBlur={handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedProduct &&
											selectedProduct.envLFR.map((version, index) => (
												<option
													key={'envLFR-' + index}
													id={'envLFR-' + index}
													label={version.name}
													value={version.value}
												/>
											)
										)}
									</select>
								</div>

								{touched[`${portletNamespace}envLFR`] &&
									errors[`${portletNamespace}envLFR`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envLFR`]}
										</div>
									)
								}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envOS`}
									>
										{Liferay.Language.get('operating-system')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${portletNamespace}envOS`}
										name={`${portletNamespace}envOS`}
										onBlur={handleBlur}
										onChange={handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion &&
											selectedLFRVersion.envOS.map((envOS, index) => (
												<option
													key={'envOS-' + index}
													id={'envOS-' + index}
													label={envOS.name}
													value={envOS.value}
												/>
											))
										}
									</select>
								</div>

								{touched[`${portletNamespace}envOS`] &&
									errors[`${portletNamespace}envOS`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envOS`]}
										</div>
									)
								}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envJVM`}
									>
										{Liferay.Language.get('java-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${portletNamespace}envJVM`}
										name={`${portletNamespace}envJVM`}
										onBlur={handleBlur}
										onChange={handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion &&
											selectedLFRVersion.envJVM.map((envJVM, index) => (
												<option
													key={'envJVM-' + index}
													id={'envJVM-' + index}
													label={envJVM.name}
													value={envJVM.value}
												/>
											))
										}
									</select>
								</div>

								{touched[`${portletNamespace}envJVM`] &&
									errors[`${portletNamespace}envJVM`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envJVM`]}
										</div>
									)
								}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envAS`}
									>
										{Liferay.Language.get('application-server')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${portletNamespace}envAS`}
										name={`${portletNamespace}envAS`}
										onBlur={handleBlur}
										onChange={handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion &&
											selectedLFRVersion.envAS.map((envAS, index) => (
												<option
													key={'envAS-' + index}
													id={'envAS-' + index}
													label={envAS.name}
													value={envAS.value}
												/>
											))
										}
									</select>
								</div>

								{touched[`${portletNamespace}envAS`] &&
									errors[`${portletNamespace}envAS`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envAS`]}
										</div>
									)
								}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envDB`}
									>
										{Liferay.Language.get('database')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${portletNamespace}envDB`}
										name={`${portletNamespace}envDB`}
										onBlur={handleBlur}
										onChange={handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion &&
											selectedLFRVersion.envDB.map((envDB, index) => (
												<option
													key={'envDB-' + index}
													id={'envDB-' + index}
													label={envDB.name}
													value={envDB.value}
												/>
											))
										}
									</select>
								</div>

								{touched[`${portletNamespace}envDB`] &&
									errors[`${portletNamespace}envDB`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envDB`]}
										</div>
									)
								}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envBrowser`}
									>
										{Liferay.Language.get('browser')}
									</label>

									<select
										className="form-control"
										disabled={!selectedLFRVersion}
										id={`${portletNamespace}envBrowser`}
										name={`${portletNamespace}envBrowser`}
										onBlur={handleBlur}
										onChange={handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{selectedLFRVersion &&
											selectedLFRVersion.envBrowser.map((envBrowser, index) => (
												<option
													key={'envBrowser-' + index}
													id={'envBrowser-' + index}
													label={envBrowser.name}
													value={envBrowser.value}
												/>
											))
										}
									</select>
								</div>

								{touched[`${portletNamespace}envBrowser`] &&
									errors[`${portletNamespace}envBrowser`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}envBrowser`]}
										</div>
									)
								}
							</div>

							{renderEnvCS && (
								<div className="col-md-6">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${portletNamespace}envCS`}
										>
											{Liferay.Language.get('cloud-services')}
										</label>

										<select
											className="form-control"
											disabled={!selectedLFRVersion}
											id={`${portletNamespace}envCS`}
											name={`${portletNamespace}envCS`}
											onBlur={handleBlur}
											onChange={handleChange}
										>
											<option value="" label={Liferay.Language.get('select')} />

											{selectedLFRVersion &&
												selectedLFRVersion.envCS.map((envCS, index) => (
													<option
														key={'envCS-' + index}
														id={'envCS-' + index}
														label={envCS.name}
														value={envCS.value}
													/>
												))
											}
										</select>
									</div>

									{touched[`${portletNamespace}envCS`] &&
										errors[`${portletNamespace}envCS`] && (
											<div className="alert alert-danger" role="alert">
												{errors[`${portletNamespace}envCS`]}
											</div>
										)
									}
								</div>
							)}

							{renderEnvSearch && (
								<div className="col-md-12">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${portletNamespace}envSearch`}
										>
											{Liferay.Language.get('search')}
										</label>

										<select
											className="form-control"
											disabled={!selectedLFRVersion}
											id={`${portletNamespace}envSearch`}
											multiple={true}
											name={`${portletNamespace}envSearch`}
											onBlur={handleBlur}
											onChange={handleChange}
										>
											{selectedLFRVersion.envSearch.find(
												search => search[isEnterprise ? 'enterprise' : 'standard']
											 )[isEnterprise ? 'enterprise' : 'standard'].map(
												(envSearch, index) => (
													<option
														key={'envSearch-' + index}
														id={'envSearch-' + index}
														label={envSearch.name}
														value={envSearch.value}
													/>
												)
											 )
											}
										</select>
									</div>

									{touched[`${portletNamespace}envSearch`] &&
										errors[`${portletNamespace}envSearch`] && (
											<div className="alert alert-danger" role="alert">
												{errors[`${portletNamespace}envSearch`]}
											</div>
										)
									}
								</div>
							)}

							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}portalExt`}
									>
										{Liferay.Language.get('portal-ext')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<div className="upload-dropzone">
										<input
											className="form-control"
											id={`${portletNamespace}portalExt`}
											name={`${portletNamespace}portalExt`}
											onBlur={handleBlur}
											onChange={this.handleFileChange}
											ref={this.portalExtRef}
											type="file"
										/>

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

								{touched[`${portletNamespace}portalExt`] &&
									errors[`${portletNamespace}portalExt`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}portalExt`]}
										</div>
									)
								}
							</div>

							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}patchLevel`}
									>
										{Liferay.Language.get('patch-info')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<div className="upload-dropzone">
										<input
											className="form-control"
											id={`${portletNamespace}patchLevel`}
											name={`${portletNamespace}patchLevel`}
											onBlur={handleBlur}
											onChange={this.handleFileChange}
											ref={this.patchLevelRef}
											type="file"
										/>

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

								{touched[`${portletNamespace}patchLevel`] &&
									errors[`${portletNamespace}patchLevel`] && (
										<div className="alert alert-danger" role="alert">
											{errors[`${portletNamespace}patchLevel`]}
										</div>
									)
								}
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