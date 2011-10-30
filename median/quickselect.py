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
