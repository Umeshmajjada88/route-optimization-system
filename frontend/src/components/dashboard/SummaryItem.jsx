function SummaryItem({ label, value }) {

    return (

        <div className="flex justify-between items-center">

            <span className="text-gray-500 font-medium">
                {label}
            </span>

            <span className="font-semibold text-gray-800">
                {value}
            </span>

        </div>

    );

}

export default SummaryItem;