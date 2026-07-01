import { useTrafficContext } from "../../context/TrafficContext";
import { useRouteContext } from "../../context/RouteContext";
import useRoute from "../../hooks/useRoute";

function TrafficNotification() {
  const { trafficUpdated, setTrafficUpdated } = useTrafficContext();

  const { lastRequest } = useRouteContext();

  const { calculateRoute } = useRoute();

  if (!trafficUpdated) {
    return null;
  }

  const handleRecalculate = async () => {
    console.log("Last Request:", lastRequest);

    if (!lastRequest) {
      setTrafficUpdated(false);
      return;
    }

    await calculateRoute(lastRequest);

    setTrafficUpdated(false);
  };

  return (
    <div className="bg-yellow-100 border border-yellow-300 rounded-lg p-4 mb-6 flex items-center justify-between">
      <div>
        <p className="font-semibold text-yellow-900">
          🚦 Traffic conditions have changed.
        </p>

        <p className="text-sm text-yellow-700">
          Your current route may no longer be optimal.
        </p>
      </div>

      <div className="flex gap-3">
        <button
          onClick={handleRecalculate}
          className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg"
        >
          Recalculate Route
        </button>

        <button
          onClick={() => setTrafficUpdated(false)}
          className="bg-gray-300 hover:bg-gray-400 px-4 py-2 rounded-lg"
        >
          Dismiss
        </button>
      </div>
    </div>
  );
}

export default TrafficNotification;
