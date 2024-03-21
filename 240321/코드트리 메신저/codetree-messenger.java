import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, Q, count;
	static int[] parents, authorities;
	static boolean[] isOFF;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		authorities = new int[N + 1];
		isOFF = new boolean[N + 1];

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());

			int cmd = Integer.parseInt(st.nextToken());
			int c, power, c2;
			switch (cmd) {
				case 100:
					for (int i = 1; i <= N; i++) {
						parents[i] = Integer.parseInt(st.nextToken());
					}
					for (int i = 1; i <= N; i++) {
						authorities[i] = Integer.parseInt(st.nextToken());
					}
					break;

				case 200:
					c = Integer.parseInt(st.nextToken());
					isOFF[c] = !isOFF[c];
					break;

				case 300:
					c = Integer.parseInt(st.nextToken());
					power = Integer.parseInt(st.nextToken());

					authorities[c] = power;
					break;

				case 400:
					c = Integer.parseInt(st.nextToken());
					c2 = Integer.parseInt(st.nextToken());

					int p = parents[c];
					parents[c] = parents[c2];
					parents[c2] = p;
					break;

				case 500:
					c = Integer.parseInt(st.nextToken());
					count = 0;

					find(c, 1);
					sb.append(count).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void find(int index, int depth) {
		for (int i = 1; i <= N; i++) {
			if (parents[i] != index || isOFF[i]) continue;
			if (authorities[i] >= depth) count += 1;

			find(i, depth + 1);
		}
	}
}