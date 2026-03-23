package WorldMap;
import Entities.*;
import Game.Adventure;
import Player.Player;

public class WorldMap {
    MapTile[][] worldMap = new MapTile[5][5];
    Player player;

    public WorldMap(Adventure game) {
        // Initialize map
        player = game.getPlayer();
        for(int i=0;i<worldMap.length;i++) {
            for(int j=0;j<worldMap[i].length;j++) {
                worldMap[i][j] = new MapTile();
            }
        }

        // Game Contents
        worldMap[2][2].contents.add(new Creature("Steven", 5, 2, 0, 10, 0));
        worldMap[2][2].contents.add(new Creature("Steven", 5, 2, 0, 10, 0));
        worldMap[2][2].contents.add(new Entity());
    }

    public MapTile getCoordinatesOfMapTile(int x, int y) {
        return worldMap[y][x];
    }
    public MapTile getCurrentMapTile() {
        return getCoordinatesOfMapTile(player.getPos().x, player.getPos().y);
    }
    public void displayGameState(int x, int y) {
        MapTile currentTile = getCoordinatesOfMapTile(x, y);

        System.out.println(currentTile.contents);
    }
}
