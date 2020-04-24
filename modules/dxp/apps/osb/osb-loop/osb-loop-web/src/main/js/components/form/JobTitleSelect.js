import Component, {Config} from 'metal-jsx';

import Select from './Select';
import sendRequest from '../../lib/request';

class JobTitlesInput extends Component {
	created() {
		if (!this.props.disabled) {
			this._request = this.fetchJobTitles_();
		}
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	fetchJobTitles_() {
		return sendRequest(
			{
				controller: 'job_titles',
				controllerMethod: 'index.json',
				data: {
					createTime: Date.now(),
					end: -1,
					start: -1
				}
			}
		).then(
			({results}) => {
				this.state.jobTitles_ = results;
			}
		);
	}

	render() {
		const {
			disabled,
			initialValue: {
				id,
				name
			}
		} = this.props;

		return (
			<Select
				disabled={disabled}
				initialValue={id}
				label={Liferay.Language.get('job-title')}
				name="loopJobTitleId"
				validator="required"
			>
				{disabled &&
					<option selected={true} value={id}>{name}</option>
				}

				{!disabled &&
					this.state.jobTitles_.map(
						({entityClassPK, name}) => <option key={entityClassPK} selected={id === entityClassPK} value={entityClassPK}>{name}</option>
					)
				}
			</Select>
		);
	}
}

JobTitlesInput.PROPS = {
	disabled: Config.bool().value(false),
	initialValue: Config.object().value({})
};

JobTitlesInput.STATE = {
	jobTitles_: Config.value([])
};

export default JobTitlesInput;