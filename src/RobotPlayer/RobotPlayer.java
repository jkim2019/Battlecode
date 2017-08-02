package RobotPlayer;

import battlecode.common.*;

import java.util.*;

/*
    This instance of RobotPlayer will test whether or not a scout is damaged when partially
    occupying a tree which is subsequently hit by a bullet.
*/

public strictfp class RobotPlayer {
    static RobotController rc;

    static Direction east = Direction.getEast();
    static Direction south = Direction.getSouth();
    static Direction west = Direction.getWest();
    static Direction north = Direction.getNorth();

    /*
        run() is called when a robot is instantiated in Battlecode. 
    */
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {
        
        // RobotController object
        RobotPlayer.rc = rc;
        
        switch (rc.getType()) {
            case ARCHON:
                runArchon();
                break;
            case GARDENER:
                runGardener();
                break;
            case SCOUT:
                runScout();
        }
    }

    /*
        runArchon() will have to do very little, as it is not involved in this test
        other than attempting to spawn a Gardener every 50 turns
    */
    static void runArchon() throws GameActionException {
        System.out.println("Archon running");

        while (true) {

            try {
                if (rc.getRoundNum() % 50 == 1) {

                    // attempt to spawn Gardener in first available location (NESW)
                    Direction dir = Direction.getNorth();

                    while (!rc.canHireGardener(dir)) {
                        dir.rotateRightDegrees(45);
                    }

                    rc.hireGardener(dir);
                }

            } catch (Exception e) {
                System.out.println("Archon Exception");
                e.printStackTrace();
            }
        }
    }

    /*
        runGardener() will spawn a Scout in the first available direction of NESW, in that
        order
    */
    static void runGardener() throws GameActionException {
        System.out.println("Gardener running");

        try {
            // spawn two Scouts on the second turn
            if (rc.getRoundNum() == 2) {
                Direction dir = north;

                // build two Scouts
                for(int count = 0; count < 2; count++){
                    while (!rc.canBuildRobot(RobotType.SCOUT, dir)) {
                        dir.rotateRightDegrees(45);
                    }
                    rc.buildRobot(RobotType.SCOUT, dir);
                }
            }
            // build trees and soldiers
            else {
                Direction dir = north;
                if (rc.canPlantTree(dir)) {

                }
            }


            // wait until next turn, then perform again
            Clock.yield();
            
        } catch (Exception e) {
            System.out.println("Gardener Exception");
            e.printStackTrace();
        }

    }

    static void runScout() throws GameActionException {
        System.out.println("Scout running");

        while (true) {
            try {


                // wait until next turn, then perform again
                Clock.yield();
                
            } catch (Exception e) {
                System.out.println("Scout Exception");
                e.printStackTrace();
            }

        }
    }
}

class RobotMap {
    List<RobotInfo> map;
    /**
     *
     * @param sensedRobots : contains RobotInfo
     *
     */
    void addLocation(RobotInfo[] sensedRobots) {
        for (RobotInfo sensedRobot : sensedRobots) {
            map.add(sensedRobot);
        }
    }
//    String translate(RobotInfo[] robotSummary) {
//        String robotID = "";
//        for (RobotInfo robot : robotSubSummary) {
//
//        }
//    }
}
