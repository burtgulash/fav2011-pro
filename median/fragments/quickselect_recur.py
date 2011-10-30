def quickselect(ar, levy, pravy, K):
	if levy == pravy:
		return ar[levy]
	nahodnyIndex = random.randint(levy, pravy)
	pivotIndex = rozdelit(ar, levy, pravy, nahodnyIndex)
	if pivotIndex == K:
		return ar[pivotIndex]
	if pivotIndex < K:
		return quickselect(ar, pivotIndex + 1, pravy, K)
	else:
		return quickselect(ar, levy, pivotIndex - 1, K)
