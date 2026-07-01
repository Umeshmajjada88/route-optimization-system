function StatisticItem({ icon, title, value }) {
    return (
        <div className="flex items-center gap-4">

            <div className="text-blue-600">
                {icon}
            </div>

            <div>

                <p className="text-sm text-gray-500">
                    {title}
                </p>

                <h3 className="text-xl font-bold text-gray-800">
                    {value}
                </h3>

            </div>

        </div>
    );
}

export default StatisticItem;