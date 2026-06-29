import { optimizeRoute } from "../services/routeService";
import { useRouteContext } from "../context/RouteContext";

export default function useRoute() {

    const {

        setLoading,

        setRoute

    } = useRouteContext();

    const calculateRoute = async (request) => {

        try {

            setLoading(true);

            const response = await optimizeRoute(request);

            setRoute(response);

        } finally {

            setLoading(false);

        }

    };

    return {

        calculateRoute

    };

}