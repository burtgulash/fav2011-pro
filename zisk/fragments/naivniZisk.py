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
