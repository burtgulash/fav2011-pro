#! /usr/bin/python

import random, sys

def generateItems(n, maxValue, maxWeight):
	for i in range(n):
		print random.randint(1, maxValue), random.randint(1, maxWeight)

if __name__ == "__main__":
	try:
		n = int(sys.argv[1])
		maxValue = int(sys.argv[2])
		maxWeight = int(sys.argv[3])
	except:
		print "usage: gen.py ITEMS MAXVALUE MAXWEIGHT"
		sys.exit(1)

	generateItems(n, maxValue, maxWeight)
