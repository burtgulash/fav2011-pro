def knapsack(N, C):
	if N == 0:
		return 0
	i = N - 1                           # začínáme od nuly
	if hmotnost[i] > C:
		return knapsack(N - 1, C)
	return max(knapsack(N - 1, C - hmotnost[i]) + hodnota[i],
	           knapsack(N - 1, C))
