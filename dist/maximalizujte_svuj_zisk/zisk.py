#! /usr/bin/python
# -*- coding: UTF-8 -*-

import sys

def naivniZisk(h):
	n = len(h)
	max_zisk, max_i, max_delka = 0, 0, 0
	pocatecni_zisk = 0
	for delka in range(1, n + 1):
		pocatecni_zisk += h[delka - 1]
		zisk = pocatecni_zisk

		for i in range(n - delka):
			if zisk > max_zisk:
				max_zisk, max_i, max_delka = zisk, i, delka
			zisk += h[delka + i] - h[i]

		if zisk > max_zisk:
			max_zisk, max_i, max_delka = zisk, n - delka, delka
	return max_zisk, max_i, max_delka
			

def dpZisk(h):
	n = len(h)
	max_zisk, max_i, max_delka = 0, 0, 0
	krajni_zisk, krajni_i, krajni_delka = 0, 0, 0
	for i in range(n):
		if krajni_zisk > 0:        # Případ 2
			krajni_zisk += h[i]
			krajni_delka += 1
		else:                      # Případ 3
			krajni_zisk ,krajni_i, krajni_delka = h[i], i, 1

		if krajni_zisk > max_zisk: # Případ 1 (obráceně)
			max_zisk, max_i, max_delka = krajni_zisk, krajni_i, krajni_delka
	return max_zisk, max_i, max_delka
			
			
if __name__ == "__main__":
	asistentky = []
	while True:
		try:
			n = raw_input()
			try:
				n = int(n)
				asistentky.append(n)
			except ValueError:
				print "%s: hodnota musí být celé číslo" % sys.argv[0]
		except EOFError:
			break

	legenda = ["metoda", "zisk", "počáteční index", "délka posloupnosti"]
	string = "{0:<6s} :  {1:>4s}  {2:>15s}  {3:>18s}"
	print string.format(*legenda)

	vysledek = dpZisk(asistentky)
	print string.format("DP", *map(str, vysledek))
	
	vysledek = naivniZisk(asistentky)
	print string.format("naivní", *map(str, vysledek))
