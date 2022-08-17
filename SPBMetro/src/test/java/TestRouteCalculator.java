import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;

public class TestRouteCalculator extends TestCase {

    public static final Line LINE_1 = new Line(1, "Line1");
    public static final Line LINE_2 = new Line(2, "Line2");
    public static final Line LINE_3 = new Line(3, "Line3");

    public static final Station STATION_1 = new Station("Station1", LINE_1);
    public static final Station STATION_2 = new Station("Station2", LINE_1);
    public static final Station STATION_3 = new Station("Station3", LINE_2);
    public static final Station STATION_7 = new Station("Station7", LINE_2);
    public static final Station STATION_4 = new Station("Station4", LINE_2);
    public static final Station STATION_5 = new Station("Station5", LINE_3);
    public static final Station STATION_6 = new Station("Station6", LINE_3);
    public static final Station STATION_8 = new Station("Station8", LINE_3);

    public StationIndex stationIndex;
    public RouteCalculator routeCalculator;

    public List <Station> route;
    public List <Station> connections;
    public List <Station> connections1;
    public List <Station> shortestWayBetween2Stations;
    public List <Station> shortestWayBetween4Stations;
    public List <Station> shortestWayBetween6Stations;

    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        routeCalculator = new RouteCalculator(stationIndex);

        route = new ArrayList<>();
        route.add(STATION_1);
        route.add(STATION_2);
        route.add(STATION_3);
        route.add(STATION_7);
        route.add(STATION_4);
        route.add(STATION_5);
        route.add(STATION_6);
        route.add(STATION_8);

        stationIndex.addStation(STATION_1);
        stationIndex.addStation(STATION_2);
        stationIndex.addStation(STATION_3);
        stationIndex.addStation(STATION_7);
        stationIndex.addStation(STATION_4);
        stationIndex.addStation(STATION_5);
        stationIndex.addStation(STATION_6);
        stationIndex.addStation(STATION_8);
        stationIndex.addLine(LINE_1);
        stationIndex.addLine(LINE_2);
        stationIndex.addLine(LINE_3);

        connections = new ArrayList<>();
        connections1 = new ArrayList<>();
        connections.add(STATION_2);
        connections.add(STATION_3);
        connections1.add(STATION_4);
        connections1.add(STATION_5);

        LINE_1.addStation(STATION_1);
        LINE_1.addStation(STATION_2);
        LINE_2.addStation(STATION_3);
        LINE_2.addStation(STATION_7);
        LINE_2.addStation(STATION_4);
        LINE_3.addStation(STATION_5);
        LINE_3.addStation(STATION_6);
        LINE_3.addStation(STATION_8);

        stationIndex.addConnection(connections);
        stationIndex.addConnection(connections1);

        shortestWayBetween2Stations = new ArrayList<>();
        shortestWayBetween2Stations.add(STATION_1);
        shortestWayBetween2Stations.add(STATION_2);

        shortestWayBetween4Stations = new ArrayList<>();
        shortestWayBetween4Stations.add(STATION_1);
        shortestWayBetween4Stations.add(STATION_2);
        shortestWayBetween4Stations.add(STATION_3);
        shortestWayBetween4Stations.add(STATION_7);

        shortestWayBetween6Stations = new ArrayList<>();
        shortestWayBetween6Stations.add(STATION_1);
        shortestWayBetween6Stations.add(STATION_2);
        shortestWayBetween6Stations.add(STATION_3);
        shortestWayBetween6Stations.add(STATION_7);
        shortestWayBetween6Stations.add(STATION_4);
        shortestWayBetween6Stations.add(STATION_5);
        shortestWayBetween6Stations.add(STATION_6);
    }

    public void testCalculateDuration() {
        assertEquals(19.5, RouteCalculator.calculateDuration(route));
    }

    public void  testGetShortestRout() {
        assertEquals(shortestWayBetween2Stations, routeCalculator.getShortestRoute(STATION_1, STATION_2));
    }

    public void  testGetShortestRoutWithOneInterchange () {
        assertEquals(shortestWayBetween4Stations, routeCalculator.getShortestRoute(STATION_1, STATION_7));
    }

    public void  testGetShortestRoutWithTwoInterchange() {
        assertEquals(shortestWayBetween6Stations, routeCalculator.getShortestRoute(STATION_1, STATION_6));
    }
}