import { useState } from "react";
import { useRouteContext } from "../../context/RouteContext";
import useRoute from "../../hooks/useRoute";

function RouteForm() {

    const { locations } = useRouteContext();

    const { calculateRoute } = useRoute();

    const [source, setSource] = useState("");

    const [destination, setDestination] = useState("");
    const [algorithm, setAlgorithm] = useState("DIJKSTRA");

    const handleSubmit = async () => {

    if (!source || !destination) {

        alert("Please select both locations.");

        return;

    }

    await calculateRoute({

        sourceId: Number(source),

        destinationId: Number(destination),

        algorithm

    });

};

    return (

        <div className="bg-white rounded-xl shadow-md p-6">

            <h2 className="text-xl font-semibold mb-5">

                Optimize Route

            </h2>

            <div className="space-y-4">

                <select
                    className="w-full border rounded-lg p-3"
                    value={source}
                    onChange={(e) => setSource(e.target.value)}
                >

                    <option value="">Select Source</option>

                    {locations.map(location => (

                        <option
                            key={location.id}
                            value={location.id}
                        >

                            {location.name}

                        </option>

                    ))}

                </select>

                <select
                    className="w-full border rounded-lg p-3"
                    value={destination}
                    onChange={(e) => setDestination(e.target.value)}
                >

                    <option value="">Select Destination</option>

                    {locations.map(location => (

                        <option
                            key={location.id}
                            value={location.id}
                        >

                            {location.name}

                        </option>

                    ))}

                </select>

                <select
                        className="w-full border rounded-lg p-3"
                        value={algorithm}
                        onChange={(e) => setAlgorithm(e.target.value)}
                    >

                        <option value="DIJKSTRA">
                            Dijkstra
                        </option>

                        <option value="ASTAR">
                            A*
                        </option>

                    </select>

                <button
                    onClick={handleSubmit}
                    className="w-full bg-blue-600 hover:bg-blue-700 text-white rounded-lg py-3"
                >

                    Optimize Route

                </button>

            </div>

        </div>

    );

}

export default RouteForm;