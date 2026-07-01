import { optimizeRoute } from "../services/routeService";
import { useRouteContext } from "../context/RouteContext";

export default function useRoute() {

    const {
    setLoading,
    setRoute,
    setError,
    setLastRequest
} = useRouteContext();

    const calculateRoute = async (request) => {

        try {

            setLoading(true);
            setLastRequest(request);

            setError(null);

            const response = await optimizeRoute(request);

            setRoute(response);

        } catch (error) {

            setRoute(null);

            setError(

                error.response?.data?.message ||

                "Unable to calculate route. Please try again."

            );

        } finally {

            setLoading(false);

        }

    };

    return {

        calculateRoute

    };

}