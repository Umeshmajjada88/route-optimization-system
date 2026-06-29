import { useRouteContext } from "../../context/RouteContext";

function RouteDetails() {

    const { route } = useRouteContext();

    return (

        <div className="bg-white rounded-xl shadow-md p-6 h-full">

            <h2 className="text-xl font-semibold mb-5">

                Route Details

            </h2>

            {

                !route ?

                    <p>No Route Selected</p>

                    :

                    <>

                        <p>

                            <strong>Total Distance :</strong>{" "}

                            {route.totalDistance.toFixed(2)} km

                        </p>

                        <p className="mt-2">

                            <strong>Total Travel Time :</strong>{" "}

                            {route.totalTravelTime.toFixed(2)} mins

                        </p>

                        <hr className="my-4" />

                        <h3 className="font-semibold">

                            Path

                        </h3>

                        <ul className="list-disc pl-5 mt-2">

                            {

                                route.path.map(node => (

                                    <li key={node.id}>

                                        {node.name}

                                    </li>

                                ))

                            }

                        </ul>

                    </>

            }

        </div>

    );

}

export default RouteDetails;