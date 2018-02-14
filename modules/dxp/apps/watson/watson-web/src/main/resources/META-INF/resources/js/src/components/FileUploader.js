import Alert from 'marble-alert';
import {bindAll, isEmpty} from 'lodash';
import bridge from 'metal-react';
import dropzone from 'react-dropzone';
import JSXComponent, {Config} from 'metal-jsx';
import sub from 'string-sub';

import CropImageModal from './CropImageModal';
import {formatBytesToString} from '../lib/util';
import sendRequest from '../lib/request';
import Spinner from './Spinner';

const MetalDropzone = bridge(dropzone);

class FileDropzone extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleCancel',
			'handleOnDropAccepted',
			'handleOnDropRejected',
			'handleUploadFile'
		);
	}

	handleCancel() {
		this.setState(
			{
				acceptedFileData: {},
				filesAccepted: false
			}
		);
	}

	handleError(rejectedFileData, filesAccepted, maxFileSize) {
		let alertLanguageKey = null;

		if (!isEmpty(rejectedFileData) && !filesAccepted) {
			if (rejectedFileData.exceededMaxFileCount === true) {
				alertLanguageKey = sub(Liferay.Language.get('max-file-count-exceeded-x'), WatsonConstants.uploadSettings.maxFileCount);
			}
			else if (rejectedFileData.size > maxFileSize) {
				alertLanguageKey = sub(Liferay.Language.get('you-cannot-upload-a-file-who-size-is-greater-than-x'), formatBytesToString(maxFileSize));
			}
			else {
				alertLanguageKey = Liferay.Language.get('please-enter-a-file-with-a-valid-file-type');
			}
		}

		return alertLanguageKey;
	}

	handleOnDropAccepted(files) {
		const {disabled, enableCropperTool, multiple} = this.props;

		if (!disabled) {
			const firstFile = files[0];

			this.setState(
				{
					filesAccepted: true,
					rejectedFileData: {}
				}
			);

			if (!multiple && enableCropperTool && firstFile && firstFile.type.includes('image')) {
				this.setState(
					{
						acceptedFileData: {
							file: firstFile,
							preview: firstFile.preview
						}
					}
				);
			}
			else if (files.length < WatsonConstants.uploadSettings.maxFileCount) {
				files.forEach(
					file => {
						this.handleUploadFile(file);
					}
				);
			}
			else {
				this.handleOnDropRejected(files, true);
			}
		}
	}

	handleOnDropRejected([file], exceededMaxFileCount = false) {
		const rejectedFileData = {
			exceededMaxFileCount,
			size: file.size,
			type: file.type
		};

		this.setState(
			{
				acceptedFileData: {},
				filesAccepted: false,
				rejectedFileData
			}
		);
	}

	handleUploadFile(file, cropData = {}) {
		const {classPK = 0, controller, controllerMethod, disabled, inputId, onChange} = this.props;

		if (!disabled && onChange) {
			const reader = new FileReader();

			reader.onload = () => {
				sendRequest(
					{
						controller,
						controllerMethod,
						data: {classPK, cropData: JSON.stringify(cropData), file, name: file.name},
						fileUpload: true
					}
				).then(
					response => {
						if (response && response.data) {
							const {data} = response;

							onChange(data, inputId);
						}
					}
				).catch(
					() => {
						this.setState({filesAccepted: false});

						this.handleOnDropRejected([file]);
					}
				);
			};
			reader.readAsDataURL(file);
		}
	}

	render() {
		const {
			acceptedTypes,
			disabled,
			enableCropperTool,
			multiple,
			uploaderLabel
		} = this.props;

		const {
			acceptedFileData,
			filesAccepted,
			rejectedFileData = {}
		} = this.state;

		const maxFileSize = disabled ? 0 : WatsonConstants.uploadSettings.maxFileSize;

		const alertLanguageKey = this.handleError(rejectedFileData, filesAccepted, maxFileSize);

		const helperMessage = alertLanguageKey ? '' : sub(Liferay.Language.get('max-file-size-x'), formatBytesToString(maxFileSize));

		return (
			<div class="metal-uploader-wrapper">
				{filesAccepted &&
					<Spinner size="large" />
				}

				{(enableCropperTool && !isEmpty(acceptedFileData)) &&
					<CropImageModal
						imageData={acceptedFileData}
						onCancel={this.handleCancel}
						onSubmit={this.handleUploadFile}
					/>
				}

				{!filesAccepted &&
					<MetalDropzone
						accept={acceptedTypes}
						activeClassName="accept"
						className="metal-file-uploader"
						disableClick={disabled}
						maxSize={maxFileSize}
						multiple={multiple}
						onDropAccepted={this.handleOnDropAccepted}
						onDropRejected={this.handleOnDropRejected}
						rejectClassName="reject"
					>
						<span className="uploader-label" key={'key'}>{uploaderLabel}</span>
					</MetalDropzone> &&

					<span class="helper-message">{helperMessage}</span>
				}

				<Alert
					body={alertLanguageKey}
					dismissible={false}
					elementClasses="alert-danger"
					visible={!!alertLanguageKey}
				/>
			</div>
		);
	}
}

FileDropzone.PROPS = {
	acceptedTypes: Config.string().value('image/*'),
	classPK: Config.value(''),
	controller: Config.string().value('resources'),
	controllerMethod: Config.string().value('upload.json'),
	disabled: Config.bool().value(false),
	enableCropperTool: Config.bool().value(true),
	multiple: Config.bool().value(false),
	onChange: Config.func(),
	uploaderLabel: Config.string().value('')
};

FileDropzone.STATE = {
	acceptedFileData: Config.object().value({}),
	filesAccepted: Config.bool().value(false),
	rejectedFileData: Config.object().value({})
};

export default FileDropzone;