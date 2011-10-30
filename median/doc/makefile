latexfile = median
texfiles  = median.tex pygments.tex quickselect.py.tex quickselect_recur.py.tex rozdelit.fig.tex rozdelit.py.tex
helpfiles = $(texfiles) makefile 


TEX = pdflatex
VIEWER = evince


show : pdf
	$(VIEWER) $(latexfile).pdf &

pdf : $(latexfile).pdf

tarball : $(latexfile).tar.gz

clean :
	rm *.dvi *.aux *.log *.out

$(latexfile).pdf : $(latexfile).tex $(helpfiles)
	$(TEX) $(latexfile).tex

$(latexfile).tar.gz : $(figures) $(latexfile).tex $(helpfiles)
	tar -c $^ | gzip > $@
