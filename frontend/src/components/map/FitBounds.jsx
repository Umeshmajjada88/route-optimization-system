import { useEffect } from "react";
import { useMap } from "react-leaflet";

function FitBounds({ positions }) {

    const map = useMap();

    useEffect(() => {

        if (!positions || positions.length === 0) {
            return;
        }

        map.fitBounds(positions, {
            padding: [50, 50]
        });

    }, [positions, map]);

    return null;
}

export default FitBounds;