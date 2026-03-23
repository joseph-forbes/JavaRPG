package WorldMap;
import Entities.*;
import Game.Engine;
import Player.Player;

public class WorldMap {
    MapTile[][] worldMap = new MapTile[5][5];
    Player player;

    public WorldMap(Engine game) {
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

    public void update() {
        for (int row = 0; row < worldMap.length; row++) {
            for (int col = 0; col < worldMap[row].length; col++) {
                worldMap[row][col].update();
            }
        }
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
