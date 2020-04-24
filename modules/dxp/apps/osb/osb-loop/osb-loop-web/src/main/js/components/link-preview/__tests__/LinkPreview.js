import LinkPreview from '../index';

describe(
	'LinkPreview',
	() => {
		let component;

		const linkData = {
			description: 'Liferay: Put people at the heart of your business',
			imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
			shortURL: 'www.liferay.com',
			title: 'Liferay',
			url: 'http://www.liferay.com'
		};

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new LinkPreview({linkData});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders without an image',
			() => {
				component = new LinkPreview(
					{
						linkData: {
							...linkData,
							imageURL: ''
						}
					}
				);

				expect(component.element.querySelector('.link-preview-image-container')).not.toBeTruthy();
			}
		);

		it(
			'renders play icon for a video',
			() => {
				component = new LinkPreview(
					{
						linkData: {
							...linkData,
							videoURL: 'https://www.youtube.com/embed/izSOXcQnfyM'
						}
					}
				);

				expect(component.element.querySelector('.play-overlay')).toBeTruthy();
			}
		);
	}
);