import java.awt.Graphics;

/**
 * the code is documentation enough
 */
public class Map {
    
    private int width;
    private int height;
    private int length;
    private double prob;
    private boolean[][] map;
    
    private boolean[] death;
    private boolean[] birth;
    private int n;
    
    public Map(int pWidth, int pHeight, double pProb) {
        width = pWidth;
        height = pHeight;
        length = 10;
        prob = pProb;
        map = new boolean[height][width];
        
        n = 9;
        death = new boolean[n];
        for(int i = 0; i <n; i++) death[i] = true; //Zelle stirbt immer, ausser bei ...
        death[2] = false; //zwei Nachbarn
        death[3] = false; //drei Nachbarn
        birth = new boolean[n];
        for(int i = 0; i <n; i++) birth[i] = false; // Zelle bleibt Tod, ausser bei...
        birth[3] = true;//drei Nachbarn
        
        for(int x = 0; x < width; x++) {
            for(int y = 0;y < height; y++) {
                map[y][x] = (Math.random() < (prob / 100.0));
            }
        }
    }
    
    public boolean get(int x, int y) {
        x = (width + x) % width;
        y = (height + y) % height;
        return map[y][x];
    }
    
    public void set(int x, int y, boolean value) {
        x = (width + x) % width;
        y = (height + y) % height;
        map[y][x] = value;
    }
    
    public int neighbourhood(int x, int y) {
        int n = 0;
        if(get(x+1, y+1)) n++;
        if(get(x+1, y)) n++;
        if(get(x+1, y-1)) n++;
        if(get(x, y+1)) n++;
        if(get(x, y-1)) n++;
        if(get(x-1, y+1)) n++;
        if(get(x-1, y)) n++;
        if(get(x-1, y-1)) n++;
        return n;
    }
    
    public boolean life(int x, int y) {
        int neighbours = neighbourhood(x, y);
        if(birth[neighbours]) {
            return true;
        }else if(death[neighbours]) {
            return false;
        }else {
            return get(x, y);
        }
    }
    
    public void nextGeneration() {
        boolean[][] maptemp = new boolean[height][width];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++){
                maptemp[y][x] = life(x, y);
            }
        }
        map = maptemp;
    }
    
    public void show(Graphics g) {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(get(x, y)) {
                    g.fillRect(x * length + 1, y * length + 1, length - 1, length - 1);
                }
            }
        }
    }
}