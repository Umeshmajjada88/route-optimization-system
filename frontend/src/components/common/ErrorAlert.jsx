import { FaExclamationTriangle } from "react-icons/fa";

function ErrorAlert({

    message = "Something went wrong."

}) {

    return (

        <div
            className="
                bg-red-50
                border
                border-red-300
                rounded-lg
                p-4
                flex
                items-center
                gap-3
            "
        >

            <FaExclamationTriangle
                className="text-red-600"
                size={22}
            />

            <span className="text-red-700 font-medium">
                {message}
            </span>

        </div>

    );

}

export default ErrorAlert;