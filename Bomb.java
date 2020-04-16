package BomberMan;
   
public class Bomb {
    int x,y;

    public Bomb(int x,int y) {
        this.x = x;
        this.y = y;
    }
    // update map for bomb
    public int[][] addCol(int[][] map) {
            map[y][x] = 3;
        return map;
    }
    // bomb make lava
    public int[][] explodeRadius(int[][] map) {
        if (map[y][x] == 0 || map[y][x] == 2 || map[y][x] == 3) {
            map[y][x] = 4; }
        if (map[y][x+1] == 0 || map[y][x+1] == 2 || map[y][x+1] == 3) {
            map[y][x+1] = 4; }
        if (map[y][x-1] == 0 || map[y][x-1] == 2 || map[y][x-1] == 3) {
            map[y][x-1] = 4; }
        if (map[y+1][x] == 0 || map[y+1][x] == 2 || map[y+1][x] == 3) {
            map[y+1][x] = 4; }
        if (map[y-1][x] == 0 || map[y-1][x] == 2 || map[y-1][x] == 3) {
            map[y-1][x] = 4; }
        return map;
    }
    // bomb effect block
    public int[][] explodeAction(int[][] map) {
        if (map[y][x] == 2 || map[y][x] == 4) {
            map[y][x] = 0; }
        if (map[y][x+1] == 2 || map[y][x+1] == 4) {
            map[y][x+1] = 0; }
        if (map[y][x-1] == 2 || map[y][x-1] == 4) {
            map[y][x-1] = 0; }
        if (map[y+1][x] == 2 || map[y+1][x] == 4) {
            map[y+1][x] = 0; }
        if (map[y-1][x] == 2 || map[y-1][x] == 4) {
            map[y-1][x] = 0; }
        return map;
    }
}
