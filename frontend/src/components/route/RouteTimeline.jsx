import TimelineNode from "./TimelineNode";

function RouteTimeline({ path }) {

    if (!path || path.length === 0) {
        return null;
    }

    return (
      <div className="mt-8">
        <h3 className="text-xl font-semibold text-gray-800 mb-6">
          Route Timeline
        </h3>

        {path.map((node, index) => (
          <TimelineNode
            key={node.id}
            node={node}
            index={index}
            isLast={index === path.length - 1}
          />
        ))}
      </div>
    );

}

export default RouteTimeline;