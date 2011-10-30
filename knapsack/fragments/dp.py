def knapsack(N, C):
	tmp = [[0 for j in range(C + 1)] for i in range(N)]  # DP tabulka
	for j in range(C + 1): # base case 
		if hmotnost[0] <= j:
			tmp[0][j] = hodnota[0]

	for i in range(1, N):
		for j in range(C + 1):
			if hmotnost[i] > j:
				tmp[i][j] = tmp[i - 1][j]
			else:
				tmp[i][j] = max(tmp[i - 1][j - hmotnost[i]] + hodnota[i],
								tmp[i - 1][j])
	return tmp[-1][-1]        # vrátit poslední prvek tabulky
