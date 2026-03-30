package util;

import util.enums.Positions;

public class Position {
    public int x, y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPos(Positions pos) {
        switch (pos) {
            case X:
                return x;
            case Y:
                return y;
            default:
                return x; // This will never be reaches as pos only has two options but not having it makes VS code unhappy
        }
    }
    public void setPos(Positions pos, int amt) {
        switch (pos) {
            case X:
                x = amt;
            case Y:
                y = amt;
        }
    }
}
