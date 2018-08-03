/* global google */

import {bindAll} from 'lodash';
import JSXComponent, {Config} from 'metal-jsx';
import MarkerClusterer from 'node-js-marker-clusterer';
import sub from 'string-sub';

import ContentHeader from './ContentHeader';
import GoogleMap from './GoogleMap';
import SelectInput from './SelectInput';

class HeatMap extends JSXComponent {
	attached() {
		this.drawHeatMap();
	}

	created() {
		bindAll(
			this,
			'drawHeatMap',
			'formatHeatMapData',
			'formatMarkerData',
			'_handleMapsReady'
		);
	}

	drawHeatMap() {
		const {data} = this.props;

		if (this.state.mapsReady) {
			Liferay.Watson.mapComponent.heatMap = new google.maps.visualization.HeatmapLayer(
				{
					data: this.formatHeatMapData(data),
					map: Liferay.Watson.mapComponent.map,
					radius: 40
				}
			);

			Liferay.Watson.mapComponent.markers = new MarkerClusterer(
				Liferay.Watson.mapComponent.map,
				this.formatMarkerData(data),
				{imagePath: ''}
			);
		}
		else {
			this.state.mapsWereNotReady = true;
		}
	}

	formatHeatMapData(data) {
		const items = [];

		if (data) {
			data.forEach(
				item => {
					items.push(new google.maps.LatLng(item.lat, item.lng));
				}
			);
		}

		return items;
	}

	formatMarkerData(data) {
		let markers = [];

		const image = {
			anchor: new google.maps.Point(0, 0),
			origin: new google.maps.Point(0, 0),
			size: new google.maps.Size(7, 7),
			url: `${Liferay.ThemeDisplay.getPathThemeImages()}/transparent.png`
		};

		if (data) {
			markers = data.map(
				item => {
					const marker = new google.maps.Marker(
						{
							icon: image,
							position: {lat: item.lat, lng: item.lng},
							title: item.name
						}
					);

					const markerInfoContent = (`<div class="infoWindow"><a href=${WatsonConstants.urls.baseURL}/incidents/${item.watsonIncidentId}/edit/addresses/${item.watsonAddressId}/edit>${sub(Liferay.Language.get('go-to-x'), sub(Liferay.Language.get('address-x'), item.name))}</a></div>`);

					const markerInfo = new google.maps.InfoWindow({content: markerInfoContent});

					marker.addListener(
						'click',
						() => {
							markerInfo.open(Liferay.Watson.mapComponent.map, marker);
						}
					);

					return marker;
				}
			);
		}

		return markers;
	}

	_handleMapsReady() {
		this.setState({mapsReady: true});
	}

	render() {
		const {updateViewBy, viewBy} = this.props;

		const {center, specialMapOptions, zoom} = this.state;

		return (
			<div class="content-container">
				<div class="content-header">
					<ContentHeader headerStringLeft={Liferay.Language.get('heatmap')} headerStringRight={Liferay.Language.get('view')} />

					<SelectInput
						omitBlankOption={true}
						onChange={updateViewBy}
						options={WatsonConstants.inputConfig.addresses.inputs.typeWatsonListTypeId.options}
						value={viewBy}
					/>
				</div>

				<div class="content">
					<div class="map-wrapper" >
						<GoogleMap
							center={center}
							onClick={this._handleOnClick}
							onReady={this._handleMapsReady}
							options={specialMapOptions}
							zoom={zoom}
						/>
					</div>
				</div>
			</div>
		);
	}

	rendered() {
		const {mapsReady, mapsWereNotReady} = this.state;

		if (!this.props.loading && mapsReady && mapsWereNotReady) {
			this.state.mapsWereNotReady = false;

			this.drawHeatMap();
		}
	}

	syncData(newState, oldState) {
		if (newState !== oldState) {
			this.drawHeatMap();
		}
	}
}

HeatMap.PROPS = {
	data: Config.any,
	loading: Config.bool().value(false),
	updateViewBy: Config.func(),
	viewBy: Config.string()
};

HeatMap.STATE = {
	center: Config.object().value(
		{
			lat: 16.04581345,
			lng: 101.27197265
		}
	),
	mapsReady: Config.bool().value(false),
	mapsWereNotReady: Config.value(null),
	specialMapOptions: Config.object().value(
		{
			mapTypeControl: true,
			mapTypeIds: [
				'ROADMAP', 'HYBRID'
			],
			styles: [
				{
					elementType: 'all',
					featureType: 'all',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'geometry',
					featureType: 'administrative',
					stylers: [
						{
							weight: '0.5'
						},
						{
							visibility: 'on'
						}
					]
				},
				{
					elementType: 'labels',
					featureType: 'administrative',
					stylers: [
						{
							visibility: 'simplified'
						}
					]
				},
				{
					elementType: 'labels.text',
					featureType: 'administrative',
					stylers: [
						{
							lightness: '-50'
						},
						{
							saturation: '-50'
						}
					]
				},
				{
					elementType: 'labels.text',
					featureType: 'administrative.neighborhood',
					stylers: [
						{
							hue: '#009aff'
						},
						{
							saturation: '25'
						},
						{
							lightness: '0'
						},
						{
							visibility: 'simplified'
						},
						{
							gamma: '1'
						}
					]
				},
				{
					elementType: 'geometry',
					featureType: 'landscape',
					stylers: [
						{
							saturation: '0'
						},
						{
							lightness: '100'
						},
						{
							gamma: '2.31'
						},
						{
							visibility: 'on'
						}
					]
				},
				{
					elementType: 'labels',
					featureType: 'landscape',
					stylers: [
						{
							visibility: 'simplified'
						},
						{
							lightness: '20'
						},
						{
							gamma: '1'
						}
					]
				},
				{
					elementType: 'labels.text.fill',
					featureType: 'landscape',
					stylers: [
						{
							saturation: '-100'
						},
						{
							lightness: '-100'
						}
					]
				},
				{
					elementType: 'labels.text.stroke',
					featureType: 'landscape',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'all',
					featureType: 'landscape.man_made',
					stylers: [
						{
							visibility: 'simplified'
						}
					]
				},
				{
					elementType: 'all',
					featureType: 'poi',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'geometry',
					featureType: 'poi.park',
					stylers: [
						{
							lightness: '0'
						},
						{
							saturation: '45'
						},
						{
							gamma: '4.24'
						},
						{
							visibility: 'simplified'
						},
						{
							hue: '#00ff90'
						}
					]
				},
				{
					elementType: 'labels',
					featureType: 'poi.park',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'all',
					featureType: 'road',
					stylers: [
						{
							visibility: 'on'
						}
					]
				},
				{
					elementType: 'geometry',
					featureType: 'road',
					stylers: [
						{
							saturation: '-100'
						},
						{
							color: '#f5f5f5'
						}
					]
				},
				{
					elementType: 'labels.text',
					featureType: 'road',
					stylers: [
						{
							visibility: 'simplified'
						},
						{
							color: '#666666'
						}
					]
				},
				{
					elementType: 'labels.icon',
					featureType: 'road',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'geometry.stroke',
					featureType: 'road.highway',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'geometry.stroke',
					featureType: 'road.arterial',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'labels.icon',
					featureType: 'transit',
					stylers: [
						{
							saturation: '-25'
						}
					]
				},
				{
					elementType: 'all',
					featureType: 'transit.line',
					stylers: [
						{
							visibility: 'simplified'
						}
					]
				},
				{
					elementType: 'labels.icon',
					featureType: 'transit.station.airport',
					stylers: [
						{
							visibility: 'off'
						}
					]
				},
				{
					elementType: 'all',
					featureType: 'water',
					stylers: [
						{
							visibility: 'on'
						}
					]
				},
				{
					elementType: 'geometry.fill',
					featureType: 'water',
					stylers: [
						{
							lightness: '50'
						},
						{
							gamma: '.75'
						},
						{
							saturation: '100'
						}
					]
				},
				{
					elementType: 'labels',
					featureType: 'water',
					stylers: [
						{
							visibility: 'simplified'
						}
					]
				},
				{
					elementType: 'labels.icon',
					featureType: 'water',
					stylers: [
						{
							visibility: 'off'
						}
					]
				}
			]
		}
	),
	zoom: Config.number().value(7)
};

export default HeatMap;