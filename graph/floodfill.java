package graph;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author timothy
 */

public class floodfill {
    static int floodfill(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int ret = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    ret++;
                    Queue<int[]> coords = new LinkedList();
                    coords.add(new int[]{i, j});
                    while(!coords.isEmpty()){
                        int[] currCoords = coords.poll();
                        int x = currCoords[1];
                        int y = currCoords[0];
                        visited[y][x] = true;
                        if(x + 1 < grid[0].length && !visited[y][x+1] && grid[y][x+1] == '1'){
                            coords.add(new int[]{y, x+1});
                        }
                        
                        if(x - 1 >= 0 && !visited[y][x-1] && grid[y][x-1] == '1'){
                            coords.add(new int[]{y, x-1});
                        }
                        
                        if(y + 1 < grid.length && !visited[y+1][x] && grid[y+1][x] == '1'){
                            coords.add(new int[]{y+1, x});
                        }
                        
                        if(y - 1 >= 0 && !visited[y-1][x] && grid[y-1][x] == '1'){
                            coords.add(new int[]{y-1, x});
                        }
                    }
                }
            }
        }
        return ret;
    }
}
