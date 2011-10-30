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
