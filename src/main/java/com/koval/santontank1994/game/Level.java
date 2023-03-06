package com.koval.santontank1994.game;

import com.koval.santontank1994.entity.MapObject;
import com.koval.santontank1994.entity.Tank;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final List<Tank> tanks = new ArrayList<>();
    private final String mapData;

    public Level(String mapData) {
        this.mapData = mapData;
        MapGenerator mapGenerator = new MapGenerator(mapData);
        MapObject[][] map = mapGenerator.getMap();

        // Iterate over the map and find tanks
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                MapObject obj = map[i][j];
                if (obj instanceof Tank) {
                    tanks.add((Tank) obj);
                }
            }
        }
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public String getMapData() {
        return mapData;
    }
}