import java.util.Scanner;

public class Jugs {
	public static boolean[][] visited;
	private static final int A_MAX = 1001;
	private static final int B_MAX = 1001;
	private static int goal;
	private static int aSize;
	private static int bSize;
	private static int[] results = new int[2];
	public static int[][] predA;
	public static int[][] predB;
	public static String[][] actions;
	private static int endA;
	private static int endB;
	
	private static int[] pour(int i, int j, int jug) {
		int[] pour = new int[2];
		if (jug == 1) {
			int iter = i;
			while (iter > 0 && j < bSize) {
				j++;
				iter--;
			}
			pour[0] = iter;
			pour[1] = j;
		} else {
			int iter = j;
			while (iter > 0 && i < aSize) {
				i++;
				iter--;
			}
			pour[0] = i;
			pour[1] = iter;
		}
		return pour;

	}

	private static boolean dfs(int i, int j) {
		endA = i;
		endB = j;
		if ((i + j) == goal)
			return true;
		boolean ret = false;
		visited[i][j] = true;
		if (!visited[0][j]) {
			predA[0][j] = i;
			predB[0][j] = j;
			actions[0][j] = "Empty Jug 1";
			ret = ret || dfs(0, j);
		

		}
		if (!visited[i][0]) {
			predA[i][0] = i;
			predB[i][0] = j;
			actions[i][0] = "Empty Jug 2";
			ret = ret || dfs(i, 0);
			

		}
		if (!visited[aSize][j]) {
			predA[aSize][j] = i;
			predB[aSize][j] = j;
			actions[aSize][j] = "Fill Jug 1";
			ret = ret || dfs(aSize, j);
		

		}
		if (!visited[i][bSize]) {
			predA[i][bSize] = i;
			predB[i][bSize] = j;
			actions[i][bSize] = "Fill Jug 2";
			ret = ret || dfs(i, bSize);
			

		}
		results = pour(i, j, 1);
		if (!visited[results[0]][results[1]]) {
			predA[results[0]][results[1]] = i;
			predB[results[0]][results[1]] = j;
			actions[results[0]][results[1]] = "Pour Jug 1 -> Jug 2";
			ret = ret || dfs(results[0], results[1]);
		

		}
		results = pour(i, j, 2);
		if (!visited[results[0]][results[1]]) {
			predA[results[0]][results[1]] = i;
			predB[results[0]][results[1]] = j;
			actions[results[0]][results[1]] = "Pour Jug 2 -> Jug 1";
			ret = ret || dfs(results[0], results[1]);
			
		}
		
		return ret;

	}
	public static void print(int i, int j)
	{
		if (predA[i][j] == 0 && predB[i][j] == 0) {
			return;
		}
		print(predA[i][j], predB[i][j]);
		System.out.println(actions[predA[i][j]][predB[i][j]] + " [ a = " + predA[i][j] + " , b = " + predB[i][j] + " ]");
		
			
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter A:");
		int a = sc.nextInt();
		System.out.print("Enter B:");
		int b = sc.nextInt();
		System.out.print("Enter C:");
		int c = sc.nextInt();
		goal = c;
		aSize = a;
		bSize = b;
		visited = new boolean[A_MAX][B_MAX];
		predA = new int[A_MAX][B_MAX];
		predB = new int[A_MAX][B_MAX];
		actions = new String[A_MAX][B_MAX];
		boolean check = dfs(0,0);
		if(check) {
			System.out.println("Yay! Found a solution.");
			print(endA, endB);
			System.out.println(actions[endA][endB] + " [ a = " + endA + " , b = " + endB + " ]");
		}
		
		else
		{
			System.out.println("Impossible!");
		}
		sc.close();

	}
}
