latexfile = mst
figures   = 
texfiles  = 
helpfiles = $(texfiles) makefile 


TEX = pdflatex
VIEWER = evince


show : pdf
	$(VIEWER) $(latexfile).pdf &

pdf : $(latexfile).pdf

tarball : $(latexfile).tar.gz

clean :
	rm *.dvi *.aux *.log *.out

$(latexfile).pdf : $(latexfile).tex $(helpfiles) $(figures)
	$(TEX) $(latexfile)

$(latexfile).tar.gz : $(figures) $(latexfile).tex $(helpfiles)
	tar -c $^ | gzip > $@
