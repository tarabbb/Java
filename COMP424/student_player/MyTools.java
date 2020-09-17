package student_player;

import java.util.ArrayList;
import java.util.LinkedList;

import Saboteur.SaboteurBoardState;
import Saboteur.cardClasses.SaboteurTile;

public class MyTools {
    //try to know what place can be reached from (5,5) by using queue
	public static ArrayList<int[]> reachableEnds(SaboteurTile[][] mapTiles, int[] entrance) {
		boolean[][] visited = new boolean[SaboteurBoardState.BOARD_SIZE][SaboteurBoardState.BOARD_SIZE];
		for (int i = 0; i < SaboteurBoardState.BOARD_SIZE; i++) {
			for (int j = 0; j < SaboteurBoardState.BOARD_SIZE; j++) {
				visited[i][j] = false;
			}
		}
		int[][] moves = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

		ArrayList<int[]> reachablePos = new ArrayList<int[]>();

		LinkedList<int[]> queue = new LinkedList<>();
		queue.offer(entrance);
		while (!queue.isEmpty()) {
			int[] currentPos = queue.poll();
			int x = currentPos[0];
			int y = currentPos[1];
			SaboteurTile currentTile = mapTiles[x][y];
			if (currentTile == null) {
				continue;
			}
			int[][] currentPath = currentTile.getPath();
			if (currentPath[1][1] == 0) {
				continue;
			}
			int neighbors = 0;
			int exits = 0;
			if (currentPath[0][1] == 1) {
				exits++;
			}
			if (currentPath[2][1] == 1) {
				exits++;
			}
			if (currentPath[1][2] == 1) {
				exits++;
			}
			if (currentPath[1][0] == 1) {
				exits++;
			}
			for (int m = 0; m < moves.length; m++) {
				int i = x + moves[m][0];
				int j = y + moves[m][1];
				if (i < 0 || i >= SaboteurBoardState.BOARD_SIZE || j < 0 || j >= SaboteurBoardState.BOARD_SIZE) {
					continue;
				}
				SaboteurTile nextTile = mapTiles[i][j];
				if (nextTile == null) {
					continue;
				}
				int[][] nextPath = nextTile.getPath();
				if (j < y) { // left
					if (currentPath[0][1] == 1) {
						if (currentPath[0][0] == nextPath[2][0] && currentPath[0][1] == nextPath[2][1]
								&& currentPath[0][2] == nextPath[2][2]) {
							neighbors++;
							if (!visited[i][j]) {
								visited[i][j] = true;
								queue.offer(new int[] { i, j });
							}
						}
					}
				} else if (j > y) { // right
					if (currentPath[2][1] == 1) {
						if (currentPath[2][0] == nextPath[0][0] && currentPath[2][1] == nextPath[0][1]
								&& currentPath[2][2] == nextPath[0][2]) {
							neighbors++;
							if (!visited[i][j]) {
								visited[i][j] = true;
								queue.offer(new int[] { i, j });
							}
						}
					}
				} else if (i < x) { // upper
					if (currentPath[1][2] == 1) {
						if (currentPath[0][2] == nextPath[0][0] && currentPath[1][2] == nextPath[1][0]
								&& currentPath[2][2] == nextPath[2][0]) {
							neighbors++;
							if (!visited[i][j]) {
								visited[i][j] = true;
								queue.offer(new int[] { i, j });
							}
						}
					}
				} else if (i > x) { // bottom
					if (currentPath[1][0] == 1) {
						if (currentPath[0][0] == nextPath[0][2] && currentPath[1][0] == nextPath[1][2]
								&& currentPath[2][0] == nextPath[2][2]) {
							neighbors++;
							if (!visited[i][j]) {
								visited[i][j] = true;
								queue.offer(new int[] { i, j });
							}
						}
					}
				}
			}
			visited[x][y] = true;
			if (exits > neighbors && currentPath[1][1] == 1) {
				reachablePos.add(currentPos);
			}
		}

		return reachablePos;
	}
}