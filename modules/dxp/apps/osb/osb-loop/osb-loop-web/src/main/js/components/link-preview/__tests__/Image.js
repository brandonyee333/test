import Image from '../Image';

describe(
	'Image',
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
				component = new Image({linkData});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set `play_` state to true',
			() => {
				component = new Image({linkData});

				component.handlePlay_();

				expect(component.state.play_).toBe(true);
			}
		);
	}
);