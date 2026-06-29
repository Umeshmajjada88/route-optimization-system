import {
    MapContainer,
    Marker,
    Popup,
    Polyline,
    TileLayer
} from "react-leaflet";

import { useRouteContext } from "../../context/RouteContext";

import {
    sourceIcon,
    destinationIcon,
    intermediateIcon
} from "../../utils/mapIcons";

import FitBounds from "./FitBounds";
import MapLegend from "./MapLegend";

function MapView() {

    const { route, locations } = useRouteContext();

    const defaultCenter = [17.42, 78.39];

    const pathCoordinates =
        route?.path.map(node => [
            node.latitude,
            node.longitude
        ]) || [];

    return (

        <div className="relative bg-white rounded-xl shadow-md overflow-hidden">

            <MapLegend />

            <MapContainer
                center={defaultCenter}
                zoom={11}
                style={{
                    height: "550px",
                    width: "100%"
                }}
            >

                <TileLayer
                    attribution="&copy; OpenStreetMap contributors"
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />

                {/* Show all locations except those in the optimized route */}
                {
                    locations
                        .filter(location =>
                            !route?.path?.some(node => node.id === location.id)
                        )
                        .map(location => (

                            <Marker
                                key={location.id}
                                position={[
                                    location.latitude,
                                    location.longitude
                                ]}
                            >

                                <Popup>
                                    {location.name}
                                </Popup>

                            </Marker>

                        ))
                }

                {/* Optimized route markers */}
                {
                    route &&
                    route.path.map((node, index) => {

                        let icon = intermediateIcon;

                        if (index === 0) {
                            icon = sourceIcon;
                        } else if (index === route.path.length - 1) {
                            icon = destinationIcon;
                        }

                        return (

                            <Marker
                                key={node.id}
                                position={[
                                    node.latitude,
                                    node.longitude
                                ]}
                                icon={icon}
                            >

                                <Popup>
                                    <strong>{node.name}</strong>
                                </Popup>

                            </Marker>

                        );

                    })
                }

                {/* Route Line */}
                {
                    route && (

                        <Polyline
                            positions={pathCoordinates}
                            pathOptions={{
                                color: "#2563eb",
                                weight: 6
                            }}
                        />

                    )
                }

                {/* Auto Zoom */}
                {
                    route && (
                        <FitBounds positions={pathCoordinates} />
                    )
                }

            </MapContainer>

        </div>

    );

}

export default MapView;