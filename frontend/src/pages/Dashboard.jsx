import RouteForm from "../components/route/RouteForm";
import RouteDetails from "../components/route/RouteDetails";
import MapView from "../components/map/MapView";
import TrafficNotification from "../components/traffic/TrafficNotification";

import useLocations from "../hooks/useLocations";

function Dashboard() {
  useLocations();

  return (
    <div className="space-y-6">
      <TrafficNotification />

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <RouteForm />

        <div className="md:col-span-2">
          <RouteDetails />
        </div>
      </div>

      <MapView />
    </div>
  );
}

export default Dashboard;
