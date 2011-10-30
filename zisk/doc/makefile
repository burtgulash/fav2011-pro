latexfile = zisk
texfiles  = dpZisk.py.tex naivniZisk.py.tex pygments.tex zisk.fig1.tex zisk.fig2.tex zisk.fig3.tex zisk.fig4.tex zisk.fig5.tex zisk.tex
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
