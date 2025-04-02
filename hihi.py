import heapq

def find_best_route(graph, start, end):
    """
    Finds the shortest path in a weighted graph using Dijkstra's algorithm.

    :param graph: Dictionary representing the graph {node: [(neighbor, weight), ...]}
    :param start: Starting node
    :param end: Destination node
    :return: Tuple (shortest_distance, path)
    """
    priority_queue = [(0, start, [])]  # (current_distance, current_node, path)
    visited = set()

    while priority_queue:
        current_distance, current_node, path = heapq.heappop(priority_queue)

        if current_node in visited:
            continue

        visited.add(current_node)
        path = path + [current_node]

        if current_node == end:
            return current_distance, path

        for neighbor, weight in graph.get(current_node, []):
            if neighbor not in visited:
                heapq.heappush(priority_queue, (current_distance + weight, neighbor, path))

    return float('inf'), []  # No path found

# Example usage
if __name__ == "__main__":
    graph = {
        'A': [('B', 1), ('C', 4)],
        'B': [('C', 2), ('D', 5)],
        'C': [('D', 1)],
        'D': []
    }
    start_node = 'A'
    end_node = 'D'
    distance, route = find_best_route(graph, start_node, end_node)
    print(f"Shortest distance: {distance}")
    print(f"Route: {' -> '.join(route)}")