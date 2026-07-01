import {
    FaClock,
    FaRoute,
    FaProjectDiagram,
    FaStopwatch
} from "react-icons/fa";

import Card from "../common/Card";
import StatisticItem from "./StatisticItem";

function StatisticsCard({ route }) {

    if (!route || !route.statistics) {
        return null;
    }

    const statistics = route.statistics;

    return (

        <Card className="mt-6">

            <h2 className="text-lg font-semibold text-gray-800 mb-5">
                Algorithm Statistics
            </h2>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">

                <StatisticItem
                    icon={<FaClock size={22} />}
                    title="Travel Time"
                    value={`${route.totalTravelTime} mins`}
                />

                <StatisticItem
                    icon={<FaRoute size={22} />}
                    title="Distance"
                    value={`${route.totalDistance.toFixed(2)} km`}
                />

                <StatisticItem
                    icon={<FaProjectDiagram size={22} />}
                    title="Visited Nodes"
                    value={statistics.visitedNodes}
                />

                <StatisticItem
                    icon={<FaStopwatch size={22} />}
                    title="Execution Time"
                    value={`${statistics.executionTimeMs} ms`}
                />

            </div>

        </Card>

    );
}

export default StatisticsCard;