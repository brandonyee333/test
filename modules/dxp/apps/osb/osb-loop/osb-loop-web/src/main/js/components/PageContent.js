import Component, {Config} from 'metal-jsx';

import MarkdownContent from './MarkdownContent';

class PageContent extends Component {
	render() {
		const {displayURL, message, title} = this.props;

		return (
			<a class="page-content-container" href={displayURL}>
				<div class="content">
					<h1 class="title">{title}</h1>

					<MarkdownContent message={message} />
				</div>

				<div class="footer">{Liferay.Language.get('see-full-page')}</div>
			</a>
		);
	}
}

PageContent.PROPS = {
	displayURL: Config.string(),
	message: Config.string(),
	title: Config.string()
};

export default PageContent;