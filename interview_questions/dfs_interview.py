#!/usr/bin/env python
## Author: Dan Tracy
## Program: Interview Problem

import sys  # for sys.exit

## We will represent the graph simply as a mapping of nodes to a
## list of adjacent nodes.  This is similar to using the standard
## adjacency list.
graph = {
  'a': ['b', 'c'],
  'b': ['d'],
  'c': ['d'],
  'd': ['b', 'c'],
}

start_node = 'a'
end_node = 'd'


## DFS implementation to find all paths
def dfs_all_paths(graph, start_node, end_node, path):
    """
    Takes a graph dictionary along with the starting and ending node then
    computes all possible paths from start to end by making use of the
    depth first search algorithm
    """
    path = path + [start_node]

    if start_node == end_node:
        return [path]

    if not start_node in graph:
        return []

    paths = []
    for node in graph[start_node]:
        ## If the node is not already in the path recursively explore it
        if node not in path:
            paths += dfs_all_paths(graph, node, end_node, path)

    return paths


if __name__ == '__main__':
    if start_node not in graph or end_node not in graph:
        print("Either the starting node or ending node are not in the graph")
        sys.exit(1)

    paths = dfs_all_paths(graph, start_node, end_node, [])

    if not paths:
        print("No path from {} to {} was found".format(start_node, end_node))
    else:
        print("All paths from node {} to node {} are".format(start_node, end_node))
        for n, p in enumerate(paths, start=1):
            print("Path {}: {}".format(n, p))

    sys.exit(0)
