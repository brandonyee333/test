import Component, {Config} from 'metal-jsx';

import ExternalLink from './ExternalLink';

class TinyLinkPreview extends Component {
	render() {
		const {
			imageURL,
			shortURL,
			title,
			url
		} = this.props.linkData;

		return (
			<ExternalLink elementClasses="tiny-link-preview-container" href={url}>
				{imageURL &&
					<div class="link-image" style={{backgroundImage: `url(${imageURL})`}} />
				}

				<div class="link-info">
					<div class="link-title">
						{title}
					</div>

					<div class="link-url">
						{shortURL}
					</div>
				</div>
			</ExternalLink>
		);
	}
}

TinyLinkPreview.PROPS = {
	linkData: Config.object()
};

export default TinyLinkPreview;