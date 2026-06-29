function MapLegend() {

    return (

        <div className="absolute top-4 right-4 z-[1000] bg-white rounded-lg shadow-lg p-4">

            <h3 className="font-semibold mb-2">
                Legend
            </h3>

            <div className="space-y-2 text-sm">

                <div className="flex items-center gap-2">
                    <span className="text-green-600">🟢</span>
                    <span>Source</span>
                </div>

                <div className="flex items-center gap-2">
                    <span className="text-red-600">🔴</span>
                    <span>Destination</span>
                </div>

                <div className="flex items-center gap-2">
                    <span className="text-blue-600">🔵</span>
                    <span>Intermediate</span>
                </div>

                <div className="flex items-center gap-2">
                    <span>⚪</span>
                    <span>Other Locations</span>
                </div>

            </div>

        </div>

    );

}

export default MapLegend;