/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Brian Nguyen, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: bn6652
 *  email address: briannguyen@utexas.edu
 *  TA name: Sai
 *  Number of slip days I am using: 1
 */

/*
 * Questions.
 *
 * 1. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results. You may
 * have to do some research on the various college football divisions to
 * make an informed answer. (What are the divisions within college
 * football? Who do teams play? How would this affect the basic
 * structure of the graph?)
 *
 * 2. Suggest another way of method of ranking teams using the results
 * from the graph. Thoroughly explain your method. The method can build
 * on one of the three existing algorithms.
 *
 * Results from 2008ap_poll.txt and div12008.txt:
 *
 * UNWEIGHTED rmse - 13.7040
 * WEIGHTED rmse - 12.5905
 * WEIGHTED win percent rmse - 6.3467
 *
 * Results from 2014.ap_poll.txt and div12014.txt:
 *
 * UNWEIGHTED rmse - 10.0896
 * WEIGHTED rmse - 8.5229
 * WEIGHTED win percent rmse - 4.1617
 *
 * Results from 2005ap_poll.txt and div12005.txt:
 *
 * UNWEIGHTED rmse - 6.8000
 * WEIGHTED rmse - 5.8241
 * WEIGHTED win percent rmse - 2.9799
 *
 * Results from 2008ap_poll.txt and games08.txt:
 *
 * UNWEIGHTED rmse - 22.6416
 * WEIGHTED rmse - 13.9900
 * WEIGHTED win percent rmse - 8.2049
 *
 * Results from 2014.ap_poll.txt and games08.txt:
 *
 * UNWEIGHTED rmse - 111.6002
 * WEIGHTED rmse - 110.9690
 * WEIGHTED win percent rmse - 110.8449
 *
 * Results from 2005ap_poll.txt and games08.txt:
 *
 * UNWEIGHTED rmse - 96.9342
 * WEIGHTED rmse - 95.6650
 * WEIGHTED win percent rmse - 95.4344
 *
 * 1. Based on the calculated RMSEs, using the games08.txt file with AP polls resulted in a
 * significantly higher RMSE than expected. This is due to the fact that the FootballRanker
 * cuts out teams from predictions if they do not have enough wins and because the AP poll files
 * do not have as many teams as in games08.txt. So teams that are in the AP poll file but are cut
 * out from predictions end up having a much higher predicted rank, causing the RMSE to also
 * be unexpectedly high. Additionally, since games08.txt includes all games aside from division 1
 * teams, this ends up creating a graph with disconnected sub-graphs as teams do not play against
 * other teams in different divisions.
 *
 * 2. Since the prediction graph cuts out certain teams based on minimum transitive or direct wins,
 * this new algorithm can set the minimum to 0 to make sure every team is ranked. As for the actual
 * algorithm, this method will be similar to the third ranking algorithm. However, instead of using
 * weighted paths and dividing by win-loss percentage, it'll use the unweighted paths and divide
 * by win-loss percentage. Although this might be less accurate than the third method, it could
 * be more accurate than the first and second ranking algorithms.
 *
 */

public class GraphAndRankTester {

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)  {
        studentGraphTests();
        graphTests();

        String actual = "2008ap_poll.txt";
        String gameResults = "div12008.txt";

        FootballRanker ranker = new FootballRanker(gameResults, actual);

        ranker.doUnweighted(true);
        ranker.doWeighted(true);
        ranker.doWeightedAndWinPercentAdjusted(true);

        System.out.println();
        stuRankTests(ranker, actual, gameResults);

        System.out.println();
    }

    // tests on various simple Graphs
    private static void graphTests() {
        System.out.println("PERFORMING TESTS ON SIMPLE GRAPHS\n");
//        graph0Tests();
        graph1Tests();
        graph2Tests();
        graph3Tests();
    }

    /* The graph used here is the same one from the example
     * used to show Dijkstra's algorithm from the slides on
     * Graphs. It may be useful to print out the priority queue
     * at the top of the main while loop in the dijkstra method
     * to see if it matches the one in the slides. Note, the Java
     * PriorityQueue (which you CAN use in the dijkstra method)
     * breaks ties in an arbitrary manner, so the order of equal
     * elements in the priority queue may be different than those
     * shown in the slides. (And that is not a problem.)
     */
    private static void graph0Tests() {
        System.out.println("Graph #0 Tests:");
        // first a simple path test
        // Graph #0
        String [][] g1Edges =  {{"A", "B", "1"},
                        {"B", "C", "3"},
                        {"A", "C", "7"},
                        {"B", "D", "21"},
                        {"C", "F", "3"},
                        {"A", "G", "17"},
                        {"D", "F", "4"},
                        {"D", "G", "5"},
                        {"D", "E", "6"}};
        Graph g1 = getGraph(g1Edges, false);

        g1.dijkstra("A");
        String actualPath = g1.findPath("E").toString();
        String expected = "[A, B, C, F, D, E]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test graph 0.");
        } else {
            System.out.println("Failed dijkstra path test graph 0. Expected: " + expected + " actual " + actualPath);
        }

        // now do all paths unweighted
        String[] expectedPaths = {"Name: D                    cost per path: 1.3333, num paths: 6",
                        "Name: B                    cost per path: 1.5000, num paths: 6",
                        "Name: A                    cost per path: 1.6667, num paths: 6",
                        "Name: C                    cost per path: 1.6667, num paths: 6",
                        "Name: F                    cost per path: 1.6667, num paths: 6",
                        "Name: G                    cost per path: 1.6667, num paths: 6",
                        "Name: E                    cost per path: 2.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, false, 3, 3.0, expectedPaths);

        // now do all paths weighted
        expectedPaths = new String[] {  "Name: F                    cost per path: 6.5000, num paths: 6",
                        "Name: C                    cost per path: 7.0000, num paths: 6",
                        "Name: D                    cost per path: 7.1667, num paths: 6",
                        "Name: B                    cost per path: 8.5000, num paths: 6",
                        "Name: A                    cost per path: 9.3333, num paths: 6",
                        "Name: G                    cost per path: 11.3333, num paths: 6",
                        "Name: E                    cost per path: 12.1667, num paths: 6"};
        doAllPathsTests("Graph 0", g1, true, 5, 17.0, expectedPaths);
    }

    private static void graph1Tests() {
        System.out.println("Graph #1 Tests:");
        // first a simple path test
        // Graph #1
        String [][] g1Edges =  {{"A", "B", "1"},
                        {"B", "C", "3"},
                        {"B", "D", "12"},
                        {"C", "F", "3"},
                        {"A", "G", "7"},
                        {"D", "F", "4"},
                        {"D", "G", "5"},
                        {"D", "E", "6"}};
        Graph g1 = getGraph(g1Edges, false);

        g1.dijkstra("A");
        String actualPath = g1.findPath("E").toString();
        String expected = "[A, B, C, F, D, E]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test graph 1.");
        } else {
            System.out.println("Failed dijkstra path test graph 1. Expected: " + expected + " actual " + actualPath);
        }

        // now do all paths unweighted
        String[] expectedPaths = {"Name: D                    cost per path: 1.3333, num paths: 6",
                        "Name: B                    cost per path: 1.5000, num paths: 6",
                        "Name: F                    cost per path: 1.8333, num paths: 6",
                        "Name: G                    cost per path: 1.8333, num paths: 6",
                        "Name: A                    cost per path: 2.0000, num paths: 6",
                        "Name: C                    cost per path: 2.0000, num paths: 6",
                        "Name: E                    cost per path: 2.1667, num paths: 6"};
        doAllPathsTests("Graph 1", g1, false, 3, 3.0, expectedPaths);

        // now do all paths weighted
        expectedPaths = new String[] {  "Name: F                    cost per path: 6.5000, num paths: 6",
                        "Name: C                    cost per path: 6.8333, num paths: 6",
                        "Name: D                    cost per path: 7.1667, num paths: 6",
                        "Name: B                    cost per path: 7.3333, num paths: 6",
                        "Name: A                    cost per path: 7.8333, num paths: 6",
                        "Name: G                    cost per path: 8.5000, num paths: 6",
                        "Name: E                    cost per path: 12.1667, num paths: 6"};
        doAllPathsTests("Graph 1", g1, true, 5, 17.0, expectedPaths);
    }

    private static void graph2Tests() {
        System.out.println("Graph #2 Tests:");
        // first a simple path test
        // Graph #1
        String[][] g2Edges = {{"E", "G", "9.6"},
                        {"G", "E", "19.2"},
                        {"D", "F", "4.0"},
                        {"F", "D", "8.0"},
                        {"E", "B", "8.0"},
                        {"B", "E", "16.0"},
                        {"F", "A", "6.0"},
                        {"A", "F", "12.0"},
                        {"F", "C", "4.0"},
                        {"C", "F", "8.0"},
                        {"C", "E", "6.9"},
                        {"E", "C", "13.8"},
                        {"D", "G", "8.0"},
                        {"G", "D", "16.0"},
                        {"E", "A", "5.7"},
                        {"A", "E", "11.4"},
                        {"C", "A", "0.4"},
                        {"A", "C", "0.8"},
                        {"D", "A", "6.1"},
                        {"A", "D", "12.2"},
                        {"D", "B", "7.9"},
                        {"B", "D", "15.8"},
                        {"C", "G", "5.4"},
                        {"G", "C", "10.8"},
                        {"A", "B", "7.1"},
                        {"B", "A", "14.2"},
                        {"E", "F", "4.4"},
                        {"F", "E", "8.8"}};
        Graph g2 = getGraph(g2Edges, true);



        // do all paths weighted
        String[] expectedPaths = new String[] { "Name: C                    cost per path: 6.8000, num paths: 6",
                        "Name: A                    cost per path: 7.1333, num paths: 6",
                        "Name: D                    cost per path: 7.6167, num paths: 6",
                        "Name: F                    cost per path: 7.6833, num paths: 6",
                        "Name: E                    cost per path: 7.7667, num paths: 6",
                        "Name: G                    cost per path: 15.4667, num paths: 6",
                        "Name: B                    cost per path: 16.8667, num paths: 6"};
        doAllPathsTests("Graph 2", g2, true, 3, 20.4, expectedPaths);
    }

    // Graph 3 is an unconnected Graph
    private static void graph3Tests() {
        System.out.println("Graph 3 Tests. Graph 3 is not fully connected. ");
        String [][] g3Edges =
                    {{"A", "B", "13"},
                                    {"A", "C", "10"},
                                    {"A", "D", "2"},
                                    {"B", "E", "5"},
                                    {"C", "B", "1"},
                                    {"D", "C", "5"},
                                    {"E", "G", "1"},
                                    {"E", "F", "4"},
                                    {"F", "C", "3"},
                                    {"F", "E", "2"},
                                    {"G", "F", "2"},
                                    {"H", "I", "10"},
                                    {"H", "J", "5"},
                                    {"H", "K", "22"},
                                    {"I", "K", "3"},
                                    {"I", "J", "1"},
                                    {"J", "L", "8"}};
        Graph g3 = getGraph(g3Edges, true);

        // do all paths weighted
        String[] expectedPaths = {"Name: A                    cost per path: 10.0000, num paths: 6",
                        "Name: D                    cost per path: 9.6000, num paths: 5",
                        "Name: F                    cost per path: 3.0000, num paths: 4",
                        "Name: E                    cost per path: 4.2500, num paths: 4",
                        "Name: G                    cost per path: 4.2500, num paths: 4",
                        "Name: C                    cost per path: 5.7500, num paths: 4",
                        "Name: B                    cost per path: 7.5000, num paths: 4",
                        "Name: H                    cost per path: 10.2500, num paths: 4",
                        "Name: I                    cost per path: 4.3333, num paths: 3",
                        "Name: J                    cost per path: 8.0000, num paths: 1"};
        doAllPathsTests("Graph 3", g3, true, 6, 16.0, expectedPaths);
    }

    private static void studentGraphTests() {
        // undirected graph
        System.out.println("Student Graph #1 Tests:");
        String[][] g1Edges = {
            {"A", "B", "7"},
            {"A", "E", "3"},
            {"C", "B", "2"},
            {"D", "B", "3"},
            {"D", "F", "4"},
            {"G", "E", "4"},
            {"H", "F", "9"},
            {"G", "I", "3"},
            {"I", "F", "1"}};
        Graph g1 = getGraph(g1Edges, false);

        // test 1 - dijkstra
        g1.dijkstra("A");
        String actualPath = g1.findPath("H").toString();
        String expected = "[A, E, G, I, F, H]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test 1 - student graph 1.");
        } else {
            System.out.println("Failed dijkstra path test 1 - student graph 1. Expected: " +
                expected + " actual: " + actualPath);
        }

        // test 2 - dijkstra
        g1.dijkstra("G");
        actualPath = g1.findPath("C").toString();
        expected = "[G, I, F, D, B, C]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test 2 - student graph 1.");
        } else {
            System.out.println("Failed dijkstra path test 2 - student graph 1. Expected: " +
                expected + " actual: " + actualPath);
        }

        // unweighted test, graph 1
        String[] expectedPaths = {
            "Name: B                    cost per path: 2.0000, num paths: 8",
            "Name: D                    cost per path: 2.0000, num paths: 8",
            "Name: F                    cost per path: 2.0000, num paths: 8",
            "Name: A                    cost per path: 2.2500, num paths: 8",
            "Name: I                    cost per path: 2.2500, num paths: 8",
            "Name: E                    cost per path: 2.3750, num paths: 8",
            "Name: G                    cost per path: 2.3750, num paths: 8",
            "Name: C                    cost per path: 2.8750, num paths: 8",
            "Name: H                    cost per path: 2.8750, num paths: 8"};
        doAllPathsTests("Student Graph 1", g1, false, 4, 4.0, expectedPaths);

        // weighted test, graph 1
        expectedPaths = new String[] {
            "Name: F                    cost per path: 6.6250, num paths: 8",
            "Name: I                    cost per path: 6.7500, num paths: 8",
            "Name: D                    cost per path: 7.5000, num paths: 8",
            "Name: G                    cost per path: 7.8750, num paths: 8",
            "Name: B                    cost per path: 8.0000, num paths: 8",
            "Name: E                    cost per path: 9.1250, num paths: 8",
            "Name: A                    cost per path: 9.6250, num paths: 8",
            "Name: C                    cost per path: 9.7500, num paths: 8",
            "Name: H                    cost per path: 14.5000, num paths: 8"};
        doAllPathsTests("Student Graph 1", g1, true, 5, 20.0, expectedPaths);

        // directed graph
        System.out.println("Student Graph #2 Tests:");
        String[][] g2Edges = {
            {"A", "B", "3"},
            {"A", "E", "2"},
            {"B", "D", "2"},
            {"C", "A", "7"},
            {"D", "E", "1"},
            {"E", "B", "3"},
            {"E", "F", "4"},
            {"F", "C", "5"}};
        Graph g2 = getGraph(g2Edges, true);

        // test 1 - dijkstra
        g2.dijkstra("F");
        actualPath = g2.findPath("D").toString();
        expected = "[F, C, A, B, D]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test 1 - student graph 2.");
        } else {
            System.out.println("Failed dijkstra path test 1 - student graph 2. Expected: " +
                expected + " actual: " + actualPath);
        }

        // test 2 - dijkstra
        g2.dijkstra("C");
        actualPath = g2.findPath("B").toString();
        expected = "[C, A, B]";
        if (actualPath.equals(expected)) {
            System.out.println("Passed dijkstra path test 2 - student graph 2.");
        } else {
            System.out.println("Failed dijkstra path test 2 - student graph 2. Expected: " +
                expected + " actual: " + actualPath);
        }

        // unweighted test, graph 2
        expectedPaths = new String[] {
            "Name: A                    cost per path: 1.8000, num paths: 5",
            "Name: E                    cost per path: 1.8000, num paths: 5",
            "Name: C                    cost per path: 2.2000, num paths: 5",
            "Name: D                    cost per path: 2.4000, num paths: 5",
            "Name: F                    cost per path: 2.6000, num paths: 5",
            "Name: B                    cost per path: 3.0000, num paths: 5"};
        doAllPathsTests("Student Graph 2", g2, false, 5, 5.0, expectedPaths);

        // weighted test, graph 2
        expectedPaths = new String[] {
            "Name: A                    cost per path: 5.4000, num paths: 5",
            "Name: D                    cost per path: 7.4000, num paths: 5",
            "Name: E                    cost per path: 7.4000, num paths: 5",
            "Name: B                    cost per path: 8.6000, num paths: 5",
            "Name: C                    cost per path: 10.2000, num paths: 5",
            "Name: F                    cost per path: 12.6000, num paths: 5"};
        doAllPathsTests("Student Graph 2", g2, true, 5, 19.0, expectedPaths);
    }

    // return a Graph based on the given edges
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }

    // Tests the all paths method. Run each set of tests twice to ensure the Graph
    // is correctly reseting each time
    private static void doAllPathsTests(String graphNumber, Graph g, boolean weighted,
                    int expectedDiameter, double expectedCostOfLongestShortestPath,
                    String[] expectedPaths) {

        System.out.println("\nTESTING ALL PATHS INFO ON " + graphNumber + ". ");
        for (int i = 0; i < 2; i++) {
            System.out.println("Test run = " + (i + 1));
            System.out.println("Find all paths weighted = " + weighted);
            g.findAllPaths(weighted);
            int actualDiameter = g.getDiameter();
            double actualCostOfLongestShortesPath = g.costOfLongestShortestPath();
            if (actualDiameter == expectedDiameter) {
                System.out.println("Passed diameter test.");
            } else {
                System.out.println("FAILED diameter test. "
                                + "Expected = "  + expectedDiameter +
                                " Actual = " + actualDiameter);
            }
            if (actualCostOfLongestShortesPath == expectedCostOfLongestShortestPath) {
                System.out.println("Passed cost of longest shortest path. test.");
            } else {
                System.out.println("FAILED cost of longest shortest path. "
                                + "Expected = "  + expectedCostOfLongestShortestPath +
                                " Actual = " + actualCostOfLongestShortesPath);
            }
            testAllPathsInfo(g, expectedPaths);
            System.out.println();
        }

    }

    // Compare results of all paths info on Graph to expected results.
    private static void testAllPathsInfo(Graph g, String[] expectedPaths) {
        int index = 0;

        for (AllPathsInfo api : g.getAllPaths()) {
            if (expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            } else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
        System.out.println();
    }

    // Test the FootballRanker on the given file.
    private static void stuRankTests(FootballRanker ranker, String actual, String gameResults) {
        System.out.println("\nTESTS ON FOOTBALL TEAM GRAPH WITH FootBallRanker CLASS: \n");
        if (actual.equals("2008ap_poll.txt") && gameResults.equals("div12008.txt")) {
            double actualError = ranker.doUnweighted(false);
            if (actualError == 13.7) {
                System.out.println("Passed unweighted test");
            } else {
                System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. " +
                    "Expected 13.7, actual: " + actualError);
            }

            actualError = ranker.doWeighted(false);
            if (actualError == 12.6) {
                System.out.println("Passed weigthed test");
            } else {
                System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. " +
                    "Expected 12.6, actual: " + actualError);
            }

            actualError = ranker.doWeightedAndWinPercentAdjusted(false);
            if (actualError == 6.3) {
                System.out.println("Passed unweighted win percent test");
            } else {
                System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR " +
                        "TEST. Expected 6.3, actual: " + actualError);
            }
        } else if (actual.equals("2014ap_poll.txt") && gameResults.equals("div12014.txt")) {
            double actualError = ranker.doUnweighted(false);
            if (actualError == 10.1) {
                System.out.println("Passed unweighted test - 2014 AP poll and games");
            } else {
                System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST - 2014 AP poll" +
                    " and games. Expected 10.1, actual: " + actualError);
            }

            actualError = ranker.doWeighted(false);
            if (actualError == 8.5) {
                System.out.println("Passed weighted test - 2014 AP poll and games");
            } else {
                System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST - 2014 AP poll " +
                    "and games. Expected 8.5, actual: " + actualError);
            }

            actualError = ranker.doWeightedAndWinPercentAdjusted(false);
            if (actualError == 4.2) {
                System.out.println("Passed unweighted win percent test - 2014 AP poll and games");
            } else {
                System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST" +
                    " - 2014 AP poll and games. Expected 4.2, actual: " + actualError);
            }
        } else if (actual.equals("2005ap_poll.txt") && gameResults.equals("div12005.txt")) {
            double actualError = ranker.doUnweighted(false);
            if (actualError == 6.8) {
                System.out.println("Passed unweighted test - 2005 AP poll and games");
            } else {
                System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST - 2005 AP poll" +
                    " and games. Expected 6.8, actual: " + actualError);
            }

            actualError = ranker.doWeighted(false);
            if (actualError == 5.8) {
                System.out.println("Passed weighted test - 2005 AP poll and games");
            } else {
                System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST - 2005 AP poll " +
                    "and games. Expected 5.8, actual: " + actualError);
            }

            actualError = ranker.doWeightedAndWinPercentAdjusted(false);
            if (actualError == 3.0) {
                System.out.println("Passed unweighted win percent test - 2005 AP poll and games");
            } else {
                System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST" +
                    " - 2005 AP poll and games. Expected 3.0, actual: " + actualError);
            }
        }
    }
}
