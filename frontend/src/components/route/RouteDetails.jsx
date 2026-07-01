import { useRouteContext } from "../../context/RouteContext";
import StatisticsCard from "../dashboard/StatisticsCard";
import RouteSummary from "../dashboard/RouteSummary";
import RouteTimeline from "./RouteTimeline";
import LoadingSpinner from "../common/LoadingSpinner";
import EmptyState from "../common/EmptyState";
import ErrorAlert from "../common/ErrorAlert";

function RouteDetails() {
  const { route, loading, error } = useRouteContext();

  if (loading) {
    return (
      <div className="bg-white rounded-xl shadow-md p-6 h-full">
        <h2 className="text-2xl font-bold text-gray-800 mb-6">Route Details</h2>

        <LoadingSpinner message="Calculating Optimal Route..." />
      </div>
    );
  }

  if (error) {
    return (
      <div
        className="
        bg-white
        rounded-xl
        shadow-md
        p-6
        h-full
        lg:sticky
        lg:top-6
    "
      >
        <h2 className="text-2xl font-bold text-gray-800 mb-6">Route Details</h2>

        <ErrorAlert message={error} />
      </div>
    );
  }

  return (
    <div className="bg-white rounded-xl shadow-md p-6 h-full">
      <h2 className="text-2xl font-bold text-gray-800 mb-6">Route Details</h2>

      {!route ? (
        <EmptyState />
      ) : (
        <>
          <RouteSummary route={route} />

          <StatisticsCard route={route} />

          <RouteTimeline path={route.path} />
        </>
      )}
    </div>
  );
}

export default RouteDetails;
