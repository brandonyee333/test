import React from 'react';

import {withFormik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

class FormFields extends React.Component {
	state = {
		[`${this.props.portletNamespace}envLFRIndex`]: null,
		[`${this.props.portletNamespace}patchLevelFiles`]: [],
		[`${this.props.portletNamespace}portalExtFiles`]: [],
		[`${this.props.portletNamespace}productIndex`]: null
	};

	handleFileChange = (event) => {
		const {files} = event.target;
		const fileNames = [];

		const {name} = event.target;

		for (var i = 0; i < files.length; i++) {
			fileNames.push(files[i].name);
		};

		this.setState({
			[`${name}Files`]: fileNames
		});

		this.props.handleChange(event);
	};

	handleSelectChange = (event) => {
		const {options} = event.target;
		const fieldNameIndex = options[options.selectedIndex].id.replace(/\D+/g, '');

		const {name} = event.target;

		this.setState({
			[`${name}Index`]: fieldNameIndex
		});

		this.props.handleChange(event);
	};

	render() {
		const {
			addEnvironmentURL,
			dirty,
			errors,
			handleBlur,
			handleChange,
			handleReset,
			handleSubmit,
			isSubmitting,
			portletNamespace,
			productEntries,
			touched,
			values
		} = this.props;

		const envLFRIndex = this.state[`${portletNamespace}envLFRIndex`];
		const patchLevelFiles = this.state[`${portletNamespace}patchLevelFiles`];
		const portalExtFiles = this.state[`${portletNamespace}portalExtFiles`];
		const productIndex = this.state[`${portletNamespace}productIndex`];

		return(
			<form onSubmit={handleSubmit}>
				<input name={`${portletNamespace}productEntryId`} type="hidden" value={productIndex ? productEntries[productIndex].productEntryId : ''} />

				<div className="row">
					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label" htmlFor="accountEnvironmentName">
								{Liferay.Language.get('name')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<input className="form-control" id={`${portletNamespace}accountEnvironmentName`} name={`${portletNamespace}name`} onBlur={handleBlur} onChange={handleChange} type="text" value={values.name} />
						</div>

						{touched[`${portletNamespace}name`] && errors[`${portletNamespace}name`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}name`]}
							</div>
						)}
					</div>

					<div className="col-md-9">
						<div className="form-group">
							<label className="control-label" htmlFor="accountEnvironmentProduct">
								{Liferay.Language.get('product')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" id={`${portletNamespace}accountEnvironmentProduct`} name={`${portletNamespace}product`} onBlur={handleBlur} onChange={this.handleSelectChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{productEntries.map(
									(productEntry, index) => (
										<option id={'product-' + index} key={'product-' + index} label={productEntry.displayName} value={productEntry.displayName} />
									)
								)}
							</select>
						</div>

						{touched[`${portletNamespace}product`] && errors[`${portletNamespace}product`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}product`]}
							</div>
						)}
					</div>

					<div className="col-md-3">
						<div className="form-group">
							<label className="control-label" htmlFor="envLFR">
								{Liferay.Language.get('liferay-version')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={productIndex == null} id={`${portletNamespace}envLFR`} name={`${portletNamespace}envLFR`} onBlur={handleBlur} onChange={this.handleSelectChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{productIndex ?
									productEntries[productIndex].envListTypes.map(
										(listType, index) => (
											<option id={'envLFR-' + index} key={'envLFR-' + index} label={listType.envLFR} value={listType.envLFR} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envLFR`] && errors[`${portletNamespace}envLFR`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envLFR`]}
							</div>
						)}
					</div>

					<div className="col-md-6">
						<div className="form-group">
							<label className="control-label" htmlFor="envOS">
								{Liferay.Language.get('operating-system')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envOS`} name={`${portletNamespace}envOS`} onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{envLFRIndex ?
									productEntries[productIndex].envListTypes[envLFRIndex].envOS.map(
										(envOS, index) => (
											<option id={'envOS-' + index} key={'envOS-' + index} label={envOS.name} value={envOS.value} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envOS`] && errors[`${portletNamespace}envOS`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envOS`]}
							</div>
						)}
					</div>

					<div className="col-md-6">
						<div className="form-group">
							<label className="control-label" htmlFor="envJVM">
								{Liferay.Language.get('java-version')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envJVM`} name={`${portletNamespace}envJVM`} onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{envLFRIndex ?
									productEntries[productIndex].envListTypes[envLFRIndex].envJVM.map(
										(envJVM, index) => (
											<option id={'envJVM-' + index} key={'envJVM-' + index} label={envJVM.name} value={envJVM.value} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envJVM`] && errors[`${portletNamespace}envJVM`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envJVM`]}
							</div>
						)}
					</div>

					<div className="col-md-6">
						<div className="form-group">
							<label className="control-label" htmlFor="envAS">
								{Liferay.Language.get('application-server')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envAS`} name={`${portletNamespace}envAS`} onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{envLFRIndex ?
									productEntries[productIndex].envListTypes[envLFRIndex].envAS.map(
										(envAS, index) => (
											<option id={'envAS-' + index} key={'envAS-' + index} label={envAS.name} value={envAS.value} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envAS`] && errors[`${portletNamespace}envAS`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envAS`]}
							</div>
						)}
					</div>

					<div className="col-md-6">
						<div className="form-group">
							<label className="control-label" htmlFor="envDB">
								{Liferay.Language.get('database')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envDB`} name={`${portletNamespace}envDB`} onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{envLFRIndex ?
									productEntries[productIndex].envListTypes[envLFRIndex].envDB.map(
										(envDB, index) => (
											<option id={'envDB-' + index} key={'envDB-' + index} label={envDB.name} value={envDB.value} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envDB`] && errors[`${portletNamespace}envDB`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envDB`]}
							</div>
						)}
					</div>

					<div className="col-md-6">
						<div className="form-group">
							<label className="control-label" htmlFor="envBrowser">
								{Liferay.Language.get('browser')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envBrowser`} name={`${portletNamespace}envBrowser`} onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{envLFRIndex ?
									productEntries[productIndex].envListTypes[envLFRIndex].envBrowser.map(
										(envBrowser, index) => (
											<option id={'envBrowser-' + index} key={'envBrowser-' + index} label={envBrowser.name} value={envBrowser.value} />
										)
									) : null
								}
							</select>
						</div>

						{touched[`${portletNamespace}envBrowser`] && errors[`${portletNamespace}envBrowser`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}envBrowser`]}
							</div>
						)}
					</div>

					{envLFRIndex && productEntries[productIndex].envListTypes[envLFRIndex].envCS ? (
						<div className="col-md-6">
							<div className="form-group">
								<label className="control-label" htmlFor="envCS">
									{Liferay.Language.get('cloud-services')}

									<svg className="lexicon-icon lexicon-icon-asterisk">
										<use xlinkHref="#asterisk" />
									</svg>
								</label>

								<select className="form-control" disabled={envLFRIndex == null} id={`${portletNamespace}envCS`} name={`${portletNamespace}envCS`} onBlur={handleBlur} onChange={handleChange} required={true}>
									<option value="" label={Liferay.Language.get('select')} />

									{productEntries[productIndex].envListTypes[envLFRIndex].envCS.map(
										(envCS, index) => (
											<option id={'envCS-' + index} key={'envCS-' + index} label={envCS.name} value={envCS.value} />
										)
									)}
								</select>
							</div>

							{touched[`${portletNamespace}envCS`] && errors[`${portletNamespace}envCS`] && (
								<div className="alert alert-danger" role="alert">
									{errors[`${portletNamespace}envCS`]}
								</div>
							)}
						</div>
					) : (
						null
					)}

					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label" htmlFor="portalExt">
								{Liferay.Language.get('portal-ext')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<div className="upload-dropzone">
								<input className="form-control" id={`${portletNamespace}portalExt`} multiple={true} name={`${portletNamespace}portalExt`} onBlur={handleBlur} onChange={this.handleFileChange} type="file" />

								<svg className="lexicon-icon lexicon-icon-paperclip">
									<use xlinkHref="#paperclip" />
								</svg>

								<span>{Liferay.Language.get('add-file-or-drop-files-here')}</span>
							</div>

							<div className="file-list">
								<ul className="attachment-pool">
									{portalExtFiles ?
										portalExtFiles.map(
											(file, index) => (
												<li className="attachment" key={'portal-ext-file-' + index}>
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													{file}
												</li>
											)
										) : null
									}
								</ul>
							</div>
						</div>

						{touched[`${portletNamespace}portalExt`] && errors[`${portletNamespace}portalExt`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}portalExt`]}
							</div>
						)}
					</div>

					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label" htmlFor="patchLevel">
								{Liferay.Language.get('patch-info')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<div className="upload-dropzone">
								<input className="form-control" id={`${portletNamespace}patchLevel`} multiple={true} name={`${portletNamespace}patchLevel`} onBlur={handleBlur} onChange={this.handleFileChange} type="file" />

								<svg className="lexicon-icon lexicon-icon-paperclip">
									<use xlinkHref="#paperclip" />
								</svg>

								<span>{Liferay.Language.get('add-file-or-drop-files-here')}</span>
							</div>

							<div className="file-list">
								<ul className="attachment-pool">
									{patchLevelFiles ?
										patchLevelFiles.map(
											(file, index) => (
												<li className="attachment" key={'patch-file-' + index}>
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													{file}
												</li>
											)
										) : null
									}
								</ul>
							</div>
						</div>

						{touched[`${portletNamespace}patchLevel`] && errors[`${portletNamespace}patchLevel`] && (
							<div className="alert alert-danger" role="alert">
								{errors[`${portletNamespace}patchLevel`]}
							</div>
						)}
					</div>
				</div>

				<div className="btn-row">
					<Button
						display="outline"
						onClick={handleReset}
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
		)
	}
}

const requiredSchema = yup.string().required(
	Liferay.Language.get('this-field-is-required')
);

export const AddAccountEnvironmentForm = withFormik(
	{
		displayName: 'addAccountEnvironment',
		handleSubmit: (values, {setSubmitting}) => {
			alert(JSON.stringify(values, null, 2));
			setSubmitting(false);
		},
		mapPropsToValues: props => (
			{
				[`${props.portletNamespace}envAS`]: '',
				[`${props.portletNamespace}envBrowser`]: '',
				[`${props.portletNamespace}envCS`]: '',
				[`${props.portletNamespace}envDB`]: '',
				[`${props.portletNamespace}envJVM`]: '',
				[`${props.portletNamespace}envLFR`]: '',
				[`${props.portletNamespace}envOS`]: '',
				[`${props.portletNamespace}name`]: '',
				[`${props.portletNamespace}patchLevel`]: '',
				[`${props.portletNamespace}portalExt`]: '',
				[`${props.portletNamespace}product`]: ''
			}
		),
		validationSchema: props => (
			yup.object().shape(
				{
					[`${props.portletNamespace}envAS`]: requiredSchema,
					[`${props.portletNamespace}envBrowser`]: requiredSchema,
					[`${props.portletNamespace}envDB`]: requiredSchema,
					[`${props.portletNamespace}envJVM`]: requiredSchema,
					[`${props.portletNamespace}envLFR`]: requiredSchema,
					[`${props.portletNamespace}envOS`]: requiredSchema,
					[`${props.portletNamespace}name`]: requiredSchema,
					[`${props.portletNamespace}patchLevel`]: requiredSchema,
					[`${props.portletNamespace}portalExt`]: requiredSchema,
					[`${props.portletNamespace}product`]: requiredSchema
				}
			)
		)
	}
)(FormFields);