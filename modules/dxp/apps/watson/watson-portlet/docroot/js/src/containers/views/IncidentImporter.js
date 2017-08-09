import Alert from 'metal-alert';
import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';

import Button from '../../components/Button';
import ContentHeader from '../../components/ContentHeader';
import FileUploader from '../../components/FileUploader';

class IncidentImporter extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleButtonOnClick',
			'handleOnChange'
		);
	}

	handleButtonOnClick() {
		this.setState({valueAccepted: false});
	}

	handleOnChange() {
		this.setState({valueAccepted: true});
	}

	render() {
		const {valueAccepted} = this.state;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('migrate-data-from-one-database-to-another')} />
				</div>

				<div class="content">
					{!valueAccepted &&
						<FileUploader
							acceptedTypes=".csv"
							controller="incidents"
							controllerMethod="importIncidentsFile.json"
							disabled={valueAccepted}
							enableCropperTool={false}
							multiple={false}
							onChange={this.handleOnChange}
							uploaderLabel={Liferay.Language.get('upload-your-csv-file-here')}
						/>
					}

					<Alert
						body={Liferay.Language.get('success')}
						dismissible={false}
						elementClasses="alert-success"
						visible={valueAccepted}
					/>

					{valueAccepted &&
						<Button
							label={Liferay.Language.get('save-and-add-another')}
							onClick={this.handleButtonOnClick}
						/>
					}
				</div>
			</div>
		);
	}
}

IncidentImporter.STATE = {
	valueAccepted: Config.bool().value(false)
};

export default IncidentImporter;