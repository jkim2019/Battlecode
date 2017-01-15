package RobotPlayer;

import battlecode.common.*;

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
            case SOLDIER:
                runSoldier();
                break;
            case LUMBERJACK:
                runLumberjack();
                break;
            case SCOUT:
                runScout();
        }
    }

    /*
        runArchon() will have to do very little, as it is not involved in this test
        other than spawning a Gardener
    */
    static void runArchon() throws GameActionException {
        System.out.println("Archon running");

        while (true) {

            try {

                // build a Gardener
                if (rc.canHireGardener(east)) {
                    rc.hireGardener(east);
                }

                MapLocation myLocation = rc.getLocation();
                rc.broadcast(0,(int)myLocation.x);
                rc.broadcast(1,(int)myLocation.y);

                // wait until next turn, then perform again
                Clock.yield();

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
        int turnNumber = rc.getRoundNum();

        try {

            // build a Scout
            if (rc.canBuildRobot(RobotType.SCOUT, north)) {
                rc.buildRobot(RobotType.SCOUT, north);
            }

            else if (rc.canBuildRobot(RobotType.SCOUT, east)) {
                rc.buildRobot(RobotType.SCOUT, east);
            }

            else if (rc.canBuildRobot(RobotType.SCOUT, south)) {
                rc.buildRobot(RobotType.SCOUT, south);
            }

            else if (rc.canBuildRobot(RobotType.SCOUT, west)) {
                rc.buildRobot(RobotType.SCOUT, west);
            }

            // wait until next turn, then perform again
            Clock.yield();
            
        } catch (Exception e) {
            System.out.println("Gardener Exception");
            e.printStackTrace();
        }

    }

    static void runSoldier() throws GameActionException {
        System.out.println("Soldier running");
    }

    static void runLumberjack() throws GameActionException {
        System.out.println("Lumberjack running");
    }

    static void runScout() throws GameActionException {
        System.out.println("Scout running");

        while (true) {

            try {

                // build a Scout
                if (rc.canBuildRobot(RobotType.SCOUT, east)) {
                    rc.buildRobot(RobotType.SCOUT, east);
                }

                // build a tree
                if (rc.canPlantTree(north)) {
                    rc.plantTree(north);
                }

                // wait until next turn, then perform again
                Clock.yield();
                
            } catch (Exception e) {
                System.out.println("Gardener Exception");
                e.printStackTrace();
            }

        }
    }
}