import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
	static int L, Q;
	static Map<String, List<Integer>> sushis;
	static Map<String, Customer> customerMap;

	static class Command {
		int time, command, position;
		String name;
		int count;

		public Command(int command, int time) {
			this.command = command;
			this.time = time;
		}

		public Command(int time, int command, int position, String name) {
			this.time = time;
			this.command = command;
			this.position = position;
			this.name = name;
		}

		public Command(int time, int command, int position, String name, int count) {
			this.time = time;
			this.command = command;
			this.position = position;
			this.name = name;
			this.count = count;
		}
	}

	static class Customer {
		String name;
		int count;
		int position;
		boolean isLeave;

		public Customer(String name, int count, int position) {
			this.name = name;
			this.count = count;
			this.position = position;
			this.isLeave = false;
		}

		public void eat() {
			this.count -= 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		sushis = new HashMap<>();

		List<Command> commandList = new LinkedList<>();
		customerMap = new HashMap<>();

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());

			int command = Integer.parseInt(st.nextToken());
			if (command == 100) {
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();

				commandList.add(new Command(t, command, x, name));
			} else if (command == 200) {
				int t = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				String name = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				commandList.add(new Command(t, command, x, name, n));
			} else {
				int t = Integer.parseInt(st.nextToken());

				commandList.add(new Command(command, t));
			}
		}

		int currentTime = 0;
		int index = 0;

		StringBuilder sb = new StringBuilder();

		while (true) {
			currentTime += 1;
			rotateTable();

			if (index >= commandList.size()) break;
			if (currentTime == commandList.get(index).time) {
				int command = commandList.get(index).command;

				if (command == 100) {
					int x = commandList.get(index).position;
					String name = commandList.get(index).name;

					List<Integer> list = new LinkedList<>();
					if (sushis.containsKey(name)) list = sushis.get(name);

					list.add(x);
					sushis.put(name, list);

					eat();
				} else if (command == 200) {
					int x = commandList.get(index).position;
					String name = commandList.get(index).name;
					int count = commandList.get(index).count;

					Customer c = new Customer(name, count, x);
					customerMap.put(name, c);

					eat();
				} else {
					eat();

					int cCount = 0, sCount = 0;
					for (String name : customerMap.keySet()) {
						if (!customerMap.get(name).isLeave)
							cCount += 1;
					}

					for (String name : sushis.keySet()) {
						sCount += sushis.get(name).size();
					}

					sb.append(cCount).append(" ").append(sCount).append("\n");
				}
				index += 1;
			}
		}
		System.out.println(sb);
	}

	private static void eat() {
		if (customerMap.isEmpty()) return;

		List<String> names = new LinkedList<>();
		for (String name : customerMap.keySet()) {
			if (!sushis.containsKey(name)) continue;

			Customer c = customerMap.get(name);
			int x = c.position;

			List<Integer> list = sushis.get(name);
			for (int i = 0; i < list.size(); i++) {
				if (c.count == 0) break;
				if (list.get(i) == x) {
					c.eat();
					list.remove(i);
					i -= 1;
				}
			}

			if (c.count <= 0) names.add(name);
			else customerMap.put(name, c);

			sushis.put(name, list);
		}

		for (String n : names) {
			customerMap.remove(n);
		}
	}

	private static void rotateTable() {
		for (String name : sushis.keySet()) {
			List<Integer> tmp = new LinkedList<>();

			for (int i = 0; i < sushis.get(name).size(); i++) {
				tmp.add((sushis.get(name).get(i) + 1) % L);
			}

			sushis.put(name, tmp);
		}
	}
}