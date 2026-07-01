import { FaCheckCircle } from "react-icons/fa";
import SummaryItem from "./SummaryItem";

function RouteSummary({ route }) {

    if (!route) {
        return null;
    }

    return (

        <div className="border rounded-xl p-5 bg-green-50 border-green-200">

            <div className="flex items-center gap-2 mb-4">

                <FaCheckCircle
                    className="text-green-600"
                    size={20}
                />

                <h3 className="font-bold text-green-700">
                    Route Found
                </h3>

            </div>

            <div className="space-y-3">

                <SummaryItem
                    label="Algorithm"
                    value={route.algorithm}
                />

                <SummaryItem
                    label="Distance"
                    value={`${route.totalDistance.toFixed(2)} km`}
                />

                <SummaryItem
                    label="Travel Time"
                    value={`${route.totalTravelTime.toFixed(2)} mins`}
                />

                <SummaryItem
                    label="Stops"
                    value={route.path.length}
                />

            </div>

        </div>

    );

}

export default RouteSummary;