function TimelineNode({ node, index, isLast }) {

    return (
      <div className="flex">
        <div className="flex flex-col items-center mr-4">
          <div
            className="
                        w-10
                        h-10
                        rounded-full
                        bg-blue-600
                        text-white
                        flex
                        items-center
                        justify-center
                        font-semibold
                    "
          >
            {index + 1}
          </div>

          {!isLast && (
            <div
              className="
                                w-1
                                flex-1
                                bg-blue-300
                                my-2
                                min-h-8
                            "
            />
          )}
        </div>

        <div className="pb-10 flex-1">
          <h4 className="font-semibold text-gray-800">{node.name}</h4>

          <p className="text-sm text-gray-500">Latitude : {node.latitude}</p>

          <p className="text-sm text-gray-500">Longitude : {node.longitude}</p>
        </div>
      </div>
    );

}

export default TimelineNode;