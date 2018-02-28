import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Button from './Button';
import {getMimeType} from '../lib/util';

class FileViewer extends JSXComponent {
	created() {
		bindAll(
			this,
			'_handleRemoveImage',
			'_handleKeyUp',
			'_handleMaximize',
			'_handleMinimizeClick',
			'minimize'
		);
	}

	detached() {
		document.removeEventListener('keyup', this._handleKeyUp);
	}

	_handleRemoveImage() {
		const {disabled, inputId, onChange} = this.props;

		if (!disabled && onChange) {
			onChange('', inputId);
		}
	}

	_handleKeyUp(event) {
		if (event.keyCode === 27) {
			this.minimize();
		}
	}

	_handleMaximize() {
		if (this.state.type) {
			this.state.maximized = true;

			document.addEventListener('keyup', this._handleKeyUp);
		}
	}

	_handleMinimizeClick(event) {
		const {type} = this.state;

		if (event.target.nodeName !== type) {
			this.minimize();
		}
	}

	minimize() {
		document.removeEventListener('keyup', this._handleKeyUp);

		this.state.maximized = false;
	}

	render() {
		const {disabled, microForm, value: fileData} = this.props;
		const {clickToPlay, maximized, type} = this.state;

		const className = microForm ? 'micro-form' : '';

		let filePreview;
		let fullScreenView;

		if (type) {
			if (type === 'IMG') {
				filePreview = (<div class="file-viewer-small" onClick={this._handleMaximize} style={`background-image: url(${fileData.previewURL});`} title={Liferay.Language.get('maximize')} />);

				fullScreenView = (<img class="file" src={fileData.previewURL} />);
			}
			else if (type === 'VIDEO') {
				filePreview = (<div class="file-viewer-small" onClick={this._handleMaximize} style={`background-image: url(${clickToPlay});`} title={Liferay.Language.get('maximize')} />);

				fullScreenView = (<video class="file" controls={true} src={fileData.previewURL} />);
			}
			else if (type === 'AUDIO') {
				filePreview = (<div class="file-viewer-small" onClick={this._handleMaximize} style={`background-image: url(${clickToPlay});`} title={Liferay.Language.get('maximize')} />);

				fullScreenView = (<audio class="file" controls={true} src={fileData.previewURL} />);
			}
			else {
				filePreview = (
					<a
						class="file"
						download={fileData.originalName || fileData.name}
						href={fileData.previewURL}
						rel="noopener noreferrer"
						target="_blank"
						title={Liferay.Language.get('download')}
						type={fileData.mimeType}
					>
						{fileData.originalName || fileData.name || Liferay.Language.get('download')}
					</a>
				);
			}
		}

		return (
			<div class="file-viewer-container">
				<div class="file-viewer-small-wrapper">
					{filePreview}

					{!disabled &&
						<Button className={`remove-button ${className}`} onClick={this._handleRemoveImage} />
					}
				</div>

				{(maximized && fullScreenView) &&
					<div class="file-viewer-maximized-wrapper">
						<a class="view-port" onClick={this._handleMinimizeClick}>
							{fullScreenView}
						</a>

						<span class="viewer-close">
							<Button className="close-button" onClick={this._handleMinimizeClick} />
						</span>
					</div>
				}
			</div>
		);
	}

	rendered() {
		const {value: fileData = {}} = this.props;
		const {type: stateType} = this.state;

		const type = getMimeType(fileData.mimeType);

		if (fileData && type && type !== stateType) {
			this.setState({type});
		}
	}
}

FileViewer.PROPS = {
	disabled: Config.bool().value(false),
	microForm: Config.bool().value(false),
	tooltipLabel: Config.string().value(''),
	value: Config.object().value({})
};

FileViewer.STATE = {
	clickToPlay: Config.string().value('data:image/svg+xml,%3Csvg%20fill%3D%22%236C7982%22%20height%3D%2248%22%20viewBox%3D%220%200%2024%2024%22%20width%3D%2248%22%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%3E%0A%20%20%20%20%3Cpath%20d%3D%22M0%200h24v24H0z%22%20fill%3D%22none%22/%3E%0A%20%20%20%20%3Cpath%20d%3D%22M12%202C6.48%202%202%206.48%202%2012s4.48%2010%2010%2010%2010-4.48%2010-10S17.52%202%2012%202zm-2%2014.5v-9l6%204.5-6%204.5z%22/%3E%0A%3C/svg%3E'),
	maximized: Config.bool().value(false),
	type: Config.string().value('')
};

export default FileViewer;