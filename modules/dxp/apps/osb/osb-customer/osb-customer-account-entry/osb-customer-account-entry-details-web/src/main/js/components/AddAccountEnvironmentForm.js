import React from 'react';

import {withFormik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

class FormFields extends React.Component {
	state = {
		envLFRIndex: null,
		productIndex: null
	};

	handleSelectChange = (event, fieldName) => {
		const {options} = event.target;
		const fieldNameIndex = options[options.selectedIndex].id.replace(/\D+/g, '');

		this.setState({
			[fieldName]: fieldNameIndex
		});

		this.props.handleChange(event);
	};

	render() {
		const {
			dirty,
			errors,
			handleBlur,
			handleChange,
			handleReset,
			handleSubmit,
			isSubmitting,
			productEntries,
			touched,
			values
		} = this.props;

		return(
			<form onSubmit={handleSubmit}>
				<div className="row">
					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label" htmlFor="accountEnvironmentName">
								{Liferay.Language.get('name')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<input className="form-control" id="accountEnvironmentName" name="name" onBlur={handleBlur} onChange={handleChange} type="text" value={values.name} />
						</div>

						{touched.name && errors.name && (
							<div className="alert alert-danger" role="alert">
								{errors.name}
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

							<select className="form-control" id="accountEnvironmentProduct" name="product" onBlur={handleBlur} onChange={(event) => this.handleSelectChange(event, 'productIndex')}>
								<option value="" label={Liferay.Language.get('select')} />

								{productEntries.map(
									(productEntry, index) => (
										<option id={'product-' + index} key={'product-' + index} label={productEntry.displayName} value={productEntry.displayName} />
									)
								)}
							</select>
						</div>

						{touched.product && errors.product && (
							<div className="alert alert-danger" role="alert">
								{errors.product}
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

							<select className="form-control" disabled={this.state.productIndex == null} id="envLFR" name="envLFR" onBlur={handleBlur} onChange={(event) => this.handleSelectChange(event, 'envLFRIndex')}>
								<option value="" label={Liferay.Language.get('select')} />

								{this.state.productIndex ?
									productEntries[this.state.productIndex].envListTypes.map(
										(listType, index) => (
											<option id={'envLFR-' + index} key={'envLFR-' + index} label={listType.envLFR} value={listType.envLFR} />
										)
									) : null
								}
							</select>
						</div>

						{touched.envLFR && errors.envLFR && (
							<div className="alert alert-danger" role="alert">
								{errors.envLFR}
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

							<select className="form-control" disabled={this.state.envLFRIndex == null} id="envOS" name="envOS" onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{this.state.envLFRIndex ?
									productEntries[this.state.productIndex].envListTypes[this.state.envLFRIndex].envOS.map(
										(envOS, index) => (
											<option id={'envOS-' + index} key={'envOS-' + index} label={envOS.name} value={envOS.name} />
										)
									) : null
								}
							</select>
						</div>

						{touched.envOS && errors.envOS && (
							<div className="alert alert-danger" role="alert">
								{errors.envOS}
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

							<select className="form-control" disabled={this.state.envLFRIndex == null} id="envJVM" name="envJVM" onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{this.state.envLFRIndex ?
									productEntries[this.state.productIndex].envListTypes[this.state.envLFRIndex].envJVM.map(
										(envJVM, index) => (
											<option id={'envJVM-' + index} key={'envJVM-' + index} label={envJVM.name} value={envJVM.name} />
										)
									) : null
								}
							</select>
						</div>

						{touched.envJVM && errors.envJVM && (
							<div className="alert alert-danger" role="alert">
								{errors.envJVM}
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

							<select className="form-control" disabled={this.state.envLFRIndex == null} id="envAS" name="envAS" onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{this.state.envLFRIndex ?
									productEntries[this.state.productIndex].envListTypes[this.state.envLFRIndex].envAS.map(
										(envAS, index) => (
											<option id={'envAS-' + index} key={'envAS-' + index} label={envAS.name} value={envAS.name} />
										)
									) : null
								}
							</select>
						</div>

						{touched.envAS && errors.envAS && (
							<div className="alert alert-danger" role="alert">
								{errors.envAS}
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

							<select className="form-control" disabled={this.state.envLFRIndex == null} id="envDB" name="envDB" onBlur={handleBlur} onChange={handleChange}>
								<option value="" label={Liferay.Language.get('select')} />

								{this.state.envLFRIndex ?
									productEntries[this.state.productIndex].envListTypes[this.state.envLFRIndex].envDB.map(
										(envDB, index) => (
											<option id={'envDB-' + index} key={'envDB-' + index} label={envDB.name} value={envDB.name} />
										)
									) : null
								}
							</select>
						</div>

						{touched.envDB && errors.envDB && (
							<div className="alert alert-danger" role="alert">
								{errors.envDB}
							</div>
						)}
					</div>

					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label" htmlFor="portalExt">
								{Liferay.Language.get('portal-ext')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<div className="upload-dropzone">
								<input className="form-control" id="portalExt" multiple={true} name="portalExt" onBlur={handleBlur} onChange={handleChange} type="file" />
							</div>
						</div>

						{touched.portalExt && errors.portalExt && (
							<div className="alert alert-danger" role="alert">
								{errors.portalExt}
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
								<input className="form-control" id="patchLevel" multiple={true} name="patchLevel" onBlur={handleBlur} onChange={handleChange} type="file" />
							</div>
						</div>

						{touched.patchLevel && errors.patchLevel && (
							<div className="alert alert-danger" role="alert">
								{errors.patchLevel}
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
		mapPropsToValues: () => (
			{
				envAS: '',
				envDB: '',
				envJVM: '',
				envLFR: '',
				envOS: '',
				name: '',
				patchLevel: '',
				portalExt: '',
				product: ''
			}
		),
		validationSchema: yup.object().shape(
			{
				envAS: requiredSchema,
				envDB: requiredSchema,
				envJVM: requiredSchema,
				envLFR: requiredSchema,
				envOS: requiredSchema,
				name: requiredSchema,
				patchLevel: requiredSchema,
				portalExt: requiredSchema,
				product: requiredSchema
			}
		)
	}
)(FormFields);