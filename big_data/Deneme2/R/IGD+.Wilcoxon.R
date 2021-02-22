write("", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex",append=FALSE)
resultDirectory<-"C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/data"
latexHeader <- function() {
  write("\\documentclass{article}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\title{StandardStudy}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\usepackage{amssymb}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\author{A.J.Nebro}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{document}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\maketitle", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\section{Tables}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}

latexTableHeader <- function(problem, tabularString, latexTableFirstLine) {
  write("\\begin{table}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\caption{", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(problem, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(".IGD+.}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)

  write("\\label{Table:", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(problem, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(".IGD+.}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)

  write("\\centering", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{scriptsize}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\begin{tabular}{", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(tabularString, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write(latexTableFirstLine, "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\hline ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}

printTableLine <- function(indicator, algorithm1, algorithm2, i, j, problem) { 
  file1<-paste(resultDirectory, algorithm1, sep="/")
  file1<-paste(file1, problem, sep="/")
  file1<-paste(file1, indicator, sep="/")
  data1<-scan(file1)
  file2<-paste(resultDirectory, algorithm2, sep="/")
  file2<-paste(file2, problem, sep="/")
  file2<-paste(file2, indicator, sep="/")
  data2<-scan(file2)
  if (i == j) {
    write("-- ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  }
  else if (i < j) {
    if (is.finite(wilcox.test(data1, data2)$p.value) & wilcox.test(data1, data2)$p.value <= 0.05) {
      if (median(data1) <= median(data2)) {
        write("$\\blacktriangle$", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}
      else {
        write("$\\triangledown$", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}
    }
    else {
      write("--", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
    }
  }
  else {
    write(" ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  }
}

latexTableTail <- function() { 
  write("\\hline", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{tabular}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{scriptsize}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
  write("\\end{table}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}

latexTail <- function() { 
  write("\\end{document}", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
}

### START OF SCRIPT 
# Constants
problemList <-c("AppWithSmp") 
algorithmList <-c("NSGAIIt27", "SPEA2t27") 
tabularString <-c("lc") 
latexTableFirstLine <-c("\\hline  & SPEA2t27\\\\ ") 
indicator<-"IGD+"

 # Step 1.  Writes the latex header
latexHeader()
tabularString <-c("| l | p{0.15cm } | ") 

latexTableFirstLine <-c("\\hline \\multicolumn{1}{|c|}{} & \\multicolumn{1}{c|}{SPEA2t27} \\\\") 

# Step 3. Problem loop 
latexTableHeader("AppWithSmp ", tabularString, latexTableFirstLine)

indx = 0
for (i in algorithmList) {
  if (i != "SPEA2t27"){
)    write(i , "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
    write(" & ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)

    jndx = 0
    for (j in algorithmList) {
      for (problem in problemList) {
        if (jndx != 0) {
          if (i != j) {
            printTableLine(indicator, i, j, indx, jndx, problem)
          }
          else {
            write("  ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
          } 
          if (problem == "AppWithSmp"){
)            if (j == "SPEA2t27"){
)              write(" \\\\ ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
            } 
            else {
              write(" & ", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
            }
          }
     else {
    write("&", "C:\Users\nilay\eclipse-workspace-salt\optimization/Deneme2/R/IGD+.Wilcoxon.tex", append=TRUE)
     }
        }
      }
      jndx = jndx + 1
}
    indx = indx + 1
  }
} # for algorithm

  latexTableTail()

#Step 3. Writes the end of latex file 
latexTail()

