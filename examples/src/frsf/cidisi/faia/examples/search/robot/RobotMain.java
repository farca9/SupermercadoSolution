/*
 * Copyright 2007-2009 Georgina Stegmayer, Milagros Gutiérrez, Jorge Roa
 * y Milton Pividori.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package frsf.cidisi.faia.examples.search.robot;

import java.awt.Point;

import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class RobotMain {

    public static void main(String[] args) throws PrologConnectorException {

    	Point p1 = new Point(0,0);
    	Point p2 = new Point(0,1);
    	Point p3 = new Point(1,0);
    	Point p4 = new Point(1,1);
    	
    	System.out.println(p1.distance(p1));
    	System.out.println(p1.distance(p2));
    	System.out.println(p1.distance(p3));
    	System.out.println(p1.distance(p4));
    	
    	RobotAgent agent = new RobotAgent();

        RobotEnvironment environment = new RobotEnvironment();

        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }

}
