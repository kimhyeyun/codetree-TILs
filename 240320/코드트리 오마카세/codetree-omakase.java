import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int L, Q;

	static class Query {
		int cmd, t, x, n;
		String name;

		public Query(int cmd, int t, int x, int n, String name) {
			this.cmd = cmd;
			this.t = t;
			this.x = x;
			this.n = n;
			this.name = name;
		}
	}

	static List<Query> queries = new LinkedList<>();
	static Set<String> customers = new HashSet<>();
	static Map<String, List<Query>> queriesOfCustomer = new HashMap<>();
	static Map<String, Integer> entryTimesOfCustomer = new HashMap<>();
	static Map<String, Integer> positionOfCustomer = new HashMap<>();
	static Map<String, Integer> leaveTimeOfCustomer = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int t = -1, x = -1, n = -1;
			String name = "";

			if (command == 100) {
				t = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				name = st.nextToken();

				queriesOfCustomer.computeIfAbsent(name, k -> new ArrayList<>()).add(new Query(command, t, x, n, name));
			} else if (command == 200) {
				t = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				name = st.nextToken();
				n = Integer.parseInt(st.nextToken());

				customers.add(name);
				entryTimesOfCustomer.put(name, t);
				positionOfCustomer.put(name, x);
			} else {
				t = Integer.parseInt(st.nextToken());
			}

			queries.add(new Query(command, t, x, n, name));
		}

		for (String name : customers) {
			leaveTimeOfCustomer.put(name, 0);

			for (Query q : queriesOfCustomer.get(name)) {
				int timeToRemoved = 0;
				if (q.t < entryTimesOfCustomer.get(name)) {
					int timeOfSushi = (q.x + (entryTimesOfCustomer.get(name) - q.t)) % L;
					int additionalTime = (positionOfCustomer.get(name) - timeOfSushi + L) % L;

					timeToRemoved = entryTimesOfCustomer.get(name) + additionalTime;
				} else {
					int additionalTime = (positionOfCustomer.get(name) - q.x + L) % L;
					timeToRemoved = q.t + additionalTime;
				}

				leaveTimeOfCustomer.put(name, Math.max(leaveTimeOfCustomer.get(name), timeToRemoved));
				queries.add(new Query(111, timeToRemoved, -1, -1, name));
			}
		}

		for (String name : customers) {
			queries.add(new Query(222, leaveTimeOfCustomer.get(name), -1, -1, name));
		}

		queries.sort((o1, o2) -> {
			if (o1.t == o2.t) return o1.cmd - o2.cmd;
			return o1.t - o2.t;
		});

		int customerCount = 0, sushiCount = 0;
		for (Query query : queries) {
			if (query.cmd == 100) sushiCount += 1;
			else if (query.cmd == 111) sushiCount -= 1;
			else if (query.cmd == 200) customerCount += 1;
			else if (query.cmd == 222) customerCount -= 1;
			else sb.append(customerCount).append(" ").append(sushiCount).append("\n");
		}

		System.out.println(sb);
	}
}