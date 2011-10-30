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
