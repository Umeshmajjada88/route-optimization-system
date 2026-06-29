import { useEffect } from "react";
import { getAllLocations } from "../services/locationService";
import { useRouteContext } from "../context/RouteContext";

export default function useLocations() {

    const { setLocations } = useRouteContext();

    useEffect(() => {

        getAllLocations()
            .then(setLocations)
            .catch(console.error);

    }, []);

}