/* global google */

import {bindAll} from 'lodash';
import bridge from 'metal-react';
import GoogleMap from 'google-map-react';
import JSXComponent, {Config} from 'metal-jsx';

const MetalGoogleMap = bridge(GoogleMap);

class GoogleMapWrapper extends JSXComponent {
	created() {
		bindAll(
			this,
			'handleMapsReady',
			'handleOnClick'
		);
	}

	disposed() {
		Liferay.Watson.mapComponent.geocoder = null;

		if (Liferay.Watson.mapComponent.heatMap) {
			Liferay.Watson.mapComponent.heatMap.setMap(null);
		}

		Liferay.Watson.mapComponent.heatMap = null;
		Liferay.Watson.mapComponent.map = null;
		Liferay.Watson.mapComponent.marker = null;

		if (Liferay.Watson.mapComponent.markers) {
			Liferay.Watson.mapComponent.markers.clearMarkers();
		}

		Liferay.Watson.mapComponent.markers = null;
	}

	handleOnClick(event) {
		const {onClick} = this.props;

		if (onClick) {
			onClick(event);
		}
	}

	handleMapsReady(googleMapObject) {
		Liferay.Watson.mapComponent.map = googleMapObject.map;
		Liferay.Watson.mapComponent.geocoder = new googleMapObject.maps.Geocoder();

		const {onReady} = this.props;

		if (onReady) {
			onReady();
		}

		if (this.state) {
			this.setState({mapsReady: true});
		}
	}

	render() {
		const {center, options, zoom} = this.props;

		const {defaultMapOptions} = this.state;

		const urlKeys = {
			key: 'AIzaSyARSBfsdAry_ZZ-pV2J34DoE4m2kTqzIkI',
			language: WatsonConstants.currentLanguageId,
			libraries: [
				'places', 'visualization'
			],
			region: 'TH'
		};

		return (
			<div class="map-container" >
				<MetalGoogleMap
					bootstrapURLKeys={urlKeys}
					center={center}
					onClick={this.handleOnClick}
					onGoogleApiLoaded={this.handleMapsReady}
					options={options || defaultMapOptions}
					yesIWantToUseGoogleMapApiInternals={true}
					zoom={zoom}
				/>
			</div>
		);
	}
}

GoogleMapWrapper.PROPS = {
	center: Config.object().value(
		{
			lat: 16.04581345,
			lng: 101.27197265
		}
	),
	onClick: Config.func(),
	onReady: Config.func(),
	options: Config.object(),
	zoom: Config.number().value(7)
};

GoogleMapWrapper.STATE = {
	defaultMapOptions: Config.object().value(
		{
			mapTypeControl: true,
			mapTypeIds: [
				'ROADMAP', 'HYBRID', 'SATELLITE'
			],
			styles: [
				{
					elementType: 'all',
					featureType: 'all',
					stylers: [
						{
							visibility: 'on'
						}
					]
				}
			]
		}
	),
	mapsReady: Config.bool().value(false)
};

export default GoogleMapWrapper;