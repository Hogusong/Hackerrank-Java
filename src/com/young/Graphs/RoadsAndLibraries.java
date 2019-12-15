package com.young.Graphs;

import java.util.*;

public class RoadsAndLibraries {

    public static void main(String[] args) {
        int[][] C1 = {{1,2},{3,1},{1,4}};
        System.out.println(roadsAndLibraries(3,6,1, C1));
        int[][] C2 = {{1,3},{3,4},{2,4},{1,2},{2,3},{5,6}};
        System.out.println(roadsAndLibraries(6,2,5, C2));
        System.out.println(roadsAndLibraries(6,5,2, C2));
    }

    static long roadsAndLibraries (int n, int c_lib, int c_road, int[][] cities) {
        if (c_road > c_lib) return c_lib * n;
        long cost = 0;
        Group allCities = new Group();
        int len = cities.length;
        City c1, c0;
        for (int i = 0; i < len; i++) {
            int n_0 = cities[i][0];
            c0 = allCities.find(n_0);
            if (c0 == null) {
                c0 = new City(n_0);
                allCities.addCity(c0);
            }

            int n_1 = cities[i][1];
            c1 = allCities.find(n_1);
            if (c1 == null) {
                c1 = new City(n_1);
                allCities.addCity(c1);
            }

            c0.addInAdjacent(c1);
        }

        List<City> list = new LinkedList<>();
        List<Group> groups = new LinkedList<>();
        while (!allCities.isEmpty()) {
            list.add(allCities.poll());
            Group group = new Group();
            int i = 0;
            while ( i < list.size()) {
                City c = list.get(i);
                group.addCity(c);
                c.adajcents.forEach(city -> {
                    allCities.remove(city);
                    if (!list.contains(city)) list.add(city);
                });
                i++;
            }
            groups.add(group);
            list.clear();
        }

        for (int i = 0; i < groups.size(); i++) {
            cost += c_lib + c_road * (groups.get(i).cities.size() - 1);
        }
        return cost;
    }

    static class Group {
        List<City> cities;

        Group() {
            this.cities = new LinkedList<>();
        }

        void addCity(City city) {
            if (find(city.name) == null) this.cities.add(city);
        }

        City find(int name) {
            for (int i = 0; i < this.cities.size(); i++) {
                if (this.cities.get(i).name == name) return this.cities.get(i);
            }
            return null;
        }

        boolean isEmpty() {
            return this.cities.size() < 1;
        }

        void printCities() {
            for (City city : this.cities) {
                city.printAdjacents();
            }
        }

        City poll() {
            if (this.cities.size() < 1) return null;
            City city = this.cities.get(0);
            this.cities.remove(0);
            return city;
        }

        void remove(City city) {
            this.cities.remove(city);
        }
    }

    static class City {
        int name;
        List<City> adajcents;

        City(int name) {
            this.name = name;
            this.adajcents = new LinkedList<>();
        }

        boolean isAdjacent(City city) {
            if (this.adajcents.size() < 1) return false;
            for (int i = 0; i < this.adajcents.size(); i++) {
                if (this.adajcents.get(i).name == city.name) return true;
            }
            return false;
        }

        void addInAdjacent(City city) {
            if (isAdjacent(city)) return;
            this.adajcents.add(city);
            city.addAdjacent(this);
        }

        void addAdjacent(City city) {
            if (isAdjacent(city)) return;
            this.adajcents.add(city);
        }

        void printAdjacents() {
            System.out.print(this.name + "'s adjacents are ");
            this.adajcents.forEach(city -> System.out.print(city.name + "  "));
            System.out.println();
        }
    }
}
