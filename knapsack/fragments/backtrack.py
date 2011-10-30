	polozky = []
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
