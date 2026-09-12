package com.example.sehja.config;

import java.util.Map;

public final class TeamColors {

    private static final String[] FALLBACK = { "#888888", "#ffffff" };

    private static final Map<String, String[]> COLORS = Map.ofEntries(
        Map.entry("Mercedes",                  new String[]{"#00D2BE", "#C8C8C8"}),
        Map.entry("Ferrari",                   new String[]{"#DC0000", "#FFF200"}),
        Map.entry("McLaren",                   new String[]{"#FF8000", "#47C7FC"}),
        Map.entry("Red Bull",                  new String[]{"#3671C6", "#DC0000"}),
        Map.entry("Red Bull Racing",           new String[]{"#3671C6", "#DC0000"}),
        Map.entry("Williams",                  new String[]{"#64C4FF", "#1A1A1A"}),
        Map.entry("Aston Martin",              new String[]{"#229971", "#BFD12B"}),
        Map.entry("RB",                        new String[]{"#6692FF", "#FF1801"}),
        Map.entry("Racing Bulls",              new String[]{"#6692FF", "#FF1801"}),
        Map.entry("RB F1 Team",                new String[]{"#6692FF", "#FF1801"}),
        Map.entry("AlphaTauri",                new String[]{"#2B4562", "#FFFFFF"}),
        Map.entry("Toro Rosso",                new String[]{"#469BFF", "#FFFFFF"}),
        Map.entry("Alpine",                    new String[]{"#FF1F8C", "#00A1E8"}),
        Map.entry("Alpine F1 Team",            new String[]{"#FF1F8C", "#00A1E8"}),
        Map.entry("Renault",                   new String[]{"#FFF500", "#000000"}),
        Map.entry("Lotus F1",                  new String[]{"#000000", "#FFB800"}),
        Map.entry("Haas",                      new String[]{"#B6BABD", "#DC0000"}),
        Map.entry("Haas F1 Team",              new String[]{"#B6BABD", "#DC0000"}),
        Map.entry("Audi",                      new String[]{"#A6A6A6", "#E10600"}),
        Map.entry("Sauber",                    new String[]{"#52E252", "#FFFFFF"}),
        Map.entry("Alfa Romeo",                new String[]{"#900000", "#FFFFFF"}),
        Map.entry("BMW Sauber",                new String[]{"#293274", "#FFFFFF"}),
        Map.entry("Cadillac",                  new String[]{"#1F3A60", "#FFFFFF"}),
        Map.entry("Cadillac F1 Team",          new String[]{"#1F3A60", "#FFFFFF"}),
        Map.entry("Racing Point",              new String[]{"#F596C8", "#FFFFFF"}),
        Map.entry("Force India",               new String[]{"#F596C8", "#FFFFFF"}),
        Map.entry("Jordan",                    new String[]{"#FFAB00", "#000000"}),
        Map.entry("Tyrrell",                   new String[]{"#1E4B9C", "#FFFFFF"}),
        Map.entry("Brabham",                   new String[]{"#0A6E2C", "#FFD700"}),
        Map.entry("Brawn",                     new String[]{"#B8FE00", "#000000"}),
        Map.entry("Honda",                     new String[]{"#FFFFFF", "#E10600"}),
        Map.entry("Toyota",                    new String[]{"#CC0000", "#FFFFFF"}),
        Map.entry("BAR",                       new String[]{"#FFFFFF", "#E10600"}),
        Map.entry("Stewart",                   new String[]{"#FFFFFF", "#0033A0"}),
        Map.entry("Benetton",                  new String[]{"#00E04E", "#FFD700"}),
        Map.entry("Jaguar",                    new String[]{"#005A2B", "#FFD700"}),
        Map.entry("Prost",                     new String[]{"#0048AA", "#FFFFFF"}),
        Map.entry("Minardi",                   new String[]{"#000000", "#FFFFFF"})
    );

    private TeamColors() {}

    public static String[] forTeam(String team) {
        if (team == null) return FALLBACK;
        return COLORS.getOrDefault(team, FALLBACK);
    }

    public static String primary(String team) { return forTeam(team)[0]; }
    public static String accent(String team)  { return forTeam(team)[1]; }
}
