import { FaMapMarkedAlt } from "react-icons/fa";

function EmptyState({

    title = "No Route Selected",
    message = "Choose a source, destination and algorithm to calculate the optimal route."

}) {

    return (

        <div className="flex flex-col items-center justify-center py-12 text-center">

            <FaMapMarkedAlt
                size={55}
                className="text-blue-500 mb-5"
            />

            <h3 className="text-xl font-semibold text-gray-700">
                {title}
            </h3>

            <p className="text-gray-500 mt-3 max-w-sm">
                {message}
            </p>

        </div>

    );

}

export default EmptyState;