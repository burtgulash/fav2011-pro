#! /usr/bin/python
# -*- coding: UTF-8 -*-

import sys

hodnota = []
hmotnost = []

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

	polozky = []	# zpětně najít vybrané položky
	while i >= 0 and j >= 0:
		if j >= hmotnost[i] \
		and tmp[i - 1][j - hmotnost[i]] + hodnota[i] == tmp[i][j]:
			polozky.append(i)
			j -= hmotnost[i]
			i -= 1
		elif tmp[i - 1][j] == tmp[i][j]:
			i -= 1
		else:
			j -= 1
	polozky = polozky[::-1] # obrátit položky

	return tmp[-1][-1], polozky # vrátit poslední prvek tabulky


if __name__ == "__main__":
	while True:
		try:
			string = raw_input()
			data  = string.split(" ")
			try:
				hodnota.append(int(data[0]))
				hmotnost.append(int(data[1]))
			except ValueError, IndexError:
				print "%s: Špatný vstup" % sys.argv[0]
		except EOFError:
			break
			print hodnota, hmotnost

	try:
		C = int(sys.argv[1])
	except ValueError:
		print "%s: Limit musí být celé číslo" % sys.argv[0]
		sys.exit(1)
	except IndexError:
		print "použití: %s LIMIT" % sys.argv[0]
		sys.exit(1)

	N = len(hodnota)
	try:
		cena, polozky = knapsack(N, C)
	except KeyboardInterrupt:
		print "KeyboardInterrupt"
		sys.exit(1)

	print "knapsack(%d, %d) = %d" % (N, C, cena)
	print "položky:"
	print polozky
