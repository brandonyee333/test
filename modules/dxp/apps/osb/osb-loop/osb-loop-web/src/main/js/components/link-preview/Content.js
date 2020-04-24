import Component, {Config} from 'metal-jsx';

import ExternalLink from '../ExternalLink';

class Content extends Component {
	render() {
		const {
			description,
			shortURL,
			title,
			url
		} = this.props.linkData;

		return (
			<div class="link-preview-content-container">
				<div class="title">
					<ExternalLink href={url}>{title}</ExternalLink>
				</div>

				<div class="short-url">
					<ExternalLink href={url}>{shortURL}</ExternalLink>
				</div>

				<div class="description">
					<ExternalLink href={url}>{description}</ExternalLink>
				</div>
			</div>
		);
	}
}

Content.PROPS = {
	linkData: Config.object()
};

export default Content;