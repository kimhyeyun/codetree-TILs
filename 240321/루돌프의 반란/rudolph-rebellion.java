import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static class Santa {
		int index;
		int x, y;
		int stunCount;
		boolean isOut;

		public Santa(int index, int x, int y) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.stunCount = 0;
			this.isOut = false;
		}
	}
	static int N, M, P, C, D;
	static int[] rudolf;
	static Santa[] santas;
	static int[][] map;
	static int[] scores;
	static final int[] rdx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] rdy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static final int[] sdx = {-1, 0, 1, 0};
	static final int[] sdy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		rudolf = new int[] {x, y};

		map[x][y] = -1;

		santas = new Santa[P + 1];
		scores = new int[P + 1];

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken()) - 1;
			y = Integer.parseInt(st.nextToken()) - 1;

			santas[index] = new Santa(index, x, y);
			map[x][y] = index;
		}

		while (M-- > 0) {
			moveRudolf();

			if (!isPossible()) break;
			moveSantas();
			getScore();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= P; i++) {
			sb.append(scores[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void getScore() {
		for (int i = 1; i <= P; i++) {
			if (santas[i].isOut) continue;
			scores[i] += 1;
		}
	}

	private static void moveSantas() {
		for (int i = 1; i <= P; i++) {
			if (santas[i].isOut) continue;
			if (santas[i].stunCount > 0) {
				santas[i].stunCount -= 1;
				continue;
			}

			int dir = -1;
			int dist = (int)(Math.pow((santas[i].x - rudolf[0]), 2) + Math.pow((santas[i].y - rudolf[1]), 2));

			for (int d = 0; d < 4; d++) {
				int nx = santas[i].x + sdx[d];
				int ny = santas[i].y + sdy[d];

				if (nx < 0 || ny < 0 || N <= nx || N <= ny) continue;
				if (1 <= map[nx][ny] && map[nx][ny] <= P) continue;

				int tmp = (int)(Math.pow((nx - rudolf[0]), 2) + Math.pow((ny - rudolf[1]), 2));
				if (tmp < dist) {
					dist = tmp;
					dir = d;
				}
			}

			if (dir == -1) continue;

			int nx = santas[i].x + sdx[dir];
			int ny = santas[i].y + sdy[dir];

			if (map[nx][ny] == -1) {
				scores[i] += D;
				santas[i].stunCount = 1;

				if (dir == 0) dir = 2;
				else if (dir == 2) dir = 0;
				else if (dir == 1) dir = 3;
				else dir = 1;

				map[santas[i].x][santas[i].y] = 0;
				crash(i, nx, ny, dir, D, false);
			} else if (map[nx][ny] == 0) {
				map[santas[i].x][santas[i].y] = 0;
				map[nx][ny] = i;
				santas[i].x = nx;
				santas[i].y = ny;
			}
		}
	}

	private static boolean isPossible() {
		for (int i = 1; i <= P; i++) {
			if (!santas[i].isOut)
				return true;
		}
		return false;
	}

	private static void moveRudolf() {
		int index = findNearestSanta();
		int dir = -1;

		if (santas[index].x > rudolf[0]) {
			if (santas[index].y == rudolf[1]) dir = 6;
			else if (santas[index].y > rudolf[1]) dir = 7;
			else dir = 5;
		} else if (santas[index].x < rudolf[0]) {
			if (santas[index].y == rudolf[1]) dir = 1;
			else if (santas[index].y > rudolf[1]) dir = 2;
			else dir = 0;
		} else {
			if (santas[index].y == rudolf[1]) dir = -1;
			else if (santas[index].y > rudolf[1]) dir = 4;
			else dir = 3;
		}

		int rnx = rudolf[0] + rdx[dir];
		int rny = rudolf[1] + rdy[dir];

		if (map[rnx][rny] != 0) {
			scores[map[rnx][rny]] += C;
			santas[map[rnx][rny]].stunCount = 2;

			crash(map[rnx][rny], rnx, rny, dir, C, true);
		}
		map[rnx][rny] = -1;
		map[rudolf[0]][rudolf[1]] = 0;
		rudolf = new int[] {rnx, rny};
	}

	private static void crash(int index, int x, int y, int dir, int dist, boolean isRudolfMove) {
		int nx = x;
		int ny = y;

		if (isRudolfMove) {
			nx = x + (rdx[dir] * dist);
			ny = y + (rdy[dir] * dist);
		}else {
			nx = x + (sdx[dir] * dist);
			ny = y + (sdy[dir] * dist);
		}

		if (nx < 0 || ny < 0 || N <= nx || N <= ny){
			map[santas[index].x][santas[index].y] = 0;
			santas[index].isOut = true;
			return;
		}

		if (map[nx][ny] != 0) crash(map[nx][ny], nx, ny, dir, 1, isRudolfMove);

		map[nx][ny] = index;
		santas[index].x = nx;
		santas[index].y = ny;
	}

	private static int findNearestSanta() {
		int dist = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 1; i <= P; i++) {
			if (santas[i].isOut) continue;
			int tmp = (int)(Math.pow((santas[i].x - rudolf[0]), 2) + Math.pow((santas[i].y - rudolf[1]), 2));

			if (tmp < dist) {
				dist = tmp;
				index = i;
			} else if (tmp == dist) {
				if (santas[index].x < santas[i].x) index = i;
				else if (santas[index].x == santas[i].x && santas[index].y < santas[i].y) index = i;
			}
		}

		return index;
	}
}