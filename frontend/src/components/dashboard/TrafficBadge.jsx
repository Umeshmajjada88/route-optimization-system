const badgeStyles = {
    LOW: "bg-green-100 text-green-700",
    MEDIUM: "bg-yellow-100 text-yellow-700",
    HIGH: "bg-orange-100 text-orange-700",
    SEVERE: "bg-red-100 text-red-700",
    BLOCKED: "bg-gray-900 text-white"
};

function TrafficBadge({ level }) {

    return (

        <span
            className={`px-3 py-1 rounded-full text-xs font-semibold ${badgeStyles[level] || "bg-gray-100 text-gray-700"}`}
        >
            {level}
        </span>

    );
}

export default TrafficBadge;