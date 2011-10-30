#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys, random

def rozdelit(ar, levy, pravy, p):
	pivot = ar[p]
	i, j = levy, levy
	ar[pravy], ar[p] = ar[p], ar[pravy]
	while i <= pravy:
		if ar[i] < pivot:
			ar[i], ar[j] = ar[j], ar[i]
			j += 1
		i += 1
	ar[pravy], ar[j] = ar[j], ar[pravy]
	return j


def quickselect(ar, levy, pravy, K):
	while levy != pravy:
		nahodnyIndex = random.randint(levy, pravy)
		pivotIndex = rozdelit(ar, levy, pravy, nahodnyIndex)
		if pivotIndex == K:
			return ar[pivotIndex]
		if pivotIndex < K:
			levy = pivotIndex + 1
		else:
			pravy = pivotIndex - 1
	return ar[levy]


def median(ar):
	levy, pravy = 0, len(ar) - 1
	K = len(ar) // 2 - 1        # pole začíná nulou, odečíst 1
	return quickselect(ar, levy, pravy, K)
	

if __name__ == "__main__":
	ar = []
	while True:
		try:
			n = raw_input()
			try:
				n = int(n)
				ar.append(n)
			except ValueError:
				print "%s: hodnota není celé číslo" % sys.argv[0]
		except EOFError:
			break

	print median(ar)
		
