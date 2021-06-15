# graph-gui
NETS 150 final project (Spring 2021)

## Authors: 
Matt Friedman, Emily Paul, Sara Xin, Hetvi Shah

## Name: 
Graph GUI

## Description: 
We created a program that allows the user to create a graph and then calculate characteristics like homophily, page rank, and clustering coefficient, run algorithms, and visualize the graph in R. 

## Categories: 
- Graph and graph algorithms: included multiple algorithms, including Prim’s, Kosaraju's, Kahn’s, and BFS
- Social networks: homophily, clustering coefficient
- Physical networks: page rank

## Work Breakdown:
- Matt: Developed methods for Graph and Node class to integrate with algorithms. Worked with Sara on saving the graph as a CSV (GraphCSVConverter). Wrote graph visualizer on R which reads CSV file, parses it into vectors, and produces the graph visualization.
- Hetvi: Wrote Kosaraju’s algorithm (requiring a new version of DFS not implemented in class), used BFS to find the shortest path between two nodes, created the GUI using JavaSwing (Display.java), and connected each button to the respective popup/output.
- Emily: Implemented Prim’s algorithm to find the minimum spanning tree of an undirected graph, and PageRank algorithm, which runs either 52 times or until page ranks converge. Set up and maintained remote repository, troubleshot git for team members.
- Sara: Wrote Kahn’s algorithm to topologically sort a DAG and implemented homophily for graphs that have 2 colored nodes. Created a converter with Matt that allows our graph type into a csv file that can be read by the R program. 

## Instructions to get started: 
- Clone our remote repository (into your Downloads folder to simplify graph visualization):
	https://github.com/em-paul/graph-gui.git
- Run the Java application, “Display.java”.
- Create a graph.
- Download and retrieve the NodesCSV.csv and EdgesCSV.csv (if the graph-gui directory was not cloned into your Downloads folder, you’ll either need to bring these two files into your Downloads folder or change the filepaths of the NodesCSV and EdgesCSV objects in the GraphR.R file).
- Click here (https://docs.google.com/document/d/1wlR07aXHAaI4sPV5gv0x5w4Lr3akp0j3mZsoAPZTKjQ/edit) to download R and RStudio (courtesy of OIDD 245 staff)
Open the R file and install packages.
* Note: packages may take a little while to download
- There are two options to have R access these files after making a graph: Move both csv files to your Downloads folder OR change the filepath in the R file
- To run the R file, highlight all the lines and press “run”
- See the visualization render in RStudio

## Assumptions made:
- The user will correctly input a proper graph for the respective algorithms/statistics. For example, since Kahn’s algorithm outputs a topological sort of DAGs, we assume that the user will input a DAG.
- The user will only add edges between nodes that have been added and delete nodes/edges of those that have already been added (and they will correctly spell the names of the nodes as they were first input).
- The statistics (homophily, page rank, and clustering coefficient) will work on weighted graphs, but will disregard the weight. 
- Once the user starts adding nodes and edges, they will not change the state of the three checkboxes. 
For homophily, we let the user color the nodes (which is asked for when adding a node if the “Colored” checkbox is checked). We assume that the user will only enter in two colors, which will be the two colors entered as input when wanting to calculate the homophily. We also assume that the user inputs a valid R color (http://www.stat.columbia.edu/~tzheng/files/Rcolor.pdf) to be able to visualize the graph with our R script.
- For any input asked, we assume that the name of the nodes will be valid, existent, and correct.

## Interacting with the GUI:
- Check the respective checkboxes of directed, colored, and weighted.
* Note: Coloring the nodes is to calculate the homophily. See Assumptions for more details.
- Click “Add Node” and enter the name of the node and color if necessary. 
- Click “Add Edge” and enter the names of the two adjacent nodes and the weight if necessary.
- After creating the graph, click on any of the algorithms or statistics buttons to see their output. 
* Note: Some algorithms/statistics will ask for input. For example, “Find Shortest Path” will ask for the starting and ending nodes. Enter the name of the nodes.
* Note: After adding/deleting a node and adding/deleting an edge, you can look into the console for a confirming statement about whether or not the edge/node has been added/deleted.
- Click “Export CSV” to export a CSV file containing the graph that you made. This can be opened in R to see the graph.

## List of various functionalities:

Directed and Unweighted
- Kosaraju’s Alg
- Kahn’s Alg
- BFS
- Clustering Coefficient
- PageRank

Directed and Weighted
- Kosaraju’s Alg
- Kahn’s Alg
- Clustering Coefficient
- PageRank

Undirected and Unweighted
- BFS
- Clustering Coefficient
- PageRank

Undirected and Weighted
- Prim’s Alg
- Clustering Coefficient
- PageRank

Colored
- Homophily

